package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.common.Address;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * Tests CRUD operations on Address Model.
 * BASE FILE - This will be updated with the latest changes to tests. Please use this as a base for tests in the future.
 * @author echhung
 */
// Comment for the class is always necessary.
// You need to extend HibernateDAOTestBase for the test to work and it provides helpful methods for use in the test
// CrudTests should always be implemented unless there is a good reason.
public class AddressesHibernateTest extends HibernateDAOTestBase implements CrudTests {

	// object test data - constants are in capitals
	private static final String ADDRESS_LINE_1 = "Test Address Line 1";
	private static final String ADDRESS_LINE_2 = "Test Address Line 2";
	private static final String ADDRESS_LINE_3 = "Test Address Line 3";
	private static final String CITY = "Manchester";
	private static final String COUNTRY = "Test Country";
	private static final String PLACE_TYPE = "Large";
	private static final String ZIP_POST_CODE = "M50 2NT";
	
	// Create meaningful constant names for dbunit ids
	private final Long DBUNIT_ADDRESS_ID50 = 50L;
	
	@Before
	public void setup()
	{
		// Reset sequences here, always use the database table constants file.
		testUtil.resetSequences(DatabaseTable.ADDRESSES);
	}
	
	@Test
	@DatabaseSetup(value = { "classpath:dbunit/party/address.xml" })
	public void retrieveTest()
	{
		// retrieve it
		Address addressRetrieved = hibernateDAO.retrieve(Address.class, DBUNIT_ADDRESS_ID50);
		
		// assert values are correct
		assertEquals(ADDRESS_LINE_1, addressRetrieved.getAddressLine1());
		assertEquals(ADDRESS_LINE_2, addressRetrieved.getAddressLine2());
		assertEquals(ADDRESS_LINE_3, addressRetrieved.getAddressLine3());
		assertEquals(COUNTRY, addressRetrieved.getCountry());
		assertEquals(PLACE_TYPE, addressRetrieved.getTypeOfPlace());
		assertEquals(CITY, addressRetrieved.getCity());
		assertEquals(ZIP_POST_CODE, addressRetrieved.getZipPostCode());
		
		// assert audit fields are not null 
		assertAuditFields(addressRetrieved);
	}
	
	@Test
	// Use dbunit setup for foreign keys.
	// Use expected db setup when you cannot use assertions (generally it is not needed).
	// No comments are needed for these tests.
	public void createTest()
	{
		Address testAddress = new Address();
		
		// assign variables to object
		testAddress.setAddressLine1(ADDRESS_LINE_1);
		testAddress.setAddressLine2(ADDRESS_LINE_2);
		testAddress.setAddressLine3(ADDRESS_LINE_3);
		testAddress.setCountry(COUNTRY);
		testAddress.setTypeOfPlace(PLACE_TYPE);
		testAddress.setZipPostCode(ZIP_POST_CODE);
		testAddress.setCity(CITY);
		
		// create it
		hibernateDAO.create(testAddress);
		flushAndClear();
		
		// retrieve it
		Address addressRetrieved = hibernateDAO.retrieve(Address.class, testAddress.getId());
		
		// assert values are correct
		assertEquals(ADDRESS_LINE_1, addressRetrieved.getAddressLine1());
		assertEquals(ADDRESS_LINE_2, addressRetrieved.getAddressLine2());
		assertEquals(ADDRESS_LINE_3, addressRetrieved.getAddressLine3());
		assertEquals(COUNTRY, addressRetrieved.getCountry());
		assertEquals(PLACE_TYPE, addressRetrieved.getTypeOfPlace());
		assertEquals(CITY, addressRetrieved.getCity());
		assertEquals(ZIP_POST_CODE, addressRetrieved.getZipPostCode());
		
		// assert audit fields are not null 
		assertAuditFields(addressRetrieved);
	}

	@Test
	@DatabaseSetup(value = { "classpath:dbunit/party/address.xml" })
	public void updateTest ()
	{
		Address addressToUpdate = hibernateDAO.retrieve(Address.class, DBUNIT_ADDRESS_ID50);
		
		// Updated variables should be final.
		final String updatedAddressLine1 = "6 Manchester Lane";
		final String updatedAddressLine2 = "Bury";
		final String updatedAddressLine3 = "Stockholm";

		addressToUpdate.setAddressLine1(updatedAddressLine1);
		addressToUpdate.setAddressLine2(updatedAddressLine2);
		addressToUpdate.setAddressLine3(updatedAddressLine3);
		
		hibernateDAO.update(addressToUpdate);		
		
		Address addressRetrieved = hibernateDAO.retrieve(Address.class, addressToUpdate.getId());
		
		assertEquals(updatedAddressLine1, addressRetrieved.getAddressLine1());
		assertEquals(updatedAddressLine2, addressRetrieved.getAddressLine2());
		assertEquals(updatedAddressLine3, addressRetrieved.getAddressLine3());
		
		assertAuditFields(addressRetrieved);
	}
	
	@Test (expected=ObjectNotFoundException.class)
	@DatabaseSetup(value = { "classpath:dbunit/party/address.xml" })
	public void deleteTest()
	{	
		Address addressToDelete = hibernateDAO.retrieve(Address.class, DBUNIT_ADDRESS_ID50);
		hibernateDAO.delete(addressToDelete);
		flushAndClear();
		
		Address retrievedAddress = hibernateDAO.retrieve(Address.class, addressToDelete.getId());
		
		/* Include this comment in all delete tests using this method */
		// Because of lazy loading, must do something with the retrieved address to trigger the load 
		// to throw the exception, thus the printline.
		System.out.println("Retrieved Address: " + retrievedAddress);
	}
	
	@Test()
	@DatabaseSetup(value = { "classpath:dbunit/party/address.xml" })
	public void staleStateExceptionTest()
	{
		// Both users retrieve version 0
		Address addressRetrieved1 = hibernateDAO.retrieve(Address.class, DBUNIT_ADDRESS_ID50);
		flushAndClear();
		Address addressRetrieved2 = hibernateDAO.retrieve(Address.class, DBUNIT_ADDRESS_ID50);
		flushAndClear();
		
		// only update one field - not an audit field though !
		addressRetrieved1.setAddressLine1("updated address line 1");
		
		hibernateDAO.update(addressRetrieved1);
		flushAndClear(); 
		assertEquals("Version number not incremented", 1, addressRetrieved1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		addressRetrieved2.setAddressLine1(ADDRESS_LINE_1);
		// and tries to update
		try
		{
			hibernateDAO.update(addressRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, addressRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown: " + e);
		}
	}
}
