/* SVN FILE: $Id: ProxyJMS.java 5888 2012-12-12 02:14:04Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.proxy;
/**
 * ProxyJMS submit JMS messages to Queues
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.proxy 
 * @author $Author: jal55 $
 * @version $Rev: 5888 $
 * @lastrevision $Date: 2012-12-11 21:14:04 -0500 (Tue, 11 Dec 2012) $
 */

import java.util.Map;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;
import edu.psu.iam.cpr.ip.ui.valueobject.JMSRequestObject;

public final class ProxyJMS 
{
	public static final boolean PASSWORD_MESSAGE_SENT       = true;
	public static final boolean PASSWORD_MESSAGE_NOT_SENT   = false;
	
	/** Instance of logger */                                                     
	public static final Logger LOG = Logger.getLogger(ProxyJMS.class);
	
	/**
	 * Prevent or make it difficult for someone to instantiate this utility class
	 */
	private ProxyJMS()  { }
	
	/**
	 * Send a JMS message to the Queue 
	 * @param application - Application session contains the JMS connection object
	 * @param jmsObj - The JMS Request object
	 * @return
	 */
	public static boolean sendMessage(Map application, JMSRequestObject jmsObj) 
	{
		boolean result = PASSWORD_MESSAGE_NOT_SENT;
		try
		{
			// JMS messages are sent and received using a Session. We will
			// create here a non-transactional session object. If you want
			// to use transactions you should set the first parameter to 'true'
			Session session = ((Connection)application.get("jms.connection")).createSession(false, Session.AUTO_ACKNOWLEDGE);
		
			// Destination represents here our queue on the
			// JMS server. You don't have to do anything special on the
			// server to create it, it will be created automatically if defined as such.
			Destination destination  = session.createQueue(jmsObj.getQueueName());

        
			// MessageProducer is used for sending messages 
			MessageProducer producer  = session.createProducer(destination);
        
			// Create the [text] message to send 
			TextMessage message    = session.createTextMessage(jmsObj.getMessage());

			// Add a replyQueue if one specified
        	String replyQueueName = jmsObj.getReplyQueueName();
        	if(FieldUtility.fieldIsPresent(replyQueueName))
        	{
        		message.setJMSReplyTo(session.createQueue(replyQueueName));
        	}

			// Send the message and close the session
			producer.send(message);
			result = PASSWORD_MESSAGE_SENT;
			session.close();
		}
		catch(JMSException jmse)
		{
			LOG.info(String.format("Error writing request to Queue [%s] \n ..jsonMessage[%s]", jmse.getMessage(), jmsObj.toString()));
		}
		return result;
	}
}
