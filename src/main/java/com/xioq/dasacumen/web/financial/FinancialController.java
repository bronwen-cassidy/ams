package com.xioq.dasacumen.web.financial;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Landing screen controller for the Financial module
 * @author Stephen
 */
@Controller
@RequestMapping("/financial")
public class FinancialController
{
	@Autowired
	private CRUDService crudService;
	
	/**
	 * Landing screen for the financial register module. We default to view a list of suppliers, i.e. where the supplier is the 
	 * type_name from the party_type table.
	 *
	 * @return partyList - all entries in party table.
	 * 
	 * TODO- logic must determine which landing screen to go to. At the moment it just redirects to the supplier landing screen.
	 */
	
	@RequestMapping("")
	public String redirectController()
	{
		return "redirect:financial/supplier/register";
	}
	
}
