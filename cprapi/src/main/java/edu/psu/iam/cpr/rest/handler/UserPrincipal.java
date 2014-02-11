package edu.psu.iam.cpr.rest.handler;

import java.security.Principal;

/**
 * This class implements subclasses Principal to provide a mechanism by which the central
 * authentication for the CPR can be used.
 * 
 * Copyright 2013 The Pennsylvania State University
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
 * @package edu.psu.iam.cpr.rest.handler
 * @author $Author: jvuccolo $
 */
public class UserPrincipal implements Principal {

	/** Contains the principal's name. */
	private String name;
	
	/** Contains the principal's password */
	private String password;
	
	/**
	 * Constructor
	 * @param name contains the principal's name.
	 */
	public UserPrincipal(String name) {
		this(name, null);
	}
	
	/**
	 * Constructor.
	 * @param name contains the principal's name.
	 * @param password contains the principal's password.
	 */
	public UserPrincipal(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	/**
	 * @return Return the name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return the password.
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
}
