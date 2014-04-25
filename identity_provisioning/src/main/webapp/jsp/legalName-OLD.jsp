<%  /* SVN FILE: $Id: legalName.jsp 5865 2012-12-10 18:20:17Z Charles $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/*
 * legalName.jsp processes the legal name information.  
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: Charles $
 * @version $Rev: 5865 $
 * @lastrevision $Date: 2012-12-10 13:20:17 -0500 (Mon, 10 Dec 2012) $
 */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">
<title><s:property value="#application['ui.sitename']"/>: Legal Name</title>
	
<!-- DOCUMENT HEAD -->
<jsp:include page="head.jsp" />

</head>
	  
<body>

<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"> &gt; <s:property value="#application['ui.sitename']"/> &gt; <b>Legal Name </b></div><div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>


<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- DATACARD -->
<jsp:include page="datacardLegalName.jsp" />

<!-- PROGRESS INDICATOR -->
<jsp:include page="progress.jsp" />

<!-- DEVELOPER TOOLS -->
<jsp:include page="devtools.jsp" />

<!-- NAVIGATION (Small Screen) -->
<jsp:include page="navigationSmall.jsp" />

<!-- FOOTER -->
<jsp:include page="footer.jsp" />

	<!-- JavaScript placed at the end of the document so the pages load faster! -->
    <script src="./assets/js/jquery.js"></script>
    <script src="./assets/js/bootstrap-transition.js"></script>
    <script src="./assets/js/bootstrap-alert.js"></script>
    <script src="./assets/js/bootstrap-modal.js"></script>
    <script src="./assets/js/bootstrap-dropdown.js"></script>
    <script src="./assets/js/bootstrap-scrollspy.js"></script>
    <script src="./assets/js/bootstrap-tab.js"></script>
    <script src="./assets/js/bootstrap-tooltip.js"></script>
    <script src="./assets/js/bootstrap-popover.js"></script>
    <script src="./assets/js/bootstrap-button.js"></script>
    <script src="./assets/js/bootstrap-collapse.js"></script>
    <script src="./assets/js/bootstrap-carousel.js"></script>
    <script src="./assets/js/bootstrap-typeahead.js"></script>
    <script src="./assets/js/bootstrap-iam.js"></script>

	<script type="text/javascript">
	var URL = getCookie('raReferrer');
	var RA = getCookie('ra');
	var TRAIL = document.getElementById('breadcrumbs').innerHTML;
	document.getElementById('breadcrumbs').innerHTML=RA + TRAIL;
	goVerify();
	focusOn('firstName');
	</script>

  </body>
</html>