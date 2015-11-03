/**
 * 
 */
package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
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
import com.xioq.dasacumen.model.common.LeaseOut;
import com.xioq.dasacumen.model.common.LeaseOutExtras;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners(
{ DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
/**
 * Tests Asset with LeaseOut is working with CRUD operations.
 * @author mwaude
 */
public class AssetWithLeaseOutHibernateTest
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

	// LeaseOut specific fields.
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
	
	private static final BigDecimal extraCost = new BigDecimal(10);

	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	// Should only be used for retrieving as these values are set by db
	private static final Long id = 1L;
	private static final Integer versionNumber = 0;

	/**
	 * testLeaseOut and testLeaseOut used for testing.
	 */
	LeaseOut testLeaseOut;

	/**
	 * testLeaseOut2 and testLeaseOut2 used for testing.
	 */
	LeaseOut testLeaseOut2;
	
	LeaseOutExtras testLeaseOutExtras = new LeaseOutExtras();
	
	/**
	 * Sets up a test LeaseOut.
	 */
	@Before
	public void setup()
	{
		testLeaseOut = new LeaseOut();

		testLeaseOut.setLeaseCharge(leaseCharge);
		testLeaseOut.setLeaseCommences(leaseCommences);
		testLeaseOut.setLeaseExpires(leaseExpires);
		testLeaseOut.setLeasePeriod(leasePeriod);
		testLeaseOut.setLeaseCost(leaseCost);
		testLeaseOut.setLeaseValue(leaseValue);
		testLeaseOut.setLocationPostcode(locationPostcode);
		testLeaseOut.setLeaseOutMargin(leaseOutMargin);

		testLeaseOut.setCreatedBy(createdBy);
		testLeaseOut.setCreatedDate(createdDate);
		testLeaseOut.setLastUpdatedBy(lastUpdatedBy);
		testLeaseOut.setLastUpdatedDate(lastUpdatedDate);
		testLeaseOut.setTenantId(tenantId);
		testLeaseOut.setVersionNumber(versionNumber);

		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("lease_out");//TODO:reset sequences for lease in and lease out
		testUtil.resetSequences("lease_out_extras");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
	}

	/**
	 * Create a LeaseOut with an asset.
	 * All pre req for an asset creation exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithoutAssetOrLeaseOut.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseOutWithoutDate.xml", table = "acumen.lease_out", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createAssetWithLeaseOutsTest()
	{
		// Retrieve a user data and party
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		Company partyCompany = hibernateDAO.retrieve(Company.class, id);

		// set up an asset for creation.
		Assets testAsset = new Assets();

		testAsset.setCompanyId(userData.getId());
		testAsset.setAssetStatusId(userData.getId());
		testAsset.setSupplierId(partyCompany.getId());
		testAsset.setCustodianId(partyCompany.getId());

		testAsset.setAssetNumberPart1Id(userData.getId());
		testAsset.setAssetNumberPart2Id(userData.getId());
		testAsset.setAssetNumberPart3Id(userData.getId());
		testAsset.setAssetNumberPart4(1);
		testAsset.setSiteId(userData.getId());
		testAsset.setLocationId(userData.getId());

		testAsset.setCategoryId(userData.getId());
		testAsset.setCountryId(userData.getId());

		testAsset.setDepreciationCodeId(userData.getId());
		testAsset.setDivisionId(userData.getId());
		testAsset.setDepartmentId(userData.getId());

		testAsset.setName("PS4 Console");
		testAsset.setDescription("Gaming system");

		testAsset.setSerialNumber("PN-1322154545");
		testAsset.setSupplierPn("SONY_PN_12354546555");

		testAsset.setIsAFacility(false);
		testAsset.setIsEquipment(false);
		testAsset.setIsPart(false);
		testAsset.setCriticalAsset(false);

		testAsset.setRiskAssessment(true);

		testAsset.setBcp(false);

		testAsset.setTenantId(tenantId);
		testAsset.setVersionNumber(versionNumber);

		testAsset.setCreatedBy(createdBy);
		testAsset.setCreatedDate(createdDate);

		testAsset.setLastUpdatedBy(lastUpdatedBy);
		testAsset.setLastUpdatedDate(lastUpdatedDate);

		// finish setting the testLeaseOut
		testLeaseOut.setAsset(testAsset);
		testLeaseOut.setChargePeriod(userData);
		testLeaseOut.setChargePeriodId(1L);
		testLeaseOut.setLeaseType(userData);
		testLeaseOut.setLeaseTypeId(1L);
		testLeaseOut.setVatCode(userData);
		testLeaseOut.setVatCodeId(1L);

		// Add the testLeaseOut to the set and add the set to the asset object
		testAsset.setLeaseOut(testLeaseOut);

		// save the asset
		hibernateDAO.create(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseOut retrievedLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedLeaseOut.getAsset().getId());
		assertEquals(leaseCharge, retrievedLeaseOut.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLeaseOut.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLeaseOut.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLeaseOut.getLeasePeriod());
		assertEquals(leaseCost, retrievedLeaseOut.getLeaseCost());
		assertEquals(leaseValue, retrievedLeaseOut.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLeaseOut.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLeaseOut.getLocationPostcode());
		assertEquals(leaseOutMargin, retrievedLeaseOut.getLeaseOutMargin());
		assertEquals(userData.getId(), retrievedLeaseOut.getChargePeriod().getId());
		assertEquals(userData.getId(), retrievedLeaseOut.getLeaseType().getId());
		assertEquals(userData.getId(), retrievedLeaseOut.getVatCode().getId());

		assertEquals(tenantId, retrievedLeaseOut.getTenantId());
		assertEquals(createdBy, retrievedLeaseOut.getCreatedBy());
		assertNotNull(retrievedLeaseOut.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLeaseOut.getLastUpdatedBy());
		assertNotNull(retrievedLeaseOut.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLeaseOut.getVersionNumber());
	}

	/**
	 * Create a LeaseOut on an existing asset.
	 * Asset + no LeaseOut type.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/leaseOutTestDataWithTestAsset.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseOutWithoutDate.xml", table = "acumen.lease_out", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createLeaseOutWithExistingAssetTest()
	{
		// Retrieve asset, userdata and party
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		Company partyCompany = hibernateDAO.retrieve(Company.class, id);

		// finish setting the testLeaseOut
//		testLeaseOut.setAsset(testAsset);
		testLeaseOut.setChargePeriod(userData);
		testLeaseOut.setChargePeriodId(1L);
		testLeaseOut.setLeaseType(userData);
		testLeaseOut.setLeaseTypeId(1L);
		testLeaseOut.setVatCode(userData);
		testLeaseOut.setVatCodeId(1L);
		
		// Add the testLeaseOut to the set and add the set to the asset object
		testAsset.setLeaseOut(testLeaseOut);
		
		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseOut retrievedLI = hibernateDAO.retrieve(LeaseOut.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedLI.getAsset().getId());
		assertEquals(leaseCharge, retrievedLI.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLI.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLI.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLI.getLeasePeriod());
		assertEquals(leaseCost, retrievedLI.getLeaseCost());
		assertEquals(leaseValue, retrievedLI.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLI.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLI.getLocationPostcode());
		assertEquals(userData.getId(), retrievedLI.getLeaseType().getId());
		assertEquals(userData.getId(), retrievedLI.getVatCode().getId());

		assertEquals(tenantId, retrievedLI.getTenantId());
		assertEquals(createdBy, retrievedLI.getCreatedBy());
		assertNotNull(retrievedLI.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLI.getLastUpdatedBy());
		assertNotNull(retrievedLI.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLI.getVersionNumber());
	}
	
	/**
	 * Create a LeaseOut and Extra on an existing asset.
	 * Asset + no LeaseOut type + no LeaseOutExtra.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/leaseOutTestDataWithTestAsset.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseOutExtraWithoutDate.xml", table = "acumen.lease_out_extras", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createLeaseOutAndExtraWithExistingAssetTest()
	{
		// Retrieve asset, userdata and party
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		
		// finish setting the testLeaseOut
		testLeaseOut.setChargePeriod(userData);
		testLeaseOut.setChargePeriodId(1L);
		testLeaseOut.setLeaseType(userData);
		testLeaseOut.setLeaseTypeId(1L);
		testLeaseOut.setVatCode(userData);
		testLeaseOut.setVatCodeId(1L);
		
		Set<LeaseOutExtras> leaseOutExtrasSet = testLeaseOut.getLeaseOutExtras();
		
		testLeaseOutExtras.setExtraCost(extraCost);
		testLeaseOutExtras.setLeaseOutExtras(userData);
		testLeaseOutExtras.setExtrasId(1L);
		testLeaseOutExtras.setLeaseOut(testLeaseOut);
		
		testLeaseOut.addLeaseOutExtra(testLeaseOutExtras);
		
		testAsset.setLeaseOut(testLeaseOut);
		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseOut retrievedLo = hibernateDAO.retrieve(LeaseOut.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedLo.getAsset().getId());
		assertEquals(testLeaseOut.getId(), retrievedLo.getLeaseOutExtra().getId());
		assertEquals(leaseCharge, retrievedLo.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLo.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLo.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLo.getLeasePeriod());
		assertEquals(leaseCost, retrievedLo.getLeaseCost());
		assertEquals(leaseValue, retrievedLo.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLo.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLo.getLocationPostcode());
		assertEquals(userData.getId(), retrievedLo.getLeaseType().getId());
		assertEquals(userData.getId(), retrievedLo.getVatCode().getId());
		assertEquals(extraCost, retrievedLo.getLeaseOutExtra().getExtraCost());

		assertEquals(tenantId, retrievedLo.getTenantId());
		assertEquals(createdBy, retrievedLo.getCreatedBy());
		assertNotNull(retrievedLo.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLo.getLastUpdatedBy());
		assertNotNull(retrievedLo.getLastUpdatedDate());
//		assertEquals(versionNumber, retrievedLo.getVersionNumber());
	}
	
	/**
	 * Create a LeaseOutExtra on an existing LeaseOut.
	 * LeaseOut + no LeaseOutExtra.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOutWithDate.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseOutExtraWithoutDate.xml", table = "acumen.lease_out_extras", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createLeaseOutExtraWithExistingLeaseOutTest()
	{
		Assets asset = hibernateDAO.retrieve(Assets.class, id);
		LeaseOut testLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		
		LeaseOutExtras testLeaseOutExtras = new LeaseOutExtras();
		
		Set<LeaseOutExtras> leaseOutExtrasSet = testLeaseOut.getLeaseOutExtras();
		
		testLeaseOutExtras.setExtraCost(extraCost);
		testLeaseOutExtras.setLeaseOutExtras(userData);
		testLeaseOutExtras.setExtrasId(1L);
		testLeaseOutExtras.setLeaseOut(testLeaseOut);
		
		leaseOutExtrasSet.add(testLeaseOutExtras);
		asset.getLeaseOut().addLeaseOutExtra(testLeaseOutExtras);		
		// save the asset
		hibernateDAO.update(testLeaseOut);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseOut retrievedLo = hibernateDAO.retrieve(LeaseOut.class, id);

		// Assert values are stored correctly
		assertEquals(testLeaseOut.getId(), retrievedLo.getAsset().getId());
		assertEquals(testLeaseOut.getId(), retrievedLo.getLeaseOutExtra().getId());
		assertEquals(extraCost, retrievedLo.getLeaseOutExtra().getExtraCost());

		assertEquals(tenantId, retrievedLo.getTenantId());
		assertEquals(createdBy, retrievedLo.getCreatedBy());
		assertNotNull(retrievedLo.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLo.getLastUpdatedBy());
		assertNotNull(retrievedLo.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLo.getVersionNumber());
	}

	/**
	 * Update a field on LeaseOut and save it by updating the asset.
	 * Asset + 1 LeaseOut already exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOut.xml")
	public void updateLeaseOutFromAssetTest()
	{

		// Retrieve asset, UserData, party, and existing LeaseOut
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, 2L);
		LeaseOut testLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);

		// finish setting the testLeaseOut
//		testLeaseOut.setAsset(testAsset);
		testLeaseOut.setChargePeriod(userData);
		testLeaseOut.setChargePeriodId(2L);
		testLeaseOut.setLeaseType(userData);
		testLeaseOut.setLeaseTypeId(2L);
		testLeaseOut.setVatCode(userData);
		testLeaseOut.setVatCodeId(2L);

		// Add the testLeaseOut to the set and add the set to the asset object
		testAsset.setLeaseOut(testLeaseOut);

		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseOut retrievedLeaseOut = hibernateDAO.retrieve(LeaseOut.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedLeaseOut.getAsset().getId());
		assertEquals(leaseCharge, retrievedLeaseOut.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLeaseOut.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLeaseOut.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLeaseOut.getLeasePeriod());
		assertEquals(leaseCost, retrievedLeaseOut.getLeaseCost());
		assertEquals(leaseValue, retrievedLeaseOut.getLeaseValue());
		assertEquals(locationPostcode, retrievedLeaseOut.getLocationPostcode());
		assertEquals(leaseOutMargin, retrievedLeaseOut.getLeaseOutMargin());
		assertEquals(userData.getId(), retrievedLeaseOut.getChargePeriod().getId());
		assertEquals(userData.getId(), retrievedLeaseOut.getLeaseType().getId());
		assertEquals(userData.getId(), retrievedLeaseOut.getVatCode().getId());
	}

	/**
	 * Remove a LeaseOut from an existing set. Making the set empty.
	 * Asset + Set + 1 LeaseOut already exists.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseOut.xml")
	public void deleteLeaseOutFromAsset()
	{
		// Retrieve asset and LeaseOut
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		flushAndClear();
		
		LeaseOut leaseOut = testAsset.getLeaseOut();
		testAsset.setLeaseOut(null);
		hibernateDAO.update(testAsset);
		hibernateDAO.delete(leaseOut);
		flushAndClear();
		
		// Retrieve all to see if the LeaseOut was deleted
		LeaseOut retrieveLeaseOuts = hibernateDAO.retrieve(LeaseOut.class, id);
		flushAndClear();
		System.out.println("TEST: "+retrieveLeaseOuts);
		
		hibernateDAO.retrieve(LeaseOut.class, testAsset.getId());
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
