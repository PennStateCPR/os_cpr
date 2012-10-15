/* SVN FILE: $Id: RAGroupData.java 5340 2012-09-27 14:48:52Z jvuccolo $ */


package edu.psu.iam.cpr.core.grouper;

/**
 *  This class provides an implementation for interfacing with the Grouper.  There are methods
 *  here to send REST request to grouper and process the response.
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
 *  
 * @package edu.psu.iam.cpr.core.grouper
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class RAGroupData {

	/**
	 *  Contains the Group name for the registration authority
	 */
	private String registrationAuthorityGroup;
	
	/**
	 * Contains the suspend status of the registration authority
	 */
	
	private boolean registrationAuthoritySuspendFlag;
	
	/**
	 * @return the registrationAuthorityGroup
	 */
	public String getRegistrationAuthorityGroup() {
		return registrationAuthorityGroup;
	}

	/**
	 * @param registrationAuthorityGroup the registrationAuthorityGroup to set
	 */
	public void setRegistrationAuthorityGroup(String registrationAuthorityGroup) {
		this.registrationAuthorityGroup = registrationAuthorityGroup;
	}

	/**
	 * @return the registrationAuthoritySuspendFlag
	 */
	public boolean isRegistrationAuthoritySuspendFlag() {
		return registrationAuthoritySuspendFlag;
	}

	/**
	 * @param registrationAuthoritySuspendFlag the registrationAuthoritySuspendFlag to set
	 */
	public void setRegistrationAuthoritySuspendFlag(
			boolean registrationAuthoritySuspendFlag) {
		this.registrationAuthoritySuspendFlag = registrationAuthoritySuspendFlag;
	}
	

	/**
	 * 
	 */
	public RAGroupData() {
		super();
		
	}

	/**
	 * @param registrationAuthorityGroup
	 * @param registrationAuthoritySuspendFlag
	 */
	public RAGroupData(String registrationAuthorityGroup,
			boolean registrationAuthoritySuspendFlag) {
		super();
		this.registrationAuthorityGroup = registrationAuthorityGroup;
		this.registrationAuthoritySuspendFlag = registrationAuthoritySuspendFlag;
	}

	}	
