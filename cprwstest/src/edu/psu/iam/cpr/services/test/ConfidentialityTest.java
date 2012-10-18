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
import edu.psu.iam.cpr.service.ConfidentialityServiceReturn;
import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.ServiceReturn;

public class ConfidentialityTest {
	
	static final Cprws userid = new Cprws();
	static final CprwsSEI port = userid.getCprwsPort();
	
	// Exception invalid password.
	@Test(expectedExceptions=Exception.class)
	public void _01testGetConfidentiality1() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, null, null, null, "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Exception invalid userid.
	@Test(expectedExceptions=Exception.class)
	public void _02testGetConfidentiality2() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold("portal", ServiceAuthentication.GOOD_PASSWORD, null, null, null, "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Exception missing data.
	@Test(expectedExceptions=Exception.class)
	public void _03testGetConfidentiality3() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, null, null, null, "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Exception missing requestor
	@Test(expectedExceptions=Exception.class)
	public void _04testGetConfidentiality4() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Invalid requestor.
	@Test(expectedExceptions=Exception.class)
	public void _05testGetConfidentiality5() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "zye", "person_id", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier type.
	@Test(expectedExceptions=Exception.class)
	public void _06testGetConfidentiality6() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Invalid person identifier.
	@Test(expectedExceptions=Exception.class)
	public void _07testGetConfidentiality7() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "1", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Test no active holds.
	@Test
	public void _08testGetConfidentiality8() throws Exception {
		port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");
		port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ADDRESS_CONFIDENTIALITY");
		port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "PHONE_CONFIDENTIALITY");
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
		AssertJUnit.assertEquals(csReturn.getNumberElements(),0);
	}
	
	// Test active hold.
	@Test
	public void _09testGetConfidentiality9() throws Exception {
		port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
		AssertJUnit.assertEquals(csReturn.getNumberElements(),1);
		AssertJUnit.assertEquals(csReturn.getConfidentialityReturn().get(0).getConfidentialityType(),"ALL_CONFIDENTIALITY");
		port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");
	}
	
	// Test Add Exception.
	@Test(expectedExceptions=Exception.class)
	public void _10testAddConfidentiality1() throws Exception {
		ServiceReturn csReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Test Add Exception.
	@Test(expectedExceptions=Exception.class)
	public void _11testAddConfidentiality2() throws Exception {
		ServiceReturn csReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "phone");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Test Add Exception.
	@Test(expectedExceptions=Exception.class)
	public void _12testAddConfidentiality3() throws Exception {
		ServiceReturn csReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "address");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}
	
	// Test Add Address.
	@Test
	public void _13testAddConfidentiality4() throws Exception {
		ServiceReturn serviceReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ADDRESS_CONFIDENTIALITY");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		AssertJUnit.assertEquals(csReturn.getNumberElements(),1);
		AssertJUnit.assertEquals(csReturn.getConfidentialityReturn().get(0).getConfidentialityType(),"ADDRESS_CONFIDENTIALITY");
		serviceReturn = port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ADDRESS_CONFIDENTIALITY");
	}
	
	// Test Add Phone.
	@Test
	public void _14testAddConfidentiality5() throws Exception {
		ServiceReturn serviceReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "PHONE_CONFIDENTIALITY");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn  csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		AssertJUnit.assertEquals(csReturn.getNumberElements(),1);
		AssertJUnit.assertEquals(csReturn.getConfidentialityReturn().get(0).getConfidentialityType(),"PHONE_CONFIDENTIALITY");
		port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "PHONE_CONFIDENTIALITY");
	}
	
	// Test Add All.
	@Test
	public void _15testAddConfidentiality6() throws Exception {
		ServiceReturn serviceReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		AssertJUnit.assertEquals(csReturn.getNumberElements(),1);
		AssertJUnit.assertEquals(csReturn.getConfidentialityReturn().get(0).getConfidentialityType(),"ALL_CONFIDENTIALITY");
		port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");
	}
	
	// Test Update All.
	@Test
	public void _16testUpdateConfidentiality1() throws Exception {
		ServiceReturn serviceReturn = port.updateConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000","N");
		AssertJUnit.assertEquals(csReturn.getNumberElements(),1);
		AssertJUnit.assertEquals(csReturn.getConfidentialityReturn().get(0).getConfidentialityType(),"ALL_CONFIDENTIALITY");
		port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");
	}
	
	// Test archive All exception..
	@Test(expectedExceptions=Exception.class)
	public void _17testArchiveConfidentiality1() throws Exception {
		ServiceReturn serviceReturn = port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "ALL_CONFIDENTIALITY");

		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		AssertJUnit.assertEquals(csReturn.getNumberElements(),0);
	}
	
	// Test archive Address exception..
	@Test(expectedExceptions=Exception.class)
	public void _18testArchiveConfidentiality2() throws Exception {
		 ServiceReturn serviceReturn = port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "address_CONFIDENTIALITY");

		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		AssertJUnit.assertEquals(csReturn.getNumberElements(),0);
	}
	
	// Test archive Phone exception..
	@Test(expectedExceptions=Exception.class)
	public void _19testArchiveConfidentiality3() throws Exception {
		ServiceReturn serviceReturn = port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "phone_CONFIDENTIALITY");

		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		AssertJUnit.assertEquals(csReturn.getNumberElements(),0);
	}
	
	// Test archive Phone exception..
	@Test(expectedExceptions=Exception.class)
	public void _20testArchiveConfidentiality4() throws Exception {
		ServiceReturn serviceReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "phone_CONFIDENTIALITY");

		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
		AssertJUnit.assertEquals(csReturn.getNumberElements(),1);
		AssertJUnit.assertEquals(csReturn.getConfidentialityReturn().get(0).getConfidentialityType(),"PHONE_CONFIDENTIALITY");
		serviceReturn = port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "phone_CONFIDENTIALITY");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
		
		csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
		AssertJUnit.assertEquals(csReturn.getNumberElements(),0);
		serviceReturn = port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "phone_CONFIDENTIALITY");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	// Test archive Phone exception..
	@Test
	public void _21testArchiveConfidentiality5() throws Exception {
		ServiceReturn serviceReturn = port.addConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "phone_CONFIDENTIALITY");

		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
		AssertJUnit.assertEquals(csReturn.getNumberElements(),1);
		AssertJUnit.assertEquals(csReturn.getConfidentialityReturn().get(0).getConfidentialityType(),"PHONE_CONFIDENTIALITY");
		serviceReturn = port.archiveConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "phone_CONFIDENTIALITY");
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	// Test Get history.
	@Test
	public void _22testGetConfidentialityHistory1() throws Exception {
		ConfidentialityServiceReturn csReturn = port.getConfidentialityHold(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "Y");
		if (csReturn.getStatusCode() != 0) {
			throw new Exception(csReturn.getStatusMessage());
		}
	}

}
