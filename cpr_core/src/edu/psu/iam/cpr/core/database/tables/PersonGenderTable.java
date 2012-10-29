/* SVN FILE: $Id: PersonGenderTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.PersonGender;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.GenderReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides an implementation for interfacing with the person gender
 * database table.  This table contains gender information for people within in the
 * CPR.  This class provides methods to add, update and get gender information
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
 *
 * @package edu.psu.iam.cpr.core.database.tables
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class PersonGenderTable {
	
	/** person Gender bean. */
	private PersonGender personGenderBean;
	
	/** the gender type enumerated type */
	private GenderType genderType;
	
	/** boolean flag that indicates whether to return history or not. */
	private boolean returnHistoryFlag;
	
	/**
	 * Constructor.
	 */
	public PersonGenderTable() {
		super();
	}
	
	/**
	 * Constructor
	 * @param personId contains the person identifier
	 * @param genderString contains the gender string.
	 * @param updatedBy contains the last updated by.
	 * @throws Exception will be thrown if the gender is invalid.
	 */
	public PersonGenderTable(long personId, String genderString, String updatedBy) throws Exception {
		
		setGenderType(genderString);
		
		final PersonGender bean = new PersonGender();
		setPersonGenderBean(bean);
		final Date d = new Date();
		
		bean.setPersonId(personId);
		bean.setDataTypeKey(getGenderType().index());
		
		bean.setStartDate(d);
		bean.setEndDate(null);
		
		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		bean.setCreatedBy(updatedBy);
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
	 * @param personGenderBean the personGenderBean to set
	 */
	public final void setPersonGenderBean(PersonGender personGenderBean) {
		this.personGenderBean = personGenderBean;
	}

	/**
	 * @return the personGenderBean
	 */
	public PersonGender getPersonGenderBean() {
		return personGenderBean;
	}

	/**
	 * @return the genderType
	 */
	public GenderType getGenderType() {
		return genderType;
	}

	/**
	 * @param genderType the genderType to set
	 */
	public final void setGenderType(final GenderType genderType) {
		this.genderType = genderType;
	}
	
	/**
	 * This routine will accept a gender type string, trim it and attempt to generate an enum.
	 * @param genderType contains the gender type.
	 * @throws Exception will be throw if the gender cannot be converted.
	 */
	public final void setGenderType(final String genderType) throws Exception {
		setGenderType(GenderType.valueOf(genderType.toUpperCase().trim()));
	}
	
	
	/**
	 * This routine is used to add/update a gender in the CPR database.
	 * @param db contains the database connection.
	 * @throws CprException 
	 */
	public void addGender(final Database db) throws CprException {
		
		try {
			final Session session = db.getSession();
			final PersonGender bean = getPersonGenderBean();
			
			// Expire the existing gender that is still active.
			final String sqlQuery = "from PersonGender where personId = :person_id AND endDate IS NULL";
			final Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				PersonGender dbBean = (PersonGender) it.next();
				dbBean.setEndDate(bean.getLastUpdateOn());
				dbBean.setLastUpdateBy(bean.getLastUpdateBy());
				dbBean.setLastUpdateOn(bean.getLastUpdateOn());
				session.update(dbBean);
				session.flush();
			}
				
			// Save off the new record.
			session.save(bean);
			session.flush();
		}
		catch (Exception e) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "gender");
		}
		
	}
	
	/**
	 * This routine is used to obtain the gender for a user.
	 * @param db contain the database connection.
	 * @param personId contains the person identifier from the central person registry.
	 * @return will return an array of GenderReturn objects.
	 * @throws GeneralDatabaseException will throw this exception for any problem.
	 */
	public GenderReturn[] getGenderForPersonId(final Database db, long personId) throws GeneralDatabaseException {

		try {
			final Session session = db.getSession();
			final StringBuilder sb = new StringBuilder(2048);
			final ArrayList<GenderReturn> results = new ArrayList<GenderReturn>();
			
			sb.append("SELECT data_type_key, ");
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("FROM person_gender ");
			sb.append("WHERE person_id = :person_id_in ");
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			sb.append("ORDER BY start_date ASC ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("data_type_key", StandardBasicTypes.LONG);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);
			
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Object res[] = (Object []) it.next();
				results.add(new GenderReturn(GenderType.get((Long) res[0]).toString(),			
											Utility.convertTimestampToString((Date) res[1]),	
											Utility.convertTimestampToString((Date) res[2]),	
											(String) res[3],									
											Utility.convertTimestampToString((Date) res[4]),	
											(String) res[5],									
											Utility.convertTimestampToString((Date) res[6])));	
			}
			
			return results.toArray(new GenderReturn[results.size()]);
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve gender for person identifier = " + personId);
		}
	}
}
