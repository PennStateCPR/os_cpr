/* SVN FILE: $Id: IdCardTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;


import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PersonIdCard;
import edu.psu.iam.cpr.core.database.beans.PersonPhoto;
import edu.psu.iam.cpr.core.database.types.IdCardType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.PersonIdCardNumberReturn;
import edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 **
 * This class provides an implementation for interfacing with the person id card
 * database table.  This table contains id card information for people within in the
 * CPR.  This class provides methods to add, update and get id card information
 * for persons in the CPR.
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
* @package put the correct package name here.
* @author $Author: jvuccolo $
* @version $Rev: 5340 $
* @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
*/
public class IdCardTable {

	/** person Id Card bean. */
	private PersonIdCard personIdCardBean;
	
	/** the id cardtype enumerated type */
	private IdCardType idCardType;
	
	/** Flag used by the Get service to indicate whether to return a complete history or not */
	private boolean returnHistoryFlag;
	

	/**
	 * contains the personPhotoBean
	 * 
	 */
	
	private  PersonPhoto personPhotoBean;
	/**
	 * @return the personIdCardBean
	 */
	public PersonIdCard getPersonIdCardBean() {
		return personIdCardBean;
	}

	/**
	 * @param personIdCardBean the personIdCardBean to set
	 */
	public final void setPersonIdCardBean(PersonIdCard personIdCardBean) {
		this.personIdCardBean = personIdCardBean;
	}

	/**
	 * @return the idCardType
	 */
	public IdCardType getIdCardType() {
		return idCardType;
	}

	/**
	 * @param idCardType the idCardType to set
	 */
	public final void setIdCardType(IdCardType idCardType) {
		this.idCardType = idCardType;
	}

	/**
	 * @return the personPhotoBean
	 */
	public PersonPhoto getPersonPhotoBean() {
		return personPhotoBean;
	}

	/**
	 * @param personPhotoBean the personPhotoBean to set
	 */
	public final void setPersonPhotoBean(PersonPhoto personPhotoBean) {
		this.personPhotoBean = personPhotoBean;
	}

