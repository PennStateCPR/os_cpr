/* SVN FILE: $Id: TransformAddressHelper.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.helper;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.AddressesTable;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;
import edu.psu.iam.cpr.service.returns.TransformServiceReturn;

/**
 * This class provides helper methods for the transform address service.
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
public class TransformAddressHelper {

	/**
	 * Constructor.
	 */
	public TransformAddressHelper() {
		super();
	}
	
	/**
	 * This routine provides the implementation for the transform address functionality.  It is called by the main service (TransformAddress)
	 * and other services.
	 * @param db contains an instance of the database connection.
	 * @param serviceCoreReturn contains a service core return class.
	 * @param requestedBy contains the user that requested the transform.
	 * @param address1 contains address line #1.
	 * @param address2 contains address line #2.
	 * @param address3 contains address line #3.
	 * @param city contains the city.
	 * @param stateOrProvince contains the state or province.
	 * @param postalCode contains the postal code.
	 * @param countryCode contains the country code.
	 * @return will return a TransformServiceReturn object upon success.
	 * @throws SessionError will be thrown if there are any transform problems.
	 */
	public TransformServiceReturn doTransformAddress(
			Database db, 
			ServiceCoreReturn serviceCoreReturn,			
			String requestedBy, 
			String address1, 
			String address2, 
			String address3, 
			String city, 
			String stateOrProvince, 
			String postalCode,
			String countryCode) {
		
		return null;
	}
		
	/**
	 * This routine provides an interface for the CPR to execute the Transform Address service functionality.
	 * @param db contains an instance of the database connection.
	 * @param serviceCoreReturn contains a service core return.
	 * @param addressesTable contains the address database table information.
	 */
	public void transformRegistryAddress(Database db, 
			ServiceCoreReturn serviceCoreReturn, 
			AddressesTable addressesTable)  {
		
	}
}
