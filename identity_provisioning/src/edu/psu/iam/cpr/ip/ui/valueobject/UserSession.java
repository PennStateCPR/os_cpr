/* SVN FILE: $Id: UserSession.java 5827 2012-12-10 10:22:25Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.valueobject;

import javax.servlet.http.HttpSession;

/**
 * ActiveSessionUser is a mapping of session id to a simpler derived Id 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.valueobject 
 * @author $Author: jal55 $
 * @version $Rev: 5827 $
 * @lastrevision $Date: 2012-12-10 05:22:25 -0500 (Mon, 10 Dec 2012) $
 */
public class UserSession 
{
	private String      derivedId;
	private HttpSession session  ;
	
	
	/** Record the Map of SessionId to a more easily followed derivedId
	 * @param sessionId
	 * @param derivedId
	 */
	public UserSession(HttpSession session, String derivedId) {
		this.session   = session;
		this.derivedId = derivedId;
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
	 * @return the derivedId
	 */
	public String getDerivedId() {
		return derivedId;
	}
	/**
	 * @param derivedId the derivedId to set
	 */
	public void setDerivedId(String derivedId) {
		this.derivedId = derivedId;
	}
	
}