	/**
	 * 
	 */
	public IdCardTable() {
		super();
		
	}
	/**
	 * Constructor for ArchiveIdCard
	 * @param personId contains the person identifier from the CPR.
	 * @param idCardTypeString contains the string representation of the Id Card Type
	 * @param updatedBy contains the updatedBy system identifier.
	 */
	public IdCardTable( long personId, 
			String idCardTypeString,
			String updatedBy) {
		this(personId, idCardTypeString, updatedBy, null, null, null, null);
		
	}
	/**
	 * Constructor for AddIdCard no photo
	 * @param personId contains the person identifier from the CPR.
	 * @param idCardTypeString contains the string representation of the Id Card Type
	 * @param idCardNumber contains the id card number
	 * @param idSerialNumber contains the id serial number
	 * @param updatedBy contains the updatedBy system identifier.
	 * 
	 */
	public IdCardTable( long personId, 
			String idCardTypeString,
			String updatedBy,
			String idCardNumber,
			String idSerialNumber) {
		this(personId, idCardTypeString, updatedBy, idCardNumber, idSerialNumber, null, null);
		
	}
	/**
	 * Constructor for Add IdCard with photo id
	 * @param personId contains the person identifier from the CPR.
	 * @param idCardTypeString contains the string representation of the Id Card Type
	 * @param updatedBy contains the updatedBy system identifier.
	 * @param idCardNumber contains the id card number
	 * @param idSerialNumber contains the id serial number
	 * @param photo contains the JPEG photo.
	 * @param datePhotoTaken contains the string representation of the date the photo was taken.
	 * 
	 */
	public IdCardTable( long personId, 
			String idCardTypeString,
			String updatedBy,
			String idCardNumber,
			String idSerialNumber,
			byte[] photo,
			Date datePhotoTaken) {
		super();
		/*  setup the Person Id Card Bean 
		 * 
		 */
		setIdCardType(idCardTypeString);
		final PersonIdCard bean = new PersonIdCard();
		
		final Date d = new Date();
		bean.setPersonId(personId);
		bean.setDataTypeKey(getIdCardType().index());
		bean.setIdCardNumber(idCardNumber);
		bean.setIdSerialNumber(idSerialNumber);
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);	
		setPersonIdCardBean(bean);
		/*
		 * setup the PersonPhoto bean
		 */
		if (photo !=null) {
			final PersonPhoto photoBean  = new PersonPhoto();
			photoBean.setPersonId(personId);
			photoBean.setPhoto(photo);
			photoBean.setDateTaken(datePhotoTaken);
			photoBean.setCreatedBy(updatedBy);
			photoBean.setCreatedOn(d);
		
			photoBean.setLastUpdateBy(updatedBy);
			photoBean.setLastUpdateOn(d);
			setPersonPhotoBean(photoBean);
		}
		else {
			setPersonPhotoBean(null);
		}
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
	 * @param idCardTypeString the idCardTypeString to set
	 */
	public final void setIdCardType(String idCardTypeString){
		
		setIdCardType(IdCardType.valueOf(idCardTypeString.toUpperCase().trim()));
	}
	/**
	 * This purpose of this routins is to interface with the database with hibernate to 
	 * add a PersonIdCard and possible a PersonPhoto card associated with a person_id to the
	 * database
	 * 
	 * 
	 * @param db
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	public void addIdCard (Database db) throws  CprException{
		
		PersonIdCard existingPersonIdPlusCardBean  = null;
		PersonIdCard existingPersonIdCardBean = null;
		PersonIdCard saveDuplicatePersonIdCardBean = null;
		boolean idPlusCard = true;
		boolean matchFound = false;
		boolean invalidIdCardTypesFound = false;
		try {
			
			PersonPhoto dbExistingPhotoBean = null;
			final Session session =db.getSession();
			final PersonIdCard bean = getPersonIdCardBean();
			final PersonPhoto photoBean = getPersonPhotoBean();
			String sqlQuery = null;
			Query query = null;
			Iterator<?>it = null;
			if (photoBean != null)	{
				// Perform a query to determine if a photo already exists for the person.
				sqlQuery = "from PersonPhoto where personId = :person_id_in";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id_in", photoBean.getPersonId());
				 it = query.list().iterator();
			
				// Photo exists - update the bean
				if (it.hasNext()) {
					dbExistingPhotoBean = (PersonPhoto) it.next();	
					dbExistingPhotoBean.setDateTaken(photoBean.getDateTaken());
					dbExistingPhotoBean.setPhoto(photoBean.getPhoto());
					dbExistingPhotoBean.setLastUpdateBy(photoBean.getLastUpdateBy());
					dbExistingPhotoBean.setLastUpdateOn(photoBean.getLastUpdateOn());
				}
			}
		
			//check to see if the person has any id card
			sqlQuery = "from PersonIdCard where personId = :person_id AND endDate IS NULL";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());

			it = query.list().iterator();
			// are there any active card records for this person
			if (! it.hasNext()) {
				// no active id cards
				session.save(bean);
				session.flush();
				if (photoBean != null) {
					if (dbExistingPhotoBean != null ) {
						session.update(dbExistingPhotoBean);
						session.flush();
					}
					else 
					{
						// Photo does not exist, so we need to do an add.
						session.save(photoBean);
						session.flush();
					}
				}
			}
			else 
			{	
				// find the existing id card records
				for ( it=query.list().iterator(); it.hasNext();) {
					PersonIdCard tempPersonIdCardBean = (PersonIdCard) it.next();
					
					if (tempPersonIdCardBean.getDataTypeKey().equals(bean.getDataTypeKey())) {
						if (saveDuplicatePersonIdCardBean != null ) {
							invalidIdCardTypesFound = true;
						}
						else 
						{
							saveDuplicatePersonIdCardBean= tempPersonIdCardBean;
						}
					}
					if (tempPersonIdCardBean.getDataTypeKey().longValue() >= IdCardType.ID_CARD_ID_CARD_HEALTH_SERVICES_PHOTO_ID.index() && tempPersonIdCardBean.getDataTypeKey().longValue() <= IdCardType.ID_CARD_ID_CARD_ARL_PHOTO_ID.index()) {
						if (existingPersonIdCardBean != null) {
							//this is an error
							invalidIdCardTypesFound = true;
						}
						else 
						{
							existingPersonIdCardBean = tempPersonIdCardBean ;
						}
					}
					else 
					{
						if (existingPersonIdPlusCardBean != null) {
							// this is a error
							invalidIdCardTypesFound = true;
						}
						else 
						{
							existingPersonIdPlusCardBean = tempPersonIdCardBean;
						}
					}
				}
				
				// not id card of type in the cpr
				if (!invalidIdCardTypesFound) {
					if (bean.getDataTypeKey().longValue() >= IdCardType.ID_CARD_ID_CARD_HEALTH_SERVICES_PHOTO_ID.index() && 
							bean.getDataTypeKey().longValue() <= IdCardType.ID_CARD_ID_CARD_ARL_PHOTO_ID.index()) {
						idPlusCard = false;
					}
					if (saveDuplicatePersonIdCardBean == null) {
						if ((idPlusCard && existingPersonIdPlusCardBean == null) || (!idPlusCard && existingPersonIdCardBean == null)) {
							// this is an instances of a new id card just add it
					
							session.save(bean);
							session.flush();
							if ( photoBean != null) {
								if (dbExistingPhotoBean != null) {
									session.update(dbExistingPhotoBean);
									session.flush();
								}
								else 
								{
								// Photo does not exist, so we need to do an add.
									session.save(photoBean);
									session.flush();
								}	 
							}
						}
						else 
						{
							//archive the existing id card type and add a new one
							final PersonIdCard dbPersonIdCardBean = idPlusCard ? existingPersonIdPlusCardBean : existingPersonIdCardBean ;
							dbPersonIdCardBean.setEndDate(bean.getLastUpdateOn());
							dbPersonIdCardBean.setLastUpdateBy(bean.getLastUpdateBy());
							dbPersonIdCardBean.setLastUpdateOn(bean.getLastUpdateOn());
							session.update(dbPersonIdCardBean);
							session.save(bean);
							session.flush();
							if ( bean != null) {
								if (dbExistingPhotoBean != null)  {
									session.update(dbExistingPhotoBean);
									session.flush();
								}
								else 
								{
									// Photo does not exist, so we need to do an add.
									session.save(photoBean);
									session.flush();
								}	 
							}
						}
					}
					else	
					{
					// id card of type exist
						// check to see if this is duplicate Person id card data
						if (db.areStringFieldsEqual(saveDuplicatePersonIdCardBean.getIdCardNumber(), bean.getIdCardNumber()) &&
							db.areStringFieldsEqual(saveDuplicatePersonIdCardBean.getIdSerialNumber(), bean.getIdSerialNumber()) ){
						// a photo is included on the add check for duplicate data
							if ( photoBean != null) {
								if (  dbExistingPhotoBean !=null && 
									(db.areStringFieldsEqual(Utility.convertDateToString(dbExistingPhotoBean.getDateTaken()), Utility.convertDateToString(personPhotoBean.getDateTaken())))) {
									// the record is a duplicate throw an error
									matchFound = true;		
								}
								else 
								{
									// this is a new photo update id card and add/update photo
									saveDuplicatePersonIdCardBean.setLastUpdateBy(bean.getLastUpdateBy());
									saveDuplicatePersonIdCardBean.setLastUpdateOn(bean.getLastUpdateOn());
									session.update( saveDuplicatePersonIdCardBean);
									session.flush();
									session.save(bean);
									session.flush();
									// Perform a query to determine if a photo already exists for the person.
									if (dbExistingPhotoBean != null) {
										session.update(dbExistingPhotoBean);
										session.flush();
									}
									else 
									{
										// Photo does not exist, so we need to do an add.
										session.save(photoBean);
										session.flush();
									}	
								}					
							}
							else 
							{
								// there is no Id photo passed
								matchFound = true;
							}
						}
						
						else 
						{
						// the person id card is not a duplicate  archive and add the new one.
						// if a photo is passed in update the photo also
						// this is a new photo update id card and add/update photo
							saveDuplicatePersonIdCardBean.setLastUpdateBy(bean.getLastUpdateBy());
							saveDuplicatePersonIdCardBean.setLastUpdateOn(bean.getLastUpdateOn());
							session.update( saveDuplicatePersonIdCardBean);
							session.flush();
							session.save(bean);
							session.flush();
							if ( photoBean != null) {
								if (dbExistingPhotoBean != null) {
									session.update(dbExistingPhotoBean);
									session.flush();
								}
								else 
								{
									// Photo does not exist, so we need to do an add.
									session.save(photoBean);
									session.flush();
								}	 
							}
						}	
					}		
				}
			}
		}	
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "Id Card");
		}
		if (matchFound) {
			throw new CprException(ReturnType.RECORD_ALREADY_EXISTS, "Id Card");
		}
		if (invalidIdCardTypesFound) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "Id Card");
		}
	}
	/**
	 * The purpose of this routine is to archive id Card data associated with a person id.
	 * 
	 * @param db
	 * @throws GeneralDatabaseException
	 * @throws CprException
	 */
	public void archiveIdCard (Database db) throws  CprException {
		boolean recordNotFound = false;
		boolean alreadyArchived = false;
		try {
			final Session session = db.getSession();
			final PersonIdCard bean = getPersonIdCardBean();
			String sqlQuery = null;
			Query query = null;
			
		
			sqlQuery = "from PersonIdCard where personId = :person_id AND dataTypeKey = :data_type_key ";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("data_type_key", bean.getDataTypeKey());
			Iterator<?> it = query.list().iterator();
			
			if (it.hasNext()) {
				// Check to see if an active record exists for the user and specified address type.
				sqlQuery += " and endDate is NULL";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("data_type_key", bean.getDataTypeKey());

				it = query.list().iterator();
				if (it.hasNext()) {
					PersonIdCard personIdCardDbBean = (PersonIdCard) it.next();
					personIdCardDbBean.setEndDate(bean.getLastUpdateOn());
					personIdCardDbBean.setLastUpdateBy(bean.getLastUpdateBy());
					personIdCardDbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(personIdCardDbBean);
					session.flush();
					}
				else
				{
					alreadyArchived = true;
				}
			}
			else 
			{
				recordNotFound=true;
			}

		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "Id Card");
		}

