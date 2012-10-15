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

import edu.psu.iam.cpr.core.service.returns.UseridReturn;

/**
 * @author jvuccolo
 *
 */
public class UseridReturnTest {

	private UseridReturn ur = new UseridReturn();
	private String str = "abcd";
	
	@Test
	public final void testGetUseridReturn() {
		new UseridReturn();
	}
	@Test
	public final void testGetUseridReturnStringString() {
		new UseridReturn("xyz123", "n","","","","","","");
	}
	@Test
	public final void testGetUserid() {
		UseridReturn u = new UseridReturn("xyz123", "N","","","","","","");
		AssertJUnit.assertEquals(u.getUserid(), "xyz123");
	}
	@Test
	public final void testSetUserid() {
		UseridReturn u = new UseridReturn();
		u.setUserid("xyz123");
		AssertJUnit.assertEquals(u.getUserid(), "xyz123");
	}
	@Test
	public final void testGetPrimary() {
		UseridReturn u = new UseridReturn("xyz123", "N","","","","","","");
		AssertJUnit.assertEquals(u.getPrimary(), "N");
	}
	@Test
	public final void testSetPrimary() {
		UseridReturn u = new UseridReturn();
		u.setPrimary("N");
		AssertJUnit.assertEquals(u.getPrimary(), "N");
	}
	@Test
	public final void testGetStartDate() {
		ur.setStartDate(str);
		AssertJUnit.assertEquals(ur.getStartDate(),str);
	}
	
	@Test 
	public final void testGetEndDate() {
		ur.setEndDate(str);
		AssertJUnit.assertEquals(ur.getEndDate(),str);
	}
	
	@Test
	public final void testGetLastUpdateBy() {
		ur.setLastUpdateBy(str);
		AssertJUnit.assertEquals(ur.getLastUpdateBy(),str);
	}
	
	@Test
	public final void testGetLastUpdateOn() {
		ur.setLastUpdateOn(str);
		AssertJUnit.assertEquals(ur.getLastUpdateOn(),str);
	}
	
	@Test
	public final void testGetCreatedBy() {
		ur.setCreatedBy(str);
		AssertJUnit.assertEquals(ur.getCreatedBy(),str);
	}
	
	@Test
	public final void testGetCreatedOn() {
		ur.setCreatedOn(str);
		AssertJUnit.assertEquals(ur.getCreatedOn(),str);
	}

}
