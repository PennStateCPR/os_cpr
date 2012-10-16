/* SVN FILE: $Id: ServiceHelper.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.helper;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.CprComponentStatusTable;
import edu.psu.iam.cpr.core.database.types.ComponentStatus;
import edu.psu.iam.cpr.core.database.types.CprComponent;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
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
* @package edu.psu.iam.cpr.service.helper
* @author $Author: jvuccolo $
* @version $Rev: 5343 $
* @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
*/

public class ServiceHelper {
		
	/** Success message! */
	final public static String SUCCESS_MESSAGE = "Success!";

	/**
	 * Empty Constructor.
	 */
	public ServiceHelper() {
		super();
	}
	
	
	/**
	 * This function is used to initialize a service.
	 * @param serviceName contains the name of the service.
	 * @param ipAddress contains the ip address of the caller. 
	 * @param principalId contains the principal identifier that is used to authenticate the service.
	 * @param password contains the password associated with the principal.
	 * @param updatedBy contains the user that either updated or requested information.
	 * @param identifierType contains the identifier type used to find the user.
	 * @param identifier contains the value of the identifier.
	 * @param serviceCore contains the service core information.
	 * @param db contains a reference to the database.
	 * @param parameters contains the parameters associated with the services.
	 * @return will return a ServiceCoreReturn object if successful.
	 * @throws GeneralDatabaseException will be thrown if there are any database problems.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	public ServiceCoreReturn initializeService(
			String serviceName,
			String ipAddress,
			String principalId,
			String password, 
			String updatedBy, 
			String identifierType,
			String identifier, 
			ServiceCore serviceCore, 
			Database db,
			StringBuilder parameters) throws GeneralDatabaseException, CprException {
		
		ServiceCoreReturn serviceCoreReturn;
		db.openSession(SessionFactoryUtil.getSessionFactory());
		serviceCoreReturn = serviceCore.initializeLogging(db, serviceName, ipAddress, parameters.toString(), updatedBy);
		serviceCore.initializeService(db, principalId, password, identifierType, identifier, serviceName, serviceCoreReturn);
		return serviceCoreReturn;
	}	

	/**
	 * This routine is used to handle generic exceptions.
	 * @param log4jLogger contains a log4j Logger instance.
	 * @param serviceCoreReturn contains the service core return instance.
	 * @param db contains a database connection.
	 * @param e Contains the generic exception.
	 */
	public void handleOtherException(Logger log4jLogger, 
			ServiceCoreReturn serviceCoreReturn,
			Database db, 
			Exception e) {
		try {
			serviceCoreReturn.getServiceLogTable().endLog(db, "Exception: " + e.getMessage());
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		log4jLogger.info("Exception: " + e.getMessage());
		db.rollbackSession();
	}

	/**
	 * This routine is used to handle exceptions of the GeneralDatabase type.
	 * @param log4jLogger contains a log4j Logger instance.
	 * @param serviceCoreReturn contains the service core return instance.
	 * @param db contains a database connection.
	 * @param e contains the GeneralDatabase exception information.
	 */
	public void handleGeneralDatabaseException(Logger log4jLogger,
			ServiceCoreReturn serviceCoreReturn,
			Database db, 
			GeneralDatabaseException e) {
		try {
			serviceCoreReturn.getServiceLogTable().endLog(db, "General Database Exception: " + e.getMessage());
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		log4jLogger.info("General Database Exception: " + e.getMessage());
		db.rollbackSession();
	}

	/**
	 * This routine is used to handle a CPR specific exception.
	 * @param log4jLogger contains an instance of a log4j Logger.
	 * @param serviceCoreReturn contains the service core instance.
	 * @param db contains a database instance.
	 * @param e contains the CPR specfic exception information.
	 * @return will return a formatted string of the exception.
	 */
	public String handleCprException(Logger log4jLogger,
			ServiceCoreReturn serviceCoreReturn,
			Database db, 
			CprException e) {
		String errorMessage = e.getReturnType().message();
		if (e.getParameterValue() != null) {
			errorMessage = String.format(errorMessage, e.getParameterValue());
		}
		try {
			serviceCoreReturn.getServiceLogTable().endLog(db, "CPR Exception: " + errorMessage);
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		log4jLogger.info("CPR Exception: " + e.getReturnType().toString() + ", Message: " + errorMessage);
		db.rollbackSession();
		return errorMessage;
	}

	
	/**
	 * This routine is used to route messages to the service provisioners.
	 * @param serviceName contains the service name
	 * @param mCore contains the MessagingCode object
	 * @param db contains the Database Object
	 * @param jsonMessage contains the json message
	 * @return MessagingCore
	 * 
	 */
	public MessagingCore sendMessagesToServiceProviders(String serviceName, MessagingCore mCore,
			Database db, JsonMessage jsonMessage) {

		boolean sendMessages = true;	
		final CprComponentStatusTable cprComponentStatusTable = new CprComponentStatusTable();
		
		// Is messaging active?
		if (cprComponentStatusTable.isCprComponentActive(db, CprComponent.MESSAGING)) {
				
			// Initialize messaging.
			try {
				mCore = new MessagingCore(db, serviceName);
				mCore.initializeMessaging();
			}
			catch (CprException e) {
				
				// A failure has occurred with messaging, so record its status as being down, and write all messages to the database.
				cprComponentStatusTable.changeComponentStatus(db, CprComponent.MESSAGING, ComponentStatus.DISABLED);
				mCore.recordFailures(db, jsonMessage);
				sendMessages = false;
			}

			// Send the messages if everything went OK with the initialization.
			if (sendMessages) {
				try {
					mCore.sendMessage(db, jsonMessage);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		// Messaging is down, so we need to queue up all of the messages.
		else {
			try {
				mCore = new MessagingCore(db, serviceName);
				mCore.recordFailures(db, jsonMessage);
			}
			catch (CprException e) {
			}
		}
		return mCore;
	}
}
