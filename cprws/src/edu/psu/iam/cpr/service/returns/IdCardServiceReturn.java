/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.service.returns;

import java.util.Arrays;

import edu.psu.iam.cpr.core.service.returns.PersonIdCardReturn;
import edu.psu.iam.cpr.core.service.returns.PhotoReturn;

/**
 * This class provides the implementation of the IdCard Service Return.
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
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class IdCardServiceReturn {
	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;
	
	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;
	
	/** Contains the id card return information, this will only be populated during a GetIdCard service call. */
	private PersonIdCardReturn personIdCardsReturnRecord[] = null;
	
	/** Contains the number of elements in the idCardsReturnRecord array. */
	private int numberPersonIdCardElements = 0;

	/**
	 * Contain the photo for the id Card
	 */
	private PhotoReturn photoReturn[] = null;
	
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
	 * @return the personIdCardsReturnRecord
	 */
	public PersonIdCardReturn[] getPersonIdCardsReturnRecord() {
		return personIdCardsReturnRecord;
	}
	/**
	 * @param personIdCardsReturnArray the personIdCardsReturnRecord to set
	 */
	public void setPersonIdCardsReturnRecord(
			PersonIdCardReturn[] personIdCardsReturnArray) {
		this.personIdCardsReturnRecord = Arrays.copyOf(personIdCardsReturnArray, personIdCardsReturnArray.length);
	}
	/**
	 * @return the numberPersonIdCardElements
	 */
	public int getNumberPersonIdCardElements() {
		return numberPersonIdCardElements;
	}
	/**
	 * @param numberPersonIdCardElements the numberPersonIdCardElements to set
	 */
	public void setNumberPersonIdCardElements(int numberPersonIdCardElements) {
		this.numberPersonIdCardElements = numberPersonIdCardElements;
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
		this.photoReturn = Arrays.copyOf(photoArray, photoArray.length);
	}
	/**
	 * 
	 */
	public IdCardServiceReturn() {
		super();
		
	}
	/**
	 * @param statusCode
	 * @param statusMessage
	 */
	public IdCardServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	
	/**
	 * @param statusCode
	 * @param statusMessage
	 * @param personIdCardsReturnArray
	 * @param numberPersonIdCardElements
	 * @param photoArray
	 */
	public IdCardServiceReturn(int statusCode, String statusMessage,
			PersonIdCardReturn[] personIdCardsReturnArray,
			int numberPersonIdCardElements, PhotoReturn[] photoArray) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.personIdCardsReturnRecord = Arrays.copyOf(personIdCardsReturnArray, personIdCardsReturnArray.length);
		this.numberPersonIdCardElements = numberPersonIdCardElements;
		this.photoReturn = Arrays.copyOf(photoArray, photoArray.length);
	}
	
	
	
}
