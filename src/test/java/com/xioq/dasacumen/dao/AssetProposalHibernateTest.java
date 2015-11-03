package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.assetregister.AssetProposalExtras;
import com.xioq.dasacumen.model.assetregister.AssetSchedule;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Address;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.Email;
import com.xioq.dasacumen.model.common.LeaseOutExtras;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.TelephoneNumber;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;
import com.xioq.dasacumen.web.config.DateEditor;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

//@TransactionConfiguration(defaultRollback = false)
public class AssetProposalHibernateTest extends HibernateDAOTestBase  {
	/* From assetProposalTestData.xml */
	private static final Long DBUNIT_PROPOSAL_ID = 140L;

	/* From assetTestData.xml */
	private static final Long DBUNIT_ASSET_SCHEDULE_ID = 71l;
	private static final Long DBUNIT_LEASE_ASSET = 51l;
	private static final Long DBUNIT_LEASE_OUT_EXTRA = 91l; // This extra is linked to asset 51
	
	/* From emailsTelephoneEtcTestData.xml */
	private static final Long DBUNIT_ADDRESS = 200l;
	private static final Long DBUNIT_EMAIL = 300l;
	private static final Long DBUNIT_TELEPHONE = 400l;
	
	/* From suppliersTestData.xml */
	private static final Long DBUNIT_COMPANY = 101l;
	private static final Long DBUNIT_PERSON = 103l;
	
	/* From assetProposalTestData_duplicateExtrasBug.xml */
	private static final Long DBUNIT_PROPOSAL_ID_EXTRAS_TEST = 440L;
	private static final Long DBUNIT_ASSET_ID_EXTRAS_TEST = 451L;

	private static final BigDecimal COST = new BigDecimal(99.99);
	private static final BigDecimal costUpdated = new BigDecimal(300);
	private static final String emailAddress = "test@aspeKt.kool.com";
	private static final String updatedEmailAddress = "testUPDATED@aspeKt.kool.com";
	private static final String addressLine1 = "TEST ADD LINE 1";
	private static final String city = "MANCHESTER";
	private static final String telNo ="01617727382";
	
	/*
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;
	
	@Before
	public void setup()
	{	
		testUtil.resetSequences(DatabaseTable.ASSET_PROPOSAL);
		testUtil.resetSequences(DatabaseTable.ASSET_PROPOSAL_EXTRAS);
		testUtil.resetSequences(DatabaseTable.ASSET_SCHEDULE);
		testUtil.resetSequences(DatabaseTable.USER_DATA);
		testUtil.resetSequences(DatabaseTable.ASSETS);
		testUtil.resetSequences(DatabaseTable.EMAILS);
		testUtil.resetSequences(DatabaseTable.TELEPHONE);
		testUtil.resetSequences(DatabaseTable.ADDRESSES);
	}
	 
	 /**
	 * Builds up an Asset Proposal by setting the various parts from existing data from the DB
	 */
	private final AssetProposal getNewProposal()
	{
		AssetProposal testAssetProposal = new AssetProposal();
   		testAssetProposal.setAsset(hibernateDAO.retrieve(Assets.class, DBUNIT_LEASE_ASSET));
   		testAssetProposal.setAssetSchedule(hibernateDAO.retrieve(AssetSchedule.class, DBUNIT_ASSET_SCHEDULE_ID));
   		testAssetProposal.setContactParty(hibernateDAO.retrieve(Party.class, DBUNIT_PERSON));
   		
   		testAssetProposal.setAddress(hibernateDAO.retrieve(Address.class, DBUNIT_ADDRESS));
   		testAssetProposal.setEmail(hibernateDAO.retrieve(Email.class, DBUNIT_EMAIL));
   		testAssetProposal.setTelephone(hibernateDAO.retrieve(TelephoneNumber.class, DBUNIT_TELEPHONE));
   		testAssetProposal.setCompany(hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY));

