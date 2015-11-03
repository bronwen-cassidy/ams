/**
 * 
 */
package com.xioq.dasacumen.web.systemadmin;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.model.systemadmin.UserDataCat;
import com.xioq.dasacumen.model.systemadmin.UserDataTypes;
import com.xioq.dasacumen.service.CRUDService;

/**
 * @author Stephen Elliott
 *
 */
@Controller
@RequestMapping("/systemAdmin")
public class SystemAdminController
{
	Logger logger = LoggerFactory.getLogger(SystemAdminController.class);
	
	@Autowired
	private CRUDService crudService;
	
	/**
	 * The landing page for the system admin module
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView systemAdminHomePage()
	{
		ModelAndView mv = new ModelAndView("systemadmin.home");
		
		return mv;
	}
	
	/**
	 * The starting screen to modify user defined lists. For example, categories.
	 * This retrieves all the user data categories and types to allow the screen
	 * to create a tree like structure to find and modify any user data.
	 * @return
	 */
	@RequestMapping("/userData")
	public ModelAndView userData()
	{
		logger.error("logging message");
		
		ModelAndView mv = new ModelAndView("systemadmin.userData");
		List<UserDataCat> allCats = crudService.retrieveAll(UserDataCat.class);
		List<UserDataCat> topLevelCats = new ArrayList<UserDataCat>();
		for (UserDataCat userDataCat : allCats)
		{
			if(null == userDataCat.getParentCat())
			{
				topLevelCats.add(userDataCat);
			}
		}
		mv.addObject("topLevelUserDataCats", topLevelCats);
		

		return mv;
	}
	
	/**
	 * Returns a lists data for editing
	 */
	@RequestMapping(value = "/listItems/{userDataTypeId}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getList(@PathVariable Long userDataTypeId)
	{	
		ModelAndView result = new ModelAndView("systemadmin.editlist");
		
		UserData searchExample = new UserData();
		
		UserDataTypes editingType = crudService.retrieve(UserDataTypes.class, userDataTypeId);
		
		if(null == editingType)
			throw new RuntimeException("Failed to retrieve User Data Type for type ID:" + userDataTypeId);

		// Should just put the whole type class on the model but JSP currently just needed the name
		result.addObject("userDataTypeToEdit", editingType);
		result.addObject("userDataFieldName", editingType.getName());
		
		// TODO Need to set tenant ID properly
		searchExample.setTenantId(1);
		searchExample.setUserDataTypeId(userDataTypeId);
		
		List<UserData> listItems = crudService.findByExample(searchExample);
		
		result.addObject("listItems", listItems);
		
		return result;
	}
	
	/**
	 * Gets an empty list field
	 */
	@RequestMapping(value = "/listItem", method = RequestMethod.GET)
	public @ResponseBody ModelAndView getNewListItem()
	{
		ModelAndView result = new ModelAndView("systemAdmin/templates/newListItem");
		
		return result;
	}
	
	/**
	 * Adds a list field
	 * @return UserData
	 */
	@RequestMapping(value = "/listItem", method = RequestMethod.POST, consumes="application/json", produces="application/json")
	public @ResponseBody UserData addListField(@RequestBody UserData userData)
	{		
		// XXX Tenant ID to be replaced
		userData.setTenantId(1);	
		
		crudService.create(userData);
		
		return userData;
	}
	
	/**
	 * Deletes a list field
	 * @return Boolean
	 */
	@RequestMapping(value = "/listItem", method = RequestMethod.DELETE, consumes="application/json")
	public @ResponseBody boolean deleteListField(@RequestBody UserData userData)
	{
		// XXX Tenant ID to be replaced
		userData.setTenantId(1);
		
		crudService.delete(userData);
		
		return true;
	}
	
	/**
	 * Updates a list field
	 * @return Boolean
	 */
	@RequestMapping(value = "/listItem", method = RequestMethod.PUT, consumes="application/json")
	public @ResponseBody UserData updateListField(@RequestBody UserData userData)
	{		
		// XXX Tenant ID to be replaced
		userData.setTenantId(1);
		
		crudService.update(userData);
		
		return userData;
	}
	
	/**
	 * Updates all list fields
	 * @return Boolean
	 */
	@RequestMapping(value = "/listItems", method = RequestMethod.PUT, consumes="application/json")
	public @ResponseBody boolean updateListFields(@RequestBody UserDataForm userData)
	{		
//		crudService.update(userData);
		
		System.out.println(userData);
		System.out.println(userData.getFullList());
		
		for (UserData tmp : userData.getFullList())
		{
			System.out.println(tmp);
		}
		
		return true;
	}	
	
}
