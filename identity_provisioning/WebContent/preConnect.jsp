<%@page import="org.apache.struts2.RequestUtils"%>
<%@page import="org.apache.struts2.dispatcher.FilterDispatcher"%>
<%  /* SVN FILE: $Id: preConnect.jsp 6114 2013-02-01 09:02:06Z jal55 $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
/**
 * preConnect.jsp is a front-end [just for testing] that removes any userid/password
 * credentials from front-end, client, viewing.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @author $Author: jal55 $
 * @version $Rev: 6114 $
 * @lastrevision $Date: 2013-02-01 04:02:06 -0500 (Fri, 01 Feb 2013) $
 */
 
 String ra          = null;
 String sitename    = null;
 String requestedBy = null;
 String password    = null;
 String principalId = null;
 
 // I don't like using request -- too many characters
 HttpServletRequest req = request;
 
 // uao 
 if(request.getParameter("ra").equalsIgnoreCase("uao"))
 {
 	ra          = request.getParameter("ra");
 	sitename    = "Undergraduate Admissions";
 	requestedBy = "cpruser";
 	principalId = "cpruser";
 	password    = "abcd1234";
 }
 
  // gso 
 if(request.getParameter("ra").equalsIgnoreCase("gso"))
 {
 	ra          = request.getParameter("ra");
 	sitename    = "COMM-IT";
 	requestedBy = "cpruser";
 	principalId = "cpruser";
 	password    = "abcd1234";
 }
 
 request.setAttribute("ra", ra);
 request.setAttribute("sitename", sitename);
 request.setAttribute("requestedBy", requestedBy);
 request.setAttribute("principalId", principalId);
 request.setAttribute("password", password);
 
 // Redirect the next request at the server, shielding the "ra_connect" action from the end-user
 request.getRequestDispatcher("ra_connect").forward(request, response);
%>