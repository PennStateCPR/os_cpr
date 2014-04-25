/* SVN FILE: $Id: IdentityInfoAction.java 7362 2013-05-23 12:32:05Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * IdentifyInfoAction Business logic for collecting identity information 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 7362 $
 * @lastrevision $Date: 2013-05-23 08:32:05 -0400 (Thu, 23 May 2013) $
 */
public class IdentityInfoAction extends BaseAction 
{

	private static final String PER_CITIZENSHIP = "per.citizenship";
	
	private String socialSecurityNumber;
	private String pennStateId;
	private String userId;
	
	// This is an output field only.  
	// The front-end needs a simple way to determine US citizen
	// It is used to control the display of the SSN label and textbox
	private String usCitizen;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	@Action(value="/identity_info",results={ @Result(name=SUCCESS,location="/verify_info",type=REDIRECT),
            @Result(name="Welcome"       ,location="/welcome"         ,type=REDIRECT),
            @Result(name="DataAccuracy"  ,location="/data_accuracy"   ,type=REDIRECT),
            @Result(name="LegalName"     ,location="/legal_name"      ,type=REDIRECT),
            @Result(name="CurrentAddress",location="/current_address" ,type=REDIRECT),
            @Result(name="ContactInfo"   ,location="/contact_info"    ,type=REDIRECT),
            @Result(name="PersonalInfo"  ,location="/personal_info"   ,type=REDIRECT),
            @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),
            @Result(name="VerifyInfo"    ,location="/verify_info"     ,type=REDIRECT),
			                                 @Result(name="stay on page",location="/jsp/identityInformation.jsp"),
				                             @Result(name="verify",location="/verify_info",type=REDIRECT),
                                             @Result(name="failure",location="/jsp/endPage.jsp")
                                     })
	public String execute() 
	{
		if(!setup("idi"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
		
		String returnLocation = SUCCESS;
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;

			// // Some RA(s) may allow email address to be userid, so let's pre-populate userid as email in those instances
			String prePopulateUserid = getApplicationString(UIConstants.POPULATE_USERID_WITH_EMAIL);
			if(FieldUtility.fieldIsPresent(prePopulateUserid) &&  FieldUtility.isOptionYes(prePopulateUserid))
			{
				userId = getSessionString(UIConstants.CON_EMAIL); 
			}
		}
		
		/* The front-end, Identity Information needs an easy switch
		 * to determine whether or not to output the SSN field		
		 */
		usCitizen = "N";
		if(FieldUtility.fieldIsPresent(getSessionString(PER_CITIZENSHIP)) && 
		  (getSessionString(PER_CITIZENSHIP).equalsIgnoreCase("US")))
		{
				usCitizen = "Y";
		}
		
		// Validate fields if data exists
		String copyReturnLocation = returnLocation;
		returnLocation = validateScreenFields(copyReturnLocation);

		
		// Save form data to session
		saveFieldsToSession(getPrefix());
		
		return filterSuccess(returnLocation);
	}
	
	/**
	 * Validate fields: SSN, Penn STate Id, User Id, if entered
	 * @param returnLocation The current status (prior to this method) of whether or not to stay on same screen
	 * @return a status indicating whether or not screen should remain until fixed
	 */
	public String validateScreenFields(String returnLocation)
	{
		String copyReturnLocation = returnLocation;
		
		// Validate the SSN if entered -- just check for 9 digits, with or without delimiter of space or dash
		getSessionMap().put(UIConstants.SSN_FLAG, "false");
		if(FieldUtility.fieldIsPresent(socialSecurityNumber))
		{
			String pattern = getApplicationString(UIConstants.IDI_REGEX_KEY_SSN);
			copyReturnLocation = validateByRegex(copyReturnLocation, socialSecurityNumber, 
					                         pattern, UIConstants.IDI_INVALID_SSN);
			getSessionMap().put(UIConstants.SSN_FLAG, (copyReturnLocation.equals(SUCCESS) ? "true" : "false"));
		}
		
		// Validate the entity id (Penn State Id) if entered 
		if(FieldUtility.fieldIsPresent(pennStateId))
		{
			String pattern = getApplicationString(UIConstants.IDI_REGEX_KEY_ENTITY_ID);
			copyReturnLocation = validateByRegex(copyReturnLocation, pennStateId, 
					                         pattern, UIConstants.IDI_INVALID_ENTITY_ID);
		}
		
		// Validate the userId if entered
		if(FieldUtility.fieldIsPresent(userId))
		{
			userId = userId.toLowerCase();
			String pattern = getApplicationString(UIConstants.IDI_REGEX_KEY_USERID);
			copyReturnLocation = validateByRegex(copyReturnLocation, userId, pattern, UIConstants.IDI_INVALID_USERID);
		}
		
		return copyReturnLocation;
	}
	
	/**
	 * Validate the contents of a field based upon a regex pattern
	 * @param returnLocation - String object representing the current returnLocation status: proceed or remain on page
	 * @param regexPattern - String object representing the regex pattern for subject field 
	 * @return Either STAY_ON_PAGE, if there is an error, or SUCCESS if the validation is ok
	 */
	public String validateByRegex(String returnLocation, String field, String regexPattern, String errorMessageKey)
	{
		String copyReturnLocation = returnLocation;
		
		String  fieldPattern = regexPattern;
		Pattern pattern;
		Matcher matcher;
		
		pattern = Pattern.compile(fieldPattern);
		matcher = pattern.matcher(field);
		
		if(!matcher.matches())
		{
			addActionMessage(getApplicationString(errorMessageKey));
	        copyReturnLocation = STAY_ON_PAGE;
		}
		
		return copyReturnLocation;
	}


	/**
	 * @return the socialSecurityNumber
	 */
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}


	/**
	 * @param socialSecurityNumber the socialSecurityNumber to set
	 */
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	 * @return the pennStateId
	 */
	public String getPennStateId() {
		return pennStateId;
	}


	/**
	 * @param pennStateId the pennStateId to set
	 */
	public void setPennStateId(String pennStateId) {
		this.pennStateId = pennStateId;
	}


	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}


	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}


	/**
	 * @return the usCitizen
	 */
	public String getUsCitizen() {
		return usCitizen;
	}


	/**
	 * @param usCitizen the usCitizen to set
	 */
	public void setUsCitizen(String usCitizen) {
		this.usCitizen = usCitizen;
	}
}
