/* SVN FILE: $Id: GetServicesFactory.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.factory;

import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.service.helper.ServiceInterface;
import edu.psu.iam.cpr.service.impl.GetAddressImpl;
import edu.psu.iam.cpr.service.impl.GetAffiliationsImpl;
import edu.psu.iam.cpr.service.impl.GetConfidentialityImpl;
import edu.psu.iam.cpr.service.impl.GetCredentialImpl;
import edu.psu.iam.cpr.service.impl.GetEmailAddressImpl;
import edu.psu.iam.cpr.service.impl.GetExternalAffiliationsImpl;
import edu.psu.iam.cpr.service.impl.GetExternalIAPImpl;
import edu.psu.iam.cpr.service.impl.GetIdCardImpl;
import edu.psu.iam.cpr.service.impl.GetIdCardNumberImpl;
import edu.psu.iam.cpr.service.impl.GetIdCardPrintEventImpl;
import edu.psu.iam.cpr.service.impl.GetInternalAffiliationsImpl;
import edu.psu.iam.cpr.service.impl.GetNameImpl;
import edu.psu.iam.cpr.service.impl.GetPSUIAPImpl;
import edu.psu.iam.cpr.service.impl.GetPersonIdentifierImpl;
import edu.psu.iam.cpr.service.impl.GetPersonImpl;
import edu.psu.iam.cpr.service.impl.GetPersonLinkageImpl;
import edu.psu.iam.cpr.service.impl.GetPhoneImpl;
import edu.psu.iam.cpr.service.impl.GetPhotoImpl;
import edu.psu.iam.cpr.service.impl.GetUserCommentsImpl;
import edu.psu.iam.cpr.service.impl.GetUseridImpl;

/**
 * Implementation of the get service factory.  The class determines which service implementation to call
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
public class GetServicesFactory {
	
	/**
	 * This routine is used to obtain a service implementation based on a service name.
	 * @param cprServiceName contains the service name.
	 * @return will return a service implementation.
	 */
	public ServiceInterface getServiceImplementation(CprServiceName cprServiceName) {
		switch (cprServiceName) {
		case GetAddress:
			return new GetAddressImpl();
		case GetAffiliations:
			return new GetAffiliationsImpl();
		case GetConfidentialityHold:
			return new GetConfidentialityImpl();
		case GetCredential:
			return new GetCredentialImpl();
		case GetEmailAddress:
			return new GetEmailAddressImpl();
		case GetExternalAffiliations:
			return new GetExternalAffiliationsImpl();
		case GetExternalIAP:
			return new GetExternalIAPImpl();
		case GetIdCardNumber:
			return new GetIdCardNumberImpl();
		case GetIdCard:
			return new GetIdCardImpl();
		case GetIdCardPrintEvent:
			return new GetIdCardPrintEventImpl();
		case GetInternalAffiliations:
			return new GetInternalAffiliationsImpl();
		case GetName:
			return new GetNameImpl();
		case GetPerson:
			return new GetPersonImpl();
		case GetPersonIdentifier:
			return new GetPersonIdentifierImpl();
		case GetPersonLinkage:
			return new GetPersonLinkageImpl();
		case GetPhone:
			return new GetPhoneImpl();
		case GetPhoto:
			return new GetPhotoImpl();
		case GetPSUIAP:
			return new GetPSUIAPImpl();
		case GetUserComments:
			return new GetUserCommentsImpl();
		case GetUserid:
			return new GetUseridImpl();
		default:
			return null;
		}
	}


}
