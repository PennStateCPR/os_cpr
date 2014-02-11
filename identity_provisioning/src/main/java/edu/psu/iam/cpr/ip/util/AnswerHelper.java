/* SVN FILE: $Id: AnswerHelper.java 5888 2012-12-12 02:14:04Z jal55 $ */
package edu.psu.iam.cpr.ip.util;

/**
 * AnswerHelper is a collection of static methods to assist in interpreting <br/>
 * and matching user answers with their previously answered security questions. 
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
public final class AnswerHelper 
{
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private AnswerHelper()  { }
	
	/**
	 * Remove all trailing, leading, and embedded spaces from <br/>
	 * security question answers, and also make it lowercase
	 * @param securityQuestionAnswer - The users security question answer
	 * @return a String composed any remaining characters
	 */
	public static String compress(String securityQuestionAnswer)
	{
		/* Remove leading and trailing spaces */
        return securityQuestionAnswer.toLowerCase().replaceAll(" ", "");
	}
	
	/**
	 * Compare two Strings after removing any spaces, and converting <br/>
	 * all characters to lower case.
	 * @param aString - String object to be compared
	 * @param bString - String object to be compared
	 * @return true if the Strings contain same characters, else false
	 */
	public static boolean equals(String aString, String bString)
	{
		boolean result = false;
		if(compress(aString).equals(compress(bString)))
		{
		   result = true;
		}
		
		return result;

	}
}
