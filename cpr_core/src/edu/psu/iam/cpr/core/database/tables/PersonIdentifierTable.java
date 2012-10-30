/* SVN FILE: $Id: PersonIdentifierTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.beans.PersonIdentifier;
import edu.psu.iam.cpr.core.database.helpers.DBTypesHelper;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PersonIdentifierReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for interfacing with the person identifier
 * database table.
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
public class PersonIdentifierTable {
	
	private static final int TYPE_KEY = 0;

	private static final int IDENTIFER_VALUE = 1;

	private static final int START_DATE = 2;

	private static final int END_DATE = 3;

	private static final int LAST_UPDATE_BY = 4;

	private static final int LAST_UPDATE_ON = 5;

	private static final int CREATED_BY = 6;

	private static final int CREATED_ON = 7;

	/** Contains the person identifier bean */
	private PersonIdentifier personIdentifierBean;
	
	/** Contains the return history flag */
	private boolean returnHistoryFlag;
	
	/** Contains the identifier that would be used as part of a GET */
	private IdentifierType identifierType;
	
	/**
	 * Default contructor.
	 */
	public PersonIdentifierTable() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param personId contains the person identifier.
	 * @param identifierValue contains the identifier value.
	 * @param updatedBy contains the user that last updated the record.
	 */
	public PersonIdentifierTable(long personId, String identifierValue, String updatedBy) {

		super();
		Date d = new Date();
		PersonIdentifier bean = new PersonIdentifier();
		
		bean.setPersonId(personId);
		bean.setTypeKey(null); 
		bean.setIdentifierValue(identifierValue);
		
		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		setPersonIdentifierBean(bean);
	}
	
	/**
	 * Constructor.
	 * @param personId contains the person identifier.
	 * @param updatedBy contains the user that last updated the record.
	 */
	public PersonIdentifierTable(long personId, String updatedBy) {
		this(personId, null, updatedBy);
	}

	/**
	 * @param personIdentifierBean the personIdentifierBean to set
	 */
	public final void setPersonIdentifierBean(PersonIdentifier personIdentifierBean) {
		this.personIdentifierBean = personIdentifierBean;
	}

	/**
	 * @return the personIdentifierBean
	 */
	public PersonIdentifier getPersonIdentifierBean() {
		return personIdentifierBean;
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
	
	/**
	 * This routine is used to add a new person identifier.  NOTE: if the identifier and the value already exist,
	 * and error will be thrown.
	 * @param db contains a open database connection.
	 * @throws CprException will be thrown if there are any CPR specific problems.
	 */
	public void addPersonIdentifier(Database db) throws CprException {
		
		boolean matchFound = false;
		try {
			final Session session = db.getSession();
			final PersonIdentifier bean = getPersonIdentifierBean();
			
			bean.setTypeKey(getIdentifierType().getTypeKey());
			
			String sqlQuery = null;
			Query query = null;
			
			sqlQuery = "from PersonIdentifier where personId = :person_id AND typeKey = :type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("type_key", bean.getTypeKey());
			
			for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
				PersonIdentifier dbBean = (PersonIdentifier) it.next();
				
				// Check to ensure that the fields are not already there.
				if (db.areStringFieldsEqual(dbBean.getIdentifierValue(), bean.getIdentifierValue())) {
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
				
				bean.setTypeKey(getIdentifierType().getTypeKey());
				session.save(bean);
				session.flush();
			}
		}
		catch (Exception e ) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "person identifier");
		}
		
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "Person identifier");
		}
	}
	
	/**
	 * This routine is used to archive a person identifier.
	 * @param db contains the database connection.
	 * @throws CprException will be thrown if there are any CPR specific exceptions.
	 */
	public void archivePersonIdentifier(Database db) throws CprException {
		
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		
		try {
			final Session session = db.getSession();
			final PersonIdentifier bean = getPersonIdentifierBean();
			
			bean.setTypeKey(getIdentifierType().getTypeKey());

			// Check to see if the record exists for a the user and the specified identifier type.
			Query query = null;
			String sqlQuery = null;
			sqlQuery = "from PersonIdentifier where personId = :person_id and typeKey = :type_key";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("type_key", bean.getTypeKey());
									
			if (query.list().size() > 0) {
				// Check to see if an active record exists for the user and specified identifier type.
				sqlQuery += " and endDate is NULL";

				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("type_key", bean.getTypeKey());
				
				final Iterator <?> it = query.list().iterator();
				if (it.hasNext()) {
					
					// Expire the active record.
					final PersonIdentifier dbBean = (PersonIdentifier) it.next();
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
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "person identifier");
		}
		
		// Handle the errors.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "person identifier");
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "person identifier");
		}

	}
	
	/**
	 * This routine is used to obtain all of the system identifiers for a person.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier to retrieve information for.
	 * @return will return a person identifier return array.
	 * @throws GeneralDatabaseException will be thrown if there are any problems.
	 */
	public PersonIdentifierReturn[] getPersonIdentifiersForPersonId(Database db, long personId) throws GeneralDatabaseException {
		try {
			final ArrayList<PersonIdentifierReturn> results = new ArrayList<PersonIdentifierReturn>();
			final Session session = db.getSession();
			
			// Build the query string.
			final StringBuilder sb = new StringBuilder(2048);
			sb.append("SELECT type_key, identifier_value, ");
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("FROM person_identifier ");
			sb.append("WHERE person_id = :person_id_in ");
			
			// If we are doing a query for a specific identifier type, we need to specify this clause.
			if (getIdentifierType() != null) {
				sb.append("AND type_key = :type_key_in ");
			}
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			
			sb.append("ORDER BY type_key ASC, start_date ASC ");

			// Set up hibernate for the query, bind parameters and determine return types.
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			
			if (getIdentifierType() != null) {
				query.setParameter("type_key_in", getIdentifierType().getTypeKey());
			}
			
			query.addScalar("type_key", StandardBasicTypes.LONG);
			query.addScalar("identifier_value", StandardBasicTypes.STRING);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);

			HashMap<String,Object> map = DBTypesHelper.INSTANCE.getTypeMaps(DBTypesHelper.IDENTIFIER_TYPE);
			
			// Perform the query.
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Object res[] = (Object []) it.next();
				PersonIdentifierReturn personIdentifierReturn = new PersonIdentifierReturn();

				// Need to find the matching identifier type object.
				Long typeKey = (Long) res[TYPE_KEY];
				for (Map.Entry<String,Object> entry : map.entrySet()) {
					IdentifierType idType = (IdentifierType) entry.getValue();
					if (idType.getTypeKey().equals(typeKey)) {
						personIdentifierReturn.setIdentifierTypeString(idType.getTypeName());
						break;
					}
				}
	
				personIdentifierReturn.setIdentifierValue((String) res[IDENTIFER_VALUE]);
				personIdentifierReturn.setStartDate(Utility.convertTimestampToString((Date) res[START_DATE]));
				personIdentifierReturn.setEndDate(Utility.convertTimestampToString((Date) res[END_DATE]));
				personIdentifierReturn.setLastUpdateBy((String) res[LAST_UPDATE_BY]);
				personIdentifierReturn.setLastUpdateOn(Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]));
				personIdentifierReturn.setCreatedBy((String) res[CREATED_BY]);
				personIdentifierReturn.setCreatedOn(Utility.convertTimestampToString((Date) res[CREATED_ON]));
				
				results.add(personIdentifierReturn);
			}
			
			// Check on the results.
			return results.toArray(new PersonIdentifierReturn[results.size()]);
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve identifier(s) for person identifier = " + personId);
		}
	}
}
