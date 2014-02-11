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
import edu.psu.iam.cpr.service.PhoneReturn;
import edu.psu.iam.cpr.service.PhoneServiceReturn;
import edu.psu.iam.cpr.service.ServiceReturn;


public class PhoneTest {



	static final Cprws userid = new Cprws();
	static final CprwsSEI port = userid.getCprwsPort();
	
	public void cleanUpAll(String person_id) throws Exception {
		PhoneServiceReturn getPhoneReturn = null;
		ServiceReturn archivePhoneReturn = null;
		getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", person_id, null, "N");
		if (getPhoneReturn.getStatusCode() != 0) {
			throw new Exception(getPhoneReturn.getStatusMessage());
		} 
		else 
		{
			if (getPhoneReturn.getNumberElements() > 0) {
				for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext();) {
					PhoneReturn phone = it.next();
					archivePhoneReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
							ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", person_id,
							phone.getPhoneType(), phone.getGroupId());
					if (archivePhoneReturn.getStatusCode() != 0) {
						throw new Exception(
								archivePhoneReturn.getStatusMessage());
					}
				}
			}
		}
	}
	@Test(expectedExceptions = Exception.class)
		public void _01testPhoneBadPassword() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
					 ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _02testPhoneBadPrincipal() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone("portal",
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _03testPhoneNullPassword() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				null, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
	}	
	@Test(expectedExceptions = Exception.class)
		public void _04testPhoneNullUpdateBy() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100003", "permanent_phone",
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _05testPhoneBadUpdateBy() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, "llg", "person_id", "100003", "permanent_phone",
			"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _06testPhoneBadUpdateByTooLong() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, "llg123456789012345678901234567890", "person_id", "100003", "permanent_phone",
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _07testPhoneBadPersonIdType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person", "100003", "permanent_phone",
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _08testPhoneBadPersonId() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", null, "permanent_phone",
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _09testPhoneNoPersonId() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1", "permanent_phone",
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _10testPhoneNoPersonNoActive() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100001", "permanent_phone",
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _11testPhoneNullPhoneType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null,
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _12testPhoneBadPhoneType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent",
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _13testPhoneNoPhone() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
					null, "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _14testPhonePhoneNoTooLong() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
					"814865846412345678901234567890123456789012", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _15testPhoneExtTooLong() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
				"8148658464","814865846412345678901234567890123456789012",  "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _16testPhoneBadPhoneNO() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
				"a8148654864", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _17testPhoneBadExt() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
			"8148654864", "aext", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _18testPhoneIntlFlag() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
				"	8148654864", "123", "T");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _19testUpdatePhoneBadPrincipal() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone("portal",
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L,
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _20testUpdatePhoneNullPassword() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
					null, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L,
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(PhoneServiceReturn.getStatusMessage());
		}
	}	
	@Test(expectedExceptions = Exception.class)
	 	public void _21testUpdatePhoneNullUpdateBy() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100003", "permanent_phone",1L,
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _22testUpdatePhoneBadUpdateBy() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg", "person_id", "100003", "permanent_phone",1L,
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _23testUpdatePhoneBadUpdateByTooLong() throws Exception {
		 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg123456789012345678901234567890", "person_id", "100003", "permanent_phone", 1L,
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
				}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _24testUpdatePhoneBadPersonIdType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person", "100003", "permanent_phone",1L,
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(PhoneServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
		public void _25testUpdatePhoneBadPersonId() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", null, "permanent_phone",1L,
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(PhoneServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
		public void _26testUpdatePhoneNoPersonId() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1", "permanent_phone",1L,
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _27testUpdatePhoneNoPersonNoActive() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100001", "permanent_phone", 1L,
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _28testUpdatePhoneNullPhoneType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null,1L, 
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _29testUpdatePhoneBadPhoneType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent",1L,
					"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _30testUpdatePhoneNoPhone() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L,
				null, "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
	public void _31testUpdatePhonePhoneNoTooLong() throws Exception {
		 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L,
				"814865846412345678901234567890123456789012", "123", "N");
		if (PhoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(PhoneServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _32testUpdatePhoneExtTooLong() throws Exception {
		 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003","permanent_phone",1L,
			"8148658464","814865846412345678901234567890123456789012",  "N");
		if (PhoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(PhoneServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _33testUpdatePhoneBadPhoneNO() throws Exception {
		 ServiceReturn PhoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID,
			ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",
			"a8148654864", "123", "N");
		if (PhoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(PhoneServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
		public void _34testUpdatePhoneBadExt() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L,
				"8148654864", "aext", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _35testUpdatePhoneIntlFlag() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L,
					"	8148654864", "123", "T");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _36testPhoneUpdateNoGroupId() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",null,
					"8148654864", "123", "T");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _37testArchivePhoneBadPrincipal() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone("portal",
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _38testArchivePhoneNullPassword() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				null, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_phone",1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _39testArchivePhoneNullUpdateBy() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100003", "permanent_phone",1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
				}
		}
	@Test(expectedExceptions = Exception.class)
		public void _40testArchivePhoneBadUpdateBy() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg", "person_id", "100003", "permanent_phone",1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _41testArchivePhoneBadUpdateByTooLong() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, "llg123456789012345678901234567890", "person_id", "100003", "permanent_phone", 1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
				}
		}	
	@Test(expectedExceptions = Exception.class)
		public void _42testArchivePhoneBadPersonIdType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person", "100003", "permanent_phone",1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _43testArvhivePhoneBadPersonId() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", null, "permanent_phone",1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _44testArchivePhoneNoPersonId() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1", "permanent_phone",1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _45testArchivePhoneNoPersonNoActive() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100001", "permanent_phone", 1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
		public void _46testArchivePhoneNullPhoneType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null,1L);
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _47testArchivePhoneBadPhoneType() throws Exception {
			 ServiceReturn PhoneServiceReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent",1L,
				"8148658464", "123", "N");
			if (PhoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(PhoneServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
	public void _48testPhoneBadGetType() throws Exception {
		PhoneServiceReturn phoneServiceReturn = port.getPhone(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_", "N");
		if (phoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(phoneServiceReturn.getStatusMessage());
		}
	}
	@Test
		public void _49testAddPhoneLocalUSA() throws Exception {
	
			try {
				cleanUpAll("100003");
			} 
			catch (Exception e) {
				throw new Exception("cleanup failed");
			}
	
			ServiceReturn phoneServiceReturn = null;
			phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "LOCAL_Phone","8148654864", null, "N");
			if (phoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(phoneServiceReturn.getStatusMessage());
				} 
			else 
			{
				PhoneServiceReturn getPhoneReturn = null;
				getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
				if (getPhoneReturn.getStatusCode() != 0) {
					throw new Exception(getPhoneReturn.getStatusMessage());
				} 
				else 
				{
					if (getPhoneReturn.getNumberElements() != 1) {
						throw new Exception("number of elements not equal 1");
					} 
					else 
					{
						for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext();) {
							PhoneReturn phone = it.next();
							if (phone.getInternationalNumber().equals("Y")) {
								throw new Exception("Phone International flag should not be set for  Phone");
						}
					}
				}
			}
		}
	
		}
	@Test
		public void _50testAddPhonePermanentIntl() throws Exception {
	
			try {
				cleanUpAll("100003");
			} catch (Exception e) {
				throw new Exception("cleanup failed");
			}
	
			ServiceReturn phoneServiceReturn = null;
			phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "permanent_Phone","3458148654864", null, "Y");
			if (phoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(phoneServiceReturn.getStatusMessage());
				} 
			else 
			{
				PhoneServiceReturn getPhoneReturn = null;
				getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
				if (getPhoneReturn.getStatusCode() != 0) {
					throw new Exception(getPhoneReturn.getStatusMessage());
				} 
				else 
				{
					if (getPhoneReturn.getNumberElements() != 1) {
						throw new Exception("number of elements not equal 1");
					} 
					else 
					{
						for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext();) {
							PhoneReturn phone = it.next();
							if (phone.getInternationalNumber().equals("N")) {
								throw new Exception("Phone International flag not set for  Phone");
							}
						}
					}
				}
			}
	
		}
	@Test
		public void _51testAddPhoneWorkUpdateExt() throws Exception {
			boolean phoneUpdate	=	false;
			Long saveGroupId;
			try {
				cleanUpAll("100003");
			} 
			catch (Exception e) {
				throw new Exception("cleanup failed");
			}
	
			ServiceReturn phoneServiceReturn = null;
			phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
			"person_id", "100003", "work_Phone","8148654864", null, "N");
			if (phoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(phoneServiceReturn.getStatusMessage());
			} 
			else 
			{
				PhoneServiceReturn getPhoneReturn = null;
				getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
						"person_id", "100003", null, "N");
				if (getPhoneReturn.getStatusCode() != 0) {
					throw new Exception(getPhoneReturn.getStatusMessage());
				} 
				else 
				{	
					if (getPhoneReturn.getNumberElements() != 1) {
						throw new Exception("number of elements not equal 1");
					} 
					else 
					{
						ServiceReturn updatePhoneReturn =null;
						for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext() && (! phoneUpdate);) {
							PhoneReturn phone = it.next();
							phoneUpdate = true;
							saveGroupId=phone.getGroupId();
							updatePhoneReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
									"person_id", "100003", "work_Phone",saveGroupId,"8148654864", "123", "N");
							if (updatePhoneReturn.getStatusCode() != 0) {
								throw new Exception(updatePhoneReturn.getStatusMessage());
							} 
							else 
							{
								PhoneServiceReturn getAgainPhoneReturn = null;
								getAgainPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
										"person_id", "100003", null, "N");
								if (getAgainPhoneReturn.getStatusCode() != 0) {
									throw new Exception(getAgainPhoneReturn.getStatusMessage());
								} 
								else 
								{	
									if (getAgainPhoneReturn.getNumberElements() != 1) {
										throw new Exception("number of elements not equal 1");
									} 
									else
									{
										for (final Iterator<PhoneReturn> its = getAgainPhoneReturn.getPhoneReturnRecord().iterator(); its.hasNext();) {
											PhoneReturn phone1 = its.next();
											if (phone1.getGroupId().equals(saveGroupId)) {
												
												
												if ( (!phone1.getExtension().equals("123"))) {
													throw new Exception("phone extension was not updated correctly");
												}
											}
											else 
											{
												throw new Exception("problems getting the updated phone record");
											}
										}	
									}
								}
							}
						}
					}
				}
			}
		}
	@Test
	public void _52testAddGetByType() throws Exception  {
		try {
			cleanUpAll("100003");
		} 
		catch (Exception e) {
			throw new Exception("cleanup failed");
		}
	
		ServiceReturn phoneServiceReturn = null;
		phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
		"person_id", "100003", "work_Phone","8148654864", null, "N");
		if (phoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(phoneServiceReturn.getStatusMessage());
		} 
		else 
		{
			PhoneServiceReturn getPhoneReturn = null;
			getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "work_phone", "N");
			if (getPhoneReturn.getStatusCode() != 0) {
				throw new Exception(getPhoneReturn.getStatusMessage());
			} 
			else 
			{	
				if (getPhoneReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
				
			}
		}
	}
	@Test(expectedExceptions = Exception.class)
	public void _53testAddGetByTypeNoType() throws Exception  {
		try {
			cleanUpAll("100003");
		} 
		catch (Exception e) {
			throw new Exception("cleanup failed");
		}
	
		ServiceReturn phoneServiceReturn = null;
		phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
		"person_id", "100003", "work_Phone","8148654864", null, "N");
		if (phoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(phoneServiceReturn.getStatusMessage());
		} 
		else 
		{
			PhoneServiceReturn getPhoneReturn = null;
			getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "local_phone", "N");
			if (getPhoneReturn.getStatusCode() != 0) {
				throw new Exception(getPhoneReturn.getStatusMessage());
			} 
			else 
			{	
				if (getPhoneReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
				
			}
		}
	}
	@Test
	public void _54testAddGetByTypeHistory() throws Exception  {
		try {
			cleanUpAll("100003");
		} 
		catch (Exception e) {
			throw new Exception("cleanup failed");
		}
	
		ServiceReturn phoneServiceReturn = null;
		phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
		"person_id", "100003", "work_Phone","8148654864", null, "N");
		if (phoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(phoneServiceReturn.getStatusMessage());
		} 
		else 
		{
			PhoneServiceReturn getPhoneReturn = null;
			getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "local_phone", "Y");
			if (getPhoneReturn.getStatusCode() != 0) {
				throw new Exception(getPhoneReturn.getStatusMessage());
			} 
			else 
			{	
				if (getPhoneReturn.getNumberElements() < 1) {
					throw new Exception("number of elements less than 1");
				} 
				
			}
		}
	}
	@Test
	public void _55testUpdatePhoneType() throws Exception {
		boolean phoneUpdate	=	false;
		Long saveGroupId;
		try {
			cleanUpAll("100003");
		} 
		catch (Exception e) {
			throw new Exception("cleanup failed");
		}
	
		ServiceReturn phoneServiceReturn = null;
		phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "work_Phone","8148654864", null, "N");
		if (phoneServiceReturn.getStatusCode() != 0) {
			throw new Exception(phoneServiceReturn.getStatusMessage());
		} 
		else 
		{
			PhoneServiceReturn getPhoneReturn = null;
			getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getPhoneReturn.getStatusCode() != 0) {
				throw new Exception(getPhoneReturn.getStatusMessage());
			} 
			else 
			{	
				if (getPhoneReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
				else 
				{
					ServiceReturn updatePhoneReturn =null;
					for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext() && (! phoneUpdate);) {
						PhoneReturn phone = it.next();
						phoneUpdate = true;
						saveGroupId=phone.getGroupId();
						updatePhoneReturn = port.updatePhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
								"person_id", "100003", "work_Phone",saveGroupId,"8148654864", "123", "N");
						if (updatePhoneReturn.getStatusCode() != 0) {
							throw new Exception(updatePhoneReturn.getStatusMessage());
						} 
						else 
						{
							PhoneServiceReturn getAgainPhoneReturn = null;
							getAgainPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
									"person_id", "100003", null, "N");
							if (getAgainPhoneReturn.getStatusCode() != 0) {
								throw new Exception(getAgainPhoneReturn.getStatusMessage());
							} 
							else 
							{	
								if (getAgainPhoneReturn.getNumberElements() != 1) {
									throw new Exception("number of elements not equal 1");
								} 
								else
								{
									for (final Iterator<PhoneReturn> its = getAgainPhoneReturn.getPhoneReturnRecord().iterator(); its.hasNext();) {
										PhoneReturn phone1 = its.next();
										if (phone1.getGroupId().equals(saveGroupId)) {
											
											
											if ( (!phone1.getExtension().equals("123"))) {
												throw new Exception("phone extension was not updated correctly");
											}
										}
										else 
										{
											throw new Exception("problems getting the updated phone record");
										}
									}	
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Test
		public void _56testSetPrimaryWork() throws Exception {
	
			boolean phoneUpdate	=	false;
			boolean stopCheck = false;
			Long saveGroupId;
			try {
				cleanUpAll("100003");
			} 
			catch (Exception e) {
				throw new Exception("cleanup failed");
			}
	
			ServiceReturn phoneServiceReturn = null;
			phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "work_Phone","8148654864", null, "N");
			if (phoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(phoneServiceReturn.getStatusMessage());
			} 
			else 
			{
				phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
						"person_id", "100003", "work_Phone","8149495256", null, "N");
				if (phoneServiceReturn.getStatusCode() != 0) {
					throw new Exception(phoneServiceReturn.getStatusMessage());
				} 
				else 
				{
					PhoneServiceReturn getPhoneReturn = null;
					getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
							"person_id", "100003", null, "N");
					if (getPhoneReturn.getStatusCode() != 0) {
						throw new Exception(getPhoneReturn.getStatusMessage());
					} 
					else 
					{	
						if (getPhoneReturn.getNumberElements() != 2) {
							throw new Exception("number of elements not equal 2");
						} 
						else 
						{
							ServiceReturn setPrimaryPhoneReturn =null;
							for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext() && (! phoneUpdate);) {
								PhoneReturn phone = it.next();
								phoneUpdate = true;
								saveGroupId=phone.getGroupId();
								setPrimaryPhoneReturn = port.setPrimaryPhoneByType(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
										"person_id", "100003", "work_Phone",saveGroupId);
								if (setPrimaryPhoneReturn.getStatusCode() != 0) {
									throw new Exception(setPrimaryPhoneReturn.getStatusMessage());
								} 
								else 
							{
									PhoneServiceReturn getAgainPhoneReturn = null;
									getAgainPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
											"person_id", "100003", null, "N");
									if (getAgainPhoneReturn.getStatusCode() != 0) {
										throw new Exception(getAgainPhoneReturn.getStatusMessage());
									} 
									else 
									{	
										if (getAgainPhoneReturn.getNumberElements() != 2) {
											throw new Exception("number of elements not equal 2");
										} 
										else
										{
											for (final Iterator<PhoneReturn> its = getAgainPhoneReturn.getPhoneReturnRecord().iterator(); its.hasNext() && (! stopCheck);) {
												PhoneReturn phone1 = its.next();
												if (phone1.getGroupId().equals(saveGroupId)) {	
													stopCheck = true;
													if ( (phone1.getPrimaryFlag().equals("N"))) {
														throw new Exception("primary flag not set to Y");
													}
												}
									
											}	
										}
									}
								}
							}
						}
					}
				}
			}
		}
	
	@Test
		public void _57testSetPrimaryWorkAndUpdate() throws Exception {
	
			boolean phoneUpdate	=	false;
			Long saveGroupId;
			Long saveGroupId2;
			try {
				cleanUpAll("100003");
			} 
			catch (Exception e) {
				throw new Exception("cleanup failed");
			}
			/** 
			 * add a work address
			 * 
			 */
			ServiceReturn phoneServiceReturn = null;
			phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "work_Phone","8148654864", null, "N");
			if (phoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(phoneServiceReturn.getStatusMessage());
				} 
			else 
			{
				/** 
				 * add a second work address
				 */
				phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "work_Phone","8149495256", null, "N");
				if (phoneServiceReturn.getStatusCode() != 0) {
					throw new Exception(phoneServiceReturn.getStatusMessage());
				} 
				else 
				{
					/**
					 * Verify two phones have been added 
					 * 
					 */
					PhoneServiceReturn getPhoneReturn = null;
					getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
						"person_id", "100003", null, "N");
					if (getPhoneReturn.getStatusCode() != 0) {
						throw new Exception(getPhoneReturn.getStatusMessage());
					} 
					else 
					{	
						if (getPhoneReturn.getNumberElements() != 2) {
							throw new Exception("number of elements not equal 2");
						} 
						else 
						{
							/**
							 * set primary for first item returned
							 */
							ServiceReturn setPrimaryPhoneReturn =null;
							for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext() && (! phoneUpdate);) {
								PhoneReturn phone = it.next();
								phoneUpdate = true;
								saveGroupId=phone.getGroupId();
								setPrimaryPhoneReturn = port.setPrimaryPhoneByType(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
									"person_id", "100003", "work_Phone",saveGroupId);
								if (setPrimaryPhoneReturn.getStatusCode() != 0) {
									throw new Exception(setPrimaryPhoneReturn.getStatusMessage());
								} 
								else 
								{
									/**
									 * get the phone records, verify two phone record
									 * verify that the primary flag was set
									 */
									PhoneServiceReturn getAgainPhoneReturn = null;
									getAgainPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
										"person_id", "100003", null, "N");
									if (getAgainPhoneReturn.getStatusCode() != 0) {
										throw new Exception(getAgainPhoneReturn.getStatusMessage());
									} 
									else 
									{	
										if (getAgainPhoneReturn.getNumberElements() != 2) {
											throw new Exception("number of elements not equal 2");
										} 
										else
										{
											/**
											 * get phone records
											 */
											
											for (final Iterator<PhoneReturn> its = getAgainPhoneReturn.getPhoneReturnRecord().iterator(); its.hasNext()  ;) {
												PhoneReturn phone1 = its.next();
												/**
												 * verify that the primary flag was set for correct record
												 * 
												 */
												if (phone1.getGroupId().equals(saveGroupId)) {	
													if ( (phone1.getPrimaryFlag().equals("N"))) {
														throw new Exception("primary flag not set to Y");
													}
												}
												else
												{
													/**
													 * update the primary to the other phone record
													 * 
													 */
													saveGroupId2 = phone1.getGroupId();
													setPrimaryPhoneReturn = port.setPrimaryPhoneByType(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
														"person_id", "100003", "work_Phone",saveGroupId2);
													if (setPrimaryPhoneReturn.getStatusCode() != 0) {
														throw new Exception(setPrimaryPhoneReturn.getStatusMessage());
													} 
													else 
													{
														/**
														 * get phone records
														 */
														PhoneServiceReturn getOneMorePhoneReturn = null;
														getOneMorePhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
															"person_id", "100003", null, "N");
														if (getOneMorePhoneReturn.getStatusCode() != 0) {
															throw new Exception(getOneMorePhoneReturn.getStatusMessage());
														} 
														else 
														{	
															if (getOneMorePhoneReturn.getNumberElements() != 2) {
																throw new Exception("number of elements not equal 2");
															} 
															else
															{
																for (final Iterator<PhoneReturn> its2 = getOneMorePhoneReturn.getPhoneReturnRecord().iterator(); its2.hasNext()  ;) {
																	/**
																	 * verify the new primary
																	 */
																	PhoneReturn phone2 = its2.next();
																	if (phone2.getGroupId().equals(saveGroupId2)) {	
																		if ( (phone2.getPrimaryFlag().equals("N"))) {
																			throw new Exception("primary flag on record "+ saveGroupId2 + " not set to Y");
																		}
																	}
																	else
																	{
																		if (phone2.getGroupId().equals(saveGroupId)) {	
																			if ( (phone2.getPrimaryFlag().equals("Y"))) {
																				throw new Exception("primary flag on record "+ saveGroupId + " not set to N");
																			}	
																		}
																	}
																		
																}
															}
														}
								
													}	
												}
											}
										}
									}
								}
							}
						}
					}
				}
	
			}
		
		}
	@Test
		public void _58testArchiveAWorkAddress() throws Exception {
	
			boolean phoneUpdate	=	false;
			boolean recordArchived = false;
			Long saveGroupId;
		
			try {
				cleanUpAll("100003");
			} 
			catch (Exception e) {
				throw new Exception("cleanup failed");
			}
			/** 
			 * add a work address
			 * 
			 */
			ServiceReturn phoneServiceReturn = null;
			phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "work_Phone","8148654864", null, "N");
			if (phoneServiceReturn.getStatusCode() != 0) {
				throw new Exception(phoneServiceReturn.getStatusMessage());
				} 
			else 
			{
				/** 
				 * add a second work address
				 */
				phoneServiceReturn = port.addPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
						"person_id", "100003", "work_Phone","8149495256", null, "N");
				if (phoneServiceReturn.getStatusCode() != 0) {
					throw new Exception(phoneServiceReturn.getStatusMessage());
				} 
				else 
				{
					/**
					 * Verify two phones have been added 
					 * 
					 */
					PhoneServiceReturn getPhoneReturn = null;
					getPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
							"person_id", "100003", null, "N");
					if (getPhoneReturn.getStatusCode() != 0) {
						throw new Exception(getPhoneReturn.getStatusMessage());
					} 
					else 
					{	
						if (getPhoneReturn.getNumberElements() != 2) {
							throw new Exception("number of elements not equal 2");
						} 
						else 
						{
						/**
						 * archive first item returned
						 */
							ServiceReturn archivePhoneReturn =null;
							for (final Iterator<PhoneReturn> it = getPhoneReturn.getPhoneReturnRecord().iterator(); it.hasNext() && (! phoneUpdate);) {
								PhoneReturn phone = it.next();
								phoneUpdate = true;
								saveGroupId=phone.getGroupId();
								archivePhoneReturn = port.archivePhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
								"person_id", "100003", "work_Phone",saveGroupId);
								if (archivePhoneReturn.getStatusCode() != 0) {
									throw new Exception(archivePhoneReturn.getStatusMessage());
								} 
								else 
								{
								/**
								 * get the phone records, verify only one returned
								 * 
								 */
								PhoneServiceReturn getAgainPhoneReturn = null;
									getAgainPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
											"person_id", "100003", null, "N");
									if (getAgainPhoneReturn.getStatusCode() != 0) {
										throw new Exception(getAgainPhoneReturn.getStatusMessage());
									} 
									else 
									{	
										if (getAgainPhoneReturn.getNumberElements() != 1) {
											throw new Exception("number of elements not equal 1");
										} 
										else
										{
											/**
											 * get phone records
											 */
										
											for (final Iterator<PhoneReturn> its = getAgainPhoneReturn.getPhoneReturnRecord().iterator(); its.hasNext()  ;) {
												PhoneReturn phone1 = its.next();
												/**
												 * verify that archived group id not in list
												 * 
												 */
												if (phone1.getGroupId().equals(saveGroupId)) {	
													throw new Exception("phone record " + saveGroupId + " not archived correctly");
												}
											}
											
											
											
										
										}
									}
									PhoneServiceReturn getHistoryPhoneReturn = null;
									getHistoryPhoneReturn = port.getPhone(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
											"person_id", "100003", null, "Y");
									if (getHistoryPhoneReturn.getStatusCode() != 0) {
										throw new Exception(getAgainPhoneReturn.getStatusMessage());
									} 
									else 
									{	
										if (getHistoryPhoneReturn.getNumberElements() == 0) {
											throw new Exception("number of elements for getHistory  equal 0");
										} 
										else
										{
											/**
											 * get phone records
											 */
										
											for (final Iterator<PhoneReturn> its = getHistoryPhoneReturn.getPhoneReturnRecord().iterator(); its.hasNext() && (! recordArchived) ;) {
												PhoneReturn phone1 = its.next();
												/**
												 * verify that archived group id not in list
												 * 
												 */
												if (phone1.getGroupId().equals(saveGroupId)) {	
													recordArchived   = true;
												}
											}
											
											if (! recordArchived) {
												throw new Exception("archive for record " + saveGroupId + " not found in getHistory");
											}
											
										
										}
									}
								}
							}
						}
					}
				}
	
			}
	
		}
}
