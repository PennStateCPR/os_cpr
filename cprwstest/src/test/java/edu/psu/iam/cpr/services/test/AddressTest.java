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

import edu.psu.iam.cpr.service.AddressReturn;
import edu.psu.iam.cpr.service.AddressServiceReturn;
import edu.psu.iam.cpr.service.Cprws;
import edu.psu.iam.cpr.service.CprwsSEI;
import edu.psu.iam.cpr.service.ServiceReturn;

public class AddressTest {


	static final Cprws cprws= new Cprws();
	static final CprwsSEI port = cprws.getCprwsPort();

	public void cleanUpAll(String person_id) throws Exception {
		AddressServiceReturn getAddressReturn = null;
		ServiceReturn archiveAddressReturn = null;
		getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", person_id, null, "N");
		if (getAddressReturn.getStatusCode() != 0) {
			throw new Exception(getAddressReturn.getStatusMessage());
		} 
		else 
		{
			if (getAddressReturn.getNumberElements() > 0) {
				for (final Iterator<AddressReturn> it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext();) {
					AddressReturn address = it.next();
					archiveAddressReturn = port.archiveAddress(ServiceAuthentication.GOOD_USERID,
							ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", person_id,
							address.getAddressType(),
							address.getDocumentType(), address.getGroupId());
					if (archiveAddressReturn.getStatusCode() != 0) {
						throw new Exception(
								archiveAddressReturn.getStatusMessage());
					}
				}
			}
		}
	}

