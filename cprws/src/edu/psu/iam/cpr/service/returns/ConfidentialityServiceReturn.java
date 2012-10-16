/* SVN FILE: $Id: ConfidentialityServiceReturn.java 5343 2012-09-27 14:56:40Z jvuccolo $ */
/**
 * This class contains information that is returned as the result of executing a
 * confidentiality service end point.
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
 * @package edu.psu.iam.cpr.services
 * @author $Author: jvuccolo $
 * @version $Rev: 5343 $
 * @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
 */
package edu.psu.iam.cpr.service.returns;

import edu.psu.iam.cpr.core.service.returns.ConfidentialityReturn;

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
* @package edu.psu.iam.cpr.service.returns
* @author $Author: jvuccolo $
* @version $Rev: 5343 $
* @lastrevision $Date: 2012-09-27 10:56:40 -0400 (Thu, 27 Sep 2012) $
*/
public class ConfidentialityServiceReturn {

	private int statusCode;
	/**
	 * Status code that is result of executing this service.
	 */
	
	private String statusMessage;
	/** 
	 * Status message returned as the result of executing this service.
	 */
	
	private int numberElements;
	/**
	 * Contains the number of elements in return array.
	 */
	
	private ConfidentialityReturn confidentialityReturn[];
	/**
	 * Contains an array of results from executing the GetConfidentialityHold service.
	 */

	/**
	 * Default constructor.
	 */
	public ConfidentialityServiceReturn() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param statusCode contains the status code that is the result of executing this service.
	 * @param statusMessage contains the the status message.
	 * @param numberElements contains the number of elements in the return.
	 * @param confidentialityReturn contains the array of elements in the return.
	 */
	public ConfidentialityServiceReturn(int statusCode, String statusMessage,
			int numberElements, ConfidentialityReturn[] confidentialityReturn) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.numberElements = numberElements;
		this.confidentialityReturn = confidentialityReturn;
	}

	/**
	 * Constructor containing the status code and status message
	 * @param statusCode contains the status code that is returned as the result of executing this service.
	 * @param statusMessage contains the status message that is returned as the result of executing this service.
	 */
	public ConfidentialityServiceReturn(int statusCode, String statusMessage) {
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
	 * @param numberOfElements the numberOfElements to set
	 */
	public void setNumberElements(int numberOfElements) {
		this.numberElements = numberOfElements;
	}

	/**
	 * @return the numberOfElements
	 */
	public int getNumberElements() {
		return numberElements;
	}

	/**
	 * @param confidentialityReturn the confidentialityReturn to set
	 */
	public void setConfidentialityReturn(ConfidentialityReturn confidentialityReturn[]) {
		this.confidentialityReturn = confidentialityReturn;
	}

	/**
	 * @return the confidentialityReturn
	 */
	public ConfidentialityReturn[] getConfidentialityReturn() {
		return confidentialityReturn;
	}
	
	/**
	 * @return a string value.
	 */
	public String toString() {
		return "";
	}
}
