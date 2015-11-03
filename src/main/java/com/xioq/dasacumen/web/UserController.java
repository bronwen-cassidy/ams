package com.xioq.dasacumen.web;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This controller is mapped to the location where the user will interact whilst
 * logged in.
 * 
 * @author Joseph
 */
@Controller
@RequestMapping("ucp/")
public class UserController
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
	 * This is the initial view for the user's home page.
	 * 
	 * @param session the session for the request
	 * @return the view that redirects dependent on the session
	 */
	@RequestMapping("/")
	public String userController(HttpSession session)
	{

		// Check if the session is valid
		if (!sessionIsValid(session))
		{
			// If the session does not exist, then redirect the user to the
			// splashscreen to login
			System.out.println("A user is not logged in, redirecting to Splash");
			return "redirect:../splash/";
		}
		else
		{
			// If the session does exist, redirect the logged in user to their
			// home page
			System.out.println("A user is logged in, redirecting");
			return "redirect:index";
		}
	}

	/**
	 * Log in the user that is generated from the request. Set the session to
	 * contain the user's ID
	 * 
	 * @param userObject the user that is requesting to login
	 * @param session the session for the request
	 * @return the redirected view to the user control panel
	 */
	@RequestMapping("login")
	public String doLogin(User userObject, HttpSession session, SessionStatus status)
	{
		// If userObject contains an ID then set into session.
		if (userObject.getId() > 0)
		{
			session.setAttribute("userID", userObject.getId());
		}

		// Always remove Asset from session upon login. NOTE this is a temporary
		// solution to be changed.
		status.setComplete();
		session.removeAttribute("asset");
		session.removeAttribute("draft");
		
		return "redirect:../assetRegister";
	}

	/**
	 * Logs into DAS without specifying a user. This is needed as the current
	 * starting screen just has a log in button
	 */
	@RequestMapping("login/auto")
	public String autoLogin(HttpSession session)
	{
		Long defaultUserId = 1l;
		
		User defaultUser;
		try
		{
			defaultUser = someService.retrieve(User.class, defaultUserId);
		}
		catch (Exception e)
		{
			// Create the default user with a Tenant id of 1
			defaultUser = new User(null, "pJones", "Jones", "Peter", 1, "autoLogin", new Date(), "autoLogin", new Date(), null);
			someService.create(defaultUser);
		}

		session.setAttribute("userID", defaultUser.getId());
		session.setAttribute("user", defaultUser);

		return "redirect:/das/assetRegister";
	}

	/**
	 * Shows the home page for the user if there is a session, otherwise
	 * redirect the user to the root
	 * of the controller so they can login.
	 * 
	 * @param session the session from the request
	 * @return the model and view for the
	 */
	@RequestMapping("index")
	public ModelAndView indexPage(HttpSession session)
	{
		// If the session is valid, set path to homepage, else redirect to root
		String path = sessionIsValid(session) ? "redirect:../assetRegister" : "redirect:../login/";
		ModelAndView result = new ModelAndView(path);

		// Retrieve the user who's ID is stored in the session
//		Users user = someService.retrieve(Users.class, (long) session.getAttribute("userID"));
//		result.addObject("user", session.getAttribute("user"));

		return result;
	}

	/**
	 * Logs out the user by invalidating the session and redirecting the page.
	 * 
	 * @param session the session for the request
	 * @return the string that redirects to the root of the controller
	 */
	@RequestMapping("logout")
	public String doLogout(HttpSession session)
	{
		if (sessionIsValid(session))
		{
			session.invalidate();
		}

		return "redirect:../ucp/";
	}

	/**
	 * Checks the session to verify that it contains a non-null value for the
	 * 'userID' attribute.
	 * 
	 * @param session the session to check
	 * @return true if the session contains a non-null value for the 'userID'
	 *         attribute, else false
	 */
	private boolean sessionIsValid(HttpSession session)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null==auth.getName()) {
			return false;
		}
		else
			return true;
	}
}
