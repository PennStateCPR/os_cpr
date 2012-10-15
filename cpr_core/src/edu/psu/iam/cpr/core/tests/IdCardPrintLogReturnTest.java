/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

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
import java.util.Date;

import edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * @author llg5
 *
 */
public class IdCardPrintLogReturnTest {

	
	Date d = new Date();
	String dConverted = Utility.convertTimestampToString((Date) d);
	String wsName= "myWorkStation";
	String ipAddr = "128.118.88.3";
	String thePrinter = "llg5";
	String idCardNo = "1234567890123456";
	Long personId = new Long(123576546);
	IdCardPrintLogReturn icplr = new IdCardPrintLogReturn ();
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#getPersonId()}.
	 */
	@Test
	public final void testGetPersonId() {
	
		icplr.setPersonId(personId);
		AssertJUnit.assertEquals(icplr.getPersonId(), personId);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#setPersonId(java.lang.String)}.
	 */
	@Test
	public final void testSetPersonId() {
		icplr.setPersonId(personId);
		AssertJUnit.assertEquals(icplr.getPersonId(), personId);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#getIdCardNumber()}.
	 */
	@Test
	public final void testGetIdCardNumber() {
	
		icplr.setIdCardNumber(idCardNo);
		AssertJUnit.assertEquals(icplr.getIdCardNumber(), idCardNo);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#setIdCardNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetIdCardNumber() {
		icplr.setIdCardNumber(idCardNo);
		AssertJUnit.assertEquals(icplr.getIdCardNumber(), idCardNo);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#getWorkStationName()}.
	 */
	@Test
	public final void testGetWorkStationName() {
		icplr.setWorkStationName(wsName);
		AssertJUnit.assertEquals(icplr.getWorkStationName(), wsName);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#setWorkStationName(java.lang.String)}.
	 */
	@Test
	public final void testSetWorkStationName() {
		icplr.setWorkStationName(wsName);
		AssertJUnit.assertEquals(icplr.getWorkStationName(), wsName);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#getIpAddress()}.
	 */
	@Test
	public final void testGetIpAddress() {
		icplr.setIpAddress(ipAddr);
		AssertJUnit.assertEquals(icplr.getIpAddress(), ipAddr);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#setIpAddress(java.lang.String)}.
	 */
	@Test
	public final void testSetIpAddress() {
		icplr.setIpAddress(ipAddr);
		AssertJUnit.assertEquals(icplr.getIpAddress(), ipAddr);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#getPrintDate()}.
	 */
	@Test
	public final void testGetPrintDate() {
		icplr.setPrintDate(dConverted);
		AssertJUnit.assertEquals(icplr.getPrintDate(), dConverted);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#setPrintDate(java.lang.String)}.
	 */
	@Test
	public final void testSetPrintDate() {
		icplr.setPrintDate(dConverted);
		AssertJUnit.assertEquals(icplr.getPrintDate(), dConverted);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#getPrintedBy()}.
	 */
	@Test
	public final void testGetPrintedBy() {
		icplr.setPrintedBy(thePrinter);
		AssertJUnit.assertEquals(icplr.getPrintedBy(), thePrinter);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#setPrintedBy(java.lang.String)}.
	 */
	@Test
	public final void testSetPrintedBy() {
		icplr.setPrintedBy(thePrinter);
		AssertJUnit.assertEquals(icplr.getPrintedBy(), thePrinter);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#IdCardPrintLogReturn()}.
	 */
	@Test
	public final void testIdCardPrintLogReturn() {
		new IdCardPrintLogReturn();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.service.returns.IdCardPrintLogReturn#IdCardPrintLogReturn(java.lang.String,java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIdCardPrintLogReturnStringStringStringStringString() {
		IdCardPrintLogReturn itest = new IdCardPrintLogReturn(personId, idCardNo,wsName, ipAddr, dConverted, thePrinter);
		AssertJUnit.assertEquals(itest.getIdCardNumber(), idCardNo);
		AssertJUnit.assertEquals(itest.getWorkStationName(), wsName);
		AssertJUnit.assertEquals(itest.getIpAddress(), ipAddr);
		AssertJUnit.assertEquals(itest.getPrintDate(), dConverted);
		AssertJUnit.assertEquals(itest.getPrintedBy(), thePrinter);
		
	}

}
