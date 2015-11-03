package com.xioq.dasacumen.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.EntityList;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.PartyContactDetail;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This controller is mapped to the location where the user will interact whilst
 * logged in.
 * 
 * @author Ashley Smith
 */
@Controller
@RequestMapping("/email")
public class EmailController
{

	/*
	 * The @Autowired annotation means that someService will be instantiated
	 * only once across the whole program
	 * by Spring. This is much more efficient that creating a new one for each
	 * controller.
	 */
	@Autowired
	private CRUDService someService;
	/**
	 * Mapping for the Email Modal
	 */
	@RequestMapping("")
	public ModelAndView sendEmailAsset (Long partyId, Long assetId)
	{
		ModelAndView mv = new ModelAndView("view.sendEmailAsset.modal");

		if(null != partyId) // To allow for emails not to a specific party
		{
			Party partyToEmail = someService.retrieve(Party.class, partyId);
			if(null != partyToEmail)
			{
				PartyContactDetail pcd = partyToEmail.getPartyContactDetails();
				mv.addObject("emailAddress", pcd.getPartyEmail().getEmail().getEmailAddress());
			}
		}
		
		if(assetId != null)
		{
			//adding assets number and name data to object.
			Assets asset = someService.retrieve(Assets.class, assetId);
			mv.addObject("assetNumber", asset.getAssetNumber());
			mv.addObject("assetName", asset.getName());
		}
		
		EntityList usersGroups = new EntityList();
		usersGroups.setUserId(1L);
		return mv;
	}
	
	@RequestMapping("/sendEmail")//creating a separate email modal that doesn't pass in any data.
	public ModelAndView sendEmail(Long partyId, Long assetId)
	{
		ModelAndView mv = new ModelAndView("view.sendEmail.modal");
		return mv;
	}
}
