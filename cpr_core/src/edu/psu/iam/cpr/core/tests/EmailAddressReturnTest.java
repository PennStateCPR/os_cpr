/* SVN FILE: $Id: EmailAddressReturnTest.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
 * 
 * @package edu.psu.iam.cpr.core.tests
 * @author $Author: jvuccolo $
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
 * @author jvuccolo
 *
 */
public class EmailAddressReturnTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#EmailAddressReturn(java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testEmailAddressReturnStringStringStringStringStringStringStringStringString() {
		new EmailAddressReturn("home", "xyz123@psu.edu", "","","","","","");
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
		EmailAddressReturn n = new EmailAddressReturn();
		n.setEmailAddressType("home");
		AssertJUnit.assertEquals(n.getEmailAddressType(), "home");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#setEmailAddressType(java.lang.String)}.
	 */
	@Test
	public final void testSetEmailAddressType() {
		EmailAddressReturn n = new EmailAddressReturn();
		n.setEmailAddressType("home");
		AssertJUnit.assertEquals(n.getEmailAddressType(), "home");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#getEmailAddress()}.
	 */
	@Test
	public final void testGetEmailAddress() {
		EmailAddressReturn n = new EmailAddressReturn();
		n.setEmailAddress("xyz123@psu.edu");
		AssertJUnit.assertEquals(n.getEmailAddress(), "xyz123@psu.edu");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.EmailAddressReturn#setEmailAddress(java.lang.String)}.
	 */
	@Test
	public final void testSetEmailAddress() {
		EmailAddressReturn n = new EmailAddressReturn();
		n.setEmailAddress("xyz123@psu.edu");
		AssertJUnit.assertEquals(n.getEmailAddress(), "xyz123@psu.edu");
	}

}
