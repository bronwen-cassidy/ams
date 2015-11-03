package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.PartyTelephoneNumber;
import com.xioq.dasacumen.model.common.TelephoneNumber;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
/**
 * Tests that the party telephone numbers object interacts with hibernate and the db correctly.
 * @author nmarlor
 *
 */
public class PartyTelephoneNumbersHibernateTest 
{
	@Autowired
	private TestUtil testUtil;

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
	
	private static final Long id = 50L;
	
	static final Long telNumbersType = 173L;

	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	private static final Integer tenantId = 1;
	private static final Integer versionNumber = 0;
	
	static final String uid = "party_uid_123468946546546";
	
	/**
	 * Party telephone number used for testing.
	 */
	PartyTelephoneNumber testPartyTelNo;
	
	/**
	 * foreign key telephone number field
	 */
	TelephoneNumber telNo = new TelephoneNumber();
	
	/**
	 * foreign key company field
	 */
	Company companyParty = new Company();

	/**
	 * Sets up a test party telephone number
	 */
	@Before
	public void setup() {
		testPartyTelNo = new PartyTelephoneNumber();
		
		testPartyTelNo.setTelNumberType(telNumbersType);
		
		testPartyTelNo.setCreatedBy(createdBy);
		testPartyTelNo.setCreatedDate(createdDate);
		testPartyTelNo.setLastUpdatedBy(lastUpdatedBy);
		testPartyTelNo.setLastUpdatedDate(lastUpdatedDate);
		testPartyTelNo.setTenantId(tenantId);
		testPartyTelNo.setVersionNumber(versionNumber);

		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("party_telephone_numbers");
		testUtil.resetSequences("tel_numbers");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");	
	}
	
