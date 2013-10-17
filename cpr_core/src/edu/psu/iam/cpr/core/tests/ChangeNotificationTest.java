package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import org.hibernate.HibernateException;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.PersonBio;
import edu.psu.iam.cpr.core.database.beans.Addresses;
import edu.psu.iam.cpr.core.database.beans.DateOfBirth;
import edu.psu.iam.cpr.core.database.beans.EmailAddress;
import edu.psu.iam.cpr.core.database.beans.Employee;
import edu.psu.iam.cpr.core.database.beans.Names;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.beans.PersonGender;
import edu.psu.iam.cpr.core.database.beans.Phones;
import edu.psu.iam.cpr.core.database.beans.PsuId;
import edu.psu.iam.cpr.core.database.beans.Student;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicCollege;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicDepartment;
import edu.psu.iam.cpr.core.database.beans.StudentMajor;
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.ChangeNotificationType;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.NameType;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.messaging.ChangeNotification;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;

public class ChangeNotificationTest {

	private StatelessSession statelessSession = null;
	private Transaction tx = null;
	private MessagingCore messagingCore = null;
	private ChangeNotification changeNotification = null;
	private String psuId = null;
	private String userid = null;
	
	private void openDbConnection() throws Exception {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		messagingCore = new MessagingCore(statelessSession, "BatchProcessing");
		messagingCore.initializeMessaging();
		changeNotification = new ChangeNotification(statelessSession, messagingCore);
		tx = statelessSession.beginTransaction();
	}
	
	private void closeDbConnection() {
		tx.commit();
		statelessSession.close();
		messagingCore.closeMessaging();
	}
	
