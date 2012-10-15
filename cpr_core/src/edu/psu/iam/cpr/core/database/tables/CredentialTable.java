/* SVN FILE: $Id: CredentialTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Credential;
import edu.psu.iam.cpr.core.database.types.CredentialType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.CredentialReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for interfacing with the Credential database
 * table.  There are methods within here to add, update, and get an credential for a 
 * person in the CPR.
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
 * @package edu.psu.iam.cpr.core.database.tables
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */

public class CredentialTable {
	
	/** Contains an instance of the credentials database bean. */
	private Credential credentialBean;
	
	/** Contains the credentials type */
	private CredentialType credentialType;
	
	/** This flag will be true if all records are to be returned on a GET. */
	private boolean returnHistoryFlag;
	
	/**
	 * Constructor.
	 */
	public CredentialTable() {
		super();
	}

	/**
	 * Constructor.
	 * @param personId contains the person identifier to store in the bean.
	 * @param credentialTypeString contains the credential type as a string.
	 * @param requestedBy contains the user who requested the action.
	 */
	public CredentialTable(long personId, String credentialTypeString, String requestedBy) {
		this(personId, credentialTypeString, null, requestedBy);
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifier to store in the bean.
	 * @param credentialTypeString contains the credential type.
	 * @param credentialData contains the credential data.
	 * @param requestedBy contains the user who requested the action.
	 */
	public CredentialTable(long personId, String credentialTypeString, String credentialData, String requestedBy) {
		super();
		
		final Credential bean = new Credential();
		final Date d = new Date();
		
		setCredentialType(credentialTypeString);
		
		bean.setPersonId(personId);
		bean.setDataTypeKey(getCredentialType().index());
		bean.setCredentialData(credentialData);
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setLastUpdateBy(requestedBy);
		bean.setLastUpdateOn(d);
		
		bean.setCreatedBy(requestedBy);
		bean.setCreatedOn(d);
		
		setCredentialBean(bean);
	}
	
	/**
	 * @param returnHistoryFlag the returnHistoryFlag to set
	 */
	public void setReturnHistoryFlag(boolean returnHistoryFlag) {
		this.returnHistoryFlag = returnHistoryFlag;
	}

	/**
	 * @return the returnHistoryFlag
	 */
	public boolean isReturnHistoryFlag() {
		return returnHistoryFlag;
	}

	/**
	 * @return the credentialBean
	 */
	public Credential getCredentialBean() {
		return credentialBean;
	}

	/**
	 * @param credentialBean the credentialBean to set
	 */
	public void setCredentialBean(Credential credentialBean) {
		this.credentialBean = credentialBean;
	}

	/**
	 * @return the credentialType
	 */
	public CredentialType getCredentialType() {
		return credentialType;
	}

	/**
	 * @param credentialType the credentialType to set
	 */
	public void setCredentialType(CredentialType credentialType) {
		this.credentialType = credentialType;
	}
	
	/**
	 * @param credentialTypeString the credentialTypeString to set
	 */
	public void setCredentialType(String credentialTypeString){
		
		setCredentialType(CredentialType.valueOf(credentialTypeString.toUpperCase().trim()));
	}
	
	/**
	 * This routine is used to add a new credential to the database.
	 * @param db contains the database object.
	 * @throws CprException 
	 */
	public void addCredential(Database db) throws CprException {
		boolean matchFound = false;
		try {
			final Session session = db.getSession();
			final Credential bean = getCredentialBean();
			
			String sqlQuery = null;
			Query query = null;

			sqlQuery = "from Credential where personId = :person_id AND dataTypeKey = :data_type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());

			for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
				Credential dbBean = (Credential) it.next();
				
				// Check to ensure that the fields are not already there.
				if (db.areStringFieldsEqual(dbBean.getCredentialData(), bean.getCredentialData())) {
					matchFound = true;
				}
				
				// Otherwise, we can expire the record.
				else {
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
			}
			
			// If we did not find a match, we can add the record.
			if (! matchFound) {
				session.save(bean);
				session.flush();
			}
		}
		catch (Exception e ) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "credential");
		}
		
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "Credential");
		}
		
	}

	/**
	 * This routine is used to archive a credential from the database.
	 * @param db contains the database object.
	 * @throws CprException 
	 */
	public void archiveCredential(Database db) throws CprException {
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		
		try {
			final Session session = db.getSession();
			final Credential bean = getCredentialBean();
			
			// Check to see if the record exists for a the user and the specified credential type.
			Query query = null;
			String sqlQuery = null;

			sqlQuery = "from Credential where personId = :person_id and dataTypeKey = :data_type_key";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());

			if (query.list().size() > 0) {
				// Check to see if an active record exists for the user and specified credential type.
				sqlQuery += " and endDate is NULL";

				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());

				final Iterator <?> it = query.list().iterator();
				if (it.hasNext()) {
					
					// Expire the active record.
					final Credential dbBean = (Credential) it.next();
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					session.update(dbBean);
					session.flush();
				}
				else {
					alreadyArchived = true;
				}
			}
			else {
				recordNotFound = true;
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "credential");
		}
		
		// Handle the errors.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "credential");
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "credential");
		}
	
	}
	
	/**
	 * This routine is used to obtain credential information from the database.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier to retrieve information for.
	 * @return will return a CredentialReturn array.
	 * @throws GeneralDatabaseException
	 */
	public CredentialReturn[] getCredentialForPersonId(Database db, long personId) throws GeneralDatabaseException {
		try {
			final ArrayList<CredentialReturn> results = new ArrayList<CredentialReturn>();
			final Session session = db.getSession();
			
			// Build the query string.
			final StringBuilder sb = new StringBuilder(2048);
			sb.append("SELECT data_type_key, credential_data, ");
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("FROM credential ");
			sb.append("WHERE person_id = :person_id_in ");
			
			// If we are doing a query for a specific credential type, we need to specify this clause.
			if (getCredentialType() != null) {
				sb.append("AND data_type_key = :data_type_key_in ");
			}
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			
			sb.append("ORDER BY data_type_key ASC, start_date ASC ");

			// Set up hibernate for the query, bind parameters and determine return types.
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			
			if (getCredentialType() != null) {
				query.setParameter("data_type_key_in", getCredentialType().index());
			}
			
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("credential_data", StandardBasicTypes.STRING);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);

			// Perform the query.
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Object res[] = (Object []) it.next();
				CredentialReturn credentialReturn = new CredentialReturn();
				
				credentialReturn.setCredentialType(CredentialType.get((Long) res[0]).toString());			
				credentialReturn.setCredentialData((String) res[1]);
				credentialReturn.setStartDate(Utility.convertTimestampToString((Date) res[2]));
				credentialReturn.setEndDate(Utility.convertTimestampToString((Date) res[3]));
				credentialReturn.setLastUpdateBy((String) res[4]);
				credentialReturn.setLastUpdateOn(Utility.convertTimestampToString((Date) res[5]));
				credentialReturn.setCreatedBy((String) res[6]);
				credentialReturn.setCreatedOn(Utility.convertTimestampToString((Date) res[7]));
				
				results.add(credentialReturn);
			
			}
			
			// Check on the results.
			return results.toArray(new CredentialReturn[results.size()]);
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve credentials for person identifier = " + personId);
		}
	}
}
