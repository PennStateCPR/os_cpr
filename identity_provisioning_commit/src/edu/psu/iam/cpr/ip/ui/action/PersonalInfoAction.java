/* SVN FILE: $Id: PersonalInfoAction.java 5953 2012-12-20 22:21:58Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import edu.psu.iam.cpr.ip.ui.dao.IdentityProvisioningDAO;
import edu.psu.iam.cpr.ip.ui.proxy.ProxyDb;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * PersonalInfoAction validates and processes personal information  
 * entered by users/clients.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5953 $
 * @lastrevision $Date: 2012-12-20 17:21:58 -0500 (Thu, 20 Dec 2012) $
 */
public class PersonalInfoAction extends BaseAction 
{

	private String birthMonth;
	private String birthDay;    
	private String birthYear;       
	private String gender;
	private String citizenship;
	private String country;
	
	private Map<String, String>  genderMap;
	private Map<String, String> countryMap;
	private Map<String, String> citizenList;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
        @Action(value="personal_info",results={ @Result(name=SUCCESS,location="/verify_info",type=REDIRECT),
                                                @Result(name="IdentityInfo"  ,location="/identity_info"   ,type=REDIRECT),  // @add this
                                                @Result(name="VerifyInfo"   ,location="/verify_info"   ,type=REDIRECT),     // @add this
                                             @Result(name="stay on page",location="/jsp/personalInformation.jsp"),
                                                     @Result(name="verify",location="/verify_info",type=REDIRECT),
                                             @Result(name="failure",location="/jsp/endPage.jsp")
                                             })
	public String execute() 
	{
		if(!setup("per"))
		{
			return FAILURE;
		}
		
		log.debug(String.format("%s ", getUniqueId()))  ;
	
		String returnLocation = "success";
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		// Get the List of countries and codes - ISO 3166
		if(countryMap == null)
		{
			countryMap  = ProxyDb.loadCountryListFromDb(getApplicationMap());  
			genderMap   = ProxyDb.loadGenderListFromDb(getApplicationMap());
			citizenList = IdentityProvisioningDAO.loadCitizenshipt();
		}
		
		if(country == null)
		{
			country = "USA";
		}
		

		if( fieldsPresent().equals("error"))
		{
			returnLocation = STAY_ON_PAGE;
		}
		
		saveFieldsToSession(getPrefix());
			
		// There is a dependent field in IdentityInfo, which may not be reset
		// when verify returns to this screen, but bypasses Identity; so, check and set it here also
		if(getSessionString("vfy.return.to.verify") != null)
		{
			String usCitizen = "N";
			if(FieldUtility.fieldIsPresent(citizenship) && getSessionString("per.citizenship").equalsIgnoreCase("US"))
			{
				usCitizen = "Y";
			}

			getSessionMap().put("idi.usCitizen", usCitizen);
		}
		
		return filterSuccess(returnLocation);
	}
	

	/**
	 * Check birth month, and birthday to see if they're present -- they're required, but year is not
	 * @return
	 */
    private String fieldsPresent()
    {
    	String validateStatus = "success";
    	
		if((FieldUtility.fieldIsNotPresent(birthMonth, birthDay)))
		{
			validateStatus = STAY_ON_PAGE;
		}
		
		return filterSuccess(validateStatus);
    }
	
	/**
	 * @return the birthMonth
	 */
	public String getBirthMonth() {
		return birthMonth;
	}

	/**
	 * @param birthMonth the birthMonth to set
	 */
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	/**
	 * @return the birthDay
	 */
	public String getBirthDay() {
		return birthDay;
	}

	/**
	 * @param birthDay the birthDay to set
	 */
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	/**
	 * @return the birthYear
	 */
	public String getBirthYear() {
		return birthYear;
	}

	/**
	 * @param birthYear the birthYear to set
	 */
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the citizenship
	 */
	public String getCitizenship() {
		return citizenship;
	}

	/**
	 * @param citizenship the citizenship to set
	 */
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}


	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}


	/**
	 * @return the control
	 */


	/**
	 * @param control the control to set
	 */


	/**
	 * @return the genderList
	 */
	public Map<String, String> getGenderMap() {
		return genderMap;
	}

	/**
	 * @param genderList the genderList to set
	 */
	public void setGenderMap(Map<String, String> genderMap) {
		this.genderMap = genderMap;
	}


	/**
	 * @return the countryMap
	 */
	public Map<String, String> getCountryMap() {
		return countryMap;
	}


	/**
	 * @param countryMap the countryMap to set
	 */
	public void setCountryMap(Map<String, String> countryMap) {
		this.countryMap = countryMap;
	}


	/**
	 * @return the citizenList
	 */
	public Map<String, String> getCitizenList() {
		return citizenList;
	}

	/**
	 * @param citizenList the citizenList to set
	 */
	public void setCitizenList(Map<String, String> citizenList) {
		this.citizenList = citizenList;
	}
}
