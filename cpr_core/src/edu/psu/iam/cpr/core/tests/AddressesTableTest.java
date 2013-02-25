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
import org.hibernate.Query;
import org.hibernate.Session;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.database.types.AddressType;

/**
 * @author cpruser
 *
 */
public class AddressesTableTest {

	private static Database db = new Database();
	
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#AddressesTable()}.
	 * @throws Exception 
	 */
	@Test
	public final void _01testAddressesTable() throws Exception {
		new AddressesTable();
		openDbConnection();
		String sqlQuery = null;
		Query query = null;
		Session session = db.getSession();
		sqlQuery = "delete from Addresses where personId = :person_id ";
		query = session.createQuery(sqlQuery);
		query.setParameter("person_id",100004L);
		@SuppressWarnings("unused")
		int result = query.executeUpdate();
		session.flush();
		db.closeSession();
		
		
	}
	
	@Test
	public final void _02testGetCampus() {
		AddressesTable t = new AddressesTable();
		t.setCampusName("UP");
		AssertJUnit.assertEquals(t.getCampusName(),"UP");
	}
	
	@Test
	public final void _03testGetCountry() {
		AddressesTable t = new AddressesTable();
		t.setCountryName("UP");
		AssertJUnit.assertEquals(t.getCountryName(),"UP");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#AddressesTable(int,  java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _04testAddressesTable51Parms() throws Exception {
		AddressesTable aTable = new AddressesTable(100000, "LOCAL_ADDRESS", null, 1L, "updatedby");
		AssertJUnit.assertEquals(aTable.getAddressesBean().getPersonId(), new Long(100000));

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#AddressesTable(int,  java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _05testAddressesTable52Parms() throws Exception {
		AddressesTable aTable = new AddressesTable(100000, "LOCAL_ADDRESS",  null, 1L, "updatedby");
		AssertJUnit.assertEquals(aTable.getAddressType(), AddressType.LOCAL_ADDRESS);

	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#AddressesTable(int,  java.lang.String, java.lang.String)}.
	 * @throws Exception 
	 */
	@Test
	public final void _06testAddressesTable53Parms() throws Exception {
		AddressesTable aTable = new AddressesTable(100000, "LOCAL_ADDRESS",  null,1L, "updatedby");
		AssertJUnit.assertEquals(null, aTable.getAddressesBean().getLastUpdateBy(),"updatedby");
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _07testAddressesTableFields() throws Exception {
		new AddressesTable(0, null,null, null, null,
				null, null,null, null, null, null, null,  null, null,"",null, "");

	}


	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#getPersonId()}.
	 */
	@Test
	public final void _08testGetPersonId() throws Exception {
		AddressesTable aTable = new AddressesTable(100001, "LOCAL_ADDRESS", null, null, null, null, null, null, null, null,null, null,  null,null,"", null,"");
		AssertJUnit.assertEquals(aTable.getAddressesBean().getPersonId(), new Long(100001));

	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#getAddress1()}.
	 */
	@Test
	public final void _09testGetAddress1() throws Exception {
		AddressesTable aTable = new AddressesTable(100001, "WORK_ADDRESS",null, null,   null,  "address1","address2","address3", "city", "PA", "postalcode",  null,1L, 1L,"","", null);
		AssertJUnit.assertEquals(aTable.getAddressesBean().getAddress1(),"address1");
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#getVerifiedFlag()}.
	 */
	@Test
	public final void _10testGetVerifiedFlag() throws Exception {
		AddressesTable aTable = new AddressesTable(100001, "WORK_ADDRESS",null, null,   null,  "address1","address2","address3", "city", "PA", "postalcode",  null,1L, 1L,"","",null);
		AssertJUnit.assertEquals(aTable.getAddressesBean().getVerifiedFlag(),"N");
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#getAddressType()}.
	 * @throws Exception 
	 */
	@Test
	public final void _11testGetAddressTypeENUM() throws Exception {
		AddressesTable aTable  = new AddressesTable();
		aTable.setAddressType(AddressType.WORK_ADDRESS);
		AssertJUnit.assertEquals( aTable.getAddressType(), AddressType.WORK_ADDRESS);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#getAddressType()}.
	 * @throws Exception 
	 */
	@Test
	public final void _12testGetAddressType() throws Exception {
		AddressesTable aTable  = new AddressesTable();
		aTable.setAddressType(aTable.findAddressTypeEnum("WORK_ADDRESS"));
		AssertJUnit.assertEquals( aTable.getAddressType(), AddressType.WORK_ADDRESS);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#setAddressType(edu.psu.iam.cpr.core.database.types.AddressType)}.
	 * @throws Exception 
	 */
	@Test
	public final void _13testSetAddressType() throws Exception {
		AddressesTable aTable  = new AddressesTable();
		aTable.setAddressType(aTable.findAddressTypeEnum("LOCAL_ADDRESS"));
		AssertJUnit.assertEquals( aTable.getAddressType(), AddressType.LOCAL_ADDRESS);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTable#setAddressType(edu.psu.iam.cpr.core.database.types.AddressType)}.
	 * @throws Exception 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _14testSetAddressType1() throws Exception {
		AddressesTable aTable  = new AddressesTable();
		aTable.setAddressType(aTable.findAddressTypeEnum("EMPLOYEE"));

	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _15testAddAddressDbNullNoDbOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345","USA", null, 100048L,"","up","USA" );
		aTable.addAddress( null );		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _16testAddAddressNoPersonNoDbOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
		aTable.addAddress( db );		
	}
	
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#getAddressByType()}.
//	 */
//	@Test(expected=Exception.class)
//	public final void _17testGetAddressByTypeBadType()  throws Exception  {
//		openDbConnection();
//		AddressesTable aTable = new AddressesTable();
//		aTable.setReturnHistoryFlag(false);
//		aTable.setAddressType("work");
//		aTable.getAddress(db, 100004);
//		
//		db.closeSession();
//	}
//	
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
//	 * 
//	 */
//	@Test(expected=Exception.class)
//	public final void _18testAddAddressDbNullDbOpen() throws Exception {
//		openDbConnection();
//		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up","USA");
//		aTable.addAddress( null );	
//		db.closeSession();
//		
//	}
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
//	 * 
//	 */
//	@Test(expected=Exception.class)
//	public final void _19testAddAddressNoPersonDbOpen() throws Exception {
//		try {
//			openDbConnection();
//			AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
//			aTable.addAddress( db );
//		}
//		catch (Exception e) {
//			throw new Exception(e);
//		}
//		finally {
//			try {
//				db.closeSession();
//			}
//			catch (Exception e) {
//
//			}
//		}
//	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _20testArchiveAddressDbNullDbNotOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up","USA");
		aTable.archiveAddress( null );		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _21testArchiveAddressNoPersonDbNotOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
		aTable.archiveAddress( db );		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
//	@Test(expected=Exception.class)
//	public final void _22testArchiveAddressNoDbDbOpen() throws Exception {
//		try {
//			openDbConnection();
//			AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up","USA");
//			aTable.archiveAddress( null );	
//		} catch (Exception e) {
//			throw new Exception(e);
//		}
//		finally {
//			try {
//				db.closeSession();
//			}
//			catch (Exception e) {}
//
//		}
//
//	}
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
//	 * 
//	 */
//	@Test(expected=Exception.class)
//	public final void _23testArchiveAddressNoPersonDbOpen() throws Exception {
//		try {
//			openDbConnection();
//			AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
//			aTable.archiveAddress( db );
//		} 
//		catch (Exception e) {
//			throw new Exception(e);
//		}
//		finally {
//			try {
//				db.closeSession();				
//			}
//			catch (Exception e) {}
//		}
//	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _24testGetAddressDbNullDbNotOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
		aTable.setReturnHistoryFlag(false);
		aTable.getAddress( null, 1);		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _25testGetAddressNoPersonDbNotOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up","USA");
		aTable.setReturnHistoryFlag(false);
		aTable.getAddress( db, 1);		
	}
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
//	 * 
//	 */
//	@Test(expected=Exception.class)
//	public final void _26testGetAddressNoDbDbOpen() throws Exception {
//		try {
//			openDbConnection();
//			AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
//			aTable.setReturnHistoryFlag(false);
//			aTable.getAddress( null, 1);
//		}
//		catch (Exception e) {
//			throw new Exception(e);
//		}
//		finally {
//			try {
//				db.closeSession();
//			}
//			catch (Exception e) { 
//
//			}
//		}
//		
//	}
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
//	 * 
//	 */
//	@Test
//	public final void _27testGetAddressNoPersonDbOpen() throws Exception {
//		openDbConnection();
//		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
//		aTable.setReturnHistoryFlag(false);
//		aTable.getAddress( db, 1 );
//		db.closeSession();
//	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _28testUpdateAddressDbNullDbNotOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up","USA");
		aTable.updateAddress( null );		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _29testUpdateAddressNoPersonDbNotOpen() throws Exception {
		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
		aTable.updateAddress( db );		
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * 
	 */
//	@Test(expected=Exception.class)
//	public final void _30testUpdateAddressNoDbDbOpen() throws Exception {
//		openDbConnection();
//		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
//		aTable.updateAddress( null );	
//		db.closeSession();
//		
//	}
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
//	 * 
//	 */
//	@Test(expected=Exception.class)
//	public final void _31testUpdateAddressNoPersonDbOpen() throws Exception {
//		openDbConnection();
//		AddressesTable aTable = new AddressesTable( 1 , "LOCAL_ADDRESS", null, null,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null, null, 100048L,"","up", "USA");
//		aTable.updateAddress( db );
//		db.closeSession();
//	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _32testAddAddressNewType()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",  null, null, "cpruser", "123 Church St", null, null, "Boalsburg", "PA", "16827",null,100069L, null,"usa", null,"USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _33testAddAddressDupExceptPostal()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",  null, null, "cpruser", "123 Church St", null, null, "Boalsburg", "PA", "16828",null, 100069L, null,"usa", null,"USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test(expectedExceptions=Exception.class)	
	public final void _34testAddAddressDup()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",  null, null, "cpruser", "123 Church St", null, null, "Boalsburg", "PA", "16827",null,100069L, null,"usa", null,"USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _35testAddAddressGood()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "PASSPORT", null,"cpruser", "address1", "address2", "address3", "city", "PA", "12345",null,100069L, 100048L,"usa", "university park","USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _36testAddAddressGoodNONUSA()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",  null, null,"cpruser", "canaddress1", "can address2", "can address3", "Toronto", null, "1234522","Ontario",100040L, null,"can", null, "USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _37testAddAddressGoodNoStateNoProvinve() throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",  null, null,"cpruser", "canaddress1", "fra address2", "fra address3", "Nice", null, "1234522",null,100075L, null,"fra", null, "FRA");
		aTable.addAddress( db );
		db.closeSession();
	}
	@Test
	public final void _38testGetAddressGoodAfterAdd()  throws Exception  {
		openDbConnection();
		AddressesTable a = new AddressesTable();
		a.setReturnHistoryFlag(false);
		a.getAddress(db, 100004);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _39testGetByTypeAfterAdd() throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "WORK_ADDRESS",  null, null,"cpruser", "canaddress1", "fra address2", "fra address3", "Nice", null, "1234522",null,100075L, null,"fra", null, "FRA");
		aTable.addAddress(db);
		aTable.setReturnHistoryFlag(false);
		aTable.setAddressType(aTable.findAddressTypeEnum("work_address"));
		aTable.getAddress(db, 100004);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _40testGetByTypeDocumented() throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable();
		aTable.setReturnHistoryFlag(false);
		aTable.setAddressType(aTable.findAddressTypeEnum("documented_address"));
		aTable.getAddress(db, 100004);
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _41testAddAddressDuplicateType()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "PASSPORT", null, "cpruser", "address1", null, null, "My Town", "PA", "12345",null,100069L, 100048L,"usa", "university park", "USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _42testAddAddressDuplicateAddressDataWithinType()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "STATE_DRIVERS_LICENCE", null, "cpruser", "address1", "address2", "address3", "city", "PA", "12345",null,100069L, 100048L,"usa","USA", "university park");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _43testAddAddressDuplicateAddressDataDifferentType()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "BILLING_ACADEMIC_ADDRESS",  null, null,"cpruser", "address1", "address2", "address3", "city", "PA", "12345",null,100069L, 100048L,"usa", "university park", "USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#updateAddress()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _44testUpdateAddressDuplicateAddressData()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "BILLING_ACADEMIC_ADDRESS",  null, 1L,"cpruser", "address1", "address2", "address3", "city", "PA", "12345",null,100069L, 100048L,"usa", "university park", "USA");
		aTable.updateAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#updateAddress()}.
	 */
	@Test
	public final void _45testUpdateAddressNewAddress()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "BILLING_ACADEMIC_ADDRESS", null, 1L, "cpruser", "a bill address1", "address2", "address3", "city", "PA", "12345",null,100069L, 100048L,"usa", "university park", "USA");
		aTable.updateAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#updateAddress()}.
	 */
	@Test
	public final void _46testUpdateAddressGood()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "PASSPORT", 1L, "cpruser", "New address1", " New address21", "address31", "city", "PA", "12345",null, 100069L, 100048L,"usa", "university park", "USA");
		aTable.updateAddress(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#updateAddress()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _47testUpdateAddressGoodAgain()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "PASSPORT", 1L,  "cpruser", "New address1", " New address21", "address31", "city", "PA", "12345",null, 100069L, 100048L,"usa", "university park","USA");
		aTable.updateAddress(db  );
		db.closeSession();
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#updateAddress()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _48testUpdateAddressDupAddrDiffDocTyep()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "STATE_DRIVERS_LICENSE", 1L,  "cpruser", "New address1", " New address21", "address31", "city", "PA", "12345",null, 100069L, 100048L,"usa", "university park", "USA");
		aTable.updateAddress(db  );
		db.closeSession();
	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#updateAddress()}.
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _49testUpdateAddressDiffAddrDiffDocType()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "STATE_DRIVERS_LICENSE", 1L,  "cpruser", "New address1 again", " New address21", "address31", "city", "PA", "12345",null, 100069L, 100048L,"usa", "university park", "USA");
		aTable.updateAddress(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#archiveAddress()}.
	 * Fails type not found for person id
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _50testArchiveNoAddressOfType() throws Exception {
		AddressesTable aTable = new AddressesTable(100004 , "LOCAL_ADDRESS", null, 1L, "cpruser");
		aTable.archiveAddress(db);
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#ArchiveAddress()}.
	 * Valid type and invalid group id for person id
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _51testArchiveAddressGoodTypeInvalidGroupId()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS", null, 10L,  "cpruser");
		aTable.archiveAddress(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#ArchiveAddress()}.
	 * Valid type and invalid group id for person id
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _52testArchiveAddressNoType()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "BILLING_ADMINISTRATIVE_ADDRESS", null, 10L,  "cpruser");
		aTable.archiveAddress(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#ArchiveAddress()}.
	 * Valid type and invalid group id for person id
	 */
	@Test
	public final void _53testArchiveAddressNotDocumented()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "BILLING_ACADEMIC_ADDRESS", null, 1L,  "cpruser");
		aTable.archiveAddress(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#ArchiveAddress()}.
	 * Valid type and invalid group id for person id
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _54testArchiveAddressAlreadyNotDocumented()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "BILLING_ACADEMIC__ADDRESS", null, 1L,  "cpruser");
		aTable.archiveAddress(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#ArchiveAddress()}.
	 * Valid type and group id for person id
	 */
	@Test
	public final void _55testArchiveAddressGoodTypeGroupId()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS", "PASSPORT", 1L,  "cpruser");
		aTable.archiveAddress(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#ArchiveAddress()}.
	 * fails already archived
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _56testArchiveAddressAlreadyArchived()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS","PASSPORT", 1L,   "cpruser");
		aTable.archiveAddress(db  );
		db.closeSession();
	}
	

	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _57testSetPrimaryAddressByTypeNoType()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "LOCAL_ADDRESS",null, 1L,   "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _58testSetPrimaryAddressByTypeNoGroupID()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",null, 5L,   "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test
	public final void _59testSetPrimaryAddressByTypeValid()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",null, 1L,   "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _60testSetPrimaryAddressByTypeAlreadyPrimary()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",null, 1L,   "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test
	public final void _61testSetPrimaryAddressByTypeNewPrimary()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "PERMANENT_ADDRESS",null, 2L,   "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _62testSetup1ForSetPrimary()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "PASSPORT", null, "cpruser", "123 Church St", null, null, "Boalsburg", "PA", "16827",null,100069L, null,"usa", null, "USA");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#addAddress()}.
	 */
	@Test
	public final void _63testSetup2ForSetPrimary()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS",  "MILITARY_ID", null,"cpruser", "canaddress1", "can address2", "can address3", "Toronto", null, "1234522","Ontario",100040L, null,"can", null, "can");
		aTable.addAddress( db );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test
	public final void _64testSetPrimaryAddressByTypeNewPrimaryDocumented()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS", "PASSPORT" , 2L, "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test(expectedExceptions=Exception.class)
	public final void _65testSetPrimaryAddressByTypeNewPrimaryDocumentedAlreadyPrimary()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS","PASSPORT",  2L,   "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#setPrimaryAddressByTypeAddress()}.
	 * fails already archived
	 */
	@Test
	public final void _66testSetPrimaryAddressByTypeNewPrimaryDocumentedNew()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "DOCUMENTED_ADDRESS", "MILITARY_ID", 3L,   "cpruser");
		aTable.setPrimaryByType(db  );
		db.closeSession();
	}
	@Test
	public final void _67testGetAddressNoPersonID()  throws Exception  {
		openDbConnection();
		AddressesTable a = new AddressesTable();
		a.setReturnHistoryFlag(false);
		a.getAddress(db, 1);
		db.closeSession();
	}
	@Test
	public final void _68testGetAddressNo()  throws Exception  {
		openDbConnection();
		AddressesTable a = new AddressesTable();
		a.setReturnHistoryFlag(false);
		a.getAddress(db, 1);
		db.closeSession();
	}
	@Test
	public final void _69testGetAddressGood()  throws Exception  {
		openDbConnection();
		AddressesTable a = new AddressesTable();
		a.setReturnHistoryFlag(false);
		a.getAddress(db, 100004);
		db.closeSession();
	}
	
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#getMatchAddress()}.
//	 */
//	@Test
//	public final void _70testgetMatchAddress()  throws Exception  {
//		openDbConnection();
//		AddressesTable aTable = new AddressesTable(100004 , "LOCAL_ADDRESS",  null,1L,  "cpruser", "address1", "address2", "address3", "city", "PA", "12345", null,null, 100057L,"","yk","USA");
//		AssertJUnit.assertEquals(aTable.getMatchAddress(), "address1 address2 address3");
//		db.closeSession();
//	}
//	/*
//	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#getMatchCity()}.
//	 */
//	@Test
//	public final void _71testgetMatchCity()  throws Exception  {
//		openDbConnection();
//		AddressesTable aTable = new AddressesTable(100004 , "LOCAL_ADDRESS",  null, 1L, "cpruser", "address1", "address21", "address31", "city", "PA", "12345", null,null, 100056L,"","xw", "USA");
//		AssertJUnit.assertEquals(aTable.getMatchCity(), "city");
//		db.closeSession();
//	}
	
	/*
	 * Test method for {@link edu.psu.iam.cpr.core.database.tables.AddressesTables#getMatchCity()}.
	 */
	@Test
	public final void _72testAddAddress()  throws Exception  {
		openDbConnection();
		AddressesTable aTable = new AddressesTable(100004 , "WORK_ADDRESS",   null,  "cpruser", "215D Computer Bldg", null, null, "University Park", "PA", "16802", null,100069L, null,"United States", null, "USA");
		aTable.addAddress(db);
		AddressesTable updateTable = new AddressesTable(100004 , "WORK_ADDRESS",   null,  1L, "cpruser", "Computer Bldg, 215D", null, null, "University Park", "PA", "16802", null,100069L, 100048L,"United States", "University Park", "USA");
		updateTable.updateAddress(db);
		db.closeSession();
	}
	
	
	
	
}

