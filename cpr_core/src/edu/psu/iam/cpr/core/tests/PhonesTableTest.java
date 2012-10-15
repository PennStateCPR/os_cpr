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
import org.hibernate.Query;
import org.hibernate.Session;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.PhonesTable;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.service.returns.PhoneReturn;

public class PhonesTableTest {

	private static Database db = new Database();

	public static void openDbConnection() throws GeneralDatabaseException {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#PhoneTable(int,  String, String)}.
	 *
	 */
	@Test
	public final void _01testPhoneTableSetup() throws GeneralDatabaseException {
		new PhonesTable();
		openDbConnection();
		String sqlQuery = null;
		Query query = null;
		Session session = db.getSession();
		sqlQuery = "delete from Phones where personId = :person_id ";
		query = session.createQuery(sqlQuery);
		query.setParameter("person_id",100004L);
		@SuppressWarnings("unused")
		int result = query.executeUpdate();
		session.flush();
		query = session.createQuery(sqlQuery);
		query.setParameter("person_id",100003L);
		@SuppressWarnings("unused")
		int result1 = query.executeUpdate();
		session.flush();
		db.closeSession();

		
	}
	@Test (expectedExceptions=Exception.class)
	public final void _02testPhonesTableUnknownType() throws Exception{
		new PhonesTable(100001, "unknown",null, "8148654846", "1234", "N", "system");
	}

	@Test (expectedExceptions=Exception.class)
	public final void _03testPhonesTableInvalidType() throws Exception{
		new PhonesTable(100001, "employee",null,  "8148654846", "1234", "N", "system");
	}

	@Test (expectedExceptions=Exception.class)
	public final void _04testPhonesTableNullType() throws Exception{
		new PhonesTable(100001, null, null, "8148654846", "1234", "N", "system");
	}
	@Test(expectedExceptions=Exception.class)
	public final void _05testPhonesTableBlankPhoneType() throws Exception{
		new PhonesTable(100001, " ", null, "8148654846", "1234", "N", "system");
	}
	
	@Test
	public final void _06testPhonesTableLocalPhoneType() throws Exception{
		new PhonesTable(100001, "LOCAL_PHONE", null, "8148654846", "1234", "N", "system");
	}

	@Test
	public final void _07testPhonesTablePermanentPhoneType() throws Exception{
		new PhonesTable(100001, "permanent_phone", null, "8148654846", "1234", "N", "system");
	}

	@Test
	public final void _08testPhonesTableRightTrimWorkType() throws Exception{
		new PhonesTable(100001, "    WORK_PHONE", null, "8148654846", "1234", "N", "system");
	}

	@Test
	public final void _09testPhonesTableLeftTrimWorkType() throws Exception{
		new PhonesTable(100001, "WORK_PHONE   ", null,"8148654846", "1234", "N", "system");
	}

	@Test
	public final void _10testPhonesTableBothTrimLocalPhoneType() throws Exception{
		new PhonesTable(100001, "  LOCAL_PHONE   ",null,  "8148654846", "1234", "N", "system");
	}

	@Test(expectedExceptions=Exception.class)
	public final void _11testSetPhoneTypeStringUnknown() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("unknown");
	}

