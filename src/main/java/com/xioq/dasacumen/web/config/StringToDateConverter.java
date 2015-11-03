package com.xioq.dasacumen.web.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.xioq.dasacumen.lib.utilities.StringUtil;

/**
 * 
 *  This class implements the Spring converter interface effectively overriding the default converter method with
 *  our own below which is used to specify our own date conversions based on a local date format
 *  that will be selected by the user.
 *  This is registered in the spring-servlet as a custom Converter with a Spring ConversionService bean. 
 *  
 *  Every date input will automatically call this converter to convert the string entered to a date.
 *  
 *  XXX - Currently just uses a hard coded date format as this will change once the system has something in
 *  place to allow the user to choose the local date format.
 * 
 * @author Mark Walsh
 */
public class StringToDateConverter  implements Converter<String, Date> {

    private DateFormat formatter = new SimpleDateFormat("dd-yyyy-MM");

	@Override
	public Date convert(String dateString) {
		Date date = null;
		if (!StringUtil.isEmpty(dateString)) {
			try {
				date =(new SimpleDateFormat("dd/MM/yyyy").parse(dateString));
			} catch (Exception e) {
				date = null;
			}
		}
		return date;
	}

}
