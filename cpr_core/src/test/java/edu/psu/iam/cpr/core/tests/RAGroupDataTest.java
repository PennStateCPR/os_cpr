package edu.psu.iam.cpr.core.tests;

import edu.psu.iam.cpr.core.grouper.RAGroupData;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

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


/**
 * JUnit tests for RAGroupData.
 *
 */
public class RAGroupDataTest {

    /**
     * Test for {@link edu.psu.iam.cpr.core.grouper.RAGroupData#RAGroupData()}
     */
    @Test
    public void testDefaultConstructor() {
        final RAGroupData raGroupData = new RAGroupData();
        AssertJUnit.assertNull(raGroupData.getRegistrationAuthorityGroup());
        AssertJUnit.assertFalse(raGroupData.isRegistrationAuthoritySuspendFlag());
    }

    /**
     * Test for {@link edu.psu.iam.cpr.core.grouper.RAGroupData#RAGroupData(String, boolean)}
     */
    @Test
    public void testConstructorParameters() {
        final RAGroupData raGroupData = new RAGroupData("foo", true);
        AssertJUnit.assertEquals(raGroupData.getRegistrationAuthorityGroup(), "foo");
        AssertJUnit.assertTrue(raGroupData.isRegistrationAuthoritySuspendFlag());
    }

    /**
     * Test for {@link edu.psu.iam.cpr.core.grouper.RAGroupData#setRegistrationAuthoritySuspendFlag(boolean)}
     */
    @Test
    public void testSetRegistrationAuthoritySuspendFlag() {
        final RAGroupData raGroupData = new RAGroupData();
        raGroupData.setRegistrationAuthoritySuspendFlag(false);
        AssertJUnit.assertFalse(raGroupData.isRegistrationAuthoritySuspendFlag());
    }

    /**
     * Test for {@link edu.psu.iam.cpr.core.grouper.RAGroupData#setRegistrationAuthorityGroup(String)}
     */
    @Test
    public void testSetRegistrationAuthorityGroup() {
        final RAGroupData raGroupData = new RAGroupData();
        raGroupData.setRegistrationAuthorityGroup("bar");
        AssertJUnit.assertEquals(raGroupData.getRegistrationAuthorityGroup(), "bar");
    }
}
