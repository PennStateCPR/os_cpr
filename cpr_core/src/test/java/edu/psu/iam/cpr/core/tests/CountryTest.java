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

import edu.psu.iam.cpr.core.database.beans.Country;

public class CountryTest {

	Country c = new Country();
	Date d = new Date();
	String s = "abcd";
	
	@Test
	public final void testCountry() {
		AssertJUnit.assertNotNull(c);
	}

	@Test
	public final void testGetCountryAlphaCode2() {
		c.setCountryCodeTwo(s);
		AssertJUnit.assertEquals(c.getCountryCodeTwo(),s);
	}

	@Test
	public final void testGetCountryKey() {
		c.setCountryKey(1L);
		AssertJUnit.assertEquals(c.getCountryKey(), new Long(1));
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
	public final void testSetEndDate() {
		c.setEndDate(d);
		AssertJUnit.assertEquals(c.getEndDate(),d);
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
	public final void testGetCountry() {
		c.setCountry(s);
		AssertJUnit.assertEquals(c.getCountry(),s);
	}

	@Test
	public final void testGetCountryAlphaCode3() {
		c.setCountryCodeThree(s);
		AssertJUnit.assertEquals(c.getCountryCodeThree(),s);
	}

	@Test
	public final void testGetCountryNumericCode() {
		c.setCountryNumericCode(s);
		AssertJUnit.assertEquals(c.getCountryNumericCode(), s);
	}

}
