package com.xioq.dasacumen.web.financial;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xioq.dasacumen.lib.utilities.SortedSetUtil;
import com.xioq.dasacumen.model.common.Address;
import com.xioq.dasacumen.model.common.BankDetail;
import com.xioq.dasacumen.model.common.Email;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.PartyAddress;
import com.xioq.dasacumen.model.common.PartyBankDetail;
import com.xioq.dasacumen.model.common.PartyContactDetail;
import com.xioq.dasacumen.model.common.PartyEmail;
import com.xioq.dasacumen.model.common.PartyTelephoneNumber;
import com.xioq.dasacumen.model.common.PartyWebsite;
import com.xioq.dasacumen.model.common.TelephoneNumber;
import com.xioq.dasacumen.model.common.Website;

/**
 * This controller deals with generic methods for any party (eg. Supplier or Client)
 * An example of what you can find in this controller is adding multiples to a party. We do not specify the type of party, but 
 * we perform the necessary functions and the type is set in the correct controller
 * 
 * @author bworsley
 *
 */

public class PartyUtilController 
{
	@RequestMapping(value = "/addExtraWebsite", method = RequestMethod.POST)
	public @ResponseBody void addExtraWebsite(@ModelAttribute Party party, 
			@RequestParam(value="websiteUrl") String websiteUrl) 
	{
		// Creates a new website using the field values passed through the ajax call
		Website website = new Website();
		website.setWebsiteUrl(websiteUrl);
		
		// Creates a new PartyWebsite for party
		PartyWebsite partyWebsite = new PartyWebsite();
		partyWebsite.setWebsite(website);
		partyWebsite.setParty(party);

		// Adds the Website to the companies PartyWebsite collection
		party.addPartyWebsite(partyWebsite);
	}
	
	@RequestMapping(value = "/addExtraEmailAddress", method = RequestMethod.POST)
	public @ResponseBody void addExtraEmailAddress(@ModelAttribute Party party, 
			@RequestParam(value="emailAddress") String emailAddress, 
			@RequestParam(value="emailType") Long emailType) 
	{

		// Creates a new Email using the field values passed through the ajax call
		Email email = new Email();
		email.setEmailAddress(emailAddress);
		
		// Creates a new PartyEmail for party
		PartyEmail partyEmail = new PartyEmail();
		partyEmail.setEmail(email);
		partyEmail.setParty(party);
		partyEmail.setEmailType(emailType);

		// Adds the PartyEmail to the companies PartyEmail collection
		party.addPartyEmail(partyEmail);
		System.out.println(party.getPartyEmails().size());
		
		// Creates a PartyContactDetail if the companies PartyContactDetail is null.
		PartyContactDetail partyContactDetail = party.getPartyContactDetails();
		if(partyContactDetail == null) {
			partyContactDetail = new PartyContactDetail();
			partyContactDetail.setParty(party);
			party.setPartyContactDetails(partyContactDetail);
		}
		
		// Sets the current PartyEmail to the companies PartyContactDetail if the PartyContactDetail's PartyEmail is null, this will be used to identify the companies primary Email.
		if(partyContactDetail.getPartyEmail() == null){
			partyContactDetail.setPartyEmail(partyEmail);
		}
	}
	
	@RequestMapping(value = "/addExtraTelephoneNumber", method = RequestMethod.POST)
	public @ResponseBody void addExtraTelephoneNumber(@ModelAttribute Party party, 
			@RequestParam(value="telephoneNumber") String telephoneNumber, 
			@RequestParam(value="telephoneNumberType") Long telephoneNumberType) 
	{
		
		// Creates a new TelephoneNumber using the field values passed through the ajax call
		TelephoneNumber telNo = new TelephoneNumber();
		telNo.setTelNo(telephoneNumber);
		
		// Creates a new PartyTelephoneNumber for party
		PartyTelephoneNumber partyTelNo = new PartyTelephoneNumber();
		partyTelNo.setTelephoneNumber(telNo);
		partyTelNo.setParty(party);
		partyTelNo.setTelNumberType(telephoneNumberType);
		
		// Adds the PartyTelephoneNumber to the companies PartyTelephoneNumber collection
		party.addPartyTelephoneNumber(partyTelNo);
		
		// Creates a PartyContactDetail if the companies PartyContactDetail is null.
		PartyContactDetail partyContactDetail = party.getPartyContactDetails();
		if(partyContactDetail == null) {
			partyContactDetail = new PartyContactDetail();
			partyContactDetail.setParty(party);
			party.setPartyContactDetails(partyContactDetail);
		}
		
		// Sets the current PartyTelephoneNumber to the companies PartyContactDetail if the PartyContactDetail's PartyTelephoneNumber is null, this will be used to identify the companies primary TelephoneNumber.
		if(partyContactDetail.getPartyTelephoneNumber() == null){
			partyContactDetail.setPartyTelephoneNumber(partyTelNo);;
		}
	}
	
