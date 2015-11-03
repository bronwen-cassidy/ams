/**
 * 
 */
package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;

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
import com.xioq.dasacumen.model.common.LeaseIn;
import com.xioq.dasacumen.model.common.Person;
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
 * Tests Asset with LeaseIn is working with CRUD operations.
 * @author nmarlor
 *
 */
public class AssetWithLeaseInHibernateTest
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

	// leaseIn specific fields.
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
	 * testLeaseIn and testLeaseOut used for testing.
	 */
	LeaseIn testLeaseIn;

	/**
	 * testLeaseIn2 and testLeaseOut2 used for testing.
	 */
	LeaseIn testLeaseIn2;
	
	/**
	 * Sets up a test leaseIn.
	 */
	@Before
	public void setup()
	{
		testLeaseIn = new LeaseIn();

		testLeaseIn.setLeaseCharge(leaseCharge);
		testLeaseIn.setLeaseCommences(leaseCommences);
		testLeaseIn.setLeaseExpires(leaseExpires);
		testLeaseIn.setLeasePeriod(leasePeriod);
		testLeaseIn.setLeaseCost(leaseCost);
		testLeaseIn.setLeaseValue(leaseValue);
		testLeaseIn.setLocationPostcode(locationPostcode);
		testLeaseIn.setMaintenanceIncluded(maintenanceIncluded);
		testLeaseIn.setWarrantyIncluded(warrantyIncluded);
		testLeaseIn.setLeaseOutMargin(leaseOutMargin);

		testLeaseIn.setCreatedBy(createdBy);
		testLeaseIn.setCreatedDate(createdDate);
		testLeaseIn.setLastUpdatedBy(lastUpdatedBy);
		testLeaseIn.setLastUpdatedDate(lastUpdatedDate);
		testLeaseIn.setTenantId(tenantId);
		testLeaseIn.setVersionNumber(versionNumber);

		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("lease_in");//TODO:reset sequences for lease in and lease out
		testUtil.resetSequences("assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
	}

	/**
	 * Create a LeaseIn with an asset.
	 * All pre req for an asset creation exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithoutAssetOrLeaseIn.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseInNoDate.xml", table = "acumen.lease_in", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createAssetWithLeaseInsTest()
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

		// finish setting the testLeaseIn
		testLeaseIn.setAsset(testAsset);
		testLeaseIn.setParty(partyCompany);
		testLeaseIn.setPartyId(1L);
		testLeaseIn.setChargePeriod(userData);
		testLeaseIn.setChargePeriodId(1L);
		testLeaseIn.setLeaseType(userData);
		testLeaseIn.setLeaseTypeId(1L);
		testLeaseIn.setVatCode(userData);
		testLeaseIn.setVatCodeId(1L);

		testAsset.setLeaseIn(testLeaseIn);

		// save the asset
		hibernateDAO.create(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseIn retrievedLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedLeaseIn.getAsset().getId());
		assertEquals(partyCompany.getId(), retrievedLeaseIn.getParty().getId());
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
		assertEquals(leaseOutMargin, retrievedLeaseIn.getLeaseOutMargin());
		assertEquals(userData.getId(), retrievedLeaseIn.getChargePeriod().getId());
		assertEquals(userData.getId(), retrievedLeaseIn.getLeaseType().getId());
		assertEquals(userData.getId(), retrievedLeaseIn.getVatCode().getId());

		assertEquals(tenantId, retrievedLeaseIn.getTenantId());
		assertEquals(createdBy, retrievedLeaseIn.getCreatedBy());
		assertNotNull(retrievedLeaseIn.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedLeaseIn.getLastUpdatedBy());
		assertNotNull(retrievedLeaseIn.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedLeaseIn.getVersionNumber());
	}

	/**
	 * Create a LeaseIn on an existing asset.
	 * Asset + no LeaseIn type.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/leaseInTestDataWithTestAsset.xml")
	@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestLeaseInNoDate.xml", table = "acumen.lease_in", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createLeaseInWithExistingAssetTest()
	{
		// Retrieve asset, userdata and party
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		Company partyCompany = hibernateDAO.retrieve(Company.class, id);

		// finish setting the testLeaseIn
//		testLeaseIn.setAsset(testAsset);
		testLeaseIn.setParty(partyCompany);
		testLeaseIn.setPartyId(1L);
		testLeaseIn.setChargePeriod(userData);
		testLeaseIn.setChargePeriodId(1L);
		testLeaseIn.setLeaseType(userData);
		testLeaseIn.setLeaseTypeId(1L);
		testLeaseIn.setVatCode(userData);
		testLeaseIn.setVatCodeId(1L);

		testAsset.setLeaseIn(testLeaseIn);

		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseIn retrievedLI = hibernateDAO.retrieve(LeaseIn.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedLI.getAsset().getId());
		assertEquals(partyCompany.getId(), retrievedLI.getParty().getId());
		assertEquals(leaseCharge, retrievedLI.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLI.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLI.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLI.getLeasePeriod());
		assertEquals(leaseCost, retrievedLI.getLeaseCost());
		assertEquals(leaseValue, retrievedLI.getLeaseValue());
		assertEquals(leaseOutMargin, retrievedLI.getLeaseOutMargin());
		assertEquals(locationPostcode, retrievedLI.getLocationPostcode());
		assertEquals(maintenanceIncluded, retrievedLI.getMaintenanceIncluded());
		assertEquals(warrantyIncluded, retrievedLI.getWarrantyIncluded());
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
	 * Update a field on LeaseIn and save it by updating the asset.
	 * Asset + 1 LeaseIn already exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseIn.xml")
	public void updateLeaseInFromAssetTest()
	{

		// Retrieve asset, UserData, party, and existing LeaseIn
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, 2L);
		Person partyPerson = hibernateDAO.retrieve(Person.class, 2L);
		LeaseIn testLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);

		// finish setting the testLeaseIn
//		testLeaseIn.setAsset(testAsset);
		testLeaseIn.setParty(partyPerson);
		testLeaseIn.setChargePeriod(userData);
		testLeaseIn.setChargePeriodId(2L);
		testLeaseIn.setLeaseType(userData);
		testLeaseIn.setLeaseTypeId(2L);
		testLeaseIn.setVatCode(userData);
		testLeaseIn.setVatCodeId(2L);

		testAsset.setLeaseIn(testLeaseIn);

		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		LeaseIn retrievedLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedLeaseIn.getAsset().getId());
		assertEquals(leaseCharge, retrievedLeaseIn.getLeaseCharge());
		assertEquals(leaseCommences, retrievedLeaseIn.getLeaseCommences());
		assertEquals(leaseExpires, retrievedLeaseIn.getLeaseExpires());
		assertEquals(leasePeriod, retrievedLeaseIn.getLeasePeriod());
		assertEquals(leaseCost, retrievedLeaseIn.getLeaseCost());
		assertEquals(leaseValue, retrievedLeaseIn.getLeaseValue());
		assertEquals(locationPostcode, retrievedLeaseIn.getLocationPostcode());
		assertEquals(leaseOutMargin, retrievedLeaseIn.getLeaseOutMargin());
		assertEquals(maintenanceIncluded, retrievedLeaseIn.getMaintenanceIncluded());
		assertEquals(warrantyIncluded, retrievedLeaseIn.getWarrantyIncluded());
		assertEquals(userData.getId(), retrievedLeaseIn.getChargePeriod().getId());
		assertEquals(userData.getId(), retrievedLeaseIn.getLeaseType().getId());
		assertEquals(userData.getId(), retrievedLeaseIn.getVatCode().getId());
	}

	/**
	 * Remove a LeaseIn from an existing set. Making the set empty.
	 * Asset + Set + 1 LeaseIn already exists.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/testDataWithTestLeaseIn.xml")
	public void deleteLeaseInFromAsset()
	{
		// Retrieve asset and LeaseIn
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		flushAndClear();
		
		LeaseIn testLeaseIn = testAsset.getLeaseIn();
		hibernateDAO.delete(testLeaseIn);
		
		flushAndClear();

		// Retrieve all to see if the LeaseOut was deleted
		LeaseIn retrieveLeaseIn = hibernateDAO.retrieve(LeaseIn.class, id);
		flushAndClear();
		System.out.println("TEST: "+ retrieveLeaseIn);
		
		hibernateDAO.retrieve(LeaseIn.class, testAsset.getId());
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
