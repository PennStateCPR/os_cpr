/* SVN FILE: $Id: AddEmailAddressImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.EmailAddressTable;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidateEmail;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.ServiceReturn;

/**
 * This class provides the implementation for the Add Email services.
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
public class AddEmailAddressImpl implements ServiceInterface {

	final private static Logger LOG4J_LOGGER = Logger.getLogger(AddEmailAddressImpl.class);
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
		
		final ServiceHelper serviceHelper = new ServiceHelper();
		EmailAddressTable emailAddressTable = null;
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		MessagingCore mCore = null;
		ServiceCore serviceCore = new ServiceCore();
		Database db = new Database();
		
		LOG4J_LOGGER.info("AddEmailAddress: Start of service.");
		try {

			final String emailAddressType = (String) otherParameters[0];
			final String emailAddress = (String) otherParameters[1];
			
			StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("updatedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("emailAddressType=[").append(emailAddressType).append("] ");
			parameters.append("emailAddress=[").append(emailAddress).append("] ");
			
			LOG4J_LOGGER.info("AddEmailAddress: Input Parameters = " + parameters.toString());
			
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
			LOG4J_LOGGER.info("AddEmailAddress: Found Person Id = " + serviceCoreReturn.getPersonId());

			// Validate the data passed into the service.
			emailAddressTable = ValidateEmail.validateEmailAddressParameters(db, serviceCoreReturn.getPersonId(), emailAddressType, 
					emailAddress, updatedBy);

			// Determine if the caller is authorized to make this call.
			db.isDataActionAuthorized(serviceCoreReturn, emailAddressTable.getEmailAddressType().toString(), 
					AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy);
				
			// Insert the record.
			emailAddressTable.addAddress(db);
			
			// Create a new json message.
			JsonMessage jsonMessage = new JsonMessage(db, serviceCoreReturn.getPersonId(), serviceName, updatedBy);
			jsonMessage.setEmailAddress(emailAddressTable);			

			mCore = serviceHelper.sendMessagesToServiceProviders(serviceName, mCore, db, jsonMessage); 				
			
			// Log a success!
			LOG4J_LOGGER.info("AddEmailAddress: Status = SUCCESS, EmailAddress added");
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			db.closeSession();
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
		} 
		catch (JSONException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.JSON_EXCEPTION.index(), e.getMessage());
		} 
		catch (JMSException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.JMS_EXCEPTION.index(), e.getMessage());
		}
		finally {
			mCore.closeMessaging();
		}	
		
		LOG4J_LOGGER.info("AddEmailAddress: End of service.");
		// Return a successful status.
		return (Object) new ServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE);
	}

}
