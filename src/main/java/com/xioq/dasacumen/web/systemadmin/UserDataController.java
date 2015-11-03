package com.xioq.dasacumen.web.systemadmin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.service.CRUDService;

/**
 * @author Ben Hargreaves
 *
 */
@Controller
@RequestMapping("/systemAdmin/userData")
public class UserDataController
{
	@Autowired
	private CRUDService crudService;

	@RequestMapping(value="/childData/{parentId}", method = RequestMethod.GET)
	public @ResponseBody Set<Map<String, Object>> loadChildUserData(@PathVariable Long parentId)
	{
		UserData parentUD = crudService.retrieve(UserData.class, parentId);
		Set<UserData> descendantUserData = parentUD.getDescendantUserData();
		
		Set<Map<String, Object>> result = new HashSet<Map<String, Object>>();
		for (UserData userData : descendantUserData)
		{
			HashMap<String, Object> tmp = new HashMap<String, Object>();
			tmp.put("id", userData.getId());
			tmp.put("name", userData.getName());
			result.add(tmp);
			
			System.out.println(userData.getName());
		}
		return result;
	}
}
