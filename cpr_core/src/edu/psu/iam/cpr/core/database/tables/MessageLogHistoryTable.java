/* SVN FILE: $Id$ */
package edu.psu.iam.cpr.core.database.tables;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.MessageLog;
import edu.psu.iam.cpr.core.database.beans.MessageLogHistory;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.GeneralDatabaseException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.service.returns.MessageLogHistoryReturn;

/**
 * This class provides an implementation for the message log history database table.
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
 * @author $Author$
 * @version $Rev$
 * @lastrevision $Date$
 */
public class MessageLogHistoryTable {

	private static final int MESSAGE_LOG_KEY = 0;
	private static final int MESSAGE_SENT_TIMESTAMP = 1;
	private static final int MESSAGE_RECEIVED_TIMESTAMP = 2;
	private static final int MESSAGE_RECEIVED = 3;
	private static final int ERROR_CODE = 4;
	private static final int ERROR_MESSAGE = 5;
	private static final int TRY_NUMBER = 6;
	private static final int BUFFER_SIZE = 300;
	
	/** Contains a reference to the message log history database bean */
	private MessageLogHistory messageLogHistoryBean;
	
	/**
	 * Constructor.
	 */
	public MessageLogHistoryTable() {
		super();
	}

	/**
	 * Constructor
	 * @param messageLogBean contains the message log to associate this history
	 * @throws Exception
	 */
	public MessageLogHistoryTable(MessageLog messageLogBean) throws Exception {
		
		super();
		final MessageLogHistory bean = new MessageLogHistory();
		final Date d = new Date();
		setMessageLogHistoryBean(bean);
		
		bean.setMessageLogKey(messageLogBean.getMessageLogKey());
		bean.setMessageId(null);
		bean.setMessageSentTimestamp(d);
		bean.setCreatedOn(d);
		bean.setLastUpdateOn(d);
		bean.setTryNumber(messageLogBean.getNumberOfTries() + 1);
		messageLogBean.setNumberOfTries(bean.getTryNumber());
		
	}

	/**
	 * @param messsageLogHistoryBean the messsageLogHistoryBean to set
	 */
	public final void setMessageLogHistoryBean(MessageLogHistory messsageLogHistoryBean) {
		this.messageLogHistoryBean = messsageLogHistoryBean;
	}

	/**
	 * @return the messsageLogHistoryBean
	 */
	public MessageLogHistory getMessageLogHistoryBean() {
		return messageLogHistoryBean;
	}
	
	/**
	 * This routine is used to add history to a message log used for communication between a Web Service and a Service Provider.
	 * @param db contains an open database connection.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public void addMessageLogHistory(Database db) throws CprException {
		
		try {

			final Session session = db.getSession();
			final MessageLogHistory bean = getMessageLogHistoryBean();

			session.save(bean);
			session.flush();
		}
		catch (Exception e ) {
			throw new CprException(ReturnType.ADD_FAILED_EXCEPTION, "messageLogHistory");
		}
		
	}
	
	
	/**
	 * This routine is used to update a log message with the message sent.
	 * @param db contains an open database connection.
	 * @param messageId contains the message identifier string.
	 * @throws CprException will be thrown if there are any problems.
	 */
	public void updateMessageLogHistory(Database db, String messageId) throws CprException {
		
		try {

			final Session session = db.getSession();
			final MessageLogHistory bean = getMessageLogHistoryBean();
			bean.setMessageId(messageId);

			session.save(bean);
			session.flush();
		}
		catch (Exception e ) {
			throw new CprException(ReturnType.UPDATE_FAILED_EXCEPTION, "messageLogHistory");
		}
		
	}
	
	/**
	 * This routine is used to get a message history log.
	 * @param db contains an open database connection.
	 * @param messageId contains the message identifier.
	 * @throws GeneralDatabaseException will be thrown if there are any database problems.
	 * @return will return an array of message log history information.
	 */
	public MessageLogHistoryReturn[] getMessageLogHistory(Database db, String messageId) throws GeneralDatabaseException {
		
		try {
			
			// Init some variables.
			final ArrayList<MessageLogHistoryReturn> results = new ArrayList<MessageLogHistoryReturn>();
			final Session session = db.getSession();
			final StringBuilder sb = new StringBuilder(BUFFER_SIZE);
			
			// Build the select statement as a string.
			sb.append("SELECT ");
		    sb.append("message_log_key, ");
		    sb.append("message_sent_timestamp, ");
		    sb.append("message_received_timestamp, ");
		    sb.append("message_received, ");
		    sb.append("error_code, ");
		    sb.append("error_message, ");
		    sb.append("try_number ");
		    sb.append("FROM message_log_history ");
		    sb.append("WHERE message_id = :message_id_in ");
		    
		    // Create the hibernate select statement.
		    final SQLQuery query = session.createSQLQuery(sb.toString());
		    query.setParameter("message_id_in", messageId);
		    query.addScalar("message_log_key", StandardBasicTypes.LONG);
		    query.addScalar("message_sent_timestamp", StandardBasicTypes.TIMESTAMP);
		    query.addScalar("message_received_timestamp", StandardBasicTypes.TIMESTAMP);
		    query.addScalar("message_received", StandardBasicTypes.STRING);
		    query.addScalar("error_code", StandardBasicTypes.STRING);
		    query.addScalar("error_message", StandardBasicTypes.STRING);
		    query.addScalar("try_number", StandardBasicTypes.LONG);
		    
		    final Iterator<?> it = query.list().iterator();
		    
		    // Loop for the results.
		    while (it.hasNext()) {
		    	
		    	// For each result, store its value in the return class.
		    	Object res[] = (Object []) it.next();
				
		    	MessageLogHistoryReturn msgLogHistory = new MessageLogHistoryReturn();
		    	msgLogHistory.setMessageId(messageId);
		    	msgLogHistory.setMessageLogKey((Long) res[MESSAGE_LOG_KEY]);
		    	msgLogHistory.setMessageSentTimestamp((Timestamp) res[MESSAGE_SENT_TIMESTAMP]);
		    	msgLogHistory.setMessageReceivedTimestamp((Timestamp) res[MESSAGE_RECEIVED_TIMESTAMP]);
		    	msgLogHistory.setMessageReceived((String) res[MESSAGE_RECEIVED]);
		    	msgLogHistory.setErrorCode((String) res[ERROR_CODE]);
		    	msgLogHistory.setErrorMessage((String) res[ERROR_MESSAGE]);
		    	msgLogHistory.setTryNumber((Long) res[TRY_NUMBER]);
				results.add(msgLogHistory);
		    }
		    
			return results.toArray(new MessageLogHistoryReturn[results.size()]);
			
		}
		catch (Exception e) {
			throw new GeneralDatabaseException("Unable to retrieve message history log for message identifier = " + messageId);	
		}
	}
	
}
