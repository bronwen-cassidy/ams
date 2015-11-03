package com.xioq.dasacumen.web.assetregister.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Print screen controller for the print ability in an assets detailed view.
 * @author Ashley
 */
@Controller
@RequestMapping("/print")
public class AssetPrintController 
{
	@Autowired
	private CRUDService crudService;

	/**
	 * Used to set up the full asset search screen.
	 * @param assetSearchParams
	 * @return
	 */
	@RequestMapping(value = "assetPrint", method = RequestMethod.GET)
	public ModelAndView assetPrint(@ModelAttribute Assets asset, Long assetId)
	{
		ModelAndView results = new ModelAndView("asset.register.print");
		
		if (null != assetId) {
			
			asset = crudService.retrieve(Assets.class, assetId, Assets.ALL_EAGER);
			results.addObject("asset", asset);
			return results;		
		}
		
		results.addObject("asset", asset);
		return results;
	}
}