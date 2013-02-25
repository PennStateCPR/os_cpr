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

import edu.psu.iam.cpr.core.service.returns.PersonIdentifierReturn;

public class PersonIdentifierReturnTest {

	PersonIdentifierReturn p = new PersonIdentifierReturn();
	String s = "abcd";
	
	@Test
	public final void testPersonIdentifierReturnStringStringStringStringStringStringStringString() {
		new PersonIdentifierReturn(null,null,null,null,null,null,null,null);
	}

	@Test
	public final void testGetIdentifierTypeString() {
		p.setIdentifierTypeString(s);
		AssertJUnit.assertEquals(p.getIdentifierTypeString(),s);
	}

	@Test
	public final void testGetIdentifierValue() {
		p.setIdentifierValue(s);
		AssertJUnit.assertEquals(p.getIdentifierValue(),s);
	}

	@Test
	public final void testGetStartDate() {
		p.setStartDate(s);
		AssertJUnit.assertEquals(p.getStartDate(),s);
	}

	@Test
	public final void testGetEndDate() {
		p.setEndDate(s);
		AssertJUnit.assertEquals(p.getEndDate(),s);
	}

	@Test
	public final void testGetLastUpdateBy() {
		p.setLastUpdateBy(s);
		AssertJUnit.assertEquals(p.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		p.setLastUpdateOn(s);
		AssertJUnit.assertEquals(p.getLastUpdateOn(),s);
	}

	@Test
	public final void testGetCreatedBy() {
		p.setCreatedBy(s);
		AssertJUnit.assertEquals(p.getCreatedBy(),s);
	}

	@Test
	public final void testGetCreatedOn() {
		p.setCreatedOn(s);
		AssertJUnit.assertEquals(p.getCreatedOn(),s);
	}

}
