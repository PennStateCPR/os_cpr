package edu.psu.iam.cpr.core.api.returns;

/**
 * This class provides the implementation for the Transform API.
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
public class TransformServiceReturn {
	/** Contains the status code that is the result of executing of a service. */
	private int statusCode;

	/** Contains the text message associated with the execution of a service. */
	private String statusMessage;

	/** Contains the address line 1 */
	private String address1;
	
	/** Contains the address line 2 */
	private String address2;

	/** Contains the city */
	private String city;

	/** Contains the state or province */
	private String stateOrProvince;

	/**  Contains the postalCode */	
	private String postalCode;
	
	/** Contains the countryCode */
	private String countryCode;

	/** Contains the country Name */
	private String countryName;

	/** Contains a code  deliverability.
	 *  Possible values are VALID, HIGH, MEDIUM, LOW, UNKNOWN
	 */
	private String deliverability;

	/**
	 * Empty constructor.
	 */
	public TransformServiceReturn() {
		super();
	}
	
	/**
 	 * Constructor
 	 * 
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 */
	public TransformServiceReturn(int statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	/**
	 * @param statusCode status code that is returned as the result of executing a service.
	 * @param statusMessage the status message that is returned as the result of executing a service.
	 * @param address1 first line of the street address
	 * @param address2 second line of the street address
	 * @param city the city of the address
	 * @param stateOrProvince for US addresses, a State; for Canadian addresses, a Province, 
	 * @param postalCode the postal code of the address. For US address, may include plus4 code.
	 * @param countryCode three character country code as defined in ISO 3166
	 *
	 */
	
	public TransformServiceReturn(int statusCode, String statusMessage,
			String address1, String address2, String city,
			String stateOrProvince, String postalCode, String countryCode,
			String countryName, String deliverability) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
		this.postalCode = postalCode;
		this.countryCode = countryCode;
		this.countryName = countryName;
		this.deliverability = deliverability;
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
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the stateOrProvince
	 */
	public String getStateOrProvince() {
		return stateOrProvince;
	}

	/**
	 * @param stateOrProvince the stateOrProvince to set
	 */
	public void setStateOrProvince(String stateOrProvince) {
		this.stateOrProvince = stateOrProvince;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode the postalcode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * @param countryCode the countryCode to set
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * @return the deliverability code
	 */
	public String getDeliverability() {
		return deliverability;
	}

	/**
	 * @param deliverability the deliverability to set
	 */
	public void setDeliverability(String deliverability) {
		this.deliverability = deliverability;
	}
}
