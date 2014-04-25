<%  /* SVN FILE: $Id: head.jsp 5676 2012-11-20 21:08:33Z Charles $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%
/**
 * head.jsp contains meta tags and links to external css and javascript files as per the HTML spec.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @author $Author: Charles $
 * @version $Rev: 5676 $
 * @lastrevision $Date: 2012-11-20 16:08:33 -0500 (Tue, 20 Nov 2012) $
 */
%>

<meta name="language" content="English">

<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Expires" CONTENT="-1">

<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />

<meta name="description" content="Identity Provisioning">
<meta name="keywords" content="Identity Provisioning">
<meta name="author" content="Charles Crust, Identity and Access Management, The Pennsylvania State University">

<!-- CSS DEPENDENCIES -->
<link href="./assets/css/bootstrap.css" rel="stylesheet">
<link href="./assets/css/bootstrap-responsive.css" rel="stylesheet">

<!-- INSTITUTION SPECIFIC CSS -->
<s:if test="%{#application['gbl.name.familiar'] == 'Penn State'}">
	<link href="./assets/css/psu-iam.css" rel="stylesheet">
</s:if>

<!-- HTML5 SHIM (for IE6-8 support of HTML5 elements) -->
<!--[if lt IE 9]>
<script src="./assets/js/html5shiv.js"></script>
<![endif]-->

<!-- FAV & TOUCH ICONS -->
<link rel="shortcut icon" href="<s:property value='#application["ui.shortcut.icon"]'/>">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="<s:property value='#application["ui.apple.touch.icon.144"]'/>">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="<s:property value='#application["ui.apple.touch.icon.114"]'/>">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="<s:property value='#application["ui.apple.touch.icon.72"]'/>">
<link rel="apple-touch-icon-precomposed" sizes="57x57" href="<s:property value='#application["ui.apple.touch.icon.57"]'/>">