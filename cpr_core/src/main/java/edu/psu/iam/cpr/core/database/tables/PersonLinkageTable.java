/* SVN FILE: $Id: PersonLinkageTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PersonLinkage;
import edu.psu.iam.cpr.core.database.types.LinkageType;
import edu.psu.iam.cpr.core.error.CprException;
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
	
	/** Contains the name of the database table that this implementation is being associated with */
	private static final String TABLE_NAME = "Person Linkage";
	
	private static final int LINKAGE_TYPE = 0;
	private static final int PERSON_ID = 1;
	private static final int LINKED_PERSON_ID = 2;
	private static final int START_DATE = 3;
	private static final int END_DATE = 4;
	private static final int LAST_UPDATE_BY = 5;
	private static final int LAST_UPDATE_ON = 6;
	private static final int CREATED_BY = 7;
	private static final int CREATED_ON = 8;
	
	private static final int BUFFER_SIZE = 1024;

	private static final String PERSON_ID_STRING = "person_id";
	private static final String DATA_TYPE_KEY_STRING = "data_type_key";

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
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public PersonLinkageTable(final long personId, final long linkedPersonId, final String linkageType, final String updatedBy) throws CprException {
		
		final PersonLinkage bean = new PersonLinkage();
		final Date d = new Date();
		
		setPersonLinkageBean(bean);
		
		setLinkageType(findLinkageTypeEnum(linkageType));
		
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
	public void setReturnHistoryFlag(final boolean returnHistoryFlag) {
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
	public final void setPersonLinkageBean(final PersonLinkage personLinkageBean) {
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
	public final void setLinkageType(final LinkageType linkageType) {
		this.linkageType = linkageType;
	}
	
	/**
	 * This routine will be used to convert a string to an enumerated type.
	 * @param linkageType the linkageType to set
	 * @return will return an enum if successful.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public final LinkageType findLinkageTypeEnum(final String linkageType) throws CprException {
		if (linkageType != null) {
			LinkageType linkageTypeEnum = Utility.getEnumFromString(LinkageType.class, linkageType);
			if (linkageTypeEnum != null) {
				return linkageTypeEnum;
			}
		}
		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Linkage Type");
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
	 */
	public void addPersonLinkage(final Database db) {
		
		final Session session = db.getSession();
		final PersonLinkage bean = getPersonLinkageBean();

		// Expire the existing relationship.
		final String sqlQuery = "from PersonLinkage where personId = :person_id AND dataTypeKey = :data_type_key";
		final Query query = session.createQuery(sqlQuery);
		query.setParameter(PERSON_ID_STRING, bean.getPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
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
	
	/**
	 * This routine is used to archive a person's linkage.
	 * @param db contains an open database connection.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public void archivePersonLinkage(final Database db) throws CprException {
		
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		
		final Session session = db.getSession();
		final PersonLinkage bean = getPersonLinkageBean();

		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT person_id FROM {h-schema}person_linkage WHERE person_id = :person_id AND ");
		sb.append("linked_person_id = :linked_person_id AND ");
		sb.append("data_type_key = :data_type_key ");
		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter(PERSON_ID_STRING, bean.getPersonId());
		query.setParameter("linked_person_id", bean.getLinkedPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		query.addScalar(PERSON_ID_STRING, StandardBasicTypes.LONG);
		if (query.list().size() == 0) {
			recordNotFound = true;
		}
		else {
			sb.delete(0, sb.length());
			sb.append("from PersonLinkage WHERE personId = :person_id AND linkedPersonId = :linked_person_id AND ");
			sb.append("dataTypeKey = :data_type_key AND endDate IS NULL");
			final Query query1 = session.createQuery(sb.toString());
			query1.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query1.setParameter("linked_person_id", bean.getLinkedPersonId());
			query1.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
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
		
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
		if (alreadyArchived) {
            throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);		}	
	}
	
	/**
	 * This routine is use to obtain person linkage information for a person in the registry.
	 * @param db contains an open database connection.
	 * @param personId contains the person identifier to perform the query for.
	 * @return will return a PersonLinkageReturn array if successful, otherwise it will return null.
	 */
	public PersonLinkageReturn[] getPersonLinkage(final Database db, final long personId) {
		
		final List<PersonLinkageReturn> results = new ArrayList<PersonLinkageReturn>();
		final Session session = db.getSession();

		// Build the query string.
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT data_type_key, person_id, linked_person_id, ");
		sb.append("start_date, ");
		sb.append("end_date,  ");
		sb.append("last_update_by, ");
		sb.append("last_update_on, ");
		sb.append("created_by, ");
		sb.append("created_on ");
		sb.append("FROM {h-schema}person_linkage ");
		sb.append("WHERE person_id = :person_id_in ");

		// If we are not returning all records, we need to just return the active ones.
		if (! isReturnHistoryFlag()) {
			sb.append("AND end_date IS NULL ");
		}

		sb.append("ORDER BY data_type_key ASC, start_date ASC ");

		// Set up hibernate for the query, bind parameters and determine return types.
		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("person_id_in", personId);
		query.addScalar(DATA_TYPE_KEY_STRING, StandardBasicTypes.LONG);
		query.addScalar(PERSON_ID_STRING, StandardBasicTypes.LONG);
		query.addScalar("linked_person_id", StandardBasicTypes.LONG);
		query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("last_update_by", StandardBasicTypes.STRING);
		query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
		query.addScalar("created_by", StandardBasicTypes.STRING);
		query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);

		// Perform the query.
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Object[] res = (Object []) it.next();
			PersonLinkageReturn personLinkageReturn = new PersonLinkageReturn(
					LinkageType.get((Long) res[LINKAGE_TYPE]).toString(),			
					(Long) res[PERSON_ID],										
					(Long) res[LINKED_PERSON_ID],										
					Utility.formatDateToISO8601((Date) res[START_DATE]),	
					Utility.formatDateToISO8601((Date) res[END_DATE]),	
					(String) res[LAST_UPDATE_BY],									
					Utility.formatDateToISO8601((Date) res[LAST_UPDATE_ON]),
					(String) res[CREATED_BY],									
					Utility.formatDateToISO8601((Date) res[CREATED_ON]));	
			results.add(personLinkageReturn);

		}

		return results.toArray(new PersonLinkageReturn[results.size()]);
	}
}
