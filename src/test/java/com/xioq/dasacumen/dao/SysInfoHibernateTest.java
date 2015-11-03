package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

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

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import com.xioq.dasacumen.model.systemadmin.SystemInfo;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
public class SysInfoHibernateTest 
{
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
	
	static final String property = "Version 1.1";
	static final Date valDate = new Date (100, 12, 04);
	static final int valNum = 1;
	static final String valStr = "system";
		
	/**
	 * SysInfo used for testing.
	 */
	SystemInfo sysInfo;
	
	/**
	 * Sets up a test system info.
	 */
	@Before
	public void setup()
	{
		sysInfo = new SystemInfo();
		
		sysInfo.setProperty(property);
		sysInfo.setValStr(valStr);
		sysInfo.setValDate(valDate);
		sysInfo.setValNum(valNum);
	}
	 
	/**
	 * Tests if a system info can be created correctly.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/sysInfoTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/sysInfoTestData.xml", table = "acumen.system_info", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void retrieveSystemInfoTest()
	{
		// Retrieve system info
		SystemInfo retrievedSysInfo = hibernateDAO.retrieve(SystemInfo.class, property);
		
		// Assert values are stored correctly
		assertEquals(property, retrievedSysInfo.getProperty());
		assertEquals(valStr, retrievedSysInfo.getValStr());
		assertNotNull(retrievedSysInfo.getValDate());
		assertEquals((int)valNum, (int)retrievedSysInfo.getValNum());
	}
	
	/*
	 * Test to create a system info object then retrieve this object to check that it was created successfully
	 */
	@Test
	// No setup required for system info
	@ExpectedDatabase(value = "classpath:dbunit/sysInfoTestData.xml", table = "acumen.system_info", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createSystemInfoTest()
	{
		hibernateDAO.create(sysInfo);
		flushAndClear();
		
		SystemInfo sysInfoRetrieved = hibernateDAO.retrieve(SystemInfo.class, sysInfo.getProperty());
		
		assertEquals((int)valNum, (int)sysInfoRetrieved.getValNum());
		assertEquals(valStr, sysInfoRetrieved.getValStr());
		assertEquals(property, sysInfoRetrieved.getProperty());
	}
	
	/**
	 * Update existing data regarding System Information in the database.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/sysInfoTestData.xml")
	public void updateSystemInfoTest()
	{	
		// Updated fields
		final int updatedValNum = 2;
		final String updatedValStr = "system info";
		
		// Retrieve the system info
		SystemInfo sysInfoRetrieved = hibernateDAO.retrieve(SystemInfo.class, sysInfo.getProperty());
		
		// Set the new updated fields
		sysInfoRetrieved.setValNum(updatedValNum);
		sysInfoRetrieved.setValStr(updatedValStr);
		
		// Do the update
		hibernateDAO.update(sysInfoRetrieved);
		flushAndClear();
		
		// Retrieve the updated system info
		SystemInfo sysInfoRetrieved2 = hibernateDAO.retrieve(SystemInfo.class, sysInfo.getProperty());
		
		// Assert values are stored correctly
		assertEquals(updatedValStr, sysInfoRetrieved2.getValStr());
		assertEquals((int)updatedValNum, (int)sysInfoRetrieved2.getValNum());
	}
	
	/**
	 * Delete an existing System Info in the database and test for exception being thrown.
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/sysInfoTestData.xml")
	public void deleteSystemInfoTest()
	{	
		SystemInfo sysInfoRetrieved = hibernateDAO.retrieve(SystemInfo.class, sysInfo.getProperty());

		hibernateDAO.delete(sysInfoRetrieved);
		flushAndClear();
		
		SystemInfo sysInfoRetrieved2 = hibernateDAO.retrieve(SystemInfo.class, sysInfo.getProperty());
		
		// Will trigger the exception
		System.out.println(sysInfoRetrieved2.getValStr());
	}
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
		public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
	
}
