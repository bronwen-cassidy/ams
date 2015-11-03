package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import com.xioq.dasacumen.model.Group;
import com.xioq.dasacumen.model.GroupAsset;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.test.TestUtil;


//TODO replaced groupId with group, tests for group need to be done
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Transactional
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners(
{ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class GroupsHibernateTest
{

	@Autowired
	private TestUtil testUtil;
	
	Group testGroup;
	GroupAsset testGroupAsset;
	Assets testAsset;

	private static final Long groupId = 1L;
	private static final String name = "assetGroup1";

	private static final String createdBy = "audit";
	private static final Date createdDate = new Date();

	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date();

	private static final Set<GroupAsset> groupAssets = new HashSet<GroupAsset>(0);

	private static final String createdByGA = "audit";
	
	private static final Integer tenantId = 1;
	private static final Integer versionNumber = 1;
	
	private static final Long assetId = 1L;
	
	private static final Long TEST_DB_UNIT_ID1 = 1L;
	private static final Long TEST_DB_UNIT_ID2 = 2L;
	
//	private static final Assets asset = new Assets();
	
	
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

	
	@Before
	public void setup()
	{
		testGroup = new Group();
		testGroup.setName(name);
		testGroup.setTenantId(tenantId);
		testGroup.setCreatedBy(createdBy);
		testGroup.setCreatedDate(createdDate);
		testGroup.setLastUpdatedBy(lastUpdatedBy);
		testGroup.setLastUpdatedDate(lastUpdatedDate);
		testGroup.setVersionNumber(versionNumber);

		testGroupAsset = new GroupAsset();
		testGroupAsset.setAssetId(assetId);
		testGroupAsset.setTenantId(tenantId);
		testGroupAsset.setCreatedBy(createdBy);
		testGroupAsset.setCreatedDate(createdDate);
		
		testGroupAsset.setGroup(testGroup);

		testUtil.resetSequences("groups");
		testUtil.resetSequences("groups_assets");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("users");

	}

	/**
	 * 
	 */
	@Test
	@DatabaseSetup(value = "classpath:dbunit/groupTestData.xml")
	@ExpectedDatabase(value = "classpath:dbunit/groupTestDataWithTestData.xml", table="acumen.groups", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createGroup()
	{
		hibernateDAO.create(testGroup);
		flushAndClear();
		hibernateDAO.create(testGroupAsset);
		flushAndClear();
	
		Group groupRetrieved = hibernateDAO.retrieve(Group.class, TEST_DB_UNIT_ID1);
		
		//Asserts for the group itself
		assertEquals(name, groupRetrieved.getName());
		assertEquals(createdBy, groupRetrieved.getCreatedBy());
		assertEquals(lastUpdatedBy, groupRetrieved.getLastUpdatedBy());
		assertEquals(tenantId, groupRetrieved.getTenantId());
		assertEquals(versionNumber, groupRetrieved.getVersionNumber());
		assertNotNull(groupRetrieved.getCreatedDate());
		assertNotNull(groupRetrieved.getLastUpdatedDate());
		
		//Asserts for asset attached to group through group asset
		for (GroupAsset ga : groupRetrieved.getGroupAssets())
		{
			assertEquals(createdByGA, ga.getCreatedBy());
			assertEquals(tenantId, ga.getTenantId());
			assertNotNull(ga.getCreatedBy());
			assertNotNull(ga.getCreatedDate());
		}
	}

	@Test
	@DatabaseSetup("classpath:dbunit/groupTestDataWithTestGroup.xml")
	public void retrieveGroup()
	{
		Group groupRetrieved = hibernateDAO.retrieve(Group.class, groupId);
		
		///Asserts for the group itself
		assertEquals(name, groupRetrieved.getName());
		assertEquals(createdBy, groupRetrieved.getCreatedBy());		
		assertEquals(lastUpdatedBy, groupRetrieved.getLastUpdatedBy());
		assertEquals(tenantId, groupRetrieved.getTenantId());
		assertEquals(versionNumber, groupRetrieved.getVersionNumber());		
		
		for (GroupAsset ga : groupRetrieved.getGroupAssets())
		{
			assertEquals(assetId, ga.getAssetId());
			assertEquals(tenantId, ga.getTenantId());
			assertEquals(createdByGA, ga.getCreatedBy());
		}
	}

	@Test
	@DatabaseSetup("classpath:dbunit/groupTestData.xml")
	public void updateGroup()
	{
		Group retrievedGroup = hibernateDAO.retrieve(Group.class, TEST_DB_UNIT_ID1);
		
		final String updatedName = "assetGroup3";
		
		retrievedGroup.setName(updatedName);

		hibernateDAO.update(retrievedGroup);
		flushAndClear();
		
		Group retrievedGroup2 = hibernateDAO.retrieve(Group.class, retrievedGroup.getId());
		
		assertEquals(updatedName, retrievedGroup2.getName());
		
		assertEquals(createdBy, retrievedGroup2.getCreatedBy());
		assertNotNull(retrievedGroup2.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedGroup2.getLastUpdatedBy());
		assertNotNull(retrievedGroup2.getLastUpdatedDate());
		
		for (GroupAsset ga : retrievedGroup2.getGroupAssets())
		{
			assertEquals(tenantId, ga.getTenantId());
			assertEquals(createdByGA, ga.getCreatedBy());
		}
	}

	/**
	 * makes sure that the fields that are not able to be updated.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/groupTestData.xml")
	public void failUpdateGroup()
	{
		long testId = 1L;
		Group groupToFail = hibernateDAO.retrieve(Group.class, testId);
		GroupAsset groupAssetToFail = hibernateDAO.retrieve(GroupAsset.class, testId);

		Date updatedCreatedDate = new Date(22, 10, 55);;
		String updatedCreatedBy = "Jim";
		Integer updatedTenantId = 2;
		Integer updatedVersionNumber = 2;

		groupToFail.setCreatedBy(updatedCreatedBy);
		groupToFail.setCreatedDate(updatedCreatedDate);
		groupToFail.setTenantId(updatedTenantId);
		groupToFail.setVersionNumber(updatedVersionNumber);
		
		groupAssetToFail.setCreatedBy(updatedCreatedBy);
		groupAssetToFail.setCreatedDate(updatedCreatedDate);
		groupAssetToFail.setTenantId(updatedTenantId);

		Group groupRetrieved = hibernateDAO.retrieve(Group.class, testId);
		GroupAsset groupAssetRetrieved = hibernateDAO.retrieve(GroupAsset.class, testId);

		assertEquals(updatedCreatedBy, groupRetrieved.getCreatedBy());
		assertEquals(updatedCreatedDate, groupRetrieved.getCreatedDate());
		assertEquals(updatedTenantId, groupRetrieved.getTenantId());
		assertEquals(updatedVersionNumber, groupRetrieved.getVersionNumber());
		assertEquals(updatedCreatedBy,groupAssetRetrieved.getCreatedBy());
		assertEquals(updatedCreatedDate,groupAssetRetrieved.getCreatedDate());
		assertEquals(updatedTenantId,groupAssetRetrieved.getTenantId());
	}

	@Test(expected = ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/groupTestData.xml")
	public void deleteGroup()
	{
		hibernateDAO.create(testGroup);
		hibernateDAO.delete(testGroup);
		hibernateDAO.delete(testGroupAsset);
		flushAndClear();
		// This should throw the exception ObjectNotFoundException
		Group retrieveGroup = hibernateDAO.retrieve(Group.class, testGroup.getId());
		// Because of lazy loading, must do something with the retrieved user to
		// trigger the load
		System.out.println("END:" + retrieveGroup);
		
		hibernateDAO.create(testGroupAsset);
		hibernateDAO.delete(testGroupAsset);
		flushAndClear();
		// This should throw the exception ObjectNotFoundException
		GroupAsset retrieveGroupAsset = hibernateDAO.retrieve(GroupAsset.class, testGroupAsset.getId());
		// Because of lazy loading, must do something with the retrieved user to
		// trigger the load
		System.out.println("END:" + retrieveGroupAsset);
	}

	/*
	 * Test to check that update when the version number is changed should throw
	 * a stale state exception
	 */
	@Test(expected = StaleStateException.class)
	@DatabaseSetup("classpath:dbunit/groupTestData.xml")
	public void staleStateExceptionTest()
	{
		Long testId1 = 1L;
		
		Group firstGroupRetrieved = hibernateDAO.retrieve(Group.class, testId1);
		if (null == firstGroupRetrieved)
			throw new RuntimeException("Test data not setup correctly. Failed to retrieve list for id: "
					+ testId1);
		flushAndClear();

		Group secondGroupRetrieved = hibernateDAO.retrieve(Group.class, testId1);
		flushAndClear();

		firstGroupRetrieved.setName("Bob");
		hibernateDAO.update(firstGroupRetrieved);
		flushAndClear();

		// Should get an error as the first update should have incremented (by
		// hibernate) the version number
		secondGroupRetrieved.setName("Dave");
		hibernateDAO.update(secondGroupRetrieved);
		flushAndClear();
	}
	
	/**
	 * Creates a set of groupAsset it then adds some content into the contents set and finally removes it 
	 * if statement checks if the groupAssets set is empty, if so its a pass, else its a fail.
	 */
	@Test()
	@DatabaseSetup("classpath:dbunit/groupTestData.xml")
	public void removeGroupAssetFromGroup()
	{
		Long testId = 1L;
		Group testGroupList = hibernateDAO.retrieve(Group.class, testId);
		
		Set<GroupAsset> groupAssets = testGroupList.getGroupAssets();
		GroupAsset elc1 = hibernateDAO.retrieve(GroupAsset.class, testId);
		groupAssets.remove(elc1);
		
		testGroupList.setGroupAssets( groupAssets);
		
		hibernateDAO.update(testGroupList);
		flushAndClear();
		
		List<GroupAsset> groupListList = hibernateDAO.retrieveAll(GroupAsset.class);
		
		assertEquals(1, groupListList.size());
		
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
