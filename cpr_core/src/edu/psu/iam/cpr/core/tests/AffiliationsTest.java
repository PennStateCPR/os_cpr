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

import edu.psu.iam.cpr.core.database.beans.Affiliations;

public class AffiliationsTest {

	Affiliations a = new Affiliations();
	Date d = new Date();
	String s = "abcd";
	
	@Test
	public final void testAffiliations() {
		AssertJUnit.assertNotNull(a);
	}

	@Test
	public final void testGetCreatedBy() {
		a.setCreatedBy(s);
		AssertJUnit.assertEquals(a.getCreatedBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		a.setLastUpdateOn(d);
		AssertJUnit.assertEquals(a.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetActiveFlag() {
		a.setActiveFlag(s);
		AssertJUnit.assertEquals(a.getActiveFlag(),s);
	}

	@Test
	public final void testGetAffiliation() {
		a.setAffiliation(s);
		AssertJUnit.assertEquals(a.getAffiliation(),s);
	}

	@Test
	public final void testGetCreatedOn() {
		a.setCreatedOn(d);
		AssertJUnit.assertEquals(a.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		a.setLastUpdateBy(s);
		AssertJUnit.assertEquals(a.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetAffiliationDesc() {
		a.setAffiliationDesc(s);
		AssertJUnit.assertEquals(a.getAffiliationDesc(),s);
	}

	@Test
	public final void testGetAffiliationKey() {
		a.setAffiliationKey(1L);
		AssertJUnit.assertEquals(a.getAffiliationKey(),new Long(1));
	}

	@Test
	public final void testGetParentAffiliationKey() {
		a.setParentAffiliationKey(1L);
		AssertJUnit.assertEquals(a.getParentAffiliationKey(),new Long(1));
	}

	@Test
	public final void testGetCanAssignFlag() {
		a.setCanAssignFlag(s);
		AssertJUnit.assertEquals(a.getCanAssignFlag(),s);
	}

	@Test
	public final void testGetEnumString() {
		a.setEnumString(s);
		AssertJUnit.assertEquals(a.getEnumString(),s);
	}

}
