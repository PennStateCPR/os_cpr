/* SVN FILE: $Id: EmailAddressTest.java 5340 2012-09-27 14:48:52Z cpruser $ */
/**
 * 
 * @package edu.psu.iam.cpr.core.tests
 * @author $Author: cpruser $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
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

import edu.psu.iam.cpr.core.database.beans.EmailAddress;

public class EmailAddressTest {

	EmailAddress e = new EmailAddress();
	Date d = new Date();
	
	@Test
	public final void testEmailAddress() {
		AssertJUnit.assertNotNull(e);
	}

	@Test
	public final void testGetStartDate() {
		e.setStartDate(d);
		AssertJUnit.assertEquals(e.getStartDate(),d);
	}

	@Test
	public final void testGetCreatedBy() {
		e.setCreatedBy("abcd");
		AssertJUnit.assertEquals(e.getCreatedBy(),"abcd");
	}

	@Test
	public final void testGetLastUpdateOn() {
		e.setLastUpdateOn(d);
		AssertJUnit.assertEquals(e.getLastUpdateOn(),d);
	}

	@Test
	public final void testGetEmailAddressKey() {
		e.setEmailAddressKey(1L);
		AssertJUnit.assertEquals(e.getEmailAddressKey(),new Long(1));
	}

	@Test
	public final void testGetCreatedOn() {
		e.setCreatedOn(d);
		AssertJUnit.assertEquals(e.getCreatedOn(),d);
	}

	@Test
	public final void testGetLastUpdateBy() {
		e.setLastUpdateBy("abcd");
		AssertJUnit.assertEquals(e.getLastUpdateBy(),"abcd");
	}

	@Test
	public final void testGetDataTypeKey() {
		e.setDataTypeKey(1L);
		AssertJUnit.assertEquals(e.getDataTypeKey(),new Long(1));
	}

	@Test
	public final void testGetPersonId() {
		e.setPersonId(1L);
		AssertJUnit.assertEquals(e.getPersonId(),new Long(1));
	}

	@Test
	public final void testGetEndDate() {
		e.setEndDate(d);
		AssertJUnit.assertEquals(e.getEndDate(), d);
	}

	@Test
	public final void testGetEmailAddress() {
		e.setEmailAddress("abcd");
		AssertJUnit.assertEquals(e.getEmailAddress(),"abcd");
	}

}
