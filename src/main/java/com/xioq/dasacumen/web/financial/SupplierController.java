package com.xioq.dasacumen.web.financial;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.crm.service.CRMService;
import com.xioq.dasacumen.lib.exceptions.NoIDException;
import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.lib.utilities.SortedSetUtil;
import com.xioq.dasacumen.lib.utilities.StringUtil;
import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.model.common.Address;
import com.xioq.dasacumen.model.common.BankDetail;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.Email;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.PartyAddress;
import com.xioq.dasacumen.model.common.PartyBankDetail;
import com.xioq.dasacumen.model.common.PartyContactDetail;
import com.xioq.dasacumen.model.common.PartyEmail;
import com.xioq.dasacumen.model.common.PartyTelephoneNumber;
import com.xioq.dasacumen.model.common.PartyType;
import com.xioq.dasacumen.model.common.PartyWebsite;
import com.xioq.dasacumen.model.common.SupplierClientSearch;
import com.xioq.dasacumen.model.common.TelephoneNumber;
import com.xioq.dasacumen.model.common.Website;
import com.xioq.dasacumen.model.constants.PartyTypeConstants;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Controller for searching, creating, editing suppliers.
 * 
 * @author Stephen
 */
@Controller
@RequestMapping("/financial/supplier")
@SessionAttributes({"company"})
public class SupplierController
{
	private static final Logger LOG = LoggerFactory.getLogger(SupplierController.class);
	
	@Autowired
	private CRUDService crudService;
	
	@Autowired
	private CRMService crmService;
	
	@Autowired
	private SupplierValidator companyValidator;
	
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
	 * This controller relies on the company being in the session. So if there is no session 
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
	
	/**
	 * The supplier landing screen 
	 */
	@RequestMapping("register")
	public ModelAndView supplierLanding()
	{
		ModelAndView result = new ModelAndView("finance.home");
		
		SupplierClientSearch supplierClientSearch = new SupplierClientSearch();
		
		supplierClientSearch.setTypeName(PartyTypeConstants.partyTypes.SUPPLIER.toString());
		result.addObject("typeName", PartyTypeConstants.partyTypes.SUPPLIER.toString());
		
		SearchResult<Party> partyList = crudService.generalSearch(supplierClientSearch);
		result.addObject("partyList", partyList.getResult());
		result.addObject("searchTotalCount", partyList.getTotalCount());
		
		return result;		
	}	
	
	/**
	 * Entry point into creating or editing an Supplier
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView startEditOrCreate(Long partyId)
	{
		String baseUrl = "redirect:/das/financial/supplier/create/supplierTab";
		ModelAndView result = new ModelAndView(baseUrl);
		Company company = null;
		
		//if we are editing we will have a party id
		if(null != partyId)
		{
			company = crudService.retrieve(Company.class, partyId, Party.ALL_EAGER);
			result = new ModelAndView(baseUrl + "?parentPartyId=" + partyId);
		}
		
		// No id so must be creating
		if (null == company) 
		{
			company = new Company();
		}
		
		result.addObject("company", company); //result.addobjects may not be needed
			
		return result;
	}
	
	/**
	 * The create supplier details tab. 
	 * If we have a parentPartyId set then we are calling this after adding a new contact, so we go and retrieve 
	 * the parent party i.e. the company object and obtain its party to party links. These P2P links
	 * can contain any links so we just get the type of 'CONTACT' which means a person and add these values to
	 * a list for the jsp to display.
	 * 
	 * @return personPartyList
	 */
	
	@RequestMapping("create/supplierTab")
	public ModelAndView supplierDetailsTab(HttpSession session, Long parentPartyId)
	{
		ModelAndView result = new ModelAndView("finance.supplier.create.supplierTab");
		
		//If we have a parentId then have returned to this method from creating a new person contact, so get the parent which we can get
		//all person contact links from i.e. the Party to Party links.
		if (null!=parentPartyId) {
			Party parent = crmService.retrievePartyAndPersonLinks(parentPartyId);
			result.addObject("parent", parent.getLinkingParties());
		}

		//gets the user if there is one and sends it to the jsp to be displayed in the created by text box
		if(session.getAttribute("userID") != null)
		{
			User user = crudService.retrieve(User.class, (long)session.getAttribute("userID"));
			result.addObject("user", user);
		}

		return result;
	}
	
