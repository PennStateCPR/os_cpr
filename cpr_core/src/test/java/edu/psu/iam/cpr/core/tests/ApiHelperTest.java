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

import java.util.HashMap;
import java.util.Map;

import edu.psu.iam.cpr.core.api.BaseApi;
import edu.psu.iam.cpr.core.api.helper.ApiHelper;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;


public class ApiHelperTest {

    @Test
    public final void testDumpParametersNullInput() {
       AssertJUnit.assertEquals("", ApiHelper.dumpParameters(null));
    }

    @Test
    public final void testDumpParametersEmptyInput() {
        final Map<String, Object> parameterMap = new HashMap<String, Object>();
        AssertJUnit.assertEquals("", ApiHelper.dumpParameters(null));
    }

    @Test
    public final void testDumpParametersValidInput() {
        final Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put("param2", "value2");
        parameterMap.put("param1", "value1");
        parameterMap.put("param3", null);
        AssertJUnit.assertEquals("param1=[value1] param2=[value2] param3=[null]", ApiHelper.dumpParameters(parameterMap));
    }

    @Test
    public final void testDumpParametersSensitiveInputInput() {
        final Map<String, Object> parameterMap = new HashMap<String, Object>();
        parameterMap.put(BaseApi.SSN_KEY, "sensitiveSSN");
        parameterMap.put("param2", "value2");
        AssertJUnit.assertEquals("param2=[value2] ssn=[Sensitive Value Cannot Output]", ApiHelper.dumpParameters(parameterMap));
    }
}
