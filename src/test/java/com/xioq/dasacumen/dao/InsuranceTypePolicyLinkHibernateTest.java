package com.xioq.dasacumen.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Date;

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

import com.xioq.dasacumen.model.common.AssetInsuranceType;
import com.xioq.dasacumen.model.common.InsurancePolicy;
import com.xioq.dasacumen.model.common.InsuranceTypePolicyLink;
import com.xioq.dasacumen.test.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@WebAppConfiguration
@ContextConfiguration("classpath:application-context-test.xml")
@TestExecutionListeners({ 
	DependencyInjectionTestExecutionListener.class,
	TransactionDbUnitTestExecutionListener.class })
/**
 * Tests that the linking table between asset insurance types and insurance policies is working correctly
 * @author echhung
 *
 */

public class InsuranceTypePolicyLinkHibernateTest {
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

	private static final Long insurancePolicyId = 45L;
	private static final Long assetInsuranceTypeId = 66L;
	private static final Long existingPolicyLink = 15L;
	
	private static final Long unlinkedInsurancePolicyId = 333L;
	private static final Long assetInsuranceTypeWithNoPolicyId = 23L;

	// Audit fields
	private static final Integer tenantId = 1;
	private static final String createdBy = "JamesWesker@jla.co.uk";
	private static final Date createdDate = new Date(100, 12, 04);
	private static final String lastUpdatedBy = "chrisredfield@jla.co.uk";
	private static final Date lastUpdatedDate = new Date(120, 12, 04);

	/**
	 * Sets up a test Type Policy.
	 */
	@Before
	public void setup() 
	{
		// resets the sequences so that Ids should be consistent every time a test runs.
		testUtil.resetSequences("asset_insurance_types");
		testUtil.resetSequences("insurance_type_policy_links");
		testUtil.resetSequences("parties");
		testUtil.resetSequences("user_data");
	}

