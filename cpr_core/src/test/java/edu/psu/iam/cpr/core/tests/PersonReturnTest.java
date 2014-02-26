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

import edu.psu.iam.cpr.core.service.returns.PersonReturn;

public class PersonReturnTest {

	@Test
	public final void testPersonReturn() {
		new PersonReturn();
	}

	@Test
	public final void testPersonReturnInt() {
		new PersonReturn(100000);
	}

	@Test
	public final void testSetPersonId() {
		final PersonReturn p = new PersonReturn();
		p.setPersonId(100000);
		AssertJUnit.assertEquals(100000, p.getPersonId());
	}

	@Test
	public final void testGetPersonId() {
		final PersonReturn p = new PersonReturn();
		p.setPersonId(100000);
		AssertJUnit.assertEquals(100000, p.getPersonId());
	}

    @Test
    public final void testGetPersonUri() {
        final PersonReturn p = new PersonReturn();
        p.setUri("personUri");
        AssertJUnit.assertEquals("personUri", p.getUri());
    }
}
