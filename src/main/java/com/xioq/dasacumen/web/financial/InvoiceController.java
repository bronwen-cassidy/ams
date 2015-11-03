package com.xioq.dasacumen.web.financial;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.assetregister.AssetProposalSearch;
import com.xioq.dasacumen.model.constants.SimpleConstants;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Controller for invoices
 * @author BenWorsley
 */

@Controller
@RequestMapping("/invoice")
public class InvoiceController 
{
	@Autowired
	private CRUDService crudService;
	
	@RequestMapping("")
	public ModelAndView invoicePage()
	{
		ModelAndView result = new ModelAndView("finance.invoicing");
		
		AssetProposalSearch assetProposalSearchParams = new AssetProposalSearch();
		//assetProposalSearchParams.setCurrentStatus("accepted"); MAY BE NEEDED?
		SearchResult<AssetProposal> assetProposal = crudService.generalSearch(assetProposalSearchParams);

		result.addObject("proposalList", assetProposal.getResult());
		result.addObject("searchTotalCount", assetProposal.getTotalCount());
		
		return result;
	}
	
	/*
	 * This sends us to the invoice details page.
	 * Here we calculate the difference in time in either days, weeks, months, or years depending on which option was selected in the 
	 * invoice schedule drop down. We then send the result of the calculation to be used to limit the for loop in the jsp.
	 */
	@RequestMapping("invoiceDetails")
	public ModelAndView invoiceDetails(String proposalId) 
	{		
		ModelAndView results = new ModelAndView("finance.invoicing.details");
		
		AssetProposal assetProposal = new AssetProposal();
		
		int timePeriod = 0;
		if (null != proposalId) 
		{
	    	assetProposal = crudService.retrieve(AssetProposal.class, new Long (proposalId)); 
	    	
	    	DateTime leaseCommences;
			DateTime leaseExpires; 
	    	
	    	//get the invoicingScheduleId and concatenate into a string to be used in switch
	    	Long invoicingScheduleId = assetProposal.getInvoicingScheduleId();
	    	String invoicingScheduleString = "" + invoicingScheduleId;

	    	//Use switch statement to determine the necessary calculation. Cases commented out for now as only using weekly for demo
	    	switch (invoicingScheduleString) 
	    	{
//		    	case  //daily
//					leaseCommences = new DateTime(assetProposal.getAssetSchedule().getLeaseCommences());
//			    	leaseExpires = new DateTime(assetProposal.getAssetSchedule().getLeaseExpires());
//		
//			    	timePeriod = Days.daysBetween(leaseCommences, leaseExpires).getDays();
//			    	results.addObject("nameOfTimePeriod", "Day");
//					break;
	    	
		    	/*
	    		 * This case will calculate not-full weeks (eg. 3days) as 0 weeks so 3 weeks and 3 days would
	    		 * calculate to 3 weeks
	    		 */
//	    		case "169": //Weekly
//					leaseCommences = new DateTime(assetProposal.getAssetSchedule().getLeaseCommences());
//			    	leaseExpires = new DateTime(assetProposal.getAssetSchedule().getLeaseExpires());
//			    	
//			    	timePeriod = Weeks.weeksBetween(leaseCommences, leaseExpires).getWeeks();
//			    	results.addObject("nameOfTimePeriod", "Week");
//					break;
				
	    		/*
	    		 * This case will calculate not-full weeks (eg. 3days) as 1 week so 3 weeks and 3 days would
	    		 * calculate to 4 weeks
	    		 */
	    		case "169": //Weekly
					leaseCommences = new DateTime(assetProposal.getAssetSchedule().getLeaseCommences());
			    	leaseExpires = new DateTime(assetProposal.getAssetSchedule().getLeaseExpires());
			    	
			    	//get the amount of days between the two dates
			    	timePeriod = Days.daysBetween(leaseCommences, leaseExpires).getDays();
			    	
			    	/*if the number of days is divisible by 7 then timePeriod/7 will not have a remainder and will 
			    	 calculate the correct number of weeks. If there is a remainder (eg.3 days) we add one to the sum of timePeriod/7
			    	 to represent that as a whole week*/ 
			    	if((timePeriod%7) != 0)
			    	{
			    		timePeriod = (timePeriod/7)+1;
			    	}else
			    	{
			    		timePeriod = timePeriod/7;
			    	}
			    	
			    	results.addObject("nameOfTimePeriod", "Week");
					break;	
				
//				case  //monthly	
//					leaseCommences = new DateTime(assetProposal.getAssetSchedule().getLeaseCommences());
//			    	leaseExpires = new DateTime(assetProposal.getAssetSchedule().getLeaseExpires());
//		
//			    	timePeriod = Months.monthsBetween(leaseCommences, leaseExpires).getMonths();
//					results.addObject("nameOfTimePeriod", "Month");
//					break;
				
//				case  //yearly	
//					leaseCommences = new DateTime(assetProposal.getAssetSchedule().getLeaseCommences());
//			    	leaseExpires = new DateTime(assetProposal.getAssetSchedule().getLeaseExpires());
//		
//			    	timePeriod = Years.yearsBetween(leaseCommences, leaseCommences).getYears();
//					results.addObject("nameOfTimePeriod", "Year");
//					break;	
					
				default: //defaults to weekly	
					leaseCommences = new DateTime(assetProposal.getAssetSchedule().getLeaseCommences());
			    	leaseExpires = new DateTime(assetProposal.getAssetSchedule().getLeaseExpires());
			    	
			    	timePeriod = Days.daysBetween(leaseCommences, leaseExpires).getDays();
			    	
			    	if((timePeriod%7) != 0)
			    	{
			    		timePeriod = (timePeriod/7)+1;
			    	}else
			    	{
			    		timePeriod = timePeriod/7;
			    	}
			    	
			    	results.addObject("nameOfTimePeriod", "Week");
					break;	
	    	}	
		}
		
		//concatenate timePeriod in a string to be sent over to the jsp to tell the for loop when to stop
		String amountOfTime = "" + timePeriod;
		
		results.addObject("timePeriod", amountOfTime);
		results.addObject("assetProposal", assetProposal);
		results.addObject("readOnly", SimpleConstants.DEFAULT);
		
		return results;
	}

}
