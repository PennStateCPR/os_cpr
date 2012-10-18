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
import edu.psu.iam.cpr.service.CredentialServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;

public class CredentialTest {

	static final Cprws userid = new Cprws();
	static final CprwsSEI port = userid.getCprwsPort();
	

	@Test
	public void _00setUp() {

		port.archiveCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000",  "CREDENTIAL_TYPE_OPENID");
		port.archiveCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000",  "CREDENTIAL_TYPE_FACEBOOK");
		port.archiveCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000",  "CREDENTIAL_TYPE_SECUREID");
	}
	
	@Test
	(expectedExceptions=Exception.class)
	public void _01testAddCredential1() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(null, null, null, null, null, null, null);
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	
	@Test
	(expectedExceptions=Exception.class)
	public void _02testAddCredential2() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential("portal", ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "740221", "credential_type_facebook", "xxx");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	@Test
	(expectedExceptions=Exception.class)
	public void _03testAddCredential3() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, "jvuccolo", "person_id", "740221", "credential_type_facebook", "xxx");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	@Test
	(expectedExceptions=Exception.class)
	public void _04testAddCredential4() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_", "740221", "credential_type_facebook", "xxx");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	@Test
	(expectedExceptions=Exception.class)
	public void _05testAddCredential5() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "-1", "credential_type_facebook", "xxx");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	@Test
	(expectedExceptions=Exception.class)
	public void _06testAddCredential6() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100000", "credential_type_facebook", "xxx");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	@Test
	(expectedExceptions=Exception.class)
	public void _07testAddCredential7() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_", "xxx");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	@Test
	(expectedExceptions=Exception.class)
	public void _08testAddCredential8() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook", null);
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	@Test
	public void _09testAddCredential9() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook", "abcd");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _10testAddCredential10() throws Exception {
		ServiceReturn credentialServiceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook", "abcd");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _11testGetCredential1() throws Exception {
		CredentialServiceReturn credentialServiceReturn = port.getCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", null, "N");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());			
		}
		if (credentialServiceReturn.getNumberElements() == 0) {
			throw new Exception("No records were returned!");
		}
	}
	
	@Test
	public void _12testGetCredential2() throws Exception {
		port.archiveCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD,"jvuccolo", "person_id", "100000",  "credential_type_facebook");
		CredentialServiceReturn credentialServiceReturn = port.getCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", null, "N");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());			
		}
		if (credentialServiceReturn.getNumberElements() != 0) {
			throw new Exception("No records were returned!");
		}
		ServiceReturn serviceReturn = port.addCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook", "abcd");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _13testCredentialByType1() throws Exception {
		CredentialServiceReturn credentialServiceReturn = port.getCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook", "N");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
		if (credentialServiceReturn.getNumberElements() != 1) {
			throw new Exception("No records were returned!");
		}
	}
	
	@Test
	public void _14testCredentialByType2() throws Exception {
		CredentialServiceReturn credentialServiceReturn = port.getCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_secureid", "N");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}
		if (credentialServiceReturn.getNumberElements() != 0) {
			throw new Exception("No records were returned!");
		}
	}
	
	@Test
	public void _15testCredentialHistory() throws Exception {
		CredentialServiceReturn credentialServiceReturn = port.getCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", null, "N");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}		
	}
	
	@Test
	public void _16testCredentialByTypeHistory() throws Exception {
		CredentialServiceReturn credentialServiceReturn = port.getCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook", "Y");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}				
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _17testCredentialArchive1() throws Exception {
		ServiceReturn credentialServiceReturn = port.archiveCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_secureid");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}				
		
	}
	
	@Test
	public void _18testCredentialArchive2() throws Exception {
		ServiceReturn credentialServiceReturn = port.archiveCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}				
		
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _19testCredentialArchive3() throws Exception {
		ServiceReturn credentialServiceReturn = port.archiveCredential(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "credential_type_facebook");
		if (credentialServiceReturn.getStatusCode() != 0) {
			throw new Exception(credentialServiceReturn.getStatusMessage());
		}				
		
	}

}
