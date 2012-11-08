/* SVN FILE: $Id: GetPersonImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
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
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.returns.UseridReturn;
import edu.psu.iam.cpr.core.util.Validate;
import edu.psu.iam.cpr.core.util.ValidatePerson;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
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
public class GetPersonImpl implements ServiceInterface {

	final private static Logger LOG4J_LOGGER = Logger.getLogger(GetPersonImpl.class);
	private static final int BUFFER_SIZE = 2048;
	
	/**
	 * This method provides the implementation for a service.
	 * @param serviceName contains the name of the service.
	 * @param ipAddress contains the ip address of the caller. 
	 * @param principalId contains the principal identifier that is used to authenticate the service.
	 * @param password contains the password associated with the principal.
	 * @param updatedBy contains the user that either updated or requested information.
	 * @param identifierType contains the identifier type used to find the user.
	 * @param identifier contains the value of the identifier.
	 * @param otherParameters contains an array of Objects that are additional parameters to the service implementation.
	 * @return Object will return an object will needed to be casted to obtain the real return.
	 */

	public Object implementService(String serviceName, String ipAddress,
			String principalId, String password, String updatedBy,
			String identifierType, String identifier, Object[] otherParameters) {
		
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		final ServiceCore serviceCore = new ServiceCore();
		PersonServiceReturn personServiceReturn = null;
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		
		LOG4J_LOGGER.info(serviceName + ": start of service.");
		
		try {
			
			String returnHistory = (String) otherParameters[0];
			
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("requestedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("returnHistory=[").append(returnHistory).append("] ");
			LOG4J_LOGGER.info(serviceName + ": input parameters = " + parameters.toString());
			
			if ((returnHistory = Validate.isValidYesNo(returnHistory)) == null) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Return history");
			}
			boolean returnHistoryFlag = (returnHistory.equals("Y")) ? true : false;

			// Init the service.
			serviceCoreReturn = serviceHelper.initializeService(serviceName, 
					ipAddress,
					principalId,
					password,
					updatedBy,
					identifierType, 
					identifier,
					serviceCore, 
					db, 
					parameters);
			
			ValidatePerson.validatePersonParameters(db, serviceCoreReturn.getPersonId(), updatedBy);

			personServiceReturn = new PersonServiceReturn();
			
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
			
			// Log a success!
			LOG4J_LOGGER.info(serviceName + ": the service was successful.");
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			
			// Do a commit.
			db.closeSession();
		} 
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PersonServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PersonServiceReturn(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new PersonServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
		} 
		
		LOG4J_LOGGER.info(serviceName + ": end of service.");
		return (Object) personServiceReturn;
	}

}
