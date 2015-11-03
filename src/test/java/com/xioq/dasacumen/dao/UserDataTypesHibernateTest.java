package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.model.systemadmin.UserDataCat;
import com.xioq.dasacumen.model.systemadmin.UserDataTypes;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

public class UserDataTypesHibernateTest extends HibernateDAOTestBase
{
	private static final Long STATUSES_TYPE_ID = 150L;
	
	/**
	 * UserDataTypes used for testing.
	 */
	UserDataTypes testUserDataTypes;
	
	/**
	 * UserDataTypes used for testing.
	 */
	UserData testUserData;

	private static final String descCode = "todo";
	private static final String description = "Description";
	private static final String langCode = "Lang code";
	private static final String name = "USA";
	private static final String userDataValueType = "test type";
	private static final long udCatId = 1L;
	private static final boolean valueRequired = true;
	
	// Audit fields
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	@Before
	public void setup()
	{
		testUserDataTypes = new UserDataTypes();
		
		testUserDataTypes.setDescCode(descCode);
		testUserDataTypes.setDescription(description);
		testUserDataTypes.setLangCode(langCode);
		testUserDataTypes.setName(name);
		testUserDataTypes.setUserDataValueType(userDataValueType);
		testUserDataTypes.setValueRequired(valueRequired);
		
		testUserDataTypes.setCreatedBy(createdBy);
		testUserDataTypes.setCreatedDate(createdDate);
		testUserDataTypes.setLastUpdatedBy(lastUpdatedBy);
		testUserDataTypes.setLastUpdatedDate(lastUpdatedDate);
		
		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("user_data_types");
		testUtil.resetSequences("user_data_cat");
		testUtil.resetSequences("user_data");
	}
	
	/*
	 * Test to retrieve in instance of the user data types table from the database and check that this exists
	 */
	@Test
   	@DatabaseSetup("classpath:dbunit/userDataTypesTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataTypesTestData.xml", table = "acumen.user_data_types", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveUserDataTypesTest()
	{
		UserDataTypes retrievedUserDataTypes = hibernateDAO.retrieve(UserDataTypes.class, STATUSES_TYPE_ID);
		
		assertEquals(name, retrievedUserDataTypes.getName());
		assertEquals(descCode, retrievedUserDataTypes.getDescCode());
		assertEquals(description, retrievedUserDataTypes.getDescription());
		assertEquals(langCode, retrievedUserDataTypes.getLangCode());
		assertEquals(userDataValueType, retrievedUserDataTypes.getUserDataValueType());
		assertEquals(valueRequired, retrievedUserDataTypes.getValueRequired());
		
		assertEquals(createdBy, retrievedUserDataTypes.getCreatedBy());
		assertEquals(lastUpdatedBy, retrievedUserDataTypes.getLastUpdatedBy());
		assertEquals(createdDate, retrievedUserDataTypes.getCreatedDate());
		assertEquals(lastUpdatedDate, retrievedUserDataTypes.getLastUpdatedDate());
	}
	
	/**
	 * Test that a user data type cannot be created in the database.
	 * The identifier generator has been removed in UserDataTypes.java, 
	 * meaning that it can't be created in the database. The id field 
	 * has been set to private so it can't be set outside the class.
	 */
	@Test(expected=IdentifierGenerationException.class )
	@Transactional
	public void failToCreateUserDataTypesTest()
	{	
		testUserDataTypes.setUserDataCat(hibernateDAO.retrieve(UserDataCat.class, udCatId));
		hibernateDAO.create(testUserDataTypes);		
	}
			
	/**
	 * Test to create an instance of user data types then update this 
	 * and check that this can't be updated as the fields are set to 
	 * mutable = "false", preventing any updates.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataTypesTestData.xml")
	public void failToUpdateUserDataTypesTest()
	{
		// Updated fields
		String updatedName = "user Data Type updated name test";
		String updatedDescCode = "Desc code update";
		String updatedLastUpdatedBy = "Bill";
		
		// retrieve one
		UserDataTypes retrievedUserDataTypes = hibernateDAO.retrieve(UserDataTypes.class, STATUSES_TYPE_ID);
		flushAndClear();
		
		retrievedUserDataTypes.setName(updatedName);
		retrievedUserDataTypes.setDescCode(updatedDescCode);
		retrievedUserDataTypes.setLastUpdatedBy(updatedLastUpdatedBy);
		
		hibernateDAO.update(retrievedUserDataTypes);
		flushAndClear();
		
		UserDataTypes retrievedUserDataTypes2 = hibernateDAO.retrieve(UserDataTypes.class, retrievedUserDataTypes.getId());
		
		assertEquals(name, retrievedUserDataTypes2.getName());
		assertEquals(descCode, retrievedUserDataTypes2.getDescCode());
		assertEquals(lastUpdatedBy, retrievedUserDataTypes2.getLastUpdatedBy());	
	}
	
	/**
	 *  Test a UserDataType is not deleted and no exception is thrown.
	 *  The purpose of this test is to delete a UserData and ensure that
	 *  the userDataType and userDataCat still exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataTypesTestData.xml")
	public void failToDeleteUserDataTypesTest()
	{
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, 1L);
		Long typeId = retrievedUserData.getUserDataTypeId();
		UserDataTypes retrievedUserDataTypes = hibernateDAO.retrieve(UserDataTypes.class, typeId);
		Long catId = retrievedUserDataTypes.getUserDataCat().getId();
		
		hibernateDAO.delete(retrievedUserData);
		flushAndClear();
		
		UserDataTypes retrievedUserDataTypes2 = hibernateDAO.retrieve(UserDataTypes.class, typeId);
		UserDataCat retrievedUserDataCat2 = hibernateDAO.retrieve(UserDataCat.class, catId);
		
   		// Will show it hasn't been deleted
		assertNotNull(retrievedUserDataTypes2.getId());
		assertEquals(name, retrievedUserDataTypes2.getName());
		assertNotNull(retrievedUserDataCat2.getId());
	}
	
	/**
	 * User data types and categories are put into the second level cache. So retrieving for a 
	 * second time should result in zero DB activity
	 */
	@Test
	public void secondLevelCache()
	{
		Statistics stats = sessionFactory.getStatistics();
        System.out.println("Stats enabled="+stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
        
		hibernateDAO.retrieveAll(UserDataTypes.class);
		
		flushAndClear();
		
		stats.clear();
		
		hibernateDAO.retrieveAll(UserDataTypes.class);
		
        System.out.println("Fetch Count=" + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
        System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count="  + stats.getSecondLevelCachePutCount());
        
        assertEquals("Second level miss count should be zero", 0, stats.getSecondLevelCacheMissCount());
	}
}