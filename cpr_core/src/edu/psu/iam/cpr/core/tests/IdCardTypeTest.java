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


import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;
import edu.psu.iam.cpr.core.database.types.IdCardType;


/**
 * @author cpruser
 *
 */
public class IdCardTypeTest {

	@Test
	public final void testIndex() {
		assertEquals(IdCardType.ID_CARD_ID_PLUS_CARD_STUDENT.index(),327);
	}

	@Test
	public final void testGet() {
		assertEquals(IdCardType.get(327), IdCardType.ID_CARD_ID_PLUS_CARD_STUDENT);
	}
}
