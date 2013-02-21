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

import edu.psu.iam.cpr.core.service.returns.MatchReturn;

/**
 * @author cpruser
 *
 */
public class MatchReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#MatchReturn()}.
	 */
	@Test
	public final void testMatchReturn() {
		MatchReturn m = new MatchReturn();
		AssertJUnit.assertNotNull(m);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#MatchReturn(Long, Long)}.
	 */
	@Test
	public final void testMatchReturnLongLong() {
		new MatchReturn(1L,330L);
		MatchReturn m = new MatchReturn();
		AssertJUnit.assertNotNull(m);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#MatchReturn(Long, Long, String)}.
	 */
	@Test
	public final void testMatchReturnLongLongString() {
		new MatchReturn(1L,330L, "9500000001");
		MatchReturn m = new MatchReturn();
		AssertJUnit.assertNotNull(m);
	}

	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#MatchReturn(Long, Long, String)}.
	 */
	@Test
	public final void testMatchReturnLongLongStringString() {
		new MatchReturn(1L,330L, "9500000001", "abc1");
		MatchReturn m = new MatchReturn();
		AssertJUnit.assertNotNull(m);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
		MatchReturn m = new MatchReturn(1L, 330L, "9500000001", "abc1");
		AssertJUnit.assertEquals(m.getPersonId(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#setPersonId(Long)}.
	 */
	@Test
	public final void testSetPersonId() {
		MatchReturn m = new MatchReturn();
		m.setPersonId(1L);
		AssertJUnit.assertEquals(m.getPersonId(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#getScore()}.
	 */
	@Test
	public final void testGetScore() {
		MatchReturn m = new MatchReturn(1L, 330L);
		AssertJUnit.assertEquals(m.getScore(),new Long(330));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.MatchReturn#setScore(int)}.
	 */
	@Test
	public final void testSetScore() {
		MatchReturn m = new MatchReturn();
		m.setScore(1L);
		AssertJUnit.assertEquals(m.getScore(),new Long(1));
	}

}