	@BeforeMethod
	public void _01testSetup() {
		StatelessSession session = null;
		Transaction t = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			t = session.beginTransaction();
			PersonBio personBio = new PersonBio(session, BatchDataSource.UNIT_TEST, null);
			personBio.findPersonUsingPersonId(100000L);
			psuId = personBio.getPsuIdNumber();
			userid = personBio.getPrimaryUserid();
			t.commit();
		}
		catch (HibernateException e) {
			t.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (Exception e) {
			}
		}	
	}
	
	@Test
	public void _02testSetRequiredInfo() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		closeDbConnection();
	}

	@Test
	public void _03testCreateBaseJsonMessage() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		JsonMessage jsonMessage = changeNotification.createBaseJsonMessage(ChangeNotificationType.ADDRESS_CHANGE);
		closeDbConnection();
		AssertJUnit.assertNotNull(jsonMessage);
	}

	@Test
	public void _04testAddressChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.addressChange(null, null);
		closeDbConnection();
	}
	
	@Test
	public void _05testAddressChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Addresses newOne = new Addresses();
		newOne.setDataTypeKey(AddressType.LOCAL_ADDRESS.index());
		newOne.setAddress1("1234 Big Street");
		newOne.setCity("Lock Haven");
		newOne.setState("PA");
		newOne.setPostalCode("17745");
		changeNotification.addressChange(null, newOne);
		closeDbConnection();
	}
	
	@Test
	public void _06testAddressChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Addresses newOne = new Addresses();
		newOne.setDataTypeKey(AddressType.LOCAL_ADDRESS.index());
		newOne.setAddress1("1234 Big Street");
		newOne.setCity("Lock Haven");
		newOne.setState("PA");
		newOne.setPostalCode("17745");
		changeNotification.addressChange(newOne, newOne);
		closeDbConnection();
	}

	@Test
	public void _07testEmailAddressChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.emailAddressChange(null, null);
		closeDbConnection();
	
	}
	@Test
	public void _08testEmailAddressChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		EmailAddress newOne = new EmailAddress();
		newOne.setDataTypeKey(EmailAddressType.OTHER_EMAIL.index());
		newOne.setEmailAddress("abc123@gmail.com");
		changeNotification.emailAddressChange(null, newOne);
		closeDbConnection();	
	}
	
	@Test
	public void _09testEmailAddressChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		EmailAddress oldOne = new EmailAddress();
		oldOne.setDataTypeKey(EmailAddressType.OTHER_EMAIL.index());
		oldOne.setEmailAddress("abc1234@gmail.com");
		changeNotification.emailAddressChange(oldOne, null);
		closeDbConnection();
	
	}
	@Test
	public void _10testEmailAddressChange4() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		EmailAddress newOne = new EmailAddress();
		newOne.setDataTypeKey(EmailAddressType.OTHER_EMAIL.index());
		newOne.setEmailAddress("abc123@gmail.com");
		EmailAddress oldOne = new EmailAddress();
		oldOne.setDataTypeKey(EmailAddressType.OTHER_EMAIL.index());
		oldOne.setEmailAddress("abc1234@gmail.com");
		changeNotification.emailAddressChange(oldOne, newOne);
		closeDbConnection();
	
	}

	@Test
	public void _11testEmployeeChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.employeeChange(null, null);
		closeDbConnection();

	}
	
	@Test
	public void _12testEmployeeChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Employee e = new Employee();
		e.setAdminArea("046");
		e.setApptCode("P");
		e.setClassCode("AA");
		changeNotification.employeeChange(null, e);
		closeDbConnection();
	}
	
	@Test
	public void _13testEmployeeChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Employee e = new Employee();
		e.setAdminArea("046");
		e.setApptCode("P");
		e.setClassCode("AA");
		e.setSpecialStatus("E");
		changeNotification.employeeChange(e, null);
		closeDbConnection();
	}

	@Test
	public void _14testGenderChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.genderChange(null, null);
		closeDbConnection();
	}

	@Test
	public void _15testGenderChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PersonGender gender = new PersonGender();
		gender.setDataTypeKey(GenderType.GENDER_MALE.index());
		changeNotification.genderChange(gender, null);
		closeDbConnection();
	}

	@Test
	public void _16testGenderChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PersonGender gender = new PersonGender();
		gender.setDataTypeKey(GenderType.GENDER_MALE.index());
		changeNotification.genderChange(null, gender);
		closeDbConnection();
	}

	@Test
	public void _17testNameChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.nameChange(null, null);
		closeDbConnection();
	}
	
	@Test
	public void _18testNameChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Names names = new Names();
		names.setDataTypeKey(NameType.LEGAL_NAME.index());
		names.setFirstName("Jeff");
		names.setLastName("Jones");
		changeNotification.nameChange(null, names);
		closeDbConnection();
	}
	
	@Test
	public void _19testNameChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Names names = new Names();
		names.setDataTypeKey(NameType.LEGAL_NAME.index());
		names.setFirstName("Jeff");
		names.setLastName("Jones");
		changeNotification.nameChange(names, names);
		closeDbConnection();
	}

	@Test
	public void _20testPhoneChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.nameChange(null,null);
		closeDbConnection();
	}

	@Test
	public void _21testPhoneChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Phones phones = new Phones();
		phones.setDataTypeKey(PhoneType.LOCAL_PHONE.index());
		phones.setPhoneNumber("814-777-0079");
		changeNotification.phoneChange(null,phones);
		closeDbConnection();
	}

	@Test
	public void _22testPhoneChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Phones phones = new Phones();
		phones.setDataTypeKey(PhoneType.LOCAL_PHONE.index());
		phones.setPhoneNumber("814-777-0079");
		changeNotification.phoneChange(phones,phones);
		closeDbConnection();
	}

	@Test
	public void _23testPsuIdChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.psuIdChange(null,null);
		closeDbConnection();
	}

	@Test
	public void _24testPsuIdChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		changeNotification.psuIdChange(null, psuid);
		closeDbConnection();
	}

	@Test
	public void _25testPsuIdChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		changeNotification.psuIdChange(psuid, psuid);
		closeDbConnection();
	}

	@Test
	public void _26testStudentChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		changeNotification.studentChange(null, null, null, null, null, null, null, null);
		closeDbConnection();
		
	}
	
	@Test
	public void _27testStudentChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Student s = new Student();
		s.setAcademicLevel("UG");
		s.setClassLoad("F");
		s.setGraduatingFlag("N");
		s.setSemesterCode("201213SP");
		StudentAcademicDepartment sad = new StudentAcademicDepartment();
		sad.setSemesterCode("201213SP");
		sad.setDepartmentCode("abcd");
		StudentAcademicCollege sac = new StudentAcademicCollege();
		sac.setSemesterCode("201213SP");
		sac.setCollegeCode("abcd");
		StudentMajor sm = new StudentMajor();
		sm.setSemesterCode("201213SP");
		sm.setMajorCode("abcd");
		changeNotification.studentChange(null, null, null, null, s, sac, sad, sm);
		closeDbConnection();
		
	}
	
	@Test
	public void _28testStudentChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Student s = new Student();
		s.setAcademicLevel("UG");
		s.setClassLoad("F");
		s.setGraduatingFlag("N");
		s.setSemesterCode("201213SP");
		StudentAcademicDepartment sad = new StudentAcademicDepartment();
		sad.setSemesterCode("201213SP");
		sad.setDepartmentCode("abcd");
		StudentAcademicCollege sac = new StudentAcademicCollege();
		sac.setSemesterCode("201213SP");
		sac.setCollegeCode("abcd");
		StudentMajor sm = new StudentMajor();
		sm.setSemesterCode("201213SP");
		sm.setMajorCode("abcd");
		changeNotification.studentChange(s, sac, sad, sm, null, null, null, null);
		closeDbConnection();
		
	}

	@Test
	public void _29testPrimaryAffiliationChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		changeNotification.primaryAffiliationChange(null, null);
		closeDbConnection();

	}
	
	@Test
	public void _30testPrimaryAffiliationChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		PersonAffiliation pa = new PersonAffiliation();
		pa.setAffiliationKey(AffiliationsType.EMPLOYEE.index());
		changeNotification.primaryAffiliationChange(null, pa);
		closeDbConnection();

	}
	
	@Test
	public void _31testPrimaryAffiliationChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		PersonAffiliation pa = new PersonAffiliation();
		pa.setAffiliationKey(AffiliationsType.EMPLOYEE.index());
		changeNotification.primaryAffiliationChange(pa, null);
		closeDbConnection();

	}
	@Test
	public void _32testBirthDateChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		changeNotification.primaryAffiliationChange(null, null);
		closeDbConnection();

	}
	
	@Test
	public void _33testBirthDateChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		DateOfBirth dob = new DateOfBirth();
		dob.setDobChar("10101950");
		changeNotification.dateOfBirthChange(null, dob);
		closeDbConnection();

	}
	
	@Test
	public void _34testBirthDateChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		DateOfBirth dob = new DateOfBirth();
		dob.setDobChar("10101950");
		changeNotification.dateOfBirthChange(dob, null);
		closeDbConnection();

	}
	@Test
	public void _35testUseridChange1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		PsuId psuid = new PsuId();
		psuid.setPsuId("123456789");
		changeNotification.useridChange(null, null);
		closeDbConnection();

	}
	
	@Test
	public void _36testUseridChange2() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Userid u = new Userid();
		u.setUserid("abcd1232");
		u.setPrimaryFlag("N");
		changeNotification.useridChange(null, u);
		closeDbConnection();

	}
	
	@Test
	public void _37testUseridChange3() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Userid u = new Userid();
		u.setUserid("abcd1232");
		u.setPrimaryFlag("N");
		changeNotification.useridChange(u, u);
		closeDbConnection();

	}
	
	@Test
	public void _38testNewStudent1() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Addresses newOne = new Addresses();
		newOne.setDataTypeKey(AddressType.LOCAL_ADDRESS.index());
		newOne.setAddress1("1234 Big Street");
		newOne.setCity("Lock Haven");
		newOne.setState("PA");
		newOne.setPostalCode("17745");
		Phones phones = new Phones();
		phones.setDataTypeKey(PhoneType.LOCAL_PHONE.index());
		phones.setPhoneNumber("814-777-0079");
		Student s = new Student();
		s.setAcademicLevel("UG");
		s.setClassLoad("F");
		s.setGraduatingFlag("N");
		s.setSemesterCode("201213SP");
		StudentAcademicDepartment sad = new StudentAcademicDepartment();
		sad.setSemesterCode("201213SP");
		sad.setDepartmentCode("abcd");
		StudentAcademicCollege sac = new StudentAcademicCollege();
		sac.setSemesterCode("201213SP");
		sac.setCollegeCode("abcd");
		StudentMajor sm = new StudentMajor();
		sm.setSemesterCode("201213SP");
		sm.setMajorCode("abcd");
		PersonGender gender = new PersonGender();
		gender.setDataTypeKey(GenderType.GENDER_MALE.index());
		Names names = new Names();
		names.setDataTypeKey(NameType.LEGAL_NAME.index());
		names.setFirstName("Jeff");
		names.setLastName("Jones");
		changeNotification.newStudent(names, newOne, phones, null,  null, gender, null, s, sac, sad, sm);
		closeDbConnection();

		
	}
	
	@Test
	public void _39testNewEmployee() throws Exception {
		openDbConnection();
		changeNotification.setRequiredInfo(100000L, psuId, userid, BatchDataSource.UNIT_TEST.toString());
		Addresses newOne = new Addresses();
		newOne.setDataTypeKey(AddressType.LOCAL_ADDRESS.index());
		newOne.setAddress1("1234 Big Street");
		newOne.setCity("Lock Haven");
		newOne.setState("PA");
		newOne.setPostalCode("17745");
		Phones phones = new Phones();
		phones.setDataTypeKey(PhoneType.LOCAL_PHONE.index());
		phones.setPhoneNumber("814-777-0079");
		PersonGender gender = new PersonGender();
		gender.setDataTypeKey(GenderType.GENDER_MALE.index());
		Names names = new Names();
		names.setDataTypeKey(NameType.LEGAL_NAME.index());
		names.setFirstName("Jeff");
		names.setLastName("Jones");
		Employee e = new Employee();
		e.setAdminArea("046");
		e.setApptCode("P");
		e.setClassCode("AA");
		changeNotification.newEmployee(names, newOne, phones,  null, null, gender, null, e);
		closeDbConnection();
	}

}
