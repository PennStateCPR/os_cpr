package edu.psu.iam.cpr.rest.handler;

import java.security.Principal;

import javax.ws.rs.core.Response;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.message.Message;
import org.apache.cxf.security.SecurityContext;

/**
 * This class implements a standard JAX-RS request handler.  This handler is responsible to intercepting the authentication
 * information from a request and storing it into a singleton class so that it can be accessed by a RESTful service. 
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
public class AuthorizationHandler implements RequestHandler {

	/**
	 * This method is used to handle authorization requests from the server.
	 * @param message contains the request message.
	 * @param resourceClass contains the resource class.
	 * 
	 * @return will return an HTTP response header.
	 */
	public Response handleRequest(Message message, ClassResourceInfo resourceClass) {
		try {
			AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);

			final String username = policy.getUserName();
			final String password = policy.getPassword();
			
			message.put(SecurityContext.class, new SecurityContext() {
				public Principal getUserPrincipal() {
					return new UserPrincipal(username, password);
				}
				public boolean isUserInRole(String role) {
					return false;
				}
			});

		}
		catch (Exception e) {
			return Response.status(401).header("WWW-Authenticate", "Basic").build();
		}
		return null;
	}
}
