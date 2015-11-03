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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

//
/**
 * Tests the mapping and CRUD operations for the hibernate mappings and java classes. 
 * @author echhung
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class AssetHibernateTest
{
	@Autowired
	private TestUtil testUtil;

	static final Long udCatAndTypeId = 1L;
	
	/**
	 * Asset used for testing.
	 */
	Assets testAsset;
	
	static final int assetNumberPart4 = 1;
	
	static final Boolean bcp = true;
	static final BigDecimal budgetLimit = new BigDecimal(4500.99);
	
	static final Date commissioningDate = new Date(90, 12, 04);
//	static final String createdBy = "Bob";	
//	static final Date createdDate = new Date(89, 12, 04);
	static final Boolean criticalAsset = false;
	
	static final Date dateOfPurchase = new Date(89, 12, 04);
	static final String description = "Its a big blue radiator";
	static final Date decommissioningDate = new Date(100, 12, 04);
	
	static final Date endOfLifeDate = new Date(150, 12, 04);
	
	static final Date installationDate = new Date(90, 12, 04);
	static final Boolean isAFacility = false;
	static final Boolean isEquipment = true;
	static final Boolean isPart = false;
	
//	static final String lastUpdatedBy = "Ricky";
//	static final Date lastUpdatedDate = new Date(89, 12, 04);
	static final int lifeExpectancy = 10;
	
	static final String manufacturerPn = "125488974";
	
	static final String name = "Blue Radiator";
	
	static final BigDecimal purchasePrice = new BigDecimal(200.99);
	static final Integer purchaseLeadTime = 1;
	static final Boolean partOfGroup = false;
	static final Boolean parts = false;
	
	static final Boolean riskAssessment = true;
	
	static final String serialNumber = "BlUE_RADIATOR-MODEL-BIG-452";
	// Supplier catalogue number
	static final String supplierPn = "BB-RAD-M-M112321454";
	
