package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.xioq.dasacumen.model.common.LeaseOut;
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
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
/**
 * Tests that LeaseOut is working with CRUD operations.
 * @author mwaude
 */
public class LeaseOutHibernateTest {
	@Autowired
	private TestUtil testUtil;

	/*
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;

	/*
	 * Creates an instance of session factory so that the session cache can be cleared to avoid false positives on tests
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
	 * Lease out used for testing.
	 */
	LeaseOut leaseOut;

	/**
	 * Sets up a test Lease out
	 */
	@Before
	public void setUp() {

		leaseOut = new LeaseOut();

		leaseOut.setLeaseCharge(leaseCharge);
		leaseOut.setLeaseCommences(leaseCommences);
		leaseOut.setLeaseExpires(leaseExpires);
		leaseOut.setLeasePeriod(leasePeriod);
		leaseOut.setLeaseCost(leaseCost);
		leaseOut.setLeaseValue(leaseValue);
		leaseOut.setLocationPostcode(locationPostcode);
		leaseOut.setLeaseOutMargin(leaseOutMargin);

		leaseOut.setCreatedBy(createdBy);
		leaseOut.setCreatedDate(createdDate);
		leaseOut.setLastUpdatedBy(lastUpdatedBy);
		leaseOut.setLastUpdatedDate(lastUpdatedDate);
		leaseOut.setTenantId(tenantId);
		leaseOut.setVersionNumber(versionNumber);

		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("lease_out");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");

	}

	@Test
	@DatabaseSetup("classpath:dbunit/leaseOutTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseOutNoDate.xml", table = "acumen.lease_out", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createLeaseOutTest() {
		//Needed to setup Lease out because dbunit data only lives here
		Assets assets = hibernateDAO.retrieve(Assets.class, id);
		UserData ud = hibernateDAO.retrieve(UserData.class, id);

		leaseOut.setAsset(assets);
		leaseOut.setChargePeriod(ud);
		leaseOut.setChargePeriodId(1L);
		leaseOut.setLeaseType(ud);
		leaseOut.setLeaseTypeId(1L);
		leaseOut.setVatCode(ud);
		leaseOut.setVatCodeId(1L);

		// Create the list and contents in one operation
		hibernateDAO.create(leaseOut);
		flushAndClear();

		LeaseOut retrievedLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);

		assertEquals(assets.getId(), retrievedLeaseOut.getAsset().getId());
		assertEquals(leaseCharge, retrievedLeaseOut.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLeaseOut.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLeaseOut.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLeaseOut.getLeasePeriod());
		assertEquals(leaseCost, retrievedLeaseOut.getLeaseCost());
		assertEquals(leaseValue, retrievedLeaseOut.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLeaseOut.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLeaseOut.getLocationPostcode());
		assertEquals(ud.getId(), retrievedLeaseOut.getChargePeriod().getId());
		assertEquals(ud.getId(), retrievedLeaseOut.getLeaseType().getId());
		assertEquals(ud.getId(), retrievedLeaseOut.getVatCode().getId());

		assertEquals(tenantId, retrievedLeaseOut.getTenantId());
		assertEquals(createdBy, retrievedLeaseOut.getCreatedBy());
		assertNotNull(retrievedLeaseOut.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLeaseOut.getLastUpdatedBy());
		assertNotNull(retrievedLeaseOut.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLeaseOut.getVersionNumber());

	}