		// Handle the error cases.
		if (recordNotFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "ID Card");
		}
		
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "Id Card");
		}					
		

	}
	/**
	 * This routine is used to obtain id card information for a person.
	 * @param db contains the database connection object.
	 * @param personId contains the person identifier to do the query for.
	 * @return PersonIdCardReturn array.
	 * @throws GeneralDatabaseException will be thrown if there are any problems.
	 */
	public PersonIdCardReturn[] getIdCardForPersonId(Database db, long personId) 
		throws GeneralDatabaseException {
		
		try {
			
			final ArrayList <PersonIdCardReturn> results = new ArrayList<PersonIdCardReturn> ();
			final Session session = db.getSession();
			final StringBuilder sb = new StringBuilder(2048);
			sb.append("SELECT data_type_key, id_card_number, id_serial_number, " );
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("from person_id_card ");
			sb.append("WHERE person_id=:person_id ");

			if (getIdCardType() != null) {
				sb.append("AND data_type_key = :data_type_key_in ");
			}
			
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			
			sb.append("ORDER BY data_type_key ASC, start_date ASC ");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id", personId);
			
			if (getIdCardType() != null) {
				query.setParameter("data_type_key_in", getIdCardType().index());
			}
			
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("id_card_number", StandardBasicTypes.STRING);
			query.addScalar("id_serial_number", StandardBasicTypes.STRING);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);
			for (final Iterator<?> it=query.list().iterator(); it.hasNext();) {
				Object res[] = (Object []) it.next();
				PersonIdCardReturn personIdCardReturn = new PersonIdCardReturn();
				personIdCardReturn.setIdCardType(IdCardType.get((Long) res[0]).toString());
				personIdCardReturn.setIdCardNumber((String) res[1]);
				personIdCardReturn.setIdSerialNumber((String) res[2]);
				personIdCardReturn.setStartDate(Utility.convertTimestampToString((Date) res[3]));
				personIdCardReturn.setEndDate(Utility.convertTimestampToString((Date) res[4]));
				personIdCardReturn.setLastUpdateBy((String) res[5]);
				personIdCardReturn.setLastUpdateOn(Utility.convertTimestampToString((Date) res[6]));
				personIdCardReturn.setCreatedBy((String) res[7]);
				personIdCardReturn.setCreatedOn(Utility.convertTimestampToString((Date) res[8]));
				results.add(personIdCardReturn);
			}
			return results.toArray(new PersonIdCardReturn[results.size()]);

		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve id card  for person identifier = " + personId);
		}
	}
	/**
	 * This routine is used to obtain id card number for a person.
	 * @param db contains the database connection object.
	 * @param personId contains the person identifier to do the query for.
	 * @return PersonIdCardNumberReturn array.
	 * @throws GeneralDatabaseException will be thrown if there are any problems.
	 */
	public PersonIdCardNumberReturn[] getIdCardNumberForPersonId(Database db, long personId) 
		throws GeneralDatabaseException {
		
		try {
			
			final ArrayList <PersonIdCardNumberReturn> results = new ArrayList<PersonIdCardNumberReturn> ();
			final Session session = db.getSession();
			final StringBuilder sb = new StringBuilder(2048);
			sb.append("SELECT data_type_key, id_card_number " );
			sb.append("from person_id_card ");
			sb.append("WHERE person_id=:person_id ");
			sb.append("AND end_date IS NULL ");
			
			sb.append("ORDER BY data_type_key ASC, start_date ASC ");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id", personId);
			
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("id_card_number", StandardBasicTypes.STRING);
			
			for (final Iterator<?> it=query.list().iterator(); it.hasNext();) {
				Object res[] = (Object []) it.next();
				PersonIdCardNumberReturn personIdCardNumberReturn = new PersonIdCardNumberReturn();
				personIdCardNumberReturn.setIdCardType(IdCardType.get((Long) res[0]).toString());
				personIdCardNumberReturn.setIdCardNumber((String) res[1]);
				
				results.add(personIdCardNumberReturn);
			}
			return results.toArray(new PersonIdCardNumberReturn[results.size()]);

		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve id card  for person identifier = " + personId);
		}
	}
}
