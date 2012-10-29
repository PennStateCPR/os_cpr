/* SVN FILE: $Id: RulesReturn.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.rules.engine;

/**
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
 * 
 * @package edu.psu.iam.cpr.core.rules.engine
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class RulesReturn {
	
	/** Status code that is result of executing this service. */
	private int statusCode;
	
	/**  Status message returned as the result of executing this service. */
	private String statusMessage;

	/**  Contains the number of facts returned from the service. */
	private int numberOfFacts = 0;
	
	/** Contains the facts return data. */
	private String facts[] = null;

	/** 
	 * Constructor.
	 */
	public RulesReturn() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param statusCode contains the status code.
	 * @param statusMessage contains the status message.
	 */
	public RulesReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * Constructor
	 * @param statusCode contains the status code.
	 * @param statusMessage contains the status message.
	 * @param numberOfFacts contains the number of facts that have been processed.
	 * @param facts contains the facts array.
	 */
	public RulesReturn(int statusCode, String statusMessage, int numberOfFacts,
			String[] facts) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.numberOfFacts = numberOfFacts;
		if (facts != null) {
			this.facts = new String[facts.length];
			System.arraycopy(facts, 0, this.facts, 0, facts.length);
		}
		else {
			this.facts = null;
		}
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
	 * @param numberOfFacts the numberOfFacts to set
	 */
	public void setNumberOfFacts(int numberOfFacts) {
		this.numberOfFacts = numberOfFacts;
	}

	/**
	 * @return the numberOfFacts
	 */
	public int getNumberOfFacts() {
		return numberOfFacts;
	}

	/**
	 * @param facts the facts to set
	 */
	public void setFacts(String facts[]) {
		if (facts != null) {
			this.facts = new String[facts.length];
			System.arraycopy(facts, 0, this.facts, 0, facts.length);
		}
		else {
			this.facts = null;
		}
	}

	/**
	 * @return the facts
	 */
	public String[] getFacts() {
		return facts;
	}
}
