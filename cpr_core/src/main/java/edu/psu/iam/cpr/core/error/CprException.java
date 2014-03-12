/* SVN FILE: $Id: CprException.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.error;
/**
 * This class represents a CPR exception that can be thrown for a variety of reasons.
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
 * @package edu.psu.iam.cpr.core.database.error
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class CprException extends Exception {

	/** Serial UID. */
	private static final long serialVersionUID = 1L;

	/** Contains the return type enumerated value. */
	private final ReturnType returnType;

	/** Contains the parameter value string (optional). */
	private final String parameterValue;

	/** Contains a throwable that caused this exception. */
	private final Throwable cause;


	/**
	 * Default constructor
	 *
	 * @param returnType contains the return type parameter.
	 * @param parameterValue contains the  parameter value.
	 */
	public CprException(final ReturnType returnType, final String parameterValue) {
		super();
		this.returnType = returnType;
		this.parameterValue = parameterValue;
        this.cause = null;
	}

	/**
	 * Constructor
	 * @param returnType contains the return type parameter.
	 */
	public CprException(final ReturnType returnType) {
		super();
		this.returnType = returnType;
		this.parameterValue = null;
        this.cause = null;
	}

	/**
	 * Constructor
	 *
	 * @param returnType contains the return type parameter.
	 * @param parameterValue contains the parameter value.
	 * @param cause The detail cause for the exception
	 */
	public CprException(final ReturnType returnType, final String parameterValue, final Throwable cause) {
		super();
		this.returnType = returnType;
		this.parameterValue = parameterValue;
		this.cause = cause;
	}

	/**
	 * Constructor
	 * @param returnType contains the return type parameter.
	 * @param cause The detail cause for the exception
	 */
	public CprException(final ReturnType returnType, final Throwable cause) {
		super();
		this.returnType = returnType;
		this.parameterValue = null;
		this.cause = cause;
	}

	/**
	 * Conntructor
	 * @param cause The detail cause for the exception
	 */
	public CprException(final Throwable cause) {
		super(cause);
		this.returnType = null;
		this.parameterValue = null;
		this.cause = cause;
	}

	/**
	 * @return the returnType
	 */
	public ReturnType getReturnType() {
		return returnType;
	}

	/**
	 * @return the parameterValue
	 */
	public String getParameterValue() {
		return parameterValue;
	}

	/**
	 * @return the detail cause.
	 */
	@Override
	public synchronized Throwable getCause() {
		return this.cause;
	}

}
