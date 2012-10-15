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

import edu.psu.iam.cpr.core.service.returns.ConfidentialityReturn;

public class ConfidentialityReturnTest {

	private ConfidentialityReturn cr = new ConfidentialityReturn();
	private String str = "abc";
	
	@Test
	public final void testConfidentialityReturn() {
		new ConfidentialityReturn();
	}

	@Test
	public final void testConfidentialityReturnStringStringStringStringBoolean() {
		new ConfidentialityReturn("abcd", "abcd", "","","","","");
	}

	@Test
	public final void testGetConfidentialityType() {
		ConfidentialityReturn t = new ConfidentialityReturn();
		t.setConfidentialityType("abcd");
		AssertJUnit.assertEquals(t.getConfidentialityType(), "abcd");
	}
	
	@Test
	public final void testGetStartDate() {
		cr.setStartDate(str);
		AssertJUnit.assertEquals(cr.getStartDate(),str);
	}
	
	@Test 
	public final void testGetEndDate() {
		cr.setEndDate(str);
		AssertJUnit.assertEquals(cr.getEndDate(),str);
	}
	
	@Test
	public final void testGetLastUpdateBy() {
		cr.setLastUpdateBy(str);
		AssertJUnit.assertEquals(cr.getLastUpdateBy(),str);
	}
	
	@Test
	public final void testGetLastUpdateOn() {
		cr.setLastUpdateOn(str);
		AssertJUnit.assertEquals(cr.getLastUpdateOn(),str);
	}
	
	@Test
	public final void testGetCreatedBy() {
		cr.setCreatedBy(str);
		AssertJUnit.assertEquals(cr.getCreatedBy(),str);
	}
	
	@Test
	public final void testGetCreatedOn() {
		cr.setCreatedOn(str);
		AssertJUnit.assertEquals(cr.getCreatedOn(),str);
	}

}