	@Test(expectedExceptions = Exception.class)
		public void _01testAddressBadPassword() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.BAD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "permanent_address",
					null, "3820 Oak Street", null, null, "Vancouver", "BC",
					"V6H2M5", "CAN", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _02testAddressUpdateByBad() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, "me", "person_id", "100003", "permanent_address",
					null, "3820 Oak Street", null, null, "Vancouver", "BC",
					"V6H2M5", "CAN", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _03testAddressUpdateByNull() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, null, "person_id", "100003", "permanent_address",
					null, "3820 Oak Street", null, null, "Vancouver", "BC",
					"V6H2M5", "CAN", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _04testAddressPersonTypeBad() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person", "100003", "local_address", null,
					"109 Town Centre Dr.", null, null, "Johnstown", "PA", "15904",
					"USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _05testAddressPersonIdBad() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "1",
							"local_address", null, "746 Tussey Lane", null, null,
							null, "PA", "16801", "USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _06testAddressBadAddressType() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "local", null,
					"1415 Chestnut", null, null, "Berwick", null, "18603", "USA",
					null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _07testAddressNullAddressType() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, null,
					"1415 Chestnut", null, null, "Berwick", null, "18603", "USA",
					null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _08testAddressBadDocumentType() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "DOCUMENTED_ADDRESS",
					"Military", "1415 Chestnut", null, null, "Berwick", null,
					"18603", "USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _09testAddressBadComboAddressDocumentType() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "LOCAL_ADDRESS",
					"MILITARY_ID", "1415 Chestnut", null, null, "Berwick", null,
					"18603", "USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _10testAddressBadComboAddressDocumentTypeNull() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "DOCUMENTED_ADDRESS",
					null, "1415 Chestnut", null, null, "Berwick", null, "18603",
					"USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _11testAddressBadComboAddressTypeNullDocumentType() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "MILITARY_ID",
					"1415 Chestnut", null, null, "Berwick", null, "18603", "USA",
					null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _12testAddressNoCountryCode() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", null,
					"1415 Chestnut", null, null, "Berwick", null, "18603", null,
					null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _13testAddressBlankCountryCode() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", null,
					"1415 Chestnut", null, null, "Berwick", null, "18603", " ",
					null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _14testAddressBadCountryCode() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", null,
					"1415 Chestnut", null, null, "Berwick", null, "18603", "xxx",
					null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _15testAddressBadState() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", null,
					"1415 Chestnut", null, null, "Berwick", "PENNSYLVANIA",
					"18603", "USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _16testAddressBadPostalCode() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", null,
					"1415 Chestnut", null, null, "Berwick", "PA", "abcde", "USA",
					null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _17testAddressNoAddresses() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", null,
					null, null, null, "Berwick", "PA", "18603", "USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _18testAddressBadCampus() throws Exception {
			ServiceReturn addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", null,
					"215A Computer Building", null, null, "University Park", "PA",
					"16802", "USA", "ik", "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	
	@Test(expectedExceptions = Exception.class)
		public void _19testAddressNoGroupId() throws Exception {
			ServiceReturn addressServiceReturn = port.updateAddress(
					ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003",
					"WORK_ADDRESS", null, null, "215A Computer Building", null,
					null, "University Park", "PA", "16802", "USA", "ik", "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
		}
	@Test(expectedExceptions = Exception.class)
	public void _20testAddressBadGetType() throws Exception {
		AddressServiceReturn addressServiceReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_", "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		}
	}
	@Test(expectedExceptions = Exception.class)
		public void _21testUpdateAddressWorkNBadGroupId() throws Exception {
	
			try {
				cleanUpAll("100003");
			} catch (Exception e) {
				throw new Exception("GetAddressAll failed");
			}
	
			ServiceReturn addressServiceReturn = null;
			addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "WORK_ADDRESS", null,
					"215A Computer Building", null, null, "University Park", "PA",
					"16802", "USA", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			} 
			else 
			{
				AddressServiceReturn getAddressReturn = null;
				ServiceReturn updateAddressReturn = null;
				getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
						"person_id", "100003", null, "N");
				if (getAddressReturn.getStatusCode() != 0) {
					throw new Exception(getAddressReturn.getStatusMessage());
				} 
				else
				{
					if (getAddressReturn.getNumberElements() != 1) {
						throw new Exception("number of elements not equal 1");
					} else {
						for (Iterator<AddressReturn> it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext();) {
							AddressReturn address = it.next();
							updateAddressReturn = port.updateAddress(ServiceAuthentication.GOOD_USERID,
									ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003",
									"WORK_ADDRESS", null, address.getGroupId()+ 1,
									address.getAddress1(), address.getAddress2(),
									address.getAddress3(), address.getCity(),
									address.getStateOrProvince(),
									address.getPostalCode(), "USA", "UP", "N");
							if (updateAddressReturn.getStatusCode() != 0) {
								throw new Exception(
										updateAddressReturn.getStatusMessage());
							} else {
								getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,
										ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "N");
								if (getAddressReturn.getStatusCode() != 0) {
									throw new Exception(
											getAddressReturn.getStatusMessage());
								}
								if (getAddressReturn.getNumberElements() != 1) {
									throw new Exception(
											"number of elements not equal 1");
								} else {
									for (it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext();) {
										address = it.next();
										if (address.getCampusCode() == null) {
											throw new Exception(
													"Campus code is null, should be UP");
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
		public void _22testGetAddress() throws Exception {
			AddressServiceReturn addressServiceReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,
					ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
			try {
				cleanUpAll("100003");
			} catch (Exception e) {
				throw new Exception("GetAddressAll failed");
			}
			addressServiceReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (addressServiceReturn.getStatusCode() != 0) {
	
			} else {
				if (addressServiceReturn.getNumberElements() > 0) {
					throw new Exception("Address cleanup failed");
				}
			}
	
		}
	@Test
	public void _23testGetAddressByType() throws Exception {
		
		
		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("CleanupAll failed");
		}
	
		ServiceReturn addressServiceReturn = null;
		AddressServiceReturn getAddressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "WORK_ADDRESS", null,
				"215A Computer Building", null, null, "University Park", "PA",
				"16802", "USA", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		}
		else 
		{
			getAddressServiceReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", "N");

			if (getAddressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
			try {
				cleanUpAll("100003");
			} catch (Exception e) {
			throw new Exception("cleanup all failed");
			}
			getAddressServiceReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", null, null);
			if (getAddressServiceReturn.getStatusCode() != 0) {
	
			} else {
				if (getAddressServiceReturn.getNumberElements() > 0) {
				throw new Exception("Address cleanup failed");
				}
			}
		}
	
	}
	@Test
	public void _24testGetAddressByTypeHistory() throws Exception {
		
		
		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("cleanup failed");
		}
	
		ServiceReturn addressServiceReturn = null;
		AddressServiceReturn getAddressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "WORK_ADDRESS", null,
				"215A Computer Building", null, null, "University Park", "PA",
				"16802", "USA", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		}
		else 
		{
			getAddressServiceReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,
				ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", "WORK_ADDRESS", "Y");

			if (getAddressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
			try {
				cleanUpAll("100003");
			} 
			catch (Exception e) {
				throw new Exception("Cleanup failed");
				}
			getAddressServiceReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", null, "N");
			if (getAddressServiceReturn.getStatusCode() != 0) {
	
			} 
			else 
			{
				if (getAddressServiceReturn.getNumberElements() > 0) {
				throw new Exception("Address cleanup failed");
				}
			}
		}
	
	}
@Test
	public void _25testAddAddressLocalUsa() throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("cleanup failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "LOCAL_ADDRESS", null, "1415 Chestnut",
				null, null, "Berwick", "PA", "18603", "USA", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
//				else {
//					for (final Iterator<AddressReturn> it = getAddressReturn
//							.getAddressReturnRecord().iterator(); it.hasNext();) {
//					AddressReturn address = it.next();
//						if (address.getVerifiedFlag().equals("N")) {
//							throw new Exception(
//									"Address verify flag not set for valid address");
//						}
//					}
//				}
			}
		}

	}

@Test
	public void _26testAddAddressPermanentCan() throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "permanent_address", null,
				"3820 Oak Street", null, null, "Vancouver", "BC", "V6H2M5",
				"CAN", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
//				else {
//					for (final Iterator<AddressReturn> it = getAddressReturn
//							.getAddressReturnRecord().iterator(); it.hasNext();) {
//						AddressReturn address = it.next();
//						if (address.getVerifiedFlag().equals("N")) {
//							throw new Exception(
//									"Address verify flag not set for valid address");
//						}
//					}
//				}
			}
		}

	}

