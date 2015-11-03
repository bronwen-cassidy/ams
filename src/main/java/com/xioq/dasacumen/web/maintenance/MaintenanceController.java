package com.xioq.dasacumen.web.maintenance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.service.CRUDService;

/**
 * Landing screen controller for the Maintenance module
 * @author Ashley
 */
@Controller
@RequestMapping("/maintenance")
public class MaintenanceController
{
	@Autowired
	private CRUDService crudService;
	
	/**
	 * Landing screen for the maintenance module
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView maintenanceHome()
	{
//		List<Assets> assets = someService.retrieveAll(Assets.class);
				
		ModelAndView result = new ModelAndView("maintenance.home");
		
		return result;
	}
	
}
