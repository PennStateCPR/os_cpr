package edu.psu.iam.cpr.provisioner.directory;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import edu.psu.iam.cpr.core.database.types.CprPropertyName;
import edu.psu.iam.cpr.core.database.types.CprServiceName;
import edu.psu.iam.cpr.core.util.CprProperties;

/**
 * Directory Message Processor.
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
 * @package edu.psu.iam.cpr.provisioner.directory
 * @author $Author: jvuccolo $
 * @version $Rev: 5340 $
 * @lastrevision $Date: 2012-09-27 10:48:52 -0400 (Thu, 27 Sep 2012) $
 */
public class DirectoryMessageProcessor {

	private static final String USER_PASSWORD = "userPassword";

	/** Directory queue name property */
	private static final String CPR_JMS_DIR_QUEUE = "cpr.jms.dir.queue";
	
	/** LDAP manager DN property */
	private static final String CPR_LDAP_MANAGER_DN = "cpr.ldap.manager.dn";
	
	/** LDAP manager password property */
	private static final String CPR_LDAP_MANAGER_PASSWORD = "cpr.ldap.manager.password";
	
	private static final String COMMA = ",";

	private static final String EQUALS = "=";
	
	/** Connection factory */
	private ConnectionFactory jmsConnectionFactory = null;
	
	/** Connection */
	private Connection jmsConnection = null;
	
	/** JMS Session */
	private Session jmsSession = null;
	
	/** Directory context */
	private DirContext directoryContext = null;

	/** JMS message consumer. */
	private MessageConsumer messageConsumer = null;
	
	/** JMS Message Map */
	private Map<String, String> msgMap = null;

	private static String logClassPath = DirectoryMessageProcessor.class.getName();

	/** Log properties file name */
	private static final String LOG_PROPERTIES_FILE = "log4j/log4j.properties";
	
	/** String Builder buffer size */
	private static final int BUFFER_SIZE = 500;

	private static Logger log = null;
	
	/** 
	 * Constructor.
	 * @throws JMSException 
	 * @throws NamingException 
	 */
	public DirectoryMessageProcessor() throws JMSException, NamingException {
		super();
		initializeMessaging();
		initializeDirectoryContext();
		
		PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
		log = Logger.getLogger(logClassPath);
	}
	
	/**
	 * This routine is used to initialize messaging.
	 * @throws JMSException will be thrown if there are JMS initialization problems.
	 */
	private void initializeMessaging() throws JMSException {
		
		// Get a connection and start a session.
		Properties props = CprProperties.INSTANCE.getProperties();
		jmsConnectionFactory = new ActiveMQConnectionFactory(props.getProperty(CprPropertyName.CPR_JMS_BROKER.toString()));
		jmsConnection = jmsConnectionFactory.createConnection(props.getProperty(CprPropertyName.CPR_JMS_USERID.toString()), 
													props.getProperty(CprPropertyName.CPR_JMS_PASSWORD.toString()));
		jmsConnection.start();
		jmsSession = jmsConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		messageConsumer = jmsSession.createConsumer(jmsSession.createQueue(props.getProperty(CPR_JMS_DIR_QUEUE)));
	}
	
	/**
	 * Close Messaging...
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
	 * Close Directory...
	 */
	public void closeDirectoryContext() {
		try {
			directoryContext.close();
		}
		catch (Exception e) {
		}
	}
	
	/**
	 * This routine is used to initialize the directory context.
	 * @throws NamingException will be thrown if there are any directory problems.
	 */
	private void initializeDirectoryContext() throws NamingException {
		
		Properties props = CprProperties.INSTANCE.getProperties();
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, props.getProperty(CprPropertyName.CPR_LDAP_URL.toString()));
		env.put(Context.SECURITY_AUTHENTICATION, props.getProperty(CprPropertyName.CPR_LDAP_SECURITY_AUTHENTICATION.toString()));
		env.put(Context.SECURITY_PRINCIPAL, props.getProperty(CPR_LDAP_MANAGER_DN));
		env.put(Context.SECURITY_CREDENTIALS, props.getProperty(CPR_LDAP_MANAGER_PASSWORD));

