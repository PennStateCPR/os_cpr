/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

/**
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
 */


import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.MatchingTable;

/**
 * @author cpruser
 *
 */
public class MatchingTableTest {
	
	
	/** the SQL to retrieve a match set in the database with a minimum match score. */
	protected static final String GET_MATCH_SET_MIN_SCORE_SQL = "SELECT MATCH_SET_KEY, PERSON_ID, SCORE FROM {h-schema}MATCH_RESULTS WHERE MATCH_SET_KEY=:match_set_id_in AND SCORE>=:score_in ORDER BY SCORE DESC";

	/** the SQL to retrieve a match set in the database with a minimum match score. Only retrieve N hits. */
	protected static final String GET_MATCH_SET_MIN_SCORE_SQL_LIMIT = "SELECT * FROM (" +  GET_MATCH_SET_MIN_SCORE_SQL + ") WHERE ROWNUM <:rownum_in";

	private static Database db = new Database();
	
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#AddressesTable()}.
	 * @throws Exception 

	@Test
	public final void testMatchingTableQuery() throws Exception {
		ArrayList<MatchResultsTable> matchResultsTable = new ArrayList<MatchResultsTable>(10);
		openDbConnection();
		Session session = db.getSession();
		SQLQuery query = session.createSQLQuery(GET_MATCH_SET_MIN_SCORE_SQL_LIMIT);
		query.setParameter("match_set_id_in", 822);
		query.setParameter("score_in", 330);
		query.setParameter("rownum_in", 10);
		query.addScalar("match_set_key", StandardBasicTypes.LONG);
		query.addScalar("person_id", StandardBasicTypes.LONG);
		query.addScalar("score", StandardBasicTypes.LONG);
		Iterator<?> it = query.list().iterator();
		while (it.hasNext()) {
			Object res[] = (Object []) it.next();
			matchResultsTable.add(new MatchResultsTable((Long) res[0], (Long) res[1], (Long) res[2]));
		}
		assertEquals(matchResultsTable.size(), 1);
		session.flush();
		db.closeSession();
	}
	
	*/
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchingTable#MatchingTable()}.
	 */
	@Test
	public final void testMatchingTable() {
		new MatchingTable();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchingTable#MatchingTable(int)}.
	 */
	@Test
	public final void testMatchingTableInt() {
		new MatchingTable(1L);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchingTable#setMatchSetId(int)}.
	 */
	@Test
	public final void testSetMatchSetKey() {
		MatchingTable t = new MatchingTable();
		t.setMatchSetKey(1L);
		assertEquals(t.getMatchSetKey(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchingTable#findMatches(edu.psu.iam.cpr.core.database.Database, edu.psu.iam.cpr.core.database.tables.NamesTable, edu.psu.iam.cpr.core.database.tables.AddressesTable, edu.psu.iam.cpr.core.database.tables.DateOfBirthTable)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testFindMatches() throws Exception {
		MatchingTable t = new MatchingTable();
		t.findMatches(null, null, null, null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchingTable#getMatchSetId()}.
	 */
	@Test
	public final void testGetMatchSetKey() {
		MatchingTable t = new MatchingTable();
		t.setMatchSetKey(1L);
		assertEquals(t.getMatchSetKey(),new Long(1L));
	}


}
