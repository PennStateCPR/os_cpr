/* SVN FILE: $Id: GrouperPermissions.java 5877 2012-12-11 14:07:11Z llg5 $ */

package edu.psu.iam.cpr.core.grouper;



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
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.util.CprProperties;
import edu.psu.iam.cpr.core.util.Utility;

/**
 *  This class provides an implementation for interfacing with the Grouper.  There are methods
 *  here to send REST request to grouper and process the response.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 *  
 * @package edu.psu.iam.cpr.core.grouper
 * @author $Author: llg5 $
 * @version $Rev: 5877 $
 * @lastrevision $Date: 2012-12-11 09:07:11 -0500 (Tue, 11 Dec 2012) $
 */
public class GrouperPermissions {

    private static final int BUFFER_SIZE = 512;
	private static final int MASTER_STRING_LENGTH = 6;
    protected static final String SUSPEND_FLAG = "suspend_flag";

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
 	 */
	public RAGroupData getGroupNameFromPrincipal(String principal)  {
		final RAGroupData raGroupData = new RAGroupData(null, true);
		final GcGetGroups gcGetGroups = new GcGetGroups();
		gcGetGroups.addSubjectId(principal);
		gcGetGroups.assignIncludeGroupDetail(true);	
		/*
		 * get the group associated with the server principal
		 */
		final WsGetGroupsResults wsGetGroupsResults = gcGetGroups.execute();
		if (wsGetGroupsResults.getResults() == null) {
			return raGroupData;
		}
		else
		{		
			final WsGetGroupsResult[] groupArray = wsGetGroupsResults.getResults();
			
			for (int grpIndx = 0; grpIndx < groupArray.length; grpIndx++) {
				if (groupArray[grpIndx].getWsGroups() == null ) {
					/*
					 * no group data found with the principal
					 */
					return raGroupData;
				}
				else
				{
					/*
					 * process the group data
					 */
					WsGroup[] principalGroup = groupArray[grpIndx].getWsGroups();
					for (int prinIndx = 0; prinIndx < principalGroup.length; prinIndx++) {
						/*
						 * the  principal is a member the master group associated with the ra
						 */
						if (principalGroup[prinIndx].getName().indexOf("Master") >0) {
							/* found the master group
							 * 
							 */
							raGroupData.setRegistrationAuthorityGroup(returnRaName(principalGroup[prinIndx].getName()));
							/*
							 * detail information contains ra suspend information
							 * if getDetail is null, no extra information on the ra
							 */
							if (principalGroup[prinIndx].getDetail() == null ) {
								return raGroupData;
								
							}
							else
							{
								WsGroupDetail groupDetail =principalGroup[prinIndx].getDetail();
								if (groupDetail.getAttributeNames() == null ) {
									return raGroupData;
								}
								else 
								{
									/*
									 * get the group attributes
									 */
									String[] groupAttributesNames =groupDetail.getAttributeNames();
									if (groupDetail.getAttributeValues() == null ) {
										return raGroupData;
									}
									else
									{
										String[] groupAttributesValues =groupDetail.getAttributeValues();
										for (int attrIndex = 0; attrIndex < groupAttributesNames.length; attrIndex++) {
											/*
											 * look for the suspend flag
											 */
											if (SUSPEND_FLAG.equals(groupAttributesNames[attrIndex]) ) {
												if (Utility.isOptionYes(groupAttributesValues[attrIndex])) {
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
		
		return raGroupData;
	}
	
	/**
	 * this routine determines if the affiliation access is authorized or not.
	 * @param affiliationType contains the type of affiliation.
	 * @param requestor contains the requestor.
	 * @return will return true if the access is allowed, otherwise it will return false.
	 */
	public boolean isAfiliationAccessAuthorized(String affiliationType, String requestor) {
		
		final WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();
		final GcGetPermissionAssignments gcPermission = new GcGetPermissionAssignments();
		wsSubjectLookup.setSubjectId(requestor);
		StringBuilder grouperAffiliationType = new StringBuilder(BUFFER_SIZE);
		grouperAffiliationType.append(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_GROUPER_RESOURCES_STEM.toString()));
		grouperAffiliationType.append(":");
		grouperAffiliationType.append(affiliationType);
		gcPermission.addSubjectLookup(wsSubjectLookup);
		gcPermission.addAttributeDefNameName(grouperAffiliationType.toString());
		gcPermission.addAction("access");
		/*
		 * get affiliation permissions
		 * use the affiliation string and the requestor
		 */
		final WsGetPermissionAssignmentsResults wsPermissionResults =gcPermission.execute();
		if (wsPermissionResults.getWsPermissionAssigns() == null) {
			return false;
	
		}
		else 
		{
			
			final WsPermissionAssign[] permissionArray = wsPermissionResults.getWsPermissionAssigns();
			/*
			 * determine if the requestor is allowed to use affiliation
			 */
			for ( int permIndex = 0;permIndex < permissionArray.length; permIndex++)  {
				if (permissionArray[permIndex].getAttributeDefNameName().equals(grouperAffiliationType.toString())) {
					return ("T".equals(permissionArray[permIndex].getAllowedOverall())) ;
					
				}
		
			}
		}
		
		
		return false;
	}
	
	/**
	 * This routine is used to determine if a data action is authorized or not.
	 * @param dataResource contains the data resource that is being checked.
	 * @param action contains the data operation.
	 * @param requestor contains the requestor.
	 * @return will return true if the operation is allowed.
	 */
	public boolean isDataActionAuthorized(String dataResource, String action ,String requestor) {
	
		final WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();
		final GcGetPermissionAssignments gcPermission = new GcGetPermissionAssignments();
		wsSubjectLookup.setSubjectId(requestor);
		final StringBuilder grouperDataResource  = new StringBuilder(BUFFER_SIZE);
		grouperDataResource.append(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_GROUPER_RESOURCES_STEM.toString()));
		grouperDataResource.append(":");
		grouperDataResource.append(dataResource);
		gcPermission.addSubjectLookup(wsSubjectLookup);
		gcPermission.addAttributeDefNameName(grouperDataResource.toString());
		gcPermission.addAction(action);
		/*
		 * get data permission 
		 * specify data type, action (read, write, archive) and requestor
		 */
		final WsGetPermissionAssignmentsResults wsPermissionResults =gcPermission.execute();
		if (wsPermissionResults.getWsPermissionAssigns() == null) {
			return false;

		}
		else 
		{

			final WsPermissionAssign[] permissionArray = wsPermissionResults.getWsPermissionAssigns();
			/*
			 * is the action allowed
			 */
			for ( int permIndex = 0;permIndex < permissionArray.length; permIndex++)  {
				if (permissionArray[permIndex].getAttributeDefNameName().equals(grouperDataResource.toString())) {
					return ("T".equals(permissionArray[permIndex].getAllowedOverall())) ;
				}

			}
		}
		
		
		return false;
	}
	
	/**
	 * This routine is used to determine if a web server operation is authorized or not.
	 * @param webService contains the web service to be checked.
	 * @param requestor contains the requestor.
	 * @return will return true if the operation is allowed.
	 */
	public boolean isRequestAuthorized(String webService, String requestor){
		final WsSubjectLookup wsSubjectLookup = new WsSubjectLookup();
		final GcGetPermissionAssignments gcPermission = new GcGetPermissionAssignments();
		wsSubjectLookup.setSubjectId(requestor);
		final StringBuilder grouperWebService  = new StringBuilder(BUFFER_SIZE);
		grouperWebService.append(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_GROUPER_RESOURCES_STEM.toString()));
		grouperWebService.append(":");
		grouperWebService.append(webService);
		gcPermission.addSubjectLookup(wsSubjectLookup);
		gcPermission.addAttributeDefNameName(grouperWebService.toString());
		gcPermission.addAction("access");
		/*
		 * get web service permission 
		 * specify web service and requestor
		 */
		final WsGetPermissionAssignmentsResults wsPermissionResults =gcPermission.execute();
		if (wsPermissionResults.getWsPermissionAssigns() == null) {
			return false;
		}
		else
		{
			final WsPermissionAssign[] permissionArray = wsPermissionResults.getWsPermissionAssigns();
	
			
			for (int permIndex = 0; permIndex < permissionArray.length; permIndex++)  {
				/*
				 * is access allowed
				 */
				if (permissionArray[permIndex].getAttributeDefNameName().equals(grouperWebService.toString())) {
					return  ("T".equals(permissionArray[permIndex].getAllowedOverall()));
				}
		
			}
		}
		
		return false;
	}
	
	/**
	 * This routine will determine if a subject member is a member of a group.
	 * @param groupName contains the group name to check.
	 * @param subjectId contains the subject to be checked.
	 * @return will return true if the member is a member of the group.
	 */
	public boolean isSubjectMemberOfMasterGroup(String groupName, String subjectId) {
		
		GcHasMember gcHasMember = new GcHasMember();
		gcHasMember.addSubjectId(subjectId);
		gcHasMember.assignIncludeSubjectDetail(false);
		gcHasMember.assignGroupName(CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_GROUPER_GROUPS_STEM.toString()) + ":" + groupName + "Master");
		gcHasMember.assignMemberFilter(WsMemberFilter.Effective);
		/*
		 * determine if requestor is member of group associated with ra
		 *
		 */
		final WsHasMemberResults wsHasMembersResults = gcHasMember.execute();
		if (wsHasMembersResults.getResults() == null) {
			return false;
		}
		else
		{
			final WsHasMemberResult[] membersArray = wsHasMembersResults.getResults();
			
			final WsSubject member = membersArray[0].getWsSubject();
			/*
			 * success of T implies request is member
			 * 
			 */
			if (member.getSuccess() != null) {
				if ("T".equals(member.getSuccess())) {
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
	
	/**
	 * This routine will return the name of the registration authority.
	 * @param raName contains the name of the registration authority.
	 * @return will return a string.
	 */
    private String returnRaName(String raName) {

        final String[] parts = raName.split(":");
        final StringBuilder result = new StringBuilder(BUFFER_SIZE);
        result.append(parts[parts.length - 1].substring(0, parts[parts.length - 1].length() - MASTER_STRING_LENGTH));
        return result.toString();
    }

//	/**
//	 * This routine determines is the requestor is authorized to use a service
//	 * @param principalId principal id
//	 * @param requestor userid of user accessing the service
//	 * @param serviceName service name
//	 * @param serviceCoreReturn ServiceCoreReturn object
//	 * @return ServiceCoreReturn object
//	 * @throws CprException indicates cpr specific error
//	 */
//	public ServiceCoreReturn requestorAuthorized(String principalId, String requestor, String serviceName, ServiceCoreReturn serviceCoreReturn) throws CprException  {
//		
//			RAGroupData raGroupData = null;
//			// Determine what RA a person is associated with.
//				// get the ra name from grouper
//			raGroupData = getGroupNameFromPrincipal(principalId);
//	
//			if (raGroupData.getRegistrationAuthorityGroup() == null) {
//				throw new CprException(ReturnType.NOT_AUTHORIZED_EXCEPTION, serviceName);
//			}
//				// Is the RA suspended?
//			if (raGroupData.isRegistrationAuthoritySuspendFlag() ) {
//				throw new CprException(ReturnType.NOT_AUTHORIZED_EXCEPTION, serviceName);
//				}
//			// check to make sure user is member of group
//
//			if (!isRequestAuthorized(serviceName, requestor)) {
//				throw new CprException(ReturnType.NOT_AUTHORIZED_EXCEPTION, serviceName);
//			}
//			
//			serviceCoreReturn.setGrouperPerm(this);
//			
//			return serviceCoreReturn;
//
//		}
//		

}

