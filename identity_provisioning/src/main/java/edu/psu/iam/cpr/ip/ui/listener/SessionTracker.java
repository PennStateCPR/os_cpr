/* SVN FILE: $Id: SessionTracker.java 5938 2012-12-18 22:08:34Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.listener;

/**
 * SessionTracker is SessionListener keeping track of the number of active sessions. 
 * Optionally, it lays the framework for viewing in-flight information
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.servlet 
 * @author $Author: jal55 $
 * @version $Rev: 5938 $
 * @lastrevision $Date: 2012-12-18 17:08:34 -0500 (Tue, 18 Dec 2012) $
 */

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.ui.valueobject.UserSession;
import edu.psu.iam.cpr.ip.util.Pair;

@WebListener("Accumulate and write-out performance data in milliseconds")
public class SessionTracker implements HttpSessionListener 
{
	public static final String ACTIVE_USERS = "active.users";

	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(SessionTracker.class);
	
	/** Session Number which is incremented sequentially */
	private static long sessionNumber = 0;
	
	/** formatter for part of new unique id */
	private static final DecimalFormat DF = new DecimalFormat("###,###,###,##0");
	
	@Override
	public void sessionCreated(HttpSessionEvent event) 
	{
		// A new session was just created, park a reference to it in map
		HttpSession session     = event.getSession();
		ServletContext ctx  = session.getServletContext();  
			
		if(ctx.getAttribute(ACTIVE_USERS) == null)
		{
			ctx.setAttribute(ACTIVE_USERS, new ConcurrentHashMap<String, UserSession>());
		}
			
	    @SuppressWarnings("unchecked")
		Map<String, UserSession>  activeUsers = (Map<String, UserSession>)ctx.getAttribute(ACTIVE_USERS); 
	    
	    // Bump session number by 1 
		String newSessionNumber = DF.format(++sessionNumber);
		
		// Create the unique Id by combining the new number with the suffix created in UIInitializationServlet
		String newUniqueId = String.format("%s%s", newSessionNumber, ctx.getAttribute(UIConstants.SESSION_UNIQUE_ID_SUFFIX));
		UserSession userSession = new UserSession(session, newUniqueId );
		
		// Add the UserSession object to the Map
		activeUsers.put(session.getId(), userSession);
		
		// Output the map back to the ServletContext/application scope memory
		ctx.setAttribute(ACTIVE_USERS, activeUsers);
		
		// For individual [session] quick access, store individual entry in session also
		session.setAttribute(UIConstants.SESSION_NEW_UNIQUE_ID, newUniqueId);
		
		LOG.info(String.format("%s created -- mapped to %s", session.getId(), newUniqueId));
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event)   
	{
		/** A session just expired, or was invalidated, remove the reference to it in map
		 *  and print any performance data to the log
		 */
		
		HttpSession session     = event.getSession();
		ServletContext ctx  = session.getServletContext();  
			
		if(ctx.getAttribute(ACTIVE_USERS) == null)
		{
			ctx.setAttribute(ACTIVE_USERS, new ConcurrentHashMap<String, UserSession>());
		}
			
	    Map<String, UserSession>  activeUsers = (Map<String, UserSession>)ctx.getAttribute(ACTIVE_USERS);
	    
	    // When outputting, use the proper unique id:  sessionId, or simple unique Id
	    String simpleIdOption = (String) ctx.getInitParameter(UIConstants.TRANSACTION_ID_NUMBERING);
	    String uniqueId = "";
	    String sessionId= session.getId();
	    
		if(FieldUtility.fieldIsPresent(simpleIdOption) && FieldUtility.isOptionYes(simpleIdOption))
		{
			uniqueId = (String)session.getAttribute(UIConstants.SESSION_NEW_UNIQUE_ID); 
		}
		else
		{
			uniqueId = session.getId();
		}
		
		// Remove this user from session table
	    activeUsers.remove(session.getId());
	    
	    // Output the performance timings to the log -- someone can later review them
	    ArrayList<Pair<String, String>> performanceList = null; 
	    performanceList =  (session.getAttribute(UIConstants.PERFORMANCE_DATA_KEY) == null) 
	    		? new ArrayList<Pair<String, String>>() : (ArrayList<Pair<String, String>>)session.getAttribute(UIConstants.PERFORMANCE_DATA_KEY);
	    	
	    
	    DecimalFormat df = new DecimalFormat("0000000");
	    for(Pair<String, String> timingEvent: performanceList)
	    {
	    	LOG.info(String.format("%s @timing-event  time=%s class=%s",
	    			               uniqueId, df.format(Long.parseLong(timingEvent.getValue())), timingEvent.getKey()));
	    }
	    
	    LOG.info(String.format("%s session destroyed session id (%s)", uniqueId, sessionId ));
	}
}
