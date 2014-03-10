/* SVN FILE: $Id: MessagingCore.java 8256 2013-10-08 15:17:30Z jvuccolo $ */
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
 * @version $Rev: 8256 $
 * @lastrevision $Date: 2013-10-08 11:17:30 -0400 (Tue, 08 Oct 2013) $
 */
package edu.psu.iam.cpr.core.messaging;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.StatelessSession;
import org.json.JSONException;

import edu.psu.iam.cpr.core.database.Database;
import edu.psu.iam.cpr.core.database.beans.MessageLog;
import edu.psu.iam.cpr.core.database.beans.MessageLogHistory;
import edu.psu.iam.cpr.core.database.beans.VConsumerNotificationDest;
import edu.psu.iam.cpr.core.database.beans.VSpNotification;
import edu.psu.iam.cpr.core.database.tables.MessageLogHistoryTable;
import edu.psu.iam.cpr.core.database.tables.MessageLogTable;
import edu.psu.iam.cpr.core.database.types.ChangeNotificationType;
import edu.psu.iam.cpr.core.database.types.CprMessageType;
import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.MessageKeyName;
import edu.psu.iam.cpr.core.error.CprException;
import edu.psu.iam.cpr.core.error.ReturnType;
import edu.psu.iam.cpr.core.util.CprProperties;

/**
 * Contains the JMS messaging interface for the web services to be able to initialize connections and queue, send messages and receive messages
 *
 */
public class MessagingCore {

	/** Wait timeout value for recieveWait */
	private static final long MESSAGING_WAIT_TIMEOUT = 1000L;

	/** Contains the batch message consumer key value */
	private Long batchMessageConsumerKey = null;

	private static final String BATCH_PROCESSING_CONSUMER = "Batch Processing";

	/** Instance of logger */
	private static final Logger LOG4J_LOGGER = Logger.getLogger(MessagingCore.class);

	private static final int BUFFER_SIZE = 2048;


	/** Array of message queues */
    private List<VSpNotification> msgQueues = null;

	/**  Contains the notification queues stored by change type, for example TITLE_CHANGE */
    private Map<String, List<VSpNotification>> msgNotificationQueues = null;

	 /** Connection factory */
    private ConnectionFactory jmsConnectionFactory = null;

    /** Connection */
    private Connection jmsConnection = null;

	/** JMS Session */
	private Session jmsSession = null;

	/**
	 * Constructor using the default connection factory
	 * @throws CprException
	 */
	public MessagingCore() throws CprException {
		super();
	}

