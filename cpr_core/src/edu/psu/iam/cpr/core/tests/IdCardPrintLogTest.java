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

import edu.psu.iam.cpr.core.database.beans.IdCardPrintLog;

/**
 * @author cpruser
 *
 */
public class IdCardPrintLogTest {

	Date d = new Date();
	String wsName= "myWorkStation";
	String ipAddr = "128.118.88.3";
	String thePrinter = "cpruser";
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#IdCardPrintLog()}.
	 */
	@Test
	public final void testIdCardPrintLog() {
		new IdCardPrintLog();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#getPersonIdCardKey()}.
	 */
	@Test
	public final void testGetPersonIdCardKey() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setPersonIdCardKey(new Long(1));
		AssertJUnit.assertEquals(printLog.getPersonIdCardKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#setPersonIdCardKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetPersonIdCardKey() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setPersonIdCardKey(new Long(1));
		AssertJUnit.assertEquals(printLog.getPersonIdCardKey(), new Long(1));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#getWorkStationName()}.
	 */
	@Test
	public final void testGetWorkStationName() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setWorkStationName(wsName);
		AssertJUnit.assertEquals(printLog.getWorkStationName(), wsName);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#setWorkStationName(java.lang.String)}.
	 */
	@Test
	public final void testSetWorkStationName() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setWorkStationName(wsName);
		AssertJUnit.assertEquals(printLog.getWorkStationName(), wsName);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#getPrintedOn()}.
	 */
	@Test
	public final void testGetPrintedOn() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setPrintedOn(d);
		AssertJUnit.assertEquals(printLog.getPrintedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#setPrintedOn(java.util.Date)}.
	 */
	@Test
	public final void testSetPrintedOn() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setPrintedOn(d);
		AssertJUnit.assertEquals(printLog.getPrintedOn(), d);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#getWorkStationIpAddress()}.
	 */
	@Test
	public final void testGetWorkStationIpAddress() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setWorkStationIpAddress(ipAddr);
		AssertJUnit.assertEquals(printLog.getWorkStationIpAddress(), ipAddr);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#setWorkStationIpAddress(java.lang.String)}.
	 */
	@Test
	public final void testSetWorkStationIpAddress() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setWorkStationIpAddress(ipAddr);
		AssertJUnit.assertEquals(printLog.getWorkStationIpAddress(), ipAddr);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#getPrintedBy()}.
	 */
	@Test
	public final void testGetPrintedBy() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setPrintedBy(thePrinter);
		AssertJUnit.assertEquals(printLog.getPrintedBy(), thePrinter);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#setPrintedBy(java.lang.String)}.
	 */
	@Test
	public final void testSetPrintedBy() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setPrintedBy(thePrinter);
		AssertJUnit.assertEquals(printLog.getPrintedBy(), thePrinter);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#getIdCardPrintLogKey()}.
	 */
	@Test
	public final void testGetIdCardPrintLogKey() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setIdCardPrintLogKey(new Long(2));
		AssertJUnit.assertEquals(printLog.getIdCardPrintLogKey(), new Long(2));
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.beans.IdCardPrintLog#setIdCardPrintLogKey(java.lang.Long)}.
	 */
	@Test
	public final void testSetIdCardPrintLogKey() {
		IdCardPrintLog printLog = new IdCardPrintLog();
		printLog.setIdCardPrintLogKey(new Long(2));
		AssertJUnit.assertEquals(printLog.getIdCardPrintLogKey(), new Long(2));
	}

}