@Test
	public void _27testAddAddressWorkUK() throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "work_address", null,
				"57 Cornmarket St", null, null, "Oxford", null, "OX1 3HB",
				"CAN", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} else {
					for (final Iterator<AddressReturn> it = getAddressReturn
							.getAddressReturnRecord().iterator(); it.hasNext();) {
						AddressReturn address = it.next();
						if (address.getVerifiedFlag().equals("Y")) {
							throw new Exception(
									"Address verify set on for invalid address");
						}
					}
				}
			}
		}

	}

@Test
	public void _28testAddAddressBillingAcadUsaNoState() throws Exception {

	try {
		cleanUpAll("100003");
	} catch (Exception e) {
		throw new Exception("GetAddressAll failed");
	}

	ServiceReturn addressServiceReturn = null;
	addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
			"person_id", "100003", "BILLING_ACADEMIC_ADDRESS", null, "1415 Chestnut",
			null, null, "Berwick", null, "18603", "USA", null, "N");
	if (addressServiceReturn.getStatusCode() != 0) {
		throw new Exception(addressServiceReturn.getStatusMessage());
	} 
	else {
		AddressServiceReturn getAddressReturn = null;
		getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", null, "N");
		if (getAddressReturn.getStatusCode() != 0) {
			throw new Exception(getAddressReturn.getStatusMessage());
		} 
		else {
			if (getAddressReturn.getNumberElements() != 1) {
				throw new Exception("number of elements not equal 1");
			} 
//			else {
//				for (final Iterator<AddressReturn> it = getAddressReturn
//						.getAddressReturnRecord().iterator(); it.hasNext();) {
//					AddressReturn address = it.next();
//					if (address.getVerifiedFlag().equals("Y")) {
//						if (address.getStateOrProvince() == null) {
//							throw new Exception("State null");
//						} else {
//							if (!address.getStateOrProvince().equals("PA")) {
//								throw new Exception(
//								"State value is not set correctly");
//							}
//						}
//					} else {
//						throw new Exception(
//								"Address verify flag not set for valid address");
//					}
//				}
//			}
		}
	}

	}
