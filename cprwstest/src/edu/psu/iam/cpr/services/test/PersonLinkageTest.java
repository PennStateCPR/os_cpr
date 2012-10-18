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
import edu.psu.iam.cpr.service.PersonLinkageReturn;
import edu.psu.iam.cpr.service.PersonLinkageServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;

public class PersonLinkageTest {
	

	static final Cprws userid = new Cprws();
	static final CprwsSEI port = userid.getCprwsPort();
	// Missing data.
	@Test(expectedExceptions=Exception.class)
	public void _01testGetPersonLinkage1() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(null, null, null, null, null, "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}
	}
	
	// Empty strings.
	@Test(expectedExceptions=Exception.class)
	public void _02testGetPersonLinkage2() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage("", "", "", "", "", "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}
	}
	
	// Invalid userid.
	@Test(expectedExceptions=Exception.class)
	public void _03testGetPersonLinkage3() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage("portal", ServiceAuthentication.GOOD_PASSWORD, null, null, null, "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
	}
	
	// Bad password.
	@Test(expectedExceptions=Exception.class)
	public void _04testGetPersonLinkage4() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, null, null, null, "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
	}
	
	// Good userid&password, missing data.
	@Test(expectedExceptions=Exception.class)
	public void _05testGetPersonLinkage5() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, null, null, null, "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
	}
	
	// Request is not authorized.
	@Test(expectedExceptions=Exception.class)
	public void _06testGetPersonLinkage6() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvucclo", "person_id", "100000", "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
	}
	
	// Invalid identifier type.
	@Test(expectedExceptions=Exception.class)
	public void _07testGetPersonLinkage7() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "personid", "100000", "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
	}
	
	// Inactive person.
	@Test(expectedExceptions=Exception.class)
	public void _08testGetPersonLinkage8() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100001", "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
	}
	
	// Person with no linkages.
	@Test
	public void _09testGetPersonLinkage9() throws Exception   {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100003", "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
		AssertJUnit.assertEquals(personLinkageReturn.getNumberElements(),0);
	}
	
	// Person with linkages.
	@Test
	public void _10testGetPersonLinkage10() throws Exception   {
		ServiceReturn serviceReturn = port.addPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "LINKAGE_TYPE_DEPENDANT", "person_id", "100002");
		if ( serviceReturn.getStatusCode() != 0) {
			throw new Exception( serviceReturn.getStatusMessage());
		}
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000" ,"N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
		AssertJUnit.assertTrue((personLinkageReturn.getNumberElements() > 0) ? true : false);
	}
	
	// Get history.
	@Test
	public void _11testGetPersonLinkageHistory() throws Exception {
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage (ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "Y");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}
		AssertJUnit.assertTrue((personLinkageReturn.getNumberElements() > 0) ? true : false);
	}
	
	// Add Exception.  Invalid linkage type.
	@Test(expectedExceptions=Exception.class)
	public void _12testAddPersonLinkage1() throws Exception {
		 ServiceReturn personLinkageReturn = port.addPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "who cares", "person_id", "100000");
		if (personLinkageReturn.getStatusCode() != 0 ) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}
	}
	
	// Add Exception.  Attempting to link a person to him or herslef. 
	@Test(expectedExceptions=Exception.class)
	public void _13testAddPersonLinkage2() throws Exception {
		 ServiceReturn personLinkageReturn = port.addPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "LINKAGE_TYPE_DEPENDANT", "person_id", "100000");
		if (personLinkageReturn.getStatusCode() != 0 ) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}
	}
	
	// Add working OK.
	@Test
	public void _14testAddPersonLinkage3() throws Exception {
		ServiceReturn serviceReturn = port.addPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "LINKAGE_TYPE_DEPENDANT", "person_id", "100003");
		if (serviceReturn.getStatusCode() != 0 ) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		PersonLinkageServiceReturn personLinkageReturn = port.getPersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}	
		PersonLinkageReturn plReturn = null;
		for (Iterator <PersonLinkageReturn> it = personLinkageReturn.getPersonLinkageReturn().iterator(); it.hasNext(); ) {
			plReturn = it.next();
			if (plReturn.getLinkageTypeString().equals("LINKAGE_TYPE_DEPENDANT")) {
				break;
			}
		}
		AssertJUnit.assertEquals(plReturn.getLinkeePersonId(), 100003L);
	}
	
	// Skipping the update test, since its the add test.
	
	// Archive Person Linkage.
	@Test
	public void _15testArchivePersonLinkage1() throws Exception {
		ServiceReturn personLinkageReturn = port.archivePersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "LINKAGE_TYPE_DEPENDANT", "person_id", "100003");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}
	}
	
	// Already archived.
	@Test(expectedExceptions=Exception.class)
	public void _16testArchivePersonLinkage2() throws Exception {
		ServiceReturn personLinkageReturn = port.archivePersonLinkage(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "LINKAGE_TYPE_DEPENDANT", "person_id", "100003");
		if (personLinkageReturn.getStatusCode() != 0) {
			throw new Exception(personLinkageReturn.getStatusMessage());
		}
	}

}
