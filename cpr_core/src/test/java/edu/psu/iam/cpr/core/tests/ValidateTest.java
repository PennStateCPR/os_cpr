/**
 * 
 */
package edu.psu.iam.cpr.core.tests;

/**
 * Copyright 2012 The Pennsylvania State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.util.Validate;

/**
 * @author cpruser
 *
 */
public class ValidateTest {

	private static Database db = new Database();
	
	public static void openDbConnection()  {
		if (db.isSessionOpen()) {
			db.closeSession();
		}
		db.openSession(SessionFactoryUtil.getSessionFactory());
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidYesNo(java.lang.String)}.
	 */
	@Test
	public final void testIsValidYesNo1() {
		String retValue = Validate.isValidYesNo(null);
		AssertJUnit.assertEquals("N", retValue);
	}

    /**
     * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidYesNo(java.lang.String)}.
     */
    @Test
    public final void testIsValidYesNo2() {
        String retValue = Validate.isValidYesNo("");
        AssertJUnit.assertEquals("N", retValue);
    }

    /**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidYesNo(java.lang.String)}.
	 */
	@Test
	public final void testIsValidYesNo3() {
		String retValue = Validate.isValidYesNo("X");
		AssertJUnit.assertNull(retValue);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidYesNo(java.lang.String)}.
	 */
	@Test
	public final void testIsValidYesNo4() {
		String retValue = Validate.isValidYesNo("y");
		AssertJUnit.assertEquals("Y", retValue);
	}
	
	@Test
	public final void testIsValidYesNo5() {
		String retValue = Validate.isValidYesNo("yes");
		AssertJUnit.assertEquals("Y", retValue);
	}

    /**
     * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidYesNo(java.lang.String)}.
     */
    @Test
    public final void testIsValidYesNo6() {
        String retValue = Validate.isValidYesNo("n");
        AssertJUnit.assertEquals("N", retValue);
    }

    @Test
    public final void testIsValidYesNo7() {
        String retValue = Validate.isValidYesNo("no");
        AssertJUnit.assertEquals("N", retValue);
    }

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidPsuId(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPsuId1() {
		boolean retValue = Validate.isValidPsuId(null);
		AssertJUnit.assertFalse(retValue);
	}
	
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidPsuId(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPsuId2() {
		boolean retValue = Validate.isValidPsuId("");
		AssertJUnit.assertFalse(retValue);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidPsuId(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPsuId3() {
		boolean retValue = Validate.isValidPsuId("9");
		AssertJUnit.assertFalse(retValue);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidPsuId(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPsuId4() {
		boolean retValue = Validate.isValidPsuId("91234567890");
		AssertJUnit.assertFalse(retValue);
	}
	/**
	 * Test method for {@link edu.psu.iam.cpr.core.util.Validate#isValidPsuId(java.lang.String)}.
	 */
	@Test
	public final void testIsValidPsuId5() {
		boolean retValue = Validate.isValidPsuId("912345678");
		AssertJUnit.assertTrue(retValue);
	}

	@Test
	public final void testIsValidDate1() {
		boolean retValue = Validate.isValidDate(null);
		AssertJUnit.assertFalse(retValue);
	}
	@Test
	public final void testIsValidDate2() {
		boolean retValue = Validate.isValidDate("");
		AssertJUnit.assertFalse(retValue);
	}
	@Test
	public final void testIsValidDate3() {
		boolean retValue = Validate.isValidDate("m/d/y");
		AssertJUnit.assertFalse(retValue);
	}
	@Test
	public final void testIsValidDate4() {
		boolean retValue = Validate.isValidDate("1/1/1");
		AssertJUnit.assertTrue(retValue);
	}
	@Test
	public final void testIsValidDate5() {
		boolean retValue = Validate.isValidDate("12/33/2010");
		AssertJUnit.assertFalse(retValue);
	}
	@Test
	public final void testIsValidDate6() {
		boolean retValue = Validate.isValidDate("12/31/2010");
		AssertJUnit.assertTrue(retValue);
	}
	@Test
	public final void testIsValidPartialDate1() {
		boolean retValue = Validate.isValidPartialDate(null);
		AssertJUnit.assertFalse(retValue);
	}
	@Test
	public final void testIsValidPartialDate2() {
		boolean retValue = Validate.isValidPartialDate("");
		AssertJUnit.assertFalse(retValue);
	}
	@Test
	public final void testIsValidPartialDate3() {
		boolean retValue = Validate.isValidPartialDate("1");
		AssertJUnit.assertFalse(retValue);
	}
	@Test
	public final void testIsValidPartialDate4() {
		boolean retValue = Validate.isValidPartialDate("1/1");
		AssertJUnit.assertTrue(retValue);
	}
	
}
