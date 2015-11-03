/**
 * 
 */
package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
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
import com.xioq.dasacumen.model.common.AssetInsuranceType;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
/**
 * Tests Insurance policy is working with CRUD operations.
 * @author echhung
 *
 */
public class AssetWithInsuranceTypeHibernateTest {
	
	/*
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;
	@Autowired
	private TestUtil testUtil;
	
	/*
	 * Creates an instance of session factory so that the session cache can be cleared
	 * to avoid false positives on tests
	 */
	@Autowired @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	// asset insurance type specific fields.
	private static final Long insuranceTypeId = 1L;
	private static final Long assetId = 1L;
	
	private static final Boolean mandatory = true;

	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "JamesWesker@jla.co.uk";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "chrisredfield@jla.co.uk";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	
	// Only used for retrieving
	private static final Long id = 1L;
	private static final Integer versionNumber = 0;
	
	/**
	 * AssetInsuranceType used for testing.
	 */
	AssetInsuranceType testAssetInsuranceType ;
	
	/**
	 * Sets up a test Insurance Policy.
	 */
	@Before
	public void setup() {
		testAssetInsuranceType = new AssetInsuranceType();
		
		// Configure AssetInsuranceType- version number and id are set by database
		testAssetInsuranceType.setMandatory(mandatory);
		
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("asset_insurance_types");
		testUtil.resetSequences("insurance_policies");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("insurance_type_policy_links");
	}

