/**
 * 
 */
package edu.psu.iam.cpr.services.test;

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
import java.util.Iterator;
import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.EmailAddressReturn;
import edu.psu.iam.cpr.service.EmailAddressServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;

public class EmailAddressTest {
	
	static final Cprws userid = new Cprws();
	static final CprwsSEI port = userid.getCprwsPort();
	

	// invalid password.
	@Test(expectedExceptions=Exception.class)
	public void _01testGetEmailAddress1() throws Exception {
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "N");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Not authorized user.
	@Test(expectedExceptions=Exception.class)
	public void _02testGetEmailAddress2() throws Exception {
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jxs", "person_id", "100010", "N");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Invalid person id.
	@Test(expectedExceptions=Exception.class)
	public void _03testGetEmailAddress3() throws Exception {
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1", "N");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Missing email address type.
	@Test(expectedExceptions=Exception.class)
	public void _04testAddEmailAddress1() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.addEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", null, "inter@gration.test");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Missing email address.
	@Test(expectedExceptions=Exception.class)
	public void _05testAddEmailAddress2() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.addEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "OTHER_EMAIL", null);
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}

	// Invalid email address.
	@Test(expectedExceptions=Exception.class)
	public void _06testAddEmailAddress3() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.addEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "OTHER_EMAIL", "1@2.3");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Success add email address.
	@Test
	public void _07testAddEmailAddress4() throws Exception {
		ServiceReturn serviceReturn = port.addEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "OTHER_EMAIL", "inter@gration.test");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "N");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
		EmailAddressReturn emailAddressReturn = null;
		for (final Iterator<EmailAddressReturn> it = emailAddressServiceReturn.getEmailAddressReturnRecord().iterator(); it.hasNext(); ) {
			emailAddressReturn = it.next();
			if (emailAddressReturn.getEmailAddressType().equals("OTHER_EMAIL")) {
				break;
			}
		}
		AssertJUnit.assertEquals(emailAddressReturn.getEmailAddress(), "inter@gration.test");
	}	
	
	// Success without history.
	@Test
	public void _08testGetEmailAddress4() throws Exception {
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010",  "N");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Success with history.
	@Test
	public void _09testGetEmailAddressHistory1() throws Exception {
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010",  "Y");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	

	
	// Missing email address type.
	@Test(expectedExceptions=Exception.class)
	public void _10testUpdateEmailAddress1() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.updateEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", null, "inter@gration.test");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Missing email address.
	@Test(expectedExceptions=Exception.class)
	public void _11testUpdateEmailAddress2() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.updateEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "OTHER_EMAIL", null);
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Invalid email address.
	@Test(expectedExceptions=Exception.class)
	public void _12testUpdateEmailAddress3() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.updateEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "OTHER_EMAIL", "1@2.3");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}	
	
	// Success update email address.
	@Test
	public void _13testUpdateEmailAddress4() throws Exception {
		ServiceReturn serviceReturn = port.updateEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "OTHER_EMAIL", "inter2@gration.test");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "N");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
		EmailAddressReturn emailAddressReturn = null;
		for (final Iterator<EmailAddressReturn> it = emailAddressServiceReturn.getEmailAddressReturnRecord().iterator(); it.hasNext(); ) {
			emailAddressReturn = it.next();
			if (emailAddressReturn.getEmailAddressType().equals("OTHER_EMAIL")) {
				break;
			}
		}
		AssertJUnit.assertEquals(emailAddressReturn.getEmailAddress(), "inter2@gration.test");
	}
	
	// Missing email address type.
	@Test(expectedExceptions=Exception.class)
	public void _14testArchiveEmailAddress1() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.archiveEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", null);
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}

	// Invalid email address type.
	@Test(expectedExceptions=Exception.class)
	public void _15testArchiveEmailAddress2() throws Exception {
		ServiceReturn emailAddressServiceReturn = port.archiveEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "AOL_MAIL");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
	}
	
	// Success.
	@Test
	public void _16testArchiveEmailAddress3() throws Exception {
		int hasOtherEmail = 0;
		ServiceReturn serviceReturn = port.archiveEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "OTHER_EMAIL");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		EmailAddressServiceReturn emailAddressServiceReturn = port.getEmailAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100010", "N");
		if (emailAddressServiceReturn.getStatusCode() != 0) {
			throw new Exception(emailAddressServiceReturn.getStatusMessage());
		}
		EmailAddressReturn emailAddressReturn = null;
		for (final Iterator<EmailAddressReturn> it = emailAddressServiceReturn.getEmailAddressReturnRecord().iterator(); it.hasNext(); ) {
			emailAddressReturn = it.next();
			if (emailAddressReturn.getEmailAddressType().equals("OTHER_EMAIL")) {
				hasOtherEmail++;
				break;
			}
		}
		AssertJUnit.assertEquals(hasOtherEmail, 0);
	}
}
