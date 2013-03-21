/* SVN FILE: $Id: PhonesTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Phones;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PhoneReturn;
import edu.psu.iam.cpr.core.util.Utility;
/**
 *  This class provides an implementation for interfacing with the Phones database
 * table.  There are methods within here to add, archive,  update, and get an address for a 
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
public class PhonesTable {
	
	/** Contains the table name for this database implementation */
	private static final String TABLE_NAME = "Phones";
	
	private static final int PHONE_TYPE = 0;
	private static final int GROUP_ID = 1;
	private static final int PRIMARY_FLAG = 2;
	private static final int PHONE_NUMBER = 3;
	private static final int EXTENSION = 4;
	private static final int INTERNATIONAL_NUMBER = 5;
	private static final int START_DATE = 6;
	private static final int END_DATE = 7;
	private static final int LAST_UPDATE_BY = 8;
	private static final int LAST_UPDATE_ON = 9;
	private static final int CREATED_BY = 10;
	private static final int CREATED_ON = 11;
	
	private static final int BUFFER_SIZE = 1024;
	
	private static final String PERSON_ID_STRING = "person_id";
	private static final String DATA_TYPE_KEY_STRING = "data_type_key";
	private static final String GROUP_ID_STRING = "group_id";

	/** Contains a phone bean reference */
	private PhoneType phoneType;
	
	
	/** Contains the phone type value */
	private Phones phonesBean;
	
	/** Boolean that will be used by the GetPhone to determine if history is to be returned or not. */
	private boolean returnHistoryFlag;
	
	

	/**
	 * Empty constructor
	 */
	public PhonesTable() {
		super();

	}

	/**
	 * Constructor for archive phone information
	 * @param personId contains the person's primary identifier in the CPR.
	 * @param phoneType  contains the phone type represented as a string
	 * @param groupId contains the ranking within the particular phone type.
	 * @param lastUpdateBy contains the system identifier and/or userid that updated the record
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */

	public PhonesTable(long personId, String phoneType, Long groupId, String lastUpdateBy) throws CprException
	{

		this(personId, phoneType, groupId,  null, null, null, lastUpdateBy);

	}	
	
	/**
	 * 
	 * @param personId contains the person's primary identifier in the CPR.
	 * @param phoneType contains the phone type represented as a string
	 * @param phoneNumber contains the phone number
	 * @param extension contains the phone extension number	
	 * @param internationalNumber contains a flag to indicate whether the phone number is international.
	 * @param lastUpdateBy contains the system identifier and/or userid that updated the record
	 * @throws CprException will be thrown if there are any CPR related problems.
	 
	 */
	public PhonesTable(long personId, String phoneType,  
			String phoneNumber, String extension, String internationalNumber,
			String lastUpdateBy) throws CprException {
		this(personId, phoneType, null, phoneNumber, extension, internationalNumber, lastUpdateBy);
	}

	/**
	 * @param personId contains the person's primary identifier in the CPR.
	 * @param phoneType  contains the phone type represented as a string
	 * @param groupId contains the ranking within the particular phone type.
	 * @param phoneNumber contains the phone number
	 * @param extension contains the phone extension number	
	 * @param internationalNumber contains a flag to indicate whether the phone number is international.
	 * @param lastUpdateBy contains the system identifier and/or userid that updated the record
	 * @throws CprException will be thrown if there are any CPR related problems.
	 *
	 */
	public PhonesTable(long personId, String phoneType, Long groupId,  
			String phoneNumber, String extension, String internationalNumber,
			String lastUpdateBy) throws CprException {

		setPhoneType(findPhoneTypeEnum(phoneType));
		final Phones bean  = new Phones();
		setPhonesBean(bean);
		final Date d = new Date();
		
		bean.setPersonId(personId);
		
		bean.setDataTypeKey(getPhoneType().index());
		bean.setPhoneNumber(phoneNumber);
		bean.setExtension(extension);
		bean.setInternationalNumberFlag(internationalNumber);
		
		bean.setPrimaryFlag("N" );
		bean.setGroupId(groupId);
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setLastUpdateBy(lastUpdateBy);
		bean.setLastUpdateOn(d);
		
		bean.setCreatedBy(lastUpdateBy);
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
	 * @param phonesBean the phonesBean to set
	 */
	public final void setPhonesBean(Phones phonesBean) {
		this.phonesBean = phonesBean;
	}


	/**
	 * @return the phonesBean
	 */
	public Phones getPhonesBean() {
		return phonesBean;
	}


	/**
	 * @return the phoneType
	 */
	public PhoneType getPhoneType() {
		return phoneType;
	}
	
	/**
	 * @param phoneType the phoneType to set
	 */
	public final void setPhoneType(PhoneType phoneType) {
		this.phoneType = phoneType;
	}


	/**
	 * This routine accepts a String argument to assign that to an enumerated type.
	 * If the assignment fails, an exception is thrown.
	 * @param phoneTypeString Contains the String to convert to an enumerated type.
	 * @return will an enum if successful.
	 * @throws CprException 
	 */
	public final PhoneType findPhoneTypeEnum(String  phoneTypeString) throws CprException  {
		if (phoneTypeString != null) {
			PhoneType phoneTypeEnum = Utility.getEnumFromString(PhoneType.class, phoneTypeString);
			if (phoneTypeEnum != null) {
				return phoneTypeEnum;
			}
		}
		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Phone Type");
	}

	/**
	 *  The purpose of this routine is to interface with the database JDBC calls to
	 *  call a store function to add a phone of the specified type to user's record.  
	 *  The information necessary to add the phone is passed in the PhonesTable class.
	 *  @param db
	 * @throws CprException 
	 */
	public void addPhone (Database db) throws CprException {
		
		boolean matchFound = false;
		final Session session = db.getSession();
		final Phones bean = getPhonesBean();
		Long maxGroupId = null;
		// verify that this is not a duplicate record within type
		String sqlQuery = "from Phones where personId = :person_id AND dataTypeKey = :data_type_key AND endDate IS NULL";
		final Query query = session.createQuery(sqlQuery);
		query.setParameter(PERSON_ID_STRING, bean.getPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
			Phones dbBean = (Phones) it.next();
			if (db.areStringFieldsEqual(bean.getPhoneNumber(), dbBean.getPhoneNumber()) &&
					db.areStringFieldsEqual(bean.getExtension(), dbBean.getExtension()) &&
					db.areStringFieldsEqual(bean.getInternationalNumberFlag(), dbBean.getInternationalNumberFlag())) {
				matchFound = true;
			}
		}

		if (! matchFound) {
			// Find the maximum group id for the person and their phone type combination.
			sqlQuery = "SELECT MAX(group_id) as max_group_id FROM {h-schema}phones WHERE person_id = :person_id AND data_type_key = :data_type_key";
			final SQLQuery query1 = session.createSQLQuery(sqlQuery);
			query1.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query1.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
			query1.addScalar("max_group_id", StandardBasicTypes.LONG);
			final Iterator<?> it = query1.list().iterator();
			if (it.hasNext()) {
				maxGroupId = (Long) it.next();
				maxGroupId = (maxGroupId == null) ? 1L : maxGroupId+1L;

			}
			else {
				maxGroupId = 1L;
			}


			// Save off the new record.
			bean.setGroupId(maxGroupId);
			session.save(bean);
			session.flush();
		}

		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, TABLE_NAME);
		}
	}
	
	/**
	 *  The purpose of this routine is to interface with the database JDBC calls to
	 *  call a store function to update a phone number of a specified type for  user's 
	 *  record.  The information necessary to add the phone is passed in the 
	 *  PhonesTable class.
	 *  @param db 
	 * @throws CprException 
	 *  
	 * 
	 */
	public void updatePhone (Database db) throws CprException {
		
		boolean matchFound = false;
		int updateCount = 0;
	
		final Session session = db.getSession();
		final Phones bean = getPhonesBean();

		String sqlQuery = "from Phones where personId = :person_id AND dataTypeKey = :data_type_key AND endDate IS NULL";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PERSON_ID_STRING, bean.getPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
			Phones dbBean = (Phones) it.next();
			if (db.areStringFieldsEqual(bean.getPhoneNumber(), dbBean.getPhoneNumber()) &&
					db.areStringFieldsEqual(bean.getExtension(), dbBean.getExtension()) &&
					db.areStringFieldsEqual(bean.getInternationalNumberFlag(), dbBean.getInternationalNumberFlag())) {
				matchFound = true;
			}
		}

		if (! matchFound) {
			sqlQuery = "from Phones where personId = :person_id and dataTypeKey = :data_type_key and groupId = :group_id and endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
			query.setParameter(GROUP_ID_STRING, bean.getGroupId());
			for (final Iterator<? >it = query.list().iterator(); it.hasNext(); ) {
				Phones dbBean = (Phones) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				dbBean.setPrimaryFlag("N");
				session.update(dbBean);
				session.flush();
				updateCount++;
			}

			// Save off the new record.
			if (updateCount > 0) {
				session.save(bean);
				session.flush();
			}
		}

		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, TABLE_NAME);
		}
		if (updateCount==0) {
			throw new CprException(ReturnType.UPDATE_FAILED_EXCEPTION, TABLE_NAME);
		}
	}
	
	/**
	 *  The purpose of this routine is to interface with the database JDBC calls to
	 *  call a store function to archive a phone number of a specified type  for  user's 
	 *  record.  The information necessary to add the phone is passed in the 
	 *  PhonesTable class.
	 * 	@param db
	 * 	@throws CprException 
	 *  
	 * 
	 */
	public void archivePhone (Database db) throws  CprException {
		
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		final Session session = db.getSession();
		final Phones bean = getPhonesBean();

		String sqlQuery = "from Phones where personId = :person_id and dataTypeKey = :data_type_key and groupId = :group_id ";
		Query query = session.createQuery(sqlQuery);
		query.setParameter(PERSON_ID_STRING, bean.getPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		query.setParameter(GROUP_ID_STRING, bean.getGroupId());

		if (query.list().size() > 0)  {
			sqlQuery += " and endDate is null";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
			query.setParameter(GROUP_ID_STRING, bean.getGroupId());
			final Iterator<?> it = query.list().iterator();
			if (it.hasNext()) {
				Phones dbBean = (Phones) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				dbBean.setPrimaryFlag("N");
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
		
		// Handle the errors.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);
		}
	}
	
	/**
	 * This routine will obtain a list of phone numbers for a person id
	 * @param db 
	 * @param personId contains the personID
	 * @return list of phone numbers
	 */
	public PhoneReturn[] getPhones (Database db, long personId) {

		final ArrayList<PhoneReturn> results = new ArrayList<PhoneReturn>();

		final Session session = db.getSession();
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT data_type_key, group_id, primary_flag, phone_number, extension, international_number_flag,  ");
		sb.append("start_date, ");
		sb.append("end_date, ");
		sb.append("last_update_by, " );
		sb.append("last_update_on, ");
		sb.append("created_by, " );
		sb.append("created_on ");
		sb.append("FROM {h-schema}phones ");
		sb.append("WHERE person_id = :person_id_in ");

		if (getPhoneType() != null) {
			sb.append("AND  data_type_key = :data_type_key_in ");
		}

		if (! isReturnHistoryFlag()) {
			sb.append("AND end_date IS NULL");
		}

		sb.append(" ORDER BY data_type_key ASC, start_date ASC ");

		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("person_id_in", personId);

		if (getPhoneType() != null) {
			query.setParameter("data_type_key_in", getPhoneType().index());
		}

		query.addScalar(DATA_TYPE_KEY_STRING, StandardBasicTypes.LONG);
		query.addScalar(GROUP_ID_STRING, StandardBasicTypes.LONG);
		query.addScalar("primary_flag", StandardBasicTypes.STRING);
		query.addScalar("phone_number", StandardBasicTypes.STRING);
		query.addScalar("extension", StandardBasicTypes.STRING);
		query.addScalar("international_number_flag", StandardBasicTypes.STRING);
		query.addScalar("start_date",StandardBasicTypes.TIMESTAMP );
		query.addScalar("end_date",StandardBasicTypes.TIMESTAMP );
		query.addScalar("last_update_by",StandardBasicTypes.STRING );
		query.addScalar("last_update_on",StandardBasicTypes.TIMESTAMP );
		query.addScalar("created_by",StandardBasicTypes.STRING );
		query.addScalar("created_on",StandardBasicTypes.TIMESTAMP );
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Object res[] = (Object []) it.next();
			PhoneReturn aPhone = new PhoneReturn();
			aPhone.setPhoneType(PhoneType.get((Long) res[PHONE_TYPE]).toString());
			aPhone.setGroupId((Long) res[GROUP_ID]);
			aPhone.setPrimaryFlag((String) res[PRIMARY_FLAG]);
			aPhone.setPhoneNumber((String) res[PHONE_NUMBER]);
			aPhone.setExtension((String) res[EXTENSION]);
			aPhone.setInternationalNumber((String) res[INTERNATIONAL_NUMBER]);
			aPhone.setStartDate(Utility.convertTimestampToString((Date) res[START_DATE]));
			aPhone.setEndDate(Utility.convertTimestampToString((Date) res[END_DATE]));
			aPhone.setLastUpdateBy((String) res[LAST_UPDATE_BY]);
			aPhone.setLastUpdateOn(Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]));
			aPhone.setCreatedBy((String) res[CREATED_BY]);
			aPhone.setCreatedOn(Utility.convertTimestampToString((Date) res[CREATED_ON]));
			results.add(aPhone); 
		}


		return results.toArray(new PhoneReturn[results.size()]);
	}

	/**
	 * This routine will set a primary phone number within a phonetype for a person id
	 * @param db 
	 * @throws CprException 
	 * 
	 */
	public void setPrimaryByType(Database db) throws  CprException {
		
		boolean notFound = false;
		boolean alreadyPrimary = false;
	
		final Session session = db.getSession();
		final Phones bean = getPhonesBean();

		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT  primary_flag ");
		sb.append("FROM {h-schema}phones ");
		sb.append("WHERE person_id = :person_id_in ");
		sb.append("AND data_type_key = :data_type_key ");
		sb.append("AND group_id = :group_id ");
		sb.append("AND end_date IS NULL ");
		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("person_id_in", bean.getPersonId());
		query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		query.setParameter(GROUP_ID_STRING,bean.getGroupId());
		query.addScalar("primary_flag", StandardBasicTypes.STRING);
		Iterator<?> it = query.list().iterator();
		if (! it.hasNext()) {
			notFound = true;
		}
		else {

			final String primaryFlag = (String) it.next();
			if (primaryFlag.equals("Y")) {
				alreadyPrimary = true;
			}
			else {

				String sqlQuery = "from Phones where personId = :person_id and dataTypeKey = :data_type_key and primaryFlag = 'Y' and endDate is null";
				Query query1 = session.createQuery(sqlQuery);
				query1.setParameter(PERSON_ID_STRING, bean.getPersonId());
				query1.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
				for (it = query1.list().iterator(); it.hasNext(); ) {
					Phones dbBean = (Phones) it.next();
					dbBean.setPrimaryFlag("N");
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}

				sqlQuery = "from Phones where personId = :person_id and dataTypeKey = :data_type_key and groupId = :group_id and endDate IS NULL";
				query1 = session.createQuery(sqlQuery);
				query1.setParameter(PERSON_ID_STRING, bean.getPersonId());
				query1.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
				query1.setParameter(GROUP_ID_STRING, bean.getGroupId());
				it = query1.list().iterator();
				if (it.hasNext()) {
					Phones dbBean = (Phones) it.next();
					dbBean.setPrimaryFlag("Y");
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
			}
		}		
	
		if (notFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
	
		if (alreadyPrimary) {
			throw new CprException(ReturnType.SET_PRIMARY_FAILED_EXCEPTION, TABLE_NAME);
		}
	}
}
