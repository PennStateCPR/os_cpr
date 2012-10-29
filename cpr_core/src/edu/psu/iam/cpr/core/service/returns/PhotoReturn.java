/* SVN FILE: $Id: PhotoReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing a GetPhoto service call.
 * This data is returned as an array to the caller.
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
public class PhotoReturn {

	/** Contains the photo. */
	private byte photo[];
	
	/** Contains the date the photo was taken. */
	private String dateTaken;
	
	/** Contains the last update person */
	private String lastUpdateBy;
	
	/** Contains the date the record was last updated on */
	private String lastUpdateOn;
	
	/** Contains the person who created the record */
	private String createdBy;
	
	/** Contains the date the record was created on */
	private String createdOn;

	
	/**
	 * Constructor
	 */
	public PhotoReturn() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param photo contains the photo (byte representation).
	 * @param dateTaken contains the date the photo was taken.
	 * @param lastUpdateBy contains the entity that last updated the record.
	 * @param lastUpdateOn contains the date the record was last updated.
	 * @param createdBy contains the entity that created the record.
	 * @param createdOn contains the date the record was created on.
	 */
	public PhotoReturn(byte[] photo, String dateTaken, 
			String lastUpdateBy, String lastUpdateOn,
			String createdBy, String createdOn) {
		super();
		if (photo != null) {
			this.photo = new byte[photo.length];
			System.arraycopy(photo, 0, this.photo, 0, photo.length);
		}
		else {
			this.photo = null;
		}
		this.dateTaken = dateTaken;
		this.lastUpdateBy = lastUpdateBy;
		this.lastUpdateOn = lastUpdateOn;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}
	
	/**
	 * @return the photo
	 */
	public byte[] getPhoto() {
		return photo;
	}

	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(byte[] photo) {
		if (photo != null) {
			this.photo = new byte[photo.length];
			System.arraycopy(photo, 0, this.photo, 0, photo.length);
		}
		else {
			this.photo = null;
		}
	}

	/**
	 * @return the dateTaken
	 */
	public String getDateTaken() {
		return dateTaken;
	}

	/**
	 * @param dateTaken the dateTaken to set
	 */
	public void setDateTaken(String dateTaken) {
		this.dateTaken = dateTaken;
	}

	/**
	 * @return the lastUpdateBy
	 */
	public String getLastUpdateBy() {
		return lastUpdateBy;
	}

	/**
	 * @param lastUpdateBy the lastUpdateBy to set
	 */
	public void setLastUpdateBy(String lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	/**
	 * @return the lastUpdateOn
	 */
	public String getLastUpdateOn() {
		return lastUpdateOn;
	}

	/**
	 * @param lastUpdateOn the lastUpdateOn to set
	 */
	public void setLastUpdateOn(String lastUpdateOn) {
		this.lastUpdateOn = lastUpdateOn;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	
}
