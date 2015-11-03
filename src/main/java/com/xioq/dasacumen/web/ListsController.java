package com.xioq.dasacumen.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.EntityList;
import com.xioq.dasacumen.model.EntityListContent;
import com.xioq.dasacumen.model.assetregister.AssetSearch;
import com.xioq.dasacumen.model.constants.EntityType;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This controller is mapped to the location where the user will interact whilst
 * logged in.
 * 
 * @author Stephen Elliott
 */
@Controller
@RequestMapping("/list")
public class ListsController
{

	@RequestMapping("/myList")
	public ModelAndView myListStart()
	{

			EntityList usersList = new EntityList();
			usersList.setUserId(1L);
			List<EntityList> usersLists = someService.findByExample(usersList);
			
			ModelAndView mv = new ModelAndView("page.myList.myList"); 
			mv.addObject("usersLists", usersLists);
			return mv;
		
	}
	/*
	 * The @Autowired annotation means that someService will be instantiated
	 * only once across the whole program
	 * by Spring. This is much more efficient that creating a new one for each
	 * controller.
	 */
	@Autowired
	public CRUDService someService;

	/**
	 */
	@RequestMapping("")
	public ModelAndView viewLists()
	{
		EntityList usersList = new EntityList();
		usersList.setUserId(1L);
		List<EntityList> usersLists = someService.findByExample(usersList);
		
		ModelAndView mv = new ModelAndView("view.lists.modal");
		mv.addObject("usersLists", usersLists);
		mv.addObject("someMessage", "hello");
		return mv;
	}

	/**
	 * Displays a read only detailed view of a particular asset.
	 * @param assetId The primary key of the asset
	 */
	@RequestMapping("/list")
	public ModelAndView viewListContents(Long listId)
	{
		
		ModelAndView result = new ModelAndView();
		SearchResult<?> searchResult;
		
		// Retrieve list from database
		EntityList el = someService.retrieve(EntityList.class, listId);
		
		// Check which type it is in the list and compare it to the entity types.
		// TODO this needs to be added to.
		switch (EntityType.valueOf(el.getEntityType().toUpperCase())) {
			case ASSETS :
				AssetSearch assetSearch = new AssetSearch();
				assetSearch.setListId(listId);
				searchResult = someService.generalSearch(assetSearch);
				result.setViewName("list.content.assets");
				// TODO Need to change object name to search results
				// result.addObject("searchResults", searchResult.getResult());
				result.addObject("assets", searchResult.getResult());
				break;
			default:
				throw new RuntimeException("Unsupported list search type");
		}

		result.addObject("searchTotalCount", searchResult.getTotalCount());
		result.addObject("entityList",el);

		return result;
	}
	
	@RequestMapping("/createView")
	public ModelAndView createNewList(Long listId, String entityType)
	{
		EntityList usersList = new EntityList();
		usersList.setUserId(1L);
		List<EntityList> usersLists = someService.findByExample(usersList);
		
		ModelAndView mv = new ModelAndView("view.createList.modal");
		mv.addObject("usersLists", usersLists);
		mv.addObject("someMessage", "hello");
		return mv;
	}
	
	/**
	 * Deletes a list item from a users list updates the database and sends 
	 * the new list to the view for a view/page reload
	 * @param entityList
	 * @param entityIds
	 * @return
	 */	
	@RequestMapping(value="/removeFromList")
	public String removeFromList(@ModelAttribute("removeFromListForm") EntityList entityList, Long[] entityIds)
	{
		entityList = someService.retrieve(EntityList.class, entityList.getId());
		Set<EntityListContent> elcs = entityList.getEntityListContents();
		
		for (Iterator<EntityListContent> iterator = elcs.iterator(); iterator.hasNext();)
		{
			EntityListContent entityListContent = iterator.next();
			
			for (Long anEntityId : entityIds)
			{
				if(entityListContent.getEntityId().equals(anEntityId))
				{
					iterator.remove();
					break;
				}
			}
		}
		
		someService.update(entityList);
		
		return "redirect:list?listId=" + entityList.getId();
	}
	
	
	@RequestMapping(value="/addToList", method = RequestMethod.GET)
	public @ResponseBody String addToList(EntityList el, @RequestParam Long[] entityIds)
	{	
		Set<EntityListContent> elcs = new HashSet<EntityListContent>(0);
		
		/*DONT re-use this code its a temporary solution to fix a bug on the front-end 
		  to remove duplicate enitityIds as there is a constraint on the 
		  database
		*/
		List<Long> currentAssetIds = new ArrayList<Long>();
		Set<Long> entityIdsSet = new HashSet<Long>(Arrays.asList(entityIds));
		
		if(el.getId() != null){
			
			EntityList currentList= someService.retrieve(EntityList.class, el.getId());
			Set<EntityListContent> currentEntityContent = currentList.getEntityListContents();
			
			for(Iterator<EntityListContent> iterator = currentEntityContent.iterator(); iterator.hasNext();) {
				currentAssetIds.add(iterator.next().getEntityId());
			}
		}
		entityIdsSet.removeAll(currentAssetIds);
		
		for(Iterator<Long> iterator = entityIdsSet.iterator(); iterator.hasNext();) {
			EntityListContent elc = new EntityListContent();
			elc.setEntityList(el);
			elc.setEntityId(iterator.next());
			elcs.add(elc);
		}
		el.setEntityListContents(elcs);
		
		someService.update(el);
		
		return "success";
	}	
	
	@RequestMapping(value = "/createNewList", method = RequestMethod.POST)
	public @ResponseBody String createListModal(@ModelAttribute EntityList entityList, Long[] entityIds) 
	{
		entityList.setUserId(1L);
		
		Set<EntityListContent> elcs = new HashSet<EntityListContent>(0);
		
		/*DONT re-use this code its a temporary solution to fix a bug on the front-end 
		  to remove duplicate enitityIds as there is a constraint on the 
		  database
		*/
		Set<Long> entityIdsSet = new HashSet<Long>(Arrays.asList(entityIds));
		
		for(Iterator<Long> iterator = entityIdsSet.iterator(); iterator.hasNext();) {
			EntityListContent elc = new EntityListContent();
			elc.setEntityList(entityList);
			elc.setEntityId(iterator.next());
			elcs.add(elc);
		}
		
		entityList.setEntityListContents(elcs);
		
		someService.create(entityList);
//		ModelAndView result = new ModelAndView("redirect:/das/assetRegister"); //XXX add a redirect to list view (search for redirect:)
//		result.addObject("entityList", entityList);
		return "SUCCESS";
		
	}
	/**
	 * Deletes the entire list and all its contents. If successful will redirect to the 
	 * myLists page.
	 * @param el The list to delete
	 */
	@RequestMapping("/deleteList")
	public String deleteList(EntityList el)
	{
		
		// XXX Need to change. Should not retrieve before an update or delete. 
		// But need to sort the cascade options on the hibernate mappings
		
		if( el.getId()!=null)
		{
			el = someService.retrieve(EntityList.class, el.getId());
			someService.delete(el);
		}
		else if(el.getId() ==null)
		{
			System.out.println("list was not selected");
		}
		return "redirect:myList";
	}
	
}