package com.xioq.dasacumen.web.assetregister;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.Group;
import com.xioq.dasacumen.model.GroupAsset;
import com.xioq.dasacumen.model.assetregister.AssetSearch;
import com.xioq.dasacumen.model.common.GroupsSearch;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This controller is mapped to the location where the user will interact whilst
 * logged in.
 * 
 * @author Ashley Smith
 */
@Controller
@RequestMapping("/group")
public class GroupsController
{

	/*
	 * The @Autowired annotation means that someService will be instantiated
	 * only once across the whole program
	 * by Spring. This is much more efficient that creating a new one for each
	 * controller.
	 */
	@Autowired
	private CRUDService someService;

	/*
	 * This function returns a list of groups for the user with the modal view
	 */
	@RequestMapping("")
	public ModelAndView viewGroupsInModal()
	{
		Group usersGroup = new Group();		
		List<Group> usersGroups = someService.findByExample(usersGroup);
		
		ModelAndView mv = new ModelAndView("view.groups.modal");
		mv.addObject("usersGroups", usersGroups);
		return mv;
	}
	
	/*
	 * This function returns a list of groups for the user with the sarchResultsView view
	 */
	@RequestMapping("groups")
	public ModelAndView viewGroups()
	{
		Group usersList = new Group();
		List<Group> usersLists = someService.findByExample(usersList);
		
		ModelAndView mv = new ModelAndView("page.groups.groups"); 
		mv.addObject("usersLists", usersLists);
		return mv;
	}
	/*
	 * This function returns a list of contents a group contains based on a group id
	 */
	@RequestMapping("/groupContent")
	public ModelAndView viewGroupContents(Long groupId)
	{
		ModelAndView result = new ModelAndView("group.content.assets");
		SearchResult<?> searchResult;
		
		// Retrieve group from database
		Group group = someService.retrieve(Group.class, groupId);
		
		
		AssetSearch assSearch = new AssetSearch();
		assSearch.setGroupId(groupId);
		
		searchResult = someService.generalSearch(assSearch);
		
		result.addObject("assets",searchResult.getResult());
		result.addObject("searchTotalCount", searchResult.getTotalCount());
		result.addObject("group",group);

		return result;
	}
	
	/*
	 * The following code executes when you select add to group on the context menu (modal)
	 * We create a group object and set it an ID
	 * We make a list of groups and add the group object to that list
	 * The url given to the ModelAndView opens the tiles-das.xml file which then sends us to the create group jsp
	 * The list of groups is added to the ModelAndView
	 */
	@RequestMapping("/createGroup")
	public ModelAndView createView(Long groupId)
	{
		Group groupsList = new Group();
		List<Group> groupsLists = someService.findByExample(groupsList);
		
		ModelAndView mv = new ModelAndView("view.createGroup.modal");
		mv.addObject("usersGroups", groupsLists);
		mv.addObject("someMessage", "hello");
		return mv;

	}
	
	// TODO first step is getting it to add into the database.
	@RequestMapping(value="/addToGroup", method = RequestMethod.GET)
	public @ResponseBody String addToGroup(Group groupList,@RequestParam Long[] assetIds)
	{	
		Set<GroupAsset> gas = new HashSet<GroupAsset>(0);
		Set<Long> assetIdsSet = new HashSet<Long>(Arrays.asList(assetIds));
		List<Long> currentAssetIds = new ArrayList<Long>();
		
		if(groupList.getId() != null){
			
			Group currentGroup= someService.retrieve(Group.class, groupList.getId(), "groupAssets");
			Set<GroupAsset> currentGroupContent = currentGroup.getGroupAssets();
			
			for(Iterator<GroupAsset> iterator = currentGroupContent.iterator(); iterator.hasNext();) {
				currentAssetIds.add(iterator.next().getAssetId());
			}
		}
		assetIdsSet.removeAll(currentAssetIds);
		
		for(Iterator<Long> iterator = assetIdsSet.iterator(); iterator.hasNext();) {
			GroupAsset ga = new GroupAsset();
			ga.setGroup(groupList);
			ga.setAssetId(iterator.next());
			gas.add(ga);
		}
		groupList.setGroupAssets(gas);

		someService.update(groupList);
		
		return "success";
	}
	
