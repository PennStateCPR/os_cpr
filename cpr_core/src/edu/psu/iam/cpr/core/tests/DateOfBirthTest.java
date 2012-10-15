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

import edu.psu.iam.cpr.core.database.beans.DateOfBirth;

public class DateOfBirthTest {

	DateOfBirth dob = new DateOfBirth();
	Date d = new Date();
	
	@Test
	public final void testDateOfBirth() {
		AssertJUnit.assertNotNull(dob);
	}

	@Test
	public final void testGetStartDate() {
		dob.setStartDate(d);
		AssertJUnit.assertEquals(dob.getStartDate(), d);
	}

	@Test
	public final void testGetCreatedBy() {
		dob.setCreatedBy("abcd");
		AssertJUnit.assertEquals(dob.getCreatedBy(), "abcd");
	}

	@Test
	public final void testGetLastUpdateOn() {
		dob.setLastUpdateOn(d);
		AssertJUnit.assertEquals(dob.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetDobChar() {
		dob.setDobChar("abcd");
		AssertJUnit.assertEquals(dob.getDobChar(),"abcd");
	}

	@Test
	public final void testGetCreatedOn() {
		dob.setCreatedOn(d);
		AssertJUnit.assertEquals(dob.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		dob.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(dob.getLastUpdateBy(),"abcd");
	}

	@Test
	public final void testGetDob() {
		dob.setDob(d);
		AssertJUnit.assertEquals(dob.getDob(),d);
	}

	@Test
	public final void testGetDateOfBirthKey() {
		dob.setDateOfBirthKey(1L);
		AssertJUnit.assertEquals(dob.getDateOfBirthKey(),new Long(1));
	}

	@Test
	public final void testGetPersonId() {
		dob.setPersonId(1L);
		AssertJUnit.assertEquals(dob.getPersonId(),new Long(1));
	}

	@Test
	public final void testGetEndDate() {
		dob.setEndDate(d);
		AssertJUnit.assertEquals(dob.getEndDate(),d);
	}

}
