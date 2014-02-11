/* SVN FILE: $Id: MessageLogTable.java 5340 2012-09-27 14:48:52Z jvuccolo $ */
package edu.psu.iam.cpr.core.database.tables;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
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
	 * @param messageConsumerKey contains the service provider identifier.
	 * @param serviceKey contains the service key.
	 * @param messageSent contains the json message that was sent.
	 * @param requestUserid contains the user requesting this message be sent.
	 */
	
	public MessageLogTable(final long webServiceId, final long messageConsumerKey, final long serviceKey,
			final String messageSent, final String requestUserid) {
		super();
		final MessageLog bean = new MessageLog();
		final Date d = new Date();
		setMessageLogBean(bean);
		
		bean.setWebServiceKey(webServiceId);
		bean.setMessageConsumerKey(messageConsumerKey);
		bean.setServiceKey(serviceKey);
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
	public void addMessageLog(final Database db) {
		
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
	public void updateMessageLog(final Database db, final String successFlag, final Long numberOfTries) {
		

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
	public MessageLogReturn[] getMessageLog(final Database db, final long messageLogKey) {
		
		// Init some variables.
		final List<MessageLogReturn> results = new ArrayList<MessageLogReturn>();
		final Session session = db.getSession();

		// Create the hibernate select statement.
		final Query query = session.createQuery("from MessageLog where messageLogKey = :message_log_key_in");
		query.setParameter("message_log_key_in", messageLogKey);

		for (final Iterator<?> it = query.list().iterator(); it.hasNext(); ) {

			// For each result, store its value in the return class.
			MessageLog messageLog = (MessageLog) it.next();

			results.add(new MessageLogReturn(messageLog.getMessageLogKey(),
					messageLog.getWebServiceKey(),
					messageLog.getMessageConsumerKey(),
					messageLog.getServiceKey(),
					messageLog.getMessageSent(),
					messageLog.getNumberOfTries(),
					messageLog.getSuccessFlag(),
					messageLog.getRequestUserid()));
		}

		return results.toArray(new MessageLogReturn[results.size()]);
	}	
}
