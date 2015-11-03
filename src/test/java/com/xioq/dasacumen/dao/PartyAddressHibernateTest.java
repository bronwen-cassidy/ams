package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.common.Address;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.PartyAddress;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * Tests partyAddress is working with CRUD operations.
 * @author BenWorsley
 *
 */
public class PartyAddressHibernateTest extends HibernateDAOTestBase implements CrudTests {

	static final String PARTY_ADDRESS_TYPE = "Test Address Type";
	
	static final Long DBUNIT_PARTYADDRESS_ID1 = 1L;
	static final Long DBUNIT_COMPANY_ID1 = 1L;
	static final Long DBUNIT_ADDRESS_ID1 = 1L;
	
	@Before
	public void setUp() {
		testUtil.resetSequences(DatabaseTable.PARTY_ADDRESSES);
		testUtil.resetSequences(DatabaseTable.ADDRESSES);
		testUtil.resetSequences(DatabaseTable.USER_DATA);
		testUtil.resetSequences(DatabaseTable.PARTIES);
	}
    
    @Test
    @DatabaseSetup("classpath:dbunit/testDataPartyAddress_expected.xml")
	public void retrieveTest()
	{	    	
    	PartyAddress retrievedPartyAddress = hibernateDAO.retrieve(PartyAddress.class, DBUNIT_PARTYADDRESS_ID1);
		
		assertEquals(PARTY_ADDRESS_TYPE, retrievedPartyAddress.getAddressType());
		assertEquals(DBUNIT_COMPANY_ID1, retrievedPartyAddress.getParty().getId());
		assertEquals(DBUNIT_ADDRESS_ID1, retrievedPartyAddress.getAddress().getId());
		
		assertAuditFields(retrievedPartyAddress);
	}
    
	@Test
	@DatabaseSetup(value = { "classpath:dbunit/baseTestData.xml", "classpath:dbunit/partyAddressTestData.xml" })
	public void createTest()
	{	
		PartyAddress testPartyAddress = new PartyAddress();
		testPartyAddress.setAddress(hibernateDAO.retrieve(Address.class, DBUNIT_ADDRESS_ID1));	
		testPartyAddress.setParty(hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY_ID1));
		testPartyAddress.setAddressType(PARTY_ADDRESS_TYPE);
		
		hibernateDAO.create(testPartyAddress);
		flushAndClear();
		
		PartyAddress partyAddressRetrieved = hibernateDAO.retrieve(PartyAddress.class, testPartyAddress.getId());
		
		assertEquals(DBUNIT_ADDRESS_ID1, partyAddressRetrieved.getAddress().getId());
		assertEquals(PARTY_ADDRESS_TYPE, partyAddressRetrieved.getAddressType());
		assertEquals(DBUNIT_COMPANY_ID1, partyAddressRetrieved.getParty().getId());

		assertAuditFields(testPartyAddress);		
	}
	
	@Test
	@DatabaseSetup("classpath:dbunit/testDataPartyAddress_expected.xml")
	public void updateTest()
	{
		final String UPDATED_ADDRESS_TYPE = "Updated Address Type";
		final Long UPDATED_PARTY_ID = 2L;
		final Long UPDATED_ADDRESS_ID = 2L;
		
		PartyAddress retrievedPartyAddress = hibernateDAO.retrieve(PartyAddress.class, DBUNIT_PARTYADDRESS_ID1);
		
		retrievedPartyAddress.setAddress(hibernateDAO.retrieve(Address.class, UPDATED_ADDRESS_ID));
		retrievedPartyAddress.setParty(hibernateDAO.retrieve(Company.class, UPDATED_PARTY_ID));
		retrievedPartyAddress.setAddressType(UPDATED_ADDRESS_TYPE);
		
		// Update the party address
		hibernateDAO.update(retrievedPartyAddress);
		flushAndClear();
		
		// Retrieve it again
		PartyAddress retrievedPartyAddress2 = hibernateDAO.retrieve(PartyAddress.class, retrievedPartyAddress.getId());
		
		assertEquals(UPDATED_ADDRESS_ID, retrievedPartyAddress2.getAddress().getId());
		assertEquals(UPDATED_PARTY_ID, retrievedPartyAddress2.getParty().getId());
		assertEquals(UPDATED_ADDRESS_TYPE, retrievedPartyAddress2.getAddressType());
		}
	
	@Test (expected=ObjectNotFoundException.class)
	public void deleteTest()
	{
		PartyAddress retrievedPartyAddress = hibernateDAO.retrieve(PartyAddress.class, DBUNIT_PARTYADDRESS_ID1);		
		
		hibernateDAO.delete(retrievedPartyAddress);
		flushAndClear();
		
		hibernateDAO.retrieve(PartyAddress.class, DBUNIT_PARTYADDRESS_ID1);
		
		// This should throw the exception ObjectNotFoundException because of the lazy load
		System.out.println("END:" + retrievedPartyAddress);
	}

	@Test()
	@DatabaseSetup("classpath:dbunit/testDataPartyAddress_expected.xml")
	public void staleStateExceptionTest()
	{
		//1st partyAddress
		PartyAddress partyAddress1 = hibernateDAO.retrieve(PartyAddress.class, DBUNIT_PARTYADDRESS_ID1);
		flushAndClear();
		//2nd partyAddress
		PartyAddress partyAddress2 = hibernateDAO.retrieve(PartyAddress.class, DBUNIT_PARTYADDRESS_ID1);
		flushAndClear();
		
		//Update partyAddress1
		partyAddress1.setAddressType("Updated the Address type");
		
		hibernateDAO.update(partyAddress1);
		flushAndClear();
		assertEquals("Version number not incremented", 1, partyAddress1.getVersionNumber().intValue());
		
		//Update partyAddress2 back to original
		partyAddress2.setAddressType(PARTY_ADDRESS_TYPE);
		// and tries to update
		try
		{
			hibernateDAO.update(partyAddress2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, partyAddress2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown");
		}
		
	}
}