	/**
	 * Tests creating an insurance policy link.
	 */
	@Test
	@DatabaseSetup({"classpath:dbunit/testDataInsuranceTypePolicyLink_setup.xml"})
	@ExpectedDatabase(value = "classpath:dbunit/testDataInsuranceTypePolicyLink_expected.xml", table = "acumen.insurance_type_policy_links", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void createTypePolicyLinkTest() 
	{
		testUtil.resetSequences("insurance_type_policy_links");
		InsuranceTypePolicyLink testTypePolicy = new InsuranceTypePolicyLink();
		testTypePolicy.setTenantId(tenantId);

		testTypePolicy.setInsurancePolicy(hibernateDAO.retrieve(InsurancePolicy.class, unlinkedInsurancePolicyId));
		testTypePolicy.setAssetInsuranceType(hibernateDAO.retrieve(AssetInsuranceType.class, assetInsuranceTypeWithNoPolicyId));

		hibernateDAO.create(testTypePolicy);
		flushAndClear();

		InsuranceTypePolicyLink retrievedTypePolicy = hibernateDAO.retrieve(InsuranceTypePolicyLink.class, testTypePolicy.getId());
		assertNotNull(retrievedTypePolicy);
		assertEquals(unlinkedInsurancePolicyId, retrievedTypePolicy.getInsurancePolicy().getId());
		assertEquals(assetInsuranceTypeWithNoPolicyId, retrievedTypePolicy.getAssetInsuranceType().getId());

		assertEquals(tenantId, retrievedTypePolicy.getTenantId());
		assertNotNull(retrievedTypePolicy.getCreatedBy());
		assertNotNull(retrievedTypePolicy.getCreatedDate());
		assertNotNull(retrievedTypePolicy.getLastUpdatedBy());
		assertNotNull(retrievedTypePolicy.getLastUpdatedDate());
		assertNotNull(retrievedTypePolicy.getVersionNumber());
		assertEquals(0, retrievedTypePolicy.getVersionNumber().intValue());
	}

	/**
	 * Tests retrieving a specific insurance policy link. 
	 * Note that the expected DB is the same as the setup, as the retrieve should make no difference
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataInsuranceTypePolicyLink_setup.xml")
	public void retrieveTypePolicyLinkTest() {
		InsuranceTypePolicyLink retrievedTypePolicy = hibernateDAO.retrieve(InsuranceTypePolicyLink.class, existingPolicyLink);

		// Assert values are stored correctly
		assertEquals(insurancePolicyId, retrievedTypePolicy.getInsurancePolicy().getId());
		assertEquals(assetInsuranceTypeId, retrievedTypePolicy.getAssetInsuranceType().getId());

		assertEquals(tenantId, retrievedTypePolicy.getTenantId());
		assertEquals(createdBy, retrievedTypePolicy.getCreatedBy());
		assertEquals(createdDate, retrievedTypePolicy.getCreatedDate());
		assertEquals(lastUpdatedBy, retrievedTypePolicy.getLastUpdatedBy());
		assertEquals(lastUpdatedDate, retrievedTypePolicy.getLastUpdatedDate());
	}

	/**
	 * Update test isn't really valid but here for completion. As insurance policy links are created and deleted not amended.
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataInsuranceTypePolicyLink_setup.xml")
	public void updateTypePolicyTest() {
		// Updated fields
		InsurancePolicy updatedInsurancePolicy = hibernateDAO.retrieve(InsurancePolicy.class, unlinkedInsurancePolicyId);
		AssetInsuranceType updatedAssetInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, assetInsuranceTypeWithNoPolicyId);

		//Retrieve the AssetInsuranceType
		InsuranceTypePolicyLink retrievedTypePolicyLink = hibernateDAO.retrieve(InsuranceTypePolicyLink.class, existingPolicyLink);

		// Set the new updated fields
		retrievedTypePolicyLink.setAssetInsuranceType(updatedAssetInsuranceType);
		retrievedTypePolicyLink.setInsurancePolicy(updatedInsurancePolicy);

		// Do the update
		hibernateDAO.update(retrievedTypePolicyLink);
		flushAndClear();

		// retrieve the existing AssetInsuranceType
		InsuranceTypePolicyLink retrievedTypePolicy2 = hibernateDAO.retrieve(InsuranceTypePolicyLink.class, retrievedTypePolicyLink.getId());

		// Assert values are stored correctly
		assertEquals(unlinkedInsurancePolicyId, retrievedTypePolicy2.getInsurancePolicy().getId());
		assertEquals(assetInsuranceTypeWithNoPolicyId, retrievedTypePolicy2.getAssetInsuranceType().getId());
	}
	
	/**
	 * Tests trying to update fields that can't be updated. This tests that the hibernate mapping is set correctly
	 */
	@Test
	@DatabaseSetup("classpath:dbunit/testDataInsuranceTypePolicyLink_setup.xml")
	public void failToUpdateNonUpdatableTypePolicyFields() {
		// Updated fields
		Integer UpdatedTenantId  = 202;
		String UpdatedCreatedBy = "darthVader@jla.co.uk";
		Date UpdatedCreatedDate = new Date(22, 10, 21);
		String  UpdatedUpdatedBy = "darthVader2@jla.co.uk";
		Date UpdatedUpdatedDate = new Date(24, 10, 21);
		
		//Retrieve the asset to updat.e
		InsuranceTypePolicyLink retrievedTypePolicy = hibernateDAO.retrieve(InsuranceTypePolicyLink.class, existingPolicyLink);
		
		// Set asset with new fields.
		retrievedTypePolicy.setTenantId(UpdatedTenantId);
		retrievedTypePolicy.setCreatedBy(UpdatedCreatedBy);
		retrievedTypePolicy.setCreatedDate(UpdatedCreatedDate);
		
		// Update the draft
		hibernateDAO.update(retrievedTypePolicy);
		flushAndClear();
		
		// Retrieve it again
		InsuranceTypePolicyLink retrievedTypePolicy2 = hibernateDAO.retrieve(InsuranceTypePolicyLink.class, existingPolicyLink);
		
		// Asserts fields are not changed.
		assertEquals(tenantId, retrievedTypePolicy2.getTenantId());
		assertEquals(createdBy, retrievedTypePolicy2.getCreatedBy());
		assertEquals(createdDate, retrievedTypePolicy2.getCreatedDate());
	}

	/** 
	 * Test deletion of an insurance policy link. To do this, the link needs to be removed from the insurance type
	 */
	@Test(expected=ObjectNotFoundException.class)
	@DatabaseSetup("classpath:dbunit/testDataInsuranceTypePolicyLink_setup.xml")
	public void deleteTypePolicyTest() {
		InsuranceTypePolicyLink retrievedTypePolicy = hibernateDAO.retrieve(InsuranceTypePolicyLink.class, existingPolicyLink);
		AssetInsuranceType assetInsuranceType = retrievedTypePolicy.getAssetInsuranceType();
		
		assetInsuranceType.getInsuranceTypePolicyLinks().remove(retrievedTypePolicy);
		hibernateDAO.delete(retrievedTypePolicy);
		flushAndClear();

		AssetInsuranceType postDeleteInsuranceType = hibernateDAO.retrieve(AssetInsuranceType.class, assetInsuranceType.getId());
		assertNotNull(postDeleteInsuranceType);
		assertNotNull(postDeleteInsuranceType.getInsuranceTypePolicyLinks());
		assertTrue(postDeleteInsuranceType.getInsuranceTypePolicyLinks().isEmpty());
		
		// the policy link should no longer exist. Flush to force retrieve and exception
		hibernateDAO.retrieve(InsuranceTypePolicyLink.class, retrievedTypePolicy.getId());
		flushAndClear();
	}
	
	/**
	 * 	Prevents false positives due to caching - must be called after DAO use
	 */
	public void flushAndClear() {
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().clear();
	}
}
