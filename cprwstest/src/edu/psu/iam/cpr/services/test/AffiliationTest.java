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

import edu.psu.iam.cpr.service.AffiliationReturn;
import edu.psu.iam.cpr.service.AffiliationServiceReturn;
import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.ServiceReturn;



public class AffiliationTest {
	
	
	static final Cprws cprws= new Cprws();
	static final CprwsSEI port = cprws.getCprwsPort();
	
	public void cleanUpAll(String person_id) throws Exception {
		AffiliationServiceReturn getAffReturn = null;
		ServiceReturn archiveAffReturn = null;
		getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", person_id,  "N");
		if (getAffReturn.getStatusCode() != 0) {
			throw new Exception(getAffReturn.getStatusMessage());
		} 
		else 
		{
			if (getAffReturn.getNumberElements() > 0) {
				for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
					AffiliationReturn aff = it.next();
					archiveAffReturn = port.archiveAffiliation(ServiceAuthentication.GOOD_USERID,
							ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", person_id,aff.getAffiliationType());
					if (archiveAffReturn.getStatusCode() != 0) {
						throw new Exception(archiveAffReturn.getStatusMessage());
					}
				}
			}
		}
	}

	
@Test(expectedExceptions = Exception.class)
	public void _01testGetsAffiliationBadPrincipal() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.BAD_PASSWORD, "llg5", "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _02testGetsAffiliationNullPrincipal() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(null,
				ServiceAuthentication.BAD_PASSWORD, "llg5", "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _03testGetsAffiliationBlankPrincipal() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations("",
				ServiceAuthentication.BAD_PASSWORD, "llg5", "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
	}
}
@Test(expectedExceptions = Exception.class)
	public void _04testGetsAffiliationNullPassword() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				null, "llg5", "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _05testGetsAffiliationBadPassword() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.BAD_PASSWORD, "llg5", "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _06testGetsAffiliationBlankPassword() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
			" ", "llg5", "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}	
@Test(expectedExceptions = Exception.class)
	public void _07testGetsAffiliationBlankUpdatedBy() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, " ", "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _08testGetsAffiliationNullUpdatedBy() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _09testGetsAffiliationTooLongUpdatedBy() throws Exception {
	AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
		ServiceAuthentication.GOOD_PASSWORD, "llg5123456789012345678901234567890","person_id", "100003");
	if (affiliationServiceReturn.getStatusCode() != 0) {
		throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _10testGetsAffiliationBadPersonType() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5","person", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
	}
@Test(expectedExceptions = Exception.class)
	public void _11testGetsAffiliationBlankPersonType() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5"," ", "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _12testGetsAffiliationNullPersonType() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5",null, "100003");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _13testGetsAffiliationNullPersonId() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", null);
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _14testGetsAffiliationBlankPersonId() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
		ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _15testGetsAffiliationInvalidPersonId() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "a3");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}

@Test(expectedExceptions = Exception.class)
	public void _16testGetsAffiliationNoPersonId() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "1");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _17testUpdatesAffiliationBadPrincipal() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation("port",
		"notme", "llg5", "person_id", "100003", "STUDENT_UNDERGRADUATE_FORMER");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _18testUpdatesAffiliationNullPrincipal() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(null,
				"notme", "llg5", "person_id", "100003", "STUDENT_UNDERGRADUATE_FORMER");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _19testUpdatesAffiliationBlankPrincipal() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation("",
				"notme", "llg5", "person_id", "100003","STUDENT_UNDERGRADUATE_FORMER" );
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _20testUpdatesAffiliationNullPassword() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
			null, "llg5", "person_id", "100003","STUDENT_UNDERGRADUATE_FORMER");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _21testUpdatesAffiliationBadPassword() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
				"notme", "llg5", "person_id", "100003", "EMPLOYEE_STAFF");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _22testUpdatesAffiliationBlankPassword() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
		" ", "llg5", "person_id", "100003", "EMPLOYEE_FACULTY");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}	
@Test(expectedExceptions = Exception.class)
	public void _23testUpdatesAffiliationBlankUpdatedBy() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, " ", "person_id", "100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _24testUpdatesAffiliationNullUpdatedBy() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100003", "STUDENT_MEDICAL_PROSPECT");
			if (affiliationServiceReturn.getStatusCode() != 0) {
				throw new Exception(affiliationServiceReturn.getStatusMessage());
			}	
		}
@Test(expectedExceptions = Exception.class)
	public void _25testUpdatesAffiliationTooLongUpdatedBy() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5123456789012345678901234567890","person_id", "100003", "STUDENT_LAW_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _26testUpdatesAffiliationBadPersonType() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5","person", "100003", "STUDENT_LAW_FORMER");
	if (affiliationServiceReturn.getStatusCode() != 0) {
		throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
}
@Test(expectedExceptions = Exception.class)
	public void _27testUpdatesAffiliationBlankPersonType() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5"," ", "100003", "STUDENT_UNDERGRADUATE_APPLICANT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _28testUpdatesAffiliationNullPersonType() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5",null, "100003", "STUDENT_LAW_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _29testUpdatesAffiliationNullPersonId() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", null, "STUDENT_MEDICAL_FORMER");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _30testUpdateAffiliationBlankPersonId() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _31testUpdateAffiliationInvalidPersonId() throws Exception {
		AffiliationServiceReturn affiliationServiceReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "a3");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}

