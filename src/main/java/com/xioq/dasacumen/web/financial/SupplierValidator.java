package com.xioq.dasacumen.web.financial;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.PartyBankDetail;

@Component("companyValidator")
public class SupplierValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Company.class.isAssignableFrom(clazz);
	}

	private void validateBankDetails(Company company, Errors errors) {
		if (company.getPartyBankDetails().isEmpty())
			errors.rejectValue("company.partyBankDetails", "company.partyBankDetails.mand",
					"Bank details are mandatory : Please return back to the financial tab and fill out the bank details");
		for (PartyBankDetail pbd : company.getPartyBankDetails()) {
			if (null == pbd.getBankDetails().getBankName())
				errors.rejectValue("someformname", "partyBankDetails.bankDetails.bankName.mand",
						"Bank name is a mandatory field.");
			if (null == pbd.getBankDetails().getBankAddress())
				errors.rejectValue("someformname", "partyBankDetails.bankDetails.bankAddress.mand",
						"Bank address is a mandatory field.");
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (target instanceof Company) {
			Company company = (Company) target;
			validateBankDetails(company, errors);
			if (null == company.getName())
				errors.rejectValue("name", "company.name.mand", "The suppliers name is mandatory");

		}
	}
}