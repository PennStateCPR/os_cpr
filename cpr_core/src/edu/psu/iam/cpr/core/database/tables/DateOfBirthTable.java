/* SVN FILE: $Id: DateOfBirthTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.DateOfBirth;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.DateOfBirthReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * This class provides the implementation for interfacing with the date of birth database
 * table.  There are methods to perform an add, update and a get of the date of 
 * birth for a person.
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
public class DateOfBirthTable {

	/** Date of birth Bean **/
	private DateOfBirth dateOfBirthBean;
	
	/** Date of birth that can be used by find person. */
	private String formattedDateOfBirth;
	
	/** Boolean flag that indicates whether to return all of the history records on a GET. */
	private boolean returnHistoryFlag;
	
	/**
	 * Constructor.
	 * @param personId represents the person identifier associated with this record.
	 * @param dobString represents the date of birth string.
	 * @param updatedBy represents the updated by identifier.
	 * @throws CprException 
	 */
	public DateOfBirthTable(long personId, final String dobString, final String updatedBy) throws CprException {
		
		super();
		
		final DateOfBirth bean = new DateOfBirth();
		setDateOfBirthBean(bean);
		final Date d = new Date();
		
		bean.setPersonId(personId);
		
		// Extract the numbers 
		final String dobChar = DateOfBirthTable.toDobChar(dobString);
		if (dobChar == null) {
			throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Date of Birth");
		}
		bean.setDobChar(dobChar);
		
		// Attempt to convert the DOB string representation to an actual date.
		if (dobChar.endsWith("0000")) {
			bean.setDob(null);
		}
		else {
			try {
				final DateFormat df = new SimpleDateFormat("MMddyyyy");
				bean.setDob(df.parse(dobChar));
			}
			catch (Exception e) {
				throw new CprException(ReturnType.INVALID_PARAMETERS_EXCEPTION, "Date of Birth");	
			}
		}
		
		bean.setStartDate(d);
		bean.setEndDate(null);

		bean.setCreatedBy(updatedBy);
		bean.setCreatedOn(d);

		bean.setLastUpdateBy(updatedBy);
		bean.setLastUpdateOn(d);
		
		// Call a routine that will reformat the date of birth into something that find person can use.
		setFormattedDateOfBirth(dobChar);
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
	 * @param dateOfBirthBean the dateOfBirthBean to set
	 */
	public void setDateOfBirthBean(DateOfBirth dateOfBirthBean) {
		this.dateOfBirthBean = dateOfBirthBean;
	}

	/**
	 * @return the dateOfBirthBean
	 */
	public DateOfBirth getDateOfBirthBean() {
		return dateOfBirthBean;
	}

	/**
	 * Constructor.
	 */
	public DateOfBirthTable() {
		super();
	}

	/**
	 * @param dobString the dobString to set
	 * @return will return the numeric portion of the date of the birth.
	 */
	public static String toDobChar(final String dobString) {
		
		try {
			final int lengthArray[] = { 2, 2, 4 };

			// Empty string just return.
			if (dobString == null) {
				return null;
			}

			// Attempt to split the string into its parts.
			final String[] dobParts = dobString.split("/");

			// We should have received either an array of size 2 or 3, otherwise we have an array.
			if (dobParts.length != 2 && dobParts.length != 3) {
				return null;
			}

			final StringBuilder buffer = new StringBuilder(100);

			// Reformat the strings.
			int i = 0;
			for (i = 0; i < dobParts.length; ++i) {
				if (lengthArray[i] == 2) {
					buffer.append(String.format("%02d", new Integer(dobParts[i])));
				}
				else if (lengthArray[i] == 4) {
					buffer.append(String.format("%04d", new Integer(dobParts[i])));
				}
			}

			// Did we reach the end of the array, if not append on 4 zero's for the year.
			if (i != lengthArray.length) {
				buffer.append("0000");
			}
			
			return buffer.toString();
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * This routine is used to add a date of birth to a person.  It will either be successful or throw an exception.
	 * @param db Contains a connection to the database.
	 * @throws AddFailedException will throw this exception if the user cannot be added.
	 * @throws CprException 
	 */
	public void addDateOfBirth(final Database db) throws CprException {
		
		
		try {
			final Session session = db.getSession();
			final DateOfBirth bean = getDateOfBirthBean();
			
			final String sqlQuery = "from DateOfBirth where personId = :person_id and endDate IS NULL";
			Query query = session.createQuery(sqlQuery);
			query.setParameter("person_id", bean.getPersonId());
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				DateOfBirth dbBean = (DateOfBirth) it.next();
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
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "date of birth");	
		}
	}
	
	/**
	 * This routine is used to obtain the date of birth for a user.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier from the central person registry.
	 * @return will return an array of DateOfBirthReturn objects if successful.
	 * @throws GeneralDatabaseException will throw an exception if their are any problems.
	 */
	public DateOfBirthReturn[] getDateOfBirthForPersonId(final Database db, long personId) throws GeneralDatabaseException {

		try {
			final ArrayList<DateOfBirthReturn> results = new ArrayList<DateOfBirthReturn>();
			final Session session = db.getSession();
			final StringBuilder sb = new StringBuilder(2048);

			sb.append("SELECT dob_char, ");
			sb.append("start_date, ");
			sb.append("end_date, ");
			sb.append("last_update_by, ");
			sb.append("last_update_on, ");
			sb.append("created_by, ");
			sb.append("created_on ");
			sb.append("FROM date_of_birth ");
			sb.append("WHERE person_id = :person_id_in ");
			
			// If we are not returning all records, we need to just return the active ones.
			if (! isReturnHistoryFlag()) {
				sb.append("AND end_date IS NULL ");
			}
			sb.append("ORDER BY start_date ASC ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("person_id_in", personId);
			query.addScalar("dob_char", StandardBasicTypes.STRING);
			query.addScalar("start_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("end_date", StandardBasicTypes.TIMESTAMP);
			query.addScalar("last_update_by", StandardBasicTypes.STRING);
			query.addScalar("last_update_on", StandardBasicTypes.TIMESTAMP);
			query.addScalar("created_by", StandardBasicTypes.STRING);
			query.addScalar("created_on", StandardBasicTypes.TIMESTAMP);
			
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Object res[] = (Object []) it.next();
				String dobChar = (String) res[0];
				
				if (dobChar != null && dobChar.length() == 8) {
					final String month = dobChar.substring(0, 2);
					final String day = dobChar.substring(2, 4);
					final String year = dobChar.substring(4);
										
					// Only month and day of DOB was specified.
					if (year.equals("0000")) {
						results.add(new DateOfBirthReturn(month + "/" + day,				// DOB month and day only.
										Utility.convertTimestampToString((Date) res[1]),	// Start date
										Utility.convertTimestampToString((Date) res[2]),	// End date
										(String) res[3],									// Last Update by
										Utility.convertTimestampToString((Date) res[4]),	// Last update on.
										(String) res[5],									// Created By.
										Utility.convertTimestampToString((Date) res[6])));	// Created On.
					}
					
					// Full date was specified.
					else {
						results.add(new DateOfBirthReturn(month + "/" + day + "/" + year,	// DOB, month, day and year.
										Utility.convertTimestampToString((Date) res[1]),	// Start date
										Utility.convertTimestampToString((Date) res[2]),	// End date
										(String) res[3],									// Last Update by
										Utility.convertTimestampToString((Date) res[4]),	// Last update on.
										(String) res[5],									// Created By.
										Utility.convertTimestampToString((Date) res[6])));	// Created On.
								
					}
				}
			}
			
			return results.toArray(new DateOfBirthReturn[results.size()]);
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve date of birth for person identifier = " + personId);
		}
	}

	/**
	 * This routine is used to reformat a date string into something that FindPerson can use.
	 * @param formattedDateOfBirth the formattedDateOfBirth to set
	 */
	public void setFormattedDateOfBirth(String formattedDateOfBirth) {
		
		final String month = formattedDateOfBirth.substring(0, 2);
		final String day = formattedDateOfBirth.substring(2, 4);
		final String year = formattedDateOfBirth.substring(4);
		
		final StringBuilder sb = new StringBuilder(30);
		sb.append(month);
		sb.append("/");
		sb.append(day);
		if (! year.equals("0000")) {
			sb.append("/");
			sb.append(year);
		}
		
		this.formattedDateOfBirth = sb.toString();
	}

	/**
	 * @return the formattedDateOfBirth
	 */
	public String getFormattedDateOfBirth() {
		return formattedDateOfBirth;
	}
}
