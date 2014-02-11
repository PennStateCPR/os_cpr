/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.ip.ui.action;

import java.util.Map;

import edu.psu.iam.cpr.ip.ui.proxy.ProxyDb;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
/**
 * AddressBaseAction is a base class for Current Address, and PreviousAddress Action.  
 * the student.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public abstract class AddressBaseAction extends BaseAction 
{

	private String country;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String city;
	private String state;
	private String postalCode;
	private String province;
	private String addressType;
	
	private Map<String, String> countryMap;
	private Map<String, String> stateMap;
	
	/* (non-Javadoc)
	 * @see edu.psu.iam.cpr.ui.action.BaseAction#execute()
	 */
	@Override
	public String execute() 
	{
		log.debug(String.format("%s ", getUniqueId()))  ;

		if(!setup(getPrefix()))
		{
			return FAILURE;
		}
		
		addressType="PERMANENT_ADDRESS";
		String returnLocation = SUCCESS;
		
		// Get the List of countries and codes - ISO 3166
		if(countryMap == null)
		{
			countryMap = ProxyDb.loadCountryListFromDb(getApplicationMap());
			stateMap   = ProxyDb.loadStateListFromDb(getApplicationMap());
		}
		
		if(FieldUtility.fieldIsNotPresent(getBtnsubmit()))
		{
			returnLocation =  STAY_ON_PAGE;
		}
		
		// Check for required fields having data
		String tempReturnLocation = dependencyCheck();
		if(tempReturnLocation != null)
		{
			returnLocation = tempReturnLocation;
		}
		
		// Set Country to Null if It's the only field initialized, in some instances; such as, Previous Address
		countryCheck(returnLocation);
		
		// Save form data to session
		saveFieldsToSession(getPrefix());
		
		if(country == null)
		{
			country = "USA";
		}
		
		return filterSuccess(returnLocation);
	}

	/**
	 * Perform any field dependency [do required fields contain data] checks
	 * @return STAY_ON_PAGE if dependent fields are missing, else return null
	 */
	abstract public String dependencyCheck();
	
	/**
	 * Some screens, such as 'PreviousAddressAction' may need to blank-out the country
	 * if all of the other fields are blank.  
	 * 
	 * This keeps us from saving information for a non-required screen, when none of the data fields have been entered
	 * 
	 * This is not the case with CurrentAddress, which is required and does have required fields
	 * 
	 */
	abstract public void countryCheck(String returnLocation);
	

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
	 * @return the addressLine1
	 */
	public String getAddressLine1() {
		return addressLine1;
	}

	/**
	 * @param addressLine1 the addressLine1 to set
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * @return the addressLine2
	 */
	public String getAddressLine2() {
		return addressLine2;
	}

	/**
	 * @param addressLine2 the addressLine2 to set
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * @return the addressLine3
	 */
	public String getAddressLine3() {
		return addressLine3;
	}

	/**
	 * @param addressLine3 the addressLine03 to set
	 */
	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
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

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}



	/**
	 * @return the addressType
	 */
	public String getAddressType() {
		return addressType;
	}



	/**
	 * @param addressType the addressType to set
	 */
	public void setAddressType(String addressType) {
		this.addressType = addressType;
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
	 * @return the stateMap
	 */
	public Map<String, String> getStateMap() {
		return stateMap;
	}



	/**
	 * @param stateMap the stateMap to set
	 */
	public void setStateMap(Map<String, String> stateMap) {
		this.stateMap = stateMap;
	}
}
