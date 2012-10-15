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
import edu.psu.iam.cpr.core.database.types.CprRunningMode;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.ValidateSSN;

/**
 * JUnit tests for ValidateSSN.
 * 
 * @author dvm105
 *
 */
public class ValidateSSNTest {

	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testNullSSN() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN(null), false);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testValidSSNWithoutSeperators() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("123456789"), true);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testValidSSNWithHyphenSeperators() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("123-45-6789"), true);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testValidSSNWithSpaceSeperators() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("123 45 6789"), true);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testInvalidSSNAreaZeros() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("000-11-2222"), false);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testInvalidSSNGroupZeros() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("111-00-2222"), false);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testInvalidSSNSerialZeros() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("111-22-0000"), false);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testInvalidSSNGroup666() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("666-11-2222"), false);
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testInvalidSSNArea900() {
		if (CprProperties.getInstance().getCprMode() == CprRunningMode.TEST ||
				CprProperties.getInstance().getCprMode() == CprRunningMode.ACCEPTANCE) {
			AssertJUnit.assertEquals(ValidateSSN.validateSSN("900-11-2222"), true);
		}
		else if (CprProperties.getInstance().getCprMode() == CprRunningMode.PRODUCTION) {
			AssertJUnit.assertEquals(ValidateSSN.validateSSN("900-11-2222"), false);
		}
	}
	
	/**
	 * Test for {@link edu.psu.iam.cpr.core.util.ValidateSSN#validateSSN(String)}
	 */
	@Test
	public void testInvalidSSNAdvertising() {
		AssertJUnit.assertEquals(ValidateSSN.validateSSN("987-65-4320"), false);
	}
	
	/**
	 * Test for @{link eedu.psu.iam.cpr.core.util.ValidateSSN#extractNumericSSN(String) }
	 */
	@Test
	public void testSSNWithHypenSeperators() {
		AssertJUnit.assertEquals(ValidateSSN.extractNumericSSN("123-45-6789"), "123456789");
	}

	/**
	 * Test for @{link edu.psu.iam.cpr.core.util.ValidateSSN#extractNumericSSN(String) }
	 */
	@Test
	public void testSSNWithSpaceSeperators() {
		AssertJUnit.assertEquals(ValidateSSN.extractNumericSSN("123 45 6789"), "123456789");
	}

	/**
	 * Test for @{link edu.psu.iam.cpr.core.util.ValidateSSN#extractNumericSSN(String) }
	 */
	@Test
	public void testSSNWithoutSeperators() {
		AssertJUnit.assertEquals(ValidateSSN.extractNumericSSN("123456789"), "123456789");
	}

	/**
	 * Test for @{link edu.psu.iam.cpr.core.util.ValidateSSN#extractNumericSSN(String) }
	 */
	@Test
	public void testInvalidSSN() {
		AssertJUnit.assertEquals(ValidateSSN.extractNumericSSN("abc123foobar"), "");
	}
}
