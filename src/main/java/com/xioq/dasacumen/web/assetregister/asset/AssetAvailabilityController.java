package com.xioq.dasacumen.web.assetregister.asset;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.assetregister.AssetAvailabilitySearch;
import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.assetregister.AssetProposalSearch;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.constants.SalesConstants;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Controller to search on asset data for specific requirements such as assets that are available
 * for leasing etc... XXX More to follow!
 * 
 * @author MWalsh
 *
 */

@Controller
@RequestMapping("/assetAvailability")
public class AssetAvailabilityController 
{
	@Autowired
	private CRUDService crudService;
	
	@RequestMapping(value = "")
	public ModelAndView salesLandingScreen()
	{
		ModelAndView result = new ModelAndView("sales.home");
		AssetProposalSearch assetProposalSearchParams = new AssetProposalSearch();
		SearchResult<AssetProposal> assetProposal = crudService.generalSearch(assetProposalSearchParams);
		
		
		result.addObject("proposalList", assetProposal.getResult());
		result.addObject("searchTotalCount", assetProposal.getTotalCount());
		
		return result;
	}

	/**
	 * Forwards to the availability asset search screen.
	 * @param assetAvailabilitySearch
	 * @return
	 */
	@RequestMapping(value = "/full", method = RequestMethod.GET)
	public ModelAndView fullSearchStart(@ModelAttribute AssetAvailabilitySearch assetAvailabilitySearch, HttpSession session, SessionStatus status)
	{
		ModelAndView result = new ModelAndView("asset.availability.search");
		//Always clear proposal from session:
		status.setComplete();
		session.removeAttribute("assetProposal");
		return result;
	}
	
	/**
	 * Performs a basic asset search returning all available assets in no particular order.
	 * 
	 * @param assetSearchParams
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/searchAvailabileAssets", method = RequestMethod.POST)
	public ModelAndView availabilitySearchStart(@RequestParam(value="query") String query,
												@RequestParam(value="client_Id") Long clientId,
												@RequestParam(value="from_date") String userFrom,
												@RequestParam(value="to_date") String userTo) throws ParseException
	{
		ModelAndView result = new ModelAndView("asset.availability.searchResults");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		
		DateTime from = formatter.parseDateTime(userFrom);	
		DateTime to = formatter.parseDateTime(userTo);	
		
		System.out.println("From: "+from+" To: "+to);
		
		//TODO ADD ORDER BY BEST AVAILABLE!
		
		AssetAvailabilitySearch availableAssetsSearch = new AssetAvailabilitySearch();
		availableAssetsSearch.setAvailableDates(query, from.toDate(), to.toDate());
		SearchResult<Assets> assets = crudService.generalSearch(availableAssetsSearch);
		
		result.addObject("assets", assets.getResult());
		result.addObject("clientId", clientId);
		result.addObject("fromDate", userFrom);
		result.addObject("toDate", userTo);
		result.addObject(SalesConstants.AVAILABLE,SalesConstants.AVAILABLE);
		return result;
	}
	
	@RequestMapping(value = "/searchNotAvailableAssets", method = RequestMethod.POST)
	public ModelAndView notAvailableAssets(@RequestParam(value="query") String query,
											@RequestParam(value="client_Id") Long clientId,
										   @RequestParam(value="from_date") String userFrom,
										   @RequestParam(value="to_date") String userTo) throws ParseException
	{
		ModelAndView result = new ModelAndView("asset.availability.searchResults");
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		
		DateTime from = formatter.parseDateTime(userFrom);	
		DateTime to = formatter.parseDateTime(userTo);	
		
		//TODO ADD ORDER BY BEST AVAILABLE!
		
		AssetAvailabilitySearch availableAssetsSearch = new AssetAvailabilitySearch();
		availableAssetsSearch.setNotAvailableDates(query, from.toDate(), to.toDate());
		SearchResult<Assets> assets = crudService.generalSearch(availableAssetsSearch);
		
		result.addObject("assets", assets.getResult());
		result.addObject("clientId", clientId);
		result.addObject("fromDate", userFrom);
		result.addObject("toDate", userTo);
		result.addObject("toDate", userTo);
		result.addObject("unavailable",SalesConstants.UNAVAILABLE);
		return result;
	}
	
	@RequestMapping(value = "/detailAvailability", method = RequestMethod.GET)
	public ModelAndView detailAvailability(String query, @ModelAttribute AssetAvailabilitySearch assetAvailabilitySearch)
	{
		ModelAndView result = new ModelAndView("asset.availability.detail");
		
		//@TODO XXX
		
		return result;
	}
}
