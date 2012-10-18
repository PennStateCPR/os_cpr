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
import edu.psu.iam.cpr.service.FindPersonServiceReturn;


public class FindPersonTest {

	static final Cprws findPerson = new Cprws();
	static final CprwsSEI port = findPerson.getCprwsPort();
	
	@Test(expectedExceptions=Exception.class)
	public void _01testFindPerson() throws Exception {
		FindPersonServiceReturn findPersonServiceReturn = port.searchForPerson(
				ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", null, 
				null, // userid 
				null, // ssn
				null, // first name 
				null, // last name
				null, // middle name. 
				null, // address1 
				null, // address2
				null, // address3 
				null, // city 
				null, // state 
				null, // postal code 
				null, // plus4
				null, // country
				null, // date of birth
				null, // gender
				null); // rank cut off
		if (findPersonServiceReturn.getStatusCode() != 0) {
			throw new Exception(findPersonServiceReturn.getStatusMessage());
		}	
	}
	
	@Test
	public void _02testFindPerson() throws Exception {
		FindPersonServiceReturn findPersonServiceReturn = port.searchForPerson(
				ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", null, 
				"testuser", // userid 
				null, // ssn
				"test", // first name 
				"user", // last name
				null, // middle name. 
				null, // address1 
				null, // address2
				null, // address3 
				null, // city 
				null, // state 
				null, // postal code 
				null, // plus4
				null, // country
				"1/1/1970", // date of birth
				null, // gender
				null); // rank cut off
		if (findPersonServiceReturn.getStatusCode() != 0) {
			throw new Exception(findPersonServiceReturn.getStatusMessage());
		}
		
	}
	
	@Test
	public void _03testFindPerson() throws Exception {
		FindPersonServiceReturn findPersonServiceReturn = port.searchForPerson(
				ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", null, 
				null, // userid 
				"888-88-8888", // ssn
				"test", // first name 
				"user", // last name
				null, // middle name. 
				null, // address1 
				null, // address2
				null, // address3 
				null, // city 
				null, // state 
				null, // postal code 
				null, // plus4
				null, // country
				"1/1/1970", // date of birth
				null, // gender
				null); // rank cut off
		if (findPersonServiceReturn.getStatusCode() != 0) {
			throw new Exception(findPersonServiceReturn.getStatusMessage());
		}
		
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _04testFindPerson() throws Exception {
		FindPersonServiceReturn findPersonServiceReturn = port.searchForPerson(
				ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", null, 
				null, // userid 
				"888-88-8887", // ssn
				"test", // first name 
				"user", // last name
				null, // middle name. 
				null, // address1 
				null, // address2
				null, // address3 
				null, // city 
				null, // state 
				null, // postal code 
				null, // plus4
				null, // country
				"1/1/1970", // date of birth
				null, // gender
				null); // rank cut off
		if (findPersonServiceReturn.getStatusCode() != 0) {
			throw new Exception(findPersonServiceReturn.getStatusMessage());
		}
		
	}
}
