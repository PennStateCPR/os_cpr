/* SVN FILE: $Id: ArchiveConfidentialityHoldImpl.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.impl;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.ConfidentialityTable;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.ValidateConfidentiality;
import edu.psu.iam.cpr.service.helper.ServiceHelper;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.returns.ServiceReturn;

/**
 * This class provides the implementation for the Archive Confidentiality Hold service.
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
public class ArchiveConfidentialityHoldImpl implements ServiceInterface {

	final private static Logger log4jLogger = Logger.getLogger(ArchiveConfidentialityHoldImpl.class);
	
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
		final ServiceCore serviceCore = new ServiceCore();
		ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		MessagingCore mCore = null;
		final Database db = new Database();
		
		log4jLogger.info("ArchiveConfidentialityHold: Start of service.");
		
		try {
			
			final String confidentialityType = (String) otherParameters[0];
			
			// Build the parameter list.
			final StringBuilder parameters = new StringBuilder(10000);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("updatedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
			parameters.append("confidentialityType=[").append(confidentialityType).append("] ");
			log4jLogger.info("ArchiveConfidentialityHold: input parameters = " + parameters.toString());

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
			log4jLogger.info(serviceName + ": person identifier = " + serviceCoreReturn.getPersonId());

			// Validate the input parameters.
			final ConfidentialityTable confidentialityTable = ValidateConfidentiality.validateArchiveConfidentialityParameters(db, 
											serviceCoreReturn.getPersonId(), confidentialityType, updatedBy);
			
			// Verify that the user is authorized to perform the action based on the type of data.
			db.isDataActionAuthorized(serviceCoreReturn, confidentialityTable.getConfidentialityType().toString(), 
											AccessType.ACCESS_OPERATION_ARCHIVE.toString(), updatedBy);

			// Archive the hold.
			log4jLogger.info(serviceName + ": archive confidentiality hold.");
			confidentialityTable.archiveConfidentiality(db);
			
			// Create a new json message.
			final JsonMessage jsonMessage = new JsonMessage(db, serviceCoreReturn.getPersonId(), serviceName, updatedBy);
			jsonMessage.setConfidentiality(confidentialityTable);
			log4jLogger.info(serviceName + ": JSON message = " + jsonMessage.toString());
				
			mCore = serviceHelper.sendMessagesToServiceProviders(serviceName, mCore, db, jsonMessage); 		

			// Log a success!
			serviceCoreReturn.getServiceLogTable().endLog(db, ServiceHelper.SUCCESS_MESSAGE);
			
			// Commit;
			db.closeSession();
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (GeneralDatabaseException e) {
			serviceHelper.handleGeneralDatabaseException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), e.getMessage());
		} 
		catch (Exception e) {
			serviceHelper.handleOtherException(log4jLogger, serviceCoreReturn, db, e);
			return (Object) new ServiceReturn(ReturnType.ARCHIVE_FAILED_EXCEPTION.index(), e.getMessage());
		} 
		finally {
			try {
				mCore.closeMessaging();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		log4jLogger.info("ArchiveConfidentialityHold: End of service.");
		
		// Success so return it.
		return (Object) new ServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE);
	}

}
