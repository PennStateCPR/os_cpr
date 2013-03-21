/* SVN FILE: $Id: DateOfBirthTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.text.DateFormat;
import java.text.ParseException;
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

	private static final int DOB_CHAR = 0;
	private static final int START_DATE = 1;
	private static final int END_DATE = 2;
	private static final int LAST_UPDATE_BY = 3;
	private static final int LAST_UPDATE_ON = 4;
	private static final int CREATED_BY = 5;
	private static final int CREATED_ON = 6;

	private static final int MONTH_DAY_ONLY = 2;
	private static final int FULL_DOB = 3;

	private static final int MONTH_DAY_SIZE = 2;
	private static final int YEAR_SIZE = 4;
	private static final int MMDDYYYY_SIZE = 8;
	
	private static final int BUFFER_SIZE = 256;
	
	private static final int MONTH_START_POSITION = 0;
	private static final int MONTH_END_POSITION = 2;
	private static final int DAY_START_POSITION = 2;
	private static final int DAY_END_POSITION = 4;
	private static final int YEAR_START_POSITION = 4;
	
	private static final String EMPTY_YEAR_STRING = "0000";

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
	 * @throws ParseException 
	 */
	public DateOfBirthTable(long personId, final String dobString, final String updatedBy) throws ParseException {
		
		super();
		
		final DateOfBirth bean = new DateOfBirth();
		setDateOfBirthBean(bean);
		final Date d = new Date();
		
		bean.setPersonId(personId);
		
		// Extract the numbers 
		final String dobChar = DateOfBirthTable.toDobChar(dobString);
		bean.setDobChar(dobChar);
		
		// Attempt to convert the DOB string representation to an actual date.
		if (dobChar.endsWith(EMPTY_YEAR_STRING)) {
			bean.setDob(null);
		}
		else {
			final DateFormat df = new SimpleDateFormat("MMddyyyy");
			bean.setDob(df.parse(dobChar));
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
	public final void setDateOfBirthBean(DateOfBirth dateOfBirthBean) {
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
		
		
		final int lengthArray[] = { MONTH_DAY_SIZE, MONTH_DAY_SIZE, YEAR_SIZE };

		// Empty string just return.
		if (dobString == null) {
			return null;
		}

		// Attempt to split the string into its parts.
		final String[] dobParts = dobString.split("/");

		// We should have received either an array of size 2 or 3, otherwise we have an array.
		if (dobParts.length != MONTH_DAY_ONLY && dobParts.length != FULL_DOB) {
			return null;
		}

		final StringBuilder buffer = new StringBuilder(BUFFER_SIZE);

		// Reformat the strings.
		int i = 0;
		for (i = 0; i < dobParts.length; ++i) {
			if (lengthArray[i] == MONTH_DAY_SIZE) {
				buffer.append(String.format("%02d", Integer.valueOf(dobParts[i])));
			}
			else if (lengthArray[i] == YEAR_SIZE) {
				buffer.append(String.format("%04d", Integer.valueOf(dobParts[i])));
			}
		}

		// Did we reach the end of the array, if not append on 4 zero's for the year.
		if (i != lengthArray.length) {
			buffer.append(EMPTY_YEAR_STRING);
		}

		return buffer.toString();
	}

	/**
	 * This routine is used to add a date of birth to a person.  It will either be successful or throw an exception.
	 * @param db Contains a connection to the database.
	 * @throws AddFailedException will throw this exception if the user cannot be added.
	 */
	public void addDateOfBirth(final Database db) {
		
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
	
	/**
	 * This routine is used to obtain the date of birth for a user.
	 * @param db contains the database connection.
	 * @param personId contains the person identifier from the central person registry.
	 * @return will return an array of DateOfBirthReturn objects if successful.
	 */
	public DateOfBirthReturn[] getDateOfBirthForPersonId(final Database db, long personId) {

		final ArrayList<DateOfBirthReturn> results = new ArrayList<DateOfBirthReturn>();
		final Session session = db.getSession();
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);

		sb.append("SELECT dob_char, ");
		sb.append("start_date, ");
		sb.append("end_date, ");
		sb.append("last_update_by, ");
		sb.append("last_update_on, ");
		sb.append("created_by, ");
		sb.append("created_on ");
		sb.append("FROM {h-schema}date_of_birth ");
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
			String dobChar = (String) res[DOB_CHAR];

			if (dobChar != null && dobChar.length() == MMDDYYYY_SIZE) {
				final String month = dobChar.substring(MONTH_START_POSITION, MONTH_END_POSITION);
				final String day = dobChar.substring(DAY_START_POSITION, DAY_END_POSITION);
				final String year = dobChar.substring(YEAR_START_POSITION);

				// Only month and day of DOB was specified.
				if (year.equals(EMPTY_YEAR_STRING)) {
					results.add(new DateOfBirthReturn(month + "/" + day,				
							Utility.convertTimestampToString((Date) res[START_DATE]),	
							Utility.convertTimestampToString((Date) res[END_DATE]),	
							(String) res[LAST_UPDATE_BY],									
							Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]),	
							(String) res[CREATED_BY],									
							Utility.convertTimestampToString((Date) res[CREATED_ON])));	
				}

				// Full date was specified.
				else {
					results.add(new DateOfBirthReturn(month + "/" + day + "/" + year,	
							Utility.convertTimestampToString((Date) res[START_DATE]),	
							Utility.convertTimestampToString((Date) res[END_DATE]),	
							(String) res[LAST_UPDATE_BY],									
							Utility.convertTimestampToString((Date) res[LAST_UPDATE_ON]),	
							(String) res[CREATED_BY],									
							Utility.convertTimestampToString((Date) res[CREATED_ON])));	

				}
			}
		}

		return results.toArray(new DateOfBirthReturn[results.size()]);
	}

	/**
	 * This routine is used to reformat a date string into something that FindPerson can use.
	 * @param formattedDateOfBirth the formattedDateOfBirth to set
	 */
	public final void setFormattedDateOfBirth(String formattedDateOfBirth) {
		
		final String month = formattedDateOfBirth.substring(MONTH_START_POSITION, MONTH_END_POSITION);
		final String day = formattedDateOfBirth.substring(DAY_START_POSITION, DAY_END_POSITION);
		final String year = formattedDateOfBirth.substring(YEAR_START_POSITION);
		
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append(month);
		sb.append("/");
		sb.append(day);
		if (! year.equals(EMPTY_YEAR_STRING)) {
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
