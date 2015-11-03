package com.xioq.dasacumen.web.security;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.xioq.dasacumen.model.Authority;
import com.xioq.dasacumen.model.User;
import com.xioq.dasacumen.model.UserValidator;
import com.xioq.dasacumen.service.CRUDService;

/**
 * This controller is used by Spring Security for the initial login verification.
 * It is simply used to forward to the spring login jsp which submits the users
 * details for checking against the Users & Authorities tables, and also for
 * creating new user accounts.
 * 
 * @author Mark Walsh
 */
@Controller
public class LoginController
{
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CRUDService someService;

	@RequestMapping("/login")
	public String showLogin() {
		return "/security/login";
	}

	/**
	 * This method only exists in case we require a custom logout page as it currently just forwards to one.
	 * @return login screen.
	 */
	@RequestMapping("/loggedout")
	public String showLoggedout() {
		return "/security/loggedout";
	}
	
	/**
	 * Simple forward to the jsp enabling you to create a new account. 
	 * @param model
	 * @return account creation screen.
	 */
	@RequestMapping("/newAccount")
	public String showNewAccount(Model model) {
		
		model.addAttribute("user", new User());
		return "/security/newAccount";
	}
	
	/**
	 * Will add a new user_name into the users table and also the corresponding record into the authorities table linked by the unique key 'user_name'.
	 * @param users
	 * @return Account created screen.
	 */
	@RequestMapping(value = "createAccount", method = RequestMethod.POST)
		public ModelAndView createAccount(@ModelAttribute User user, BindingResult result) {

		 userValidator.validate(user, result);
		 if (result.hasErrors()) {
			 ModelAndView mv = new ModelAndView("/security/newAccount");
			 mv.addObject("errors", result);
			 return mv;
		 }

		if (null != user && null!= user.getUserName())
		{
			Authority auth = new Authority();
			auth.setAuthority("READ");
			auth.setUser(user);
			
			Set<Authority> authList = new HashSet<Authority>(0);
			authList.add(auth);
			user.setAuthorities(authList);
			
			//Encrypt password so cannot see value in DB.
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			
			try {
				someService.create(user);
			} catch (DataIntegrityViolationException e) {
				if (e.getCause() instanceof ConstraintViolationException) {
					ModelAndView mv = new ModelAndView("/security/newAccount");
					user.setPassword(null);
					user.setConfirmPassword(null);
					result.rejectValue("userName", "duplicateKey.user.userName");
					mv.addObject("errors", result);
					return mv;
				} else {
					//TODO XXX : decide on how we handle this as will get lost! Perhaps replace the try catch with a check to the DB
					//to see if record exists before we call the create?
					throw e;
				}
			}
		}
		ModelAndView mv = new ModelAndView("/security/accountCreated");
		return mv;
	}

}
