package com.xioq.dasacumen.lib.exceptions;


/**
 * Document exception is used when user tries to use the 
 * document service. This will be triggered on the event 
 * that a user will either load or save a document.
 * 
 * @author Alex Hurst
 *
 */
@SuppressWarnings("serial")
public class DocumentException extends RuntimeException {

	/**
	 * Constructor for the document exception that will use the message from the thrown exception
	 * 
	 * @param message this is the message that will prompt what has caused the exception to be triggered
	 */
	protected DocumentException (String message){
		super(message);
	}
	/**
	 * Method that will be used that will use the message from the thrown exception and will also 
	 * use the root exception cause that has triggered this.							
	 * 
	 * @param message this is the message that will prompt what has caused the exception to be triggered
	 * @param cause the root exception cause of the custom exception that is thrown
	 */
	public DocumentException (String message, Throwable cause){
		super(message, cause);
	}
	
}
