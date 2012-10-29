/* SVN FILE: $Id: Database.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * Database is a utility class that will facility the opening and closing of database connections
 * to the database pool that maintained in the broker.  In addition it provides helper methods
 * that are used to obtain person identifiers from the CPR.
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
 * @package edu.psu.iam.cpr.core.database
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class Database {

	/** Person identifier string */
	public static final String PERSON_ID_IDENTIFIER = "PERSON_ID";
	
	/** Userid identifier string */
	public static final String USERID_IDENTIFIER 	= "USERID";
	
	/** Social security number identifier string */
	public static final String SSN_IDENTIFIER 		= "SSN";
	
	/** PSU ID identifier string */
	public static final String PSU_ID_IDENTIFIER 	= "PSU_ID";
	
	/** ID Card string */
	public static final String ID_CARD_IDENTIFIER 	= "ID_CARD";
	
	/** Instance of logger */
	private static final Logger Log4jLogger = Logger.getLogger(Database.class);
	
	/**
	 * Value that represents something that is not found.
	 */
	public final long NOT_FOUND_VALUE = -1L;

	/** 
	 * Contains a table's columns.
	 */
	private HashMap<String, TableColumn> tableColumns = null;
	
	/**
	 * Contains an open hibernate session.
	 */
	private Session session = null;
	
	/**
	 * Contains the identifier type.
	 */
	private IdentifierType identifierType = null;
	
	
	/**
	 * openSession routine attempts to obtain a database connection from the ESB connection
	 * pool.
	 * @param sessionFactory hibernate session factory.
	 * @throws GeneralDatabaseException 
	 */
	public void openSession(SessionFactory sessionFactory) throws GeneralDatabaseException {
		
		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to obtain a database connection from the connection pool.");
		}
	}
	
	/**
	 * closeSession routine will attempt to close an open database connection freeing
	 * up a resource in the connection pool.
	 * 
	 */
	public void closeSession() {
		try {
			getSession().getTransaction().commit();
		}
		catch (Exception e) {
		}
		finally {
			resetSession();
		}
	}
	
	/**
	 * This routine is used to rollback a session.
	 */
	public void rollbackSession() {
		try {
			getSession().getTransaction().rollback();
		}
		catch (Exception e) {
		}
		finally { 
			resetSession();
		}
	}

	/**
	 * This routine will reset a hibernate session value to its initial value of null.
	 */
	public void resetSession() {
		session = null;
	}

	/**
	 * This routine will return a boolean flag to indicate whether a hibernate session is open or not.
	 * @return will return true if the connection is open, otherwise it will return false.
	 */
	public boolean isSessionOpen() {
		return (getSession() != null) ? true : false;
	}
	
	/**
	 * getSession will return the open database connection.
	 * @return returns the open database connection.
	 */
	public Session getSession() {
		
		return session;
	}
	
	/**
	 * This routine will determine if a particular server principal is authorized to call a service.
	 * @param principalId contains the requestor's principal identifier.
	 * @param serviceName contains the name of the service.
	 * @param requestor contains the userid of the person requesting access.
	 * @param serviceCoreReturn contains the service core return class.
	 * @throws CprException exception indicates a cpr specific java exception.
	 * @throws GeneralDatabaseException exception indicates a general database exception.
	 * @return ServiceCoreReturn will be returned to the caller.
	 */
	public ServiceCoreReturn requestorAuthorized(String principalId, String requestor, String serviceName, ServiceCoreReturn serviceCoreReturn) throws CprException, GeneralDatabaseException {

		Long registrationAuthorityKey = NOT_FOUND_VALUE;
		Long iamGroupKey = NOT_FOUND_VALUE;
		
		String suspendFlag = "Y";
		String grpmbrsSuspendFlag = "Y";
		String iamgrpsSuspendFlag = "Y";
		String grpaccSuspendFlag = "Y";

		// Determine what RA a person is associated with.
		try {
			// Build the query.
			final StringBuilder sb = new StringBuilder(400);
			sb.append("SELECT ra.registration_authority_key, ra.suspend_flag ");
			sb.append("FROM registration_authority ra JOIN ra_server_principals rasrvrprinc ");
			sb.append("ON ra.registration_authority_key = rasrvrprinc.registration_authority_key ");
			sb.append("WHERE rasrvrprinc.ra_server_principal = :ra_server_principal_in ");
			sb.append("AND ra.end_date IS NULL ");
			sb.append("AND rasrvrprinc.end_date IS NULL");
			
			// Create the query, bind the input parameters and determine the output parameters.
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("ra_server_principal_in", principalId);
			query.addScalar("registration_authority_key", StandardBasicTypes.LONG);
			query.addScalar("suspend_flag", StandardBasicTypes.STRING);

			// See if a record is found, if so get its data.
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				registrationAuthorityKey = (Long) res[0];
				suspendFlag = (String) res[1];
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
	
		// Is the RA suspended?
		if (registrationAuthorityKey == NOT_FOUND_VALUE ||
				suspendFlag.equals("Y")) {
			throw new CprException(ReturnType.NOT_AUTHORIZED_EXCEPTION, serviceName);
		}

		// Determine the user's status and group for the particular RA.
		try {
			
			// Build the query.
			final StringBuilder sb = new StringBuilder(300);
			sb.append("SELECT iam_group_key, grpmbrs_suspend_flag, iamgrps_suspend_flag, grpacc_suspend_flag ");
		    sb.append("FROM v_ra_group_web_service ");
		    sb.append("WHERE registration_authority_key = :l_ra_key ");
		    sb.append("AND web_service = :web_service_in ");
		    sb.append("AND userid = :requested_by_in");
		    
		    // Create the query, bind the parameters and determine the returns.
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("l_ra_key", registrationAuthorityKey);
			query.setParameter("web_service_in", serviceName);
			query.setParameter("requested_by_in", requestor);
			query.addScalar("iam_group_key", StandardBasicTypes.LONG);
			query.addScalar("grpmbrs_suspend_flag", StandardBasicTypes.STRING);
			query.addScalar("iamgrps_suspend_flag", StandardBasicTypes.STRING);
			query.addScalar("grpacc_suspend_flag", StandardBasicTypes.STRING);

			// Perform the query.
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				iamGroupKey = (Long) res[0];
				grpmbrsSuspendFlag = (String) res[1];
				iamgrpsSuspendFlag = (String) res[2];
				grpaccSuspendFlag = (String) res[3];
			}
	
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());			
		}
	
		// If any of the suspend flags is set to Yes, we need to throw an exception.
		if (iamGroupKey == NOT_FOUND_VALUE ||
				grpmbrsSuspendFlag.equals("Y") ||
				iamgrpsSuspendFlag.equals("Y") ||
				grpaccSuspendFlag.equals("Y")) {
			throw new CprException(ReturnType.NOT_AUTHORIZED_EXCEPTION, serviceName);
		}
	
		serviceCoreReturn.setIamGroupKey(iamGroupKey);
		serviceCoreReturn.setRegistrationAuthorityKey(registrationAuthorityKey);
		
		return serviceCoreReturn;
		
	}



	/**
	 * This routine is used to verify that the requester is allowed to perform an operation on a particular data type.
	 * This routine will return true if the operation is allowed, otherwise it will throw an exception.
	 * @param serviceCoreReturn contains the service core return class. 
	 * @param dataResource contains the data source that is being checked.
	 * @param action contains the action that is being checked.
	 * @param requestedBy contains the access id of the perform who requested this operation.
	 * @return will return true if successful.
	 * @throws CprException will be thrown if the access is denied.
	 * @throws GeneralDatabaseException  will be thrown if there are any database exceptions.
	 */
	public boolean isDataActionAuthorized(ServiceCoreReturn serviceCoreReturn, String dataResource, String action, String requestedBy) throws CprException, GeneralDatabaseException {

		// Verify that the operation being checked is against a valid data key.
		final Long dataTypeKey = AccessType.valueOf(dataResource.toUpperCase().trim()).index();
		final Long accessOperationKey = AccessType.valueOf(action.toUpperCase().trim()).index();
		boolean dataKeyValid = false;
		try {
			
			// Build the query.
			final StringBuilder sb = new StringBuilder(128);
	        sb.append("SELECT data_types.data_type_key ");
	        sb.append("FROM data_types ");
	        sb.append("WHERE data_types.data_type_key = :data_type_key_in ");
	        sb.append("AND data_types.active_flag = 'Y' ");

	        // Create the query, bind the parameters and set the return type.
	        final SQLQuery query = session.createSQLQuery(sb.toString());
	        query.setParameter("data_type_key_in", dataTypeKey);
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				dataKeyValid = true;
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());				
		}
		if (! dataKeyValid) {
			throw new CprException(ReturnType.DATA_CHANGE_EXCEPTION,dataResource);
		}
		
		// Do the query to determine if they have access.
		String readFlag = "N";
		String writeFlag = "N";
		String archiveFlag ="N";
		try {
			final StringBuilder sb = new StringBuilder(300);
	        sb.append("SELECT v_group_data_type_access.read_flag, v_group_data_type_access.write_flag, ");
    		sb.append("v_group_data_type_access.archive_flag ");
    		sb.append("FROM v_group_data_type_access ");
    		sb.append("WHERE v_group_data_type_access.iam_group_key = :iam_group_key_in ");
    		sb.append("AND v_group_data_type_access.data_type_key = :data_type_key_in");

	        // Create the query, bind the parameters and set the return type.
	        final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("iam_group_key_in", serviceCoreReturn.getIamGroupKey());
			query.setParameter("data_type_key_in", dataTypeKey);
			query.addScalar("read_flag", StandardBasicTypes.STRING);
			query.addScalar("write_flag", StandardBasicTypes.STRING);
			query.addScalar("archive_flag", StandardBasicTypes.STRING);
			
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				readFlag = (String) res[0];
				writeFlag = (String) res[1];
				archiveFlag = (String) res[2];
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());							
		}
		
		boolean hasAccess = false;
		if (accessOperationKey == AccessType.ACCESS_OPERATION_ARCHIVE.index()) {
			hasAccess = archiveFlag.equals("Y");
		}
		else if (accessOperationKey == AccessType.ACCESS_OPERATION_READ.index()) {
			hasAccess = readFlag.equals("Y");
		}
		else if (accessOperationKey == AccessType.ACCESS_OPERATION_WRITE.index()) {
			hasAccess = writeFlag.equals("Y");
		}
		
		if (! hasAccess) {
			throw new CprException(ReturnType.DATA_CHANGE_EXCEPTION, AccessType.get(dataTypeKey).toString());
		}
		
		return hasAccess;
	}
	/**
	 * This routine is used to verify that the requester is allowed to perform an operation on a particular data type.
	 * This routine will return true if the operation is allowed, otherwise it will throw an exception.
	 * @param iamGroupKey contains the iam group key which indicates which group the user is a member of. 
	 * @param dataTypeKey contains the data type key associated with the data element.
	 * @param accessOperationKey contains the access operation key which indicates the type of operation.
	 * @param requestedBy contains the access id of the perform who requested this operation.
	 * @return will return true if successful.
	 * @throws CprException will be thrown if the access is denied.
	 * @throws GeneralDatabaseException  will be thrown if there are any database exceptions.
	 */
	public boolean isDataActionAuthorizedOldCode(long iamGroupKey, long dataTypeKey, long accessOperationKey, String requestedBy) throws CprException, GeneralDatabaseException {

		// Verify that the operation being checked is against a valid data key.
		boolean dataKeyValid = false;
		try {
			
			// Build the query.
			final StringBuilder sb = new StringBuilder(128);
	        sb.append("SELECT data_types.data_type_key ");
	        sb.append("FROM data_types ");
	        sb.append("WHERE data_types.data_type_key = :data_type_key_in ");
	        sb.append("AND data_types.active_flag = 'Y' ");

	        // Create the query, bind the parameters and set the return type.
	        final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("data_type_key_in", dataTypeKey);
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				dataKeyValid = true;
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());				
		}
		if (! dataKeyValid) {
			throw new CprException(ReturnType.DATA_CHANGE_EXCEPTION, AccessType.get(dataTypeKey).toString());
		}
		
		// Do the query to determine if they have access.
		String readFlag = "N";
		String writeFlag = "N";
		String archiveFlag ="N";
		try {
			final StringBuilder sb = new StringBuilder(300);
	        sb.append("SELECT v_group_data_type_access.read_flag, v_group_data_type_access.write_flag, ");
    		sb.append("v_group_data_type_access.archive_flag ");
    		sb.append("FROM v_group_data_type_access ");
    		sb.append("WHERE v_group_data_type_access.iam_group_key = :iam_group_key_in ");
    		sb.append("AND v_group_data_type_access.data_type_key = :data_type_key_in");

	        // Create the query, bind the parameters and set the return type.
	        final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("iam_group_key_in", iamGroupKey);
			query.setParameter("data_type_key_in", dataTypeKey);
			query.addScalar("read_flag", StandardBasicTypes.STRING);
			query.addScalar("write_flag", StandardBasicTypes.STRING);
			query.addScalar("archive_flag", StandardBasicTypes.STRING);
			
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				readFlag = (String) res[0];
				writeFlag = (String) res[1];
				archiveFlag = (String) res[2];
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());							
		}
		
		boolean hasAccess = false;
		if (accessOperationKey == AccessType.ACCESS_OPERATION_ARCHIVE.index()) {
			hasAccess = archiveFlag.equals("Y");
		}
		else if (accessOperationKey == AccessType.ACCESS_OPERATION_READ.index()) {
			hasAccess = readFlag.equals("Y");
		}
		else if (accessOperationKey == AccessType.ACCESS_OPERATION_WRITE.index()) {
			hasAccess = writeFlag.equals("Y");
		}
		
		if (! hasAccess) {
			throw new CprException(ReturnType.DATA_CHANGE_EXCEPTION, AccessType.get(dataTypeKey).toString());
		}
		
		return hasAccess;
	}
	/**
	 * This routine is used to determine if an RA is authorize to assign an affiliation.
	 * @param serviceCoreReturn contains the serviceCoreReturn object
	 * @param affiliationType - contains the affiliation
	 * @param requestedBy - userid of the requestor
	 * 
	 * @return true if ra is authorized for affiliation
	 * 
	 * @throws AffiliationUseException
	 * @throws CprException
	 * @throws GeneralDatabaseException 
	 */
	public boolean isAffiliationAccessAuthorized(ServiceCoreReturn serviceCoreReturn, String affiliationType, String requestedBy)  throws CprException, GeneralDatabaseException {

		final Long affiliationKey = AffiliationsType.valueOf(affiliationType.toUpperCase().trim()).index();
		boolean affiliationKeyValid = false;
		final StringBuilder sb = new StringBuilder(128);
		try {
			
			// Build the query.
			
	        sb.append("SELECT affiliations.affiliation_key ");
	        sb.append("FROM affiliations ");
	        sb.append("WHERE affiliations.affiliation_key = :affiliation_key_in ");
	        sb.append("AND affiliations.active_flag = 'Y' ");

	        // Create the query, bind the parameters and set the return type.
	        final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("affiliation_key_in", affiliationKey);
			query.addScalar("affiliation_key", StandardBasicTypes.LONG);
			
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				affiliationKeyValid = true;
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());				
		}
		if (! affiliationKeyValid) {
			throw new CprException(ReturnType.DATA_CHANGE_EXCEPTION, AffiliationsType.get(affiliationKey).toString());
		}
		try {
			sb.setLength(0);
	  		sb.append("select * FROM ra_affiliation ");
    		sb.append("WHERE affiliation_key = :affiliation_key_in ");
    		sb.append("AND registration_authority_key= :ra_type_key_in ");
    		sb.append("AND end_date is null ");
    		 // Create the query, bind the parameters and set the return type.
	        final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("affiliation_key_in", affiliationKey);
			query.setParameter("ra_type_key_in", serviceCoreReturn.getRegistrationAuthorityKey());
						
			final Iterator<?> it = query.list().iterator();
			if ( !it.hasNext()) {
				affiliationKeyValid = false;
			}		


		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());				
		}
		if (! affiliationKeyValid) {
			throw new CprException(ReturnType.DATA_CHANGE_EXCEPTION, AffiliationsType.get(affiliationKey).toString());
		}
		return affiliationKeyValid;
	}
	
	/**
	 * This routine is used to obtain a person identifier using a psu id number.
	 * @param psuId contains the psu id number.
	 * @return person id if the psu id can be found, otherwise it will return a -1 to indicate an error.
	 * @throws GeneralDatabaseException exception indicates that a general database exception was encountered.
	 * @throws CprException exception indicates a cpr specific java exception.
	 */
	public long getPersonIdUsingPsuId(String psuId) throws GeneralDatabaseException, CprException  {
		
		Long personId = NOT_FOUND_VALUE;
		try {
			final String sqlQuery = "SELECT person_id FROM psu_id WHERE psu_id = :psuid AND end_date IS NULL";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("psuid", psuId);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personId = (Long) it.next();
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personId.equals(NOT_FOUND_VALUE)) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);		
		}
		
		return personId;
		
	}
	
	/**
	 * This routine is used to obtain a person identifier using a userid.
	 * @param userid contains the userid to be used in the search.
	 * @return person id if the userid can be found, otherwise it will return a -1 to indicate an error.
	 * @throws GeneralDatabaseException exception indicates that a general database exception was encountered.
	 * @throws CprException exception indicates a cpr specific java exception.
	 */
	public long getPersonIdUsingUserid(String userid) throws GeneralDatabaseException, CprException  {
		
		Long personId = NOT_FOUND_VALUE;
		try {
			final String sqlQuery = "SELECT person_id FROM userid WHERE userid = :userid";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("userid", userid);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personId = (Long) it.next();
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personId.equals(NOT_FOUND_VALUE)) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);		
		}
		
		return personId;
	}
	
	/**
	 * This routine is used to find a person using their id card number.
	 * @param idCard contains the id card that is used to be search for.
	 * @return will return the person identifier if a user was found with the correct id.
	 * @throws GeneralDatabaseException will be thrown if there are any general database errors.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	public long getPersonIdUsingIdCard(String idCard) throws GeneralDatabaseException, CprException  {
		
		Long personId = NOT_FOUND_VALUE;
		try {
			final String sqlQuery = "SELECT person_id FROM person_id_card WHERE id_card_number = :idcard AND end_date IS NULL";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("idcard", idCard);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personId = (Long) it.next();
		}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personId.equals(NOT_FOUND_VALUE)) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);		
		}
		
		return personId;
	}
	
	/**
	 * This routine is used to determine whether a userid/person id is valid and whether the userid is still active.
	 * @param personId contains the person identifier to do the query for.
	 * @param userid contains the userid to do the query for.
	 * @return will return true if valid, otherwise it will return false.
	 */
	public boolean isValidUserid(Long personId, String userid) {

		boolean found = false;
		try {
			final String sqlQuery = "SELECT person_id FROM userid WHERE userid = :userid AND person_id = :person_id AND end_date IS NULL";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("person_id", personId);
			query.setParameter("userid", userid);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			found =  (query.list().size() == 0) ? false : true;
		}
		catch (Exception e) {
			found = false;
		}
		return found;
	}
	
	/**
	 * This routine is used to determine if a person with a person identifier exists in the CPR or not.  
	 * @param personId contains the person identifier to do a search for.
	 * @return returns the person identifier found.
	 * @throws CprException exception indicates a cpr specific java exception.
	 * @throws GeneralDatabaseException exception indicates that a general database error was encountered.
	 */
	public long getPersonIdUsingPersonId(long personId) throws CprException, GeneralDatabaseException {
		
		Long personIdOut = NOT_FOUND_VALUE;
		try {
			final String sqlQuery = "SELECT person_id FROM person WHERE person_id = :personid";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("personid", personId);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personIdOut = (Long) it.next();
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personIdOut.equals(NOT_FOUND_VALUE)) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);		
		}
		
		return personId;
	}
	
	/**
	 * This routine is used to obtain a person identifier using a SOR identifier.
	 * @param identifierType contains the identifier type.
	 * @param identifier contains the identifier value.
	 * @return will return the person identifier or throw an exception.
	 * @throws GeneralDatabaseException will be thrown if there is a database problem exception.
	 * @throws CprException will be thrown if there is a CPR specific problem.
	 */
	public long getPersonIdUsingPersonIdentifier(IdentifierType identifierType, String identifier) throws GeneralDatabaseException, CprException {
		
		Long personId = NOT_FOUND_VALUE;
		try {
			final StringBuilder sb = new StringBuilder(1024);
			sb.append("SELECT person_id FROM person_identifier WHERE type_key = :type_key ");
			sb.append("AND identifier_value = :identifier_value ");
			sb.append("AND end_date IS NULL");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("type_key", identifierType.getTypeKey());
			query.setParameter("identifier_value", identifier);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personId = (Long) it.next();
		}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personId.equals(NOT_FOUND_VALUE)) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);		
		}
		
		return personId;
	}
	
	/**
	 * The purpose of this routine is to accept an identifier and a IdentifierType and obtain from the database the person's
	 * Person Identifier.
	 * @param identifierType contains the type of identifier being used to do the query.
	 * @param identifier contains the identifier.
	 * @return will either return the Central Person Registry's person identifier corresponding to the identifier or a -1 to indicate an error.
	 * @throws CprException exception indicates a cpr specific java exception.
	 * @throws GeneralDatabaseException exception indicates that a general database error was encountered.
	 */
	public long getPersonIdUsingIdentifier(IdentifierType identifierType, String identifier) throws GeneralDatabaseException, CprException {
		
		long personId = NOT_FOUND_VALUE;
		
		if (identifier == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Identifier");
		}
		
		String typeName = identifierType.getTypeName();
		if (typeName.equals(PERSON_ID_IDENTIFIER)) {
			try {
				personId = Long.parseLong(identifier.trim());
			}
			catch (NumberFormatException e) {
				throw new CprException(ReturnType.GENERAL_EXCEPTION, "Invalid person identifier was specified");
			}
			personId = getPersonIdUsingPersonId(personId);
		}
		
		else if (typeName.equals(PSU_ID_IDENTIFIER)) {
			personId = getPersonIdUsingPsuId(identifier.trim());
		}
		
		else if (typeName.equals(USERID_IDENTIFIER)) {
			personId = getPersonIdUsingUserid(identifier.trim());
		}
		
		else if (typeName.equals(ID_CARD_IDENTIFIER)) {
			personId = getPersonIdUsingIdCard(identifier.trim());
		}
		
		else {
			personId = getPersonIdUsingPersonIdentifier(identifierType, identifier);
		}
		
		return personId;
	}
	

	/**
	 * This routine will called a stored function to determine if a person is active in the CPR or not.
	 * @param personId person identifier from the Central Person Registry.
	 * @return true if the person is active.
	 * @throws GeneralDatabaseException exception indicates that a general database error was encountered.
	 * @throws CprException exception indicates a cpr specific java exception.
	 */
	public boolean isPersonActive(long personId) throws GeneralDatabaseException, CprException {
		
		long personIdOut = NOT_FOUND_VALUE;
		try {
			final String sqlQuery = "SELECT person_id FROM person WHERE person_id = :personid AND end_date IS NULL";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("personid", personId);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personIdOut = (Long) it.next();
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personId != personIdOut) {
			throw new CprException(ReturnType.PERSON_NOT_ACTIVE_EXCEPTION);			
		}
		
		return true;
	}
	
	/**
	 * doesPsuIdExist accepts a single parameter the psuId and checks to see if it exists in the CPR.
	 * @param psuId contains the psu id to check for existence.
	 * @throws CprException exception indicates a cpr specific java exception.
	 * @throws GeneralDatabaseException exception indicates that a general database error happened.
	 */
	public void doesPsuIdExist(String psuId) throws CprException, GeneralDatabaseException {

		Long personId = NOT_FOUND_VALUE;
		try {
			final String sqlQuery = "SELECT person_id FROM psu_id WHERE psu_id = :psuid AND end_date IS NULL";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("psuid", psuId);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personId = (Long) it.next();
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personId.equals(NOT_FOUND_VALUE)) {
			throw new CprException(ReturnType.PSUID_NOT_FOUND_EXCEPTION);			
		}
	}
	
	/**
	 * This routine will verify whether a userid exists in the CPR or not.
	 * @param userid input userid to be checked for existence.
	 * @throws CprException exception indicates a cpr specific java exception.
	 * @throws GeneralDatabaseException exception thrown indicates that a general database failure was encountered.
	 */
	public void doesUseridExist(String userid) throws CprException, GeneralDatabaseException {
		
		Long personId = NOT_FOUND_VALUE;
		try {
			final String sqlQuery = "SELECT person_id FROM userid WHERE userid = :userid";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("userid", userid);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				personId = (Long) it.next();
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException(e.getMessage());
		}
		
		if (personId.equals(NOT_FOUND_VALUE)) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "userid");
		}
	}
	
	/**
	 * For a particular identifier type and identifier, this routine will attempt to retrieve the correct person id from the registry.
	 * @param idType contains the string representation of the identifier type.
	 * @param identifier contains the identifier to be searched for.
	 * @return contains the person identifier if found.
	 * @throws CprException exception indicates a cpr specific java exception.
	 * @throws GeneralDatabaseException  exception will be thrown if a general database failure was encountered.
	 */
	public long getPersonId(String idType, String identifier) throws CprException, GeneralDatabaseException {
		
		long personId = NOT_FOUND_VALUE;

		Log4jLogger.info("Start of get Person");
		if (idType == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Identifier type");
		}
		
		if (identifier == null) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Identifier");
		}
			
		// Validate the identifierType.
		final IdentifierType localIdentifierType = Validate.isValidIdentifierType(this, idType);
		if (localIdentifierType == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Identifier type");
		}
		
		// Save off the identifier type.
		setIdentifierType(localIdentifierType);
		
		// Validate the length of the identifier type.
		Validate.isIdentifierLengthValid(this, localIdentifierType.getTypeName(), identifier);
		
		try {
			personId = getPersonIdUsingIdentifier(localIdentifierType, identifier);
		}
		catch (Exception e) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);
		}
			
		Log4jLogger.info("End of get Person");
		return personId;
	}
	
	/**
	 * This is a private routine that is used to obtain the tableColumns hashmap
	 * @return will return the hashmap.
	 */
	public  HashMap<String, TableColumn> getTableColumns() {
		return tableColumns;
	}
	
	/**
	 * This routine is used to obtain all of the database columns metadata information for a particular table within the CPR.
	 * @param tableName contains the table name that is useds to obtain data for.
	 * @throws GeneralDatabaseException exception that is thrown for any problems.
	 * @throws CprException exception indicates a cpr specific java exception.
	 */
	public  void getAllTableColumns(final String tableName) throws GeneralDatabaseException, CprException {
		
		if (tableName == null || tableName.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Table name");
		}
		
		try {
			session.doWork(new Work() {
				public void execute(Connection conn) throws SQLException {
					final ResultSet rs = conn.getMetaData().getColumns(null, 
											CprProperties.getInstance().getProperties().getProperty(CprPropertyName.CPR_DATABASE_NAME.toString()), 
											tableName.toLowerCase(), "_%");
					tableColumns = new HashMap<String, TableColumn>();
					while (rs.next()) {
						try {
							tableColumns.put(rs.getString("column_name").toUpperCase(), new TableColumn(rs.getString("column_name").toUpperCase(), rs.getShort("data_type"), rs.getInt("column_size"), rs.getInt("decimal_digits"), rs.getInt("nullable")));		
						}
						catch (Exception e) {
						}
					}
					try {
						rs.close();
					}
					catch (Exception e) {
					}
				}
			});
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to obtain metadata for database table = " + tableName);
		}
	}
	
	/**
	 * This routine is used to obtain column data for a particular column.
	 * @param columnName contains the column name to obtain data for.
	 * @return will return a TableColumn object if successful.
	 * @throws GeneralDatabaseException 
	 * @throws CprException exception indicates a cpr specific java exception.
	 */
	public  TableColumn getColumn(String columnName) throws CprException, GeneralDatabaseException {
		
		if (columnName == null || columnName.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Column name");
		}

		try {
			final TableColumn tableColumn = getTableColumns().get(columnName.toUpperCase());
			if (tableColumn == null) {
				throw new GeneralDatabaseException("Unable to find database column in metadata.");
			}
			return tableColumn;
		}
		catch (NullPointerException e) {
			throw new GeneralDatabaseException("Unable to find database column in metadata.");
		}
	}
	
	/**
	 * This routine is used to determine if a database field already equals another field.
	 * @param s1 contains the first field to test.
	 * @param s2 contains the second field to test.
	 * @return will return true if the field are equal, otherwise it will return false.
	 */
	public boolean areStringFieldsEqual(String s1, String s2) {
		
		try {
			final String tmp_s1 = (s1 == null || s1.trim().length() == 0) ? "NULL" : s1.trim();
			final String tmp_s2 = (s2 == null || s2.trim().length() == 0) ? "NULL" : s2.trim();

			return tmp_s1.equals(tmp_s2);
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * This routine is used to determine if a database field already equals another field.
	 * @param parm1 contains the first field to test.
	 * @param parm2 contains the second field to test.
	 * @return will return true if the field are equal, otherwise it will return false.
	 */
	public boolean areLongFieldsEqual(Long parm1, Long parm2) {
		
		try {
			if (parm1 == null && parm2 == null) {
				return true;
			}
			else if ((parm1 == null && parm2 != null) ||
					 (parm1 != null && parm2 == null)) {
				return false;
			}
			else if (parm1 != null && parm2 == null) {
				return false;
			}
			else {
				return parm1.equals(parm2);
			}
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * @param identifierType the identifierType to set
	 */
	public void setIdentifierType(IdentifierType identifierType) {
		this.identifierType = identifierType;
	}

	/**
	 * @return the identifierType
	 */
	public IdentifierType getIdentifierType() {
		return identifierType;
	}
}
