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

import edu.psu.iam.cpr.core.database.beans.Userid;

/**
 * @author jvuccolo
 *
 */
public class UseridTest {

	Userid u = new Userid();
	Date d = new Date();
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#Userid()}.
	 */
	@Test
	public final void testUserid() {
		AssertJUnit.assertNotNull(u);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getNumPart()}.
	 */
	@Test
	public final void testGetNumPart() {
		u.setNumPart(1L);
		AssertJUnit.assertEquals(u.getNumPart(),new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		u.setStartDate(d);
		AssertJUnit.assertEquals(u.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getCreatedBy()}.
	 */
	@Test
	public final void testGetCreatedBy() {
		u.setCreatedBy("abcd");
		AssertJUnit.assertEquals(u.getCreatedBy(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getLastUpdateOn()}.
	 */
	@Test
	public final void testGetLastUpdateOn() {
		u.setLastUpdateOn(d);
		AssertJUnit.assertEquals(u.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getPrimaryFlag()}.
	 */
	@Test
	public final void testGetPrimaryFlag() {
		u.setPrimaryFlag("abcd");
		AssertJUnit.assertEquals(u.getPrimaryFlag(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getCreatedOn()}.
	 */
	@Test
	public final void testGetCreatedOn() {
		u.setCreatedOn(d);
		AssertJUnit.assertEquals(u.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getLastUpdateBy()}.
	 */
	@Test
	public final void testGetLastUpdateBy() {
		u.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(u.getLastUpdateBy(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getCharPart()}.
	 */
	@Test
	public final void testGetCharPart() {
		u.setCharPart("abcd");
		AssertJUnit.assertEquals(u.getCharPart(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
		u.setPersonId(1L);
		AssertJUnit.assertEquals(u.getPersonId(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		u.setEndDate(d);
		AssertJUnit.assertEquals(u.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getDisplayNameFlag()}.
	 */
	@Test
	public final void testGetDisplayNameFlag() {
		u.setDisplayNameFlag("abcd");
		AssertJUnit.assertEquals(u.getDisplayNameFlag(),"abcd");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.Userid#getUserid()}.
	 */
	@Test
	public final void testGetUserid() {
		u.setUserid("abcd");
		AssertJUnit.assertEquals(u.getUserid(),"abcd");
	}

}