@Test(expectedExceptions = Exception.class)
	public void _32testUpdateAffiliationNoPersonId() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "1", "STUDENT_UNDERGRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _33testUpdateAffiliationBadAffiliation() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100004", "STUDENT");
	if (affiliationServiceReturn.getStatusCode() != 0) {
		throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _34testUpdateAffiliationNullAffiliation() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100004", null);
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _35testUpdateAffiliationBlankAffiliation() throws Exception {
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5","person_id", "100004", " ");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception(affiliationServiceReturn.getStatusMessage());
		}
	}

@Test
	public void _36testAddAffiliation() throws Exception {
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} else 
			{
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_PROSPECT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
	}
@Test
	public void _37testAddTwoAffiliations() throws Exception {
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} else 
			{
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
				}
			else 
				
			{	
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT")) &&
								(!aff.getAffiliationType().equals("STUDENT_GRADUATE_PROSPECT") ) ){
							throw new Exception("the returned affiliations didn't match the types added");
						}
					}
				}
				else {
						throw new Exception("Too many affiliations found for user");
					}
				}
			}
			
	}
@Test(expectedExceptions=Exception.class)
	public void _38testUpdateAffiliationFailure () throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
		throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		// update with bad affiliation
		ServiceReturn updateServiceReturn = port.updateAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_PROSPECT");
		if (updateServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation update failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
	}
@Test
	public void _39testUpdateAffiliationSucceed () throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		// update with bad affiliation
		ServiceReturn updateServiceReturn = port.updateAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_FORMER");
		if (updateServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation update failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_FORMER"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
	}

@Test
	public void _40testSetPrimaryAffiliation() throws Exception {
	// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		// setPrimary with bad affiliation
		ServiceReturn setPrimaryServiceReturn = port.setPrimaryAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (setPrimaryServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation setPrimary failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getPrimary().equals("Y"))) {
							throw new Exception("the primary flag was not set for affiliation");
						}
					}
				}	
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
	}
@Test
	public void _41testSetPrimaryAffiliationChange() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
		{
			// verify the type is correct
			if (getAffReturn.getNumberElements() == 1) {
				for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
					AffiliationReturn aff = it.next();
					if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
						throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
				}
			else 
				
			{	
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT")) &&
								(!aff.getAffiliationType().equals("STUDENT_GRADUATE_PROSPECT") ) ){
							throw new Exception("the returned affiliations didn't match the types added");
						}
					}
				}
				else {
						throw new Exception("Too many affiliations found for user");
					}
				}
			}
		// setPrimary with for undergraduate affiliation
		ServiceReturn setPrimaryServiceReturn = port.setPrimaryAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (setPrimaryServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation setPrimary failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if (aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT")) {
							if ((!aff.getPrimary().equals("Y"))) {
								throw new Exception("the primary flag was not set for affiliation");
							}
						}
						else
						{	
							if ((aff.getPrimary().equals("Y"))) {
								throw new Exception("the primary flag was set for wrong affiliation");
							}
						}
					}
				}	
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		// setPrimary with for graduate affiliation
		setPrimaryServiceReturn = port.setPrimaryAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_GRADUATE_PROSPECT");
		if (setPrimaryServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation setPrimary failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if (aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT")) {
							if (aff.getPrimary().equals("Y")) {
								throw new Exception("the primary flag was not unset for affiliation");
							}
						}
						else
						{	
							if ((!aff.getPrimary().equals("Y"))) {
								throw new Exception("the primary flag was not set for graduate affiliation");
							}
						}
					}
				}	
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		
	}
@Test
	public void _42testPrimaryAffiliationChangeAfterArchive() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "EMPLOYEE_STAFF_ACTIVE");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
		{
			// verify the type is correct
			if (getAffReturn.getNumberElements() == 1) {
				for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
					AffiliationReturn aff = it.next();
					if ((!aff.getAffiliationType().equals("EMPLOYEE_STAFF_ACTIVE"))) {
						throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
				}
			else 
				
			{	
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("EMPLOYEE_STAFF_ACTIVE")) &&
								(!aff.getAffiliationType().equals("STUDENT_GRADUATE_PROSPECT") ) ){
							throw new Exception("the returned affiliations didn't match the types added");
						}
					}
				}
				else {
						throw new Exception("Too many affiliations found for user");
					}
				}
			}
	// setPrimary with for employee affiliation
		ServiceReturn setPrimaryServiceReturn = port.setPrimaryAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "EMPLOYEE_STAFF_ACTIVE");
		if (setPrimaryServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation setPrimary failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if (aff.getAffiliationType().equals("EMPLOYEE_STAFF_ACTIVE")) {
							if ((!aff.getPrimary().equals("Y"))) {
								throw new Exception("the primary flag was not set for affiliation");
							}
						}
						else
						{	
							if ((aff.getPrimary().equals("Y"))) {
								throw new Exception("the primary flag was set for wrong affiliation");
							}
						}
					}
				}	
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		// archive with for employee affiliation
		ServiceReturn archiveServiceReturn = port.archiveAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "EMPLOYEE_STAFF_ACTIVE");
		if (archiveServiceReturn.getStatusCode() != 0) {
			throw new Exception("The archive affiliation failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if (aff.getAffiliationType().equals("STUDENT_GRADUATE_PROSPECT" )) {
							if (aff.getPrimary().equals("Y")) {
								throw new Exception("the primary flag was set for wrong affiliation for affiliation");
							}
						}
						else
						{	
							throw new Exception(" problems with archive");
						}
					}
				}	
				else {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		
	}
