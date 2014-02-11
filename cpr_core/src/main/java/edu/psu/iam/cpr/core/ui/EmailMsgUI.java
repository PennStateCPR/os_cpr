/* SVN FILE: $Id: EmailMsgUI.java 5659 2012-11-19 17:24:59Z slk24 $ */
package edu.psu.iam.cpr.core.ui;


/**
 * EmailMsgUI is an object class for transporting the necessary email notification data from the database to the UI application
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.database
 * @author $Author: slk24 $
 * @version $Rev: 5659 $
 * @lastrevision $Date: 2012-11-19 12:24:59 -0500 (Mon, 19 Nov 2012) $
 */

public class EmailMsgUI {
	
	private String emailSubject;
	private String emailText;
	private String emailHtml;
	private String notificationProcess;
	
	public EmailMsgUI() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public EmailMsgUI(String emailSubject, String emailText,
			String emailHtml, String notificationProcess) {
		super();
		this.emailSubject = emailSubject;
		this.emailText = emailText;
		this.emailHtml = emailHtml;
		this.notificationProcess = notificationProcess;
	}


	/**
	 * @return the emailSubject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}
	/**
	 * @param emailSubject the emailSubject to set
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	/**
	 * @return the emailText
	 */
	public String getEmailText() {
		return emailText;
	}
	/**
	 * @param emailText the emailText to set
	 */
	public void setEmailText(String emailText) {
		this.emailText = emailText;
	}
	/**
	 * @return the emailHtml
	 */
	public String getEmailHtml() {
		return emailHtml;
	}
	/**
	 * @param emailHtml the emailHtml to set
	 */
	public void setEmailHtml(String emailHtml) {
		this.emailHtml = emailHtml;
	}
	/**
	 * @return the notificationProcess
	 */
	public String getNotificationProcess() {
		return notificationProcess;
	}
	/**
	 * @param notificationProcess the notificationProcess to set
	 */
	public void setNotificationProcess(String notificationProcess) {
		this.notificationProcess = notificationProcess;
	}
	
	public String toString() {
		return "Subject: " + emailSubject + " Text body " + emailText + " HTML body " + emailHtml + " Notification process is " + notificationProcess;
	}

}
