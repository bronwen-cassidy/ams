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

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import com.xioq.dasacumen.model.common.InternalResource;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
public class InternalResourcesHibernateTest 
{
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
	
	static final String forenames = "James";
	static final String gender = "Male";
	static final String position = "Developer";
	static final String reportTo = "Team Leader";
	static final String surname = "Bond";
	static final String uid = "18256";
	
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	private static final Integer tenantId = 1;
	
	private static final Long id = 1L;
		
	/**
	 * Internal resource used for testing.
	 */
	InternalResource internalResource;
	
	/**
	 * Sets up a test system info.
	 */
	@Before
	public void setup()
	{
		internalResource = new InternalResource();
		
		internalResource.setCreatedBy(createdBy);
		internalResource.setCreatedDate(createdDate);
		internalResource.setForenames(forenames);
		internalResource.setGender(gender);
		internalResource.setLastUpdatedBy(lastUpdatedBy);
		internalResource.setLastUpdatedDate(lastUpdatedDate);
		internalResource.setPosition(position);
		internalResource.setReportTo(reportTo);
		internalResource.setSurname(surname);
		internalResource.setTenantId(tenantId);
		internalResource.setUid(uid);
						
		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("parties");
	}
	 
	/**
	 * Tests if an Internal Resource can be created correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/internalResourcesTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/internalResourcesTestData.xml", table = "acumen.internal_resources", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveInternalResourcesTest()
	{
		// Retrieve internal resource	
		InternalResource retrievedInternalResource = hibernateDAO.retrieve(InternalResource.class, id);
		
		assertEquals(forenames, retrievedInternalResource.getForenames());
		assertEquals(surname, retrievedInternalResource.getSurname());
		assertEquals(gender, retrievedInternalResource.getGender());
		
		assertEquals(createdBy, retrievedInternalResource.getCreatedBy());
		assertNotNull(retrievedInternalResource.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedInternalResource.getLastUpdatedBy());
		assertNotNull(retrievedInternalResource.getLastUpdatedDate());
	}
	
	/**
	 * Test to create an Internal Resource object then retrieve this object to check that it was created successfully
	 **/
	@Test
	@ExpectedDatabase(value = "classpath:dbunit/internalResourcesTestData.xml", table = "acumen.internal_resources", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createInternalResourceTest()
	{
		hibernateDAO.create(internalResource);
		flushAndClear();
		
		InternalResource retrievedInternalResource = hibernateDAO.retrieve(InternalResource.class, id);
		
		assertEquals(forenames, retrievedInternalResource.getForenames());
		assertEquals(surname, retrievedInternalResource.getSurname());
		assertEquals(gender, retrievedInternalResource.getGender());
		
		assertEquals(createdBy, retrievedInternalResource.getCreatedBy());
		assertNotNull(retrievedInternalResource.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedInternalResource.getLastUpdatedBy());
		assertNotNull(retrievedInternalResource.getLastUpdatedDate());
	}
	
	/**
	 * Update existing data regarding Internal Resources in the database.
	 */
	@Test
	@Transactional
	@DatabaseSetup("classpath:dbunit/internalResourcesTestData.xml")
	public void updateInternalResourceTest()
	{	
		InternalResource internalResourceToUpdate = hibernateDAO.retrieve(InternalResource.class, id);
		
		final String updatedForenames = "Peter";
		final String updatedSurname = "Griffin";
		final String updatedGender = "M";
		
		internalResourceToUpdate.setForenames(updatedForenames);
		internalResourceToUpdate.setSurname(updatedSurname);
		internalResourceToUpdate.setGender(updatedGender);
		
		hibernateDAO.update(internalResourceToUpdate);
		flushAndClear();
		
		InternalResource internalResourceToUpdate2 = hibernateDAO.retrieve(InternalResource.class, internalResourceToUpdate.getId());
		
		assertEquals(updatedForenames, internalResourceToUpdate2.getForenames());
		assertEquals(updatedSurname, internalResourceToUpdate2.getSurname());
		assertEquals(updatedGender, internalResourceToUpdate2.getGender());
		
		assertEquals(createdBy, internalResourceToUpdate2.getCreatedBy());
		assertNotNull(internalResourceToUpdate2.getCreatedDate());
		assertEquals(lastUpdatedBy, internalResourceToUpdate2.getLastUpdatedBy());
		assertNotNull(internalResourceToUpdate2.getLastUpdatedDate());
	}
	
	/**
	 * Delete an existing Internal Resource in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/internalResourcesTestData.xml")
	public void deleteSystemInfoTest()
	{	
		InternalResource retrievedInternalResource = hibernateDAO.retrieve(InternalResource.class, id);

		hibernateDAO.delete(retrievedInternalResource);
		flushAndClear();
		
		InternalResource retrievedInternalResource2 = hibernateDAO.retrieve(InternalResource.class, id);
		
		// Will trigger the exception
		System.out.println(retrievedInternalResource2.getForenames());
	}
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
		public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
	
}
