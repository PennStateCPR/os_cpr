package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;


import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.PersonBio;
import edu.psu.iam.cpr.core.database.beans.Country;
import edu.psu.iam.cpr.core.database.beans.Person;
import edu.psu.iam.cpr.core.database.beans.PsuId;
import edu.psu.iam.cpr.core.database.beans.Userid;
import edu.psu.iam.cpr.core.database.types.AddressType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.database.types.EmailAddressType;
import edu.psu.iam.cpr.core.database.types.GenderType;
import edu.psu.iam.cpr.core.database.types.PhoneType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.util.DataQualityService;

public class PersonBioTest {

	private StatelessSession statelessSession = null;
	private DataQualityService dataQualityService = null;
	private Transaction tx = null;
	private PersonBio personBio = new PersonBio(statelessSession, BatchDataSource.UNIT_TEST, dataQualityService);
	private Long personId;

	private void openDbConnection()  {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		dataQualityService = new DataQualityService();
		personBio = new PersonBio(statelessSession, BatchDataSource.UNIT_TEST, dataQualityService);
		tx = statelessSession.beginTransaction();
	}

	private void closeDbConnection()  {
		tx.commit();
		statelessSession.close();
		dataQualityService.closeSession();
	}

	@BeforeClass
	public static void oneTimeSetup() {
		StatelessSession session = null;
		Transaction tx = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from Userid where (userid = :userid1 OR userid = :userid2) and endDate is NULL");
			query.setParameter("userid1", "abcxyz123");
			query.setParameter("userid2", "defxyz123");
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				final Userid bean = (Userid) it.next();
				session.delete(bean);
			}

			final Date d = new Date();