//	static final int versionNumber =  1;
	
	static final String uid = "das_pt_1234567895452154879";
	
	
	/**
	 * foreign key user data field, this is used by multiple fields.
	 */
	UserData userData = new UserData();
	
	/**
	 * foreign key party fields, this is used by multiple fields.
	 */
	Company partyCompany = new Company();
	
	/**
	 * Hibernate data access object
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;
	
	/*
	 * Creates an instance of session factory so that the session cache can be cleared
	 * to avoid false positives on tests
	 */
	@Autowired @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
    @Before
    @Transactional
    public void setUp() {
    	testUtil.resetSequences("assets");
    	testUtil.resetSequences("parties");
    	testUtil.resetSequences("user_data");
		
		
    	testAsset = new Assets();
    	// Assets pre req items.
    	partyCompany.setName("JCB");
    	partyCompany.setUid(uid);
		hibernateDAO.create(partyCompany);
		flushAndClear();
		
		userData.setUserDataTypeId(udCatAndTypeId);
		userData.setName("BlueRad");
		hibernateDAO.create(userData);
		flushAndClear();
		configureTestAsset();
    }
    
    /**
     * Set all the fields to the test asset.
     */
    public void configureTestAsset() {
    	// Configure Test Asset - listed in alphabetical order.
    	
    	testAsset.setCompanyId(userData.getId());
    	
		testAsset.setAssetNumberPart1Id(userData.getId());
		testAsset.setAssetNumberPart2Id(userData.getId());
		testAsset.setAssetNumberPart3Id(userData.getId());
		testAsset.setAssetNumberPart4(assetNumberPart4);
		testAsset.setAssetStatusId(userData.getId());
		
		testAsset.setBudgetLimit(budgetLimit);
		testAsset.setBcp(bcp);
		
		testAsset.setCategoryId(userData.getId());
		testAsset.setCountryId(userData.getId());
		testAsset.setCommissioningDate(commissioningDate);
//		testAsset.setCreatedBy(createdBy);
//		testAsset.setCreatedDate(createdDate);
		testAsset.setCriticalAsset(criticalAsset);
		testAsset.setCustodianId(partyCompany.getId());
		
		testAsset.setDateOfPurchase(dateOfPurchase);
		testAsset.setDecommissioningDate(decommissioningDate);
		testAsset.setDescription(description);
		testAsset.setDepreciationCodeId(userData.getId());
		testAsset.setDivisionId(userData.getId());
		testAsset.setDepartmentId(userData.getId());
		
		testAsset.setEndOfLifeDate(endOfLifeDate);
		
		testAsset.setInstallationDate(installationDate);
		testAsset.setIsAFacility(isAFacility);
		testAsset.setIsEquipment(isEquipment);
		testAsset.setIsPart(isPart);
		
//		testAsset.setLastUpdatedBy(lastUpdatedBy);
//		testAsset.setLastUpdatedDate(lastUpdatedDate);
		testAsset.setLifeExpectancy(lifeExpectancy);
		testAsset.setLocationId(userData.getId());

		testAsset.setManufacturerId(partyCompany.getId());
		testAsset.setManufacturerPn(manufacturerPn);
		
		testAsset.setName(name);

		testAsset.setPartOfGroup(partOfGroup);
		testAsset.setParts(parts);
		testAsset.setPurchaseLeadTime(purchaseLeadTime);
		testAsset.setPurchasePrice(purchasePrice);
		
		testAsset.setRiskAssessment(riskAssessment);
		
		testAsset.setSerialNumber(serialNumber);
		testAsset.setSiteId(userData.getId());
		testAsset.setSupplierId(partyCompany.getId());
		testAsset.setSupplierPn(supplierPn);
		testAsset.setTenantId(1);
		//testAsset.setUid(uid);
		
		testAsset.setVatCodeId(userData.getId());
//		testAsset.setVersionNumber(versionNumber);
    }

	/**
	 * Creates an asset and retrieves it from the database. 
	 * TODOThis is essentially the same as the create test until we use DB Unit .
	 */
	@Test
	@Transactional
	public void retrieveAssetTest() {
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		Assets assetRetrieved = hibernateDAO.retrieve(Assets.class, testAsset.getId());
		flushAndClear();
		
		assertNotNull(assetRetrieved);
	}
    
	
	/**
	 * Creates an asset and tests if the data is present in db.
	 */
	@Test
	@Transactional
	public void createAssetTest()
	{
		
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		Assets assetRetrieved = hibernateDAO.retrieve(Assets.class, testAsset.getId());
		flushAndClear();
		
    	// Configure Test Asset - listed in alphabetical order.
		assertEquals(userData.getName(), assetRetrieved.getAssetNumberPart1().getName());
		assertEquals(userData.getName(), assetRetrieved.getAssetNumberPart2().getName());
		assertEquals(userData.getName(), assetRetrieved.getAssetNumberPart3().getName());
		assertEquals((int)assetNumberPart4, (int)assetRetrieved.getAssetNumberPart4());
		assertEquals(userData.getName(), assetRetrieved.getAssetStatus().getName());
		
		assertEquals(budgetLimit, assetRetrieved.getBudgetLimit());
		assertEquals(bcp, assetRetrieved.getBcp());
		
		assertEquals(userData.getName(), assetRetrieved.getCategory().getName());
		assertEquals(userData.getName(), assetRetrieved.getCountry().getName());
		assertEquals(commissioningDate, assetRetrieved.getCommissioningDate());
//		assertEquals(createdBy, assetRetrieved.getCreatedBy());
//		assertEquals(createdDate, assetRetrieved.getCreatedDate());
		assertEquals(criticalAsset, assetRetrieved.getCriticalAsset());
		assertEquals(partyCompany.getName(), assetRetrieved.getCustodian().getName());
		
		assertEquals(dateOfPurchase, assetRetrieved.getDateOfPurchase());
		assertEquals(decommissioningDate, assetRetrieved.getDecommissioningDate());

		assertEquals(description, assetRetrieved.getDescription());
		assertEquals(userData.getName(), assetRetrieved.getDepreciationCode().getName());
		assertEquals(userData.getName(), assetRetrieved.getDivision().getName());
		assertEquals(userData.getName(), assetRetrieved.getDepartment().getName());
		
		assertEquals(endOfLifeDate, assetRetrieved.getEndOfLifeDate());
		
		//testAsset.setId(id);
		assertEquals(installationDate, assetRetrieved.getInstallationDate());
		assertEquals(isAFacility, assetRetrieved.getIsAFacility());
		assertEquals(isEquipment, assetRetrieved.getIsEquipment());
		assertEquals(isPart, assetRetrieved.getIsPart());
		
//		assertEquals(lastUpdatedBy, assetRetrieved.getLastUpdatedBy());
//		assertEquals(lastUpdatedDate, assetRetrieved.getLastUpdatedDate());
		assertEquals((int)lifeExpectancy, (int)assetRetrieved.getLifeExpectancy());
		
		assertEquals(userData.getName(), assetRetrieved.getLocation().getName());

		assertEquals(partyCompany.getName(), assetRetrieved.getManufacturer().getName());
		assertEquals(manufacturerPn, assetRetrieved.getManufacturerPn());
		
		assertEquals(name, assetRetrieved.getName());

		assertEquals(partOfGroup, assetRetrieved.getPartOfGroup());
		assertEquals(parts, assetRetrieved.getParts());
		assertEquals(purchaseLeadTime,assetRetrieved.getPurchaseLeadTime());
		assertEquals(purchasePrice, assetRetrieved.getPurchasePrice());
		
		assertEquals(riskAssessment, assetRetrieved.getRiskAssessment());
		
		assertEquals(serialNumber, assetRetrieved.getSerialNumber());
		assertEquals(userData.getName(), assetRetrieved.getSite().getName());
		assertEquals(partyCompany.getName(), assetRetrieved.getSupplier().getName());
		assertEquals(supplierPn, assetRetrieved.getSupplierPn());
		
		// XXX more tests needed
		assertNotNull(assetRetrieved.getUid());
		System.out.println(assetRetrieved.getUid());
		
		assertEquals(userData.getName(), assetRetrieved.getVatCode().getName());
	}

	/**
	 * Update an existing asset in the database.
	 */
	@Test
	@Transactional
	public void updateAssetTest()
	{
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		Assets assetToUpdate= hibernateDAO.retrieve(Assets.class, testAsset.getId());
		flushAndClear();
		
		// Updated some fields fields.
		String updatedUid = "DLO4738JDUEHTJDLKJUTJT78754638";
		String updatedName = "Red Radiator";
		
		// updated user data
		UserData updatedUserData = new UserData();
		updatedUserData.setUserDataTypeId(udCatAndTypeId);

		updatedUserData.setName("RedRad");
		hibernateDAO.create(updatedUserData);
		flushAndClear();
		
		// updated partyCompany
		Company updatedPartyCompany = new Company();
    	updatedPartyCompany.setName("NSA");
    	updatedPartyCompany.setUid("updated_pt");
		hibernateDAO.create(updatedPartyCompany);
		flushAndClear();
		
		//assetToUpdate.setUid(updatedUid);
		assetToUpdate.setName(updatedName);
		assetToUpdate.setLocationId(updatedUserData.getId());
		assetToUpdate.setCustodianId(updatedPartyCompany.getId());
		
		hibernateDAO.update(assetToUpdate);
		flushAndClear();
		
		Assets assetRetrieved = hibernateDAO.retrieve(Assets.class, testAsset.getId());
		flushAndClear();
		
    	// Configure Test Asset - listed in alphabetical order.
		//assertEquals(updatedUserData.getName(), assetRetrieved.getLocation().getName());
		assertNotNull(assetRetrieved.getUid());
		assertEquals(updatedPartyCompany.getName(), assetRetrieved.getCustodian().getName());
	}
	
	/**
	 * Delete an existing asset in the database and test for exception being thrown.
	 */
	@Test (expected=ObjectNotFoundException.class)
	@Transactional
	public void deleteAssetTest()
	{
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		hibernateDAO.delete(testAsset);
		flushAndClear();
		
		// This should throw the exception ObjectNotFoundException
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, testAsset.getId());
		// Because of lazy loading, must do something with the retrieved asset to trigger the load
		System.out.println("END:" + retrievedAsset);
		
		hibernateDAO.retrieve(Assets.class, testAsset.getId());
	}
	
	//Test not needed anymore
