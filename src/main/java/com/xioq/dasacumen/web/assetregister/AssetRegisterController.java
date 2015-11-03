package com.xioq.dasacumen.web.assetregister;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.Draft;
import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.model.assetregister.AssetSearch;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.constants.AssetTypeConstants;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Main controller for the Asset Register. Mostly just handling the links to the more specific actions.
 * @author Stephen
 */
@Controller
@RequestMapping("/assetRegister")
public class AssetRegisterController
{
	@Autowired
	private CRUDService someService;
	
	/**
	 * Landing screen for the asset register module
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView assetRegisterHome()
	{
		AssetSearch assetSearchParams = new AssetSearch();
		assetSearchParams.addSort("lastUpdatedDate", true);
		SearchResult<Assets> assets = someService.generalSearch(assetSearchParams );

		ModelAndView result = new ModelAndView("asset.register.home");
		result.addObject("assets", assets.getResult());
		result.addObject("searchTotalCount", assets.getTotalCount());
		return result;
	}
	
	/**
	 * Used to obtain the draft data from Drafts table for a specific user id. It will then convert the draft JSON
	 * data into a list of Asset objects which are then returned to the jsp. 
	 * 
	 * @param userId
	 * @param session
	 * @return drafts summary view. 
	 */
	@RequestMapping(value = "myDrafts", method = RequestMethod.GET)
	public ModelAndView myDraftsPage(Long userId, HttpSession session) {
		ModelAndView result = new ModelAndView("drafts.summary");
		
		if (session.getAttribute("userID") != null) {
			User user = someService.retrieve(User.class,
					(long) session.getAttribute("userID"));
			result.addObject("user", user);
		}

		Draft searchExample = new Draft();
		searchExample.setUserId(1L); // XXX get the userId and remove hard coded
										// reference.
		List<Draft> drafts = someService.findByExample(searchExample);

		List<Assets> assetList = new ArrayList<Assets>();

		// Loop through draft list, then convert JSON data into asset object &
		// add to list.
		for (Draft draft : drafts) {
			Assets retrievedDraftAsset = (Assets) draft.getObject();
			retrievedDraftAsset.setDraftId(draft.getId());
			assetList.add(retrievedDraftAsset);
		}
		
		//result.addObject("searchTotalCount", assetList.size()); Could use this to fix load more results bug on myDrafts
		result.addObject("draftList", assetList);
		// result.addObject("drafts", drafts);
		return result;
	}
	
	/**
	 * Save method called manually using the save details button of an asset.
	 * 
	 * @return back to view which was specified with the currentView param..
	 */
	@RequestMapping(value = "deleteDraft")
	public ModelAndView removeDraft(Long draftId) {
		
		if (null!=draftId) {
			Draft draft = someService.retrieve(Draft.class, draftId);
			someService.delete(draft);
		}
		// TODO Add in exception handling/logging

		ModelAndView results = new ModelAndView("redirect:../assetRegister/myDrafts");
		return results;
	}

	
	@RequestMapping(value = "/assetLandingSearch", method = RequestMethod.GET)
	public ModelAndView scratchpadSearch(@RequestParam(value="query") String query,
									@RequestParam(value="offset") Integer offset,
									@RequestParam(value="limit") Integer limit,
									@RequestParam(value="order_by") String order_by,
									@RequestParam(value="order_type") String order_type,
									HttpSession session){
		
		ModelAndView result = new ModelAndView("assetRegister/assetSearchTable");
		
		AssetSearch assetSearchParams = new AssetSearch();
		
		switch(query) {
			case  "":
				assetSearchParams.setName("");
				break;
			case AssetTypeConstants.LIFE_EXPECANCY_30_DAYS:
				//All assets with life expectancy less than 30 days.
				//Calculate todays date plus one month, then find all assets that have a life expectancy date within this date range.
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MONTH, +1);
				Date todayPlus1Month = cal.getTime();
				assetSearchParams.setEndOfLifeDateRange(new Date(), todayPlus1Month);
				break;
			case AssetTypeConstants.LIFE_EXPECANCY_CEASED:
				//All assets with life expectancy date that is older than today.
				assetSearchParams.setEndOfLifeDateRange(null, new Date());
				break;
			case AssetTypeConstants.CRITICAL_ASSET:
				assetSearchParams.setCriticalAsset(Boolean.TRUE);
				break;
			case AssetTypeConstants.CREATED_BY:
				if (session.getAttribute("userID") != null) {
					User user = someService.retrieve(User.class, (long) session.getAttribute("userID"));
					assetSearchParams.setCreatedBy(user.getUserName());
				}
				//TODO:  XXX - If user is empty then should not perform search!
				break;
		}
		
		assetSearchParams.setFirstResult(offset);
		assetSearchParams.setMaxResults(limit);
	
		SearchResult<Assets> assets = someService.generalSearch(assetSearchParams);
		result.addObject("assets", assets.getResult());
		result.addObject("searchTotalCount", assets.getTotalCount());		
		return result;
		
	}
	
}
