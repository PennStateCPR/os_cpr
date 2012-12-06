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

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;

import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.PersonServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;

public class PersonTest {
	

	static final Cprws cprws = new Cprws();
	static final CprwsSEI port = cprws.getCprwsPort();

	static final String[] firstNames = { "Jeff", "Terry", "Jack", "John", "Victor", "Albert", "Samantha", "Amanda", "Madison", "Morgan" };
	static final String[] lastNames = { "Jackson", "Railroad", "Southern", "Carey", "Norfolk", "Chessie", "Santa Fe", "Police", "Tech", "Crunch" };
	static final String[] states = { "AK", "AL", "NY", "PA", "VT", "TX", "CA", "FL", "MN", "MA" };
	static final String[] streets = { "1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th", "10th" };
	static final String[] cities = { "Jackson", "Railroad", "Southern", "Carey", "Norfolk", "Chessie", "Santa Fe", "Police", "Tech", "Crunch" };
	static final String[] postalCodes = { "11111", "22222", "33333", "44444","55555", "66666", "77777", "88888", "99999", "32222" };
	
	static String userid;

	public int nextIndex(int min, int max) {
		Random rand = new Random(new Date().getTime());	
		return rand.nextInt(max-min+1) + min;
	}
	
	// Invalid password.
	@Test(expectedExceptions=Exception.class)
	public void _01testGetPerson1() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.BAD_PASSWORD, null, null, null, "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid userid.
	@Test(expectedExceptions=Exception.class)
	public void _02testGetPerson2() throws Exception {
		PersonServiceReturn personReturn = port.getPerson("portal1a", ServiceAuthentication.GOOD_PASSWORD, null, null, null, "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid requestor.
	@Test(expectedExceptions=Exception.class)
	public void _03testGetPerson3() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100000", "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid requestor.
	@Test(expectedExceptions=Exception.class)
	public void _04testGetPerson4() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "xyz", "person_id", "100000", "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier type.
	@Test(expectedExceptions=Exception.class)
	public void _05testGetPerson5() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", null, "100000", "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier type.
	@Test(expectedExceptions=Exception.class)
	public void _06testGetPerson6() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_", "100000", "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier.
	@Test(expectedExceptions=Exception.class)
	public void _07testGetPerson7() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", null, "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid identifier.
	@Test(expectedExceptions=Exception.class)
	public void _08testGetPerson8() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "1", "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Inactive person identifier.
	@Test(expectedExceptions=Exception.class)
	public void _09testGetPerson9() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100001", "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Successful.
	@Test
	public void _10testGetPerson10() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "N");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Successful.
	@Test
	public void _11testGetPersonHistory1() throws Exception {
		PersonServiceReturn personReturn = port.getPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "person_id", "100000", "Y");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	// Invalid parameters.
	@Test(expectedExceptions=Exception.class)
	public void _12testAddPerson1() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _13testAddPerson2() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _14testAddPerson3() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "X", "X", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _15testAddPerson4() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "z", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _16testAddPerson5() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _17testAddPerson6() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "111", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _18testAddPerson7() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _19testAddPerson8() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/34/2011", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _20testAddPerson9() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/34", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _21testAddPerson10() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _22testAddPerson11() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "name", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _23testAddPerson12() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _24testAddPerson13() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _25testAddPerson14() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "ADDRESS", null, "STREET", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _26testAddPerson15() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _27testAddPerson16() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, null, null, null, "US", null, "PHONE", null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _28testAddPerson17() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, null, null, null, "USA", null, "PHONE", null, null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _29testAddPerson18() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "PHONE", "814", null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _30testAddPerson19() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "PHONE", "8142789153", null, null, null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _31testAddPerson20() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "Z", null, null, null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _32testAddPerson21() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "N", "email", "yya", null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _33testAddPerson22() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "N", "OTHER_EMAIL", "yya", null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _34testAddPerson23() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "N", "OTHER_EMAIL", "yya@", null, null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _35testAddPerson24() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "N", "OTHER_EMAIL", "yya@blah.com", "", null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _36testAddPerson25() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "N", "OTHER_EMAIL", "yya@blah.com", "zzz", null);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _37testAddPerson26() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "N", "OTHER_EMAIL", "yya@blah.com", "STUDENT_GRADUATE_APPLICANT", "");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions=Exception.class)
	public void _38testAddPerson27() throws Exception {
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, null, null, "LAST_NAME", null, "LOCAL_ADDRESS", null, "STREET", null, null, "State College", null, null, "USA", null, "LOCAL_PHONE", "8142789153", null, "N", "OTHER_EMAIL", "yya@blah.com", "STUDENT_GRADUATE_APPLICANT", "abcd12345");
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _39testAddPerson28() throws Exception {
		final int firstNameIndex = nextIndex(0, firstNames.length-1);
		final int lastNameIndex = nextIndex(0,lastNames.length-1);
		final int statesIndex = nextIndex(0, states.length-1);
		final int streetsIndex = nextIndex(0, streets.length-1);
		final int citiesIndex = nextIndex(0,cities.length-1);
		final int postalCodesIndex = nextIndex(0,postalCodes.length-1);
		String s = String.valueOf(new Date().getTime());

		Random rp = new Random(new Date().getTime());
		int pA = Math.abs(rp.nextInt())%1000;  //0-999
		int pB = Math.abs(rp.nextInt())%100;   //0-99
		int pC = Math.abs(rp.nextInt())%10000; //0-9999
		DecimalFormat fmt2 = new DecimalFormat("00");
		DecimalFormat fmt3 = new DecimalFormat("000");
		DecimalFormat fmt4 = new DecimalFormat("0000");
		String ssn = fmt3.format(pA) + fmt2.format(pB) + fmt4.format(pC);
		
		PersonServiceReturn personReturn = port.addPerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, 
				"jvuccolo", "Y", "Y", "GENDER_MALE", "1/31", "legal_name", null, firstNames[firstNameIndex]+s, null, 
				lastNames[lastNameIndex]+s, null, "LOCAL_ADDRESS", null, streets[streetsIndex], null, null, 
				cities[citiesIndex]+s, states[statesIndex], postalCodes[postalCodesIndex], "USA", null, "LOCAL_PHONE", 
				"8142789153", null, "N", "OTHER_EMAIL", "yya@blah.com", "STUDENT_GRADUATE_APPLICANT", ssn);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
		else {
			userid = personReturn.getUseridReturnRecord().get(0).getUserid();
		}
	}
	
	@Test
	public void _40testArchivePerson1() throws Exception {
		ServiceReturn personReturn = port.archivePerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "userid", userid);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _41testArchivePerson2() throws Exception {
		ServiceReturn personReturn = port.archivePerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "userid", userid);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _42testUnarchivePerson1() throws Exception {
		ServiceReturn personReturn = port.unarchivePerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "userid", userid);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _43testUnarchivePerson2() throws Exception {
		ServiceReturn personReturn = port.unarchivePerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", "userid", userid);
		if (personReturn.getStatusCode() != 0) {
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	@Test(expectedExceptions=Exception.class)
	public void _44testUpdatePerson() throws Exception {
		String assignPsuId = null;
		String assignUserid = null;
		String gender = null;
		String dob = "11/99/2001";
		String nameType = null;
		String nameDocumentType = null;
		String firstName = null;
		String middleNames = null;
		String lastName = null;
		String suffix = null;
		String addressType = null;
		String addressDocumentType = null;
		Long addressGroupId =null;
		String address1 =null;
		String address2 = null;
		String address3 = null;
		String city = null;
		String stateOrProvince = null;
		String postalCode = null;
		String countryCode = null;
		String campusCode = null;
		String phoneType = null;
		Long phoneGroupId = null;
		String phoneNumber = null;
		String extension = null;
		String internationalNumber = null;
		String emailType = null;
		String emailAddress = null;
		String affiliation = null;
		String ssn = null;
		PersonServiceReturn personReturn = port.updatePerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", 
				"person_id", "100000", assignPsuId, assignUserid, gender, dob, nameType, nameDocumentType, firstName, middleNames, lastName, 
				suffix, 
				addressType, addressDocumentType, addressGroupId, address1, address2, address3, city, stateOrProvince, postalCode, 
				countryCode, campusCode, phoneType, phoneGroupId, phoneNumber, extension, internationalNumber, 
				emailType, emailAddress, affiliation, ssn);
		if (personReturn.getStatusCode() != 0) {			
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _45testUpdatePerson() throws Exception {
		String assignPsuId = null;
		String assignUserid = null;
		String gender = null;
		String dob = "1/1/2001";
		String nameType = null;
		String nameDocumentType = null;
		String firstName = null;
		String middleNames = null;
		String lastName = null;
		String suffix = null;
		String addressType = null;
		String addressDocumentType = null;
		Long addressGroupId =null;
		String address1 =null;
		String address2 = null;
		String address3 = null;
		String city = null;
		String stateOrProvince = null;
		String postalCode = null;
		String countryCode = null;
		String campusCode = null;
		String phoneType = null;
		Long phoneGroupId = null;
		String phoneNumber = null;
		String extension = null;
		String internationalNumber = null;
		String emailType = null;
		String emailAddress = null;
		String affiliation = null;
		String ssn = null;
		PersonServiceReturn personReturn = port.updatePerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", 
				"person_id", "100000", assignPsuId, assignUserid, gender, dob, nameType, nameDocumentType, firstName, middleNames, lastName, 
				suffix, 
				addressType, addressDocumentType, addressGroupId, address1, address2, address3, city, stateOrProvince, postalCode, 
				countryCode, campusCode, phoneType, phoneGroupId, phoneNumber, extension, internationalNumber, 
				emailType, emailAddress, affiliation, ssn);
		if (personReturn.getStatusCode() != 0) {			
			throw new Exception(personReturn.getStatusMessage());
		}
	}
	
	@Test
	public void _46testUpdatePerson() throws Exception {
		String assignPsuId = null;
		String assignUserid = null;
		String gender = null;
		String dob = null;
		String nameType = null;
		String nameDocumentType = null;
		String firstName = null;
		String middleNames = null;
		String lastName = null;
		String suffix = null;
		String addressType = null;
		String addressDocumentType = null;
		Long addressGroupId =null;
		String address1 =null;
		String address2 = null;
		String address3 = null;
		String city = null;
		String stateOrProvince = null;
		String postalCode = null;
		String countryCode = null;
		String campusCode = null;
		String phoneType = null;
		Long phoneGroupId = null;
		String phoneNumber = null;
		String extension = null;
		String internationalNumber = null;
		String emailType = "OTHER_EMAIL";
		Random rp = new Random(new Date().getTime());
		int pA = Math.abs(rp.nextInt())%1000;  //0-999
		DecimalFormat fmt2 = new DecimalFormat("000");
		String emailAddress = "abcd" + fmt2.format(pA) + "@gmail.com";
		String affiliation = null;
		String ssn = null;
		PersonServiceReturn personReturn = port.updatePerson(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "jvuccolo", 
				"person_id", "100000", assignPsuId, assignUserid, gender, dob, nameType, nameDocumentType, firstName, middleNames, lastName, 
				suffix, 
				addressType, addressDocumentType, addressGroupId, address1, address2, address3, city, stateOrProvince, postalCode, 
				countryCode, campusCode, phoneType, phoneGroupId, phoneNumber, extension, internationalNumber, 
				emailType, emailAddress, affiliation, ssn);
		if (personReturn.getStatusCode() != 0) {			
			throw new Exception(personReturn.getStatusMessage());
		}
	}
}
