package com.xioq.dasacumen.lib.exceptions;


/**
 * This should be used for outputting a detailed message when a specific key or id is null when it should be
 * populated, for example upon editing a record.
 * 
 * @author mwalsh
 *
 */
@SuppressWarnings("serial")
public class NoIDException extends Exception {

	 /**
	   * @param msg The message holding a reason for the exception.
	   */
	  public NoIDException(String message)
	  {
	    super(message);
	  }

	  /**
	   * Used when an exception is caught and re-thrown.
	   * 
	   * @param msg The message detailing what was attempted when the exception occured, always 
	   * be sure to pass in detailed information such as the id/key of the record etc.
	   * 
	   * @param cause The cause of the exception.
	   */
	  public NoIDException(String message, Throwable cause)
	  {
	    super(message, cause);
	  }

	}