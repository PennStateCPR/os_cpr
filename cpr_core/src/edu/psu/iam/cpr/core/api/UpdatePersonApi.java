package edu.psu.iam.cpr.core.api;

import static edu.psu.iam.cpr.core.api.BaseApi.*;
import java.text.ParseException;
import java.util.Map;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.PersonServiceReturn;
import edu.psu.iam.cpr.core.database.Database;
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
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.PersonReturn;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.service.returns.UseridReturn;
import edu.psu.iam.cpr.core.util.Utility;
import edu.psu.iam.cpr.core.util.Validate;
import edu.psu.iam.cpr.core.util.ValidateSSN;

/**
 * This class provides the implementation for the Update Person API.
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
 * @package edu.psu.iam.cpr.core.api
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class UpdatePersonApi extends ExtendedBaseApi {

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
	
    /**
     * This method is used to execute the core logic for a service.
     * @param apiName contains the name of the api.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the person identifier value.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains a Map of Java objects that are additional parameters for the service.
     * @return will return an object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     * @throws JMSException will be thown if there are any JMS issues.
     */
	@Override
	public Object runApi(final String apiName, final Database db, final ServiceCoreReturn serviceCoreReturn,
			final String updatedBy, final Map<String, Object> otherParameters,
			final boolean checkAuthorization) throws CprException, JSONException,
			ParseException, JMSException {
        final String assignPsuIdFlag            = (String) otherParameters.get(ASSIGN_PSU_ID_FLAG_KEY);
        final String assignUseridFlag           = (String) otherParameters.get(ASSIGN_USERID_FLAG_KEY);
        final String gender						= (String) otherParameters.get(GENDER_KEY);
        final String dob						= (String) otherParameters.get(DOB_KEY);
        final String nameType					= (String) otherParameters.get(NAME_TYPE_KEY);
        final String nameDocumentType           = (String) otherParameters.get(NAME_DOCUMENT_TYPE_KEY);
        final String firstName					= (String) otherParameters.get(FIRST_NAME_KEY);
        final String middleNames				= (String) otherParameters.get(MIDDLE_NAMES_KEY);
        final String lastName					= (String) otherParameters.get(LAST_NAME_KEY);
        final String suffix						= (String) otherParameters.get(SUFFIX_KEY);
        final String nickname					= (String) otherParameters.get(NICKNAME);
        final String addressType				= (String) otherParameters.get(ADDRESS_TYPE_KEY);
        final String addressDocumentType        = (String) otherParameters.get(ADDRESS_DOCUMENT_TYPE_KEY);
        final Long addressGroupId				= Utility.safeConvertStringToLong((String) otherParameters.get(ADDRESS_GROUP_ID_KEY));
        final String address1					= (String) otherParameters.get(ADDRESS1_KEY);
        final String address2					= (String) otherParameters.get(ADDRESS2_KEY);
        final String address3					= (String) otherParameters.get(ADDRESS3_KEY);
        final String city						= (String) otherParameters.get(CITY_KEY);
        final String stateOrProvince            = (String) otherParameters.get(STATE_KEY);
        final String postalCode					= (String) otherParameters.get(POSTALCODE_KEY);
        final String countryCode				= (String) otherParameters.get(COUNTRY_KEY);
        final String campusCode					= (String) otherParameters.get(CAMPUS_KEY);
        final String verifyAddressFlag          = (String) otherParameters.get(VERIFY_ADDRESS_FLAG_KEY);
        final String phoneType					= (String) otherParameters.get(PHONE_TYPE_KEY);
        final Long phoneGroupId					= Utility.safeConvertStringToLong((String) otherParameters.get(PHONE_GROUP_ID_KEY));
        final String phoneNumber				= (String) otherParameters.get(PHONE_NUMBER_KEY);
        final String extension					= (String) otherParameters.get(PHONE_EXTENSION_KEY);
        final String internationalNumber        = (String) otherParameters.get(PHONE_INTERNATIONAL_NUMBER_KEY);
        final String emailType					= (String) otherParameters.get(EMAIL_ADDRESS_TYPE_KEY);
        final String emailAddress				= (String) otherParameters.get(EMAIL_ADDRESS_KEY);
        final String affiliation				= (String) otherParameters.get(AFFILIATION_KEY);
        final String ssn						= (String) otherParameters.get(SSN_KEY);
        final long personId						= serviceCoreReturn.getPersonId();

		// Validate the inputs to the service.
		validateServiceInput(db, serviceCoreReturn, updatedBy, assignPsuIdFlag,
				assignUseridFlag, gender, dob, nameType, nameDocumentType,
				firstName, middleNames, lastName, suffix, nickname,
				addressType, addressDocumentType, addressGroupId, address1,
				address2, address3, city, stateOrProvince, postalCode,
				countryCode,campusCode, verifyAddressFlag, phoneType, phoneGroupId, phoneNumber,
				extension, internationalNumber, emailType, emailAddress, affiliation, ssn);

		// Update the database.
		updatePersonInformation(db, updatedBy, ssn);

		// Build the service return.
		final PersonServiceReturn personServiceReturn = buildServiceReturn();

		// Send the messages.
		buildAndSendMessages(db, apiName, personId, updatedBy); 			

		return personServiceReturn;
	}
	
	/**
	 * This function is used to send messages to the service provisioners.
	 * @param db contains a database connection.
	 * @param serviceName contains the name of the service.
	 * @param personId contains the person identifier.
	 * @param updatedBy contains the user that updated the record.
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 * @throws ParseException will be thrown if there are any parsing problems.
	 * @throws JMSException will be thrown if there are any JMS problems.
	 */
	private void buildAndSendMessages(Database db, String serviceName,
			long personId,
			String updatedBy) throws JSONException, CprException,
			ParseException, JMSException {
		
		final JsonMessage jsonMessage = new JsonMessage(db, personId, serviceName, updatedBy);
		
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

		ApiHelper.sendMessagesToServiceProviders(serviceName, db, jsonMessage);
	}

	/**
	 * This routine is used to build the service return (successful!).
	 * @return will return a person service return object.
	 */
	private PersonServiceReturn buildServiceReturn() {
		
		PersonServiceReturn personServiceReturn = new PersonServiceReturn();
		personServiceReturn.setStatusCode(ReturnType.SUCCESS.index());
		personServiceReturn.setStatusMessage(ApiHelper.SUCCESS_MESSAGE);
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
	 * This routine is used to update person information.
	 * @param db contains the database connection.
	 * @param updatedBy contains the userid that is doing the update.
	 * @param ssn contains the social security number if one was specified.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	private void updatePersonInformation(Database db, String updatedBy, String ssn) throws CprException {
		
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
			addressesTable.updateAddress(db);
		}

		if (phonesTable != null) {
			phonesTable.getPhonesBean().setPersonId(personTable.getPersonBean().getPersonId());
			phonesTable.updatePhone(db);
		}

		if (emailAddressTable != null) {
			emailAddressTable.getEmailAddressBean().setPersonId(personTable.getPersonBean().getPersonId());
			emailAddressTable.updateAddress(db);
		}

		if (affiliationsTable != null) {
			affiliationsTable.getPersonAffiliationBean().setPersonId(personTable.getPersonBean().getPersonId());
			affiliationsTable.updateAffiliation(db);
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
	 * @param nickname contains the nickname.
	 * @param addressType contains the address type.
	 * @param addressDocumentType contains the address document type.
	 * @param addressGroupId contains the group id within the type to be updated.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2.
	 * @param address3 contains address line #3.
	 * @param city contains the city.
	 * @param stateOrProvince contains the state.
	 * @param postalCode contains the postal code.
	 * @param countryCode contains the country code.
	 * @param campusCode contains the campus code.
	 * @param phoneType contains the phone type.
	 * @param phoneGroupId contains the phone group id within the type to be updated.
	 * @param phoneNumber contains the phone number.
	 * @param extension contains the extension.
	 * @param internationalNumber contains the international number flag.
	 * @param emailType contains the email type.
	 * @param emailAddress contains the email address.
	 * @param affiliation contains the affiliation.
	 * @param ssn contains the ssn to be validated if specified.
	 * @param verifyAddressCode contains indication of whether address should be verified
	 * @throws CprException will be thrown for any CPR related problems.
	 * @throws ParseException will be thrown if there are any parsing problems.
	 */
	private void validateServiceInput(Database db,
			ServiceCoreReturn serviceCoreReturn, String updatedBy,
			String assignPsuIdFlag, String assignUseridFlag, String gender,
			String dob, String nameType, String nameDocumentType,
			String firstName, String middleNames, String lastName,
			String suffix, String nickname, String addressType,
			String addressDocumentType, Long addressGroupId, String address1,
			String address2, String address3, String city,
			String stateOrProvince, String postalCode, String countryCode, String campusCode,
			String verifyAddressFlag, String phoneType, Long phoneGroupId,
			String phoneNumber, String extension, String internationalNumber,
			String emailType, String emailAddress, String affiliation, String ssn)
			throws CprException, ParseException {
		
		// Validate all of the input parameters to the service.
		personTable = ValidatePerson.validatePersonParameters(db, serviceCoreReturn.getPersonId(), updatedBy);

		assignPsuId = Validate.isValidYesNo(assignPsuIdFlag);
		if (assignPsuId == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, 
					"Assign psu id flag");
		}

		assignUserid = Validate.isValidYesNo(assignUseridFlag);
		if (assignUserid == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, 
					"Assign userid flag");
		}

		if (gender != null) {
			personGenderTable = ValidatePerGenderRel.validateAddGenderParameters(serviceCoreReturn.getPersonId(), gender, updatedBy);
		}

		if (dob != null) {
			dateOfBirthTable = ValidateDateOfBirth.validateAddDateOfBirthParameters(serviceCoreReturn.getPersonId(), dob, updatedBy);
		}

		if (lastName != null) {
			namesTable = ValidateName.validateAddNameParameters(db, serviceCoreReturn.getPersonId(), nameType, nameDocumentType, 
					firstName, middleNames, lastName, suffix, nickname, updatedBy);
		}

		if (address1 != null) {
			addressesTable = ValidateAddress.validateUpdateAddressParameters(db, serviceCoreReturn.getPersonId(), addressType, 
					addressDocumentType, addressGroupId, updatedBy, address1, address2, address3, city, stateOrProvince, postalCode,  countryCode, campusCode);
			String doAddressTransform = Validate.isValidYesNo(verifyAddressFlag);
			if (doAddressTransform == null) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "verify address flag");
			}
		}

		if (phoneNumber != null) {
			phonesTable = ValidatePhone.validateUpdatePhonesParameters(db, serviceCoreReturn.getPersonId(), phoneType, phoneGroupId, 
					phoneNumber, extension, internationalNumber, updatedBy);
		}

		if (emailAddress != null) {
			emailAddressTable = ValidateEmail.validateEmailAddressParameters(db, serviceCoreReturn.getPersonId(), emailType, 
					emailAddress, updatedBy);
		}

		if (affiliation != null) {
			affiliationsTable = ValidatePersonAffiliation.validateAddAffiliationParameters(db,serviceCoreReturn.getPersonId(), 
					affiliation, updatedBy);
		}

		if (ssn != null && (! ValidateSSN.validateSSN(ssn))) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "SSN");
		}
	}
}
