/** SVN FILE: $Id: Utility.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;

/**
 * Generic utility class.
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
public final class Utility {

	/** 
	 * Constructor
	 */
	private Utility() {
		
	}
	
	/**
	 * This routine is used to convert a java.util.Date to a String formatted based on the value of a property.
	 * @param d contains the date to be converted.
	 * @return will return a string representation of the date.
	 */
	public static String convertDateToString(Date d) {
		if (d == null) {
			return null;
		}
		else {
			final SimpleDateFormat dateFormat = new SimpleDateFormat(CprProperties.INSTANCE.getProperties().getProperty(
																	CprPropertyName.CPR_FORMAT_DATE.toString()));
			return dateFormat.format(d);
		}
	}
	
	/**
	 * This routine is used to convert a java.util.Date (timestamp) to a String formatted based on the value of a property.
	 * @param d contains the timestamp to be converted.
	 * @return will return a string representation of the timestamp.
	 */
	public static String convertTimestampToString(Date d) {
		if (d == null) {
			return null;
		}
		else {
			final SimpleDateFormat dateFormat = new SimpleDateFormat(CprProperties.INSTANCE.getProperties().getProperty(
																	CprPropertyName.CPR_FORMAT_TIMESTAMP.toString()));
			return dateFormat.format(d);
		}
	}

	/**
	 * This routine is used to convert a string to an enumerated type.
	 * @param enumClass Contains the enumerated type's class.
	 * @param stringValue Contains the string that will be used to convert to an enum.
	 * @return will return the enumerated value if successful.
	 */
	public static <T extends Enum<T>> T getEnumFromString(Class<T> enumClass, String stringValue)
	{
	    if( enumClass != null && stringValue != null ) {
	        try {
	            return Enum.valueOf(enumClass, stringValue.trim().toUpperCase());
	        }
	        catch(IllegalArgumentException ex) {
	        }
	    }
	    return null;
	}
	
	/**
	 * This routine is a helper to convert a long value to a string, it handles the case where a null pointer is passed in.
	 * @param value contains the value to be converted.
	 * @return will return a null value if one was specified, otherwise it will return the string representation of the long value.
	 */
	public static String safeConvertLongToString(Long value) {
		if (value == null) {
			return null;
		}
		else {
			return Long.toString(value);
		}
	}

}
