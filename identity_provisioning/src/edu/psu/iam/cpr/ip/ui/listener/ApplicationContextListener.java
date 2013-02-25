/* SVN FILE: $Id: ApplicationContextListener.java 5995 2013-01-10 05:39:37Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.dao.IdentityProvisioningDAO;
import edu.psu.iam.cpr.ip.ui.validation.FieldUtility;

/**
 * ApplicationContextListener allocates long-term resources; JMS connections, database connections <br/>
 * and stores a reference to them in the application context.<br/><br/>
 * It also deallocates those resources when the application is shutdown. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.listener 
 * @author $Author: jal55 $
 * @version $Rev: 5995 $
 * @lastrevision $Date: 2013-01-10 00:39:37 -0500 (Thu, 10 Jan 2013) $
 */

/**
 * Application Lifecycle Listener implementation class ApplicationContextListener
 *
 */
@WebListener("Allocate and deallocate, shared, long-term IdentityProvisioning application resources")
		
public class ApplicationContextListener implements ServletContextListener 
{
	/* JMS Connection */
	Connection connection = null;
	
	/* Milliseconds in a second */
	public static final int MILLISECONDS = 1000;
	
	/* Instance of logger */                                                     
	private Logger log = Logger.getLogger(ApplicationContextListener.class);
    /**
     * Default constructor. 
     */
    public ApplicationContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) 
    {
    	ServletContext ctx = sce.getServletContext();
    	
		// Set mode to 'test' when running locally on a developer workstation
		if(FieldUtility.fieldIsNotPresent(System.getProperty(UIConstants.CPR_MODE_PROPERTY)))
		{
			// Normally set by tomcat, except when doing local testing
			System.setProperty(UIConstants.CPR_MODE_PROPERTY, "TEST");     
		}
    	
		// Expose the mode to the front-end: TEST, ACCEPTANCE, PRODUCTION
		String mode = System.getProperty(UIConstants.CPR_MODE_PROPERTY);
		ctx.setAttribute(UIConstants.RUNNING_MODE, mode);
		log.info("running mode = " +mode);
    	
		// Setup numbering of transactions for easier review of system log
		String idSuffix = new SimpleDateFormat(" [DDD,HHmm]").format(new Date());
		
		// Add the last 3-digits of the server to the unique id
		InetAddress inetAddress = null; 
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) { }

		String ip = (inetAddress != null ? inetAddress.getHostAddress() : "");
		if(FieldUtility.fieldIsPresent(ip))
		{
			idSuffix = new StringBuffer(idSuffix).insert(idSuffix.length()-1, ip.substring(ip.lastIndexOf('.'))).toString();
		}

		// Park the new suffix for unique id(s) in application-scope memory
		ctx.setAttribute(UIConstants.SESSION_UNIQUE_ID_SUFFIX, idSuffix);
		
		// Report on simple id numbering options
		log.info(String.format("unique simple numbering id option setting [%s], suffixId[%s]", 
				                 ctx.getInitParameter(UIConstants.TRANSACTION_ID_NUMBERING),idSuffix));
		log.info("Unique id suffix --> " +idSuffix);
		
    	// Hibernate doesn't actually allocate the pool until the first (getSession) call, so let's make one now
		log.info("Initiating the hibernate pool ");
		IdentityProvisioningDAO.initializeHibernatePool();
		
		// Get a JMS Connection and save in context for sharing
		ctx.removeAttribute("jms.connection");
		try {
			getJMSConnection(ctx);
		} catch (IOException e) {
			log.info(String.format("IOException loadign JMS properties: %s", e.getMessage()));
		}
		ctx.setAttribute("jms.connection", connection);
	}
    
    
    /**
     * Get a JMS connection to be shared
     * @param ctx - The Servlet context so we can read initialization parameters from web.xml
     * @throws IOException 
     */
    public void getJMSConnection(ServletContext ctx) throws IOException
    {
    	String jmsUserid   = null;
		String jmsPassword = null;
		String jmsURL      = null;

		// Decide where jms properties should be read from
		String jmsPropertyLocation = ctx.getInitParameter("jms.properties.location");
		
    	if(jmsPropertyLocation!= null && jmsPropertyLocation.equalsIgnoreCase("web.xml"))
    	{
    		log.info("Reading JMS Connection properties from 'web.xml'");
    		jmsUserid   = ctx.getInitParameter("authentication.queue.userid");
    		jmsPassword = ctx.getInitParameter("authentication.queue.password");
    		jmsURL      = ctx.getInitParameter("jms.connect.url");
    	}
    	else
    	{
    		log.info("Reading JMS Connection properties from 'cpr.properties'");
    		Properties props = new Properties();
    		props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("cpr.properties"));
    		jmsUserid   = props.getProperty("cpr.jms.userid");
    		jmsPassword = props.getProperty("cpr.jms.password");
    		jmsURL      = props.getProperty("cpr.jms.broker");
    		props = null;
    	}
    	
		// Create a JMS connection which will be used by the Identity Provisioning UI
		ConnectionFactory connectionFactory =
            new ActiveMQConnectionFactory(jmsURL);
		
		log.info(String.format("initiating jms connection jmsURL[%s]  userid[%s]", jmsURL, jmsUserid));
		
		try 
		{
			connection = connectionFactory.createConnection(jmsUserid, jmsPassword);
			connection.start();
			log.info("JMS connection saved in application context");
		} 
		catch (JMSException jmse) 
		{
			log.error(String.format("..Could not get a connection to ActiveMQ [%s]", jmse.getMessage()));
		}
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce) 
    {
        // Return resources acquired on start-up
    	ServletContext ctx = sce.getServletContext();
    	
    	String strWaitMilliSeconds  = ctx.getInitParameter("jms.wait.for.disconnect.ms");
    	
    	// Disconnect from any resources; such as, databases, JMS Servers
    	try 
    	{
			connection.stop();
			connection.close();

			if(FieldUtility.fieldIsPresent(strWaitMilliSeconds))
			{
				int waitMilliseconds = Integer.parseInt(strWaitMilliSeconds);
				log.info(String.format("waiting %d seconds for jms disconnect", waitMilliseconds/MILLISECONDS));
				try 
				{
					Thread.sleep(waitMilliseconds);
				} 
				catch (InterruptedException e) {}  
			}
			
			ctx.removeAttribute("jms.connection");

			log.info("Java Messaging Service connection successfully shutdown"); 
		} 
    	catch (JMSException jmse)      
    	{
			log.warn(String.format("..Could not shutdown connection to ActiveMQ [%s]", jmse.getMessage()));
		}
    }
	
}

