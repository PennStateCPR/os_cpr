/* SVN FILE: $Id: ExceptionAction.java 6124 2013-02-05 21:26:15Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.XWorkException;

import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.exception.DataLockException;
import edu.psu.iam.cpr.ip.ui.helper.SessionDeleteHelper;
import edu.psu.iam.cpr.ip.ui.helper.StringHelper;
import edu.psu.iam.cpr.ip.ui.navigation.Navigation;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * ExceptionAction is a catch-all Error handler for the Identity Provisioning UI 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 6124 $
 * @lastrevision $Date: 2013-02-05 16:26:15 -0500 (Tue, 05 Feb 2013) $
 */
public class ExceptionAction extends BaseAction 
{
	private static final String DO_NOT_INVALIDATE = "do.not.invalidate";
    private static final long serialVersionUID = -4539516386453991142L;

    public String exception;
	
	@Override
	@Action(value="error_report",results={ @Result(name=SUCCESS,location="/jsp/endPage.jsp"),
			                               @Result(name="failure",location="/jsp/endPage.jsp"),
			                               @Result(name="SecurityQuestionAction",location="/security_questions",type=REDIRECT),
			                               @Result(name="security_questions",location="/security_questions",type=REDIRECT),
			                               @Result(name="SecurityQuestionSplash",location="/sq0",type=REDIRECT),
			                               @Result(name="SecurityQuestion1Action",location="/sq1",type=REDIRECT),
			                               @Result(name="SecurityQuestion2Action",location="/sq2",type=REDIRECT),
			                               @Result(name="SecurityQuestion3Action",location="/sq3",type=REDIRECT),
			                               @Result(name="PasswordAction",location="/password",type=REDIRECT)
			                              } )
	
