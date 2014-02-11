package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;



import edu.psu.iam.cpr.core.database.types.AccessAccountServiceType;

public class AccessAccountServiceTypeTest {

	@Test
	public void _01testIndex() {
		AssertJUnit.assertEquals(AccessAccountServiceType.KERBEROS.index(),1L);
	}

	@Test
	public void _02testGet() {
		AssertJUnit.assertEquals(AccessAccountServiceType.get(1L), AccessAccountServiceType.KERBEROS);
	}

}