	/**
	 * Create an insurance type with an asset.
	 * All pre req for an asset creation exists.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/baseTestData.xml")
	public void createAssetWithInsuranceTypeTest() {
		// Retrieve a user data
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		Company partyCompany= hibernateDAO.retrieve(Company.class, id);
		
		//set up an asset for creation.
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
		
		// create a set
		Set<AssetInsuranceType> assetInsuranceTypeSet = new HashSet<AssetInsuranceType>(0);
		
		// finish setting the testAssetInsurance 
		testAssetInsuranceType.setInsuranceTypeId(userData.getId());
		testAssetInsuranceType.setAsset(testAsset);
	
		//Add the testAssetInsurance to the set and add the set to the asset object
		assetInsuranceTypeSet.add(testAssetInsuranceType);
		testAsset.setAssetInsuranceTypes(assetInsuranceTypeSet);
		
		// save the asset
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		assertNotNull(testAsset.getId());
		assertNotNull(testAssetInsuranceType.getId());
		
		// assert that this has been saved to the database
		AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, testAssetInsuranceType.getId());
		
		// Assert values are stored correctly
		assertEquals(insuranceTypeId, retrievedAssetInsuranceType.getInsuranceType().getId());
		assertEquals(assetId, retrievedAssetInsuranceType.getAsset().getId());
		assertEquals(mandatory, retrievedAssetInsuranceType.getMandatory());

		assertEquals(tenantId, retrievedAssetInsuranceType.getTenantId());
		assertNotNull(retrievedAssetInsuranceType.getCreatedBy());
		assertNotNull(retrievedAssetInsuranceType.getCreatedDate());
		assertNotNull(retrievedAssetInsuranceType.getLastUpdatedBy());
		assertNotNull(retrievedAssetInsuranceType.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedAssetInsuranceType.getVersionNumber());
	}
	
	/**
	 * Create an insurance type on an existing asset.
	 * Asset + no insurance type.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/assetTestData.xml")
	public void createInsuranceTypeWithExistingAssetTest() {
		// Retrieve asset and userdata
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		
		// create a set
		Set<AssetInsuranceType> assetInsuranceTypeSet = testAsset.getAssetInsuranceTypes();
		
		// finish setting the testAssetInsurance 
		testAssetInsuranceType.setInsuranceTypeId(userData.getId());
		testAssetInsuranceType.setAsset(testAsset);
	
		//Add the testAssetInsurance to the set and add the set to the asset object
		assetInsuranceTypeSet.add(testAssetInsuranceType);
		testAsset.setAssetInsuranceTypes(assetInsuranceTypeSet);
		
		// update the asset
		hibernateDAO.update(testAsset);
		flushAndClear();
		
		// assert that this has been saved to the database
		AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
		
		// Assert values are stored correctly
		assertEquals(insuranceTypeId, (Long)retrievedAssetInsuranceType.getInsuranceType().getId());
		assertEquals(assetId, retrievedAssetInsuranceType.getAsset().getId());
		assertEquals(mandatory, retrievedAssetInsuranceType.getMandatory());

		assertEquals(tenantId, retrievedAssetInsuranceType.getTenantId());
		assertNotNull(retrievedAssetInsuranceType.getCreatedBy());
		assertNotNull(retrievedAssetInsuranceType.getCreatedDate());
		assertNotNull(retrievedAssetInsuranceType.getLastUpdatedBy());
		assertNotNull(retrievedAssetInsuranceType.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedAssetInsuranceType.getVersionNumber());
	}
	
	/**
	 * Update a field on Insurance type and save it by updating the asset.
	 * Asset + 1 insurance type already exists.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
	public void updateInsuranceTypeFieldFromAssetTest() {
		// Retrieve asset and userdata
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		
		Set<AssetInsuranceType> assetInsuranceTypeSet = testAsset.getAssetInsuranceTypes();
		
		// Sets all mandatory fields to false but this there should be only one inside anyway.
		for(AssetInsuranceType ait: assetInsuranceTypeSet) {
			ait.setMandatory(false);
		}
		
		// Set the updated set
		testAsset.setAssetInsuranceTypes(assetInsuranceTypeSet);
		
		// update the asset
		hibernateDAO.update(testAsset);
		flushAndClear();
		
		// assert that this has been saved to the database and the mandatory field has been changed
		AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
		
		// Assert values are stored correctly
		assertEquals(insuranceTypeId, (Long)retrievedAssetInsuranceType.getInsuranceType().getId());
		assertEquals(assetId, retrievedAssetInsuranceType.getAsset().getId());
		assertEquals(false, retrievedAssetInsuranceType.getMandatory());
	}
	
	/**
	 * Add an insurance type object to an already existing set.
	 * Asset + Set + 1 insurance type already exists.
	 * TODO Test failing this is to do with the test util not resetting the sequence properly.
	 * Then the sequence number gives an id 1 to the new object made in the test.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
	public void addInsuranceTypeFromAssetTest() {
		testUtil.resetSequences("asset_insurance_types");

		// Retrieve asset and userdata
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		
		Set<AssetInsuranceType> assetInsuranceTypeSet = testAsset.getAssetInsuranceTypes();
		
		// create an assetInsuranceType and add it (should be two now inside the set)
		// slight change to the second assetInsurance type 
		AssetInsuranceType testAssetInsuranceType2 = new AssetInsuranceType();
		
		// Configure AssetInsuranceType- version number and id are set by database
		testAssetInsuranceType2.setMandatory(false);
		testAssetInsuranceType2.setAsset(testAsset);
		testAssetInsuranceType2.setInsuranceTypeId(userData.getId());
		testAssetInsuranceType2.setTenantId(tenantId);
		
		assetInsuranceTypeSet.add(testAssetInsuranceType2);
		
		// add it to the asset object
		testAsset.setAssetInsuranceTypes(assetInsuranceTypeSet);
		
		// update the asset
		hibernateDAO.update(testAsset);
		flushAndClear();
		
		assertNotNull(testAssetInsuranceType2);
		assertNotNull(testAssetInsuranceType2.getId());
		assertNotNull(testAssetInsuranceType2.getVersionNumber());
		
		// Retrieve all to see if the type was added
		Assets postAddAsset = hibernateDAO.retrieve(Assets.class, id);
		assertNotNull(postAddAsset.getAssetInsuranceTypes());
		assertEquals(2, postAddAsset.getAssetInsuranceTypes().size());
		
		// assert that this has been saved to the database
		AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
		AssetInsuranceType retrievedAssetInsuranceType2 = hibernateDAO.retrieve(AssetInsuranceType.class, testAssetInsuranceType2.getId());
		
		// assert that both are saved in the database correctly
		// Assert values are stored correctly
		assertEquals(insuranceTypeId, (Long)retrievedAssetInsuranceType.getInsuranceType().getId());
		assertEquals(assetId, retrievedAssetInsuranceType.getAsset().getId());
		assertEquals(mandatory, retrievedAssetInsuranceType.getMandatory());

		assertEquals(tenantId, retrievedAssetInsuranceType.getTenantId());
		assertEquals(versionNumber, retrievedAssetInsuranceType.getVersionNumber());
		
		// assert that both are saved in the database correctly
		// Assert values are stored correctly
		assertEquals(insuranceTypeId, (Long)retrievedAssetInsuranceType2.getInsuranceType().getId());
		assertEquals(assetId, retrievedAssetInsuranceType2.getAsset().getId());
		assertEquals(false, retrievedAssetInsuranceType2.getMandatory());

		assertEquals(tenantId, retrievedAssetInsuranceType2.getTenantId());
		assertNotNull(retrievedAssetInsuranceType2.getCreatedBy());
		assertNotNull(retrievedAssetInsuranceType2.getCreatedDate());
		assertNotNull(retrievedAssetInsuranceType2.getLastUpdatedBy());
		assertNotNull(retrievedAssetInsuranceType2.getLastUpdatedDate());
		assertEquals(versionNumber, retrievedAssetInsuranceType2.getVersionNumber());
	}
	
	/**
	 * Remove an insurance type from an existing set. Making the set empty.
	 * Asset + Set + 1 insurance type already exists.
	 */
	@Test(expected=ObjectNotFoundException.class)
    @DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
	public void removeInsuranceTypeFromAsset() {
		// Retrieve asset and userdata
		Assets testAsset = hibernateDAO.retrieve(Assets.class, id);
		
		// Get the assetInsuranceTypes
		Set<AssetInsuranceType> assetInsuranceTypes = testAsset.getAssetInsuranceTypes();
		// retrieve the insurance type
		AssetInsuranceType ait1 = hibernateDAO.retrieve(AssetInsuranceType.class, id);		
		assetInsuranceTypes.remove(ait1);
		
		testAsset.setAssetInsuranceTypes(assetInsuranceTypes);

		// save the asset
		hibernateDAO.update(testAsset);
		flushAndClear();
		
		Assets postRemoveAsset = hibernateDAO.retrieve(Assets.class, id);
		assertNotNull(postRemoveAsset);
		assertNotNull(postRemoveAsset.getAssetInsuranceTypes());
		assertEquals(0, postRemoveAsset.getAssetInsuranceTypes().size());
		
		// The insurance type should no longer exist. Flush to force the retrieve and exception
		hibernateDAO.retrieve(AssetInsuranceType.class, id);		
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
