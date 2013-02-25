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

import edu.psu.iam.cpr.core.service.returns.GenderReturn;

public class GenderReturnTest {

	private final String str = "abcd";
	private final GenderReturn nr = new GenderReturn();
	
	@Test
	public final void testGenderReturn() {
		new GenderReturn();
	}

	@Test
	public final void testGenderReturnString() {
		new GenderReturn("M","","","","","","");
	}

	@Test
	public final void testGetGender() {
		GenderReturn g = new GenderReturn();
		g.setGender("M");
		AssertJUnit.assertEquals(g.getGender(),"M");
	}

	@Test
	public final void testSetGender() {
		GenderReturn g = new GenderReturn();
		g.setGender("M");
		AssertJUnit.assertEquals(g.getGender(),"M");
	}
	@Test
	public final void testGetStartDate() {
		nr.setStartDate(str);
		AssertJUnit.assertEquals(nr.getStartDate(),str);
	}
	
	@Test 
	public final void testGetEndDate() {
		nr.setEndDate(str);
		AssertJUnit.assertEquals(nr.getEndDate(),str);
	}
	
	@Test
	public final void testGetLastUpdateBy() {
		nr.setLastUpdateBy(str);
		AssertJUnit.assertEquals(nr.getLastUpdateBy(),str);
	}
	
	@Test
	public final void testGetLastUpdateOn() {
		nr.setLastUpdateOn(str);
		AssertJUnit.assertEquals(nr.getLastUpdateOn(),str);
	}
	
	@Test
	public final void testGetCreatedBy() {
		nr.setCreatedBy(str);
		AssertJUnit.assertEquals(nr.getCreatedBy(),str);
	}
	
	@Test
	public final void testGetCreatedOn() {
		nr.setCreatedOn(str);
		AssertJUnit.assertEquals(nr.getCreatedOn(),str);
	}

}
