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

import edu.psu.iam.cpr.core.database.beans.CampusCs;

public class CampusCsTest {

	@Test
	public final void testCampusCs() {
		CampusCs t = new CampusCs();
		AssertJUnit.assertNotNull(t);
	}

	@Test
	public final void testGetCreatedBy() {
		CampusCs t = new CampusCs();
		t.setCreatedBy("abcd");
		AssertJUnit.assertEquals(t.getCreatedBy(),"abcd");
	}

	@Test
	public final void testGetLastUpdateOn() {
		Date d = new Date();
		CampusCs t = new CampusCs();
		t.setLastUpdateOn(d);
		AssertJUnit.assertEquals(t.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetActiveFlag() {
		CampusCs t = new CampusCs();
		t.setActiveFlag("active");
		AssertJUnit.assertEquals(t.getActiveFlag(),"active");
	}

	@Test
	public final void testGetCreatedOn() {
		Date d = new Date();
		CampusCs t = new CampusCs();
		t.setCreatedOn(d);
		AssertJUnit.assertEquals(t.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		CampusCs t = new CampusCs();
		t.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(t.getLastUpdateBy(),"abcd");
	}

	@Test
	public final void testGetCampus() {
		CampusCs t = new CampusCs();
		t.setCampus("abcd");
		AssertJUnit.assertEquals(t.getCampus(),"abcd");
	}

	@Test
	public final void testGetCampusCodeKey() {
		CampusCs t = new CampusCs();
		t.setCampusCodeKey(1L);
		AssertJUnit.assertEquals(t.getCampusCodeKey(),new Long(1));
	}

	@Test
	public final void testGetCampusCode() {
		CampusCs t = new CampusCs();
		t.setCampusCode("abcd");
		AssertJUnit.assertEquals(t.getCampusCode(),"abcd");
	}

}
