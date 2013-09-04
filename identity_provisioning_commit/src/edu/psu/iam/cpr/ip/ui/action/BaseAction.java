/* SVN FILE: $Id: BaseAction.java 7246 2013-05-10 15:35:11Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.ParentPackage;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.ip.ui.common.MagicNumber;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.exception.DataLockException;
import edu.psu.iam.cpr.ip.ui.exception.IPException;
import edu.psu.iam.cpr.ip.ui.navigation.Navigation;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * BaseAction is a skeleton Struts 2 action class  Its provides back-end 
 * methods for accessing server resources.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 7246 $
 * @lastrevision $Date: 2013-05-10 11:35:11 -0400 (Fri, 10 May 2013) $
 */

@ParentPackage("datalockPackage")
@InterceptorRefs({
    @InterceptorRef("datalock"),
    @InterceptorRef("defaultStack")
})

public abstract class BaseAction extends ActionSupport
{
	public static final String RA_CONNECTION = "RAConnection";
	private static final String RAC_VALID_SESSION = "rac.valid.session";
	private String uiMessage;   
	private String endPageHeader;
	private String control;
	private String btnsubmit;
	private String prefix;
	private String previousAction;
	
	
	public static final String STAY_ON_PAGE      = "stay on page";
	public static final String VALIDATION_ERROR  = "validation error";
	public static final String FAILURE           = "failure";
	public static final String CONNECTION_ACTION = "RaConnectionAction";
	
	// Annotation constants
	public static final String REDIRECT          = "redirect";
	private static final int FIRST_SCREEN = 0;
	
	/** Instance of logger */                                                     
	Logger log = Logger.getLogger(this.getClass().getName());
	/**
	 * 
	 * @return a String indicating the next process to invoke
	 * @throws CprException 
	 * @throws NamingException 
	 * @throws IPException 
	 */
	public abstract String execute() throws CprException, NamingException, IPException ; 
	
	public void checkLockable() throws DataLockException
	{
		throw new DataLockException("..Data Lock Condition");
	}
	
	public void noSession() throws IPException
	{
		throw new IPException(IPException.RETURN_TYPE.SESSION_EXPIRATION);
	}
			
	public boolean setup(String prefix)
	{
		boolean allowedOrNot = false;
		
		this.prefix = prefix;

		/*
		 * In a previous version of this code there was a 'return true' here if the following condition were true
		 * (prefix.equalsIgnoreCase("exa"))
		 */
		// If the user has already completed this session, then remind them
		// they have completed their request
		String requestCompleted = getSessionString("suc.request.complete");
		
		if(FieldUtility.fieldIsPresent(requestCompleted) && requestCompleted.equalsIgnoreCase("true"))
		{
			endPageHeader = getApplicationString("ui.request.complete.header");
			List<String> messageList = getMessageElements("ui.request.complete.message.count");
			
			for(String message: messageList)
			{
				addActionMessage(message);
			}
			
			addReferenceNumber();
			
			return false;
		}
		
		allowedOrNot = checkSession(this.getClass().getSimpleName());     
		
		/* If session expired, or invalidated, then put up messages indicating state */
		if(!allowedOrNot)
		{
			endPageHeader = (String)getApplicationMap().get("ui.expired.header");
			
			List<String> messageList = getMessageElements("ui.expired.message.count");
			
			for(String message: messageList)
			{
				addActionMessage(message);
			}
		}
		else
		{
             // Keep a list of the prefixes which add data to session
			if(getSessionMap().get(UIConstants.USER_ACTION_FLOW)  == null)
			{
				getSessionMap().put(UIConstants.USER_ACTION_FLOW, new ArrayList<String>());
			}
			
			// Don't double-enter a screen both when we arrive, and when they enter data and/or hit enter
			ArrayList<String> navList = ((ArrayList<String>)getSessionMap().get(UIConstants.USER_ACTION_FLOW));
			
			int listSize =  navList.size();


			if(navList.isEmpty() ||  !navList.get(listSize -1).equals(prefix +"."))
			{
				((ArrayList<String>)getSessionMap().get(UIConstants.USER_ACTION_FLOW)).add(prefix+".");
			}
			
			// 1st instance of ActionClass is a transfer from previous screen, next is a hit of 'continue' button
			control = (String)getSessionMap().get("control");
			if(FieldUtility.fieldIsPresent(control) && !control.equalsIgnoreCase(prefix))
			{
				getSessionMap().remove("control");
				mergeFormDataFromSession2();               
				control = null;
			}
		}

		// Update Progress Meter information if we're going to the next logical page
		if(allowedOrNot)
		{
			String newScreenNumber = getApplicationString(UIConstants.SCREEN_POSITION_PREFIX +this.getClass().getSimpleName());
			if(FieldUtility.fieldIsPresent(newScreenNumber))
			{
				getSessionMap().put(UIConstants.CURRENT_SCREEN_NUMBER, newScreenNumber);
			}
            // Do not add Exception Actions
			/*
			 * In a previous version there was an if stmt surrounding the bracketed code below consisting of this conditional
			 * (!this.getClass().getSimpleName().startsWith("Exception"))
			 */
 
			{
				String uri = this.getHttpServletRequest().getRequestURI();
				Navigation.addAction(this.getClass().getSimpleName(), uri, getSessionMap());
			}
		}
		
		if(!getClass().getSimpleName().equals("RAConnectionAction") && !getClass().getSimpleName().equals("WelcomeAction"))
		{
			previousAction = Navigation.getPreviousAction(getSessionMap(), getClass().getSimpleName(), Navigation.RETURN_TYPE.LATTER); 
		}
		
		return allowedOrNot;
	}
	
