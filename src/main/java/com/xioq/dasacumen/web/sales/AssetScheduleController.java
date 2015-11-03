package com.xioq.dasacumen.web.sales;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.assetregister.AssetAvailability;
import com.xioq.dasacumen.model.assetregister.AssetAvailabilitySearch;
import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.assetregister.AssetProposalSearch;
import com.xioq.dasacumen.model.assetregister.AssetSchedule;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.service.CRUDService;

@Controller
@RequestMapping("/assetSchedule")
public class AssetScheduleController
{
	@Autowired
	private CRUDService crudService;
	//  at the moment the asset schedule page doesn't exist asset schedule only works with the scratchpad, we may need a larger indepth view of the asset schedules later on. 
	@RequestMapping(value = "")
	public ModelAndView assetSchedule()
	{
		ModelAndView result = new ModelAndView("sales.home");
		AssetProposalSearch assetProposalSearchParams = new AssetProposalSearch();
		SearchResult<AssetProposal> assetProposal = crudService.generalSearch(assetProposalSearchParams);
		
		result.addObject("proposalList", assetProposal.getResult());
		result.addObject("searchTotalCount", assetProposal.getTotalCount());
		
		return result;
	}
	
	/**
	 * Scratchpad Asset Schedule preview search. TODO need to complete this method, take a look at supplier controller, keep in mind it shares similarities but are both different
	 * @return
	 */
	@RequestMapping("/assetSchedulePreview")
	public ModelAndView scratchpadPreviewSearch(@RequestParam Map<String, String> attrs, AssetAvailabilitySearch searchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.sales.preview.results");
		
		Map<String,String> searchMap = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			//convert JSON string to Map
			searchMap = mapper.readValue(attrs.get("attrs"), 
			    new TypeReference<HashMap<String,String>>(){});
		} catch (Exception e) {
			e.printStackTrace();
		}
		Assets asset=new Assets();
		if(searchMap.get("assetId") != null){
			asset = crudService.retrieve(Assets.class, Long.parseLong(searchMap.get("assetId")), "assetProposal");
		}
		
		Set<AssetProposal> scheduleResults = asset.getAssetProposal();
		if(scheduleResults.isEmpty())
		{
			// What do we do???
		}
		else
		{
			result.addObject("assetProposalItems", scheduleResults);
			return result;
		}
		return result;
		
	}
	
	/**
	 * 
	 * depending on the format
	 * 
	 *  TODO use asset availability, if Boolean available = true then 
	 *  	private String assetName;
	 *		private Date availabilityDate;
	 *		private Assets asset;
	 *		private Boolean available;
	 *		private Long durationNotAvailable;.
	 * 
	 * @param leaseStart
	 * @param leaseEnd
	 * @param chargePeriod
	 * @return result
	 */
	@RequestMapping(value = "/as", method = RequestMethod.GET)
	public AssetSchedule assetSchedule2(@RequestParam(value = "leaseStart") Date leaseStart,
			@RequestParam(value = "leaseEnd") Date leaseEnd,
			@RequestParam(value = "chargePeriod") String chargePeriod)
	{
		AssetSchedule result = new AssetSchedule();
		DateTime jodaFrom = new DateTime(result.getLeaseCommences());
		DateTime jodaTo = new DateTime(result.getLeaseExpires());
		Period period;
		AssetAvailability aa = new AssetAvailability();
		
		result.setLeaseCommences(leaseStart);
		result.setLeaseExpires(leaseEnd);
		aa.getAvailabilityDate();

		
		period = new Period(jodaFrom, jodaTo, PeriodType.standard());
		
		if(aa.getAvailable()==false && aa.getDurationNotAvailable()>1)
		{
//			result.getCurrentStatus();
		}
		else if(aa.getAvailable()==false)
		{
			
		}
		else
		{
			
		}
		
		return result;
	}

}
