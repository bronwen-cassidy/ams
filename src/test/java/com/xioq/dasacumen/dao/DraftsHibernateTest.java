package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

import com.xioq.dasacumen.model.Draft;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.assetregister.Maintenance;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.LeaseIn;
import com.xioq.dasacumen.model.common.LeaseOut;
import com.xioq.dasacumen.model.common.Person;
import com.xioq.dasacumen.model.common.UserDataSets;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.model.systemadmin.UserDataTypes;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
/**
 * Tests drafts are working with CRUD operations and json conversion is working.
 * @author echhung
 *
 */
public class DraftsHibernateTest {
	@Autowired
	private TestUtil testUtil;
	
	/*
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;
	
	/*
	 * Creates an instance of session factory so that the session cache can be cleared
	 * to avoid false positives on tests
	 */
	@Autowired @Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	/**
	 * Asset draft used for testing.
	 */
	Draft<Assets> testAssetDraft;
	
	/**
	 * Asset draft used for testing.
	 */
	Draft<Assets> testConversionAssetDraft;
	
	/**
	 * Company draft used for testing.
	 */
	Draft<Company> testCompanyDraft;
	
	/**
	 * Person draft used for testing.
	 */
	Draft<Person> testPersonDraft;
	
	/**
	 * test asset to be used in conjunction with draft.
	 */
	Assets testAsset;
	
	private static final String name = "Radiator draft";
	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	private static final long userId = 1L;
	
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
	
	// Maintenance variables
	private static final Boolean maintenanceMandatory = true;
	private static final String maintenanceMandatoryNote = "maintenance mandatory";
	private static final Boolean maintenanceDocumentType = true;
	private static final Boolean ppmRequired = true;
	private static final String ppmRequiredNote = "ppm required";
	private static final Boolean tag = true;
	private static final String tagNote = "tag required";
	private static final Boolean thirdPartyProvider = true;
	
	private static final Long id = 1L;
	
	/**
	 * Used for retrieving asset from database (dbunit xml) NOT setting for creation.
	 */
	private static final long testDraftId =  1L; 
	
	private static final String assetName = "Blue Acer Computer";
	
	/**
	 * Sets up a test asset.
	 */
	@Before
	public void setup() {
		// Asset draft setup
		testAsset = new Assets();
		testAsset.setName(assetName);
		
		// Draft setup
		testAssetDraft = new Draft<Assets>();
		testAssetDraft.setName(name);
		testAssetDraft.setUserId(userId);
		testAssetDraft.setObject(testAsset);
		
		testUtil.resetSequences("drafts");
		testUtil.resetSequences("users");
	}
	
