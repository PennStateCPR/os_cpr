package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.EmployeeInfo;
import edu.psu.iam.cpr.core.database.beans.Employee;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;
import edu.psu.iam.cpr.core.error.CprException;

public class EmployeeInfoTest {
	private StatelessSession statelessSession = null;
	private Transaction tx = null;
	private EmployeeInfo employeeInfo = new EmployeeInfo(statelessSession, BatchDataSource.UNIT_TEST, null);
	private final long personId = 100000L;
	private Date dateOne;
	private Date dateTwo;

	private void openDbConnection() {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		employeeInfo = new EmployeeInfo(statelessSession, BatchDataSource.UNIT_TEST, null);
		tx = statelessSession.beginTransaction();
	}

	private void closeDbConnection() {
		tx.commit();
		statelessSession.close();
	}

	@BeforeMethod
	public final void setupDates() throws ParseException {

		final String dateformat = "yyyyMMDD";
		final SimpleDateFormat dateParser = new SimpleDateFormat(dateformat);
		dateOne = dateParser.parse("20010105");
		dateTwo = dateParser.parse("20090105");
	}

	@BeforeMethod
	public final void preCleanUp() {
		StatelessSession session = null;
		Transaction trans = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			trans = session.beginTransaction();
			Query query = null;

		 	query = session.createQuery("from Employee where personId = :person_id");
			query.setParameter("person_id", personId);
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				final Employee employee = (Employee) it.next();
				session.delete(employee);
			}

			trans.commit();
		}
		catch (final HibernateException e) {
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (final Exception e) {
			}
		}
	}

	/**
	 * Internal helper method to add a "starter" employee record, which the update tests operate on.
	 */
	public final void addDummyEmployeeRecord() throws Exception {
		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.addEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", null, null, null, null, null, null, null, null, null);
		closeDbConnection();
	}

	/**
	 * Helper method to retrieve employee info.
	 *
	 * @return An employee object.
	 */
	public final Employee fetchDummyEmployee() {
		StatelessSession session = null;
		Transaction trans = null;
		Employee employee = new Employee();
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			trans = session.beginTransaction();
			Query query = null;

		 	query = session.createQuery("from Employee where personId = :person_id");
			query.setParameter("person_id", personId);
			for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				employee = (Employee) it.next();
			}

			trans.commit();
		}
		catch (final HibernateException e) {
			e.printStackTrace();
			trans.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (final Exception e) {
			}
		}

		return employee;
	}

	@Test
	public final void _01testEmployeeInfo() {
		new EmployeeInfo(null, BatchDataSource.UNIT_TEST, null);
	}

	// add tests

	@Test
	public final void _02testAddEmployeeWithNullApptCode() throws Exception {
		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.addEmployee("SYS DSGN SPEC", "", dateOne, dateTwo, null, "", null, "", "", "", "", "", "", "", "", "", "", "", "", null);
		closeDbConnection();
	}

	@Test
	public final void _03testAddEmployee1() throws CprException {
		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.addEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "STEX", "", "", "", "", "", "", null);
		closeDbConnection();
	}

	// update tests

	@Test
	public final void _04testUpdateEmployeeWithNullApptCode() throws Exception {
		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("SYS DSGN SPEC", "", dateOne, null, null, "", null, "", "", "", "", "", "", "", "", "", "", "", "", null, null);
		closeDbConnection();
	}

	@Test
	public final void _05testUpdateEmployeeLayoffFlagY() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, dateTwo, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "Y", "", "", "", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getLayoffFlag(), "Y");
	}

	@Test
	public final void _06testUpdateEmployeeLayoffFlagN() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, dateTwo, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "N", "", "", "", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getLayoffFlag(), "N");
	}

	@Test
	public final void _07testUpdateEmployeeChangeDepartment() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("", "", dateOne, dateTwo, null, "WAG", null, "LIBERAL ARTS", "", "PSYCHOLOGY", "UP", "", "", "", "", "", "", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getDepartment(), "PSYCHOLOGY");
		assertEquals(employee.getAdminArea(), "LIBERAL ARTS");
	}

	@Test
	public final void _08testUpdateEmployeeChangeVisaType() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "H1", "", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getVisaType(), "H1");
	}

	@Test
	public final void _09testUpdateEmployeeChangeEmplClass() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "ACAD", "", "", "", "", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getClassCode(), "ACAD");
	}

	@Test
	public final void _10testUpdateEmployeeChangeEmplStatus() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("", "", null, null, null, "WAG", null, "", "", "", "", "TER", "", "", "", "", "", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getStatusCode(), "TER");
	}

	@Test
	public final void _11testUpdateEmployeeChangeTitle() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("VICE PROVOST INFO TECH", "", null, null, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "", "", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getJobTitle(), "VICE PROVOST INFO TECH");
	}

	@Test
	public final void _12testUpdateEmployeeStudentFlagY() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "", "Y", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getStudentStatus(), "Y");
	}

	@Test
	public final void _13testUpdateEmployeeStudentFlagN() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "", "N", "", "", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getStudentStatus(), "N");
	}

	@Test
	public final void _14testUpdateEmployeeDirectPhoneNull() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "", "", "", null, null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getShowInDirectoryFlag(), "Y");
	}

	@Test
	public final void _15testUpdateEmployeeDirectPhoneB() throws Exception {
		addDummyEmployeeRecord();
		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "", "", "", "B", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getShowInDirectoryFlag(), "Y");
	}

	@Test
	public final void _16testUpdateEmployeeDirectPhoneW() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "", "", "", "W", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getShowInDirectoryFlag(), "Y");
	}

	@Test
	public final void _17testUpdateEmployeeDirectPhoneN() throws Exception {
		addDummyEmployeeRecord();

		openDbConnection();
		employeeInfo.setPersonId(personId);
		employeeInfo.updateEmployee("RES ASST  RESEARCH ASST", "", dateOne, dateTwo, null, "WAG", null, "SCIENCE - EBERLY COLLEGE", "", "ASTRONOMY", "UP", "", "", "", "", "", "", "", "N", null, null);
		closeDbConnection();

		final Employee employee = fetchDummyEmployee();
		assertEquals(employee.getShowInDirectoryFlag(), "N");
	}
}
