/* SVN FILE: $Id: SessionFactoryUtil.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * This class implements the production session factory for the CPR.  The
 * session factory is used by hibernate to determine how to connect to the database
 * and how to map classes for beans.
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
 * @package edu.psu.iam.cpr.core.database
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public final class SessionFactoryUtil {
	
	/** The session factory enables the establishment of database connections (sessions). */
	private static SessionFactory sessionFactory = null;
	
	/**
	 * Constructor.
	 */
	private SessionFactoryUtil() {
		
	}
	
	static {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
