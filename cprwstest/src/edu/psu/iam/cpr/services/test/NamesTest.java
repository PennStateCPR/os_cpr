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
import edu.psu.iam.cpr.service.NameReturn;
import edu.psu.iam.cpr.service.NamesServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;

public class NamesTest {
	
	static final Cprws names = new Cprws();
	static final CprwsSEI port = names.getCprwsPort();

	// invalid password test.
	@Test(expectedExceptions=Exception.class)
	public void _01testGetNames1() throws Exception {
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, "blah", ServiceAuthentication.GOOD_USERID, "person_id", "100000",null,"N");
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	
	// Not authorized user.
	@Test(expectedExceptions=Exception.class)
	public void _02testGetNames2() throws Exception {
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jxs", "person_id", "100000",null,"N");
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Invalid person id.
	@Test(expectedExceptions=Exception.class)
	public void _03testGetNames3() throws Exception {
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1",null,"N");
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Success.
	@Test
	public void _04testGetNames4() throws Exception {
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000",null,"N");
		if (namesServiceReturn.getStatusCode() != 0) {
			System.out.println("Message " + namesServiceReturn.getStatusMessage());
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Success.
	@Test
	public void _05testGetNamesHistory1() throws Exception {
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000",null,"Y");
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Missing name type.
	@Test(expectedExceptions=Exception.class)
	public void _06testAddName1() throws Exception {
		ServiceReturn namesServiceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", null, null, "James", null, "Jones", null);
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Invalid document type.
	@Test(expectedExceptions=Exception.class)
	public void _07testAddName2() throws Exception {
		ServiceReturn namesServiceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "legal_name", "junk", "James", null, "Jones", null);
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Missing name components.
	@Test(expectedExceptions=Exception.class)
	public void _08testAddName3() throws Exception {
		ServiceReturn namesServiceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "legal_name", null, null, null, null, null);
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Successful add of a name that just has a last name.
	@Test
	public void _09testAddName4() throws Exception {
		ServiceReturn serviceReturn = port.archiveName(ServiceAuthentication.GOOD_USERID, 
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "legal_name", null);
		serviceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 
				"person_id", "100000", "legal_name", null, null, null, "Cher", null);
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, 
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000",null,"N");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
		NameReturn nameReturn = null;
		for (final Iterator<NameReturn> it = namesServiceReturn.getNamesReturnRecord().iterator(); it.hasNext(); ) {
			nameReturn = it.next();
			if (nameReturn.getNameType().equals("LEGAL_NAME")) {
				break;
			}
		}
		AssertJUnit.assertEquals(nameReturn.getLastName(), "Cher");
	}
	
	// Successful add of a name with a first name and a last name.
	@Test
	public void _10testAddName5() throws Exception {
		ServiceReturn serviceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "legal_name", null, "James", null, "Jones", null);
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000",null,"N");
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
		NameReturn nameReturn = null;
		for (final Iterator<NameReturn> it = namesServiceReturn.getNamesReturnRecord().iterator(); it.hasNext(); ) {
			nameReturn = it.next();
			if (nameReturn.getNameType().equals("LEGAL_NAME")) {
				break;
			}
		}
		AssertJUnit.assertEquals(nameReturn.getLastName(), "Jones");
		AssertJUnit.assertEquals(nameReturn.getEndDate(), null);
	}
	
	// Unsuccessful name type/document type combination.
	@Test(expectedExceptions=Exception.class)
	public void _11testAddName6() throws Exception {
		ServiceReturn namesServiceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "preferred_name", "passport", "James", null, "Jackson", null);
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	// Successful name type/document type combination.
	@Test
	public void _12testAddName7() throws Exception {
		ServiceReturn serviceReturn = port.archiveName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "documented_name", "passport");
//		if (namesServiceReturn.getStatusCode() != 0) {
//			throw new Exception(namesServiceReturn.getStatusMessage());
//		}		
		serviceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "documented_name", "passport", "James", null, "Jackson", null);
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		NamesServiceReturn namesServiceReturn = port.getName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000",null,"N");
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
		NameReturn nameReturn = null;
		for (final Iterator<NameReturn> it = namesServiceReturn.getNamesReturnRecord().iterator(); it.hasNext(); ) {
			nameReturn = it.next();
			if (nameReturn.getNameType().equals("DOCUMENTED_NAME") && nameReturn.getDocumentType().equals("PASSPORT")) {
				break;
			}
		}
		AssertJUnit.assertEquals(nameReturn.getLastName(), "Jackson");
	}
	
	@Test
	public void _13testUpdateName1() throws Exception {
		ServiceReturn namesServiceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "legal_name", null, "James", null, "Jackson", null);
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _14testUpdateName2() throws Exception {
		ServiceReturn namesServiceReturn = port.addName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "preferred_name", null, "James", null, "James", null);
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _15testArchiveName() throws Exception {
		ServiceReturn namesServiceReturn = port.archiveName(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "preferred_name", null);
		if (namesServiceReturn.getStatusCode() != 0) {
			throw new Exception(namesServiceReturn.getStatusMessage());
		}
	}

}