	@RequestMapping(value = "/addExtraAddress", method = RequestMethod.POST)
	public @ResponseBody void addExtraAddress(@ModelAttribute Party party, 
			@RequestParam(value="addressLine1") String addressLine1, 
			@RequestParam(value="addressLine2") String addressLine2,
			@RequestParam(value="city") String city,
			@RequestParam(value="country") String country,
			@RequestParam(value="postZipCode") String postZipCode,
			@RequestParam(value="addressType") String addressType) 
	{
		
		// Creates a new Address using the field values passed through the ajax call
		Address address = new Address();
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setCountry(country);
		address.setZipPostCode(postZipCode);
		
		// Creates a new PartyAddress for Party
		PartyAddress partyAddress = new PartyAddress();
		partyAddress.setAddress(address);
		partyAddress.setParty(party);
		partyAddress.setAddressType(addressType);
		
		// Adds the new PartyAddress to the party
		party.addPartyAddress(partyAddress);
		
		// Creates a party contact detail if the companies PartyContactDetail is null.
		PartyContactDetail partyContactDetail = party.getPartyContactDetails();
		if(partyContactDetail == null) {
			partyContactDetail = new PartyContactDetail();
			partyContactDetail.setParty(party);
			party.setPartyContactDetails(partyContactDetail);
		}
		
		// Adds the current PartyAddress to the companies PartyContactDetail if the PartyContactDetail's PartyAddress is null, this will be used to identify the companies primary Address.
		if(partyContactDetail.getPartyAddress() == null){
			partyContactDetail.setPartyAddress(partyAddress);
		}
	}
	
	@RequestMapping(value = "/addExtraBankDetail", method = RequestMethod.POST)
	public @ResponseBody void addExtraBankDetail(@ModelAttribute Party party, 
			@RequestParam(value="bankName") String bankName,
			@RequestParam(value="bankAddress") String bankAddress,
			@RequestParam(value="bankAccountNumber") String bankAccountNumber,
			@RequestParam(value="bankSortCode") String bankSortCode
			) 
	{
		// Creates a new BankDetail using the field values passed through the ajax call
		BankDetail bankDetail = new BankDetail();
		bankDetail.setBankName(bankName);
		bankDetail.setBankAddress(bankAddress);
		bankDetail.setAccountNumber(bankAccountNumber);
		bankDetail.setSortCode(bankSortCode);
		
		// Creates a new PartyBankDetail for party
		PartyBankDetail partyBankDetail= new PartyBankDetail();
		partyBankDetail.setBankDetails(bankDetail);
		partyBankDetail.setParty(party);

		// Adds the BankDetail to the companies PartyBankDetail collection
		party.addPartyBankDetail(partyBankDetail);
	}
	
	@RequestMapping(value = "/updatePrimaryEmail", method = RequestMethod.POST)
	public @ResponseBody void updatePrimaryEmailAddress(@ModelAttribute Party party,
			@RequestParam int setIndex) 
	{
		// Retrieves the PartyEmail at the setIndex from the companies SortedSet of PartyEmails.
		PartyEmail partyEmail = (PartyEmail) SortedSetUtil.getAtIndex(party.getPartyEmails(), setIndex);
		// Retrieves the companies PartyContactDetails.
		PartyContactDetail pContactDetail = party.getPartyContactDetails();
		// Sets the companies PartyContactDetails PartyEmail to the PartyEmail retrieved - setting the primary Email address
		pContactDetail.setPartyEmail(partyEmail);
	}
	
	@RequestMapping(value = "/updatePrimaryTelephoneNumber", method = RequestMethod.POST)
	public @ResponseBody void updatePrimaryTelephoneNumber(@ModelAttribute Party party,
			@RequestParam int setIndex) 
	{
		// Retrieves the PartyTelephoneNumber at the setIndex from the companies SortedSet of PartyTelephoneNumber.
		PartyTelephoneNumber partyTelephoneNumber = (PartyTelephoneNumber) SortedSetUtil.getAtIndex(party.getPartyTelephoneNumbers(), setIndex);
		// Retrieves the companies PartyContactDetails.
		PartyContactDetail pContactDetail = party.getPartyContactDetails();
		// Sets the companies PartyContactDetails PartyTelephoneNumber to the PartyTelephoneNumber retrieved - setting the primary TelephoneNumber.
		pContactDetail.setPartyTelephoneNumber(partyTelephoneNumber);
	}
	
	@RequestMapping(value = "/updatePrimaryAddress", method = RequestMethod.POST)
	public @ResponseBody void updatePrimaryAddress(@ModelAttribute Party party,
			@RequestParam int setIndex) 
	{
		// Retrieves the PartyAddress at the setIndex from the companies SortedSet of PartyAddress.
		PartyAddress partyAddress = (PartyAddress) SortedSetUtil.getAtIndex(party.getPartyAddresses(), setIndex);
		// Retrieves the companies PartyContactDetails.
		PartyContactDetail pContactDetail = party.getPartyContactDetails();
		// Sets the companies PartyContactDetails PartyAddress to the PartyAddress retrieved - setting the primary Address.
		pContactDetail.setPartyAddress(partyAddress);
	}
}
