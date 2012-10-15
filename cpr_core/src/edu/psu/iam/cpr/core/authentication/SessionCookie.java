/* SVN FILE: $Id: SessionCookie.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.authentication;

/**
 * SessionCookie is a utility class that is used by all of the services
 * within the CPR to authenticate client calls to a service.  It provides
 * a class that is used to store session cookie information.
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
public class SessionCookie {
	
	/** Contains the service principal */
	private String principal;
	
	/** Contains the hashed cookie value */
	private String cookie;
	
	/** Contains the timestamp of when the cookie was created */
	private long timeValue;

	/**
	 * Constructor.
	 */
	public SessionCookie() {
		super();
	}

	/**
	 * Constructor
	 * @param principal contains the service principal.
	 * @param cookie contains the session cookie.
	 * @param timeValue contains the timestamp of the cookie.
	 */
	public SessionCookie(String principal, String cookie, long timeValue) {
		super();
		this.principal = principal;
		this.cookie = cookie;
		this.timeValue = timeValue;
	}

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the cookie
	 */
	public String getCookie() {
		return cookie;
	}

	/**
	 * @param cookie the cookie to set
	 */
	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	/**
	 * @return the timeValue
	 */
	public long getTimeValue() {
		return timeValue;
	}

	/**
	 * @param timeValue the timeValue to set
	 */
	public void setTimeValue(long timeValue) {
		this.timeValue = timeValue;
	}
}
