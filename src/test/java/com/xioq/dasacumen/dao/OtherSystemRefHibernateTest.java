package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.OtherSystemRef;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners(
{ DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })

public class OtherSystemRefHibernateTest {
	
	@Autowired
	private TestUtil testUtil;
	
	/**
	 * OtherSystemRef used for testing.
	 */
	OtherSystemRef testOtherSysRef;
	
	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	
	final String otherSystemId = "1";
		
	final int versionNumber = 0;
	
	final String uid = "party_uid_123456789";
	
	/**
	 * foreign key assets field
	 */
	Assets asset;
	
	/**
	 * foreign key user data field
	 */
	UserData userData;

	/**
	 * foreign key company party field
	 */
	Company companyParty;

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
	
	
	final Long udTypeAndCat = 1L;
	
	
	@Before
	public void setup()
	{
		
		testOtherSysRef = new OtherSystemRef();
		
		testOtherSysRef.setOtherSystemId(otherSystemId);
		
		testOtherSysRef.setTenantId(tenantId);
		testOtherSysRef.setCreatedBy(createdBy);
		testOtherSysRef.setCreatedDate(createdDate);
		testOtherSysRef.setLastUpdatedBy(lastUpdatedBy);
		testOtherSysRef.setLastUpdatedDate(lastUpdatedDate);
		testOtherSysRef.setVersionNumber(versionNumber);
		
		testUtil.resetSequences("other_system_ref");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("assets");
		
	}
	 
	 /**
	 * Retrieves an existing OtherSystemRef from the database. 
  	 */
	 @Test
	 @DatabaseSetup("classpath:dbunit/otherSystemRefTestDataWithTestOtherSystemRef.xml")
	 @ExpectedDatabase(value = "classpath:dbunit/otherSystemRefTestDataWithTestOtherSystemRef.xml", table = "acumen.other_system_ref", assertionMode = DatabaseAssertionMode.NON_STRICT)
	 public void retrieveOtherSystemRefTest() {
   		OtherSystemRef otherSystemRefRetrieved = hibernateDAO.retrieve(OtherSystemRef.class, 1L);
   		
   		assertEquals(createdBy, otherSystemRefRetrieved.getCreatedBy());
		assertNotNull(otherSystemRefRetrieved.getCreatedDate());
		assertEquals(lastUpdatedBy, otherSystemRefRetrieved.getLastUpdatedBy());
		assertNotNull(otherSystemRefRetrieved.getLastUpdatedDate());
			
		assertEquals(otherSystemId, otherSystemRefRetrieved.getOtherSystemId());
		assertNotNull(otherSystemRefRetrieved.getOtherSystemName());

		assertEquals((int)tenantId, (int)otherSystemRefRetrieved.getTenantId());
		assertEquals((int)versionNumber, (int)otherSystemRefRetrieved.getVersionNumber());
	} 
	   	
	/**
	* Creates OtherSysRef and tests if the data is present in db.
	*/
   	@Test
   	@DatabaseSetup("classpath:dbunit/otherSystemRefTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/otherSystemRefTestDataWithoutDate.xml", table="acumen.other_system_ref", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createOtherSystemRefTest()
	{
		Assets asset = hibernateDAO.retrieve(Assets.class, 1L);
		UserData userData = hibernateDAO.retrieve(UserData.class, 1L);
		
		testOtherSysRef.setAssets(asset);
		testOtherSysRef.setOtherSystemNameId(1L);
		testOtherSysRef.setOtherSystemName(userData);
   		
   		hibernateDAO.create(testOtherSysRef);
		flushAndClear();
			
		OtherSystemRef otherSysRefRetrieved = hibernateDAO.retrieve(OtherSystemRef.class, testOtherSysRef.getId());
			
		assertEquals(createdBy, otherSysRefRetrieved.getCreatedBy());
		assertNotNull(otherSysRefRetrieved.getCreatedDate());
		assertEquals(lastUpdatedBy, otherSysRefRetrieved.getLastUpdatedBy());
		assertNotNull(otherSysRefRetrieved.getLastUpdatedDate());
			
		assertEquals(otherSystemId, otherSysRefRetrieved.getOtherSystemId());
			
		assertEquals((int)tenantId, (int)otherSysRefRetrieved.getTenantId());
			
		assertEquals((int)versionNumber, (int)otherSysRefRetrieved.getVersionNumber());
	}
		
	/**
	 * Update an existing OtherSysRef in the database.
	 */
   	@Test
   	@DatabaseSetup("classpath:dbunit/otherSystemRefTestDataWithTestOtherSystemRef.xml")
	@Transactional
	public void updateOtherSystemRefTest()
	{
   		// Updated fields
   		final String updatedOtherSystemId = "2";
   		
   		//Retrieve the other system ref
   		OtherSystemRef retrievedOtherSystemRef = hibernateDAO.retrieve(OtherSystemRef.class, 1L);
   		
   		// Set the new updated field
   		retrievedOtherSystemRef.setOtherSystemId(updatedOtherSystemId);
   		
   		// Do the update
		hibernateDAO.update(retrievedOtherSystemRef);
   		flushAndClear();
   		
   		// retrieve the updated other system ref
   		OtherSystemRef retrievedOtherSystemRef2 = hibernateDAO.retrieve(OtherSystemRef.class, retrievedOtherSystemRef.getId());
   		
   		// Assert values are stored correctly
   		assertEquals(updatedOtherSystemId, retrievedOtherSystemRef.getOtherSystemId());
   		assertNotNull(retrievedOtherSystemRef2.getLastUpdatedBy());
   		assertNotNull(retrievedOtherSystemRef2.getLastUpdatedDate());			
	}
	
	/**
	 * Delete an existing OtherSysRef in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
   	@DatabaseSetup("classpath:dbunit/otherSystemRefTestDataWithTestOtherSystemRef.xml")
	public void deleteOtherSystemRefTest()
	{		
   		OtherSystemRef retrievedOtherSystemRef = hibernateDAO.retrieve(OtherSystemRef.class, 1L);
   		flushAndClear();
   		
   		//delete operation 
   		hibernateDAO.delete(retrievedOtherSystemRef);
   		flushAndClear();

   		OtherSystemRef retrievedOtherSystemRef2 = hibernateDAO.retrieve(OtherSystemRef.class, retrievedOtherSystemRef.getId());
   		
   		// Will trigger the exception
   		System.out.println(retrievedOtherSystemRef2.getOtherSystemId());		
	}

	/**
	 * Test to check that in the scenario where two users edit the same version of an other system ref but update at different times
	 * the user who tries to update last will get a stale state exception because the data is now out of date and they will
	 * have to retrieve the other system ref again.
	 */
	@Test(expected=StaleStateException.class)
	@DatabaseSetup(value = { "classpath:dbunit/otherSystemRefTestDataWithoutDate.xml" })
	@Transactional
	public void staleStateExceptionTest()
	{
		OtherSystemRef firstOtherSysRef = hibernateDAO.retrieve(OtherSystemRef.class, 1L);
		flushAndClear();
		
		OtherSystemRef sameOtherSysRef = hibernateDAO.retrieve(OtherSystemRef.class, 1L);
		flushAndClear();
		
		firstOtherSysRef.setOtherSystemId("First id");
		hibernateDAO.update(firstOtherSysRef);
		flushAndClear();
		
		sameOtherSysRef.setOtherSystemId("updated id");
		hibernateDAO.update(sameOtherSysRef);
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