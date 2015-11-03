package com.xioq.dasacumen.model.constants;

/**
 * User data type constants which allows for constant IDs to be stored which can be referenced by Enum names.
 * 
 * @author mwaude
 */
public enum UserDataType {

	/** Statuses - The status of the asset */
	ASSET_STATUSES(1L),
	/** Categories - The asset category */
	ASSET_CATEGORIES(2L),
	/** Country */
	COUNTRY(3L),
	/** Company */
	COMPANY(4L),
	/** Division */
	DIVISION(5L),
	/** Site */
	SITE(6L),
	/** Department */
	DEPARTMENT(7L),
	/** Location */
	LOCATION(8L),
	/** Asset part numbers */
	ASSET_NUMBER_PART_1(9L),
	/** Asset part numbers */
	ASSET_NUMBER_PART_2(10L),
	/** Asset part numbers */
	ASSET_NUMBER_PART_3(11L),
	/** The lease type - Asset Register > tenure > VAT Codes */
	LEASE_TYPE(12L),
	/** The charge period - Asset Register > tenure > VAT Codes */
	CHARGE_PERIOD(13L),
	/** Asset Register > finance > VAT Codes */
	VAT_CODE(14L),
	/** Asset Register > Indemnities > Insurance Types */
	INSURANCE_TYPE(15L),
	/** Asset Register > finance > VAT Codes */
	PURCHASE_LEAD_TIMES(16L),
	/** Asset Register > Indemnities > Warranty Types */
	WARRANTY_TYPE(17L),
	/** Asset Register > finance > VAT Codes */
	DEPRECIATION_CODE(18L),
	/** Asset Names from landing register - Asset Names*/
	ASSET_NAMES(19L),
	/** The status of the supplier - Financial > create supplier > status */
	SUPPLIER_STATUSES(20L),
	/** The status of the supplier - Financial > create supplier > categories */
	SUPPLIER_CATEGORIES(21L),
	/** The rating of the websites - Financial > create supplier > ratings */
	WEBSITE_RATING(22L),
	/** The currencies accepted - Financial > create supplier > currency accepted */
	ACCEPTED_CURRENCIES(23L),
	/** The terms agreed - Financial > create supplier > terms agreed */
	AGREED_TERMS(24L),
	/** The spend limit - Financial > create supplier > spend limit */
	SPEND_LIMIT(25L),
	/** The country of the supplier - Financial > create supplier > country */
	BANK_ADDRESS(27L),
	/** Accreditations of the supplier - Financial > create supplier > accreditations */
	SUPPLIER_ACCREDITATIONS(28L),
	/** The country of the supplier - Financial > create supplier > country*/
	SUPPLIER_COUNTRY(29L),
	/** Other system name - Asset Register > General > Other System Names */
	OTHER_SYSTEM_NAME(30L),
	/** Other options - Asset Register > tenure > Optional Extras*/
	OPTIONAL_EXTRA(31L),
	/** Invoicing schedule - Sales > Create proposal >Invoicing schedule */
	INVOICING_SCHEDULE(32L),
	/** Industry - Asset Register > Asset > Maintenance > Industry */
	INDUSTRY(33L),
	/** Industry - Asset Register > Asset > Maintenance > Core Skills */
	CORE_SKILLS(34L),
	/** Industry - Asset Register > Asset > Maintenance > Qualifications */
	QUALIFICATIONS(35L),
	/** Industry - Asset Register > Asset > Maintenance > Discipline */
	DISCIPLINE(36L),
	/** Financial > supplier > addressType */
	ADDRESS_TYPE(37L),
	/** Type of an email - Financial > create supplier > Email type */
	EMAIL_TYPE(38L),
	/** Type of a telephone number - Financial > create supplier > Telephone number type */
	TELEPHONE_NUMBER_TYPE(39L);

	private Long id;

	private UserDataType(Long id) {
		this.id = id;
	}

	/**
	 * Returns the ID of the UserDataType constant
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}
}