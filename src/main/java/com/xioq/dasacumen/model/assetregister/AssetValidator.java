package com.xioq.dasacumen.model.assetregister;

import java.util.HashSet;

import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xioq.dasacumen.lib.utilities.StringUtil;
import com.xioq.dasacumen.model.common.AssetInsuranceType;
import com.xioq.dasacumen.model.common.AssetWarranty;
import com.xioq.dasacumen.model.common.LeaseIn;

/**
 * @author Stephen Elliott
 *
 */
@Component("assetValidator")
public class AssetValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return Assets.class.isAssignableFrom(clazz);
	}
	

	/**
	 * This method will effectively initialise embedded sets within an object if they are empty.
	 * We use it to fix known spring form binding 'issues' when hibernate tries to persist
	 * empty set data.
	 *  
	 * This method will also perform validation on fields to ensure a set is only cleared when necessary.
	 * 
	 * Currently this method is only used for asset set data.
	 *  
	 * @param target - this is any object you want.
	 */
	public void initialiseEmbeddedObjectValidation(Object target, Errors errors)
	{
		if (target instanceof Assets) {
			Assets asset = (Assets) target;
			//If in create mode then initialize sets, in specific situations i.e. adding a warranty but not insurance data then init the insurance set.
			//Warranty Checks:
			if (null!=asset.getWarranty()) {
				validateWarranty(asset, errors);
			}
			
			//Insurance Checks:
			if (null!=asset.getAssetInsurance()) {
				validateInsurance(asset, errors);
			}
			
			//Lease Checks:
			if (null!=asset.getLeaseOut()) {
				
				validateLeaseOut(asset, errors);
			}
			if (null!=asset.getLeaseIn()) {
				
				validateLeaseIn(asset, errors);
			}
			
			//Maintenance checks:
			if(null != asset.getMaintenance())
			{
				validateMaintenance(asset, errors);
			}
		}
	}
	
	
	/**
	 * Perform field validation then if no fields set clear insurance set OR if any field has been entered then display any errors.
	 * 
	 * @param asset
	 * @param errors
	 */
	public void validateInsurance(Assets asset, Errors errors) {
	
		//Note field below stays FALSE if no fields have been entered & TRUE if mandatory fields have been entered.
		Boolean fieldsPresent = Boolean.FALSE; 
		Errors insuranceErrors = new BeanPropertyBindingResult(asset, "asset");
		
		if (null!=asset.getAssetInsurance()) {
		 
			//TODO XXX : add all validation message text into a new properties lookup file.
			
			//Check mandatory fields entered.
			if (null==asset.getAssetInsurance().getInsuranceTypeId()) {
				insuranceErrors.rejectValue("assetInsurance.insuranceTypeId", "assetInsurance.insuranceTypeId.mand", "INDEMNITIES : Insurance Type is a mandatory field : Please return to Indemnities tab to correct.");
			} else {
				fieldsPresent=Boolean.TRUE;
			}
			
			if (null==asset.getAssetInsurance().getInsuranceTypePolicyLink() || null==asset.getAssetInsurance().getInsuranceTypePolicyLink().getInsurancePolicy()
					|| null==asset.getAssetInsurance().getInsuranceTypePolicyLink().getInsurancePolicy().getId()) {
				insuranceErrors.rejectValue("assetInsurance.insuranceTypePolicyLink.insurancePolicy.id", "assetInsurance.insuranceTypePolicyLink.insurancePolicy.mand", "INDEMNITIES : Insurance policy is a mandatory field : Please return to Indemnities tab to correct.");
			} else {
				fieldsPresent=Boolean.TRUE;
			}
	
			//False means no fields entered so clear set!  XXX Possibly remove set differently via id as possibility of getting confused?
			if (Boolean.FALSE.equals(fieldsPresent)) {
				asset.setAssetInsuranceTypes(new HashSet<AssetInsuranceType>());
			}
			else if (null!=insuranceErrors) { //Fields are present so add any errors found....
					errors.addAllErrors(insuranceErrors);
			}
		}
	}

	/**
	 * Perform field validation then if no fields set clear warranty set OR if any field has been entered then display any errors.
	 * 
	 * @param asset
	 * @param errors
	 */
	public void validateWarranty(Assets asset, Errors errors) {
	
		//Note field below stays FALSE if no fields have been entered & TRUE if mandatory fields have been entered.
		Boolean fieldsPresent = Boolean.FALSE; 
		Errors warrantyErrors = new BeanPropertyBindingResult(asset, "asset");
		
		if (null!=asset.getWarranty()) {
		 
			//TODO XXX : add all validation message text into a new properties lookup file.
			
			//Check mandatory fields entered.
			if (null==asset.getWarranty().getWarrantyPolicyId()) {
				warrantyErrors.rejectValue("warranty.warrantyPolicyId", "warranty.warrantyPolicyId.mand", "WARRANTY : Existing policy is a mandatory field : Please return to Indemnities tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==asset.getWarranty().getAssetWarrantyTypeId()) {
				warrantyErrors.rejectValue("warranty.assetWarrantyTypeId", "warranty.assetWarrantyTypeId.mand", "WARRANTY : Warranty type a mandatory field : Please return to Indemnities tab to correct.");
			} else {
				fieldsPresent=true;
			}
			
			//False means no fields entered so clear set!
			if (Boolean.FALSE.equals(fieldsPresent)) {
				asset.setWarranties(new HashSet<AssetWarranty>());
			}
			else if (null!=warrantyErrors) { //Fields are present so add any errors found....
					errors.addAllErrors(warrantyErrors);
					asset.getWarranty().setWarrantyPolicy(null);
					asset.getWarranty().setAssetWarrantyType(null);
			}
		}
	}

	/**
	 * Method to perform validation on the required lease in fields.
	 * This method will check each required field for validation purposes, and then add it to a new error property, then before this error property
	 * is added to our main errors we check to see if any fields have been entered i.e. if they are all null then we simply clear the leaseIn set
	 * and do not add our validation errors.
	 * 
	 * @param asset
	 * @param errors
	 */
	public void validateLeaseIn(Assets asset, Errors errors) {
	
		//Note field below stays FALSE if no fields have been entered & TRUE if mandatory fields have been entered.
		boolean fieldsPresent = false; 
		Errors leaseInErrors = new BeanPropertyBindingResult(asset, "asset");
		
		LeaseIn leaseIn = asset.getLeaseIn();
		if (null!=leaseIn) {
		 
			//TODO XXX : add all validation message text into a new properties lookup file.
			
			//Check mandatory fields entered.
			if (null==leaseIn.getLeaseTypeId()) {
				leaseInErrors.rejectValue("leaseIn.leaseType.id", "leaseIn.leaseType.id.mand", "LEASE IN : Lease Type is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==leaseIn.getVatCodeId()) {
				leaseInErrors.rejectValue("leaseIn.vatCode.id", "leaseIn.vatCode.id.mand", "LEASE IN : Vat code is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==leaseIn.getLeaseCommences()) {
				leaseInErrors.rejectValue("leaseIn.leaseCommences", "leaseIn.leaseCommences.mand", "LEASE IN : Lease commences is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==leaseIn.getLeaseExpires()) {
				leaseInErrors.rejectValue("leaseIn.leaseExpires", "leaseIn.leaseExpires.mand", "LEASE IN : Lease expires is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==leaseIn.getLeaseCost()) {
				leaseInErrors.rejectValue("leaseIn.leaseCost", "leaseIn.leaseCost.mand", "LEASE IN : Lease cost is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==leaseIn.getLeaseCharge()) {
				leaseInErrors.rejectValue("leaseIn.leaseCharge", "leaseIn.leaseCharge.mand", "LEASE IN : Leased in charge is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==leaseIn.getChargePeriodId()) {
				leaseInErrors.rejectValue("leaseIn.chargePeriod.id", "leaseIn.chargePeriod.mand", "LEASE IN : Charge Period is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (StringUtil.isEmpty(leaseIn.getPartyName())) {
				leaseInErrors.rejectValue("leaseIn.partyName", "leaseIn.partyName.mand", "LEASE IN : Leased From is a mandatory field : Please return to LeaseIn tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==leaseIn.getPartyId()) {
				leaseInErrors.rejectValue("leaseIn.partyId", "leaseIn.partyId.mand", "LEASE IN : Leased From is a mandatory field : Please return to LeaseIn tab to correct.");
			} 
			else {
				fieldsPresent=true;
			}
			
			if(!fieldsPresent) // If no fields are present then set to null on the asset.
			{
				asset.setLeaseIn(null);
			}
			else
			{ //Fields are present so add any errors found....
				errors.addAllErrors(leaseInErrors);
			}
		}
	}
	
	
	/**
	 * Method to perform validation on the required lease out fields.
	 * This method will check each required field for validation purposes, and then add it to a new error property, then before this error property
	 * is added to our main errors we check to see if any fields have been entered i.e. if they are all null then we simply clear the leaseIn set
	 * and do not add our validation errors.
	 * 
	 * @param asset
	 * @param errors
	 */
	public void validateLeaseOut(Assets asset, Errors errors) {

		boolean fieldsPresent = false;
		Errors leaseOutErrors = new BeanPropertyBindingResult(asset, "asset");
		
		if (null!=asset.getLeaseOut()) {
		 		
			//TODO XXX : add all validation message text into a new properties lookup file.
			
			//Check mandatory fields entered.
			if (null==asset.getLeaseOut().getLeaseTypeId()) { //Looks like could cause a null pointer but will not as leaseType empty object user data.
				leaseOutErrors.rejectValue("leaseOut.leaseType.id", "leaseOut.leaseType.id.mand", "LEASE OUT : Lease Type is a mandatory field : Please return to LeaseOut tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==asset.getLeaseOut().getVatCodeId()) {
				leaseOutErrors.rejectValue("leaseOut.vatCode.id", "leaseOut.vatCode.id.mand", "LEASE OUT : Vat code is a mandatory field : Please return to LeaseOut tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==asset.getLeaseOut().getChargePeriodId()) {
				leaseOutErrors.rejectValue("leaseOut.chargePeriod.id", "leaseOut.chargePeriod.id.mand", "LEASE OUT : Charge period is a mandatory field : Please return to LeaseOut tab to correct.");
			} else {
				fieldsPresent=true;
			}
			if (null==asset.getLeaseOut().getLeaseCharge()) {
				leaseOutErrors.rejectValue("leaseOut.leaseCharge", "leaseOut.leaseCharge.mand", "LEASE OUT : Leased in charge is a mandatory field : Please return to LeaseOut tab to correct.");
			} else {
				fieldsPresent=true;
			}				
			//False means no fields entered so clear set!  XXX Possibly remove set differently via id as possibility of getting confused?
			if (!fieldsPresent) {
				asset.setLeaseOut(null);
			}
			else{ //Fields are present so add any errors found....
				errors.addAllErrors(leaseOutErrors);
			}
		}
	}
	
	public void validateMaintenance(Assets asset, Errors errors) 
	{
		//Note field below stays FALSE if no fields have been entered & TRUE if mandatory fields have been entered.
		boolean fieldsPresent = false; 
		Errors maintenanceErrors = new BeanPropertyBindingResult(asset, "asset");
		
		Maintenance maintenance = asset.getMaintenance();
		if (null!=maintenance) 
		{
			//TODO XXX : add all validation message text into a new properties lookup file.	
			//Check mandatory fields entered.
			if (null==maintenance.getThirdPartySupplierId()) 
			{
				maintenanceErrors.rejectValue("maintenance.thirdPartySupplierId", "maintenance.thirdPartySupplierId.mand", "Maintenance : thirdPartySupplierId is a mandatory field : Please return to Maintenance tab to correct.");
			} else 
			{
				fieldsPresent=true;
			}
			if (null==maintenance.getFaultCodeCategoryId()) 
			{
				maintenanceErrors.rejectValue("maintenance.faultCodeCategoryId", "maintenance.faultCodeCategoryId.mand", "Maintenance : faultCodeCategoryId is a mandatory field : Please return to Maintenance tab to correct.");
			} else 
			{
				fieldsPresent=true;
			}
			if (null==maintenance.getFaultCodeId()) 
			{
				maintenanceErrors.rejectValue("maintenance.faultCodeId", "maintenance.faultCodeId.mand", "Maintenance : faultCodeId is a mandatory field : Please return to Maintenance tab to correct.");
			} else 
			{
				fieldsPresent=true;
			}
			
			if(!fieldsPresent) // If no fields are present then set to null on the asset.
			{
				asset.setMaintenance(null);
			} else
			{ //Fields are present so add any errors found....
				errors.addAllErrors(maintenanceErrors);
			}
		}
	}
	
	
	@Override
	public void validate(Object target, Errors errors)
	{
		Assets asset = (Assets)target;
		initialiseEmbeddedObjectValidation(asset, errors);
		
		//@TODO XXX: USe language file for text.
		if(null == asset.getCategoryId())
			errors.rejectValue("categoryId", "asset.category.mand", "Category is mandatory");
		if(null == asset.getAssetStatusId())
			errors.rejectValue("assetStatusId", "asset.status.mand", "Status is mandatory");
		if(null == asset.getAssetNumberPart1Id())
			errors.rejectValue("assetNumberPart1Id", "asset.assetNumber1.mand", "Asset Number is mandatory");
		if(null == asset.getAssetNumberPart2Id())
			errors.rejectValue("assetNumberPart2Id", "asset.assetNumber2.mand", "Asset Number is mandatory");
		if(null == asset.getAssetNumberPart3Id())
			errors.rejectValue("assetNumberPart3Id", "asset.assetNumber3.mand", "Asset Number is mandatory");
		if(null == asset.getTenantId())
			errors.rejectValue("tenantId", "asset.tenantId.mand", "Asset Tenant is mandatory");
		if(null == asset.getSupplierId())
			errors.rejectValue("supplierId", "asset.supplierId.mand", "Asset Supplier is mandatory");
		if(null == asset.getCustodianId())
			errors.rejectValue("custodian", "asset.custodianId.mand", "Asset Custodian is mandatory");
		if(null == asset.getCountryId())
			errors.rejectValue("countryId", "asset.countryId.mand", "Asset Country is mandatory");
		if(null == asset.getDepreciationCodeId())
			errors.rejectValue("DepreciationCodeId", "asset.depreciationCodeId.mand", "Asset Depreciation Code is mandatory");
		if(null == asset.getCompanyId())
			errors.rejectValue("company", "asset.company.mand", "Asset Company is mandatory");
		if(null == asset.getDivisionId())
			errors.rejectValue("divisionId", "asset.divisionId.mand", "Asset Division is mandatory");
		if(null == asset.getDepartmentId())
			errors.rejectValue("department", "asset.department.mand", "Asset Department is mandatory");
		if(null == asset.getLocationId())
			errors.rejectValue("location", "asset.location.mand", "Asset Location is mandatory");
		if(null == asset.getSiteId())
			errors.rejectValue("siteId", "asset.siteId.mand", "Asset Site is mandatory");
	}


}
