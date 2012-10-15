/* SVN FILE: $Id: EmailAddressTypeTest.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
 * 
 * @package edu.psu.iam.cpr.core.tests
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
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
import edu.psu.iam.cpr.core.database.types.EmailAddressType;

/**
 * @author jvuccolo
 *
 */
public class EmailAddressTypeTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.types.EmailAddressType#index()}.
	 */
	@Test
	public final void testIndex() {
		AssertJUnit.assertEquals(EmailAddressType.OTHER_EMAIL.index(), 270);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.types.EmailAddressType#get(int)}.
	 */
	@Test
	public final void testGet() {
		AssertJUnit.assertEquals(EmailAddressType.get(270), EmailAddressType.OTHER_EMAIL);
	}

}
