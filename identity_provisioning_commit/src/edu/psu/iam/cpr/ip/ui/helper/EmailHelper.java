/* SVN FILE: $Id: EmailHelper.java 6013 2013-01-16 17:17:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.helper;

/**
 * EmailHelper - static methods to assist in email transmission 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.helper 
 * @author $Author: jal55 $
 * @version $Rev: 6013 $
 * @lastrevision $Date: 2013-01-16 12:17:58 -0500 (Wed, 16 Jan 2013) $
 */
import java.util.Map;

import edu.psu.iam.cpr.ip.util.HtmlEmail;

public final class EmailHelper 
{
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private EmailHelper()  { }
	
	/**
	 * Send a dual email [HTML & text] indicating successful creation, or reset of a user-id
	 * @param applicationMap
	 * @param to
	 * @param textHtml 
	 * @return
	 */
	public static HtmlEmail getSuccessEmailObj(Map<String, Object> applicationMap, String type, String to, String textHtml, String uniqueId)
	{
		String subject = (type.equalsIgnoreCase("created")) 
				? (String)applicationMap.get("ui.mail.success.subject") : (String)applicationMap.get("ui.mail.success.reset.subject");
				
		String text = (type.equalsIgnoreCase("created")) 
				? (String)applicationMap.get("ui.mail.success.text") : (String)applicationMap.get("ui.mail.success.reset.text");
				
		return  new HtmlEmail( (String)applicationMap.get("ui.mail.host")
	            ,(String)applicationMap.get("ui.mail.from")
	            ,(String)applicationMap.get("ui.mail.from.person")
	            ,to
	            ,(String)applicationMap.get("ui.mail.bcc")
	            ,subject
	            ,text
	            ,textHtml
	            ,uniqueId
               );
	}
}
