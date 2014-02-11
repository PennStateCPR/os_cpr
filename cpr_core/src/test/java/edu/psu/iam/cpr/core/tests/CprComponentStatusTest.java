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

import edu.psu.iam.cpr.core.database.beans.CprComponentStatus;

public class CprComponentStatusTest {

	Date d = new Date();
	String s = "abcd";
	CprComponentStatus c = new CprComponentStatus();
	
	@Test
	public final void testCprComponentStatus() {
		new CprComponentStatus();
	}

	@Test
	public final void testGetCprComponentDesc() {
		c.setCprComponentDesc(s);
		AssertJUnit.assertEquals(c.getCprComponentDesc(),s);
	}

	@Test
	public final void testGetCreatedBy() {
		c.setCreatedBy(s);
		AssertJUnit.assertEquals(c.getCreatedBy(),s);
	}

	@Test
	public final void testGetLastUpdateOn() {
		c.setLastUpdateOn(d);
		AssertJUnit.assertEquals(c.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetActiveFlag() {
		c.setActiveFlag(s);
		AssertJUnit.assertEquals(c.getActiveFlag(),s);
	}

	@Test
	public final void testGetCreatedOn() {
		c.setCreatedOn(d);
		AssertJUnit.assertEquals(c.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		c.setLastUpdateBy(s);
		AssertJUnit.assertEquals(c.getLastUpdateBy(),s);
	}

	@Test
	public final void testGetCprComponentStatusKey() {
		c.setCprComponentStatusKey(1L);
		AssertJUnit.assertEquals(c.getCprComponentStatusKey(),new Long(1));
	}

	@Test
	public final void testGetCprComponent() {
		c.setCprComponent(s);
		AssertJUnit.assertEquals(c.getCprComponent(),s);
	}

}
