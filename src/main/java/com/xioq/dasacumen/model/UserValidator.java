package com.xioq.dasacumen.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xioq.dasacumen.lib.utilities.StringUtil;

/**
 * Validator class for the Users object.
 * 
 * @author mwalsh
 *
 */
@Component("userValidator")
public class UserValidator implements Validator
{
	@Override
	public boolean supports(Class<?> clazz)
	{
		return User.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors)
	{
		User user = (User)target;
		//@TODO XXX: language file for text.
		if(StringUtil.isEmpty(user.getUserName()))
			errors.rejectValue("userName", "user.userName.mand");
		if(StringUtil.isEmpty(user.getPassword()))
			errors.rejectValue("password", "user.password.mand");
		if(!StringUtil.isEmpty(user.getPassword()) && (!StringUtil.isEmpty(user.getConfirmPassword())
				&& !user.getPassword().equals(user.getConfirmPassword()))) {
			errors.rejectValue("password", "user.password.invalid");
		}
	}

}
