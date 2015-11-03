package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.common.Person;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * Tests CRUD operations on Person Model.
 * @author echhung
 */
public class PersonHibernateTest extends HibernateDAOTestBase implements CrudTests 
{
	private static final String FORENAMES = "James";
	private static final String SURNAME = "Brown";
	private static final String GENDER = "1";
	private static final String POSITION = "Singer";
	private static final String UID = "dbunit_person_102";
	
	private static final Long DBUNIT_PERSON_ID102 = 102L;

	@Before
	public void setup() 
	{
		testUtil.resetSequences(DatabaseTable.PARTIES);
	}
	
	@Test
	@DatabaseSetup("classpath:dbunit/parties/suppliersTestData.xml")
	public void retrieveTest() {
		
		Person personRetrieved = hibernateDAO.retrieve(Person.class, DBUNIT_PERSON_ID102);
		
		assertEquals(FORENAMES, personRetrieved.getForenames());
		assertEquals(SURNAME, personRetrieved.getSurname());
		assertEquals(GENDER, personRetrieved.getGender());
		assertEquals(POSITION, personRetrieved.getPosition());
		assertEquals(UID, personRetrieved.getUid());
		
		assertAuditFields(personRetrieved);
	}
	
	@Test
	public void createTest()
	{	
		Person testPerson = new Person();
		
		testPerson.setForenames(FORENAMES);
		testPerson.setSurname(SURNAME);
		testPerson.setGender(GENDER);
		testPerson.setPosition(POSITION);
		testPerson.setUid(UID);
		
		hibernateDAO.create(testPerson);
		flushAndClear();
		
		Person personRetrieved = hibernateDAO.retrieve(Person.class, testPerson.getId());
		
		assertEquals(FORENAMES, personRetrieved.getForenames());
		assertEquals(SURNAME, personRetrieved.getSurname());
		assertEquals(GENDER, personRetrieved.getGender());
		assertEquals(POSITION, personRetrieved.getPosition());
		assertEquals(UID, personRetrieved.getUid());
		
		assertAuditFields(personRetrieved);
	}

	@Test
	@DatabaseSetup("classpath:dbunit/parties/suppliersTestData.xml")
	public void updateTest()
	{	
		Person personToUpdate = hibernateDAO.retrieve(Person.class, DBUNIT_PERSON_ID102);
		
		final String updatedForename = "Jane";
		final String updatedSurname = "Addams";
		final String updatedGender = "FEMALE";
		final String updatedUid = "das_pt_12345654554587854565";
		final String updatedPosition = "FEMALE";

		personToUpdate.setForenames(updatedForename);
		personToUpdate.setGender(updatedGender);
		personToUpdate.setSurname(updatedSurname);
		personToUpdate.setPosition(updatedPosition);
		personToUpdate.setUid(updatedUid);
		
		hibernateDAO.update(personToUpdate);
		
		Person personRetrieved = hibernateDAO.retrieve(Person.class, personToUpdate.getId());
		
		assertEquals(updatedForename, personRetrieved.getForenames());
		assertEquals(updatedSurname, personRetrieved.getSurname());
		assertEquals(updatedGender, personRetrieved.getGender());
		assertEquals(updatedPosition, personRetrieved.getPosition());
		assertEquals(updatedUid, personRetrieved.getUid());
		
		assertAuditFields(personRetrieved);
		
	}
	
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/parties/suppliersTestData.xml")
	public void deleteTest()
	{	
		Person personToDelete = hibernateDAO.retrieve(Person.class, DBUNIT_PERSON_ID102);
		hibernateDAO.delete(personToDelete);
		flushAndClear();
		
		Person retrievedPerson = hibernateDAO.retrieve(Person.class, personToDelete.getId());
		
		/* Include this comment in all delete tests using this method */
		// Because of lazy loading, must do something with the retrieved address to trigger the load 
		// to throw the exception, thus the printline.
		System.out.println("Retrieved Person: " + retrievedPerson);
	}

	@Test()
	@DatabaseSetup("classpath:dbunit/parties/suppliersTestData.xml")
	public void staleStateExceptionTest()
	{
		// Both users retrieve version 0
		Person personRetrieved1 = hibernateDAO.retrieve(Person.class, DBUNIT_PERSON_ID102);
		flushAndClear();
		Person personRetrieved2 = hibernateDAO.retrieve(Person.class, DBUNIT_PERSON_ID102);
		flushAndClear();
		
		// only update one field - not an audit field though !
		personRetrieved1.setForenames("Bob");
		
		hibernateDAO.update(personRetrieved1);
		flushAndClear(); 
		assertEquals("Version number not incremented", 1, personRetrieved1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		personRetrieved2.setForenames(FORENAMES);
		// and tries to update
		try
		{
			hibernateDAO.update(personRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, personRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown: " + e);
		}
	}


}