	/**
	 * Checks that a Lease out can be retrieved.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOut.xml")
	public void retrieveLeaseOutTest() {
		Assets assets = hibernateDAO.retrieve(Assets.class, id);
		UserData ud = hibernateDAO.retrieve(UserData.class, id);

		LeaseOut retrievedLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);

		assertEquals(assets.getId(), retrievedLeaseOut.getAsset().getId());
		assertEquals(leaseCharge, retrievedLeaseOut.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLeaseOut.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLeaseOut.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLeaseOut.getLeasePeriod());
		assertEquals(leaseCost, retrievedLeaseOut.getLeaseCost());
		assertEquals(leaseValue, retrievedLeaseOut.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLeaseOut.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLeaseOut.getLocationPostcode());
		assertEquals(ud.getId(), retrievedLeaseOut.getChargePeriod().getId());
		assertEquals(ud.getId(), retrievedLeaseOut.getLeaseType().getId());
		assertEquals(ud.getId(), retrievedLeaseOut.getVatCode().getId());

		assertEquals(tenantId, retrievedLeaseOut.getTenantId());
		assertEquals(createdBy, retrievedLeaseOut.getCreatedBy());
		assertNotNull(retrievedLeaseOut.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLeaseOut.getLastUpdatedBy());
		assertNotNull(retrievedLeaseOut.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLeaseOut.getVersionNumber());
	}

	/**
	 * Tests if a Lease out is updated correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOut.xml")
	public void updateLeaseOutTest() {

		Long updatedId = 2L;
		BigDecimal updatedLeaseCharge = new BigDecimal(6000.93);
		Date updatedLeaseCommences = new Date(303, 11, 11);
		Date updatedLeaseExpires = new Date(304, 11, 11);
		BigDecimal updatedLeaseOutMargin = new BigDecimal(10000.10);
		String updatedLeasePeriod = "20";
		BigDecimal updatedLeaseCost = new BigDecimal(68.80);
		BigDecimal updatedLeaseValue = new BigDecimal(2000);
		String updatedLocationPostcode = "M45 1BE";
		Boolean updatedMaintenanceIncluded = false;
		Boolean updatedWarrantyIncluded = false;

		LeaseOut retrievedLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);

		//Needed to setup Lease out because dbunit data only lives here
		retrievedLeaseOut.setAsset(hibernateDAO.retrieve(Assets.class, updatedId));
		UserData ud = hibernateDAO.retrieve(UserData.class, updatedId);
		retrievedLeaseOut.setChargePeriod(ud);
		retrievedLeaseOut.setChargePeriodId(2L);
		retrievedLeaseOut.setLeaseType(ud);
		retrievedLeaseOut.setLeaseTypeId(2L);
		retrievedLeaseOut.setVatCode(ud);
		retrievedLeaseOut.setVatCodeId(2L);

		retrievedLeaseOut.setLeaseCharge(updatedLeaseCharge);
		retrievedLeaseOut.setLeaseCommences(updatedLeaseCommences);
		retrievedLeaseOut.setLeaseExpires(updatedLeaseExpires);
		retrievedLeaseOut.setLeasePeriod(updatedLeasePeriod);
		retrievedLeaseOut.setLeaseCost(updatedLeaseCost);
		retrievedLeaseOut.setLeaseValue(updatedLeaseValue);
		retrievedLeaseOut.setLocationPostcode(updatedLocationPostcode);
		retrievedLeaseOut.setLeaseOutMargin(updatedLeaseOutMargin);

		// Update the Lease out
		hibernateDAO.update(retrievedLeaseOut);
		flushAndClear();

		// Retrieve it again
		LeaseOut retrievedLeaseOut2 = hibernateDAO.retrieve(LeaseOut.class, retrievedLeaseOut.getId());

		// Asserts fields are retrieved correctly.
		assertEquals(updatedId, retrievedLeaseOut2.getAsset().getId());
		assertEquals(updatedId, retrievedLeaseOut2.getLeaseType().getId());
		assertEquals(updatedId, retrievedLeaseOut2.getVatCode().getId());
		assertEquals(updatedId, retrievedLeaseOut2.getChargePeriod().getId());

		assertEquals(updatedLeaseCharge, retrievedLeaseOut2.getLeaseCharge());
		assertEquals(updatedLeaseCommences, retrievedLeaseOut2.getLeaseCommences());
		assertEquals(updatedLeaseExpires, retrievedLeaseOut2.getLeaseExpires());
		assertEquals(updatedLeasePeriod, retrievedLeaseOut2.getLeasePeriod());
		assertEquals(updatedLeaseCost, retrievedLeaseOut2.getLeaseCost());
		assertEquals(updatedLeaseValue, retrievedLeaseOut2.getLeaseValue());
		assertEquals(updatedLocationPostcode, retrievedLeaseOut2.getLocationPostcode());
	}

	/**
	 * Test checks these fields cannot be updated by the system.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOut.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseOut.xml", table = "acumen.lease_out", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void failToUpdateNonUpdatableLeaseOutFields() {
		// Updated fields
		Integer updatedTenantId = 5;
		String updatedCreatedBy = "JoeBloggs@hotmail.co.uk";
		Date updatedCreatedDate = new Date(120, 10, 10);

		//Retrieve the Lease out to update
		LeaseOut retrievedLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);

		// Set Lease out with new fields
		retrievedLeaseOut.setTenantId(updatedTenantId);
		retrievedLeaseOut.setCreatedBy(updatedCreatedBy);
		retrievedLeaseOut.setCreatedDate(updatedCreatedDate);

		// Update the Lease out
		hibernateDAO.update(retrievedLeaseOut);
		flushAndClear();

		// Retrieve it again
		LeaseOut retrievedLeaseOut2 = hibernateDAO.retrieve(LeaseOut.class, retrievedLeaseOut.getId());

		// Asserts fields are not changed.
		assertEquals(tenantId, retrievedLeaseOut2.getTenantId());
		assertEquals(createdBy, retrievedLeaseOut2.getCreatedBy());
		assertNotNull(retrievedLeaseOut2.getCreatedDate());
	}

	/**
	 * Tests if a Lease out can be deleted correctly.
	 */
	@Test(expected = ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOut.xml")
	public void deleteLeaseOutTest() {
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		flushAndClear();
		
		LeaseOut retrievedLeaseOut = testAsset.getLeaseOut();
		testAsset.setLeaseOut(null);
		hibernateDAO.update(testAsset);
		hibernateDAO.delete(retrievedLeaseOut);
		flushAndClear();

		LeaseOut retrievedLeaseOut2 = hibernateDAO.retrieve(LeaseOut.class, retrievedLeaseOut.getId());
		
		
		// Will trigger the exception
		System.out.println(retrievedLeaseOut2.getAsset());
	}

	/**
	 * Test to check that in the scenario where two users edit the same version of a party telephone number but update at different times the user who
	 * tries to update last will get a stale state exception because the data is now out of date and they will have to retrieve the party telephone
	 * number again.
	 */
	@Test(expected = StaleStateException.class)
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOut.xml")
	@Transactional
	public void staleStateExceptionTest() {
		LeaseOut firstLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);
		flushAndClear();

		LeaseOut sameLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);
		flushAndClear();

		firstLeaseOut.setLocationPostcode("BL9 8OU");
		hibernateDAO.update(firstLeaseOut);
		flushAndClear();

		sameLeaseOut.setLocationPostcode("M56 7SY");
		hibernateDAO.update(sameLeaseOut);
		flushAndClear();
	}

	/**
	 * Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}

}
