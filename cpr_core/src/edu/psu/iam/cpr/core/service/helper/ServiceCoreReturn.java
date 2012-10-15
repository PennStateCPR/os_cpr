/* SVN FILE: $Id: ServiceCoreReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.helper;

import edu.psu.iam.cpr.core.database.tables.ServiceLogTable;
import edu.psu.iam.cpr.core.grouper.GrouperPermissions;

/**
 * This class provides an implementation of functions that represent the results of initializing a service,
 * this data is returned back to the service so that it can interface with the database and messaging.
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
 * @package edu.psu.iam.cpr.core.service.helper
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class ServiceCoreReturn {
	
	/** Contains the person identifier. */
	private long personId = -1;
	
	/** Contains the service log table. */
	private ServiceLogTable serviceLogTable;
	
	/** IAM Group Key. */
	private long IamGroupKey;
	
	/** Registration Authority Key. */
	private long registrationAuthorityKey;
	
	/** grouper data */
	private GrouperPermissions grouperPerm;
	/**
	 * Constructor.
	 */
	public ServiceCoreReturn() {
		super();
	}
	
	/**
	 * @return the personId
	 */
	public long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(long personId) {
		this.personId = personId;
	}

	/**
	 * @param serviceLogTable the serviceLogTable to set
	 */
	public void setServiceLogTable(ServiceLogTable serviceLogTable) {
		this.serviceLogTable = serviceLogTable;
	}

	/**
	 * @return the serviceLogTable
	 */
	public ServiceLogTable getServiceLogTable() {
		return serviceLogTable;
	}

	/**
	 * @param iamGroupKey the iamGroupKey to set
	 */
	public void setIamGroupKey(long iamGroupKey) {
		IamGroupKey = iamGroupKey;
	}

	/**
	 * @return the iamGroupKey
	 */
	public long getIamGroupKey() {
		return IamGroupKey;
	}

	/**
	 * @param registrationAuthorityKey the registrationAuthorityKey to set
	 */
	public void setRegistrationAuthorityKey(long registrationAuthorityKey) {
		this.registrationAuthorityKey = registrationAuthorityKey;
	}

	/**
	 * @return the registrationAuthorityKey
	 */
	public long getRegistrationAuthorityKey() {
		return registrationAuthorityKey;
	}

	/**
	 * @return the grouperPerm
	 */
	public GrouperPermissions getGrouperPerm() {
		return grouperPerm;
	}

	/**
	 * @param grouperPerm the grouperPerm to set
	 */
	public void setGrouperPerm(GrouperPermissions grouperPerm) {
		this.grouperPerm = grouperPerm;
	}	
}
