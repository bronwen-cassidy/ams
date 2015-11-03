package com.xioq.dasacumen.web.assetregister.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.assetregister.AssetSearch;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.service.CRUDService;

@Controller
@RequestMapping("/assetSearch")
public class AssetSearchController 
{
	@Autowired
	private CRUDService crudService;

	/**
	 * Used to set up the full asset search screen.
	 * @param assetSearchParams
	 * @return
	 */
	@RequestMapping(value = "/full", method = RequestMethod.GET)
	public ModelAndView fullSearchStart(@ModelAttribute AssetSearch assetSearch)
	{
		ModelAndView result = new ModelAndView("asset.register.search");
		return result;
	}
	
	/**
	 * The scratchpad asset search only allows the user to enter a single search field which 
	 * is mapped to the query parameter. But other parameters can be pulled in from the main 
	 * screen and these are mapped into the AssetSearch parameter. These include the paging info.
	 * 
	 * @param query
	 * @param assetSearchParams
	 * @return
	 */
	@RequestMapping(value = "/scratchpadSearch", method = RequestMethod.GET)
	public ModelAndView scratchpadSearch(String query, AssetSearch assetSearchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.assetSearch.results");
	
		assetSearchParams.setName(query);
		SearchResult<Assets> assetsResults = crudService.generalSearch(assetSearchParams);
		
		result.addObject("itemSearchResults", assetsResults.getResult());
		result.addObject("searchTotalCount", assetsResults.getTotalCount());
		
		return result;
	}
	
	/**
	 * Asset search displaying the results in the main page. This accepts requests from the simple
	 * search box within the utility bar, as well as the full asset search screen0.
	 * @param query Single query string that is mapped to the asset name (at the moment)
	 * @param assetSearchParams 
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView quickSearch(String query)
	{
		ModelAndView result = new ModelAndView("asset.register.home");
		
		AssetSearch assetSearchParams = new AssetSearch();
		assetSearchParams.setName(query);
		
//		assetSearchParams.setFirstResult(offset);
//		assetSearchParams.setMaxResults(limit);

		SearchResult<Assets> assets = crudService.generalSearch(assetSearchParams);
		result.addObject("assets", assets.getResult());
		result.addObject("searchTotalCount", assets.getTotalCount());

		return result;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView fullSearch(String query, AssetSearch assetSearchParams)
	{
		ModelAndView result = new ModelAndView("asset.register.home");
		
		assetSearchParams.setName(query);
		//XXX - Implement below restrictions & decide if 'query' will be required.
//		assetSearchParams.setFirstResult(offset);
//		assetSearchParams.setMaxResults(limit);

		SearchResult<Assets> assets = crudService.generalSearch(assetSearchParams);
		result.addObject("assets", assets.getResult());
		result.addObject("searchTotalCount", assets.getTotalCount());

		return result;
	}
}