	/**
	 * Retrieve message(s) which share all but the last node of the property which represents the count<br/>
	 *          number of messages.  <br/>
	 *          For Example: ui.error.message.count=2, would also mean there are two properties with the following name:<br/>
	 *                       ui.error.message1, ui.error.message2
	 * @param countPropertyName
	 * @return
	 */
	public List<String> getMessageElements(String countPropertyName)
	{
		ArrayList<String> messageList = new ArrayList<String>();
		int elementCount = 0;           
		
		String elementNamePrefix = countPropertyName.substring(0, countPropertyName.lastIndexOf('.'));   

		if(FieldUtility.fieldIsPresent((String)getApplicationMap().get(countPropertyName)))
		{
			elementCount =  FieldUtility.convertStringToInt((String)getApplicationMap().get(countPropertyName));
			for(int i = 1; i <= elementCount ; i++)
			{
				messageList.add((String)getApplicationMap().get(elementNamePrefix +i));
			}
		}


		return messageList;
	}
	
	/**
	 * Add a list of messages to the Struts2 ActionMessage object
	 * @param messages - The list of messages to add
	 */
	public void addActionMessage(List<String> messages)
	{
		for(String message: messages)
		{
			addActionMessage(message);
		}
	}
	
	
	/**
	 * Check to see if this action is allowed
	 * @param className String representing the class name of the current action
	 * @return
	 */
	private boolean checkSession(String className)
	{
		boolean allow          = true;
		
		/* Clean-up any old[er] session data on every RA_CONNECTION */
		if(className.equalsIgnoreCase(CONNECTION_ACTION))                    
		{
			deleteOldSessionData();
			allow = true;
            getSessionMap().put(RAC_VALID_SESSION, "y");
            getSessionMap().put("client.ip.address", getHttpServletRequest().getRemoteAddr());
            log.info(String.format("%s client.ip.address(%s)", getUniqueId(), getHttpServletRequest().getRemoteAddr()));
		}
		else 
		if(getHttpServletRequest().getSession(false) == null || 
		   getSessionMap().get(RAC_VALID_SESSION)  == null ||
		  !getSessionMap().get(RAC_VALID_SESSION).equals("y"))              
		{
			allow = false;
            log.error(String.format("%s SESSION[%s] -- RAC_VALID_SESSION[%s]"
                    , getUniqueId()
                    , getHttpServletRequest().getSession(false)
                    , getSessionMap().get(RAC_VALID_SESSION)
                    ));
		}
		
		return allow;
	}

	/**
	 * Delete any old session data every time RA makes a new connection
	 */
	public void deleteOldSessionData()
	{
		Map<String, Object> ses   = getSessionMap();
		String uniqueId      =  getUniqueId();
		ses.clear();
		if(uniqueId != null)
		{
			ses.put(UIConstants.SESSION_NEW_UNIQUE_ID, uniqueId);
		}
	}
	