	/*
	 * The following function removes specific items from a group, and sends the updated 
	 * group to the view for a a page reload.
	 * @param groupList
	 * @param assetIds
	 * @return
	 */
	@RequestMapping(value="/removeFromGroup")
	public String removeFromGroup(@ModelAttribute("removeForm") Group groupList, Long[] assetIds)
	{

		groupList = someService.retrieve(Group.class, groupList.getId(), "groupAssets");
		Set<GroupAsset> groupAssets = groupList.getGroupAssets();

		for (Iterator<GroupAsset> iterator = groupAssets.iterator(); iterator.hasNext();)
		{
			GroupAsset gAsset = iterator.next();
			
			for (Long anAssetId : assetIds)
			{
				/*System.out.println(gAsset.getAssetId() + "  " + anAssetId + "  " + gAsset.getAssetId().equals(anAssetId));*/
				if(gAsset.getAssetId().equals(anAssetId))
				{
					iterator.remove();
					break;
				}
			}
		}

		someService.update(groupList);
		
		return "redirect:groupContent?groupId=" + groupList.getId();
	}
	
	/*
	 * The following code executes when you hit the create button on the create button model
	 * We create a set of GroupAsset 's  
	 * We use a for loop which iterates through the groupIds array
	 * The for creates a new group asset and sets that group asset an id then adds it to the set for each item in that array 
	 */
	@RequestMapping(value = "/createNewGroup", method = RequestMethod.POST)
	public @ResponseBody String createGroupModel(@ModelAttribute Group group, Long[] assetIds) 
	{
		Set<GroupAsset> gas = new HashSet<GroupAsset>(0);
		
		Set<Long> assetIdsSet = new HashSet<Long>(Arrays.asList(assetIds));
		
		for(Iterator<Long> iterator = assetIdsSet.iterator(); iterator.hasNext();) {
			GroupAsset ga = new GroupAsset();
			ga.setGroup(group);
			ga.setAssetId(iterator.next());
			gas.add(ga);
		}
		
		group.setGroupAssets(gas);

		someService.create(group);
//		ModelAndView result = new ModelAndView("redirect:/das/assetRegister"); //XXX add a redirect to list view (search for redirect:)
		return "SUCCESS";
	}
	
	/*
	 * Deletes the entire group and all its contents. If successful will redirect to the 
	 * assetsRegister page.
	 * @param group The group to delete
	 */
	@RequestMapping("/deleteGroup")
	public String deleteGroup(Group group)
	{
		// XXX Need to change. Should not retrieve before an update or delete. 
		// But need to sort the cascade options on the hibernate mappings
		if(group.getId() != null)
		{	
			group = someService.retrieve(Group.class, group.getId());
			someService.delete(group);
		}
		else if(group.getId() == null)
		{
			System.out.println("select a group");
		}
		
		return "redirect:groups";
	}
	
	/**
	 * Scratchpad Group search.
	 * @return
	 * TODO: Not case sensitive
	 */
	@RequestMapping("/search/sp")
	public ModelAndView scratchpadSearch(String query, GroupsSearch searchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.groups.results");

		searchParams.setName(query);
		SearchResult<Group> groupResults = someService.generalSearch(searchParams);
		
		result.addObject("groupSearchResults", groupResults.getResult());
		result.addObject("searchTotalCount", groupResults.getTotalCount());

		return result;
		
	}
	
	/**
	 * Scratchpad Group preview search.
	 * @return
	 */
	@RequestMapping("/groupPreview")
	public ModelAndView scratchpadPreviewSearch(@RequestParam Map<String, String> attrs, GroupsSearch searchParams)
	{
		ModelAndView result = new ModelAndView("scratchpad.groups.preview.results");
		
		Map<String,String> searchMap = new HashMap<String,String>();
		ObjectMapper mapper = new ObjectMapper();
		try 
		{
			//convert JSON string to Map
			searchMap = mapper.readValue(attrs.get("attrs"), 
			    new TypeReference<HashMap<String,String>>(){});
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		searchParams.setAssetId(Long.parseLong(searchMap.get("assetId")));
		SearchResult<Group> groupResults = someService.generalSearch(searchParams);
		if(groupResults.getResult().isEmpty())
		{
			//What do we do???
		}
		else
		{
			//Foreach loop is used in the view to display all groups an asset belongs to
			result.addObject("groupItem", groupResults.getResult());
		}

		return result;
		
	}
	
}
