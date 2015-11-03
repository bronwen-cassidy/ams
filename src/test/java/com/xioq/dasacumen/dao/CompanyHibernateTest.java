package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * Tests companies intereacts with hibernate and the db correctly.
 * @author echhung
 *
 */
public class CompanyHibernateTest extends HibernateDAOTestBase implements CrudTests
{
	private static final String NAME = "Churchill Ltd";
	private static final String REG_NO = "1234";
	private static final String VAT_NO = "929293";
	private static final String UID = "das_pt_1216396282453037280";
	
	private static final Long DBUNIT_COMPANY_ID1 = 1L;

	@Before
	
	public void setup()
	{
		testUtil.resetSequences(DatabaseTable.PARTIES);
	}
	
	@Test
    @DatabaseSetup("classpath:dbunit/companyTestData.xml")
	public void retrieveTest() 
	{
		Company retrievedCompany = hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY_ID1);
		
		assertEquals(NAME, retrievedCompany.getName());
		assertEquals(REG_NO, retrievedCompany.getRegNo());
		assertEquals(VAT_NO, retrievedCompany.getVatNo());
		assertEquals(UID, retrievedCompany.getUid());
		
		assertAuditFields(retrievedCompany);
		
	}
	
	@Test
	public void createTest()
	{
		Company testCompany = new Company();
		testCompany.setName(NAME);
		testCompany.setRegNo(REG_NO);
		testCompany.setVatNo(VAT_NO);
		testCompany.setUid(UID);

		hibernateDAO.create(testCompany);
		flushAndClear();
		
		Company retrievedCompany = hibernateDAO.retrieve(Company.class, testCompany.getId());
		
		assertEquals(NAME, retrievedCompany.getName());
		assertEquals(REG_NO, retrievedCompany.getRegNo());
		assertEquals(VAT_NO, retrievedCompany.getVatNo());
		assertEquals(UID, retrievedCompany.getUid());
		
		assertAuditFields(retrievedCompany);
	}
	
	@Test
    @DatabaseSetup("classpath:dbunit/companyTestData.xml")
	public void updateTest()
	{	
		Company companyToUpdate = hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY_ID1);
		
		final String updatedName = "Bulldog ltd";
		final String updatedVatNo = "15";
		final String updatedRegNo = "153";
		
		companyToUpdate.setName(updatedName);
		companyToUpdate.setVatNo(updatedVatNo);
		companyToUpdate.setRegNo(updatedRegNo);
		
		hibernateDAO.update(companyToUpdate);
		flushAndClear();
		
		Company retrievedCompany = hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY_ID1);
		
		assertEquals(updatedName, retrievedCompany.getName());
		assertEquals(updatedRegNo, retrievedCompany.getRegNo());
		assertEquals(updatedVatNo, retrievedCompany.getVatNo());
		
		assertAuditFields(retrievedCompany);
	}
	
	@Test(expected=ObjectNotFoundException.class)
    @DatabaseSetup("classpath:dbunit/companyTestData.xml")
	public void deleteTest()
	{	
		Company retrievedCompany = hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY_ID1);
		
		hibernateDAO.delete(retrievedCompany);
		flushAndClear();
		
		Company retrieveCompany = hibernateDAO.retrieve(Company.class, retrievedCompany.getId());
		
		/* Include this comment in all delete tests using this method */
		// Because of lazy loading, must do something with the retrieved address to trigger the load 
		// to throw the exception, thus the printline.
		System.out.println("Retrieved Company: " + retrieveCompany);
	}

	@Test()
    @DatabaseSetup("classpath:dbunit/companyTestData.xml")
	public void staleStateExceptionTest()
	{	
		// Both users retrieve version 0
		Company comanyRetrieved1 = hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY_ID1);
		flushAndClear();
		Company comanyRetrieved2 = hibernateDAO.retrieve(Company.class, DBUNIT_COMPANY_ID1);
		flushAndClear();
		
		comanyRetrieved1.setName("Bright Future Software");
		
		hibernateDAO.update(comanyRetrieved1);
		flushAndClear(); 
		assertEquals("Version number not incremented", 1, comanyRetrieved1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		comanyRetrieved2.setName(NAME);
		// and tries to update
		try
		{
			hibernateDAO.update(comanyRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, comanyRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown");
		}
	}
}
