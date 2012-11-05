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
import edu.psu.iam.cpr.core.util.ValidateIdCard;


/**
 * @author llg5
 *
 */
public class ValidateIdCardTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateIdCard#ValidateAddUpdateIdCard(edu.psu.iam.cpr.core.database.Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws CprException 
	 * @throws Exception 
	 */
	
	private static Database db = new Database();
	public static void openDbConnection() throws Exception {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullIdCardType() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, null, null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardBlankIdCardType() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0,  "", null,  null, null, null, null);
	}		
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNot16CharIdCard() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0,"ID_CARD_STUDENT",  "SYSTEM","1234567890123456", null, null, null);
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNot16DigitIdCard() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM","123456789012345a", null, null, null);
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAdddUpdateIdCardNullIdCardType() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, null, null, null, null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullUpdateBy() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0,"ID_CARD_STUDENT", null, "1234567890123456", null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardBlankUpdateBy() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "",  "1234567890123456", null, null, null);
	}		
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullIdCard() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, null, null);
	}
		
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardPhotoBlankDateTaken() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, null, " ");
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardPhotoBadDateTaken() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, new byte[1], "1/1");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullPhotoDateTaken() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, null, "1/1/2011");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardZeroBytePhotoDateTaken() throws Exception {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, new byte[0], "1/1/2011");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardNullUpdateBy() throws Exception {
		ValidateIdCard.validateGetIdCardParameters(null, 0, null,null,"N");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardBlankUpdateBy() throws Exception {
		ValidateIdCard.validateGetIdCardParameters(null, 0,  "",null,"N");
	}		
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeNullUpdateBy() throws Exception {
		ValidateIdCard.validateGetIdCardParameters(null, 0, null, "ID_CARD_STUDENT",null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeBlankUpdateBy() throws Exception {
		ValidateIdCard.validateGetIdCardParameters(null, 0,  "",  "ID_CARD_STUDENT","N");
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardNullUpdateBy() throws Exception {
		ValidateIdCard.validateArchiveIdCardParameters(null, 0, null, "ID_CARD_STUDENT");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardBlankUpdateBy() throws Exception {
		ValidateIdCard.validateArchiveIdCardParameters(null, 0,  "",  "ID_CARD_STUDENT");
	}		
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardBadIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "fred", "SYSTEM", "1234567890123456",null, null, null);
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardTooLongUpdateBy() throws Exception {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_STUDENT", "asdfghjklpoiuytrewqzxcvbnmasdfgh", "1234567890123456111111", null, null, null);
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardTooLongIdCard() throws Exception {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_STUDENT", "SYSTEM","asdfghjklpoiuytrewqzxcvbnmasdfgh", null, null, null);
		db.closeSession();
	}	
	
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeBadIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "SYSTEM", "fred","n");
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardBadIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0, "SYSTEM", "fred");
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardBadIdTypeUserid() throws Exception {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0,  "fred", "SYSTEM");
		db.closeSession();
	}	
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeTooLongUpdateBy() throws Exception {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "12345678901234567890123456789012345", "ID_CARD_STUDENT","y");
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardTooLongUpdateBy() throws Exception {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0, "asdfghjklpoiuytrewqzxcvbnmasdfgh", "ID_CARD_STUDENT");
		db.closeSession();
	}	
	@Test
	public final void testValidateUpdateIdCardGoodIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_ID_PLUS_CARD_STUDENT", "SYSTEM", "1234567890123456",null,null, null);
		db.closeSession();
	}	
	@Test
	public final void testValidateAddUpdateIdCardGoodIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_ID_PLUS_CARD_STUDENT", "SYSTEM", "1234567890123456",null, null, null);
		db.closeSession();
	}	
	@Test
	public final void testValidateAddUpdateIdCardGoodIdTypeWithPhoto() throws Exception {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_ID_PLUS_CARD_STUDENT", "SYSTEM", "1234567890123456",null, new byte[1], "1/2/2011");
		db.closeSession();
	}	
	@Test
	public final void testValidateGetIdCardGoodIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "SYSTEM",null,"n");
		db.closeSession();
	}	
	@Test
	public final void testValidateGetIdCardByTypeGoodIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "SYSTEM", "ID_CARD_ID_PLUS_CARD_STUDENT","n");
		db.closeSession();
	}	
	@Test
	public final void testValidateArchiveIdCardGoodIdType() throws Exception {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0, "SYSTEM", "ID_CARD_ID_PLUS_CARD_STUDENT");
		db.closeSession();
	}	
}
