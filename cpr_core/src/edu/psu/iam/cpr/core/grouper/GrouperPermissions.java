/* SVN FILE: $Id: GrouperPermissions.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.grouper;



import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;


import edu.internet2.middleware.grouperClient.api.GcGetGroups;
import edu.internet2.middleware.grouperClient.api.GcGetPermissionAssignments;
import edu.internet2.middleware.grouperClient.api.GcHasMember;
import edu.internet2.middleware.grouperClient.ws.WsMemberFilter;
import edu.internet2.middleware.grouperClient.ws.beans.WsGetGroupsResult;
import edu.internet2.middleware.grouperClient.ws.beans.WsGetGroupsResults;
import edu.internet2.middleware.grouperClient.ws.beans.WsGetPermissionAssignmentsResults;
import edu.internet2.middleware.grouperClient.ws.beans.WsGroup;
import edu.internet2.middleware.grouperClient.ws.beans.WsGroupDetail;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResult;
import edu.internet2.middleware.grouperClient.ws.beans.WsHasMemberResults;
import edu.internet2.middleware.grouperClient.ws.beans.WsPermissionAssign;
import edu.internet2.middleware.grouperClient.ws.beans.WsSubject;
import edu.internet2.middleware.grouperClient.ws.beans.WsSubjectLookup;
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
public class GrouperPermissions {
	
	/** A string representing the folder containing the grouper permissions */
	private static final String GROUPER_CPR_RESOURCES = "apps:cpr:resources:";
	
	/** A string representing the folder containing the grouper permissions */	
	private static final String GROUPER_CPR_GROUPS_STEM ="apps:cpr:regAuth";
	

	
	
     
     /**
 	 * 	Constructor
 	 */
 	public GrouperPermissions() {
 		super();
 	}

 	/**
 	 * This routine is used to return group name for a principal.
 	 * @param principal contains the principal.
 	 * @return RAGroupData
 	 * @throws CprException will be thrown if there are any CPR related problems.
 	 */
	public RAGroupData getGroupNameFromPrincipal(String principal) throws CprException {
		final RAGroupData raGroupData = new RAGroupData(null, true);
		final GcGetGroups gcGetGroups = new GcGetGroups();
		gcGetGroups.addSubjectId(principal);
		gcGetGroups.assignIncludeGroupDetail(true);	
		try {
			final WsGetGroupsResults wsGetGroupsResults = gcGetGroups.execute();
			if (wsGetGroupsResults.getResults() == null) {
				return raGroupData;
			}
			else
			{
				final WsGetGroupsResult groupArray[] = wsGetGroupsResults.getResults();
				for (int grpIndx = 0; grpIndx < groupArray.length; grpIndx++) {
					if (groupArray[grpIndx].getWsGroups() == null ) {
						return raGroupData;
					}
					else
					{
						if (groupArray[grpIndx].getWsGroups() == null) {
							return raGroupData;
						}
						else
						{
							WsGroup[] principalGroup = groupArray[grpIndx].getWsGroups();
							for (int prinIndx = 0; prinIndx < principalGroup.length; prinIndx++) {
								if (principalGroup[prinIndx].getName().indexOf("Master") >0) {
									raGroupData.setRegistrationAuthorityGroup(returnRaName(principalGroup[prinIndx].getName()));
									if (principalGroup[prinIndx].getDetail() == null ) {
										return raGroupData;
									
									}
									else {
										
										WsGroupDetail groupDetail =principalGroup[prinIndx].getDetail();
										if (groupDetail.getAttributeNames() == null ) {
											return raGroupData;
										}
										else 
										{
											String groupAttributesNames[] =groupDetail.getAttributeNames();
											if (groupDetail.getAttributeValues() == null ) {
												return raGroupData;
											}
											else
											{
												String groupAttributesValues[] =groupDetail.getAttributeValues();
												for (int attrIndex = 0; attrIndex < groupAttributesNames.length; attrIndex++) {
													if (groupAttributesNames[attrIndex].equals("suspend_flag") ) {
														if (groupAttributesValues[attrIndex].equals("Y")) {
															raGroupData.setRegistrationAuthoritySuspendFlag(true);
															return raGroupData;
														}
														else 
														{
															raGroupData.setRegistrationAuthoritySuspendFlag(false);
															return raGroupData;
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				 }
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Grouper errors");
			}
		return raGroupData;
	}
	
	/**
	 * this routine determines if the affiliation access is authorized or not.
	 * @param affiliationType contains the type of affiliation.
	 * @param requestor contains the requestor.
	 * @return will return true if the access is allowed, otherwise it will return false.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public boolean isAfiliationAccessAuthorized(String affiliationType, String requestor) throws CprException {
		
		final WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();
		final GcGetPermissionAssignments gcPermission = new GcGetPermissionAssignments();
		wsSubjectLookup.setSubjectId(requestor);
		StringBuilder grouperAffiliationType = new StringBuilder(100);
		grouperAffiliationType.append(GROUPER_CPR_RESOURCES);
		grouperAffiliationType.append(affiliationType);
		gcPermission.addSubjectLookup(wsSubjectLookup);
		gcPermission.addAttributeDefNameName(grouperAffiliationType.toString());
		gcPermission.addAction("access");
		try {
			
			final WsGetPermissionAssignmentsResults wsPermissionResults =gcPermission.execute();
			if (wsPermissionResults.getWsPermissionAssigns() == null) {
				return false;
		
			}
			else 
			{
				
				final WsPermissionAssign permissionArray[] = wsPermissionResults.getWsPermissionAssigns();
		
				for ( int permIndex = 0;permIndex < permissionArray.length; permIndex++)  {
					if (permissionArray[permIndex].getAttributeDefNameName().equals(grouperAffiliationType.toString())) {
						if (permissionArray[permIndex].getAllowedOverall().equals("T")) {
							return true;
						}
						else
						{
							return false;
					
						}
					}
			
				}
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Grouper errors");
		}
		
		return false;
	}
	
	/**
	 * This routine is used to determine if a data action is authorized or not.
	 * @param dataResource contains the data resource that is being checked.
	 * @param action contains the data operation.
	 * @param requestor contains the requestor.
	 * @return will return true if the operation is allowed.
	 * @throws CprException will be thrown if there are any CPR problems.
	 */
	public boolean isDataActionAuthorized(String dataResource, String action ,String requestor) throws CprException {
	
		final WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();
		final GcGetPermissionAssignments gcPermission = new GcGetPermissionAssignments();
		wsSubjectLookup.setSubjectId(requestor);
		final StringBuilder grouperDataResource  = new StringBuilder(100);
		grouperDataResource.append(GROUPER_CPR_RESOURCES);
		grouperDataResource.append(dataResource);
		gcPermission.addSubjectLookup(wsSubjectLookup);
		gcPermission.addAttributeDefNameName(grouperDataResource.toString());
		gcPermission.addAction(action);
		try {
			
			final WsGetPermissionAssignmentsResults wsPermissionResults =gcPermission.execute();
			if (wsPermissionResults.getWsPermissionAssigns() == null) {
				return false;
		
			}
			else 
			{
				
				final WsPermissionAssign permissionArray[] = wsPermissionResults.getWsPermissionAssigns();
		
				for ( int permIndex = 0;permIndex < permissionArray.length; permIndex++)  {
					if (permissionArray[permIndex].getAttributeDefNameName().equals(grouperDataResource.toString())) {
						if (permissionArray[permIndex].getAllowedOverall().equals("T")) {
							return true;
						}
						else
						{
							return false;
					
						}
					}
			
				}
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Grouper errors");
		}
		
		return false;
	}
	
	/**
	 * This routine is used to determine if a web server operation is authorized or not.
	 * @param webService contains the web service to be checked.
	 * @param requestor contains the requestor.
	 * @return will return true if the operation is allowed.
	 * @throws CprException will be thrown if there any CPR related problems.
	 */
	public boolean isRequestAuthorized(String webService, String requestor) throws CprException {
		final WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();
		final GcGetPermissionAssignments gcPermission = new GcGetPermissionAssignments();
		wsSubjectLookup.setSubjectId(requestor);
		final StringBuilder grouperWebService  = new StringBuilder(100);
		grouperWebService.append(GROUPER_CPR_RESOURCES);
		grouperWebService.append(webService);
		gcPermission.addSubjectLookup(wsSubjectLookup);
		gcPermission.addAttributeDefNameName(grouperWebService.toString());
		gcPermission.addAction("access");
		try {
			final WsGetPermissionAssignmentsResults wsPermissionResults =gcPermission.execute();
			if (wsPermissionResults.getWsPermissionAssigns() == null) {
				return false;
			}
			else
			{
				final WsPermissionAssign permissionArray[] = wsPermissionResults.getWsPermissionAssigns();
		
				
				for ( int permIndex = 0;permIndex < permissionArray.length; permIndex++)  {
					if (permissionArray[permIndex].getAttributeDefNameName().equals(grouperWebService.toString())) {
						if (permissionArray[permIndex].getAllowedOverall().equals("T")) {
							return true;
						}	
						else	
						{
							return false;
					
						}
					}
			
				}
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Grouper errors");
			
		}
		return false;
	}
	
	/**
	 * This routine will determine if a subject member is a member of a group.
	 * @param groupName contains the group name to check.
	 * @param subjectId contains the subject to be checked.
	 * @return will return true if the member is a member of the group.
	 * @throws CprException will be thrown if there are any CPR related problems.
	 */
	public boolean isSubjectMemberOfMasterGroup(String groupName, String subjectId) throws CprException {
		
		GcHasMember gcHasMember = new GcHasMember();
		gcHasMember.addSubjectId(subjectId);
		gcHasMember.assignIncludeSubjectDetail(false);
		gcHasMember.assignGroupName(GROUPER_CPR_GROUPS_STEM + ":" + groupName + "Master");
		gcHasMember.assignMemberFilter(WsMemberFilter.Effective);
		
		try {
			final WsHasMemberResults wsHasMembersResults = gcHasMember.execute();
			if (wsHasMembersResults.getResults() == null) {
				return false;
			}
			else
			{
				final WsHasMemberResult membersArray[] = wsHasMembersResults.getResults();
				
				final WsSubject member = membersArray[0].getWsSubject();
					
				if (member.getSuccess() != null) {
					if (member.getSuccess().equals("T")) {
						return true;							
					}							
					else							
					{			
						return false;
					}
				}
				else 
				{
					return false;
				}
			}
		}
		catch (Exception e) {
			throw new CprException(ReturnType.GENERAL_EXCEPTION, "Grouper errors");
		}
	}
	
	/**
	 * This routine will return the name of the registration authority.
	 * @param raName contains the name of the registration authority.
	 * @return will return a string.
	 */
	private String returnRaName (String raName ) {
			
			final String parts[] = raName.split(":");
			final StringBuilder result = new StringBuilder(100);
			result.append(parts[parts.length -1].substring( 0, parts[parts.length-1].length() - 6));
			return result.toString();
		
		}
	}
