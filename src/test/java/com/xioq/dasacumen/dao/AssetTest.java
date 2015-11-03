package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xioq.dasacumen.model.assetregister.Assets;

/**
 * 
 * @author Jakub Wawszczyk
 *
 */
public class AssetTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * If value set is null, setAssetNumberAsArray should create empty string array[4]
	 */
	@Test
	public void setAssetNumberAsArrayTest(){
		 Assets Asset = new Assets();
		 
		 String[] testArr = new String[4];
		 String[] testArrSet = null;
		 Asset.setAssetNumberAsArray(testArrSet);
		 assertArrayEquals(testArr, Asset.getAssetNumberAsArray());
	}
	/*
	 * If set array length is lower than 4, setAssetNumberAsArray should create empty string array[4]
	 */
	@Test
	public void setAssetNumberAsArrayShortArrayTest(){
		Assets Asset = new Assets();
		
		String[] testArr = new String[4];
		String[] testArrSet = new String[3];
		Asset.setAssetNumberAsArray(testArrSet);
		assertArrayEquals(testArr, Asset.getAssetNumberAsArray());
	}
	
	
}
