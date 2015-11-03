package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.Authority;
import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * Tests user and authority is working with CRUD operations. A user should always have an authority to
 * have basic access right. We are not testing just a user because it means they will have no access to the system.
 * @author BenWorsley
 */
public class UserWithAuthoritiesHibernateTest extends HibernateDAOTestBase implements CrudTests 
{
	private static final String FORENAMES = "Test Forename";
	private static final String SURNAME = "Test Surname";
	private static final String USERNAME = "testUser";
	private static final String PASSWORD = "testPass";
	
	// By default authority is set to DEFAULT if null so we use a different string here.
	private static final String AUTHORITY = "READ";
	
	// Should only be used for retrieving as these values are set by db
	private static final Long DBUNIT_USER_ID600 = 600L;
	
	@Before
    public void setUp() {
		testUtil.resetSequences(DatabaseTable.USERS);
    }

	@Test
	@DatabaseSetup(value = { "classpath:dbunit/userTestData.xml" })
	public void retrieveTest() {
		
		User userRetrieved = hibernateDAO.retrieve(User.class, DBUNIT_USER_ID600);
		
		assertEquals(FORENAMES, userRetrieved.getForenames());
		assertEquals(SURNAME, userRetrieved.getSurname());
		assertEquals(USERNAME, userRetrieved.getUserName());
		assertEquals(PASSWORD, userRetrieved.getPassword());
		
		assertAuditFields(userRetrieved);
	}
	
	private Authority getAuthority(User user, String authName) {
		Authority auth = new Authority();
		auth.setAuthority(authName);
		auth.setUser(user);
		
		return auth;
	}
	
	@Test
	public void createTest ()
	{
		User testUser = new User();
		
		testUser.setForenames(FORENAMES);
		testUser.setSurname(SURNAME);
		testUser.setUserName(USERNAME);
		testUser.setEnabled(true);
		testUser.setPassword(PASSWORD);
		
		Set<Authority> authList = new HashSet<Authority>(0);
		authList.add(getAuthority(testUser, AUTHORITY));
		
		testUser.setAuthorities(authList);
		
		hibernateDAO.create(testUser);
		flushAndClear();
		
		User userRetrieved = hibernateDAO.retrieve(User.class, testUser.getId());

		assertEquals(FORENAMES, userRetrieved.getForenames());
		assertEquals(SURNAME, userRetrieved.getSurname());
		assertEquals(USERNAME, userRetrieved.getUserName());
		assertEquals(PASSWORD, userRetrieved.getPassword());
		
		assertAuditFields(testUser);
		
		
		// Verify authority
		Authority retrievedAuth = null;
		for (Authority authorities : testUser.getAuthorities()) {
			retrievedAuth = authorities;
		}
		assertEquals(AUTHORITY, retrievedAuth.getAuthority());
	}
	
	@Test
	@DatabaseSetup(value = { "classpath:dbunit/userTestData.xml" })
	public void updateTest ()
	{
		User userToUpdate = hibernateDAO.retrieve(User.class, DBUNIT_USER_ID600);
		
		// Cannot change/update username!
		final String forenamesUpdate = "random forename";
		final String surnameUpdate = "random surname";
		final String passwordUpdate = "random password";
		
		userToUpdate.setForenames(forenamesUpdate);
		userToUpdate.setSurname(surnameUpdate);
		userToUpdate.setPassword(passwordUpdate);
		
		hibernateDAO.update(userToUpdate);
		flushAndClear();
		
		User userRetrieved = hibernateDAO.retrieve(User.class, userToUpdate.getId());
		
		assertEquals(forenamesUpdate, userRetrieved.getForenames());
		assertEquals(surnameUpdate, userRetrieved.getSurname());
		assertEquals(passwordUpdate, userRetrieved.getPassword());
	}
	
	@Test
	@DatabaseSetup(value = { "classpath:dbunit/userTestData.xml" })
	public void addAuthorityWithExistingAuthorityTest ()
	{
		final String newAuth = "ADMIN";
		User retrievedUser = hibernateDAO.retrieve(User.class, DBUNIT_USER_ID600);
		
		Set<Authority> authList = retrievedUser.getAuthorities();
		authList.add(getAuthority(retrievedUser, newAuth));
		
		// Add another authority so there should be two now.
		retrievedUser.setAuthorities(authList);
		
		hibernateDAO.update(retrievedUser);
		flushAndClear();
		
		User userUpdatedRetrieved = hibernateDAO.retrieve(User.class, retrievedUser.getId());

		// Verify authority by putting strings in array list.
		List<String> authorityList = new ArrayList<String>();
		for (Authority authorities : userUpdatedRetrieved.getAuthorities()) {
			authorityList.add(authorities.getAuthority());
		}
		
		//make an expected list
		List<String> expectedList = new ArrayList<String>();
		expectedList.add(newAuth);
		expectedList.add(AUTHORITY);
		// assert lists - explained here http://stackoverflow.com/questions/1092981/hamcrests-hasitems
		assertThat(authorityList, Matchers.hasItems(expectedList.toArray(new String[expectedList.size()])));	
	}
	
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup(value = { "classpath:dbunit/userTestData.xml" })
	public void deleteTest()
	{	
		User retrievedUser = hibernateDAO.retrieve(User.class, DBUNIT_USER_ID600);
		
		hibernateDAO.delete(retrievedUser);
		flushAndClear();
		
		// This should throw the exception ObjectNotFoundException
		User retrievedDeletedUser = hibernateDAO.retrieve(User.class, DBUNIT_USER_ID600);
		
		/* Include this comment in all delete tests using this method */
		// Because of lazy loading, must do something with the retrieved user to trigger the load 
		// to throw the exception, thus the printline.
		System.out.println("Retrieved Users: " + retrievedDeletedUser);
	}
	
	@Test()
	@DatabaseSetup(value = { "classpath:dbunit/userTestData.xml" })
	public void staleStateExceptionTest()
	{
		// Both users retrieve version 0
		User retrievedUser1  = hibernateDAO.retrieve(User.class, DBUNIT_USER_ID600);
		flushAndClear();
		User retrievedUser2 = hibernateDAO.retrieve(User.class, DBUNIT_USER_ID600);
		flushAndClear();
		
		// only update one field - not an audit field though !
		retrievedUser1.setPassword("New password");
		
		hibernateDAO.update(retrievedUser1);
		flushAndClear(); 
		assertEquals("Version number not incremented", 1, retrievedUser1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		retrievedUser2.setPassword(PASSWORD);
		// and tries to update
		try
		{
			hibernateDAO.update(retrievedUser2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, retrievedUser2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown: " + e);
		}
	}
	
	@Test(expected=ConstraintViolationException.class)
	@DatabaseSetup(value = { "classpath:dbunit/userTestData.xml" })
	public void anotherUserWithSameUserNameCannotBeCreatedTest() {
		User testUser = new User();
	
		testUser.setUserName(USERNAME);
		testUser.setPassword(PASSWORD);
		
		Set<Authority> authList = new HashSet<Authority>(0);
		authList.add(getAuthority(testUser, AUTHORITY));
		
		testUser.setAuthorities(authList);
		
		hibernateDAO.create(testUser);
		flushAndClear();
	}
}
