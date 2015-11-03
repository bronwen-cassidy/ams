package com.xioq.dasacumen.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.service.CRUDService;

/**
 * Landing screen controller for the Resources module
 * @author Ashley
 */
@Controller
@RequestMapping("/resources")
public class ResourcesController
{
	@Autowired
	private CRUDService crudService;
	
	/**
	 * Landing screen for the asset register module
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView resourceHome()
	{
//		List<Assets> assets = someService.retrieveAll(Assets.class);
				
		ModelAndView result = new ModelAndView("resource.home");
		
		return result;
	}
	
}
