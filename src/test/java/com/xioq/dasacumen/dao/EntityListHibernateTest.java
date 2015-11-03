package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.ComparisonFailure;
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

import com.xioq.dasacumen.model.EntityList;
import com.xioq.dasacumen.model.EntityListContent;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
public class EntityListHibernateTest
{
	@Autowired
	private TestUtil testUtil;
	
	EntityList testList;
	EntityListContent testEntityListContent;
	Set<EntityListContent> entityListContents;
	

	// Primary keys of the lists from the DBUnit data scripts
	private static final Long TEST_LIST_ONE_ID = 1l;
	private static final Long TEST_LIST_TWO_ID = 2l;

	private static final String name = "ListOne";
	private static final String entityType = "assets";
	private static final Long userId = 1L;
	private static final Long entityId = 1L;

	private static final String createdBy = "audit";
	private static final Date createdDate = new Date();

	private static final String lastUpdatedBy = "audit";
	private static final Date lastUpdatedDate = new Date();

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
		testList = new EntityList();
		testList.setName(name);
		testList.setUserId(userId);
		testList.setCreatedBy(createdBy);
		testList.setCreatedDate(createdDate);
		testList.setLastUpdatedBy(lastUpdatedBy);
		testList.setEntityType(entityType);
		testList.setLastUpdatedDate(lastUpdatedDate);

		testEntityListContent = new EntityListContent();
		testEntityListContent.setEntityId(entityId);
		testEntityListContent.setEntityList(testList);

		entityListContents = new HashSet<EntityListContent>(0);
		entityListContents.add(testEntityListContent);
		testList.setEntityListContents(entityListContents);
		
