package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.id.IdentifierGenerationException;
import org.hibernate.stat.Statistics;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import com.xioq.dasacumen.model.systemadmin.UserDataCat;
import com.xioq.dasacumen.model.systemadmin.UserDataTypes;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

public class UserDataCatHibernateTest extends HibernateDAOTestBase
{
	private static final Long udCatId = 1L;
	private static final Long udTypeId = 1L;
	private static final String descCode = "todo";
	private static final String description = "todo";
	private static final String languageCode = "systemAdmin";
	private static final String name = "Asset Register";
	
	// Audit fields
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	@Before
	public void setup()
	{
		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("user_data_cat");
		
		// clear the second level cache for user data cats
		this.sessionFactory.getCache().evictAllRegions();
	}
		
	/*
	 * Test to retrieve the User Data Cat table from the database to check that it has been created
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataCatTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/userDataCatTestData.xml", table = "acumen.user_data_cat", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveUserDataCatTest()
	{
		UserDataCat retrievedUserDataCat = hibernateDAO.retrieve(UserDataCat.class, udCatId);
		
		assertEquals(descCode, retrievedUserDataCat.getDescCode());
		assertEquals(description, retrievedUserDataCat.getDescription());
		assertEquals(languageCode, retrievedUserDataCat.getLanguageCode());
		assertEquals(name, retrievedUserDataCat.getName());
	}
	
	/**
	 * Test that a user data cat cannot be created in the database.
	 * The identifier generator has been removed in UserDataCat.java, 
	 * meaning that it can't be created in the database. The id field 
	 * has been set to private so it can't be set outside the class.
	 */
	@Test(expected=IdentifierGenerationException.class )
	@Transactional
	public void failToCreateUserDataCatTest()
	{
		UserDataCat testUserDataCat = new UserDataCat();
		
		testUserDataCat.setDescCode(descCode);
		testUserDataCat.setDescription(description);
		testUserDataCat.setLanguageCode(languageCode);
		testUserDataCat.setName(name);
		
		testUserDataCat.setCreatedBy(createdBy);
		testUserDataCat.setCreatedDate(createdDate);
		testUserDataCat.setLastUpdatedBy(lastUpdatedBy);
		testUserDataCat.setLastUpdatedDate(lastUpdatedDate);

		testUserDataCat.setParentCat(hibernateDAO.retrieve(UserDataCat.class, udCatId));
		hibernateDAO.create(testUserDataCat);
	}
	
	/*
	 * Test to create an instance of User Data Cat and then update the details within this and check 
	 * that this can't be updated as "mutable = false"
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataCatTestData.xml")
	public void failToUpdateUserDataCatTest ()
	{
		// Updated fields
		String updatedName = "New name";
		String updateDescCode = "12345";
		
		// retrieve one
		UserDataCat retrievedUserDataCat = hibernateDAO.retrieve(UserDataCat.class, udCatId);
		flushAndClear();
		
		retrievedUserDataCat.setName(updatedName);
		retrievedUserDataCat.setDescCode(updateDescCode);
		
		hibernateDAO.update(retrievedUserDataCat);
		flushAndClear();
		
		UserDataCat retrievedUserDataCat2 = hibernateDAO.retrieve(UserDataCat.class, retrievedUserDataCat.getId());
		
		assertEquals(name, retrievedUserDataCat2.getName());
		assertEquals(descCode, retrievedUserDataCat2.getDescCode());
	}
	
	/**
	 * Test a UserDataCat is not deleted and no exception is thrown.
	 *  The purpose of this test is to delete a UserDataType and ensure 
	 *  that the userDataCat still exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/userDataCatTestData.xml")
	public void failToDeleteUserDataCatTest()
	{	
		UserDataTypes retrievedUserDataTypes = hibernateDAO.retrieve(UserDataTypes.class, udTypeId);
		UserDataCat retrievedUserDataCat = hibernateDAO.retrieve(UserDataCat.class, udCatId);
		
		hibernateDAO.delete(retrievedUserDataTypes);
		flushAndClear();
		
		UserDataCat retrievedUserDataCat2 = hibernateDAO.retrieve(UserDataCat.class, retrievedUserDataCat.getId());

		// Will show it hasn't been deleted
		assertNotNull(retrievedUserDataCat2.getId());
		assertEquals(descCode, retrievedUserDataCat2.getDescCode());
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
        
		List<UserDataCat> all = hibernateDAO.retrieveAll(UserDataCat.class);
		assertFalse(all.isEmpty());
		flushAndClear();
		
		stats.clear();
		
		hibernateDAO.retrieveAll(UserDataCat.class);
		
        System.out.println("Fetch Count=" + stats.getEntityFetchCount());
        System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
        System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
        System.out.println("Second Level Put Count="  + stats.getSecondLevelCachePutCount());
        
        assertEquals("Second level miss count should be zero", 0, stats.getSecondLevelCacheMissCount());
	}
	
}