package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.StaleStateException;
import org.junit.Before;
import org.junit.Test;

import com.xioq.dasacumen.model.assetregister.AssetParts;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.test.CrudTests;
import com.xioq.dasacumen.test.DatabaseTable;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

import com.github.springtestdbunit.annotation.DatabaseSetup;


/**
 * Tests that the asset parts table works with Crud operations
 * @author echhung
 *
 */
public class AssetPartsHibernateTest extends HibernateDAOTestBase implements CrudTests {

	private static final Long DBUNIT_ASSETPART_ID200 = 200L;
	private static final Long DBUNIT_PARENT_ASSET_ID50 = 50L;
	private static final Long DBUNIT_CHILD_ASSET_ID51 = 51L;
	private static final Long DBUNIT_UPDATED_ASSET_ID53 = 53L;
	
	@Before
	public void setup ()
	{
		testUtil.resetSequences(DatabaseTable.USER_DATA);
		testUtil.resetSequences(DatabaseTable.PARTIES);
		testUtil.resetSequences(DatabaseTable.ASSETS);
		testUtil.resetSequences(DatabaseTable.ASSET_PARTS);
	}

	@Test
    @DatabaseSetup(value={	"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
    						"classpath:dbunit/parties/suppliersTestData.xml",
    						"classpath:dbunit/asset/assetsTestData.xml", 
    						"classpath:dbunit/asset/assetPartsTestData.xml"})
	public void retrieveTest() {
		
		AssetParts assetPartsRetrieved = hibernateDAO.retrieve(AssetParts.class, DBUNIT_ASSETPART_ID200);
		flushAndClear();
		
		assertEquals(DBUNIT_CHILD_ASSET_ID51, assetPartsRetrieved.getChildAsset().getId());
		assertEquals(DBUNIT_PARENT_ASSET_ID50, assetPartsRetrieved.getParentAsset().getId());
	}
	
	@Test
    @DatabaseSetup(value={	"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
							"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/assetsTestData.xml"})
	public void createTest()
	{
		AssetParts testAssetParts = new AssetParts();
		testAssetParts.setChildAsset(hibernateDAO.retrieve(Assets.class, DBUNIT_CHILD_ASSET_ID51));
		testAssetParts.setParentAsset(hibernateDAO.retrieve(Assets.class, DBUNIT_PARENT_ASSET_ID50));

		hibernateDAO.create(testAssetParts);
		flushAndClear();
		
		AssetParts assetPartsRetrieved = hibernateDAO.retrieve(AssetParts.class, testAssetParts.getId());
		flushAndClear();
		
		assertEquals(DBUNIT_CHILD_ASSET_ID51, assetPartsRetrieved.getChildAsset().getId());
		assertEquals(DBUNIT_PARENT_ASSET_ID50, assetPartsRetrieved.getParentAsset().getId());
		assertAuditFields(testAssetParts);
	}
	
	@Test
    @DatabaseSetup(value={	"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
							"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/assetsTestData.xml", 
							"classpath:dbunit/asset/assetPartsTestData.xml"})
	public void updateTest()
	{
		AssetParts assetPartsToUpdate = hibernateDAO.retrieve(AssetParts.class, DBUNIT_ASSETPART_ID200);
		
		//Update AssetParts
		assetPartsToUpdate.setChildAsset(hibernateDAO.retrieve(Assets.class, DBUNIT_UPDATED_ASSET_ID53));
		
		hibernateDAO.update(assetPartsToUpdate);
		flushAndClear();
		
		AssetParts assetPartsRetrieved = hibernateDAO.retrieve(AssetParts.class, assetPartsToUpdate.getId());
		
		assertEquals(DBUNIT_UPDATED_ASSET_ID53, assetPartsRetrieved.getChildAsset().getId());
		assertEquals(DBUNIT_PARENT_ASSET_ID50, assetPartsRetrieved.getParentAsset().getId());
		assertAuditFields(assetPartsRetrieved);
	}
	
	@Test (expected=ObjectNotFoundException.class)
    @DatabaseSetup(value={	"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
							"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/assetsTestData.xml", 
							"classpath:dbunit/asset/assetPartsTestData.xml"})
	public void deleteTest()
	{
		AssetParts assetPartsToDelete = hibernateDAO.retrieve(AssetParts.class, DBUNIT_ASSETPART_ID200);
		
		hibernateDAO.delete(assetPartsToDelete);
		flushAndClear();
		
		AssetParts retrievedAssetParts = hibernateDAO.retrieve(AssetParts.class, assetPartsToDelete.getId());
		
		/* Include this comment in all delete tests using this method */
		// Because of lazy loading, must do something with the retrieved address to trigger the load 
		// to throw the exception, thus the printline.
		System.out.println("Retrieved AssetParts: " + retrievedAssetParts);
		
	}
	
    @Test()
    @DatabaseSetup(value={	"classpath:dbunit/systemAdmin/userDataOneOfEachTestData.xml",
							"classpath:dbunit/parties/suppliersTestData.xml",
							"classpath:dbunit/asset/assetsTestData.xml", 
							"classpath:dbunit/asset/assetPartsTestData.xml"})
	public void staleStateExceptionTest()
	{
		// Both users retrieve version 0
		AssetParts assetPartRetrieved1 = hibernateDAO.retrieve(AssetParts.class, DBUNIT_ASSETPART_ID200);
		flushAndClear();
		AssetParts assetPartRetrieved2 = hibernateDAO.retrieve(AssetParts.class, DBUNIT_ASSETPART_ID200);
		flushAndClear();
		
		// only update one field - not an audit field though !
		assetPartRetrieved1.setChildAsset(hibernateDAO.retrieve(Assets.class, DBUNIT_UPDATED_ASSET_ID53));
		
		hibernateDAO.update(assetPartRetrieved1);
		flushAndClear(); 
		assertEquals("Version number not incremented", 1, assetPartRetrieved1.getVersionNumber().intValue());
		
		// 2nd user has old data (version number = 0) and we make sure just by setting it again.
		assetPartRetrieved2.setChildAsset(hibernateDAO.retrieve(Assets.class, DBUNIT_CHILD_ASSET_ID51));
		// and tries to update
		try
		{
			hibernateDAO.update(assetPartRetrieved2);
			flushAndClear();
			
			fail("No Exception Thrown");
		}
		catch(StaleStateException e)
		{
			assertEquals("Version number incremented", 1, assetPartRetrieved2.getVersionNumber().intValue());
		}
		catch(Exception e)
		{
			fail("An Unexpected Exception was thown: " + e);
		}
	}

}