	/** 
	 * Tests if a draft can be created correctly.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/draftTestData.xml")
	// TODO problem with assigning object data in dbunit. Slight differences with set data.
//	@ExpectedDatabase(value = "classpath:dbunit/draftTestDataWithTestDraft.xml", table = "acumen.drafts", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createDraftTest(){
		hibernateDAO.create(testAssetDraft);
		flushAndClear();
		
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testAssetDraft.getId());
		
		// Asserts fields are saved correctly.
		assertEquals(name, retrievedDraft.getName());

		Assets asset = retrievedDraft.getObject();
		// Checks that object draft's name is saved correctly.
		assertEquals(assetName, asset.getName());
		
	}
	
	/** 
	 * Tests if a draft can be created correctly with an asset and a lease in
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/draftTestData.xml")
	public void createDraftWithAssetAndLeaseInTest(){
		// Alter the test Asset to take in a Lease in.
		LeaseIn testLeaseIn =  new LeaseIn();
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
		
		// add it to the asset
		testAsset.setLeaseIn(testLeaseIn);
		
		// set the object on thedraft.
		testAssetDraft.setObject(testAsset);
		
		hibernateDAO.create(testAssetDraft);
		flushAndClear();
		
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testAssetDraft.getId());
		
		// Asserts fields are saved correctly.
		assertEquals(name, retrievedDraft.getName());

		Assets asset = retrievedDraft.getObject();
		// Checks that object draft's name is saved correctly.
		assertEquals(assetName, asset.getName());
		
	}
	
	/** 
	 * Tests if a draft can be created correctly with an asset and a lease in
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/draftTestData.xml")
	public void createDraftWithAssetAndLeaseOutTest(){
		// Alter the test Asset to take in a Lease out.
		LeaseOut testLeaseOut =  new LeaseOut();
		testLeaseOut = new LeaseOut();

		testLeaseOut.setLeaseCharge(leaseCharge);
		testLeaseOut.setLeaseCommences(leaseCommences);
		testLeaseOut.setLeaseExpires(leaseExpires);
		testLeaseOut.setLeasePeriod(leasePeriod);
		testLeaseOut.setLeaseCost(leaseCost);
		testLeaseOut.setLeaseValue(leaseValue);
		testLeaseOut.setLocationPostcode(locationPostcode);
		testLeaseOut.setLeaseOutMargin(leaseOutMargin);
		
		// add it to the asset
		testAsset.setLeaseOut(testLeaseOut);
		
		// set the object on thedraft.
		testAssetDraft.setObject(testAsset);
		
		hibernateDAO.create(testAssetDraft);
		flushAndClear();
		
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testAssetDraft.getId());
		
		// Asserts fields are saved correctly.
		assertEquals(name, retrievedDraft.getName());

		Assets asset = retrievedDraft.getObject();
		// Checks that object draft's name is saved correctly.
		assertEquals(assetName, asset.getName());
		
	}

	
	/**
	 * Tests if a draft can be created correctly with an asset and a maintenance
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/draftTestData.xml")
	public void createDraftWithAssetAndMaintenanceTest(){
		
		Maintenance testMaintenance =  new Maintenance();
		testMaintenance = new Maintenance();

		testMaintenance.setMaintenanceDocumentType(maintenanceDocumentType);
		testMaintenance.setMaintenanceMandatory(maintenanceMandatory);
		testMaintenance.setMaintenanceMandatoryNote(maintenanceMandatoryNote);
		testMaintenance.setPpmRequired(ppmRequired);
		testMaintenance.setPpmRequiredNote(ppmRequiredNote);
		testMaintenance.setTag(tag);
		testMaintenance.setTagNote(tagNote);
		testMaintenance.setThirdPartyProvider(thirdPartyProvider);
		
		Set<UserDataSets> setOfSkills = new HashSet<UserDataSets>();
		
		UserDataSets coreSkill = new UserDataSets(hibernateDAO.retrieve(UserData.class, 1L), testAsset, hibernateDAO.retrieve(UserDataTypes.class, 1L));
		UserDataSets qualificationSkill = new UserDataSets(hibernateDAO.retrieve(UserData.class, 1L), testAsset, hibernateDAO.retrieve(UserDataTypes.class, 1L));
		UserDataSets disciplineSkill = new UserDataSets(hibernateDAO.retrieve(UserData.class, 1L), testAsset, hibernateDAO.retrieve(UserDataTypes.class, 1L));
		UserDataSets industrySkill = new UserDataSets(hibernateDAO.retrieve(UserData.class, 1L), testAsset, hibernateDAO.retrieve(UserDataTypes.class, 1L));
		
		setOfSkills.add(coreSkill);
		setOfSkills.add(qualificationSkill);
		setOfSkills.add(disciplineSkill);
		setOfSkills.add(industrySkill);

		testMaintenance.setSkills(setOfSkills);
		
		testAsset.setMaintenance(testMaintenance);
		
		// set the object on the draft.
		testAssetDraft.setObject(testAsset);
		
		hibernateDAO.create(testAssetDraft);
		flushAndClear();
		
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testAssetDraft.getId());
		
		// Asserts fields are saved correctly.
		assertEquals(name, retrievedDraft.getName());

		Assets asset = retrievedDraft.getObject();
		// Checks that object draft's name is saved correctly.
		assertEquals(assetName, asset.getName());
		assertEquals(maintenanceDocumentType, asset.getMaintenance().getMaintenanceDocumentType());
		
		for (UserDataSets theCoreSkill : asset.getMaintenance().getCoreSkills())
		{
			assertEquals(1L,theCoreSkill);
		}
		
		for (UserDataSets theIndustry : asset.getMaintenance().getIndustries())
		{
			assertEquals(1L,theIndustry);
		}
		
		for (UserDataSets theQualification : asset.getMaintenance().getQualifications())
		{
			assertEquals(1L,theQualification);
		}
		
		for (UserDataSets theDiscipline : asset.getMaintenance().getDiscipline())
		{
			assertEquals(1L,theDiscipline);
		}
		
	}
	
	/**
	 * Tests if a draft is retrieved correctly. Dbunit xml file already holds a draft for 
	 * retrieval.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/draftTestDataWithTestDraft.xml")
//	@ExpectedDatabase(value = "classpath:dbunit/draftTestDataWithTestDraft.xml", table = "acumen.drafts", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveDraftTest(){
		
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testDraftId);
		
		// Asserts fields are retrieved correctly.
		assertEquals(name, retrievedDraft.getName());

		// Checks that object draft's name is saved correctly.
		assertEquals(assetName, retrievedDraft.getObject().getName());
	}
	
	/**
	 * Tests if a draft is updated correctly. Dbunit xml file already holds a draft for 
	 * retrieval with all fields allowed to be updated.
	 */
	@Test
    @DatabaseSetup("classpath:dbunit/draftTestDataWithTestDraft.xml")
	//TODO new xml file needs to be made.
	//@ExpectedDatabase(value = "classpath:dbunit/draftTestDataWithTestDraft.xml", table = "acumen.drafts", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void updateDraftTest(){
		// Updated fields
		String updatedName = "Locker Draft";
		String updatedAssetName = "Dark Red Laptop";
		 