		// Establish a new directory context 
		directoryContext = new InitialDirContext(env);
	}
	
	/**
	 * This routine is used to process messages.  It stays in a infinite loop waiting for messages.
	 */
	@SuppressWarnings("unchecked")
	public void processMessages() {
		
		JSONParser jsonParser = new JSONParser();
		
		ContainerFactory containerFactory = new ContainerFactory(){
			public List<String> creatArrayContainer() {
				return new LinkedList<String>();
			}

			public Map<String, String> createObjectContainer() {
				return new LinkedHashMap<String, String>();
			}
		};
	
		while (true) {
			
			@SuppressWarnings("unused")
			String correlationId = null;
			
			try {
				Message message = messageConsumer.receive();
				
				// Only process text messages.
				if (message instanceof TextMessage) {
					
					TextMessage textMessage = (TextMessage) message;
					correlationId = textMessage.getJMSMessageID();
					String msgString = textMessage.getText();

					if (msgString != null && msgString.length() > 0) {
						
						// Parse the message for the JSON.
						Object parsedMessage = jsonParser.parse(msgString, containerFactory);
						
						if (parsedMessage instanceof Map) {
							msgMap = (Map<String, String>) parsedMessage;
							String serviceName = msgMap.get("SERVICE_NAME");
							String requestedBy = msgMap.get("REQUESTED_BY");
							String userId = msgMap.get("USERID");
							
							if (serviceName != null) {
								findMessageHandler(serviceName);
							}
							else {
								log.info("Skipping message... serviceName = " + serviceName + " requested by = " + requestedBy + " userid= " + userId);
							}
						}
					}
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
				log.info("JSON Exception: " + e.getMessage());
			}
			catch (JMSException e) {
				e.printStackTrace();
				log.info("JMS Exception: " + e.getMessage());
			} 
			catch (ParseException e) {
				e.printStackTrace();
				log.info("Parse Exception: " + e.getMessage());
			} 
			catch (NamingException e) {
				e.printStackTrace();
				log.info("Naming Exception: " + e.getMessage());
			}
		}
	}

	/**
	 * This routine is used to find a message handler for the appropriate message.
	 * @param serviceName contains the service name from the message.
	 * @throws JSONException will be thrown if there are any JSON problems.
	 * @throws NamingException will be thrown if there are any JNDI problems.
	 */
	private void findMessageHandler(String serviceName) throws JSONException, NamingException {
		
		if (serviceName.equals(CprServiceName.AddPerson.toString())) {
			processAddPersonMessage();
		}
		else if (serviceName.equals(CprServiceName.SetPassword.toString())) {
			processSetPasswordMessage();
		}
	}
	
	/**
	 * This function adds the given attribute/value pair to the directory.
	 * 
	 * @param dn contains the DN whose entry is to be updated.
	 * @param attribute to be stored in the directory
	 * @param value to be stored for this attribute
	 * @throws JSONException will be thrown if there are any problems.
	 * @throws NamingException  will be thrown if there are any JDNI problems.
	 */
	private void addAttributeToDirectory(String dn, String attribute, String value) throws JSONException, NamingException {

		// add attribute to directory
		ModificationItem mod = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute(attribute, value));
		ModificationItem mods[] = new ModificationItem[1];
		mods[0] = mod;
		log.info("Replacing " + attribute + " for dn " + dn + " with " + value);
		directoryContext.modifyAttributes(dn, mods);
	}

	/**
	 * This function finds a directory entry given the directory id or user id
	 * @param userId for the entry to find
	 * @throws JSONException will be thrown if there are JSON problems.
	 * @throws NamingException will be thrown if there are any JNDI problems.
	 * @return will return if found, otherwise it will return null.
	 */
	private String findDirectoryEntry(String userId ) throws JSONException, NamingException {

		String filter = null;
		SearchControls c = new SearchControls();
		c.setSearchScope(SearchControls.SUBTREE_SCOPE);

		Properties props = CprProperties.INSTANCE.getProperties();
		StringBuilder sb = new StringBuilder(BUFFER_SIZE);
		
		sb.append("(");
		sb.append(props.getProperty(CprPropertyName.CPR_LDAP_PEOPLE_DN_PREFIX.toString()));
		sb.append(EQUALS);
		sb.append(userId);
		sb.append(")");
		
		filter = sb.toString();
		log.info("filter = " + filter);

		NamingEnumeration<SearchResult> n = directoryContext.search(
				props.getProperty(CprPropertyName.CPR_LDAP_PEOPLE_BASE_DN.toString()), filter, c);

		// Loop while we have entries that match.
		String dn = null;
		while (n.hasMore()) {
			SearchResult sr = n.next();
			dn = sr.getNameInNamespace();
			log.info("DN found = " + dn);
		}
		if (dn == null) {
			log.info("No entry found in directory for filter " + filter);
		}
		return dn;

	}

	/**
	 * This routine is used to process a set password message.
	 * @throws JSONException will be thrown if there is a JSON problem.
	 * @throws NamingException will be thrown if there is a JNDI problem.
	 */
	private void processSetPasswordMessage() throws JSONException, NamingException {
		
		log.info("Processing Set Password Messsage");

		String userId = getMapValue("USERID");
		String password = getMapValue("PASSWORD");
		if (userId.length() != 0 && password.length() != 0) {
			String dn = findDirectoryEntry(userId);
			if (dn != null) {
				addAttributeToDirectory(dn, USER_PASSWORD, password);
			}
		}
		else {
			log.info("Skipping password change because userid and/or password was empty");
		}
	}
	
	/**
	 * This function adds the directory information for an add person, the dn entry along with all attributes specified
	 * @throws JSONException
	 * @throws NamingException 
	 */
	private void processAddPersonMessage() throws JSONException, NamingException {
		
		log.info("Processing Add Person Messsage");

		String userId = getMapValue("USERID");

		Attributes attrs = new BasicAttributes();
		Attribute objClass = new BasicAttribute("objectclass");
		objClass.add("top");
		objClass.add("inetOrgPerson");
		objClass.add("eduPerson");
		attrs.put(objClass);

		String lastName = getMapValue("LAST_NAME");
		String nameType = getMapValue("NAME_TYPE");

		if (lastName.length() != 0 && nameType.length() != 0) {
			String firstName = getMapValue("FIRST_NAME");
			String middleNames = getMapValue("MIDDLE_NAMES");
			String suffix = getMapValue("SUFFIX");

			attrs.put(new BasicAttribute("sn", lastName));

			String displayName = firstName + " " + lastName;
			attrs.put(new BasicAttribute("displayName", displayName));

			String cn = firstName + " " + middleNames + " " + lastName + " " + suffix;
			attrs.put(new BasicAttribute("cn", cn));

			String givenName = firstName + " " + middleNames;
			attrs.put(new BasicAttribute("givenName", givenName));
		}

		String address1 = getMapValue("ADDRESS1");
		String addressType = getMapValue("ADDRESS_TYPE");
		if (address1.length() != 0 && addressType.length() != 0) {

			String postalCode = getMapValue("POSTAL_CODE");
			String city = getMapValue("CITY");
			String address2 = getMapValue("ADDRESS2");
			String address3 = getMapValue("ADDRESS3");
			String state = getMapValue("STATE");
			String country = getMapValue("COUNTRY_NAME");

			// build the address string to insert into the directory (address1$address2$address3$city, state postal code$country)
			StringBuffer directoryAddress = new StringBuffer(address1 + "$");
			if (address2.length() != 0) {
				directoryAddress.append(address2 + "$");
			}
			if (address3.length() != 0) {
				directoryAddress.append(address3 + "$");
			}
			if (city.length() != 0) {
				directoryAddress.append(city + ",");
			}
			if (state.length() != 0) {
				directoryAddress.append(" " + state);
			}
			if (postalCode.length() != 0) {
				directoryAddress.append(" " + postalCode);
			}
			if (country.length() != 0) {
				directoryAddress.append("$" + country);
			}

			attrs.put(new BasicAttribute("postalAddress", directoryAddress.toString()));
		}

		attrs.put(new BasicAttribute("eduPersonAffiliation", "student"));
		attrs.put(new BasicAttribute("eduPersonPrimaryAffiliation", "student"));

		String phoneNumber = getMapValue("PHONE_NUMBER");
		String phoneType = getMapValue("PHONE_TYPE");
		if (phoneNumber.length() != 0 && phoneType.length() != 0) {

			String extension = getMapValue("EXTENSION");
			String directoryPhone = null;
			if (extension.length() != 0) {
				directoryPhone = phoneNumber;
			}
			else {
				directoryPhone = phoneNumber + "  " + extension;
			}

			attrs.put(new BasicAttribute("telephoneNumber", directoryPhone.toString()));
		}

		String emailAddressType = getMapValue("EMAIL_ADDRESS_TYPE");
		String emailAddress = getMapValue("EMAIL_ADDRESS");
		if (emailAddressType.length() != 0 && emailAddress.length() != 0) {

			attrs.put(new BasicAttribute("mail", emailAddress));
			attrs.put(new BasicAttribute("eduPersonPrincipalName", emailAddress));
		}

		log.info("Adding entry for user " + userId);
		directoryContext.createSubcontext(buildDN(userId), attrs);

	}
	
	/**
	 * This routine is used to obtain a value from the JSON map, it will return either the value or an empty string.
	 * @param keyValue contains the key value to be searched for.
	 * @return will return either the value or a empty string if not found.
	 */
    private String getMapValue(String keyValue) {
    	String value = msgMap.get(keyValue);
    	if (value == null) {
    		return "";
    	}
    	else {
    		return value;
    	}
    }
    
    /**
     * This routine is used to construct a DN from a userid.
     * @param userId contains the userid to be used to construct the DN.
     * @return will return the fully qualified DN.
     */
    private String buildDN(String userId) {
    	
    	Properties props = CprProperties.INSTANCE.getProperties();
    	
    	StringBuilder sb = new StringBuilder(BUFFER_SIZE);
    	
    	sb.append(props.getProperty(CprPropertyName.CPR_LDAP_PEOPLE_DN_PREFIX.toString()));
    	sb.append(EQUALS);
    	sb.append(userId);
    	sb.append(COMMA);
    	sb.append(props.getProperty(CprPropertyName.CPR_LDAP_PEOPLE_BASE_DN.toString()));
    	
    	return sb.toString();
    }
}
