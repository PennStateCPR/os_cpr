/* SVN FILE: $Id: ContactInfoAction.java 5997 2013-01-10 05:58:37Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * ContactInfoAction blah-blah. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @author $Author: jal55 $
 * @version $Rev: 5997 $
 * @lastrevision $Date: 2013-01-10 00:58:37 -0500 (Thu, 10 Jan 2013) $
 */
public class ContactInfoAction extends BaseAction 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String phoneNumber;
	private String extension;
	private String internationalNumber;
	private String phoneType;
	private String emailType;
	private String dob;
	private String city;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="contact_info",results={ 
			@Result(name=SUCCESS, location="/verify_info", type=REDIRECT),
            @Result(name="VerifyInfo", location="/verify_info", type=REDIRECT),
			@Result(name="stay on page", location="/jsp/contactInformation.jsp"),
		    @Result(name="verify", location="/verify_info", type=REDIRECT),
            @Result(name="failure", location="/jsp/endPage.jsp")
    })
	
	public String execute() 
	{
		if(!setup("con"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		phoneType = "PERMANENT_PHONE";
		emailType = "OTHER_EMAIL";
		
		String returnLocation = "success";

		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		// Reformat DOB to the correct format.
		if (getDob() != null) {
			String[] parts = getDob().split("/");
			final int month = Integer.parseInt(parts[0]);
			final int day = Integer.parseInt(parts[1]);
			final int year = Integer.parseInt(parts[2]);
			setDob(String.format("%02d/%02d/%04d", month, day, year));
			
			Years age = Years.yearsBetween(new LocalDate(year, month, day), new LocalDate());
			
			if (age.getYears() < 13) {
				returnLocation = STAY_ON_PAGE;
				addActionMessage("You must be 13 years old or older to create a IAM Testbed Username.");
			}
		}
		
		// Save form data to session
		saveFieldsToSession(getPrefix());
		
		return filterSuccess(returnLocation);
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}


	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	/**
	 * @return the extension
	 */
	public String getExtension() {
		return extension;
	}


	/**
	 * @param extension the extension to set
	 */
	public void setExtension(String extension) {
		this.extension = extension;
	}


	/**
	 * @return the internationalNumber
	 */
	public String getInternationalNumber() {
		return internationalNumber;
	}


	/**
	 * @param internationalNumber the internationalNumber to set
	 */
	public void setInternationalNumber(String internationalNumber) {
		this.internationalNumber = internationalNumber;
	}


	/**
	 * @return the phoneType
	 */
	public String getPhoneType() {
		return phoneType;
	}


	/**
	 * @param phoneType the phoneType to set
	 */
	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}


	/**
	 * @return the emailType
	 */
	public String getEmailType() {
		return emailType;
	}


	/**
	 * @param emailType the emailType to set
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}


	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}


	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}


	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}


	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}


}
