package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;







import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.EduPersonAffiliationCalculator;
import edu.psu.iam.cpr.core.database.beans.PersonAffiliation;
import edu.psu.iam.cpr.core.database.types.AffiliationCalculatorKey;
import edu.psu.iam.cpr.core.database.types.AffiliationsType;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.error.CprException;

public class EduPersonAffiliationCalculatorTest {
	private StatelessSession statelessSession = null;
	private Transaction tx = null;
	private EduPersonAffiliationCalculator affiliationCalc = new EduPersonAffiliationCalculator(statelessSession, BatchDataSource.UNIT_TEST, null);
	private static final long personId = 100000L;
		
	private void openDbConnection(BatchDataSource source) {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		affiliationCalc = new EduPersonAffiliationCalculator(statelessSession, source, null);
		affiliationCalc.setPersonId(personId);
		tx = statelessSession.beginTransaction();
	}
	
	private void closeDbConnection() {
		tx.commit();
		statelessSession.close();
	}
		
	@Test
	public final void _01testEduPersonAffiliationCalculator() {
		new EduPersonAffiliationCalculator(null, BatchDataSource.UNIT_TEST, null);
	}
	
	@BeforeMethod
	public final void beforeUpdateAffiliationIBIS() {

		// Remove all active affiliations from the database for the test personId
		StatelessSession session = null;
		Transaction trans = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			trans = session.beginTransaction();
				
			List<Long> affiliationsList = new ArrayList<Long>();
				
			affiliationsList.add(AffiliationsType.EMERITUS.index());
			affiliationsList.add(AffiliationsType.EMPLOYEE.index());
			affiliationsList.add(AffiliationsType.FACULTY.index());
			affiliationsList.add(AffiliationsType.MEMBER.index());
			affiliationsList.add(AffiliationsType.RETIREE.index());
			affiliationsList.add(AffiliationsType.STAFF.index());
			affiliationsList.add(AffiliationsType.STUDENT.index());

			Query query = null;
			
			query = session.createQuery("from PersonAffiliation a where a.personId = :personId AND a.affiliationKey IN (:affiliationKeys)");
			query.setParameterList("affiliationKeys", affiliationsList);
			query.setParameter("personId", personId);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				PersonAffiliation affiliation = (PersonAffiliation) it.next();
				session.delete(affiliation);
			}
			
