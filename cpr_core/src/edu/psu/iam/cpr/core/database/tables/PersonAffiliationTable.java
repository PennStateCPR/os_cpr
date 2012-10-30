/* SVN FILE: $Id: PersonAffiliationTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

/**
 * This class support the manipulation of PERSON_PSU_AFF_MOD_TYPE tables
 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.rules.engine.RulesEngineHelper;
import edu.psu.iam.cpr.core.rules.engine.RulesReturn;
import edu.psu.iam.cpr.core.service.returns.AffiliationReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 *  This class provides an implementation for interfacing with the PersonAffiliation database
 * table.  There are methods within here to get affiliations (internal, external, all) for a 
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
public class PersonAffiliationTable {

	
	private static final int ALL_AFFILIATION_TYPE = 0;

	private static final int ALL_AFFILIATION = 1;

	private static final int ALL_PRIMARY = 2;

	private static final int ALL_START_DATE = 3;

	private static final int ALL_END_DATE = 4;

	private static final int ALL_LAST_UPDATE_BY = 5;

	private static final int ALL_LAST_UPDATE_ON = 6;

	private static final int ALL_CREATED_BY = 7;

	private static final int ALL_CREATED_ON = 8;

	private static final int EXT_AFF_PRIMARY_FLAG = 0;

	private static final int EXT_AFF_AFFILIATION_TYPE = 1;

	private static final int EXT_AFF_AFFILATION = 2;

	private static final int INT_AFFILIATION_TYPE = 0;

	private static final int INT_AFFILIATION = 1;

	private static final int INT_PRIMARY_FLAG = 2;

	private static final int INT_START_DATE = 3;

	private static final int INT_END_DATE = 4;

	private static final int INT_LAST_UPDATE_BY = 5;

	private static final int INT_LAST_UPDATE_ON = 6;

	private static final int INT_CREATED_BY = 7;

	private static final int INT_CREATED_ON = 8;

	/** 
	 *  personAffiliationBean Contains a reference to the person affiliation database table bean 
	 */
	private PersonAffiliation personAffiliationBean;

	/** 
	 *  affiliationsType contains the affiliations type
	 */
	private AffiliationsType affiliationsType;
	
	/** Flag that indicates whether to return history or not. */
	private boolean returnHistoryFlag;
	
	/**
	 * 
	 */
	public PersonAffiliationTable() {
		super();
	}

	/**
	 * Constructor
	 * @param personId contains the person identifier.
	 * @param affiliationTypeString contains the  affiliation string.
	 * @param updatedBy contains the userid of the entity that is updating record.
	 * @param exceptionFlag contains the exception flag.
	 * @param exceptionComment contains the exception flag.
	 */
	public PersonAffiliationTable(long personId, String affiliationTypeString, 
			String updatedBy, String exceptionFlag, String exceptionComment) {
		super();
		
		final PersonAffiliation bean = new PersonAffiliation();
		setPersonAffiliationBean(bean);
		final Date d = new Date();
		setAffiliationsType(affiliationTypeString);
		
		bean.setPersonId(personId);
		bean.setAffiliationKey(getAffiliationsType().index());
		bean.setPrimaryFlag("N");
		bean.setExceptionComments(exceptionComment);
		bean.setExceptionFlag(exceptionFlag);
		
		bean.setStartDate(d);
		bean.setEndDate(null);
	
		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
	}

	/**
	 * Call to create class when no exception comments are not included with the affiliation data
	 * 
	 * @param personId contains the person identifier.
	 * @param affiliationTypeString contains the  affiliation string.
	 * @param updatedBy contains the userid of the entity that is updating record.
	 */
	public PersonAffiliationTable(long personId, String affiliationTypeString, String updatedBy)  {
		this(personId, affiliationTypeString, updatedBy, "N", null);
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
	 * @param personAffiliationBean the personAffiliationBean to set
	 */
	public final void setPersonAffiliationBean(PersonAffiliation personAffiliationBean) {
		this.personAffiliationBean = personAffiliationBean;
	}

	/**
	 * @return the personAffiliationBean
	 */
	public PersonAffiliation getPersonAffiliationBean() {
		return personAffiliationBean;
	}

	/**
	 * @param affiliationsType the affiliationsType to set
	 */
	public final void setAffiliationsType(AffiliationsType affiliationsType) {
		this.affiliationsType = affiliationsType;
	}

	/**
	 * @return the affiliationsType
	 */
	public AffiliationsType getAffiliationsType() {
		return affiliationsType;
	}

	/**
	 * @param affiliationTypeString the affilationsType to set.
	 * @throws Exception will be thrown if the string cannot be converted to an enum.
	 */
	public final void setAffiliationsType(String affiliationTypeString) {
		setAffiliationsType(AffiliationsType.valueOf(affiliationTypeString.toUpperCase().trim()));
	}

	/**
	 * This method add an affiliation record to the CPR
	 *
	 * @param db contains the Database object
	 * @throws CprException 
	 */
	public void  addAffiliation(Database db) throws  CprException { 

		RulesReturn rulesReturn = null;
		boolean fatalError = false;
		final ArrayList<String> existingAffiliations = new ArrayList<String>();
		try {

			Long anAffiliationKey = null;
			Long newAffiliationKey = null;
			Long oldAffiliationKey = null;
			final Session session = db.getSession();
			final PersonAffiliation bean = getPersonAffiliationBean();

			// Check to see if the person has any affiliations.  If none, add one.
			// This assumes that all affiliations as a lone affiliations
			String sqlQuery = "from PersonAffiliation where personId = :person_id ";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			Iterator<?> it = query.list().iterator();
			if (! it.hasNext()) {
				session.save(bean);
				session.flush();
			}
			else {
				newAffiliationKey = bean.getAffiliationKey();
				String newAffiliationString = AffiliationsType.get(newAffiliationKey).toString();
				// Create an arraylist of all existing affiliations to send to the Rules engine.
				// Select the affiliation_ke for all active affiliations. Store the enum string in the arraylist
				final StringBuilder sb = new StringBuilder(2000);
				sb.append("SELECT affiliation_key from person_affiliation where person_id = :person_id_in  AND end_date IS NULL");
				final SQLQuery query1 = session.createSQLQuery(sb.toString());
				query1.setParameter("person_id_in", bean.getPersonId());
				query1.addScalar("affiliation_key", StandardBasicTypes.LONG);
				it = query1.list().iterator();

				while (it.hasNext()) {
					anAffiliationKey = (Long) it.next();
					if (anAffiliationKey.equals(newAffiliationKey)) {
						//the specified affiliation is already assigned to the person
						// do nothing
						return;
					}
					
					existingAffiliations.add(AffiliationsType.get(anAffiliationKey).toString());
				}
				// pass the existingAffliliations to the rules engine 
				String[] existingArray = new String[existingAffiliations.size()];
				existingArray = existingAffiliations.toArray(existingArray);				
				rulesReturn = new RulesEngineHelper().processRules("rules.drl", existingArray, newAffiliationString);

				if (rulesReturn.getStatusCode() != 0) {
					fatalError = true;
				}
				else
				{
					if  ((rulesReturn.getNumberOfFacts()  <  existingAffiliations.size() )||
							(rulesReturn.getNumberOfFacts()  >  existingAffiliations.size() + 1 )) {
						fatalError = true;
					}
					else 
					{


						//process the returned array
						final List<String> newFacts = new ArrayList<String>(Arrays.asList(rulesReturn.getFacts()));
						final Iterator<String> facts =newFacts.iterator();
						while (facts.hasNext()) {
							String newAffiliation = (String) facts.next();
							Iterator<String> existing = existingAffiliations.iterator();
							while(existing.hasNext()) {
								String oldAffiliation = (String)existing.next();
								if (newAffiliation.equals(oldAffiliation) ) {
									facts.remove();
									existing.remove();
								}
							}
						}
						
						// if both arrays are now empty nothing needs to change
						if (!(existingAffiliations.isEmpty())) {
							// make the change
							if (newFacts.size() != 1 ) {	
								fatalError = true;
							}
							else
							{ 
								// archive the old affiliation
								// store the new affiliations

								if (newFacts.get(0).equals(newAffiliationString)) {
									oldAffiliationKey = AffiliationsType.valueOf((existingAffiliations.get(0)).toUpperCase().trim()).index();
									// archive the affiliation
									sqlQuery = "from PersonAffiliation where personId = :person_id_in  AND affiliationKey = :l_aff_key AND endDate IS NULL";
									query = session.createQuery(sqlQuery);
									query.setParameter("person_id_in", bean.getPersonId());
									query.setParameter("l_aff_key", oldAffiliationKey );
									it = query.list().iterator();
									if (it.hasNext()) {
										PersonAffiliation dbBean = (PersonAffiliation) it.next();
										dbBean.setEndDate(bean.getLastUpdateOn());
										dbBean.setPrimaryFlag("N");
										dbBean.setLastUpdateBy(bean.getLastUpdateBy());
										dbBean.setLastUpdateOn(bean.getLastUpdateOn());
										session.update(dbBean);
										session.flush();
									}

									session.save(bean);
									session.flush();
								}
								else 
								{
									fatalError = true;
								}

							}
						}
						else 
						{
							// add the new affiliation
							session.save(bean);
							session.flush();

						}
					}	

				}	
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "affiliation");
		}

		if (fatalError) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "affiliation");
		}
		
	}
	/**
	 * This method add an affiliation record to the CPR
	 *
	 * @param db contains the Database object
	 * @throws CprException 
	 */
	public void  archiveAffiliation(Database db) throws CprException { 
		
		boolean notFound = false;
		boolean alreadyArchived = false;
		
		
		try {
			final Session session = db.getSession();
			final PersonAffiliation bean = getPersonAffiliationBean();
						
			String sqlQuery = "from PersonAffiliation where personId = :person_id AND affiliationKey = :affiliation_key";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			query.setParameter("affiliation_key", bean.getAffiliationKey());
			if (query.list().size() == 0) {
				notFound = true;
			}
			else {
				sqlQuery = "from PersonAffiliation where personId = :person_id AND affiliationKey = :affiliation_key and endDate is NULL";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id", bean.getPersonId());
				query.setParameter("affiliation_key", bean.getAffiliationKey());
				final Iterator<?> it = query.list().iterator();
				
				if (it.hasNext()) {
					PersonAffiliation dbBean = (PersonAffiliation) it.next();
					dbBean.setEndDate(bean.getLastUpdateOn());
					dbBean.setPrimaryFlag("N");
					dbBean.setLastUpdateBy(bean.getLastUpdateBy());
					dbBean.setLastUpdateOn(bean.getLastUpdateOn());
					session.update(dbBean);
					session.flush();				
				}
				else {
					alreadyArchived = true;
				}
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ARCHIVE_FAILED_EXCEPTION, "affiliation");
		}
		
		if (notFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "affiliation");
		}
		if (alreadyArchived) {
			throw new CprException(ReturnType.ALREADY_DELETED_EXCEPTION, "affiliation");
		}
			
	}
	/**
	 * This method will obtain a list of all PSU affiliations for a person id
	 * @param db contains the Database object
	 * @param personId contains the person id.
	 * 
	 * @return list of PSU Affiliations.
	 * 
	 * @throws GeneralDatabaseException 
	 * 
	 */
	
	public AffiliationReturn[] getAllAffiliationsForPersonId(Database db, long personId) throws GeneralDatabaseException {

		try {
			final ArrayList<AffiliationReturn> results = new ArrayList<AffiliationReturn>();
			final Session session = db.getSession();
			final StringBuilder sb = new StringBuilder(750);
			sb.append("SELECT affiliations.enum_string, affiliations.affiliation, person_affiliation.primary_flag, ");
			sb.append("person_affiliation.start_date, ");
			sb.append("person_affiliation.end_date, ");
			sb.append("person_affiliation.last_update_by, " );
			sb.append("person_affiliation.last_update_on, ");
			sb.append("person_affiliation.created_by, " );
			sb.append("person_affiliation.created_on ");
			sb.append("FROM person_affiliation ");
			sb.append("LEFT JOIN affiliations ");
			sb.append("ON person_affiliation.affiliation_key = affiliations.affiliation_key " );
			sb.append("WHERE person_affiliation.person_id = :person_id_in ");
			if (! isReturnHistoryFlag()) {
				sb.append("AND person_affiliation.end_date IS NULL ");
			}
			
			sb.append("ORDER BY person_affiliation.affiliation_key ASC, person_affiliation.start_date ASC ");
			SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("enum_string", StandardBasicTypes.STRING);
			query.addScalar("affiliation", StandardBasicTypes.STRING);
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
			query.addScalar("start_date",StandardBasicTypes.TIMESTAMP );
			query.addScalar("end_date",StandardBasicTypes.TIMESTAMP );
			query.addScalar("last_update_by",StandardBasicTypes.STRING );
			query.addScalar("last_update_on",StandardBasicTypes.TIMESTAMP );
			query.addScalar("created_by",StandardBasicTypes.STRING );
			query.addScalar("created_on",StandardBasicTypes.TIMESTAMP );
			
			Iterator<?> it = query.list().iterator();
			
		
			while (it.hasNext()) {
				Object res[] = (Object []) it.next();
				AffiliationReturn newAffRet = new AffiliationReturn();
				newAffRet.setAffiliationType((String) res[ALL_AFFILIATION_TYPE]);
				newAffRet.setAffiliation((String)res[ALL_AFFILIATION]);
				newAffRet.setPrimary((String) res[ALL_PRIMARY]);
				newAffRet.setStartDate(Utility.convertTimestampToString((Date) res[ALL_START_DATE]));
				newAffRet.setEndDate(Utility.convertTimestampToString((Date) res[ALL_END_DATE]));
				newAffRet.setLastUpdateBy((String) res[ALL_LAST_UPDATE_BY]);
				newAffRet.setLastUpdateOn(Utility.convertTimestampToString((Date) res[ALL_LAST_UPDATE_ON]));
				newAffRet.setCreatedBy((String) res[ALL_CREATED_BY]);
				newAffRet.setCreatedOn(Utility.convertTimestampToString((Date) res[ALL_CREATED_ON]));
				results.add(newAffRet);
				
			}

			sb.setLength(0);
			sb.append("SELECT DISTINCT ");
			sb.append("person_affiliation.primary_flag, ");
			sb.append("v_ext_affiliation_mapping.ext_affiliation_type, ");
			sb.append("v_ext_affiliation_mapping.ext_affiliation ");
			sb.append("FROM person_affiliation JOIN v_ext_affiliation_mapping ");
			sb.append("ON person_affiliation.affiliation_key = v_ext_affiliation_mapping.affiliation_key ");
			sb.append("AND v_ext_affiliation_mapping.extaff_active_flag = 'Y' ");
			sb.append("AND v_ext_affiliation_mapping.aff_active_flag = 'Y' ");
			sb.append("WHERE person_affiliation.person_id = :person_id_in ");
			sb.append("AND person_affiliation.end_date IS NULL ");
			query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
			query.addScalar("ext_affiliation_type", StandardBasicTypes.STRING);
			query.addScalar("ext_affiliation", StandardBasicTypes.STRING);
			it = query.list().iterator();

			while (it.hasNext()) {
				Object res[] = (Object []) it.next();
				
				AffiliationReturn newAffRet = new AffiliationReturn();
				newAffRet.setPrimary((String) res[EXT_AFF_PRIMARY_FLAG]);
				newAffRet.setAffiliationType((String) res[EXT_AFF_AFFILIATION_TYPE]);
				newAffRet.setAffiliation((String) res[EXT_AFF_AFFILATION]);
				results.add(newAffRet);
				
			}
			
			
			
		return	results.toArray(new AffiliationReturn[results.size()]);
			
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve All Affiliations for person identifier = " + personId);
		}
	}
	/**
	 * This method will obtain a list of PSU Affiliation for a person id.
	 * @param db contains the Database object
	 * @param personId contains the person id.
	 * 
	 * @return list of PSU Affiliations.
	 * 
	 * @throws CprException 
	 
	 */
	public AffiliationReturn[] getExternalAffiliationsForPersonId(Database db, long personId) throws CprException {
		final ArrayList<AffiliationReturn> results = new ArrayList<AffiliationReturn>();
		
		try {
			final Session session = db.getSession();
		
			final StringBuilder sb = new StringBuilder(650);
			sb.append("SELECT affiliations.enum_string, person_affiliation.primary_flag ");
			sb.append("FROM person_affiliation LEFT JOIN affiliations ");
			sb.append("ON person_affiliation.affiliation_key = affiliations.affiliation_key ");
			sb.append("WHERE person_affiliation.person_id = :person_id_in ");
			sb.append("AND person_affiliation.end_date IS NULL ");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("enum_string", StandardBasicTypes.STRING);
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
			final Iterator<?> it = query.list().iterator();

			while (it.hasNext() ) {
				Object res[] = (Object []) it.next();
				
				AffiliationReturn newAffRet = new AffiliationReturn();
				// fix the call  
				// assume eduPerson 
				// need to addess mulitple federation
				newAffRet.setPrimary((String) res[1]);
				newAffRet.setAffiliationType("eduPerson");
				newAffRet.setAffiliation(getEduPersonAffiliation((String) res[0] ));
				results.add(newAffRet);		
				}		
			}
			
		catch (Exception e) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Unable to retrieve PSU Affiliations for person identifier = " + personId);
		}
		
		return	results.toArray(new AffiliationReturn[results.size()]);
	}
	
	/**
	 * This method will obtain a list of PSU Affiliation for a person id.
	 * @param db contains the Database object
	 * @param personId contains the person id.
	 * @return list of PSU Affiliations.
	 * @throws GeneralDatabaseException 
	 */
	public AffiliationReturn[] getInternalAffiliationsForPersonId(Database db, long personId) throws GeneralDatabaseException{

		try {
			final ArrayList<AffiliationReturn> results = new ArrayList<AffiliationReturn>();
			final Session session = db.getSession();

			final StringBuilder sb = new StringBuilder(200);
			
			

			sb.append("SELECT affiliations.enum_string, affiliations.affiliation,person_affiliation.primary_flag, ");
			sb.append("person_affiliation.start_date, ");
			sb.append("person_affiliation.end_date, ");
			sb.append("person_affiliation.last_update_by, " );
			sb.append("person_affiliation.last_update_on, ");
			sb.append("person_affiliation.created_by, " );
			sb.append("person_affiliation.created_on ");
			sb.append("FROM person_affiliation ");
			sb.append("LEFT JOIN affiliations ");
			sb.append("ON person_affiliation.affiliation_key = affiliations.affiliation_key " );
			sb.append("WHERE person_affiliation.person_id = :person_id_in ");
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND person_affiliation.end_date IS NULL ");
			}		
			sb.append("ORDER BY person_affiliation.affiliation_key ASC, person_affiliation.start_date ASC ");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("enum_string", StandardBasicTypes.STRING);
			query.addScalar("affiliation", StandardBasicTypes.STRING);
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
			query.addScalar("start_date",StandardBasicTypes.TIMESTAMP );
			query.addScalar("end_date",StandardBasicTypes.TIMESTAMP );
			query.addScalar("last_update_by",StandardBasicTypes.STRING );
			query.addScalar("last_update_on",StandardBasicTypes.TIMESTAMP );
			query.addScalar("created_by",StandardBasicTypes.STRING );
			query.addScalar("created_on",StandardBasicTypes.TIMESTAMP );
			
			Iterator<?> it = query.list().iterator();
			
		
			while (it.hasNext()) {
				Object res[] = (Object []) it.next();
				AffiliationReturn newAffRet = new AffiliationReturn();
				newAffRet.setAffiliationType((String) res[INT_AFFILIATION_TYPE]);
				newAffRet.setAffiliation((String)res[INT_AFFILIATION]);
				newAffRet.setPrimary((String) res[INT_PRIMARY_FLAG]);
				newAffRet.setStartDate(Utility.convertTimestampToString((Date) res[INT_START_DATE]));
				newAffRet.setEndDate(Utility.convertTimestampToString((Date) res[INT_END_DATE]));
				newAffRet.setLastUpdateBy((String) res[INT_LAST_UPDATE_BY]);
				newAffRet.setLastUpdateOn(Utility.convertTimestampToString((Date) res[INT_LAST_UPDATE_ON]));
				newAffRet.setCreatedBy((String) res[INT_CREATED_BY]);
				newAffRet.setCreatedOn(Utility.convertTimestampToString((Date) res[INT_CREATED_ON]));
				results.add(newAffRet);
				
			}

			
			return results.toArray(new AffiliationReturn[results.size()]);
			
		}
		catch (Exception e) {

 			throw new GeneralDatabaseException("Unable to retrieve PSU Affiliations for person identifier = " + personId);
		}
	}
	/**
	 * This method is used to set a user's primary name.
	 * @param db contains the Database object
	 * @throws CprException 
	 */
	public void setPrimaryAffiliation(Database db) throws CprException {
		
		boolean notFound = false;
		boolean alreadyPrimary = false;
		
		try {
			final Session session = db.getSession();
			final PersonAffiliation bean = getPersonAffiliationBean();
			
			final StringBuilder sb = new StringBuilder(200);
			sb.append("SELECT  primary_flag ");
			sb.append("FROM person_affiliation ");
			sb.append("WHERE person_id = :person_id_in ");
			sb.append("AND affiliation_key = :affiliation_key_in ");
			sb.append("AND end_date IS NULL ");
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", bean.getPersonId());
			query.setParameter("affiliation_key_in", bean.getAffiliationKey());
			query.addScalar("primary_flag", StandardBasicTypes.STRING);
			Iterator<?> it = query.list().iterator();
			if (! it.hasNext()) {
				notFound = true;
			}
			else {
//				Object res[] = (Object []) it.next();
				String primaryFlag = (String) it.next();
				if (primaryFlag.equals("Y")) {
					alreadyPrimary = true;
				}
				else {
					
					String sqlQuery = "from PersonAffiliation where personId = :person_id and primaryFlag = 'Y' and endDate is null";
					Query query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id", bean.getPersonId());
					it = query1.list().iterator();
					while (it.hasNext()) {
						PersonAffiliation dbBean = (PersonAffiliation) it.next();
						dbBean.setPrimaryFlag("N");
						dbBean.setLastUpdateBy(bean.getLastUpdateBy());
						dbBean.setLastUpdateOn(bean.getLastUpdateOn());
						session.update(dbBean);
						session.flush();
					}
					
					sqlQuery = "from PersonAffiliation where personId = :person_id and affiliationKey = :affiliation_key and endDate IS NULL";
					query1 = session.createQuery(sqlQuery);
					query1.setParameter("person_id", bean.getPersonId());
					query1.setParameter("affiliation_key", bean.getAffiliationKey());
					it = query1.list().iterator();
					if (it.hasNext()) {
						PersonAffiliation dbBean = (PersonAffiliation) it.next();
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
			throw new CprException(ReturnType.SET_PRIMARY_FAILED_EXCEPTION, "affiliation");
		}
		
		if (notFound) {
			throw new CprException(ReturnType.RECORD_NOT_FOUND_EXCEPTION, "affiliation");
		}
		
		if (alreadyPrimary) {
			throw new CprException(ReturnType.SET_PRIMARY_FAILED_EXCEPTION, "affiliation");
		}
	}
	/**
	 * This methods add an affiliation record to the CPR
	 *
	 * @param db contains the Database object
	 * @throws CprException 
	 */
	public void  updateAffiliation(Database db) throws CprException { 
		
		addAffiliation(db);
	}
	
//	/**
//	 * This routine is used to determine if an RA is authorize to assign an affiliation.
//	 * @param db contains the Database object
//	 * @param registrationKey - registration authority key
//	 * 
//	 * @return true if ra is authorized for affiliation
//	 * 
//	 * @throws AffiliationUseException
//	 * @throws CprException
//	 */
//	public boolean isRAAffiliationAuthorized( Database db, long registrationKey)  throws CprException {
//		return true;
////		boolean notAuthorized = false;
//		try {
//			Session session = db.getSession();
//			PersonAffiliation bean = getPersonAffiliationBean();
//			
//			String suspendFlag = "";
//			String activeFlag = "";
//			
//			StringBuilder sb = new StringBuilder(200);
//			sb.append("SELECT suspend_flag ");
//			sb.append("FROM registration_authority ");
//			sb.append("WHERE registration_authority_key = :ra_key_in ");
//			sb.append("AND end_date IS NULL ");
//			SQLQuery query = session.createSQLQuery(sb.toString());
//			query.setParameter("ra_key_in", registrationKey);
//			query.addScalar("suspend_flag", StandardBasicTypes.STRING);
//			Iterator<?> it = query.list().iterator();
//			if (it.hasNext()) {
//				suspendFlag = (String) it.next();
//				if (suspendFlag.equals("Y")) {
//					notAuthorized = true;
//				}
//				else {
//					sb = new StringBuilder(200);
//					sb.append("SELECT active_flag ");
//					sb.append("FROM affiliations ");
//					sb.append("WHERE affiliation_key = :affiliation_key_in ");
//					query = session.createSQLQuery(sb.toString());
//					query.setParameter("affiliation_key_in", bean.getAffiliationKey());
//					query.addScalar("active_flag", StandardBasicTypes.STRING);
//					it = query.list().iterator();
//					if (it.hasNext()) {
//						activeFlag = (String) it.next();
//						if (activeFlag.equals("N")) {
//							notAuthorized = true;
//						}
//						else {
//							sb = new StringBuilder(200);
//							sb.append("SELECT ra_affiliation_key ");
//							sb.append("FROM ra_affiliation ");
//							sb.append("WHERE registration_authority_key = :ra_key_in ");
//							sb.append("AND affiliation_key = :affiliation_key_in ");
//							sb.append("AND end_date IS NULL ");
//							query = session.createSQLQuery(sb.toString());
//							query.setParameter("ra_key_in", registrationKey);
//							query.setParameter("affiliation_key_in", bean.getAffiliationKey());
//							query.addScalar("ra_affiliation_key", StandardBasicTypes.LONG);
//							if (query.list().size() == 0) {
//								notAuthorized = true;
//							}
//						}
//					}
//					else {
//						notAuthorized = true;
//					}
//				}
//
//			}
//			else {
//				notAuthorized = true;
//			}			
//		}
//		catch (Exception e) {
//			throw new CprException(ReturnType.AFFILIATION_USE_EXCEPTION, getAffiliationsType().toString());
//		}
//		
//		if (notAuthorized) {
//			throw new CprException(ReturnType.AFFILIATION_USE_EXCEPTION, getAffiliationsType().toString());
//		}
//		
//		return true;
//	}
	
	/**
	 * This routine is used to returned the eduPerson Affiliation.
	 * @param personAffiliation
	 * @return Return's the edu person affiliation.
	 * @throws CprException will be thrown if there are any problems.
	 */
	private String getEduPersonAffiliation( String personAffiliation) throws CprException {
		
		RulesReturn rulesReturn = null;
		
		ArrayList <String> knownFacts  = new ArrayList <String>();
		knownFacts.add(null);

		String[] existingArray = new String[knownFacts.size()];
		existingArray = knownFacts.toArray(existingArray);
		rulesReturn = new RulesEngineHelper().processRules("eduPersonRules.drl", existingArray, personAffiliation);
		if (rulesReturn.getStatusCode() != ReturnType.SUCCESS.index()) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, rulesReturn.getStatusMessage());
		}
		
		else 
		{	
			if (rulesReturn.getNumberOfFacts() == 1 ) {
				return rulesReturn.getFacts()[0];
			}
			else
			{
				throw new CprException(ReturnType.GENERAL_EXCEPTION, "Unable to retrieve Affiliations for person");			
			}
		}
	}
}


