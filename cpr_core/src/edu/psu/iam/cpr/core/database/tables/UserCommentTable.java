/* SVN FILE: $Id: UserCommentTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.UserComments;
import edu.psu.iam.cpr.core.database.types.UserCommentType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.UserCommentReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
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
public class UserCommentTable {

	/**
	 * Contains the user comments bean.
	 */
	private UserComments userCommentsBean;

	/**
	 *  Contains the enumerated user comment type.
	 */	
	private UserCommentType userCommentType;
	
	/** Flag that indicates whether to return history or not. */
	private boolean returnHistoryFlag;

	
	/**
	 * Constructor
	 * @param personId contains the person identifier from the CPR.
	 * @param userId contains the user id of person.
	 * @param comment contains the comment.
	 * @param userCommentType contains the string representation of the user comment type.
	 * @param updatedBy contains the updatedBy system identifier.
	 * @throws Exception exception will be thrown if there are any problems.
	 */
	public UserCommentTable(long personId, String userId, 
			String userCommentType, String comment,
			String updatedBy) throws Exception {
		super();
		
		setUserCommentType(userCommentType);
		final UserComments bean = new UserComments();
		final Date d = new Date();
		setUserCommentsBean(bean);
		
		bean.setPersonId(personId);
		bean.setUserid(userId);
		bean.setDataTypeKey(getUserCommentType().index());
		bean.setComments(comment);
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
	}

	/**
	 * Constructor for UserCommentTable.
	 * @param personId long
	 * @param userId String
	 * @param userCommentType String
	 * @param updatedBy String
	 * @throws Exception
	 */
	public UserCommentTable(long personId, String userId, 
			String userCommentType,
			String updatedBy) throws Exception {
		this(personId, userId, userCommentType, null, updatedBy);
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
	 * @param userCommentsBean the userCommentsBean to set
	 */
	public final void setUserCommentsBean(UserComments userCommentsBean) {
		this.userCommentsBean = userCommentsBean;
	}


	/**
	 * @return the userCommentsBean
	 */
	public UserComments getUserCommentsBean() {
		return userCommentsBean;
	}


	/**
	 * Constructor
	 */
	public UserCommentTable() {
		super();
	}


	/**
	 * @return the userCommentType
	 */
	public UserCommentType getUserCommentType() {
		return userCommentType;
	}


	/**
	 * @param userCommentType the userCommentType to set
	 */
	public final void setUserCommentType(UserCommentType userCommentType) {
		this.userCommentType = userCommentType;
	}

	/**
	 * @param userCommentType the string representation of userCommentType.
	 * @throws Exception
	 */
	public final void setUserCommentType(String userCommentType) throws Exception {
		setUserCommentType(UserCommentType.valueOf(userCommentType.toUpperCase().trim()));
	}

	
	/**
	 * This method is used to add a comment on a userid in the CPR.
	 * @param db 
	 * @throws GeneralDatabaseException exception will be thrown for any general database failures.
	 * @throws CprException exception will be thrown for any CPR specific failures.
	 */
	public void addUserComment(Database db) throws GeneralDatabaseException, CprException {
		
		boolean commentFound = false;
		try {
			final Session session = db.getSession();
			final UserComments bean = getUserCommentsBean();
			
			// Determine if a comment already exists for the user
			final String sqlQuery = "from UserComments where personId = :person_id AND userid = :userid AND dataTypeKey = :data_type_key AND endDate is NULL";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("userid", bean.getUserid());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			final Iterator<?> it = query.list().iterator();
			
			// If the comment exists, we have an error otherwise we can add the new comment.
			if (it.hasNext()) {
				commentFound = true;
			}
			else {
				session.save(bean);
				session.flush();
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "user comment");
		}
		
		if (commentFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "user comment");
		}
	}

	/**
	 * This method is used to update a comment on a userid in the CPR.
	 * @param db 
	 * @throws GeneralDatabaseException exception will be thrown for any general database failures.
	 * @throws CprException exception will be thrown for any CPR specific failures.
	 */
	public void updateUserComment(Database db) throws GeneralDatabaseException, CprException {
		
		try {
			final Session session = db.getSession();
			final UserComments bean = getUserCommentsBean();
			
			// Determine if there is an active comment for the user and the specified type.
			final String sqlQuery = "from UserComments where personId = :person_id AND userid = :userid AND dataTypeKey = :data_type_key AND endDate IS NULL";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("userid", bean.getUserid());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			final Iterator<?> it = query.list().iterator();
			
			// If a record has been found, we need to append the new comment on to the end of the record.
			if (it.hasNext()) {
				UserComments dbBean = (UserComments) it.next();
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				final StringBuilder sb = new StringBuilder(128);
				sb.append(dbBean.getComments());
				sb.append(System.getProperty("line.separator"));
				sb.append('(');
				sb.append(bean.getLastUpdateBy());
				sb.append(") ");
				sb.append(bean.getComments());
				dbBean.setComments(sb.toString());
				
				session.update(dbBean);
				session.flush();
			}
			
			// If no record was found, we need to add a new comment.
			else {
				session.save(bean);
				session.flush();
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.UPDATE_FAILED_EXCEPTION, "user comment");
		}
	}
	
	/**
	 * This method will attempt to archive( delete ) a particular comment.
	 * @param db
	 * @throws GeneralDatabaseException exception will be thrown for any general database failures.
	 * @throws CprException exception will be thrown for any CPR specific failures.
	 */
	public void archiveUserComment(Database db) throws GeneralDatabaseException, CprException {
		
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		try {
			
			final Session session = db.getSession();
			final UserComments bean = getUserCommentsBean();
			
			// Check to see if there is a record of the specific type.
			String sqlQuery = "from UserComments where personId = :person_id and userid = :userid and dataTypeKey = :data_type_key";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("userid", bean.getUserid());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			Iterator<?> it = query.list().iterator();
			if (! it.hasNext()) {
				recordNotFound = true;
			}
			else {
				// Check to see if there is an active record of the specific type.
				sqlQuery = "from UserComments where personId = :person_id and userid = :userid and dataTypeKey = :data_type_key and endDate IS NULL";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("userid", bean.getUserid());
				query.setParameter("data_type_key", bean.getDataTypeKey());
				it = query.list().iterator();
				if (! it.hasNext()) {
					alreadyArchived = true;
				}
				else {

					// Record (active) found, so we can do the archive!
					UserComments dbBean = (UserComments) it.next();
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "user comment");
		}
		
		// Handle errors.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "user comment");
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "user comment");
		}
	}
	
	/**
	 * Obtain user comments.
	 * @param db
	 * @param userId contains the user id of person.
	 * @return a list of user comments.
	 * @throws GeneralDatabaseException exception will be thrown for any general database failures.
	 * @throws CprException exception will be thrown for any CPR specific failures.
	 */
	public UserCommentReturn[] getUserComments( Database db, String userId ) throws GeneralDatabaseException, CprException {

		try {
			final ArrayList<UserCommentReturn> results = new ArrayList<UserCommentReturn>();
			
			final Session session = db.getSession();

			// Build the query string.
			final StringBuilder sb = new StringBuilder(2048);
			sb.append("SELECT data_type_key, comments, ");
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by ");
			sb.append("FROM user_comments ");
			sb.append("WHERE userid = :userid_in ");
			
			if (getUserCommentType() != null) { 
				sb.append("AND data_type_key = :data_type_key_in ");
			}
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			
			sb.append("ORDER BY data_type_key ASC, start_date ASC ");

			// Init the hibernate query.
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("userid_in", userId);
			
			if (getUserCommentType() != null) {
				query.setParameter("data_type_key_in", getUserCommentType().index());
			}
			
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("comments", StandardBasicTypes.STRING);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);

			// Perform the query.
			final Iterator<?> it = query.list().iterator();
			
			// Process the results.
			while (it.hasNext()) {
				Object res[] = (Object []) it.next();
				UserCommentReturn ucr = new UserCommentReturn();
				ucr.setUserCommentType(UserCommentType.get((Long) res[0]).toString());
				ucr.setComment((String) res[1]);
				ucr.setCommentDateString(Utility.convertTimestampToString((Date) res[2]));
				ucr.setEndDate(Utility.convertTimestampToString((Date) res[3]));
				ucr.setLastUpdatedBy((String) res[4]);
				ucr.setLastUpdateOn(Utility.convertTimestampToString((Date) res[5]));
				ucr.setCommenter((String) res[6]);				
				results.add(ucr);
			}
			
			return results.toArray(new UserCommentReturn[results.size()]);
			
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve comments for user id = " + userId);
		}			
	}

	/**
	 * @return a string value.
	 */
	public String toString() {
		return "UserCommentTable";
	}	
}
