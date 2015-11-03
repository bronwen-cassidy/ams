package com.xioq.dasacumen.service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
 
/**
 * Service for sending an email using credentials set in the application-context file
 * @author hjemei
 *
 */
@Service("emailService")
public class EmailService
{
	@Autowired
    private MailSender mailSender;
	
	@Autowired
	private JavaMailSenderImpl javaMailSender;
 
    public void setMailSender(MailSender mailSender) {  
        this.mailSender = mailSender;  
    } 
    
    /**
     * This method will compose and send a simple email message 
     * @param from
     * @param to
     * @param subject
     * @param messageBody
     */
    public void sendSimpleMessage(String from, String to, String[] bcc, String[] cc, String subject, String messageBody) 
    {
        SimpleMailMessage message = new SimpleMailMessage();
        
        message.setFrom(from);
        message.setCc(cc);
        message.setBcc(bcc);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(messageBody);
        
        mailSender.send(message);
    }
    /**
     * This method will just send a pre-composed simple email
     * 
     * @param message
     */
    public void sendSimpleMessage(SimpleMailMessage message) 
    {
        mailSender.send(message);
    }
    
    
    /**
     * This method will compose and send an email with an attachment that could contain images, audio, docs etc... 
     *  
     * @param from
     * @param to
     * @param cc
     * @param bcc
     * @param subject
     * @param messageBody
     * @param attachment contains file location i.e. C:\\Pictures\img001.jpeg
     */
    public void sendMessageWithAttachment(String from, String to, String[] cc, String[] bcc, String subject, String messageBody, String attachment) 
    {
    
    	MimeMessage message = javaMailSender.createMimeMessage();
		 
		try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(from);
	        helper.setTo(to);
	        helper.setCc(cc);
	        helper.setBcc(bcc);
	        helper.setSubject(subject);
	        helper.setText(messageBody);
	        
	        FileSystemResource file = new FileSystemResource(attachment);
	        helper.addAttachment(file.getFilename(), file);
	   
	    }catch (MessagingException e) {
	    	throw new MailParseException(e);
	    }
        javaMailSender.send(message);
     }

    /**
     * This method will send an already composed multi-part email that could contain images, audio, docs etc... 
     * */
    public void sendComposedMessage(MimeMessage message) 
    {
    	JavaMailSenderImpl sender = new JavaMailSenderImpl();
    	sender.setHost("office.outlook.com");

    	//TODO This method is a template at the moment and will require implementing later on
    	sender.send(message);
    }
    
    /**
     * 
     * @param alert
     * @param to
     */
    public void sendAlertMail(String alert, String to) 
    {
    	  SimpleMailMessage mailMessage = new SimpleMailMessage();
    	  mailMessage.setText(alert);
    	  mailMessage.setTo(to);
    	  mailSender.send(mailMessage);
    }

}