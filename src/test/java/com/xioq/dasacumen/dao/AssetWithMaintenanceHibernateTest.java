/**
 * 
 */
package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

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
import com.xioq.dasacumen.model.assetregister.Maintenance;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.document.Doc;
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
 * Tests Asset with maintenance is working with CRUD operations.
 * @author nmarlor
 *
 */
public class AssetWithMaintenanceHibernateTest
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

	/**
	 * Maintenance used for testing.
	 */
	Maintenance testMaintenance;
	
	private static final Boolean maintenanceMandatory = true;
	private static final String maintenanceMandatoryNote = "maintenance mandatory";
	private static final Boolean maintenanceDocumentType = true;
	
	private static final Boolean ppmRequired = true;
	private static final String ppmRequiredNote = "ppm required";
	
	private static final Boolean tag = true;
	private static final String tagNote = "tag required";
	
	private static final Boolean thirdPartyProvider = true;

	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "audit";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	// Should only be used for retrieving as these values are set by db
	private static final Long id = 1L;
	private static final Long updatedId = 2L;
	private static final Integer versionNumber = 1;

	@Before
	public void setup()
	{
		testMaintenance = new Maintenance();
		
		testMaintenance.setMaintenanceDocumentType(maintenanceDocumentType);
		testMaintenance.setMaintenanceMandatory(maintenanceMandatory);
		testMaintenance.setMaintenanceMandatoryNote(maintenanceMandatoryNote);
		testMaintenance.setPpmRequired(ppmRequired);
		testMaintenance.setPpmRequiredNote(ppmRequiredNote);
		testMaintenance.setTag(tag);
		testMaintenance.setTagNote(tagNote);
		testMaintenance.setThirdPartyProvider(thirdPartyProvider);
		
		testMaintenance.setCreatedBy(createdBy);
		testMaintenance.setCreatedDate(createdDate);
		testMaintenance.setLastUpdatedBy(lastUpdatedBy);
		testMaintenance.setLastUpdatedDate(lastUpdatedDate);
		testMaintenance.setTenantId(tenantId);
		testMaintenance.setVersionNumber(versionNumber);
		
		// resets the sequences so that Id's should be consistent every time a
		// test runs.
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("tenants");
		testUtil.resetSequences("docs");
		testUtil.resetSequences("maintenance");
	}

	/**
	 * Create a maintenance with an asset.
	 * All pre req for an asset creation exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithoutAssetOrMaintenance.xml")
	@ExpectedDatabase(value = "classpath:dbunit/maintenanceTestDataNoDate_expected.xml", table = "acumen.maintenance", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createAssetWithMaintenanceTest()
	{
		Company partyCompany = hibernateDAO.retrieve(Company.class, id);
		UserData userData = hibernateDAO.retrieve(UserData.class, id);
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, id);
		
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

		testAsset.setTenantId(tenantId);
		testAsset.setVersionNumber(versionNumber);

		testAsset.setCreatedBy(createdBy);
		testAsset.setCreatedDate(createdDate);

		testAsset.setLastUpdatedBy(lastUpdatedBy);
		testAsset.setLastUpdatedDate(lastUpdatedDate);
		
		// finish setting the testMaintenance
		testMaintenance.setAsset(testAsset);
		testMaintenance.setAssetsId(1L);
		testMaintenance.setThirdPartySupplier(partyCompany);
		testMaintenance.setThirdPartySupplierId(partyCompany.getId());
		
		testMaintenance.setFaultCodeId(id);
		testMaintenance.setFaultCode(userData);
		testMaintenance.setFaultCodeCategory(userData);
		testMaintenance.setFaultCodeCategoryId(id);
		testMaintenance.setDocumentType(retrievedDoc.getDocType());
		
		testAsset.setMaintenance(testMaintenance);
		
		hibernateDAO.create(testAsset);
		flushAndClear();
		
   		Maintenance retrievedMaintenance = hibernateDAO.retrieve(Maintenance.class, testMaintenance.getId());
		flushAndClear();
   		
		assertEquals(testAsset.getId(), retrievedMaintenance.getAsset().getId());
   		assertEquals(maintenanceMandatoryNote, retrievedMaintenance.getMaintenanceMandatoryNote());
   		assertEquals(maintenanceMandatory, retrievedMaintenance.getMaintenanceMandatory());
   		assertEquals(ppmRequired, retrievedMaintenance.getPpmRequired());
   		assertEquals(tag, retrievedMaintenance.getTag());
   		assertEquals(tagNote, retrievedMaintenance.getTagNote());
   		
		assertEquals(createdBy, retrievedMaintenance.getCreatedBy());
		assertNotNull(retrievedMaintenance.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedMaintenance.getLastUpdatedBy());
		assertNotNull(retrievedMaintenance.getLastUpdatedDate());
	}

	/**
	 * Create a maintenance on an existing asset.
	 * Asset + no maintenance type.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/maintenanceTestData_setup.xml")
	@ExpectedDatabase(value = "classpath:dbunit/maintenanceTestDataNoDate_expected.xml", table = "acumen.maintenance", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createMaintenanceWithExistingAssetTest()
	{
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, id);
		Company retrievedCompany = hibernateDAO.retrieve(Company.class, id);
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, id);
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, id);
		
		// finish setting the testMaintenance
		testMaintenance.setAsset(retrievedAsset);
		testMaintenance.setAssetsId(1L);
		testMaintenance.setThirdPartySupplier(retrievedCompany);
		testMaintenance.setThirdPartySupplierId(retrievedCompany.getId());
		testMaintenance.setFaultCodeId(id);
		testMaintenance.setFaultCode(retrievedUserData);
		testMaintenance.setFaultCodeCategory(retrievedUserData);
		testMaintenance.setFaultCodeCategoryId(id);
		testMaintenance.setDocumentType(retrievedDoc.getDocType());
		
		retrievedAsset.setMaintenance(testMaintenance);
		
		hibernateDAO.update(retrievedAsset);
		flushAndClear();
		
   		Maintenance retrievedMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
		flushAndClear();
   		
   		assertEquals(retrievedAsset.getId(), retrievedMaintenance.getAsset().getId());
   		assertEquals(maintenanceMandatoryNote, retrievedMaintenance.getMaintenanceMandatoryNote());
   		assertEquals(maintenanceMandatory, retrievedMaintenance.getMaintenanceMandatory());
   		assertEquals(ppmRequired, retrievedMaintenance.getPpmRequired());
   		assertEquals(tag, retrievedMaintenance.getTag());
   		assertEquals(tagNote, retrievedMaintenance.getTagNote());
   		
		assertEquals(createdBy, retrievedMaintenance.getCreatedBy());
		assertNotNull(retrievedMaintenance.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedMaintenance.getLastUpdatedBy());
		assertNotNull(retrievedMaintenance.getLastUpdatedDate());
	}

	/**
	 * Update a field on maintenance and save it by updating the asset.
	 * Asset + 1 maintenance already exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/maintenanceTestData_updated.xml")
	public void updateMaintenanceFromAssetTest()
	{
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, id);
		Company retrievedCompany = hibernateDAO.retrieve(Company.class, id);
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, id);
		Doc retrievedDoc = hibernateDAO.retrieve(Doc.class, id);
		Maintenance testMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
		
		// finish setting the testMaintenance
		testMaintenance.setThirdPartySupplier(retrievedCompany);
		testMaintenance.setThirdPartySupplierId(updatedId);
		testMaintenance.setFaultCode(retrievedUserData);
		testMaintenance.setFaultCodeId(updatedId);
		testMaintenance.setFaultCodeCategory(retrievedUserData);
		testMaintenance.setFaultCodeCategoryId(updatedId);
		testMaintenance.setDocumentType(retrievedDoc.getDocType());
		
		retrievedAsset.setMaintenance(testMaintenance);
		
		hibernateDAO.update(retrievedAsset);
		flushAndClear();
		
   		Maintenance retrievedMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
		flushAndClear();
   		
   		assertEquals(retrievedAsset.getId(), retrievedMaintenance.getAsset().getId());
   		assertEquals(maintenanceMandatoryNote, retrievedMaintenance.getMaintenanceMandatoryNote());
   		assertEquals(maintenanceMandatory, retrievedMaintenance.getMaintenanceMandatory());
   		assertEquals(ppmRequired, retrievedMaintenance.getPpmRequired());
   		assertEquals(tag, retrievedMaintenance.getTag());
   		assertEquals(tagNote, retrievedMaintenance.getTagNote());
   		assertEquals(updatedId, retrievedMaintenance.getFaultCode().getId());
   		assertEquals(updatedId, retrievedMaintenance.getThirdPartySupplierId());
   		assertEquals(updatedId, retrievedMaintenance.getFaultCodeCategory().getId());
	}

	/**
	 * Remove a maintenance from an existing set. Making the set empty.
	 * Asset + Set + 1 maintenance already exists.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/maintenanceTestData_expected.xml")
	public void removeMaintenanceFromAsset()
	{
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, id);
		Maintenance testMaintenance = hibernateDAO.retrieve(Maintenance.class, id);
		
		retrievedAsset.getId();
		
		retrievedAsset.setMaintenance(null);
		
		hibernateDAO.update(testMaintenance);
		flushAndClear();
		
		List<Maintenance> retrieveMaintenance = hibernateDAO.retrieveAll(Maintenance.class);
		assertEquals(0, retrieveMaintenance.size());
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
