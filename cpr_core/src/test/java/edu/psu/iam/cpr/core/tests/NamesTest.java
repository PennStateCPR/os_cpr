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

import edu.psu.iam.cpr.core.database.beans.Names;

public class NamesTest {

	Names n = new Names();
	Date d = new Date();
	String s = "abcd";
	
	@Test
	public final void testNames() {
		AssertJUnit.assertNotNull(n);
	}

	@Test
	public final void testGetDataTypeKey() {
		n.setDataTypeKey(1L);
		AssertJUnit.assertEquals(n.getDataTypeKey(), new Long(1));
	}

	@Test
	public final void testGetSuffix() {
		n.setSuffix(s);
		AssertJUnit.assertEquals(n.getSuffix(),s);
	}

	@Test
	public final void testGetLastUpdateBy() {
		n.setLastUpdateBy(s);
		AssertJUnit.assertEquals(n.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetEndDate() {
		n.setEndDate(d);
		AssertJUnit.assertEquals(n.getEndDate(),d);
	}

	@Test
	public final void testGetNameMatchCode() {
		n.setNameMatchCode(s);
		AssertJUnit.assertEquals(n.getNameMatchCode(),s);
	}

	@Test
	public final void testGetDocumentTypeKey() {
		n.setDocumentTypeKey(1L);
		AssertJUnit.assertEquals(n.getDocumentTypeKey(), new Long(1));
	}

	@Test
	public final void testGetNameKey() {
		n.setNameKey(1L);
		AssertJUnit.assertEquals(n.getNameKey(), new Long(1));
	}

	@Test
	public final void testGetLastName() {
		n.setLastName(s);
		AssertJUnit.assertEquals(n.getLastName(),s);
	}

	@Test
	public final void testGetStartDate() {
		n.setStartDate(d);
		AssertJUnit.assertEquals(n.getStartDate(),d);
	}

	@Test
	public final void testGetCreatedBy() {
		n.setCreatedBy(s);
		AssertJUnit.assertEquals(n.getCreatedBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		n.setLastUpdateOn(d);
		AssertJUnit.assertEquals(n.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetCreatedOn() {
		n.setCreatedOn(d);
		AssertJUnit.assertEquals(n.getCreatedOn(),d);
	}

	@Test
	public final void testGetFirstName() {
		n.setFirstName(s);
		AssertJUnit.assertEquals(n.getFirstName(),s);
	}

	@Test
	public final void testGetPersonId() {
		n.setPersonId(1L);
		AssertJUnit.assertEquals(n.getPersonId(), new Long(1));
	}

	@Test
	public final void testGetMiddleNames() {
		n.setMiddleNames(s);
		AssertJUnit.assertEquals(n.getMiddleNames(),s);
	}

}
