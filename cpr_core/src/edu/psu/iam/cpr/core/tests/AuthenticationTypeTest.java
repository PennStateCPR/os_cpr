package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.database.types.AuthenticationType;




public class AuthenticationTypeTest {
	@Test
	public final void _01testToString() {
		AssertJUnit.assertEquals(AuthenticationType.LDAP_AUTHENTICATION.toString(), "ldap");
	}

}
