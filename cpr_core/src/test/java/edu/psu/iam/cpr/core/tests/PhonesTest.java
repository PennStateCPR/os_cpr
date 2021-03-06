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

import edu.psu.iam.cpr.core.database.beans.Phones;

public class PhonesTest {

	Phones p = new Phones();
	Date d = new Date();
	
	@Test
	public final void testPhones() {
		AssertJUnit.assertNotNull(p);
	}

	@Test
	public final void testGetPrimaryFlag() {
		p.setPrimaryFlag("Y");
		AssertJUnit.assertEquals(p.getPrimaryFlag(),"Y");
	}
	
	@Test
	public final void testGetGroupId() {
		p.setGroupId(1L);
		AssertJUnit.assertEquals(p.getGroupId(), new Long(1));	
	}
	
	@Test
	public final void testGetStartDate() {
		p.setStartDate(d);
		AssertJUnit.assertEquals(p.getStartDate(),d);
	}

	@Test
	public final void testGetCreatedBy() {
		p.setCreatedBy("abcd");
		AssertJUnit.assertEquals(p.getCreatedBy(),"abcd");
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
		p.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(p.getLastUpdateBy(),"abcd");
	}

	@Test
	public final void testGetInternationalNumberFlag() {
		p.setInternationalNumberFlag("abcd");
		AssertJUnit.assertEquals(p.getInternationalNumberFlag(),"abcd");
	}

	@Test
	public final void testGetDataTypeKey() {
		p.setDataTypeKey(1L);
		AssertJUnit.assertEquals(p.getDataTypeKey(),new Long(1));
	}

	@Test
	public final void testGetPersonId() {
		p.setPersonId(1L);
		AssertJUnit.assertEquals(p.getPersonId(),new Long(1));
	}

	@Test
	public final void testGetEndDate() {
		p.setEndDate(d);
		AssertJUnit.assertEquals(p.getEndDate(),d);
	}

	@Test
	public final void testGetPhoneKey() {
		p.setPhoneKey(1L);
		AssertJUnit.assertEquals(p.getPhoneKey(),new Long(1));
	}

	@Test
	public final void testGetExtension() {
		p.setExtension("abcd");
		AssertJUnit.assertEquals(p.getExtension(),"abcd");
	}

	@Test
	public final void testGetPhoneNumber() {
		p.setPhoneNumber("abcd");
		AssertJUnit.assertEquals(p.getPhoneNumber(),"abcd");
	}

}
