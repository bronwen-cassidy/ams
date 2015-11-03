package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import com.xioq.dasacumen.model.systemadmin.Tenant;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
/**
 * Tests that the tenant object interacts with hibernate and the db correctly.
 * @author echhung
 *
 */

public class TenantHibernateTest {
	@Autowired
	private TestUtil testUtil;

	/*
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;

	/*
	 * Creates an instance of session factory so that the session cache can be cleared
	 * to avoid false positives on tests
	 */
	@Autowired @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	// Doc Links specific fields.
	private static final String bucketName =  "AMAZONBUCKET";
	private static final String companyName = "BrightFuture";
	// entity type uses the enum so no need for variable here.

	// Audit fields
//	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	private static final Integer versionNumber = 0;

	// Should only be used for retrieving as these values are set by db or dbunit
	private static final Long TEST_DBUNIT_ID_1L = 1L;
	private static final Long TEST_DBUNIT_ID_2L = 2L;

	/**
	 * Tenant used for testing.
	 */
	Tenant testTenant ;

	/**
	 * Sets up a test doc Link
	 */
	@Before
	public void setup() {
		testTenant = new Tenant();
		
		testTenant.setCompanyName(companyName);
		testTenant.setBucketName(bucketName);

//		testTenant.setTenantId(tenantId);
		testTenant.setCreatedBy(createdBy);
		testTenant.setCreatedDate(createdDate);
		testTenant.setLastUpdatedBy(lastUpdatedBy);
		testTenant.setLastUpdatedDate(lastUpdatedDate);
		testTenant.setVersionNumber(versionNumber);
		
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("docs");
		testUtil.resetSequences("doc_links");
		testUtil.resetSequences("tenants");
	}

	/**
	 * Tests if a tenant can be created correctly.
	 */
	@Test
	// 	No setup is required for tenant
	@ExpectedDatabase(value = "classpath:dbunit/tenantTestDataWithoutDate.xml", table = "acumen.tenants", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createTenantTest() {
		hibernateDAO.create(testTenant);
		flushAndClear();

		Tenant retrievedTenant = hibernateDAO.retrieve(Tenant.class, testTenant.getId());
		
		assertEquals(bucketName, retrievedTenant.getBucketName());
		assertEquals(companyName, retrievedTenant.getCompanyName());
		
		assertEquals(createdBy, retrievedTenant.getCreatedBy());
		assertNotNull(retrievedTenant.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedTenant.getLastUpdatedBy());
		assertNotNull(retrievedTenant.getLastUpdatedDate());
	}

	/**
	 * Tests if a Tenant can be retrieved correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/tenantTestDataWithTestTenant.xml")
	@ExpectedDatabase(value = "classpath:dbunit/tenantTestDataWithTestTenant.xml", table = "acumen.tenants", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveTenantTest() {
		Tenant retrievedTenant = hibernateDAO.retrieve(Tenant.class, TEST_DBUNIT_ID_1L);
		
		assertEquals(bucketName, retrievedTenant.getBucketName());
		assertEquals(companyName, retrievedTenant.getCompanyName());

		assertEquals(createdBy, retrievedTenant.getCreatedBy());
		assertNotNull(retrievedTenant.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedTenant.getLastUpdatedBy());
		assertNotNull(retrievedTenant.getLastUpdatedDate());
	}

	/**
	 * Tests if updating the fields on the object actually update existing fields in the db.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/tenantTestDataWithTestTenant.xml")
	public void updateTenantTest() {
		// Updated fields
		final String updatedCompanyName = "TheCompany";
		final String updatedBucketName = "AMAZONBUCKET123433224422";

		//Retrieve the Tenant
		Tenant retrievedTenant = hibernateDAO.retrieve(Tenant.class, TEST_DBUNIT_ID_1L);

		// Set the new updated fields
		retrievedTenant.setBucketName(updatedBucketName);
		retrievedTenant.setCompanyName(updatedCompanyName);
		
		// Do the update
		hibernateDAO.update(retrievedTenant);
		flushAndClear();

		// retrieve the updated doc
		Tenant retrievedTenant2 = hibernateDAO.retrieve(Tenant.class, retrievedTenant.getId());

		// Assert values are stored correctly
		assertEquals(updatedBucketName, retrievedTenant.getBucketName());
		assertEquals(updatedCompanyName, retrievedTenant.getCompanyName());
		
		assertNotNull(retrievedTenant2.getLastUpdatedBy());
		assertNotNull(retrievedTenant2.getLastUpdatedDate());
	}
	
	/**
	 * Tests if certain audit fields are non updateable. 
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/tenantTestDataWithTestTenant.xml")
	public void failToUpdateNonUpdatableTenantFields() {
		// Updated fields
		String UpdatedCreatedBy = "darthVader@jla.co.uk";
		Date UpdatedCreatedDate = new Date(22, 10, 21);
		
		//Retrieve the Doc to update
		Tenant retrievedTenant = hibernateDAO.retrieve(Tenant.class, TEST_DBUNIT_ID_1L);
		
		// Set asset with new fields.
		retrievedTenant.setCreatedBy(UpdatedCreatedBy);
		retrievedTenant.setCreatedDate(UpdatedCreatedDate);
		
		// Update the Doc
		hibernateDAO.update(retrievedTenant);
		flushAndClear();
		
		// Retrieve it again
		Tenant retrievedTenant2 = hibernateDAO.retrieve(Tenant.class, TEST_DBUNIT_ID_1L);
		
		// Asserts fields are not changed.
		assertNotEquals(UpdatedCreatedBy, retrievedTenant2.getCreatedBy());
		assertNotEquals(UpdatedCreatedDate, retrievedTenant2.getCreatedDate());
	}

	/**
	 * Tests if a Tenant can be successfully deleted.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/tenantTestDataWithTestTenant.xml")
	public void deleteTenantTest() {
		Tenant retrievedTenant = hibernateDAO.retrieve(Tenant.class, TEST_DBUNIT_ID_1L);

		hibernateDAO.delete(retrievedTenant);
		flushAndClear();

		Tenant retrievedTenant2= hibernateDAO.retrieve(Tenant.class, retrievedTenant.getId());

		// Will trigger the exception
		System.out.println(retrievedTenant2.getBucketName());
	}
	
	/**
	 * Test to check that in the scenario where two users edit the same version of an entity but update at different times
	 * the user who tries to update last will get a stalestateexception because the data is now out of date and they will
	 * have to retrieve the entity again.
	 */
	@Test(expected=StaleStateException.class)
	@Transactional
	public void staleStateExceptionTest()
	{	
		// 1st user creates the tenant (version number = 0)
		testTenant.setBucketName(bucketName);
		testTenant.setCompanyName(companyName);
		hibernateDAO.create(testTenant);
		flushAndClear();

		// 2nd user retrieves (version number = 0)
		Tenant sameTenant = hibernateDAO.retrieve(testTenant.getClass(), testTenant.getId());
		flushAndClear();
		
		//1st user then updates row (version number becomes 1)
		testTenant.setBucketName("Google Bucket");
		testTenant.setCompanyName("Google");
		hibernateDAO.update(testTenant);
		flushAndClear();
		assertEquals("Version number not incremented", 1, testTenant.getVersionNumber().intValue());

		// 2nd user has old data (version number = 0) and tries to update
		sameTenant.setBucketName("Microsoft Bucket");
		sameTenant.setCompanyName("Microsoft");
		// This should throw stale state exception
		hibernateDAO.update(sameTenant);
		flushAndClear();
	}
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}