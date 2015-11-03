package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;

import com.github.springtestdbunit.annotation.DatabaseSetup;

import com.xioq.dasacumen.model.common.InsurancePolicy;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

/**
 * Tests CRUD operations on WarrantyPolicy Model.
 * @author nmarlor
 */
public class InsurancePolicyHibernateTest extends HibernateDAOTestBase implements CrudTests {
	
	private static final Date COMMENCEMENT_DATE = new Date(100,10,10);
	private static final Date EXPIRY_DATE = new Date(150,10,10);
	private static final String POLICY_NUMBER = "QWERTYUIOPSD";
	private static final BigDecimal COST = new BigDecimal(2000.46);
	
	// Should only be used for retrieving as these values are set by db
	private static final Long DBUNIT_INSURANCE_SUPPLIER_ID_101 = 101L;
	private static final Long DBUNIT_INSURANCE_POLICY_ID_505 = 505L;
	
	@Before
	public void setup() {
		
		testUtil.resetSequences(DatabaseTable.INSURANCE_POLICIES);
	}

	/**
	 * Checks an insurance policy can be created correctly
	 */
	@Test
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/insurance/testDataWithTestInsurancePolicy.xml"})
	public void createTest() 
	{
		InsurancePolicy testInsurancePolicy = new InsurancePolicy();
		
		// assign variables to object
		testInsurancePolicy.setCommencementDate(COMMENCEMENT_DATE);
		testInsurancePolicy.setCost(COST);
		testInsurancePolicy.setExpiryDate(EXPIRY_DATE);
		testInsurancePolicy.setPartyId(DBUNIT_INSURANCE_SUPPLIER_ID_101);
		testInsurancePolicy.setPolicyNumber(POLICY_NUMBER);
		
		hibernateDAO.create(testInsurancePolicy);
		flushAndClear();
		
		InsurancePolicy insurancePolicyRetrieved = hibernateDAO.retrieve(InsurancePolicy.class, testInsurancePolicy.getId());
		
		assertEquals(COMMENCEMENT_DATE, insurancePolicyRetrieved.getCommencementDate());
		assertEquals(COST, insurancePolicyRetrieved.getCost());
		assertEquals(EXPIRY_DATE, insurancePolicyRetrieved.getExpiryDate());
		assertEquals(DBUNIT_INSURANCE_SUPPLIER_ID_101, insurancePolicyRetrieved.getInsuranceSupplier().getId());
		assertEquals(POLICY_NUMBER, insurancePolicyRetrieved.getPolicyNumber());
		
		// assert audit fields are not null 
		assertAuditFields(insurancePolicyRetrieved);	
	}
	
	/**
	 * Checks that an insurance policy can be retrieved
	 */
	@Test
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
    						"classpath:dbunit/asset/insurance/testDataWithTestInsurancePolicy.xml"})
	public void retrieveTest() 
	{
		InsurancePolicy insurancePolicyRetrieved = hibernateDAO.retrieve(InsurancePolicy.class, DBUNIT_INSURANCE_POLICY_ID_505);
		
		// Assert values are stored correctly
		assertEquals(DBUNIT_INSURANCE_SUPPLIER_ID_101, insurancePolicyRetrieved.getInsuranceSupplier().getId());
		assertEquals(COMMENCEMENT_DATE, insurancePolicyRetrieved.getCommencementDate());
		assertEquals(EXPIRY_DATE, insurancePolicyRetrieved.getExpiryDate());
		assertEquals(POLICY_NUMBER, insurancePolicyRetrieved.getPolicyNumber());
		assertEquals(COST, insurancePolicyRetrieved.getCost());
		
		// assert audit fields are not null 
		assertAuditFields(insurancePolicyRetrieved);
	}
	
	/**
	 * Test that an insurance policy can be updated.
	 */
	@Test
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/insurance/testDataWithTestInsurancePolicy.xml"})
	public void updateTest() 
	{
		InsurancePolicy insurancePolicyToUpdate = hibernateDAO.retrieve(InsurancePolicy.class, DBUNIT_INSURANCE_POLICY_ID_505);
		
		Long updatedSupplierId = 100L;
		Date updatedCommencementDate = new Date(430,02,11);
		Date updatedExpiryDate = new Date(435,02,11);
		String updatedPolicyNumber = "GYUHJSBUS";
		BigDecimal updatedCost = new BigDecimal(2345.45);
		
		insurancePolicyToUpdate.setCommencementDate(updatedCommencementDate);
		insurancePolicyToUpdate.setCost(updatedCost);
		insurancePolicyToUpdate.setExpiryDate(updatedExpiryDate);
		insurancePolicyToUpdate.setPartyId(updatedSupplierId);
		insurancePolicyToUpdate.setPolicyNumber(updatedPolicyNumber);
		
		hibernateDAO.update(insurancePolicyToUpdate);
		
		InsurancePolicy insurancePolicyRetrieved = hibernateDAO.retrieve(InsurancePolicy.class, insurancePolicyToUpdate.getId());
		
		assertEquals(updatedSupplierId, insurancePolicyRetrieved.getPartyId());
		assertEquals(updatedCommencementDate, insurancePolicyRetrieved.getCommencementDate());
		assertEquals(updatedExpiryDate, insurancePolicyRetrieved.getExpiryDate());
		assertEquals(updatedPolicyNumber, insurancePolicyRetrieved.getPolicyNumber());
		assertEquals(updatedCost, insurancePolicyRetrieved.getCost());		
	}
	
	/**
	 * Checks that an insurance policy is deleted correctly
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/insurance/testDataWithTestInsurancePolicy.xml"})
	public void deleteTest() 
	{
		InsurancePolicy insurancePolicyToDelete = hibernateDAO.retrieve(InsurancePolicy.class, DBUNIT_INSURANCE_POLICY_ID_505);
		hibernateDAO.delete(insurancePolicyToDelete);
		flushAndClear();
		
		InsurancePolicy retrievedInsurancePolicy = hibernateDAO.retrieve(InsurancePolicy.class, insurancePolicyToDelete.getId());
		
		System.out.println("Retrieved Insurance Policy: " + retrievedInsurancePolicy);
	}
	
	/**
	 * Test to check that in the scenario where two users edit the same version of an insurance policy but update at different times
	 * the user who tries to update last will get a stale state exception because the data is now out of date and they will
	 * have to retrieve the insurance policy again.
	 */
	@Test()
	@DatabaseSetup(value = {"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/insurance/testDataWithTestInsurancePolicy.xml"})
	public void staleStateExceptionTest()
	{		
		InsurancePolicy insurancePolicyRetrieved1 = hibernateDAO.retrieve(InsurancePolicy.class, DBUNIT_INSURANCE_POLICY_ID_505);
		flushAndClear();
		InsurancePolicy insurancePolicyRetrieved2 = hibernateDAO.retrieve(InsurancePolicy.class, DBUNIT_INSURANCE_POLICY_ID_505);
		flushAndClear();
		
		insurancePolicyRetrieved1.setPolicyNumber("New policy number");
		
		hibernateDAO.update(insurancePolicyRetrieved1);
		flushAndClear();	
		assertEquals("Version number not incremented", 1, insurancePolicyRetrieved1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		insurancePolicyRetrieved2.setPolicyNumber(POLICY_NUMBER);
		// and tries to update
		try
		{
			hibernateDAO.update(insurancePolicyRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, insurancePolicyRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown");
		}
	}
}
