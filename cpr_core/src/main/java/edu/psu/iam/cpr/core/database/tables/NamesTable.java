/* SVN FILE: $Id: NamesTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.DocumentType;
import edu.psu.iam.cpr.core.database.types.MatchingAlgorithmType;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.NameReturn;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.DataQualityService;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for interfacing with the Names database
 * table.  There are methods within here to add, update, and get a name for a 
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
public class NamesTable {
	
	private static final int NAME_KEY = 0;
	private static final int NAME_TYPE = 1;
	private static final int DOCUMENT_TYPE = 2;
	private static final int FIRST_NAME = 3;
	private static final int MIDDLE_NAMES = 4;
	private static final int LAST_NAME = 5;
	private static final int SUFFIX = 6;
	private static final int NICKNAME = 7;
	private static final int START_DATE = 8;
	private static final int END_DATE = 9;
	private static final int LAST_UPDATE_BY = 10;
	private static final int LAST_UPDATE_ON = 11;
	private static final int CREATED_BY = 12;
	private static final int CREATED_ON = 13;
	private static final int BUFFER_SIZE = 2048;
	
	private static final String DATA_TYPE_KEY_STRING = "data_type_key";
	private static final String DOCUMENT_TYPE_KEY_STRING = "document_type_key";
	private static final String PERSON_ID_STRING = "person_id";
	private static final String NAME_KEY_STRING = "name_key";

	/** Contains the name of the names database table */
	private static final String TABLE_NAME = "Names";

	/** Names database bean */
	private Names namesBean;
	
	/** Contains the enumerated name type. */
	private NameType nameType;
	
	/** Contains the return history flag */
	private boolean returnHistoryFlag;
	
	/** Contains the enumerated document type. */
	private DocumentType documentType;
	
	/** Contains the name Key that is set for the RESTful service */
	private Long nameKey = 0L;
	
	/**
	 * Constructor
	 */
	public NamesTable() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param personId contains the person identifier from the CPR.
	 * @param nameType contains the string representation of the name type.
	 * @param documentType contains the string representation of the document type.
	 * @param firstName contains the first name.
	 * @param middleNames contains the middle names (optional).
	 * @param lastName contains the last name.
	 * @param suffix contains the suffix (optional).
	 * @param nickname contains the nickname (optional).
	 * @param updatedBy contains the updatedBy system identifier.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public NamesTable(final long personId, final String nameType, final String documentType, final 
			String firstName, final String middleNames,
			final String lastName, final String suffix, final String nickname, final String updatedBy) throws CprException {

		final Names bean = new Names();
		final Date d = new Date();

		setNameType(findNameTypeEnum(nameType));
		if (documentType != null && documentType.trim().length() > 0) {
			setDocumentType(findDocumentTypeEnum(documentType));
		}

		setNamesBean(bean);

		bean.setPersonId(personId);

		bean.setDataTypeKey(getNameType().index());
		bean.setDocumentTypeKey((documentType != null && documentType.trim().length() > 0) ? getDocumentType().index() : null);

		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setMiddleNames(middleNames);
		bean.setSuffix(suffix);
		bean.setNickname(nickname);

		bean.setNameMatchCode(null);

		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);

		bean.setImportFrom(null);
		bean.setImportDate(null);
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifer.
	 * @param nameType contains the name type
	 * @param documentType contains the document type.
	 * @param updatedBy contains the person who is updating the record.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public NamesTable(final long personId, final String nameType, final String documentType, final String updatedBy) throws CprException {
		this(personId, nameType, documentType, null, null, null, null, null, updatedBy);
	}
	
	/**
	 * @param namesBean the namesBean to set
	 */
	public final void setNamesBean(Names namesBean) {
		this.namesBean = namesBean;
	}

	/**
	 * @return the namesBean
	 */
	public Names getNamesBean() {
		return namesBean;
	}

	/**
	 * @return the nameType
	 */
	public NameType getNameType() {
		return nameType;
	}

	/**
	 * @param nameType the nameType to set
	 */
	public final void setNameType(NameType nameType) {
		this.nameType = nameType;
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
	 * Will find the enumerated name type based on the input string.
	 * @param nameType contains the name type to be searched for.
	 * @return will contain the returned name type.
	 * @throws CprException  will be thrown if there are any problems.
	 */
	public final NameType findNameTypeEnum(final String nameType) throws CprException {
		if (nameType != null) {
			NameType nameTypeEnum = Utility.getEnumFromString(NameType.class, nameType);
			if (nameTypeEnum != null) {
				return nameTypeEnum;
			}
		}
		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Name Type");
	}
	
	/**
	 * @param documentType the documentType to set
	 */
	public final void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	/**
	 * This routine is used to convert a string representation of a document type of an enum.
	 * @param documentType contains the string value of the document type.
	 * @return will return an enum if successful.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public final DocumentType findDocumentTypeEnum(final String documentType) throws CprException {
		if (documentType != null) {
			DocumentType documentTypeEnum = Utility.getEnumFromString(DocumentType.class, documentType);
			if (documentTypeEnum != null) {
				return documentTypeEnum;
			}
		}
		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Document Type");
	}
	
	/**
	 * @return the documentType
	 */
	public DocumentType getDocumentType() {
		return documentType;
	}

	/**
	 * This routine will obtain a list of names for a person id.
	 * @param db contains the database connection.
	 * @param personId contains the person id.
	 * @return list of names.
	 */
	public NameReturn[] getNamesForPersonId(final Database db, final long personId) {
		
		final List<NameReturn> results = new ArrayList<NameReturn>();
		final Session session = db.getSession();

		// Build the query string.
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT name_key, data_type_key, document_type_key, first_name, middle_names, last_name, suffix, nickname, ");
		sb.append("start_date, ");
		sb.append("end_date, ");
		sb.append("last_update_by, ");
		sb.append("last_update_on, ");
		sb.append("created_by, ");
		sb.append("created_on ");
		sb.append("FROM {h-schema}names ");
		sb.append("WHERE person_id = :person_id_in ");

		// If we are doing a query for a specific name type, we need to specify this clause.
		if (getNameType() != null) {
			sb.append("AND data_type_key = :data_type_key_in ");
		}

		// If we are not returning all records, we need to just return the active ones.
		if (! isReturnHistoryFlag()) {
			sb.append("AND end_date IS NULL ");
		}
		
		// Check to see if we are returning a specific name record 
		if (getNameKey() > 0L) {
			sb.append("AND name_key = :name_key_in ");
		}

		sb.append("ORDER BY data_type_key ASC, start_date ASC ");

		// Set up hibernate for the query, bind parameters and determine return types.
		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("person_id_in", personId);

		if (getNameType() != null) {
			query.setParameter("data_type_key_in", getNameType().index());
		}
		
		if (getNameKey() > 0L) {
			query.setParameter("name_key_in", getNameKey());
		}

		query.addScalar(NAME_KEY_STRING, StandardBasicTypes.LONG);
		query.addScalar(DATA_TYPE_KEY_STRING, StandardBasicTypes.LONG);
		query.addScalar(DOCUMENT_TYPE_KEY_STRING, StandardBasicTypes.LONG);
		query.addScalar("first_name", StandardBasicTypes.STRING);
		query.addScalar("middle_names", StandardBasicTypes.STRING);
		query.addScalar("last_name", StandardBasicTypes.STRING);
		query.addScalar("suffix", StandardBasicTypes.STRING);
		query.addScalar("nickname", StandardBasicTypes.STRING);
		query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
		query.addScalar("last_update_by", StandardBasicTypes.STRING);
		query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
		query.addScalar("created_by", StandardBasicTypes.STRING);
		query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);

		// Perform the query.
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Object[] res = (Object []) it.next();
			NameReturn nameReturn = new NameReturn();
			
			nameReturn.setNameKey(((Long) res[NAME_KEY]).toString());
			nameReturn.setNameType(NameType.get((Long) res[NAME_TYPE]).toString());

			if ((Long) res[DOCUMENT_TYPE] != null) {
				nameReturn.setDocumentType(DocumentType.get((Long) res[DOCUMENT_TYPE]).toString());
			}
			else {
				nameReturn.setDocumentType(null);
			}

			nameReturn.setFirstName((String) res[FIRST_NAME]);
			nameReturn.setMiddleNames((String) res[MIDDLE_NAMES]);
			nameReturn.setLastName((String) res[LAST_NAME]);
			nameReturn.setSuffix((String) res[SUFFIX]);
			nameReturn.setNickname((String) res[NICKNAME]);
			nameReturn.setStartDate(Utility.formatDateToISO8601((Date) res[START_DATE]));
			nameReturn.setEndDate(Utility.formatDateToISO8601((Date) res[END_DATE]));
			nameReturn.setLastUpdateBy((String) res[LAST_UPDATE_BY]);
			nameReturn.setLastUpdateOn(Utility.formatDateToISO8601((Date) res[LAST_UPDATE_ON]));
			nameReturn.setCreatedBy((String) res[CREATED_BY]);
			nameReturn.setCreatedOn(Utility.formatDateToISO8601((Date) res[CREATED_ON]));

			results.add(nameReturn);

		}

		// Check on the results.
		return results.toArray(new NameReturn[results.size()]);
	}

	/**
	 * This routine will attempt to delete (archive) a particular name type.
	 * @param db contains the database connection.
	 * @throws CprException 
	 */
	public void archiveName(final Database db) throws CprException  {
		
		boolean recordNotFound = false;
		boolean alreadyArchived = false;

		final Session session = db.getSession();
		final Names bean = getNamesBean();

		// Check to see if the record exists for a the user and the specified name type.
		Query query = null;
		String sqlQuery = null;

		// Name key was specified.
		if (getNameKey() > 0L) {
			sqlQuery = "from Names where personId = :person_id and nameKey = :name_key";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(NAME_KEY_STRING, getNameKey());
		}
		
		// No document type key was specified.
		else if (bean.getDocumentTypeKey() == null) {
			sqlQuery = "from Names where personId = :person_id and dataTypeKey = :data_type_key";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		}

		// A Document type key was specified.
		else {
			sqlQuery = "from Names where personId = :person_id and dataTypeKey = :data_type_key and documentTypeKey = :document_type_key";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
			query.setParameter(DOCUMENT_TYPE_KEY_STRING, bean.getDocumentTypeKey());
		}

		if (query.list().size() > 0) {
			// Check to see if an active record exists for the user and specified name type.
			sqlQuery += " and endDate is NULL";
			query = session.createQuery(sqlQuery);

			if (getNameKey() > 0L) {
				query.setParameter(PERSON_ID_STRING, bean.getPersonId());
				query.setParameter(NAME_KEY_STRING, getNameKey());				
			}
			else if (bean.getDocumentTypeKey() == null) {
				query.setParameter(PERSON_ID_STRING, bean.getPersonId());
				query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
			}
			else {
				query.setParameter(PERSON_ID_STRING, bean.getPersonId());
				query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
				query.setParameter(DOCUMENT_TYPE_KEY_STRING, bean.getDocumentTypeKey());
			}

			final Iterator <?> it = query.list().iterator();
			if (it.hasNext()) {

				// Expire the active record.
				final Names dbBean = (Names) it.next();
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

		// Handle the errors.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);
		}
	}
	
	/**
	 * This routine is used to add/update a person's name in the CPR.  
	 * @param db contains the database connection.
	 * @throws CprException 
	 */
	public void addName(final Database db) throws CprException {
	
		boolean matchFound = false;
		final Session session = db.getSession();
		final Names bean = getNamesBean();

		String sqlQuery = null;
		Query query = null;

		if (bean.getDocumentTypeKey() == null) {
			sqlQuery = "from Names where personId = :person_id AND dataTypeKey = :data_type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());
		}
		else {
			sqlQuery = "from Names where personId = :person_id AND dataTypeKey = :data_type_key AND documentTypeKey = :document_type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter(PERSON_ID_STRING, bean.getPersonId());
			query.setParameter(DATA_TYPE_KEY_STRING, bean.getDataTypeKey());				
			query.setParameter(DOCUMENT_TYPE_KEY_STRING, bean.getDocumentTypeKey());
		}

		for (final Iterator<?> it = query.list().iterator(); it.hasNext() && (! matchFound); ) {
			Names dbBean = (Names) it.next();

			// Check to ensure that the fields are not already there.
			if (Utility.areStringFieldsEqual(dbBean.getFirstName(), bean.getFirstName()) &&
					Utility.areStringFieldsEqual(dbBean.getMiddleNames(), bean.getMiddleNames()) &&
					Utility.areStringFieldsEqual(dbBean.getLastName(), bean.getLastName()) &&
					Utility.areStringFieldsEqual(dbBean.getSuffix(), bean.getSuffix())) {
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

			if (MatchingAlgorithmType.valueOf(CprProperties.INSTANCE.getProperties().getProperty(
					CprPropertyName.CPR_MATCHING_ALGORITHM.toString())) == MatchingAlgorithmType.PENN_STATE) {
				getNameMatchCode(bean);
			}

			session.save(bean);
			session.flush();
		}
		
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, TABLE_NAME);
		}
	}
	
	/**
	 * This routine is used to return a name that will be used by the match code server.  At this time it consists
	 * of the first and last names concatenated together.
	 * @return string containing the name.
	 */
	public String getMatchName() {
		return null;
	}

	/**
	 * This routine is used to obtain match codes for a name.
	 * @param bean contains the database bean.
	 */
	public void getNameMatchCode(Names bean) {
	}
 
    /**
     * This method is used to obtain match codes using an existing opened data quality service.
     * @param dqService contains the data quality service reference.
     * @param bean contains the names bean.
     */
    public void getMatchCodeUsingOpenSession(final DataQualityService dqService, final Names bean) {
    }

	/**
	 * @return the nameKey
	 */
	public Long getNameKey() {
		return nameKey;
	}

	/**
	 * @param nameKey the nameKey to set
	 */
	public void setNameKey(Long nameKey) {
		this.nameKey = nameKey;
	}
}
