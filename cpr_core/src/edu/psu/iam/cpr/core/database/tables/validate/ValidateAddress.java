/* SVN FILE: $Id: ValidateAddress.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.database.tables.validate;

import java.util.regex.Pattern;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.CampusCsTable;
import edu.psu.iam.cpr.core.database.tables.CountryTable;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.DocumentType;
import edu.psu.iam.cpr.core.database.types.StateList;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Utility;
/**
This class provides an implementation of functions that perform address information validation. 
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
public final class ValidateAddress {

	private static final String COUNTRY_CODE = "Country code";
	private static final String ADDRESS_DOCUMENT_TYPE = "Address type and/or document type";
	private static final String ADDRESS = "Address";
	private static final String CAMPUS_CODE = "Campus code";
	private static final String GROUP_ID = "Group Id";
	
	private static final String DATABASE_TABLE_NAME = "ADDRESSES";
	private static final String LAST_UPDATE_BY = "LAST_UPDATE_BY";	
	private static final String ADDRESS1 = "ADDRESS1";
	private static final String ADDRESS2 = "ADDRESS2";
	private static final String ADDRESS3 = "ADDRESS3";
	private static final String CITY = "CITY";
	private static final String STATE = "STATE";
	private static final String POSTAL_CODE = "POSTAL_CODE";
	private static final String PROVINCE = "PROVINCE";
	
	
	private static final int BUFFER_SIZE = 200;

	/**
	 * Constructor
	 */
	private ValidateAddress() {
	}

	/**
	 * Check validity of address parameters for US addresses
	 * @param db contains the database connection.
	 * @param localAddress1 contains address line 1 data
	 * @param localAddress2 contains address line 2 data
	 * @param localAddress3 contains address line 3 data
	 * @param localCity contains city data
	 * @param stateName contains state data
	 * @param localPostalCode contains the postal code data
	 * @param usaAddress true if address is us address, false otherwise
	 * @return true if address is valid; false otherwise
	 * @throws CprException 
	 */
	public static boolean isAddressValid(Database db, String localAddress1, String localAddress2,
			String localAddress3, String localCity,  String stateName, String localPostalCode, boolean usaAddress ) throws CprException {
		
		// at least one of the street address fields must be populated
		if (ValidateHelper.isFieldEmpty(localAddress1) &&
				ValidateHelper.isFieldEmpty(localAddress2) &&
				ValidateHelper.isFieldEmpty(localAddress3)) {
			return false;
		}
		
		if (ValidateHelper.isFieldEmpty(localCity)) {
			return false;
		}
		
		if (usaAddress) {
			if (stateName != null && (! isValidState(stateName))) {
				return false;
			}

			// assume USA zip code for now
			if (!isValidZipCode(localPostalCode)) {
				return false;
			}

		}
		return true;
	}

	/**
	 * This routine will check the input string for a valid campus code format
	 * and existence in the database
	 * 
	 * @param db contains reference to open database connection
	 * @param campusCode  contains the campus code to validate
	 * @param campusName - contains a buffer to hold the campus name. Null is allowed if the caller doesn't want the name returned. 
	 * @param updatedBy - contains the userid issuing the service request
	 * 
	 * @return true if campusCode is valid, false otherwise
	 * @throws CprException 
	 */
	public static Long validCampusCode(final Database db, String campusCode,
			StringBuffer campusName, String updatedBy) throws CprException {
		CampusCsTable campusData = new CampusCsTable();
		
		// Null is valid
		if (campusCode == null) {
			return null;
		}
		String localCampusCode = campusCode.toUpperCase().trim();

		// Empty string is valid
		if (localCampusCode.length() == 0) {
			return null;
		}
		
		// Campus code contains 2 upper case alpha
		
		if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_CAMPUS_CODE.toString()), 
				localCampusCode)) {
			
			// Get the campus information by using the code against the database.
			campusData.getCampusInfo(db, localCampusCode, updatedBy);
			
			// Store of the campus code key value and the campus name.
			long code = campusData.getCampusCsBean().getCampusCodeKey();
			if (code != -1L) {
				if (campusName != null) {
					campusName.append(campusData.getCampusCsBean().getCampus());
				}
				return code;
			}
		}
		
		// An error occurred.  
		return -1L;
	}

	/**
	 * This routine will check the input string for a valid country code format
	 * and existence in the database.
	 * 
	 * @param db contains reference to open database connection
	 * @param countryCode   contains the country code to validate
	 * @param countryName contains a buffer to hold the country name. Null is allowed if the caller doesn't want the name returned.
	 * @param retrievedBy  contains the userid issuing the service request
	 * 
	 * @return countryCodeKey if it exists
	 * -1 otherwise or if countryCode null or blank
	 * @throws CprException 
	 */
	public static long validCountryCode(final Database db, String countryCode,
			StringBuffer countryName, String retrievedBy) throws CprException {
		CountryTable countryData = new CountryTable();
		
		
		// Null is valid
		if (countryCode == null) {
			return -1L;
		}

		String localCountryCode = countryCode.toUpperCase().trim();
		
		// Empty string is not valid
		if (localCountryCode.length() == 0) {
			return -1L;
		}
		
		// Country code contains 3 upper case alpha
		
		if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_COUNTRY_CODE.toString()), 
				localCountryCode)) {
			
			// Get the country code information from the database.
			countryData.getCountryInfo(db, localCountryCode, retrievedBy);
			
			long code = countryData.getCountryBean().getCountryKey();
			if (code != -1L) {
				if (countryName != null) {
					countryName.append(countryData.getCountryBean().getCountry());
				}
				return code;
			}
		}
		return -1L;
	}

	

	/**
	 * This routine will check the input string for a valid state
	 * 
	 * @param state
	 *            - contains the state to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 * 
	 */
	public static boolean isValidState(String state)  {

		// Null is valid
		if (state == null) {
			return true;
		}

		String localState = state.toUpperCase().trim();

		// Empty string is valid
		if (localState.length() == 0) {
			return true;
		}

		// state contains 2 alpha
		if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_STATE.toString()), localState)) {
			try {
				StateList.valueOf(localState);
			}
			catch (IllegalArgumentException e) {
				return false;
			}
			return true;
		}

		return false;
	}

	/**
	 * This routine will check the input string for a valid postal code
	 * 
	 * @param zipCode
	 *            - contains the postalCode to validate.
	 * 
	 * @return true if format is valid, false otherwise
	 */
	public static boolean isValidZipCode(String zipCode) {

		// plus4 is not required

		// Null is valid
		if (zipCode == null) {
			return true;
		}

		String localZipCode = zipCode.trim();

		// Empty string is valid
		if (localZipCode.length() == 0) {
			return true;
		}

		// zipCode contains 5 digits + plus4
		if (Pattern.matches(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_REGEX_POSTAL_CODE.toString()), 
				localZipCode)) {
			return true;
		}

		return false;
	}

	/**
	 * Validate the parameters passed to the AddAddress Service
	 * @param db contain the reference to the open database connection
	 * @param personId contains the personid
	 * @param addressTypeString contains the addresstype
	 * @param documentType contains the document type
	 * @param address1 contains address line1
	 * @param address2 contains address line2
	 * @param address3 contains address line3
	 * @param updatedBy contains userid or system id of last updater
	 * @param city contains the city
	 * @param stateOrProvince contains the state or province
	 * @param postalCode contains the postal code and plu4
	 * @param countryCode contains the countrycode to convert to a country code id
	 * @param campusCode contains a 2 digit campus code
	 * @return AddressTable
	 * @throws CprException 
	 */
	public static AddressesTable validateAddAddressParameters(final Database db,
			long personId, String addressTypeString, String documentType,  String updatedBy,
			String address1, String address2, String address3, String city,
			String stateOrProvince, String postalCode,  String countryCode,
			String campusCode) throws 
			CprException {
				
		StringBuffer countryName = new StringBuffer(BUFFER_SIZE);
		StringBuffer campusName = new StringBuffer(BUFFER_SIZE);
		
		Long countryCodeId = -1L;
		Long campusCodeId = -1L;
		String stateName= null;
		String provinceName = null;
		
		db.getAllTableColumns(DATABASE_TABLE_NAME);

		String localAddress1 = ValidateHelper.trimField(address1);
		String localAddress2 = ValidateHelper.trimField(address2);
		String localAddress3 = ValidateHelper.trimField(address3);
		String localCity = ValidateHelper.trimField(city);
		String localPostalCode = ValidateHelper.trimField(postalCode);
		String localStateOrProvince = ValidateHelper.trimField(stateOrProvince);
		String localCampusCode = ValidateHelper.trimField(campusCode);
		String localCountryCode = ValidateHelper.trimField(countryCode);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		String localAddressTypeString = null;
		if (addressTypeString != null) {
			localAddressTypeString = addressTypeString.trim().toUpperCase();
		}
		localAddressTypeString = ValidateHelper.checkField(null, localAddressTypeString, null, "Address type", false);
		
		String localDocumentType = null;
		if (documentType != null) {
			localDocumentType = documentType.trim().toUpperCase();
		}
		
		String dbColumnNames[] = { ADDRESS1, ADDRESS2, ADDRESS3, CITY, STATE, POSTAL_CODE, PROVINCE };
		String inputFields[] = { localAddress1, localAddress2, localAddress3, localCity, stateName, localPostalCode, provinceName };
		String prettyNames[] = {  "Address line 1", "Address line 2 ",
				"Address line 3", "City", "State", "Postal code", "Province" };
		for (int i = 0; i < dbColumnNames.length; ++i) {
			if (inputFields[i] != null && (! ValidateHelper.isFieldLengthValid(db, inputFields[i], dbColumnNames[i]))) {
				throw new CprException(ReturnType.PARAMETER_LENGTH_EXCEPTION, prettyNames[i]);
			}
		}
		
		countryCodeId = validCountryCode(db, localCountryCode, countryName, updatedBy);
		if (countryCodeId == -1L) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, COUNTRY_CODE);
		}
			
		// Validate that the address type and document type combination is valid.
		if (! isValidCombination(localAddressTypeString, localDocumentType)) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, ADDRESS_DOCUMENT_TYPE);
		}

		if (localCountryCode.equalsIgnoreCase("USA")) {
			stateName = localStateOrProvince;
			if (! isAddressValid(db, localAddress1, localAddress2, localAddress3, localCity, stateName, localPostalCode, true)) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, ADDRESS);
			}
		}
		else  {
			provinceName = localStateOrProvince;
			if (! isAddressValid(db, localAddress1, localAddress2, localAddress3, localCity, provinceName, localPostalCode, false)) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, ADDRESS);
			}
		}

		campusCodeId = validCampusCode(db, localCampusCode, campusName, updatedBy);
		if (campusCodeId != null && campusCodeId.equals(-1L) ) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, CAMPUS_CODE);
		}
		
		return new AddressesTable(personId, localAddressTypeString, localDocumentType, null,
					localUpdatedBy, localAddress1, localAddress2, localAddress3, localCity, stateName,
					localPostalCode,  provinceName, countryCodeId, campusCodeId, 
					countryName.toString(), campusName.toString(),localCountryCode);		
	}

	/**
	 * Validate the parameters passed to the ArchiveAddress Service
	 * 
	 * @param db contains the reference to the open database connection
	 * @param personId contains the personid
	 * @param addressTypeString contains the addresstype
	 * @param documentType contains the document type
	 * @param groupId contains the groupId
	 * @param updatedBy contains the user requesting the service
	 * @return AddressTable
	 * @throws CprException 
	 */
	public static AddressesTable validateArchiveAndSetPrimaryAddressParameters(final Database db,
			long personId, String addressTypeString, String documentType, Long groupId, String updatedBy)
			throws CprException {
				
		db.getAllTableColumns(DATABASE_TABLE_NAME);
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Updated by", true);
		
		String localAddressTypeString = null;
		if (addressTypeString != null) {
			localAddressTypeString = addressTypeString.trim().toUpperCase();
			localAddressTypeString = ValidateHelper.checkField(null, localAddressTypeString, null, "Address type", false);
		}
		
		String localDocumentType = null;
		if (documentType != null) {
			localDocumentType = documentType.trim().toUpperCase();
		}
				
		if (groupId == null) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, GROUP_ID);
    	}
		
		// Validate that the address type and document type combination is valid.
		if (! isValidCombination(localAddressTypeString, localDocumentType)) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, ADDRESS_DOCUMENT_TYPE);
		}

		return new AddressesTable(personId, localAddressTypeString, localDocumentType, groupId, localUpdatedBy);
	}

	/**
	 * Validate the parameters passed to the GetAddress Service
	 * @param db reference to the open database connection
	 * @param personId   contains the personid
	 * @param updatedBy contains the requestor
	 * @param addressType if specified, will be the type of address to be retrieved.
	 * @param returnHistory Y/N flag that indicates whether history is to be retrieved or not.
	 * @return AddressesTable
	 * @throws CprException 
	 * 
	 */
	public static AddressesTable validateGetAddressParameters(final Database db, long personId,
			String updatedBy, String addressType, String returnHistory) throws CprException {

		db.getAllTableColumns(DATABASE_TABLE_NAME);
		
		// Trim all of the string values.
		@SuppressWarnings("unused")
		String localUpdatedBy = ValidateHelper.checkField(db, updatedBy, LAST_UPDATE_BY, "Requested by", true);
		String localAddressType = null;
		if (addressType != null) {
			localAddressType = addressType.trim().toUpperCase();
		}		
		boolean returnHistoryFlag = ValidateHelper.checkReturnHistory(returnHistory);
		
		// If an address type was specified, need to validate it.
		final AddressesTable addressesTable = new AddressesTable();
		
		if (addressType != null) {
				addressesTable.setAddressType(addressesTable.findAddressTypeEnum(localAddressType));
		}
		
		addressesTable.setReturnHistoryFlag(returnHistoryFlag);
		
		return addressesTable;
	}
	
	/**
	 * Validate the parameters passed to the UpdateAddress Service
	 * @param db contains the reference to the open database connection
	 * @param personId contains the personid
	 * @param addressTypeString contains the addresstype
	 * @param documentType contains the document type
	 * @param groupId contains the groupId
	 * @param address1 contains address line1
	 * @param address2 contains address line2
	 * @param address3 contains address line3
	 * @param updatedBy contains userid or system id of last updater
	 * @param city contains the city
	 * @param stateOrProvince contains the state or province
	 * @param postalCode contains the postal code
	 * @param countryCode contains the countrycode to convert to a country code id
	 * @param campusCode contains a 2 digit campus code
	 * @return AddressTable
	 * @throws CprException 
	 */
	public static AddressesTable validateUpdateAddressParameters(final Database db,
			long personId, String addressTypeString, String documentType, Long groupId, 
			String updatedBy,String address1, String address2, String address3, String city,
			String stateOrProvince, String postalCode, String countryCode,
			String campusCode) throws 
			CprException {
		
		AddressesTable addressesTable = validateAddAddressParameters(db ,personId, addressTypeString, documentType, updatedBy,
				address1, address2, address3, city, stateOrProvince, postalCode, countryCode, campusCode);
		
		if (groupId == null ) {
    		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, GROUP_ID);
    	}
		
		addressesTable.getAddressesBean().setGroupId(groupId);
		
		return addressesTable;
	}
	
	/**
	 * This routine is used to determine if a valid combination was specified for name type and document type.
	 * @param addressType contains the name type that was passed in.
	 * @param documentType contains the document type that was passed in.
	 * @return will return true if the combination is valid, otherwise it will return false.
	 */
	public static boolean isValidCombination(String addressType, String documentType) {
		
		AddressType addressEnum = Utility.getEnumFromString(AddressType.class, addressType);
		DocumentType documentTypeEnum = Utility.getEnumFromString(DocumentType.class, documentType);
		
		// If either the document type and/or name type could not have been converted return false.
		if ((addressType != null && addressEnum == null) ||
				(documentType != null && documentTypeEnum == null)) {
			return false;
		}
		
		// If the document type was specified, verify that a address type was specified and that the name type was
		// DOCUMENTED_ADDRESS
		if (documentTypeEnum != null) {
			if (addressEnum == null || addressEnum != AddressType.DOCUMENTED_ADDRESS) {
				return false;
			}
		}
		else
		{
			if (addressEnum != null &&  addressEnum == AddressType.DOCUMENTED_ADDRESS) {
				return false;
			}
		}
		
		// Success!
		return true;
	}
}
