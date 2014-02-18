/* SVN FILE: $Id: ErrorHandler.java 6124 2013-02-05 21:26:15Z jal55 $ */
package edu.psu.iam.cpr.ip.ui.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import edu.psu.iam.cpr.ip.ui.common.UIConstants;

/**
 * ErrorHandler is a catches all Exceptions for the Identity Provisioning UI
 * and sends them to ExceptionAction to handle. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.servlet 
 * @author $Author: jal55 $
 * @version $Rev: 6124 $
 * @lastrevision $Date: 2013-02-05 16:26:15 -0500 (Tue, 05 Feb 2013) $
 */
/**
 * Servlet implementation class ErrorHandler
 */
@WebServlet(description = "Generic", urlPatterns = { "/exception" })
public class ErrorHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/** Instance of logger */                                                     
	private static final Logger LOG = Logger.getLogger(ErrorHandler.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ErrorHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		try
		{
		// Get the session if it exists, but don't allocate a new one if it doesn't
		HttpSession session = request.getSession(false);
		String uniqueId = "";
		if(session != null)
		{
			uniqueId = (String)session.getAttribute(UIConstants.SESSION_NEW_UNIQUE_ID);
		}
		LOG.info(String.format("%s query string [%s] - request URI - requestURI[%s]original uri[%s]",
				           uniqueId,
				           request.getQueryString(),
				           request.getRequestURI(),
				           request.getAttribute("javax.servlet.forward.request_uri")
					));
		
		LOG.info(String.format("%s The exception is [%s]",
				                uniqueId,
								request.getAttribute("javax.servlet.error.exception")));
		
		LOG.info(String.format("%s error status code [%s]"   , uniqueId, request.getAttribute("javax.servlet.error.status_code")));
		LOG.info(String.format("%s error message [%s]"       , uniqueId, request.getAttribute("javax.servlet.error.message")));
		LOG.info(String.format("%s error exception type [%s]", uniqueId, request.getAttribute("javax.servlet.error.exception_type")));
		LOG.info(String.format("%s error request uri [%s]"   , uniqueId, request.getAttribute("javax.servlet.error.request_uri")));
	    Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
		
	    if(session != null)
	    {
	    	session.setAttribute("eh.exception.object"       , request.getAttribute("javax.servlet.error.exception"));     
			session.setAttribute("eh.exception.original.uri" , request.getAttribute("javax.servlet.forward.request_uri")); 
			session.setAttribute("eh.exception.stack.trace"  , throwable.getStackTrace() );            
			session.setAttribute("eh.exception.message"      , request.getAttribute("javax.servlet.error.message"));
			session.setAttribute("eh.status.code"            , request.getAttribute("javax.servlet.error.status_code"));
			session.setAttribute("eh.message.type"           , request.getAttribute("javax.servlet.error.exception_type"));
	    }
		
		response.sendRedirect("./error_report");
		}
		catch(Exception e){}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
