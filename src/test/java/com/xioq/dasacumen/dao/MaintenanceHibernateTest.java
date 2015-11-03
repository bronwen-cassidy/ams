package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;
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

import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.assetregister.Maintenance;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.document.Doc;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
/**
 * testing CRUD operations for Maintenance.
 * @author nmarlor
 *
 */
public class MaintenanceHibernateTest
{
	@Autowired
	private TestUtil testUtil;
	
	/*
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;
	
	/*
	 * Creates an instance of session factory so that the session cache can be
	 * cleared
	 * to avoid false positives on tests
	 */
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Maintenance used for testing.
	 */
	Maintenance testMaintenance;
	
	private static final Boolean maintenanceMandatory = true;
	private static final String maintenanceMandatoryNote = "maintenance mandatory";
	private static final Boolean maintenanceDocumentType = true;
	
	private static final Boolean ppmRequired = true;
	private static final String ppmRequiredNote = "ppm required";
	
	private static final Boolean tag = true;
	private static final String tagNote = "tag required";
	
	private static final Boolean thirdPartyProvider = true;
	
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	private static final Integer tenantId = 1;
	
	private static final Integer versionNumber = 1;
	
	private static final Long id = 1L;
	
//	Assets testAsset = new Assets();
//	Company testCompany = new Company();


	@Before
	public void setup()
	{	
		testMaintenance = new Maintenance();
		
		testMaintenance.setMaintenanceDocumentType(maintenanceDocumentType);
		testMaintenance.setMaintenanceMandatory(maintenanceMandatory);
		testMaintenance.setMaintenanceMandatoryNote(maintenanceMandatoryNote);
		testMaintenance.setPpmRequired(ppmRequired);
		testMaintenance.setPpmRequiredNote(ppmRequiredNote);
		testMaintenance.setTag(tag);
		testMaintenance.setTagNote(tagNote);
		testMaintenance.setThirdPartyProvider(thirdPartyProvider);
		
		testMaintenance.setCreatedBy(createdBy);
		testMaintenance.setCreatedDate(createdDate);
		testMaintenance.setLastUpdatedBy(lastUpdatedBy);
		testMaintenance.setLastUpdatedDate(lastUpdatedDate);
		testMaintenance.setTenantId(tenantId);
		testMaintenance.setVersionNumber(versionNumber);
		
		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("tenants");
		testUtil.resetSequences("docs");
		testUtil.resetSequences("maintenance");
	}
    
    /**
   	 * Creates a Maintenance and retrieves it from the database. 
   	 */
   	@Test
   	@DatabaseSetup("classpath:dbunit/maintenanceTestData_expected.xml")
	@ExpectedDatabase(value = "classpath:dbunit/maintenanceTestData_expected.xml", table = "acumen.maintenance", assertionMode = DatabaseAssertionMode.NON_STRICT)
   	public void retrieveMaintenanceTest() 
   	{
   		// Retrieve a maintenance
   		Maintenance retrievedMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
   		
   		assertEquals(maintenanceMandatoryNote, retrievedMaintenance.getMaintenanceMandatoryNote());
   		assertEquals(maintenanceMandatory, retrievedMaintenance.getMaintenanceMandatory());
   		assertEquals(ppmRequired, retrievedMaintenance.getPpmRequired());
   		assertEquals(tag, retrievedMaintenance.getTag());
   		assertEquals(tagNote, retrievedMaintenance.getTagNote());
   		
   		assertNotNull(retrievedMaintenance.getAsset());
   		
		assertEquals(createdBy, retrievedMaintenance.getCreatedBy());
		assertNotNull(retrievedMaintenance.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedMaintenance.getLastUpdatedBy());
		assertNotNull(retrievedMaintenance.getLastUpdatedDate());
   	}
	
