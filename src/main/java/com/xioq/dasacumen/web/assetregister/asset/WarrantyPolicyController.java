package com.xioq.dasacumen.web.assetregister.asset;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.utilities.StringUtil;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.WarrantyPolicy;
import com.xioq.dasacumen.model.common.AssetWarranty;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.service.CRUDService;

/**
 * CRUD controller for WarrantyPolicy data held on the asset indemnities tab.
 * 
 * @author BenWorsley
 *
 */
@Controller
@RequestMapping("/warrantyPolicy")
public class WarrantyPolicyController 
{
	
	@Autowired
	private CRUDService someService;
	
	@RequestMapping("")
	public ModelAndView start()
	{
		
		return null;
	}
	
	@RequestMapping(value = "createWarrantyPolicy", method = RequestMethod.POST)
	public ModelAndView createPolicyFromIndemnities(@ModelAttribute("warrantyPolicy")WarrantyPolicy warrantyPolicy, 
			AssetWarranty warrantyPolicyLink, BindingResult result, HttpSession session) 
	{
		Party supplier = null;
		
		//get the warranty type from session
		Assets asset = (Assets) session.getAttribute("asset");
		if(null!=asset.getWarranty() && null!=asset.getWarranty().getWarrantyPolicy() && null!=asset.getWarranty().getWarrantyPolicy().getTypeId()) 
		{
			warrantyPolicy.setTypeId(asset.getWarranty().getWarrantyPolicy().getTypeId());
		}
		
		if(!StringUtil.isEmpty(warrantyPolicy.getSupplierId())) 
		{
			supplier = someService.retrieve(Party.class, warrantyPolicy.getSupplierId());
		}
		else { //TODO XXX : remove this once validation arrives!
			supplier = someService.retrieve(Party.class, 1L);
		}
		//Default not null values, only here until we have validation.
		if (StringUtil.isEmpty(warrantyPolicy.getSupplier())) warrantyPolicy.setSupplier(supplier);
		if (StringUtil.isEmpty(warrantyPolicy.getTenantId())) warrantyPolicy.setTenantId(1);
		if (StringUtil.isEmpty(warrantyPolicy.getCommencementDate())) warrantyPolicy.setCommencementDate(new Date());
		if (StringUtil.isEmpty(warrantyPolicy.getExpiryDate())) warrantyPolicy.setExpiryDate(new Date());
		if (StringUtil.isEmpty(warrantyPolicy.getPolicyNumber())) warrantyPolicy.setPolicyNumber("1");
		
		someService.create(warrantyPolicy);
		
		ModelAndView results = new ModelAndView("redirect:../assetController/indemnities?warrantyPolicyId=" + warrantyPolicy.getId());
		return results;
	}
	
	@Deprecated
	@RequestMapping(value = "/saveWarrantyPolicy", method = RequestMethod.POST)
	public @ResponseBody
	String processedAjaxID(@ModelAttribute("warrantyPolicy") WarrantyPolicy warrantyPolicy, @ModelAttribute("asset") Assets asset, HttpSession session) {
		String response = null;
		
		//TEMP DEFAULT NOT NULL DATA SETUP TO BE REMOVED!
		
		Party warrantySupplier = someService.retrieve(Party.class, 1L);
		warrantyPolicy.setSupplier(warrantySupplier);
		warrantyPolicy.setTenantId(1);
		warrantyPolicy.setCommencementDate(new Date());
		warrantyPolicy.setExpiryDate(new Date());
		warrantyPolicy.setPolicyNumber("1");
		//TODO XXX EJ :GET THESE DEFAULT VALUES PASSED IN FROM SCREEN, ESPECIALLY THE DRAG/DROP SUPPLIER.
		
		someService.create(warrantyPolicy);

		if (null != warrantyPolicy.getId()) 
		{
			response = warrantyPolicy.getId().toString();
		}
		
		return response;
	}
	
	
	private void prepareIndemnitiesView(ModelAndView mv) {
		UserData searchExample = new UserData();
		searchExample.setTenantId(1);

		// Insurance types - fully comp, 3rd party, etc
		searchExample.setUserDataTypeId(15L);
		mv.addObject("insuranceTypes", someService.findByExample(searchExample));

		// Warranty types
		searchExample.setUserDataTypeId(17L);
		mv.addObject("warrantyTypes", someService.findByExample(searchExample));
	}

}
