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
import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.ServiceReturn;

public class SecurityActionTest {
	
	static final Cprws userid = new Cprws();
	static final CprwsSEI port = userid.getCprwsPort();

	// Invalid service principal.
	@Test(expectedExceptions=Exception.class)
	public void _01testBlockUser1() throws Exception {
		ServiceReturn securityReturn = port.blockUser("portral", ServiceAuthentication.GOOD_PASSWORD, null, null, null, null);
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid password
	@Test(expectedExceptions=Exception.class)
	public void _02testBlockUser2() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, null, null, null, null);
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid requested by
	@Test(expectedExceptions=Exception.class)
	public void _03testBlockUser3() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, null, "person_id", "100000", "xyz");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid requested by
	@Test(expectedExceptions=Exception.class)
	public void _04testBlockUser4() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, "zzz", "person_id", "100000", "xyz");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier Type
	@Test(expectedExceptions=Exception.class)
	public void _05testBlockUser5() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_", "100000", "xyz");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier Type
	@Test(expectedExceptions=Exception.class)
	public void _06testBlockUser6() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, null, "100000", "xyz");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier
	@Test(expectedExceptions=Exception.class)
	public void _07testBlockUser7() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", null, "xyz");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier
	@Test(expectedExceptions=Exception.class)
	public void _08testBlockUser8() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1", "xyz");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Invalid userid
	@Test(expectedExceptions=Exception.class)
	public void _09testBlockUser9() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1", null);
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}
	
	// Valid Userid for doing a block.
	@Test
	public void _10testBlockUser10() throws Exception {
		ServiceReturn securityReturn = port.blockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "smj368");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
		securityReturn = port.unblockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "smj368");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}
	}

	// Unblock a user.
	@Test
	public void _11testUnblockUser1() throws Exception {
		ServiceReturn securityReturn = port.unblockUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "smj368");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}		
	}
	
	// Disable a user.
	@Test
	public void _12testDisableUser1() throws Exception {
		ServiceReturn securityReturn = port.disableUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "smj368");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}		
	}
	
	// Enable a user.
	@Test
	public void _13testEnableUser1() throws Exception {
		ServiceReturn securityReturn = port.enableUser(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "smj368");
		if (securityReturn.getStatusCode() != 0) {
			throw new Exception(securityReturn.getStatusMessage());
		}		
	}
}
