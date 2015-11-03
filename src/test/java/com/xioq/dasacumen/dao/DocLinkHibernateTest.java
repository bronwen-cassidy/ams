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
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.model.document.Doc;
import com.xioq.dasacumen.model.document.DocLink;
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
 * Tests that the doc links object interacts with hibernate and the db correctly.
 * @author echhung
 *
 */

public class DocLinkHibernateTest {
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
	private static final Long docsId =  1L;
	private static final Long entityId = 1L;
	// entity type uses the enum so no need for variable here.

	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	// Should only be used for retrieving as these values are set by db or dbunit
	private static final Long TEST_DBUNIT_ID_1L = 1L;
	private static final Long TEST_DBUNIT_ID_2L = 2L;

	/**
	 * Doc used for testing.
	 */
	DocLink testDocLink ;

	/**
	 * Sets up a test doc Link
	 */
	@Before
	public void setup() {
		testDocLink = new DocLink();
		
		testDocLink.setEntityType(EntityType.ASSETS);
		testDocLink.setDocType(DocType.MAINIMAGE);

		testDocLink.setTenantId(tenantId);
		testDocLink.setCreatedBy(createdBy);
		testDocLink.setCreatedDate(createdDate);
		testDocLink.setLastUpdatedBy(lastUpdatedBy);
		testDocLink.setLastUpdatedDate(lastUpdatedDate);
		
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("docs");
		testUtil.resetSequences("doc_links");
		testUtil.resetSequences("tenants");
	}

	/**
	 * Tests if a doc Link can be created correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocTestDataWithTestDoc.xml")
	@ExpectedDatabase(value = "classpath:dbunit/DocLinkTestDataWithTestDocLink2.xml", table = "acumen.doc_links", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createDocLinkTest() {
		// Needed because prereq entities are only initialised in test thus they need to be set here.
		testDocLink.setDoc(hibernateDAO.retrieve(Doc.class, TEST_DBUNIT_ID_1L));
		testDocLink.setEntityId(entityId);

		hibernateDAO.create(testDocLink);
		flushAndClear();

		DocLink retrievedDocLink = hibernateDAO.retrieve(DocLink.class, testDocLink.getId());
		
		assertEquals(docsId, retrievedDocLink.getDoc().getId());
		assertEquals(entityId, retrievedDocLink.getEntityId());
		assertEquals(EntityType.ASSETS, retrievedDocLink.getEntityType());

		assertEquals(tenantId, retrievedDocLink.getTenantId());
		assertEquals(createdBy, retrievedDocLink.getCreatedBy());
		assertNotNull(retrievedDocLink.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedDocLink.getLastUpdatedBy());
		assertNotNull(retrievedDocLink.getLastUpdatedDate());
	}

	/**
	 * Tests if a doc can be retrieved correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocLinkTestDataWithTestDocLink.xml")
	@ExpectedDatabase(value = "classpath:dbunit/DocLinkTestDataWithTestDocLink.xml", table = "acumen.doc_links", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveDocLinkTest() {
		DocLink retrievedDocLink = hibernateDAO.retrieve(DocLink.class, TEST_DBUNIT_ID_1L);
		
		assertEquals(docsId, retrievedDocLink.getDoc().getId());
		assertEquals(entityId, retrievedDocLink.getEntityId());
		assertEquals(EntityType.ASSETS, retrievedDocLink.getEntityType());

		assertEquals(tenantId, retrievedDocLink.getTenantId());
		assertEquals(createdBy, retrievedDocLink.getCreatedBy());
		assertEquals(createdDate, retrievedDocLink.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedDocLink.getLastUpdatedBy());
		assertEquals(lastUpdatedDate, retrievedDocLink.getLastUpdatedDate());
	}

	/**
	 * Tests if the updating the fields on the object actually update existing fields in the db.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocLinkTestDataWithTestDocLink.xml")
	public void updateDocLinkTest() {
		// Updated fields
		final Long updatedDocsAndEntityId = 2L;
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, TEST_DBUNIT_ID_2L);

		//Retrieve the Doc
		DocLink retrievedDocLink = hibernateDAO.retrieve(DocLink.class, TEST_DBUNIT_ID_1L);

		// Set the new updated fields
		retrievedDocLink.setEntityId(updatedDocsAndEntityId);;
		retrievedDocLink.setDoc(retrievedDoc);
		retrievedDocLink.setEntityType(EntityType.COMPANIES);;

		// Do the update
		hibernateDAO.update(retrievedDocLink);
		flushAndClear();

		// retrieve the updated doc
		DocLink retrievedDocLink2 = hibernateDAO.retrieve(DocLink.class, retrievedDocLink.getId());

		// Assert values are stored correctly
		assertEquals(updatedDocsAndEntityId, retrievedDocLink2.getDoc().getId());
		assertEquals(updatedDocsAndEntityId, retrievedDocLink2.getEntityId());
		assertEquals(EntityType.COMPANIES, retrievedDocLink2.getEntityType());
	}
	
	/**
	 * Tests if certain audit fields are non updateable. 
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/DocLinkTestDataWithTestDocLink.xml")
	public void failToUpdateNonUpdatableDocLinkFields() {
		// Updated fields
		Integer UpdatedTenantId  = 202;
		String UpdatedCreatedBy = "darthVader@jla.co.uk";
		Date UpdatedCreatedDate = new Date(22, 10, 21);
		
		//Retrieve the Doc to update
		DocLink retrievedDocLink = hibernateDAO.retrieve(DocLink.class, TEST_DBUNIT_ID_1L);
		
		// Set asset with new fields.
		retrievedDocLink.setTenantId(UpdatedTenantId);
		retrievedDocLink.setCreatedBy(UpdatedCreatedBy);
		retrievedDocLink.setCreatedDate(UpdatedCreatedDate);
		
		// Update the Doc
		hibernateDAO.update(retrievedDocLink);
		flushAndClear();
		
		// Retrieve it again
		DocLink retrievedDocLink2 = hibernateDAO.retrieve(DocLink.class, retrievedDocLink.getId());
		
		
		// Asserts fields are not changed.
		assertEquals(tenantId, retrievedDocLink2.getTenantId());
		assertEquals(createdBy, retrievedDocLink2.getCreatedBy());
		assertEquals(createdDate, retrievedDocLink2.getCreatedDate());
	}

	/**
	 * Tests if a doc can be successfully deleted.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/DocLinkTestDataWithTestDocLink.xml")
	public void deleteDocLinkTest() {
		DocLink retrievedDocLink = hibernateDAO.retrieve(DocLink.class, TEST_DBUNIT_ID_1L);

		hibernateDAO.delete(retrievedDocLink);
		flushAndClear();

		DocLink retrievedDocLink2 = hibernateDAO.retrieve(DocLink.class, retrievedDocLink.getId());

		// Will trigger the exception
		System.out.println(retrievedDocLink2.getEntityType());
	}
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}