package com.xioq.dasacumen.web.assetregister.asset;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.lib.utilities.SortedSetUtil;
import com.xioq.dasacumen.lib.utilities.StringUtil;
import com.xioq.dasacumen.model.Draft;
import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.model.assetregister.AssetValidator;
import com.xioq.dasacumen.model.assetregister.Assets;
import com.xioq.dasacumen.model.common.InsurancePolicy;
import com.xioq.dasacumen.model.common.LeaseOutExtras;
import com.xioq.dasacumen.model.common.OtherSystemRef;
import com.xioq.dasacumen.model.common.Party;
import com.xioq.dasacumen.model.common.WarrantyPolicy;
import com.xioq.dasacumen.model.constants.AssetTypeConstants;
import com.xioq.dasacumen.model.constants.SimpleConstants;
import com.xioq.dasacumen.model.systemadmin.UserData;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This controller operates using session data for the Asset object. It is stored in session until the submit is performed on 
 * the summary tab or session is killed.
 * When any field in the asset object is changed it is automatically saved into the drafts table. To do this we pass 
 * the session object asset to the save on the draft. By doing this each controller method does not need to get the draft
 * record back each time from the DB as session data is what was persisted, so is more efficient when each tab is clicked
 * to simply use the session object. The JSON draft object is returned when the summary tab is selected before it creates
 * the Asset, after this creation the object is removed from session.
 *
 */
@Controller
@RequestMapping("/assetController")
@SessionAttributes({"asset","draft"})
public class AssetController {
	
	private static final Logger LOG = LoggerFactory.getLogger(AssetController.class);
	
	@Autowired
	private CRUDService someService;
	@Autowired
	private AssetValidator assetValidator;

