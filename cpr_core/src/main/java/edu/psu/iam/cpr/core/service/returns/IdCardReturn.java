/* SVN FILE: $Id */
package edu.psu.iam.cpr.core.service.returns;
/**
 * This class is used to represent the return from executing GetIdCard
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
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class IdCardReturn {
	/**
	 * contains the PersonIdCard data
	 */
	private PersonIdCardReturn aPersonIdCardReturn;
	
	/**
	 * contains the PersonPhoto data
	 */
	private PhotoReturn aPhotoReturn;

	/**
	 * @return the aPersonIdCardReturn
	 */
	public PersonIdCardReturn getaPersonIdCardReturn() {
		return aPersonIdCardReturn;
	}

	/**
	 * @param aPersonIdCardReturn the aPersonIdCardReturn to set
	 */
	public void setaPersonIdCardReturn(PersonIdCardReturn aPersonIdCardReturn) {
		this.aPersonIdCardReturn = aPersonIdCardReturn;
	}

	/**
	 * @return the aPhotoReturn
	 */
	public PhotoReturn getaPhotoReturn() {
		return aPhotoReturn;
	}

	/**
	 * @param aPhotoReturn the aPhotoReturn to set
	 */
	public void setaPhotoReturn(PhotoReturn aPhotoReturn) {
		this.aPhotoReturn = aPhotoReturn;
	}

	/**
	 * 
	 */
	public IdCardReturn() {
		super();
		
	}

	/**
	 * @param aPersonIdCardReturn
	 * @param aPhotoReturn
	 */
	public IdCardReturn(PersonIdCardReturn aPersonIdCardReturn,
			PhotoReturn aPhotoReturn) {
		super();
		this.aPersonIdCardReturn = aPersonIdCardReturn;
		this.aPhotoReturn = aPhotoReturn;
	}

	/**
	 * @param aPersonIdCardReturn
	 */
	public IdCardReturn(PersonIdCardReturn aPersonIdCardReturn) {
		super();
		this.aPersonIdCardReturn = aPersonIdCardReturn;
	}

	
	
}
