/**
 * 
 */
package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import java.util.Date;

import org.hamcrest.Matchers;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.SessionFactory;
import org.junit.Before;
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

import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.AssetInsuranceType;
import com.xioq.dasacumen.model.common.InsurancePolicy;
import com.xioq.dasacumen.model.common.InsuranceTypePolicyLink;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
		DependencyInjectionTestExecutionListener.class,
		TransactionDbUnitTestExecutionListener.class })
/**
 * Tests that the asset Insurance Type linking table is working correctly 
 * @author echhung
 *
 */
public class AssetInsuranceTypeHibernateTest 
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
		
		// asset insurance type specific fields.
		private static final Long insuranceTypeId = 1L;
		private static final Long assetId = 1L;
		// From the DB Unit scripts
		private static final Long insuranceTypeIdWhichHasPolicy = 2l;
		private static final Long insurancePolicyId = 200L;
		private static final Long unlinkedInsurancePolicyId = 201L;
		
		private static final Boolean mandatory = true;

		// Audit fields
		private static final Integer tenantId = 1;
		private static final String createdBy = "JamesWesker@jla.co.uk";
		private static final Date createdDate = new Date(100, 12, 04);
		private static final String lastUpdatedBy = "chrisredfield@jla.co.uk";
		private static final Date lastUpdatedDate = new Date(120, 12, 04);
		
		// Should only be used for retrieving as these values are set by db
		private static final Long id = 1L;
		private static final Integer versionNumber = 0;
		
		/**
		 * AssetInsuranceType used for testing.
		 */
		AssetInsuranceType testAssetInsuranceType ;
		
		/**
		 * Sets up a test Insurance Policy.
		 */
		@Before
		public void setup() {
			testAssetInsuranceType = new AssetInsuranceType();
			
			// Configure AssetInsuranceType- version number and id are set by database
			testAssetInsuranceType.setMandatory(mandatory);
			
			testAssetInsuranceType.setTenantId(tenantId);
			testAssetInsuranceType.setCreatedBy(createdBy);
			testAssetInsuranceType.setCreatedDate(createdDate);
			testAssetInsuranceType.setLastUpdatedBy(lastUpdatedBy);
			testAssetInsuranceType.setLastUpdatedDate(lastUpdatedDate);
			
			// resets the sequences so that Ids should be consistent every time a test runs.
			testUtil.resetSequences("asset_insurance_types");
			testUtil.resetSequences("insurance_policies");
			testUtil.resetSequences("assets");
			testUtil.resetSequences("parties");
			testUtil.resetSequences("user_data");
			testUtil.resetSequences("insurance_type_policy_links");
		}

		@Test
		// TODO could probably use one general file for the create test since many of them need a, asset, party and user data,
		// thus we can use the asset test data here.
	    @DatabaseSetup("classpath:dbunit/assetTestData.xml")
		@ExpectedDatabase(value = "classpath:dbunit/expectedTestDataAssetInsuranceType.xml", table = "acumen.asset_insurance_types", assertionMode = DatabaseAssertionMode.NON_STRICT)
		public void createAssetInsuranceTypeTest() {
			// Needed because prereq entity are only initialised in test thus they need to be set here.
			testAssetInsuranceType.setAsset(hibernateDAO.retrieve(Assets.class, assetId));
			testAssetInsuranceType.setInsuranceTypeId(hibernateDAO.retrieve(UserData.class, insuranceTypeId).getId());
			
			hibernateDAO.create(testAssetInsuranceType);
			flushAndClear();
			
			AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
			
			assertEquals(insuranceTypeId, (Long)retrievedAssetInsuranceType.getInsuranceType().getId());
			assertEquals(assetId, retrievedAssetInsuranceType.getAsset().getId());
			assertEquals(mandatory, retrievedAssetInsuranceType.getMandatory());

			assertEquals(tenantId, retrievedAssetInsuranceType.getTenantId());
			assertNotNull(retrievedAssetInsuranceType.getCreatedBy());
			assertNotNull(retrievedAssetInsuranceType.getCreatedDate());
			assertNotNull(retrievedAssetInsuranceType.getLastUpdatedBy());
			assertNotNull(retrievedAssetInsuranceType.getLastUpdatedDate());
			// Version number should be zero - ie new
			assertNotNull(retrievedAssetInsuranceType.getVersionNumber());
			assertEquals(0, retrievedAssetInsuranceType.getVersionNumber().intValue());
		}
		
		@Test
		@DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
		@ExpectedDatabase(value = "classpath:dbunit/testDataWithTestAssetInsuranceType.xml", table = "acumen.asset_insurance_types", assertionMode = DatabaseAssertionMode.NON_STRICT)
		public void retrieveAssetInsuranceTypeTest() {
			AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
			
			// Assert values are stored correctly
			assertEquals(insuranceTypeId, (Long)retrievedAssetInsuranceType.getInsuranceType().getId());
			assertEquals(assetId, retrievedAssetInsuranceType.getAsset().getId());
			assertEquals(mandatory, retrievedAssetInsuranceType.getMandatory());

			assertEquals(tenantId, retrievedAssetInsuranceType.getTenantId());
			assertEquals(createdBy, retrievedAssetInsuranceType.getCreatedBy());
			assertEquals(createdDate, retrievedAssetInsuranceType.getCreatedDate());
			assertEquals(lastUpdatedBy, retrievedAssetInsuranceType.getLastUpdatedBy());
			assertEquals(lastUpdatedDate, retrievedAssetInsuranceType.getLastUpdatedDate());
			assertEquals(versionNumber, retrievedAssetInsuranceType.getVersionNumber());
		}
		
		@Test
	    @DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
		public void updateAssetInsuranceTypeTest() {
			// Updated fields
			UserData updatedInsuranceType = hibernateDAO.retrieve(UserData.class, 2L);
			Assets updatedAsset = hibernateDAO.retrieve(Assets.class, 2L);
			
			Boolean updatedMandatory= false;
		
			//Retrieve the AssetInsuranceType
			AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
			Date previousUpdatedDate = retrievedAssetInsuranceType.getLastUpdatedDate();
			
			// Set the new updated fields
			retrievedAssetInsuranceType.setInsuranceTypeId(updatedInsuranceType.getId());
			retrievedAssetInsuranceType.setAsset(updatedAsset);
			retrievedAssetInsuranceType.setMandatory(updatedMandatory);

			// Do the update
			hibernateDAO.update(retrievedAssetInsuranceType);
			flushAndClear();
			
			// retrieve the existing AssetInsuranceType
			AssetInsuranceType retrievedAssetInsuranceType2 = hibernateDAO.retrieve(AssetInsuranceType.class, retrievedAssetInsuranceType.getId());
			
			// Assert values are stored correctly
			assertEquals(updatedInsuranceType.getId(), retrievedAssetInsuranceType2.getInsuranceType().getId());
			assertEquals(updatedAsset.getId(), retrievedAssetInsuranceType2.getAsset().getId());
			assertEquals(updatedMandatory, retrievedAssetInsuranceType2.getMandatory());

			assertNotNull(retrievedAssetInsuranceType2.getLastUpdatedBy());
			assertNotNull(retrievedAssetInsuranceType2.getLastUpdatedDate());
			assertNotEquals(previousUpdatedDate, retrievedAssetInsuranceType2.getLastUpdatedDate());
		}
		
		@Test
		@DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
		public void failToUpdateNonUpdatableAssetInsuranceTypeFields() {
			// Updated fields
			Integer UpdatedTenantId  = 102;
			String UpdatedCreatedBy = "darthVader@jla.co.uk";
			Date UpdatedCreatedDate = new Date(22, 10, 21);
			
			//Retrieve the asset to updat.e
			AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
			
			// Set asset with new fields.
			retrievedAssetInsuranceType.setTenantId(UpdatedTenantId);
			retrievedAssetInsuranceType.setCreatedBy(UpdatedCreatedBy);
			retrievedAssetInsuranceType.setCreatedDate(UpdatedCreatedDate);
			
			// Update the draft
			hibernateDAO.update(retrievedAssetInsuranceType);
			flushAndClear();
			
			// Retrieve it again
			AssetInsuranceType retrievedAssetInsuranceType2 = hibernateDAO.retrieve(AssetInsuranceType.class, retrievedAssetInsuranceType.getId());
			
			// Asserts fields are not changed.
			assertEquals(tenantId, retrievedAssetInsuranceType2.getTenantId());
			assertEquals(createdBy, retrievedAssetInsuranceType2.getCreatedBy());
			assertEquals(createdDate, retrievedAssetInsuranceType2.getCreatedDate());
		}
		
		@Test(expected=ObjectNotFoundException.class)
	    @DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
		public void deleteAssetInsuranceTypeTest() {
			AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
			Assets asset = retrievedAssetInsuranceType.getAsset();
			asset.getAssetInsuranceTypes().remove(retrievedAssetInsuranceType);
			hibernateDAO.update(asset);
			hibernateDAO.delete(retrievedAssetInsuranceType);
			flushAndClear();
			
			// Check the asset that it has no insurance types
			Assets postDeleteAsset = hibernateDAO.retrieve(Assets.class, asset.getId());
			assertNotNull(postDeleteAsset);
			assertTrue(postDeleteAsset.getAssetInsuranceTypes().isEmpty());
			
			// Attempt to retrieve the insurance type specifically. This will cause the exception - when flushed
			hibernateDAO.retrieve(AssetInsuranceType.class, retrievedAssetInsuranceType.getId());
			flushAndClear();
		}

		/**
		 * 	Prevents false positives due to caching - must be called after DAO use
		 */
		public void flushAndClear() {
			sessionFactory.getCurrentSession().flush();
			sessionFactory.getCurrentSession().clear();
		}
		
	/**
	 * Collection test on insuranceTypePolicyLinks. Creating an Insurance Type
	 * policy link
	 * with a link should create both in the db.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
	public void createInsuranceTypePolicyLinkFromAssetInsuranceTypeTest()
	{
		AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, id);
		InsurancePolicy insurancePolicy = hibernateDAO.retrieve(InsurancePolicy.class, insurancePolicyId);
		if(!retrievedAssetInsuranceType.getInsuranceTypePolicyLinks().isEmpty())
			throw new RuntimeException("Test setup error. Insurance type already has policy linked.");
		
		retrievedAssetInsuranceType.addInsuranceTypePolicyLink(insurancePolicy);

		hibernateDAO.update(retrievedAssetInsuranceType);
		flushAndClear();

		// Check the insurance type has a policy linked and is the expected policy
		AssetInsuranceType postRemove = hibernateDAO.retrieve(AssetInsuranceType.class, id);
		assertNotNull(postRemove);
		assertNotNull(postRemove.getInsuranceTypePolicyLinks());
		assertEquals(1, postRemove.getInsuranceTypePolicyLinks().size());
		InsuranceTypePolicyLink policyLink = retrievedAssetInsuranceType.getInsuranceTypePolicyLinks().iterator().next();
		assertNotNull(policyLink.getInsurancePolicy());
		assertEquals(insurancePolicyId, policyLink.getInsurancePolicy().getId());
	}
		
	/**
	 * Adds an additional policy to an insurance type that has a policy attached already
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
	public void updateInsuranceTypePolicyAddAnotherPolicyTest()
	{
		AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, insuranceTypeIdWhichHasPolicy);
		InsurancePolicy additionalInsurancePolicy = hibernateDAO.retrieve(InsurancePolicy.class, unlinkedInsurancePolicyId);
		if(retrievedAssetInsuranceType.getInsuranceTypePolicyLinks().isEmpty())
			throw new RuntimeException("Test setup error. Insurance type does not have a policy linked.");
		
		retrievedAssetInsuranceType.addInsuranceTypePolicyLink(additionalInsurancePolicy);

		hibernateDAO.update(retrievedAssetInsuranceType);
		flushAndClear();

		// Check the insurance type has a policy linked and is the expected policy
		AssetInsuranceType postRemove = hibernateDAO.retrieve(AssetInsuranceType.class, insuranceTypeIdWhichHasPolicy);
		assertNotNull(postRemove);
		assertNotNull(postRemove.getInsuranceTypePolicyLinks());
		assertEquals(2, postRemove.getInsuranceTypePolicyLinks().size());
		
		// Check that have a policy link that has a policy of the correct id
		assertThat(postRemove.getInsuranceTypePolicyLinks(), 
				hasItem(Matchers.<InsuranceTypePolicyLink>hasProperty("insurancePolicy", hasProperty("id", equalTo(unlinkedInsurancePolicyId)))));
	}
		
	/**
	 * Collection test on insuranceTypePolicyLinks. Removing an Insurance Type policy link
	 * from the asset insurance type should remove it from the db.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataWithTestAssetInsuranceType.xml")
	public void removeInsuranceTypePolicyLinkFromAssetInsuranceTypeTest()
	{
		AssetInsuranceType retrievedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, insuranceTypeIdWhichHasPolicy);
		InsurancePolicy insurancePolicy = hibernateDAO.retrieve(InsurancePolicy.class, insurancePolicyId);
		if(retrievedAssetInsuranceType.getInsuranceTypePolicyLinks().isEmpty())
			throw new RuntimeException("Test setup error. Insurance type does not have a policy linked.");
		
		InsuranceTypePolicyLink policyLink = retrievedAssetInsuranceType.getInsuranceTypePolicyLinks().iterator().next();

		// Remove the policy from the insurance type
		retrievedAssetInsuranceType.getInsuranceTypePolicyLinks().remove(policyLink);
		hibernateDAO.update(retrievedAssetInsuranceType);
		flushAndClear();

		// Check the asset that it has no insurance types
		AssetInsuranceType postRemove = hibernateDAO.retrieve(AssetInsuranceType.class, insuranceTypeIdWhichHasPolicy);
		assertNotNull(postRemove);
		assertTrue(postRemove.getInsuranceTypePolicyLinks().isEmpty());

		// The policy should still be there.
		insurancePolicy = hibernateDAO.retrieve(InsurancePolicy.class, insurancePolicyId);
		assertNotNull(insurancePolicy);
		flushAndClear();
	}
}
