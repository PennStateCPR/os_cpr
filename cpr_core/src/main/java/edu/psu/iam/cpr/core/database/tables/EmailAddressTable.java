/* SVN FILE: $Id: EmailAddressTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
import edu.psu.iam.cpr.core.database.beans.EmailAddress;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.EmailAddressReturn;
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
public class EmailAddressTable {
	
	/** Contains the name of the database table this implementation is acting on */
	private static final String TABLE_NAME = "Email Address";
	
	private static final int EMAIL_KEY = 0;
	private static final int EMAIL_ADDRESS_TYPE = 1;
	private static final int EMAIL_ADDRESS = 2;
	private static final int START_DATE = 3;
	private static final int END_DATE = 4;
	private static final int LAST_UPDATE_BY = 5;
	private static final int LAST_UPDATE_ON = 6;
	private static final int CREATED_BY = 7;
	private static final int CREATED_ON = 8;
	private static final int BUFFER_SIZE = 1024;

	private static final String PERSON_ID_STRING = "person_id";
	private static final String DATA_TYPE_KEY_STRING = "data_type_key";
	private static final String EMAIL_ADDRESS_KEY_STRING = "email_address_key";

	/** Email address bean */
	private EmailAddress emailAddressBean;
	
	/** Contains the enumerated type representation of the email address. */	
	private EmailAddressType emailAddressType;
	
	/** Contains a boolean flag that for a GET indicates whether to return history or not */
	private boolean returnHistoryFlag;
	
	/** Contains the email address database table key value */
	private Long emailKey = 0L;

	/**
	 * Empty constructor.
	 */
	public EmailAddressTable() {
		super();
	}
	
	/**
	 * Constructor
	 * @param personId Contains the person's primary identifier in the CPR.
	 * @param emailAddressType Contains the email address type represented as a string.
	 * @param emailAddress Contains the email address.
	 * @param updatedBy Contains the system identifier or userid that updated the record.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */ 
	public EmailAddressTable(final long personId, final String emailAddressType, final String emailAddress, final String updatedBy) throws CprException {
		
		setEmailAddressType(findEmailAddressEnum(emailAddressType));
		
		final EmailAddress bean = new EmailAddress();
		setEmailAddressBean(bean);
		final Date d = new Date();
		
		bean.setPersonId(personId);
		bean.setDataTypeKey(getEmailAddressType().index());
		bean.setEmailAddress(emailAddress);

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
	}

	/**
	 * Constructor.
	 * @param personId
	 * @param emailAddressType
	 * @param updatedBy
	 * @throws CprException 
	 */
	public EmailAddressTable(final long personId, final String emailAddressType, final String updatedBy) throws CprException {
		this(personId, emailAddressType, null, updatedBy);
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
	 * @param emailAddressType the emailAddressType to set
	 */
	public final void setEmailAddressType(EmailAddressType emailAddressType) {
		this.emailAddressType = emailAddressType;
	}
	
	/**
	 * @param emailAddressType to be set.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public final EmailAddressType findEmailAddressEnum(final String emailAddressType) throws CprException {
		if (emailAddressType != null) {
			EmailAddressType emailAddressEnum = Utility.getEnumFromString(EmailAddressType.class, emailAddressType);
			if (emailAddressEnum != null) {
				return emailAddressEnum;
			}
		}
		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Email Address Type");
	}

	/**
	 * @return the emailAddressType */
	public EmailAddressType getEmailAddressType() {
		return emailAddressType;
	}

	/**
	 * @param emailAddressBean the emailAddressBean to set
	 */
	public final void setEmailAddressBean(EmailAddress emailAddressBean) {
		this.emailAddressBean = emailAddressBean;
	}

	/**
	 * @return the emailAddressBean */
	public EmailAddress getEmailAddressBean() {
		return emailAddressBean;
	}

	
	/**
	 * The purpose of this routine is to interface with the database with JDBC to 
	 * call a stored function to add an email address to a user's record.  The 
	 * information necessary to add the return is passed in the EmailAddress class.
	 * 
	 * @param db Database
	 * 
	 * @throws CprException  
	 */
	public void addAddress(final Database db) throws CprException {
		
		boolean matchFound = false;
		final Session session = db.getSession();
		final EmailAddress bean = getEmailAddressBean();

		// Expire the existing one.
		final String sqlQuery = "from EmailAddress where personId = :person_id and dataTypeKey = :data_type_key AND endDate is NULL";
		final Query query = session.createQuery(sqlQuery);
		query.setParameter(PERSON_ID_STRING, bean.getPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
			EmailAddress dbBean = (EmailAddress) it.next();
			if ( Utility.areStringFieldsEqual(dbBean.getEmailAddress(), bean.getEmailAddress())) {
				matchFound = true;
			}
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
		
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, TABLE_NAME);
		}		
	}
	
	/**
	 * The purpose of this routine is to interface with the database with JDBC to 
	 * call a stored function to update an email address to a user's record.  The 
	 * information necessary to update the record is passed in the EmailAddress class.
	 * @param db Database
	 * 
	 * @throws CprException  
	 */
	public void updateAddress(final Database db) throws CprException {
		
		boolean matchFound = false;
		final Session session = db.getSession();
		final EmailAddress bean = getEmailAddressBean();

		// Expire the existing one.
		final String sqlQuery = "from EmailAddress where personId = :person_id and dataTypeKey = :data_type_key AND endDate is NULL";
		final Query query = session.createQuery(sqlQuery);
		query.setParameter(PERSON_ID_STRING, bean.getPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
			EmailAddress dbBean = (EmailAddress) it.next();
			if ( Utility.areStringFieldsEqual(dbBean.getEmailAddress(), bean.getEmailAddress())) {
				matchFound = true;
			}
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

		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, TABLE_NAME);
		}
		
	}	
	
	/**
	 * The purpose of this routine is to delete (archive) an email address for a user.
	 * @param db Database
	 * @throws CprException 
	 * 
	 */
	public void archiveEmailAddress(final Database db) throws CprException {

		boolean recordNotFound = false;
		boolean holdNotActive = false;
		final Session session = db.getSession();
		final EmailAddress bean = getEmailAddressBean();

		Query query = null;
		String sqlQuery = null;
		if (getEmailKey() > 0L) {
			sqlQuery = "from EmailAddress where personId = :person_id and emailAddressKey = :email_address_key";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(EMAIL_ADDRESS_KEY_STRING, getEmailKey());
		}
		else {
			sqlQuery = "from EmailAddress where personId = :person_id and dataTypeKey = :data_type_key";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		}
		Iterator<?> it = query.list().iterator();

		if (it.hasNext()) {

			sqlQuery += " and endDate is NULL";
			query = session.createQuery(sqlQuery);
			if (getEmailKey() > 0L) {
				query.setParameter(PERSON_ID_STRING, bean.getPersonId());
				query.setParameter(EMAIL_ADDRESS_KEY_STRING, getEmailKey());
			}
			else {
				query.setParameter(PERSON_ID_STRING, bean.getPersonId());
				query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());				
			}

			it = query.list().iterator();

			// Active email address exists, so we expire it.
			if (it.hasNext()) {
				EmailAddress dbBean = (EmailAddress) it.next();
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

		session.flush();

		// Handle the error cases.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
		
		if (holdNotActive) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);
		}		

	}
	
	/**
	 * This routine is used to retrieve the list of email addresses for a person.
	 * @param db contains the database connection.
	 * @param personId contains the person id.
	 * @return will return a list of email addresses.
	 * @throws CprException  
	 */
	public EmailAddressReturn[] getEmailAddressForPersonId(final Database db, final long personId) throws CprException {
		
		final List<EmailAddressReturn> results = new ArrayList<EmailAddressReturn>();
		final Session session = db.getSession();

		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT email_address_key, data_type_key, email_address, " );
		sb.append("start_date, ");
		sb.append("end_date, ");
		sb.append("last_update_by, ");
		sb.append("last_update_on, ");
		sb.append("created_by, ");
		sb.append("created_on ");
		sb.append("FROM {h-schema}email_address ");
		sb.append("WHERE person_id=:person_id ");

		// If we are not returning all records, we need to just return the active ones.
		if (! isReturnHistoryFlag()) {
			sb.append("AND end_date IS NULL ");
		}
		
		if (getEmailKey() > 0L) {
			sb.append("AND email_address_key = :email_address_key ");
		}

		sb.append("ORDER BY data_type_key ASC, start_date ASC ");

		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter(PERSON_ID_STRING, personId);
		
		if (getEmailKey() > 0L) {
			query.setParameter(EMAIL_ADDRESS_KEY_STRING, getEmailKey());
		}
		
		query.addScalar(EMAIL_ADDRESS_KEY_STRING, StandardBasicTypes.LONG);
		query.addScalar(DATA_TYPE_KEY_STRING, StandardBasicTypes.LONG);
		query.addScalar("email_address", StandardBasicTypes.STRING);
		query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("last_update_by", StandardBasicTypes.STRING);
		query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
		query.addScalar("created_by", StandardBasicTypes.STRING);
		query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);

		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Object[] res = (Object []) it.next();
			EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
			emailAddressReturn.setEmailKey(((Long) res[EMAIL_KEY]).toString());
			emailAddressReturn.setEmailAddressType(EmailAddressType.get((Long) res[EMAIL_ADDRESS_TYPE]).toString());
			emailAddressReturn.setEmailAddress((String) res[EMAIL_ADDRESS]);
			emailAddressReturn.setStartDate(Utility.formatDateToISO8601((Date) res[START_DATE]));
			emailAddressReturn.setEndDate(Utility.formatDateToISO8601((Date) res[END_DATE]));
			emailAddressReturn.setLastUpdateBy((String) res[LAST_UPDATE_BY]);
			emailAddressReturn.setLastUpdateOn(Utility.formatDateToISO8601((Date) res[LAST_UPDATE_ON]));
			emailAddressReturn.setCreatedBy((String) res[CREATED_BY]);
			emailAddressReturn.setCreatedOn(Utility.formatDateToISO8601((Date) res[CREATED_ON]));
			results.add(emailAddressReturn);
		}

		return results.toArray(new EmailAddressReturn[results.size()]);
	}
	
	/**
	 * @return the emailKey
	 */
	public Long getEmailKey() {
		return emailKey;
	}

	/**
	 * @param emailKey the emailKey to set
	 */
	public void setEmailKey(Long emailKey) {
		this.emailKey = emailKey;
	}

	/**
	 * @return a string value.
	 */
	public String toString() {
		return "EmailAddressTable";
	}
}
