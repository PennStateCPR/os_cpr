/* SVN FILE: $Id: RegexHelper.java 5888 2012-12-12 02:14:04Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.helper;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * RegexHelper contains static methods to format miscellaneous items such as <br/>
 * the 9-digit university id 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.helper 
 * @author $Author: jal55 $
 * @version $Rev: 5888 $
 * @lastrevision $Date: 2012-12-11 21:14:04 -0500 (Tue, 11 Dec 2012) $
 */
public final class RegexHelper 
{
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private RegexHelper()  { }
	
	/**
	 * Return a university ID with the appropriate delimiter between groups of numbers
	 * @param universityId - the 9-digit university id
	 * @param patterns     - an array of two patterns</br>
	 *                       1) a pattern splitting up the individual groups</br>
	 *                       2) a formatting pattern which may include spaces or dashes
	 * @return
	 */
	public static String formatUniversityId(Map<String, Object> applicationMap, String universityId)
	{
		String formatted = universityId;    
		
		// separate the groups
		String newPattern = (String) applicationMap.get("edit.pattern.uid.groups");         

		// Delimit  the groups
		String formatting = (String) applicationMap.get("edit.pattern.uid.group.display");  
		
		Pattern p = Pattern.compile(newPattern);
		Matcher m = p.matcher(universityId);
		String fmtId = m.replaceAll(formatting);
		formatted = fmtId;
		
		return formatted;
	}

}