	/**
	 * Test to create in instance of Maintenance and then check that this has been created successfully
	 **/
	@Test
	@DatabaseSetup("classpath:dbunit/maintenanceTestData_setup.xml")
	@ExpectedDatabase(value = "classpath:dbunit/maintenanceTestDataNoDate_expected.xml", table = "acumen.maintenance", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createMaintenanceTest()
	{	
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, id);
		Company retrievedCompany = hibernateDAO.retrieve(Company.class, id);
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, id);
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, id);
		
		testMaintenance.setAsset(retrievedAsset);
		testMaintenance.setAssetsId(retrievedAsset.getId());
		testMaintenance.setThirdPartySupplier(retrievedCompany);
		testMaintenance.setThirdPartySupplierId(retrievedCompany.getId());
		
		testMaintenance.setFaultCodeId(id);
		testMaintenance.setFaultCode(retrievedUserData);
		testMaintenance.setFaultCodeCategory(retrievedUserData);
		testMaintenance.setFaultCodeCategoryId(id);
		
		testMaintenance.setDocumentType(retrievedDoc.getDocType());
		testMaintenance.setThirdPartyProvider(true);
		
		hibernateDAO.create(testMaintenance);
		flushAndClear();
		
   		Maintenance retrievedMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
		flushAndClear();
   		
   		assertEquals(maintenanceMandatoryNote, retrievedMaintenance.getMaintenanceMandatoryNote());
   		assertEquals(maintenanceMandatory, retrievedMaintenance.getMaintenanceMandatory());
   		assertEquals(ppmRequired, retrievedMaintenance.getPpmRequired());
   		assertEquals(tag, retrievedMaintenance.getTag());
   		assertEquals(tagNote, retrievedMaintenance.getTagNote());
   		
		assertEquals(createdBy, retrievedMaintenance.getCreatedBy());
		assertNotNull(retrievedMaintenance.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedMaintenance.getLastUpdatedBy());
		assertNotNull(retrievedMaintenance.getLastUpdatedDate());
	}
	
	/**
	 * Update an existing Maintenance in the database.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/maintenanceTestData_expected.xml")
	public void updateMaintenanceTest()
	{	
   		Maintenance retrievedMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
   		
   		final String updatedMandatoryNote = "No longer needed";
   		final Boolean updatedMaintenanceMandatory = false;
   		final String updatedTagNote = "Tag no longer needed";
   		final Boolean updatedTag = false;
   		final Boolean updatedPpmRequired = false;
   		
   		retrievedMaintenance.setMaintenanceMandatoryNote(updatedMandatoryNote);
   		retrievedMaintenance.setMaintenanceMandatory(updatedMaintenanceMandatory);
   		retrievedMaintenance.setTag(updatedTag);
   		retrievedMaintenance.setTagNote(updatedTagNote);
   		retrievedMaintenance.setPpmRequired(updatedPpmRequired);
   		
   		hibernateDAO.update(retrievedMaintenance);
   		flushAndClear();
   		
   		Maintenance retrievedMaintenance2 = hibernateDAO.retrieve(Maintenance.class, retrievedMaintenance.getId());
   		
   		assertEquals(updatedMandatoryNote, retrievedMaintenance2.getMaintenanceMandatoryNote());
   		assertEquals(updatedMaintenanceMandatory, retrievedMaintenance2.getMaintenanceMandatory());
   		assertEquals(updatedPpmRequired, retrievedMaintenance2.getPpmRequired());
   		assertEquals(updatedTag, retrievedMaintenance2.getTag());
   		assertEquals(updatedTagNote, retrievedMaintenance2.getTagNote());
   		
		assertEquals(createdBy, retrievedMaintenance2.getCreatedBy());
		assertNotNull(retrievedMaintenance2.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedMaintenance2.getLastUpdatedBy());
		assertNotNull(retrievedMaintenance2.getLastUpdatedDate());
	}
	
	/**
	 * Delete an existing Maintenance in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/maintenanceTestData_expected.xml")
	public void deleteMaintenanceTest()
	{		
   		Maintenance retrievedMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
   		
   		hibernateDAO.delete(retrievedMaintenance);
   		flushAndClear();
   		
   		Maintenance retrievedMaintenance2 = hibernateDAO.retrieve(Maintenance.class, retrievedMaintenance.getId());
		
   		// Will trigger the exception
		System.out.println(retrievedMaintenance2.getMaintenanceMandatoryNote());
	}
	
	/*
	 * Test to check that update when the version number is changed should throw a stale state exception 
	 */
	@Test(expected =StaleStateException.class)
	@DatabaseSetup("classpath:dbunit/maintenanceTestData_expected.xml")
	public void staleStateExceptionTest()
	{
   		Maintenance firstMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
   		flushAndClear();
   		
   		Maintenance sameMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
   		flushAndClear();
   		
   		firstMaintenance.setMaintenanceMandatoryNote("Testing first maintenance");
   		hibernateDAO.update(firstMaintenance);
   		flushAndClear();
   		
		// Should get an error as the first update should have incremented (by
		// hibernate) the version number
   		sameMaintenance.setMaintenanceMandatoryNote("Testing setting same maintenance");
   		hibernateDAO.update(sameMaintenance);
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
