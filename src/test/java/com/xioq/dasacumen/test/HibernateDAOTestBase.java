package com.xioq.dasacumen.test;

import static org.junit.Assert.assertNotNull;

import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.xioq.dasacumen.dao.CRUDHibernateDAO;
import com.xioq.dasacumen.lib.model.VersionControlled;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
//Change this to false to see changes in the db but becareful of test data which is left in the db when using this.
//If database is dirtied with test data. Use @DatabaseSetup("classpath:dbunit/tableClear.xml") in the setup of the
//class. This will allow dbunit to clean the database table referenced in the xml file and run the test in a clean state.
//This will not remove the entry but just basically allow dbunit to revert the table to nothing to perform the test then
//put the entry back in the db.
@TransactionConfiguration(defaultRollback = true)
public class HibernateDAOTestBase {

	/**
	 * Used for resetting the sequence number.
	 */
	@Autowired
	protected TestUtil testUtil;
	
	/**
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	protected CRUDHibernateDAO hibernateDAO;
	
	/**
	 * Creates an instance of session factory so that the session cache can be cleared
	 * to avoid false positives on tests
	 */
	@Autowired @Qualifier("sessionFactory")
	protected SessionFactory sessionFactory;
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
	
	protected void assertAuditFields(VersionControlled objectToTest) {
		// Put these in a method if using more than once.
		assertNotNull("CreatedBy field is null", objectToTest.getCreatedBy());
		assertNotNull("CreatedDate field is null", objectToTest.getCreatedDate());
		assertNotNull("UpdatedBy field is null", objectToTest.getLastUpdatedBy());
		assertNotNull("UpdatedDate field is null", objectToTest.getLastUpdatedDate());
		assertNotNull("TenantId field is null", objectToTest.getTenantId());
		assertNotNull("VersionNumber field is null", objectToTest.getVersionNumber());
	}
	
}
