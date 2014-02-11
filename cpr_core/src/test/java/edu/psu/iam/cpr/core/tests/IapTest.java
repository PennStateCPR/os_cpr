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

import edu.psu.iam.cpr.core.database.beans.Iap;

public class IapTest {

	Iap i = new Iap();
	Date d = new Date();
	String s = "abcd";
	
	@Test
	public final void testIap() {
		AssertJUnit.assertNotNull(i);
	}

	@Test
	public final void testGetIapDesc() {
		i.setIapDesc(s);
		AssertJUnit.assertEquals(i.getIapDesc(),s);
	}

	@Test
	public final void testGetCreatedBy() {
		i.setCreatedBy(s);
		AssertJUnit.assertEquals(i.getCreatedBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		i.setLastUpdateOn(d);
		AssertJUnit.assertEquals(i.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetActiveFlag() {
		i.setActiveFlag(s);
		AssertJUnit.assertEquals(i.getActiveFlag(),s);
	}

	@Test
	public final void testGetCreatedOn() {
		i.setCreatedOn(d);
		AssertJUnit.assertEquals(i.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		i.setLastUpdateBy(s);
		AssertJUnit.assertEquals(i.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetIap() {
		i.setIap(s);
		AssertJUnit.assertEquals(i.getIap(),s);
	}

	@Test
	public final void testGetIapKey() {
		i.setIapKey(1L);
		AssertJUnit.assertEquals(i.getIapKey(),new Long(1));
	}

}
