/* SVN FILE: $Id: StringHelper.java 5888 2012-12-12 02:14:04Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.helper;

/**
 * StringHelper is a class with static methods to manipulate String objects 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5888 $
 * @lastrevision $Date: 2012-12-11 21:14:04 -0500 (Tue, 11 Dec 2012) $
 */
import java.util.Map;

public final class StringHelper 
{

	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private StringHelper()  { }
	
	/**
	 * Replace tokens with their map correspondents.  The token names in the text 
	 * are surrounded, on both the front and back with '%'.
	 * 
	 * For example:  Dear %lna.firstName%, S
	 * 
	 * @param dataMap - A map with keys that match the token names, such as "lna.firstName"
	 * @param message - The message which requires data substitution
	 * @return        - A String after parameter substitution has taken place.
	 *                  However, if any/some substitutions did not took place, then the tokens will be returned 
	 *                  with pound signs around them instead of '%'
	 */
	public static String replaceTokensFromMap(Map<String, Object> dataMap, String message)
	{
		String result = message;
		
		while(result.contains("%"))
		{
			int beginDelim = result.indexOf('%');
			int endDelim  = result.indexOf('%', beginDelim+1);
			String varName = result.substring(beginDelim +1, endDelim);
			
			if(dataMap.containsKey(varName))
			{
				result = result.replaceAll(result.substring(beginDelim, endDelim+1), (String) dataMap.get(varName));
			}
			else
			{
				result = result.replaceAll(result.substring(beginDelim, endDelim+1), "#" +varName +"#");
			}
		}
		
		return result;
	}
	
	/**
	 * Replace global tokens with their map correspondents. 
	 * 
	 * @param dataMap - A map with keys that match the token names, such as "lna.firstName"
	 * @param message - The message which requires data substitution
	 * @return        - A String after parameter substitution has taken place.
	 *                  However, if any/some substitutions did not took place, then the tokens will be returned 
	 *                  with pound signs around them instead of '%'
	 */
	public static String replaceGlobalTokensFromMap(Map<String, Object> dataMap, String message)
	{
		String result = message;
		int beginDelim = 0;
		while((beginDelim = result.indexOf("%gbl.")) >= 0)
		{
			int endDelim  = result.indexOf("%", beginDelim+1);
			String varName = result.substring(beginDelim +1, endDelim);
			
			if(dataMap.containsKey(varName))
			{
				result = result.replaceAll(result.substring(beginDelim, endDelim+1), (String) dataMap.get(varName));
			}
			else
			{
				result = result.replaceAll(result.substring(beginDelim, endDelim+1), "#" +varName +"#");
			}
			
		}

		return result.toString();
	}
}
