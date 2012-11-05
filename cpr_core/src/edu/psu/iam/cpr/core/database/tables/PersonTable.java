/* SVN FILE: $Id: PersonTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.Date;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Person;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * 
 * This class contains methods for interfacing with the person database table.  The person
 * table contains all of the unique person identifiers in the CPR.  This class provides
 * for implementations to add, archive, unarchive, and get person information.
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
public class PersonTable {
	
	/** Contains the database table name that this implementation is associated with */
	private static final String TABLE_NAME = "Person";
	
	/** Contains the person database table bean */
	private Person personBean;
		
	/**
	 * Constructor.
	 */
	public PersonTable() {
		super();
	}
	
	/**
	 * Constructor
	 * @param personId person identifier.
	 * @param updatedBy contains the person who is updating the record.
	 */
	public PersonTable(long personId, String updatedBy) {
		super();

		final Person bean = new Person();
		final Date d = new Date();
		setPersonBean(bean);
		
		bean.setPersonId(personId);

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
	}
	
	/**
	 * Constructor
	 * @param updatedBy contains the person who last updated the record.
	 */
	public PersonTable(String updatedBy) {
		this(-1L, updatedBy);
	}
	
	/**
	 * @param personBean the personBean to set
	 */
	public final void setPersonBean(Person personBean) {
		this.personBean = personBean;
	}

	/**
	 * @return the personBean
	 */
	public Person getPersonBean() {
		return personBean;
	}

	/**
	 * This routine is used to add a new person identifier to the CPR.
	 * @param db contains an open database connection.
	 * @throws CprException 
	 */
	public void addPerson(final Database db) throws CprException {
			
		final Session session = db.getSession();
		final Person bean = getPersonBean();

		session.save(bean);
		session.flush();

		final PsuDirectoryTable psuDirectoryTable = new PsuDirectoryTable(bean.getPersonId(), bean.getLastUpdateBy());
		psuDirectoryTable.addDirectoryTable(db);

	}
	
	/**
	 * This routine is used to archive a person in the central person registry.
	 * @param db contains an open database connection.
	 * @throws CprException 
	 */
	public void archivePerson(Database db) throws CprException {
		
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		
		final Session session = db.getSession();
		final Person bean = getPersonBean();

		// Determine if the person exists?
		String sqlQuery = "from Person where personId = :person_id";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("person_id", bean.getPersonId());
		Iterator<?> it = query.list().iterator();
		if (it.hasNext()) {

			// Determine if the person is active or not.
			sqlQuery = "from Person where personId = :person_id AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			it = query.list().iterator();

			// Person is active, expire them.
			if (it.hasNext()) {
				Person dbBean = (Person) it.next();
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
		else {
			recordNotFound = true;
		}
		
		// Catch the errors.
		if (recordNotFound) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);
		}
		
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);
		}
	}
	
	/**
	 * This routine is used to un-archive a person in the central person registry.
	 * @param db contains an open database connection.
	 * @throws CprException 
	 */
	public void unarchivePerson(Database db) throws CprException {
		boolean recordNotFound = false;
		boolean alreadyUnarchived = false;
		
		final Session session = db.getSession();
		final Person bean = getPersonBean();

		// Determine if the person exists?
		String sqlQuery = "from Person where personId = :person_id";
		Query query = session.createQuery(sqlQuery);
		query.setParameter("person_id", bean.getPersonId());
		Iterator<?> it = query.list().iterator();
		if (it.hasNext()) {

			// Determine if the person is active or not.
			sqlQuery = "from Person where personId = :person_id AND endDate IS NOT NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			it = query.list().iterator();

			// Person is active, expire them.
			if (it.hasNext()) {
				Person dbBean = (Person) it.next();
				dbBean.setEndDate(null);
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				session.update(dbBean);
				session.flush();
			}
			else {
				alreadyUnarchived = true;
			}
		}
		else {
			recordNotFound = true;
		}
		
		// Catch the errors.
		if (recordNotFound) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);
		}
		
		if (alreadyUnarchived) {
			throw new CprException(ReturnType.UNARCHIVE_FAILED_EXCEPTION, TABLE_NAME);
		}
	}
}
