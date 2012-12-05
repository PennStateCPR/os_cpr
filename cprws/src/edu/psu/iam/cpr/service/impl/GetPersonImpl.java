/* SVN FILE: $Id: GetPersonImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import java.text.ParseException;

import javax.jms.JMSException;

import org.apache.log4j.Logger;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.tables.DateOfBirthTable;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.tables.NamesTable;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.database.tables.PersonGenderTable;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.tables.PsuIdTable;
import edu.psu.iam.cpr.core.database.tables.UseridTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.AddressReturn;
import edu.psu.iam.cpr.core.service.returns.AffiliationReturn;
import edu.psu.iam.cpr.core.service.returns.DateOfBirthReturn;
import edu.psu.iam.cpr.core.service.returns.EmailAddressReturn;
import edu.psu.iam.cpr.core.service.returns.GenderReturn;
import edu.psu.iam.cpr.core.service.returns.NameReturn;
import edu.psu.iam.cpr.core.service.returns.PhoneReturn;
import edu.psu.iam.cpr.core.service.returns.PsuIdReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.UseridReturn;
import edu.psu.iam.cpr.core.database.tables.validate.ValidatePerson;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.returns.PersonServiceReturn;

/**
 * This class provides an implementation for the get person service.
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
public class GetPersonImpl extends ExtendedBaseServiceImpl {

	/** Contains the index for the return history parameter */
	private static final int RETURN_HISTORY = 0;

    /**
     * This method is used to execute the core logic for a service.
     * @param db contains a open database session.
     * @param serviceName contains the name of the service.
     * @param log4jLogger database logger.
     * @param serviceHelper contains the service helper object.
     * @param serviceCoreReturn contains the service core information.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an array of Java objects that are additional parameters for the service.
     * @return will return an object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any JSON issues.
     * @throws JMSException will be thrown if there are any JMS issues.
     * @throws ParseException will be thrown if there are any parsing issues.
     */
	@Override
	public Object runService(Database db, String serviceName,
			Logger log4jLogger, ServiceHelper serviceHelper, ServiceCoreReturn serviceCoreReturn, String updatedBy, 
			Object[] otherParameters) throws CprException, JMSException, JSONException, ParseException {
		
		// Validate the return history parameter.
		String returnHistory = (String) otherParameters[RETURN_HISTORY];
		if (returnHistory == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
		}
		boolean returnHistoryFlag = (returnHistory.equals("Y")) ? true : false;
		
		// Validate the person parameters.
		ValidatePerson.validatePersonParameters(db, serviceCoreReturn.getPersonId(), updatedBy);

		PersonServiceReturn personServiceReturn = new PersonServiceReturn();

		personServiceReturn.setStatusCode(ReturnType.SUCCESS.index());
		personServiceReturn.setStatusMessage(ServiceHelper.SUCCESS_MESSAGE);

		// Get the DOB.
		final DateOfBirthTable dateOfBirthTable = new DateOfBirthTable();
		dateOfBirthTable.setReturnHistoryFlag(returnHistoryFlag);
		final DateOfBirthReturn dateOfBirthReturn[] = dateOfBirthTable.getDateOfBirthForPersonId(db, 
				serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfDateOfBirthdays(dateOfBirthReturn.length);
		personServiceReturn.setDateOfBirthRecord(dateOfBirthReturn);

		// Get the Gender.
		final PersonGenderTable perGenderRelTable = new PersonGenderTable();
		perGenderRelTable.setReturnHistoryFlag(returnHistoryFlag);
		final GenderReturn genderReturn[] = perGenderRelTable.getGenderForPersonId(db, 
				serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfGenders(genderReturn.length);
		personServiceReturn.setGenderReturnRecord(genderReturn);

		// Get the PSU ID.
		final PsuIdTable psuIdTable = new PsuIdTable();
		psuIdTable.setReturnHistoryFlag(returnHistoryFlag);
		final PsuIdReturn psuIdReturn[] = psuIdTable.getPsuIdForPersonId(db, 
				serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfPsuIds(psuIdReturn.length);
		personServiceReturn.setPsuIdReturnRecord(psuIdReturn);

		// Get the names.
		final NamesTable namesTable = new NamesTable();
		namesTable.setReturnHistoryFlag(returnHistoryFlag);
		final NameReturn nameReturn[] = namesTable.getNamesForPersonId(db, serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfNames(nameReturn.length);
		personServiceReturn.setNameReturnRecord(nameReturn);

		// Get the addresses.
		final AddressesTable addressesTable = new AddressesTable();
		addressesTable.setReturnHistoryFlag(returnHistoryFlag);
		final AddressReturn addressReturn[] = addressesTable.getAddress(db, serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfAddresses(addressReturn.length);
		personServiceReturn.setAddressReturnRecord(addressReturn);

		// Get the phones.
		final PhonesTable phonesTable = new PhonesTable();
		phonesTable.setReturnHistoryFlag(returnHistoryFlag);
		final PhoneReturn phoneReturn[] = phonesTable.getPhones(db, serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfPhones(phoneReturn.length);
		personServiceReturn.setPhoneReturnRecord(phoneReturn);

		// Get the email addresses.
		final EmailAddressTable emailAddressTable = new EmailAddressTable();
		emailAddressTable.setReturnHistoryFlag(returnHistoryFlag);
		final EmailAddressReturn emailAddressReturn[] = emailAddressTable.getEmailAddressForPersonId(db, serviceCoreReturn.getPersonId());			
		personServiceReturn.setNumberOfEmailAddresses(emailAddressReturn.length);
		personServiceReturn.setEmailAddressReturnRecord(emailAddressReturn);

		// Get the userids.
		final UseridTable useridTable = new UseridTable();
		useridTable.setReturnHistoryFlag(returnHistoryFlag);
		final UseridReturn useridReturn[] = useridTable.getUseridsForPersonId(db, serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfUserids(useridReturn.length);
		personServiceReturn.setUseridReturnRecord(useridReturn);


		// Get the affiliations.
		final PersonAffiliationTable affiliationsTable = new PersonAffiliationTable();
		affiliationsTable.setReturnHistoryFlag(returnHistoryFlag);
		final AffiliationReturn affiliationReturn[] = affiliationsTable.getAllAffiliationsForPersonId(db, serviceCoreReturn.getPersonId());
		personServiceReturn.setNumberOfAffiliations(affiliationReturn.length);
		personServiceReturn.setAffiliationReturnRecord(affiliationReturn);
		
		return (Object) personServiceReturn;

	}

    /**
     * This routine is used to handle exceptions.
     * @param statusCode contains the status code associated with the exception.
     * @param statusMessage contains the error message text.
     * @return will return an service return containing the exception information.
     */
	@Override
	public Object handleException(int statusCode, String statusMessage) {
		return (Object) new PersonServiceReturn(statusCode, statusMessage);
	}
}
