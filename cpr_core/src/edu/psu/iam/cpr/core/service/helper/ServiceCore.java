/* SVN FILE: $Id: ServiceCore.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.helper;

import java.util.Date;

import javax.naming.NamingException;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.authentication.AuthenticateService;
import edu.psu.iam.cpr.core.authentication.SessionCache;
import edu.psu.iam.cpr.core.authentication.SessionCookie;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.ServiceLog;
import edu.psu.iam.cpr.core.database.tables.ServiceLogTable;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.util.PasswordService;

/**
 * This class provides an implementation for the service core functions.  These functions are executed
 * by every service to initialize themselves.
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
 *   
 * @package edu.psu.iam.cpr.core.service.helper
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class ServiceCore {

	/** Instance of logger */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(ServiceCore.class);
	
	/**
	 * Constructor.
	 */
	public ServiceCore() {
		super();
	}
	
	/**
	 * This routine is used to perform service setup tasks which include authenticating the end user, determining if the
	 * caller is authorized to call the service and finding the person identifier.
	 * @param db contains the database instance.
	 * @param principalId contains the principal identifier that is accessing the service.
	 * @param password contains the password for the principal identifier.
	 * @param identifierType contains the type of identifier that is used to find the user.
	 * @param identifier contains the value of the identifier.
	 * @param serviceName contains the name of the service that was being called.
	 * @param serviceCoreReturn contains the service core return class, a number of its values will be set as the result of 
	 *        initializing the service.
	 * @throws CprException 
	 * @throws NamingException 
	 */
	public void initializeService(Database db, String principalId, String password, String identifierType, String identifier, String serviceName, 
			ServiceCoreReturn serviceCoreReturn) throws CprException, NamingException {
		
		LOG4J_LOGGER.info("initalizeService: start of function.");
		
		// Do an service principal authentication.
		LOG4J_LOGGER.info("Performing service authentication...");
		authenticateService(db, principalId, password);
	
		// Add in check to see if the caller is authorized.
		LOG4J_LOGGER.info("Checking for service authorization...");
		db.requestorAuthorized(principalId, serviceCoreReturn.getServiceLogTable().getServiceLogBean().getRequestUserid(), serviceName);
		serviceCoreReturn.setIamGroupKey(db.getIamGroupKey());
		serviceCoreReturn.setRegistrationAuthorityKey(db.getRegistrationAuthorityKey());
	
		// Using the identifierType and identifier attempt to find the person in the CPR.
		LOG4J_LOGGER.info("Attempting to get the person id....");
		serviceCoreReturn.setPersonId(db.getPersonId(identifierType, identifier));
			
		// Check to see if the person is active, only if the service is not UnarchivePerson.
		LOG4J_LOGGER.info("Determing if the person is active or not...");
		if (! serviceName.equals(CprServiceName.UnarchivePerson.toString())) {
			db.isPersonActive(serviceCoreReturn.getPersonId());
		}

		LOG4J_LOGGER.info("initalizeService: end of function.");
			
	}	
	
	/**
	 * 
	 * @param db
	 * @param principalId
	 * @param password
	 * @throws NamingException 
	 * @throws CprException 
	 */
	public void authenticateService(Database db, String principalId, String password) throws CprException, NamingException {
		
		String hash = null;
		
		LOG4J_LOGGER.info("authenticateService: start of function.");

		hash = PasswordService.INSTANCE.encrypt(password);
		
		final SessionCookie cookie = new SessionCookie(principalId, hash, new Date().getTime());
				
		if (! SessionCache.INSTANCE.isSessionValid(cookie)) {
			AuthenticateService.authenticate(principalId, password);
			SessionCache.INSTANCE.storeSession(cookie);
		}
				
		LOG4J_LOGGER.info("authenticateService: end of function.");
	}
	
	/**
	 * This routine is used to initialize logging for a service.  It will establish a connection to the database and do
	 * the initial log record.
	 * @param db contains the database connection.
	 * @param serviceName contains the name of the service which is calling this routine.
	 * @param clientIpAddress contains the client ip address that called the service.
	 * @param serviceInputParameters contains the input parameters for the service.
	 * @param requestor contains the requester who is calling the service.
	 * @return will return a ServiceCoreReturn object upon successful initialization.
	 */
	public ServiceCoreReturn initializeLogging(Database db, String serviceName, String clientIpAddress, String serviceInputParameters, 
			String requestor) {
		
		LOG4J_LOGGER.info("initalizeLogging: start of function.");
		final ServiceCoreReturn s = new ServiceCoreReturn();
		// Set up the service log table and start logging.  NOTE: if we cannot log a start record, we do not stop the service, we
		// just dump a stack trace.
		try {
			final ServiceLogTable serviceLogTable = new ServiceLogTable();

			// Init the bean.
			final ServiceLog bean = new ServiceLog();
			bean.setIpAddress(clientIpAddress);
			bean.setRequestStart(new Date());
			bean.setRequestString(serviceInputParameters);
			bean.setRequestUserid(requestor);
			bean.setRequestDuration(0L);
			bean.setWebServiceKey(serviceLogTable.getWebServiceKey(db, serviceName));
			serviceLogTable.setServiceLogBean(bean);

			// Save off the service log.
			s.setServiceLogTable(serviceLogTable);
		}
		catch (Exception e) {
		}
		LOG4J_LOGGER.info("initalizeLogging: end of function.");
	
		return s;
		
	}

	/**
	 * This routine is used to initialize the AddPerson service.
	 * @param db contains the database connection.
	 * @param principalId contains the principal identifier used to authenticate the service.
	 * @param password contains the password for the principal.
	 * @param serviceName contains the name of the service.
	 * @param serviceCoreReturn contains the service core return object.
	 * @throws CprException 
	 * @throws NamingException 

	 */
	public void initializeService(Database db, String principalId, String password, String serviceName, ServiceCoreReturn serviceCoreReturn) 
		throws CprException, NamingException {
		
		LOG4J_LOGGER.info("initializeService: start of function.");
		// Do an service principal authentication.
		authenticateService(db, principalId, password);
	
		// Add in check to see if the caller is authorized.
		db.requestorAuthorized(principalId, serviceCoreReturn.getServiceLogTable().getServiceLogBean().getRequestUserid(), serviceName);
		serviceCoreReturn.setIamGroupKey(db.getIamGroupKey());
		serviceCoreReturn.setRegistrationAuthorityKey(db.getRegistrationAuthorityKey());
		LOG4J_LOGGER.info("initializeService: start of function.");
	}
	
	/**
	 * This routine is used to dump a service parameter.  It will either return the parameter's value or NOT SPECIFIED if the 
	 * parameter is null.
	 * @param parameter contains the parameter to check.
	 * @return will return the parameter value if its not null, otherwise it will return NOT SPECIFIED.
	 */
	public String dumpParameter(String parameter) {
		return (parameter == null) ? "NOT SPECIFIED" : parameter;
	}
}