@Test(expectedExceptions = Exception.class)
	public void _29testAddAddressDocumentLicenseUsaNoCity() throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "DOCUMENTED_ADDRESS", "STATE_DRIVERS_LICENSE", "1415 Chestnut",
				null, null, null,"PA", "18603", "USA", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} else {
					for (final Iterator<AddressReturn> it = getAddressReturn
							.getAddressReturnRecord().iterator(); it.hasNext();) {
						AddressReturn address = it.next();
						if (address.getVerifiedFlag().equals("Y")) {
							if (address.getCity()== null) {
								throw new Exception("City null");
							} else {
								if (!address.getCity().equals("Berwick")) {
									throw new Exception(
											"City value is not set correctly");
								}
							}
						} else {
							throw new Exception(
									"Address verify flag not set for valid address");
						}
					}
				}
			}
		}

	}
@Test(expectedExceptions = Exception.class)
	public void _30testAddAddressDocumentStateIdUsaNoCityState() throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "DOCUMENTED_ADDRESS","STATE_ID_CARD", "1415 Chestnut",
				null, null, null, null, "18603", "USA", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} else {
					for (final Iterator<AddressReturn> it = getAddressReturn
							.getAddressReturnRecord().iterator(); it.hasNext();) {
						AddressReturn address = it.next();
						if (address.getVerifiedFlag().equals("Y")) {
							if (address.getCity() == null) {
								throw new Exception("City null");
							} else {
								if (!address.getCity().equals("Berwick")) {
									throw new Exception(
											"City value is not set correctly");
								}
							}
							if (address.getStateOrProvince()== null) {
								throw new Exception("State null");
							} else {
								if (!address.getStateOrProvince().equals("PA")) {
									throw new Exception(
											"State value is not set correctly");
								}
							}
						} else {
							throw new Exception(
									"Address verify flag not set for valid address");
						}
					}
				}
			}
		}

	}
@Test
	public void _31testAddAddressDocumentPassportUsaAddress2Only()
			throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "DOCUMENTED_ADDRESS", "PASSPORT",
				null, "1415 Chestnut", null, "Berwick", "PA", "18603", "USA",
				null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
//				else {
//					for (final Iterator<AddressReturn> it = getAddressReturn
//							.getAddressReturnRecord().iterator(); it.hasNext();) {
//						AddressReturn address = it.next();
//						if (address.getVerifiedFlag().equals("Y")) {
//							if (address.getAddress1() ==null) {
//								throw new Exception("Address1 is null");
//							}
//							if (!(address.getAddress2() == null)) {
//								throw new Exception("Address2 is  not null");
//							}
//						} else {
//							throw new Exception(
//									"Address verify flag not set for valid address");
//						}
//					}
//				}
			}
		}

	}
@Test
	public void _32testAddAddressDocumentMilitaryUsaAddress3Only()
			throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "DOCUMENTED_ADDRESS", "MILITARY_ID",
				null, null, "1415 Chestnut", "Berwick", "PA", "18603", "USA",
				null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
//				else {
//					for (final Iterator<AddressReturn> it = getAddressReturn
//							.getAddressReturnRecord().iterator(); it.hasNext();) {
//						AddressReturn address = it.next();
//						if (address.getVerifiedFlag().equals("Y")) {
//							if ((address.getAddress1()== null)) {
//								throw new Exception("Address1 is null");
//							}
//							if (!(address.getAddress3() == null) ){
//								throw new Exception("Address3 is  not null");
//							}
//						} else {
//							throw new Exception(
//									"Address verify flag not set for valid address");
//						}
//					}
//				}
			}
		}

	}
