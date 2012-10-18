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

import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.IdCardServiceReturn;
import edu.psu.iam.cpr.service.PersonIdCardNumberServiceReturn;
import edu.psu.iam.cpr.service.PersonIdCardReturn;
import edu.psu.iam.cpr.service.ServiceReturn;

public class IdCardTest {

	
	static final Cprws cprws = new Cprws();
	static final CprwsSEI port = cprws.getCprwsPort();

	public void cleanUpAll(String person_id) throws Exception {
		IdCardServiceReturn getIdCardReturn = null;
		ServiceReturn archiveIdCardReturn = null;
		getIdCardReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", person_id, null, "N");
		if (getIdCardReturn.getStatusCode() != 0) {
			throw new Exception(getIdCardReturn.getStatusMessage());
		} 
		else 
		{
			if (getIdCardReturn.getNumberPersonIdCardElements() > 0) {
				for (final Iterator<PersonIdCardReturn> it = getIdCardReturn.getPersonIdCardsReturnRecord().iterator(); it.hasNext();) {
					PersonIdCardReturn IdCard = it.next();
					archiveIdCardReturn = port.archiveIdCard(ServiceAuthentication.GOOD_USERID,
							ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", person_id,
							IdCard.getIdCardType());
					if (archiveIdCardReturn.getStatusCode() != 0) {
						throw new Exception(
								archiveIdCardReturn.getStatusMessage());
					}
				}
			}
		}
	}	

@Test(expectedExceptions = Exception.class)
	public void _01testIdCardBadPrincipal() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(null,
				ServiceAuthentication.GOOD_PASSWORD, "", "person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"1234567890123456", null, null, null);
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}

@Test(expectedExceptions = Exception.class)
	public void _02testIdCardBadPassword() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				"notme", "llg5", "person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"1234567890123456", null, null, null);
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _03testIdCardBadPersonIdentifier() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5", " ", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"1234567890123456", null, null, null);
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _04testIdCardBadUserid() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, " ", "person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"1234567890123456", null, null, null);
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
}
@Test(expectedExceptions = Exception.class)
	public void _05testIdCardBadPerson() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5 ", "person_id", "", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"1234567890123456", null, null, null);
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _06testIdCardBadCardType() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5 ", "person_id", "100003", "id_card_",
				"1234567890123456", null, null, null);
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _07testIdCardBadIdNumber() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5 ", "person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"12345", null, null, null);
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _08testIdCardBadPhotoAndDateCombo() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5 ", "person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"1234567890123456", null, null, "1/1/2012");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _09testIdCardBadDate() throws Exception {
		ServiceReturn IdCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5 ", "person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT",
				"1234567890123456", null,new byte[1], "1/12");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	}
@Test
	public void _10testGetIdCard() throws Exception {
		IdCardServiceReturn IdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100003", null, "N");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
		

	}
@Test
	public void _11testGetIdCardHistory() throws Exception {
		IdCardServiceReturn IdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100003", null, "Y");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}

	}
@Test
	public void _12testGetIdCardByType() throws Exception {
		IdCardServiceReturn IdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100003", null, "N");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
		
	
	}
@Test
	public void _13testGetIdCardByTypeHistory() throws Exception {
		IdCardServiceReturn IdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100003", "ID_CARD_ID_PLUS_CARD_STUDENT", "Y");
		if (IdCardServiceReturn.getStatusCode() != 0) {
			throw new Exception(IdCardServiceReturn.getStatusMessage());
		}
	
	}
