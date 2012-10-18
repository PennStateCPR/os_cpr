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
import edu.psu.iam.cpr.service.IapServiceReturn;


public class IAPTest {

	static final Cprws cprws = new Cprws();
	static final CprwsSEI port = cprws.getCprwsPort();
@Test(expectedExceptions = Exception.class)

	public void _01testIAPBadPassword() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.BAD_PASSWORD, "llg5", "person_id", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _02testIAPNullPassword() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			null, "llg5", "person_id", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
}
@Test(expectedExceptions = Exception.class)
	public void _03testIAPNullUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
		ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _04testIAPBadUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg", "person_id", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _05testIAPTooLongUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg123456789012345678901234567890", "person_id", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _06testIAPBlianUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, " ", "person_id", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _07testIAPNoPersonIdType() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", null, "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _08testIAPBadPersonIdType() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5", "person", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _09testIAPNullPersonIdType() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", " ", "1000000", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _10testIAPNullPersonId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id ", null, "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _11testIAPBadPersonId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id ", "aa", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _12testIAPNoPersonId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id ", "1", "tuj139", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
	
@Test(expectedExceptions = Exception.class)
	public void _13testIAPBlankUserId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id ", "1000000", " ", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _14testIAPNullUserId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id ", "1000000", null, "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _15testIAPWrongUserId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id ", "1000000", "tuj13", "N");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _16testExtIAPBadPassword() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			"notme", "llg5", "person_id", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _17testExtIAPNullPassword() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			null, "llg5", "person_id", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _18testExtIAPNullUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _19testExtIAPBadUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg", "person_id", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _20testExtIAPTooLongUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5123456789012345678901234567890", "person_id", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _21testExtIAPBlankUpdateBy() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "", "person_id", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}	
@Test(expectedExceptions = Exception.class)
	public void _22testExtIAPBlankPersonIdType() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test(expectedExceptions = Exception.class)
	public void _23testExtIAPBadPersonIdType() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person", "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _24testExtIAPNullPersonIdType() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", null, "1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _25testExtIAPNullPersonId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", null, "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _26testExtIAPBadPersonId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "a1000000", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _27testExtIAPNoPersonId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "1", "tuj139", "Incommon");
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	

@Test(expectedExceptions = Exception.class)	
	public void _28testExtIAPBlankUserId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "1000000", " ", "Incommon");		
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _29testExtIAPNullUserId() throws Exception {
	IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "1000000", null, "Incommon");	
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test(expectedExceptions = Exception.class)
	public void _30testExtIAPWrongUserId() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "1000000", "tuj13", "Incommon");	
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _31testIAPNullFederation() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "1000000", "tuj139", null);	
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _32testIAPBlankFederation() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "1000000", "tuj139", " ");	
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}
@Test(expectedExceptions = Exception.class)
	public void _33testIAPBadFederation() throws Exception {
		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
				"dunrIAM", "llg5", "person_id", "1000000", "tuj139", "Inc ");	
		if (iapServiceReturn.getStatusCode() != 0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
			}
		}	
@Test
	public void _34testIAPGet() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100000", "dummy","N");
		if (iapServiceReturn.getStatusCode() !=0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	
	}
@Test
	public void _35testIAPGetHistory() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100000", "dummy", "Y");
		if (iapServiceReturn.getStatusCode() !=0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
	}
@Test
	public void _36testIAPGetNoIaps() throws Exception {
		IapServiceReturn iapServiceReturn = port.getPSUIAP(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "100000", "dummy", "N");
		if (iapServiceReturn.getStatusCode() !=0) {
			throw new Exception(iapServiceReturn.getStatusMessage());
		}
		else
		{
			if (iapServiceReturn.getNumberElements() !=0) {
				throw new Exception ("there shuld not be any iaps of this person/userid combinations");
			}
		}
	}
//@Test
//	public void _37testExtIAPGetNone() throws Exception {
//		IapServiceReturn iapServiceReturn = port.getExternalIAP(ServiceAuthentication.GOOD_USERID,
//			ServiceAuthentication.GOOD_PASSWORD, "llg5", "person_id", "751116", "llg5", "Incommon");
//		if (iapServiceReturn.getStatusCode() !=0) {
//			throw new Exception(iapServiceReturn.getStatusMessage());
//		}
//		else
//		{
//			if (iapServiceReturn.getNumberElements() !=0) {
//				
//				for (final Iterator<IapReturn> it  = iapServiceReturn.getIapReturnRecord().iterator(); it.hasNext();) {
//					IapReturn iapReturn = it.next();
//					if ((!iapReturn.getIap().equals("InCommon Bronze"))) {
//						throw new Exception ("iap not bronze");
//						
//					}
//				}
//			}
//		}
//	}
}
