package com.xioq.dasacumen.web.config;

import java.util.Date;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Register the date formatter for all controllers rather than applying an initBinder to each.
 * 
 * TODO Look into conversion service as this does a similar thing
 * 
 * @author Stephen Elliott
 */
@Deprecated
//@ControllerAdvice
public class GlobalBindingInitializer
{
	/*
	 * Initialize a global InitBinder for dates instead of cloning its code in
	 * every Controller
	 */
	@InitBinder
	public void binder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
}