	/**
	 * The financial details tab 
	 */
	@RequestMapping("create/financialTab")
	public ModelAndView financialTab()
	{
		ModelAndView result = new ModelAndView("finance.supplier.create.financialTab");
		prepareCreateFinancialDetailsView(result);
		return result;
	}
		
	/**
	 * The Other details tab 
	 */
	@RequestMapping("create/otherTab")
	public ModelAndView otherTab()
	{
		ModelAndView result = new ModelAndView("finance.supplier.create.otherTab");
		prepareCreateOtherTabView(result);
		return result;
	}

	/**
	 * Method to handle the saving of company data. The other entities can hang from the company object like address, contact info etc, as we can obviously not
	 * pass in the Party object with it being abstract.
	 * 
	 * @param company
	 * @param result
	 * @return redirect to view
	 */
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView generalSave(@ModelAttribute("company") Company company, BindingResult result, HttpSession session, SessionStatus status, 
			String newEmail, Long newEmailType, String newTelephoneNumber, Long newTelephoneType,
			String newAddressLine1, String newAddressLine2, String newCity, String newCountry, String newZipPostcode, String newAddressType,
			String newWebsite, String newBankName, String newBankAddress, String newBankAccountNumber, String newBankSortCode)
	{
		//Company needs UID so we set default
		company.setUid("Ferg_G_" + "fgsa321321");
		
		//Create a set of partyTypes and a partyType 
		Set<PartyType> pTypes = new HashSet<PartyType>(0);  
		PartyType ptype = new PartyType();
		
		//Set the party and the type name of the partyType then add it to the set of partyTypes
		ptype.setParty(company);
		ptype.setTypeName("SUPPLIER");
		pTypes.add(ptype);
		
		//Set the company's party types to the set of partyTypes we created, that contains the needed data
		company.setPartyTypes(pTypes);
		
		//Creates a new email if the values filled in the regular front-end form inputs have values in
		if (!StringUtil.isEmpty(newEmail) && newEmailType != null) {
			addExtraEmailAddress(company, newEmail, newEmailType);
		}

		//Creates a new telephone if the values filled in the regular front-end form inputs have values in
		if (!StringUtil.isEmpty(newTelephoneNumber) && newTelephoneType != null) {
			addExtraTelephoneNumber(company, newTelephoneNumber, newTelephoneType);
		}

		//Creates a new address if the values filled in the regular front-end form inputs have values in
		if (!StringUtil.isEmpty(new String[] { newAddressLine1, newAddressLine2, newCity, newCountry, newZipPostcode, newAddressType })) {
			addExtraAddress(company, newAddressLine1, newAddressLine2, newCity, newCountry, newZipPostcode, newAddressType);
		}

		//Creates a new website if the values filled in the regular front-end form inputs have values in
		if (!StringUtil.isEmpty(newWebsite)) {
			addExtraWebsite(company, newWebsite);
		}
		
		//Creates a new BankDetail if the values filled in the regular front-end form inputs have values in
		if (!StringUtil.isEmpty(new String[] { newBankName, newBankAddress, newBankAccountNumber, newBankSortCode })) {
			addExtraBankDetail(company, newBankName, newBankAddress, newBankAccountNumber, newBankSortCode);
		}

		crudService.create(company);
		
//		status.setComplete();
//		session.removeAttribute("company");
		ModelAndView results = new ModelAndView("redirect:/das/financial/supplier/create/supplierTab");
		return results;
	}

	/**
	 * Method called using Ajax function which operates when a field is changed on the company object. 
	 * By method simply being called it saves the changed field into session, but a supplier is not created till 
	 * the save button is clicked and the create method is called
	 */
	@RequestMapping(value = "/saveDraftSupplier", method = RequestMethod.POST)
	public @ResponseBody void saveSupplierDraft(@ModelAttribute("company") Company companyForm) {};
	
	/**
	 * Setup search screen for a Supplier.
	 * @param supplierClientSearch
	 * @return
	 */
	@RequestMapping(value = "/searchSupplier", method = RequestMethod.GET)
	public ModelAndView fullSearchStart(@ModelAttribute SupplierClientSearch supplierClientSearch)
	{
		ModelAndView result = new ModelAndView("supplier.search");

		return result;
	}
	
