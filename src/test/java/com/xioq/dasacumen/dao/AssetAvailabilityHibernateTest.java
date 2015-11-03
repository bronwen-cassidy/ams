package com.xioq.dasacumen.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.assetregister.AssetAvailabilitySearch;
import com.xioq.dasacumen.model.assetregister.AssetSchedule;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
@TransactionConfiguration(defaultRollback=false)
public class AssetAvailabilityHibernateTest
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
	
	@Before
	public void setup()
	{
		testUtil.resetSequences("asset_schedule");
	}
		
	@After
    public void tearDown() {
//	    Not required as using rollback
    }
	
	/*
	 * Test to retrieve the address table from the database to check that it has been created
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/assetAvailableTestData.xml")
	public void retrieveAvailableAssetsTest()
	{
		String query = "Spanner";	
		Calendar from = new GregorianCalendar(2014, 10, 22);
		Calendar to = new GregorianCalendar(2014, 10, 29);
		
		AssetAvailabilitySearch availableAssetsSearch = new AssetAvailabilitySearch();
		availableAssetsSearch.setAvailableDates(query, from.getTime(), to.getTime());
		SearchResult<Assets> assets = hibernateDAO.generalSearch(availableAssetsSearch);

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");
		System.out.println("From: " + sdf.format(from.getTime()) + " To: "+ sdf.format(to.getTime()));
		
		for(Assets asset : assets.getResult())
		{
			System.out.println("Asset Retrieved " + asset.getName() + " id " + asset.getId());
			Set<AssetSchedule> scheds = asset.getAssetSchedule(); 
			for(AssetSchedule sched: scheds){
				System.out.println("From: " + sched.getLeaseCommences() + " To: " + sched.getLeaseExpires());
			}
		}
		System.out.println("Assets Retrieved " + assets.getResult().size());
		
	}
	
	/*
	 * Test to create an instance of an address then checking this is created successfully
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/assetAvailableTestData.xml")
	public void retrieveNotAvailableAssetsTest()
	{
		String query = "Spanner";	
		Calendar from = new GregorianCalendar(2014, 10, 22); // 
		Calendar to = new GregorianCalendar(2014, 10, 29);
		
		AssetAvailabilitySearch availableAssetsSearch = new AssetAvailabilitySearch();
		availableAssetsSearch.setNotAvailableDates(query, from.getTime(), to.getTime());
		SearchResult<Assets> assets = hibernateDAO.generalSearch(availableAssetsSearch);
		flushAndClear();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-YYYY");

		System.out.println("From: " + sdf.format(from.getTime()) + " To: "+ sdf.format(to.getTime()));
		
		for(Assets asset : assets.getResult())
		{
			System.out.println("Asset Retrieved " + asset.getName() + " id " + asset.getId());
			Set<AssetSchedule> scheds = asset.getAssetSchedule(); 
			for(AssetSchedule sched: scheds){
				System.out.println("From: " + sched.getLeaseCommences() + " To: " + sched.getLeaseExpires());
			}
		}
		System.out.println("Assets Retrieved " + assets.getResult().size());
		
	}
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}
