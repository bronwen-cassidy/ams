package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.AssetWarranty;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.WarrantyPolicy;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

/**
 * Test that an asset with warranties collection interacts with hibernate correctly (this includes cascading).
 * @author echhung
 *
 */
public class AssetWithWarrantiesTest extends HibernateDAOTestBase{

	static final Long udCatAndTypeId = 1L;
	
	/**
	 * AssetWarranty used for testing.
	 */
	AssetWarranty testAssetWarranty;
	
	private static final Long assetId = 1L;
	
	// Should only be used for retrieving as these values are set by db
	private static final Long id = 1L;
	
	@Before
    public void setUp() {
		testAssetWarranty = new AssetWarranty();
		
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("warranty_policies");
		testUtil.resetSequences("asset_warranties");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("assets");
    }
    
    /**
	 * Create an asset warranty with an asset.
	 * All pre req for an asset creation exists.
     */
	@Test
    @DatabaseSetup("classpath:dbunit/testDataWithoutAssetOrWarranty.xml")
	@ExpectedDatabase(value = "classpath:dbunit/warrantyTestDataNoDate_expected.xml", table = "acumen.asset_warranties", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createAssetWithWarrantyTest() {

		Company partyCompany = hibernateDAO.retrieve(Company.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		WarrantyPolicy warrantyPolicy = hibernateDAO.retrieve(WarrantyPolicy.class, id);
				
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
		
//		testAsset.setUid("UID_12323443");
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

		AssetWarranty testAssetWarranty = new AssetWarranty();
		testAssetWarranty.setAsset(testAsset);
		testAssetWarranty.setWarrantyPolicyId(warrantyPolicy.getId());
		testAssetWarranty.setAssetWarrantyTypeId(userData.getId());
		Set<AssetWarranty> warranties = new HashSet<AssetWarranty>();
		warranties.add(testAssetWarranty);
		testAsset.setWarranties(warranties);

		// save the asset
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		// assert that this has been saved to the database
		AssetWarranty retrievedAssetWarranty = hibernateDAO.retrieve(AssetWarranty.class, id);
		
		// Assert values are stored correctly
		assertEquals(warrantyPolicy.getId(), retrievedAssetWarranty.getWarrantyPolicy().getId());
		
		assertAuditFields(testAsset);
		assertAuditFields(retrievedAssetWarranty);
	}
	
	/**
	 * Add an AssetWarranty object to an already existing set.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/warrantyTestDataWithTestWarranty.xml")
	public void addWarrantyFromAssetTest() {
		
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, assetId);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		WarrantyPolicy warrantyPolicy = hibernateDAO.retrieve(WarrantyPolicy.class, id);
		
		Set<AssetWarranty> assetWarrantiesSet = retrievedAsset.getWarranties();
		
		// create a new assetWarranty
		AssetWarranty testAssetWarranty2 = new AssetWarranty();
		
		testAssetWarranty2.setAsset(retrievedAsset);
		testAssetWarranty2.setWarrantyPolicyId(warrantyPolicy.getId());
		testAssetWarranty2.setAssetWarrantyTypeId(userData.getId());

		// Add the testAssetWarranty to the set and add the set to the asset object
		assetWarrantiesSet.add(testAssetWarranty2);
		retrievedAsset.setWarranties(assetWarrantiesSet);

		hibernateDAO.update(retrievedAsset);
		flushAndClear();
		
		List<AssetWarranty> assetWarranties = hibernateDAO.retrieveAll(AssetWarranty.class);
		assertEquals(2, assetWarranties.size());
		
		AssetWarranty retrievedAssetWarranty = hibernateDAO.retrieve(AssetWarranty.class, id);
		AssetWarranty retrievedAssetWarranty2 = hibernateDAO.retrieve(AssetWarranty.class, 2L);

		assertEquals(warrantyPolicy.getId(), retrievedAssetWarranty.getWarrantyPolicy().getId());
		
		assertAuditFields(retrievedAssetWarranty);
	
		assertEquals(warrantyPolicy.getId(), retrievedAssetWarranty2.getWarrantyPolicy().getId());
		assertAuditFields(retrievedAssetWarranty2);
	}
	
	
	/**
	 * Remove an assetWarranty from an existing set. Making the set empty.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/warrantyTestDataWithTestWarranty.xml")
	public void removeWarrantiesFromAssetTest() {
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, assetId);
		AssetWarranty testAssetWarranty = hibernateDAO.retrieve(AssetWarranty.class, id);
		
		// Get the asset warranty
		Set<AssetWarranty> assetWarranties = retrievedAsset.getWarranties();
		// Remove the test warranty policy
		assetWarranties.remove(testAssetWarranty);

		// save the asset
		hibernateDAO.update(retrievedAsset);
		flushAndClear();
		
		// Retrieve all to see if the asset warranty was deleted
		List<AssetWarranty> retrieveAssetWarranties = hibernateDAO.retrieveAll(AssetWarranty.class);
		assertEquals(0, retrieveAssetWarranties.size());
	}
		
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}
