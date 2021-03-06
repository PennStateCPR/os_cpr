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

import edu.psu.iam.cpr.core.gi.GetPsuIdServiceReturn;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

public class GetPsuIdServiceReturnTest {

    @Test
    public void testDefaultConstructor() {
        final GetPsuIdServiceReturn getPsuIdServiceReturn = new GetPsuIdServiceReturn();
        AssertJUnit.assertNull(getPsuIdServiceReturn.getStatusMessage());
        AssertJUnit.assertNull(getPsuIdServiceReturn.getPsuId());
        AssertJUnit.assertEquals(0, getPsuIdServiceReturn.getStatusCode());
    }

    @Test
    public void testConstructorParameters() {
        final GetPsuIdServiceReturn getPsuIdServiceReturn = new GetPsuIdServiceReturn(100, "Status Message");
        AssertJUnit.assertEquals(100, getPsuIdServiceReturn.getStatusCode());
        AssertJUnit.assertEquals("Status Message", getPsuIdServiceReturn.getStatusMessage());
        AssertJUnit.assertNull(getPsuIdServiceReturn.getPsuId());
    }

    @Test
    public void testSetStatusCode() {
        final GetPsuIdServiceReturn getPsuIdServiceReturn = new GetPsuIdServiceReturn();
        getPsuIdServiceReturn.setStatusCode(100);
        AssertJUnit.assertEquals(100, getPsuIdServiceReturn.getStatusCode());
    }

    @Test
    public void testSetStatusMessage() {
        final GetPsuIdServiceReturn getPsuIdServiceReturn = new GetPsuIdServiceReturn();
        getPsuIdServiceReturn.setStatusMessage("Status Message");
        AssertJUnit.assertEquals("Status Message", getPsuIdServiceReturn.getStatusMessage());
    }

    @Test
    public void testSetPsuId() {
        final GetPsuIdServiceReturn getPsuIdServiceReturn = new GetPsuIdServiceReturn();
        getPsuIdServiceReturn.setPsuId("12345");
        AssertJUnit.assertEquals("12345", getPsuIdServiceReturn.getPsuId());
    }
}
