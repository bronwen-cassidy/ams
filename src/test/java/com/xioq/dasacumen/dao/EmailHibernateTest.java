package com.xioq.dasacumen.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;

import com.xioq.dasacumen.service.EmailService;
import com.xioq.dasacumen.test.HibernateDAOTestBase;

/**
 * Hibernate test to test out the email service and makee sure the service is working appropriately.
 * @author hjemei
 *
 */
public class EmailHibernateTest extends HibernateDAOTestBase {

	/**
	 * Creates an instance of email account using the settings in the application context file
	 **/
	@Autowired
    private EmailService simpleMessage;

	/**
	 * Test to see if a simple email can be sent. If no exceptions have been thrown then the email was successfully sent,
	 *  but a different test needs to be done to check whether the email was received.
	 */
	@Test
	public void sendSimpleEmailTest()
	{
		simpleMessage.sendSimpleMessage("brightfuturesoftware1@gmail.com", 
										"hamid.jemei@brightfuture.co.uk", null, null, 
										"testing Das email", "If you got this EUREKA :)");
	}
	
	/**
	 * Test to see if a simple email can be sent. If no exceptions have been thrown then the email was successfully sent,
	 *  this specific test checks if CC'd email's will receive the email to.
	 */
	@Test
	public void sendSimpleEmailwithCarbonCopyTest()
	{
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setTo("ben.worsley@brightfuture.co.uk");
		message.setCc("michael.Waude@brightfuture.co.uk");
		message.setSubject("testing Das email");
		message.setText("If you got this EUREKA :)");
		
		simpleMessage.sendSimpleMessage(message);
	}
}