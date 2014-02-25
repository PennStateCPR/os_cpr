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

import edu.psu.iam.cpr.core.rules.engine.RulesReturn;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class RulesReturnTest {

    @Test
    public void testDefaultConstructor() {
        final RulesReturn rulesReturn = new RulesReturn();
        AssertJUnit.assertNotNull(rulesReturn);
        AssertJUnit.assertEquals(0, rulesReturn.getStatusCode());
        AssertJUnit.assertNull(rulesReturn.getStatusMessage());
        AssertJUnit.assertNull(rulesReturn.getFacts());
        AssertJUnit.assertEquals(0, rulesReturn.getNumberOfFacts());
    }

    @Test
    public void testConstructorParameters2() {
        final RulesReturn rulesReturn = new RulesReturn(15, "StatusMessage");
        AssertJUnit.assertNotNull(rulesReturn);
        AssertJUnit.assertEquals(15, rulesReturn.getStatusCode());
        AssertJUnit.assertEquals("StatusMessage", rulesReturn.getStatusMessage());
        AssertJUnit.assertNull(rulesReturn.getFacts());
        AssertJUnit.assertEquals(0, rulesReturn.getNumberOfFacts());
    }

    @Test
    public void testConstructorParameters4() {
        final RulesReturn rulesReturn = new RulesReturn(15, "StatusMessage",
                4, new String[] { "fact1", "fact2", "fact3", "fact4"});
        AssertJUnit.assertNotNull(rulesReturn);
        AssertJUnit.assertEquals(15, rulesReturn.getStatusCode());
        AssertJUnit.assertEquals("StatusMessage", rulesReturn.getStatusMessage());
        AssertJUnit.assertArrayEquals(new String[] { "fact1", "fact2", "fact3", "fact4"}, rulesReturn.getFacts());
        AssertJUnit.assertEquals(4, rulesReturn.getNumberOfFacts());
    }

    @Test
    public void testConstructorParameters4Null() {
        final RulesReturn rulesReturn = new RulesReturn(15, "StatusMessage",
                4, null);
        AssertJUnit.assertNotNull(rulesReturn);
        AssertJUnit.assertEquals(15, rulesReturn.getStatusCode());
        AssertJUnit.assertEquals("StatusMessage", rulesReturn.getStatusMessage());
        AssertJUnit.assertNull(rulesReturn.getFacts());
        AssertJUnit.assertEquals(4, rulesReturn.getNumberOfFacts());
    }

    @Test
    public void testSetStatusCode() {
        final RulesReturn rulesReturn = new RulesReturn();
        rulesReturn.setStatusCode(15);
        AssertJUnit.assertEquals(15, rulesReturn.getStatusCode());
    }

    @Test
    public void testSetStatusMessage() {
        final RulesReturn rulesReturn = new RulesReturn();
        rulesReturn.setStatusMessage("StatusMessage");
        AssertJUnit.assertEquals("StatusMessage", rulesReturn.getStatusMessage());
    }

    @Test
    public void testSetNumberOfFacts() {
        final RulesReturn rulesReturn = new RulesReturn();
        rulesReturn.setNumberOfFacts(100);
        AssertJUnit.assertEquals(100, rulesReturn.getNumberOfFacts());
    }

    @Test
    public void testSetFacts() {
        final RulesReturn rulesReturn = new RulesReturn();
        rulesReturn.setFacts(new String[] { "fact1", "fact2", "fact3", "fact4"});
        AssertJUnit.assertArrayEquals(new String[] { "fact1", "fact2", "fact3", "fact4"}, rulesReturn.getFacts());
    }

    @Test
    public void testSetFactsNull() {
        final RulesReturn rulesReturn = new RulesReturn();
        rulesReturn.setFacts(null);
        AssertJUnit.assertNull(rulesReturn.getFacts());
    }
}
