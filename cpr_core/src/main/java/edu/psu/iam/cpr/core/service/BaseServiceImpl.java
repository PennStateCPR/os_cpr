package edu.psu.iam.cpr.core.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import javax.jms.JMSException;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.hibernate.JDBCException;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.service.helper.ServiceHelper;
import edu.psu.iam.cpr.core.util.Utility;
import edu.psu.iam.cpr.core.api.helper.ApiHelper;
import edu.psu.iam.cpr.core.api.returns.ServiceReturn;

public abstract class BaseServiceImpl {

	/** Contains the log4j Logger instance */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(BaseServiceImpl.class);
	
	/** Buffer size */
	private static final int BUFFER_SIZE = 4096;
	
	/** Elapsed Time (milliseconds) */
	private long elapsedTime;
	
	/** Contains the response timestamp */
	private String responseTimestamp;
	
	/** Contains the record key for an add, update and/or archive */
	private Long recordKey;
	
	/**
	 * This code provides an abstract template for the implementation of a non-GET service.
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
		
		ServiceCoreReturn serviceCoreReturn = null;
		final long startTime = System.currentTimeMillis();
		final ServiceCore serviceCore = new ServiceCore();
		final Database db = new Database();
		final ServiceHelper serviceHelper = new ServiceHelper();
		
		LOG4J_LOGGER.info(serviceName + ": Start of service.");
		try {
			
			final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);
			parameters.append("principalId=[").append(principalId).append("] ");
			parameters.append("updatedBy=[").append(updatedBy).append("] ");
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
			LOG4J_LOGGER.info(serviceName + ": Found person identifier = " + serviceCoreReturn.getPersonId());
			
			// Run the service.
			runService(serviceName, db, serviceCoreReturn, updatedBy, otherParameters);
			
			// Log a success!
			LOG4J_LOGGER.info(serviceName + ": SUCCESS!");
			
			// Commit
			db.closeSession();
		} 
		catch (CprException e) {
			final String errorMessage = serviceHelper.handleCprException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return (Object) new ServiceReturn(e.getReturnType().index(), errorMessage);
		}
		catch (NamingException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return (Object) new ServiceReturn(ReturnType.DIRECTORY_EXCEPTION.index(), e.getMessage());
		}
		catch (JDBCException e) {
			final String errorMessage = serviceHelper.handleJDBCException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return (Object) new ServiceReturn(ReturnType.GENERAL_DATABASE_EXCEPTION.index(), errorMessage);
		} 
		catch (JSONException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return (Object) new ServiceReturn(ReturnType.JSON_EXCEPTION.index(), e.getMessage());
		} 
		catch (JMSException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return (Object) new ServiceReturn(ReturnType.JMS_EXCEPTION.index(), e.getMessage());
		}
		catch (ParseException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return (Object) new ServiceReturn(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());
		}
		catch (RuntimeException e) {
			serviceHelper.handleOtherException(LOG4J_LOGGER, serviceCoreReturn, db, e);
			calcRespAndElapTime(startTime);
			return (Object) new ServiceReturn(ReturnType.GENERAL_EXCEPTION.index(), e.getMessage());
		}
		
		LOG4J_LOGGER.info(serviceName + ": End of service.");

		// Return the status to the caller.
		calcRespAndElapTime(startTime);
		return (Object) new ServiceReturn(ReturnType.SUCCESS.index(), ServiceHelper.SUCCESS_MESSAGE, getRecordKey());
	
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
	 * @return the recordKey
	 */
	public Long getRecordKey() {
		return recordKey;
	}

	/**
	 * @param recordKey the recordKey to set
	 */
	public void setRecordKey(Long recordKey) {
		this.recordKey = recordKey;
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
	
	public abstract void runService(String serviceName, Database db, ServiceCoreReturn serviceCoreReturn, 
					String updatedBy, Map<String,Object> otherParameters) throws CprException, JSONException, JMSException, ParseException;

	
}
