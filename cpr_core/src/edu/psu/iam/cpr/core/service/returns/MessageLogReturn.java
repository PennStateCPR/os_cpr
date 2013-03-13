/* SVN FILE: $Id$ */

package edu.psu.iam.cpr.core.service.returns;

/**
 * This class is used to represent the return from executing the GetMessageLog service call.
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
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class MessageLogReturn {

	/** 
	 * Contains the message log key.
	 */
	private Long messageLogKey;
	
	/** 
	 * Contains the web service key.
	 */
	private Long webServiceKey;

	/**
	 * Contains the message consumer key.
	 */
	
	private Long messageConsumerKey;
	
	/**
	 * Contains the service key.
	 */
	private Long serviceKey;
	
	/**
	 * Contains the string representation of the message sent.
	 */
	
	private String messageSent;
	
	/**
	 * Contains the number of times this message has been sent.
	 */
	
	private Long numberOfTries;
	
	/**
	 * Contains a Y/N indicator as to whether the message was processed successfully.
	 */
	private String successFlag;
	

	/** 
	 * Contains the requesting userid
	 */
	private String requestUserid;


	/**
	 * Constructor
	 */
	public MessageLogReturn() {
		super();

	}

	/**
	 * Constructor.
	 * @param messageLogKey
	 * @param webServiceKey
	 * @param messageConsumerKey
	 * @param serviceKey
	 * @param messageSent
	 * @param numberOfTries
	 * @param successFlag
	 * @param requestUserid
	 */
	public MessageLogReturn(Long messageLogKey, Long webServiceKey, Long messageConsumerKey, Long serviceKey,
			String messageSent, Long numberOfTries, String successFlag,
			String requestUserid) {
		super();
		this.setMessageLogKey(messageLogKey);
		this.webServiceKey = webServiceKey;
		this.messageConsumerKey = messageConsumerKey;
		this.serviceKey = serviceKey;
		this.messageSent = messageSent;
		this.numberOfTries = numberOfTries;
		this.successFlag = successFlag;
		this.requestUserid = requestUserid;
	}

	/**
	 * @param messageLogKey the messageLogKey to set
	 */
	public final void setMessageLogKey(Long messageLogKey) {
		this.messageLogKey = messageLogKey;
	}

	/**
	 * @return the messageLogKey
	 */
	public Long getMessageLogKey() {
		return messageLogKey;
	}

	/**
	 * @return the webServiceKey
	 */
	public Long getWebServiceKey() {
		return webServiceKey;
	}
	/**
	 * @param webServiceKey the webServiceKey to set
	 */
	public void setWebServiceKey(Long webServiceKey) {
		this.webServiceKey = webServiceKey;
	}
	/**
	 * @return the messageConsumerKey
	 */
	public Long getMessageConsumerKey() {
		return messageConsumerKey;
	}
	/**
	 * @param messageConsumerKey the messageConsumerKey to set
	 */
	public void setMessageConsumerKey(Long messageConsumerKey) {
		this.messageConsumerKey = messageConsumerKey;
	}
	/**
	 * @return the messageSent
	 */
	public String getMessageSent() {
		return messageSent;
	}
	/**
	 * @param messageSent the messageSent to set
	 */
	public void setMessageSent(String messageSent) {
		this.messageSent = messageSent;
	}
	/**
	 * @return the numberOfTries
	 */
	public Long getNumberOfTries() {
		return numberOfTries;
	}
	/**
	 * @param numberOfTries the numberOfTries to set
	 */
	public void setNumberOfTries(Long numberOfTries) {
		this.numberOfTries = numberOfTries;
	}
	/**
	 * @return the successFlag
	 */
	public String getSuccessFlag() {
		return successFlag;
	}
	/**
	 * @param successFlag the successFlag to set
	 */
	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}
	/**
	 * @return the requestUserid
	 */
	public String getRequestUserid() {
		return requestUserid;
	}
	/**
	 * @param requestUserid the requestUserid to set
	 */
	public void setRequestUserid(String requestUserid) {
		this.requestUserid = requestUserid;
	}

	/**
	 * @param serviceKey the serviceKey to set
	 */
	public void setServiceKey(Long serviceKey) {
		this.serviceKey = serviceKey;
	}

	/**
	 * @return the serviceKey
	 */
	public Long getServiceKey() {
		return serviceKey;
	}

}
