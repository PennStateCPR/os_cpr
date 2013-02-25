/* SVN FILE: $Id: PasswordRequestObject.java 5824 2012-12-10 10:03:49Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.valueobject;

import java.util.List;

/**
 * PasswordRequestObject is a value object used to pass data into password validation routines 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.valueobject 
 * @author $Author: jal55 $
 * @version $Rev: 5824 $
 * @lastrevision $Date: 2012-12-10 05:03:49 -0500 (Mon, 10 Dec 2012) $
 */

public class PasswordRequestObject 
{
	private String       password;
	private String       passwordConfirmed;
	private List<String> listOfNames;
	private String       regex;
	private List<String> listMessages;
	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the passwordConfirmed
	 */
	public String getPasswordConfirmed() {
		return passwordConfirmed;
	}
	/**
	 * @param passwordConfirmed the passwordConfirmed to set
	 */
	public void setPasswordConfirmed(String passwordConfirmed) {
		this.passwordConfirmed = passwordConfirmed;
	}
	/**
	 * @return the listOfNames
	 */
	public List<String> getListOfNames() {
		return listOfNames;
	}
	/**
	 * @param listOfNames the listOfNames to set
	 */
	public void setListOfNames(List<String> listOfNames) {
		this.listOfNames = listOfNames;
	}
	/**
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}
	/**
	 * @param regex the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}
	/**
	 * @return the listMessages
	 */
	public List<String> getListMessages() {
		return listMessages;
	}
	/**
	 * @param listMessages the listMessages to set
	 */
	public void setListMessages(List<String> listMessages) {
		this.listMessages = listMessages;
	}

}
