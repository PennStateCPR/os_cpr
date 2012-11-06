/* SVN FILE: $Id: AddressesTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.DocumentType;
import edu.psu.iam.cpr.core.database.types.MatchingAlgorithmType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.helper.ServiceCore;
import edu.psu.iam.cpr.core.service.returns.AddressReturn;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Utility;

/**
 *  This class provides an implementation for interfacing with the Addresses database
 * table.  There are methods within here to add, update, and get an address for a 
 * person in the CPR.
 *  * 
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
public class AddressesTable {
	
	/** Name of the database table that is being used by this implementation */
	private static final String TABLE_NAME = "Addresses";
	
	/** Instance of logger */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(ServiceCore.class);

	private static final int BUFFER_SIZE = 2048;

	private static final int ADDRESS_TYPE = 0;
	private static final int DOCUMENT_TYPE = 1;
	private static final int GROUP_ID = 2;
	private static final int PRIMARY_FLAG = 3;
	private static final int ADDRESS1 = 4;
	private static final int ADDRESS2 = 5;
	private static final int ADDRESS3 = 6;
	private static final int CITY = 7;
	private static final int STATE = 8;
	private static final int POSTAL_CODE = 9;
	private static final int PROVINCE = 10;
	private static final int VERIFIED_FLAG = 11;
	private static final int START_DATE = 12;
	private static final int END_DATE = 13;
	private static final int LAST_UPDATE_BY = 14;
	private static final int LAST_UPDATE_ON = 15;
	private static final int CREATED_BY = 16;
	private static final int CREATED_ON = 17;
	private static final int CAMPUS_CODE = 18;
	private static final int CAMPUS_NAME = 19;
	private static final int COUNTRY_CODE = 20;
	private static final int COUNTRY_NAME = 21;
	
	/**
	 *  Contains a reference to the addresses bean.
	 */
	private Addresses addressesBean;
	
	/**
	 *  Contains the enumerated type representation of the address.
	 */
	private AddressType addressType;
	
	
	/** Contains the country name for the specified country code */
	private String countryName;
	
	/** Contains the campus name for the specified campus code */
	private String campusName;
	
	/**
	 * Contains the country Three characted Code
	 * 
	 */
	private String countryThreeCharCode;
	/** 
	 * Contains the enumerated document type.
	 *  
	 */

	private DocumentType documentType;
	
	/** Boolean flag that indicates whether to return history or not with a GET */
	private boolean returnHistoryFlag;
	
	/**
	 * Default constructor.
	 */
	public AddressesTable() {
		super();
	}
	
	/**
	 * Constructor for the ArchiveAddress and SetPrimaryAddressType
	 * @param personId contains the person identifier from the CPR.
	 * @param addressTypeString contains the string representation of the address type.
	 * @param documentType contains the string representation of the document type.
	 * @param groupId contains the ranking within the particular address type.
	 * @param updatedBy contains the updatedBy system identifier.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public AddressesTable (long personId, 
			String addressTypeString, 
			String documentType,
			Long groupId, 
			String updatedBy) throws CprException  {
		this(personId, addressTypeString, documentType, groupId,  updatedBy,  null, null, null, null, null, null, null, null, 
				null, null, null, null);
	}
	/**
	 * Constructor for add Address
	 * @param personId contains the person identifier from the CPR.
	 * @param addressTypeString contains the string representation of the address type.
	 * @param documentType contains the string representation of the document type.
	 * @param address1  contains the first address line.
	 * @param address2 contains the second address line.
	 * @param address3 contains the third address line.
	 * @param updatedBy contains the updatedBy system identifier.
	 * @param city contains the city for the address. 
	 * @param state contains the state, if a US address, of the address.
	 * @param postalCode contains the postal code of the address.
	 * @param province contains the province, if not a US address, of the address
	 * @param countryCodeId contains the country code of the address.
	 * @param campusCodeId contains the campus of the address.
	 * @param countryName contains the country name of the address.
	 * @param campusName contains the campus name of the address
	 * @param countryThreeCharCode contains the three digit countyCode
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public AddressesTable(long personId,  
			String addressTypeString,
			String documentType,
			String updatedBy, 
			String address1,
			String address2, 
			String address3,  
			String city, 
			String state,
			String postalCode, 
			String province, 
			Long countryCodeId, 
			Long campusCodeId,
			String countryName,
			String campusName,
			String countryThreeCharCode) throws CprException {
		this(personId, addressTypeString, documentType, null, updatedBy,  address1, address2, address3, city,state, postalCode, 
				province, countryCodeId, campusCodeId,countryName,campusName, countryThreeCharCode);
	}
	/**
	 * Constructor for updateAddress
	 * @param personId contains the person identifier from the CPR.
	 * @param addressTypeString contains the string representation of the address type.
	 * @param documentType contains the string representation of the document type.
	 * @param groupId contains the ranking within the particular address type.
	 * @param address1  contains the first address line.
	 * @param address2 contains the second address line.
	 * @param address3 contains the third address line.
	 * @param updatedBy contains the updatedBy system identifier.
	 * @param city contains the city for the address. 
	 * @param state contains the state, if a US address, of the address.
	 * @param postalCode contains the postal code of the address.
	 * @param province contains the province, if not a US address, of the address
	 * @param countryCodeId contains the country code of the address.
	 * @param campusCodeId contains the campus of the address.
	 * @param countryName contains the country name of the address.
	 * @param campusName contains the campus name of the address
	 * @param countryThreeCharCode contains the three digit countyCode
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public AddressesTable(long personId,  
			String addressTypeString,
			String documentType,
			Long groupId,
			String updatedBy, 
			String address1,
			String address2, 
			String address3,  
			String city, 
			String state,
			String postalCode, 
			String province, 
			Long countryCodeId, 
			Long campusCodeId,
			String countryName,
			String campusName,
			String countryThreeCharCode) throws CprException  {
		super();
		
		setAddressType(findAddressTypeEnum(addressTypeString));
		if (documentType != null && documentType.trim().length() > 0) {
			setDocumentType(findDocumentTypeEnum(documentType));
		}
		setCountryName(countryName);
		setCampusName(campusName);
		setCountryThreeCharCode(countryThreeCharCode);
		final Addresses bean = new Addresses();
		final Date d = new Date();
		setAddressesBean(bean);
		
		bean.setPersonId(personId);
		bean.setDataTypeKey(getAddressType().index());
		bean.setDocumentTypeKey((documentType != null && documentType.trim().length() > 0) ? getDocumentType().index() : null);
		
		bean.setAddress1(address1);
		bean.setAddress2(address2);
		bean.setAddress3(address3);
		bean.setCity(city);
		bean.setState((state != null) ? state.toUpperCase().trim() : null);
		bean.setPostalCode(postalCode);
		bean.setProvince(province);
		
		bean.setAddressMatchCode(null);
		bean.setCityMatchCode(null);
		
		bean.setCampusCodeKey(campusCodeId);
		bean.setCountryKey(countryCodeId);
		
		bean.setPrimaryFlag("N");
		bean.setGroupId(groupId);
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		bean.setVerifiedFlag("N");
		bean.setValidateAttemptedOn(d);
		
		
		
		
		
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
	 * @param campusName the campusName to set
	 */
	public final void setCampusName(String campusName) {
		this.campusName = campusName;
	}

	/**
	 * @return the campusName
	 */
	public String getCampusName() {
		return campusName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public final void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param addressesBean the addressesBean to set
	 */
	public final void setAddressesBean(Addresses addressesBean) {
		this.addressesBean = addressesBean;
	}

	/**
	 * @return the addressesBean
	 */
	public Addresses getAddressesBean() {
		return addressesBean;
	}
	
	/**
	 * @return the addressType
	 */
	public AddressType getAddressType() {
		return addressType;
	}
	
	/**
	 * @param addressType the addressType to set
	 */
	public final void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
	
	/**
	 * This routine will find the address type for a specific string.
	 * @param addressTypeString the addressTypeString to set
	 * @return will return an enum if successful.
	 * @throws CprException will be thrown if there are any cpr related problems.
	 */
	public final AddressType findAddressTypeEnum(String addressTypeString) throws CprException{
		
		if (addressTypeString != null) {
			for (AddressType addressTypeEnum: AddressType.values()) {
				if (addressTypeEnum.toString().equalsIgnoreCase(addressTypeString)) {
					return addressTypeEnum;
				}
			}
		}
		throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Address Type");
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
	 * @return will return a document type enum if successful.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public final DocumentType findDocumentTypeEnum(String documentType) throws CprException {
		if (documentType != null) {
			for (DocumentType documentTypeEnum: DocumentType.values()) {
				if (documentTypeEnum.toString().equalsIgnoreCase(documentType)) {
					return documentTypeEnum;
				}
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
	 * @return the countryThreeCharCode
	 */
	public String getCountryThreeCharCode() {
		return countryThreeCharCode;
	}

	/**
	 * @param countryThreeCharCode the countryThreeCharCode to set
	 */
	public final void setCountryThreeCharCode(String countryThreeCharCode) {
		this.countryThreeCharCode = countryThreeCharCode;
	}

	/**
	 * The purpose of this routine is to interface with the database with hibernate  to 
	 * add an  address to a user's record.  The information necessary to add
	 * the address is passed in the AddressesTable class.
	 *  @param db contains the Database object
	 * 
	 * @throws CprException 
	 */
	public void addAddress(Database db) throws CprException {
		 
		boolean matchFound = false;
		final Session session = db.getSession();
		final Addresses bean = getAddressesBean();
		Long maxGroupId = null;
		String sqlQuery = null;
		Query query = null;
		if (bean.getDocumentTypeKey() == null) {
			sqlQuery = "from Addresses where personId = :person_id AND dataTypeKey = :data_type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
		}
		else
		{
			sqlQuery = "from Addresses where personId = :person_id AND dataTypeKey = :data_type_key AND  documentTypeKey = :document_type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			query.setParameter("document_type_key", bean.getDocumentTypeKey());
		}

		for (final Iterator<?> it = query.list().iterator(); it.hasNext() &&  (! matchFound); ) {
			Addresses dbBean = (Addresses) it.next();

			// Check to ensure that the fields are not already there
			// ignore campus code and country code

			if (db.areStringFieldsEqual(dbBean.getAddress1(), bean.getAddress1()) &&
					db.areStringFieldsEqual(dbBean.getAddress2(), bean.getAddress2())	 &&
					db.areStringFieldsEqual(dbBean.getAddress3(), bean.getAddress3()) &&
					db.areStringFieldsEqual(dbBean.getCity(), bean.getCity()) &&
					db.areStringFieldsEqual(dbBean.getState(), bean.getState()) && 
					db.areStringFieldsEqual(dbBean.getProvince(), bean.getProvince()) &&
					db.areStringFieldsEqual(dbBean.getPostalCode(), bean.getPostalCode()) &&
					db.areLongFieldsEqual(dbBean.getCampusCodeKey(), bean.getCampusCodeKey()) && 
					db.areLongFieldsEqual(dbBean.getCountryKey(), bean.getCountryKey())) {
				matchFound = true;
			}
			else {
				// is there already a address with the same Document type 
				if (bean.getDocumentTypeKey() != null &&
						dbBean.getDocumentTypeKey() != null &&
						bean.getDocumentTypeKey().equals(dbBean.getDocumentTypeKey())) {
					matchFound = true;
				}
			}
		}

		// If no match is found, find the maximum group id for the person and their address type combination
		// the group id is associated with addresstype.  the document type for addresses of type DOCUMENTED_ADDRESS is ignored when
		// setting group id

		if (! matchFound) {
			sqlQuery =" SELECT MAX(group_id) as max_group_id FROM addresses WHERE person_id=:person_id and data_type_key=:data_type_key";
			final SQLQuery query1 = session.createSQLQuery(sqlQuery);
			query1.setParameter("person_id", bean.getPersonId());
			query1.setParameter("data_type_key", bean.getDataTypeKey());
			query1.addScalar("max_group_id", StandardBasicTypes.LONG);
			final Iterator<? >it = query1.list().iterator();
			// Oracle results in a return value of null if no max_group_id -    that is this address type has no records assigned to it for person
			//  else s included if another database behaves differently
			if (it.hasNext()) {
				maxGroupId = (Long) it.next();
				maxGroupId = (maxGroupId == null) ? 1L : maxGroupId+1L;
			}
			else {
				maxGroupId= 1L;

			}

			// save off the new record
			bean.setGroupId(maxGroupId);

			if (MatchingAlgorithmType.valueOf(CprProperties.INSTANCE.getProperties().getProperty(
					CprPropertyName.CPR_MATCHING_ALGORITHM.toString())) == MatchingAlgorithmType.PENN_STATE) {
				getAddressCityMatchCode(bean);
			}

			session.save(bean);
			session.flush();
		}
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, TABLE_NAME);
		}

	}
	
	/**
	 The purpose of this routine is to interface with the database with hibernate  to 
	 * archive an  address associated with a person.  The information necessary to archive
	 * the address is passed in the AddressesTable class.
	 * @param db contains the Database object
	 *  
	 *  @throws CprException
	 * 
	 */
	public void archiveAddress (Database db) throws CprException {
		
		boolean recordNotFound =false;
		boolean alreadyArchived = false;
		
		final Session session = db.getSession();
		final Addresses bean = getAddressesBean();
		String sqlQuery = null;
		Query query = null;

		if (bean.getDocumentTypeKey() == null) {
			sqlQuery = "from Addresses where personId = :person_id AND dataTypeKey = :data_type_key AND groupId = :group_id";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			query.setParameter("group_id", bean.getGroupId());
		}
		else
		{
			sqlQuery = "from Addresses where personId = :person_id AND dataTypeKey = :data_type_key AND  documentTypeKey = :document_type_key AND groupId = :group_id";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			query.setParameter("document_type_key", bean.getDocumentTypeKey());
			query.setParameter("group_id", bean.getGroupId());
		}
		if (query.list().size() > 0) {
			// Check to see if an active record exists for the user and specified address type.
			sqlQuery += " and endDate is NULL";
			if (bean.getDocumentTypeKey() == null) {
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());
				query.setParameter("group_id", bean.getGroupId());
			}
			else {
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());
				query.setParameter("document_type_key", bean.getDocumentTypeKey());
				query.setParameter("group_id", bean.getGroupId());
			}
			final Iterator<?> it= query.list().iterator();
			// Expire existing one
			if (it.hasNext()) {
				// check to see if an active record exists for the user and specified address type


				Addresses dbBean = (Addresses) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				dbBean.setPrimaryFlag("N");
				session.update(dbBean);
				session.flush();

			}
			else {
				alreadyArchived= true;		
			}
		}
		else {
			recordNotFound = true;
		}	
		
		// Handle the errors.   No record found to archive
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, TABLE_NAME);
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, TABLE_NAME);
		}
		
	}
	
	/**
	 * This routine will obtain a list of addresses for a person id
	 * @param db contains the Database object
	 * @param personId   contains the personID
	 * 
	 * @return list of addresses
	 */
	public AddressReturn[] getAddress(Database db, long personId) {
		

		final Session session = db.getSession();
		final List<AddressReturn> results = new ArrayList<AddressReturn>();

		final StringBuffer sb = new StringBuffer(BUFFER_SIZE);

		sb.append("SELECT addresses.data_type_key, addresses.document_type_key, addresses.group_id,");
		sb.append("addresses.primary_flag,addresses.address1, addresses.address2, addresses.address3, ");
		sb.append("addresses.city, addresses.state, addresses.postal_code, addresses.province, verified_flag, ");
		sb.append("addresses.start_date, ");
		sb.append("addresses.end_date, ");
		sb.append("addresses.last_update_by, " );
		sb.append("addresses.last_update_on, ");
		sb.append("addresses.created_by, " );
		sb.append("addresses.created_on, ");
		sb.append("campus_cs.campus_code, campus_cs.campus, ");
		sb.append("country.country_code_three, country.country ");
		sb.append("FROM addresses ");
		sb.append("LEFT JOIN campus_cs ON addresses.campus_code_key = campus_cs.campus_code_key ");
		sb.append("LEFT JOIN country ON addresses.country_key = country.country_key ");
		sb.append("WHERE addresses.person_id = :person_id_in ");

		if (getAddressType() != null) {
			sb.append("AND addresses.data_type_key = :data_type_key_in ");
		}

		// If we are not returning all records, we need to just return the active ones.
		if (! isReturnHistoryFlag()) {
			sb.append("AND addresses.end_date IS NULL ");
		}		
		sb.append("ORDER BY addresses.data_type_key ASC, addresses.start_date ASC ");

		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("person_id_in", personId);

		if (getAddressType() != null) {
			query.setParameter("data_type_key_in", getAddressType().index());
		}
		query.addScalar("data_type_key", StandardBasicTypes.LONG);
		query.addScalar("document_type_key", StandardBasicTypes.LONG);
		query.addScalar("group_id", StandardBasicTypes.LONG);
		query.addScalar("primary_flag", StandardBasicTypes.STRING);
		query.addScalar("address1", StandardBasicTypes.STRING);
		query.addScalar("address2", StandardBasicTypes.STRING);
		query.addScalar("address3", StandardBasicTypes.STRING);
		query.addScalar("city", StandardBasicTypes.STRING);
		query.addScalar("state", StandardBasicTypes.STRING);
		query.addScalar("postal_code", StandardBasicTypes.STRING);
		query.addScalar("province", StandardBasicTypes.STRING);
		query.addScalar("verified_flag", StandardBasicTypes.STRING);
		query.addScalar("start_date",StandardBasicTypes.TIMESTAMP );
		query.addScalar("end_date",StandardBasicTypes.TIMESTAMP );
		query.addScalar("last_update_by",StandardBasicTypes.STRING );
		query.addScalar("last_update_on",StandardBasicTypes.TIMESTAMP );
		query.addScalar("created_by",StandardBasicTypes.STRING );
		query.addScalar("created_on",StandardBasicTypes.TIMESTAMP );
		query.addScalar("campus_code", StandardBasicTypes.STRING);
		query.addScalar("campus", StandardBasicTypes.STRING);
		query.addScalar("country_code_three", StandardBasicTypes.STRING);
		query.addScalar("country", StandardBasicTypes.STRING);

		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			Object res[] = (Object []) it.next();

			AddressReturn anAddress = new AddressReturn();
			anAddress.setAddressType(AddressType.get((Long) res[ADDRESS_TYPE]).toString());
			if (res[1] != null) {
				anAddress.setDocumentType(DocumentType.get((Long) res[DOCUMENT_TYPE]).toString());
			}
			else {
				anAddress.setDocumentType(null);
			}
			anAddress.setGroupId((Long) res[GROUP_ID]);
			anAddress.setPrimaryFlag((String) res[PRIMARY_FLAG]);
			anAddress.setAddress1((String) res[ADDRESS1]);
			anAddress.setAddress2((String) res[ADDRESS2]);
			anAddress.setAddress3((String) res[ADDRESS3]);
			anAddress.setCity((String) res[CITY]);
			String tempState = (String) res[STATE]; 
			anAddress.setPostalCode((String) res[POSTAL_CODE]);
			String tempProvince = ((String) res[PROVINCE]);
			anAddress.setVerifiedFlag((String) res[VERIFIED_FLAG]);
			anAddress.setStartDate(Utility.convertTimestampToString((Date) res[START_DATE]));
			anAddress.setEndDate(Utility.convertTimestampToString((Date) res[END_DATE]));
			anAddress.setLastUpdateBy((String) res[LAST_UPDATE_BY]);
			anAddress.setLastUpdateOn(Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]));
			anAddress.setCreatedBy((String) res[CREATED_BY]);
			anAddress.setCreatedOn(Utility.convertTimestampToString((Date) res[CREATED_ON]));
			anAddress.setCampusCode((String) res[CAMPUS_CODE]);
			anAddress.setCampusName((String) res[CAMPUS_NAME]);
			anAddress.setCountryCode((String) res[COUNTRY_CODE]);
			anAddress.setCountryName((String) res[COUNTRY_NAME]);

			if (tempState != null) {
				anAddress.setStateOrProvince(tempState);
			}
			else if (tempProvince != null) {
				anAddress.setStateOrProvince(tempProvince);
			}
			else {
				anAddress.setStateOrProvince(null);
			}

			results.add(anAddress);
		}


		return results.toArray(new AddressReturn[results.size()]);
	}

	/**
	 * This routine will set a primary address within a address type for a person id
	 * @param db contains the Database object
	 *
	 * 
	 * @throws CprException 
	 * 
	 */
	public void setPrimaryByType(Database db) throws CprException {
		
		boolean notFound = false;
		boolean alreadyPrimary = false;
	
		final Session session = db.getSession();
		final Addresses bean = getAddressesBean();
		SQLQuery query = null;
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		if (bean.getDocumentTypeKey() == null) {
			sb.append("SELECT  primary_flag ");
			sb.append("FROM addresses ");
			sb.append("WHERE person_id = :person_id_in ");
			sb.append("AND data_type_key = :data_type_key ");
			sb.append("AND group_id = :group_id ");
			sb.append("AND end_date IS NULL ");
			query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			query.setParameter("group_id",bean.getGroupId());
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
		}
		else {

			sb.append("SELECT  primary_flag ");
			sb.append("FROM addresses ");
			sb.append("WHERE person_id = :person_id_in ");
			sb.append("AND data_type_key = :data_type_key ");
			sb.append("AND document_type_key = :document_type_key ");
			sb.append("AND group_id = :group_id ");
			sb.append("AND end_date IS NULL ");
			query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			query.setParameter("document_type_key", bean.getDocumentTypeKey());
			query.setParameter("group_id",bean.getGroupId());
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
		}
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
				String sqlQuery  = null;
				Query query1 = null;
				if (bean.getDocumentTypeKey() == null) {
					sqlQuery = "from Addresses where personId = :person_id and dataTypeKey = :data_type_key and primaryFlag = 'Y' and endDate is null";
					query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id", bean.getPersonId());
					query1.setParameter("data_type_key", bean.getDataTypeKey());	
				}
				else{
					sqlQuery = "from Addresses where personId = :person_id and dataTypeKey = :data_type_key and documentTypeKey = :document_type_key and primaryFlag = 'Y' and endDate is null";
					query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id", bean.getPersonId());
					query1.setParameter("data_type_key", bean.getDataTypeKey());	
					query1.setParameter("document_type_key", bean.getDocumentTypeKey());	
				}			
				it = query1.list().iterator();
				while (it.hasNext()) {
					Addresses dbBean = (Addresses) it.next();
					dbBean.setPrimaryFlag("N");
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
				if (bean.getDocumentTypeKey() == null) {
					sqlQuery = "from Addresses where personId = :person_id and dataTypeKey = :data_type_key and groupId = :group_id and endDate IS NULL";
					query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id", bean.getPersonId());
					query1.setParameter("data_type_key", bean.getDataTypeKey());
					query1.setParameter("group_id", bean.getGroupId());
				}
				else {

					sqlQuery = "from Addresses where personId = :person_id and dataTypeKey = :data_type_key and documentTypeKey = :document_type_key and groupId = :group_id and endDate IS NULL";
					query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id", bean.getPersonId());
					query1.setParameter("data_type_key", bean.getDataTypeKey());
					query1.setParameter("document_type_key", bean.getDocumentTypeKey());
					query1.setParameter("group_id", bean.getGroupId());
				}
				it = query1.list().iterator();
				if (it.hasNext()) {
					Addresses dbBean = (Addresses) it.next();
					dbBean.setPrimaryFlag("Y");
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();
				}
			}
		}		
	
		if (notFound) {
			LOG4J_LOGGER.info("AddressTable:Set primary not found" );
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "address");
		}
	
		if (alreadyPrimary) {
			LOG4J_LOGGER.info("AddressTable:Set primary already primary" );
			throw new CprException(ReturnType.SET_PRIMARY_FAILED_EXCEPTION, "address");
		}
	}
	/**
	 * The purpose of this routine is to interface with the database with hibernate  to 
	 * update an  address to a user's record.  The information necessary to update 
	 * the address is passed in the AddressesTable class.
	 * @param db contains the Database object
	 * 
	 * @throws CprException 
	 */
	public void updateAddress(Database db) throws CprException {
		
		int updateCount =0;
		boolean matchFound = false;
		final Session session = db.getSession();
		final Addresses bean = getAddressesBean();
		String sqlQuery = null;
		Query query = null;
		if (bean.getDocumentTypeKey() == null) {
			sqlQuery = "from Addresses where personId = :person_id AND dataTypeKey = :data_type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
		}
		else
		{
			sqlQuery = "from Addresses where personId = :person_id AND dataTypeKey = :data_type_key AND documentTypeKey = :document_type_key AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			query.setParameter("document_type_key", bean.getDocumentTypeKey());
		}


		for (final Iterator<?> it = query.list().iterator(); it.hasNext() &&  (! matchFound); ) {
			Addresses dbBean = (Addresses) it.next();

			// Check to ensure that the fields are not already there
			// do we need to check campus code and country code

			if (db.areStringFieldsEqual(dbBean.getAddress1(), bean.getAddress1()) &&
					db.areStringFieldsEqual(dbBean.getAddress2(), bean.getAddress2())	 &&
					db.areStringFieldsEqual(dbBean.getAddress3(), bean.getAddress3()) &&
					db.areStringFieldsEqual(dbBean.getCity(), bean.getCity()) &&
					db.areStringFieldsEqual(dbBean.getState(), bean.getState()) && 
					db.areStringFieldsEqual(dbBean.getProvince(), bean.getProvince()) &&
					db.areStringFieldsEqual(dbBean.getPostalCode(), bean.getPostalCode()) &&
					db.areLongFieldsEqual(dbBean.getCampusCodeKey(), bean.getCampusCodeKey()) && 
					db.areLongFieldsEqual(dbBean.getCountryKey(), bean.getCountryKey()))
			{

				matchFound = true;	
			}

		}
		// If no match is found, find the maximum group id for the person and their address type combination

		if (! matchFound) {
			if (bean.getDocumentTypeKey() == null) {
				sqlQuery ="from Addresses where personId = :person_id and dataTypeKey = :data_type_key and groupId = :group_id and endDate IS NULL";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());
				query.setParameter("group_id", bean.getGroupId());
			}
			else {
				sqlQuery ="from Addresses where personId = :person_id and dataTypeKey = :data_type_key AND groupId = :group_id  AND documentTypeKey = :document_type_key AND endDate IS NULL";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());
				query.setParameter("document_type_key", bean.getDocumentTypeKey());
				query.setParameter("group_id", bean.getGroupId());
			}
			final Iterator<?> it = query.list().iterator();
			// Expire the existing address
			if (it.hasNext()) {
				Addresses dbBean = (Addresses) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				dbBean.setPrimaryFlag("N");
				session.update(dbBean);
				session.flush();
				updateCount++;

			}
			else
			{
				LOG4J_LOGGER.info("AddressTable:Update query for record to update failed, GroupID:" +bean.getGroupId() + " personid:" + bean.getPersonId() + " data type key: " 
						+bean.getDataTypeKey());
			}
			// add the new address


		}
		// save off the new record
		if (updateCount > 0) {

			if (MatchingAlgorithmType.valueOf(CprProperties.INSTANCE.getProperties().getProperty(
					CprPropertyName.CPR_MATCHING_ALGORITHM.toString())) == MatchingAlgorithmType.PENN_STATE) {
				getAddressCityMatchCode(bean);
			}

			session.save(bean);
			session.flush();
		}
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "Addresses");
		}
		if (updateCount == 0){
			LOG4J_LOGGER.info("AddressTable:Update failed because no records where updated");
			throw new CprException(ReturnType.UPDATE_FAILED_EXCEPTION, "address ");
		}	
	}
	
	
	/**
	 * This routine is used to return an address that will be used by the match code server.  At this time it consists
	 * of address1, address2 and address3 concatenated together.
	 * 
	 * @return string containing the address.
	 */
	public String getMatchAddress() {
		return null;
	}
	
	/**
	 * This routine is used to return city that will be used by the match code server.  At this time it consists
	 * of city.
	 * 
	 * @return string containing the city.
	 */
	public String getMatchCity() {
		return null;
	}
	
	/**
	 * This routine is used to obtain match codes for a name.
	 * @param bean contains the database bean.
	 */
	public void getAddressCityMatchCode(Addresses bean) {
	}
}
