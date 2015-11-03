package com.xioq.dasacumen.web;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Date;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.xioq.dasacumen.dao.CRUDHibernateDAO;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.LeaseIn;
import com.xioq.dasacumen.model.common.LeaseOut;
import com.xioq.dasacumen.test.TestUtil;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class, })
// Can be used for debugging - this will allow the objects to be persisted to db.
//@TransactionConfiguration(defaultRollback = false)
/**
 * Checks that from the create asset controller an asset can be created with a other objects inside of it, like warranties, LeaseIn and LeaseOut.
 * @author echhung
 *
 */
public class AssetWithEntityControllerIntegrationTests {
	
	/*
	 * Creates an instance of the DAO for the CRUD operations
	 */
	@Autowired
	private CRUDHibernateDAO hibernateDAO;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private TestUtil testUtil;

	/**
	 * A mock browser to perform requests to the controllers
	 */
	private MockMvc mockMvc;
	
	// Asset specific fields
	private static final String name = "fan";
	
	// Warranty specific fields.
	private static final Date commencementDate = new Date(100,10,10);
	private static final Date expiryDate = new Date(150,10,10);
	private static final Boolean om = false;
	private static final String policyNumber = "XYZALPHA123445533";
	private static final BigDecimal cost = new BigDecimal(2000.46);
	
	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "JamesWesker@jla.co.uk";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "chrisredfield@jla.co.uk";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);
	
	// Should only be used for retrieving as these values are set by db
	private static final Long ID1_DBUNIT = 1L;

	/**
	 * Asset used for testing.
	 */
	Assets testAsset;
	
	/**
	 * LeaseIn used for testing
	 */
	LeaseIn testLeaseIn;
	
	/**
	 * LeaseOut used for testing
	 */
	LeaseOut testLeaseOut;

	/**
	 * Sets up the mock mvc in setup
	 */
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		testAsset = new Assets(name, ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,
				ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT,ID1_DBUNIT);
		
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("warranties");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
		testUtil.resetSequences("assets");
		testUtil.resetSequences("lease_in");
		testUtil.resetSequences("lease_out");
	}
	

	/**
	 * Tests create asset works correctly with a warranty from the controller.
	 */
	@Test
	@Transactional
    @DatabaseSetup("classpath:dbunit/baseTestData.xml")
	public void CreatingAssetWithWarrantyTest() throws Exception {
		// Set up warranty
//		testWarranty = new Warranty();
//		
//		// Configure Warranty - version number and id are set by database
//		testWarranty.setSupplierId(ID1_DBUNIT);
//		testWarranty.setTypeId(ID1_DBUNIT);
//		testWarranty.setCommencementDate(commencementDate);
//		testWarranty.setExpiryDate(expiryDate);
//		testWarranty.setOm(om);
//		testWarranty.setPolicyNumber(policyNumber);
//		testWarranty.setCost(cost);
//		
//		// Make an asset with a warranty and attach user data and party
//		testWarranty.setAsset(testAsset);
//		testAsset.setWarranty(testWarranty);

		// perform a post request with an asset with warranty.
		mockMvc.perform(post("/assetController/summary")
				// asset in session attribute
				.sessionAttr("asset", testAsset))
				// check if status ok 302 (redirected)
				.andExpect(status().is(302))
				// check if the redirected url is this
				.andExpect(redirectedUrl("../assetRegister"))
				.andExpect(model().hasNoErrors());
		
		// check the database entries
		assertEquals(1, hibernateDAO.retrieveAll(Assets.class).size());
//		assertEquals(1, hibernateDAO.retrieveAll(Warranty.class).size());
	}
	
	/**
	 * Tests create asset works correctly with a LeaseIn from the controller.
	 */
	@Test
	@Transactional
    @DatabaseSetup("classpath:dbunit/baseTestData.xml")
	public void CreatingAssetWithLeaseInTest() throws Exception {		
		// Set up LeaseIn and add it to asset.
		testLeaseIn = new LeaseIn();
		
		testLeaseIn.setLeaseTypeId(ID1_DBUNIT);
		testLeaseIn.setVatCodeId(ID1_DBUNIT);
		testLeaseIn.setChargePeriodId(ID1_DBUNIT);
		testLeaseIn.setLeaseCommences(commencementDate);
		testLeaseIn.setLeaseExpires(expiryDate);
		testLeaseIn.setLeaseCost(cost);
		testLeaseIn.setLeaseCharge(cost);
		testLeaseIn.setAsset(testAsset);
		testLeaseIn.setPartyId(ID1_DBUNIT);
		testLeaseIn.setPartyName("testPartyName");
		
		testAsset.setLeaseIn(testLeaseIn);
	
		// perform a post request with an asset with LeaseIn.
		mockMvc.perform(post("/assetController/summary")
				// asset in session attribute
				.sessionAttr("asset", testAsset))
				// check if status ok 302 (redirected)
				.andExpect(status().is(302))
				// check if the redirected url
				.andExpect(redirectedUrl("../assetRegister"))
				.andExpect(model().hasNoErrors());
		
		// check the database entries
		assertEquals(1, hibernateDAO.retrieveAll(Assets.class).size());
		assertEquals(1, hibernateDAO.retrieveAll(LeaseIn.class).size());
	}
	
	/**
	 * Tests create asset works correctly with a LeaseOut from the controller.
	 */
	@Test
	@Transactional
	@DatabaseSetup("classpath:dbunit/baseTestData.xml")
	public void CreatingAssetWithLeaseOutTest() throws Exception {
		// Set up LeaseIn and add it to asset.
		testLeaseOut = new LeaseOut();

		testLeaseOut.setLeaseTypeId(ID1_DBUNIT);
		testLeaseOut.setVatCodeId(ID1_DBUNIT);
		testLeaseOut.setChargePeriodId(ID1_DBUNIT);
		testLeaseOut.setLeaseCharge(cost);
		testLeaseOut.setAsset(testAsset);
		
		testAsset.setLeaseOut(testLeaseOut);
	
		// perform a post request with an asset with warranty.
		mockMvc.perform(post("/assetController/summary")
				// asset in session attribute
				.sessionAttr("asset", testAsset))
				// check if status ok 302 (redirected)
				.andExpect(status().is(302))
				// check if the redirected url
				.andExpect(redirectedUrl("../assetRegister"))
				.andExpect(model().hasNoErrors());
		
		// check the database entries
		assertEquals(1, hibernateDAO.retrieveAll(Assets.class).size());
		assertEquals(1, hibernateDAO.retrieveAll(LeaseOut.class).size());
	}
	
	/**
	 * Tests create asset works correctly with LeaseIn and LeaseOut from the controller.
	 */
	@Test
	@Transactional
	@DatabaseSetup("classpath:dbunit/baseTestData.xml")
	public void CreatingAssetWithLeaseOutAndInTest() throws Exception {
		
		// Set up Lease In and Lease out, then adds both to asset.
		testLeaseIn = new LeaseIn();
		testLeaseIn.setLeaseTypeId(ID1_DBUNIT);
		testLeaseIn.setVatCodeId(ID1_DBUNIT);
		testLeaseIn.setChargePeriodId(ID1_DBUNIT);
		testLeaseIn.setLeaseCommences(commencementDate);
		testLeaseIn.setLeaseExpires(expiryDate);
		testLeaseIn.setLeaseCost(cost);
		testLeaseIn.setLeaseCharge(cost);
		testLeaseIn.setAsset(testAsset);
		testLeaseIn.setPartyId(ID1_DBUNIT);
		testLeaseIn.setPartyName("testPartyName");
		
		testLeaseOut = new LeaseOut();
		testLeaseOut.setLeaseTypeId(ID1_DBUNIT);
		testLeaseOut.setVatCodeId(ID1_DBUNIT);
		testLeaseOut.setChargePeriodId(ID1_DBUNIT);
		testLeaseOut.setLeaseCharge(cost);
		testLeaseOut.setAsset(testAsset);
		
		testAsset.setLeaseIn(testLeaseIn);
		testAsset.setLeaseOut(testLeaseOut);
	
		// perform a post request with an asset with both LeaseIn and LeaseOut.
		mockMvc.perform(post("/assetController/summary")
				// asset in session attribute
				.sessionAttr("asset", testAsset))
				// check if status ok 302 (redirected)
				.andExpect(status().is(302))
				// check if the redirected url
				.andExpect(redirectedUrl("../assetRegister"))
				.andExpect(model().hasNoErrors());
		
		// check the database entries
		assertEquals(1, hibernateDAO.retrieveAll(Assets.class).size());
		assertEquals(1, hibernateDAO.retrieveAll(LeaseIn.class).size());
		assertEquals(1, hibernateDAO.retrieveAll(LeaseOut.class).size());
	}
}
