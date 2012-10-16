/* SVN FILE: $Id: ServiceInterface.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.helper;

/**
 * Service interface.
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
 * @package edu.psu.iam.cpr.service.helper
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public interface ServiceInterface {

	/**
	 * This method will be implemented by a class that implements this information.  It provides a generic method for calling
	 * a service.
	 * @param serviceName contains the name of the service to be called.
	 * @param ipAddress contains the remote address IP of the caller.
	 * @param principalId contains the service principal that will be used to authenticate the service.
	 * @param password contain the password for the service principal that will be used to authenticate the service.
	 * @param updatedBy contains the userid of the requestor or the person who last updated the record.
	 * @param identifierType contains the identifier type that is used to find the user to be updated.
	 * @param identifier contains the value of the identifier.
	 * @param otherParameters contains an array of objects that are additional parameters to the service.
	 * @return will return an object that contains the result of executing the service.
	 */
	public Object implementService(
			String serviceName,
			String ipAddress,
			String principalId,
			String password,
			String updatedBy,
			String identifierType,
			String identifier,
			Object[] otherParameters);
}
