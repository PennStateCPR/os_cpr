/* SVN FILE: $Id: PasswordValidator.java 5980 2013-01-07 14:34:28Z jal55 $ */

package edu.psu.iam.cpr.ip.ui.validation; 

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
/**
 * PasswordValidator validates passwords using pattern matching.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.validation 
 * @author $Author: jal55 $
 * @version $Rev: 5980 $
 * @lastrevision $Date: 2013-01-07 09:34:28 -0500 (Mon, 07 Jan 2013) $
 */
public class PasswordValidator 
{
	/** Instance of logger */                                                     
	protected static final Logger LOG = Logger.getLogger(PasswordValidator.class);
	
    private Pattern pattern;

	/*
     * (?=.*\d)		    #   must contains one digit from 0-9 
     * (?=.*[a-z])		#   must contains one lowercase characters 
     * (?=.*[A-Z])		#   must contains one uppercase characters 
     * (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
     * .		        #   match anything with previous condition checking
     * {8,8}	        #  length at least 8 characters and maximum of 8
     * 
     * ((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,8})
     */
	 private String passwordPattern =  
	 		 "((?=.*\\d)(?=.*[a-z]).{8,20})";
	 
	 private String regex;
	 
	// Error Messages
	 private List<String> listMessages;                              
	 
	 public PasswordValidator()
	 {
	     pattern = Pattern.compile(passwordPattern);
	 }
	 
	 /* Constructor allowing the Regular Expression(s) to be passed */
	 public PasswordValidator(String regex, List<String> listMessages2)
	 {
		 this.regex        = regex;
		 this.listMessages = listMessages2;
		 passwordPattern = regex;                      
		 
		 // Compile the pattern passed	 
		 pattern = Pattern.compile(stripEscapes(passwordPattern));
	 }
	 
	  /**
	   * Compile the pattern, but remove unnecessary/redundant escape characters
	  * @param newPattern
	  */
	  public PasswordValidator(String newPattern)
	  {
		  pattern = Pattern.compile(stripEscapes(newPattern));
	  }

	  /**
	   * Compile the pattern, but remove unnecessary/redundant escape characters
	   * @param objPattern
	   */
	  public PasswordValidator(Object objPattern)
	  {
		  pattern = Pattern.compile(stripEscapes(objPattern.toString()));
	  }
	 
	 
	  /**
	   * Strip unnecessary escape sequences from the regex patterns
	   * @param pattern
	   * @return
	   */
	  protected String stripEscapes(String pattern)
	  {
		  String result = pattern;

		  while(result.contains("\\\\"))
		  {
			  result = result.replace("\\\\", "\\");
		  }
		  
		  return result;
	  }
 
	 /**
	  * Validate password with regular expression
	  * @param password password for validation
	  * @return true valid password, false invalid password
	  */	
	  public boolean validate(final String password)
	  {
		  Matcher matcher = pattern.matcher(password);
		  return matcher.matches();
	  }

	  
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  String password;
		  PasswordValidator pv = new PasswordValidator();
	  
		  // fail -> too few characters
		  password = "abc";
		  LOG.info(String.format("test results 01: %s, for password(%s) ", pv.validate(password), password));
		  
		  // fail -> should have contained at least 1 number
		  password = "abcAbbc#";
		  LOG.info(String.format("test results 02: %s, for password(%s) ", pv.validate(password), password));
		  
		  // true -> everything is there
		  password = "abcA1#12";
		  LOG.info(String.format("test results 02: %s, for password(%s) \n", pv.validate(password), password));
		  
		  // fail -> too many characters
		  password = "abcA1#1233";
		  LOG.info(String.format("test results 02: %s, for password(%s) \n", pv.validate(password), password));		

	}
}
