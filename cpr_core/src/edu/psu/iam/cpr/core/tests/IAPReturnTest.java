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

import edu.psu.iam.cpr.core.service.returns.IAPReturn;

/**
 * @author llg5
 *
 */
public class IAPReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#getIap()}.
	 */
	@Test
	public final void testGetIap() {
		IAPReturn iapReturn = new IAPReturn("Bronze","02/01/2012 09:09:09",null,  "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
		AssertJUnit.assertEquals(iapReturn.getIap(), "Bronze");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#setIap(java.lang.String)}.
	 */
	@Test
	public final void testSetIap() {
		IAPReturn iapReturn = new IAPReturn();
		iapReturn.setIap("Bronze");
		AssertJUnit.assertEquals(iapReturn.getIap(), "Bronze");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#setStartDate(java.lang.String)}.
	 */
	@Test
	public final void testSetStartDate() {
		IAPReturn iapReturn = new IAPReturn();
		iapReturn.setStartDate("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(iapReturn.getStartDate(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#setEndDate(java.lang.String)}.
	 */
	@Test
	public final void testSetEndDate() {
		IAPReturn iapReturn = new IAPReturn();
		iapReturn.setEndDate("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(iapReturn.getEndDate(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateBy() {
		IAPReturn iapReturn = new IAPReturn();
		iapReturn.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(iapReturn.getLastUpdateBy(), "SYSTEM");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#setLastUpdateOn(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateOn() {
		IAPReturn iapReturn = new IAPReturn();
		iapReturn.setLastUpdateOn("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(iapReturn.getLastUpdateOn(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedBy() {
		IAPReturn iapReturn = new IAPReturn();
		iapReturn.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(iapReturn.getCreatedBy(), "SYSTEM");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#setCreatedOn(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedOn() {
		IAPReturn iapReturn = new IAPReturn();
		iapReturn.setCreatedOn("02/01/2012 09:09:09");
		AssertJUnit.assertEquals(iapReturn.getCreatedOn(), "02/01/2012 09:09:09");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#IAPReturn()}.
	 */
	@Test
	public final void testIAPReturn() {
		new IAPReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IAPReturn#IAPReturn(java.lang.String)}.
	 */
	@Test
	public final void testIAPReturnString() {
		new IAPReturn("Bronze", "02/01/2012 09:09:09", null, "02/01/2012 09:09:09", "SYSTEM", "02/01/2012 09:09:09","SYSTEM");
	}

	
}
