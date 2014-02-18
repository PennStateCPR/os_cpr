/* SVN FILE: $Id: PasswordAction.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.helper.EmailHelper;
import edu.psu.iam.cpr.ip.ui.helper.JSONHelper;
import edu.psu.iam.cpr.ip.ui.helper.StringHelper;
import edu.psu.iam.cpr.ip.ui.proxy.ProxyJMS;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.ui.validation.Password;
import edu.psu.iam.cpr.ip.ui.valueobject.JMSRequestObject;
import edu.psu.iam.cpr.ip.ui.valueobject.PasswordRequestObject;
import edu.psu.iam.cpr.ip.ui.valueobject.PasswordResponseObject;
import edu.psu.iam.cpr.ip.util.ApplicationPropertyHelper;
import edu.psu.iam.cpr.ip.util.HtmlEmail;

/**
 * PasswordAction processes the password: validates the inclusion of required
 * entries; such as, uppercase, lowercase, numbers, special characters, and ensures
 * both the password and the confirmation password are equal.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5953 $
 * @lastrevision $Date: 2012-12-20 17:21:58 -0500 (Thu, 20 Dec 2012) $
 */
public class PasswordAction extends BaseAction 
{
	private static final String YOU_MUST_ENTER_A_MATCHING_PASSWORD_IN_BOTH_FIELDS = "You must enter a matching password in both fields";
	private static final String UI_SETPASSWORD_REPLY_QUEUE_NAME = "ui.setpassword.reply.queue.name";
	private static final String UI_SETPASSWORD_QUEUE_NAME       = "ui.setpassword.queue.name";
    private static final long serialVersionUID = 1643016775815160131L;
    private String password;
	private String passwordConfirmed;
	private String message;
	

	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="password",results={ @Result(name=SUCCESS,location="/success",type=REDIRECT),
			                           @Result(name="stay on page",location="/jsp/setPassword.jsp"),
			                           @Result(name="validation error",location="/jsp/setPassword.jsp"),
                                       @Result(name="failure",location="/jsp/endPage.jsp")
                                     })
	public String execute() 
	{
		// TODO Business logic 
		if(!setup("psw"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		String returnLocation = SUCCESS;
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		if(FieldUtility.fieldIsPresent(getBtnsubmit()) &&  FieldUtility.fieldIsNotPresent(password, passwordConfirmed))
		{
			setUiMessage(YOU_MUST_ENTER_A_MATCHING_PASSWORD_IN_BOTH_FIELDS);
			addActionMessage(YOU_MUST_ENTER_A_MATCHING_PASSWORD_IN_BOTH_FIELDS);
			password = "";
			passwordConfirmed = "";
			return VALIDATION_ERROR;
		}
		
		/* Get List of names for user and use to disallow as part of password  */

		/* Prepare to validate the password */
		Map<String, Object> applicationMap = getApplicationMap();

		if(returnLocation.equals(SUCCESS))
		{
			PasswordRequestObject pswdObject = new PasswordRequestObject();
			pswdObject.setRegex            (getApplicationString("ui.password.validation"));
			pswdObject.setListMessages     (ApplicationPropertyHelper.getPropertyListByName("ui.password.validation.error", applicationMap));
			pswdObject.setListOfNames      (createListOfNames()); 
			pswdObject.setPassword         (password);
			pswdObject.setPasswordConfirmed(passwordConfirmed);
			
			PasswordResponseObject pro = Password.checkPassword(pswdObject);
			if(pro.containsErrorMessage())
			{
				setUiMessage(pro.getMessage());         
				addActionMessage(pro.getMessage());  
				
				returnLocation =  VALIDATION_ERROR; 
			}
		}
		
		/* Time to either set the password, or reset it -- no errors encountered */
		if(returnLocation.equals(SUCCESS))
		{
			String jsonMessage    = null;
			String queueName      = getApplicationString(UI_SETPASSWORD_QUEUE_NAME);
			String replyQueueName = getApplicationString(UI_SETPASSWORD_REPLY_QUEUE_NAME);
			try
			{
				jsonMessage = JSONHelper.createJsonMessage( "service_name", UIConstants.PASSWORD_SERVICE_NAME
						                                  , "userid"      , getSessionString("suc.userId")
						                                  , "password"    , password
						                                  , "msgid"       , getUniqueId()
						                                  );
				
				// Place a password reset request on the queue
				ProxyJMS.sendMessage(getApplicationMap(), new JMSRequestObject(queueName, replyQueueName, jsonMessage));  
				
		        String htmlText = StringHelper.replaceTokensFromMap(getSessionMap(), getApplicationString("ui.mail.success.html"));

		        // The reset password gets a slightly different email
		        if(personWasFound())
		        {
		        	htmlText = StringHelper.replaceTokensFromMap(getSessionMap(), getApplicationString("ui.mail.success.reset.html"));
		        }
		        
		        String emailTo = getSessionString("con.email");
		        HtmlEmail mailObj = EmailHelper.getSuccessEmailObj(getApplicationMap()
		        		                       ,(personWasFound() ? "reset":"created")
		        		                       ,emailTo 
		        		                       ,htmlText
		        		                       ,getUniqueId());

		        Thread mailThread = new Thread(mailObj);
		        mailThread.start();
		        log.info(String.format("%s email [%s] sent to: %s", getUniqueId(), (personWasFound() ? "reset":"created"), emailTo));
				
			}
			catch(Exception e)
			{
				setEndPageHeader(getApplicationString("ui.set.password.no.person.id.header"));
				addActionMessage(getApplicationString("ui.set.password.no.person.id.message"));
				if(getUniqueId() != null)
				{
					addReferenceNumber();
				}
				returnLocation = FAILURE;
			}
        
		}
			
		
		// Save form data to session
		saveFieldsToSession(getPrefix());
		return returnLocation;
	}
	
	/**
	 * Create a list of names that will be used to check password choices against
	 * @return a List of names: first name, middles names, and last name
	 */
	public List<String> createListOfNames() 
	{
		List<String> listNames = new ArrayList<String>();
		
		// Ensure names are not part of password(s) 
		listNames = prepareListOfNames( "lna.firstName", "lna.middleNames", "lna.lastName"
				                      , "fmr.firstName", "fmr.middleNames", "fmr.lastName" );
		
		return listNames;
		
	}

	/**
	 * Supply a list of names that the user has supplied on input screens
	 * @param names - an infinite list of string objects representing sesion names of data fields 
	 * @return a List of all of the users names: first, middle names, and last name
	 */
	public List<String> prepareListOfNames(String... names)
	{
		List<String> listOfNames = new ArrayList<String>();
		
		for(String name: names)
		{
			String actualName = (String)getSessionMap().get(name);
			if(FieldUtility.fieldIsPresent(actualName))
			{	
				if(name.toLowerCase().contains("middle"))
				{
					listOfNames.addAll(FieldUtility.getNameTokens(actualName));
				}
				else
				{
					listOfNames.add(actualName);
				}
			}
		}
		
		return listOfNames;
	}
	
	/**
	 * Return the Initials of the passed list of names: forward and backward
	 * @param names
	 * @return
	 */
	public List<String>  listInitials(List<String> names)
	{
		List<String> listNames = new ArrayList<String>();
		// Add initials to the list, both forward and backward
		String initials = "";
		for(String initial: names)
		{
			initials += initial.substring(0,1);
		}
		
		if(initials.length() > 1)
		{
			listNames.add(initials);
			listNames.add(new StringBuffer(initials).reverse().toString());
		}
		
		return listNames;
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

	/**
	 * @return the passwordConfirmed
	 */
	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}

	/**
	 * @param passwordConfirmed the passwordConfirmed to set
	 */
	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
