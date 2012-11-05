/* SVN FILE: $Id: ServiceLogTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.Date;
import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.SessionFactoryUtil;
import edu.psu.iam.cpr.core.database.beans.ServiceLog;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;

/**
 * This class provides an implementation for interfacing with the service log database table.
 * This table contains a log of all service activity within the CPR.
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
 * @package edu.psu.iam.cpr.core.database.tables
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class ServiceLogTable {
	
	private static final int BUFFER_SIZE = 1024;
	
	/** service log bean */
	private ServiceLog serviceLogBean;
	
	/** Constant the represents a value that is not found */
	private static final long NOT_FOUND_VALUE = -1L;
	
	/**
	 * Constructor.
	 */
	public ServiceLogTable() {
		super();
	}
		
	/**
	 * @param serviceLogBean the serviceLogBean to set
	 */
	public void setServiceLogBean(ServiceLog serviceLogBean) {
		this.serviceLogBean = serviceLogBean;
	}

	/**
	 * @return the serviceLogBean
	 */
	public ServiceLog getServiceLogBean() {
		return serviceLogBean;
	}

	/**
	 * @param db a database object that contains an open database connection.
	 * @param serviceName the service name to obtain a web service identifier for.
	 * @return will contain the web service key
	 * @throws CprException 
	 */
	public Long getWebServiceKey(Database db, String serviceName) throws CprException {
		
		Long webServiceKey = NOT_FOUND_VALUE;
		final Session session = db.getSession();
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		sb.append("SELECT web_service_key FROM web_service WHERE web_service = :web_service_in");

		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("web_service_in", serviceName);
		query.addScalar("web_service_key", StandardBasicTypes.LONG);
		final Iterator<?> it = query.list().iterator();
		if (it.hasNext()) {
			webServiceKey = (Long) it.next();
		}
		
		if (webServiceKey == NOT_FOUND_VALUE) {
			throw new CprException(ReturnType.WEB_SERVICE_NOT_FOUND_EXCEPTION, serviceName);
		}
		
		return webServiceKey;
	}

	/**
	 * This routine is used to write the end of a log record.
	 * @param db contains an object for a database class.
	 * @param results contains the results of executing this service.
	 */
	public void endLog(Database db, String results)  {
		
		Session logSession = null;
		try {
			
			logSession = SessionFactoryUtil.getSessionFactory().openSession();
			logSession.getTransaction().begin();
			final ServiceLog bean = getServiceLogBean();
			
			final Date end = new Date();
			bean.setRequestEnd(end);
			bean.setRequestDuration(end.getTime() - bean.getRequestStart().getTime());
			bean.setResultString(results);
			
			logSession.save(bean);
			logSession.getTransaction().commit();
		}
		catch (Exception e) {
			try {
				logSession.getTransaction().rollback();
			}
			catch (Exception e1) {}
		}
		finally {
			logSession.close();
		}
	}
}