			query = session.createQuery("from Person where importFrom = :import_from and endDate is null");
			query.setParameter("import_from", BatchDataSource.UNIT_TEST.toString());
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				final Person bean = (Person) it.next();
				bean.setEndDate(d);
				session.update(bean);
			}
			tx.commit();
		}
		catch (final HibernateException e) {
			tx.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (final Exception e) { // $codepro.audit.disable emptyCatchClause
			}
		}
	}

	@BeforeMethod
	public final void findTheActivePerson()  {
		StatelessSession session = null;
		Transaction tx = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			tx = session.beginTransaction();
			final Query query = session.createQuery("from Person where importFrom = :import_from and endDate is null");
			query.setParameter("import_from", BatchDataSource.UNIT_TEST.toString());
			personId = null;
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				final Person bean = (Person) it.next();
				personId = bean.getPersonId();
			}
			tx.commit();

			if (personId == null) {
				addPerson();
			}
		}
		catch (final HibernateException e) {
			tx.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (final Exception e) { // $codepro.audit.disable emptyCatchClause
			}
		}
	}

	@Test
	public final void _01testFindPerson()  {
		openDbConnection();
		personBio.findPerson("812345678");
		closeDbConnection();
		assertEquals(personBio.getPersonId(),new Long(100001));
	}

	@Test
	public final void _02testFindPerson3()  {
		openDbConnection();
		personBio.findPerson("812345678");
		closeDbConnection();
		assertEquals(personBio.getPrimaryUserid(),"testuser1");
	}

	@Test
	public final void _03testFindPerson1()  {
		openDbConnection();
		personBio.findPerson("9---------");
		closeDbConnection();
		assertNull(personBio.getPersonId());
	}

	@Test
	public final void addPerson()  {
		openDbConnection();
		personBio.addPerson();
		closeDbConnection();
		personId = personBio.getPersonId();
		assertNotNull(personBio.getPersonId());
	}

	@Test
	public final void _04testUpdateAddress1() throws Exception {

		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateAddress(AddressType.LOCAL_ADDRESS, "1121 Oak Street", null, null, "State College", "PA", "16801", "USA", null);
		closeDbConnection();
	}

	@Test
	public final void _05testUpdateAddress2() throws Exception {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateAddress(AddressType.LOCAL_ADDRESS, "1121 Oak Street", null, null, "State College", "PA", "16801", "USA", null);
		closeDbConnection();
	}

	@Test
	public final void _06testUpdatePsuId1()  {

		openDbConnection();
		final String psuId = "800000000";
		personBio.setPersonId(personId);
		personBio.updatePsuId(psuId);
		closeDbConnection();
	}

	@Test
	public final void _07testUpdatePsuId2()  {
		openDbConnection();
		final String psuId = "800000000";
		personBio.setPersonId(personId);
		personBio.updatePsuId(psuId);
		closeDbConnection();
	}

	@Test
	public final void _08testUpdatePsuId3()  {

		openDbConnection();
		personBio.setPersonId(personId);
		final Query query = personBio.getDatabaseSession().createQuery("from PsuId where personId = :person_id and endDate is null");
		query.setParameter("person_id", personBio.getPersonId());
		String psuId = null;
		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			final PsuId bean = (PsuId) it.next();
			psuId = bean.getPsuId();
		}
		if (psuId == null) {
			psuId = "800000000";
		}
		else {
			final long tmp = Long.valueOf(psuId) + 1;
			psuId = Long.toString(tmp);
		}
		personBio.updatePsuId(psuId);
		closeDbConnection();
	}

	@Test
	public final void _09testUpdateGender1()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateGender(GenderType.GENDER_MALE);
		closeDbConnection();
	}

	@Test
	public final void _10testUpdateGender2()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateGender(GenderType.GENDER_MALE);
		closeDbConnection();
	}

	@Test
	public final void _11testUpdateGender3()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateGender(GenderType.GENDER_FEMALE);
		assertNotNull(personBio.getNewPersonGender());
		closeDbConnection();
	}

	@Test
	public final void _12testUpdateDob1() throws ParseException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateDateOfBirth("01012000");
		closeDbConnection();
	}

	@Test
	public final void _13testUpdateDob2() throws ParseException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateDateOfBirth("01012000");
		closeDbConnection();
	}

	@Test
	public final void _14testUpdateDob3() throws ParseException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateDateOfBirth((String)null);
		closeDbConnection();
	}

	@Test
	public final void _15testUpdateDob4() throws ParseException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateDateOfBirth("01022000");
		closeDbConnection();
	}

	@Test
	public final void _16testGetEmailAddressType1() {
		assertEquals(personBio.getEmailAddressType("abcd@Psu.Edu"), EmailAddressType.UNIVERSITY_EMAIL);
	}

	@Test
	public final void _17testGetEmailAddressType2() {
		assertEquals(personBio.getEmailAddressType("abcd@hmc.Psu.Edu"), EmailAddressType.UNIVERSITY_EMAIL);
	}

	@Test
	public final void _18testGetEmailAddressType3() {
		assertEquals(personBio.getEmailAddressType("abcd@gmail.com"), EmailAddressType.OTHER_EMAIL);
	}

	@Test
	public final void _19testUpdateEmailAddress1()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateEmailAddress("abc123@psu.edu");
		closeDbConnection();
	}

	@Test
	public final void _20testUpdateEmailAddress2()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateEmailAddress("");
		closeDbConnection();
	}

	@Test
	public final void _21testUpdateEmailAddress3()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateEmailAddress("abc123@psu.edu");
		closeDbConnection();
	}

	@Test
	public final void _22testUpdateEmailAddress4()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateEmailAddress("jabc123@psu.edu");
		closeDbConnection();
	}

	@Test
	public final void _23testUpdateEmailAddress5()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateEmailAddress("jabc123@Gmail.Com");
		closeDbConnection();
	}

	@Test
	public final void _24testUpdateName1() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateName("JEFF", null, "JONES", null);
		closeDbConnection();
	}

	@Test
	public final void _25testUpdateName2() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateName("JEFF", null, "JONES", null);
		closeDbConnection();
	}

	@Test
	public final void _26testUpdateName3() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateName("SAM", null, "JONES", null);
		closeDbConnection();
	}

	@Test
	public final void _27testUpdateName4() throws CprException {
		openDbConnection();
		personBio = new PersonBio(statelessSession, BatchDataSource.ISIS, dataQualityService);
		personBio.setPersonId(personId);
		personBio.updateName("JACKIE", null, "JONES", null);
		closeDbConnection();
	}

	@Test
	public final void _28testUpdateName5() throws CprException {
		openDbConnection();
		personBio = new PersonBio(statelessSession, BatchDataSource.ISIS, dataQualityService);
		personBio.setPersonId(personId);
		personBio.updateName("Jackie", "F", "Jones", null);
		closeDbConnection();
	}

	@Test
	public final void _29testAddName() throws Exception {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.addName("Richard", "C", "Test User", "Jr.");
		closeDbConnection();
	}

	@Test
	public final void _30testUpdatePhone1()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updatePhone(PhoneType.LOCAL_PHONE, "814-111-1212", null);
		closeDbConnection();
	}

	@Test
	public final void _31testUpdatePhone2()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updatePhone(PhoneType.LOCAL_PHONE, "814-111-1212", null);
		closeDbConnection();
	}

	@Test
	public final void _32testUpdatePhone3()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updatePhone(PhoneType.LOCAL_PHONE, "814-111-1210", null);
		closeDbConnection();
	}

	@Test
	public final void _33testUpdatePhone4()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updatePhone(PhoneType.PERMANENT_PHONE, "814-777-1211", null);
		closeDbConnection();
	}

	@Test
	public final void _34testUpdatePhone5()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updatePhone(PhoneType.PERMANENT_PHONE, "814-777-1211", null);
		closeDbConnection();
	}

	@Test
	public final void _35testUpdatePhone6()  {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updatePhone(PhoneType.PERMANENT_PHONE, "814-778-1211", "1121");
		closeDbConnection();
	}

	@Test
	public final void _36testUpdateUserid1() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateUserid("abcxyz123");
		closeDbConnection();
	}

	@Test
	public final void _37testUpdateUserid2() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateUserid("abcxyz123");
		closeDbConnection();
	}

	@Test(expectedExceptions=Exception.class)
	public final void _38testUpdateUserid3() throws Exception {
		try {
			openDbConnection();
			personBio.setPersonId(personId);
			personBio.updateUserid("jvuccolo");
		}
		catch (final Exception e) {
			throw new Exception(e);
		}
		finally {
			closeDbConnection();
		}
	}

	@Test
	public void _39testUpdateUserid4() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateUserid("defxyz123");
		closeDbConnection();
	}

	@Test
	public void _40testGetCampusBean1() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.getCampusBean("UP");
		closeDbConnection();

	}

	@Test
	public void _41testGetCampusBean2() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.getCampusBean("up");
		closeDbConnection();
	}

	@Test(expectedExceptions=Exception.class)
	public void _42testGetCampusBean3() throws Exception {
		try {
			openDbConnection();
			personBio.setPersonId(personId);
			personBio.getCampusBean("xxZ");
		}
		catch (final Exception e) {
			throw new Exception(e);
		}
		finally {
			closeDbConnection();
		}
	}

	@Test
	public void _43testGetCountryBean1() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		final Country country = personBio.getCountryBean("US");
		closeDbConnection();
		assertEquals(country.getCountryCodeThree(), "USA");
	}

	@Test
	public void _44testGetCountryBean2() throws CprException {
		openDbConnection();
		personBio.setPersonId(personId);
		final Country country = personBio.getCountryBean("usa");
		closeDbConnection();
		assertEquals(country.getCountryCodeThree(), "USA");
	}

	@Test(expectedExceptions=Exception.class)
	public void _45testGetCountryBean3() throws Exception {
		try {
			openDbConnection();
			personBio.setPersonId(personId);
			personBio.getCountryBean("abcd");
		}
		catch (final Exception e) {
			throw new Exception(e);
		}
		finally {
			closeDbConnection();
		}
	}

	@Test(expectedExceptions=Exception.class)
	public void _46testGetCountryBean4() throws Exception {
		try {
			openDbConnection();
			personBio.setPersonId(personId);
			personBio.getCountryBean("XXZ");
		}
		catch (final Exception e) {
			throw new Exception(e);
		}
		finally {
			closeDbConnection();
		}
	}

	@Test
	public final void _47testUpdateAddress3() throws Exception {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateAddress(AddressType.LOCAL_ADDRESS, "11 Treet Street", null, null, "State College", "PA", "16801", "USA", null);
		closeDbConnection();
	}

	@Test
	public final void _48testUpdateAddress4() throws Exception {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateAddress(AddressType.PERMANENT_ADDRESS, "11 Tree Street", null, null, "State College", "PA", "16801", "USA", null);
		closeDbConnection();
	}

	@Test
	public final void _49testUpdateAddress5() throws Exception {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateAddress(AddressType.PERMANENT_ADDRESS, "11 Tree Street", null, null, "State College", "PA", "16801", "USA", null);
		closeDbConnection();
	}

	@Test
	public final void _50testUpdateAddress6() throws Exception {
		openDbConnection();
		personBio.setPersonId(personId);
		personBio.updateAddress(AddressType.PERMANENT_ADDRESS, "12 Tree Street", null, null, "State College", "PA", "16801", "USA", null);
		closeDbConnection();
	}

	@Test
	public final void _51testFindPersonUsingPersonId() throws Exception {
		openDbConnection();
		final Long pid = personBio.findPersonUsingPersonId(100001L);
		closeDbConnection();
		assertEquals(pid, new Long(100001L));
	}

	@Test
	public final void _52testFindPrimaryUserid() throws Exception {
		openDbConnection();
		personBio.setPersonId(100001L);
		personBio.findPrimaryUserid();
		closeDbConnection();
		assertEquals(personBio.getPrimaryUserid(), "testuser1");
	}

}
