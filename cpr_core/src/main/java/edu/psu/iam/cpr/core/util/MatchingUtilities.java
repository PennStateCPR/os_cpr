/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.util;
/**
 * 
 * This class provides several methods and static variables that are useful for the matching
 * process.
 * 
 * Author: E. Hayes 05/03/2012
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
 * @package edu.psu.iam.cpr.core.util;
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 *
*/


public final class MatchingUtilities {
	
	/** Matching data type for a person's name. */
	public static final String MATCH_DATATYPE_NAME = "NAME";
	
	/** Matching data type for a street address. */
	public static final String MATCH_DATATYPE_ADDRESS = "ADDR";
	
	/** Matching data type for a city name. */
	public static final String MATCH_DATATYPE_CITY = "CITY";
	
	/** Matching data type for last line (city, state, zip code) of an address */
	public static final String MATCH_DATATYPE_LAST_LINE = "LAST_LINE";
	
	/** Matching data type for organization and business names */
	public static final String MATCH_DATATYPE_ORGANIZATION = "ORG";
	
	/** Matching data type for matching of general text information */
	public static final String MATCH_DATATYPE_TEXT = "TEXT";

	private static final int BUFFER_SIZE = 2048;

	/** 
	 * Constructor
	 */
	private MatchingUtilities() {
		
	}
	
	/**
	 * Format the name of a person for input to the match code generator.
	 * 
	 * @param firstName The person's first name.
	 * @param middleName The person's middle name.
	 * @param lastName The person's last name.
	 * 
	 * @return the formatted name, or an empty string if no name components are passed
	 */
	public static String formatNameMatchInput(final String firstName, final String middleName, final String lastName) {
		
		final StringBuilder builder = new StringBuilder(BUFFER_SIZE);
		
		if (firstName != null && !firstName.trim().equals("")) {
			builder.append(firstName.trim());
		}
		
		/**  
		 * for now, match codes for name are only generated using first and last names
		 * In the future may include middle name
		 */
	
		if (lastName != null && !lastName.trim().equals("")) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(lastName.trim());
		}
		
		if (builder.length() == 0) {
			return "";
		}
		
		return builder.toString();
	}

	/**
	 * Format an address string for input to the match code generator.
	 * 
	 * @param address1 The first line of the street address
	 * @param address2 The second line of the street address
	 * @param address3 The third line of the street address
	 * 
	 * @return the formatted address, or an empty string if no address components are passed
	 */
	public static String formatAddressMatchInput(final String address1, final String address2, final String address3) {
		
		final StringBuilder builder = new StringBuilder(BUFFER_SIZE);
		
		if (address1 != null && !address1.trim().equals("")) {
			builder.append(address1.trim());
		}
		
		if (address2 != null && !address2.trim().equals("")) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(address2.trim());
		}
	
		if (address3 != null && !address3.trim().equals("")) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(address3.trim());
		}
		
		if (builder.length() == 0) {
			return "";
		}
		
		return builder.toString();
	}

	/**
	 * Format a city name for input to the match code generator
	 * 
	 * @param city The name of a U. S. city
	 * 
	 * @return the formatted city name, or an empty string if none is passed
	 */
	public static String formatCityMatchInput(final String city) {
		
		if (city == null) {
			return "";
		}
		
		return city.trim();
	}

	/**
	 * Format one or more address elements from the last line of a postal address for input to match code generator.
	 * 
	 * @param city The name of a U. S. city to be included in the last line
	 * @param state The name of a U. S. state to be included in the last line
	 * @param postalCode The postal code to be included in the last line
	 * 
	 * @return the formatted last line of an address
	 */
	public static String formatLastLineMatchInput(final String city, final String state, final String postalCode) {
	
		final StringBuilder builder = new StringBuilder(BUFFER_SIZE);
		
		if (city != null && !city.trim().equals("")) {
			builder.append(city.trim());
		}
		
		if (state != null && !state.trim().equals("")) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(state.trim());
		}
		
		if (postalCode != null && !postalCode.trim().equals("")) {
			if (builder.length() > 0) {
				builder.append(" ");
			}
			builder.append(postalCode.trim());
		}	
		
		if (builder.length() == 0) {
			return "";
		}
		
		return builder.toString();
	}
}



