package com.xioq.dasacumen.web.sales;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.joda.time.Weeks;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.assetregister.AssetProposalSearch;
import com.xioq.dasacumen.model.assetregister.AssetProposalExtras;
import com.xioq.dasacumen.model.assetregister.AssetSchedule;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Company;
import com.xioq.dasacumen.model.common.LeaseOut;
import com.xioq.dasacumen.model.common.PartyLink;
import com.xioq.dasacumen.model.common.Person;
import com.xioq.dasacumen.model.constants.SalesConstants;
import com.xioq.dasacumen.model.constants.SimpleConstants;
import com.xioq.dasacumen.service.CRUDService;
import com.xioq.dasacumen.service.EmailService;
import com.xioq.dasacumen.web.assetregister.asset.AssetProposalValidator;

@Controller
@RequestMapping("/proposal")
@SessionAttributes({"assetProposal"})
public class ProposalController
{
	@Autowired
	private CRUDService someService;
	
	@Autowired
	private AssetProposalValidator assetProposalValidator;
	
	@Autowired
    private EmailService simpleMessage;
	
	@RequestMapping("")
	public ModelAndView proposalView(String fromDate, String toDate, Long assetId, Long clientId, HttpSession session, SessionStatus status)
	{
		//1) Get asset and setup new AssetSchedule with passed in params & asset retrieved.
		//2) add assetSchedule to a new AssetProposal object.
		//3) add customer (person) to proposal as not creating new person here (can create contact details tho).
		//4) Get any leaseOut extras ensure we link them to the proposalExtras? then jsp can use c:if to output
		//   the leaseOutExtras and next to each have a form input for the proposalExtra fields?
		//5) return to jsp view for input of proposal & extra fields.
		
    	Assets asset = someService.retrieve(Assets.class, assetId);
		AssetSchedule assetSchedule = new AssetSchedule();
		assetSchedule.setAssets(asset);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime from = formatter.parseDateTime(fromDate);	
		DateTime to = formatter.parseDateTime(toDate);
		
		assetSchedule.setLeaseCommences(from.toDate());
		assetSchedule.setLeaseExpires(to.toDate());

    	AssetProposal assetProposal = new AssetProposal();
		assetProposal.setAsset(asset);
    	assetProposal.setAssetSchedule(assetSchedule);
    	Company company = someService.retrieve(Company.class, clientId, "linkingParties");
    	assetProposal.setCompany(company);
    	assetProposal.setContactParty(company);

    	assetProposal.setAddress(company.getPartyContactDetails().getPartyAddress().getAddress());
    	assetProposal.setEmail(company.getPartyContactDetails().getPartyEmail().getEmail());
    	assetProposal.setTelephone(company.getPartyContactDetails().getPartyTelephoneNumber().getTelephoneNumber());
    	
    	List<Person> listOfContacts = new ArrayList<Person>();
    	for (Iterator<PartyLink> partyLinks = company.getLinkingParties().iterator();partyLinks.hasNext();){
    		PartyLink partyLink = partyLinks.next();
    		listOfContacts.add((Person)partyLink.getPartyId1());
    	 }

    	ModelAndView result = new ModelAndView("proposal.create");
		result.addObject("assetProposal", assetProposal);
		result.addObject("listOfContacts", listOfContacts);
		return result;
	}
	
