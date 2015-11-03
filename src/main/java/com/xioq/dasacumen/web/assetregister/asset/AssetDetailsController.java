package com.xioq.dasacumen.web.assetregister.asset;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.exceptions.NoIDException;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.service.CRUDService;

/**
 * Asset details landing screen controller.
 * @author Ashley
 */
@Controller
@RequestMapping("/assetDetails")
public class AssetDetailsController
{
	@Autowired
	private CRUDService crudService;
	
	/**
	 * Displays a read only detailed view of a particular asset.
	 * @param assetId The primary key of the asset
	 */
	@RequestMapping("")
	public ModelAndView assetDetails(Long assetId) throws NoIDException
	{
		
		// TODO What if no ID provided!?!?!?!
		
		ModelAndView result = new ModelAndView("asset.register.assetDetails");
		Assets asset = crudService.retrieve(Assets.class, assetId, "groups", "assetInsuranceTypes", "warranties");
		// TODO what if the asset what not found!?!?!?!
		Long supplierId = asset.getSupplierId();
		
		if (null== supplierId)
		{
		//@TODO - we should set a standard here to be implemented across all controllers :suggestion =
		//return specific exception containing detailed message.
			throw new NoIDException("PartyId was expected as trying to view specific record for detailed view.");
		}
		
		result.addObject("asset", asset);
		return result;
	}
}