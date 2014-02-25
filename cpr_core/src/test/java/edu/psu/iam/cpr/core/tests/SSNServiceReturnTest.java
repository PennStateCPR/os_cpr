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

import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.gi.SSNServiceReturn;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class SSNServiceReturnTest {

    @Test
    public void testConstructor() {
        final SSNServiceReturn ssnServiceReturn = new SSNServiceReturn();
        AssertJUnit.assertNotNull(ssnServiceReturn);
        AssertJUnit.assertNull(ssnServiceReturn.getStatusMessage());
        AssertJUnit.assertNull(ssnServiceReturn.getPsuId());
        AssertJUnit.assertNull(ssnServiceReturn.getStatusCode());
    }

    @Test
    public void testSetStatusCode() {
        final SSNServiceReturn ssnServiceReturn = new SSNServiceReturn();
        ssnServiceReturn.setStatusCode(ReturnType.SUCCESS);
        AssertJUnit.assertEquals(ReturnType.SUCCESS, ssnServiceReturn.getStatusCode());
    }

    @Test
    public void testSetStatusMessage() {
        final SSNServiceReturn ssnServiceReturn = new SSNServiceReturn();
        ssnServiceReturn.setStatusMessage("Status Message");
        AssertJUnit.assertEquals("Status Message", ssnServiceReturn.getStatusMessage());
    }

    @Test
    public void testSetPsuId() {
        final SSNServiceReturn ssnServiceReturn = new SSNServiceReturn();
        ssnServiceReturn.setPsuId("12345");
        AssertJUnit.assertEquals("12345", ssnServiceReturn.getPsuId());
    }
}
