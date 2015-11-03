package com.xioq.dasacumen.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.hamcrest.Matchers;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.xioq.dasacumen.model.constants.UserDataType;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class UserDataHibernateTest extends HibernateDAOTestBase
{
	/** Existing UD from the DB Unit script systemAdmin/userDataTestData.xml */
	private static final Long DBUNIT_EXISTING_UD_ID = 9991l;
	/** UD used to add as a descendant UD */
	private static final Long DBUNIT_EXISTING_UD_ID_2 = 9994l;
	private static final String DBUNIT_UD_2_NAME = "Acme Products";
	private static final Long DBUNIT_UD_WITH_DESCENDING = 9992l;
	
	private static final String value = "40000.12";
	private static final String name = "Test";
	private static final int tennantID = 1;
	private static final Boolean isActive = true;
	private static final int udOrder = 1;
	
	@Before
	public void setup()
	{
		testUtil.resetSequences("user_data");
	}
	
	
	private static UserData newUserData()
	{
		UserData userData = new UserData();
		// Configure Test User Data - listed in alphabetical order.
		userData.setUserDataTypeId(UserDataType.ASSET_NUMBER_PART_1.getId());
		userData.setActive(isActive);
		userData.setValue(value);
		userData.setName(name);
		userData.setTenantId(tennantID);
		userData.setUdOrder(udOrder);
		return userData;
	}
	
	 
	/*
	 * Test to retrieve in instance of the user data table from the database and check that this exists
	 * DB should not change
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	@ExpectedDatabase(value="classpath:dbunit/systemAdmin/userDataTestData.xml", table = "acumen.user_data", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveUserDataTest()
	{
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		flushAndClear();
		
		assertNotNull(retrievedUserData);
		assertEquals(DBUNIT_EXISTING_UD_ID, retrievedUserData.getId());
		assertEquals("USA", retrievedUserData.getName());
		assertEquals(new Long(3), retrievedUserData.getUserDataTypeId());
	}
	
	/**
	 * Creates User Data and tests if the data is present in db.
	 */
	@Test
	@Transactional
	public void createUserDataTest()
	{	
		UserData userData = newUserData();
		hibernateDAO.create(userData);
		flushAndClear();
		
		UserData userDataRetrieved = hibernateDAO.retrieve(UserData.class, userData.getId());
		flushAndClear();
		
		assertEquals(UserDataType.ASSET_NUMBER_PART_1.getId(), userDataRetrieved.getUserDataTypeId());
		assertEquals(isActive, userDataRetrieved.getActive());
		assertEquals(value, userDataRetrieved.getValue());
		assertEquals(name, userDataRetrieved.getName());
		assertNotNull(userDataRetrieved.getUdOrder());
		assertEquals(udOrder, userDataRetrieved.getUdOrder().intValue());

		assertAuditFields(userDataRetrieved);
	}
	
	/**
	 * Update an existing User Data in the database.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	public void updateUserDataTest()
	{
		UserData userDataToUpdate= hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		flushAndClear();
		
		// Updated some fields fields.
		String updatedName = "Update Test";
		
		userDataToUpdate.setName(updatedName);
		
		hibernateDAO.update(userDataToUpdate);
		flushAndClear();
		
		UserData userDataRetrieved = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		flushAndClear();
		
		// Configure Test UserData
		assertEquals(updatedName, userDataRetrieved.getName());
	}
	
	/**
	 * Delete an existing User Data in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	public void deleteUserDataTest()
	{	
		UserData userDataToUpdate= hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		flushAndClear();
		
		hibernateDAO.delete(userDataToUpdate);
		flushAndClear();
				
		// This should throw the exception ObjectNotFoundException
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		// Because of lazy loading, must do something with the retrieved user to trigger the load
		System.out.println("END:" + retrievedUserData);
	}
	
	/**
	 * Update a UD by adding a new descendant UD. Start with no descendant UD and add one new
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	public void updateDescendantUserData()
	{	
		UserData userDataToUpdate = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		UserData userDataRetrieved = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID_2);	
		flushAndClear();
		
		userDataToUpdate.addDescendantUserData(userDataRetrieved);
		hibernateDAO.update(userDataToUpdate);
		flushAndClear();
		
		UserData userDataRetrieved2 = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);	
		
		assertNotNull(userDataRetrieved2);
		assertNotNull(userDataRetrieved2.getDescendantUserData());
		assertEquals(1, userDataRetrieved2.getDescendantUserData().size());

		UserData descendantUD = userDataRetrieved2.getDescendantUserData().iterator().next();
		assertNotNull(descendantUD);
		assertEquals(DBUNIT_UD_2_NAME, descendantUD.getName());
	}
	
	/**
	 * Test to check that user data cannot be updated via the descendant user data.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	public void failToUpdateUserDataInDescendantUserData() {
		
		UserData userDataRetrieved = hibernateDAO.retrieve(UserData.class, DBUNIT_UD_WITH_DESCENDING);	
		flushAndClear();

		// Get descendants
		Set<UserData> retrievedDescendants = userDataRetrieved.getDescendantUserData();
		
		if(retrievedDescendants.isEmpty())
			throw new RuntimeException("Setup failure. No descendant UD to update");
		// Sets all user data to have name break.
		for(UserData ud : retrievedDescendants ){
			ud.setName("break");
		}
		 
		hibernateDAO.update(userDataRetrieved);
		flushAndClear();
		
		UserData userDataRetrieved2 = hibernateDAO.retrieve(UserData.class, DBUNIT_UD_WITH_DESCENDING);	
		assertFalse(userDataRetrieved2.getDescendantUserData().isEmpty());
		//convert user data descendants into a list of their field name. 
		for (UserData ud: userDataRetrieved2.getDescendantUserData()) {
			assertNotEquals("break", ud.getName());
		}
	}
	
	/**
	 * Tests removing of a descendant UD
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	public void updateToEmptyDescendantUserData()
	{	
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, DBUNIT_UD_WITH_DESCENDING);	
		if(retrievedUserData.getDescendantUserData().isEmpty())
			throw new RuntimeException("Setup failure. No descendant UD to update");

		// make empty set
		Set<UserData> emptyDescendantsSet = new HashSet<UserData>();
		
		// Updates the decendants in the original userdata
		retrievedUserData.setDescendantUserData(emptyDescendantsSet);
		hibernateDAO.update(retrievedUserData);
		flushAndClear();
		
		UserData retrievedUserData2 = hibernateDAO.retrieve(UserData.class, retrievedUserData.getId());	
		
		assertNotNull(retrievedUserData2.getDescendantUserData());
		assertTrue(retrievedUserData2.getDescendantUserData().isEmpty());
	}
	
	
	/**
	 * Retrieve a UD with descendant data
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	@ExpectedDatabase("classpath:dbunit/systemAdmin/userDataTestData.xml") // DB should not change
	public void retrieveDescendantUserData()
	{	
		UserData userDataRetrieved = hibernateDAO.retrieve(UserData.class, DBUNIT_UD_WITH_DESCENDING);
		
		// Get descendants
		Set<UserData> retrievedDescendants = userDataRetrieved.getDescendantUserData();
		
		assertNotNull(retrievedDescendants);
		assertFalse("No descendant user datas",retrievedDescendants.isEmpty());
		
		// Check that have a policy link that has a policy of the correct id
		assertThat(retrievedDescendants, 
				hasItems(Matchers.<UserData>hasProperty("name",equalTo("Country"))));

		// Assert this list
//		assertThat(nameSet, hasItems("Country", "Location")); 
	}
	
	/*
	 * Test to check that update when the version number is changed should throw a stale state exception 
	 */
	@Test
	@Transactional
	@DatabaseSetup("classpath:dbunit/systemAdmin/userDataTestData.xml")
	public void staleStateExceptionTest()
	{
		UserData firstUserData = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		flushAndClear();
		UserData secondUserData = hibernateDAO.retrieve(UserData.class, DBUNIT_EXISTING_UD_ID);
		flushAndClear();
		
		firstUserData.setName("new name");
		hibernateDAO.update(firstUserData);
		flushAndClear();
		
		try
		{
			secondUserData.setName("another name");
			hibernateDAO.update(secondUserData);
			flushAndClear();
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number not incremented", 2, firstUserData.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown");
		}
	}
}
