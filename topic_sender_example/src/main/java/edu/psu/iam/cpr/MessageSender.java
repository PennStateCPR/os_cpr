package edu.psu.iam.cpr;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Topic sender using JMS.
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
 * @package edu.psu.iam.cpr
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class MessageSender {

	/** Contains the password on the topic */
	private static final String TOPIC_PASSWORD = "<PUT THE TOPIC PASSWORD HERE>";

	/** Contains the userid associated with the topic */
	private static final String TOPIC_USER = "<PUT THE TOPIC USERID HERE>";

	/** Contains the jms broker URL. */
	private static final String JMS_BROKER = "<PUT THE JMS BROKER URL HERE>";

	/** Contains the name of the topic. */
	private static final String TOPIC = "<PUT THE TOPIC NAME HERE>";

	/** Connection factory */
	private static ConnectionFactory jmsConnectionFactory = null;

    /** Connection */
    private static Connection jmsConnection = null;

	/** JMS Session */
	private static Session jmsSession = null;
	
	/**
	 * Main method.
	 *
	 * @param args  not used
	 *
	 */
	public static void main(String[] args) throws JMSException {

		try {

		    // Get a connection and start a session.
	        jmsConnectionFactory = new ActiveMQConnectionFactory(JMS_BROKER);
	        jmsConnection = jmsConnectionFactory.createConnection(TOPIC_USER,TOPIC_PASSWORD);
	        jmsConnection.start();
	        jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = jmsSession.createTopic(TOPIC);
			
			MessageProducer producer = jmsSession.createProducer(destination);
			
			// Create some random JSON 
			String message = "{\"SUFFIX\":null,\"LAST_NAME\":\"USERLNAME\",\"PERSON_ID\":\"100000\",\"SERVICE_NAME\":\"UpdateName\",\"USERID\":\"jzv1\",\"REQUESTED_BY\":\"slk24\",\"FIRST_NAME\":\"USER_FIRSTNAME\",\"DIRECTORY_ID\":\"100001\",\"MIDDLE_NAMES\":\"Z\",\"NAME_TYPE\":\"LEGAL_NAME\"}";

			TextMessage textMessage = jmsSession.createTextMessage(message);
			
			producer.send(textMessage);
			
		} 
		catch (Exception e) {
			System.out.println("Exception occurred : " + e.toString());
			e.printStackTrace();
		}
		finally {
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
		}
	}
}
