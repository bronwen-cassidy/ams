package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
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

import com.xioq.dasacumen.model.constants.DocType;
import com.xioq.dasacumen.model.document.Doc;
import com.xioq.dasacumen.model.systemadmin.Tenant;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
/**
 * Tests that the doc information interacts with hibernate and the db correctly.
 * @author echhung
 *
 */

public class DocHibernateTest {

	@Autowired
	TestUtil testUtil;
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

	// Doc specific fields.
	private static final Long tenantsId =  1L;
	private static final String objectKey = "FilePathOnS3";

	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	// Should only be used for retrieving as these values are set by db or dbunit
	private static final Long TEST_DBUNIT_ID_1L = 1L;

	/**
	 * Doc used for testing.
	 */
	Doc testDoc ;

	/**
	 * Sets up a test doc.
	 */
	@Before
	public void setup() {
		testDoc = new Doc();
		
		testDoc.setObjectKey(objectKey);
		testDoc.setDocType(DocType.MAINIMAGE);

		testDoc.setTenantId(tenantId);
		testDoc.setCreatedBy(createdBy);
		testDoc.setCreatedDate(createdDate);
		testDoc.setLastUpdatedBy(lastUpdatedBy);
		testDoc.setLastUpdatedDate(lastUpdatedDate);
		
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("docs");
		testUtil.resetSequences("doc_links");
		testUtil.resetSequences("tenants");
	}

	/**
	 * Tests if a doc can be created correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/DocTestDataWithTestDoc2.xml", table = "acumen.docs", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createDocLinkTest() {
		// Needed because prereq entities are only initialised in test thus they need to be set here.
		testDoc.setTenant(hibernateDAO.retrieve(Tenant.class, tenantsId));

		testDoc.setDocType(DocType.MAINIMAGE);
		hibernateDAO.create(testDoc);
		flushAndClear();

		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, testDoc.getId());

		assertEquals(tenantsId, retrievedDoc.getTenant().getId());
		assertEquals(objectKey, retrievedDoc.getObjectKey());
		assertEquals(DocType.MAINIMAGE, retrievedDoc.getDocType());

		assertNotNull(retrievedDoc.getTenantId());
		assertNotNull(retrievedDoc.getCreatedBy());
		assertNotNull(retrievedDoc.getCreatedDate());
		assertNotNull(retrievedDoc.getLastUpdatedBy());
		assertNotNull(retrievedDoc.getLastUpdatedDate());
	}

	/**
	 * Tests if a doc can be retrieved correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocTestDataWithTestDoc2Date.xml")
	//DocTestDataWithTestDoc2 has created date and updated date removed
	@ExpectedDatabase(value = "classpath:dbunit/DocTestDataWithTestDoc2Date.xml", table = "acumen.docs", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveDocTest() {
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, TEST_DBUNIT_ID_1L);

		// Assert values are stored correctly
		assertEquals(tenantsId, retrievedDoc.getTenant().getId());
		assertEquals(objectKey, retrievedDoc.getObjectKey());
		assertEquals(DocType.MAINIMAGE, retrievedDoc.getDocType());

		assertEquals(createdBy, retrievedDoc.getCreatedBy());
		assertNotNull(retrievedDoc.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedDoc.getLastUpdatedBy());
		assertNotNull(retrievedDoc.getLastUpdatedDate());
	}

	/**
	 * Tests if the updating the fields on the object actually update existing fields in the db.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocTestDataWithTestDoc.xml")
	public void updateDocTest() {
		// Updated fields
		final Long updatedTenantId = 2L;
		final String updateedObjectKey = "TheUpdatedFilePathOnS3";		
		final Tenant updatedTenant = hibernateDAO.retrieve(Tenant.class, updatedTenantId);
		
		final String updatedUpdatedBy = "Gandalf@jla.co.uk";		
		final Date updatedUpdatedDate = new Date(200, 12, 11);		

		//Retrieve the Doc
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, TEST_DBUNIT_ID_1L);

		// Set the new updated fields
		retrievedDoc.setTenant(updatedTenant);
		retrievedDoc.setObjectKey(updateedObjectKey);
		retrievedDoc.setLastUpdatedBy(updatedUpdatedBy);
		retrievedDoc.setLastUpdatedDate(updatedUpdatedDate);
		retrievedDoc.setDocType(DocType.MAINIMAGE);

		// Do the update
		hibernateDAO.update(retrievedDoc);
		flushAndClear();

		// retrieve the updated doc
		Doc retrievedDoc2 = hibernateDAO.retrieve(Doc.class, retrievedDoc.getId());

		// Assert values are stored correctly
		assertEquals(updatedTenant.getId(), retrievedDoc2.getTenant().getId());
		assertEquals(updateedObjectKey, retrievedDoc2.getObjectKey());
		assertEquals(DocType.MAINIMAGE, retrievedDoc2.getDocType());
		
		assertNotNull(retrievedDoc.getLastUpdatedBy());
		assertNotNull(retrievedDoc.getLastUpdatedDate());
	}
	
	/**
	 * Tests if certain audit fields are non updateable. 
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocTestDataWithTestDoc.xml")
	public void failToUpdateNonUpdatableDocFields() {
		// Updated fields
		Integer UpdatedTenantId  = 202;
		String UpdatedCreatedBy = "darthVader@jla.co.uk";
		Date UpdatedCreatedDate = new Date(22, 10, 21);
		
		//Retrieve the Doc to update
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, TEST_DBUNIT_ID_1L);
		
		// Set asset with new fields.
		retrievedDoc.setTenantId(UpdatedTenantId);
		retrievedDoc.setCreatedBy(UpdatedCreatedBy);
		retrievedDoc.setCreatedDate(UpdatedCreatedDate);
		
		// Update the Doc
		hibernateDAO.update(retrievedDoc);
		flushAndClear();
		
		// Retrieve it again
		Doc retrievedDoc2 = hibernateDAO.retrieve(Doc.class, retrievedDoc.getId());
		
		
		// Asserts fields are not changed.
		assertEquals(tenantId, retrievedDoc2.getTenantId());
		assertNotNull(retrievedDoc2.getCreatedBy());
		assertEquals(createdDate, retrievedDoc2.getCreatedDate());
	}

	/**
	 * Tests if a doc can be successfully deleted.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/DocTestDataWithTestDoc.xml")
	public void deleteDocTest() {
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, TEST_DBUNIT_ID_1L);

		hibernateDAO.delete(retrievedDoc);
		flushAndClear();

		Doc retrievedDoc2 = hibernateDAO.retrieve(Doc.class, retrievedDoc.getId());

		// Will trigger the exception
		System.out.println(retrievedDoc2.getObjectKey());
	}
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}