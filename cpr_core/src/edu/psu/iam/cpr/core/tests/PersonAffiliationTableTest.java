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
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

/**
 * 
 */


public class PersonAffiliationTableTest {

	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#AffiliationsTable()}.
	 * @throws GeneralDatabaseException 
	 */
	@Test
	public final void _01testAffiliationsTable() throws GeneralDatabaseException {
		new PersonAffiliationTable();
		openDbConnection();
		String sqlQuery = null;
		Query query = null;
		Session session = db.getSession();
		sqlQuery = "delete from PersonAffiliation  where personId = :person_id ";
		query = session.createQuery(sqlQuery);
		query.setParameter("person_id",100000L);
		@SuppressWarnings("unused")
		int result = query.executeUpdate();
		query = session.createQuery(sqlQuery);
		query.setParameter("person_id",100002L);
		@SuppressWarnings("unused")
		int result1 = query.executeUpdate();
		session.flush();
		db.closeSession();
		
		
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#getPersonId()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _02testAffiliationsTableIntStringStringStringBadAff() throws Exception {
		new PersonAffiliationTable(100000, "STUF_UNDERGRAD_PROSPECT",  null)	;
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _03testAffiliationsTableBadAff() throws Exception {
		new PersonAffiliationTable(100000, "STUF_UNDERGRAD_PROSPECT",  "jvuccolo");
	}
	@Test(expectedExceptions=Exception.class)
	public final void _04testAffiliationsTableBadAffiliation() throws Exception {
		new PersonAffiliationTable(100000, "STUF_UNDERGRAD_PROSPECT",  "jvuccolo","","");
	}
	
	@Test
	public final void _05testAffiliationsTableGoodAffExceptionData() throws Exception {
		new PersonAffiliationTable(100000, "EMPLOYEE_STAFF_ACTIVE",  "jvuccolo","x","x");
	}
	@Test
	public final void _06testAffiliationsTableGoodAddNoExceptionData() throws Exception {
		new PersonAffiliationTable(100000, "EMPLOYEE_STAFF_ACTIVE",  "jvuccolo");
	}
	@Test 
	public final void _07testGetAffilationsBean() {
		PersonAffiliation bean = new PersonAffiliation();
		PersonAffiliationTable t =new PersonAffiliationTable();
		t.setPersonAffiliationBean(bean);
		AssertJUnit.assertEquals(t.getPersonAffiliationBean(),bean);
		
	}
	@Test(expectedExceptions=Exception.class)
	public final void _08testGetAffiliationType() throws Exception {
		PersonAffiliationTable t =new PersonAffiliationTable();
		t.setAffiliationsType("");
	}
	@Test
	public final void _09testGetAffiliationType1() throws Exception {
		PersonAffiliationTable t =new PersonAffiliationTable();
		t.setAffiliationsType(AffiliationsType.EMPLOYEE_STAFF_ACTIVE);
	}
	
	
	@Test(expectedExceptions=CprException.class)
	public final void _10testSetPrimaryAffiliationRequestorNull() throws Exception {
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_PROSPECT", null)	;
		pATable.setPrimaryAffiliation(db);
	}
	
	@Test(expectedExceptions=CprException.class)
	public final void _11testAddAffiliationExceptionNoDb() throws Exception {
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_PROSPECT", "jvuccolo","N", "")	;
		pATable.addAffiliation(db);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#addAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _12testAddAffiliationNoExistingAffiliations() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_PROSPECT",  "llg5", "N", "")	;
		pATable.addAffiliation(db);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#addAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _13testAddAffiliationNewTypeAffiliation() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "EMPLOYEE_STAFF_ACTIVE",  "llg5", "N", "")	;
		pATable.addAffiliation(db);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#addAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _14testAddAffiliationValidTransition() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_APPLICANT",  "llg5", "N", "")	;
		pATable.addAffiliation(db);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#updateAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _15testUpdateAffiliationValidTransition() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_CURRENT",   "llg5","N", "")	;
		pATable.updateAffiliation(db);
		db.closeSession();

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#addAffiliation()}.
	 * @throws Exception 
	 */
//	@Test(expected=CprException.class)
//	public final void _16testAddAffiliationInValidTransition() throws Exception {
//		openDbConnection();
//		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_APPLICANT",  "llg5", "N", "")	;
//		pATable.addAffiliation(db);
//		db.closeSession();
//	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#updateAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _17testUpdateAffiliationTreatedAsAdd() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_GRADUATE_CURRENT",   "llg5","N", "")	;
		pATable.updateAffiliation(db);
		db.closeSession();

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#addAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _18testAddAffiliationNoExceptionData() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "STUDENT_UNDERGRADUATE_PROSPECT",  "llg5")	;
		pATable.addAffiliation(db);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#addAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _19testAddAffiliationAddTreatAsUpdate() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "STUDENT_UNDERGRADUATE_PROSPECT",  "llg5","N", "")	;
		pATable.addAffiliation(db);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#archiveAffiliation()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _20testArchiveAffiliationNoDb() throws Exception {
	
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "STUDENT_UNDERGRADUATE_PROSPECT",  "llg5")	;
		pATable.archiveAffiliation(db);
		

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#archiveAffiliation()}.
	 * @throws Exception 
	 */
	@Test
	public final void _21testArchiveAffiliation() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "STUDENT_UNDERGRADUATE_PROSPECT",  "llg5")	;
		pATable.archiveAffiliation(db);
		db.closeSession();

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#archiveAffiliation()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _22testDeleteAffiliationNoAffiliation() throws Exception {

		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "STUDENT_GRADUATE_PROSPECT",  "llg5")	;
		pATable.archiveAffiliation(db);
		db.closeSession();

	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#archiveAffiliation()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=CprException.class)
	public final void _23testDeleteAffiliationAlreadyArchived() throws Exception {

		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "STUDENT_UNDERGRADUATE_PROSPECT",  "llg5")	;
		pATable.archiveAffiliation(db);
		db.closeSession();

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#updateAffiliation()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=IllegalArgumentException.class)
	public final void _24testDeleteAffiliationGoodSetup() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000 , "STUDENT_UNDERGRADUATE_STUDENT",  "llg5","N","")	;
		pATable.archiveAffiliation(db);
		db.closeSession();

	}
	
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.PersonAffiliationTable#addAffiliation()}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=IllegalArgumentException.class)
	public final void _25testAddAffiliationTreatAsAddBadModifier() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "STUDENT_UNDERGRADUATE_STUDENT",   "llg5")	;
		pATable.addAffiliation(db);
		db.closeSession();

	}
	

	@Test
	public final void exceptionCleanUp1() throws GeneralDatabaseException {
		openDbConnection();
		db.closeSession();
	}

	
