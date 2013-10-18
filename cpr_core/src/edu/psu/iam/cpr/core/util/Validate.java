/* SVN FILE: $Id: Validate.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
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
package edu.psu.iam.cpr.core.util;

import java.text.SimpleDateFormat;
import java.util.regex.*;

import edu.psu.iam.cpr.core.database.types.CprPropertyName;


/**
 * Validate is a utility class that will validate data inputs 
 * @author slk24
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
 * @version %I%, %G%
 * 
 * @since 1.0
 */
public final class Validate {
	
	private static final SimpleDateFormat FULL_DATE_FORMAT;
	private static final SimpleDateFormat PARTIAL_DATE_FORMAT;
	
	static {
		FULL_DATE_FORMAT = new SimpleDateFormat(CprProperties.INSTANCE.getProperties().getProperty(
													CprPropertyName.CPR_FORMAT_DATE.toString()));
		FULL_DATE_FORMAT.setLenient(false);
		
		PARTIAL_DATE_FORMAT = new SimpleDateFormat(CprProperties.INSTANCE.getProperties().getProperty(
													CprPropertyName.CPR_FORMAT_PARTIAL_DATE.toString()));
		PARTIAL_DATE_FORMAT.setLenient(false);
	}
	
	/**
	 * Constructor
	 */
	private Validate() {
	}
	
    /**
     * The purpose of this routine is valid a field containing a yes/no value.  
     * The routine will automatically return an "N" for values passed in that are
     * null.  The routine will strip out whitespace to find a Y/N value.
     * 
     * @param yesNo contains the string to be validated.
     * @return if successful will return a Y/N, otherwise it will return a null object.
     */
    public static String isValidYesNo(String yesNo) {
    	
    	if (yesNo == null || "".equals(yesNo)) {
    		return "N";
    	}

    	final String s = yesNo.toUpperCase().trim();

    	if (s.equals("Y") || s.equals("YES") ||
    			s.equals("N") || s.equals("NO")) {
    		return s.substring(0,1);
    	}

    	return null;
    }

	/**
	 * This routine will use a regular expression to check the input string for a valid 
	 * PSU Id format
	 * 
	 * @param psuId - contains the PSU Id to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 */
    public static boolean isValidPsuId(String psuId) {
    	if (psuId == null || psuId.length() == 0) {
    		return false;
    	}
	    if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_PSU_ID_NUMBER.toString()), psuId)) {
	    	return true;
	    }
	    return false;
	}
    /**
     * This routine is used to validate a date.  It is passed in a date in the format of MM/DD/YYYY.  If the 
     * date is valid it will return true, otherwise it will return false.
     * @param dateString contains the date to be validated.
     * @return true if a valid date, otherwise it will return false.
     */
    public static boolean isValidDate(String dateString) {
 
    	try {
    		FULL_DATE_FORMAT.parse(dateString);
    		return true;
    	}
    	catch (Exception e) {
    		return false;
    	}
    }
    
    /**
     * This routine is used to validate a partial date of the format MM/DD.
     * @param dateString contains the partial date to be validated.
     * @return will return true if the partial date is valid, otherwise it will return false.
     */
    public static boolean isValidPartialDate(String dateString) {
    	
    	try {
    		PARTIAL_DATE_FORMAT.parse(dateString);
    		return true;
    	} 
    	catch (Exception e) {
    		return false;
    	}
    }
}
