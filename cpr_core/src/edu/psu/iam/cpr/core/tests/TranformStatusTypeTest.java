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
import edu.psu.iam.cpr.core.database.types.TransformStatusType;


/**
 * @author llg5
 *
 */
public class TranformStatusTypeTest {

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.types.TransformStatusType#index()}.
	 */
@Test
	public final void testIndex() {
		AssertJUnit.assertEquals(TransformStatusType.VALID.index(), 1);
	}

	/**
	 * Test method for {@link edu.psu.iam.cpr.core.database.types.TransformStatusType#get(int)}.
	 */
@Test
	public final void testGet() {
		AssertJUnit.assertEquals(TransformStatusType.get(1),TransformStatusType.VALID);
	}

}
