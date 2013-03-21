/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;


import edu.psu.iam.cpr.core.authorization.AuthorizationService;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;

/**
 * @author llg5
 *
 */
public class AuthorizationServiceTest {
	private static Database db = new Database();
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}
	AuthorizationService authorizationService = new AuthorizationService();
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.authorization.AuthorizationService#serviceAuthorized(edu.psu.iam.cpr.core.database.Database, java.lang.String, java.lang.String, java.lang.String, edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn)}.
	 * @throws Exception 
	 */
	@Test
	public final void testServiceAuthorized() throws Exception {
		try {
			openDbConnection();
			
			authorizationService.serviceAuthorized(db, UnitTestHelper.TEST_USERID, UnitTestHelper.TEST_REQUESTOR, "AddAddress", "*");
			db.closeSession();
		}
		catch (Exception e ) {
			throw e;
		}
 	}
	@Test (expectedExceptions=Exception.class)
	public final void testBadPrincipalId () throws Exception {
		
		openDbConnection();
		authorizationService.serviceAuthorized(db, "portal", UnitTestHelper.TEST_REQUESTOR, "AddAddress", "*");
		db.closeSession();
		
	}
	@Test (expectedExceptions=Exception.class)
	public final void testBadWebService () throws Exception {
		openDbConnection();
		authorizationService.serviceAuthorized(db, "portal1", UnitTestHelper.TEST_REQUESTOR, "Addsomething", "*");
		db.closeSession();
		
	}
	@Test (expectedExceptions=Exception.class)
	public final void testBadRequestor () throws Exception {
		openDbConnection();
		authorizationService.serviceAuthorized(db, "portal1", "llg", "Addsomething", "*");
		db.closeSession();
		
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.authorization.AuthorizationService#dataActionAuthorized(edu.psu.iam.cpr.core.database.Database, java.lang.String, java.lang.String, java.lang.String, edu.psu.iam.cpr.core.service.ServiceCoreReturn)}.
	 * @throws Exception 
	 */
	@Test
	public final void testDataActionAuthorized() throws Exception {
		boolean theResult = false;
		try {
			openDbConnection();
			 authorizationService.serviceAuthorized(db, UnitTestHelper.TEST_USERID, UnitTestHelper.TEST_REQUESTOR, "AddAddress", "*");
			 theResult = authorizationService.dataActionAuthorized(db, "LEGAL_NAME", "ACCESS_OPERATION_WRITE", UnitTestHelper.TEST_REQUESTOR);
			 AssertJUnit.assertTrue(theResult);
			 db.closeSession();
		}
		catch (Exception e ) {
			throw e;
		}
	}

	@Test
	public final void testDataActionAuthorizedBadRequestor() throws Exception {
		boolean theResult = false;
		try {
			openDbConnection();
			authorizationService.serviceAuthorized(db, UnitTestHelper.TEST_USERID, UnitTestHelper.TEST_REQUESTOR, "AddAddress", "*");
			theResult = authorizationService.dataActionAuthorized(db, "LEGAL_NAME", "ACCESS_OPERATION_WRITE", UnitTestHelper.TEST_REQUESTOR);
			AssertJUnit.assertTrue(theResult);
			db.closeSession();
		}
		catch (Exception e ) {
			throw e;
		}
	}
}