	/**
	 * Determine if current session is expired/new or not
	 * @param session - session created from getSession(false)
	 * @return - true if session is new; else, return false
	 */
	public boolean isNewSession(Object session)
	{
		return (session == null ? true: false);
	}
	

	
	/**
	 * This method is for testing, and turns off server-side validation
	 * if a key exists in session memory of 'debug.skip.validation'
	 * 
	 * Basically, it allows you to walk from screen-to-screen without beign stopped
	 * @param originalStatus is the original return status
	 * @return 'success' if validation key exists, else returns the original status
	 */
	public String filterSuccess(String originalStatus)
	{
		String filterSuccess = "";

		if(getSessionMap().containsKey("debug.skip.validation") && getSessionString("debug.skip.validation").equalsIgnoreCase("on" )
			&& FieldUtility.fieldIsPresent(btnsubmit))
		{
			filterSuccess =  "success";
		}
		else
		if(getSessionMap().containsKey("vfy.return.to.verify") && ((String)getSessionMap().get("vfy.return.to.verify")).equalsIgnoreCase("true") &&
			originalStatus.equalsIgnoreCase(SUCCESS))
		{
			filterSuccess =  "verify";
		}
		else if(originalStatus.equalsIgnoreCase(SUCCESS))
		{
			String className         = this.getClass().getSimpleName();
			String currentActionName = className.substring(0, className.indexOf("Action"));
			String nextActionName   = getNextActionName(currentActionName, originalStatus);
			filterSuccess =  nextActionName;
		}
		else
		{
			filterSuccess = originalStatus;
		}
		
		return filterSuccess;
	}
	
	public String getNextActionName(String currentScreenName, String originalStatus)
	{
		String nextScreen    = "";
		String raScreenName  = getSessionString("rac.raScreens");     
		ArrayList<String> screenList = (ArrayList<String>)getApplicationMap().get(raScreenName);
		int orderIndex = screenList.indexOf(currentScreenName);
		
		log.info("current screen name = " + currentScreenName);
		log.info("ra screen name = " + raScreenName);
		log.info("order index = " + orderIndex);
		
		// When we are at RAConnection, then be sure to follow order in database for next screen
		String currentAction = this.getClass().getSimpleName();
		if(currentScreenName.equals(RA_CONNECTION) && currentScreenName.equals(currentAction.substring(0, currentAction.length()- "Action".length())))
		{
			nextScreen = screenList.get(FIRST_SCREEN);
			return nextScreen;
		}
		
		if(orderIndex > -1)
		{
			orderIndex++;
		}
		
		// Prevent IndexOutOfBoundsException
		if((orderIndex > -1) && (orderIndex <= (screenList.size() - 1)))
		{
			nextScreen = screenList.get(orderIndex);
		}
		else
		{
			nextScreen = originalStatus;
		}
		
		// However, if the originalStatus was 'success' and we're going to an optional screen
		// reset status to success as next action name
		// The action Class should always specify [in source code] the name of the optional screen
		if(originalStatus.equalsIgnoreCase(SUCCESS) && isNextScreenOptional(nextScreen))
		{
			nextScreen = SUCCESS;
		}
		
		log.info("Next screen = " + nextScreen);
		return nextScreen;
	}
	
	public boolean isNextScreenOptional(String nextScreen)
	{
		boolean isNextScreenOptional = false;
		
		String optionalScreens = getApplicationString("logic.optional.screens");
		if(optionalScreens.contains(nextScreen))
		{
			isNextScreenOptional = true;
		}
		log.info("Next Screen optional "  + isNextScreenOptional);
		return isNextScreenOptional;
	}
	
	/**
	 * Return the current URI so the front-end can set the back button when we leave an action
	 * @return
	 */
	protected String getCurrentURI()
	{
		return getHttpServletRequest().getRequestURI();
	}
	
	/**
	 * 
	 * @return the Map<String, Object> which represents application memory for this application
	 */
	protected Map<String, Object> getApplicationMap()
	{
		return ActionContext.getContext().getApplication();
	}
	
	/**
	 * Return an item from application memory, by anme
	 * @param propertyName - The name of the property to return
	 * @return - an Object stored by the name, or null if there is no property;
	 */
	protected String getApplicationString(String propertyName)
	{
		return (String)getApplicationMap().get(propertyName);
	}
	
	/**
	 * 
	 * @return the Map<String, Object> which represents session memory for this user
	 */
	protected Map<String, Object> getSessionMap()
	{
		return ActionContext.getContext().getSession();
	}
	
	/**
	 * Return an item from session memory, by name
	 * @param propertyName - The name of the property to return
	 * @return - an Object stored by the name, or null if there is no property;
	 */
	protected String getSessionString(String propertyName)
	{
		return (String)getSessionMap().get(propertyName);
	}
	
	/**
	 * 
	 * @return the HttpServletRequest object
	 */
	protected HttpServletRequest getHttpServletRequest()
	{
		return ServletActionContext.getRequest();
	}
	
