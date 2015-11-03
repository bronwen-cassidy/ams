/**
 * 
 */
package com.xioq.dasacumen.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This Controller is mapped to the first DAS Acumen location which is
 * "splash/".
 * It allows us to login from a list of users and add new users.
 * 
 * @author Stephen
 */
@Controller
@RequestMapping("splash/")
public class SplashController
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
	 * The original view for the DAS Acumen page.
	 * 
	 * @return the model and view for the splash screen
	 */
	@RequestMapping("")
	public String splashScreen(HttpSession session)
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

		return result;
	}
	
	/**
	 * Checks the session to verify that it contains a non-null value for the
	 * 'userID' attribute.
	 * 
	 * @param session the session to check
	 * @return true if the session contains a non-null value for the 'userID'
	 *         attribute, else false
	 */
	
	//@TODO XXXX: REMOVE!
	private boolean sessionIsValid(HttpSession session)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (null==auth.getName()) {
			return false;
		}
		else
			return true;
	}
	
	
	
	
	

	/**
	 * Checks if user is valid and adds the user to the database. Redirects to
	 * Splash.
	 * 
	 * @param user the user created from the data from the request
	 * @return the view that redirects to the splash screen
	 */
	@Deprecated
	@RequestMapping("adduser")
	public String addUser(@ModelAttribute User user)
	{
		Long usId = user.getId();
		// Check if there was a valid user generated
		if (null == usId || usId == 0)
		{
			System.out.println("Adding User: " + user.getUserName() + ", name is " + user.getSurname());

			// Request that the user be created in the database
			someService.create(user);
		}

		return "redirect:";
	}

	/**
	 * Checks if the user is valid and deletes that user from the database.
	 * Redirects to Splash.
	 * 
	 * @param user the user created from the data from the request
	 * @return the view that redirects to the splash screen
	 */
	@Deprecated
	@RequestMapping("deleteuser")
	public String deleteUser(@ModelAttribute User user)
	{
		// Request that the user be deleted from the database
		someService.delete(user);

		return "redirect:";
	}
	
	@RequestMapping("todo")
	public @ResponseBody String safeURL(String param)
	{
		return "TODO: " + param;
	}
}
