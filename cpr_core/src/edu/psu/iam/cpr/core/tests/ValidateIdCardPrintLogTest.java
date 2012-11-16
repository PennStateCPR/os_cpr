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
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog;

/**
 * @author llg5
 *
 */
public class ValidateIdCardPrintLogTest {

	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParameters() throws Exception {
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters( null, null, null,null, null, null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String,  java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadIdType() throws Exception {
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(null, "FRED", null, null, null, null);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String,  java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersGoodIdTypeNoIdentifier() throws Exception {
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(null, "ID_CARD", null, null,null,  null);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventId() throws Exception {
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(null, "ID_CARD", "1234567890123456",null, "128.118.88.126", "MyWorkStation");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String,  java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventIP() throws Exception {
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(null, "ID_CARD", "1234567890123456","eventId", null, "MyWorkStation");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String,  java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventWorkstation2() throws Exception {
		openDbConnection();
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(db, "ID_CARD", "1234567890123456","eventId", "128.118.88.126",null);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String,  java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventWorkstation() throws Exception {
		openDbConnection();
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(db, "ID_CARD", "1234567890123456","eventId", "128.118.88.126","asdfghjklqwertyuiopzxcvbnmadsfghj");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventIp() throws Exception {
		openDbConnection();
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(db, "ID_CARD", "1234567890123456","eventId", "0000:0000:0000:0000:0000:0000:0000:0000:0000:0000:0000:0000","my workstation");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventIp2() throws Exception {
		openDbConnection();
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(db, "ID_CARD", "1234567890123456","eventId", " ","my workstation");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String,  java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventUser() throws Exception {
		openDbConnection();
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(db, "ID_CARD", "1234567890123456","asdfghjklzxcvbnmqwertyuiopasdfghjk", "0000:0000:0000:0000:0000:0000:0000","my workstation");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateAddIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database, java.lang.String,  java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddIdCardPrintLogParametersBadEventUser2() throws Exception {
		openDbConnection();
		ValidateIdCardPrintLog.validateAddIdCardPrintLogParameters(db, "ID_CARD", "1234567890123456"," ", "0000:0000:0000:0000:0000:0000:0000","my workstation");
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateGetIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database,  java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardPrintLogParameters() throws Exception {
		ValidateIdCardPrintLog.validateGetIdCardPrintLogParameters(null, null, null);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateGetIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database,  java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardPrintLogParametersBadType() throws Exception {
		ValidateIdCardPrintLog.validateGetIdCardPrintLogParameters(null, null,"1234567890123456");
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateGetIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database,  java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardPrintLogParametersBadType2() throws Exception {
		openDbConnection();
		ValidateIdCardPrintLog.validateGetIdCardPrintLogParameters(db, "fred","1234567890123456");
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.validate.ValidateIdCardPrintLog#validateGetIdCardPrintLogParameters(edu.psu.iam.cpr.core.database.Database,  java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardPrintLogParametersBadID() throws Exception {
		
		ValidateIdCardPrintLog.validateGetIdCardPrintLogParameters(null, "ID_CARD",null);
		
	}
	
	
}