	/**
	 * 
	 * @return the HttpServletResponse object
	 */
	protected HttpServletResponse getHttpServletResponse(){
		return ServletActionContext.getResponse();
	}
	
	/**
	 * Writes all declared fields [which represent form variables] to session memory.
	 * List, or dropdowns will be skipped.
	 * 
	 * @param prefixName is the 3-character unique form identifier.  It is used
	 *        as part of the prefix of the session key
	 */
	protected void saveFieldsToSession(String prefixName )
	{
		LOG.info("base class name ->> " +this.getClass().getSuperclass().getSimpleName());
		Method[] methods = this.getClass().getDeclaredMethods();
		
		if(!this.getClass().getSuperclass().getSimpleName().equalsIgnoreCase("BaseAction"))
		{
			methods = this.getClass().getSuperclass().getDeclaredMethods();
		}
		
		Map<String, Object> session   = getSessionMap();
		
		for(Method method: methods)
		{
			if(method.getName().startsWith("get"))
			{
				try 
				{
					String originalFieldName = method.getName().substring(MagicNumber.I3);
					String fieldName = originalFieldName;
					LOG.info("field name = = " + fieldName);

					/* Skip any list(s) or map(s) constructs */
					fieldName = fieldName.substring(0,1).toLowerCase() + fieldName.substring(1);
					if(fieldName.toLowerCase().contains("list") || fieldName.toLowerCase().contains("map")
					|| fieldName.equals("log"))
					{
						continue;
					}

					/* Skip any constants which are traditionally all uppercase variables */
					if(originalFieldName.equals(originalFieldName.toUpperCase()))
					{
						continue;
					}
					
					LOG.info("field value = " + method.invoke(this));
					session.put(prefixName +"." +fieldName, method.invoke(this));
				} 
				catch (Exception e) 
				{
					log.warn(String.format("Exception processing saveFieldsToSession -- %s", e.getMessage()));
				} 
			}
		}
		// Indicate this is a 2nd to n instance of screen
		getSessionMap().put("control", prefix);		
		control = prefix;
	}
	
	/**
	 * Determine if the person was found by 'find person', during verification process 
	 * @return true if the person was found, else false
	 */
	public boolean personWasFound()
	{
		return (getSessionString("vfy.person.found").equalsIgnoreCase("yes"));
	}
	
	/**
	 * Determine if the person was not found by 'find person', during verification process
	 * @return true if the person was not found, else false
	 */
	public boolean personWasNotFound()
	{
		boolean personWasNotFound = true;
		
		if(personWasFound())
		{
			personWasNotFound = false;
		}
        return personWasNotFound;
        
	}
	
	public void listDeclaredFields()
	{
		Field[] fields = this.getClass().getDeclaredFields();
		for(Field field: fields)
		{
			log.info(String.format("  field name[%s]", field.getName()));
		}
	}
	
	public void mergeFormDataFromSession2()
	{
		/** 1. Get field names, 
		 *  2. then exclude 'map' and 'list', 'log'
		 *  3. then get data from session, and call setter method
		 *   
		 */
		Class[] args = new Class[1];
        args[0] = String.class;
        
        // Indicates whether or not this class is extended from a class other than BaseAction
        boolean otherSuperClass = false;    
        
		Field[] fields = this.getClass().getDeclaredFields();
		
		// With extended classes, we must go back to pick-up the fields of the base class
		if(!this.getClass().getSuperclass().getSimpleName().equalsIgnoreCase("BaseAction"))
		{
			fields = this.getClass().getSuperclass().getDeclaredFields();
			otherSuperClass = true;
		}
		
		Method method, method2;
		for(Field field: fields)
		{
			// Ignore 'list', 'map' and 'log' fields
			if(excludedFieldName(field))
			{
				continue;
			}
			
			/* Skip any constants which are traditionally all uppercase variables */
			if(field.getName().toUpperCase().equals(field.getName()))
			{
				continue;
			}
			
			String methodNameSet = field.getName();
			String methodNameGet =  field.getName();     
			methodNameSet = String.format("set%s%s", methodNameSet.substring(0,1).toUpperCase(), methodNameSet.substring(1));
			methodNameGet = String.format("get%s%s", methodNameGet.substring(0,1).toUpperCase(), methodNameGet.substring(1));
			try 
			{
				if(!otherSuperClass )
				{
					method = this.getClass().getDeclaredMethod(methodNameSet, args);
					method2 = this.getClass().getDeclaredMethod(methodNameGet);
				}
				else
				{
					method = this.getClass().getSuperclass().getDeclaredMethod(methodNameSet, args);
					method2 = this.getClass().getSuperclass().getDeclaredMethod(methodNameGet);
				}
				
				String previousFieldData = (String)getSessionMap().get(prefix +"." +field.getName());
				String currentFieldData  = (String)method2.invoke(this);
				if(currentFieldData != null )
				{
					method.invoke( this, currentFieldData );
				}
				else
				if(previousFieldData != null )
				{
					method.invoke( this, previousFieldData );
				}
				
			}
			catch (NoSuchMethodException e) 
			{
				// Ignore failure, but log in syslog
				log.debug(String.format("%s Action[%s] missing method for variable[%s] -- methodName[%s] for ", getUniqueId(), getClass().getSimpleName(), field.getName(),methodNameSet));
			} 
			catch (IllegalArgumentException e) 
			{
				log.debug(String.format("%s Action[%s] IllegalArgumentException[%s]    -- methodName[%s] for ", getUniqueId(), getClass().getSimpleName(), field.getName(),methodNameSet));
			} 
			catch (IllegalAccessException e) 
			{
				log.debug(String.format("%s Action[%s] IllegalAccessException[%s]      -- methodName[%s] for ", getUniqueId(), getClass().getSimpleName(), field.getName(),methodNameSet));
			} 
			catch (InvocationTargetException e) 
			{
				log.info(String.format("%s Action[%s] InvocationTargetException[%s]    -- methodName[%s] for ", getUniqueId(), getClass().getSimpleName(), field.getName(),methodNameSet));
			}
		}
	}
	
