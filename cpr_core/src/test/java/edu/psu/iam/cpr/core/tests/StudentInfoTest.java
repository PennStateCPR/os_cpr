package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.StudentInfo;
import edu.psu.iam.cpr.core.database.beans.Student;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicCollege;
import edu.psu.iam.cpr.core.database.beans.StudentAcademicDepartment;
import edu.psu.iam.cpr.core.database.beans.StudentMajor;
import edu.psu.iam.cpr.core.database.types.BatchDataSource;

public class StudentInfoTest {
	private StatelessSession statelessSession = null;
	private Transaction tx = null;
	private StudentInfo studentInfo = new StudentInfo(statelessSession, BatchDataSource.UNIT_TEST, null);
	private final long personId = 100000L;
		
	private void openDbConnection() {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		studentInfo = new StudentInfo(statelessSession, BatchDataSource.UNIT_TEST, null);
		tx = statelessSession.beginTransaction();
	}
	
	private void closeDbConnection() {
		tx.commit();
		statelessSession.close();
	}
		
	@Test
	public final void _01testStudentInfo() {
		new StudentInfo(null, BatchDataSource.UNIT_TEST, null);
	}

	@BeforeMethod
	public final void beforeUpdateStudent1() {
		
	}
	
	@BeforeMethod
	public final void preCleanUp() {

		StatelessSession session = null;
		Transaction trans = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			trans = session.beginTransaction();
			
//			Query query = session.createQuery("from StudentHist where personId = :person_id");
//			query.setParameter("person_id", personId);
//			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
//				StudentHist studentHist = (StudentHist) it.next();
//				session.delete(studentHist);
//			}
			
			Query query = null;
			
			query = session.createQuery("from StudentAcademicCollege where personId = :person_id");
			query.setParameter("person_id", personId);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				StudentAcademicCollege studentAcademicCollege = (StudentAcademicCollege) it.next();
				session.delete(studentAcademicCollege);
			}
			
			query = session.createQuery("from StudentAcademicDepartment where personId = :person_id");
			query.setParameter("person_id", personId);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				StudentAcademicDepartment studentAcademicDepartment = (StudentAcademicDepartment) it.next();
				session.delete(studentAcademicDepartment);
			}
			
			query = session.createQuery("from StudentMajor where personId = :person_id");
			query.setParameter("person_id", personId);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				StudentMajor studentMajor = (StudentMajor) it.next();
				session.delete(studentMajor);
			}
			
		    query = session.createQuery("from Student where personId = :person_id");
			query.setParameter("person_id", personId);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				Student student = (Student) it.next();
				session.delete(student);
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
			catch (Exception e) {
			}
		}
	}
	
	public final void addStudentRecord() throws Exception {
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "UP", "SCHED", "UG", "01", "N", null, null, null, null, "N");
		closeDbConnection();
	}
	@Test
	public final void _02testUpdateStudent1() throws Exception {
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "UP", "SCHED", "UG", "01", "N", null, null, null, null, "Y");
		closeDbConnection();
	}
	
	@Test
	public final void _03testUpdateStudent2() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "SCHED", "UG", "01", "N", null, null, null, null, "N");
		closeDbConnection();
	}
	
	@Test
	public final void _04testUpdateStudent3() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "UG", "01", "N", null, null, null, null, "N");
		closeDbConnection();
	}
	
	@Test
	public final void _05testUpdateStudent4() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "01", "N", null, null, null, null, "Y");
		closeDbConnection();
	}
	
	@Test
	public final void _06testUpdateStudent5() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "02", "N", null, null, null, null, "N");
		closeDbConnection();
	}
	
	@Test
	public final void _07testUpdateStudent6() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "02", "Y", null, null, null, null, "N");
		closeDbConnection();
	}
	
	@Test
	public final void _08testUpdateStudent7() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "02", "Y", "01012000", null, null, null, "N");
		closeDbConnection();
	}
	
	@Test
	public final void _09testUpdateStudent8() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "02", "Y", "01012000", "01022001", null, null, "N");
		closeDbConnection();
	}
	
	@Test
	public final void _10testUpdateStudent9() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "02", "Y", null, null, "TERN", null, "N");
		closeDbConnection();
	}
	
	@Test
	public final void _11testUpdateStudent10() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "02", "Y", null, null, "TERN", "F", "Y");
		closeDbConnection();
	}
	
	@Test
	public final void _12testUpdateStudent11() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "AA", "REGIST", "LW", "02", "Y", null, null, "TERN", "P", "N");
		closeDbConnection();
	}
	
	@Test
	public final void _13testUpdateStudent12() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent(null, "AA", "REGIST", "LW", "02", "Y", null, null, "TERN", "P", "Y");
		closeDbConnection();
	}
	@Test
	public final void _14testUpdateStudent13() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", null, "REGIST", "LW", "02", "Y", null, null, "TERN", "P", "N");
		closeDbConnection();
	}
	
	@Test
	public final void _15testUpdateStudent14() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudent("201213SP", "UP", "REGIST", "LW", "03", "Y", null, null, "TERN", "P", "N");
		closeDbConnection();
	}
	
	public final void addAcademicCollege() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentAcademicCollege("201213SP", "MD", 1L);
		closeDbConnection();
	}
	
	@Test
	public final void _16testUpdateAcademicCollege1() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentAcademicCollege("201213SP", "HD", 1L);
		closeDbConnection();
	}
	
	@Test
	public final void _17testUpdateAcademicCollege2() throws Exception {
		addAcademicCollege();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentAcademicCollege("201213SP", "ID", 1L);
		closeDbConnection();
	}
	
	public final void addStudentMajor() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentMajor("201213SP", "GD", 1L);
		closeDbConnection();
	}
	
	@Test
	public final void _18testUpdateStudentMajor1() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentMajor("201213SP", "FINAN", 1L);
		closeDbConnection();
	}
	
	@Test
	public final void _19testUpdateStudentMajor2() throws Exception {
		addStudentMajor();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentMajor("201213SP", "ISSBL", 1L);
		closeDbConnection();
	}
	
	public final void addStudentAcademicDepartment() throws Exception {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentAcademicDepartment("201213SP", "HLS", 1L);
		closeDbConnection();
	}
	
	@Test
	public final void _20testUpdateStudentAcademicDepartment1() throws Exception  {
		addStudentRecord();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentAcademicDepartment("201213SP", "CLADM", 1L);
		closeDbConnection();
	}
	
	@Test
	public final void _21testUpdateStudentAcademicDepartment2() throws Exception  {
		addStudentAcademicDepartment();
		openDbConnection();
		studentInfo.setPersonId(personId);
		studentInfo.updateStudentAcademicDepartment("201213SP", "METEO", 1L);
		closeDbConnection();
	}
	
}
