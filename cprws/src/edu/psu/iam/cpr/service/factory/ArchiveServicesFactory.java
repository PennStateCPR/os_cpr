/* SVN FILE: $Id: ArchiveServicesFactory.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.factory;

import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.impl.ArchiveAddressImpl;
import edu.psu.iam.cpr.service.impl.ArchiveAffiliationImpl;
import edu.psu.iam.cpr.service.impl.ArchiveConfidentialityHoldImpl;
import edu.psu.iam.cpr.service.impl.ArchiveCredentialImpl;
import edu.psu.iam.cpr.service.impl.ArchiveEmailAddressImpl;
import edu.psu.iam.cpr.service.impl.ArchiveIdCardImpl;
import edu.psu.iam.cpr.service.impl.ArchiveNameImpl;
import edu.psu.iam.cpr.service.impl.ArchivePersonIdentifierImpl;
import edu.psu.iam.cpr.service.impl.ArchivePersonImpl;
import edu.psu.iam.cpr.service.impl.ArchivePersonLinkageImpl;
import edu.psu.iam.cpr.service.impl.ArchivePhoneImpl;
import edu.psu.iam.cpr.service.impl.ArchiveUserCommentImpl;
import edu.psu.iam.cpr.service.impl.ArchiveUseridImpl;

/**
 * Implementation of the archive service factory.  The class determines which service implementation to call
 * based on the service name. 
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
 * @package edu.psu.iam.cpr.service.factory
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
public class ArchiveServicesFactory {

	/**
	 * This routine is used to obtain a service implementation based on a service name.
	 * @param cprServiceName contains the service name.
	 * @return will return a service implementation.
	 */
	public ServiceInterface getServiceImplementation(CprServiceName cprServiceName) {
		switch (cprServiceName) {
		case ArchiveAddress:
			return new ArchiveAddressImpl();
		case ArchiveAffiliation:
			return new ArchiveAffiliationImpl();
		case ArchiveConfidentialityHold:
			return new ArchiveConfidentialityHoldImpl();
		case ArchiveCredential:
			return new ArchiveCredentialImpl();
		case ArchiveEmailAddress:
			return new ArchiveEmailAddressImpl();
		case ArchiveIdCard:
			return new ArchiveIdCardImpl();
		case ArchiveName:
			return new ArchiveNameImpl();
		case ArchivePerson:
			return new ArchivePersonImpl();
		case ArchivePersonIdentifier:
			return new ArchivePersonIdentifierImpl();
		case ArchivePersonLinkage:
			return new ArchivePersonLinkageImpl();
		case ArchivePhone:
			return new ArchivePhoneImpl();
		case ArchiveUserComment:
			return new ArchiveUserCommentImpl();
		case ArchiveUserid:
			return new ArchiveUseridImpl();
		default:
			return null;
		}
	}

}
