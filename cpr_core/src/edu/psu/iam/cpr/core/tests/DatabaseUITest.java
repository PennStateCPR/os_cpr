package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.util.ArrayList;
import java.util.Date;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.SecurityQuestionAnswers;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.ui.DatabaseUI;

public class DatabaseUITest {
	
	private static DatabaseUI db = new DatabaseUI();
	
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	
	@Test
	public final void testGetStateList() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getStateList());
		db.closeSession();
	}
	
	@Test
	public final void testGetCountryList() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getCountryList());
		db.closeSession();
	}
	
	@Test
	public final void testGetGenderList() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getGenderList());
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetTypeListBadType() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getTypeList(1L));
		db.closeSession();
	}
	
	@Test
	public final void testGetTypeList() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getTypeList(AccessType.EMAIL.index()));
		db.closeSession();
	}
	
	@Test
	public final void testGetSecurityQuestionList() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getSecurityQuestionList(1));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testSetUserSecurityQuestionAnswersException1() throws Exception {
		openDbConnection();
		// create question/answer set to save
		ArrayList<SecurityQuestionAnswers> answerList = new ArrayList<SecurityQuestionAnswers>();
		try {
			db.setUserSecurityQuestionAnswers(answerList);
		} catch (Exception e) {
			db.rollbackSession();
			throw new Exception("Unable to save security question answers to the database.");
					
		}
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testSetUserSecurityQuestionAnswersException2() throws Exception {
		openDbConnection();
		// create question/answer set to save
		ArrayList<SecurityQuestionAnswers> answerList = new ArrayList<SecurityQuestionAnswers>();
		Date today = new Date();
		long personId = 1000000;
		String userId = "dummy";
		SecurityQuestionAnswers answer1 = new SecurityQuestionAnswers();
		long groupKey = 1;
		long questKey = 103;
		answer1.setAnswer("Unit test answer to 103 at " + today.toString());
		answer1.setPersonId(10000L);
		answer1.setUserid(userId);
		answer1.setSecQuestGroupKey(groupKey);
		answer1.setSecQuestKey(questKey);
		answer1.setCreatedOn(today);
		answerList.add(answer1);
		SecurityQuestionAnswers answer2 = new SecurityQuestionAnswers();
		groupKey = 2;
		questKey = 116;
		answer2.setAnswer("Unit test answer to 116 at " + today.toString());
		answer2.setPersonId(personId);
		answer2.setUserid(userId);
		answer2.setSecQuestGroupKey(groupKey);
		answer2.setSecQuestKey(questKey);
		answer2.setCreatedOn(today);
		answerList.add(answer2);
		SecurityQuestionAnswers answer3 = new SecurityQuestionAnswers();
		groupKey = 3;
		questKey = 126;
		answer3.setAnswer("Unit test answer to 126 at " + today.toString());
		answer3.setPersonId(personId);
		answer3.setUserid(userId);
		answer3.setSecQuestGroupKey(groupKey);
		answer3.setSecQuestKey(questKey);
		answer3.setCreatedOn(today);
		answerList.add(answer3);
		try {
			db.setUserSecurityQuestionAnswers(answerList);
		} catch (Exception e) {
			db.rollbackSession();
			throw new Exception("Unable to save security question answers to the database.");
					
		}
		db.closeSession();
	}

	@Test
	public final void testSetUserSecurityQuestionAnswers() throws Exception {
		openDbConnection();
		// create question/answer set to save
		ArrayList<SecurityQuestionAnswers> answerList = new ArrayList<SecurityQuestionAnswers>();
		Date today = new Date();
		long personId = 100000;
		String userId = "dummy";
		SecurityQuestionAnswers answer1 = new SecurityQuestionAnswers();
		long groupKey = 1;
		long questKey = 103;
		answer1.setAnswer("Unit test answer to 103 at " + today.toString());
		answer1.setPersonId(personId);
		answer1.setUserid(userId);
		answer1.setSecQuestGroupKey(groupKey);
		answer1.setSecQuestKey(questKey);
		answer1.setCreatedOn(today);
		answerList.add(answer1);
		SecurityQuestionAnswers answer2 = new SecurityQuestionAnswers();
		groupKey = 2;
		questKey = 116;
		answer2.setAnswer("Unit test answer to 116 at " + today.toString());
		answer2.setPersonId(personId);
		answer2.setUserid(userId);
		answer2.setSecQuestGroupKey(groupKey);
		answer2.setSecQuestKey(questKey);
		answer2.setCreatedOn(today);
		answerList.add(answer2);
		SecurityQuestionAnswers answer3 = new SecurityQuestionAnswers();
		groupKey = 3;
		questKey = 126;
		answer3.setAnswer("Unit test answer to 126 at " + today.toString());
		answer3.setPersonId(personId);
		answer3.setUserid(userId);
		answer3.setSecQuestGroupKey(groupKey);
		answer3.setSecQuestKey(questKey);
		answer3.setCreatedOn(today);
		answerList.add(answer3);
		try {
			db.setUserSecurityQuestionAnswers(answerList);
		} catch (Exception e) {
			db.rollbackSession();
			throw new Exception("Unable to save security question answers to the database.");
					
		}
		db.closeSession();
	}
	
	@Test
	public final void testGetUserSecurityQuestionAnswers() throws Exception {
		openDbConnection();
		long personId = 1000000;
		String userId = "dummy";
		AssertJUnit.assertNotNull(db.getUserSecurityQuestionAnswerList(personId, userId));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetRaScreenListBadRa() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaScreenList("I P", "portal1"));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetRaScreenListBadPrincipal() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaScreenList("Identity Provisioning", "portal1"));
		db.closeSession();
	}
	
	@Test
	public final void testGetRaScreenList() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaScreenList("Identity Provisioning", UnitTestHelper.TEST_USERID));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetRaFieldListBadRa() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaFieldList("I P", "portal1", "LegalName"));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetRaFieldListBadPrincipal() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaFieldList("Identity Provisioning", "portal1", "LegalName"));
		db.closeSession();
	}
	
	@Test
	public final void testGetRaFieldList() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaFieldList("Identity Provisioning", UnitTestHelper.TEST_USERID, "LegalName"));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetRaApplicationPropsBadRa() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaApplicationProperties("IP", "portal1"));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetRaApplicationPropsBadPrincipal() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaApplicationProperties("Identity Provisioning", "portal1"));
		db.closeSession();
	}
	
	@Test
	public final void testGetRaApplicationProperties() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getRaApplicationProperties("Identity Provisioning", UnitTestHelper.TEST_USERID));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetAllApplicationPropsBadRa() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getAllApplicationProperties("IP"));
		db.closeSession();
	}
	
	@Test
	public final void testGetAllApplicationProperties() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getAllApplicationProperties("Identity Provisioning"));
		db.closeSession();
	}

	@Test(expectedExceptions=Exception.class)
	public final void testGetAllScreenListsBadRa() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getAllScreenLists("IP"));
		db.closeSession();
	}
	
	@Test
	public final void testGetAllScreenLists() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getAllScreenLists("Identity Provisioning"));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetAllScreenAndFieldListsBadRa() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getAllScreenAndFieldLists("IP"));
		db.closeSession();
	}
	
	@Test
	public final void testGetAllScreenAndFieldLists() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getAllScreenAndFieldLists("Identity Provisioning"));
		db.closeSession();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void testGetEmailContentsBadProcess() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getEmailContents("Test Process"));
		db.closeSession();
	}
	
	@Test
	public final void testGetEmailContents() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getEmailContents("Account Creation"));
		db.closeSession();
	}
	
	
	@Test
	public final void testGetAllEmailContents() throws Exception {
		openDbConnection();
		AssertJUnit.assertNotNull(db.getAllEmailContents());
		db.closeSession();
	}

}
