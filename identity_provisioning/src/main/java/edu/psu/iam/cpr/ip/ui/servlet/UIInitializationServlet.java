/* SVN FILE: $Id: UIInitializationServlet.java 5888 2012-12-12 02:14:04Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.core.ui.EmailMsgUI;
import edu.psu.iam.cpr.ip.ui.common.UIConstants;
import edu.psu.iam.cpr.ip.ui.dao.IdentityProvisioningDAO;
import edu.psu.iam.cpr.ip.ui.helper.StringHelper;
import edu.psu.iam.cpr.ip.util.LinkedProperties;

/**
 * UIInitializationServlet performs application-scope initialization. 
 * Reading and initialization from property and/or database files.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5888 $
 * @lastrevision $Date: 2012-12-11 21:14:04 -0500 (Tue, 11 Dec 2012) $
 */
@WebServlet( description="IP Initialization Servlet"
	       , loadOnStartup=1
		   , asyncSupported=false
		   , name="UIInitializationServlet"
		   , displayName="/UIInitializationServlet"
		   , urlPatterns="/UIInitializationServlet")
//@WebServlet("/UIInitializationServlet")
public class UIInitializationServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(UIInitializationServlet.class);
	
	// Separate map, solely of globals only used during initialization
	Map<String, Object> globals = new HashMap<String, Object>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UIInitializationServlet() 
    {
        super();
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException
	{
		LOG.info("UIInitializationServlet setting up appliation scope items");
		// Read Application properties and store in application scope memory 
		ServletContext ctx  = config.getServletContext();
		
		// Default to application deployed, affects display of developer tools
		// Front-end will not display developer tools if deployed
		ctx.setAttribute("deployed", "yes");
		
		// Store application Properties in Memory -- or burp!
		try 
		{
			storeApplicationProperties(ctx);
		} catch (IOException e) 
		{
			throw new ServletException(e);
		}
		
		// Start the Hibernate pool by making one call
		LOG.info("Initiating the hibernate pool ");
		IdentityProvisioningDAO.initializeHibernatePool();
		
		// Store in application memory some common [high-utilization] database objects 
		LOG.info("Preloading   IN-CORE Maps: Country, State, and Gender into memory");
		ctx.setAttribute(UIConstants.IN_CORE_COUNTRY_MAP_KEY, IdentityProvisioningDAO.loadCountryListFromDb());
		ctx.setAttribute(UIConstants.IN_CORE_STATE_MAP_KEY  , IdentityProvisioningDAO.loadStateListFromDb());
		ctx.setAttribute(UIConstants.IN_CORE_GENDER_MAP_KEY , IdentityProvisioningDAO.loadGenderListFromDb());
		LOG.info("done loading IN-CORE Maps: Country, State, and Gender into memory");
		
		// Note: some properties can be overiden by database entries; such as, set and reset email information
		Map<String, EmailMsgUI> map = null;
		LOG.info("calling IdentityProvisioningDAO.getEmailobjectsFromDbByKey()");
		map = IdentityProvisioningDAO.getEmailObjectsFromDbByKey();
		LOG.info("done calling IdentityProvisioningDAO.getEmailobjectsFromDbByKey()");
		 
		if(map != null && !map.isEmpty())
		{
			Set<Map.Entry<String, EmailMsgUI>> mapSet = map.entrySet();
			for(Map.Entry<String, EmailMsgUI> emailEntry : mapSet)
			{
				String key = emailEntry.getKey();
				EmailMsgUI emailUI = emailEntry.getValue();
				
				String appKeyPrefix = (String)ctx.getAttribute(key);
				String ctxKeySubject = String.format("%s.%s", appKeyPrefix, "subject");
				String ctxKeyText    = String.format("%s.%s", appKeyPrefix, "text"   );
				String ctxKeyHtml    = String.format("%s.%s", appKeyPrefix, "html"   );
				
				ctx.setAttribute(ctxKeySubject, StringHelper.replaceGlobalTokensFromMap(globals, emailUI.getEmailSubject()));
				ctx.setAttribute(ctxKeyText   , StringHelper.replaceGlobalTokensFromMap(globals, emailUI.getEmailText()   ));
				ctx.setAttribute(ctxKeyHtml   , StringHelper.replaceGlobalTokensFromMap(globals, emailUI.getEmailHtml()   ));
				
				LOG.debug(String.format("email init.. ctx.key[%s.%s], data[%s]", appKeyPrefix, "subject", ctx.getAttribute(ctxKeySubject)));
				LOG.debug(String.format("email init.. ctx.key[%s.%s], data[%s]", appKeyPrefix, "text"   , ctx.getAttribute(ctxKeyText   )));
				LOG.debug(String.format("email init.. ctx.key[%s.%s], data[%s]", appKeyPrefix, "html"   , ctx.getAttribute(ctxKeyHtml   )));
			}
		}
		else
		{
			LOG.warn("the EmailMsgUI from the database returned an empty map");
		}
		
		
		// check-out the getAllRAPropertiesFromDb from the DAO
		Map<String, Map<String, String>> maps = IdentityProvisioningDAO.getAllRAPropertiesFromDb((String)ctx.getAttribute("ui.application.name"));
		LOG.info(String.format("returned from allprops with [%s]", maps));
		
		Set<String> keySet = maps.keySet();
			
		for(String key: keySet)
		{
			LOG.info(String.format("key[%s] : data[%s] ", key, maps.get(key)));
		}
			
		// Load Security Questions from Database into memory: ArrayList<Map<String, String>>
		LOG.info("Preloading Security Questions from database");
		ctx.setAttribute(UIConstants.IN_CORE_SECURITY_QUESTIONS, IdentityProvisioningDAO.loadSecurityQuestionsFromDb());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}
	
	public boolean isValid(Object obj)
	{
		return (obj == null) ? false: true;
	}
	
	/**
	 * This routine stores any application-scope properties into application memory.
	 * 
	 * @param ctx - the servlet context
	 * @throws IOException 
	 */
	protected void storeApplicationProperties(ServletContext ctx) throws IOException
	{
		//Properties props= new Properties();
		LinkedProperties props = new LinkedProperties();

		LOG.info(String.format("Loading application properties from %s", UIConstants.UI_PROPERTY_FILE));
		props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(UIConstants.UI_PROPERTY_FILE));
		LOG.info("Done loading application properties");

		LOG.debug(".....properties -> " +props);
		
		Set<Map.Entry<Object, Object>> map = props.entrySet();
		
		LOG.debug("Application Properties follow:");
		for(Map.Entry<Object, Object> mapEntry: map)
		{
			LOG.debug(String.format("[%s=%s]",mapEntry.getKey().toString(), mapEntry.getValue().toString()));
		}
		
		for(Map.Entry<Object, Object> mapEntry: map)
		{
			// Save the global variables for later replacement activities
			if(((String)mapEntry.getKey()).startsWith("gbl."))
			{
				globals.put((String)mapEntry.getKey(), mapEntry.getValue());
				ctx.setAttribute((String)mapEntry.getKey(), mapEntry.getValue());
				continue;
			}
			
			if(((String)mapEntry.getValue()).indexOf("%gbl.") == -1)
			{
				ctx.setAttribute((String)mapEntry.getKey(), mapEntry.getValue());
			}
			else
			{
				LOG.debug(String.format("substitution old string [%s]", (String)mapEntry.getValue()));
				ctx.setAttribute((String)mapEntry.getKey(),StringHelper.replaceGlobalTokensFromMap(globals, (String)mapEntry.getValue()));
				LOG.debug(String.format("substitution new string [%s]", ctx.getAttribute((String)mapEntry.getKey())));
			}
		}

		props = null;

		LOG.info(String.format("Servlet Initialization: %s entries loaded to application context", map.size()));
		LOG.debug(String.format("[%s] \n", map.toString()));
		

	}
}