	/**
	 * Search screen for a supplier. 
	 * @param supplierClientSearch
	 * @return
	 */
	@RequestMapping(value = "/searchSupplier", method = RequestMethod.POST)
	public ModelAndView fullSearch(SupplierClientSearch supplierClientSearch)
	{
		ModelAndView result = new ModelAndView("finance.home");
		
		supplierClientSearch.setTypeName(PartyTypeConstants.partyTypes.SUPPLIER.toString());
		result.addObject("typeName", PartyTypeConstants.partyTypes.SUPPLIER.toString());
		
		SearchResult<Party> partyList = crudService.generalSearch(supplierClientSearch);
		result.addObject("partyList", partyList.getResult());
		result.addObject("searchTotalCount", partyList.getTotalCount());

		return result;
	}
	
	/**
	 * Scratchpad Supplier search.
	 * @return
	 */
	@RequestMapping("/search/sp")
	public ModelAndView scratchpadSearch(String query, SupplierClientSearch searchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.custodian.results");

		// to be removed. Just an example on how to search via asset
//		searchParams.setCustodianAssetId(2L);
		searchParams.setName(query);
		SearchResult<Party> supplierResults = crudService.generalSearch(searchParams);
		
		result.addObject("itemSearchResults", supplierResults.getResult());
		result.addObject("searchTotalCount", supplierResults.getTotalCount());

		return result;
		
	}

