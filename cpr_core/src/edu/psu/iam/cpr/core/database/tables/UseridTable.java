/* SVN FILE: $Id: UseridTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.helpers.UseridHelper;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.UseridReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for interfacing with the userid database 
 * table.  This table contains a mapping of all of the userids to people within the 
 * CPR.
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
public class UseridTable {
	
	/** Contains the name of the database table for this implementation */
	private static final String TABLE_NAME = "Userid";
	
	/** Contains a reference to the userid bean */
	private Userid useridBean;
	
	/** Contains the set id returned from the userid package */
	private int setId;
	
	/** For a GET indicates whether to return history or not */
	private boolean returnHistoryFlag;
	
	/** Contains a reference to the userid helper */
	private UseridHelper useridHelper;
	
	private static final int USERID 		= 0;
	private static final int PRIMARY_FLAG 	= 1;
	private static final int START_DATE 	= 2;
	private static final int END_DATE 		= 3;
	private static final int LAST_UPDATE_BY = 4;
	private static final int LAST_UPDATE_ON = 5;
	private static final int CREATED_BY		= 6;
	private static final int CREATED_ON 	= 7;

	private static final int BUFFER_SIZE = 1024;

	
	/**
	 * Constructor.
	 * @param personId contains the person identifier in the CPR.
	 * @param updatedBy contains the user/system identifier that last updated the record.
	 */
	public UseridTable(long personId, String updatedBy) {
		super();
		final Userid bean = new Userid();
		final Date d = new Date();
		setUseridBean(bean);
		
		bean.setPersonId(personId);
		bean.setUserid(null);
		bean.setCharPart(null);
		bean.setNumPart(null);
		
		bean.setDisplayNameFlag("Y");
		bean.setPrimaryFlag(null);
		
		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		setUseridHelper(new UseridHelper());
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
	 * @param setId the setId to set
	 */
	public void setSetId(int setId) {
		this.setId = setId;
	}

	/**
	 * @return the setId
	 */
	public int getSetId() {
		return setId;
	}

	/**
	 * @param useridBean the useridBean to set
	 */
	public final void setUseridBean(Userid useridBean) {
		this.useridBean = useridBean;
	}

	/**
	 * @return the useridBean
	 */
	public Userid getUseridBean() {
		return useridBean;
	}

	/**
	 * @param useridHelper the useridHelper to set
	 */
	public final void setUseridHelper(UseridHelper useridHelper) {
		this.useridHelper = useridHelper;
	}

	/**
	 * @return the useridHelper
	 */
	public UseridHelper getUseridHelper() {
		return useridHelper;
	}

	/**
	 * Default constructor.
	 */
	public UseridTable() {
		super();
	}

	/**
	 * This routine is to call a stored procedure to add a new userid for a user.
	 * @param db contains a database connection.
	 * @throws CprException 
	 * 
	 */
	public void addUserid(Database db) throws CprException {
		
		final Session session = db.getSession();
		GeneratedIdentityTable generatedIdentityTable = null;
		try {
			final Userid bean = getUseridBean();

			// Obtain a userid from the pool, check to see if there was a failure.
			getUseridHelper().generateUserid(session, bean);

			// Obtain the character part and number part.
			final String charPart = getCharacterPart(bean.getUserid());
			bean.setCharPart(charPart);
			bean.setNumPart(getNumberPart(bean.getUserid(), charPart));
			
			generatedIdentityTable = new GeneratedIdentityTable(bean.getPersonId(), bean.getUserid(), bean.getCharPart(), bean.getNumPart(), 
												bean.getLastUpdateBy());
			generatedIdentityTable.addGeneratedIdentity(session);

			// Do a select to determine what primary needs to be set to.
			final String sqlQuery = "SELECT person_id FROM {h-schema}userid WHERE person_id = :person_id_in AND end_date IS NULL";
			final SQLQuery query = session.createSQLQuery(sqlQuery);
			query.setParameter("person_id_in", bean.getPersonId());
			query.addScalar("person_id", StandardBasicTypes.LONG);
			if (query.list().size() == 0) {
				bean.setPrimaryFlag("Y");
			}
			else {
				bean.setDisplayNameFlag("N");
				bean.setPrimaryFlag("N");
			}

			// Save off the new userid record.
			session.save(bean);
			session.flush();

			// Add a record to the psu directory table.
			final PsuDirectoryTable psuDirectoryTable = new PsuDirectoryTable(bean.getPersonId(), bean.getUserid(), bean.getLastUpdateBy());
			psuDirectoryTable.addDirectoryTable(db);

		}
		finally {
			try {
				generatedIdentityTable.removeGeneratedIdentity(session);
			}
			catch (Exception e) {
			}
		}
	}
	
	/**
	 * This routine is to call a stored procedure to set the primary userid for a user.
	 * @param db contains a reference to a open database connection.
	 * @throws CprException  will be thrown if there are any CPR related errors.
	 * 
	 */
	public void setPrimaryUserid(Database db) throws  CprException {
		
		boolean recordExpired = false;
		boolean alreadyPrimary = false;
		boolean recordNotFound = false;
		
		final Session session = db.getSession();
		final Userid bean = getUseridBean();

		// For the selected userid, obtain the end date and their primary flag.
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT end_date, primary_flag ");
		sb.append("FROM {h-schema}userid ");
		sb.append("WHERE person_id = :person_id_in ");
		sb.append("AND userid = :userid_in ");
		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("person_id_in", bean.getPersonId());
		query.setParameter("userid_in", bean.getUserid());
		query.addScalar("end_date", StandardBasicTypes.DATE);
		query.addScalar("primary_flag", StandardBasicTypes.STRING);
		Iterator<?> it = query.list().iterator();

		if (it.hasNext()) {
			Object res[] = (Object []) it.next();
			bean.setEndDate((Date) res[0]);
			bean.setPrimaryFlag((String) res[1]);

			// Expired, we have an error.
			if (bean.getEndDate() != null) {
				recordExpired = true;
			}

			// Already primary, we have an error.
			else if (bean.getPrimaryFlag().equals("Y")) {
				alreadyPrimary = true;
			}
			else {

				// Switch the current primary record.
				String sqlQuery = "from Userid where personId = :person_id_in AND primaryFlag = 'Y' AND endDate IS NULL";
				Query query1 = session.createQuery(sqlQuery);
				query1.setParameter("person_id_in", bean.getPersonId());
				for (it = query1.list().iterator(); it.hasNext();) {
					Userid dbBean = (Userid) it.next();

					dbBean.setPrimaryFlag("N");
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}

				// Make the new record primary.
				sqlQuery = "from Userid where personId = :person_id_in AND userid = :userid_in AND endDate IS NULL";
				query1 = session.createQuery(sqlQuery);
				query1.setParameter("person_id_in", bean.getPersonId());
				query1.setParameter("userid_in", bean.getUserid());
				for (it = query1.list().iterator(); it.hasNext(); ) {
					Userid dbBean = (Userid) it.next();
					dbBean.setPrimaryFlag("Y");
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
			}
		}
		else {
			recordNotFound = true;
		}	
		
		// Handle other errors.
		if (recordExpired) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);
		}
		if (alreadyPrimary) {
			throw new CprException(ReturnType.SET_PRIMARY_FAILED_EXCEPTION, TABLE_NAME);
		}
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
	}
	
	/**
	 * This routine is used to archive a userid.  It is called by the ArchiveUserid service.
	 * @param db contains a reference to an open database connection.
	 * @throws CprException will be thrown for any CPR specific problems.
	 */
	public void archiveUserid(Database db) throws CprException {
		
		boolean noneActive = false;
		boolean notFound = false;
		boolean alreadyArchived = false;
		boolean cannotArchive = false;
		
		final Session session = db.getSession();
		final Userid bean = getUseridBean();

		// Determine how many userids are active for the current user.
		String sqlQuery = "SELECT person_id FROM {h-schema}userid WHERE person_id = :person_id_in AND end_date IS NULL";
		SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setParameter("person_id_in", bean.getPersonId());
		query.addScalar("person_id", StandardBasicTypes.LONG);
		final int activeCount = query.list().size();
		if (activeCount == 0) {
			noneActive = true;
		}
		else {

			// For the selected userid, obtain the end date and their primary flag.
			final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			sb.append("SELECT end_date, primary_flag ");
			sb.append("FROM {h-schema}userid ");
			sb.append("WHERE person_id = :person_id_in ");
			sb.append("AND userid = :userid_in ");
			query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", bean.getPersonId());
			query.setParameter("userid_in", bean.getUserid());
			query.addScalar("end_date", StandardBasicTypes.DATE);
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
			Iterator<?> it = query.list().iterator();

			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				bean.setEndDate((Date) res[0]);
				bean.setPrimaryFlag((String) res[1]);

				// Error if the record already has an end date.
				if (bean.getEndDate() != null) {
					alreadyArchived = true;
				}

				// If there are more than one record and this one is primary, do not all the archival.
				else if (activeCount > 1 && bean.getPrimaryFlag().equals("Y")) {
					cannotArchive = true;
				}

				// Otherwise we can do the archive.
				else {
					sqlQuery = "from Userid where personId = :person_id_in AND userid = :userid_in AND endDate IS NULL";
					final Query query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id_in", bean.getPersonId());
					query1.setParameter("userid_in", bean.getUserid());
					for (it = query1.list().iterator(); it.hasNext(); ) {
						Userid dbBean = (Userid) it.next();
						dbBean.setPrimaryFlag("N");
						dbBean.setEndDate(bean.getLastUpdateOn());
						dbBean.setLastUpdateBy(bean.getLastUpdateBy());
						dbBean.setLastUpdateOn(bean.getLastUpdateOn());
						session.update(dbBean);
						session.flush();
					}
				}
			}
			else {
				notFound = true;
			}

		}
		
		if (notFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
		if (noneActive) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Cannot archive userid, because there are no active userids.");
		}
		if (alreadyArchived)  {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);
		}
		if (cannotArchive) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Cannot archive userid, because its the primary userid.");
		}
		
	}
	
	/**
	 * This routine is used to unarchive a userid.  It is called by the UnarchiveUserid service.
	 * @param db contains a reference to an open database connection.
	 * @throws CprException will be thrown for any CPR specific problems.
	 */
	public void unarchiveUserid(Database db) throws CprException {
		
		boolean alreadyUnarchived = false;
		boolean noArchivedRecords = false;
		boolean recordNotFound = false;
		
		final Session session = db.getSession();
		final Userid bean = getUseridBean();

		// See how any userids are archived for the user, if there are none that are archived, we have an error.
		String sqlQuery = "SELECT person_id FROM {h-schema}userid WHERE person_id = :person_id_in AND end_date IS NOT NULL";
		SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setParameter("person_id_in", bean.getPersonId());
		query.addScalar("person_id", StandardBasicTypes.LONG);
		final int archivedCount = query.list().size();

		if (archivedCount == 0) {
			noArchivedRecords = true;
		}
		else {

			// For the selected userid, obtain the end date and their primary flag.
			final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			sb.append("SELECT end_date, primary_flag ");
			sb.append("FROM {h-schema}userid ");
			sb.append("WHERE person_id = :person_id_in ");
			sb.append("AND userid = :userid_in ");
			query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", bean.getPersonId());
			query.setParameter("userid_in", bean.getUserid());
			query.addScalar("end_date", StandardBasicTypes.DATE);
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
			Iterator<?> it = query.list().iterator();

			if (it.hasNext()) {
				Object res[] = (Object []) it.next();
				bean.setEndDate((Date) res[0]);
				bean.setPrimaryFlag((String) res[1]);

				if (bean.getEndDate() == null) {
					alreadyUnarchived = true;
				}
				else {
					// Determine how many userids are active for the current user.
					sqlQuery = "SELECT person_id FROM {h-schema}userid WHERE person_id = :person_id_in AND end_date IS NULL";
					query = session.createSQLQuery(sqlQuery);
					query.setParameter("person_id_in", bean.getPersonId());
					query.addScalar("person_id", StandardBasicTypes.LONG);
					final int activeCount = query.list().size();

					if (activeCount == 0) {
						bean.setPrimaryFlag("Y");
					}
					else {
						bean.setPrimaryFlag("N");
					}

					// Do the unarchive.
					sqlQuery = "from Userid where personId = :person_id AND userid = :userid_in AND endDate IS NOT NULL";
					final Query query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id", bean.getPersonId());
					query1.setParameter("userid_in", bean.getUserid());
					for (it = query1.list().iterator(); it.hasNext(); ) {
						Userid dbBean = (Userid) it.next();
						dbBean.setPrimaryFlag(bean.getPrimaryFlag());
						dbBean.setEndDate(null);
						dbBean.setLastUpdateBy(bean.getLastUpdateBy());
						dbBean.setLastUpdateOn(bean.getLastUpdateOn());
						session.update(dbBean);
						session.flush();
					}
				}

			}
			else {
				recordNotFound = true;
			}
		}
		
		if (alreadyUnarchived) {
			throw new CprException(ReturnType.UNARCHIVE_FAILED_EXCEPTION, "userid");
		}
		
		if (noArchivedRecords) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "There are no records that can be unarchived.");
		}
		
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "userid");
		}
	}
	
	/**
	 * This routine is used to add a special userid.  It is called by the AddSpecialUserid service.
	 * @param db contains a reference to an open database connection.
	 * @throws CprException will be thrown for any CPR specific problems.
	 */
	public void addSpecialUserid(Database db) throws CprException {
		
		// Verify that the new userid contains valid characters.
		if (! isUseridValid(db, getUseridBean().getUserid())) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, TABLE_NAME);
		}
		
		final Session session = db.getSession();
		final Userid bean = getUseridBean();

		// Fill in the char and number parts of the userid.
		final String charPart = getCharacterPart(bean.getUserid());
		bean.setCharPart(charPart);
		bean.setNumPart(getNumberPart(bean.getUserid(), charPart));

		// Do a select to determine what primary needs to be set to.
		final String sqlQuery = "SELECT person_id FROM {h-schema}userid WHERE person_id = :person_id_in AND end_date IS NULL";
		final SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setParameter("person_id_in", bean.getPersonId());
		query.addScalar("person_id", StandardBasicTypes.LONG);
		if (query.list().size() == 0) {
			bean.setPrimaryFlag("Y");
		}
		else {
			bean.setDisplayNameFlag("N");
			bean.setPrimaryFlag("N");
		}

		// Save off the new userid record.
		session.save(bean);
		session.flush();

		// Add a record to the psu directory table.
		final PsuDirectoryTable psuDirectoryTable = new PsuDirectoryTable(bean.getPersonId(), bean.getUserid(), bean.getLastUpdateBy());
		psuDirectoryTable.addDirectoryTable(db);
	}
	
	/**
	 * This routine will obtain a list of userids for a person id.
	 * @param db contains an open database connection.
	 * @param personId contains the person id.
	 * @return list of userids.
	 */
	public UseridReturn[] getUseridsForPersonId(Database db, long personId) {
		
		final Session session = db.getSession();
		final ArrayList<UseridReturn> results = new ArrayList<UseridReturn>();

		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT userid, primary_flag, ");
		sb.append("start_date, ");
		sb.append("end_date, ");
		sb.append("last_update_by, ");
		sb.append("last_update_on, ");
		sb.append("created_by, ");
		sb.append("created_on ");
		sb.append("FROM {h-schema}userid ");
		sb.append("WHERE person_id = :person_id_in ");
		if (! isReturnHistoryFlag()) {
			sb.append("AND end_date IS NULL ");
		}
		sb.append("ORDER BY start_date");

		final SQLQuery query = session.createSQLQuery(sb.toString());

		query.setParameter("person_id_in", personId);

		query.addScalar("userid", StandardBasicTypes.STRING);
		query.addScalar("primary_flag", StandardBasicTypes.STRING);
		query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("last_update_by", StandardBasicTypes.STRING);
		query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
		query.addScalar("created_by", StandardBasicTypes.STRING);
		query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);


		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Object res[] = (Object []) it.next();
			results.add(new UseridReturn((String) res[USERID], 							
					(String) res[PRIMARY_FLAG],										
					Utility.convertTimestampToString((Date) res[START_DATE]),		
					Utility.convertTimestampToString((Date) res[END_DATE]),		
					(String) res[LAST_UPDATE_BY],										
					Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]),		
					(String) res[CREATED_BY],										
					Utility.convertTimestampToString((Date) res[CREATED_ON])));		
		}

		return results.toArray(new UseridReturn[results.size()]);
	}
	
	/**
	 * This routine is used to determine if the passed in userid is valid.
	 * @param db contains a reference to the database handle.
	 * @param userid contains the userid to valid.
	 * @return will return true if the userid is valid, otherwise it will return false.
	 */
	public boolean isUseridValid(Database db, String userid) {
		
		final Session session = db.getSession();
		// Verify that the userid does not contain spaces.
		if (userid.contains(" ")) {
			return false;
		}
		// Verify that the userid only contains letters, numbers, $ and underscore.
		if (! userid.matches("^[a-zA-Z0-9$_]+$")) {
			return false;
		}

		// Obtain the character portion of the userid.
		final String charPart = getCharacterPart(userid);

		// Verify that the userid does not exist in the bad prefixes table.
		String sqlQuery = "SELECT char_part FROM {h-schema}bad_prefixes WHERE char_part = :char_part_in";
		SQLQuery query = session.createSQLQuery(sqlQuery);
		query.setParameter("char_part_in", charPart);
		query.addScalar("char_part", StandardBasicTypes.STRING);
		if (query.list().size() > 0) {
			return false;
		}

		// Verify that the userid does not already exist.
		sqlQuery = "SELECT person_id FROM {h-schema}userid WHERE userid = :userid_in";
		query = session.createSQLQuery(sqlQuery);
		query.setParameter("userid_in", userid);
		query.addScalar("person_id", StandardBasicTypes.LONG);
		if (query.list().size() > 0) {
			return false;
		}

		return true;
	}
	
	/**
	 * This routine is used to extract the character porition of a userid.
	 * @param s contains the userid.
	 * @return will return the character porition of the userid or null if there are errors.
	 */
	public String getCharacterPart(String s) {
		
		try {
			if (s == null || s.length() == 0) {
				return null;
			}
			final char c[] = s.toCharArray();
			final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			for (int i = 0; i < c.length; ++i) {
				if ((c[i] >= 'a' && c[i] <= 'z') ||
						c[i] == '$' ||
						c[i] == '_') {
					sb.append(c[i]);
				}
				else {
					break;
				}
			}
			
			return sb.toString();
		}
		catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Get the numeric portion of a userid.
	 * @param s contains the userid.
	 * @param charPart contains the character porition of the userid.
	 * @return will return the numeric portion of the userid.
	 */
	public Long getNumberPart(String s, String charPart) {
		try {
			if (s == null || s.length() == 0) {
				return null;
			}
			final char c[] = s.toCharArray();
			final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			for (int i = charPart.length(); i < c.length; ++i) {
				if (c[i] >= '0' && c[i] <= '9') {
					sb.append(c[i]);
				}
				else {
					break;
				}
			}
			return Long.valueOf(sb.toString());
		}
		catch (Exception e) {
			return null;
		}
	}
}
