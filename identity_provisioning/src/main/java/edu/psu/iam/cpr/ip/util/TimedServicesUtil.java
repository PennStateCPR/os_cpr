/* SVN FILE: $Id: TimedServicesUtil.java 5975 2013-01-07 03:07:43Z jal55 $ */
package edu.psu.iam.cpr.ip.util;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;

/**
 * TimedServicesUtil is a service class that permits asynchronous actions to be executed
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5975 $
 * @lastrevision $Date: 2013-01-06 22:07:43 -0500 (Sun, 06 Jan 2013) $
 */

public class TimedServicesUtil implements Runnable 
{
	// The session object to focus on
    private HttpSession session;
    
    // A Map of the application
    @SuppressWarnings("rawtypes")
	private Map application;
    
    // The action to take against the session
    private String sessionRequest;
    
	// The delay, in seconds, before deleting the session object
    String sessionDelay;
    
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(TimedServicesUtil.class);
    
    /**
     * Constructor for SessionUtil
     * @param session
     * @param sessionRequest
     */
    public TimedServicesUtil(HttpSession session, Map application, String sessionRequest)
    {
    	this.session        = session;
    	this.sessionRequest = sessionRequest;
    	this.application    = application;
    }
    
	@Override
	public void run() 
	{
		String uniqueSessionId = (String) session.getAttribute(UIConstants.SESSION_NEW_UNIQUE_ID);
	
		if(sessionRequest.equalsIgnoreCase("invalidate"))
		{
			String sessionDelay    = (String) application.get(UIConstants.SESSION_DELETE_DELAY_IN_SECONDS);
			LOG.info(String.format("%s Invalidating session in %s seconds", uniqueSessionId, sessionDelay));
			try 
			{
				Thread.sleep(convertToMilliseconds(sessionDelay));
			} 
			catch (InterruptedException e) { }
			
			session.invalidate();
			
			LOG.info(String.format("%s session has been invalidated", uniqueSessionId ));
		}
	}
	
	/**
	 * Convert the number of seconds into milliseconds
	 * @param seconds
	 * @return a long of the number of milliseconds
	 */
	private long convertToMilliseconds(String seconds)
	{
		// Default -- if there are no seconds specified [null or zero] then default to 1 second
		Long delayInMilliseconds;
		
        try
        {
        	delayInMilliseconds = Long.parseLong(seconds) * 1000;
        }
        catch(Exception e)
        {
        	// All/any exceptions will force the delay to be 1 second, 1000 milliseconds
        	delayInMilliseconds = 1000l;
        }
		
		return delayInMilliseconds;
	}

	/**
	 * @return the session
	 */
	public HttpSession getSession() {
		return session;
	}

	/**
	 * @param session the session to set
	 */
	public void setSession(HttpSession session) {
		this.session = session;
	}

	/**
	 * @return the sessionRequest
	 */
	public String getSessionRequest() {
		return sessionRequest;
	}

	/**
	 * @param sessionRequest the sessionRequest to set
	 */
	public void setSessionRequest(String sessionRequest) {
		this.sessionRequest = sessionRequest;
	}
	
    /**
	 * @return the sessionDelay
	 */
	public String getSessionDelay() {
		return sessionDelay;
	}

	/**
	 * @param sessionDelay the sessionDelay to set
	 */
	public void setSessionDelay(String sessionDelay) {
		this.sessionDelay = sessionDelay;
	}

	/**
	 * @return the application
	 */
	public Map getApplication() {
		return application;
	}

	/**
	 * @param application the application to set
	 */
	public void setApplication(Map application) {
		this.application = application;
	}

}
