/* SVN FILE: $Id$ */

package edu.psu.iam.cpr.core.service.returns;

import java.sql.Timestamp;

/**
 * This class is used to represent the return from executing the GetMessageHistoryLog service call.
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
public class MessageLogHistoryReturn {

	
	/** 
	 * Contains the identifier for the message
	 */
	private String messageId;
	
	/** 
	 * Contains the identifier for the message log
	 */
	private Long messageLogKey;
	
	/**
	 * Contains the time stamp when the message was sent.
	 */
	private Timestamp messageSentTimestamp;
	
	/**
	 * Contains the time stamp when the message was received.
	 */
	private Timestamp messageReceivedTimestamp;

	/** 
	 * Contains the string representation of the message received.
	 */
	private String messageReceived;
	
	/**
	 * Contains the error code received.
	 */
	private String errorCode;
	
	/** 
	 * Contains the error message received.
	 */
	private String errorMessage;
	
	/** 
	 * Contains the number of times this message was sent.
	 */
	private Long tryNumber;

	/**
	 * Constructor
	 */
	public MessageLogHistoryReturn() {
		super();

	}
	
	/**
	 * Constructor.
	 * @param messageLogKey contains the the identifier for the message log.
	 * @param messageSentTimestamp contains the date the message was sent.
 	 * @param messageReceivedTimestamp contains the date the message was received.
	 * @param messageReceived contains the received message.
	 * @param errorCode contains the error code received.
	 * @param errorMessage contains the error message received.
	 * @param tryNumber contains the number of times this message was sent.
	 */
	public MessageLogHistoryReturn(String messageId, Long messageLogKey, Timestamp messageSentTimestamp,
			Timestamp messageReceivedTimestamp, String messageReceived, String errorCode,
			String errorMessage, Long tryNumber) {
		super();
		this.messageId = messageId;
		this.messageLogKey = messageLogKey;
		this.messageSentTimestamp = messageSentTimestamp;
		this.messageReceivedTimestamp = messageReceivedTimestamp;
		this.messageReceived = messageReceived;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.tryNumber = tryNumber;
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * @param messageId the messageId to set
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	/**
	 * @return the messageLogKey
	 */
	public Long getMessageLogKey() {
		return messageLogKey;
	}

	/**
	 * @param messageLogKey the messageLogKey to set
	 */
	public void setMessageLogKey(Long messageLogKey) {
		this.messageLogKey = messageLogKey;
	}

	/**
	 * @return the messageSentTimestamp
	 */
	public Timestamp getMessageSentTimestamp() {
		return messageSentTimestamp;
	}

	/**
	 * @param messageSentTimestamp the messageSentTimestamp to set
	 */
	public void setMessageSentTimestamp(Timestamp messageSentTimestamp) {
		this.messageSentTimestamp = messageSentTimestamp;
	}

	/**
	 * @return the messageReceivedTimestamp
	 */
	public Timestamp getMessageReceivedTimestamp() {
		return messageReceivedTimestamp;
	}

	/**
	 * @param messageReceivedTimestamp the messageReceivedTimestamp to set
	 */
	public void setMessageReceivedTimestamp(
			Timestamp messageReceivedTimestamp) {
		this.messageReceivedTimestamp = messageReceivedTimestamp;
	}

	/**
	 * @return the messageReceived
	 */
	public String getMessageReceived() {
		return messageReceived;
	}

	/**
	 * @param messageReceived the messageReceived to set
	 */
	public void setMessageReceived(String messageReceived) {
		this.messageReceived = messageReceived;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the tryNumber
	 */
	public Long getTryNumber() {
		return tryNumber;
	}

	/**
	 * @param tryNumber the tryNumber to set
	 */
	public void setTryNumber(Long tryNumber) {
		this.tryNumber = tryNumber;
	}

}
