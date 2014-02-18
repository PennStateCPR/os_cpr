/* SVN FILE: $Id: DataLockInterceptor.java 5980 2013-01-07 14:34:28Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.interceptor;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import edu.psu.iam.cpr.ip.ui.action.BaseAction;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.exception.DataLockException;
import edu.psu.iam.cpr.ip.ui.navigation.Navigation;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.util.Pair;

/**
 * DataLockInterceptor intercepts requests to return to pages which have been locked 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.interceptor 
 * @author $Author: jal55 $
 * @version $Rev: 5980 $
 * @lastrevision $Date: 2013-01-07 09:34:28 -0500 (Mon, 07 Jan 2013) $
 */

public class DataLockInterceptor implements Interceptor 
{
	private static final String DATALOCK_INITIAL_WARNING = "datalock.initial.warning";
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(DataLockInterceptor.class);
    private static final long serialVersionUID = -1855238485022372087L;

    @Override
	public void destroy() 
	{
	}

	@Override
	public void init() 
	{
	}

	@SuppressWarnings("unchecked")
	@Override
	public String intercept(ActionInvocation invocation) throws Exception 
	{
		Object action = invocation.getAction();
		String simpleClassName = invocation.getAction().getClass().getSimpleName();
	    long startTime = System.currentTimeMillis();
	    
	    // logic begin
	    ActionContext context = invocation.getInvocationContext();
	    ServletContext ctx = (ServletContext) context.get(ServletActionContext.SERVLET_CONTEXT);
	    HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
	    HttpSession session = request.getSession(false);
	    
	    String uniqueId = getUniqueId(ctx, session);

	    // Ensure the data lock switch goes on just prior to Password, if Policy didn't enable it
	    if( session != null && action.getClass().getSimpleName().startsWith("SecurityQuestion"))
	    {
	    	session.setAttribute(UIConstants.DATA_LOCKDOWN_KEY, "true");
	    	
	    	if(FieldUtility.fieldIsNotPresent((String)session.getAttribute(DATALOCK_INITIAL_WARNING)))
	    	{
	    		LOG.info(String.format("%s data is in lock-down mode for this session/task", uniqueId));
	    		session.setAttribute(DATALOCK_INITIAL_WARNING, "warned");
	    	}
	    }

	    if(isDataInLockDown(session) && isCurrentActionProhibited(invocation, session) &&  action instanceof BaseAction)
	    {
	    	((BaseAction)action).checkLockable();
	    }
	    
	    String result = invocation.invoke();
	 
	    long endTime = System.currentTimeMillis();

	    ArrayList<Pair<String, String>> performanceList = null; 
	    performanceList =  (session.getAttribute(UIConstants.PERFORMANCE_DATA_KEY) == null) 
	    		? new ArrayList<Pair<String, String>>() 
	    		: (ArrayList<Pair<String, String>>)session.getAttribute(UIConstants.PERFORMANCE_DATA_KEY);
	    Pair<String, String> timings = new Pair<String, String>(simpleClassName, Long.toString(endTime - startTime));
	    performanceList.add(timings);

	    session.setAttribute(UIConstants.PERFORMANCE_DATA_KEY, performanceList);
	     
		return null;
	}
	
	/**
	 * Determine if this session's data is locked-down; meaning, cannot be changed
	 * @param session
	 * @return true if locked down, else return false if not
	 * @throws DataLockException
	 */
	public boolean isDataInLockDown(HttpSession session) throws DataLockException
	{
		boolean isInLockDown = false;
		
		if(session != null)
		{
			String policyAgree = (String)session.getAttribute(UIConstants.DATA_LOCKDOWN_KEY);
			if(policyAgree != null && policyAgree.equalsIgnoreCase("true") )
			{
				isInLockDown = true;
			}
		}
		
		return isInLockDown;
	}
	
	/**
	 * Ensure navigation from screen-to-screen is allowed
	 * @param invocation - The element describing where the user wants to go
	 * @param session - Reference to HttpSession memory
	 * @return - return true if navigation request is allowable, else return false
	 */
	public boolean isCurrentActionProhibited(ActionInvocation invocation, HttpSession session)
	{
		boolean isActionProhibited = false;
		String className = invocation.getAction().getClass().getSimpleName();
		String uniqueId = (String)session.getAttribute(UIConstants.SESSION_NEW_UNIQUE_ID);
		
		Map<String, Navigation.NavigationElement> navElements;
		
		if(session.getAttribute(UIConstants.NAVIGATION_KEY) != null)
		{
			navElements = (Map<String, Navigation.NavigationElement>)session.getAttribute(UIConstants.NAVIGATION_KEY);
			if(navElements.containsKey(className) && navElements.get(className).isLocked())
			{
				isActionProhibited = true;
				LOG.info(String.format("%s Current action [%s] is prohibited because of data lock-down", uniqueId, className));
			}
		}
		return isActionProhibited;
	}
	
	/**
	 * Retrieve the unique/simple id for this user's session
	 * @param ctx - Reference to application Servlet context
	 * @param session - Reference to HttpSession for this user
	 * @return
	 */
	public String getUniqueId(ServletContext ctx, HttpSession session)
	{
		// If there is no session, don't wait for a recursive NullPointerException 
		if(session == null)
		{
			return null;
		}
		// Option for a more eye-friendly session/Number
		String simpleIdOption = ctx.getInitParameter(UIConstants.TRANSACTION_ID_NUMBERING);
		if(FieldUtility.fieldIsPresent(simpleIdOption) && FieldUtility.isOptionYes(simpleIdOption))
		{
			return (String)session.getAttribute(UIConstants.SESSION_NEW_UNIQUE_ID); 
		}
		else
		{
			return session.getId();
		}
	}

}
