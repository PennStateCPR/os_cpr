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

import edu.psu.iam.cpr.core.rules.engine.InputFact;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class InputFactTest {

    @Test
    public void testDefaultConstructor() {
        final InputFact inputFact = new InputFact();
        AssertJUnit.assertNull(inputFact.getFact());
    }

    @Test
    public void testConstructorParameters() {
        final InputFact inputFact = new InputFact("fact");
        AssertJUnit.assertEquals("fact", inputFact.getFact());
    }

    @Test
    public void testSetFact() {
        final InputFact inputFact = new InputFact();
        inputFact.setFact("fact");
        AssertJUnit.assertEquals("fact", inputFact.getFact());
    }
}