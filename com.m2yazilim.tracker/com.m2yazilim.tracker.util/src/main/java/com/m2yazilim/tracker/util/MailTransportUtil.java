package com.m2yazilim.tracker.util;

import java.util.Locale;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.context.WebApplicationContext;


public class MailTransportUtil {

	private Properties mailProperties = new Properties();
	private static ReloadableResourceBundleMessageSource  RESOURCE_BUNDLE;
	private static final String BEAN_NAME = "reloadableResourceBundleMessageSource";

	private Properties getConfiguration() {

		if (mailProperties != null) {
			try {
				mailProperties.load(this.getClass().getResourceAsStream(
						"/mailConfig.properties"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return mailProperties;
	}

	public void sendHtmlMail(String from,  String recipient, String subject,String messageTxt) throws MessagingException {
		getConfiguration();
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.host", mailProperties.getProperty("hostName"));
		props.put("mail.smtp.user", mailProperties.getProperty("username"));
		props.put("mail.smtp.password", mailProperties.getProperty("password"));
		props.put("mail.smtp.port", mailProperties.getProperty("smtpPort"));
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.socketFactory.port", mailProperties.getProperty("smtpPort"));
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.quitwait", "false");

		Session mailSession = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(mailProperties
								.getProperty("username"), mailProperties
								.getProperty("password"));
					}
				});
	

		MimeMessage message = new MimeMessage(mailSession);
		message.setSubject(subject);
		message.setContent(messageTxt,
				"text/html; charset=UTF-8");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				recipient));

		Transport.send(message);

	}
	
	
	public void sendMail(String[] recipient, String subject,String messageTxt) throws Exception {
		
		String host = getResourceByName("CorpLineOptions.mailserverhost");
//		String host = "172.21.253.99";
		
		String from = getResourceByName("CorpLineOptions.from");
//		String from = "mh1@vodafone.com";
		
	   

		Properties props = System.getProperties();
	    props.put("mail.smtp.host", host);
	    
	    Session session = Session.getDefaultInstance(props, null);
	    MimeMessage mailMsg = new MimeMessage(session);
	    mailMsg.setFrom(new InternetAddress(from));
	    mailMsg.setHeader("MIME-Version", "1.0");
	    mailMsg.setHeader("Content-type", "text/plain; charset=iso-8859-9");
	    InternetAddress[] toAddress = new InternetAddress[recipient.length];

	    // To get the array of addresses
	    for( int i=0; i < recipient.length; i++ ) { // changed from a while loop
	        toAddress[i] = new InternetAddress(recipient[i]);
	    }
	    //System.out.println(Message.RecipientType.TO);

	    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
	        mailMsg.addRecipient(Message.RecipientType.TO, toAddress[i]);
	    }
	    
	    
	    mailMsg.setSubject(subject,"utf-8");
        mailMsg.setContent(subject, "charset=UTF-8");
	    mailMsg.setText(messageTxt);
	    Transport transport = session.getTransport("smtp");
	    

	    transport.connect();
	    transport.sendMessage(mailMsg, mailMsg.getAllRecipients());
	    transport.close();
	    
	}
	
	
	
	public void sendMail(String from,  String[] recipient, String subject,String messageTxt) throws Exception {
		
		String host = getResourceByName("CorpLineOptions.mailserverhost");


		Properties props = System.getProperties();
	    props.put("mail.smtp.host", host);
	    
	    Session session = Session.getDefaultInstance(props, null);
	    MimeMessage mailMsg = new MimeMessage(session);
	    mailMsg.setFrom(new InternetAddress(from));
	    mailMsg.setHeader("MIME-Version", "1.0");
	    mailMsg.setHeader("Content-type", "text/plain; charset=iso-8859-9");
	    InternetAddress[] toAddress = new InternetAddress[recipient.length];

	    // To get the array of addresses
	    for( int i=0; i < recipient.length; i++ ) { // changed from a while loop
	        toAddress[i] = new InternetAddress(recipient[i]);
	    }
	    //System.out.println(Message.RecipientType.TO);

	    for( int i=0; i < toAddress.length; i++) { // changed from a while loop
	        mailMsg.addRecipient(Message.RecipientType.TO, toAddress[i]);
	    }
	    mailMsg.setSubject(subject);
	    mailMsg.setText(messageTxt);
	    Transport transport = session.getTransport("smtp");
	    

	    transport.connect();
	    transport.sendMessage(mailMsg, mailMsg.getAllRecipients());
	    transport.close();
	    
	}
	
	
	public static String getResourceByName(String resourceName){
		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = getResourceBundle();			
		return reloadableResourceBundleMessageSource.getMessage(resourceName, new Object[0], Locale.getDefault());				
	}
	
	private static ReloadableResourceBundleMessageSource getResourceBundle(){
		if(RESOURCE_BUNDLE == null){
			synchronized (BEAN_NAME) {		
				if (ApplicationContextProvider.getApplicationContext() instanceof WebApplicationContext) {
					WebApplicationContext wac = (WebApplicationContext) ApplicationContextProvider.getApplicationContext();		
					RESOURCE_BUNDLE =  (ReloadableResourceBundleMessageSource)wac.getBean(BEAN_NAME);	
				} else if (ApplicationContextProvider.getApplicationContext() instanceof GenericApplicationContext) {
					GenericApplicationContext wac = (GenericApplicationContext) ApplicationContextProvider.getApplicationContext();		
					RESOURCE_BUNDLE =  (ReloadableResourceBundleMessageSource)wac.getBean(BEAN_NAME);	
				}
			}
		}
		return RESOURCE_BUNDLE;
	}
	
	
}
