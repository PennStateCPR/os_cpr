package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import static org.junit.Assert.*;

import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.batch.AccessAccountStatus;
import edu.psu.iam.cpr.core.database.beans.UserServiceStatus;
import edu.psu.iam.cpr.core.database.types.AccessAccountServiceType;
import edu.psu.iam.cpr.core.database.types.AccessAccountStatusType;
import edu.psu.iam.cpr.core.messaging.MessagingCore;

public class AccessAccountStatusTest {

	private StatelessSession statelessSession = null;
	private MessagingCore messagingCore = null;
	private Transaction tx = null;
	private Long personId = 100000L;
	private String userid = "dummy";

	@BeforeMethod
	public void removeAllStatus() {
		StatelessSession session = null;
		Transaction txSession = null;
		try {
			session = SessionFactoryUtil.getSessionFactory().openStatelessSession();
			txSession = session.beginTransaction();
			Query query = session.createQuery("from UserServiceStatus where personId = :person_id");
			query.setParameter("person_id", 100000L);
			for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
				UserServiceStatus userServiceStatus = (UserServiceStatus) it.next();
				session.delete(userServiceStatus);
			}
			
			messagingCore = new MessagingCore(session, "BatchProcessing");
			txSession.commit();
		}
		catch (HibernateException e) {
			e.printStackTrace();
			txSession.rollback();
		}
		finally {
			try {
				session.close();
			}
			catch (Exception e) {
			}
			
		}
	}
	private void openDbConnection() {
		statelessSession = SessionFactoryUtil.getSessionFactory().openStatelessSession();
		tx = statelessSession.beginTransaction();
	}
	
	private void closeDbConnection() {
		tx.commit();
		statelessSession.close();
	}
	
	private void addKerberos() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.addService(personId, userid, AccessAccountServiceType.KERBEROS);
		closeDbConnection();
	}
	
	private void addEmail() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.addService(personId, userid, AccessAccountServiceType.EMAIL);
		closeDbConnection();
	}
	
	private void addPASS() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.addService(personId, userid, AccessAccountServiceType.PASS);
		closeDbConnection();
	}
	
	private void addDirectory() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.addService(personId, userid, AccessAccountServiceType.DIRECTORY);
		closeDbConnection();
	}

	private void lockKerberos() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.removeService(personId, userid, AccessAccountServiceType.KERBEROS);
		closeDbConnection();
	}
	
	private void lockDirectory() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.removeService(personId, userid, AccessAccountServiceType.DIRECTORY);
		closeDbConnection();
	}

	private boolean isKerberosActive() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		boolean status = accessAccountStatus.isServiceActive(personId, userid, AccessAccountServiceType.KERBEROS);
		closeDbConnection();
		return status;
	}
	
	private boolean isEmailActive() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		boolean status = accessAccountStatus.isServiceActive(personId, userid, AccessAccountServiceType.EMAIL);
		closeDbConnection();
		return status;
	}
	
	private boolean isDirectoryActive() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		boolean status = accessAccountStatus.isServiceActive(personId, userid, AccessAccountServiceType.DIRECTORY);
		closeDbConnection();
		return status;
	}
	
	private boolean isPASSActive() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		boolean status = accessAccountStatus.isServiceActive(personId, userid, AccessAccountServiceType.PASS);
		closeDbConnection();
		return status;
	}

	@Test
	public void _01testIsServiceActive1() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		assertFalse(accessAccountStatus.isServiceActive(personId, userid, AccessAccountServiceType.KERBEROS));
		closeDbConnection();
	}
	
	@Test
	public void _02testIsServiceActive2() {
		addKerberos();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		assertTrue(accessAccountStatus.isServiceActive(personId, userid, AccessAccountServiceType.KERBEROS));
		closeDbConnection();
	}

	@Test
	public void _03testIsAccountNew1() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		assertTrue(accessAccountStatus.isAccountNew(personId, userid));
		closeDbConnection();
	}
	
	@Test
	public void _04testIsAccountNew2() {
		addKerberos();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		assertFalse(accessAccountStatus.isAccountNew(personId, userid));
		closeDbConnection();
		
	}
	@Test
	public void _05testAddService() {
		addKerberos();
		assertTrue(isKerberosActive());
	}
	
	@Test
	public void _06testAccessAccountStatus() {
		new AccessAccountStatus();
	}

	@Test
	public void _07testAccessAccountStatusStatelessSessionLong() {
		new AccessAccountStatus(statelessSession, messagingCore);
	}

	@Test
	public void _08testProcessStatusChange1() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processStatusChange(personId, userid, AccessAccountStatusType.FULL_ACCOUNT);
		closeDbConnection();
		assertTrue(isKerberosActive() && isEmailActive() && isDirectoryActive() && isPASSActive());
	}
	
	@Test
	public void _09testProcessStatusChange2() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processStatusChange(personId, userid, AccessAccountStatusType.PARTIAL_ACCOUNT);
		closeDbConnection();
		assertTrue(isKerberosActive() && isDirectoryActive() && (! isEmailActive()) && (! isPASSActive()));
	}
	
	@Test
	public void _10testProcessStatusChange3() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processStatusChange(personId, userid, AccessAccountStatusType.LOCKED_ACCOUNT);
		closeDbConnection();
		assertTrue((! isKerberosActive()) && (! isDirectoryActive()) && (! isEmailActive()) && (! isPASSActive()));

	}
	
	@Test
	public void _11testProcessStatusChange4() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processStatusChange(personId, userid, AccessAccountStatusType.DELETED_ACCOUNT);
		closeDbConnection();
		assertTrue((! isKerberosActive()) && (! isDirectoryActive()) && (! isEmailActive()) && (! isPASSActive()));
	}

	@Test
	public void _12testProcessLockedAccountStatus1() {
		addKerberos();
		addEmail();
		addDirectory();
		addPASS();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processLockedAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue((! isKerberosActive()) && (! isDirectoryActive()) && (! isEmailActive()) && (! isPASSActive()));

		
	}
	
	@Test
	public void _13testProcessLockedAccountStatus2() {
		addKerberos();
		addDirectory();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processLockedAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue((! isKerberosActive()) && (! isDirectoryActive()) && (! isEmailActive()) && (! isPASSActive()));
	}

	@Test
	public void _14testProcessFullAccountStatus1() {
		addKerberos();
		addEmail();
		addDirectory();
		addPASS();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processFullAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isEmailActive() && isDirectoryActive() && isPASSActive());
	}
	
	@Test
	public void _15testProcessFullAccountStatus2() {
		addKerberos();
		addDirectory();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processFullAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isEmailActive() && isDirectoryActive() && isPASSActive());
	}

	@Test
	public void _16testProcessFullAccountStatus3() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processFullAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isEmailActive() && isDirectoryActive() && isPASSActive());
	}
	
	@Test
	public void _17testProcessFullAccountStatus4() {
		addKerberos();
		addDirectory();
		lockKerberos();
		lockDirectory();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processFullAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isEmailActive() && isDirectoryActive() && isPASSActive());
	}

	@Test
	public void _18testProcessPartialAccountStatus1() {
		addKerberos();
		addDirectory();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processPartialAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isDirectoryActive() && (! isEmailActive()) && (! isPASSActive()));
	}
	
	@Test
	public void _19testProcessPartialAccountStatus2() {
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processPartialAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isDirectoryActive() && (! isEmailActive()) && (! isPASSActive()));
	}
	
	@Test
	public void _20testProcessPartialAccountStatus3() {
		addKerberos();
		addEmail();
		addDirectory();
		addPASS();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processPartialAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isDirectoryActive() && (! isEmailActive()) && (! isPASSActive()));
	}
	
	@Test
	public void _21testProcessPartialAccountStatus4() {
		addKerberos();
		addDirectory();
		lockKerberos();
		lockDirectory();
		openDbConnection();
		AccessAccountStatus accessAccountStatus = new AccessAccountStatus(statelessSession, messagingCore);
		accessAccountStatus.processPartialAccountStatus(personId, userid);
		closeDbConnection();
		assertTrue(isKerberosActive() && isDirectoryActive() && (! isEmailActive()) && (! isPASSActive()));
	}


}
