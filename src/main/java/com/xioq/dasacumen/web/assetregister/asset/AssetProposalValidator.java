package com.xioq.dasacumen.web.assetregister.asset;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xioq.dasacumen.lib.utilities.StringUtil;
import com.xioq.dasacumen.model.assetregister.AssetProposal;

/**
 * @author BenWorsley
 * 
 */

@Component("assetProposalValidator")
public class AssetProposalValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) 
	{
		AssetProposal assetProposal = (AssetProposal)target;
		
		if(null!=assetProposal.getTelephone() && StringUtil.isEmpty(assetProposal.getTelephone().getTelNo()))
			errors.rejectValue("telephone", "assetProposal.telephone.mand", "Telephone is mandatory");
		if(null!=assetProposal.getEmail() && StringUtil.isEmpty(assetProposal.getEmail().getEmailAddress()))
			errors.rejectValue("email", "assetProposal.email.mand", "Email is mandatory");
		if(null!=assetProposal.getAddress() && StringUtil.isEmpty(assetProposal.getAddress().getAddressLine1()))
			errors.rejectValue("address.addressLine1", "assetProposal.address.addressLine1.mand", "Address line 1 is mandatory");
//		if(null!=assetProposal.getAddress() && StringUtil.isEmpty(assetProposal.getAddress().getZipPostCode()))
//			errors.rejectValue("address.zipPostCode", "assetProposal.address.zipPostCode.mand", "PostCode is mandatory");
	}

}
