/* SVN FILE: $Id: UnknownEvent.java 6124 2013-02-05 21:26:15Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.web.handler;
/**
 * This is a catch-all for any unknown actions.  
 * 
 * Any URL that does not have a defined 'Struts' action will end-up in this handler. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.web.handler 
 * @author $Author: jal55 $
 * @version $Rev: 6124 $
 * @lastrevision $Date: 2013-02-05 16:26:15 -0500 (Tue, 05 Feb 2013) $
 */

import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.UnknownHandler;
import com.opensymphony.xwork2.XWorkException;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.entities.ActionConfig;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import static java.lang.System.out;

public class UnknownEvent implements UnknownHandler 
{
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(UnknownEvent.class);
	
	protected Configuration configuration;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.UnknownHandler#handleUnknownAction(java.lang.String, java.lang.String)
	 */
	@Override
	public ActionConfig handleUnknownAction(String namespace, String actionName) throws XWorkException 
	{
		Map<String, Object> session = ActionContext.getContext().getSession();
		String uniqueId = (String)session.get(UIConstants.SESSION_NEW_UNIQUE_ID);
		
		// Someone, either an end-user, or a programmed Struts action has requested an undefined action
		LOG.info(String.format("%s UnknownAction requested. namespace[%s], action[%s]", uniqueId, namespace, actionName));
		throw new XWorkException(UIConstants._404_UNKNOWN_ACTION);
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.UnknownHandler#handleUnknownActionMethod(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object handleUnknownActionMethod(Object arg0, String arg1)   throws NoSuchMethodException 
	{
		// TODO Auto-generated method stub
		out.println("\n..inside unkown hndler..handleUnknownMethod string string");
		return null;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.UnknownHandler#handleUnknownResult(com.opensymphony.xwork2.ActionContext, java.lang.String, com.opensymphony.xwork2.config.entities.ActionConfig, java.lang.String)
	 */
	@Override
	public Result handleUnknownResult(ActionContext arg0, String arg1,
			ActionConfig arg2, String arg3) throws XWorkException {
		// TODO Auto-generated method stub
		out.println("\n..inside unkown hndler..handleUnknownResult string string");
		return null;
	}

}
