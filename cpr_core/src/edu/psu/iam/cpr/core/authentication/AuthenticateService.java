/* SVN FILE: $Id: AuthenticateService.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.authentication;

import java.util.Hashtable;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import edu.psu.iam.cpr.core.database.types.AuthenticationType;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;

/**
 * AuthenticateService is a utility class that is used by all of the services
 * within the CPR to authenticate client calls to a service.  Its provides a 
 * single static method that performs the authentication.
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
public final class AuthenticateService {
	
	private static final int BUFFER_SIZE = 1024;
	private static final String EQUALS = "=";
	private static final String COMMA = ",";

	/**
	 * Constructor.
	 */
	private AuthenticateService() {
		
	}
	
	/**
	 * Performs authentication for a service principal/password against
	 * an LDAP server using JNDI.
	 * 
	 * @param userid  the service principal identifier.
	 * @param password  the password for the service principal.
	 * @throws CprException 
	 * @throws NamingException will be thrown for LDAP authentication issues.
	 */
	public static void authenticate(String userid, String password) throws CprException, NamingException {

		final Properties props = CprProperties.INSTANCE.getProperties();
		DirContext ctx = null;
		final Hashtable<String,String> env = new Hashtable<String,String>();

		// Verify that a service principal and a password were specified.
		if (userid == null || userid.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Service principal");
		}

		if (password == null || password.length() == 0) {
			throw new CprException(ReturnType.NOT_SPECIFIED_EXCEPTION, "Service principal's password");
		}

		// LDAP Authentication.
		if (props.getProperty(CprPropertyName.CPR_SERVICE_AUTHENTICATION.toString()).equalsIgnoreCase(
				AuthenticationType.LDAP_AUTHENTICATION.toString())) {

			// Convert the service principal to a DN.
			final StringBuilder dn = new StringBuilder(BUFFER_SIZE);
			dn.append(props.getProperty(CprPropertyName.CPR_LDAP_PEOPLE_DN_PREFIX.toString()));
			dn.append(EQUALS);
			dn.append(userid);
			dn.append(COMMA);
			dn.append(props.getProperty(CprPropertyName.CPR_LDAP_PEOPLE_BASE_DN.toString()));

			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, props.getProperty(CprPropertyName.CPR_LDAP_URL.toString()));
			env.put(Context.SECURITY_AUTHENTICATION, props.getProperty(CprPropertyName.CPR_LDAP_SECURITY_AUTHENTICATION.toString()));
			env.put(Context.SECURITY_PROTOCOL, props.getProperty(CprPropertyName.CPR_LDAP_SECURITY_PROTOCOL.toString()));
			env.put(Context.SECURITY_PRINCIPAL, dn.toString());
			env.put(Context.SECURITY_CREDENTIALS, password);

			try {
				ctx = new InitialDirContext(env);
			}
			finally {
				try {
					ctx.close();
				}
				catch (Exception e) {
				}

			}
		}

		// Properties Authentication.
		else if (props.getProperty(CprPropertyName.CPR_SERVICE_AUTHENTICATION.toString()).equalsIgnoreCase(
				AuthenticationType.PROPERTIES_AUTHENTICATION.toString())) {
			String serviceUser = props.getProperty(CprPropertyName.CPR_SERVICE_USERID.toString());
			String servicePassword = props.getProperty(CprPropertyName.CPR_SERVICE_PASSWORD.toString());
			if ((! serviceUser.equals(userid)) || (! servicePassword.equals(password))) {
				throw new CprException(ReturnType.SERVICE_AUTHENTICATION_EXCEPTION);
			}
		}
	}
}

