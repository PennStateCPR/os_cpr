/* SVN FILE: $Id: PsuDirectoryTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PsuDirectory;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * 
 * This class provides an implementation for interfacing with the PSU directory database table.
 * This table contains a mapping of people records to entry in the directory.  There are 
 * methods contained within this class to add, update and get directory information.
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
public class PsuDirectoryTable {

	/** Directory bean class */
	private PsuDirectory psuDirectoryBean;
	
	/**
	 * Constructor
	 */
	public PsuDirectoryTable() {
		super();
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifier.
	 * @param userid contains the userid.
	 * @param updatedBy contains the last updated person.
	 */
	public PsuDirectoryTable(long personId, String userid, String updatedBy) {
		super();
		
		final PsuDirectory bean = new PsuDirectory();
		final Date d = new Date();
		setPsuDirectoryBean(bean);
		
		bean.setPersonId(personId);
		bean.setUserid(userid);
		
		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
	
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
	
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifier.
	 * @param updatedBy contains the user that updated this record.
	 */
	public PsuDirectoryTable(long personId, String updatedBy) {
		this(personId, null, updatedBy);
	}

	
	/**
	 * @param psuDirectoryBean the psuDirectoryBean to set
	 */
	public final void setPsuDirectoryBean(PsuDirectory psuDirectoryBean) {
		this.psuDirectoryBean = psuDirectoryBean;
	}

	/**
	 * @return the psuDirectoryBean
	 */
	public PsuDirectory getPsuDirectoryBean() {
		return psuDirectoryBean;
	}

	/**
	 * Add a record to the psu directory table.
	 * @param db contains a database connection.
	 * @throws GeneralDatabaseException will be thrown if there are any problems.
	 */
	public void addDirectoryTable(Database db) throws GeneralDatabaseException {
		
		boolean recordExists = false;
		try {
			final Session session = db.getSession();
			final PsuDirectory bean = getPsuDirectoryBean();		
			
			// If the userid is NULL, we only need to check and see if the person exists.
			if (bean.getUserid() == null) {
				// Check to see if the record already exists.
				final String sqlQuery = "from PsuDirectory where personId = :person_id";
				final Query query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				final Iterator<?> it = query.list().iterator();
				if (it.hasNext()) {
					recordExists = true;
				}
			}
			
			// If the userid is not null, we need to check and see if the person and the userid exist.
			else {
				// Check to see if the record already exists.
				final String sqlQuery = "from PsuDirectory where personId = :person_id and userid = :userid";
				final Query query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("userid", bean.getUserid());
				final Iterator<?> it = query.list().iterator();
				if (it.hasNext()) {
					recordExists = true;
				}
			}
			
			boolean recordUpdated = false;
			
			// If the record does not exist, we can continue.
			if (! recordExists) {
				
				// Check to see if a userid was specified.
				if (bean.getUserid() != null) {
					
					// If we have a userid, attempt to update a psu directory record that contains a NULL userid.  There 
					// will only be one of these.
					final String sqlQuery = "from PsuDirectory where personId = :person_id_in and userid is NULL";
					final Query query = session.createQuery(sqlQuery);
					query.setParameter("person_id_in", bean.getPersonId());
					final Iterator<?> it = query.list().iterator();
					if (it.hasNext()) {
						PsuDirectory dbBean = (PsuDirectory) it.next();
						dbBean.setUserid(bean.getUserid());
						dbBean.setLastUpdateBy(bean.getLastUpdateBy());
						dbBean.setLastUpdateOn(bean.getLastUpdateOn());
						session.update(dbBean);
						session.flush();
						recordUpdated = true;
					}
				}
				
				// If we did not update the record, we need to add a new one.
				if (! recordUpdated) {

					// Save off the new record.
					session.save(bean);
					session.flush();
				}
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to store a record in the psu directory table.");
		}
		
		if (recordExists) {
			throw new GeneralDatabaseException("A record already exists in the PSU Directory table for this user.");
		}
	}

	/**
	 * This routine is used to populate the directory table class with information from the PSU_DIRECTORY table for a specific person identifier.
	 * @param db contains the database connection
	 * @param personId contains the person identifier.
	 * @throws PersonNotFoundException
	 * @throws GeneralDatabaseException
	 * @throws CprException 
	 */
	public void getPsuDirectoryTable(Database db, Long personId) throws GeneralDatabaseException, CprException {

		boolean found = false;
		try {
			final Session session = db.getSession();
			
			final StringBuilder sb = new StringBuilder(200);
			sb.append("SELECT userid, psu_directory_key ");
			sb.append("FROM psu_directory ");
			sb.append("WHERE person_id = :person_id_in ");
			sb.append("AND end_date IS NULL ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("userid", StandardBasicTypes.STRING);
			query.addScalar("psu_directory_key", StandardBasicTypes.LONG);
			
			final Iterator<?> it = query.list().iterator();
			
			if (it.hasNext()) {
				Object res[] = (Object []) it.next();

				final PsuDirectory bean = new PsuDirectory();
				bean.setPersonId(personId);
				bean.setUserid((String) res[0]);
				bean.setPsuDirectoryKey((Long) res[1]);
				setPsuDirectoryBean(bean);
				found = true;
			}
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve information for person identifier = " + personId);
		}
		
		if (! found) {
			throw new CprException(ReturnType.PERSON_NOT_FOUND_EXCEPTION);
		}
	}
	
	/**
	 * Will be used to clean up database information for unit testing.
	 * @param db
	 */
	public void junitCleanUp(Database db) {
		try {
			final Session session = db.getSession();
			final PsuDirectory bean = getPsuDirectoryBean();
			
			Query query = null;
			if (bean.getUserid() != null) {
				final String sqlQuery = "from PsuDirectory where personId = :person_id and userid = :userid";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("userid", bean.getUserid());
			}
			else {
				final String sqlQuery = "from PsuDirectory where personId = :person_id";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
			}
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				PsuDirectory dbBean = (PsuDirectory) it.next();
				session.delete(dbBean);
				session.flush();
			}
		}
		catch (Exception e) {
		}
	}
}
