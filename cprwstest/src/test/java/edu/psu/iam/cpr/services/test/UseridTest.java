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
import java.util.Iterator;
import java.util.Random;

import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.ServiceReturn;
import edu.psu.iam.cpr.service.UseridReturn;
import edu.psu.iam.cpr.service.UseridServiceReturn;

public class UseridTest {

	static final Cprws cprws = new Cprws();
	static final CprwsSEI port = cprws.getCprwsPort();
	static String newUserid="";
	
	@Test(expectedExceptions=Exception.class)
	public void _01testGetUserid1() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, null, null, null, "N");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _02testGetUserid2() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, null, null, null, "N");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _03testGetUserid3() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "", null, null, "N");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _04testGetUserid4() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "xxx", "person_id", "100000", "N");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _05testGetUserid5() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id1", "100000", "N");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _06testGetUserid6() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1", "N");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _07testGetUserid7() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "N");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _08testGetUseridHistory() throws Exception {
		UseridServiceReturn useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100000", "Y");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}	
	}
	
	@Test
	public void _09testAddUserid() throws Exception {
		UseridServiceReturn useridReturn = port.addUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 100002);
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
		else if (useridReturn.getNumberElements() != 1) {
			throw new Exception("Invalid number of userids were returned.");
		}
		else {
			newUserid = useridReturn.getUseridReturnRecord().get(0).getUserid();
		}
	}
	
	@Test
	public void _10testSetPrimaryUserid() throws Exception {
		 ServiceReturn useridReturn = port.setPrimaryUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", newUserid);
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	// Cannot archive the primary.
	@Test(expectedExceptions=Exception.class)
	public void _11testArchiveUserid1() throws Exception {
		 ServiceReturn useridReturn = port.archiveUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", newUserid);
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	// Successful archive of a non-primary userid.
	@Test
	public void _12testArchiveUserid2() throws Exception {
		UseridServiceReturn useridServiceReturn = null;
		useridServiceReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", "N");
		for (final Iterator<UseridReturn> it = useridServiceReturn.getUseridReturnRecord().iterator(); it.hasNext();) {
			UseridReturn ur = it.next();
			if (ur.getPrimary().equals("N")) {
				newUserid = ur.getUserid();
				break;
			}
		}
		ServiceReturn serviceReturn = port.archiveUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", newUserid);
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	// Unsuccessful archive of an already exist archived userid.
	@Test(expectedExceptions=Exception.class)
	public void _13testArchiveUserid3() throws Exception {
		 ServiceReturn useridReturn = port.archiveUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", newUserid);
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	// Unsuccessful of the archive of a userid, because its already unarchived.
	@Test(expectedExceptions=Exception.class) 
	public void _14testUnarchiveUserid1() throws Exception {
		UseridServiceReturn useridReturn = null;
		useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", "N");
		newUserid = null;
		for (final Iterator<UseridReturn> it = useridReturn.getUseridReturnRecord().iterator(); it.hasNext(); ) {
			UseridReturn ur = it.next();
			if (ur.getEndDate() == null  && (! ur.getUserid().equals("tuj20")) && (! ur.getUserid().equals("tuj27"))) {
				newUserid = ur.getUserid();
				break;
			}
		}
		ServiceReturn serviceReturn = port.unarchiveUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", newUserid);
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	// Unsuccessful, userid not found.
	@Test(expectedExceptions=Exception.class)
	public void _15testUnarchiveUserid2() throws Exception {
		 ServiceReturn useridReturn = port.unarchiveUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", "xyz123");
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}
	
	
	// Successful of the archive of a userid, because its already unarchived.
	@Test
	public void _16testUnarchiveUserid3() throws Exception {
		UseridServiceReturn useridReturn = null;
		useridReturn = port.getUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", "Y");
		newUserid = null;
		for (final Iterator<UseridReturn> it = useridReturn.getUseridReturnRecord().iterator(); it.hasNext(); ) {
			UseridReturn ur = it.next();
			if (ur.getEndDate() != null && (! ur.getUserid().equals("tuj20")) && (! ur.getUserid().equals("tuj27"))) {
				newUserid = ur.getUserid();
				break;
			}
		}
		
		ServiceReturn serviceReturn = port.unarchiveUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100002", newUserid);
		if (serviceReturn.getStatusCode() != 0) {
			throw new Exception(serviceReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _17testAddSpecialUserid() throws Exception {
		
		Random r = new Random();
		newUserid = "iamdemo" + Math.abs(r.nextInt());
		ServiceReturn useridReturn = port.addSpecialUserid(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, 100002, newUserid);
		if (useridReturn.getStatusCode() != 0) {
			throw new Exception(useridReturn.getStatusMessage());
		}
	}

}
