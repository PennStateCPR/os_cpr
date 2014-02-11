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

import edu.psu.iam.cpr.core.service.returns.CredentialReturn;

public class CredentialReturnTest {

	CredentialReturn c = new CredentialReturn();

	@Test
	public final void testCredentialReturnStringStringStringStringStringStringStringString() {
		new CredentialReturn("secure_id","111121",null,null,null,null,null,null);
	}

	@Test
	public final void testCredentialReturn() {
		new CredentialReturn();
	}

	@Test
	public final void testGetCredentialType() {
		c.setCredentialType("abcd");
		AssertJUnit.assertEquals(c.getCredentialType(),"abcd");
	}

	@Test
	public final void testGetCredentialData() {
		c.setCredentialData("abcd");
		AssertJUnit.assertEquals(c.getCredentialData(),"abcd");
	}

	@Test
	public final void testGetStartDate() {
		c.setStartDate("abcd");
		AssertJUnit.assertEquals(c.getStartDate(),"abcd");
	}

	@Test
	public final void testGetEndDate() {
		c.setEndDate("abcd");
		AssertJUnit.assertEquals(c.getEndDate(),"abcd");
	}

	@Test
	public final void testGetLastUpdateBy() {
		c.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(c.getLastUpdateBy(),"abcd");
	}

	@Test
	public final void testGetLastUpdateOn() {
		c.setLastUpdateOn("abcd");
		AssertJUnit.assertEquals(c.getLastUpdateOn(),"abcd");
	}

	@Test
	public final void testGetCreatedBy() {
		c.setCreatedBy("abcd");
		AssertJUnit.assertEquals(c.getCreatedBy(),"abcd");
	}

	@Test
	public final void testGetCreatedOn() {
		c.setCreatedOn("abcd");
		AssertJUnit.assertEquals(c.getCreatedOn(),"abcd");
	}

}
