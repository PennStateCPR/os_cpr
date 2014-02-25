package edu.psu.iam.cpr.core.tests;

import edu.psu.iam.cpr.core.grouper.RAGroupData;
import edu.psu.iam.cpr.core.ui.Pair;
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
 * JUnit tests for Pair.
 *
 */
public class PairTest {

    /**
     * Test for {@link edu.psu.iam.cpr.core.ui.Pair#Pair()}
     */
    @Test
    public void testDefaultConstructor() {
        final Pair<String, String> pair = new Pair<String, String>();
        AssertJUnit.assertNull(pair.getKey());
        AssertJUnit.assertNull(pair.getValue());
    }

    /**
     * Test for {@link edu.psu.iam.cpr.core.ui.Pair#Pair(Object, Object)}
     */
    @Test
    public void testConstructorParameters() {
        final Pair<String, String> pair = new Pair<String, String>("foo", "bar");
        AssertJUnit.assertEquals("foo", pair.getKey());
        AssertJUnit.assertEquals("bar", pair.getValue());
    }

    /**
     * Test for {@link edu.psu.iam.cpr.core.ui.Pair#setKey(Object)} 
     */
    @Test
    public void testSetKey() {
        final Pair<String, String> pair = new Pair<String, String>("foo", "bar");
        pair.setKey("baz");
        AssertJUnit.assertEquals("baz", pair.getKey());
        AssertJUnit.assertEquals("bar", pair.getValue());
    }


    /**
     * Test for {@link edu.psu.iam.cpr.core.ui.Pair#setValue(Object)}
     */
    @Test
    public void testSetValue() {
        final Pair<String, String> pair = new Pair<String, String>("foo", "bar");
        pair.setValue("baz");
        AssertJUnit.assertEquals("foo", pair.getKey());
        AssertJUnit.assertEquals("baz", pair.getValue());
    }
}
