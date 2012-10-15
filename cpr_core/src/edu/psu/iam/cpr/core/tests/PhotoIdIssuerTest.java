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

import edu.psu.iam.cpr.core.database.beans.PhotoIdIssuer;

public class PhotoIdIssuerTest {

	Date d = new Date();
	String userId = "llg5";
	String aString = "thestring";
	PhotoIdIssuer pi = new PhotoIdIssuer();
	@Test
	public final void testPhotoIdIssuer() {
		new PhotoIdIssuer();
	}

	@Test
	public final void testGetPhotoIdIssuerKey() {
		pi.setPhotoIdIssuerKey(new Long(3));
		AssertJUnit.assertEquals(pi.getPhotoIdIssuerKey(),new Long(3));
	}

	@Test
	public final void testSetPhotoIdIssuerKey() {
		pi.setPhotoIdIssuerKey(new Long(3));
		AssertJUnit.assertEquals(pi.getPhotoIdIssuerKey(),new Long(3));
	}

	@Test
	public final void testGetStartDate() {
		pi.setStartDate(d);
		AssertJUnit.assertEquals(pi.getStartDate(),d);
	}

	@Test
	public final void testSetStartDate() {
		pi.setStartDate(d);
		AssertJUnit.assertEquals(pi.getStartDate(),d);
	}

	@Test
	public final void testGetCreatedBy() {
		pi.setCreatedBy(userId);
		AssertJUnit.assertEquals(pi.getCreatedBy(),userId);
	}

	@Test
	public final void testSetCreatedBy() {
		pi.setCreatedBy(userId);
		AssertJUnit.assertEquals(pi.getCreatedBy(),userId);
	}

	@Test
	public final void testGetLastUpdateOn() {
		pi.setLastUpdateOn(d);
		AssertJUnit.assertEquals(pi.getLastUpdateOn(), d);
	}

	@Test
	public final void testSetLastUpdateOn() {
		pi.setLastUpdateOn(d);
		AssertJUnit.assertEquals(pi.getLastUpdateOn(), d);
	}

	@Test
	public final void testGetCreatedOn() {
		pi.setCreatedOn(d);
		AssertJUnit.assertEquals(pi.getCreatedOn(), d);
	}

	@Test
	public final void testSetCreatedOn() {
		pi.setCreatedOn(d);
		AssertJUnit.assertEquals(pi.getCreatedOn(), d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		pi.setLastUpdateBy(userId);
		AssertJUnit.assertEquals(pi.getLastUpdateBy(),userId);
	}

	@Test
	public final void testSetLastUpdateBy() {
		pi.setLastUpdateBy(userId);
		AssertJUnit.assertEquals(pi.getLastUpdateBy(),userId);
	}

	@Test
	public final void testGetEndDate() {
		pi.setEndDate(d);
		AssertJUnit.assertEquals(pi.getEndDate(),d);
	}

	@Test
	public final void testSetEndDate() {
		pi.setEndDate(d);
		AssertJUnit.assertEquals(pi.getEndDate(),d);
	}

	@Test
	public final void testGetPhotoIdIssuerDesc() {
		pi.setPhotoIdIssuerDesc(aString);
		AssertJUnit.assertEquals(pi.getPhotoIdIssuerDesc(),aString);
	}

	@Test
	public final void testSetPhotoIdIssuerDesc() {
		pi.setPhotoIdIssuerDesc(aString);
		AssertJUnit.assertEquals(pi.getPhotoIdIssuerDesc(),aString);
	}

	@Test
	public final void testGetPhotoIdIssuer() {
		pi.setPhotoIdIssuer(aString);
		AssertJUnit.assertEquals(pi.getPhotoIdIssuer(),aString);
	}

	@Test
	public final void testSetPhotoIdIssuer() {
		pi.setPhotoIdIssuer(aString);
		AssertJUnit.assertEquals(pi.getPhotoIdIssuer(),aString);
	}

}
