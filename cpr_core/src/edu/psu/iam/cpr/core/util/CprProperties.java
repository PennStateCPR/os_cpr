/* SVN FILE: $Id: CprProperties.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.util;

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
public final class CprProperties {
		
	/** Contains an instance of the singleton class */
	private static CprProperties instance = null;
	
	/** Contains the model the CPR is running in */
	private static CprRunningMode cprMode = CprRunningMode.PRODUCTION;

	/** Contains the name of the property containing the value of the CPR mode.  This property is set by Tomcat. */
	private static final String CPR_MODE_PROPERTY = "edu.psu.iam.cpr.core.mode";
	
	/** CPR Properties file name */
	private static final String CPR_PROPERTY_FILE = "cpr.properties";
	
	/** Contains all of the CPR Properties that were loaded from a file */
	private static Properties properties = null;
	
	/**
	 * Will obtain an instance of the singleton class.
	 * @return will return the instance of the class.
	 */
	public static synchronized CprProperties getInstance() {
		
		if (instance == null) {
			instance = new CprProperties();
			
			// Get all of the property values.
			try {
				final String prop = System.getProperty(CPR_MODE_PROPERTY).toUpperCase().trim();
				cprMode = CprRunningMode.valueOf(prop);
			}
			catch (Exception e) {
				cprMode = CprRunningMode.PRODUCTION;
			}
			
			instance.loadProperties();
			
		}
		return instance;
		
	}

	/**
	 * @return the cprMode
	 */
	public synchronized CprRunningMode getCprMode() {
		return cprMode;
	}
	
	/**
	 * This routine used only by test case is used to set the running mode.
	 * @param cprMode contain the running mode of the CPR.
	 */
	public synchronized void setCprMode(CprRunningMode cprMode) {
		CprProperties.cprMode = cprMode;
	}
	
	/**
	 * @return the properties.
	 */
	public synchronized Properties getProperties() {
		return properties;
	}
	
	/**
	 * This routine used only by test cases are used to set the properties.
	 * @param properties
	 */
	public synchronized void setProperties(Properties properties) {
		CprProperties.properties = properties;
	}
	
	/**
	 * This routine is use to load a cpr properties file, which one that is loaded is based on the CPR Mode that is passed
	 * into the routine.
	 */
	private synchronized void loadProperties() {
				
		InputStream is = null;
		properties = new Properties();
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CPR_PROPERTY_FILE);
			properties.load(is);
		} 
		catch (Exception e) {
		}
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
			}
		}
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("Clone is not allowed.");
	}
}
