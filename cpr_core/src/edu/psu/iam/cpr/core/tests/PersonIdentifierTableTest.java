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

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import edu.psu.iam.cpr.core.database.DBTypes;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.IdentifierType;
import edu.psu.iam.cpr.core.database.beans.PersonIdentifier;
import edu.psu.iam.cpr.core.database.tables.PersonIdentifierTable;
import edu.psu.iam.cpr.core.service.returns.PersonIdentifierReturn;

public class PersonIdentifierTableTest {

	private static Database db= new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	@Test
	public final void _01testPersonIdentifierTableLongStringString() {
		new PersonIdentifierTable(1L,"abcd","cpruser");
	}

	@Test
	public final void _02testPersonIdentifierTableLongString() {
		new PersonIdentifierTable(1L,"cpruser");
	}

	@Test
	public final void _03testSetPersonIdentifierBean() {
		PersonIdentifierTable p = new PersonIdentifierTable();
		PersonIdentifier bean = new PersonIdentifier();
		p.setPersonIdentifierBean(bean);
		AssertJUnit.assertEquals(p.getPersonIdentifierBean(),bean);
	}

	@Test
	public final void _04testSetReturnHistoryFlag() {
		PersonIdentifierTable p = new PersonIdentifierTable();
		p.setReturnHistoryFlag(true);
		AssertJUnit.assertTrue(p.isReturnHistoryFlag());
	}

	@Test
	public final void _05testSetIdentifierType() {
		IdentifierType i = new IdentifierType();
		PersonIdentifierTable p = new PersonIdentifierTable();
		p.setIdentifierType(i);
		AssertJUnit.assertEquals(p.getIdentifierType(),i);
	}

	@Test
	public final void _06testGetPersonIdentifiersForPersonId0() throws Exception {
		openDbConnection();
		PersonIdentifierReturn[] p = new PersonIdentifierTable().getPersonIdentifiersForPersonId(db, 1L);
		db.closeSession();
		AssertJUnit.assertEquals(p.length,0);
	}
	
	@Test
	public final void _07testGetPersonIdentifiersForPersonId3() throws Exception {
		openDbConnection();
		PersonIdentifierReturn[] p = new PersonIdentifierTable().getPersonIdentifiersForPersonId(db, 100000L);
		db.closeSession();
		AssertJUnit.assertEquals(p.length,1);
	}
	
	@Test
	public final void _08testGetPersonIdentifiersForPersonId1() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("UNIT_TEST_IDENTIFIER")) {
				break;
			}
		}
		PersonIdentifierTable p = new PersonIdentifierTable();
		p.setIdentifierType(identifierType);
		PersonIdentifierReturn[] pid = p.getPersonIdentifiersForPersonId(db, 100000L);
		db.closeSession();
		AssertJUnit.assertEquals(pid.length,1);

	}
	
	@Test
	public final void _09testGetPersonIdentifiersForPersonId2() throws Exception {
		openDbConnection();
		PersonIdentifierTable p = new PersonIdentifierTable();
		p.setReturnHistoryFlag(true);
		PersonIdentifierReturn[] pid = p.getPersonIdentifiersForPersonId(db, 100000L);		
		db.closeSession();
		AssertJUnit.assertNotNull(pid);
	}

	@Test
	public final void _10testAddPersonIdentifier1() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("UNIT_TEST_IDENTIFIER")) {
				break;
			}
		}
		PersonIdentifierTable p = new PersonIdentifierTable(100000L, "GHIJK", "cpruser");
		p.setIdentifierType(identifierType);
		p.addPersonIdentifier(db);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _11testAddPersonIdentifier2() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("UNIT_TEST_IDENTIFIER")) {
				break;
			}
		}
		PersonIdentifierTable p = new PersonIdentifierTable(100000L, "GHIJK", "cpruser");
		p.setIdentifierType(identifierType);
		p.addPersonIdentifier(db);
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _12testArchivePersonIdentifier1() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("PERSON_ID")) {
				break;
			}
		}
		PersonIdentifierTable p = new PersonIdentifierTable(100000L, "cpruser");
		p.setIdentifierType(identifierType);
		p.archivePersonIdentifier(db);
		db.closeSession();
	}
	
	@Test
	public final void _13testArchivePersonIdentifier2() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("UNIT_TEST_IDENTIFIER")) {
				break;
			}
		}
		PersonIdentifierTable p = new PersonIdentifierTable(100000L, "cpruser");
		p.setIdentifierType(identifierType);
		p.archivePersonIdentifier(db);
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _14testArchivePersonIdentifier3() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("UNIT_TEST_IDENTIFIER")) {
				break;
			}
		}
		PersonIdentifierTable p = new PersonIdentifierTable(100000L, "cpruser");
		p.setIdentifierType(identifierType);
		p.archivePersonIdentifier(db);
		db.closeSession();
	}
	
	@Test 
	public final void _15testSSNStorage() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("SSN")) {
				break;
			}
		}
		
		Random rp = new Random(new Date().getTime());
		int pA = Math.abs(rp.nextInt())%1000;  //0-999
		int pB = Math.abs(rp.nextInt())%100;   //0-99
		int pC = Math.abs(rp.nextInt())%10000; //0-9999
		DecimalFormat fmt2 = new DecimalFormat("00");
		DecimalFormat fmt3 = new DecimalFormat("000");
		DecimalFormat fmt4 = new DecimalFormat("0000");
		String ssn = fmt3.format(pA) + fmt2.format(pB) + fmt4.format(pC);
		
		PersonIdentifierTable p = new PersonIdentifierTable(100000L, ssn, "cpruser");
		p.setIdentifierType(identifierType);
		p.addPersonIdentifier(db);
		p.archivePersonIdentifier(db);
		db.closeSession();
		
	}
	
	@Test
	public final void restoreThings() throws Exception {
		openDbConnection();
		HashMap<String,Object> map = DBTypes.INSTANCE.getTypeMaps(DBTypes.IDENTIFIER_TYPE);
		IdentifierType identifierType = null;
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			identifierType = (IdentifierType) entry.getValue();
			if (identifierType.getTypeName().equals("UNIT_TEST_IDENTIFIER")) {
				break;
			}
		}
		PersonIdentifierTable p = new PersonIdentifierTable(100000L, "TEST_VALUE", "cpruser");
		p.setIdentifierType(identifierType);
		p.addPersonIdentifier(db);
		db.closeSession();
	}


}
