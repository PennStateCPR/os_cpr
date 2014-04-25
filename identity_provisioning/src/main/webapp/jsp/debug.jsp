<%  /* SVN FILE: $Id: debug.jsp 5481 2012-10-16 21:34:36Z jal55 $ */  %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%      
/**
 *debug.jsp allows a developer to enable the 'debug' tag  
 * on struts 2 pages -- this option is off by default, and this jsp does a 
 * flip-flop upon every invocation within the same session.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: jal55 $
 * @version $Rev: 5481 $
 * @lastrevision $Date: 2012-10-16 17:34:36 -0400 (Tue, 16 Oct 2012) $
 */
%>

The debug option is now <b><s:property value="%{#session.debug}"/></b>
</br>
The server-side debug ''skip validation' option is now <b> <s:property value="#session['debug.skip.validation']"/> </b>
