package edu.psu.iam.cpr.core.api;

import java.text.ParseException;
import java.util.Map;

import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.tables.UserCommentTable;
import edu.psu.iam.cpr.core.database.tables.validate.ValidateUserComment;
import edu.psu.iam.cpr.core.database.types.AccessType;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.messaging.JsonMessage;
import edu.psu.iam.cpr.core.service.helper.ServiceCoreReturn;

/**
 * This class provides the implementation for the Update User Comment API.
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
 * @package edu.psu.iam.cpr.core.api
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class UpdateUserCommentApi extends BaseApi {

    /**
     * This method is used to execute the core logic for a service.
     * @param apiName contains the name of the api.
     * @param db contains a open database session.
     * @param serviceCoreReturn contains the person identifier value.
     * @param updatedBy contains the userid requesting this information.
     * @param otherParameters contains an map of Java objects that are additional parameters for the service.
     * @return will return an JsonMessage object if successful.
     * @throws CprException will be thrown if there are any problems.
     * @throws JSONException will be thrown if there are any issues creating a JSON message.
     * @throws ParseException will be thrown if there are any issues related to parsing a data value.
     */	
	@Override
	public JsonMessage runApi(final String apiName, final Database db, final ServiceCoreReturn serviceCoreReturn,
			final String updatedBy, final Map<String, Object> otherParameters,
			final boolean checkAuthorization) throws CprException, JSONException,
			ParseException {
		
		final String userId = (String) otherParameters.get(USERID_KEY);
		final String userCommentType = (String) otherParameters.get(USER_COMMENT_TYPE_KEY);
		final String comment = (String) otherParameters.get(USER_COMMENT_KEY);
		final long personId = serviceCoreReturn.getPersonId();
		
		// Validate the data passed into the service.
		final UserCommentTable userCommentTable = ValidateUserComment.validateUserCommentParameters(db, personId, 
				userId, userCommentType, comment, updatedBy);

		// Determine if the caller is authorized to make this call.
		if (checkAuthorization) {
			serviceCoreReturn.getAuthorizationService().dataActionAuthorized(db,
					userCommentTable.getUserCommentType().toString(), 
					AccessType.ACCESS_OPERATION_WRITE.toString(), updatedBy);
		}

		// Update the record.
		userCommentTable.updateUserComment(db);
		setRecordKey(userCommentTable.getUserCommentsBean().getUserCommentKey());
		
		// Create a new json message.
		final JsonMessage jsonMessage = new JsonMessage(db, personId, apiName, updatedBy);
		jsonMessage.setUserComment(userCommentTable);
		
		return jsonMessage;
	}

}
