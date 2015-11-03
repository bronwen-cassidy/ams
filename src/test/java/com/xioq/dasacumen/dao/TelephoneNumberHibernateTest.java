package com.xioq.dasacumen.dao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.common.TelephoneNumber;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;

public class TelephoneNumberHibernateTest extends HibernateDAOTestBase implements CrudTests{
	
	private static final String TEL_NO = "1234567890";
	
	private static final Long DBUNIT_TEL_NO_ID1 = 1L;
	
	@Before
	public void setup()
	{
		testUtil.resetSequences(DatabaseTable.TELEPHONE);
		testUtil.resetSequences(DatabaseTable.PARTY_TELEPHONE);
		testUtil.resetSequences(DatabaseTable.PARTIES);
		testUtil.resetSequences(DatabaseTable.USER_DATA);
	}
	
	@Test
	@DatabaseSetup(value = "classpath:dbunit/telNoTestDataWithTestTelNo.xml")
	public void retrieveTest() {
		TelephoneNumber telNoRetrieved = hibernateDAO.retrieve(TelephoneNumber.class, DBUNIT_TEL_NO_ID1);
		
		assertEquals(TEL_NO, telNoRetrieved.getTelNo());
		assertAuditFields(telNoRetrieved);
	}
	
	@Test
	public void createTest()
	{
		TelephoneNumber testTelNum = new TelephoneNumber();
		testTelNum.setTelNo(TEL_NO);
		
		hibernateDAO.create(testTelNum);
		flushAndClear();
		
		TelephoneNumber telNoRetrieved = hibernateDAO.retrieve(TelephoneNumber.class, testTelNum.getId());
		
		assertEquals(TEL_NO, telNoRetrieved.getTelNo());
		assertAuditFields(telNoRetrieved);
	}

	@Test
	@DatabaseSetup(value = "classpath:dbunit/telNoTestDataWithTestTelNo.xml")
	public void updateTest ()
	{
		TelephoneNumber telNoToUpdate = hibernateDAO.retrieve(TelephoneNumber.class, DBUNIT_TEL_NO_ID1);
		
		final String updatedTelNo = "0161 777777";
		
		telNoToUpdate.setTelNo(updatedTelNo);
		
		hibernateDAO.update(telNoToUpdate);
		flushAndClear();
		
		TelephoneNumber telNumberRetrieved = hibernateDAO.retrieve(TelephoneNumber.class, telNoToUpdate.getId());
		
		assertEquals(updatedTelNo, telNumberRetrieved.getTelNo());
		
		assertAuditFields(telNoToUpdate);
	}
	
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup(value = "classpath:dbunit/telNoTestDataWithTestTelNo.xml")
	public void deleteTest()
	{	
		TelephoneNumber telNoToDelete = hibernateDAO.retrieve(TelephoneNumber.class, DBUNIT_TEL_NO_ID1);
		
		hibernateDAO.delete(telNoToDelete);
		flushAndClear();
		
		TelephoneNumber retrievedtelNo = hibernateDAO.retrieve(TelephoneNumber.class, telNoToDelete.getId());
		
		/* Include this comment in all delete tests using this method */
		// Because of lazy loading, must do something with the retrieved address to trigger the load 
		// to throw the exception, thus the printline.
		System.out.println("Retrieved Telephone Number: " + retrievedtelNo);
	}
	
	@Test()
	@DatabaseSetup(value = "classpath:dbunit/telNoTestDataWithTestTelNo.xml")
	public void staleStateExceptionTest()
	{
		// Both users retrieve version 0
		TelephoneNumber telNoRetrieved1 = hibernateDAO.retrieve(TelephoneNumber.class, DBUNIT_TEL_NO_ID1);
		flushAndClear();
		TelephoneNumber telNoRetrieved2 = hibernateDAO.retrieve(TelephoneNumber.class, DBUNIT_TEL_NO_ID1);
		flushAndClear();
		
		// only update one field - not an audit field though !
		telNoRetrieved1.setTelNo("0213 555 1123");
		
		hibernateDAO.update(telNoRetrieved1);
		flushAndClear(); 
		assertEquals("Version number not incremented", 1, telNoRetrieved1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		telNoRetrieved2.setTelNo(TEL_NO);
		// and tries to update
		try
		{
			hibernateDAO.update(telNoRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, telNoRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown");
		}
	}
}
