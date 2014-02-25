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

import edu.psu.iam.cpr.core.gi.AddSsnServiceReturn;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class AddSsnServiceReturnTest{

    @Test
    public void testDefaultConstructor() {
        final AddSsnServiceReturn addSsnServiceReturn = new AddSsnServiceReturn();
        AssertJUnit.assertNull(addSsnServiceReturn.getStatusMessage());
        AssertJUnit.assertEquals(0, addSsnServiceReturn.getStatusCode());
    }

    @Test
    public void testConstructorParameters() {
        final AddSsnServiceReturn addSsnServiceReturn = new AddSsnServiceReturn(100, "Status Message");
        AssertJUnit.assertEquals(100, addSsnServiceReturn.getStatusCode());
        AssertJUnit.assertEquals("Status Message", addSsnServiceReturn.getStatusMessage());
    }

    @Test
    public void testSetStatusCode() {
        final AddSsnServiceReturn addSsnServiceReturn = new AddSsnServiceReturn();
        addSsnServiceReturn.setStatusCode(100);
        AssertJUnit.assertEquals(100, addSsnServiceReturn.getStatusCode());
    }

    @Test
    public void testSetStatusMessage() {
        final AddSsnServiceReturn addSsnServiceReturn = new AddSsnServiceReturn();
        addSsnServiceReturn.setStatusMessage("Status Message");
        AssertJUnit.assertEquals("Status Message", addSsnServiceReturn.getStatusMessage());
    }
}
