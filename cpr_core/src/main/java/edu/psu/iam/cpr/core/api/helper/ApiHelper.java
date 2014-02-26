package edu.psu.iam.cpr.core.api.helper;

import static edu.psu.iam.cpr.core.api.BaseApi.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.jms.JMSException;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.CprComponentStatusTable;
import edu.psu.iam.cpr.core.database.types.CprComponent;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.messaging.MessagingCore;
import edu.psu.iam.cpr.core.util.Validate;
import edu.psu.iam.cpr.core.util.ValidateSSN;

/**
 * ApiHelper is a utility class that provides a series of convenience
 * functions that can be used by APIs.
 * 
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
 *
 * @package edu.psu.iam.cpr.core.api
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public final class ApiHelper {
	
	/** Success message returned to an API and/or service */
	public static final String SUCCESS_MESSAGE = "SUCCESS!";
	
	/** Flag that indicates to do the authZ check */
	public static final boolean DO_AUTHZ_CHECK = true;
	
	/** Flag that indicates to skip the authZ check */
	public static final boolean SKIP_AUTHZ_CHECK = false;
	
	/** Buffer size */
	private static final int BUFFER_SIZE = 4096;
	
	/** array of keys for sensitive parameters that should not be logged */
	protected static final Set<String> PROTECTED_KEYS = Collections.unmodifiableSet(new TreeSet<String>(Arrays.asList(
			SSN_KEY, PSUID_KEY, DOB_KEY)));
	
	/**
	 * No constructor!
	 */
	private ApiHelper() {
		
	}
	
	/**
	 * This routine is used to send messages to service provisioners.
	 * @param serviceName contains the name of the service/api.
	 * @param db contains the database connection.
	 * @param jsonMessage contains the formatted JSON message.
	 * @throws JMSException will be thrown if there are any jms errors.
	 * @throws CprException will be thrown if there are any CPR problems.
	 * @throws JSONException will be thrown if there are any json issues.
	 */
	public static void sendMessagesToServiceProviders(final String serviceName,
			final Database db, final JsonMessage jsonMessage) throws JMSException, CprException, JSONException {

		MessagingCore mCore = null;
		try {
			final CprComponentStatusTable cprComponentStatusTable = new CprComponentStatusTable();

			// Is messaging active?
			if (cprComponentStatusTable.isCprComponentActive(db, CprComponent.MESSAGING)) {
				mCore = new MessagingCore(db, serviceName);
				mCore.initializeMessaging();
				mCore.sendMessage(db, jsonMessage);
			}

			// Messaging is down, so we need to queue up all of the messages.
			else {
				mCore = new MessagingCore(db, serviceName);
				mCore.recordFailures(db, jsonMessage);
			}
		}
		finally {
			try {
				mCore.closeMessaging();
			}
			catch (Exception e) {
			}
		}
	}
	
	/**
	 * This routine is used to dump the parameters to an API.
	 * @param otherParameters contains the map of parameters to be dumped.
	 * @return will return a string object.
	 */
	public static String dumpParameters(final Map<String, Object> otherParameters) {
		
		if (otherParameters == null || otherParameters.isEmpty()) {
			return "";
		}

		final StringBuilder parameters = new StringBuilder(BUFFER_SIZE);

		final SortedSet<String> sortedKeys = new TreeSet<String>(otherParameters.keySet());
		for (String key : sortedKeys) {
						
			parameters.append(key);
			parameters.append("=[");
		
			final Object o = otherParameters.get(key);
			String value;
			if (o != null) {
				value = o.toString();
			} 
			else {
				value = "null";
			}
						
			if (PROTECTED_KEYS.contains(key) || ValidateSSN.validateSSN(value) || Validate.isValidPsuId(value)) {
				parameters.append("Sensitive Value Cannot Output");
			} 
			else {
				parameters.append(value);
			}
			parameters.append("] ");			
		}
		
		return parameters.toString().trim();
	}

}