	public String execute() 
	{
		// TODO Auto-generated method stub
		if(!setup("exa"))
		{
			return "failure";
		}
		
		String uniqueId = getUniqueId();
		
		log.debug(String.format("%s ExceptionAction", uniqueId));
		
		// If we were previously here, and did not invalidate, then we are 
		// allowing the user an opportunity to return to their previous activity/action
		String returnToAction = ""; 
		if(FieldUtility.fieldIsPresent(getSessionString(DO_NOT_INVALIDATE)) && getSessionString(DO_NOT_INVALIDATE).equalsIgnoreCase("y"))
		{
			getSessionMap().remove(DO_NOT_INVALIDATE);                 
			returnToAction = Navigation.getPreviousAction( getSessionMap(), this.getClass().getSimpleName(), Navigation.RETURN_TYPE.LATTER);
			return returnToAction;
		}
		Object statusCodeObj, messageObj, messageTypeObj, stackTraceElementObj;
		String statusCode         = null;
		String uri                = null;
        String message            = null;
        String messageType        = null;
        StackTraceElement[] trace = null;
        
        exception = "other";

		// Get Exception data objects
		statusCodeObj  = getSessionMap().get("eh.status.code");
		messageObj     = getSessionMap().get("eh.exception.message");
		messageTypeObj = getSessionMap().get("eh.message.type");
		stackTraceElementObj = getSessionMap().get("eh.exception.stack.trace");
		
		if(statusCodeObj != null)
		{
			statusCode = ((Integer)getSessionMap().get("eh.status.code")).toString() ;
		}
		
		if(stackTraceElementObj != null)
		{
			trace = (StackTraceElement[])stackTraceElementObj;
		}

		uri        = getSessionString("eh.exception.original.uri");                   
		
		if(messageObj != null)
		{
			message = messageObj.toString();
			if(message.toLowerCase().contains("data lock"))
			{
				exception = "datalock";
			}
		}
	
		if(messageTypeObj != null)
		{
			messageType = messageTypeObj.toString();
		}
		
		log.info(String.format("%s status code--> %s", uniqueId, statusCode));
		log.info(String.format("%s error uri ---> %s", uniqueId, uri    ));
		log.info(String.format("%s message -----> %s", uniqueId, message));
		log.info(String.format("%s message type-> %s", uniqueId, messageType));
		log.info(String.format("%s trace    ----> %s", uniqueId, trace[0].toString()));
		
		log.info(String.format("%s error uri ---> %s", uniqueId, uri));
		
		Map<String, Object> session = this.getSessionMap();
		Throwable exception = ((Throwable)session.get("eh.exception.object"));
		
		if(exception instanceof DataLockException)
		{
			setEndPageHeader(getApplicationString("exception.datalock.header"));
			addActionMessage(getMessageElements("exception.data.lock.message.count"));
			addReferenceNumber();
			getSessionMap().put(DO_NOT_INVALIDATE, "y");
			return SUCCESS;
		}

		if(exception instanceof CprException && ((CprException)exception).getReturnType().equals(ReturnType.SERVICE_AUTHENTICATION_EXCEPTION))
		{
			String principalId = getSessionString("rac.principalId");
			String password    = getSessionString("rac.password");
			String type        = ((CprException)exception).getReturnType().toString();
			log.info(String.format("%s RA %s failure - user (%s/%s) has failed authentication", uniqueId, type, principalId, password));
			
			/* Prepare error page with heading and messages */
			setEndPageHeader(getApplicationString(UIConstants.UI_RA_AUTH_FAILED_HEADER));
			log.info(String.format("%s reason for failure [%s]", uniqueId, exception.getMessage()));
	
			Map map = new HashMap();     

			// Make the referer the replacement token in messages
			map.put("rac" +".raURL", getSessionString("rac" +".raURL"));   
			List<String> errorList = getMessageElements("ui.ra_auth_failed.message.count");
			for(String aMessage: errorList)
			{
				this.addActionMessage(StringHelper.replaceTokensFromMap(map, aMessage));
			}
			
			addReferenceNumber();
			
			// print stack trace
			addTraceToLog(trace, uniqueId);
			
			return SUCCESS;
		}
		
		if(exception instanceof CprException &&  ((CprException)exception).getReturnType().equals(ReturnType.GENERAL_DATABASE_EXCEPTION))
		{
			log.error("Could not acquire a database connection");
			setEndPageHeader(getApplicationString(UIConstants.EXCEPTION_DATABASE_CONNECTION_HEADER));
			log.info(String.format("%s reason for failure [%s]", uniqueId, exception.getMessage()));
			List<String> errorList = getMessageElements(UIConstants.EXCEPTION_DATABASE_CONNECTION_MESSAGE_COUNT);
			addActionMessage(errorList);
			addReferenceNumber();

			log.info(String.format("%s Invalidating session", getUniqueId()));
			
			// add trace to Log
			addTraceToLog(trace, uniqueId);
			
			return SUCCESS;
		}
		
		if( exception instanceof XWorkException && exception.getMessage().equals(UIConstants._404_UNKNOWN_ACTION))
		{
			LOG.info(String.format("%s 404 unknown/undefined Struts action requested", uniqueId));
			setEndPageHeader(getApplicationString(UIConstants._404_UNKOWN_ACTION_HEADER));
			addActionMessage("Your request cannot be serviced--Request Undefined. ");
			addReferenceNumber();
			
			SessionDeleteHelper.invalidate(this.getHttpServletRequest().getSession(), this.getApplicationMap());
			
			return SUCCESS;
		}


		setEndPageHeader("Please Contact Customer Services");
		addActionMessage("Something unexpected has occurred. ");
		addActionMessage(getApplicationString(UIConstants.UI_ERROR_CALL_CUST_SERVICES));
		if(getUniqueId() != null)
		{
			addReferenceNumber();
		}
		
		// add trace to Log
		addTraceToLog(trace, uniqueId);
		
		// Invalidate this session in x-seconds
		log.info(String.format("%s initiating session delete", getUniqueId()));
		SessionDeleteHelper.invalidate(this.getHttpServletRequest().getSession(), this.getApplicationMap());
		
		return SUCCESS;
	}
	
	/**
	 * Print a stack trace if error warrants it
	 * @param trace - An array of StackTraceElement objects
	 * @param uniqueId - Unique Session id
	 */
	public void addTraceToLog(StackTraceElement[] trace, String uniqueId)
	{
		for(int i = 1; i < trace.length; i++)
		{
			log.info(String.format("%s trace --> %s", uniqueId, trace[i].toString()));
		}
	}

	/**
	 * @return the exception
	 */
	public String getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

}