@Test(expectedExceptions=Exception.class)
	public void _43testSetPrimaryAffiliationFailed() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}	
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}	
			}
		}
		// setPrimary with bad affiliation
		ServiceReturn setPrimaryServiceReturn = port.setPrimaryAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_UNDERGRADUATE_FORMER");
		if (setPrimaryServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation update failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getPrimary().equals("Y"))) {
							throw new Exception("the primary flag was not set for affiliation");
						}
					}
				}	
				else {
				throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
				}
			else 
				
			{	
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT")) &&
								(!aff.getAffiliationType().equals("STUDENT_GRADUATE_PROSPECT") ) ){
							throw new Exception("the returned affiliations didn't match the types added");
						}
					}
				}
				else {
						throw new Exception("Too many affiliations found for user");
					}
				}
			}
		
	}
@Test
	public void _44testArchiveAffiliation() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}	
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}	
			}
		}
		// archive with the affiliation
		ServiceReturn archiveServiceReturn = port.archiveAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (archiveServiceReturn.getStatusCode() != 0) {
				throw new Exception("The affiliation update failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				if (getAffReturn.getNumberElements() != 0) {
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
	}
@Test
	public void _45testGetInternalAffiliations() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			}
			else 
			
			{	
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT")) &&
							(!aff.getAffiliationType().equals("STUDENT_GRADUATE_PROSPECT") ) ){
							throw new Exception("the returned affiliations didn't match the types added");
						}
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
					}
				}	
			}
	}
@Test
	public void _46testGetInternalHistoryAffiliations() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						}
					}
				}
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			int endDateSet =0;
			int noEndDateSet =0;
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "Y");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			}
			else 
		
			{	
				if (getAffReturn.getNumberElements() > 0) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if (aff.getEndDate() == null) {
							noEndDateSet++;
						}
						else 
						{
							endDateSet++;
						}
					}
					if (endDateSet ==0 || noEndDateSet == 0) {
						if (endDateSet ==0) {
							throw new Exception("getInternalAffilliationsHistory returned no active records");
						}
						else 
						{
							throw new Exception("getInternalAffiliationsHistory returned no archive record");
						}
						
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
					}
				}	
		}
	}
@Test
	public void _47testGetExternalAffiliations() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
						
						}
					}
				}
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getExternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
				"person_id", "100003");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			}
			else 
		
			{	
				if (getAffReturn.getNumberElements() == 2) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliation().equals("student")) &&
								(!aff.getAffiliation().equals("member") ) ){
								throw new Exception("the returned affiliations didn't match the types added");
							}	
					}
				}
				else {
					throw new Exception("Too many affiliations found for user");
					}
				}	
			}
	}
@Test
	public void _48testGetAllAffiliations() throws Exception {
		// add an affiliation
		cleanUpAll("100003");
		ServiceReturn affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
				"100003", "STUDENT_UNDERGRADUATE_CURRENT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
			}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getInternalAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003", "N");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			} 
			else 
			{
				// verify the type is correct
				if (getAffReturn.getNumberElements() == 1) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						if ((!aff.getAffiliationType().equals("STUDENT_UNDERGRADUATE_CURRENT"))) {
							throw new Exception("the returned affiliation didn't match the type added");
					
						}
					}
				}	
				else 
				{
					throw new Exception("Too many affiliations found for user");
				}
			}
		}
		affiliationServiceReturn = port.addAffiliation(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id",
			"100003", "STUDENT_GRADUATE_PROSPECT");
		if (affiliationServiceReturn.getStatusCode() != 0) {
			throw new Exception("The affiliation add failed");
		}
		else
		{
			AffiliationServiceReturn getAffReturn = port.getAffiliations(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, "llg5",
					"person_id", "100003");
			if (getAffReturn.getStatusCode() != 0) {
				throw new Exception(getAffReturn.getStatusMessage());
			}
			else 
	
			{	
				if (getAffReturn.getNumberElements() > 0) {
					for (final Iterator<AffiliationReturn> it = getAffReturn.getAffiliationReturnRecord().iterator(); it.hasNext();) {
						AffiliationReturn aff = it.next();
						
						if ( aff.getEndDate() != null) {
								throw new Exception("An archive record was returned on the get");
							}
					}
				}
				else {
					throw new Exception("Problems with affiliations");
					}
				}	
			
			}
	}
}
