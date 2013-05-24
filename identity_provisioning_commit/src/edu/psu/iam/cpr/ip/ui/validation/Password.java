/* SVN FILE: $Id: Password.java 5894 2012-12-12 14:40:51Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.validation;

import java.util.List;

import edu.psu.iam.cpr.ip.ui.valueobject.PasswordRequestObject;
import edu.psu.iam.cpr.ip.ui.valueobject.PasswordResponseObject;

/**
 * Password contains static utility methods for validating passwords 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.validation 
 * @author $Author: jal55 $
 * @version $Rev: 5894 $
 * @lastrevision $Date: 2012-12-12 09:40:51 -0500 (Wed, 12 Dec 2012) $
 */
public final class Password 
{
	static final String PATTERN_MISMATCH = "Password must contain 8 characters, a lowercase character, a number";
	static final String NAME_VIOLATION   = "Password cannot contain your name, or initials";
	
	/**
	 * Prevent or make it difficult for someone to instantiate this class
	 */
	private Password()  { }
	
	public static PasswordResponseObject checkPassword(PasswordRequestObject request)
	{
		PasswordResponseObject pro = checkPassword(request.getPassword()   , request.getPasswordConfirmed(),
		                                           request.getListOfNames(), request.getRegex(),
		                                           request.getListMessages());
		return pro;
	}
	
	public static PasswordResponseObject checkPassword(String password         , String passwordConfirmed, 
			                                           List<String> listOfNames, String regex,
			                                           List<String> listMessages)
	{
		PasswordResponseObject pro = new PasswordResponseObject(password, passwordConfirmed);
		PasswordValidator pv;
		pv = (FieldUtility.fieldIsNotPresent(regex))? new PasswordValidator() : new PasswordValidator(regex, listMessages);

		pro.setCheckstatus(false);
		
		// Are the required fields both present, and matching?
		if((FieldUtility.fieldIsNotPresent(password, passwordConfirmed) ) ||
		   (!password.equals(passwordConfirmed)))	
		{
			// do not check any further if passwords do not match
			pro.message = "Passwords do not match";       
			return pro;
		}
		
		else
		{
		   // Use Pattern Matching to check rules for setting passwords
		   if(!pv.validate(password))
		   {
			  pro.message = PATTERN_MISMATCH;
		   }
		}
		
		
		/* If Password looks good so far, Check to ensure names are not part of password 
		 * Exclude names which are only 1-character in length
		 */
		if(isPasswordAcceptable(pro))           
		{
			for(String name: listOfNames)
			{
				if(name.trim().length() > 1 &&  password.toLowerCase().indexOf(name.toLowerCase()) > -1)
				{
					pro.message = NAME_VIOLATION;
					break;
				}
			}
		}
		
		return pro;
	}
	
	
	/**
	 * Determine is the password being reset is viable
	 * @param pro - The PasswordRequestObject
	 * @return true if password looks good; else, false if password fails <br>
	 *         to meet criteria
	 */
	private static boolean isPasswordAcceptable(PasswordResponseObject pro)
	{
		return (pro.containsErrorMessage() ) ? false: true;
	}
	
	
}