@Test
	public void _33testUpdateAddressWorkUpdateCampus() throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "WORK_ADDRESS", null,  "215A Computer Building", null,
				null, "University Park", "PA", "16802", "USA", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} 
		else {
			AddressServiceReturn getAddressReturn = null;
			ServiceReturn updateAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} else {
					for ( Iterator<AddressReturn> it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext();) {
						AddressReturn address = it.next();
						updateAddressReturn = port.updateAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,"person_id", "100003", "WORK_ADDRESS", null, address.getGroupId(), 
								address.getAddress1(), address.getAddress2(), address.getAddress3(),  address.getCity(), 
								 address.getStateOrProvince(),  address.getPostalCode(), "USA", "UP", "N");
						if (updateAddressReturn.getStatusCode() != 0) {
							throw new Exception(updateAddressReturn.getStatusMessage());
						}
						else {
							getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
									"person_id", "100003", null, "N");
							if (getAddressReturn.getStatusCode() != 0) {
								throw new Exception(getAddressReturn.getStatusMessage());
							}
							if (getAddressReturn.getNumberElements() != 1) {
								throw new Exception("number of elements not equal 1");
							} else 
							{
								for ( it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext();) {
									address = it.next();
									if (address.getCampusCode() == null) {
										throw new Exception("Campus code is null, should be UP");
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
	public void _34testUpdateAddressPermanentBadCountryToGoodCountry()
			throws Exception {

		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "PERMANENT_ADDRESS", null,
				"1415 Chestnut Street", null, null, "Berwick", "PA", "18603",
				"CAN", null, "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} else {
			AddressServiceReturn getAddressReturn = null;
			ServiceReturn updateAddressReturn = null;
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			} else {
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} else {
					for (Iterator<AddressReturn> it = getAddressReturn
							.getAddressReturnRecord().iterator(); it.hasNext();) {
						AddressReturn address = it.next();
						updateAddressReturn = port.updateAddress(ServiceAuthentication.GOOD_USERID,
								ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003",
								"PERMANENT_ADDRESS", null,
								address.getGroupId(), address.getAddress1(),
								address.getAddress2(), address.getAddress3(),
								address.getCity(),
								address.getStateOrProvince(),
								address.getPostalCode(), "USA", null, "N");
						if (updateAddressReturn.getStatusCode() != 0) {
							throw new Exception(
									updateAddressReturn.getStatusMessage());
						} else {
							getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,
									ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "N");
							if (getAddressReturn.getStatusCode() != 0) {
								throw new Exception(
										getAddressReturn.getStatusMessage());
							}
							if (getAddressReturn.getNumberElements() != 1) {
								throw new Exception(
										"number of elements not equal 1");
							} 
//							else {
//								for (it = getAddressReturn
//										.getAddressReturnRecord().iterator(); it
//										.hasNext();) {
//									address = it.next();
//									if (address.getVerifiedFlag().equals("N")) {
//										throw new Exception(
//												"Verified FLag should be Y but it is N");
//									} else {
//										if (!(address.getCountryName()
//												.equals("United States"))) {
//											throw new Exception(
//													"Country Name should be United States");
//										}
//									}
//								}
//							}
						}
					}
				}
			}
		}

	}