	/**
	 * Constructor.
	 * @param db contains a database object which holds an open connection.
	 * @param serviceName contains the service name that is sending/receiving messages.
	 */
	public MessagingCore(Database db, String serviceName) {


	        // Get the list of queues for the service.  If no queues were found, then just return.
	    msgQueues = new ArrayList<VSpNotification>();
	    org.hibernate.Session session = db.getSession();

        Query query = session.createQuery("from VSpNotification where webService = :service_name");
        query.setParameter("service_name", serviceName);
        for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
                msgQueues.add((VSpNotification) it.next());
        }

	}

	/**
	 * Constructor.
	 * @param session contains the stateless database session object
	 * @param serviceName contains the serviceName
	 */
	public MessagingCore(StatelessSession session, String serviceName)  {

		initQueueMap(session, serviceName);

		getMessageConsumerKey(session, BATCH_PROCESSING_CONSUMER);

	}

	/**
	 * This helper method is used to obtain a message consumer key for a specific message consumer.  Right now its only used for
	 * batch processing.
	 * @param session contains the stateless database session.
	 * @param consumer contains the consumer to be searched for.
	 */
	private void getMessageConsumerKey(StatelessSession session, String consumer) {

		Query query = session.createQuery("from MessageConsumer where consumer = :consumer AND endDate is NULL");
		query.setParameter("consumer", consumer);
		for (Iterator<?> it = query.list().iterator(); it.hasNext(); ) {
			edu.psu.iam.cpr.core.database.beans.MessageConsumer messageConsumer = (edu.psu.iam.cpr.core.database.beans.MessageConsumer) it.next();
			setBatchMessageConsumerKey(messageConsumer.getMessageConsumerKey());
		}

	}

	/**
	 * Initializes the jms context, broker and connection
	 * @throws JMSException

	 */
	public void initializeMessaging() throws JMSException  {
		final Properties props = CprProperties.INSTANCE.getProperties();


        // Get a connection and start a session.
        jmsConnectionFactory = new ActiveMQConnectionFactory(props.getProperty(CprPropertyName.CPR_JMS_BROKER.toString()));
        jmsConnection = jmsConnectionFactory.createConnection(props.getProperty(CprPropertyName.CPR_JMS_USERID.toString()),
                        props.getProperty(CprPropertyName.CPR_JMS_PASSWORD.toString()));
        jmsConnection.start();
        jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);

	}

    /**
     * Initialize message notification mapping of notification type to queue list.
     * @param databaseSession contains the database stateless session
     * @param serviceName contains the serviceName
     */
    private void initQueueMap(StatelessSession databaseSession, String serviceName) {

		// Load data from view
    	final String notificationSqlQuery = "from VConsumerNotificationDest";
    	final Query notificationQuery = databaseSession.createQuery(notificationSqlQuery);
    	if (notificationQuery.list().isEmpty()) {
    		LOG4J_LOGGER.error("Consumer notification dest view (VConsumerNotificationDest) does not contain any records");
   		}

    	msgNotificationQueues = new HashMap<String, List<VSpNotification>>();

    	// loop through results and create the array map entry for each type
    	String previousType = null;
    	List<VSpNotification> queueList = new ArrayList<VSpNotification>();
		for (final Iterator<?> it = notificationQuery.list().iterator(); it.hasNext(); ) {
			VConsumerNotificationDest notificationDbBean = (VConsumerNotificationDest) it.next();

	    	if (previousType == null) {
	    		previousType = notificationDbBean.getNotificationType();
	    	}
	    	else if (!previousType.equals(notificationDbBean.getNotificationType())) {
	    		msgNotificationQueues.put(previousType, queueList);
	    		previousType = notificationDbBean.getNotificationType();
	    		queueList = new ArrayList<VSpNotification>();
	    	}
	    	Long aConsumer = notificationDbBean.getMessageConsumerKey();
			Query query = databaseSession.createQuery("from VSpNotification where webService = :service_name and message_consumer_key=:consumer");
			query.setParameter("service_name", serviceName);
			query.setParameter("consumer", aConsumer);
	    	for (Iterator<?> vSp = query.list().iterator(); vSp.hasNext(); ) {
		    	VSpNotification vSPNotificationBean = (VSpNotification)vSp.next();
		    	queueList.add(vSPNotificationBean);
	    	}
	    	if (!it.hasNext()) {
	    			msgNotificationQueues.put(notificationDbBean.getNotificationType(), queueList);
	    		}
	    	}

    }
	/**
     * @return the msgQueues
     */
    public List<VSpNotification> getMsgQueues() {
            return msgQueues;
    }

    /**
     * @param msgQueues the msgQueues to set
     */
    public void setMsgQueues(List<VSpNotification> msgQueues) {
            this.msgQueues = msgQueues;
    }


	/**
	 * @return the msgNotificationQueues
	 */
	public Map<String, List<VSpNotification>> getMsgNotificationQueues() {
		return this.msgNotificationQueues;
	}

	/**
	 * @param msgNotificationQueues the msgNotificationQueues to set
	 */
	public void setMsgNotificationQueues(
			Map<String, List<VSpNotification>> msgNotificationQueues) {
		this.msgNotificationQueues = msgNotificationQueues;
	}

	/**
	 * This routine is used to clean up messaging.
	 */
	public void closeMessaging() {

        // Close the session and connection resources.
		try {
			jmsSession.close();
		}
		catch (Exception e) {
		}

		try {
			jmsConnection.close();
		}
		catch (Exception e) {
		}

		jmsSession = null;
		jmsConnection = null;
	}

	/**
	 *
	 * @param testQueue
	 * @return message received from the given queue, null if no message received
	 * @throws JMSException
	 */
    public String receiveMessageNoWait(Queue testQueue) throws JMSException  {

    	//Create a message consumer.
    	MessageConsumer myMsgConsumer = jmsSession.createConsumer(testQueue);

    	//Receive a message from the queue.


    	//Receive a message from the queue.
    	final Message msg = myMsgConsumer.receiveNoWait();

    	//Retrieve the contents of the message.
    	if (msg == null) {
    		return null;
    	}

    	if (msg instanceof TextMessage) {
    		String txtMsgString = ((TextMessage) msg).getText();
    		if (txtMsgString == null) {
    			return msg.toString();
    		}
    		return txtMsgString;
    	}

    	return msg.toString();

    }

    /**
     *
     * @param queueName is the name of the queue to read from
     * @return message received from the given queue, null if no message received
     * @throws JMSException
     */
    public String receiveMessageWait(String queueName) throws JMSException  {

    	// Create Queue Object and Read/Receive an item
    	Queue testQueue = jmsSession.createQueue(queueName);
    	return receiveMessageWait(testQueue);
    }

	/**
	 *
	 * @param testQueue
	 * @return message received from the given queue, wait if no message available
	 * @throws JMSException
	 */
    public String receiveMessageWait(Queue testQueue) throws JMSException   {

    	//Create a message consumer.
    	final MessageConsumer myMsgConsumer = jmsSession.createConsumer(testQueue);

        //Receive a message from the queue. Will block waiting for message
        final Message msg = myMsgConsumer.receive(MESSAGING_WAIT_TIMEOUT);

        //Retrieve the contents of the message.
        if (msg == null) {
        	return null;
        }
        if (msg instanceof TextMessage) {
        	final String txtMsgString = ((TextMessage) msg).getText();
                if (txtMsgString == null) {
                	return msg.toString();
                }
                myMsgConsumer.close();
                return txtMsgString;
        }

        return msg.toString();

    }

    /**
     * This function is used to record message delivery failures for a message event.
     * @param db contains the database connection.
     * @param msg contains the JSON message.
     */
    public void recordFailures(Database db, JsonMessage msg) {

        // Loop through queue list
        final Iterator<VSpNotification> queueIter = msgQueues.iterator();
        while (queueIter.hasNext()) {

        	try {

                VSpNotification currentQueue = queueIter.next();
                if (currentQueue != null) {

                    // Create a message log to indicate that there has been a failure.
                    MessageLogTable messageLogTable = new MessageLogTable(currentQueue.getWebServiceKey(),
                                    currentQueue.getMessageConsumerKey(),
                                    currentQueue.getServiceKey(),
                                    msg.getJsonObject().toString(),
                                    msg.getRequestedBy());
                    logMessageDelivery(messageLogTable, "FAILURE");
                    messageLogTable.addMessageLog(db);
                	}
                }
                catch (Exception e) {

            }
        }

    }

	/**
     * @param db contains a database object which holds an open connection.
     * @param msg contains the JSON message to be sent to the service provisioners.
     * @throws CprException will be thrown for any CPR related problems.jjjj
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws JMSException will be thrown if there are any messaging issues.
     */
    public void sendMessage(Database db, JsonMessage msg) throws CprException,  JMSException, JSONException {

    	final String replyToQueue = CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_JMS_REPLYTO.toString());
    	MessageProducer msgSender = null;
    	if (msg == null || msg.getJsonObject().toString().length() == 0) {
    		closeMessaging();
    		throw new CprException(ReturnType.MESSAGE_CREATION_EXCEPTION);
    	}
    	try {
    		//Create the message to send to the queues
    		TextMessage myTextMsg = null;

    		myTextMsg = jmsSession.createTextMessage();

    		// Loop through queue list
    		final Iterator<VSpNotification> queueIter = msgQueues.iterator();
    		while (queueIter.hasNext()) {

    			VSpNotification currentDestination= queueIter.next();


    			 // Update the JSON message with the message consumer key, service name, service key and message type.
                msg.setValue(MessageKeyName.MESSAGE_CONSUMER_KEY, currentDestination.getMessageConsumerKey());
                msg.setValue(MessageKeyName.SERVICE, currentDestination.getServiceName());
                msg.setValue(MessageKeyName.SERVICE_KEY, currentDestination.getServiceKey());
                msg.setValue(MessageKeyName.MESSAGE_TYPE, CprMessageType.SERVICE_REQUEST.toString());

                // Store an entry in the message log.
                MessageLogTable messageLogTable = new MessageLogTable(currentDestination.getWebServiceKey(),
                                currentDestination.getMessageConsumerKey(),
                                currentDestination.getServiceKey(),
                                msg.getJsonObject().toString(),
                                msg.getRequestedBy());
                messageLogTable.addMessageLog(db);

                // Store an entry in the message log history.
                MessageLogHistoryTable messageLogHistoryTable = new MessageLogHistoryTable(messageLogTable.getMessageLogBean());
                messageLogHistoryTable.addMessageLogHistory(db);

    			// once the log is started, the messageLogId is set, add it to the msg to be sent
    			msg.setValue(MessageKeyName.MESSAGE_LOG_ID, messageLogTable.getMessageLogBean().getMessageLogKey());

    			// Add in the JSON information.
    			myTextMsg.setText(msg.getJsonObject().toString());
    			myTextMsg.setJMSReplyTo(jmsSession.createQueue(replyToQueue));
    			myTextMsg.setJMSCorrelationID(messageLogHistoryTable.getMessageLogHistoryBean().getMessageLogHistoryKey().toString());

    			// Send the message
                Destination destination = jmsSession.createQueue(currentDestination.getConsumerDestination());
                msgSender = jmsSession.createProducer(destination);
                msgSender.setDeliveryMode(DeliveryMode.PERSISTENT);
                msgSender.send(myTextMsg);
                msgSender.close();


    			// once the message is sent, add an entry in the message log history table for this specific send
    			messageLogTable.updateMessageLog(db, "Y", 1L);
    			messageLogHistoryTable.updateMessageLogHistory(db, myTextMsg.getJMSMessageID());

    			// Log the successful message delivery to the log4j component.
    			logMessageDelivery(messageLogTable, "SUCCESS");
    		}

    	}
    	finally {
    		try {
    			if (msgSender != null) {
    				msgSender.close();
    			}
    		}
    		catch (JMSException e) {

    		}
    	}
    }

	/**
     * @param db contains a database object which holds an open connection.
     * @param messageLog contains the MessageLog bean to attempt to re-deliver.
     * @param consumerQueue is the name of the queue for the message to be written to
     * @throws CprException will be thrown for any CPR related problems.jjjj
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws JMSException will be thrown if there are any messaging issues.
     */
    public void reSendMessage(Database db, MessageLog messageLog, String consumerQueue) throws CprException,  JMSException, JSONException {

    	final String replyToQueue = CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_JMS_REPLYTO.toString());
    	MessageProducer msgSender = null;
      	if (messageLog.getMessageSent() == null || messageLog.getMessageSent().length() == 0) {
    		closeMessaging();
    		throw new CprException(ReturnType.MESSAGE_CREATION_EXCEPTION);
    	}
    	try {
    		//Create the message to send to the queues
    		TextMessage myTextMsg = null;

    		myTextMsg = jmsSession.createTextMessage();

            // Store an entry in the message log.
   			MessageLogTable messageLogTable = new MessageLogTable();

   			// The current bean we are working on
   			messageLogTable.setMessageLogBean(messageLog);

            // Store an entry in the message log history.
            MessageLogHistoryTable messageLogHistoryTable = new MessageLogHistoryTable(messageLogTable.getMessageLogBean());
            messageLogHistoryTable.addMessageLogHistory(db);

            // Number of tries gets update on success or failure, so do it now
            messageLogTable.updateMessageLog(db, "N", messageLog.getNumberOfTries() );

    		// Add in the JSON information.
    		myTextMsg.setText(messageLog.getMessageSent());
    		if(!replyToQueue.equals(consumerQueue)){
    			myTextMsg.setJMSReplyTo(jmsSession.createQueue(replyToQueue));
    		}
    		myTextMsg.setJMSCorrelationID(messageLogHistoryTable.getMessageLogHistoryBean().getMessageLogHistoryKey().toString());

    		// Send the message
            Destination destination = jmsSession.createQueue(consumerQueue);
            msgSender = jmsSession.createProducer(destination);
            msgSender.setDeliveryMode(DeliveryMode.PERSISTENT);
            msgSender.send(myTextMsg);
            msgSender.close();


    	    // once the message is sent, add an entry in the message log history table for this specific send
    		messageLogTable.updateMessageLog(db, "Y", messageLog.getNumberOfTries() );
    		messageLogHistoryTable.updateMessageLogHistory(db, myTextMsg.getJMSMessageID());

    		// Log the successful message delivery to the log4j component.
    		logMessageDelivery(messageLogTable, "SUCCESS");

    	}
    	finally {
    		try {
    			if (msgSender != null) {
    				msgSender.close();
    			}
    		}
    		catch (JMSException e) {

    		}
    	}
    }

    /**
     *
     * @param databaseSession contains the database stateless session
     * @param changeType contains the type of change notifications
     * @param msg contains the json message
     * @throws CprException
     * @throws JMSException
     * @throws JSONException
     */

    public void sendNotification(StatelessSession databaseSession, ChangeNotificationType changeType, JsonMessage msg) throws CprException,  JMSException, JSONException {

    	final String replyToQueue = CprProperties.INSTANCE.getProperties().getProperty(CprPropertyName.CPR_JMS_REPLYTO.toString());
    	final List<VSpNotification> theDest =msgNotificationQueues.get(changeType.toString());



    	MessageProducer msgSender = null;
    	if (msg == null || msg.getJsonObject().toString().length() == 0) {
    		closeMessaging();
    		throw new CprException(ReturnType.MESSAGE_CREATION_EXCEPTION);
    	}

    	try {
    		//Create the message to send to the queues
    		TextMessage myTextMsg = null;

    		myTextMsg = jmsSession.createTextMessage();

    		// Loop through queue list
    		final Iterator<VSpNotification> queueIter = theDest.iterator();
    		while (queueIter.hasNext()) {

    			VSpNotification currentDestination= queueIter.next();


    			 // Update the JSON message with the message consumer key, message type and change type
                msg.setValue(MessageKeyName.MESSAGE_CONSUMER_KEY, currentDestination.getMessageConsumerKey());
                msg.setValue(MessageKeyName.MESSAGE_TYPE, CprMessageType.CHANGE_NOTIFICATION.toString());
                msg.setValue(MessageKeyName.CHANGE_KEY, changeType.toString());

                // Store an entry in the message log.

                MessageLogTable messageLogTable = new MessageLogTable(currentDestination.getWebServiceKey(),
                        currentDestination.getMessageConsumerKey(),
                        currentDestination.getServiceKey(),
                        msg.getJsonObject().toString(),
                        msg.getRequestedBy());
               MessageLog mLogBean= messageLogTable.getMessageLogBean();
               databaseSession.insert(mLogBean);


                // Store an entry in the message log history.
               MessageLogHistoryTable messageLogHistoryTable = new MessageLogHistoryTable(messageLogTable.getMessageLogBean());
               MessageLogHistory mLogHistory = messageLogHistoryTable.getMessageLogHistoryBean();
               databaseSession.insert(mLogHistory);

    			// once the log is started, the messageLogId is set, add it to the msg to be sent
    			msg.setValue(MessageKeyName.MESSAGE_LOG_ID, mLogBean.getMessageLogKey());

    			// Add in the JSON information.
    			myTextMsg.setText(msg.getJsonObject().toString());
    			myTextMsg.setJMSReplyTo(jmsSession.createQueue(replyToQueue));
    			myTextMsg.setJMSCorrelationID(mLogHistory.getMessageLogHistoryKey().toString());

    			// Send the message
                Destination destination = jmsSession.createQueue(currentDestination.getConsumerDestination());
                msgSender = jmsSession.createProducer(destination);
                msgSender.setDeliveryMode(DeliveryMode.PERSISTENT);
                msgSender.send(myTextMsg);
                msgSender.close();


    			// once the message is sent, add an entry in the message log history table for this specific send
                mLogBean.setSuccessFlag("Y");
                mLogBean.setNumberOfTries(1L);
                mLogBean.setLastUpdateOn(new Date());
                databaseSession.update(mLogBean);
                mLogHistory.setMessageId( myTextMsg.getJMSMessageID());
                databaseSession.update(mLogHistory);
    			// Log the successful message delivery to the log4j component.
    			logMessageDelivery(messageLogTable, "SUCCESS");
    		}

    	}
    	finally {
    		try {
    			if (msgSender != null) {
    				msgSender.close();
    			}
    		}
    		catch (JMSException e) {

    		}
    	}
    }
    /**
     * This routine is used to record the status of a message delivery.  It is passed in
     * the information about the message and a status as to whether the message was delivered
     * successfully or not.
     * @param messageLogTable contains the message log table information, which is the data about the message.
     * @param status contains the status of the delivery.
     */
    private void logMessageDelivery(MessageLogTable messageLogTable, String status) {

    	MessageLog bean = messageLogTable.getMessageLogBean();

    	if (LOG4J_LOGGER.isDebugEnabled()) {
    		StringBuilder sb = new StringBuilder(BUFFER_SIZE);
    		sb.append("Message Delivery ");
    		sb.append(status);
    		sb.append(": Web Service Key=");
    		sb.append(bean.getWebServiceKey());
    		sb.append(", MessageConsumerKey=");
    		sb.append(bean.getMessageConsumerKey());
    		sb.append(", JSON Message Text=");
    		sb.append(bean.getMessageSent());

    		LOG4J_LOGGER.debug(sb.toString());
    	}
    }

	/**
	 * @param batchMessageConsumerKey the batchMessageConsumerKey to set
	 */
	public void setBatchMessageConsumerKey(Long batchMessageConsumerKey) {
		this.batchMessageConsumerKey = batchMessageConsumerKey;
	}

	/**
	 * @return the batchMessageConsumerKey
	 */
	public Long getBatchMessageConsumerKey() {
		return batchMessageConsumerKey;
	}

}
