/* SVN FILE: $Id: PasswordResponseObject.java 5826 2012-12-10 10:17:35Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.valueobject;

import java.util.List;

/**
 * PasswordResponseObject is a value object used to pass data from password validation 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.valueobject 
 * @author $Author: jal55 $
 * @version $Rev: 5826 $
 * @lastrevision $Date: 2012-12-10 05:17:35 -0500 (Mon, 10 Dec 2012) $
 */
public class PasswordResponseObject
{
	private String        password;
	private String        passwordConfirmed;
	private List<String>  listOfFailures;
	
	private boolean checkStatus;
	public String message;
	
	public PasswordResponseObject(String password, String confirmedPassword){
		this.password          = password;
		this.passwordConfirmed = confirmedPassword;
	}

	/**
	 * @return boolean false if there are no erors, or boolean true if errors exist
	 */
	public boolean containsErrorMessage()
	{
		return (message == null || message.trim().length() == 0) ? false : true;
	}
	
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
	 * @return the listOfFailures
	 */
	public List<String> getListOfFailures() {
		return listOfFailures;
	}

	/**
	 * @param listOfFailures the listOfFailures to set
	 */
	public void setListOfFailures(List<String> listOfFailures) {
		this.listOfFailures = listOfFailures;
	}

	/**
	 * @return the checkstatus
	 */
	public boolean isCheckstatus() {
		return checkStatus;
	}

	/**
	 * @param checkstatus the checkstatus to set
	 */
	public void setCheckstatus(boolean checkStatus) {
		this.checkStatus = checkStatus;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
