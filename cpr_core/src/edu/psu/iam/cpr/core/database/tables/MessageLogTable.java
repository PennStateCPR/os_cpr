/* SVN FILE: $Id: MessageLogTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.MessageLog;
import edu.psu.iam.cpr.core.service.returns.MessageLogReturn;

/**
 * This class provides an implementation for the messaging log database table.
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
public class MessageLogTable {

	private static final int WEB_SERVICE_KEY = 0;
	private static final int SERVICE_PROVISIONER_KEY = 1;
	private static final int MESSAGE_SENT = 2;
	private static final int NUMBER_OF_TRIES = 3;
	private static final int SUCCESS_FLAG = 4;
	private static final int REQUEST_USERID = 5;
	private static final int BUFFER_SIZE = 300;
	
	/** Contains a reference to the message log database bean */
	private MessageLog messageLogBean;
	
	/**
	 * Constructor.
	 */
	public MessageLogTable() {
		super();
	}
	
	/**
	 * Constructor for adding a new message log
	 * @param webServiceId contains the web service identifier.
	 * @param serviceProviderId contains the service provider identifier.
	 * @param messageSent contains the json message that was sent.
	 * @param requestUserid contains the user requesting this message be sent.
	 */
	
	public MessageLogTable(long webServiceId, long serviceProviderId,
			String messageSent, String requestUserid) {
		super();
		final MessageLog bean = new MessageLog();
		final Date d = new Date();
		setMessageLogBean(bean);
		
		bean.setWebServiceKey(webServiceId);
		bean.setServiceProvisionerKey(serviceProviderId);
		bean.setMessageSent(messageSent);
		bean.setRequestUserid(requestUserid);
		
		bean.setNumberOfTries(0L);
		bean.setSuccessFlag("N");
		
		bean.setCreatedOn(d);
		bean.setLastUpdateOn(d);

	}

	/**
	 * @param messageLogBean the messageLogBean to set
	 */
	public final void setMessageLogBean(MessageLog messageLogBean) {
		this.messageLogBean = messageLogBean;
	}

	/**
	 * @return the messageLogBean
	 */
	public MessageLog getMessageLogBean() {
		return messageLogBean;
	}
	
	/**
	 * This routine is used to add a message log for communication between 
	 * a Web Service and a Service Provider about a specific message.
	 * @param db contains an open database connection.
	 */
	public void addMessageLog(Database db) {
		
		final Session session = db.getSession();
		final MessageLog bean = getMessageLogBean();
		session.save(bean);
		session.flush();
		
	}
	
	
	/**
	 * This routine is used to update a log message with the number of tries or success flag.
	 * @param db contains an open database connection.
	 * @param successFlag contains the value of the success flag.
	 * @param numberOfTries contains the number of tries that were performed to send this message.
	 */
	public void updateMessageLog(Database db, String successFlag, Long numberOfTries) {
		

		final Session session = db.getSession();
		final MessageLog bean = getMessageLogBean();

		bean.setSuccessFlag(successFlag);
		bean.setNumberOfTries(numberOfTries);
		bean.setLastUpdateOn(new Date());

		session.update(bean);
		session.flush();
	}
	
	/**
	 * This routine is used to get a message log.
	 * @param db contains an open database connection.
	 * @param messageLogKey contains the message log key to be retrieved.
	 * @return MessageLogReturn array containing all of the message information.
	 */
	public MessageLogReturn[] getMessageLog(Database db, long messageLogKey) {
		
		// Init some variables.
		final ArrayList<MessageLogReturn> results = new ArrayList<MessageLogReturn>();
		final Session session = db.getSession();
		final StringBuilder sb = new StringBuilder(BUFFER_SIZE);

		// Build the select statement as a string.
		sb.append("SELECT ");
		sb.append("web_service_key, ");
		sb.append("service_provisioner_key, ");
		sb.append("message_sent, ");
		sb.append("number_of_tries, ");
		sb.append("success_flag, ");
		sb.append("request_userid ");
		sb.append("FROM message_log ");
		sb.append("WHERE message_log_key = :message_log_key_in ");

		// Create the hibernate select statement.
		final SQLQuery query = session.createSQLQuery(sb.toString());
		query.setParameter("message_log_key_in", messageLogKey);
		query.addScalar("web_service_key", StandardBasicTypes.LONG);
		query.addScalar("service_provisioner_key", StandardBasicTypes.LONG);
		query.addScalar("message_sent", StandardBasicTypes.STRING);
		query.addScalar("number_of_tries", StandardBasicTypes.LONG);
		query.addScalar("success_flag", StandardBasicTypes.STRING);
		query.addScalar("request_userid", StandardBasicTypes.STRING);

		final Iterator<?> it = query.list().iterator();

		// Loop for the results.
		while (it.hasNext()) {

			// For each result, store its value in the return class.
			Object res[] = (Object []) it.next();

			MessageLogReturn msgLog = new MessageLogReturn();
			msgLog.setMessageLogKey(messageLogKey);
			msgLog.setWebServiceKey((Long) res[WEB_SERVICE_KEY]);
			msgLog.setServiceProvisionerKey((Long) res[SERVICE_PROVISIONER_KEY]);
			msgLog.setMessageSent((String) res[MESSAGE_SENT]);
			msgLog.setNumberOfTries((Long) res[NUMBER_OF_TRIES]);
			msgLog.setSuccessFlag((String) res[SUCCESS_FLAG]);
			msgLog.setRequestUserid((String) res[REQUEST_USERID]);
			results.add(msgLog);
		}

		return results.toArray(new MessageLogReturn[results.size()]);
	}	
}
