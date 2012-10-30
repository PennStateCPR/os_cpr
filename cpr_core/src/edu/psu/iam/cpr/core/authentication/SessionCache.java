/* SVN FILE: $Id: SessionCache.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.authentication;

import java.util.Date;
import java.util.HashMap;

/**
 * SessionCache is a singleton class that provides a hash table
 * containing session cookie information.
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
 * @package edu.psu.iam.cpr.core.authentication
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public enum SessionCache {

	INSTANCE;
	
	/** Contains the credential cookie hash table */
	private static HashMap<String, SessionCookie> credsMap = new HashMap<String, SessionCookie>();
	
	/** Contains the expiration time of a session within the cache. */
	private static final int SESSION_EXPIRATION_TIME_MINUTES = 15;
	
	/** Contains the value of one minute in milliseconds */
	private static final int ONE_MINUTE_IN_MS = 60000;
	
	/**
	 * This routine determine if a session cookie is valid or not.
	 * @param cookie contains the session cookie to be validated.
	 * @return will return true if the cookie is still valid, otherwise it will return false.
	 */
	public boolean isSessionValid(SessionCookie cookie) {
		
		// Determine if the principal exists in the map.
		SessionCookie hashCookie = credsMap.get(cookie.getPrincipal());
		if (hashCookie == null) {
			return false;
		}
		
		// Are the cookie hash values the same?
		if (! hashCookie.getCookie().equals(cookie.getCookie())) {
			credsMap.remove(cookie.getPrincipal());
			return false;
		}
		
		// Check to see if the session has expired.
		final Date d = new Date();
		final int diff = (int) ((d.getTime()/ONE_MINUTE_IN_MS) - (hashCookie.getTimeValue()/ONE_MINUTE_IN_MS));
		if (diff > SESSION_EXPIRATION_TIME_MINUTES) {
			credsMap.remove(cookie.getPrincipal());
			return false;
		}

		// At this point we are valid!
		return true;
		
	}
	
	/**
	 * This routine is used to store a session cookie in the cache.
	 * @param cookie contains the cookie to be stored.
	 */
	public void storeSession(SessionCookie cookie) {

		// Verify that the cookie does not exist in the cache or not.
		if (credsMap.get(cookie.getPrincipal()) == null) {
			credsMap.put(cookie.getPrincipal(), cookie);
		}
	}
}
