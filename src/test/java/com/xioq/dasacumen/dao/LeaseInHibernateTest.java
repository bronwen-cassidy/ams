package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
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

import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.LeaseIn;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
/**
 * Tests that Lease In is working with CRUD operations.
 * @author nmarlor
 *
 */
public class LeaseInHibernateTest
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

	private static final Long id = 1L;

	private static final BigDecimal leaseCharge = new BigDecimal(122.23);
	private static final Date leaseCommences = new Date(99, 12, 04);
	private static final Date leaseExpires = new Date(101, 12, 04);
	private static final String leasePeriod = "12";	
	private static final BigDecimal leaseCost = new BigDecimal(50.21);
	private static final BigDecimal leaseValue = new BigDecimal(1000);
	private static final String locationPostcode = "M45 6TF";
	private static final Boolean maintenanceIncluded = true;
	private static final Boolean warrantyIncluded = true;
	private static final BigDecimal leaseOutMargin = new BigDecimal(123.43);

	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	private static final Integer tenantId = 1;
	private static final Integer versionNumber = 0;

	/**
	 * LeaseIn used for testing.
	 */
	LeaseIn LeaseIn;

	/**
	 * Sets up a test LeaseIn
	 */
	@Before
	public void setUp()
	{

		LeaseIn = new LeaseIn();

		LeaseIn.setLeaseCharge(leaseCharge);
		LeaseIn.setLeaseCommences(leaseCommences);
		LeaseIn.setLeaseExpires(leaseExpires);
		LeaseIn.setLeasePeriod(leasePeriod);
		LeaseIn.setLeaseCost(leaseCost);
		LeaseIn.setLeaseValue(leaseValue);
		LeaseIn.setLocationPostcode(locationPostcode);
		LeaseIn.setMaintenanceIncluded(maintenanceIncluded);
		LeaseIn.setWarrantyIncluded(warrantyIncluded);
		LeaseIn.setLeaseOutMargin(leaseOutMargin);
		
		LeaseIn.setCreatedBy(createdBy);
		LeaseIn.setCreatedDate(createdDate);
		LeaseIn.setLastUpdatedBy(lastUpdatedBy);
		LeaseIn.setLastUpdatedDate(lastUpdatedDate);
		LeaseIn.setTenantId(tenantId);
		LeaseIn.setVersionNumber(versionNumber);

		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("lease_in");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");

	}

	@Test
	@DatabaseSetup("classpath:dbunit/leaseInTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseInNoDate.xml", table = "acumen.lease_in", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createLeaseInTest()
	{
		//Needed to setup lease in because dbunit data only lives here
		Company company = hibernateDAO.retrieve(Company.class, id);
		Assets assets = hibernateDAO.retrieve(Assets.class, id);
		UserData ud = hibernateDAO.retrieve(UserData.class, id);
		
		LeaseIn.setAsset(assets);
		LeaseIn.setPartyId(1L);
		LeaseIn.setChargePeriod(ud);
		LeaseIn.setChargePeriodId(1L);
		LeaseIn.setLeaseType(ud);
		LeaseIn.setLeaseTypeId(1L);
		LeaseIn.setVatCode(ud);
		LeaseIn.setVatCodeId(1L);
		
		// Create the list and contents in one operation
		hibernateDAO.create(LeaseIn);
		flushAndClear();

		LeaseIn retrievedLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);

		assertEquals(assets.getId(), retrievedLeaseIn.getAsset().getId());
		assertEquals(company.getId(), retrievedLeaseIn.getParty().getId());
		assertEquals(leaseCharge, retrievedLeaseIn.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLeaseIn.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLeaseIn.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLeaseIn.getLeasePeriod());
		assertEquals(leaseCost, retrievedLeaseIn.getLeaseCost());
		assertEquals(leaseValue, retrievedLeaseIn.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLeaseIn.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLeaseIn.getLocationPostcode());
		assertEquals(maintenanceIncluded, retrievedLeaseIn.getMaintenanceIncluded());
		assertEquals(warrantyIncluded, retrievedLeaseIn.getWarrantyIncluded());
		assertEquals(ud.getId(), retrievedLeaseIn.getChargePeriod().getId());
		assertEquals(ud.getId(), retrievedLeaseIn.getLeaseType().getId());
		assertEquals(ud.getId(), retrievedLeaseIn.getVatCode().getId());

		assertEquals(tenantId, retrievedLeaseIn.getTenantId());
		assertEquals(createdBy, retrievedLeaseIn.getCreatedBy());
		assertNotNull(retrievedLeaseIn.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLeaseIn.getLastUpdatedBy());
		assertNotNull(retrievedLeaseIn.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLeaseIn.getVersionNumber());

	}
	
	/**
	 * Checks that a lease in can be retrieved.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseIn.xml")
	public void retrieveLeaseInTest() {
		
		Company company = hibernateDAO.retrieve(Company.class, id);
		Assets assets = hibernateDAO.retrieve(Assets.class, id);
		UserData ud = hibernateDAO.retrieve(UserData.class, id);
		
		LeaseIn retrievedLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);
		
		assertEquals(assets.getId(), retrievedLeaseIn.getAsset().getId());
		assertEquals(company.getId(), retrievedLeaseIn.getParty().getId());
		assertEquals(leaseCharge, retrievedLeaseIn.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLeaseIn.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLeaseIn.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLeaseIn.getLeasePeriod());
		assertEquals(leaseCost, retrievedLeaseIn.getLeaseCost());
		assertEquals(leaseValue, retrievedLeaseIn.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLeaseIn.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLeaseIn.getLocationPostcode());
		assertEquals(maintenanceIncluded, retrievedLeaseIn.getMaintenanceIncluded());
		assertEquals(warrantyIncluded, retrievedLeaseIn.getWarrantyIncluded());
		assertEquals(ud.getId(), retrievedLeaseIn.getChargePeriod().getId());
		assertEquals(ud.getId(), retrievedLeaseIn.getLeaseType().getId());
		assertEquals(ud.getId(), retrievedLeaseIn.getVatCode().getId());

		assertEquals(tenantId, retrievedLeaseIn.getTenantId());
		assertEquals(createdBy, retrievedLeaseIn.getCreatedBy());
		assertNotNull(retrievedLeaseIn.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLeaseIn.getLastUpdatedBy());
		assertNotNull(retrievedLeaseIn.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLeaseIn.getVersionNumber());
	}
	
	/**
	 * Tests if a lease in is updated correctly.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/testDataWithTestLeaseIn.xml")
	public void updateLeaseInTest(){
		
		Long updatedId = 2L;
		BigDecimal updatedLeaseCharge = new BigDecimal(6000.93);
		Date updatedLeaseCommences = new Date (303, 11, 11);
		Date updatedLeaseExpires = new Date (304, 11, 11);
		BigDecimal updatedLeaseOutMargin = new BigDecimal(10000.10);
		String updatedLeasePeriod = "20";
		BigDecimal updatedLeaseCost = new BigDecimal(68.80);
		BigDecimal updatedLeaseValue = new BigDecimal(2000);
		String updatedLocationPostcode = "M45 1BE";
		Boolean updatedMaintenanceIncluded = false;
		Boolean updatedWarrantyIncluded = false;
		
		LeaseIn retrievedLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);
		
		//Needed to setup lease in because dbunit data only lives here
		retrievedLeaseIn.setAsset(hibernateDAO.retrieve(Assets.class, updatedId));
		retrievedLeaseIn.setParty(hibernateDAO.retrieve(Party.class, updatedId));
		UserData ud = hibernateDAO.retrieve(UserData.class, updatedId);
		retrievedLeaseIn.setChargePeriod(ud);
		retrievedLeaseIn.setChargePeriodId(2L);
		retrievedLeaseIn.setLeaseType(ud);
		retrievedLeaseIn.setLeaseTypeId(2L);
		retrievedLeaseIn.setVatCode(ud);
		retrievedLeaseIn.setVatCodeId(2L);
		
		retrievedLeaseIn.setLeaseCharge(updatedLeaseCharge);
		retrievedLeaseIn.setLeaseCommences(updatedLeaseCommences);
		retrievedLeaseIn.setLeaseExpires(updatedLeaseExpires);
		retrievedLeaseIn.setLeasePeriod(updatedLeasePeriod);
		retrievedLeaseIn.setLeaseCost(updatedLeaseCost);
		retrievedLeaseIn.setLeaseValue(updatedLeaseValue);
		retrievedLeaseIn.setLocationPostcode(updatedLocationPostcode);
		retrievedLeaseIn.setMaintenanceIncluded(updatedMaintenanceIncluded);
		retrievedLeaseIn.setWarrantyIncluded(updatedWarrantyIncluded);
		retrievedLeaseIn.setLeaseOutMargin(updatedLeaseOutMargin);
		
		// Update the lease in
		hibernateDAO.update(retrievedLeaseIn);
		flushAndClear();
		
		// Retrieve it again
		LeaseIn retrievedLeaseIn2 = hibernateDAO.retrieve(LeaseIn.class, retrievedLeaseIn.getId());
		
		// Asserts fields are retrieved correctly.
		assertEquals(updatedId, retrievedLeaseIn2.getAsset().getId());
		assertEquals(updatedId, retrievedLeaseIn2.getLeaseType().getId());
		assertEquals(updatedId, retrievedLeaseIn2.getVatCode().getId());
		assertEquals(updatedId, retrievedLeaseIn2.getChargePeriod().getId());
		
		assertEquals(updatedLeaseCharge, retrievedLeaseIn2.getLeaseCharge());
		assertEquals(updatedLeaseCommences, retrievedLeaseIn2.getLeaseCommences());
		assertEquals(updatedLeaseExpires, retrievedLeaseIn2.getLeaseExpires());
		assertEquals(updatedLeasePeriod, retrievedLeaseIn2.getLeasePeriod());
		assertEquals(updatedLeaseCost, retrievedLeaseIn2.getLeaseCost());
		assertEquals(updatedLeaseValue, retrievedLeaseIn2.getLeaseValue());
		assertEquals(updatedLocationPostcode, retrievedLeaseIn2.getLocationPostcode());
		assertEquals(updatedMaintenanceIncluded, retrievedLeaseIn2.getMaintenanceIncluded());
		assertEquals(updatedWarrantyIncluded, retrievedLeaseIn2.getWarrantyIncluded());
	}
	
	/**
	 * Test checks these fields cannot be updated by the system.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseIn.xml")
    @ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseIn.xml", table = "acumen.lease_in", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void failToUpdateNonUpdatableLeaseInFields() {
		// Updated fields
		Integer updatedTenantId  = 5;
		String updatedCreatedBy = "JoeBloggs@hotmail.co.uk";
		Date updatedCreatedDate = new Date(120, 10, 10);
		
		//Retrieve the lease in to update
		LeaseIn retrievedLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);
		
		// Set lease in with new fields
		retrievedLeaseIn.setTenantId(updatedTenantId);
		retrievedLeaseIn.setCreatedBy(updatedCreatedBy);
		retrievedLeaseIn.setCreatedDate(updatedCreatedDate);
		
		// Update the lease in
		hibernateDAO.update(retrievedLeaseIn);
		flushAndClear();
		
		// Retrieve it again
		LeaseIn retrievedLeaseIn2 = hibernateDAO.retrieve(LeaseIn.class, retrievedLeaseIn.getId());
		
		// Asserts fields are not changed.
		assertEquals(tenantId, retrievedLeaseIn2.getTenantId());
		assertEquals(createdBy, retrievedLeaseIn2.getCreatedBy());
		assertNotNull(retrievedLeaseIn2.getCreatedDate());
	}
	
	/**
	 * Tests if a lease in can be deleted correctly.
	 */
	@Test(expected=ObjectNotFoundException.class)
    @DatabaseSetup("classpath:dbunit/testDataWithTestLeaseIn.xml")
	public void deleteLeaseInTest(){
		
		LeaseIn retrievedLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);
		flushAndClear();
		
		hibernateDAO.delete(retrievedLeaseIn);
		flushAndClear();
		
		LeaseIn retrievedLeaseIn2 = hibernateDAO.retrieve(LeaseIn.class, id);
		
		// Will trigger the exception
		System.out.println(retrievedLeaseIn2.getParty());
	}
	
	/**
	 * Test to check that in the scenario where two users edit the same version of a party telephone number but update at different times
	 * the user who tries to update last will get a stale state exception because the data is now out of date and they will
	 * have to retrieve the party telephone number again.
	 */
	@Test(expected=StaleStateException.class)
    @DatabaseSetup("classpath:dbunit/testDataWithTestLeaseIn.xml")
	@Transactional
	public void staleStateExceptionTest() {
		LeaseIn firstLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);
		flushAndClear();
		
		LeaseIn sameLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);
		flushAndClear();
		
		firstLeaseIn.setLocationPostcode("BL9 8OU");
		hibernateDAO.update(firstLeaseIn);
		flushAndClear();
		
		sameLeaseIn.setLocationPostcode("M56 7SY");
		hibernateDAO.update(sameLeaseIn);
		flushAndClear();	
	}
	
	/**
	 * Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear()
	{
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

}
