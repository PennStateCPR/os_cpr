/* SVN FILE: $Id: HtmlEmail.java 6013 2013-01-16 17:17:58Z jal55 $ */
package edu.psu.iam.cpr.ip.util;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * HtmlEmail Send [dual] HTML,  and text-based version of email to end-user.  
 * 
 * Allow their browser to choose which to display 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.util 
 * @author $Author: jal55 $
 * @version $Rev: 6013 $
 * @lastrevision $Date: 2013-01-16 12:17:58 -0500 (Wed, 16 Jan 2013) $
 */
public class HtmlEmail implements Runnable
{
	private String       host       ;
	private String		 port		;
	private String       userName   ;
	private String       password   ;
	private String       from       ;
	private String       fromPerson ;
	private String       to         ;
	
	// This 'cc' address is primarily used during testing to send a copy of all emails to an archive mailbox 
	private String       archiveBcc;
	private String       subject    ;
	private String       plainText  ;
	private String       htmlText   ;
	private String       uniqueId   ;
	
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(HtmlEmail.class);
	
  public HtmlEmail()
  {
  }
  
  public HtmlEmail(String host, String port, String userName, String password, String from, 
		  String fromPerson, String to, String archiveBcc, String subject, 
		  String plainText, String htmlText, String uniqueId)
  {
	  this.host		= host;
	  this.port 	= port;
	  this.userName = userName;
	  this.password = password;
	  this.from		= from;
	  this.fromPerson 
	  				= fromPerson;
	  this.to		= to;
	  this.archiveBcc 
	  				= archiveBcc; 
	  this.subject 	= subject;
	  this.plainText= plainText ;
	  this.htmlText = htmlText  ;
	  this.uniqueId = uniqueId  ;
	  
	  // Debugging log statement at info level, later change to debug level
	  LOG.info("---------------------------");
	  LOG.info(String.format("%s Email parameters follow", uniqueId));
	  LOG.info(String.format("%s host(%s), from(%s), fromPerson(%s)", uniqueId, host, from, fromPerson));
	  LOG.info(String.format("%s to(%s), subject(%s)", uniqueId, to, subject));
	  LOG.info(String.format("%s plainText(%s)", uniqueId, plainText));
	  LOG.info(String.format("%s htmlText(%s)", uniqueId, htmlText));
	  LOG.info("---------------------------");
}

  
  @Override
  public void run() 
  {
	if(FieldUtility.fieldIsPresent(to))
	{
		sendMail2();
	}
	else
	{
		LOG.warn(String.format("%s Confirmation email not sent, because no email address was provided", uniqueId));
	}
  	
  }

  /*
   * Send both(2) a text and an HTML email, and let the user's email client choose it's preference.
   */
  public void  sendMail2()
  {
	 try
	 {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.starttls.enable", "true");
		//props.put("mail.smtp.port", "465");
		props.put("mail.smtp.port", getPort());
        props.put("mail.smtp.sendpartial","true");
		Session session = Session.getInstance(props,
				  new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(getUserName(), getPassword());
					}
				  });

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from, fromPerson));
       	message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
       	
       	// This extra recipient being added for debugging -- to be removed dater
       	if(FieldUtility.fieldIsPresent(archiveBcc))
       	{
       		message.addRecipient(Message.RecipientType.BCC, new InternetAddress(archiveBcc));
       	}
        
        message.setSubject(subject);
        
        //use a MimeMultipart as we need to allow the user's browser to select either HTML [preferred] or plain text message 
        Multipart multipart = new MimeMultipart("alternative"); 
        
        BodyPart textPart = new MimeBodyPart();
        textPart.setContent(plainText, "text/plain");
        multipart.addBodyPart(textPart);

        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(htmlText, "text/html");
        multipart.addBodyPart(htmlPart);
        
        message.setContent(multipart);
        
        
        Transport transport = session.getTransport("smtp");
        transport.connect();
        
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
     }
     catch(Exception e) 
     {
    	 LOG.warn(String.format(" ..exception [%s] ", e.getMessage()));
    	 
    	 StackTraceElement[] trace = e.getStackTrace();
    	 
    	 // Print the stack trace in the log -- we might need it later
    	 for(int i = 1; i < trace.length; i++)
    	 {
 			LOG.info(String.format("%s", trace[i].toString()));
    	 }
     }
  }      

/**
 * @return the port
 */
public String getPort() {
	return port;
}

/**
 * @param port the port to set
 */
public void setPort(String port) {
	this.port = port;
}

/**
 * @return the userName
 */
public String getUserName() {
	return userName;
}

/**
 * @param userName the userName to set
 */
public void setUserName(String userName) {
	this.userName = userName;
}

/**
 * @return the password
 */
public String getPassword() {
	return password;
}

/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}

}  
