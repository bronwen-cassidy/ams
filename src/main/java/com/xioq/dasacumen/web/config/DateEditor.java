package com.xioq.dasacumen.web.config;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Converts Dates to Strings and vice versa.
 * 
 * TODO Allow for user specified formats of dates. Maybe tied to the language??
 * Should be set in the main.js to format the date pickers too
 * 
 * @author Stephen Elliott
 */
public class DateEditor extends PropertyEditorSupport
{
	 /**
     * Sets the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text  The string to be parsed.
     */

	public void setAsText(String value)
	{
		try
		{
			setValue(new SimpleDateFormat("dd/MM/yyyy").parse(value));
		}
		catch (ParseException e)
		{
			setValue(null);
		}
	}

	/**
     * Gets the property value as a string suitable for presentation
     * to a human to edit.
     *
     * @return The property value as a string suitable for presentation
     *       to a human to edit.
     * <p>   Returns "null" is the value can't be expressed as a string.
     * <p>   If a non-null value is returned, then the PropertyEditor should
     *       be prepared to parse that string back in setAsText().
     */
	public String getAsText()
	{
		String s = "";
		if (getValue() != null)
		{
			s = new SimpleDateFormat("dd/MM/yyyy").format((Date) getValue());
		}
		return s;
	}
	
   	
   	/**
   	 * Simple formatting method to return todays date in the format you pass in as a string. Useful for unit testing.
   	 * Will throw parse exception if you pass in an invalid format.
   	 * @return a formatted todays date.
   	 */
   	public static Date getTodaysDateFormatted(String format)
   	{
		Date date = null;
   		SimpleDateFormat sdf = new SimpleDateFormat(format);
   		String dateStr = sdf.format(new Date());
   		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
   	}
}
