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
import edu.psu.iam.cpr.service.PersonIdentifierServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;

public class PersonIdentifierTest {

	static final Cprws personidentifer = new Cprws();
	static final CprwsSEI port = personidentifer.getCprwsPort();
	
	// invalid password test.
	@Test(expectedExceptions=Exception.class)
	public void _01testGetPersonIdentifier1() throws Exception {
		PersonIdentifierServiceReturn personIdentifierServiceReturn = port.getPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
				"person_id", "100000", null, "N");
		if (personIdentifierServiceReturn.getStatusCode() != 0) {
			throw new Exception(personIdentifierServiceReturn.getStatusMessage());
		}
	}
	
	// invalid identifier type test.
	@Test(expectedExceptions=Exception.class)
	public void _02testGetPersonIdentifier2() throws Exception {
		PersonIdentifierServiceReturn personIdentifierServiceReturn = port.getPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
				"person", "100000", null, "N");
		if (personIdentifierServiceReturn.getStatusCode() != 0) {
			throw new Exception(personIdentifierServiceReturn.getStatusMessage());
		}
	}
	
	// invalid identifier value test.
	@Test(expectedExceptions=Exception.class)
	public void _03testGetPersonIdentifier3() throws Exception {
		PersonIdentifierServiceReturn personIdentifierServiceReturn = port.getPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
				"person_id", "0", null, "N");
		if (personIdentifierServiceReturn.getStatusCode() != 0) {
			throw new Exception(personIdentifierServiceReturn.getStatusMessage());
		}
	}
	
	// invalid identifier type test.
	@Test(expectedExceptions=Exception.class)
	public void _04testGetPersonIdentifier4() throws Exception {
		PersonIdentifierServiceReturn personIdentifierServiceReturn = port.getPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
				"person_id", "100000", "abcd", "N");
		if (personIdentifierServiceReturn.getStatusCode() != 0) {
			throw new Exception(personIdentifierServiceReturn.getStatusMessage());
		}
	}
	
	// invalid return history test.
	@Test(expectedExceptions=Exception.class)
	public void _05testGetPersonIdentifier5() throws Exception {
		PersonIdentifierServiceReturn personIdentifierServiceReturn = port.getPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
				"person_id", "100000", null, "Z");
		if (personIdentifierServiceReturn.getStatusCode() != 0) {
			throw new Exception(personIdentifierServiceReturn.getStatusMessage());
		}
	}
	
	// Successful Get.
	@Test
	public void _06testGetPersonIdentifier6() throws Exception {
		PersonIdentifierServiceReturn personIdentifierServiceReturn = port.getPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
				"person_id", "100000", null, "N");
		if (personIdentifierServiceReturn.getStatusCode() != 0) {
			throw new Exception(personIdentifierServiceReturn.getStatusMessage());
		}
	}
	
	// Successful Add.
	@Test
	public void _07testAddPersonIdentifier1() throws Exception {
		ServiceReturn serviceReturn = port.addPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, 
				ServiceAuthentication.GOOD_USERID, "person_id", "100000", "unit_test_identifier", "abcd1234");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	// UnSuccessful Add.
	@Test(expectedExceptions=Exception.class)
	public void _08testAddPersonIdentifier1() throws Exception {
		ServiceReturn serviceReturn = port.addPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, 
				ServiceAuthentication.GOOD_USERID, "person_id", "100000", "unit_test_identifier", "abcd1234");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	// Successful Archive.
	@Test
	public void _09testArchivePersonIdentifier() throws Exception {
		ServiceReturn serviceReturn = port.archivePersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "unit_test_identifier");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	//Unsuccessful Archive
	@Test(expectedExceptions=Exception.class)
	public void _10testArchivePersonIdentifier() throws Exception {
		ServiceReturn serviceReturn = port.archivePersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "unit_test_identifier");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	//Restore things.
	@Test
	public void _11restoreSetup() throws Exception {
		ServiceReturn serviceReturn = port.addPersonIdentifier(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
					"person_id", "100000", "unit_test_identifier","TEST_VALUE");
		if (serviceReturn.getStatusCode() != 0) {
			System.out.println("Status message is " + serviceReturn.getStatusMessage());
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}

}