	@Test(expectedExceptions=Exception.class)
	public final void _12testSetPhoneTypeStringInvalid() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("employee");
	}
	@Test(expectedExceptions=Exception.class)
	public final void _13testSetPhoneTypeStringNull() throws Exception{

		PhonesTable phonesTable = new PhonesTable();

		phonesTable.setPhoneType( (String)null );
	}


	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#PhoneTable(int,  String, String)}.
	 */
	@Test
	public final void _14testPhoneLocalPhone() throws Exception {

		PhonesTable phonesTable = new PhonesTable(1, "LOCAL_phone", null, "llg5");
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	} 
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneType(edu.psu.iam.cpr.core.database.types.PhoneType)}.
	 */
	@Test
	public final void _15testSetPhoneTypeIdEnumPermanent() {

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType(PhoneType.PERMANENT_PHONE);
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.PERMANENT_PHONE);
	} 
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneType(edu.psu.iam.cpr.core.database.types.PhoneType)}.
	 */
	@Test
	public final void _16testSetPhoneTypeIdEnumLocal() {

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType(PhoneType.LOCAL_PHONE);
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	} 
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneType(edu.psu.iam.cpr.core.database.types.PhoneType)}.
	 */

	@Test
	public final void _17testSetPhoneTypeIdEnumLocal1() {

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType(PhoneType.LOCAL_PHONE);
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	} 

	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneType(edu.psu.iam.cpr.core.database.types.PhoneType)}.
	 */
	@Test
	public final void _18testSetPhoneTypeIdEnumWork() {

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType(PhoneType.WORK_PHONE);
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.WORK_PHONE);
	} 

	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#getExtension(java.lang.String)}.
	 */


	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneTypeId(java.lang.String)}.
	 */
	@Test
	public final void _19testSetPhoneTypeLowerLocalPhone() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("LOCAL_PHONE");
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneTypeId(java.lang.String)}.
	 */
	@Test
	public final void _20testSetPhoneTypeStringMixedLocalPhone() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("LOCAL_phonE");
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneTypeId(java.lang.String)}.
	 */
	@Test
	public final void _21testSetPhoneTypeStringUpperLocalPhone() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("LOCAL_PHONE");
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneTypeId(java.lang.String)}.
	 */
	@Test
	public final void _22testSetPhoneTypeStringRightTrimLocalPhone() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("    LOCAL_PHONE");
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneTypeId(java.lang.String)}.
	 */
	@Test
	public final void _23testSetPhoneTypeStringLeftTrimLocalPhone() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("LOCAL_PHONE   ");
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPhoneTypeId(java.lang.String)}.
	 */
	@Test
	public final void _24testSetPhoneTypeStringBothTrimLocalPhone() throws Exception{

		PhonesTable phonesTable = new PhonesTable();
		phonesTable.setPhoneType("    LOCAL_PHONE   ");
		AssertJUnit.assertEquals(phonesTable.getPhoneType(), PhoneType.LOCAL_PHONE);
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */

	@Test(expectedExceptions=CprException.class)
	public final void _25testAddPhoneDbNullNoDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
		
		PhonesTable phonesTable = new PhonesTable(1 , "LOCAL_PHONE",null, "8148651818", (String)null, "N", "llg5");
		phonesTable.addPhone(null);
	
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _26testAddPhoneNoPersonNoDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
		
		PhonesTable phonesTable = new PhonesTable(1 , "LOCAL_PHONE", "8148651818", (String)null, "N", "llg5");
		phonesTable.addPhone(db  );
		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */

//	@Test(expected=CprException.class)
//	public final void _27testAddPhoneDbNullDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
//		openDbConnection();
//		PhonesTable phonesTable = new PhonesTable(1 , "LOCAL_PHONE", "8148651818", (String)null, "N", "llg5");
//		phonesTable.addPhone(null);
//		db.closeSession();
//	}
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
//	 */
//	@Test(expected=CprException.class)
//	public final void _28testAddPhoneNoPersonDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
//		openDbConnection();
//		PhonesTable phonesTable = new PhonesTable(1 , "LOCAL_PHONE", "8148651818", (String)null, "N", "llg5");
//		phonesTable.addPhone(db  );
//		db.closeSession();
//	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#archivePhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _29testArchivePhoneNullDbNoDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
		PhonesTable phonesTable = new PhonesTable(1, "LOCAL_PHONE", 1L, "llg5");
		phonesTable.archivePhone(null  );
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#archivePhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _30testArchivePhoneNoPersonNoDbOPen()  throws Exception, CprException, GeneralDatabaseException  {
		PhonesTable phonesTable = new PhonesTable(1, "LOCAL_PHONE", 1L, "llg5");
		phonesTable.archivePhone(db  );
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _31testUpdatePhoneNullDbNoDbOPen()  throws Exception, CprException, GeneralDatabaseException  {
		PhonesTable phonesTable = new PhonesTable(1, "LOCAL_PHONE", 1L, "8148651818", (String)null, "N", "llg5");
		phonesTable.updatePhone(null  );
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test
	public final void _32testAddPhoneAddGoodPhone()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004 , "LOCAL_PHONE",null,  "8148651818", (String)null, "N", "llg5");
		phonesTable.addPhone( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _33testAddPhoneAddDupPhone()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004 , "LOCAL_PHONE",null,  "8148651818", (String)null, "N", "llg5");
		phonesTable.addPhone( db );
		db.closeSession();
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test
	public final void _34testAddPhoneAddDupTypeNewPhone()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004 , "LOCAL_PHONE",null,  "8148654864", (String)null, "N", "llg5");
		phonesTable.addPhone( db );
		db.closeSession();
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test
	public final void _35testAddPhoneAddDupPhoneDiffType()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004 , "WORK_PHONE",null,  "8148654864", (String)null, "N", "llg5");
		phonesTable.addPhone( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _36testUpdatePhoneAddDupPhoneSameType()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004 , "WORK_PHONE",1L,  "8148654864", (String)null, "N", "llg5");
		phonesTable.addPhone( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#UpdatePhone()}.
	 */
	@Test
	public final void _37testUpdatePhoneNonDupPhone()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004 , "WORK_PHONE",1L,   "8148651234", (String)null, "N", "llg5");
		phonesTable.updatePhone( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#ArchivePhone()}.
	 */
	@Test
	public final void _38testArchivePhoneGoodGroupId()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004, "LOCAL_PHONE", 2L, "llg5");
		phonesTable.archivePhone(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#ArchivePhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _39testArchivePhoneAlreadyArchived()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100004, "LOCAL_PHONE", 2L,  "llg5");
		phonesTable.archivePhone(db  );
		db.closeSession();
	}
	

	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _40testGetPhonesNullDbNoDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
		
		PhonesTable p  = new PhonesTable();
		p.setReturnHistoryFlag(false);
		p.getPhones(null, 1);
		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test
	public final void _41testGetPhoneNoPersonNoDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
		
		PhonesTable p  = new PhonesTable();
		p.setReturnHistoryFlag(false);
		p.getPhones(db, 1);
		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test(expectedExceptions=GeneralDatabaseException.class)
	public final void _42testGetPhonesNullDbDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable p  = new PhonesTable();
		p.setReturnHistoryFlag(false);
		p.getPhones(null, 1);
		db.closeSession();
		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test
	public final void _43testGetPhoneNoPersonDbOpen()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable p  = new PhonesTable();
		p.setReturnHistoryFlag(false);
		p.getPhones(db, 1);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test
	public final void _44testAddSomePhoneData()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100003 , "LOCAL_PHONE", "8148651817", (String)null, "N", "llg5");
		phonesTable.addPhone(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test
	public final void _45testUpdatePhoneData()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100003 , "LOCAL_PHONE",1L, "8148651818", (String)null, "N", "llg5");
		phonesTable.updatePhone(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#addPhone()}.
	 */
	@Test
	public final void _46testAddSomeMorePhoneData()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100003 , "LOCAL_PHONE", "8148651234", (String)null, "N", "llg5");
		phonesTable.addPhone(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test
	public final void _47testGetPhonesValid()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable p  = new PhonesTable();
		p.setReturnHistoryFlag(false);
		p.getPhones(db, 100003);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test
	public final void _48testGetPhonesByTypeValid()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable p  = new PhonesTable();
		p.setPhoneType("local_phone");
		p.setReturnHistoryFlag(false);
		p.getPhones(db, 100003);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test
	public final void _49testGetPhonesHistoryValid()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable p  = new PhonesTable();
		p.setReturnHistoryFlag(true);
		p.getPhones(db, 100003);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#updatePhone()}.
	 */
	@Test
	public final void _50testGetPhonesValidNoPhones()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable p  = new PhonesTable();
		p.setReturnHistoryFlag(false);
		PhoneReturn[] phoneReturn  = p.getPhones(db, 100006);
		AssertJUnit.assertEquals(phoneReturn.length, 0	);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPrimaryByType()}.
	 */
	@Test
	public final void _51testPhoneSetPrimary()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100003 , "LOCAL_PHONE", 1L, "llg5");
		phonesTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPrimaryByType()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _52testSetPrimaryAlreadyPrimary()  throws Exception, CprException, GeneralDatabaseException  {
		_51testPhoneSetPrimary();
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100003 , "LOCAL_PHONE", 1L, "llg5");
		phonesTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPrimaryByType()}.
	 */
	@Test
	public final void _53testSetPrimaryNewPrimary()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100003 , "LOCAL_PHONE", 2L, "llg5");
		phonesTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPrimaryByTypePhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _54testSetPrimaryNoRecord()  throws Exception, CprException, GeneralDatabaseException  {
		openDbConnection();
		PhonesTable phonesTable = new PhonesTable(100003 , "PERMANENT_PHONE", 1L, "llg5");
		phonesTable.setPrimaryByType(db  );
		db.closeSession();
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PhonesTables#setPrimaryByTypePhone()}.
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _55testSetPrimaryNoDB()  throws Exception, CprException, GeneralDatabaseException  {
		
		PhonesTable phonesTable = new PhonesTable(100003 , "PERMANENT_PHONE", 1L, "llg5");
		phonesTable.setPrimaryByType(db  );
	
	}
}
