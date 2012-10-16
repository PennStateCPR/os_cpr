/* SVN FILE: $Id: AddServicesFactory.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.factory;

import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.impl.AddAddressImpl;
import edu.psu.iam.cpr.service.impl.AddAffiliationImpl;
import edu.psu.iam.cpr.service.impl.AddConfidentialityHoldImpl;
import edu.psu.iam.cpr.service.impl.AddCredentialImpl;
import edu.psu.iam.cpr.service.impl.AddEmailAddressImpl;
import edu.psu.iam.cpr.service.impl.AddIdCardImpl;
import edu.psu.iam.cpr.service.impl.AddIdCardPrintEventImpl;
import edu.psu.iam.cpr.service.impl.AddNameImpl;
import edu.psu.iam.cpr.service.impl.AddPersonIdentifierImpl;
import edu.psu.iam.cpr.service.impl.AddPersonLinkageImpl;
import edu.psu.iam.cpr.service.impl.AddPhoneImpl;
import edu.psu.iam.cpr.service.impl.AddPhotoImpl;
import edu.psu.iam.cpr.service.impl.AddSpecialUseridImpl;
import edu.psu.iam.cpr.service.impl.AddUserCommentImpl;
import edu.psu.iam.cpr.service.impl.AddUseridImpl;

/**
 * Implementation of the add service factory.  The class determines which service implementation to call
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
public class AddServicesFactory {

	/**
	 * This routine is used to obtain a service implementation based on a service name.
	 * @param cprServiceName contains the service name.
	 * @return will return a service implementation.
	 */
	public ServiceInterface getServiceImplementation(CprServiceName cprServiceName) {
		switch (cprServiceName) {
		case AddAddress:
			return new AddAddressImpl();
		case AddAffiliation:
			return new AddAffiliationImpl();
		case AddConfidentialityHold:
			return new AddConfidentialityHoldImpl();
		case AddCredential:
			return new AddCredentialImpl();
		case AddEmailAddress:
			return new AddEmailAddressImpl();
		case AddIdCard:
			return new AddIdCardImpl();
		case AddIdCardPrintEvent:
			return new AddIdCardPrintEventImpl();
		case AddName:
			return new AddNameImpl();
		case AddPersonIdentifier:
			return new AddPersonIdentifierImpl();
		case AddPersonLinkage:
			return new AddPersonLinkageImpl();
		case AddPhone:
			return new AddPhoneImpl();
		case AddPhoto:
			return new AddPhotoImpl();
		case AddSpecialUserid:
			return new AddSpecialUseridImpl();
		case AddUserComment:
			return new AddUserCommentImpl();
		case AddUserid:
			return new AddUseridImpl();
		default:
			return null;
		}
	}

}
