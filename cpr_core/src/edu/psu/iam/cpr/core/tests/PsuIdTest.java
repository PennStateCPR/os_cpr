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
import java.util.Date;

import edu.psu.iam.cpr.core.database.beans.PsuId;

/**
 * @author jvuccolo
 *
 */
public class PsuIdTest {
	
	PsuId p = new PsuId();
	Date d = new Date();

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#PsuId()}.
	 */
	@Test
	public final void testPsuId() {
		AssertJUnit.assertNotNull(p);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		p.setStartDate(d);
		AssertJUnit.assertEquals(p.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getCreatedBy()}.
	 */
	@Test
	public final void testGetCreatedBy() {
		p.setCreatedBy("abcd");
		AssertJUnit.assertEquals(p.getCreatedBy(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getLastUpdateOn()}.
	 */
	@Test
	public final void testGetLastUpdateOn() {
		p.setLastUpdateOn(d);
		AssertJUnit.assertEquals(p.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getCreatedOn()}.
	 */
	@Test
	public final void testGetCreatedOn() {
		p.setCreatedOn(d);
		AssertJUnit.assertEquals(p.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getPsuId()}.
	 */
	@Test
	public final void testGetPsuId() {
		p.setPsuId("abcd");
		AssertJUnit.assertEquals(p.getPsuId(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getLastUpdateBy()}.
	 */
	@Test
	public final void testGetLastUpdateBy() {
		p.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(p.getLastUpdateBy(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
		p.setPersonId(1L);
		AssertJUnit.assertEquals(p.getPersonId(),new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		p.setEndDate(d);
		AssertJUnit.assertEquals(p.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PsuId#getPsuIdKey()}.
	 */
	@Test
	public final void testGetPsuIdKey() {
		p.setPsuIdKey(1L);
		AssertJUnit.assertEquals(p.getPsuIdKey(),new Long(1));
	}

}
