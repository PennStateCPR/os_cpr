/* SVN FILE: $Id: JSONHelper.java 5888 2012-12-12 02:14:04Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.helper;

/**
 * JSONHelper contains static methods to manipulate/Create JSON objects 
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
public final class JSONHelper 
{
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private JSONHelper()  { }
	
	/**
	 * Format a JSON Message from an infinite number of arguments: key, response 
	 * @param args - JSON Keyword, then keyword answer
	 * @return - A JSON formatted String with Service name first, followed by other keywords/responses
	 */
	public static String createJsonMessage(String... args)
	{
		StringBuffer result = new StringBuffer();
		boolean keyword = true;
		for(String arg: args)
		{
			result.append("\"");
			// Process JSON Keywords
			if(keyword)
			{
				result.append(arg.toUpperCase()).append("\"").append(':');
				keyword = false;
			}
			// Process JSON arguments
			else
			{
				result.append(arg).append("\"").append(',');
				keyword = true;
			}
		}
		
		result.deleteCharAt(result.length()-1);
		result.insert(0, "{");
		result.append("}");
	
		return result.toString();
	}
}