	/**
	 * Scratchpad Supplier preview search.
	 * @return
	 */
	@RequestMapping("/supplierPreview")
	public ModelAndView scratchpadPreviewSearch(@RequestParam Map<String, String> attrs, SupplierClientSearch searchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.supplier.preview.results");
		
		Map<String,String> searchMap = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			//convert JSON string to Map
			searchMap = mapper.readValue(attrs.get("attrs"), 
			    new TypeReference<HashMap<String,String>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(null != searchMap.get("supplierId")){
			searchParams.setPartyId(Long.parseLong(searchMap.get("supplierId")));
		}
		else {
			searchParams.setSupplierAssetId(Long.parseLong(searchMap.get("assetId")));
		}
			
		SearchResult<Party> supplierResults = crudService.generalSearch(searchParams, "partyAddresses");
		if(!supplierResults.getResult().isEmpty())
		{
			Party supplier = supplierResults.getResult().get(0);
			result.addObject("supplierItem", supplier);	
			
			for (PartyAddress pa : supplier.getPartyAddresses())
			{
				result.addObject("address", pa.getAddress());
				break;
			}
		}
		
		return result;
	}
	
	
	private void prepareCreateFinancialDetailsView(ModelAndView mv)
	{
		UserData searchExample = new UserData();
		searchExample.setTenantId(1);
		
		searchExample.setUserDataTypeId(14L);
		mv.addObject("VATCode", crudService.findByExample(searchExample));
		
		searchExample.setUserDataTypeId(23L);
		mv.addObject("currency", crudService.findByExample(searchExample));
		
		searchExample.setUserDataTypeId(24L);
		mv.addObject("terms", crudService.findByExample(searchExample));
		
		searchExample.setUserDataTypeId(25L);
		mv.addObject("spendLimit", crudService.findByExample(searchExample));
		
		searchExample.setUserDataTypeId(29L);
		mv.addObject("countryList", crudService.findByExample(searchExample));
	
	}
	
	private void prepareCreateOtherTabView(ModelAndView mv)
	{
		UserData searchExample = new UserData();
		searchExample.setTenantId(1);
		
		searchExample.setUserDataTypeId(28L);
		mv.addObject("accreditationsList", crudService.findByExample(searchExample));
	}
	//read only view
	@RequestMapping("readOnlyDetails")
	public ModelAndView financeDetails(Long partyId) throws NoIDException
	{
		if (null== partyId)
		{
		//@TODO - we should set a standard here to be implemented across all controllers :suggestion =
		//return specific exception containing detailed message.
			throw new NoIDException("PartyId was expected as trying to view specific record for detailed view.");
		}
		
		ModelAndView result = new ModelAndView("finance.supplier.financeDetails");

		Party supplier = crudService.retrieve(Party.class, partyId, "partyAddresses"); // XXX The address should be got from the contact details
		result.addObject("party", supplier);
		// XXX The JSP Should just reference the address, email etc via the contact details
		for (PartyAddress pa : supplier.getPartyAddresses())
			{
				result.addObject("partyAaddress", pa.getAddress());
				break;
			}
		for (PartyType type : supplier.getPartyTypes())
			{
				result.addObject("category", type.getTypeName());
				break;
			}
			//Main contact details for party = 1 email, 1 telephone & 1 address.
		PartyContactDetail pcd = supplier.getPartyContactDetails();
		if(null != pcd)
			{
				result.addObject("contactAddress", pcd.getPartyAddress().getAddress());
				result.addObject("telNo", pcd.getPartyTelephoneNumber().getTelephoneNumber().getTelNo());
				result.addObject("email", pcd.getPartyEmail().getEmail().getEmailAddress());
			}
		
		return result;
	}
	
	@RequestMapping(value = "/addExtraWebsite", method = RequestMethod.POST)
	public @ResponseBody void addExtraWebsite(@ModelAttribute Company company, 
			@RequestParam(value="websiteUrl") String websiteUrl) 
	{
		// Creates a new website using the field values passed through the ajax call
		Website website = new Website();
		website.setWebsiteUrl(websiteUrl);
		
		// Creates a new PartyWebsite for company
		PartyWebsite partyWebsite = new PartyWebsite();
		partyWebsite.setWebsite(website);
		partyWebsite.setParty(company);

		// Adds the Website to the companies PartyWebsite collection
		company.addPartyWebsite(partyWebsite);
	}
	
	@RequestMapping(value = "/addExtraEmailAddress", method = RequestMethod.POST)
	public @ResponseBody void addExtraEmailAddress(@ModelAttribute Company company, 
			@RequestParam(value="emailAddress") String emailAddress, 
			@RequestParam(value="emailType") Long emailType) 
	{

		// Creates a new Email using the field values passed through the ajax call
		Email email = new Email();
		email.setEmailAddress(emailAddress);
		
		// Creates a new PartyEmail for company
		PartyEmail partyEmail = new PartyEmail();
		partyEmail.setEmail(email);
		partyEmail.setParty(company);
		partyEmail.setEmailType(emailType);

		// Adds the PartyEmail to the companies PartyEmail collection
		company.addPartyEmail(partyEmail);
		System.out.println(company.getPartyEmails().size());
		
		// Creates a PartyContactDetail if the companies PartyContactDetail is null.
		PartyContactDetail partyContactDetail = company.getPartyContactDetails();
		if(partyContactDetail == null) {
			partyContactDetail = new PartyContactDetail();
			partyContactDetail.setParty(company);
			company.setPartyContactDetails(partyContactDetail);
		}
		
		// Sets the current PartyEmail to the companies PartyContactDetail if the PartyContactDetail's PartyEmail is null, this will be used to identify the companies primary Email.
		if(partyContactDetail.getPartyEmail() == null){
			partyContactDetail.setPartyEmail(partyEmail);
		}
	}
	
	@RequestMapping(value = "/addExtraTelephoneNumber", method = RequestMethod.POST)
	public @ResponseBody void addExtraTelephoneNumber(@ModelAttribute Company company, 
			@RequestParam(value="telephoneNumber") String telephoneNumber, 
			@RequestParam(value="telephoneNumberType") Long telephoneNumberType) 
	{
		
		// Creates a new TelephoneNumber using the field values passed through the ajax call
		TelephoneNumber telNo = new TelephoneNumber();
		telNo.setTelNo(telephoneNumber);
		
		// Creates a new PartyTelephoneNumber for company
		PartyTelephoneNumber partyTelNo = new PartyTelephoneNumber();
		partyTelNo.setTelephoneNumber(telNo);
		partyTelNo.setParty(company);
		partyTelNo.setTelNumberType(telephoneNumberType);
		
		// Adds the PartyTelephoneNumber to the companies PartyTelephoneNumber collection
		company.addPartyTelephoneNumber(partyTelNo);
		
		// Creates a PartyContactDetail if the companies PartyContactDetail is null.
		PartyContactDetail partyContactDetail = company.getPartyContactDetails();
		if(partyContactDetail == null) {
			partyContactDetail = new PartyContactDetail();
			partyContactDetail.setParty(company);
			company.setPartyContactDetails(partyContactDetail);
		}
		
		// Sets the current PartyTelephoneNumber to the companies PartyContactDetail if the PartyContactDetail's PartyTelephoneNumber is null, this will be used to identify the companies primary TelephoneNumber.
		if(partyContactDetail.getPartyTelephoneNumber() == null){
			partyContactDetail.setPartyTelephoneNumber(partyTelNo);;
		}
	}
	
	@RequestMapping(value = "/addExtraAddress", method = RequestMethod.POST)
	public @ResponseBody void addExtraAddress(@ModelAttribute Company company, 
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
		
		// Creates a new PartyAddress for company
		PartyAddress partyAddress = new PartyAddress();
		partyAddress.setAddress(address);
		partyAddress.setParty(company);
		partyAddress.setAddressType(addressType);
		
		// Adds the new PartyAddress to the company
		company.addPartyAddress(partyAddress);
		
		// Creates a party contact detail if the companies PartyContactDetail is null.
		PartyContactDetail partyContactDetail = company.getPartyContactDetails();
		if(partyContactDetail == null) {
			partyContactDetail = new PartyContactDetail();
			partyContactDetail.setParty(company);
			company.setPartyContactDetails(partyContactDetail);
		}
		
		// Adds the current PartyAddress to the companies PartyContactDetail if the PartyContactDetail's PartyAddress is null, this will be used to identify the companies primary Address.
		if(partyContactDetail.getPartyAddress() == null){
			partyContactDetail.setPartyAddress(partyAddress);
		}
	}
	
	@RequestMapping(value = "/addExtraBankDetail", method = RequestMethod.POST)
	public @ResponseBody void addExtraBankDetail(@ModelAttribute Company company, 
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
		
		// Creates a new PartyBankDetail for company
		PartyBankDetail partyBankDetail= new PartyBankDetail();
		partyBankDetail.setBankDetails(bankDetail);
		partyBankDetail.setParty(company);

		// Adds the BankDetail to the companies PartyBankDetail collection
		company.addPartyBankDetail(partyBankDetail);
	}
	
	@RequestMapping(value = "/updatePrimaryEmail", method = RequestMethod.POST)
	public @ResponseBody void updatePrimaryEmailAddress(@ModelAttribute Company company,
			@RequestParam int setIndex) 
	{
		// Retrieves the PartyEmail at the setIndex from the companies SortedSet of PartyEmails.
		PartyEmail partyEmail = (PartyEmail) SortedSetUtil.getAtIndex(company.getPartyEmails(), setIndex);
		// Retrieves the companies PartyContactDetails.
		PartyContactDetail pContactDetail = company.getPartyContactDetails();
		// Sets the companies PartyContactDetails PartyEmail to the PartyEmail retrieved - setting the primary Email address
		pContactDetail.setPartyEmail(partyEmail);
	}
	
	@RequestMapping(value = "/updatePrimaryTelephoneNumber", method = RequestMethod.POST)
	public @ResponseBody void updatePrimaryTelephoneNumber(@ModelAttribute Company company,
			@RequestParam int setIndex) 
	{
		// Retrieves the PartyTelephoneNumber at the setIndex from the companies SortedSet of PartyTelephoneNumber.
		PartyTelephoneNumber partyTelephoneNumber = (PartyTelephoneNumber) SortedSetUtil.getAtIndex(company.getPartyTelephoneNumbers(), setIndex);
		// Retrieves the companies PartyContactDetails.
		PartyContactDetail pContactDetail = company.getPartyContactDetails();
		// Sets the companies PartyContactDetails PartyTelephoneNumber to the PartyTelephoneNumber retrieved - setting the primary TelephoneNumber.
		pContactDetail.setPartyTelephoneNumber(partyTelephoneNumber);
	}
	
	@RequestMapping(value = "/updatePrimaryAddress", method = RequestMethod.POST)
	public @ResponseBody void updatePrimaryAddress(@ModelAttribute Company company,
			@RequestParam int setIndex) 
	{
		// Retrieves the PartyAddress at the setIndex from the companies SortedSet of PartyAddress.
		PartyAddress partyAddress = (PartyAddress) SortedSetUtil.getAtIndex(company.getPartyAddresses(), setIndex);
		// Retrieves the companies PartyContactDetails.
		PartyContactDetail pContactDetail = company.getPartyContactDetails();
		// Sets the companies PartyContactDetails PartyAddress to the PartyAddress retrieved - setting the primary Address.
		pContactDetail.setPartyAddress(partyAddress);
	}
}
