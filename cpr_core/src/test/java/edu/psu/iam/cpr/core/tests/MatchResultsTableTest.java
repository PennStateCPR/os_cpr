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


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import edu.psu.iam.cpr.core.database.tables.MatchResultsTable;

/**
 * @author cpruser
 *
 */
public class MatchResultsTableTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#MatchResultsTable()}.
	 */
	@Test
	public final void testMatchResultsTable() {
		new MatchResultsTable();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#MatchResultsTable(int, int, int)}.
	 */
	@Test
	public final void testMatchResultsTableLongLongLong() {
		new MatchResultsTable(1L,1L,1L);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#getMatchSetId()}.
	 */
	@Test
	public final void testGetMatchSetKey() {
		MatchResultsTable t = new MatchResultsTable();
		t.setMatchSetKey(1L);
		AssertJUnit.assertEquals(t.getMatchSetKey(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#setMatchSetId(int)}.
	 */
	@Test
	public final void testSetMatchSetKey() {
		MatchResultsTable t = new MatchResultsTable();
		t.setMatchSetKey(1L);
		AssertJUnit.assertEquals(t.getMatchSetKey(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
		MatchResultsTable t = new MatchResultsTable();
		t.setPersonId(1L);
		AssertJUnit.assertEquals(t.getPersonId(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#setPersonId(int)}.
	 */
	@Test
	public final void testSetPersonId() {
		MatchResultsTable t = new MatchResultsTable();
		t.setPersonId(1L);
		AssertJUnit.assertEquals(t.getPersonId(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#getScore()}.
	 */
	@Test
	public final void testGetScore() {
		MatchResultsTable t = new MatchResultsTable();
		t.setScore(1L);
		AssertJUnit.assertEquals(t.getScore(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.MatchResultsTable#setScore(int)}.
	 */
	@Test
	public final void testSetScore() {
		MatchResultsTable t = new MatchResultsTable();
		t.setScore(1L);
		AssertJUnit.assertEquals(t.getScore(),new Long(1L));
	}

	@Test
	public final void testCompareTo1() {
		MatchResultsTable t1 = new MatchResultsTable();
		t1.setScore(10L);
		MatchResultsTable t2 = new MatchResultsTable();
		t2.setScore(10L);
		AssertJUnit.assertEquals(t1.compareTo(t2),0);
	}
	
	@Test
	public final void testCompareTo2() {
		MatchResultsTable t1 = new MatchResultsTable();
		t1.setScore(5L);
		MatchResultsTable t2 = new MatchResultsTable();
		t2.setScore(10L);
		AssertJUnit.assertEquals(t1.compareTo(t2),-1);
	}
	
	@Test
	public final void testCompareTo3() {
		MatchResultsTable t1 = new MatchResultsTable();
		t1.setScore(15L);
		MatchResultsTable t2 = new MatchResultsTable();
		t2.setScore(10L);
		AssertJUnit.assertEquals(t1.compareTo(t2),1);
	}
	
	@Test
	public final void testEquals1() {
		MatchResultsTable t1 = new MatchResultsTable();
		t1.setScore(15L);
		AssertJUnit.assertEquals(t1.equals(null),false);
	}
	
	@Test
	public final void testEquals2() {
		MatchResultsTable t1 = new MatchResultsTable();
		t1.setScore(15L);
		AssertJUnit.assertEquals(t1.equals(new String()),false);
	}
	
	@Test
	public final void testEquals3() {
		MatchResultsTable t1 = new MatchResultsTable();
		t1.setMatchSetKey(1L);
		t1.setScore(15L);
		t1.setPersonId(0L);
		MatchResultsTable t2 = new MatchResultsTable();
		t2.setMatchSetKey(1L);
		t2.setScore(15L);
		t2.setPersonId(0L);
		AssertJUnit.assertEquals(t1.equals(t2),true);
	}
	
	@Test
	public final void testEquals4() {
		MatchResultsTable t1 = new MatchResultsTable();
		t1.setMatchSetKey(1L);
		t1.setScore(15L);
		t1.setPersonId(0L);
		MatchResultsTable t2 = new MatchResultsTable();
		t2.setMatchSetKey(1L);
		t2.setScore(22L);
		t2.setPersonId(0L);
		AssertJUnit.assertEquals(t1.equals(t2),false);
	}
}
