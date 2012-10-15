/* SVN FILE: $Id: GeneralDatabaseException.java 5340 2012-09-27 14:48:52Z jvuccolo $ */

package edu.psu.iam.cpr.core.error;
/**
 * This class provides an implementation for a general database exception.
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
public class GeneralDatabaseException extends Exception {

	/*
	 * contains the SerialUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor
	 */
	public GeneralDatabaseException() {
		super();
	}

	/**
	 * 
	 * @param message  exception message
	 * @param cause exception cause
	 */
	public GeneralDatabaseException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param message  exception message
	 */
	public GeneralDatabaseException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param cause reason for the exception
	 */
	public GeneralDatabaseException(Throwable cause) {
		super(cause);
	}

}
