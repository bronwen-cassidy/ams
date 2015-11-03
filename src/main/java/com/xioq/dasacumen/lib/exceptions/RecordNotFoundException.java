package com.xioq.dasacumen.lib.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * This should be used to catch a specific exception when accessing DAO for a specific key that should exist.
 * 
 * @author mwalsh
 *
 */
@SuppressWarnings("serial")
public class RecordNotFoundException extends DataAccessException {

	 /**
	   * This is used in the situation that this exception is the cause.
	   * 
	   * @param msg The message holding a reason for the exception.
	   */
	  public RecordNotFoundException(String message)
	  {
	    super(message);
	  }

	  /**
	   * Used when an exception is caught and re-thrown from the data access objects???
	   * 
	   * @param msg The message detailing what was attempted when the exception occured, always 
	   * be sure to pass in detailed information such as the id/key of the record etc.
	   * 
	   * @param cause The cause of the exception.
	   */
	  public RecordNotFoundException(String message, Throwable cause)
	  {
	    super(message, cause);
	    // TODO Auto-generated constructor stub
	  }

	}