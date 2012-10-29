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
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
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
	

	/** Instance of logger */
	private static final Logger Log4jLogger = Logger.getLogger(ServiceCore.class);
	
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
	 */
	public AddressesTable (long personId, 
			String addressTypeString, 
			String documentType,
			Long groupId, 
			String updatedBy)  {
		this(personId, addressTypeString, documentType, groupId,  updatedBy,  null, null, null, null, null, null, null, null, null, null, null, null);
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
			String countryThreeCharCode) {
		this(personId, addressTypeString, documentType, null, updatedBy,  address1, address2, address3, city,state, postalCode, province, countryCodeId, campusCodeId,countryName,campusName, countryThreeCharCode);
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
			String countryThreeCharCode)  {
		super();
		
		setAddressType(addressTypeString);
		if (documentType != null && documentType.trim().length() > 0) {
			setDocumentType(documentType);
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
	 * @param addressTypeString the addressTypeString to set
	 */
	public final void setAddressType(String addressTypeString){
		
		setAddressType(AddressType.valueOf(addressTypeString.toUpperCase().trim()));
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
	 * @throws Exception will be thrown if there are any problems.
	 */
	public final void setDocumentType(String documentType) {
		setDocumentType(DocumentType.valueOf(documentType.toUpperCase().trim()));
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
		try {
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
			
			if (MatchingAlgorithmType.valueOf(CprProperties.getInstance().getProperties().getProperty(
					CprPropertyName.CPR_MATCHING_ALGORITHM.toString())) == MatchingAlgorithmType.PENN_STATE) {
				getAddressCityMatchCode(bean);
			}
			
			session.save(bean);
			session.flush();
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "address");
		}
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "Addresses");
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
		
		try {
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
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "address");
		}
		
		// Handle the errors.   No record found to archive
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "address");
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "address");
		}
		
	}
	
	/**
	 * This routine will obtain a list of addresses for a person id
	 * @param db contains the Database object
	 * @param personId   contains the personID
	 * 
	 * @return list of addresses
	 * 
	 * 
	 * @throws CprException 
	 * @throws GeneralDatabaseException
	 * 
	 */
	public AddressReturn[] getAddress(Database db, long personId)
			throws GeneralDatabaseException {
		
		try {
			
			final Session session = db.getSession();
			final List<AddressReturn> results = new ArrayList<AddressReturn>();
			
			final StringBuffer sb = new StringBuffer(8000);
			
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
				anAddress.setAddressType(AddressType.get((Long) res[0]).toString());
				if (res[1] != null) {
					anAddress.setDocumentType(DocumentType.get((Long) res[1]).toString());
				}
				else {
					anAddress.setDocumentType(null);
				}
				anAddress.setGroupId((Long) res[2]);
				anAddress.setPrimaryFlag((String) res[3]);
				anAddress.setAddress1((String) res[4]);
				anAddress.setAddress2((String) res[5]);
				anAddress.setAddress3((String) res[6]);
				anAddress.setCity((String) res[7]);
				String tempState = (String) res[8]; 
				anAddress.setPostalCode((String) res[9]);
				String tempProvince = ((String) res[10]);
				anAddress.setVerifiedFlag((String) res[11]);
				anAddress.setStartDate(Utility.convertTimestampToString((Date) res[12]));
				anAddress.setEndDate(Utility.convertTimestampToString((Date) res[13]));
				anAddress.setLastUpdateBy((String) res[14]);
				anAddress.setLastUpdateOn(Utility.convertTimestampToString((Date) res[15]));
				anAddress.setCreatedBy((String) res[16]);
				anAddress.setCreatedOn(Utility.convertTimestampToString((Date) res[17]));
				anAddress.setCampusCode((String) res[18]);
				anAddress.setCampusName((String) res[19]);
				anAddress.setCountryCode((String) res[20]);
				anAddress.setCountryName((String) res[21]);
				
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
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve address for person identifier = " + personId);			
		}
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
	
		try {
			final Session session = db.getSession();
			final Addresses bean = getAddressesBean();
			SQLQuery query = null;
			final StringBuilder sb = new StringBuilder(512);
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
		}	
		catch (Exception e) {
			Log4jLogger.info("AddressTable:Set primary Exception" );
			throw new CprException(ReturnType.SET_PRIMARY_FAILED_EXCEPTION, "address");
		}
	
		if (notFound) {
			Log4jLogger.info("AddressTable:Set primary not found" );
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "address");
		}
	
		if (alreadyPrimary) {
			Log4jLogger.info("AddressTable:Set primary already primary" );
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
		try {
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
					Log4jLogger.info("AddressTable:Update query for record to update failed, GroupID:" +bean.getGroupId() + " personid:" + bean.getPersonId() + " data type key: " 
							+bean.getDataTypeKey());
				}
				// add the new address
		
				
			}
			// save off the new record
			if (updateCount > 0) {
				
				if (MatchingAlgorithmType.valueOf(CprProperties.getInstance().getProperties().getProperty(
						CprPropertyName.CPR_MATCHING_ALGORITHM.toString())) == MatchingAlgorithmType.PENN_STATE) {
					getAddressCityMatchCode(bean);
				}
				
				session.save(bean);
				session.flush();
			}
			
		}
		catch (Exception e) {
			throw new CprException(ReturnType.UPDATE_FAILED_EXCEPTION, "address");
		}
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "Addresses");
		}
		if (updateCount == 0){
			Log4jLogger.info("AddressTable:Update failed because no records where updated");
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