		testUtil.resetSequences("list_contents");
		testUtil.resetSequences("lists");
		
		
	}

	/**
	 * i created the lists and contents and compare them against what was
	 * retrieved to make sure that
	 * each field had been created with the correct values assigned.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void createEntityList()
	{
		// Create the list and contents in one operation
		hibernateDAO.create(testList);
		flushAndClear();

		EntityList entityListRetrieved = hibernateDAO.retrieve(EntityList.class, testList.getId());

		assertEquals(name, entityListRetrieved.getName());
		assertEquals(userId, entityListRetrieved.getUserId());
		assertEquals(entityType, entityListRetrieved.getEntityType());
		assertEquals(createdBy, entityListRetrieved.getCreatedBy());
		assertNotNull(entityListRetrieved.getCreatedDate());
		assertEquals(lastUpdatedBy, entityListRetrieved.getLastUpdatedBy());
		assertNotNull(entityListRetrieved.getLastUpdatedDate());

		// fails when there is more than one.
		assertNotNull(entityListRetrieved.getEntityListContents());
		assertFalse("List of IDS is empty", entityListRetrieved.getEntityListContents().isEmpty());
		assertEquals("List has more than one content", 1, entityListRetrieved.getEntityListContents().size());
		Long contentId = null;
		for (EntityListContent elc : entityListRetrieved.getEntityListContents())
		{
			contentId = elc.getId();
			assertEquals(entityId, elc.getEntityId());
		}

		EntityListContent contentRetrieved = hibernateDAO.retrieve(EntityListContent.class, contentId);
		assertNotNull(contentRetrieved.getEntityList());
		assertEquals(entityListRetrieved, contentRetrieved.getEntityList());
		flushAndClear();
	}

	/**
	 * calling the class and the associated id for the class then comparing
	 * global
	 * variables to what are in the tables
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void retrieveEntityList()
	{
		Long testId = 1L;
		EntityList entityListRetrieved = hibernateDAO.retrieve(EntityList.class, testId);

		assertEquals(name, entityListRetrieved.getName());
		assertEquals(entityType, entityListRetrieved.getEntityType());
		assertEquals(createdBy, entityListRetrieved.getCreatedBy());
		assertNotNull(createdDate);
		assertEquals(lastUpdatedBy, entityListRetrieved.getLastUpdatedBy());
		assertNotNull(lastUpdatedDate);
		
		// fails when there is more than one.
		assertNotNull(entityListRetrieved.getEntityListContents());
		assertFalse("List of IDS is empty", entityListRetrieved.getEntityListContents().isEmpty());
		assertEquals("List has more than one contents", 1, entityListRetrieved.getEntityListContents().size());
		for (EntityListContent elc : entityListRetrieved.getEntityListContents())
		{
			assertEquals(entityId, elc.getEntityId());
		}

		EntityListContent contentRetrieved = hibernateDAO.retrieve(EntityListContent.class, testId);
		assertNotNull(contentRetrieved.getEntityList());
		assertEquals(entityListRetrieved, contentRetrieved.getEntityList());
		 flushAndClear();

	}

	@Test
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void updateEntityListFields()
	{
		Long testId1 = 1L;
		EntityList listToUpdate = hibernateDAO.retrieve(EntityList.class, testId1);

		String updatedLastUpdatedBy = "audit";
		Date updatedLastUpdateDate = new Date(10, 11, 14);
		String updatedName = "ListThree";
		String updatedEntityType = "assets";
		Long updatedEntityId = 3L;

		listToUpdate.setName(updatedName);
		listToUpdate.setEntityType(updatedEntityType);
		listToUpdate.setLastUpdatedBy(updatedLastUpdatedBy);
		listToUpdate.setLastUpdatedDate(updatedLastUpdateDate);

		EntityListContent updatedEntityListContent = hibernateDAO.retrieve(EntityListContent.class, testId1);
		updatedEntityListContent.setEntityId(updatedEntityId);
		updatedEntityListContent.setEntityList(listToUpdate);

		Set<EntityListContent> updatedEntityListContents = listToUpdate.getEntityListContents();
		
		updatedEntityListContents.add(updatedEntityListContent);

	listToUpdate.setEntityListContents(updatedEntityListContents);

		hibernateDAO.update(listToUpdate);
		flushAndClear();

		EntityList listRetrieved = hibernateDAO.retrieve(EntityList.class, testId1);

		assertEquals(updatedName, listRetrieved.getName());
		assertEquals(updatedLastUpdatedBy, listRetrieved.getLastUpdatedBy());
		assertNotNull(updatedLastUpdateDate);
		assertEquals(updatedEntityType, listRetrieved.getEntityType());


		for (EntityListContent elc : listRetrieved.getEntityListContents())
		{
			assertEquals(updatedEntityId, elc.getEntityId());
		}
		flushAndClear();
	}
	
	/**
	 * 
	 */
	@Test()
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void updateEntityListContents()
	{
		Long testId1 =1L;
		Long testId2 = 2L;
		EntityList updateList = hibernateDAO.retrieve(EntityList.class, testId1);
		EntityListContent contentToUpdate = hibernateDAO.retrieve(EntityListContent.class, testId2);

		Set<EntityListContent> updatedEntityListContents = new HashSet<EntityListContent>(0);
		updatedEntityListContents.add(testEntityListContent);
		updatedEntityListContents.add(contentToUpdate);
		int size = updatedEntityListContents.size();
		if(size==2)
		{
			System.out.println("Success");
		}
		else
		{
			fail("The size of the EntityListContents was supposed to be 2 but was " + size);
		}
	}
	
	/**
	 * Creates a set of EntityListContent it then adds some content into the contents set and finally removes it 
	 * if statement checks if the EntityListContents set is empty, if so its a pass, else its a fail.
	 */
	@Test()
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void removeEntityListContentFromEntityList()
	{
		Long testId = 1L;
		EntityList testEntityList = hibernateDAO.retrieve(EntityList.class, testId);
		
		Set<EntityListContent> entityListContents = testEntityList.getEntityListContents();
		EntityListContent elc1 = hibernateDAO.retrieve(EntityListContent.class, testId);
		entityListContents.remove(elc1);
		
		testEntityList.setEntityListContents(entityListContents);
		
		hibernateDAO.update(testEntityList);
		flushAndClear();
		
		List<EntityListContent> entityListContentList = hibernateDAO.retrieveAll(EntityListContent.class);
		
		assertEquals(1,entityListContentList.size());
		
	}

	/**
	 * this test is expected to come back with ComparisonFailure as you are
	 * unable to
	 * update the fields below so the XML data and the data set below shouldn't
	 * match.
	 */
	@Test(expected = ComparisonFailure.class)
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void failUpdateEntityList()
	{
		Long testId = 1L;
		EntityList listToUpdate = hibernateDAO.retrieve(EntityList.class, testId);

		String createdBy = "Joe";
		Date createdDate = new Date(22, 10, 05);
		Integer tenantId = 8;
		//Integer versionNumber = 8;

		listToUpdate.setCreatedBy(createdBy);
		listToUpdate.setCreatedDate(createdDate);
		listToUpdate.setTenantId(tenantId);
		//listToUpdate.setVersionNumber(versionNumber);

		hibernateDAO.update(listToUpdate);
		flushAndClear();
		EntityList listRetrieved = hibernateDAO.retrieve(EntityList.class, testId);

		assertEquals(createdBy, listRetrieved.getCreatedBy());
		assertEquals(createdDate, listRetrieved.getCreatedDate());
		assertEquals(tenantId, listRetrieved.getTenantId());
		//assertEquals(versionNumber, listRetrieved.getVersionNumber());
		flushAndClear();
	}

	/**
	 * simply create the object then delete the object, try to retrieve once its
	 * been deleted and
	 * ObjectNotFoundException should be thrown as there is nothing to retrieve.
	 */
	@Test(expected = ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void deleteEntityList()
	{
		EntityList listToDelete = hibernateDAO.retrieve(EntityList.class, TEST_LIST_ONE_ID);

		hibernateDAO.delete(listToDelete);
		flushAndClear();

		// This should throw the exception ObjectNotFoundException. make sure
		// its the expected outcome
		EntityList retrieveLists = hibernateDAO.retrieve(EntityList.class, TEST_LIST_ONE_ID);
		// Because of lazy loading, must do something with the retrieved user to
		// trigger the load
		System.out.println("END:" + retrieveLists);
	}

	/**
	 * Test to check that update when the version number is changed should throw
	 * a stale state exception
	 */
	@Test(expected = StaleStateException.class)
	@DatabaseSetup("classpath:dbunit/entityListTestData.xml")
	public void staleStateExceptionEntityListTest()
	{
		EntityList listFirstRetrieved = hibernateDAO.retrieve(EntityList.class, TEST_LIST_ONE_ID);
		if (null == listFirstRetrieved)
			throw new RuntimeException("Test data not setup correctly. Failed to retrieve list for id: "
					+ TEST_LIST_ONE_ID);
		flushAndClear();

		EntityList listSecondRetrieved = hibernateDAO.retrieve(EntityList.class, TEST_LIST_ONE_ID);
		flushAndClear();

		listFirstRetrieved.setName("Bob");
		hibernateDAO.update(listFirstRetrieved);
		flushAndClear();

		// Should get an error as the first update should have incremented (by
		// hibernate) the version number
		listSecondRetrieved.setName("Dave");
		hibernateDAO.update(listSecondRetrieved);
		flushAndClear();
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
