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
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.util.ValidateIdCard;


/**
 * @author llg5
 *
 */
public class ValidateIdCardTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.ValidateIdCard#ValidateAddUpdateIdCard(edu.psu.iam.cpr.core.database.Database, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * @throws CprException 
	 * @throws GeneralDatabaseException 
	 */
	
	private static Database db = new Database();
	public static void openDbConnection() throws GeneralDatabaseException {
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullIdCardType() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, null, null, null, null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardBlankIdCardType() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0,  "", null,  null, null, null, null);
	}		
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNot16CharIdCard() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0,"ID_CARD_STUDENT",  "SYSTEM","1234567890123456", null, null, null);
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNot16DigitIdCard() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM","123456789012345a", null, null, null);
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAdddUpdateIdCardNullIdCardType() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, null, null, null, null, null, null);
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0,"ID_CARD_STUDENT", null, "1234567890123456", null, null, null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardBlankUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "",  "1234567890123456", null, null, null);
	}		
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullIdCard() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, null, null);
	}
		
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardPhotoBlankDateTaken() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, null, " ");
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardPhotoBadDateTaken() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, new byte[1], "1/1");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardNullPhotoDateTaken() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, null, "1/1/2011");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardZeroBytePhotoDateTaken() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateAddUpdateIdCardParameters(null, 0, "ID_CARD_STUDENT", "SYSTEM", null, null, new byte[0], "1/1/2011");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardNullUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateGetIdCardParameters(null, 0, null,null,"N");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardBlankUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateGetIdCardParameters(null, 0,  "",null,"N");
	}		
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeNullUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateGetIdCardParameters(null, 0, null, "ID_CARD_STUDENT",null);
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeBlankUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateGetIdCardParameters(null, 0,  "",  "ID_CARD_STUDENT","N");
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardNullUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateArchiveIdCardParameters(null, 0, null, "ID_CARD_STUDENT");
	}
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardBlankUpdateBy() throws GeneralDatabaseException, CprException {
		ValidateIdCard.validateArchiveIdCardParameters(null, 0,  "",  "ID_CARD_STUDENT");
	}		
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardBadIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "fred", "SYSTEM", "1234567890123456",null, null, null);
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardTooLongUpdateBy() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_STUDENT", "asdfghjklpoiuytrewqzxcvbnmasdfgh", "1234567890123456111111", null, null, null);
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateAddUpdateIdCardTooLongIdCard() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_STUDENT", "SYSTEM","asdfghjklpoiuytrewqzxcvbnmasdfgh", null, null, null);
		db.closeSession();
	}	
	
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeBadIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "SYSTEM", "fred","n");
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardBadIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0, "SYSTEM", "fred");
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardBadIdTypeUserid() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0,  "fred", "SYSTEM");
		db.closeSession();
	}	
	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateGetIdCardByTypeTooLongUpdateBy() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "12345678901234567890123456789012345", "ID_CARD_STUDENT","y");
		db.closeSession();
	}	
	@Test(expectedExceptions=Exception.class)
	public final void testValidateArchiveIdCardTooLongUpdateBy() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0, "asdfghjklpoiuytrewqzxcvbnmasdfgh", "ID_CARD_STUDENT");
		db.closeSession();
	}	
	@Test
	public final void testValidateUpdateIdCardGoodIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_ID_PLUS_CARD_STUDENT", "SYSTEM", "1234567890123456",null,null, null);
		db.closeSession();
	}	
	@Test
	public final void testValidateAddUpdateIdCardGoodIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_ID_PLUS_CARD_STUDENT", "SYSTEM", "1234567890123456",null, null, null);
		db.closeSession();
	}	
	@Test
	public final void testValidateAddUpdateIdCardGoodIdTypeWithPhoto() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateAddUpdateIdCardParameters(db, 0, "ID_CARD_ID_PLUS_CARD_STUDENT", "SYSTEM", "1234567890123456",null, new byte[1], "1/2/2011");
		db.closeSession();
	}	
	@Test
	public final void testValidateGetIdCardGoodIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "SYSTEM",null,"n");
		db.closeSession();
	}	
	@Test
	public final void testValidateGetIdCardByTypeGoodIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateGetIdCardParameters(db, 0, "SYSTEM", "ID_CARD_ID_PLUS_CARD_STUDENT","n");
		db.closeSession();
	}	
	@Test
	public final void testValidateArchiveIdCardGoodIdType() throws GeneralDatabaseException, CprException {
		openDbConnection();
		ValidateIdCard.validateArchiveIdCardParameters(db, 0, "SYSTEM", "ID_CARD_ID_PLUS_CARD_STUDENT");
		db.closeSession();
	}	
}
