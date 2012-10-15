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

import edu.psu.iam.cpr.core.database.beans.PersonPhoto;

public class PersonPhotoTest {

	Date d = new Date();
	String s = "abcd";
	PersonPhoto p = new PersonPhoto();
	
	@Test
	public final void testPersonPhoto() {
		new PersonPhoto();
	}

	@Test
	public final void testGetCreatedBy() {
		p.setCreatedBy(s);
		AssertJUnit.assertEquals(p.getCreatedBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		p.setLastUpdateOn(d);
		AssertJUnit.assertEquals(p.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetCreatedOn() {
		p.setCreatedOn(d);
		AssertJUnit.assertEquals(p.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		p.setLastUpdateBy(s);
		AssertJUnit.assertEquals(p.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetPersonId() {
		p.setPersonId(1L);
		AssertJUnit.assertEquals(p.getPersonId(), new Long(1));
	}

	@Test
	public final void testGetDateTaken() {
		p.setDateTaken(d);
		AssertJUnit.assertEquals(p.getDateTaken(),d);
	}

	@Test
	public final void testSetPhoto() {
		byte b[] = new byte[1];
		p.setPhoto(b);
		AssertJUnit.assertEquals(p.getPhoto(),b);
	}

}