//	/*
//	 * Test to check that update when the version number is changed should throw a stale state exception 
//	 */
//	@Test
//	@Transactional
//	public void staleStateExceptionTest()
//	{
//		testAsset.setCompanyId(userData.getId());
//    	
//		testAsset.setAssetNumberPart1Id(userData.getId());
//		testAsset.setAssetNumberPart2Id(userData.getId());
//		testAsset.setAssetNumberPart3Id(userData.getId());
//		testAsset.setAssetNumberPart4(assetNumberPart4);
//		testAsset.setAssetStatusId(userData.getId());
//		
//		testAsset.setBudgetLimit(budgetLimit);
//		testAsset.setBcp(bcp);
//		
//		testAsset.setCategoryId(userData.getId());
//		testAsset.setCountryId(userData.getId());
//		testAsset.setCommissioningDate(commissioningDate);
////		testAsset.setCreatedBy(createdBy);
////		testAsset.setCreatedDate(createdDate);
//		testAsset.setCriticalAsset(criticalAsset);
//		testAsset.setCustodianId(partyCompany.getId());
//		
//		testAsset.setDateOfPurchase(dateOfPurchase);
//		testAsset.setDecommissioningDate(decommissioningDate);
//		testAsset.setDescription(description);
//		testAsset.setDepreciationCodeId(userData.getId());
//		testAsset.setDivisionId(userData.getId());
//		testAsset.setDepartmentId(userData.getId());
//		
//		testAsset.setEndOfLifeDate(endOfLifeDate);
//		
//		testAsset.setInstallationDate(installationDate);
//		testAsset.setIsAFacility(isAFacility);
//		testAsset.setIsEquipment(isEquipment);
//		testAsset.setIsPart(isPart);
//		
////		testAsset.setLastUpdatedBy(lastUpdatedBy);
////		testAsset.setLastUpdatedDate(lastUpdatedDate);
//		testAsset.setLifeExpectancy(lifeExpectancy);
//		testAsset.setLocationId(userData.getId());
//
//		testAsset.setManufacturerId(partyCompany.getId());
//		testAsset.setManufacturerPn(manufacturerPn);
//		
//		testAsset.setName(name);
//
//		testAsset.setPartOfGroup(partOfGroup);
//		testAsset.setParts(parts);
//		testAsset.setPurchaseLeadTime(purchaseLeadTime);
//		testAsset.setPurchasePrice(purchasePrice);
//		
//		testAsset.setRiskAssessment(riskAssessment);
//		
//		testAsset.setSerialNumber(serialNumber);
//		testAsset.setSiteId(userData.getId());
//		testAsset.setSupplierId(partyCompany.getId());
//		testAsset.setSupplierPn(supplierPn);
//		
//		testAsset.setUid(uid);
//		
//		testAsset.setVatCode(userData);
////		testAsset.setVersionNumber(versionNumber);
//		
//		hibernateDAO.create(testAsset);	
//		flushAndClear();
//		
//		testAsset.setAssetNumberPart4(2);
//
//		testAsset.setBcp(false);
//		
//		testAsset.setCommissioningDate(null);
//		testAsset.setCreatedBy("Nick");
//		testAsset.setCreatedDate(null);
//		testAsset.setCriticalAsset(true);
//		
//		testAsset.setDateOfPurchase(null);
//		testAsset.setDecommissioningDate(null);
//		testAsset.setDescription("Test asset description");
//		
//		testAsset.setEndOfLifeDate(null);
//
//		testAsset.setInstallationDate(null);
//		testAsset.setIsAFacility(true);
//		testAsset.setIsEquipment(false);
//		testAsset.setIsPart(true);
//		
//		testAsset.setLastUpdatedBy("Jack");
//		testAsset.setLastUpdatedDate(null);
//		testAsset.setLifeExpectancy(9);
//		
//		testAsset.setManufacturerPn(manufacturerPn);
//		
//		testAsset.setName("Asset name");
//
//		testAsset.setPartOfGroup(true);
//		testAsset.setParts(true);
//		testAsset.setPurchaseLeadTime(2);
//		
//		testAsset.setRiskAssessment(false);
//		
//		testAsset.setSerialNumber("New blue radiator model");
//		
//		testAsset.setSupplierPn("BB-RAD-M-M123456789");
//		
//		testAsset.setTenantId(12345);
//		
//		testAsset.setUid("0123456789");
//		
//		testAsset.setVatCode(userData);
//		testAsset.setVersionNumber(2);
//		
//		try
//		{
//			hibernateDAO.update(testAsset);
//			flushAndClear();
//			
//			fail("No Exception Thrown");
//		}
//		catch(StaleStateException e)
//		{
//			assertEquals("Version number not incremented", 2, testAsset.getVersionNumber().intValue());
//		}
//		catch(Exception e)
//		{
//			fail("An Unexpected Exception was thown");
//		}
//	}

	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}
