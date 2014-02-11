package edu.psu.iam.cpr.core.api.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.PhotoReturn;

/**
 * This class provides the implementation for the Get Photo API.
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
public class PhotoServiceReturn {
	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/**  Status message returned as the result of executing this service. */
	private String statusMessage;

	/** Contains the number of elements in the return array. */
	private int numberElements;
	
	/** Contains the array of elements returned for a "get". */
	private PhotoReturn photoReturn[];

	/**
	 * Empty Constructor.
	 */
	public PhotoServiceReturn() {
		super();
	}

	/**
	 * Constructor.
	 * @param statusCode contains the status code that is result of executing this service.
	 * @param statusMessage contains the message that is returned as the result of executing this service.
	 * @param numberElements contains the number of elements returned in the array.
	 * @param photoArray contains the array of elements returned for a get.
	 */
	public PhotoServiceReturn(int statusCode, String statusMessage,
			int numberElements, PhotoReturn[] photoArray) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.numberElements = numberElements;
		if (photoArray != null) {
			this.photoReturn = Arrays.copyOf(photoArray, photoArray.length);
		}
		else {
			this.photoReturn = null;
		}
	}

	/**
	 * @param statusCode contains the status code that is result of executing this service.
	 * @param statusMessage contains the message that is returned as the result of executing this service.
	 */
	public PhotoServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}

	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	/**
	 * @return the numberElements
	 */
	public int getNumberElements() {
		return numberElements;
	}

	/**
	 * @param numberElements the numberElements to set
	 */
	public void setNumberElements(int numberElements) {
		this.numberElements = numberElements;
	}

	/**
	 * @return the photoReturn
	 */
	public PhotoReturn[] getPhotoReturn() {
		return photoReturn;
	}

	/**
	 * @param photoArray the photoReturn to set
	 */
	public void setPhotoReturn(PhotoReturn[] photoArray) {
		if (photoArray != null) {
			this.photoReturn = Arrays.copyOf(photoArray, photoArray.length);
		}
		else {
			this.photoReturn = null;
		}
	}
	
}
