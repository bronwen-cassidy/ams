package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.assetregister.AssetSchedule;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;
import com.xioq.dasacumen.web.config.DateEditor;

import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * Tests CRUD operations and StaleStateException test for AssetSchedule Model
 * @author jmadden
 *
 */

public class AssetScheduleHibernateTest extends HibernateDAOTestBase implements CrudTests {
	
	private static final Long DBUNIT_ASSET_SCHEDULE_ID = 21L;
		
	/* From assetScheduleTestData.xml */
	private static final Date DBUNIT_LEASE_COMMENCES = new Date(114,4,9);
	private static final Date DBUNIT_LEASE_EXPIRES = new Date(114,4,10);
	private static final Date DBUNIT_DATE_COLLECTED_DEPLOYED = new Date(114,4,10);
	private static final Long DBUNIT_ASSET_ID = 50L;
	
	
	@Before
	public void setup()
	{
		testUtil.resetSequences(DatabaseTable.ASSET_SCHEDULE);
		testUtil.resetSequences(DatabaseTable.ASSETS); 					
	}
	 
	 /**
	 * Retrieves an asset_schedule record from the database. 
  	 */
	 @Test
	 @DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
		      				"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
		      				"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
			 				"classpath:dbunit/asset/assetsTestData.xml",
			 				"classpath:dbunit/assetScheduleTestData.xml"})
	 public void retrieveTest() 
	 {
    	AssetSchedule assetScheduleRetrieved = hibernateDAO.retrieve(AssetSchedule.class, DBUNIT_ASSET_SCHEDULE_ID);
   		
		assertNotNull(assetScheduleRetrieved);
    	assertEquals(DBUNIT_ASSET_SCHEDULE_ID, assetScheduleRetrieved.getId());
    	assertEquals(DBUNIT_ASSET_ID,assetScheduleRetrieved.getAsset().getId());
		assertEquals(DBUNIT_LEASE_COMMENCES,assetScheduleRetrieved.getLeaseCommences());
		assertEquals(DBUNIT_LEASE_EXPIRES,assetScheduleRetrieved.getLeaseExpires());
		
		assertAuditFields(assetScheduleRetrieved);
	} 

	/**
	* Creates AssetSchedule and tests if the data is present in db.
	*/
   	@Test
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
				"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
				"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
				"classpath:dbunit/asset/assetsTestData.xml",
				"classpath:dbunit/assetScheduleTestData.xml"})
	public void createTest()
	{
   		AssetSchedule testAssetSchedule = new AssetSchedule();
   		
   		testAssetSchedule.setAssets(hibernateDAO.retrieve(Assets.class, DBUNIT_ASSET_ID));
   		testAssetSchedule.setLeaseCommences(DBUNIT_LEASE_COMMENCES);
   		testAssetSchedule.setLeaseExpires(DBUNIT_LEASE_EXPIRES);
   		testAssetSchedule.setDateCollectedDeployed(DBUNIT_DATE_COLLECTED_DEPLOYED);
   		
   		hibernateDAO.create(testAssetSchedule);
   		flushAndClear();
   		
		AssetSchedule assetScheduleRetrieved = hibernateDAO.retrieve(AssetSchedule.class, testAssetSchedule.getId());
			
		assertEquals(DBUNIT_DATE_COLLECTED_DEPLOYED,assetScheduleRetrieved.getDateCollectedDeployed());	
		assertEquals(DBUNIT_LEASE_COMMENCES,assetScheduleRetrieved.getLeaseCommences());
		assertEquals(DBUNIT_LEASE_EXPIRES,assetScheduleRetrieved.getLeaseExpires());
		assertEquals(DBUNIT_ASSET_ID,assetScheduleRetrieved.getAsset().getId());
		assertAuditFields(assetScheduleRetrieved);
	}
		
	/**
	 * Update an existing AssetSchedule in the database.
	 */
   	@Test
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
				"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
				"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
				"classpath:dbunit/asset/assetsTestData.xml",
				"classpath:dbunit/assetScheduleTestData.xml"})
	public void updateTest()
	{
   		// Updated fields
   		final Date updatedExpiredDate = DateUtils.addDays(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"),7); 
   		AssetSchedule retrievedAssetSchedule = hibernateDAO.retrieve(AssetSchedule.class, DBUNIT_ASSET_SCHEDULE_ID);
   		
   		// Set the new updated field - to be todays date plus 7 days.
   		retrievedAssetSchedule.setLeaseExpires(DateUtils.addDays(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"), 7));
   		retrievedAssetSchedule.setLeaseCommences(DateUtils.addDays(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"), 7));
   		retrievedAssetSchedule.setDateCollectedDeployed(DateUtils.addDays(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"), 7));
   		
   		// Do the update
		hibernateDAO.update(retrievedAssetSchedule);
   		flushAndClear();
   		
   		// retrieve the updated Asset Schedule
   		AssetSchedule retrievedAssetSchedule2 = hibernateDAO.retrieve(AssetSchedule.class, retrievedAssetSchedule.getId());
   		
   		// Assert values are stored correctly
   		assertEquals(updatedExpiredDate, retrievedAssetSchedule.getLeaseExpires());
   		assertNotNull(retrievedAssetSchedule2.getLastUpdatedBy());
   		assertNotNull(retrievedAssetSchedule2.getLastUpdatedDate());			
	}

	/**
	 * Delete an existing Asset Schedule in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
				"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
				"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
				"classpath:dbunit/asset/assetsTestData.xml",
				"classpath:dbunit/assetScheduleTestData.xml"})
	public void deleteTest()
	{		
		AssetSchedule retrievedAssetSchedule = hibernateDAO.retrieve(AssetSchedule.class, DBUNIT_ASSET_SCHEDULE_ID);
   		hibernateDAO.delete(retrievedAssetSchedule);
   		AssetSchedule retrievedAssetSchedule2 = hibernateDAO.retrieve(AssetSchedule.class, retrievedAssetSchedule.getId());
   		// Will trigger the exception
   		System.out.println(retrievedAssetSchedule2.getLeaseCommences());		
	}



	/**
	 * Test to check that update when the version number is changed should throw a stale state exception 
	 */
	//might need @Transactional
	@Test//(expected=StaleStateException.class)
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
				"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
				"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
				"classpath:dbunit/asset/assetsTestData.xml",
				"classpath:dbunit/assetScheduleTestData.xml"})
	public void staleStateExceptionTest()
	{
		AssetSchedule asRetrieved1 = hibernateDAO.retrieve(AssetSchedule.class, DBUNIT_ASSET_SCHEDULE_ID);
		flushAndClear();
		AssetSchedule asRetrieved2 = hibernateDAO.retrieve(AssetSchedule.class, DBUNIT_ASSET_SCHEDULE_ID);
		flushAndClear();

		asRetrieved1.setDateCollectedDeployed(DBUNIT_DATE_COLLECTED_DEPLOYED);
   		
		hibernateDAO.update(asRetrieved1);
   		flushAndClear();
   		assertEquals("Version number incremented", 1, asRetrieved1.getVersionNumber().intValue());
   		
   		asRetrieved2.setDateCollectedDeployed(DBUNIT_DATE_COLLECTED_DEPLOYED);
		// and tries to update
		try
		{
			hibernateDAO.update(asRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, asRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown: " + e);
		}
	}
}