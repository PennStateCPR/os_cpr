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
import edu.psu.iam.cpr.core.database.beans.MatchResults;

public class MatchResultsTest {

	MatchResults m = new MatchResults();
	
	@Test
	public final void testMatchResults() {
		AssertJUnit.assertNotNull(m);
	}

	@Test
	public final void testGetScore() {
		m.setScore(1L);
		AssertJUnit.assertEquals(m.getScore(),new Long(1));
	}

	@Test
	public final void testGetPersonId() {
		m.setPersonId(1L);
		AssertJUnit.assertEquals(m.getPersonId(), new Long(1));
	}

	@Test
	public final void testGetMatchSetKey() {
		m.setMatchSetKey(1L);
		AssertJUnit.assertEquals(m.getMatchSetKey(), new Long(1));
	}

}
