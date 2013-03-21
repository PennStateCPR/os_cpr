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
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.Query;
import org.hibernate.Session;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.PersonIdCard;
import edu.psu.iam.cpr.core.database.tables.IdCardTable;
import edu.psu.iam.cpr.core.database.types.IdCardType;
import edu.psu.iam.cpr.core.error.CprException;

/**
 * @author cpruser
 *
 */
public class IdCardTableTest {
	private static Database db = new Database();
	
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#AddressesTable()}.
	 * @throws Exception 
	 */
	@Test
	public final void _01testPersonIdCardTable() throws Exception {
		new IdCardTable();
		openDbConnection();
		String sqlQuery = null;
		Query query = null;
		Session session = db.getSession();
		try {
			sqlQuery = "from PersonIdCard where personId = :person_id";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id", 100004L);
			ArrayList<PersonIdCard> personIdCardList = new ArrayList<PersonIdCard>();
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				personIdCardList.add((PersonIdCard) it.next()); 
			}
			
			for (Iterator<PersonIdCard> it = personIdCardList.iterator(); it.hasNext(); ) {
				PersonIdCard personIdCard = (PersonIdCard) it.next();
				sqlQuery = "delete from IdCardPrintLog where personIdCardKey = :person_id_card_key";
				query = session.createQuery(sqlQuery);
				query.setParameter("person_id_card_key", personIdCard.getPersonIdCardKey());
				query.executeUpdate();
				session.flush();
			}
		}
		catch (Exception e) {
			
		}
		try {
			sqlQuery = "delete from PersonIdCard where personId = :person_id ";
			query = session.createQuery(sqlQuery);
			query.setParameter("person_id",100004L);
			@SuppressWarnings("unused")
			int result = query.executeUpdate();
			session.flush();
		}
		catch (Exception e) {

		}
		finally {
			try {
				db.closeSession();
			}
			catch (Exception e) {
			}
		}
		
		
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#getPersonIdCardBean()}.
	 */
	@Test
	public final void _02testGetPersonIdCardBean() {
		PersonIdCard bean = new PersonIdCard();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setPersonIdCardBean(bean);
		AssertJUnit.assertEquals(aPICTable.getPersonIdCardBean(),bean);
	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#setPersonIdCardBean(edu.psu.iam.cpr.core.database.tables.IdCardTable)}.
	 */
	@Test
	public final void _03testSetPersonIdCardBean() {
		PersonIdCard bean = new PersonIdCard();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setPersonIdCardBean(bean);
		AssertJUnit.assertEquals(aPICTable.getPersonIdCardBean(),bean);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#getIdCardType()}.
	 * @throws Exception 
	 */
	@Test
	public final void _04testGetIdCardType() throws Exception {
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setIdCardType(aPICTable.findIdCardTypeEnum("ID_CARD_ID_PLUS_CARD_STUDENT"));
		AssertJUnit.assertEquals(aPICTable.getIdCardType(), IdCardType.ID_CARD_ID_PLUS_CARD_STUDENT);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#setIdCardType(edu.psu.iam.cpr.core.database.types.IdCardType)}.
	 */
	@Test
	public final void _05testSetIdCardTypeIdCardType() {
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setIdCardType(IdCardType.ID_CARD_ID_PLUS_CARD_AFFILIATE);
		AssertJUnit.assertEquals(aPICTable.getIdCardType(), IdCardType.ID_CARD_ID_PLUS_CARD_AFFILIATE);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#PersonIdCardTable()}.
	 */
	@Test
	public final void _06testIdCardTable() {
		IdCardTable aPICTable = new IdCardTable();
		AssertJUnit.assertNotNull(aPICTable);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#PersonIdCardTable(long, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _07testIdCardTableLongStringString() throws Exception {
		IdCardTable aPICTable = new IdCardTable(1L, "ID_CARD_ID_PLUS_CARD_STUDENT", "cpruser");
		AssertJUnit.assertNotNull(aPICTable);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#PersonIdCardTable(long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _08testIdCardTableLongStringStringStringString() throws Exception {
		IdCardTable aPICTable = new IdCardTable(1L, "ID_CARD_ID_PLUS_CARD_STUDENT", "cpruser", "1234567890123456","12345");
		AssertJUnit.assertNotNull(aPICTable);
	}
	


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#setIdCardType(java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _09testSetIdCardTypeString() throws Exception {
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setIdCardType(aPICTable.findIdCardTypeEnum("ID_CARD_ID_PLUS_CARD_STUDENT"));
		AssertJUnit.assertEquals(aPICTable.getIdCardType(), IdCardType.ID_CARD_ID_PLUS_CARD_STUDENT);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#addIdCard)}.
	 * @throws Exception 
	 * @throws CprException 
	 * */
	
	@Test
	public final void _10testIdCardTableAddIdNoPhoto() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable(100004, "ID_CARD_ID_PLUS_CARD_STUDENT", "cpruser", "1234567890123456","12345");
		aPICTable.addIdCard(db);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#addIdCard)}.
	 * @throws Exception 
	 * @throws CprException 
	 * */
	
//	@Test
//	public final void _11testIdCardTableAddIdNoPhotoAgain() throws Exception {
//		openDbConnection();
//		IdCardTable aPICTable = new IdCardTable(100004, "ID_CARD_STUDENT", "cpruser", "1234567890123456","12345");
//		aPICTable.addIdCard(db);
//		db.closeSession();
//	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#addIdCard)}.
	 * @throws Exception 
	 * @throws CprException 
	 * */
	
	@Test
	public final void _12testIdCardTableAddIdWithPhoto() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable(100004, "ID_CARD_ID_PLUS_CARD_STUDENT", "cpruser", "0123456789012345","12345", new byte [1], new Date());
		aPICTable.addIdCard(db);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#GetIdCardForPersonId)}.
	 * @throws Exception 
	 * */
	
	@Test
	public final void _13testIdCardTableAGetIdCardBeforeArchive() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setReturnHistoryFlag(false);
		aPICTable.getIdCardForPersonId(db, 100004);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#AGetIdCardForPersonId)}.
	 * @throws Exception 
	 * */
	
	@Test
	public final void _14testIdCardTableAGetIdCardHistoryBeforeArchive() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setReturnHistoryFlag(true);
		aPICTable.getIdCardForPersonId(db, 100004);
		db.closeSession();
	}
	@Test
	public final void _15testIdCardTableAGetIdCardByType() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setIdCardType(aPICTable.findIdCardTypeEnum("ID_CARD_ID_PLUS_CARD_student"));
		aPICTable.setReturnHistoryFlag(false);
		aPICTable.getIdCardForPersonId(db, 100004);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#AGetIdCardByTypeForPersonId)}.
	 * @throws Exception 
	 * */
	
	@Test
	public final void _16testIdCardTableAGetIdCardByTypeHistory() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setIdCardType(aPICTable.findIdCardTypeEnum("ID_CARD_ID_PLUS_CARD_student"));
		aPICTable.setReturnHistoryFlag(true);
		aPICTable.getIdCardForPersonId(db, 100004);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#ArchiveIdCard)}.
	 * @throws Exception 
	 * @throws CprException 
	 * @throws AlreadyArchivedException 
	 * */
	
	@Test
	public final void _17testIdCardTableArchiveIdCard() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable(100004, "ID_CARD_ID_PLUS_CARD_STUDENT", "cpruser", "0123456789012345","12345");
		aPICTable.archiveIdCard(db);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#GetIdCardForPersonId)}.
	 * @throws Exception 
	 * */
	
	@Test
	public final void _18testIdCardTableAGetIdCard() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setReturnHistoryFlag(false);
		aPICTable.getIdCardForPersonId(db, 100004);
		db.closeSession();
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#AGetIdCardForPersonId)}.
	 * @throws Exception 
	 * */
	
	@Test
	public final void _19testIdCardTableAGetIdCardHistory() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setReturnHistoryFlag(true);
		aPICTable.getIdCardForPersonId(db, 100004);
		db.closeSession();
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#AGetIdCardByTypeForPersonId)}.
	 * @throws Exception 
	 * */
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.IdCardTable#GetIdCardForPersonId)}.
	 * @throws Exception 
	 * */
	
	@Test
	public final void _20testIdCardTableAGetIdCardNumber() throws Exception {
		openDbConnection();
		IdCardTable aPICTable = new IdCardTable();
		aPICTable.setReturnHistoryFlag(false);
		aPICTable.getIdCardNumberForPersonId(db, 100004);
		db.closeSession();
	}
}
