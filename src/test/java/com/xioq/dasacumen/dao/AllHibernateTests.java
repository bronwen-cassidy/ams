package com.xioq.dasacumen.dao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	AddressesHibernateTest.class, 
	AssetHibernateTest.class,
	AssetInsuranceTypeHibernateTest.class, 
	AssetPartsHibernateTest.class,
	AssetWithInsuranceTypeHibernateTest.class,
	AssetWithLeaseInHibernateTest.class,
	AssetWithLeaseOutHibernateTest.class,
	AssetWithWarrantiesTest.class, 
	CompanyHibernateTest.class,
	DocHibernateTest.class,
	DocLinkHibernateTest.class,
	DraftsHibernateTest.class,
	EntityListHibernateTest.class,
	GroupsHibernateTest.class,
	InsurancePolicyHibernateTest.class,
	InsuranceTypePolicyLinkHibernateTest.class,
	InternalResourcesHibernateTest.class,
	MaintenanceHibernateTest.class,
	OtherSystemRefHibernateTest.class,
	PartyAddressHibernateTest.class,
	PartyTelephoneNumbersHibernateTest.class,
	PersonHibernateTest.class,
	SysInfoHibernateTest.class,
	TelephoneNumberHibernateTest.class,
	TenantHibernateTest.class,
	LeaseInHibernateTest.class,
	LeaseOutHibernateTest.class,
	UserDataCatHibernateTest.class,
	UserDataHibernateTest.class,
	UserDataSetsHibernateTest.class,
	UserDataTypesHibernateTest.class,
	UserWithAuthoritiesHibernateTest.class,
	WarrantyHibernateTest.class,
	WebsiteHibernateTest.class,
	AssetWithScheduledDataHibernateTest.class,
	AssetScheduleHibernateTest.class,
	AssetProposalHibernateTest.class })

public class AllHibernateTests {

}