		//Retrieve the draft to update
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testDraftId);
		
		// Set draft with new field.
		retrievedDraft.setName(updatedName);
		// Updated asset
		Assets updatedAsset = new Assets();
		updatedAsset.setName(updatedAssetName);
		// Set new asset to draft
		retrievedDraft.setObject(updatedAsset);
		
		
		// Update the draft
		hibernateDAO.update(retrievedDraft);
		flushAndClear();
		
		// Retrieve it again
		Draft<Assets> retrievedDraft2 = hibernateDAO.retrieve(Draft.class, retrievedDraft.getId());
		
		// Asserts fields are retrieved correctly.
		assertEquals(updatedName, retrievedDraft2.getName());
		
		// Checks that object draft's name is saved correctly.
		assertEquals(updatedAssetName, retrievedDraft2.getObject().getName());
	}
	
	/**
	 * Test checks these fields cannot be updated by the system.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/draftTestDataWithTestDraft.xml")
    @ExpectedDatabase(value = "classpath:dbunit/draftTestDataWithTestDraft.xml", table = "acumen.drafts", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void failToUpdateDraftFields() {
		// Updated fields
		Integer UpdatedTenantId  = 400;
		String UpdatedCreatedBy = "morganfreeman@jla.co.uk";
		Date UpdatedCreatedDate = new Date(200, 10, 20);
		
		//Retrieve the asset to updat.e
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testDraftId);
		
		// Set asset with new fields.
		retrievedDraft.setTenantId(UpdatedTenantId);
		retrievedDraft.setCreatedBy(UpdatedCreatedBy);
		retrievedDraft.setCreatedDate(UpdatedCreatedDate);
		
		// Update the draft
		hibernateDAO.update(retrievedDraft);
		flushAndClear();
		
		// Retrieve it again
		Draft<Assets> retrievedDraft2 = hibernateDAO.retrieve(Draft.class, retrievedDraft.getId());
		
		// Asserts fields are not changed.
		assertEquals(tenantId, retrievedDraft2.getTenantId());
		assertEquals(createdBy, retrievedDraft2.getCreatedBy());
		assertEquals(createdDate, retrievedDraft2.getCreatedDate());	
	}
	
	
	/**
	 * Tests if the draft class can correctly save and retrieve an asset.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/draftTestDataWithTestDraft.xml")
	public void jsonAssetConversionTest(){
		// Since asset has so many fields, instead of asserting all we get one of each type ie:
		// long (FK), String, bool, integer numeric and date
		Long company = 1L;
		String conversionAssetName = "I3 Acer Laptop";
		Integer purchaseLeadTime = 5;
		boolean partOfGroup = true;
		BigDecimal purchasePrice = new BigDecimal(301.68);
		Date dateOfPurchase = new Date(114, 01, 02);
		
		// Company draft setup
		Assets testConversionAsset = new Assets();
		testConversionAsset.setCompanyId(company);
		testConversionAsset.setName(conversionAssetName);
		testConversionAsset.setPurchaseLeadTime(purchaseLeadTime);
		testConversionAsset.setPartOfGroup(partOfGroup);
		testConversionAsset.setPurchasePrice(purchasePrice);
		testConversionAsset.setDateOfPurchase(dateOfPurchase);
		
		// Set the object and do the conversion
		testConversionAssetDraft = new Draft();
		testConversionAssetDraft.setObject(testConversionAsset);
		
		// Get the object back and assert fields.
		// Audit fields not added because they will never be set in the draft
		Assets jsonConvertedObject = testConversionAssetDraft.getObject();
		assertEquals(company, jsonConvertedObject.getCompanyId());
		assertEquals(conversionAssetName, jsonConvertedObject.getName());
		assertEquals(purchaseLeadTime, jsonConvertedObject.getPurchaseLeadTime());
		assertEquals(partOfGroup, jsonConvertedObject.getPartOfGroup());
		assertEquals(purchasePrice, jsonConvertedObject.getPurchasePrice());
		assertEquals(dateOfPurchase, jsonConvertedObject.getDateOfPurchase());
	}
	
	/**
	 * Tests if the draft class can correctly save and retrieve a Company.
	 */
	@Test
	public void jsonCompanyConversionTest(){
		String name = "JLA";
		String vatNo = "T1";
		String regNo = "MFDKK455445";
		
		// Company draft setup
		Company testCompany = new Company();
		testCompany.setName(name);
		testCompany.setRegNo(regNo);
		testCompany.setVatNo(vatNo);
		
		// Set the object and do the conversion
		testCompanyDraft = new Draft();
		testCompanyDraft.setObject(testCompany);
		Company jsonConvertedObject = testCompanyDraft.getObject();
		
		// Get the object back and assert fields.
		// Audit fields not added because they will never be set in the draft
		assertEquals(name, jsonConvertedObject.getName());		
		assertEquals(regNo, jsonConvertedObject.getRegNo());
		assertEquals(vatNo, jsonConvertedObject.getVatNo());
	}
	
	/**
	 * Tests if the draft class can correctly save and retrieve a Person.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/draftTestData.xml")
	public void jsonPersonConversionTest(){
		String forename = "Edmond";
		String surname = "Chhung";
		String gender = "male";
		String position = "Developer";
		
		// Person draft setup
		Person testPerson = new Person();
		testPerson.setForenames(forename);
		testPerson.setSurname(surname);
		testPerson.setGender(gender);
		testPerson.setPosition(position);
		
		// Set the object and do the conversion
		testPersonDraft = new Draft<Person>();
		testPersonDraft.setObject(testPerson);
		Person jsonConvertedObject = testPersonDraft.getObject();
		
		// Get the object back and assert fields.
		// Audit fields not added because they will never be set in the draft
		assertEquals(forename, jsonConvertedObject.getForenames());
		assertEquals(surname, jsonConvertedObject.getSurname());
		assertEquals(gender, jsonConvertedObject.getGender());
		assertEquals(position, jsonConvertedObject.getPosition());
	}
	
	/**
	 * Tests if a draft can be deleted correctly.
	 */
	@Test(expected=ObjectNotFoundException.class)
    @DatabaseSetup("classpath:dbunit/draftTestData.xml")
    //@ExpectedDatabase(value = "classpath:dbunit/draftTestData.xml", table = "acumen.drafts", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void deleteDraftTest(){
		
		Draft<Assets> retrievedDraft = hibernateDAO.retrieve(Draft.class, testDraftId);
		
		hibernateDAO.delete(retrievedDraft);
		flushAndClear();
		
		Draft<Assets> retrievedDraft2 = hibernateDAO.retrieve(Draft.class, retrievedDraft.getId());
		
		// Will trigger the exception
		System.out.println(retrievedDraft2.getName());
	}
	
	/**
	 * Test to check that in the scenario where two users edit the same version of a draft but update at different times
	 * the user who tries to update last will get a stale state exception because the data is now out of date and they will
	 * have to retrieve the draft again.
	 */
	@Test(expected = StaleStateException.class)
	@DatabaseSetup("classpath:dbunit/draftTestDataWithTestDraft.xml")
	public void staleStateExceptionEntityListTest()
	{
		Draft<Assets> firstDraft = hibernateDAO.retrieve(Draft.class, testDraftId);
		flushAndClear();
		
		Draft<Assets> sameDraft = hibernateDAO.retrieve(Draft.class, testDraftId);
		flushAndClear();
		
		firstDraft.setName("Fridge draft");
		hibernateDAO.update(firstDraft);
		flushAndClear();
		
		sameDraft.setName("Freezer draft");
		hibernateDAO.update(sameDraft);
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
