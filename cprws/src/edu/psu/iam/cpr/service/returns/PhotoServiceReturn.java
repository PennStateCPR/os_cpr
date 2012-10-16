/* SVN FILE: $Id: PhotoServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
package edu.psu.iam.cpr.service.returns;

import edu.psu.iam.cpr.core.service.returns.PhotoReturn;

/**
 * This class is returned as a result of executing a Photo service.
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
 * @package edu.psu.iam.cpr.service.returns
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
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
	 * @param photoReturn contains the array of elements returned for a get.
	 */
	public PhotoServiceReturn(int statusCode, String statusMessage,
			int numberElements, PhotoReturn[] photoReturn) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.numberElements = numberElements;
		this.photoReturn = photoReturn;
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
	 * @param photoReturn the photoReturn to set
	 */
	public void setPhotoReturn(PhotoReturn[] photoReturn) {
		this.photoReturn = photoReturn;
	}
	
}