			trans.commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (Exception e) { // $codepro.audit.disable emptyCatchClause
			}
		}
	}

	@Test
	public final void _02testCalculateEmployeeAffiliationEmeritus() {
		AffiliationsType type = affiliationCalc.calculateEmployeeAffiliation("ACAD", "RET", "E");
		AssertJUnit.assertTrue(type == AffiliationsType.EMERITUS);
	}
	
	@Test
	public final void _03testCalculateEmployeeAffiliationRetiree() {
		AffiliationsType type = affiliationCalc.calculateEmployeeAffiliation("ACAD", "RET", "P");
		AssertJUnit.assertTrue(type == AffiliationsType.RETIREE);
	}
	
	@Test
	public final void _04testCalculateEmployeeAffiliationFaculty() {
		AffiliationsType type = affiliationCalc.calculateEmployeeAffiliation("ACAM", "ACT", "P");
		AssertJUnit.assertTrue(type == AffiliationsType.FACULTY);
	}
	
	@Test
	public final void _05testCalculateEmployeeAffiliationStaff() {
		AffiliationsType type = affiliationCalc.calculateEmployeeAffiliation("ADMR", "ACT", "P");
		AssertJUnit.assertTrue(type == AffiliationsType.STAFF);
	}
	
	@Test
	public final void _05testCalculateEmployeeAffiliationEmployee() {
		AffiliationsType type = affiliationCalc.calculateEmployeeAffiliation("TECH", "ACT", "P");
		AssertJUnit.assertTrue(type == AffiliationsType.EMPLOYEE);
	}
	
	@Test
	public final void _06testCalculateEmployeeAffiliationMember() {
		AffiliationsType type = affiliationCalc.calculateEmployeeAffiliation("ADMR", "TER", "P");
		AssertJUnit.assertTrue(type == AffiliationsType.MEMBER);
	}
	
	@Test
	public final void _07testCalculateStudentAffiliationStudent() {
		AffiliationsType type = affiliationCalc.calculateStudentAffiliation("SCHED");
		AssertJUnit.assertTrue(type == AffiliationsType.STUDENT);
	}
	
	@Test
	public final void _08testCalculateStudentAffiliationMember() {
		AffiliationsType type = affiliationCalc.calculateStudentAffiliation("WITH-U");
		AssertJUnit.assertTrue(type == AffiliationsType.MEMBER);
	}
	
	@Test
	public final void _09testDetermineCurrentAffiliation1() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMERITUS.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		AssertJUnit.assertEquals((long) affiliationCalc.getPrimaryAffiliation().getAffiliationKey(), (long) AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals(affiliationCalc.getCurrentAffiliations().size(), 3);
		final ArrayList<PersonAffiliation> l = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(l.size(), 1);
		AssertJUnit.assertEquals((long) l.get(0).getAffiliationKey(), (long) AffiliationsType.EMERITUS.index());
		closeDbConnection();

	}
	
	@Test
	public final void _09testDetermineCurrentAffiliation2() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMERITUS.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.FACULTY.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		AssertJUnit.assertEquals((long) affiliationCalc.getPrimaryAffiliation().getAffiliationKey(), (long) AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals(affiliationCalc.getCurrentAffiliations().size(), 3);
		final ArrayList<PersonAffiliation> l = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(l.size(), 2);
		AssertJUnit.assertEquals((long) l.get(0).getAffiliationKey(), (long) AffiliationsType.EMERITUS.index());
		AssertJUnit.assertEquals((long) l.get(1).getAffiliationKey(), (long) AffiliationsType.FACULTY.index());
		closeDbConnection();

	}
	
	@Test(expectedExceptions=CprException.class)
	public final void _09testDetermineCurrentAffiliation3() throws CprException {
		try {
			openDbConnection(BatchDataSource.UNIT_TEST);
			affiliationCalc.createPersonAffiliation(AffiliationsType.EMERITUS.index(), "Y");
			affiliationCalc.createPersonAffiliation(AffiliationsType.FACULTY.index(), "N");
			affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "N");
			affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
			closeDbConnection();
			openDbConnection(BatchDataSource.UNIT_TEST);
			affiliationCalc.determineCurrentPersonAffiliations();
			AssertJUnit.assertEquals((long) affiliationCalc.getPrimaryAffiliation().getAffiliationKey(), (long) AffiliationsType.MEMBER.index());
			AssertJUnit.assertEquals(affiliationCalc.getCurrentAffiliations().size(), 3);
			final ArrayList<PersonAffiliation> l = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
			AssertJUnit.assertEquals(l.size(), 2);
			AssertJUnit.assertEquals((long) l.get(0).getAffiliationKey(), (long) AffiliationsType.EMERITUS.index());
			AssertJUnit.assertEquals((long) l.get(1).getAffiliationKey(), (long) AffiliationsType.FACULTY.index());
		}
		finally {
			closeDbConnection();
		}

	}
	
	@Test
	public final void _09testDetermineCurrentAffiliation4() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMERITUS.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.FACULTY.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMPLOYEE.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		AssertJUnit.assertEquals((long) affiliationCalc.getPrimaryAffiliation().getAffiliationKey(), (long) AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals(affiliationCalc.getCurrentAffiliations().size(), 3);
		final ArrayList<PersonAffiliation> l = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(l.size(), 3);
		AssertJUnit.assertEquals((long) l.get(0).getAffiliationKey(), (long) AffiliationsType.EMERITUS.index());
		AssertJUnit.assertEquals((long) l.get(1).getAffiliationKey(), (long) AffiliationsType.FACULTY.index());
		AssertJUnit.assertEquals((long) l.get(2).getAffiliationKey(), (long) AffiliationsType.EMPLOYEE.index());
		closeDbConnection();

	}
	
	@Test
	public final void _10testProcessAffiliationChangeA() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.EMPLOYEE, AffiliationsType.STAFF.index());	
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(e.size(), 1);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertNull(s);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test
	public final void _10testProcessAffiliationChangeB() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());		
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertNull(e);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertEquals(s.size(), 1);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test
	public final void _10testProcessAffiliationChangeC() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());		
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertNull(e);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertEquals(s.size(), 1);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test
	public final void _10testProcessAffiliationChangeD() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());		
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertNull(e);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertEquals(s.size(), 1);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test
	public final void _10testProcessAffiliationChangeE() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());		
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertNull(e);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertEquals(s.size(), 1);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test
	public final void _10testProcessAffiliationChangeF() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.STUDENT, AffiliationsType.MEMBER.index());		
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertNull(e);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertNull(s);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test
	public final void _10testProcessAffiliationChangeG() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMPLOYEE.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.EMPLOYEE, AffiliationsType.STAFF.index());		
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(e.size(), 1);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertEquals(s.size(), 1);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test
	public final void _10testProcessAffiliationChangeH() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMPLOYEE.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.HMC);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.EMPLOYEE, AffiliationsType.EMPLOYEE.index());		
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(e.size(), 1);
		AssertJUnit.assertEquals(e.get(0).getLastUpdateBy(), BatchDataSource.HMC.toString());
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertEquals(s.size(), 1);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		closeDbConnection();
	}
	
	@Test(expectedExceptions=Exception.class)
	public final void _10testProcessAffiliationChangeI() throws CprException {
		try {
			openDbConnection(BatchDataSource.UNIT_TEST);
			affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), null);
			affiliationCalc.createPersonAffiliation(AffiliationsType.EMPLOYEE.index(), null);
			closeDbConnection();
			openDbConnection(BatchDataSource.HMC);
			affiliationCalc.determineCurrentPersonAffiliations();
		}
		finally {
			closeDbConnection();
		}
	}
	
	@Test
	public final void _11testCalculatePrimaryAffiliationA() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());	
		affiliationCalc.calculatePrimaryAffiliation();
		AssertJUnit.assertNull(affiliationCalc.getOldPersonAffiliation());
		AssertJUnit.assertNull(affiliationCalc.getNewPersonAffiliation());
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertNull(e);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertEquals(s.size(), 1);
		AssertJUnit.assertEquals(s.get(0).getPrimaryFlag(),"Y");
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		AssertJUnit.assertEquals(m.get(0).getPrimaryFlag(),"N");
		closeDbConnection();
		
	}
	
	@Test
	public final void _11testCalculatePrimaryAffiliationB() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.STUDENT, AffiliationsType.MEMBER.index());	
		affiliationCalc.calculatePrimaryAffiliation();
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STUDENT.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertNull(e);
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertNull(s);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		AssertJUnit.assertEquals(m.get(0).getPrimaryFlag(),"Y");
		closeDbConnection();
		
	}
	
	@Test
	public final void _11testCalculatePrimaryAffiliationC() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.EMPLOYEE, AffiliationsType.FACULTY.index());	
		affiliationCalc.calculatePrimaryAffiliation();
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STAFF.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.FACULTY.index());
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(e.get(0).getPrimaryFlag(), "Y");
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertNull(s);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		AssertJUnit.assertEquals(m.get(0).getPrimaryFlag(),"N");
		closeDbConnection();
		
	}
	
	@Test
	public final void _11testCalculatePrimaryAffiliationE() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		affiliationCalc.processAffiliationChange(AffiliationCalculatorKey.EMPLOYEE, AffiliationsType.EMPLOYEE.index());	
		affiliationCalc.calculatePrimaryAffiliation();
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.determineCurrentPersonAffiliations();
		final ArrayList<PersonAffiliation> e = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.EMPLOYEE);
		AssertJUnit.assertEquals(e.get(0).getPrimaryFlag(), "Y");
		final ArrayList<PersonAffiliation> s = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.STUDENT);
		AssertJUnit.assertNull(s);
		final ArrayList<PersonAffiliation> m = affiliationCalc.getCurrentAffiliations().get(AffiliationCalculatorKey.MEMBER);
		AssertJUnit.assertEquals(m.size(), 1);
		AssertJUnit.assertEquals(m.get(0).getPrimaryFlag(),"N");
		closeDbConnection();
	}
	
	@Test
	public final void _12updateAffiliationsA() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.updateAffiliations(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());
		AssertJUnit.assertNull(affiliationCalc.getOldPersonAffiliation());
		AssertJUnit.assertNull(affiliationCalc.getNewPersonAffiliation());
		closeDbConnection();
	}
	
	@Test
	public final void _12updateAffiliationsB() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.updateAffiliations(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STUDENT.index());
		closeDbConnection();
	}
	
	@Test
	public final void _12updateAffiliationsC() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.updateAffiliations(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());
		AssertJUnit.assertNull(affiliationCalc.getOldPersonAffiliation());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STUDENT.index());
		closeDbConnection();
	}
	
	@Test
	public final void _12updateAffiliationsD() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMPLOYEE.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.updateAffiliations(AffiliationCalculatorKey.STUDENT, AffiliationsType.STUDENT.index());
		AssertJUnit.assertNull(affiliationCalc.getOldPersonAffiliation());
		AssertJUnit.assertNull(affiliationCalc.getNewPersonAffiliation());
		closeDbConnection();
	}
	
	@Test
	public final void _12updateAffiliationsE() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMPLOYEE.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.updateAffiliations(AffiliationCalculatorKey.EMPLOYEE, AffiliationsType.STAFF.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.EMPLOYEE.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STAFF.index());
		closeDbConnection();
	}
	
	@Test
	public final void _13setStudentAffiliationA() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setStudentAffiliation("REGIST");
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STUDENT.index());
		closeDbConnection();
	}
	
	@Test
	public final void _13setStudentAffiliationB() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setStudentAffiliation("SCHED");
		AssertJUnit.assertNull(affiliationCalc.getOldPersonAffiliation());
		AssertJUnit.assertNull(affiliationCalc.getNewPersonAffiliation());
		closeDbConnection();
	}
	
	@Test
	public final void _13setStudentAffiliationC() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STUDENT.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setStudentAffiliation("CANCEL");
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STUDENT.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setEmployeeAffiliationA() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setEmployeeAffiliation("ACAD", "ACT", null);
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.FACULTY.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setEmployeeAffiliationB() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setEmployeeAffiliation("ACAD", "ACT", "N");
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STAFF.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.FACULTY.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setEmployeeAffiliationC() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setEmployeeAffiliation("STNE", "ACT", "N");
		AssertJUnit.assertNull(affiliationCalc.getOldPersonAffiliation());
		AssertJUnit.assertNull(affiliationCalc.getNewPersonAffiliation());
		closeDbConnection();
	}
	
	@Test
	public final void _14setEmployeeAffiliationD() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setEmployeeAffiliation("STNE", "TER", "N");
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STAFF.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		closeDbConnection();
	}
	
	
	@Test
	public final void _14setEmployeeAffiliationE() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.EMPLOYEE.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setEmployeeAffiliation("STNE", "TER", "N");
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STAFF.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setEmployeeAffiliationF() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.FACULTY.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setEmployeeAffiliation("STN", "RET", "E");
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.FACULTY.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.EMERITUS.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setEmployeeAffiliationG() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.FACULTY.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setEmployeeAffiliation("TECH", "ACT", null);
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.FACULTY.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.EMPLOYEE.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setHersheyAffiliationA() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setHMCAffiliation(AffiliationsType.STAFF.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STAFF.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setHersheyAffiliationB() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.FACULTY.index(), "Y");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setHMCAffiliation(AffiliationsType.STAFF.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.FACULTY.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.STAFF.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setHersheyAffiliationC() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.createPersonAffiliation(AffiliationsType.MEMBER.index(), "N");
		affiliationCalc.createPersonAffiliation(AffiliationsType.FACULTY.index(), "Y");
		affiliationCalc.createPersonAffiliation(AffiliationsType.STAFF.index(), "N");
		closeDbConnection();
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setHMCAffiliation(AffiliationsType.MEMBER.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getOldPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.FACULTY.index());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		closeDbConnection();
	}
	
	@Test
	public final void _14setHersheyAffiliationD() throws CprException {
		openDbConnection(BatchDataSource.UNIT_TEST);
		affiliationCalc.setHMCAffiliation(AffiliationsType.MEMBER.index());
		AssertJUnit.assertNull(affiliationCalc.getOldPersonAffiliation());
		AssertJUnit.assertEquals((Long) affiliationCalc.getNewPersonAffiliation().getAffiliationKey(), (Long) AffiliationsType.MEMBER.index());
		closeDbConnection();
		
	}

}
