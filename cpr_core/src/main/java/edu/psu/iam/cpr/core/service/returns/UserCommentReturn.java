/* SVN FILE: $Id: UserCommentReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.returns;
/**
 * This class is used to represent the return from executing
 * a GetUserComment service call.
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
 * @package edu.psu.iam.cpr.core.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class UserCommentReturn {

	/** Contains the comment key */
	private String commentKey;
	
	/** Contains the type of user comment. */
	private String userCommentType;
	
	/** Contains the string representation of the date the comment was entered. */
	private String startDate;
	
	/** Contains the end date for the comment */
	private String endDate;
	
	/** Contains the userid of the person who supplied the comment.
	 */
	private String commenter;
	
	/** Contains the comment that was entered for the user. */
	private String comment;

	/** Contains the userid of the person who last updated the comment. */
	private String lastUpdateBy;
	
	/** Contains the date the record was last updated on */
	private String lastUpdateOn;
	
	/** Contains the URI to the resource */
	private String uri;
	
	
	/**
	 * Constructor
	 */
	public UserCommentReturn() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param commentKey contains the comment key.
	 * @param userCommentType contains the user comment type.
	 * @param commentDateString contains the comment's creation date.
	 * @param endDate contains the end date of the comment.
	 * @param commenter contains the user that supplied the comment.
	 * @param comment contains the comment supplied by the commenter.
	 * @param lastUpdateBy contains the user that last updated the comment.
	 * @param lastUpdateOn contains the last updated date.
	 */
	public UserCommentReturn(String commentKey, String userCommentType,
			String commentDateString, String endDate, String commenter,
			String comment, String lastUpdateBy, String lastUpdateOn) {
		super();
		this.commentKey = commentKey;
		this.userCommentType = userCommentType;
		this.startDate = commentDateString;
		this.endDate = endDate;
		this.commenter = commenter;
		this.comment = comment;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateOn = lastUpdateOn;
	}
	
	/**
	 * @return the userCommentType
	 */
	public String getUserCommentType() {
		return userCommentType;
	}

	/**
	 * @param userCommentType the type of user comment
	 */
	public void setUserCommentType(String userCommentType) {
		this.userCommentType = userCommentType;
	}

	/**
	 * @return the commentDateString
	 */
	public String getCommentDateString() {
		return startDate;
	}

	/**
	 * @param commentDateString the commentDateString to set 
	 */
	public void setCommentDateString(String commentDateString) {
		this.startDate = commentDateString;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the endDate
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @return the commenter
	 */
	public String getCommenter() {
		return commenter;
	}

	/**
	 * @param commenter the commenter to set
	 */
	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the updatedBy
	 */
	public String getLastUpdatedBy() {
		return lastUpdateBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setLastUpdatedBy(String updatedBy) {
		this.lastUpdateBy = updatedBy;
	}	
	
	/**
	 * @param lastUpdateOn the lastUpdateOn to set
	 */
	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	/**
	 * @return the lastUpdateOn
	 */
	public String getLastUpdateOn() {
		return lastUpdateOn;
	}
	
	/**
	 * @return the commentKey
	 */
	public String getCommentKey() {
		return commentKey;
	}

	/**
	 * @param commentKey the commentKey to set
	 */
	public void setCommentKey(String commentKey) {
		this.commentKey = commentKey;
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return a string value.
	 */
	public String toString() {
		return "UserCommentReturn";
	}
}
