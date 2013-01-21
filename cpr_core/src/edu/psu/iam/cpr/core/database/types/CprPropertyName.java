/* SVN FILE: $Id: CprPropertyName.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.types;

/**
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
 * CprRunning Modes Type
 * @package edu.psu.iam.cpr.core.database.types
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public enum CprPropertyName {
	
	CPR_JMS_BROKER("cpr.jms.broker"),
	CPR_JMS_USERID("cpr.jms.userid"),
	CPR_JMS_PASSWORD("cpr.jms.password"),
	CPR_JMS_REPLYTO("cpr.jms.replyto"),
	CPR_GI_APPLICATION("cpr.gi.application"),
	CPR_GI_USERID("cpr.gi.userid"),
	CPR_GI_TEST_URL("cpr.gi.test.url"),
	CPR_GI_PRODUCTION_URL("cpr.gi.production.url"),
	CPR_GI_PASSWORD("cpr.gi.password"),
	CPR_DQ_SERVER("cpr.dq.server"),
	CPR_DQ_TRANSPORT("cpr.dq.transport"),
	CPR_DQ_LOGFILE("cpr.dq.logfile"),
	CPR_FORMAT_DATE("cpr.format.date"),
	CPR_FORMAT_PARTIAL_DATE("cpr.format.partial.date"),
	CPR_FORMAT_RAW_DATE("cpr.format.raw.date"),
	CPR_FORMAT_TIMESTAMP("cpr.format.timestamp"),
	CPR_DATE_SEPARATOR("cpr.date.separator"),
	CPR_LDAP_URL("cpr.ldap.url"),
	CPR_LDAP_PEOPLE_BASE_DN("cpr.ldap.people.base.dn"),
	CPR_LDAP_PEOPLE_DN_PREFIX("cpr.ldap.people.dn.prefix"),
	CPR_LDAP_SECURITY_AUTHENTICATION("cpr.ldap.security.authentication"),
	CPR_LDAP_SECURITY_PROTOCOL("cpr.ldap.security.protocol"),
	CPR_REGEX_US_PHONE_NUMBER("cpr.regex.us.phone.number"),
	CPR_REGEX_INT_PHONE_NUMBER("cpr.regex.int.phone.number"),
	CPR_REGEX_PHONE_NUMBER_EXTENSION("cpr.regex.phone.number.extension"),
	CPR_REGEX_SSN("cpr.regex.ssn"),
	CPR_REGEX_PSU_ID_NUMBER("cpr.regex.psu.id.number"),
	CPR_REGEX_STATE("cpr.regex.state"),
	CPR_REGEX_CAMPUS_CODE("cpr.regex.campus.code"),
	CPR_REGEX_COUNTRY_CODE("cpr.regex.country.code"),
	CPR_REGEX_POSTAL_CODE("cpr.regex.postal.code"),
	CPR_REGEX_EMAIL_ADDRESS("cpr.regex.email.address"),
	CPR_AUTHZ_MODEL("cpr.authz.model"),
	CPR_GROUPER_RESOURCES_STEM("cpr.grouper.resources.stem"),
	CPR_GROUPER_GROUPS_STEM("cpr.grouper.groups.stem"),	
	CPR_MATCHING_ALGORITHM("cpr.matching.algorithm"),
	CPR_ADDRESS_VALIDATION("cpr.address.validation"),
	CPR_SERVICE_AUTHENTICATION("cpr.service.authentication"),
	CPR_SERVICE_USERID("cpr.service.userid"),
	CPR_SERVICE_PASSWORD("cpr.service.password"),
	CPR_DATABASE_NAME("cpr.database.name");
	
	private String cprPropertyName;
	
	private CprPropertyName(String cprPropertyName) {
		this.cprPropertyName = cprPropertyName;
	}
	
	public String toString() {
		return this.cprPropertyName;
	}

}
