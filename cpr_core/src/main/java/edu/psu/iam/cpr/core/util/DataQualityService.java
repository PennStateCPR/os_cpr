/* SVN FILE: $Id: DataQualityService.java 7850 2013-08-15 20:10:13Z emh1 $ */

package edu.psu.iam.cpr.core.util;

import java.util.List;
import java.util.Properties;

import edu.psu.iam.cpr.core.api.returns.MatchCodeServiceReturn;
import edu.psu.iam.cpr.core.api.returns.TransformServiceReturn;

/**
 * 
 * A class to perform function calls to the IdS data quality server.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 
 * United States License. To view a copy of this license, visit 
 * http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: emh1 $
 * @version $Rev: 7850 $
 * @lastrevision $Date: 2013-08-15 16:10:13 -0400 (Thu, 15 Aug 2013) $
 */

public class DataQualityService {
	
	/** Specifies how similar input data strings must be to generate the same match
	code. Expressed as a percentage with 85 as the vendor recommended default. Changing
	this value requires all stored match codes to be regenerated */
	@SuppressWarnings("unused")
	private static final int SENSITIVITY = 85;
	
	/** property key that maps to the data quality server IP address */
	@SuppressWarnings("unused")
	private static final String SERVER_IP = "server";
	
	/** property key that maps to the transport mode for communications to the data quality server */	
	@SuppressWarnings("unused")
	private static final String TRANSPORT_TYPE = "transport";
	
	/** property key that maps to the location of the data quality log file */	
	@SuppressWarnings("unused")
	private static final String LOG_FILE = "log_file";
	
	/** determines whether results of address validation will be in mixed case or
	upper case. Default value is false i.e. upper case. */
	private boolean properCaseFlag = false;
	
	/** holds a list of three character ISO country codes of countries for which address
	validation can be done  */
	private List<String> verifyCountries = null;
	
	/**						      
	 * Constructor
	 */	
	public DataQualityService() {
		startSession();
	}
	
	/**
	 * Establish a session with the DataFlux server based on parameters from
	 * the data quality section of the CPR properties file
	 */
	private void startSession() {
    }
	
	/**
	 * Format the input data and generate a match code for a street address
	 * @param streetLine1 the first line from a street address
	 * @param streetLine2 the second line from a street address
	 * @param streetLine3 the third line from a street address
	 * @return MatchCodeServiceReturn
	 */
	public MatchCodeServiceReturn getAddressMatchCode (final String streetLine1, final String streetLine2, final String streetLine3) {
		return null;
	}

	/**
	 * Format the input data and generate a match code for a person's name
	 * @param firstName a person's first name
	 * @param middleName a person's middle name(s)
	 * @param lastName a person's last name
	 * @return MatchCodeServiceReturn
	 */
	public MatchCodeServiceReturn getNameMatchCode (final String firstName, final String middleName, final String lastName) {
		return null;
	}

	/**
	 * Format the input data and generate a match code for a city name
	 * @param cityName the name of a city
	 * @return MatchCodeServiceReturn
	 */
	public MatchCodeServiceReturn getCityMatchCode (final String cityName) {
		return null;
	}

	/**
	 * Generate a match code for a matching valid data type and a data value
	 * @param dataType contains a valid matching data type
	 * @param dataValue contains a string of data consistent with the matching data type
	 * @return MatchCodeServiceReturn in the event of success or failure
	 */
	public MatchCodeServiceReturn getMatchCode(final String dataType, final String dataValue) {
		return null;
	}
	
	/**
	 * Transform an address into a standardized format conforming to postal service rules.
	 * Currently only valid for for addresses from the United States (USA) and Canada (CAN).
	 * @param address1 contains the first line of the street address.
	 * @param address2 contains the second line of the street address.
	 * @param address3 contains the third line of the street address.
	 * @param city contains the name of a city.
	 * @param stateOrProvince contains the state or province.
	 * @param postalCode contains the postal code.
	 * @param countryCode contains a three character country code.
	 * @return returns a TransformServiceReturn object in the event of success or failure
	 */
	public TransformServiceReturn transformAddress(final String address1, final String address2, final String address3,
			final String city, final String stateOrProvince, final String postalCode, final String countryCode) {
		return null;
	}
	
	
	/**
	 * Concatenate two lines of a street address
	 * @param line1 contains first address line
	 * @param line2 contains second address line
	 * @return combinedLine contains concatenated address lines
	 */	
	@SuppressWarnings("unused")
	private String combineAddressLines (final String line1, final String line2) {
    	String combinedLine = null;
    	if (line2 == null || line2.length() == 0){
    		combinedLine = line1;
    	}
    	else {
    		if (line1 == null || line1.length() == 0) {
    			combinedLine = line2;
    		}
    		else {
    			combinedLine = line1 + " " + line2;
    		}
    	}
    	return combinedLine;
	}
	
	/**
	 * Convert two character country code to three character code
	 * @param twoCharCode contains a two character country code.
	 * @return threeCharCode contains a three character country code
	 */	
	@SuppressWarnings("unused")
	private String convertTwoCharCountryCode(final String twoCharCode) {
		String threeCharCode = null;
       	if ("US".equals(twoCharCode)) {
       		threeCharCode = "USA";
    	}
    	else {
    		if ("CA".equals(twoCharCode)) {
    			threeCharCode = "CAN";
    		}
    	}
       	return threeCharCode;
	}
	
	/**
	 * Convert three character country code to two character code
	 * @param threeCharCode contains a three character country code.
	 * @return twoCharCode contains a two character country code
	 */	
	@SuppressWarnings("unused")
	private String convertThreeCharCountryCode(final String threeCharCode) {
		String twoCharCode = null;
    	if ("USA".equals(threeCharCode)) {
    		twoCharCode = "US";
    	}
    	else {
    		if ("CAN".equals(threeCharCode)) {
    			twoCharCode = "CAN";
    		}
    	}
    	return twoCharCode; 
	}
	
	/**
	 * determine if there is a session
	 * @return boolean - true if there is a base session
	 */
	public boolean hasSession() {
		return false;
	}
	
	/**
	 * Close the current DataFlux session
	 * @throws SessionError when error during session creation
	 */
	public void closeSession() {
	}
	
	/**
	 * Set a flag so that the results of address transformation will be in upper case
	 */
	public void setReturnCaseUpper() {
		properCaseFlag = false;
	}

	/**
	 * Set a flag so that the results of address transformation will be in mixed case
	 */
	public void setReturnCaseMixed() {
		properCaseFlag = true;
	}

	/**
	 * Return a string describing the current case setting for address transformation returns
	 * @return string value that holds the current case setting
	 */
	public String getReturnCaseSetting() {
		if (properCaseFlag) {
			return "MIXED";
		}
		else {
			return "UPPER";
		}
	}

	/**
	 * Create a List of 3 character ISO country codes of countries eligible for address validation
	 */
	@SuppressWarnings("unused")
	private void buildVerifyCountryList (Properties props) {
	}
	
	/**
	 * Return a string describing the current case setting for address transformation returns
	 * @return a List of Strings where each occurrence is a three character country code
	 */
	public List<String> getVerifyCountries() {
		return verifyCountries;
	}
	
	/**
	 * Return whether or not address validation can take place
	 * @return true if there are elibible countries for address validation
	 */
	public boolean hasVerifyCountries() {
		return false;
	}
	
	
	/**
	 * Return a string that textually describes this object
	 * @return string containing a meaningful description of this object
	 */
	public String toString() {
		return null;
	}
}
