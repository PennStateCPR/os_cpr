/* SVN FILE: $Id: ServiceProvisionerQueue.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
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
 * @package edu.psu.iam.cpr.core.messaging
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
package edu.psu.iam.cpr.core.messaging;

import java.util.ArrayList;
import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.VSpNotification;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;

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
public class ServiceProvisionerQueue {

	private static final int SP_KEY = 0;
	private static final int SP_NAME = 1;
	private static final int WEB_SERVICE_KEY = 2;
	private static final int WEB_SERVICE = 3;
	private static final int SP_QUEUE = 4;
	
	private static final int BUFFER_SIZE = 256;
	
	/** Contains a reference to the service providers notification view */
	private VSpNotification spNotificationView;
	
	/**
	 * Constructor.
	 */
	public ServiceProvisionerQueue() {
		super();
	}
	
	/**
	 * Constructor.
	 * @param serviceProvisionerKey contains the service provider identifier.
	 * @param serviceProvisionerName contains the name of the service provisioner.
	 * @param webServiceKey contains the web service identifier.
 	 * @param webServiceName contains the name of web service associated with the service provisioner.
	 * @param serviceProvisionerQueueName contains the name of the JMS queue.
	 */
	public ServiceProvisionerQueue(long serviceProvisionerKey, 
								String serviceProvisionerName,
								long webServiceKey, 	
								String webServiceName, 
								String serviceProvisionerQueueName) {
		super();
		
		final VSpNotification bean = new VSpNotification();
		setSpNotificationView(bean);
		
		bean.setServiceProvisioner(serviceProvisionerName);
		bean.setServiceProvisionerKey(serviceProvisionerKey);
		bean.setServiceProvisionerQueue(serviceProvisionerQueueName);
		bean.setWebService(webServiceName);
		bean.setWebServiceKey(webServiceKey);
	}

	/**
	 * @param spNotificationView the spNotificationView to set
	 */
	public final void setSpNotificationView(VSpNotification spNotificationView) {
		this.spNotificationView = spNotificationView;
	}

	/**
	 * @return the spNotificationView
	 */
	public VSpNotification getSpNotificationView() {
		return spNotificationView;
	}

	/**
	 * This routine is used to obtain a list of service providers and their respective queues for a particular web service.
	 * @param db contains a database connection.
	 * @param webService contains the web server to do the query for.
	 * @return will return an ArrayList of service provider information.
	 * @throws GeneralDatabaseException will be thrown for any problems.
	 */
	public static ArrayList<ServiceProvisionerQueue> getServiceProvisionerQueues(Database db, String webService) throws GeneralDatabaseException {
		
		try {
			final ArrayList<ServiceProvisionerQueue> results = new ArrayList<ServiceProvisionerQueue>();
			final Session session = db.getSession();
			
			final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			sb.append("SELECT service_provisioner_key, service_provisioner, web_service_key, web_service, ");
			sb.append("service_provisioner_queue FROM v_sp_notification WHERE web_service=:web_service ");
			
			final SQLQuery query = session.createSQLQuery(sb.toString());
			query.setParameter("web_service", webService);
			query.addScalar("service_provisioner_key", StandardBasicTypes.LONG);
			query.addScalar("service_provisioner", StandardBasicTypes.STRING);
			query.addScalar("web_service_key", StandardBasicTypes.LONG);
			query.addScalar("web_service", StandardBasicTypes.STRING);
			query.addScalar("service_provisioner_queue", StandardBasicTypes.STRING);
			final Iterator<?> it = query.list().iterator();
			
			while (it.hasNext()) {
				Object res[] = (Object []) it.next();
				results.add(new ServiceProvisionerQueue((Long) res[SP_KEY], 
						 (String) res[SP_NAME],
						 (Long) res[WEB_SERVICE_KEY],
						 (String) res[WEB_SERVICE],
						 (String) res[SP_QUEUE]));
			}
			
			return results;
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve the mapping for web service " + webService);
		}
 	}
}
