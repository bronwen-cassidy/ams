package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.ComparisonFailure;
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
import com.xioq.dasacumen.model.common.UserDataSets;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.model.systemadmin.UserDataTypes;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
/**
 * testing CRUD operations on the UserDataSets.
 * @author jmadden
 *
 */
public class UserDataSetsHibernateTest
{
	@Autowired
	TestUtil testUtil;
	/**
	 * UserDataSet used for testing.
	 */
	UserDataSets testUserDataSets;
	
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
	
	static final Long udCatAndType = 1L;
	
//	static Long assetNumber = 2L;
//	static Long userDataId = 13L;
//	static Long userDataSetId = 3L;
	static Long id = 1L;

	@Before
	public void setup()
	{	
		testUserDataSets = new UserDataSets();
		testUserDataSets.setUserDataType(hibernateDAO.retrieve(UserDataTypes.class, id));
		testUserDataSets.setAssets(hibernateDAO.retrieve(Assets.class, id));
		testUserDataSets.setUserData(hibernateDAO.retrieve(UserData.class, id));
		
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data_types");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("user_data_sets");
	}
	   
    /**
   	 * Creates a user data set and retrieves it from the database. 
   	 */
   	@Test
   	@DatabaseSetup("classpath:dbunit/userDataSetsTestData_expected.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataSetsTestData_expected.xml", table = "acumen.user_data_sets", assertionMode = DatabaseAssertionMode.NON_STRICT)
   	public void retrieveUserDataSetsTest() {
   		
   		UserDataSets userDataSetsRetrieved = hibernateDAO.retrieve(UserDataSets.class, id);
   		flushAndClear();
   		
   		assertEquals(id, userDataSetsRetrieved.getUserDataType().getId());
		assertEquals(id, userDataSetsRetrieved.getAssets().getId());
   		assertEquals(id, userDataSetsRetrieved.getUserData().getId());
   	}
	
	/*
	 * Test to create in instance of user data sets and then check that this has been created successfully
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataSetsTestData_setup.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataSetsTestDataNoDate_expected.xml", table = "acumen.user_data_sets", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createUserDataSetsTest()
	{	
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, id);
		UserDataTypes retrievedUserDataTypes = hibernateDAO.retrieve(UserDataTypes.class, id);
		
		testUserDataSets.setAssets(retrievedAsset);
		testUserDataSets.setUserData(retrievedUserData);
		testUserDataSets.setUserDataType(retrievedUserDataTypes);
		
		hibernateDAO.create(testUserDataSets);
   		flushAndClear();
   		
   		UserDataSets userDataSetsRetrieved = hibernateDAO.retrieve(UserDataSets.class, id);
   		flushAndClear();
   		
   		assertEquals(id, userDataSetsRetrieved.getUserDataType().getId());
   		assertEquals(id, userDataSetsRetrieved.getAssets().getId());
   		assertEquals(id, userDataSetsRetrieved.getUserData().getId());
	}
	
	/**
	 * this test is expected to come back with ComparisonFailure as you are
	 * unable to
	 * update the fields below so the XML data and the data set below shouldn't
	 * match.
	 */
	@Test(expected = ComparisonFailure.class)
	@DatabaseSetup("classpath:dbunit/userDataSetsTestData_expected.xml")
	public void failUpdateUserDataSetTest()
	{
		UserDataSets userDataSetToUpdate = hibernateDAO.retrieve(UserDataSets.class, id);
		String createdBy = "Ash";
		Date createdDate = new Date(22, 10, 05);
		
		userDataSetToUpdate.setCreatedBy(createdBy);
		userDataSetToUpdate.setCreatedDate(createdDate);
		
		hibernateDAO.update(userDataSetToUpdate);
		flushAndClear();
		
		UserDataSets retrievedUserDataSets = hibernateDAO.retrieve(UserDataSets.class, id);
		
		assertEquals(createdBy, retrievedUserDataSets.getCreatedBy());
		assertEquals(createdDate, retrievedUserDataSets.getCreatedDate());
	}
	
	/**
	 * Delete an existing User Data Set in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/userDataSetsTestData_expected.xml")
	public void deleteUserDataSetsTest()
	{		
		UserDataSets retrievedUserDataSets = hibernateDAO.retrieve(UserDataSets.class, id);
		hibernateDAO.delete(retrievedUserDataSets);
		flushAndClear();
		
		// This should throw the exception ObjectNotFoundException
		UserDataSets retrievedUserDataSets2 = hibernateDAO.retrieve(UserDataSets.class, id);
		// Because of lazy loading, must do something with the retrieved User Data Set to trigger the load
		System.out.println("END:" + retrievedUserDataSets2);
	}
	
	/*
	 * Test to check that update when the version number is changed should throw a stale state exception 
	 */
	@Test(expected =StaleStateException.class)
	@DatabaseSetup("classpath:dbunit/userDataSetsTestData_expected.xml")
	public void staleStateExceptionTest()
	{
		UserDataSets firstUserDataSet = hibernateDAO.retrieve(UserDataSets.class, id);
		flushAndClear();
		
		UserDataSets sameUserDataSet = hibernateDAO.retrieve(UserDataSets.class, id);
		flushAndClear();
		
		firstUserDataSet.setAssets(hibernateDAO.retrieve(Assets.class, id));
		hibernateDAO.update(firstUserDataSet);
		flushAndClear();
		
		sameUserDataSet.setAssets(hibernateDAO.retrieve(Assets.class, id));
		hibernateDAO.update(sameUserDataSet);
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
