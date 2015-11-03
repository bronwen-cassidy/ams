package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import com.xioq.dasacumen.model.common.WarrantyPolicy;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

/**
 * Tests CRUD operations on WarrantyPolicy Model.
 * @author nmarlor
 */
public class WarrantyHibernateTest extends HibernateDAOTestBase implements CrudTests {
	
	private static final Date COMMENCEMENT_DATE = new Date(100,10,10);
	private static final Date EXPIRY_DATE = new Date(150,10,10);
	private static final Boolean OM = false;
	private static final String POLICY_NUMBER = "XYZALPHA123445533";
	private static final BigDecimal COST = new BigDecimal(2000.46);

	// Should only be used for retrieving as these values are set by db
	private static final Long DBUNIT_WARRANTY_POLICY_ID_200 = 200L;
	private static final Long WARRANTY_SUPPLIER_ID_100 = 100L;
	private static final Long WARRANTY_TYPE_ID_10000 = 10000L;
	
	@Before
	public void setup() {
		// Reset sequences here, always use the database table constants file.
		testUtil.resetSequences(DatabaseTable.WARRANTY_POLICIES);		
	}
	
	/**
	 * Checks that a warranty policy can be retrieved
	 */
	@Test
    @DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
    		"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
    		"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
    		"classpath:dbunit/asset/assetsTestData.xml",
    		"classpath:dbunit/warrantyTestData.xml"})
	public void retrieveTest() 
	{
		// retrieve it	
		WarrantyPolicy warrantyPolicyRetrieved = hibernateDAO.retrieve(WarrantyPolicy.class, DBUNIT_WARRANTY_POLICY_ID_200);
		
		// assert values are correct
		assertEquals(WARRANTY_SUPPLIER_ID_100, warrantyPolicyRetrieved.getSupplier().getId());
		assertEquals(WARRANTY_TYPE_ID_10000, warrantyPolicyRetrieved.getTypeId());
		assertEquals(COMMENCEMENT_DATE, warrantyPolicyRetrieved.getCommencementDate());
		assertEquals(EXPIRY_DATE, warrantyPolicyRetrieved.getExpiryDate());
		assertEquals(OM, warrantyPolicyRetrieved.getOm());
		assertEquals(POLICY_NUMBER, warrantyPolicyRetrieved.getPolicyNumber());
		assertEquals(COST, warrantyPolicyRetrieved.getCost());
		
		// assert audit fields are not null 
		assertAuditFields(warrantyPolicyRetrieved);
	}
	
	/**
	 * Checks a warranty can be created correctly
	 */
	@Test
    @DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
    		"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
    		"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
    		"classpath:dbunit/asset/assetsTestData.xml",
    		"classpath:dbunit/warrantyTestData.xml"})
	public void createTest() 
	{
		WarrantyPolicy testWarrantyPolicy = new WarrantyPolicy();
		
		// assign variables to object
		testWarrantyPolicy.setCommencementDate(COMMENCEMENT_DATE);
		testWarrantyPolicy.setCost(COST);
		testWarrantyPolicy.setExpiryDate(EXPIRY_DATE);
		testWarrantyPolicy.setOm(OM);
		testWarrantyPolicy.setPolicyNumber(POLICY_NUMBER);
		testWarrantyPolicy.setSupplierId(WARRANTY_SUPPLIER_ID_100);
		testWarrantyPolicy.setTypeId(WARRANTY_TYPE_ID_10000);
		
		hibernateDAO.create(testWarrantyPolicy);
		flushAndClear();
		
		WarrantyPolicy warrantyPolicyRetrieved = hibernateDAO.retrieve(WarrantyPolicy.class, testWarrantyPolicy.getId());
		
		// Assert values are stored correctly
		assertEquals(COMMENCEMENT_DATE, warrantyPolicyRetrieved.getCommencementDate());
		assertEquals(COST, warrantyPolicyRetrieved.getCost());
		assertEquals(EXPIRY_DATE, warrantyPolicyRetrieved.getExpiryDate());
		assertEquals(OM, warrantyPolicyRetrieved.getOm());
		assertEquals(POLICY_NUMBER, warrantyPolicyRetrieved.getPolicyNumber());
		assertEquals(WARRANTY_SUPPLIER_ID_100, warrantyPolicyRetrieved.getSupplier().getId());
		assertEquals(WARRANTY_TYPE_ID_10000, warrantyPolicyRetrieved.getType().getId());
		
		// assert audit fields are not null 
		assertAuditFields(warrantyPolicyRetrieved);		
	}
	
	/**
	 * Test that a warranty policy can be updated.
	 */
	@Test
    @DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
    		"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
    		"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
    		"classpath:dbunit/asset/assetsTestData.xml",
    		"classpath:dbunit/warrantyTestData.xml"})
	public void updateTest() 
	{
		WarrantyPolicy warrantyToUpdate = hibernateDAO.retrieve(WarrantyPolicy.class, DBUNIT_WARRANTY_POLICY_ID_200);
		
		Long updatedTypeId = 10001L;
		Long updatedSupplierId = 101L;
		Date updatedCommencementDate = new Date(600,10,10);
		Date updatedExpiryDate = new Date(630,10,10);
		Boolean updatedOm= true;
		String updatedPolicyNumber = "DKDKD45454321";
		BigDecimal UpdatedCost = new BigDecimal(4000.90);
		
		warrantyToUpdate.setCommencementDate(updatedCommencementDate);
		warrantyToUpdate.setCost(UpdatedCost);
		warrantyToUpdate.setExpiryDate(updatedExpiryDate);
		warrantyToUpdate.setOm(updatedOm);
		warrantyToUpdate.setPolicyNumber(updatedPolicyNumber);
		warrantyToUpdate.setSupplierId(updatedSupplierId);
		warrantyToUpdate.setTypeId(updatedTypeId);
		
		hibernateDAO.update(warrantyToUpdate);
		
		WarrantyPolicy warrantyPolicyRetrieved = hibernateDAO.retrieve(WarrantyPolicy.class, warrantyToUpdate.getId());
		
		assertEquals(updatedTypeId, warrantyPolicyRetrieved.getTypeId());
		assertEquals(updatedCommencementDate, warrantyPolicyRetrieved.getCommencementDate());
		assertEquals(updatedExpiryDate, warrantyPolicyRetrieved.getExpiryDate());
		assertEquals(updatedOm, warrantyPolicyRetrieved.getOm());
		assertEquals(updatedPolicyNumber, warrantyPolicyRetrieved.getPolicyNumber());
		assertEquals(UpdatedCost, warrantyPolicyRetrieved.getCost());		
	}
	
	/**
	 * Checks that certain audit fields are not being updated.
	 */
	@Test
    @DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
    		"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
    		"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
    		"classpath:dbunit/asset/assetsTestData.xml",
    		"classpath:dbunit/warrantyTestData.xml"})
	public void failToUpdateWarrantyPolicyTest() 
	{
			
		Integer updatedTenantId = 10;
		String updatedCreatedBy = "JamesHacker@Hotmail.com";
		Date updatedCreatedDate = new Date(300, 10, 10);
		
		WarrantyPolicy retrievedWarrantyPolicy = hibernateDAO.retrieve(WarrantyPolicy.class, DBUNIT_WARRANTY_POLICY_ID_200);
		
		// Set none updatable fields
		retrievedWarrantyPolicy.setTenantId(updatedTenantId);
		retrievedWarrantyPolicy.setCreatedBy(updatedCreatedBy);
		retrievedWarrantyPolicy.setCreatedDate(updatedCreatedDate);
		
		// Do the update
		hibernateDAO.update(retrievedWarrantyPolicy);
		flushAndClear();
		
		// retrieve the existing warranty
		WarrantyPolicy retrievedWarrantyPolicy2 = hibernateDAO.retrieve(WarrantyPolicy.class, retrievedWarrantyPolicy.getId());
		
		// Assert values have not changed
		assertNotNull(retrievedWarrantyPolicy2.getTenantId());
		assertNotNull(retrievedWarrantyPolicy2.getCreatedBy());
		assertNotNull(retrievedWarrantyPolicy2.getVersionNumber());
		
	}
	
	/**
	 * Checks that a warranty policy is deleted correctly
	 */
	@Test(expected=ObjectNotFoundException.class)
    @DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
    		"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
    		"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
    		"classpath:dbunit/asset/assetsTestData.xml",
    		"classpath:dbunit/warrantyTestData.xml"})
	public void deleteTest() 
	{
		WarrantyPolicy warrantyPolicyToDelete = hibernateDAO.retrieve(WarrantyPolicy.class, DBUNIT_WARRANTY_POLICY_ID_200);
		hibernateDAO.delete(warrantyPolicyToDelete);
		flushAndClear();
		
		WarrantyPolicy retrievedWarrantyPolicy = hibernateDAO.retrieve(WarrantyPolicy.class, warrantyPolicyToDelete.getId());
		
		System.out.println("Retrieved Warranty Policy: " + retrievedWarrantyPolicy);
	}
	
	@Test()
    @DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
    		"classpath:dbunit/parties/emailsTelephoneEtcTestData.xml",
    		"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
    		"classpath:dbunit/asset/assetsTestData.xml",
    		"classpath:dbunit/warrantyTestData.xml"})
	public void staleStateExceptionTest()
	{
		// Both users retrieve version 0
		WarrantyPolicy warrantyPolicyRetrieved1 = hibernateDAO.retrieve(WarrantyPolicy.class, DBUNIT_WARRANTY_POLICY_ID_200);
		flushAndClear();
		WarrantyPolicy warrantyPolicyRetrieved2 = hibernateDAO.retrieve(WarrantyPolicy.class, DBUNIT_WARRANTY_POLICY_ID_200);
		flushAndClear();
		
		warrantyPolicyRetrieved1.setPolicyNumber("Updated policy number");
		
		hibernateDAO.update(warrantyPolicyRetrieved1);
		flushAndClear(); 
		assertEquals("Version number not incremented", 1, warrantyPolicyRetrieved1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		warrantyPolicyRetrieved2.setPolicyNumber(POLICY_NUMBER);
		// and tries to update
		try
		{
			hibernateDAO.update(warrantyPolicyRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, warrantyPolicyRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown");
		}
	}
}
