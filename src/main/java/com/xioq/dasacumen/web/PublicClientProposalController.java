package com.xioq.dasacumen.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.assetregister.AssetProposal;
import com.xioq.dasacumen.model.constants.SalesConstants;
import com.xioq.dasacumen.service.CRUDService;
import com.xioq.dasacumen.service.EmailService;

/**
 * Controller for the public-access proposal pages which will be accessed
 * through a unique link sent by email to clients. Users will be able to either
 * accept or decline the linked proposal.
 * 
 * @author twallwork
 */
@Controller
@SessionAttributes({ "assetProposal" })
@RequestMapping("/publicClientProposal")
public class PublicClientProposalController {

	@Autowired
	private CRUDService someService;

	@Autowired
	private EmailService simpleMessage;

	/**
	 * Landing page for publicClientProposal pages. Retrieves an assetProposal
	 * and adds it to the view. This page displays all proposal info which needs
	 * to be seen by the client and allows them to accept or decline. May also
	 * display leasing company logo and info.
	 * 
	 * @return
	 */
	// TODO XXX These pages need to be accessed via unique URL link which also
	// passes in a proposal ID
	// TODO XXX The proposal which is declared in the landing screen method
	// needs to be passed an ID by the URL link
	// TODO XXX The values on this page need to be ratified as they do not
	// currently display maintenance or insurance information

	@RequestMapping("")
	public ModelAndView proposalLanding(Long proposalId) {

		AssetProposal assetProposal = someService.retrieve(AssetProposal.class, proposalId);
		ModelAndView mav = new ModelAndView("publicClientProposal.base");
		mav.addObject("assetProposal", assetProposal);
		return mav;

	}

	/**
	 * Terms and conditions page. Simply links to tile which contains a
	 * scrolling text display which contains terms and conditions and allows the
	 * user to accept or decline.
	 * 
	 * @return
	 */
	@RequestMapping("/terms")
	public ModelAndView proposalTerms() {
		ModelAndView mav = new ModelAndView(
				"publicClientProposal.proposalTerms");
		return mav;
	}

	/**
	 * Decline page which the user is taken to when they opt to decline a
	 * proposal. Retrieves assetProposal from database and adds to view. The
	 * page allows the user to enter their reason for declining the proposal
	 * into a text box, which is submitted to and handled by declinesubmit page.
	 * 
	 * @param assetProposal
	 * @return
	 */
	@RequestMapping("/decline")
	public ModelAndView proposalDecline(
			@ModelAttribute AssetProposal assetProposal) {
		ModelAndView mav = new ModelAndView(
				"publicClientProposal.declineProposal");
		mav.addObject("assetProposal", assetProposal);
		return mav;
	}

	/**
	 * Confirmation page which again displays the proposal info which the user
	 * needs to see and again allows them to accept or decline.
	 * 
	 * @return
	 */
	@RequestMapping("/confirmation")
	public ModelAndView proposalConfirmation(
			@ModelAttribute AssetProposal assetProposal) {
		ModelAndView mav = new ModelAndView(
				"publicClientProposal.proposalConfirmation");
		mav.addObject("assetProposal", assetProposal);
		return mav;
	}

	/**
	 * This page is displayed when the proposal is accepted on the confirmation
	 * page. Sends an email to the client to confirm the lease out.
	 * 
	 * @return
	 */
	@RequestMapping("/proposalAccepted")
	public ModelAndView proposalAccepted(
			@ModelAttribute AssetProposal assetProposal, HttpSession session,
			SessionStatus status) {
		ModelAndView mav = new ModelAndView(
				"publicClientProposal.proposalAccepted");

		simpleMessage
				.sendSimpleMessage(
						"brigtfuturesoftware1@gmail.com",
						assetProposal.getEmail().getEmailAddress(),
						null,
						null,
						"Confirmation of leasing",
						"Dear "
								+ assetProposal.getCompany().getName()
								+ ", \n \n"
								+ "This is a confirmation email, to confirm your leasing. \n \n"
								+ "We would like to thank you for your service and be assured to contact us for further assistance.​ \n \n \n"
								+ "Yours Sincerly \n \n" + "Rob Kennedy \n"
								+ "Leasing Manager");

		mav.addObject("assetProposal", assetProposal);
		assetProposal.setCurrentStatus(SalesConstants.ACCEPTED);
		someService.update(assetProposal);
		status.setComplete();
		session.removeAttribute("assetProposal");
		return mav;
	}

	/**
	 * Submit page for decline form. Displays text which informs user they have
	 * successfully declined the proposal and sends an email to the account
	 * manager informing them of the declined proposal.
	 * 
	 * @param assetProposal
	 * @param session
	 * @param status
	 * @return
	 */

	@RequestMapping(value = "/submitDeclineForm", method = RequestMethod.POST)
	public ModelAndView declineSubmit(
			@ModelAttribute AssetProposal assetProposal, HttpSession session,
			SessionStatus status) {
		ModelAndView mav = new ModelAndView(
				"publicClientProposal.declineSubmit");
		mav.addObject("assetProposal", assetProposal);
		assetProposal.setCurrentStatus(SalesConstants.DECLINED);
		someService.update(assetProposal);
		// TODO XXX Recipient email needs to be changed to address assigned to
		// the current user.
		simpleMessage.sendSimpleMessage("brightfuturesoftware1@gmail.com",
				"thomas.wallwork@brightfuture.co.uk", null, null,
				"Proposal Declined", "Proposal ID " + assetProposal.getId()
						+ "sent to " + assetProposal.getCompany().getName()
						+ " has been declined." + "\n Reason given: "
						+ assetProposal.getDeclineComment());
		// TODO XXX When alert functionality is completed an alert needs to be
		// created when a proposal is declined.
		status.setComplete();
		session.removeAttribute("assetProposal");

		return mav;
	}
}