@Test
	public void _14testGetIdCardNumber() throws Exception {
	PersonIdCardNumberServiceReturn IdCardNumberServiceReturn = port.getIdCardNumber(ServiceAuthentication.GOOD_USERID,
		ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100003");
	if (IdCardNumberServiceReturn.getStatusCode() != 0) {
		throw new Exception(IdCardNumberServiceReturn.getStatusMessage());
	}

}	
@Test(expectedExceptions = Exception.class)
	public void _15testGetIdCardNumberBadRA() throws Exception {
	PersonIdCardNumberServiceReturn IdCardNumberServiceReturn = port.getIdCardNumber("portal3",
		ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100003");
	if (IdCardNumberServiceReturn.getStatusCode() != 0) {
		throw new Exception(IdCardNumberServiceReturn.getStatusMessage());
	}

}	
@Test
	public void _16testAddIdCardNoPhoto() throws Exception {
		try {
			cleanUpAll("100003");
		}
		catch (Exception e) {
			throw new Exception("cleanup failed");
		}
		ServiceReturn  idCardServiceReturn = null;
		idCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", "ID_CARD_ID_PLUS_CARD_STUDENT", "6543210987654321", null, null, null);
		if (idCardServiceReturn.getStatusCode()!=0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
		else 
		{
			IdCardServiceReturn  getIdCardServiceReturn = null;
			getIdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003",  null, "N");
			if (getIdCardServiceReturn.getStatusCode() !=0) {
				throw new Exception(getIdCardServiceReturn.getStatusMessage());
			}
			else 
			{
				if (getIdCardServiceReturn.getNumberPersonIdCardElements() != 1) {
					throw new Exception("number of id card elements is greater than one");
				}
				
			}
		}
	}
//@Test(expected = Exception.class)
//	public void _17testAddIdDuplicateCardNoPhoto() throws Exception {
//		try {
//			cleanUpAll("100003");
//		}
//		catch (Exception e) {
//			throw new Exception("cleanup failed");
//		}
//		IdCardServiceReturn  idCardServiceReturn = null;
//		idCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT", "6543210987654321", null, null, null);
//		if (idCardServiceReturn.getStatusCode()!=0) {
//			throw new Exception(idCardServiceReturn.getStatusMessage());
//		}
//		else 
//		{
//			IdCardServiceReturn  getIdCardServiceReturn = null;
//			getIdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003");
//			if (getIdCardServiceReturn.getStatusCode() !=0) {
//				throw new Exception(getIdCardServiceReturn.getStatusMessage());
//			}
//			else 
//			{
//				if (getIdCardServiceReturn.getNumberPersonIdCardElements() != 1) {
//					throw new Exception("number of id card elements is greater than one");
//				}
//				else {
//					idCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", "ID_CARD_ID_CARD_PLUS_STUDENT", "6543210987654321", null, null, null);
//					if (idCardServiceReturn.getStatusCode()!=0) {
//						throw new Exception(idCardServiceReturn.getStatusMessage());
//					}
//				}
//			}
//		}
//	}
@Test 
	public void _18testAddIdCardPhoto() throws Exception {
		try {
			cleanUpAll("100003");
		}
		catch (Exception e) {
			throw new Exception("cleanup failed");
		}
		ServiceReturn  idCardServiceReturn = null;
		idCardServiceReturn = port.addIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", "ID_CARD_ID_PLUS_CARD_FACULTY_STAFF", "6643210987654321", "12", new byte[1], "1/1/2012");
		if (idCardServiceReturn.getStatusCode()!=0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
		else 
		{
			IdCardServiceReturn  getIdCardServiceReturn = null;
			getIdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", null, "N");
			if (getIdCardServiceReturn.getStatusCode() !=0) {
				throw new Exception(getIdCardServiceReturn.getStatusMessage());
			}
			else 
			{
				if (getIdCardServiceReturn.getNumberPersonIdCardElements() != 1) {
					throw new Exception("number of id card elements is greater than one");
				}
				
			}
		}
	}
@Test
	public void _19testUpdateIdCardNoPhoto() throws Exception {
		try {
			cleanUpAll("100003");
		}
		catch (Exception e) {
			throw new Exception("cleanup failed");
		}
		ServiceReturn  idCardServiceReturn = null;
		idCardServiceReturn = port.updateIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", "ID_CARD_ID_PLUS_CARD_STUDENT", "6543210987654321", null, null, null);
		if (idCardServiceReturn.getStatusCode()!=0) {
			throw new Exception(idCardServiceReturn.getStatusMessage());
		}
		else 
		{
			IdCardServiceReturn  getIdCardServiceReturn = null;
			getIdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", null, "N");
			if (getIdCardServiceReturn.getStatusCode() !=0) {
				throw new Exception(getIdCardServiceReturn.getStatusMessage());
			}
			else 
			{
				if (getIdCardServiceReturn.getNumberPersonIdCardElements() != 1) {
					throw new Exception("number of id card elements is greater than one");
				}
				
			}
		}
	}
@Test(expectedExceptions = Exception.class)
public void _20testUpdateIdCardNoPhotoBad() throws Exception {
	try {
		cleanUpAll("100003");
	}
	catch (Exception e) {
		throw new Exception("cleanup failed");
	}
	ServiceReturn  idCardServiceReturn = null;
	idCardServiceReturn = port.updateIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", "ID_CARD_STUDENT", "6543210987654321", null, null, null);
	if (idCardServiceReturn.getStatusCode()!=0) {
		throw new Exception(idCardServiceReturn.getStatusMessage());
	}
	else 
	{
		IdCardServiceReturn  getIdCardServiceReturn = null;
		getIdCardServiceReturn = port.getIdCard(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100003", null, "N");
		if (getIdCardServiceReturn.getStatusCode() !=0) {
			throw new Exception(getIdCardServiceReturn.getStatusMessage());
		}
		else 
		{
			if (getIdCardServiceReturn.getNumberPersonIdCardElements() != 1) {
				throw new Exception("number of id card elements is greater than one");
			}
			
		}
	}
}
}
