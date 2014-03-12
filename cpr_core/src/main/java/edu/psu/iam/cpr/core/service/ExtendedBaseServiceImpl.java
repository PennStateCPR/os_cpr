package edu.psu.iam.cpr.core.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.json.JSONException;

import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceHelper;
import edu.psu.iam.cpr.core.util.Utility;
/**
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
public abstract class ExtendedBaseServiceImpl {
	
	/** Contains the log4j Logger instance */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(ExtendedBaseServiceImpl.class);
	
	/** Buffer size */
	private static final int BUFFER_SIZE = 4096;
	
	/** Elapsed Time (milliseconds) */
	private long elapsedTime;
	
	/** Contains the response timestamp */
	private String responseTimestamp;
	
	/**
	 * This code provides an abstract template for the implementation of the "GET" service.
	 * @param serviceName contains the name of the service.
	 * @param ipAddress contains the IP address of the caller.
	 * @param principalId contains the principal id of the caller. 
	 * @param password contains the password for the principal.
	 * @param updatedBy contains the updated by user.
	 * @param identifierType contains the identifier type.
	 * @param identifier contains the identifier.
	 * @param otherParameters contains other parameters related to the service.
	 * @return will return an object containing the results of this service.
	 */
	public Object implementService(String serviceName, String ipAddress,
			String principalId, String password, String updatedBy,
			String identifierType, String identifier, Map<String,Object> otherParameters) {

		final long startTime = System.currentTimeMillis();
		ServiceCoreReturn serviceCoreReturn = null;
		final ServiceCore serviceCore = new ServiceCore();
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		Object serviceReturn = null;

		try {
			
			// Dump the parameters to a formatted string.
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("requestedBy=[").append(updatedBy).append("] ");
			parameters.append("identifierType=[").append(identifierType).append("] ");
			parameters.append("identifier=[").append(identifier).append("] ");
            parameters.append(ApiHelper.dumpParameters(otherParameters));

            LOG4J_LOGGER.info(serviceName + ": Input Parameters = " + parameters.toString());

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
			LOG4J_LOGGER.info(serviceName + ": Found Person Id = " + serviceCoreReturn.getPersonId());
					
			// Do the actual work of the service.
			serviceReturn = runService(db, serviceName, LOG4J_LOGGER, serviceHelper, serviceCoreReturn, updatedBy, otherParameters);
			LOG4J_LOGGER.info(serviceName + ": Ran the service implementation.");

			// Log a success!  Add Person is special because it can return a failure due to matching and a success.
			if (! serviceName.equals(CprServiceName.AddPerson.toString())) {
				LOG4J_LOGGER.info(serviceName + ": Success!");
			}

			// Commit
			db.closeSession();
		}
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return handleException(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return handleException(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JSONException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return handleException(ReturnType.JSON_EXCEPTION.index(), e.getMessage());
		} 
		catch (JMSException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return handleException(ReturnType.JMS_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return handleException(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
		}
		catch (ParseException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return handleException(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());
		}
		catch (RuntimeException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return handleException(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());
		}

		calcRespAndElapTime(startTime);
		return serviceReturn;
	}
	
	/**
	 * @return the elapsedTime
	 */
	public long getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * @param elapsedTime the elapsedTime to set
	 */
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	/**
	 * @return the responseTimestamp
	 */
	public String getResponseTimestamp() {
		return responseTimestamp;
	}

	/**
	 * @param responseTimestamp the responseTimestamp to set
	 */
	public void setResponseTimestamp(String responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

	/**
	 * This method is used to calculate the response and elapsed times.
	 * @param startTime contains the start time of the process in milliseconds.
	 */
	private void calcRespAndElapTime(long startTime) {
		
		// Calculate elapsed time.
		final long endTime = System.currentTimeMillis();	
		setElapsedTime(endTime - startTime);
		
		// Calculate response time.
		setResponseTimestamp(Utility.formatDateToISO8601(new Date(endTime)));
	}
	
	public abstract Object runService(Database db, String serviceName, Logger log4jLogger, ServiceHelper serviceHelper, 
			ServiceCoreReturn serviceCoreReturn, String updatedBy, Map<String,Object> otherParameters) 
			throws CprException, JSONException, JMSException, ParseException;
	
	public abstract Object handleException(int statusCode, String statusMessage);


}
