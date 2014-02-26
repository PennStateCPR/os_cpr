/* SVN FILE: $Id: EmailAddressReturnTest.java 5340 2012-09-27 14:48:52Z cpruser $ */
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

import edu.psu.iam.cpr.core.service.returns.EmailAddressReturn;

/**
 * @author cpruser
 *
 */
public class EmailAddressReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#EmailAddressReturn(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testEmailAddressReturnStringStringStringStringStringStringStringStringString() {
		new EmailAddressReturn(null, "home", "xyz123@psu.edu","","","","","", "");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#EmailAddressReturn()}.
	 */
	@Test
	public final void testEmailAddressReturn() {
		new EmailAddressReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#getEmailAddressType()}.
	 */
	@Test
	public final void testGetEmailAddressType() {
		final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
		emailAddressReturn.setEmailAddressType("home");
		AssertJUnit.assertEquals("home", emailAddressReturn.getEmailAddressType());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#setEmailAddressType(java.lang.String)}.
	 */
	@Test
	public final void testSetEmailAddressType() {
		final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
		emailAddressReturn.setEmailAddressType("home");
		AssertJUnit.assertEquals("home", emailAddressReturn.getEmailAddressType());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#getEmailAddress()}.
	 */
	@Test
	public final void testGetEmailAddress() {
		final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
		emailAddressReturn.setEmailAddress("xyz123@psu.edu");
		AssertJUnit.assertEquals("xyz123@psu.edu", emailAddressReturn.getEmailAddress());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#setEmailAddress(java.lang.String)}.
	 */
	@Test
	public final void testSetEmailAddress() {
		final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
		emailAddressReturn.setEmailAddress("xyz123@psu.edu");
		AssertJUnit.assertEquals("xyz123@psu.edu", emailAddressReturn.getEmailAddress());
	}

    @Test
    public final void testSetStartDate() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setStartDate("1/1/2014");
        AssertJUnit.assertEquals("1/1/2014", emailAddressReturn.getStartDate());
    }

    @Test
    public final void testSetEndDate() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setEndDate("1/1/2014");
        AssertJUnit.assertEquals("1/1/2014", emailAddressReturn.getEndDate());
    }

    @Test
    public final void testGetUri() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setUri("uri");
        AssertJUnit.assertEquals("uri", emailAddressReturn.getUri());
    }

    @Test
    public final void testGetEmailKey() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setEmailKey("emailKey");
        AssertJUnit.assertEquals("emailKey", emailAddressReturn.getEmailKey());
    }

    @Test
    public final void testGetCreatedOn() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setCreatedOn("1/1/2014");
        AssertJUnit.assertEquals("1/1/2014", emailAddressReturn.getCreatedOn());
    }

    @Test
    public final void testGetCreatedBy() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setCreatedBy("cpruser");
        AssertJUnit.assertEquals("cpruser", emailAddressReturn.getCreatedBy());
    }

    @Test
    public final void testGetLastUpdatedOn() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setLastUpdateOn("1/1/2014");
        AssertJUnit.assertEquals("1/1/2014", emailAddressReturn.getLastUpdateOn());
    }

    @Test
    public final void testGetLastUpdatedBy() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        emailAddressReturn.setLastUpdateBy("cpruser");
        AssertJUnit.assertEquals("cpruser", emailAddressReturn.getLastUpdateBy());
    }

    @Test
    public final void testToString() {
        final EmailAddressReturn emailAddressReturn = new EmailAddressReturn();
        AssertJUnit.assertEquals("EmailAddressReturn", emailAddressReturn.toString());
    }
}
