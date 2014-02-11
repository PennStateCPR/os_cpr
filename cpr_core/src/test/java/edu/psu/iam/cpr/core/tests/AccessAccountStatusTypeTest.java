package edu.psu.iam.cpr.core.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import edu.psu.iam.cpr.core.database.types.AccessAccountStatusType;

public class AccessAccountStatusTypeTest {

	@Test
	public void _01testKey() {
		AssertJUnit.assertEquals(AccessAccountStatusType.FULL_ACCOUNT.key(), "J");
	}

	@Test
	public void _02testGet() {
		AssertJUnit.assertEquals(AccessAccountStatusType.get("J"), AccessAccountStatusType.FULL_ACCOUNT);
	}

}