//	@Test(expectedExceptions=GeneralDatabaseException.class)
//	public final void _26testGetInternalAffiliationNoDb() throws GeneralDatabaseException, CprException {
//		PersonAffiliationTable a = new PersonAffiliationTable();
//		a.setReturnHistoryFlag(false);
//		a.getInternalAffiliationsForPersonId(db, 100000);
//
//	}
	@Test
	public final void _27testGetInternalAffiliationForPersonIdInactive() throws Exception {
		openDbConnection();	
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.setReturnHistoryFlag(false);
		a.getInternalAffiliationsForPersonId(db, 100000);
		db.closeSession();

	}
	@Test
	public final void _28testGetInternalAffiliationForPersonIdInactive1() throws Exception {
		openDbConnection();	
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.setReturnHistoryFlag(false);
		a.getInternalAffiliationsForPersonId(db, 100001);
		db.closeSession();

	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _29testGetExternalAffiliationNoDb() throws GeneralDatabaseException, CprException {
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.getExternalAffiliationsForPersonId(db, 100010);

	}
	@Test
	public final void _30testGetExternalAffiliationForPersonIdInactive() throws Exception {
		openDbConnection();	
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.getExternalAffiliationsForPersonId(db, 100001);
		db.closeSession();

	}
	
//	@Test
//	public final void _31testGetExternalAffiliationForPersonId() throws Exception {
//		openDbConnection();	
//		PersonAffiliationTable a = new PersonAffiliationTable();
//		a.getExternalAffiliationsForPersonId(db, 100000);
//		db.closeSession();
//
//	}
	
//	@Test(expected=Exception.class)
//	public final void _32testGetAllAffiliationNoDb() throws GeneralDatabaseException, CprException {
//		PersonAffiliationTable a = new PersonAffiliationTable();
//		a.getAllAffiliationsForPersonId(db, 100000, false);
//
//	}
	@Test
	public final void _33testGetAllAffiliationForPersonIdInactive() throws Exception {
		openDbConnection();	
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.setReturnHistoryFlag(false);
		a.getAllAffiliationsForPersonId(db, 100000);
		db.closeSession();

	}
	@Test
	public final void _34testGetAllAffiliationForPersonIdInactive1() throws Exception {
		openDbConnection();	
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.setReturnHistoryFlag(false);
		a.getAllAffiliationsForPersonId(db, 100001);
		db.closeSession();

	}
	@Test
	public final void _35testGetAllAffiliationForPersonId() throws Exception {
		openDbConnection();	
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.setReturnHistoryFlag(false);
		a.getAllAffiliationsForPersonId(db, 100000);
		db.closeSession();

	}
	@Test
	public final void _36testGetAllAffiliationForPersonIdHistory() throws Exception {
		openDbConnection();	
		PersonAffiliationTable a = new PersonAffiliationTable();
		a.setReturnHistoryFlag(true);
		a.getAllAffiliationsForPersonId(db, 100000);
		db.closeSession();

	}
	
	@Test(expectedExceptions=IllegalArgumentException.class)
	public final void _37testSetPrimaryAffiliationBadAffiliation() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_STUDENT",  "llg5")	;
		pATable.setPrimaryAffiliation(db);
		db.closeSession();

	}
	@Test(expectedExceptions=CprException.class)
	public final void _38testSetPrimaryAffiliationNoAff() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100002, "EMPLOYEE_STAFF_ACTIVE",  "llg5")	;
		pATable.setPrimaryAffiliation(db);
		db.closeSession();

	}
	
	@Test
	public final void _39testSetPrimaryAffiliationGood() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "EMPLOYEE_STAFF_ACTIVE",  "llg5","N","")	;
		pATable.setPrimaryAffiliation(db);
		db.closeSession();

	}
	@Test(expectedExceptions=CprException.class)
	public final void _40testSetPrimaryAffiliationAlreadyPrimary() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "EMPLOYEE_STAFF_ACTIVE",  "llg5","N","")	;
		pATable.setPrimaryAffiliation(db);
		db.closeSession();

	}
	@Test
	
	
	public final void _41testSetPrimaryAffiliationNewPrimary() throws Exception {
		openDbConnection();
		PersonAffiliationTable pATable = new PersonAffiliationTable(100000, "STUDENT_UNDERGRADUATE_CURRENT",  "llg5","N","")	;
		pATable.setPrimaryAffiliation(db);
		db.closeSession();

	}
	@Test
	public final void exceptionCleanUp() throws GeneralDatabaseException {
		openDbConnection();
		db.closeSession();
	}
	

}
