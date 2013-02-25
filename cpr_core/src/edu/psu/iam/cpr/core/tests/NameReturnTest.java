/**
 * 
 */
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

import edu.psu.iam.cpr.core.service.returns.NameReturn;

/**
 * @author cpruser
 *
 */
public class NameReturnTest {

	private NameReturn nr = new NameReturn();
	private String str = "abc";
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#NameReturn()}.
	 */
	@Test
	public final void testNameReturn() {
		new NameReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#NameReturn(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testNameReturnStringStringStringStringStringString() {
		new NameReturn("COMMON_NAME", null, "Jimmy", "A", "Vuccolo", "","","","","","","");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#getNameType()}.
	 */
	@Test
	public final void testGetNameType() {
		NameReturn x = new NameReturn();
		x.setNameType("COMMON_NAME");
		AssertJUnit.assertEquals(x.getNameType(),"COMMON_NAME");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#setNameType(java.lang.String)}.
	 */
	@Test
	public final void testSetNameType() {
		NameReturn x = new NameReturn();
		x.setNameType("COMMON_NAME");
		AssertJUnit.assertEquals(x.getNameType(),"COMMON_NAME");
	}
	
	@Test 
	public final void testSetDocumentType() {
		NameReturn x = new NameReturn();
		x.setDocumentType("DRIVERS_LICENSE");
		AssertJUnit.assertEquals(x.getDocumentType(), "DRIVERS_LICENSE");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#getFirstName()}.
	 */
	@Test
	public final void testGetFirstName() {
		NameReturn x = new NameReturn();
		x.setFirstName("Jimmy");
		AssertJUnit.assertEquals(x.getFirstName(), "Jimmy");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#setFirstName(java.lang.String)}.
	 */
	@Test
	public final void testSetFirstName() {
		NameReturn x = new NameReturn();
		x.setFirstName("Jimmy");
		AssertJUnit.assertEquals(x.getFirstName(), "Jimmy");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#getMiddleNames()}.
	 */
	@Test
	public final void testGetMiddleNames() {
		NameReturn x = new NameReturn();
		x.setMiddleNames("A");
		AssertJUnit.assertEquals(x.getMiddleNames(), "A");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#setMiddleNames(java.lang.String)}.
	 */
	@Test
	public final void testSetMiddleNames() {
		NameReturn x = new NameReturn();
		x.setMiddleNames("A");
		AssertJUnit.assertEquals(x.getMiddleNames(), "A");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#getLastName()}.
	 */
	@Test
	public final void testGetLastName() {
		NameReturn x = new NameReturn();
		x.setLastName("Vuccolo");
		AssertJUnit.assertEquals(x.getLastName(), "Vuccolo");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#setLastName(java.lang.String)}.
	 */
	@Test
	public final void testSetLastName() {
		NameReturn x = new NameReturn();
		x.setLastName("Vuccolo");
		AssertJUnit.assertEquals(x.getLastName(), "Vuccolo");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#getSuffix()}.
	 */
	@Test
	public final void testGetSuffix() {
		NameReturn x = new NameReturn();
		x.setSuffix("JR");
		AssertJUnit.assertEquals(x.getSuffix(), "JR");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.NameReturn#setSuffix(java.lang.String)}.
	 */
	@Test
	public final void testSetSuffix() {
		NameReturn x = new NameReturn();
		x.setSuffix("JR");
		AssertJUnit.assertEquals(x.getSuffix(), "JR");
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