	/**
	 * Determine if this is a 'field' which should be excluded
	 * @param field - The Field being tested
	 * @return - a boolean value of 'true', which means exclude this field, or false
	 */
	public boolean excludedFieldName(Field field)
	{
		boolean excludedFieldName = false;
		if(field.getName().toLowerCase().contains("list") || field.getName().toLowerCase().contains("map") 
				|| field.getName().equalsIgnoreCase("log"))
		{
			excludedFieldName = true;
		}
		
		return excludedFieldName;

	}
	
	/**
	 * Looks for previously entered data in session memory, and initializes field if it exists.
	 * @param key is the session key of the data to lookup in session
	 * @param data is the form field, which can be null.  If it is null, we'll look for session data
	 * @return
	 */
	public String mergeFormDataFromSession(String key, String data)
	{
		return (data == null ? (String)getSessionMap().get(prefix +"." +key) : data);
	}
	
	/**
	 * Return the unique/current session Id.  
	 * @return
	 */
	public String getUniqueId()
	{
		// Option for a more eye-friendly session/Number
		String simpleIdOption = getApplicationString(UIConstants.TRANSACTION_ID_NUMBERING);
		if(FieldUtility.fieldIsPresent(simpleIdOption) && FieldUtility.isOptionYes(simpleIdOption))
		{
			return getSessionString(UIConstants.SESSION_NEW_UNIQUE_ID); 
		}
		else
		{
			return getHttpServletRequest().getSession().getId();
		}
	}
	
	public void addReferenceNumber()
	{
		addActionMessage(String.format("Reference number %s", getUniqueId()));
	}

	/**
	 * @return the uiMessage
	 */
	public String getUiMessage() {
		return uiMessage;
	}

	/**
	 * @param uiMessage the uiMessage to set
	 */
	public void setUiMessage(String uiMessage) {
		this.uiMessage = uiMessage;
	}

	/**
	 * @return the endPageHeader
	 */
	public String getEndPageHeader() {
		return endPageHeader;
	}

	/**
	 * @param endPageHeader the endPageHeader to set
	 */
	public void setEndPageHeader(String endPageHeader) {
		this.endPageHeader = endPageHeader;
	}

	/**
	 * @return the control
	 */
	public String getControl() {
		return control;
	}

	/**
	 * @param control the control to set
	 */
	public void setControl(String control) {
		this.control = control;
	}

	/**
	 * @return the btnsubmit
	 */
	public String getBtnsubmit() {
		return btnsubmit;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	/**
	 * @param btnsubmit the btnsubmit to set
	 */
	public void setBtnsubmit(String btnsubmit) {
		this.btnsubmit = btnsubmit;
	}

	/**
	 * @return the previousScreen
	 */
	public String getPreviousAction() {
		return previousAction;
	}

	/**
	 * @param previousScreen the previousScreen to set
	 */
	public void setPreviousAction(String previousAction) {
		this.previousAction = previousAction;
	}
}
