package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.xioq.dasacumen.model.common.Website;
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
 * Tests websites intereacts with hibernate and the db correctly.
 * @author echhung
 *
 */
public class WebsiteHibernateTest 
{
	@Autowired
	private TestUtil testUtil;
	
	/**
	 * Website used for testing.
	 */
	Website testWebsite;

	static final String websiteUrl = "www.google.com";
	
	static final String createdBy = "audit";	
	static final String lastUpdatedBy = "audit";
	
	// Should only be used for retrieving as these values are set by db
	private static final Long TEST_DB_UNIT_ID1 = 1L;
	private static final Long TEST_DB_UNIT_ID2 = 2L;
	private static final Integer versionNumber = 0;
	
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
	
	@Before
	public void setup()
	{
		testWebsite = new Website();
		testWebsite.setWebsiteUrl(websiteUrl);
		
		testUtil.resetSequences("websites");
	}

	/**
	 * Test to retrieve the website from the database to check that it can be retrieved.
	 */
	@Test
	@DatabaseSetup(value = "classpath:dbunit/websiteTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/websiteTestData.xml", table = "acumen.websites", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveWebsiteTest()
	{
		Website retrievedWebsite = hibernateDAO.retrieve(Website.class, TEST_DB_UNIT_ID1);
		
		assertEquals(websiteUrl, retrievedWebsite.getWebsiteUrl());
		
		// These are set by the interceptor
		assertEquals(createdBy, retrievedWebsite.getCreatedBy());
		assertEquals(lastUpdatedBy, retrievedWebsite.getLastUpdatedBy());

		// The other audit values will be null because the interceptor is not being used.
	}
	
	/**
	 * Test to create an instance of Website then check this is created successfully.
	 */
	@Test
	@ExpectedDatabase(value = "classpath:dbunit/websiteTestData.xml", table = "acumen.websites", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createWebsiteTest()
	{
		hibernateDAO.create(testWebsite);
		flushAndClear();
		
		Website retrievedWebsite = hibernateDAO.retrieve(Website.class, TEST_DB_UNIT_ID1);
				
		assertEquals(websiteUrl, retrievedWebsite.getWebsiteUrl());

		assertEquals(createdBy, retrievedWebsite.getCreatedBy());
		assertEquals(lastUpdatedBy, retrievedWebsite.getLastUpdatedBy());
		
		assertNotNull(retrievedWebsite.getCreatedDate());
		assertNotNull(retrievedWebsite.getLastUpdatedDate());
		assertNotNull(retrievedWebsite.getVersionNumber());
		assertNotNull(retrievedWebsite.getTenantId());
	}
	
	/**
	 * Test to create an instance of Website and then update the details within this and check 
	 * that this has been updated successfully
	 */
	@Test
	@Transactional
    @DatabaseSetup("classpath:dbunit/websiteTestData.xml")
	public void updateWebsiteTest ()
	{	
		Website retrievedWebsite = hibernateDAO.retrieve(Website.class, TEST_DB_UNIT_ID1);
		final String updatedWebsiteUrl = "www.google.com";
		retrievedWebsite.setWebsiteUrl(updatedWebsiteUrl);
		
		hibernateDAO.update(retrievedWebsite);
		flushAndClear();
		
		Website retrievedWebsite2 = hibernateDAO.retrieve(Website.class, TEST_DB_UNIT_ID1);
		
		assertEquals(updatedWebsiteUrl, retrievedWebsite.getWebsiteUrl());

		assertNotNull(retrievedWebsite2.getCreatedBy());
		assertNotNull(retrievedWebsite2.getLastUpdatedBy());
		assertNotNull(retrievedWebsite2.getTenantId());
		
		//CreatedDate and last updated date are never set in dbunit or from interceptor thus no assert.
		assertNotNull(retrievedWebsite2.getVersionNumber());

	}
	
	/**
	 * Delete an existing Company in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@Transactional
	public void websitePersonTest()
	{	
		hibernateDAO.create(testWebsite);
		flushAndClear();
		
		hibernateDAO.delete(testWebsite);
		flushAndClear();
		
		// This should throw the exception ObjectNotFoundException
		Website retrievedWebsite = hibernateDAO.retrieve(Website.class,TEST_DB_UNIT_ID1);
		// Because of lazy loading, must do something with the retrieved user to trigger the load
		System.out.println("END:" + retrievedWebsite);
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
		// 1st user creates the company (version number = 0)
		testWebsite.setWebsiteUrl(websiteUrl);
		hibernateDAO.create(testWebsite);
		flushAndClear();

		// 2nd user retrieves (version number = 0)
		Website sameWebsite = hibernateDAO.retrieve(testWebsite.getClass(), testWebsite.getId());
		flushAndClear();
		
		//1st user then updates row (version number becomes 1)
		testWebsite.setWebsiteUrl("www.google.co.uk");
		hibernateDAO.update(testWebsite);
		flushAndClear();
		assertEquals("Version number not incremented", 1, testWebsite.getVersionNumber().intValue());

		// 2nd user has old data (version number = 0) and tries to update
		sameWebsite.setWebsiteUrl("www.gmail.com");
		// This should throw stale state exception
		hibernateDAO.update(sameWebsite);
		flushAndClear();
	}
	
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}
