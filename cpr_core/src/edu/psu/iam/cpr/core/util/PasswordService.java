/* SVN FILE: $Id: PasswordService.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
/**
 * This class provides an implementation of a password encryption algorithm.
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
 * @package edu.psu.iam.cpr.core.util
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
package edu.psu.iam.cpr.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;

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
 * @author jvuccolo
 *
 */
public final class PasswordService
{
	/** Contains an instance of the password service */
	private static PasswordService instance;

	/**
	 * Constructor.
	 */
	private PasswordService()
	{
	}

	/**
	 * This routine is used to encrypt a plaintext password.
	 * @param plaintext contains the plaintext password.
	 * @return contains the encrypted password.
	 * @throws Exception will be thrown if there are any problems.
	 */
	public synchronized String encrypt(String plaintext) throws Exception
	{
		MessageDigest md = null;
		try
		{
			md = MessageDigest.getInstance("SHA");
		}
		catch(NoSuchAlgorithmException e) {
			throw new Exception(e.getMessage());
		}
		try
		{
			md.update(plaintext.getBytes("UTF-8"));
		}
		catch(UnsupportedEncodingException e) {
			throw new Exception(e.getMessage());
		}

		byte raw[] = md.digest();
		return Base64.encodeBase64String(raw);
	}

	/**
	 * This routine returns an instance of the singleton.
	 * @return will return the instance.
	 */
	public static synchronized PasswordService getInstance()
	{
		if(instance == null) {
			instance = new PasswordService(); 
		} 
		return instance;
	}
}