	/**
	 * Exception handler to log binding exceptions. These usually result in a 400 (Bad Request) resonse but with
	 * no detail as to what is wrong. Hence need to log if first.
	 */
	@ExceptionHandler({BindException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public String handle(BindException e) throws BindException
	{
		LOG.warn("BindException", e);
	    throw e;
	}
	
	/**
	 * This controller relies on the asset being in the session. So if there is no session 
	 * an exception is thrown. Rather than show the error, redirect to the asset register.
	 * @param e
	 * @return
	 */
	@ExceptionHandler({HttpSessionRequiredException.class})
	public String handle(HttpSessionRequiredException e)
	{
		LOG.debug("HttpSessionRequiredException " + e.getMessage() + ".  Redirecting to asset register home page");
	    return "redirect:/das/assetRegister";
	}
	
	/**
	 * Entry point into creating or editing an asset. 
	 * @param asset
	 * @param assetId
	 * @param draftId
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView startEditOrCreate(@ModelAttribute Assets asset, Draft<Assets> draft, Long assetId, Long draftId)
	{
		ModelAndView results = new ModelAndView("redirect:assetController/general");
		draft = new Draft<Assets>(); //ensure session object is cleared.
		if (null != assetId) {
			asset = someService.retrieve(Assets.class, assetId, Assets.ALL_EAGER);
			results.addObject("asset", asset);
			return results;
		}
		else if(null != draftId){
			// assetID will only be set after creation or if we are editing, otherwise we will be using the draftId.
			draft = someService.retrieve(Draft.class, draftId);
			// Now we need to convert it to an asset for display.
			asset = draft.getObject();
			asset.setDraftId(draftId);
		}

		if (null==asset) {
			asset = new Assets();
		}
		
		results.addObject("asset", asset);
		results.addObject("draft", draft);
		
		return results;
	}
	
	
	
	@RequestMapping("general")
	public ModelAndView generalPage(@ModelAttribute("asset") Assets asset, HttpSession session) {
		
		ModelAndView result = new ModelAndView("asset.general");

		if (session.getAttribute("userID") != null) {
			User user = someService.retrieve(User.class,
					(long) session.getAttribute("userID"));
			result.addObject("user", user);
		}
		
		//checks if asset.getCriticalAsset is not empty so it does not break the create new asset buttons
		//checks if it is a critical asset then sends a warning message to the jsp 
		if(asset.getCriticalAsset() != null)
		{	
			if(asset.getCriticalAsset() && null!=asset.getId())
			{
				result.addObject("warningMessage", AssetTypeConstants.CRITICAL_ASSET_WARNING);
			}
		}		
		return result;
	}

	@RequestMapping("finance")
	public ModelAndView financePage(@ModelAttribute("asset") Assets asset, HttpSession session) {
		ModelAndView result = new ModelAndView("asset.finance");
		return result;
	}

	@RequestMapping("tenure")
	public ModelAndView leasePage(@ModelAttribute("asset")  Assets asset, HttpSession session) {
		ModelAndView result = new ModelAndView("asset.tenure");
		return result;

	}

	@RequestMapping("indemnities")
	public ModelAndView guaranteesPage(@ModelAttribute("asset")  Assets asset,
			Long insurancePolicyId, Long warrantyPolicyId) {
		ModelAndView result = new ModelAndView("asset.indemnities");
		
		//Retrieve list of existing policies for display/selection - currently uses supplier id for select query.
		InsurancePolicy searchExample = new InsurancePolicy();
		searchExample.setInsuranceSupplier(asset.getSupplier());
		result.addObject("insurancePolicyList", someService.findByExample(searchExample));
		
		WarrantyPolicy searchExample2 = new WarrantyPolicy();
		result.addObject("warrantyPolicyList", someService.findByExample(searchExample2));
		
		//This check is for when we have created an insurance policy and re-directed to here.
		if(null != insurancePolicyId && null != warrantyPolicyId)
		{
			InsurancePolicy currentInsurancePolicy = someService.retrieve(InsurancePolicy.class, insurancePolicyId);
			result.addObject("insurancePolicy", currentInsurancePolicy);
			
			WarrantyPolicy currentWarrantyPolicy = someService.retrieve(WarrantyPolicy.class, warrantyPolicyId);
			result.addObject("warrantyPolicy", currentWarrantyPolicy);
		}
		else if (null != insurancePolicyId) {
			InsurancePolicy currentInsurancePolicy = someService.retrieve(InsurancePolicy.class, insurancePolicyId);
			result.addObject("insurancePolicy", currentInsurancePolicy);
			
			WarrantyPolicy warrantyPolicy = new WarrantyPolicy();
			result.addObject("warrantyPolicy", warrantyPolicy);
		}
		else if(null != warrantyPolicyId)
		{
			WarrantyPolicy currentWarrantyPolicy = someService.retrieve(WarrantyPolicy.class, warrantyPolicyId);
			result.addObject("warrantyPolicy", currentWarrantyPolicy);
			
			InsurancePolicy insurancePolicy = new InsurancePolicy();
			result.addObject("insurancePolicy", insurancePolicy);
		}
		else {
			InsurancePolicy insurancePolicy = new InsurancePolicy();
			result.addObject("insurancePolicy", insurancePolicy);
			
			WarrantyPolicy warrantyPolicy = new WarrantyPolicy();
			result.addObject("warrantyPolicy", warrantyPolicy);
		}

		return result;
	}

	@RequestMapping("maintenance")
	public ModelAndView maintenancePage(@ModelAttribute("asset")  Assets asset, HttpSession session) {
		ModelAndView result = new ModelAndView("asset.maintenance");
		
		return result;
	}

	/**
	 * Get draft details from the DB, Populate the Asset object with the Draft
	 * JSON data via getObject method, Pass Asset object to summary tab for
	 * display and ready for submission via the post method.
	 * 
	 * @param assetId
	 * @param session
	 * @param draftId
	 * @return
	 */
	@RequestMapping(value = "summary", method = RequestMethod.GET)
	public ModelAndView summaryPage(@ModelAttribute("asset")  Assets asset) 
	{
		ModelAndView result = new ModelAndView("asset.summary");
		
		if(null != asset.getSupplierId() && (null == asset.getSupplier() || !asset.getSupplierId().equals(asset.getSupplier().getId())))
			asset.setSupplier(someService.retrieve(Party.class, asset.getSupplierId()));
		
		if(null != asset.getCustodianId() && (null == asset.getCustodian() || !asset.getCustodianId().equals(asset.getCustodian().getId()) ))
			asset.setCustodian(someService.retrieve(Party.class, asset.getCustodianId()));
		
		// Lease in user data fields
		if (null != asset.getLeaseIn() && (null != asset.getLeaseIn().getLeaseTypeId() && (null == asset.getLeaseIn().getLeaseType() || !asset.getLeaseIn().getLeaseTypeId().equals(asset.getLeaseIn().getLeaseType().getId()))))
		{
			asset.getLeaseIn().setLeaseType(someService.retrieve(UserData.class, asset.getLeaseIn().getLeaseTypeId()));
		}
		
		if (null != asset.getLeaseIn() && (null != asset.getLeaseIn().getChargePeriodId() && (null == asset.getLeaseIn().getChargePeriod() || !asset.getLeaseIn().getChargePeriodId().equals(asset.getLeaseIn().getChargePeriod().getId()))))
		{
			asset.getLeaseIn().setChargePeriod(someService.retrieve(UserData.class, asset.getLeaseIn().getChargePeriodId()));
		}
		
		if (null != asset.getLeaseIn() && (null != asset.getLeaseIn().getVatCodeId() && (null == asset.getLeaseIn().getVatCode() || !asset.getLeaseIn().getVatCodeId().equals(asset.getLeaseIn().getVatCode().getId()))))
		{
			asset.getLeaseIn().setVatCode(someService.retrieve(UserData.class, asset.getLeaseIn().getVatCodeId()));
		}
		
		// Lease out user data fields
		if (null != asset.getLeaseOut() && (null != asset.getLeaseOut().getLeaseTypeId() && (null == asset.getLeaseOut().getLeaseType() || !asset.getLeaseOut().getLeaseTypeId().equals(asset.getLeaseOut().getLeaseType().getId()))))
		{
			asset.getLeaseOut().setLeaseType(someService.retrieve(UserData.class, asset.getLeaseOut().getLeaseTypeId()));
		}
		
		if (null != asset.getLeaseOut() && (null != asset.getLeaseOut().getChargePeriodId() && (null == asset.getLeaseOut().getChargePeriod() || !asset.getLeaseOut().getChargePeriodId().equals(asset.getLeaseOut().getChargePeriod().getId()))))
		{
			asset.getLeaseOut().setChargePeriod(someService.retrieve(UserData.class, asset.getLeaseOut().getChargePeriodId()));
		}
		
		if (null != asset.getLeaseOut() && (null != asset.getLeaseOut().getVatCodeId() && (null == asset.getLeaseOut().getVatCode() || !asset.getLeaseOut().getVatCodeId().equals(asset.getLeaseOut().getVatCode().getId()))))
		{
			asset.getLeaseOut().setVatCode(someService.retrieve(UserData.class, asset.getLeaseOut().getVatCodeId()));
		}

		//If we have a warranty policy id set then manually get the rest of the data.
		if (null != asset.getWarranty() && null != asset.getWarranty().getWarrantyPolicyId())
		{
			asset.getWarranty().setWarrantyPolicy(someService.retrieve(WarrantyPolicy.class, 
					asset.getWarranty().getWarrantyPolicyId()));
		}
		
		//If we have an insurance policy id set then manually get the rest of the data.
		if (null != asset.getAssetInsurance() && null != asset.getAssetInsurance().getInsuranceTypePolicyLink()
				&& null!= asset.getAssetInsurance().getInsuranceTypePolicyLink().getInsurancePolicy() 
				&& null!= asset.getAssetInsurance().getInsuranceTypePolicyLink().getInsurancePolicy().getId())
		{
			asset.getAssetInsurance().getInsuranceTypePolicyLink().setInsurancePolicy(someService.retrieve(InsurancePolicy.class,
					asset.getAssetInsurance().getInsuranceTypePolicyLink().getInsurancePolicy().getId()));
		}
		
		return result;
	}

	/**
	 * This will only be called upon manually using the save a draft button of
	 * an asset. This is not really required as drafts save automatically using
	 * ajax HOWEVER the main purpose is to perform validation. This will persist
	 * the asset data to the drafts table.
	 * 
	 * @return back to view.
	 */
	@RequestMapping(value = "submitDraftAsset", method = RequestMethod.POST)
	public ModelAndView financePage(@ModelAttribute("asset")  Assets tempAsset) {
		// @TODO: validate object.

		Draft<Assets> draft;
		draft = new Draft<Assets>();
		draft.setName("Finance Test");
		draft.setUserId(1L);
		draft.setObject(tempAsset);

		// Now save Draft.
		someService.create(draft);
		ModelAndView result = new ModelAndView("asset.general");
		result.addObject("asset", tempAsset);
		return result;
	}
	
	/**
	 * Use this method to make a clone of another Asset. The cloned fields are defined in the Assets object
	 * by implementing the Cloneable interface.
	 * 
	 * XXX - Currently just used for creating a single cloned object - multiples maybe required at some point
	 * hence the method has the capability to crate multiples by simply providing the paramater 'numberToClone'
	 * when this method is called, without this param we will just create one new record.
	 * 
	 * @param assetId
	 * @return assetRegister view with cloned Asset.
	 */
	@RequestMapping(value = "cloneAsset")
	public ModelAndView cloneAsset(Long numberToClone, Long assetId) {
		Assets asset = null;
		//Get Asset from ID passed in....
		if (null != assetId) {
			asset = someService.retrieve(Assets.class, assetId);
			
			//Iterate around this number to create multiples.
			if (null!=numberToClone) {
				for (int i = 0; i < numberToClone; i++) {
					//Clone retrieved object....
					Assets clonedAsset = (Assets)asset.clone();
					someService.create(clonedAsset);
				}
			}
			else if (null==numberToClone) {
				Assets clonedAsset = (Assets)asset.clone();
				someService.create(clonedAsset);
			}

		}

		//Redirect to assetRegister controller landing screen now displaying the cloned asset.
		ModelAndView results = new ModelAndView("redirect:../assetRegister");
		return results;
	}

	/**
	 * Save method called manually using the save details button of an asset.
	 * This method can be called from any tab so in order to return/refresh
	 * to the correct screen after a save we pass in the 'tab' name.
	 * This is used to obtain which jsp to send the user back to.
	 * 
	 * @return back to view which was specified with the currentView param..
	 */
	@RequestMapping(value = "saveAsset")
	public ModelAndView saveAsset(@ModelAttribute("asset")  Assets asset,
			BindingResult result, String tab) {

//		@TODO XXX: we need a way of defining the errors to know what tab they belong to, i.e. so if a 
//		general tab error is triggered we can highlight the general tab in red.
		 assetValidator.validate(asset, result);
		 
		 if (result.hasErrors()) {
			 ModelAndView mv = new ModelAndView();
			 mv = getSpecificView(tab, asset, mv);

			 mv.addObject("errors", result);
			 return mv;
		 }
		 if (null!=asset.getId()) {
			someService.create(asset);
		 }
		
		 ModelAndView results = new ModelAndView();
		 results = getSpecificView(tab, asset, results);
		 results.addObject("asset", asset);
		 
		return results;
	}

	/**
	 * Method called using Ajax function which operates when a field is changed on the asset object. 
	 * It then will either proceed to save or update the draft(asset) data into the Drafts table 
	 * but ONLY if the asset id is not set.
 	 * OR 
 	 * If the asset ID is set then we are editing an existing asset so no longer considered a 
 	 * draft so the method then simply updates the assets details onto the session.
	 * 
	 * @param assetForm
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/saveDraftAsset", method = RequestMethod.POST)
	public @ResponseBody
	String saveDraft(@ModelAttribute("asset")  Assets assetForm, @ModelAttribute("draft") Draft<Assets> draft) 
	{
		String response = null;

		//If we have an asset ID then must be editing a record so update the asset table instead of the draft.
		if (null==assetForm.getId()) 
		{

			if (null != assetForm.getDraftId())//if the asset has a draft id assign that draftid to response 
			{
				response = assetForm.getDraftId().toString();
			}

			draft.setName(AssetTypeConstants.ASSET);
			draft.setUserId(1L); // @TODO: get user ID from logon.
			draft.setObject(assetForm); //Asset is the object from session so contains data from other tabs.

			// Now save Draft.
			someService.create(draft);

			if (null == assetForm.getDraftId() || assetForm.getDraftId() <= 0) // 1st change only will be 0 or empty, so now get the new draft ID obtained from save above.
			{
				// Value after first persist.
				response =  draft.getId().toString();
			}
			if (null!=response) {assetForm.setDraftId(Long.valueOf(response));}
		}
		return response;
	}

	/**
	 * Method to submit the final details to create an asset for the first time.
	 * This will utilise the saved draft version, convert it to an asset and
	 * create the asset. It will then delete the draft record it used for
	 * populating the asset.
	 * 
	 * @return - refreshed asset landing screen with new Asset record
	 *         displaying?
	 */
	@RequestMapping(value = "summary", method = RequestMethod.POST)
	public ModelAndView summaryPage(@ModelAttribute("asset")  Assets asset,
			BindingResult result, HttpSession session, SessionStatus status) {
		System.out.println("Submitting asset: " + asset);
		
		// XXX Fake default values: to be removed once mapping complete & DEMO PASSES, AS ADDS EXTRA SAFETY!
		if(null == asset.getTenantId())asset.setTenantId(1);
		if(null == asset.getSupplierId())asset.setSupplierId(1L);
		if(null == asset.getCustodianId())asset.setCustodianId(2L);
		if(null == asset.getCategoryId())asset.setCategoryId(1L);
		if(null == asset.getCountryId())asset.setCountryId(1L);
		if(null == asset.getDepreciationCodeId())asset.setDepreciationCodeId(1L);
		if(null == asset.getCompanyId())asset.setCompanyId(1L);
		if(null == asset.getDivisionId())asset.setDivisionId(1L);
		if(null == asset.getDepartmentId())asset.setDepartmentId(1L);
		if(null == asset.getLocationId())asset.setLocationId(1L);
		if(null == asset.getSiteId())asset.setSiteId(1L);
		if(null == asset.getAssetStatusId())asset.setAssetStatusId(1L);
		if(null == asset.getAssetNumberPart1Id())asset.setAssetNumberPart1Id(1L);
		if(null == asset.getAssetNumberPart2Id())asset.setAssetNumberPart2Id(1L);
		if(null == asset.getAssetNumberPart3Id())asset.setAssetNumberPart3Id(1L);
		// TODO This needs to be generated from a sequence based on the values
		// of the other 3 parts
		asset.setAssetNumberPart4(1);
		asset.setManufacturerId(1L);
		if (StringUtil.isEmpty(asset.getName())) {
			asset.setName("Default Name");
		}
		
		//TODO XXX : Validation should be performed first, however the above checks are being left in for extra security FOR THE DEMO ONLY!
		 assetValidator.validate(asset, result);
		 if (result.hasErrors()) {
			 ModelAndView mv = new ModelAndView("asset.summary");
			 mv.addObject("errors", result);
		 
			 return mv;
		 }
		 
		someService.create(asset);
		Draft draft=(Draft) session.getAttribute("draft");
		// Now delete the draft record if we have a draftId which we should.
		if (null!=draft) {
			someService.delete(draft);
		}
		
		status.setComplete();
		session.removeAttribute("asset");
		session.removeAttribute("draft");
		ModelAndView results = new ModelAndView("redirect:../assetRegister");
		return results;
	}

	@RequestMapping("DAS_Acumen")
	public String home() {
		return "redirect:/";
	}

	/**
	 * Return a specific view depending on the tab value passed in. This is called when the user
	 * manually uses the generic save button of an asset so we can return to the correct view.
	 * We also call the prepare views for general,finance & indemnities respectively.
	 * @param tab
	 * @return a string representation of a specific tile view pointing to a jsp.
	 */
	private ModelAndView getSpecificView(String tab, Assets asset, ModelAndView results)
	{
//		ModelAndView results = new ModelAndView();
		if (StringUtil.isEmpty(tab)) {
			return results = new ModelAndView("asset.general");
		}
		else {
			switch(tab) {
			case SimpleConstants.GENERAL:
				results = new ModelAndView("asset.general");
				break;
			case SimpleConstants.FINANCE:
				results = new ModelAndView("asset.finance");
				break;
			case SimpleConstants.TENURE:
				results = new ModelAndView("asset.tenure");
				break;
			case SimpleConstants.INDEMNITIES:
				InsurancePolicy searchExample = new InsurancePolicy();
				searchExample.setInsuranceSupplier(asset.getSupplier());
				results.addObject("insurancePolicyList", someService.findByExample(searchExample));
				
				WarrantyPolicy searchExample2 = new WarrantyPolicy();
				results.addObject("warrantyPolicyList", someService.findByExample(searchExample2));
				InsurancePolicy insurancePolicy = new InsurancePolicy();
				results.addObject("insurancePolicy", insurancePolicy);
				
				WarrantyPolicy warrantyPolicy = new WarrantyPolicy();
				results.addObject("warrantyPolicy", warrantyPolicy);
				results.setViewName("asset.indemnities");
				break;
			case SimpleConstants.MAINTENANCE:
				results = new ModelAndView("asset.maintenance");
				break;
			case SimpleConstants.SUMMARY:
				results = new ModelAndView("asset.summary");
				break;
			}
		}
		return results;
	}
	
	//Set the other system ref id and other system name onto the asset other system ref <set>
	@RequestMapping(value = ("/setOtherSystemRef"), method = RequestMethod.POST)
	public @ResponseBody void setOtherSystemRef(
			@ModelAttribute("asset")  Assets asset,
			@RequestParam(value="otherSystemId") String otherSysId,
			@RequestParam(value="otherSystemNameId") Long otherSysNameId){
		
		OtherSystemRef tempSysRef = new OtherSystemRef();
		tempSysRef.setOtherSystemId(otherSysId);
		tempSysRef.setOtherSystemNameId(otherSysNameId);
		
		asset.addOtherSystemRef(tempSysRef);
		
	}
	
	@RequestMapping(value = ("/removeOtherSystemRef"), method = RequestMethod.POST)
	public @ResponseBody void removeOtherSystemRef(
			@ModelAttribute("asset") Assets asset,
			@RequestParam(value="otherSystemId") String otherSysId,
			@RequestParam(value="otherSystemNameId") Long otherSysNameId){
		
		Set<OtherSystemRef> referenceSet = asset.getOtherSystemRefs();
		
		OtherSystemRef tempSysRef = new OtherSystemRef();
		
		tempSysRef.setOtherSystemId(otherSysId);
		tempSysRef.setOtherSystemNameId(otherSysNameId);
		
		referenceSet.add(tempSysRef);
		
		asset.getOtherSystemRefs().removeAll(referenceSet);
	}
	
	
	@RequestMapping(value = ("/addLeaseOutExtra"), method = RequestMethod.POST)
	public @ResponseBody void addLeaseOutExtra(
			@ModelAttribute("asset")  Assets asset,
			@RequestParam(value="extraCost") BigDecimal extraCost,
			@RequestParam(value="optionalExtra") Long optionalExtra)
	{
		//create a new LeaseOutEextras
		LeaseOutExtras leaseOutExtra = new LeaseOutExtras();
		leaseOutExtra.setExtraCost(extraCost);
		leaseOutExtra.setExtrasId(optionalExtra);
		
		//add the new LeaseOutExtras to the set
		asset.getLeaseOut().addLeaseOutExtra(leaseOutExtra);
		
		//get the size of the set and minus 1 to get number we are currently at 
		System.out.println(asset.getLeaseOut().getLeaseOutExtras().size());
	}
	
	@RequestMapping(value="/removeLeaseOutExtra", method = RequestMethod.POST)
	public @ResponseBody void removeLeaseOutExtra (
			@ModelAttribute("asset")  Assets asset,
			@RequestParam(value="removableElementIndex") int removableElementIndex) {
		// Retrieves a SortedSet of LeaseOutExtras being passed through ajax.
		SortedSet<LeaseOutExtras> refSet = asset.getLeaseOut().getLeaseOutExtras();
		/*
		 *  Calls SortedSetUtils remove method to remove the element from the page.
		 */
		SortedSetUtil.removeAtIndex(refSet, removableElementIndex);
	}
}
