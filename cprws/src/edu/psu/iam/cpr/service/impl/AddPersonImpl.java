package edu.psu.iam.cpr.service.impl;

import java.text.ParseException;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.database.tables.PersonIdentifierTable;
import edu.psu.iam.cpr.core.database.tables.PersonTable;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.tables.PsuIdTable;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateAddress;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateDateOfBirth;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateEmail;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateName;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePerGenderRel;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePerson;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonAffiliation;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePersonIdentifier;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePhone;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateUserid;
import edu.psu.iam.cpr.core.database.types.MatchType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.MatchReturn;
import edu.psu.iam.cpr.core.service.returns.PersonReturn;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.service.returns.UseridReturn;
import edu.psu.iam.cpr.core.util.Validate;
import edu.psu.iam.cpr.core.util.ValidateSSN;
import edu.psu.iam.cpr.service.helper.FindPersonHelper;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.returns.FindPersonServiceReturn;
import edu.psu.iam.cpr.service.returns.PersonServiceReturn;

/**
 * This class provides the implementation for the Add Person service.
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
 * @package edu.psu.iam.cpr.service.impl
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public class AddPersonImpl extends ExtendedBaseServiceImpl {

	/** Used by the Add Person service to indicate that there is no person identifier established yet for the user. */
	private static final int NO_PERSON_ID = -1;
	
	private static final int ASSIGN_PSU_ID_FLAG 	= 0;
	private static final int ASSIGN_USERID_FLAG 	= 1;
	private static final int GENDER 				= 2;
	private static final int DOB 					= 3;
	private static final int NAME_TYPE 				= 4;
	private static final int NAME_DOCUMENT_TYPE 	= 5;
	private static final int FIRST_NAME 			= 6;
	private static final int MIDDLE_NAMES 			= 7;
	private static final int LAST_NAME 				= 8;
	private static final int SUFFIX 				= 9;
	private static final int ADDRESS_TYPE 			= 10;
	private static final int ADDRESS_DOCUMENT_TYPE 	= 11;
	private static final int ADDRESS1 				= 12;
	private static final int ADDRESS2 				= 13;
	private static final int ADDRESS3 				= 14;
	private static final int CITY 					= 15;
	private static final int STATE_OR_PROVINCE 		= 16;
	private static final int POSTAL_CODE 			= 17;
	private static final int COUNTRY_CODE 			= 18;
	private static final int CAMPUS_CODE 			= 19;
	private static final int PHONE_TYPE 			= 20;
	private static final int PHONE_NUMBER 			= 21;
	private static final int EXTENSION 				= 22;
	private static final int INTERNATIONAL_NUMBER 	= 23;
	private static final int EMAIL_TYPE 			= 24;
	private static final int EMAIL_ADDRESS 			= 25;
	private static final int AFFILIATION 			= 26;
	private static final int SSN 					= 27;
	
	private PersonGenderTable personGenderTable 	= null;
	private DateOfBirthTable dateOfBirthTable 		= null;
	private NamesTable namesTable 					= null;
	private AddressesTable addressesTable 			= null;
	private PhonesTable phonesTable 				= null;
	private EmailAddressTable emailAddressTable 	= null;
	private PersonTable personTable 				= null;
	private UseridTable useridTable 				= null;
	private PsuIdTable psuIdTable 					= null;
	private PersonAffiliationTable affiliationsTable= null;
	private String assignUserid 					= null;
	private String assignPsuId 						= null;
	private String matchingErrorMessage 			= null;

	
    /**
     * This method is used to execute the core logic for a service.
     * @param serviceName contains the name of the service.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the service core information.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an JsonMessage object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     */	
	@Override
	public Object runService(Database db, String serviceName,
			Logger log4jLogger, ServiceHelper serviceHelper,
			ServiceCoreReturn serviceCoreReturn, String updatedBy,
			Object[] otherParameters) throws CprException, JSONException,
			JMSException, ParseException {
		
		String assignPsuIdFlag 		= (String) otherParameters[ASSIGN_PSU_ID_FLAG];
		String assignUseridFlag 	= (String) otherParameters[ASSIGN_USERID_FLAG];
		String gender 				= (String) otherParameters[GENDER];
		String dob 					= (String) otherParameters[DOB];
		String nameType 			= (String) otherParameters[NAME_TYPE];
		String nameDocumentType 	= (String) otherParameters[NAME_DOCUMENT_TYPE];
		String firstName 			= (String) otherParameters[FIRST_NAME];
		String middleNames 			= (String) otherParameters[MIDDLE_NAMES];
		String lastName 			= (String) otherParameters[LAST_NAME];
		String suffix 				= (String) otherParameters[SUFFIX];
		String addressType 			= (String) otherParameters[ADDRESS_TYPE];
		String addressDocumentType 	= (String) otherParameters[ADDRESS_DOCUMENT_TYPE];
		String address1 			= (String) otherParameters[ADDRESS1];
		String address2 			= (String) otherParameters[ADDRESS2];
		String address3 			= (String) otherParameters[ADDRESS3];
		String city 				= (String) otherParameters[CITY];
		String stateOrProvince 		= (String) otherParameters[STATE_OR_PROVINCE];
		String postalCode 			= (String) otherParameters[POSTAL_CODE];
		String countryCode 			= (String) otherParameters[COUNTRY_CODE];
		String campusCode 			= (String) otherParameters[CAMPUS_CODE];
		String phoneType 			= (String) otherParameters[PHONE_TYPE];
		String phoneNumber 			= (String) otherParameters[PHONE_NUMBER];
		String extension 			= (String) otherParameters[EXTENSION];
		String internationalNumber 	= (String) otherParameters[INTERNATIONAL_NUMBER];
		String emailType 			= (String) otherParameters[EMAIL_TYPE];
		String emailAddress 		= (String) otherParameters[EMAIL_ADDRESS];
		String affiliation 			= (String) otherParameters[AFFILIATION];
		String ssn 					= (String) otherParameters[SSN];
		
		PersonServiceReturn personServiceReturn = null;
				
		serviceCoreReturn.setPersonId(NO_PERSON_ID);

		// Validate service input.
		validateServiceInput(db, serviceCoreReturn, updatedBy, assignPsuIdFlag, assignUseridFlag,
				gender, dob, nameType, nameDocumentType, firstName,
				middleNames, lastName, suffix, addressType, addressDocumentType,
				address1, address2, address3, city, stateOrProvince,
				postalCode, countryCode, campusCode, phoneType,
				phoneNumber, extension, internationalNumber, emailType,
				emailAddress, affiliation, ssn);

		// Verify that the minimum requirements for the service have been met.
		verifyServiceDataRequirements();

		// Do matching.
		personServiceReturn = performPersonMatch(db, serviceCoreReturn, updatedBy, ssn);

		// Did we find a match?  If so, we need to return.
		if (personServiceReturn != null) {
			log4jLogger.info(serviceName + ": " + matchingErrorMessage);
			serviceCoreReturn.getServiceLogTable().endLog(db, matchingErrorMessage);
			return (Object) personServiceReturn;
		}

		// Add a new person to the CPR (set the person identifier).
		personTable.addPerson(db);
		serviceCoreReturn.setPersonId(personTable.getPersonBean().getPersonId());

		// Store all of the information about the person in the CPR.
		storePersonInformation(db, updatedBy, ssn);

		// Set up the return to the caller.
		personServiceReturn = buildServiceReturn();

		// Send all of the messages to the service provisioners.
		buildAndSendMessages(db, serviceName, serviceHelper, serviceCoreReturn, updatedBy); 
		
		log4jLogger.info(serviceName + ": Success!");
		serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);

		return (Object) personServiceReturn;
	}

	/**
	 * This routine is used to build a successful service return.
	 * @return will return the return to the caller.
	 */
	private PersonServiceReturn buildServiceReturn() {
		
		PersonServiceReturn personServiceReturn = new PersonServiceReturn();
		personServiceReturn.setStatusCode(ReturnType.SUCCESS.index());
		personServiceReturn.setStatusMessage(ServiceHelper.SUCCESS_MESSAGE);
		personServiceReturn.setPersonReturn(new PersonReturn(personTable.getPersonBean().getPersonId()));
		if (useridTable != null) {
			final UseridReturn useridReturn[] = new UseridReturn[1];
			useridReturn[0] = new UseridReturn(useridTable.getUseridBean().getUserid(), useridTable.getUseridBean().getPrimaryFlag());
			personServiceReturn.setUseridReturnRecord(useridReturn);
			personServiceReturn.setNumberOfUserids(1);
		}
		if (psuIdTable != null) {
			final PsuIdReturn psuIdReturn[] = new PsuIdReturn[1];
			psuIdReturn[0] = new PsuIdReturn(psuIdTable.getPsuIdBean().getPsuId());
			personServiceReturn.setPsuIdReturnRecord(psuIdReturn);
			personServiceReturn.setNumberOfPsuIds(1);
		}
		return personServiceReturn;
	}

	/**
	 * This function is used to send messages to the service provisioners.
	 * @param db contains a database connection.
	 * @param serviceName contains the name of the service.
	 * @param serviceHelper contains the service helper object.
	 * @param serviceCoreReturn contains the service core return object.
	 * @param updatedBy contains the user that updated the record.
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws ParseException will be thrown if there are any parsing problems.
	 * @throws JMSException will be thrown if there are any JMS problems.
	 */
	private void buildAndSendMessages(Database db, String serviceName,
			ServiceHelper serviceHelper, ServiceCoreReturn serviceCoreReturn,
			String updatedBy) throws JSONException, CprException, ParseException, JMSException {
		
		// Create a new json message.
		final JsonMessage jsonMessage = new JsonMessage(db, serviceCoreReturn.getPersonId(), serviceName, updatedBy);
		if (personGenderTable != null) {
			jsonMessage.setGender(personGenderTable);
		}
		if (dateOfBirthTable != null) {
			jsonMessage.setDateOfBirth(dateOfBirthTable);
		}
		if (namesTable != null) {
			jsonMessage.setName(namesTable);
		}
		if (addressesTable != null) {
			jsonMessage.setAddress(addressesTable);
		}
		if (phonesTable != null) {
			jsonMessage.setPhone(phonesTable);
		}
		if (emailAddressTable != null) {
			jsonMessage.setEmailAddress(emailAddressTable);
		}
		if (affiliationsTable != null) {
			jsonMessage.setAffiliation(affiliationsTable);
		}
		if (useridTable != null) {
			jsonMessage.setUserid(useridTable);
		}
		serviceHelper.sendMessagesToServiceProviders(serviceName, db, jsonMessage);
	}

	/**
	 * This routine is used to store all of the person information in the CPR.
	 * @param db contains the database connection.
	 * @param updatedBy contains the updated by userid.
	 * @param ssn contains the SSN to be stored if one exists.
	 * @throws CprException will be thrown if there are any CPR related exceptions.
	 */
	private void storePersonInformation(Database db, String updatedBy, String ssn) throws CprException {
		
		if (personGenderTable != null) {
			personGenderTable.getPersonGenderBean().setPersonId(personTable.getPersonBean().getPersonId());
			personGenderTable.addGender(db);
		}

		if (dateOfBirthTable != null) {
			dateOfBirthTable.getDateOfBirthBean().setPersonId(personTable.getPersonBean().getPersonId());
			dateOfBirthTable.addDateOfBirth(db);
		}

		if (namesTable != null) {
			namesTable.getNamesBean().setPersonId(personTable.getPersonBean().getPersonId());
			namesTable.addName(db);
		}

		if (addressesTable != null) {
			addressesTable.getAddressesBean().setPersonId(personTable.getPersonBean().getPersonId());
			addressesTable.addAddress(db);
		}

		if (phonesTable != null) {
			phonesTable.getPhonesBean().setPersonId(personTable.getPersonBean().getPersonId());
			phonesTable.addPhone(db);
		}

		if (emailAddressTable != null) {
			emailAddressTable.getEmailAddressBean().setPersonId(personTable.getPersonBean().getPersonId());
			emailAddressTable.addAddress(db);
		}

		if (affiliationsTable != null) {
			affiliationsTable.getPersonAffiliationBean().setPersonId(personTable.getPersonBean().getPersonId());
			affiliationsTable.addAffiliation(db);
		}

		if (assignUserid.equals("Y")) {
			useridTable = ValidateUserid.validateUseridParameters(db, personTable.getPersonBean().getPersonId(), updatedBy);
			useridTable.addUserid(db);
		}

		if (assignPsuId.equals("Y")) {
			psuIdTable = new PsuIdTable(personTable.getPersonBean().getPersonId(), null, updatedBy);
			psuIdTable.addPsuIdForPersonId(db);
		}
		
		if (ssn != null) {
			PersonIdentifierTable personIdentifierTable = ValidatePersonIdentifier.validateAddPersonIdentifierParameters(db, 
					personTable.getPersonBean().getPersonId(), Database.SSN_IDENTIFIER, ssn, updatedBy);
			personIdentifierTable.addPersonIdentifier(db);
		}
	}

	/**
	 * This routine is used to perform a person match, by executing the find person implementation.
	 * @param db contains a database connection.
	 * @param serviceCoreReturn contains the service core return information.
	 * @param updatedBy contains the updated by userid.
	 * @param ssn contains the user's ssn if specified.
	 * @return will return a person service return object if the person was found.
	 * @throws CprException will be thrown if there are any CPR related exceptions.
	 * @throws ParseException will be thrown if there are any parsing exceptions.
	 */
	private PersonServiceReturn performPersonMatch(Database db, ServiceCoreReturn serviceCoreReturn, String updatedBy, String ssn) 
			throws CprException, ParseException {
		
		PersonServiceReturn personServiceReturn = null;
		
		final String matchDob = dateOfBirthTable.getFormattedDateOfBirth();
		
		String matchGender = null;
		if (personGenderTable != null) {
			matchGender = personGenderTable.getGenderType().toString();
		}
		
		final Names namesBean = namesTable.getNamesBean();
		final Addresses addressesBean = addressesTable.getAddressesBean();
		
		// Do a match.
		FindPersonHelper findPersonHelper = new FindPersonHelper(db);
		FindPersonServiceReturn findPersonServiceReturn = null;
		findPersonServiceReturn = findPersonHelper.doSearchForPersonOS(
				serviceCoreReturn, updatedBy, null, null, ssn, 
				namesBean.getFirstName(), namesBean.getLastName(), namesBean.getMiddleNames(), 
				addressesBean.getAddress1(), addressesBean.getAddress2(), addressesBean.getAddress3(), 
				addressesBean.getCity(), addressesBean.getState(), addressesBean.getPostalCode(), 
				null, addressesTable.getCountryThreeCharCode(), matchDob, matchGender, null);
		
		if (findPersonServiceReturn.getStatusCode() == ReturnType.SUCCESS.index()) {
			final MatchType matchType = MatchType.valueOf(findPersonServiceReturn.getMatchingMethod());
			switch (matchType) {

			// OK, we have found a match using PSU ID, SSN, and/or USERID.
			case PSU_ID:
			case SSN:
			case USERID:
				MatchReturn matchReturn = new MatchReturn();
				matchReturn.setPersonId(findPersonServiceReturn.getPersonID());
				matchReturn.setPsuId(findPersonServiceReturn.getPsuID());
				matchReturn.setUserId(findPersonServiceReturn.getUserId());

				matchingErrorMessage = String.format("Record cannot be added, because an exact match was found using \"%s\".", matchType.toString()); 
				personServiceReturn = new PersonServiceReturn();
				personServiceReturn.setStatusCode(ReturnType.EXACT_MATCH_EXCEPTION.index());
				personServiceReturn.setStatusMessage(matchingErrorMessage);
				personServiceReturn.setExactMatchReturn(matchReturn);
				break;

				// A match was found using a NEAR_MATCH.
			case NEAR_MATCH:
				matchingErrorMessage = "Record cannot be added, because multiple near matches were found.";
				personServiceReturn = new PersonServiceReturn();
				personServiceReturn.setStatusCode(ReturnType.NEAR_MATCH_EXCEPTION.index());
				personServiceReturn.setStatusMessage(matchingErrorMessage);
				personServiceReturn.setNearMatchReturnRecord(findPersonServiceReturn.getNearMatchArray());					
				personServiceReturn.setNumberofNearMatches(findPersonServiceReturn.getNearMatchArray().length);
				break;

				// NO_MATCH indicates that the user was not found in the CPR, so we can continue.
			case NO_MATCH:
				break;

			default:
				break;
			}
		}
		return personServiceReturn;
	}

	/**
	 * This routine is used to verify that the minimum amount of data required by the service has been
	 * specified.
	 * @throws CprException will be thrown if the minimum of amount of data has not been specified.
	 */
	private void verifyServiceDataRequirements() throws CprException {
		
		// Verify that they have specified a name.
		if (namesTable == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Name");
		}
		
		// Verify that they have specified an address.
		if (addressesTable == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Address");
		}
		
		// Verify that they have specified a date of birth.
		if (dateOfBirthTable == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Date of birth");
		}
	}

	/**
	 * This routine is used to validate information that is passed into the service.
	 * @param db contains the database connection.
	 * @param serviceCoreReturn contains the service core information.
	 * @param updatedBy contains the user that is updating the record.
	 * @param assignPsuIdFlag contains the assign psu id flag.
	 * @param assignUseridFlag contains the assign userid flag.
	 * @param gender contains the gender value.
	 * @param dob contains the date of birth.
	 * @param nameType contains the name type.
	 * @param nameDocumentType contains the name document type.
	 * @param firstName contains the first name.
	 * @param middleNames contains the middle names if specified.
	 * @param lastName contains the last name.
	 * @param suffix contains the suffix.
	 * @param addressType contains the address type.
	 * @param addressDocumentType contains the address document type.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2.
	 * @param address3 contains address line #3.
	 * @param city contains the city.
	 * @param stateOrProvince contains the state.
	 * @param postalCode contains the postal code.
	 * @param countryCode contains the country code.
	 * @param campusCode contains the campus code.
	 * @param phoneType contains the phone type.
	 * @param phoneNumber contains the phone number.
	 * @param extension contains the extension.
	 * @param internationalNumber contains the international number flag.
	 * @param emailType contains the email type.
	 * @param emailAddress contains the email address.
	 * @param affiliation contains the affiliation.
	 * @param ssn contains the ssn to be validated if specified.
	 * @throws CprException will be thrown for any CPR related problems.
	 * @throws ParseException will be thrown if there are any parsing problems.
	 */
	private void validateServiceInput(Database db,
			ServiceCoreReturn serviceCoreReturn, String updatedBy,
			String assignPsuIdFlag, String assignUseridFlag, String gender,
			String dob, String nameType, String nameDocumentType,
			String firstName, String middleNames, String lastName,
			String suffix, String addressType, String addressDocumentType,
			String address1, String address2, String address3,
			String city, String stateOrProvince, String postalCode,
			String countryCode, String campusCode, String phoneType,
			String phoneNumber, String extension, String internationalNumber,
			String emailType, String emailAddress, String affiliation, String ssn) throws CprException, ParseException {
		
		// Validate all of the input parameters to the service.
		personTable = ValidatePerson.validatePersonParameters(db, serviceCoreReturn.getPersonId(), updatedBy);
		
		// Validate the assign psu id flag.
		assignPsuId = Validate.isValidYesNo(assignPsuIdFlag);
		if (assignPsuId == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Invalid value was specified for the assign psu id parameter.");
		}
		
		// Validate the assign userid flag.
		assignUserid = Validate.isValidYesNo(assignUseridFlag);
		if (assignUserid == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Invalid value was specified for the assign userid parameter.");
		}
		
		// Validate gender if specified.
		if (gender != null) {
			personGenderTable = ValidatePerGenderRel.validateAddGenderParameters(serviceCoreReturn.getPersonId(), gender, updatedBy);
		}
		
		// Validate date of birth if specified.
		if (dob != null) {
			dateOfBirthTable = ValidateDateOfBirth.validateAddDateOfBirthParameters(serviceCoreReturn.getPersonId(), dob, updatedBy);
		}
		
		// Validate name if the last name was specified.
		if (lastName != null) {
			namesTable = ValidateName.validateAddNameParameters(db, serviceCoreReturn.getPersonId(), nameType, nameDocumentType, 
					firstName, middleNames, lastName, suffix, updatedBy);
		}
		
		// Validate the address if address1 was specified.
		if (address1 != null) {
			addressesTable = ValidateAddress.validateAddAddressParameters(db, serviceCoreReturn.getPersonId(), addressType, 
					addressDocumentType, updatedBy, address1, address2, address3, city, stateOrProvince, postalCode,  countryCode, campusCode);
		}
		
		// Validate the phone number if specified.
		if (phoneNumber != null) {
			phonesTable = ValidatePhone.validateAddPhonesParameters(db, serviceCoreReturn.getPersonId(), phoneType, 
					phoneNumber, extension, internationalNumber, updatedBy);
		}
		
		// Validate the email address if specified.
		if (emailAddress != null) {
			emailAddressTable = ValidateEmail.validateEmailAddressParameters(db, serviceCoreReturn.getPersonId(), emailType, 
					emailAddress, updatedBy);
		}
		
		// Validate the affiliation if specified.
		if (affiliation != null) {
			affiliationsTable = ValidatePersonAffiliation.validateAddAffiliationParameters(db, serviceCoreReturn.getPersonId(), 
					affiliation, updatedBy);
		}
		
		// Validate the SSN if one was specified.
		if ((ssn != null) && (! ValidateSSN.validateSSN(ssn))) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "SSN");	
		}
		
	}

	/**
	 * This routine is used to handle exception processing.
	 * @param statusCode contains the status code.
	 * @param statusMessage contains the status message.
	 * @return will return an service return object.
	 */
	@Override
	public Object handleException(int statusCode, String statusMessage) {
		return (Object) new PersonServiceReturn(statusCode, statusMessage);
	}

}
