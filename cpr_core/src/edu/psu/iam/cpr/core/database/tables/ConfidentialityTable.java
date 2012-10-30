/* SVN FILE: $Id: ConfidentialityTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Confidentiality;
import edu.psu.iam.cpr.core.database.types.ConfidentialityType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.ConfidentialityReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for methods that manipulate the confidentiality
 * database table.  There are methods to add, update, archive and get confidentiality 
 * information.  This table uses the confidentiality database as the interface to the
 * table.
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
public class ConfidentialityTable {
	
	private static final int CONFIDENTIALITY_TYPE = 0;

	private static final int START_DATE = 1;

	private static final int END_DATE = 2;

	private static final int LAST_UPDATE_BY = 3;

	private static final int LAST_UPDATE_ON = 4;

	private static final int CREATED_BY = 5;

	private static final int CREATED_ON = 6;

	/** Confidentiality database table bean. */
	private Confidentiality confidentialityBean;
	
	/** Contains the confidentiality hold type. */
	private ConfidentialityType confidentialityType;
	
	/** Flag to indicate whether to return all history records */
	private boolean returnHistoryFlag;
	
	/**
	 * Constructor.
	 */
	public ConfidentialityTable() {
		super();
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifier who confidentiality is being added for.
	 * @param confidentialityTypeString contains the type of confidentiality being added.
	 * @param updatedBy contains the person who either updated the record or retrieved it.
	 * @throws Exception will be thrown if there are any problems.
	 */
	public ConfidentialityTable(long personId, String confidentialityTypeString, String updatedBy) throws Exception {
		super();
		
		final Confidentiality bean = new Confidentiality();
		final Date d = new Date();
		setConfidentialityType(confidentialityTypeString);
		setConfidentialityBean(bean);
		
		bean.setPersonId(personId);
		bean.setDataTypeKey(getConfidentialityType().index());
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
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
	 * @param confidentialityBean the confidentialityBean to set
	 */
	public final void setConfidentialityBean(Confidentiality confidentialityBean) {
		this.confidentialityBean = confidentialityBean;
	}

	/**
	 * @return the confidentialityBean
	 */
	public Confidentiality getConfidentialityBean() {
		return confidentialityBean;
	}

	/**
	 * @return the confidentialityType
	 */
	public ConfidentialityType getConfidentialityType() {
		return confidentialityType;
	}

	public final void setConfidentialityType(ConfidentialityType confidentialityType) {
		this.confidentialityType = confidentialityType;
	}
	/**
	 * This routine is used to take a confidentiality type string an attempt to convert it to an enumerated type.
	 * If there is a problem with the conversion, an exception is thrown.
	 * @param confidentialityTypeString contains the confidentiality type string value.
	 * @throws Exception will be thrown if there are any problems.
	 */
	public final void setConfidentialityType(String confidentialityTypeString) throws Exception {
		setConfidentialityType(ConfidentialityType.valueOf(confidentialityTypeString.toUpperCase().trim()));
	}

	/**
	 * This routine is used to add/update confidentiality holds.
	 * @param db contains a reference to a database class that contains a open database connection.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public void addConfidentiality(Database db) throws CprException {
		
		try {
			final Session session = db.getSession();
			final Confidentiality bean = getConfidentialityBean();
			
			// Expire existing confidentiality.
			final String sqlQuery = "from Confidentiality where personId = :person_id and dataTypeKey = :data_type_key and endDate IS NULL";
			final Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Confidentiality dbBean = (Confidentiality) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				session.update(dbBean);
				session.flush();
			}
			
			// Add a new hold.
			session.save(bean);
			session.flush();
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "confidentiality hold");
		}
	}
	
	/**
	 * This routine is used to archive a person's confidentiality hold.
	 * @param db contains a reference to a database class that contains a open database connection.
	 * @throws CprException will be thrown if there are CPR related exceptions.
	 */
	public void archiveConfidentiality(Database db) throws CprException {
		
		boolean recordNotFound = false;
		boolean holdNotActive = false;
		try {
			final Session session = db.getSession();
			final Confidentiality bean = getConfidentialityBean();
			
			// Do a query to see if a conf hold exists for the person.
			String sqlQuery = "from Confidentiality where personId = :person_id and dataTypeKey = :data_type_key";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			
			Iterator<?> it = query.list().iterator();
			
			if (it.hasNext()) {
				
				// Do a query to see if an active conf hold exists for the person.
				sqlQuery = "from Confidentiality where personId = :person_id and dataTypeKey = :data_type_key and endDate is NULL";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());
				
				it = query.list().iterator();
				
				// Active hold exists, so we expire it.
				if (it.hasNext()) {
					final Confidentiality dbBean = (Confidentiality) it.next();
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
				else {
					holdNotActive = true;
				}
			}
			else {
				recordNotFound = true;
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "confidentiality hold");
		}
		
		// Handle the error cases.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "confidentiality hold");
		}
		
		if (holdNotActive) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "confidentiality hold");
		}
	}
	
	/**
	 * This routine is used to obtain the confidentiality hold status for a user.
	 * @param db contains a reference to a database class that contains a open database connection.
	 * @param personId contains the person identifier whose confidentiality is to be obtained
	 * @return will return an array of confidentiality hold statuses.
	 * @throws GeneralDatabaseException 
	 */
	public ConfidentialityReturn[] getConfidentiality(Database db, long personId) throws GeneralDatabaseException {
		
		try {
			
			// Init some variables.
			final ArrayList<ConfidentialityReturn> results = new ArrayList<ConfidentialityReturn>();
			final Session session = db.getSession();
			final StringBuilder sb = new StringBuilder(3000);
			
			// Build the select statement as a string.
			sb.append("SELECT ");
		    sb.append("data_type_key, ");
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
		    sb.append("FROM confidentiality ");
		    sb.append("WHERE person_id = :person_id_in ");
		    
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			
			sb.append("ORDER BY data_type_key ASC, start_date ASC ");

		    
		    // Create the hibernate select statement.
		    final SQLQuery query = session.createSQLQuery(sb.toString());
		    query.setParameter("person_id_in", personId);
		    query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);
		    
		    for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
		    	
		    	// For each result, store its value in the return class.
		    	Object res[] = (Object []) it.next();
				
				ConfidentialityReturn c = new ConfidentialityReturn(ConfidentialityType.get((Long) res[CONFIDENTIALITY_TYPE]).toString(),
													Utility.convertTimestampToString((Date) res[START_DATE]), 	
													Utility.convertTimestampToString((Date) res[END_DATE]), 	
													(String) res[LAST_UPDATE_BY], 									
													Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]), 	
													(String) res[CREATED_BY], 									
													Utility.convertTimestampToString((Date) res[CREATED_ON])); 	
				results.add(c);
		    }
		    
		    return results.toArray(new ConfidentialityReturn[results.size()]);

	    }
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve confidentiality holds for person identifier = " + personId);
		}
	}
	
}
