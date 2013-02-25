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

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable;
import edu.psu.iam.cpr.core.database.tables.IdCardTable;
import edu.psu.iam.cpr.core.util.Utility;

/**
 * @author cpruser
 *
 */
public class IdCardPrintLogTableTest {
	Date d = new Date();
	String dConverted = Utility.convertTimestampToString((Date) d);
	String wsName= "myWorkStation";
	String ipAddr = "128.118.88.3";
	String thePrinter = "cpruser";
	String idCardNo = "1234567890123456";
	IdCardPrintLogTable icpt = new IdCardPrintLogTable();
	
	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#getEventIdCardNumber()}.
	 */
	@Test
	public final void testGetEventIdCardNumber() {
		icpt.setEventIdCardNumber(idCardNo);
		AssertJUnit.assertEquals(icpt.getEventIdCardNumber(), idCardNo);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#setEventIdCardNumber(java.lang.String)}.
	 */
	@Test
	public final void testSetEventIdCardNumber() {
		icpt.setEventIdCardNumber(idCardNo);
		AssertJUnit.assertEquals(icpt.getEventIdCardNumber(), idCardNo);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#IdCardPrintLogTable()}.
	 */
	@Test
	public final void testIdCardPrintLogTable() {
		IdCardPrintLogTable aNewOne = new IdCardPrintLogTable(idCardNo);
		AssertJUnit.assertNotNull(aNewOne);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#IdCardPrintLogTable(java.lang.String)}.
	 */
	@Test
	public final void testIdCardPrintLogTableString() {
		IdCardPrintLogTable aNewOne = new IdCardPrintLogTable(idCardNo);
		AssertJUnit.assertEquals(aNewOne.getEventIdCardNumber(), idCardNo);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#IdCardPrintLogTable(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Test
	public final void testIdCardPrintLogTableStringStringStringString() {
		IdCardPrintLogTable aNewOne = new IdCardPrintLogTable(idCardNo, thePrinter, ipAddr, wsName);
		AssertJUnit.assertEquals(aNewOne.getEventIdCardNumber(), idCardNo);
		AssertJUnit.assertEquals(aNewOne.getIdCardPrintLogBean().getWorkStationIpAddress(), ipAddr);
		AssertJUnit.assertEquals(aNewOne.getIdCardPrintLogBean().getWorkStationName(), wsName);
		AssertJUnit.assertEquals(aNewOne.getIdCardPrintLogBean().getPrintedBy(), thePrinter);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#addIdCardPrintLog(edu.psu.iam.cpr.core.database.Database)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testAddIdCardPrintLog() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable = new IdCardTable(100003, "ID_CARD_ID_PLUS_CARD_STUDENT", "cpruser", "6543210987654321","12345", new byte [1], new Date());
		try {
			aPICTable.archiveIdCard(db);
		}
		catch (Exception e) {
			
		}
		aPICTable.addIdCard(db);
		IdCardPrintLogTable aLog = new IdCardPrintLogTable("6543210987654321", thePrinter, ipAddr, wsName);
		aLog.addIdCardPrintLog(db);
		db.closeSession();
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#getIdCardPrintLog(edu.psu.iam.cpr.core.database.Database)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetIdCardPrintLog() throws Exception {
		openDbConnection();
		IdCardPrintLogTable aLog = new IdCardPrintLogTable("6543210987654321");
		aLog.getIdCardPrintLog( db );
		
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardPrintLogTable#getIdCardPrintLog(edu.psu.iam.cpr.core.database.Database)}.
	 * @throws Exception 
	 * @throws CprException 
	 */
	@Test
	public final void testGetIdCardPrintLog1() throws Exception {
		openDbConnection();
		IdCardPrintLogTable aLog = new IdCardPrintLogTable("1234567890123456");
		aLog.getIdCardPrintLog( db );
		
		db.closeSession();
	}
}
