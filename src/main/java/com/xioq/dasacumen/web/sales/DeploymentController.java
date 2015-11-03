package com.xioq.dasacumen.web.sales;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.xioq.dasacumen.lib.search.SearchResult;
import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.assetregister.AssetProposalSearch;
import com.xioq.dasacumen.model.constants.SalesConstants;
import com.xioq.dasacumen.service.CRUDService;
import com.xioq.dasacumen.web.assetregister.asset.AssetProposalValidator;

/**
 * Controller for the deployment view. On this series of pages the deployment
 * manager will be shown all asset proposals which have been accepted by clients
 * and allows them to process the orders by deploying them for delivery or
 * marking them as collected if the client retrieves the asset themselves. The
 * date of deployment or delivery are stored on the assetSchedule table during
 * this process and the status of the assetProposals are changed depending on
 * deployment or collection.
 * 
 * @author twallwork
 *
 */
@Controller
@SessionAttributes({ "assetProposal" })
@RequestMapping("/deployment")
public class DeploymentController {
	@Autowired
	private CRUDService someService;

	@Autowired
	private AssetProposalValidator assetProposalValidator;

	/**
	 * Landing page for deployment managers view. Displays a search table for
	 * all asset proposals which hold an Accepted status. Clicking on one of the
	 * search results passes the ID of the selected proposal to the next view.
	 * 
	 * @return
	 */
	@RequestMapping("")
	public ModelAndView deploymentSearchView() {
		ModelAndView result = new ModelAndView(
				"sales.deployment.deploymentView");
		AssetProposalSearch assetProposalSearchParams = new AssetProposalSearch();
		assetProposalSearchParams.setCurrentStatus(SalesConstants.ACCEPTED);
		SearchResult<AssetProposal> assetProposal = someService
				.generalSearch(assetProposalSearchParams);
		result.addObject("proposalList", assetProposal.getResult());
		result.addObject("searchTotalCount", assetProposal.getTotalCount());

		return result;
	}

	// @TODO XXX : new method for displaying you deployment view/jsp. using ID
	// as a param.
	/**
	 * Displays deployment info for the asset proposal which has been selected
	 * in the previous view. Retrieves assetProposal using ID of asset proposal
	 * selected on previous view and adds it to the view. On this view there is
	 * a calendar allowing the user to input a date which is stored into
	 * assetProposal upon submit. Also features a collection table and button
	 * which instead allows the deployment manager to mark the asset as
	 * Collected instead of deployed, as well as submitting the date on an
	 * alternate calendar.
	 * 
	 * @param proposalId
	 * @return
	 * 
	 * 
	 */
	@RequestMapping(value = "/deploymentview")
	public ModelAndView deploymentView(Long proposalId) {
		ModelAndView result = new ModelAndView("sales.deployment.deploy");
		AssetProposal assetProposal = someService.retrieve(AssetProposal.class,
				proposalId);
		if (null != proposalId) {
			result.addObject("assetProposal", assetProposal);
		}

		return result;
	}

	/**
	 * Submit method for deployment of an asset. Sets the status of the
	 * assetProposal in session to Deployed. Status of session is set to
	 * complete and assetProposal is removed from session after this task is
	 * complete. Then redirects to the landing page.
	 * 
	 * @param assetProposal
	 * @param session
	 * @param status
	 * @return
	 * 
	 * 
	 */
	@RequestMapping(value = "/deploymentConfirm", method = RequestMethod.POST)
	public ModelAndView deploymentSubmit(
			@ModelAttribute("assetProposal") AssetProposal assetProposal,
			HttpSession session, SessionStatus status) {
		ModelAndView mav = new ModelAndView("redirect:../deployment");
		assetProposal.setCurrentStatus(SalesConstants.DEPLOYED);
		someService.create(assetProposal);

		status.setComplete();
		session.removeAttribute("assetProposal");
		return mav;

	}

	/**
	 * Submit method for declaring the collection of an asset. Sets the status
	 * of the assetProposal to Collected. Status of session is set to complete
	 * and assetProposal is removed from session after this task is complete.
	 * Then redirects to the landing page.
	 * 
	 * @param assetProposal
	 * @param session
	 * @param status
	 * @return
	 */
	@RequestMapping(value = "/deploymentCollection", method = RequestMethod.POST)
	public ModelAndView deploymentCollected(
			@ModelAttribute("assetProposal") AssetProposal assetProposal,
			HttpSession session, SessionStatus status) {
		ModelAndView mav = new ModelAndView("redirect:../deployment");
		assetProposal.setCurrentStatus(SalesConstants.COLLECTED);
		someService.create(assetProposal);
		status.setComplete();
		session.removeAttribute("assetProposal");
		return mav;

	}
	
	@RequestMapping("/deploymentMobileConfirmation")
	public ModelAndView deploymentConfirmationView(/*@ModelAttribute("assetProposal") AssetProposal assetProposal*/){
		ModelAndView mav = new ModelAndView("sales.deployment.mobileConfirmation");
		return mav;
	}
}

