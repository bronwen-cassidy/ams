package com.xioq.dasacumen.web;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.common.Address;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.Email;
import com.xioq.dasacumen.model.common.PartyAddress;
import com.xioq.dasacumen.model.common.PartyEmail;
import com.xioq.dasacumen.model.common.PartyLink;
import com.xioq.dasacumen.model.common.PartyTelephoneNumber;
import com.xioq.dasacumen.model.common.PartyType;
import com.xioq.dasacumen.model.common.Person;
import com.xioq.dasacumen.model.common.TelephoneNumber;
import com.xioq.dasacumen.model.constants.PartyTypeConstants;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Controller for creating multiple party contacts.
 * 
 * @author MWalsh
 */
@Controller
@RequestMapping("/contact")
public class ContactController
{
	private static final Logger LOG = LoggerFactory.getLogger(ContactController.class);
	
	@Autowired
	private CRUDService crudService;
	
	/**
	 * Exception handler to log binding exceptions. These usually result in a 400 (Bad Request) response but with
	 * no detail as to what is wrong. Hence need to log if first.
	 */
	@ExceptionHandler({BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handle(BindException e) throws BindException
	{
		LOG.warn("BindException", e);
	    throw e;
	}
	
	/**
	 * This controller relies on the person being in the session. So if there is no session 
	 * an exception is thrown. Rather than show the error, redirect to the supplier landing screen.
	 * @param e
	 * @return
	 */
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String handle(HttpSessionRequiredException e)
	{
		LOG.debug("HttpSessionRequiredException " + e.getMessage() + ".  Redirecting to suppleir landing screen");
	    return "redirect:/das/financial/supplier/register";
	}
	
	@RequestMapping("")
	public ModelAndView entryPoint(Long parentPartyId, String view)
	{
		ModelAndView result = new ModelAndView("finance.supplier.create.contacts");
		Person person = new Person();
		result.addObject("person", person);
		result.addObject("parentPartyId", parentPartyId);
		result.addObject("view", view);
		return result;
	}
	
	
	/**
	 * Create a new Person (Party) which adds a Party which links to Party_Links. I.e. we have a new party to create
	 * which links to another party.
	 * This is so we can have a Client/Company that has multiple people linked within. We also add various
	 * contact information such as email,address,telephone etc.
	 * 
	 * @return back to create supplier/client page displaying the newly added contact person details.
	 */
	@RequestMapping("create")
	public ModelAndView createPersonContact(@ModelAttribute("person") Person person, 
			@RequestParam(value="addressLine1") String addressLine1, 
			@RequestParam(value="addressLine2") String addressLine2, 
			@RequestParam(value="city") String city, 
			@RequestParam(value="country") String country, 
			@RequestParam(value="emailAddress") String emailAddress, 
			@RequestParam(value="telNo") String telNo, 
			@RequestParam(value="zipPostCode") String zipPostCode, 
			@RequestParam(value="parentPartyId") String parentPartyId, 
			@RequestParam(value="view") String view)
	{
		Address address = new Address();
		address.setAddressLine1(addressLine1);
		address.setAddressLine2(addressLine2);
		address.setCity(city);
		address.setCountry(country);
		address.setZipPostCode(zipPostCode);
		// Creates a new PartyAddress for person
		PartyAddress partyAddress = new PartyAddress();
		partyAddress.setAddress(address);
		partyAddress.setParty(person);
		// Adds the new PartyAddress to the person
		person.addPartyAddress(partyAddress);
		
		TelephoneNumber telephoneNumber = new TelephoneNumber();
		telephoneNumber.setTelNo(telNo);
		// Creates a new PartyTelephoneNumber for person
		PartyTelephoneNumber partyTelNo = new PartyTelephoneNumber();
		partyTelNo.setTelephoneNumber(telephoneNumber);
		partyTelNo.setParty(person);
//		partyTelNo.setTelNumberType(telephoneNumberType);
		// Adds the PartyTelephoneNumber to the companies PartyTelephoneNumber collection
		person.addPartyTelephoneNumber(partyTelNo);
		
		Email email = new Email();
		email.setEmailAddress(emailAddress);
		// Creates a new PartyEmail for person
		PartyEmail partyEmail = new PartyEmail();
		partyEmail.setEmail(email);
		partyEmail.setParty(person);
//		partyEmail.setEmailType(emailType);
		// Adds the PartyEmail to the person PartyEmail collection
		person.addPartyEmail(partyEmail);
		
		//Create a set of partyTypes and a partyType 
		Set<PartyType> partyTypeSet = new HashSet<PartyType>(0);  
		PartyType partyType = new PartyType();
		
		//Set the party and the type name of the partyType then add it to the set of partyTypes
		partyType.setParty(person);
		partyType.setTypeName(PartyTypeConstants.PERSON);
		partyTypeSet.add(partyType);
		
		//Set the party types to the set of partyTypes we created, that contains the needed data
		person.setPartyTypes(partyTypeSet);

		//parentPartyId is the parent so retrieve it and set party1 to be parent and the party2 to be person!
    	Company parentCompany = crudService.retrieve(Company.class, new Long(parentPartyId));
    	
		//Setup partyLink using parentPartyId, then add to person.
		Set<PartyLink> partyLinkSet = new HashSet<PartyLink>(0);  
		PartyLink partyLink = new PartyLink();
		partyLink.setPartyId1(parentCompany);
		partyLink.setPartyId2(person);
		partyLink.setType(PartyTypeConstants.partylinkTypes.CONTACT.toString()); //Default value when adding a person's contact details.

		partyLinkSet.add(partyLink);
		person.setLinkingParties(partyLinkSet);
		
		person.setUid("TODO"); //@TODO XXX : Should be auto set by interceptor!
		
		crudService.create(person);
		ModelAndView result = new ModelAndView("redirect:/das/financial/" + view + "/create/" + view + "Tab?parentPartyId=" + parentPartyId);		
		return result;
	}
	
	
}
