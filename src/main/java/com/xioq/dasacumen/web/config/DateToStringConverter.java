package com.xioq.dasacumen.web.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 *  This class implements the Spring converter interface effectively overriding the default converter method with
 *  our own below which is used to specify our own date conversions based on a local date format
 *  that will be selected by the user.
 *  This is registered in the spring-servlet as a custom Converter with a Spring ConversionService bean. 
 *  
 *  Every date returned from the DB will automatically call this converter to convert it to a string for the
 *  jsp's to display.
 *  
 *  XXX - Currently just uses a hard coded date format as this will change once the system has something in
 *  place to allow the user to choose the local date format.
 * 
 * @author Mark Walsh
 */
public class DateToStringConverter  implements Converter<Date, String> {

	//@TODO XXX: This format will be replaced with the locale user date format.
    private DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    public String convert(Date date) {
        String strDate = null;
    	if (null!=date) {
            strDate = formatter.format(date);
    	}

        return strDate;
    }

}
