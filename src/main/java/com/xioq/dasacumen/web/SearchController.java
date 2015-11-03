package com.xioq.dasacumen.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.service.CRUDService;

@Controller
@RequestMapping("/search")
@Deprecated
public class SearchController
{
	@Autowired
	private CRUDService crudService;
	
	@RequestMapping(value = "/scratchpadSearch", method = RequestMethod.GET)
	public ModelAndView scratchpadSearch(@RequestParam(value="type") String type,
									@RequestParam(value="query") String query,
									@RequestParam(value="offset") Integer offset,
									@RequestParam(value="limit") Integer limit){
		
		ModelAndView result = new ModelAndView();
		String order_by[] = new String[] {"name"};
		String search_by = "name";
		return result;
	}
	
	@RequestMapping(value = "/generalSearch", method = RequestMethod.GET)
	public ModelAndView generalSearch(@RequestParam(value="order_by") String[] order_by,
									@RequestParam(value="type") String type,
									@RequestParam(value="query") String query,
									@RequestParam(value="search_by") String search_by,
									@RequestParam(value="offset") Integer offset,
									@RequestParam(value="limit") Integer limit)
	{
		ModelAndView result = new ModelAndView();
		return result;
	}
}
