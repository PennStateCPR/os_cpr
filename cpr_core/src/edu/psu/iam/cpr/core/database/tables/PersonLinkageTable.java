/* SVN FILE: $Id: PersonLinkageTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PersonLinkage;
import edu.psu.iam.cpr.core.database.types.LinkageType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PersonLinkageReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for interfacing with the person_linkage database
 * table.  This table contains all of the people a person is linked to along with their
 * respective type.  Methods contained within the class allow for adding, updating and
 * getting person linkage information.
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
public class PersonLinkageTable {
	
	/** Contains the database bean */
	private PersonLinkage personLinkageBean;
	
	/** Contains the type of linkage */
	private LinkageType linkageType;
	
	/** Boolean flag that indicates whether history is supposed to be returned for a GET. */
	private boolean returnHistoryFlag;

	/**
	 * Constructor
	 */
	public PersonLinkageTable() {
		super();
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifier for the person doing the linking.
	 * @param linkedPersonId contains the person identifier for the person being linked.
	 * @param linkageType contains the type of linkage.
	 * @param updatedBy contains the userid of the person who updated the record.
	 * @throws Exception will be thrown if there are any problems.
	 */
	public PersonLinkageTable(long personId, long linkedPersonId, String linkageType, String updatedBy) throws Exception {
		
		final PersonLinkage bean = new PersonLinkage();
		final Date d = new Date();
		
		setPersonLinkageBean(bean);
		
		setLinkageType(linkageType);
		
		bean.setPersonId(personId);
		bean.setLinkedPersonId(linkedPersonId);
		bean.setDataTypeKey(getLinkageType().index());
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		
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
	 * @param personLinkageBean the personLinkageBean to set
	 */
	public void setPersonLinkageBean(PersonLinkage personLinkageBean) {
		this.personLinkageBean = personLinkageBean;
	}

	/**
	 * @return the personLinkageBean
	 */
	public PersonLinkage getPersonLinkageBean() {
		return personLinkageBean;
	}

	/**
	 * @param linkageType the linkageType to set
	 */
	public void setLinkageType(LinkageType linkageType) {
		this.linkageType = linkageType;
	}
	
	/**
	 * @param linkageType the linkageType to set
	 * @throws Exception
	 */
	public void setLinkageType(String linkageType) throws Exception {
		setLinkageType(LinkageType.valueOf(linkageType.trim().toUpperCase()));
	}

	/**
	 * @return the linkageType
	 */
	public LinkageType getLinkageType() {
		return linkageType;
	}
	
	/**
	 * This routine is used to add a person linkage to the database, if one exists for the particular type,
	 * it will expire it and add the new one.
	 * @param db contains an open database connection.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public void addPersonLinkage(Database db) throws CprException {
		
		try {
			
			final Session session = db.getSession();
			final PersonLinkage bean = getPersonLinkageBean();
			
			// Expire the existing relationship.
			final String sqlQuery = "from PersonLinkage where personId = :person_id AND dataTypeKey = :data_type_key";
			final Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				PersonLinkage dbBean = (PersonLinkage) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				session.update(dbBean);
				session.flush();
			}
			
			// Add the new relationship.
			session.save(bean);
			session.flush();
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "Person Linkage");
		}
		
	}
	
	/**
	 * This routine is used to archive a person's linkage.
	 * @param db contains an open database connection.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public void archivePersonLinkage(Database db) throws CprException {
		
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		
		try {
			final Session session = db.getSession();
			final PersonLinkage bean = getPersonLinkageBean();
			
			final StringBuilder sb = new StringBuilder(200);
			sb.append("SELECT person_id FROM person_linkage WHERE person_id = :person_id AND ");
			sb.append("linked_person_id = :linked_person_id AND ");
			sb.append("data_type_key = :data_type_key ");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("linked_person_id", bean.getLinkedPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			query.addScalar("person_id", StandardBasicTypes.LONG);
			if (query.list().size() == 0) {
				recordNotFound = true;
			}
			else {
				sb.delete(0, sb.length());
				sb.append("from PersonLinkage WHERE personId = :person_id AND linkedPersonId = :linked_person_id AND ");
				sb.append("dataTypeKey = :data_type_key AND endDate IS NULL");
				final Query query1 = session.createQuery(sb.toString());
				query1.setParameter("person_id", bean.getPersonId());
				query1.setParameter("linked_person_id", bean.getLinkedPersonId());
				query1.setParameter("data_type_key", bean.getDataTypeKey());
				final Iterator<?> it = query1.list().iterator();
				if (it.hasNext()) {
					PersonLinkage dbBean = (PersonLinkage) it.next();
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();					
				}
				else {
					alreadyArchived = true;
				}
			}
			
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "person linkage");
		}
		
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "person linkage");
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "person linkage");
		}	
	}
	
	/**
	 * This routine is use to obtain person linkage information for a person in the registry.
	 * @param db contains an open database connection.
	 * @param personId contains the person identifier to perform the query for.
	 * @return will return a PersonLinkageReturn array if successful, otherwise it will return null.
	 * @throws GeneralDatabaseException 
	 */
	public PersonLinkageReturn[] getPersonLinkage(Database db, long personId) throws GeneralDatabaseException {
		
		try {
			final ArrayList<PersonLinkageReturn> results = new ArrayList<PersonLinkageReturn>();
			final Session session = db.getSession();
			
			// Build the query string.
			final StringBuilder sb = new StringBuilder(2048);
			sb.append("SELECT data_type_key, person_id, linked_person_id, ");
			sb.append("start_date, ");
			sb.append("end_date,  ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("FROM person_linkage ");
			sb.append("WHERE person_id = :person_id_in ");
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			
			sb.append("ORDER BY data_type_key ASC, start_date ASC ");

			// Set up hibernate for the query, bind parameters and determine return types.
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("person_id", StandardBasicTypes.LONG);
			query.addScalar("linked_person_id", StandardBasicTypes.LONG);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);

			// Perform the query.
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Object res[] = (Object []) it.next();
				PersonLinkageReturn personLinkageReturn = new PersonLinkageReturn(
						LinkageType.get((Long) res[0]).toString(),			// Data type key
						(Long) res[1],										// Person identifier
						(Long) res[2],										// Linked person identifier
						Utility.convertTimestampToString((Date) res[3]),	// Start date
						Utility.convertTimestampToString((Date) res[4]),	// End date
						(String) res[5],									// last update by
						Utility.convertTimestampToString((Date) res[6]),	// last update on
						(String) res[7],									// created by
						Utility.convertTimestampToString((Date) res[8]));	// created on
				results.add(personLinkageReturn);
			
			}
			
			return results.toArray(new PersonLinkageReturn[results.size()]);

		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve linkage information for person_id=" + personId);
		}
	}
}
