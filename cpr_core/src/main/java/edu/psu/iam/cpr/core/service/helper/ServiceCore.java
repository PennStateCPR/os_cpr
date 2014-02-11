/* SVN FILE: $Id: ServiceCore.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.naming.NamingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.authentication.AuthenticateService;
import edu.psu.iam.cpr.core.authentication.SessionCache;
import edu.psu.iam.cpr.core.authentication.SessionCookie;
import edu.psu.iam.cpr.core.authorization.AuthorizationService;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

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
	 * @param requestor contains the userid of the requestor.
	 * @param identifierType contains the type of identifier that is used to find the user.
	 * @param identifier contains the value of the identifier.
	 * @param serviceName contains the name of the service that was being called.
	 * @param clientIpAddress contains the ip address of the caller.
	 * @return ServiceCoreReturn will be returned to caller.
	 * @throws CprException 
	 * @throws NamingException 
	 */
	public ServiceCoreReturn initializeService(Database db, String principalId, String password, 
			String requestor, String identifierType, String identifier, String serviceName, 
			String clientIpAddress) throws CprException, NamingException {
		
		final ServiceCoreReturn serviceCoreReturn = new ServiceCoreReturn();
		
		LOG4J_LOGGER.info("initalizeService: start of function.");
		
		// Do an service principal authentication.
		LOG4J_LOGGER.info("Performing service authentication...");
		authenticateService(db, principalId, password);
	
		// Add in check to see if the caller is authorized.
		LOG4J_LOGGER.info("Checking for service authorization...");
		AuthorizationService authorizationService = new AuthorizationService();
		serviceCoreReturn.setAuthorizationService(authorizationService);
		authorizationService.serviceAuthorized(db, principalId, 
				requestor, serviceName, clientIpAddress);
	
		// Only want to find the person id if the service is NOT Add Person and NOT Find Person.
		if ((! serviceName.equals(CprServiceName.AddPerson.toString())) &&
				(! serviceName.equals(CprServiceName.FindPerson.toString()))) {
			// Using the identifierType and identifier attempt to find the person in the CPR.
			LOG4J_LOGGER.info("Attempting to get the person id....");
			serviceCoreReturn.setPersonId(db.getPersonId(identifierType, identifier));

			// Check to see if the person is active, only if the service is not UnarchivePerson.
			if (! serviceName.equals(CprServiceName.UnarchivePerson.toString())) {
				LOG4J_LOGGER.info("Determing if the person is active or not...");
				db.isPersonActive(serviceCoreReturn.getPersonId());
			}
		}

		LOG4J_LOGGER.info("initalizeService: end of function.");
		
		return serviceCoreReturn;
			
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
		MessageDigest md = null;
		
		LOG4J_LOGGER.info("authenticateService: start of function.");
		
		// Verify that a service principal and a password were specified.
		if (principalId == null || principalId.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Service principal");
		}

		if (password == null || password.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Service principal's password");
		}

		try {
			md = MessageDigest.getInstance("SHA");
			md.update(password.getBytes("UTF-8"));
			byte raw[] = md.digest();
			hash = Base64.encodeBase64String(raw);
		} 
		catch (NoSuchAlgorithmException e) {
		} 
		catch (UnsupportedEncodingException e) {
		}

		if (hash != null) {
			final SessionCookie cookie = new SessionCookie(principalId, hash, new Date().getTime());

			if (! SessionCache.INSTANCE.isSessionValid(cookie)) {
				AuthenticateService.authenticate(principalId, password);
				SessionCache.INSTANCE.storeSession(cookie);
			}
		}
		else {
			AuthenticateService.authenticate(principalId, password);
		}
				
		LOG4J_LOGGER.info("authenticateService: end of function.");
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