		return testAssetProposal;
	}

	/**
	 * Retrieves an existing Asset Proposal from the database.
  	 */
	 @Test
	@DatabaseSetup({ "classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml", 
					 "classpath:dbunit/parties/suppliersTestData.xml",
					 "classpath:dbunit/asset/assetsTestData.xml", 
					 "classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
					 "classpath:dbunit/asset/assetProposalTestData.xml" })
	@ExpectedDatabase(value = "classpath:dbunit/asset/assetProposalTestData.xml", table = "acumen.asset_proposal", assertionMode = DatabaseAssertionMode.NON_STRICT)
	 public void retrieveAssetProposalTest() 
	 {
		AssetProposal assetProposalRetrieved = hibernateDAO.retrieve(AssetProposal.class, DBUNIT_PROPOSAL_ID);

		assertNotNull(assetProposalRetrieved);
		assertEquals(DBUNIT_PROPOSAL_ID, assetProposalRetrieved.getId());
		assertNotNull(assetProposalRetrieved.getAssetSchedule());
		assertEquals(DBUNIT_ASSET_SCHEDULE_ID, (Long) assetProposalRetrieved.getAssetSchedule().getId());

		assertAuditFields(assetProposalRetrieved);
		
		flushAndClear();
		
		Assets asset = hibernateDAO.retrieve(Assets.class,DBUNIT_LEASE_ASSET);
		Set<LeaseOutExtras> leaseOutExtras = asset.getLeaseOut().getLeaseOutExtras();
   		assertEquals(1, leaseOutExtras.size());
	}
	
	/**
	 * Specific test for retrieving an asset with lease out extras which is also part of a proposal. There was a bug which would 
	 * duplicate every extra that was also linked to a proposal extra.
	 */
	@Test
	@DatabaseSetup({ "classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml", 
					 "classpath:dbunit/parties/suppliersTestData.xml",
					 "classpath:dbunit/asset/assetProposalTestData_duplicateExtrasBug.xml" })
	@ExpectedDatabase(value = "classpath:dbunit/asset/assetProposalTestData_duplicateExtrasBug.xml", table = "acumen.asset_proposal", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveLeaseOutAssetTest_duplicateExtrasWhenPartOfProposal()
	{
		AssetProposal assetProposalRetrieved = hibernateDAO.retrieve(AssetProposal.class, DBUNIT_PROPOSAL_ID_EXTRAS_TEST);

		assertNotNull(assetProposalRetrieved);
		assertEquals(DBUNIT_PROPOSAL_ID_EXTRAS_TEST, assetProposalRetrieved.getId());
		assertNotNull(assetProposalRetrieved.getAssetSchedule());
		assertNotNull(assetProposalRetrieved.getAssetProposalExtras());
   		assertEquals(2, assetProposalRetrieved.getAssetProposalExtras().size());

		assertAuditFields(assetProposalRetrieved);
		
		Assets assetViaProposal = assetProposalRetrieved.getAsset();
		Set<LeaseOutExtras> leaseOutExtrasViaProposal = assetViaProposal.getLeaseOut().getLeaseOutExtras();
   		assertEquals(2, leaseOutExtrasViaProposal.size());

		flushAndClear();
		
		Assets asset = hibernateDAO.retrieve(Assets.class,DBUNIT_ASSET_ID_EXTRAS_TEST);
		Set<LeaseOutExtras> leaseOutExtras = asset.getLeaseOut().getLeaseOutExtras();
   		assertEquals(2, leaseOutExtras.size());
   		
	}
	   	
	/**
	* Creates AssetProposal with a set of proposal extras and tests if the data is present in db.
	*/
   	@Test
	@DatabaseSetup({"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml", 
					"classpath:dbunit/parties/suppliersTestData.xml", 
					"classpath:dbunit/asset/assetsTestData.xml", 
					"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
					"classpath:dbunit/asset/assetProposalTestDataEmpty.xml" // Needed if debugging with rollback set to false
			})
	public void createAssetProposalTest()
	{
   		AssetProposal testAssetProposal = getNewProposal();

   		//Setup a new proposalExtra, which by saving the AssetProposal should also persist the proposalExtras set.
   		AssetProposalExtras assetProposalExtra = new AssetProposalExtras();
   		assetProposalExtra.setCostOfExtras(COST);
   		assetProposalExtra.setAssetId(DBUNIT_LEASE_ASSET);
   		assetProposalExtra.setLeaseOutExtraId(DBUNIT_LEASE_OUT_EXTRA);

   		testAssetProposal.addAssetProposalExtra(assetProposalExtra);
   		testAssetProposal.addAssetProposalExtra(assetProposalExtra);

		hibernateDAO.create(testAssetProposal);
   		flushAndClear();
   		
   		{
	   		AssetProposal testAssetProposal2 = getNewProposal();
	   		
	   		//Setup a new proposalExtra, which by saving the AssetProposal should also persist the proposalExtras set.
	   		AssetProposalExtras assetProposalExtra2 = new AssetProposalExtras();
	   		assetProposalExtra2.setCostOfExtras(COST);
	   		assetProposalExtra2.setAssetId(DBUNIT_LEASE_ASSET);
	   		assetProposalExtra2.setLeaseOutExtraId(DBUNIT_LEASE_OUT_EXTRA);
	
	   		testAssetProposal2.addAssetProposalExtra(assetProposalExtra2);
	   		testAssetProposal2.addAssetProposalExtra(assetProposalExtra2);
	
			hibernateDAO.create(testAssetProposal2);
	   		flushAndClear();
   		}
   		
   		assertNotNull(testAssetProposal.getId());
		AssetProposal assetProposalRetrieved = hibernateDAO.retrieve(AssetProposal.class, testAssetProposal.getId());

		// Check that nothing has happened to the asset and lease out extras
   		assertEquals(1, assetProposalRetrieved.getAsset().getLeaseOut().getLeaseOutExtras().size());
		LeaseOutExtras leaseOutExtra = assetProposalRetrieved.getAsset().getLeaseOut().getLeaseOutExtras().iterator().next();
		assertEquals(DBUNIT_LEASE_OUT_EXTRA, leaseOutExtra.getId());
   		
		flushAndClear();
		
		Assets asset = hibernateDAO.retrieve(Assets.class,DBUNIT_LEASE_ASSET);
		Set<LeaseOutExtras> leaseOutExtras = asset.getLeaseOut().getLeaseOutExtras();
		
		LeaseOutExtras[] arr = new LeaseOutExtras[2];
		int i = 0;
		for (LeaseOutExtras leaseOutExtras2 : leaseOutExtras)
		{
			arr[i++] = leaseOutExtras2;

			/*
			 * Sorted set might be the problem.
			 * Change to HashSet
			 * Hashcode and Equals
			 */
		}
   		assertEquals(1, leaseOutExtras.size());
		

		flushAndClear();

//   		assertNotNull(testAssetProposal.getId());
//   		AssetProposal assetProposalRetrieved = hibernateDAO.retrieve(AssetProposal.class, testAssetProposal.getId());
		assertNotNull(assetProposalRetrieved);
		// Check the proposal extras. Should be only 1
		assertNotNull(assetProposalRetrieved.getAssetProposalExtras());
   		assertEquals(1, assetProposalRetrieved.getAssetProposalExtras().size());
   		AssetProposalExtras proposalExtra = assetProposalRetrieved.getAssetProposalExtras().iterator().next();
		assertNotNull(proposalExtra.getAsset());
   		assertEquals(COST, proposalExtra.getCostOfExtras());
   		assertAuditFields(proposalExtra);
		assertEquals(0, proposalExtra.getVersionNumber().intValue()); // New so version number should be zero

		flushAndClear();

		assertEquals(DBUNIT_ASSET_SCHEDULE_ID, assetProposalRetrieved.getAssetSchedule().getId());
   		assertEquals(emailAddress, assetProposalRetrieved.getEmail().getEmailAddress());
   		assertEquals((Long)DBUNIT_ADDRESS, (Long)assetProposalRetrieved.getAddress().getId());
   		assertEquals((Long)DBUNIT_EMAIL, (Long)assetProposalRetrieved.getEmail().getId());
   		assertEquals((Long)DBUNIT_TELEPHONE, (Long)assetProposalRetrieved.getTelephone().getId());
   		assertEquals((Long)DBUNIT_COMPANY, (Long)assetProposalRetrieved.getCompany().getId());
   		//assertEquals((Long)1L, (Long)assetProposalRetrieved.getProposalExtra().getId());

   		assertAuditFields(assetProposalRetrieved);
		assertEquals(0, assetProposalRetrieved.getVersionNumber().intValue()); // New so version number should be zero
	}

	/**
	 * Create a new asset proposal with new email, address and telephone. Rather than existing ones of these
	 * This tests the cascade options to these entities from the asset proposal
	 */
  	@Test
  	@DatabaseSetup(value = {
  			"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml", 
			"classpath:dbunit/parties/suppliersTestData.xml", 
			"classpath:dbunit/asset/assetsTestData.xml", 
			"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
			"classpath:dbunit/asset/assetProposalTestDataEmpty.xml" // Needed if debugging with rollback set to false
			})
	public void createAssetProposalNewPersonalDetailsTest()
	{
   		AssetProposal testAssetProposal = getNewProposal();

   		//Setup a new proposalExtra, which by saving the AssetProposal should also persist the proposalExtras set.
  		AssetProposalExtras assetProposalExtra = new AssetProposalExtras();
   		assetProposalExtra.setCostOfExtras(COST);
   		assetProposalExtra.setAssetId(DBUNIT_LEASE_ASSET);
   		assetProposalExtra.setLeaseOutExtraId(DBUNIT_LEASE_OUT_EXTRA);
   		testAssetProposal.addAssetProposalExtra(assetProposalExtra);
   		
   		AssetSchedule assetSchedule = new AssetSchedule();
   		assetSchedule.setAssets(testAssetProposal.getAsset());
   		assetSchedule.setLeaseCommences(new Date());
   		
   		Email email = new Email();
   		email.setEmailAddress(updatedEmailAddress);
   		Address address = new Address();
   		address.setAddressLine1(addressLine1);
   		address.setCity(city);
   		TelephoneNumber telephoneNumber = new TelephoneNumber();
   		telephoneNumber.setTelNo(telNo);

   		testAssetProposal.setEmail(email);
   		testAssetProposal.setAddress(address);
   		testAssetProposal.setTelephone(telephoneNumber);
   		testAssetProposal.setAssetSchedule(assetSchedule);
		hibernateDAO.create(testAssetProposal);
   		flushAndClear();
	
   		assertNotNull(testAssetProposal.getId());
   		assertNotNull(assetSchedule.getId());
   		
		AssetProposal assetProposalRetrieved = hibernateDAO.retrieve(AssetProposal.class, testAssetProposal.getId());
	
		//Ensure the extras have been created.
		for (AssetProposalExtras extra : assetProposalRetrieved.getAssetProposalExtras()) {
	   		assertEquals(testAssetProposal.getAsset().getId(), extra.getAsset().getId());
	   		assertEquals(COST, extra.getCostOfExtras());
			assertAuditFields(extra);
			assertEquals(0, extra.getVersionNumber().intValue());
		}
   		assertNotNull(assetProposalRetrieved.getAssetSchedule());
		assertEquals(assetSchedule.getId(), (Long)assetProposalRetrieved.getAssetSchedule().getId());
   		assertEquals(updatedEmailAddress, assetProposalRetrieved.getEmail().getEmailAddress());
   		assertEquals(addressLine1, assetProposalRetrieved.getAddress().getAddressLine1());
   		assertEquals(telNo, assetProposalRetrieved.getTelephone().getTelNo());

   		assertEquals((Long)201L, (Long)assetProposalRetrieved.getAddress().getId());
   		assertEquals((Long)301L, (Long)assetProposalRetrieved.getEmail().getId());
   		assertEquals((Long)401L, (Long)assetProposalRetrieved.getTelephone().getId());
   		assertEquals((Long)DBUNIT_COMPANY, (Long)assetProposalRetrieved.getCompany().getId());
   		//assertEquals((Long)1L, (Long)assetProposalRetrieved.getProposalExtra().getId());

   		assertAuditFields(assetProposalRetrieved);
	}
	/**
	 * Update an existing AssetProposal in the database.
	 */
   	@Test
	@DatabaseSetup({"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml", "classpath:dbunit/parties/suppliersTestData.xml", 
			"classpath:dbunit/asset/assetsTestData.xml", "classpath:dbunit/parties/emailsTelephoneEtcTestData.xml", 
			"classpath:dbunit/asset/assetProposalTestData.xml"})
	public void updateAssetProposalTest()
	{
		AssetProposal retrievedAssetProposal = hibernateDAO.retrieve(AssetProposal.class, DBUNIT_PROPOSAL_ID);

   		final Date updatedExpiredDate = DateUtils.addDays(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"),7); 
   		
   		// Set the new updated fields.
   		retrievedAssetProposal.getEmail().setEmailAddress(updatedEmailAddress);
   		// Update an assetSchedule date.
   		retrievedAssetProposal.getAssetSchedule().setLeaseExpires(updatedExpiredDate);
   		
   		// Update all the extras cost
   		for (AssetProposalExtras assetProposalExtras : retrievedAssetProposal.getAssetProposalExtras()) {
   				assetProposalExtras.setCostOfExtras(costUpdated);
   			}
   		
   		// Do the update
		hibernateDAO.update(retrievedAssetProposal);
   		flushAndClear();
   		
   		// retrieve the updated Asset Schedule
   		AssetProposal retrievedAssetProposal2 = hibernateDAO.retrieve(AssetProposal.class, retrievedAssetProposal.getId());
   		
   		// Assert values are stored correctly
   		assertEquals(updatedExpiredDate, retrievedAssetProposal2.getAssetSchedule().getLeaseExpires());
   		assertEquals(updatedEmailAddress, retrievedAssetProposal2.getEmail().getEmailAddress());
   		
   		assertEquals(1, retrievedAssetProposal2.getAssetProposalExtras().size());
   		for (AssetProposalExtras propExtUpdated : retrievedAssetProposal2.getAssetProposalExtras()) {
   			assertEquals(costUpdated, propExtUpdated.getCostOfExtras());
   		}
   		
   		assertAuditFields(retrievedAssetProposal2);
	}

	/**
	 * Delete an existing Asset Proposal in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup({ "classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml", "classpath:dbunit/parties/suppliersTestData.xml",
		"classpath:dbunit/asset/assetsTestData.xml", "classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
		"classpath:dbunit/asset/assetProposalTestData.xml" })
	public void deleteAssetProposalTest()
	{		
		AssetProposal retrievedAssetProposal = hibernateDAO.retrieve(AssetProposal.class, DBUNIT_PROPOSAL_ID);
		hibernateDAO.delete(retrievedAssetProposal);
		flushAndClear();
   		AssetProposal retrievedAssetProposal2 = hibernateDAO.retrieve(AssetProposal.class, DBUNIT_PROPOSAL_ID);
   		// Will trigger the exception
   		System.out.println(retrievedAssetProposal2.getAssetProposalExtras());		
	}

	/**
	 * Test to check that update when the version number is changed should throw a stale state exception 
	 */
	@Test(expected=StaleStateException.class)
	@DatabaseSetup({"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml", "classpath:dbunit/parties/suppliersTestData.xml", 
			"classpath:dbunit/asset/assetsTestData.xml", "classpath:dbunit/parties/emailsTelephoneEtcTestData.xml", 
			"classpath:dbunit/asset/assetProposalTestData.xml"})
	public void staleStateExceptionTest()
	{
    	AssetProposal testAssetProposal1 = hibernateDAO.retrieve(AssetProposal.class, DBUNIT_PROPOSAL_ID);
   		flushAndClear();

    	AssetProposal testAssetProposal2 = hibernateDAO.retrieve(AssetProposal.class, DBUNIT_PROPOSAL_ID);
   		flushAndClear();
   		
   		testAssetProposal1.getAssetSchedule().setLeaseCommences(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"));
   		hibernateDAO.update(testAssetProposal1);
		flushAndClear();
		
   		testAssetProposal2.getAssetSchedule().setLeaseCommences(DateEditor.getTodaysDateFormatted("yyyy-MM-dd"));
   		hibernateDAO.update(testAssetProposal2);
		flushAndClear();
	}
}
	
