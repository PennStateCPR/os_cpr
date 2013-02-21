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
import java.util.Date;

import edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType;

/**
 * @author cpruser
 *
 */
public class PhotoIdIssuerDataTypeTest {

	Date d = new Date();
	String userId = "cpruser";
	
	PhotoIdIssuerDataType pidt = new PhotoIdIssuerDataType();
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#PhotoIdIssuerDataType()}.
	 */
	@Test
	public final void testPhotoIdIssuerDataType() {
		new PhotoIdIssuerDataType();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getPhotoIdIssuerKey()}.
	 */
	@Test
	public final void testGetPhotoIdIssuerKey() {
		pidt.setPhotoIdIssuerKey( new  Long(5));
		AssertJUnit.assertEquals(pidt.getPhotoIdIssuerKey(), new  Long(5)); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setPhotoIdIssuerKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetPhotoIdIssuerKey() {
		pidt.setPhotoIdIssuerKey( new  Long(5));
		AssertJUnit.assertEquals(pidt.getPhotoIdIssuerKey(), new  Long(5)); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		pidt.setStartDate( d);
		AssertJUnit.assertEquals(pidt.getStartDate(), d); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setStartDate(java.util.Date)}.
	 */
	@Test
	public final void testSetStartDate() {
		pidt.setStartDate( d);
		AssertJUnit.assertEquals(pidt.getStartDate(), d); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getCreatedBy()}.
	 */
	@Test
	public final void testGetCreatedBy() {
		pidt.setCreatedBy(userId);
		AssertJUnit.assertEquals(pidt.getCreatedBy(),userId); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedBy() {
		pidt.setCreatedBy(userId);
		AssertJUnit.assertEquals(pidt.getCreatedBy(), userId); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getLastUpdateOn()}.
	 */
	@Test
	public final void testGetLastUpdateOn() {
		pidt.setLastUpdateOn( d);
		AssertJUnit.assertEquals(pidt.getLastUpdateOn(), d); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void testSetLastUpdateOn() {
		pidt.setLastUpdateOn( d);
		AssertJUnit.assertEquals(pidt.getLastUpdateOn(), d); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getPhotoIdIssuerDataTypeKey()}.
	 */
	@Test
	public final void testGetPhotoIdIssuerDataTypeKey() {
		pidt.setPhotoIdIssuerDataTypeKey( new  Long(5));
		AssertJUnit.assertEquals(pidt.getPhotoIdIssuerDataTypeKey(), new  Long(5));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setPhotoIdIssuerDataTypeKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetPhotoIdIssuerDataTypeKey() {
		pidt.setPhotoIdIssuerDataTypeKey( new  Long(5));
		AssertJUnit.assertEquals(pidt.getPhotoIdIssuerDataTypeKey(), new  Long(5));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getCreatedOn()}.
	 */
	@Test
	public final void testGetCreatedOn() {
		pidt.setCreatedOn( d);
		AssertJUnit.assertEquals(pidt.getCreatedOn(), d); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void testSetCreatedOn() {
		pidt.setCreatedOn( d);
		AssertJUnit.assertEquals(pidt.getCreatedOn(), d); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getLastUpdateBy()}.
	 */
	@Test
	public final void testGetLastUpdateBy() {
		pidt.setLastUpdateBy(userId);
		AssertJUnit.assertEquals(pidt.getLastUpdateBy(), userId); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateBy() {
		pidt.setLastUpdateBy(userId);
		AssertJUnit.assertEquals(pidt.getLastUpdateBy(), userId); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getDataTypeKey()}.
	 */
	@Test
	public final void testGetDataTypeKey() {
		pidt.setDataTypeKey( new  Long(5));
		AssertJUnit.assertEquals(pidt.getDataTypeKey(), new  Long(5)); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setDataTypeKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetDataTypeKey() {
		pidt.setDataTypeKey( new  Long(5));
		AssertJUnit.assertEquals(pidt.getDataTypeKey(), new  Long(5)); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		pidt.setEndDate( d);
		AssertJUnit.assertEquals(pidt.getEndDate(), d); 
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PhotoIdIssuerDataType#setEndDate(java.util.Date)}.
	 */
	@Test
	public final void testSetEndDate() {
		pidt.setEndDate( d);
		AssertJUnit.assertEquals(pidt.getEndDate(), d); 
	}

}
