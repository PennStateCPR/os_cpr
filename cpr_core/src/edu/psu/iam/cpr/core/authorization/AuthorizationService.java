/* SVN FILE: $Id: AuthorizationService.java 5881 2012-12-11 17:40:12Z llg5 $ */

package edu.psu.iam.cpr.core.authorization;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.CprComponentStatusTable;
import edu.psu.iam.cpr.core.database.types.CprComponent;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.grouper.GrouperPermissions;
import edu.psu.iam.cpr.core.grouper.RAGroupData;
import edu.psu.iam.cpr.core.util.CprProperties;

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
 *
 * @package edu.psu.iam.cpr.core.authorization
 * @author $Author: llg5 $
 * @version $Rev: 5881 $
 * @lastrevision $Date: 2012-12-11 12:40:12 -0500 (Tue, 11 Dec 2012) $
 */
public class AuthorizationService {

	/** Grouper Permission object */
	private GrouperPermissions grouperPerm;
	
	/**
	 * Constructor
	 */
	public AuthorizationService() {
		super();
	}

	/**
	 * handle the dataAction authorization request
	 *
	 * @param db contains the database object
	 * @param dataResource contains the data source that is being checked.
	 * @param action contains the action being checked
	 * @param requestor contains the access id of the perform who requested this operation.
	 * @return will return true is data access allowed
	 * @throws CprException will be thrown if access denied
	 */
	public boolean dataActionAuthorized(Database db, String dataResource, String action, String requestor) throws CprException {
		
		boolean theResult = false;
		CprComponentStatusTable cprComponentStatusTable = new CprComponentStatusTable();
		String authzModel = CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_AUTHZ_MODEL.toString());
		if (authzModel.equals("grouper") ) {
			if (cprComponentStatusTable.isCprComponentActive(db, CprComponent.GROUPER) )
			{
				theResult = grouperPerm.isDataActionAuthorized(dataResource,  action.toLowerCase(), requestor);
				return theResult;
			}
			else {
				theResult = db.isDataActionAuthorized(dataResource, action,requestor);
			}
		}
		else {
			
			theResult = db.isDataActionAuthorized(dataResource, action, requestor);
		}	
		return theResult;
	}
	
	/**
	 * Service authorization request
	 * @param db contains the database object
	 * @param principalId contains the requestor's principal identifier.
	 * @param requestor contains the userid of the person requesting access.
	 * @param serviceName contains the service name
     *	 
	 * @throws CprException exception indicates a cpr specific java exception.
	 */
	public void serviceAuthorized(Database db, String principalId, String requestor, String serviceName) throws CprException  {
		RAGroupData localGroupData = null;
		CprComponentStatusTable cprComponentStatusTable = new CprComponentStatusTable();
		String authzModel = CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_AUTHZ_MODEL.toString());
		
		/* 
		 * if authzM
		 */
		if (authzModel.equals("grouper") ) {
			if (cprComponentStatusTable.isCprComponentActive(db, CprComponent.GROUPER) ){
				grouperPerm = new GrouperPermissions();	
				localGroupData = grouperPerm.getGroupNameFromPrincipal(principalId);
				if (grouperPerm.isSubjectMemberOfMasterGroup(localGroupData.getRegistrationAuthorityGroup(),requestor)) {
					if (! grouperPerm.isRequestAuthorized(serviceName, requestor)) {
						throw new CprException(ReturnType.NOT_AUTHORIZED_EXCEPTION, serviceName);
					}
				}
				else
				{
					throw new CprException(ReturnType.NOT_AUTHORIZED_EXCEPTION, serviceName);
				}
			}
			else 
			{
				 db.requestorAuthorized(principalId, requestor, serviceName);
			}
		}
		else 
		{
			db.requestorAuthorized(principalId, requestor, serviceName);	
		}
	}

	/**
	 * @param grouperPerm the grouperPerm to set
	 */
	public void setGrouperPerm(GrouperPermissions grouperPerm) {
		this.grouperPerm = grouperPerm;
	}

	/**
	 * @return the grouperPerm
	 */
	public GrouperPermissions getGrouperPerm() {
		return grouperPerm;
	}
	
}