@Test
	public void _35testSetPrimaryForTwoWorkAddress()throws Exception {
	boolean primarySet = false;
	boolean groupFound = false;
		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}

		ServiceReturn addressServiceReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "Work_ADDRESS", null,
				"215 Computer Building", null, null, "University Park", "PA", "16802",
				"USA", "UP", "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} 
		else 
		{
			addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", "Work_ADDRESS", null,
					"3000 Ivyside Park", null, null, "Altoona", "PA", "16601",
					"USA", "AA", "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
			else
			{
				AddressServiceReturn getAddressReturn = null;
				ServiceReturn setPrimaryAddressReturn = null;
				getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
				if (getAddressReturn.getStatusCode() != 0) {
					throw new Exception(getAddressReturn.getStatusMessage());
				}
				else 
				{
					if (getAddressReturn.getNumberElements() != 2) {
					throw new Exception("number of elements not equal 2");
					} 
					else 
					{
						for (Iterator<AddressReturn> it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext() && (! primarySet);) {
							AddressReturn address = it.next();
							setPrimaryAddressReturn = port.setPrimaryAddressByType(ServiceAuthentication.GOOD_USERID,
								ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003",
								address.getAddressType(), null, address.getGroupId());
							if (setPrimaryAddressReturn.getStatusCode() != 0) {
								throw new Exception(
									setPrimaryAddressReturn.getStatusMessage());
							} 
							else 
							{	primarySet = true;
						
								AddressServiceReturn getAnotherAddressReturn = null;
								getAnotherAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "N");
								if (getAnotherAddressReturn.getStatusCode() != 0) {
									throw new Exception(getAnotherAddressReturn.getStatusMessage());
								}
								if (getAnotherAddressReturn.getNumberElements() != 2) {
									throw new Exception("number of elements not equal 2");
								} 
								else 
								{
									for (final Iterator<AddressReturn> its = getAnotherAddressReturn.getAddressReturnRecord().iterator(); its.hasNext() && (! groupFound);) {
										AddressReturn address1 = its.next();
										if (address1.getGroupId().equals(address.getGroupId())) {
											groupFound = true;
											if (address1.getPrimaryFlag().equals("N")) {
											throw new Exception("primary should be set to Y but is not ");
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
	public void _36testSetPrimaryChangeForTwoWorkAddress()throws Exception {
		boolean primarySet = false;
		boolean groupFound = false;
		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}
		/** 
		 * Add a work address
		 * 
	 	*/
		ServiceReturn addressServiceReturn = null;
		AddressServiceReturn getAddressReturn = null;
		AddressServiceReturn getAnotherAddressReturn = null;
		AddressServiceReturn getOneMoreAddressReturn = null;
		ServiceReturn setPrimaryAddressReturn = null;
		ServiceReturn setPrimaryAgainAddressReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
			"person_id", "100003", "Work_ADDRESS", null,
			"215 Computer Building", null, null, "University Park", "PA", "16802",
			"USA", "UP", "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} 
		else 
		{
			/**
			 * add another work address
			 */
			addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "Work_ADDRESS", null,
				"3000 Ivyside Park", null, null, "Altoona", "PA", "16601",
				"USA", "AA", "N");
			if (addressServiceReturn.getStatusCode() != 0) {
				throw new Exception(addressServiceReturn.getStatusMessage());
			}
			else
			{
				/**
				 * get the all the addresses - should only be work addresses
				 */
				
				
				getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
						"person_id", "100003", null, "N");
				if (getAddressReturn.getStatusCode() != 0) {
					throw new Exception(getAddressReturn.getStatusMessage());
				}
				else 
				{
					if (getAddressReturn.getNumberElements() != 2) {
						throw new Exception("number of elements not equal 2");
					} 
					else 
					{
						/**
						 * select the first address in the list and set it to primary
						 */
						for (Iterator<AddressReturn> it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext() && (! primarySet);) {
							AddressReturn address = it.next();
							setPrimaryAddressReturn = port.setPrimaryAddressByType(ServiceAuthentication.GOOD_USERID,
									ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003",
									address.getAddressType(), null, address.getGroupId());
							if (setPrimaryAddressReturn.getStatusCode() != 0) {
								throw new Exception(
									setPrimaryAddressReturn.getStatusMessage());
							} 
							else 
							{	primarySet = true;
							/**
							 * get all the addressed and verify that the item set to primary was
							 * set correctly
							 */
								
								getAnotherAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "N");
								if (getAnotherAddressReturn.getStatusCode() != 0) {
									throw new Exception(getAnotherAddressReturn.getStatusMessage());
								}
								if (getAnotherAddressReturn.getNumberElements() != 2) {
									throw new Exception("number of elements not equal 2");
								} 
								else 
								{
									/** 
									 * iterate through the work addresses to find a non-primary address and
									 * set this address to primary
									 */
									
									for (final Iterator<AddressReturn> its = getAnotherAddressReturn.getAddressReturnRecord().iterator(); its.hasNext() && (! groupFound);) {
										AddressReturn address1 = its.next();
										if (address1.getPrimaryFlag().equals("N")) {
											groupFound = true;
											setPrimaryAgainAddressReturn = port.setPrimaryAddressByType(ServiceAuthentication.GOOD_USERID,
													ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003",
													address.getAddressType(), null, address1.getGroupId());
											if (setPrimaryAgainAddressReturn.getStatusCode() != 0) {
												throw new Exception(setPrimaryAgainAddressReturn.getStatusMessage());
											} 
											else 
											{
												getOneMoreAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "N");
												if (getOneMoreAddressReturn.getStatusCode() != 0) {
													throw new Exception(getAnotherAddressReturn.getStatusMessage());
												}
												else 
												{
													if (getOneMoreAddressReturn.getNumberElements() != 2) {
														throw new Exception("number of elements not equal 2");
													} 
													else
													{
														for (final Iterator<AddressReturn> it3 = getOneMoreAddressReturn.getAddressReturnRecord().iterator(); it3.hasNext() && (! groupFound);) {
															AddressReturn address2 = it3.next();
															if (address2.getGroupId().equals(address1.getGroupId())) {
																groupFound = true;
																if (address2.getPrimaryFlag().equals("N")) {
																	throw new Exception("primary should be set to Y but is not ");
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
	public void _37testArchiveWorkAddress()throws Exception {
		boolean groupFound =false;
		try {
			cleanUpAll("100003");
		} catch (Exception e) {
			throw new Exception("GetAddressAll failed");
		}
	/** 
	 * Add a work address
	 * 
 	*/
		ServiceReturn addressServiceReturn = null;
		AddressServiceReturn getAddressReturn = null;
		AddressServiceReturn getAnotherAddressReturn = null;
		AddressServiceReturn getHistoryAddressReturn = null;
		ServiceReturn archiveAddressReturn = null;
		addressServiceReturn = port.addAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
				"person_id", "100003", "Work_ADDRESS", null,
		"215 Computer Building", null, null, "University Park", "PA", "16802",
		"USA", "UP", "N");
		if (addressServiceReturn.getStatusCode() != 0) {
			throw new Exception(addressServiceReturn.getStatusMessage());
		} 
		else 
		{
		
			/**
			 * get the all the addresses - should only be work addresses
			 */
			
			
			getAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID, ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID,
					"person_id", "100003", null, "N");
			if (getAddressReturn.getStatusCode() != 0) {
				throw new Exception(getAddressReturn.getStatusMessage());
			}
			else 
			{
				if (getAddressReturn.getNumberElements() != 1) {
					throw new Exception("number of elements not equal 1");
				} 
				else 
				{
					/**
					 * select the address in the list and archive it
					 */
					for (Iterator<AddressReturn> it = getAddressReturn.getAddressReturnRecord().iterator(); it.hasNext() ;) {
						AddressReturn address = it.next();
						archiveAddressReturn = port.archiveAddress(ServiceAuthentication.GOOD_USERID,
								ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003",
								address.getAddressType(), null, address.getGroupId());
						if (archiveAddressReturn.getStatusCode() != 0) {
							throw new Exception(archiveAddressReturn.getStatusMessage());
						} 
						else 
						{	
						/**
						 * get all the addressed and verify that no items were returned
						 * all archived
						 */
							
							getAnotherAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "N");
							if (getAnotherAddressReturn.getStatusCode() != 0) {
								throw new Exception(getAnotherAddressReturn.getStatusMessage());
							}
							if (getAnotherAddressReturn.getNumberElements() != 0 ){
								throw new Exception("number of elements not equal 0; all archived");
							} 
							else 
							{
								getHistoryAddressReturn = port.getAddress(ServiceAuthentication.GOOD_USERID,ServiceAuthentication.GOOD_PASSWORD, ServiceAuthentication.GOOD_USERID, "person_id", "100003", null, "Y");
								if (getHistoryAddressReturn.getStatusCode() != 0) {
									throw new Exception(getAnotherAddressReturn.getStatusMessage());
								}
								if (getHistoryAddressReturn.getNumberElements() == 0 ){
									throw new Exception("number of elements should not be 0");
								} 
								/** 
								 * iterate through the archived  addresses to find the address just archived
								 */
								
								for (final Iterator<AddressReturn> its = getAnotherAddressReturn.getAddressReturnRecord().iterator(); its.hasNext() && (! groupFound);) {
									AddressReturn address1 = its.next();
									if (!(address1.getGroupId().equals(address.getGroupId())) ) 	
									{
										throw new Exception("archived record not found in list");
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