	/**
	 * Tests if a party telephone number can be created correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/partyTelephoneNumbersTestData.xml")
	public void createPartyTelephoneNumbersTest() {	
		Company company = hibernateDAO.retrieve(Company.class, id);
		TelephoneNumber telephoneNumber = hibernateDAO.retrieve(TelephoneNumber.class, id);
		
		testPartyTelNo.setParty(company);
		testPartyTelNo.setTelephoneNumber(telephoneNumber);
		
		// Create the list and contents in one operation
		hibernateDAO.create(testPartyTelNo);
		flushAndClear();
			
		PartyTelephoneNumber partyTelephoneNumbersRetrieved = hibernateDAO.retrieve(PartyTelephoneNumber.class, testPartyTelNo.getId());
			
		assertEquals(company.getId(), partyTelephoneNumbersRetrieved.getParty().getId());
		assertEquals(telephoneNumber.getId(), partyTelephoneNumbersRetrieved.getTelephoneNumber().getId());
		assertEquals(telNumbersType, partyTelephoneNumbersRetrieved.getTelNumberType());
		
		assertEquals(createdBy, partyTelephoneNumbersRetrieved.getCreatedBy());
		assertEquals(lastUpdatedBy, partyTelephoneNumbersRetrieved.getLastUpdatedBy());
		assertNotNull(partyTelephoneNumbersRetrieved.getCreatedDate());
		assertNotNull(partyTelephoneNumbersRetrieved.getLastUpdatedDate());
		assertEquals(tenantId, partyTelephoneNumbersRetrieved.getTenantId());
		assertEquals(versionNumber, partyTelephoneNumbersRetrieved.getVersionNumber());
	}
	 
	/**
	 * Tests if a party telephone number can be retrieved correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithPartyTelephone.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithPartyTelephone.xml", table = "acumen.party_telephone_numbers", assertionMode = DatabaseAssertionMode.NON_STRICT)
	@Transactional
	public void retrievePartyTelephoneNumbersTest() {	
		Company company = hibernateDAO.retrieve(Company.class, id);
		TelephoneNumber telephoneNumber = hibernateDAO.retrieve(TelephoneNumber.class, id);
		
		PartyTelephoneNumber retrievedPartyTelephoneNumber = hibernateDAO.retrieve(PartyTelephoneNumber.class, id);
		
		assertEquals(company.getId(), retrievedPartyTelephoneNumber.getParty().getId());
		assertEquals(telephoneNumber.getId(), retrievedPartyTelephoneNumber.getTelephoneNumber().getId());
		
		assertEquals(telNumbersType, retrievedPartyTelephoneNumber.getTelNumberType());
		
		assertEquals(createdBy, retrievedPartyTelephoneNumber.getCreatedBy());
		assertEquals(lastUpdatedBy, retrievedPartyTelephoneNumber.getLastUpdatedBy());
		assertNotNull(retrievedPartyTelephoneNumber.getCreatedDate());
		assertNotNull(retrievedPartyTelephoneNumber.getLastUpdatedDate());
	}
		
	/**
	 * Tests if a party telephone number can be updated correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithPartyTelephone.xml")
	@Transactional
	public void updatePartyTelephoneNumbersTest() {
		Long updatedId = 51L;
		Long updatedTelNumbersType = 174L;
		
		PartyTelephoneNumber retrievedPartyTelephoneNumber = hibernateDAO.retrieve(PartyTelephoneNumber.class, id);
		
		//Needed to setup tenure because dbunit data only lives here
		retrievedPartyTelephoneNumber.setParty(hibernateDAO.retrieve(Party.class, updatedId));
		retrievedPartyTelephoneNumber.setTelephoneNumber(hibernateDAO.retrieve(TelephoneNumber.class, updatedId));
		
		retrievedPartyTelephoneNumber.setTelNumberType(updatedTelNumbersType);
		
		// Update the party telephone number
		hibernateDAO.update(retrievedPartyTelephoneNumber);
		flushAndClear();
		
		// Retrieve it again
		PartyTelephoneNumber retrievedPartyTelephoneNumber2 = hibernateDAO.retrieve(PartyTelephoneNumber.class, retrievedPartyTelephoneNumber.getId());
		
		// Asserts fields are retrieved correctly.
		assertEquals(updatedId, retrievedPartyTelephoneNumber2.getParty().getId());
		assertEquals(updatedId, retrievedPartyTelephoneNumber2.getTelephoneNumber().getId());
		
		assertEquals(updatedTelNumbersType, retrievedPartyTelephoneNumber2.getTelNumberType());
	}
	
	/**
	 * Tests if a party telephone number can be successfully deleted.
	 */
	@Test (expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/testDataWithPartyTelephone.xml")
	public void deletePartyTelephoneNumbersTest() {
		PartyTelephoneNumber retrievedPartyTelephoneNumber = hibernateDAO.retrieve(PartyTelephoneNumber.class, id);
		flushAndClear();
		
		hibernateDAO.delete(retrievedPartyTelephoneNumber);
		flushAndClear();
		
		PartyTelephoneNumber retrievedPartyTelephoneNumber2 = hibernateDAO.retrieve(PartyTelephoneNumber.class, retrievedPartyTelephoneNumber.getId());
		
		// Will trigger the exception
		System.out.println(retrievedPartyTelephoneNumber2.getTelephoneNumber());
	}
	
	/**
	 * Tests if certain audit fields are non updateable. 
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithPartyTelephone.xml")
	public void failToUpdateNonUpdatableInsurancePolicyFields() {
		// Updated fields
		Integer UpdatedTenantId  = 68;
		String UpdatedCreatedBy = "PeterGriffin@jla.co.uk";
		Date UpdatedCreatedDate = new Date(24, 11, 21);
		
		//Retrieve the party telephone number to update
		PartyTelephoneNumber retrievedPartyTelephoneNumber = hibernateDAO.retrieve(PartyTelephoneNumber.class, id);
		
		// Set with new fields.
		retrievedPartyTelephoneNumber.setTenantId(UpdatedTenantId);
		retrievedPartyTelephoneNumber.setCreatedBy(UpdatedCreatedBy);
		retrievedPartyTelephoneNumber.setCreatedDate(UpdatedCreatedDate);
		
		// Update the partyTelephoneNumber
		hibernateDAO.update(retrievedPartyTelephoneNumber);
		flushAndClear();
		
		// Retrieve it again
		PartyTelephoneNumber retrievedPartyTelephoneNumber2 = hibernateDAO.retrieve(PartyTelephoneNumber.class, retrievedPartyTelephoneNumber.getId());
		
		// Asserts fields are not changed.
		assertEquals(tenantId, retrievedPartyTelephoneNumber2.getTenantId());
		assertEquals(createdBy, retrievedPartyTelephoneNumber2.getCreatedBy());
		assertEquals(createdDate, retrievedPartyTelephoneNumber2.getCreatedDate());
	}
	
	/**
	 * Test to check that in the scenario where two users edit the same version of a party telephone number but update at different times
	 * the user who tries to update last will get a stale state exception because the data is now out of date and they will
	 * have to retrieve the party telephone number again.
	 */
	@Test(expected=StaleStateException.class)
	@DatabaseSetup("classpath:dbunit/testDataWithPartyTelephone.xml")
	@Transactional
	public void staleStateExceptionTest() {
		PartyTelephoneNumber firstPartyTelephoneNumber = hibernateDAO.retrieve(PartyTelephoneNumber.class, id);
		flushAndClear();
		
		PartyTelephoneNumber samePartyTelephoneNumber = hibernateDAO.retrieve(PartyTelephoneNumber.class, id);
		flushAndClear();
		
		firstPartyTelephoneNumber.setTelNumberType(173L);
		hibernateDAO.update(firstPartyTelephoneNumber);
		flushAndClear();
		
		// Should get an error as the first update should have incremented (by
		// hibernate) the version number
		samePartyTelephoneNumber.setTelNumberType(174L);
		hibernateDAO.update(samePartyTelephoneNumber);
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
