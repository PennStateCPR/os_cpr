/* SVN FILE: $Id: CprProperties.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import edu.psu.iam.cpr.core.database.types.CprRunningMode;

/**
 * Singleton class used to store off system properties. 
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
public enum CprProperties {
		
	INSTANCE;
	
	/** Contains the model the CPR is running in */
	private CprRunningMode cprMode = CprRunningMode.PRODUCTION;

	/** Contains the name of the property containing the value of the CPR mode.  This property is set by Tomcat. */
	private static final String CPR_MODE_PROPERTY = "edu.psu.iam.cpr.core.mode";
	
	/** CPR Properties file name */
	private static final String CPR_PROPERTY_FILE = "cpr.properties";
	
	/** Contains all of the CPR Properties that were loaded from a file */
	private Properties properties = null;
	
	/**
	 * Constructor.
	 * @throws IOException 
	 */
	private CprProperties() {
		
		String prop = System.getProperty(CPR_MODE_PROPERTY);
		if (prop != null) {
			cprMode = CprRunningMode.valueOf(prop.trim().toUpperCase());
		}
		
		try {
			loadProperties();
		} 
		catch (IOException e) {
		}		
	}

	/**
	 * @return the cprMode
	 */
	public CprRunningMode getCprMode() {
		return cprMode;
	}
	
	/**
	 * @return the properties.
	 */
	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * This routine is use to load a cpr properties file, which one that is loaded is based on the CPR Mode that is passed
	 * into the routine.
	 * @throws IOException 
	 */
	private void loadProperties() throws IOException {
				
		InputStream is = null;
		properties = new Properties();
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CPR_PROPERTY_FILE);
			properties.load(is);
		} 
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
			}
		}
	}
}