	@RequestMapping(value="updateAssetProposalContact", method=RequestMethod.GET)
	public ModelAndView updateContactParty(@ModelAttribute AssetProposal assetProposal,Long contactId, HttpSession session, SessionStatus status)
	{
		ModelAndView result = new ModelAndView("sales/proposal/createProposalContactDetails");
		
		if(contactId != 0l)
		{
			assetProposal.setContactParty(someService.retrieve(Person.class, contactId));
		}
		else
		{
			assetProposal.setContactParty(assetProposal.getCompany());
		}
		result.addObject("contactDetails", assetProposal.getContactParty().getPartyContactDetails());
		return result;
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ModelAndView proposalCreation(@ModelAttribute("assetProposal")  AssetProposal assetProposal, BindingResult result) 
	{
		/*
		 * Check needed fields have a value. 
		 * If needed fields have no value the page refreshes - tells the user what is mandatory - does not create proposal
		 */
		assetProposalValidator.validate(assetProposal, result);
		if (result.hasErrors()) {
		 ModelAndView mv = new ModelAndView("proposal.create");
		 mv.addObject("errors", result);
		 return mv;
		}

		assetProposal.setCurrentStatus(SalesConstants.PENDING);
		someService.create(assetProposal);

		ModelAndView results = new ModelAndView("proposal.confirm");
		results.addObject("assetProposal", assetProposal);
		
		return results;
	}
	
	
	/**
	 * This a the detailed/invoice view that gives an overview and allows the user to confirm the proposal. On confirmation an email
	 * will be sent to the client, in which they can accept the proposal.
	 * 	
	 * @param assetProposal
	 * @param result
	 * @param proposalId
	 * @return
	 */
	@RequestMapping("/confirm")
	public ModelAndView proposalConfirmation(@ModelAttribute("assetProposal")  AssetProposal assetProposal, BindingResult result, String proposalId,
			HttpSession session, SessionStatus status) 
	{		
		ModelAndView results = new ModelAndView("redirect:../assetAvailability");
//		simpleMessage.sendSimpleMessage(from, to, bcc, cc, subject, messageBody);
		
		simpleMessage.sendSimpleMessage("brigtfuturesoftware1@gmail.com",
										assetProposal.getContactParty().getPartyContactDetails().getPartyEmail().getEmail().getEmailAddress(), 
										null, null, 
										"Confirmation of leasing", 
										"Dear "+ assetProposal.getCompany().getName() + 
										", \n \n"
										+ "We would like to thank you for your service. \n \n"
										+ "Please follow the following link to accept the proposal:\n http://localhost:8080/DAS_Acumen/das/publicClientProposal?proposalId=" + assetProposal.getId() + " \n \n \n"
										+ "Yours Sincerly \n \n"
										+ "Rob Kennedy \n"
										+ "Leasing Manager" );

		assetProposal.setCurrentStatus(SalesConstants.SENT);
		someService.update(assetProposal);
		results.addObject("assetProposal", assetProposal);
		status.setComplete();
		session.removeAttribute("assetProposal");
		return results;
	}
	
	/**
	 * This is a view accessed from the landing screen when selecting as proposal id, it is simply the confirmation jsp but has the 
	 * submit button disabled.
	 * 
	 * @param proposalId
	 * @return assetProposal to confirmation jsp.
	 */
	@RequestMapping("/proposalDetails")
	public ModelAndView proposalConfirmation(String proposalId) 
	{		
		ModelAndView results = new ModelAndView("proposal.confirm");
		AssetProposal assetProposal = new AssetProposal();
		if (null!=proposalId) {
	    	assetProposal = someService.retrieve(AssetProposal.class, new Long (proposalId));
		}
		results.addObject("assetProposal", assetProposal);
		//If proposal is pending or declined then allow it to be sent.
		if (!SalesConstants.PENDING.equalsIgnoreCase(assetProposal.getCurrentStatus()) 
				&& !SalesConstants.DECLINED.equalsIgnoreCase(assetProposal.getCurrentStatus())) {
			results.addObject("readOnly", SimpleConstants.DEFAULT);
		}

		return results;
	}

	/**
	 * Set the proposal extras onto the proposal extras <set>, from the selected value from jsp OR if the checkbox is false
	 * then we remove from the set.
	 * 
	 * @param assetProposal
	 * @param extraId
	 * @param assetId
	 */
	@RequestMapping(value = ("/addProposalExtras"), method = RequestMethod.POST)
	public @ResponseBody void setProposalExtras(
			@ModelAttribute("assetProposal")  AssetProposal assetProposal,
			@RequestParam(value="extraId") String extraId,
			@RequestParam(value="assetId") String assetId,
			@RequestParam(value="checkBoxValue") boolean checkBoxValue,
			@RequestParam(value="extraCost") String extraCost
			)
	{
		AssetProposalExtras ext = new AssetProposalExtras();
		
		if (null!= assetId) 
		{
			ext.setAssetId(new Long(assetId));
		}
		
		if (null!=extraId)
		{
			ext.setLeaseOutExtraId(new Long(extraId));
			
			// Set the cost of the extra as the cost of the lease out extra(without this it is null)
			ext.setCostOfExtras(new BigDecimal(extraCost));
		}
		
		// We pass in value of boolean selected checkbox and if false/de-selected we can remove else add to set!
		if (checkBoxValue)
		{
			assetProposal.addAssetProposalExtra(ext);
		}
		else 
		{
			Set<AssetProposalExtras> assetProposalExtrasSet =  assetProposal.getAssetProposalExtras();
			// We convert asset proposal extras set into an array to avoid ConcurrentModificationException
			AssetProposalExtras[] assetProposalExtras = assetProposalExtrasSet.toArray(new AssetProposalExtras[assetProposalExtrasSet.size()]);
			for(AssetProposalExtras extra : assetProposalExtras)
			{
				if((extra.getLeaseOutExtraId()).equals((Long.valueOf(extraId))))
				{
					assetProposal.removeAssetProposalExtra(extra);
				}
			}
		}
	}
	
	@RequestMapping(value = "/salesLandingSearch", method = RequestMethod.GET)
	public ModelAndView scratchpadSearch(@RequestParam(value="query") String query,
									@RequestParam(value="offset") Integer offset,
									@RequestParam(value="limit") Integer limit,
									@RequestParam(value="order_by") String order_by,
									@RequestParam(value="order_type") String order_type,
									HttpSession session){
		
		ModelAndView result = new ModelAndView("sales/salesSearchTable");
		
		AssetProposalSearch assetProposalSearchParams = new AssetProposalSearch();
		
		switch(query) {
			case  "":
				assetProposalSearchParams.setCurrentStatus("");
				break;
			case SalesConstants.PENDING:
				assetProposalSearchParams.setCurrentStatus(query);
				break;
			case SalesConstants.ACCEPTED:
				assetProposalSearchParams.setCurrentStatus(query);
				break;
			case SalesConstants.DECLINED:
				assetProposalSearchParams.setCurrentStatus(query);
				break;
			case SalesConstants.SENT:
				assetProposalSearchParams.setCurrentStatus(query);
				break;
			case SalesConstants.DEPLOYED:
				assetProposalSearchParams.setCurrentStatus(query);
				break;
			case SalesConstants.COLLECTION:
				assetProposalSearchParams.setCurrentStatus(query);
				break;
		}
		
		assetProposalSearchParams.setFirstResult(offset);
		assetProposalSearchParams.setMaxResults(limit);
	
		SearchResult<AssetProposal> assetProposal = someService.generalSearch(assetProposalSearchParams);
		result.addObject("proposalList", assetProposal.getResult());
		result.addObject("searchTotalCount", assetProposal.getTotalCount());
		return result;
		
	}
	
	/**
	 * Ajax calls this method
	 * 
	 * Will find the cost before extras, the cost of the extras, and the total cost by performing calculations based on which period of time
	 * the user has selected when creating an asset and defining it's lease out information.
	 * 
	 * The prices are stored in a map which is returned to the ajax call where the js adds the information inside the map to the jsp.
	 * 
	 * The prices are also saved onto the assetProposal to be used elsewhere in the system
	 */
	@RequestMapping(value = ("/calculateProposedPrice"), method = RequestMethod.POST)
	public @ResponseBody Map<String, BigDecimal> calculateProposedPrice(
			@ModelAttribute("assetProposal")  AssetProposal assetProposal) 
	{
		BigDecimal differenceBetweenDates = BigDecimal.ZERO;
		BigDecimal remainingDays = BigDecimal.ZERO;
		BigDecimal standardPrice = BigDecimal.ZERO;
		BigDecimal costBeforeExtras = BigDecimal.ZERO;
		BigDecimal extrasCost = BigDecimal.ZERO;
		BigDecimal total = BigDecimal.ZERO;
		BigDecimal yearVal = new BigDecimal(365);
		String timePeriod = "";
		DateTime leaseCommences = new DateTime(assetProposal.getAssetSchedule().getLeaseCommences());
		DateTime leaseExpires= new DateTime(assetProposal.getAssetSchedule().getLeaseExpires());
		MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
		Long chargePeriodId = null;
		
		// assetProposal may not have any leaseOuts so check is necessary. Here we get standard price and the time period selected
		LeaseOut leaseOut = assetProposal.getAsset().getLeaseOut();
		if(null != leaseOut) 
		{
			standardPrice = leaseOut.getLeaseCharge();
			timePeriod = leaseOut.getChargePeriod().getName();
			chargePeriodId = leaseOut.getChargePeriod().getId();
		}
		
		// Work out the cost of the extras	
		Set<AssetProposalExtras> assetProposalExtras = assetProposal.getAssetProposalExtras();
    	if(null != assetProposalExtras)
    	{
	    	for(AssetProposalExtras extra : assetProposalExtras)
	    	{
		   		extrasCost = extrasCost.add(extra.getCostOfExtras());
	    	}
    	}	
		
		switch (timePeriod) 
    	{
	    	case "Per Day": 
	    		differenceBetweenDates = new BigDecimal(Days.daysBetween(leaseCommences, leaseExpires).getDays() + 1);
	    		costBeforeExtras = standardPrice.multiply(differenceBetweenDates);
				extrasCost = extrasCost.multiply(differenceBetweenDates);
				break;	
			
	    	case "Per Week":
	    		Weeks weeks = Weeks.weeksBetween(leaseCommences, leaseExpires);
	    	    differenceBetweenDates = new BigDecimal(weeks.getWeeks());
	    	    /*
	    	     * to find the remaining days we add the number of weeks it finds to the commencement date then get the number
	    	     * of days between that new date and the leaseExpires date. This is also done on the following cases.  
	    	     */
	    	    remainingDays = new BigDecimal(Days.daysBetween(leaseCommences.plus(weeks), leaseExpires).getDays() + 1);
	    	    BigDecimal seven = new BigDecimal(7);
	    	    
	    	    if(remainingDays == BigDecimal.ZERO)
	    	    {
	    	    	costBeforeExtras = standardPrice.multiply(differenceBetweenDates);
	    	    	extrasCost = extrasCost.multiply(differenceBetweenDates);
	    	    }else
	    	    {
	    	    	costBeforeExtras = (standardPrice.multiply(differenceBetweenDates)).add((standardPrice.divide(seven, mc)).multiply(remainingDays));
	    	    	extrasCost = (extrasCost.multiply(differenceBetweenDates)).add((extrasCost.divide(seven, mc)).multiply(remainingDays));
	    	    }
				break;	
			
	    	case "Per Month": 
	    		Months months = Months.monthsBetween(leaseCommences, leaseExpires);
	    		differenceBetweenDates = new BigDecimal(months.getMonths());
	    	    remainingDays = new BigDecimal(Days.daysBetween(leaseCommences.plus(months), leaseExpires).getDays() + 1);
	    	    
	    	    if(remainingDays == BigDecimal.ZERO)
	    	    {
	    	    	costBeforeExtras = standardPrice.multiply(differenceBetweenDates);
	    	    	extrasCost = extrasCost.multiply(differenceBetweenDates);
	    	    }else
	    	    {
	    	    	BigDecimal monthVal = new BigDecimal(12);
	    	    	// Below statement: costBeforeExtras = (standardPrice*differenceBetweenDates)+((standardPrice/365*12)*remainingDays)
	    	    	costBeforeExtras = (standardPrice.multiply(differenceBetweenDates)).add(((standardPrice.divide(yearVal, mc)).multiply(monthVal, mc)).multiply(remainingDays, mc));
	    	    	extrasCost = (extrasCost.multiply(differenceBetweenDates)).add(((extrasCost.divide(yearVal, mc)).multiply(monthVal, mc)).multiply(remainingDays, mc));
	    	    }
				break;	
			
	    	case "Per Year":
	    		Years years = Years.yearsBetween(leaseCommences, leaseExpires);
	    		differenceBetweenDates = new BigDecimal(years.getYears());
	    	    remainingDays = new BigDecimal(Days.daysBetween(leaseCommences.plus(years), leaseExpires).getDays() + 1);

	    	    if(remainingDays == BigDecimal.ZERO)
	    		{
	    			costBeforeExtras = standardPrice.multiply(differenceBetweenDates);
	    	    	extrasCost = extrasCost.multiply(differenceBetweenDates);
	    		}else
	    		{
	    			costBeforeExtras = (standardPrice.multiply(differenceBetweenDates)).add((standardPrice.divide(yearVal, mc)).multiply(remainingDays, mc));
	    			extrasCost = (extrasCost.multiply(differenceBetweenDates)).add((extrasCost.divide(yearVal, mc)).multiply(remainingDays, mc));
	    		}
	    		break;
				
			default: //defaults to days
				differenceBetweenDates = new BigDecimal(Days.daysBetween(leaseCommences, leaseExpires).getDays() + 1);
	    		costBeforeExtras = standardPrice.multiply(differenceBetweenDates);
				extrasCost = extrasCost.multiply(differenceBetweenDates);
				break;	
    	}
		total = costBeforeExtras.add(extrasCost);
		
		//set scale on prices so to represent them as a currency
		costBeforeExtras = costBeforeExtras.setScale(2, RoundingMode.CEILING);
		extrasCost = extrasCost.setScale(2, RoundingMode.CEILING);
		total = total.setScale(2, RoundingMode.CEILING);
		
		//set the costs onto the asset proposal
		assetProposal.setChargePeriodId(chargePeriodId);
		assetProposal.setStandardCost(standardPrice);
		assetProposal.setPeriodicCost(costBeforeExtras);
		assetProposal.setTotalProposedCost(total);
		
		//store the results in map and return that map to javascript
		Map<String, BigDecimal> results = new HashMap<String, BigDecimal>();
		results.put("costBeforeExtras", costBeforeExtras);
		results.put("extrasCost", extrasCost);
		results.put("proposalGrandTotal", total);
		
		return results;
	}
}
