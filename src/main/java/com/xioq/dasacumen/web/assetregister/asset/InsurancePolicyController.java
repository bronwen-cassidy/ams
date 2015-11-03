package com.xioq.dasacumen.web.assetregister.asset;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.utilities.StringUtil;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.InsurancePolicy;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.service.CRUDService;

/**
 * CRUD controller for InsurancePolicy data held on the asset indemnities tab.
 * 
 * @author MWalsh
 *
 */
@Controller
@RequestMapping("/insurancePolicy")
public class InsurancePolicyController {

	private static final Logger LOG = LoggerFactory.getLogger(InsurancePolicyController.class);

	@Autowired
	private CRUDService someService;

	@ExceptionHandler({ BindException.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handle(BindException e) {
		LOG.warn("BindException", e);
		return e.getMessage();
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handleAll(Exception e) {
		LOG.warn("All others", e);
		return e.getMessage();
	}

	@ModelAttribute("insurancePolicy")
	@RequestMapping("")
	public ModelAndView start(@ModelAttribute InsurancePolicy insurancePolicy, Long id) {
		ModelAndView results = new ModelAndView("asset.indemnities");

		if (null != id) {
			insurancePolicy = someService.retrieve(InsurancePolicy.class, id);
			results.addObject("insurancePolicy", insurancePolicy);
		}
		
		if (null == insurancePolicy) {
			insurancePolicy = new InsurancePolicy();
		}
		results.addObject("insurancePolicy", insurancePolicy);

		return results;
	}

	/**
	 * Method to persist the insurancePolicy object into insurance_policies table.
	 * 
	 * @return - refreshed indemnitites view with new insurancePolicy record
	 * 
	 */
	@RequestMapping(value = "createInsurancePolicy", method = RequestMethod.POST)
	public ModelAndView createPolicyFromIndemnities(@ModelAttribute("insurancePolicy") InsurancePolicy insurancePolicy,
			BindingResult result, HttpSession session) {
		System.out.println("Submitting insurancePolicy: " + insurancePolicy);

		//TODO XXX : Validation.
		//TODO XXX GET THIS PARTY/SUPPLIER DEFAULT VALUES PASSED IN FROM SCREEN : THE DRAG/DROP SUPPLIER, CURRENTLY POPULATES partyId WITH DRAGGED ID NOT THE PARTY OBJECT.
		Party insuranceSupplier = null;
		if(!StringUtil.isEmpty(insurancePolicy.getPartyId())) {
			insuranceSupplier = someService.retrieve(Party.class, insurancePolicy.getPartyId());
		}
		else { //TODO XXX : remove this once validation arrives!
			insuranceSupplier = someService.retrieve(Party.class, 1L);
		}
			
		//Default not null values, only here until we have validation.
		if (StringUtil.isEmpty(insurancePolicy.getInsuranceSupplier())) insurancePolicy.setInsuranceSupplier(insuranceSupplier);
		if (StringUtil.isEmpty(insurancePolicy.getTenantId())) insurancePolicy.setTenantId(1);
		if (StringUtil.isEmpty(insurancePolicy.getCommencementDate())) insurancePolicy.setCommencementDate(new Date());
		if (StringUtil.isEmpty(insurancePolicy.getExpiryDate())) insurancePolicy.setExpiryDate(new Date());
		if (StringUtil.isEmpty(insurancePolicy.getPolicyNumber())) insurancePolicy.setPolicyNumber("1");

		someService.create(insurancePolicy);
		
		ModelAndView results = new ModelAndView("redirect:../assetController/indemnities?insurancePolicyId=" + insurancePolicy.getId());
		
		return results;
	}
	
	@Deprecated
	@RequestMapping(value = "/saveInsurancePolicy", method = RequestMethod.POST)
	public @ResponseBody
	String processedAjaxID(@ModelAttribute("insurancePolicy") InsurancePolicy insurancePolicy, @ModelAttribute("asset") Assets asset, HttpSession session) {
		String response = null;
		
		//TEMP DEFAULT NOT NULL DATA SETUP TO BE REMOVED!
		
		Party insuranceSupplier = someService.retrieve(Party.class, 1L);
		insurancePolicy.setInsuranceSupplier(insuranceSupplier);
		insurancePolicy.setTenantId(1);
		insurancePolicy.setCommencementDate(new Date());
		insurancePolicy.setExpiryDate(new Date());
		insurancePolicy.setPolicyNumber("1");
		//TODO XXX EJ :GET THESE DEFAULT VALUES PASSED IN FROM SCREEN, ESPECIALLY THE DRAG/DROP SUPPLIER.
		
		someService.create(insurancePolicy);

		if (null != insurancePolicy.getId()) 
		{
			response = insurancePolicy.getId().toString();
		}
		
		return response;
	}
}