/**
 * 
 */
package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
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

import com.xioq.dasacumen.model.assetregister.AssetSchedule;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;
import com.xioq.dasacumen.web.config.DateEditor;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners(
{ DependencyInjectionTestExecutionListener.class, TransactionDbUnitTestExecutionListener.class })
public class AssetWithScheduledDataHibernateTest 
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

	// Scheduled specific fields.
	private static final Date leaseCommences = DateEditor.getTodaysDateFormatted("yyyy-MM-dd");
	private static final Date leaseExpires = DateEditor.getTodaysDateFormatted("yyyy-MM-dd");
	private static final Long proposal = 1L;
	

	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	// Should only be used for retrieving as these values are set by db
	private static final Long id = 1L;
	private static final Integer versionNumber = 0;
	private static final Integer versionNumberUpdated = 1;

	/**
	 * Asset Schedule used for testing.
	 */
	AssetSchedule testAssetSchedule;

	/**
	 * Asset Schedule2 used for testing.
	 */
	AssetSchedule testAssetSchedule2;

	/**
	 * Sets up a test Schedule.
	 */
	@Before
	public void setup()
	{
		testAssetSchedule = new AssetSchedule();

		testAssetSchedule.setLeaseCommences(leaseCommences);
		testAssetSchedule.setLeaseExpires(leaseExpires);

		testAssetSchedule.setCreatedBy(createdBy);
		testAssetSchedule.setCreatedDate(createdDate);
		testAssetSchedule.setLastUpdatedBy(lastUpdatedBy);
		testAssetSchedule.setLastUpdatedDate(lastUpdatedDate);
		testAssetSchedule.setTenantId(tenantId);
		testAssetSchedule.setVersionNumber(versionNumber);

		testUtil.resetSequences("asset_schedule");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
	}

	/**
	 * Create a Schedule with an asset.
	 * All pre req for an asset creation exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithoutAssetOrScheduled.xml")
	public void createAssetWithAssetScheduleTest()
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
		testAsset.setName("Crane");
		testAsset.setDescription("Heavy Duty High Lift Crane");
		testAsset.setSerialNumber("CR-1322154545");
		testAsset.setSupplierPn("CR_12354546555");
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

		// create a set
		Set<AssetSchedule> assetScheduleSet = new HashSet<AssetSchedule>(0);

		// finish setting the testAssetSchedule
		testAssetSchedule.setAssets(testAsset);
		testAssetSchedule.setLeaseCommences(leaseCommences);
		testAssetSchedule.setLeaseExpires(leaseExpires);
		// Add the testSchedule to the set and add the set to the asset object
		assetScheduleSet.add(testAssetSchedule);
		testAsset.setAssetSchedule(assetScheduleSet);

		// save the asset
		hibernateDAO.create(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
//		AssetSchedule retrievedAssetSchedule = hibernateDAO.retrieve(AssetSchedule.class, id);

		AssetSchedule retrievedAssetSchedule = hibernateDAO.retrieve(AssetSchedule.class, id);
		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedAssetSchedule.getAsset().getId());
		assertEquals(leaseCommences, retrievedAssetSchedule.getLeaseCommences());
		assertEquals(leaseExpires, retrievedAssetSchedule.getLeaseExpires());
		assertEquals(tenantId, retrievedAssetSchedule.getTenantId());
		assertEquals(createdBy, retrievedAssetSchedule.getCreatedBy());
		assertNotNull(retrievedAssetSchedule.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedAssetSchedule.getLastUpdatedBy());
		assertNotNull(retrievedAssetSchedule.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedAssetSchedule.getVersionNumber());
	}

	/**
	 * Create an AssetSchedule on an existing asset.
	 * Asset + no Asset Schedule.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/assetScheduleTestData.xml")
	public void createAssetScheduleWithExistingAssetTest()
	{
		// Retrieve asset, userdata and party
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);

		// create a set
		Set<AssetSchedule> assetScheduleSet = testAsset.getAssetSchedule();

		// finish setting the testAssetSchedule
		testAssetSchedule.setAssets(testAsset);
		
		testAssetSchedule.setLeaseCommences(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"));
		testAssetSchedule.setLeaseExpires(DateUtils.addDays(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"), 7));
		// Add the testSchedule to the set and add the set to the asset object
		assetScheduleSet.add(testAssetSchedule);
		testAsset.setAssetSchedule(assetScheduleSet);

		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		AssetSchedule retrievedSchedule = hibernateDAO.retrieve(AssetSchedule.class, id);

		// Assert values are stored correctly
		assertEquals(testAsset.getId(), retrievedSchedule.getAsset().getId());

		Assets finalUpdatedAsset = hibernateDAO.retrieve(Assets.class, id);
		//Should now have two scheduled attached to asset, DBUnit created one then we added the second for this test.
		assertEquals(2, finalUpdatedAsset.getAssetSchedule().size());
		
		for (AssetSchedule assetScheduleSet1 : finalUpdatedAsset.getAssetSchedule()) {

			assertEquals(tenantId, assetScheduleSet1.getTenantId());
			assertEquals(createdBy, assetScheduleSet1.getCreatedBy());
			assertNotNull(assetScheduleSet1.getCreatedDate());
			assertEquals(lastUpdatedBy, assetScheduleSet1.getLastUpdatedBy());
			assertNotNull(assetScheduleSet1.getLastUpdatedDate());
			assertEquals(versionNumber, assetScheduleSet1.getVersionNumber());
			
			//We have added a new schedule to existing set and as hibernate has created the new pk id we check that the record we are iterating on is not the 'id' from the DBUnit test 
			//data so we can then check the dates we set on the new set entry.
			if (assetScheduleSet1.getId() !=id) {
				assertEquals(leaseCommences, assetScheduleSet1.getLeaseCommences());
				assertEquals(DateUtils.addDays(leaseExpires, 7), assetScheduleSet1.getLeaseExpires());
			}

		}
		//Asset audit checks:
		assertEquals(tenantId, retrievedSchedule.getTenantId());
		assertEquals(createdBy, retrievedSchedule.getCreatedBy());
		assertNotNull(retrievedSchedule.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedSchedule.getLastUpdatedBy());
		assertNotNull(retrievedSchedule.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedSchedule.getVersionNumber());
	}

	/**
	 * Update a field on AssetScheduled and save it by updating the asset.
	 * Asset + 1 AssetScheduled already exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/assetScheduleTestData.xml")
	public void updateAssetScheduledFromAssetTest()
	{
		// Retrieve asset, UserData, party, and existing Asset Schedule
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		AssetSchedule testAssetSchedule = hibernateDAO.retrieve(AssetSchedule.class, id);

		// create a set
		Set<AssetSchedule> assetScheduleSet = testAsset.getAssetSchedule();

		// finish setting the testAssetSchedule
		testAssetSchedule.setAssets(testAsset);
		testAssetSchedule.setLeaseCommences(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"));
		testAssetSchedule.setLeaseExpires(DateUtils.addDays(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"), 7));
		
		// Add the testAssetSchedule to the set and add the set to the asset object
		assetScheduleSet.add(testAssetSchedule);
		testAsset.setAssetSchedule(assetScheduleSet);

		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// assert that this has been saved to the database
		AssetSchedule retrievedAssetSchedule = hibernateDAO.retrieve(AssetSchedule.class, id);

		assertEquals(leaseCommences, retrievedAssetSchedule.getLeaseCommences());
		assertEquals(DateUtils.addDays(leaseExpires, 7), retrievedAssetSchedule.getLeaseExpires());

		assertEquals(testAsset.getId(), retrievedAssetSchedule.getAsset().getId());
		assertEquals(tenantId, retrievedAssetSchedule.getTenantId());
		assertEquals(createdBy, retrievedAssetSchedule.getCreatedBy());
		assertNotNull(retrievedAssetSchedule.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedAssetSchedule.getLastUpdatedBy());
		assertNotNull(retrievedAssetSchedule.getLastUpdatedDate());
		assertEquals(versionNumberUpdated, retrievedAssetSchedule.getVersionNumber());
	}

	/**
	 * Remove a Schedule from an existing set.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/assetScheduleTestData.xml")
	public void removeAssetScheduledFromAsset()
	{
		// Retrieve asset and schedule
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		AssetSchedule testAssetSchedule = hibernateDAO.retrieve(AssetSchedule.class, id);

		// Get the AssetSheduled
		Set<AssetSchedule> assetScheduled = testAsset.getAssetSchedule();
		// Remove the test assetScheduled
		assetScheduled.remove(testAssetSchedule);
		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();

		// Retrieve all to see if the assetScheduled was deleted
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, id);
		assertEquals(0, retrievedAsset.getAssetSchedule().size());

		//Double check no orphaned records, with a direct search.
		List<AssetSchedule> retrieveAssetSchedule = hibernateDAO.retrieveAll(AssetSchedule.class);
		assertEquals(0, retrieveAssetSchedule.size());
	}

	/**
	 * Find available or partially available assets for a specified date range.
	 * @TODO XXX: TO BE COMPLETED AFTER SEARCH IS READY - DEPENDING ON HOW THE SEARCH IS IMPLEMENTED
	 * WILL DEPEND ON IF WE CAN TEST IT HERE! I.E. IF LOGIC IS IN CONTROLLER A SELENUIM TEST WILL
	 * BE BETTER!
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/assetScheduleTestData.xml")
	public void getAvailableAssetScheduledFromAsset()
	{
		fail("Available Asset test not yet implemented");
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
