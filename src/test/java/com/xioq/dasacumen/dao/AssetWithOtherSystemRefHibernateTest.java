package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

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
import com.xioq.dasacumen.model.common.OtherSystemRef;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class AssetWithOtherSystemRefHibernateTest {

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
	
	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "JamesWesker@jla.co.uk";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "chrisredfield@jla.co.uk";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	
	// Only used for retrieving
	private static final Long id = 1L;
	private static final Integer versionNumber = 0;
	
	@Before
	public void setup(){
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
	}
	
	
	@Test
	public void createAssetTest(){
		
		UserData userData = new UserData();
		
		userData.setCreatedBy(createdBy);
		userData.setCreatedDate(createdDate);
		userData.setTenantId(tenantId);
		userData.setUserDataTypeId(1l);
		
		hibernateDAO.create(userData);
		flushAndClear();
		
		Company testCompany = new Company();
		
		testCompany.setUid("1234");
		hibernateDAO.create(testCompany);
		flushAndClear();
		
		// Retrieve a user data
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, id);
		Company partyCompany= hibernateDAO.retrieve(Company.class, id);
		
		Assets testAsset = new Assets();
		
		testAsset.setCompanyId(retrievedUserData.getId());
		testAsset.setAssetStatusId(retrievedUserData.getId());
		testAsset.setSupplierId(partyCompany.getId());
		testAsset.setCustodianId(partyCompany.getId());
    	
		testAsset.setAssetNumberPart1Id(retrievedUserData.getId());
		testAsset.setAssetNumberPart2Id(retrievedUserData.getId());
		testAsset.setAssetNumberPart3Id(retrievedUserData.getId());
		testAsset.setAssetNumberPart4(1);
		testAsset.setSiteId(retrievedUserData.getId());
		testAsset.setLocationId(retrievedUserData.getId());
		
		testAsset.setCategoryId(retrievedUserData.getId());
		testAsset.setCountryId(retrievedUserData.getId());
		
		testAsset.setDepreciationCodeId(retrievedUserData.getId());
		testAsset.setDivisionId(retrievedUserData.getId());
		testAsset.setDepartmentId(retrievedUserData.getId());
		
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
		
		// save the asset
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, id);
		
		assertEquals(id, retrievedAsset.getId());
	}
	
	@Test
	public void createAssetWithOtherSystemRefTest(){
		
		UserData userData = new UserData();
		
		userData.setCreatedBy(createdBy);
		userData.setCreatedDate(createdDate);
		userData.setTenantId(tenantId);
		userData.setUserDataTypeId(1l);
		
		hibernateDAO.create(userData);
		flushAndClear();
		
		Company testCompany = new Company();
		
		testCompany.setUid("1234");
		hibernateDAO.create(testCompany);
		flushAndClear();
		
		// Retrieve a user data
		UserData retrievedUserData = hibernateDAO.retrieve(UserData.class, id);
		Company partyCompany= hibernateDAO.retrieve(Company.class, id);
		
		Assets testAsset = new Assets();
		
		testAsset.setCompanyId(retrievedUserData.getId());
		testAsset.setAssetStatusId(retrievedUserData.getId());
		testAsset.setSupplierId(partyCompany.getId());
		testAsset.setCustodianId(partyCompany.getId());
    	
		testAsset.setAssetNumberPart1Id(retrievedUserData.getId());
		testAsset.setAssetNumberPart2Id(retrievedUserData.getId());
		testAsset.setAssetNumberPart3Id(retrievedUserData.getId());
		testAsset.setAssetNumberPart4(1);
		testAsset.setSiteId(retrievedUserData.getId());
		testAsset.setLocationId(retrievedUserData.getId());
		
		testAsset.setCategoryId(retrievedUserData.getId());
		testAsset.setCountryId(retrievedUserData.getId());
		
		testAsset.setDepreciationCodeId(retrievedUserData.getId());
		testAsset.setDivisionId(retrievedUserData.getId());
		testAsset.setDepartmentId(retrievedUserData.getId());
		
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
		
		OtherSystemRef otherSysRef = new OtherSystemRef();
		
		otherSysRef.setAssets(testAsset);
		otherSysRef.setOtherSystemName(retrievedUserData);
//		otherSysRef.setId(id);
		otherSysRef.setOtherSystemId("testId");
		
		testAsset.addOtherSystemRef(otherSysRef);
		
		// save the asset
		hibernateDAO.create(testAsset);
		flushAndClear();
		
		assertNotNull(testAsset.getId());
		assertNotNull(otherSysRef.getId());
		
		
		Assets retrievedAsset = hibernateDAO.retrieve(Assets.class, testAsset.getId());
		flushAndClear();
		
		assertNotNull(retrievedAsset.getOtherSystemRefs());
		assertFalse(retrievedAsset.getOtherSystemRefs().isEmpty());
		assertEquals(1, retrievedAsset.getOtherSystemRefs().size());
		
		OtherSystemRef otherSystemRef = retrievedAsset.getOtherSystemRefs().iterator().next();
		
		assertEquals("testId", otherSystemRef.getOtherSystemId());
	}
	
	@Test
	public void updateAssetWithOtherSystemRefTest(){
		fail("not yet implemented");
	}
	
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}
