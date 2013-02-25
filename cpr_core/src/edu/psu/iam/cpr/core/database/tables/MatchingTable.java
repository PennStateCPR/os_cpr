/* SVN FILE: $Id: MatchingTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;

/**
 * This class provides an implementation for interfacing with the matching database table.
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
public class MatchingTable {

	/** the SQL to remove a match set from the database. */
	protected static final String REMOVE_MATCH_SET_SQL = "DELETE FROM MATCH_RESULTS WHERE MATCH_SET_KEY=:match_set_key_in";
	
	/** the SQL to find a match set in the database. */
	protected static final String FIND_MATCH_SET_SQL = "{ call match_package.find_matches(?,?,?,?,?,?,?) }";
	
	/** the SQL to retrieve a match set in the database. */
	protected static final String GET_MATCH_SET_SQL = "SELECT MATCH_SET_KEY, PERSON_ID, SCORE FROM MATCH_RESULTS WHERE MATCH_SET_KEY=:match_set_key_in ORDER BY SCORE DESC";

	/** the SQL to retrieve a match set in the database. Only retrieve N hits.*/
	protected static final String GET_MATCH_SET_SQL_LIMIT = "SELECT * FROM (" + GET_MATCH_SET_SQL + ") WHERE ROWNUM <:rownum_in";
	
	/** the SQL to retrieve a match set in the database with a minimum match score. */
	protected static final String GET_MATCH_SET_MIN_SCORE_SQL = "SELECT MATCH_SET_KEY, PERSON_ID, SCORE FROM MATCH_RESULTS WHERE MATCH_SET_KEY=:match_set_key_in AND SCORE>=:score_in ORDER BY SCORE DESC";

	/** the SQL to retrieve a match set in the database with a minimum match score. Only retrieve N hits. */
	protected static final String GET_MATCH_SET_MIN_SCORE_SQL_LIMIT = "SELECT * FROM (" +  GET_MATCH_SET_MIN_SCORE_SQL + ") WHERE ROWNUM <:rownum_in";

	private static final int NAME_MATCH_CODE = 1;
	private static final int ADDRESS_MATCH_CODE = 2;
	private static final int CITY_MATCH_CODE = 3;
	private static final int STATE = 4;
	private static final int POSTAL_CODE = 5;
	private static final int DATE_OF_BIRTH = 6;
	private static final int MATCH_SET_KEY = 7;

	private static final int RES_MATCH_SET_KEY = 0;
	private static final int RES_PERSON_ID = 1;
	private static final int RES_SCORE = 2;

	private static final String MATCH_SET_KEY_STRING = "match_set_key_in";

	
	/** the match set identifier. */
	protected Long matchSetKey;
	
	/**
	 * Constructor
	 */
	public MatchingTable() {
		super();
	}

	/**
	 * Constructor
	 * @param matchSetKey contains the match set id.
	 */
	public MatchingTable(Long matchSetKey) {
		this.matchSetKey = matchSetKey;
	}
	
	/**
	 * @param matchSetKey the matchSetKey to set
	 */
	public void setMatchSetKey(Long matchSetKey) {
		this.matchSetKey = matchSetKey;
	}

	/**
	 * @return the matchSetId
	 */
	public Long getMatchSetKey() {
		return matchSetKey;
	}

	/**
	 * The purpose of this function is to interface with the database and determine which data sets match a particular user.
	 * @param db contains a database object
	 * @param namesTable contains a names table object
	 * @param addressesTable contains an addresses table object.
	 * @param dateOfBirthTable contains a date of birth object.
	 */
	public void findMatches(final Database db, final NamesTable namesTable, 
			final AddressesTable addressesTable, final DateOfBirthTable dateOfBirthTable) {
		

		final Session session = db.getSession();

		session.doWork(new Work() {
			public void execute(Connection conn) throws SQLException {
				
				CallableStatement stmt = null;
				try {
					stmt = conn.prepareCall(FIND_MATCH_SET_SQL);
					
					// Set up output and input parameters.
					
					// Pass in the names match code if available.
					if (namesTable != null) {
						stmt.setString(NAME_MATCH_CODE, namesTable.getNamesBean().getNameMatchCode());
					}
					else {
						stmt.setNull(NAME_MATCH_CODE, Types.VARCHAR);
					}
					
					// Pass in the addresses and city match code, state and postal code if available.
					if (addressesTable != null) {
						stmt.setString(ADDRESS_MATCH_CODE, addressesTable.getAddressesBean().getAddressMatchCode());
						stmt.setString(CITY_MATCH_CODE, addressesTable.getAddressesBean().getCityMatchCode());
						stmt.setString(STATE, addressesTable.getAddressesBean().getState());
						stmt.setString(POSTAL_CODE, addressesTable.getAddressesBean().getPostalCode());
					}
					else {
						stmt.setNull(ADDRESS_MATCH_CODE, Types.VARCHAR);
						stmt.setNull(CITY_MATCH_CODE, Types.VARCHAR);
						stmt.setNull(STATE, Types.VARCHAR);
						stmt.setNull(POSTAL_CODE, Types.VARCHAR);
					}
					
					// Pass in the date of birth if available.
					if (dateOfBirthTable != null) {
						stmt.setString(DATE_OF_BIRTH, dateOfBirthTable.getDateOfBirthBean().getDobChar());
					}
					else {
						stmt.setNull(DATE_OF_BIRTH, Types.VARCHAR);
					}
					stmt.registerOutParameter(MATCH_SET_KEY, Types.INTEGER);
					
					// Execute the stored function.
					stmt.execute();		
					
					// Get the status from the database.
					setMatchSetKey(stmt.getLong(MATCH_SET_KEY));
				}
				finally {
					try {
						stmt.close();
					}
					catch (Exception e) {
					}
				}
			}
		});
		
	}
	
	/**
	 * This routine is used to remove a match set from the database.
	 * @param db contains a database object which points to an open database connection.
	 */
	public void removeMatchSet(final Database db) {
		
		final Session session = db.getSession();
		final SQLQuery query = session.createSQLQuery(REMOVE_MATCH_SET_SQL);
		query.setParameter(MATCH_SET_KEY_STRING, getMatchSetKey());
		query.executeUpdate();		
	}
	
	/**
	 * This routine is used to obtain a match set from the database.
	 * @param db contains the database object which hold an open database connection.
	 * @return will return an ArrayList of match results items.
	 */
	public List<MatchResultsTable> getMatchSet(final Database db) {
		
		final ArrayList<MatchResultsTable> matchResultsTable = new ArrayList<MatchResultsTable>();
		final Session session = db.getSession();
		final SQLQuery query = session.createSQLQuery(GET_MATCH_SET_SQL);
		query.setParameter(MATCH_SET_KEY_STRING, getMatchSetKey());
		query.addScalar("match_set_key", StandardBasicTypes.LONG);
		query.addScalar("person_id", StandardBasicTypes.LONG);
		query.addScalar("score", StandardBasicTypes.LONG);
		final Iterator<?> it = query.list().iterator();
		while (it.hasNext()) {
			Object res[] = (Object []) it.next();
			matchResultsTable.add(new MatchResultsTable((Long) res[RES_MATCH_SET_KEY], (Long) res[RES_PERSON_ID], (Long) res[RES_SCORE]));
		}
		return matchResultsTable;
	}
	
	/**
	 * This routine is used to obtain a match set from the database.
	 * This method only retrieved the first n items.
	 * 
	 * @param db contains the database object which hold an open database connection.
	 * @param maxResults The maximum number of results to retrieve.
	 * 
	 * @return will return an ArrayList of match results items containing up to maxResults items.
	 * 
	 */
	public List<MatchResultsTable> getMatchSetWithLimit(final Database db, int maxResults)  {
		
		ArrayList<MatchResultsTable> matchResultsTable = new ArrayList<MatchResultsTable>(maxResults);
		final Session session = db.getSession();

		final SQLQuery query = session.createSQLQuery(GET_MATCH_SET_SQL_LIMIT);
		query.setParameter(MATCH_SET_KEY_STRING, getMatchSetKey());
		query.setParameter("rownum_in", maxResults);
		query.addScalar("match_set_key", StandardBasicTypes.LONG);
		query.addScalar("person_id", StandardBasicTypes.LONG);
		query.addScalar("score", StandardBasicTypes.LONG);
		final Iterator<?> it = query.list().iterator();

		while (it.hasNext()) {
			Object res[] = (Object []) it.next();
			matchResultsTable.add(new MatchResultsTable((Long) res[RES_MATCH_SET_KEY], (Long) res[RES_PERSON_ID], (Long) res[RES_SCORE]));
		}	
		// XXX: error checking on maxResults (>0)

		matchResultsTable.trimToSize();
		return matchResultsTable;
	}


	/**
	 * This routine is used to obtain a match set from the database.
	 * This method only retrieved the first n items with a minimum match score.
	 * 
	 * @param db contains the database object which hold an open database connection.
	 * @param maxResults The maximum number of results to retrieve.
	 * @param minMatchScore The minimum match score for all hits.
	 * 
	 * @return will return an ArrayList of match results items containing up to maxResults items.
	 * 
	 */
	public List<MatchResultsTable> getMatchSetWithLimitAndCutoff(final Database db, int maxResults, Long minMatchScore) {
		
		// XXX: error checking on maxResults (non-negative)
		// XXX: error checking on minMatchScore (non-negative)
				
		final ArrayList<MatchResultsTable> matchResultsTable = new ArrayList<MatchResultsTable>(maxResults);
		
		final Session session = db.getSession();
		final SQLQuery query = session.createSQLQuery(GET_MATCH_SET_MIN_SCORE_SQL_LIMIT);
		query.setParameter(MATCH_SET_KEY_STRING, getMatchSetKey());
		query.setParameter("score_in", minMatchScore);
		query.setParameter("rownum_in", maxResults);
		query.addScalar("match_set_key", StandardBasicTypes.LONG);
		query.addScalar("person_id", StandardBasicTypes.LONG);
		query.addScalar("score", StandardBasicTypes.LONG);
		final Iterator<?> it = query.list().iterator();

		while (it.hasNext()) {
			Object res[] = (Object []) it.next();
			matchResultsTable.add(new MatchResultsTable((Long) res[RES_MATCH_SET_KEY], (Long) res[RES_PERSON_ID], (Long) res[RES_SCORE]));
		}				
		matchResultsTable.trimToSize();
		return matchResultsTable;
	}
}
