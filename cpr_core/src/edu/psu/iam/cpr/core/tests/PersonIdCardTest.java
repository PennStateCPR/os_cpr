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

import edu.psu.iam.cpr.core.database.beans.PersonIdCard;

/**
 * @author llg5
 *
 */
public class PersonIdCardTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#PersonIdCard()}.
	 */
	@Test
	public final void testPersonIdCard() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		AssertJUnit.assertNotNull(aPersonIdCard);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getStartDate()}.
	 */
	@Test
	public final void testGetStartDate() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setStartDate(d);
		AssertJUnit.assertEquals(aPersonIdCard.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setStartDate(java.util.Date)}.
	 */
	@Test
	public final void testSetStartDate() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setStartDate(d);
		AssertJUnit.assertEquals(aPersonIdCard.getStartDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getCreatedBy()}.
	 */
	@Test
	public final void testGetCreatedBy() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(aPersonIdCard.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setCreatedBy(java.lang.String)}.
	 */
	@Test
	public final void testSetCreatedBy() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setCreatedBy("SYSTEM");
		AssertJUnit.assertEquals(aPersonIdCard.getCreatedBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getLastUpdateOn()}.
	 */
	@Test
	public final void testGetLastUpdateOn() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setLastUpdateOn(d);
		AssertJUnit.assertEquals(aPersonIdCard.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setLastUpdateOn(java.util.Date)}.
	 */
	@Test
	public final void testSetLastUpdateOn() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setLastUpdateOn(d);
		AssertJUnit.assertEquals(aPersonIdCard.getLastUpdateOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getIdCardNumber()}.
	 */
	@Test
	public final void testGetIdCardNumber() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setIdCardNumber("1234567890123456");
		AssertJUnit.assertEquals(aPersonIdCard.getIdCardNumber(),"1234567890123456");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setIdCardNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetIdCardNumber() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setIdCardNumber("1234567890123456");
		AssertJUnit.assertEquals(aPersonIdCard.getIdCardNumber(),"1234567890123456");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getPersonIdCardKey()}.
	 */
	@Test
	public final void testGetPersonIdCardKey() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setPersonIdCardKey(1L);
		AssertJUnit.assertEquals(aPersonIdCard.getPersonIdCardKey(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setPersonIdCardKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetPersonIdCardKey() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setPersonIdCardKey(1L);
		AssertJUnit.assertEquals(aPersonIdCard.getPersonIdCardKey(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getCreatedOn()}.
	 */
	@Test
	public final void testGetCreatedOn() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setCreatedOn(d);
		AssertJUnit.assertEquals(aPersonIdCard.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setCreatedOn(java.util.Date)}.
	 */
	@Test
	public final void testSetCreatedOn() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setCreatedOn(d);
		AssertJUnit.assertEquals(aPersonIdCard.getCreatedOn(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getLastUpdateBy()}.
	 */
	@Test
	public final void testGetLastUpdateBy() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(aPersonIdCard.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setLastUpdateBy(java.lang.String)}.
	 */
	@Test
	public final void testSetLastUpdateBy() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setLastUpdateBy("SYSTEM");
		AssertJUnit.assertEquals(aPersonIdCard.getLastUpdateBy(), "SYSTEM");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getDataTypeKey()}.
	 */
	@Test
	public final void testGetDataTypeKey() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setDataTypeKey(152L);
		AssertJUnit.assertEquals(aPersonIdCard.getDataTypeKey(),new Long(152L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setDataTypeKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetDataTypeKey() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setDataTypeKey(152L);
		AssertJUnit.assertEquals(aPersonIdCard.getDataTypeKey(),new Long(152L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getIdSerialNumber()}.
	 */
	@Test
	public final void testGetIdSerialNumber() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setIdSerialNumber("12345");
		AssertJUnit.assertEquals(aPersonIdCard.getIdSerialNumber(),"12345");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setIdSerialNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetIdSerialNumber() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setIdSerialNumber("12345");
		AssertJUnit.assertEquals(aPersonIdCard.getIdSerialNumber(),"12345");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setPersonId(1L);
		AssertJUnit.assertEquals(aPersonIdCard.getPersonId(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setPersonId(java.lang.Long)}.
	 */
	@Test
	public final void testSetPersonId() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		aPersonIdCard.setPersonId(1L);
		AssertJUnit.assertEquals(aPersonIdCard.getPersonId(),new Long(1L));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#getEndDate()}.
	 */
	@Test
	public final void testGetEndDate() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setEndDate(d);
		AssertJUnit.assertEquals(aPersonIdCard.getEndDate(),d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.PersonIdCard#setEndDate(java.util.Date)}.
	 */
	@Test
	public final void testSetEndDate() {
		PersonIdCard aPersonIdCard = new PersonIdCard();
		Date d = new Date();
		aPersonIdCard.setEndDate(d);
		AssertJUnit.assertEquals(aPersonIdCard.getEndDate(),d);
	}

}
