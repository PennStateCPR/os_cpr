/* SVN FILE: $Id: ValidateSSN.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.CprRunningMode;

/**
 * Validate a Social Security Number (SSN).
 * 
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public final class ValidateSSN {

	/**
	 * Constructor
	 */
	private ValidateSSN() {
		
	}
	
	/**
	 * Validate than an SSN is well-formed.
	 *
	 * @param ssn - contains the ssn to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 */
	public static boolean validateSSN(String ssn) {
		
		String localSSN = ssn;
		
		if (localSSN == null) {
			return false;
		}
		
		localSSN = localSSN.trim();
		
		if (localSSN.length() == 0) {
			return false;
		}
		
    	// match with or without separators (hyphens or spaces)
	    if (!Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_SSN.toString()), localSSN)) {
	    	return false;
	    }
		
	    localSSN = localSSN.replace("-", "").replace(" ", "");
	    
	    String areaNumber = localSSN.substring(0, 3);
	    String groupNumber = localSSN.substring(3, 5);
	    String serialNumber = localSSN.substring(5, 9);
	    
	    // validation rules from http://ssa-custhelp.ssa.gov/app/answers/detail/a_id/425
	    
	    if (areaNumber.equals("000") || groupNumber.equals("00") || serialNumber.equals("0000")) {
	    	return false;
	    }

	    // SSNs in the range 987-65-4320 to 987-65-4329 are reserved for advertising
	    if (areaNumber.equals("987") && groupNumber.equals("65")) {
	    	int areaNum = Integer.parseInt(serialNumber);
	    	if (areaNum >= 4320 && areaNum <= 4329) {
	    		return false;
	    	}
	    }
	    
	    // area numbers 666 and 900 - 999 are invalid - for production only.
	    // see http://www.socialsecurity.gov/employer/randomization.html
	    int areaNum = Integer.parseInt(areaNumber);
	    CprRunningMode mode = CprProperties.INSTANCE.getCprMode();
	    if (mode == CprRunningMode.PRODUCTION) {
	    	if ((areaNum != 666) && (areaNum <= 899)) {
	    		return true;
	    	}
	    }
	    
	    // If we are in test or acceptance mode, numbers in the 900-999 are valid.
	    else if (mode == CprRunningMode.TEST || mode == CprRunningMode.ACCEPTANCE) {
	    	if (areaNum != 666) {
	    		return true;
	    	}
	    }
	    
		return false;
	}

	/**
	 * Extract the numeric part of an SSN.
	 * 
	 * Note: This method does not validate that the SSN is well-formed.
	 * 
	 * @param ssn The input SSN.
	 * 
	 * @return the numeric components, or an empty string on error.
	 */
	public static String extractNumericSSN(String ssn) {
	
		Pattern pattern = Pattern.compile("^([0-9]{3})[ -]?([0-9]{2})[ -]?([0-9]{4})$");
		Matcher matcher = pattern.matcher(ssn);
		
		if (!matcher.matches() || matcher.groupCount() != 3) {
			return "";
		}
		
		return matcher.group(1) + matcher.group(2) + matcher.group(3);
	}
}
