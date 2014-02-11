/* SVN FILE: $Id: SessionDeleteHelper.java 5977 2013-01-07 03:10:12Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.helper;

import java.util.Map;

import edu.psu.iam.cpr.ip.util.TimedServicesUtil;
import javax.servlet.http.HttpSession;

/**
 * SessionDeleteHelper invokes the delete session logic 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5977 $
 * @lastrevision $Date: 2013-01-06 22:10:12 -0500 (Sun, 06 Jan 2013) $
 */
public class SessionDeleteHelper 
{
	
	/**
	 * Delete the current session object
	 * @param session - the HttpSession object
	 * @param applicationMap - a map that contains application properties
	 */
	public static void invalidate(HttpSession session, Map applicationMap)
	{
		TimedServicesUtil sessionUtilObj = new TimedServicesUtil( session, applicationMap, "invalidate");
		Thread sessionThread = new Thread(sessionUtilObj);
		sessionThread.start();
	}
}
