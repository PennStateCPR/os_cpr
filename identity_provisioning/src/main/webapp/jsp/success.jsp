<%  /* SVN FILE: $Id: success.jsp 5988 2013-01-09 13:21:00Z jal55 $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/*
 * The success page is the final page within the Identity Provisioning
 * process. It confirms to the user that their ID has been
 * successfully created.
 *
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action
 * @author $Author: jal55 $
 * @version $Rev: 5988 $
 * @lastrevision $Date: 2013-01-09 08:21:00 -0500 (Wed, 09 Jan 2013) $
 */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">
<title><s:property value="#application['ui.sitename']"/>: Success!</title>

<!-- DOCUMENT HEAD -->
<jsp:include page="head.jsp" />

</head>

<body>

<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.sitename']"/> &gt; <b>Success! </b></div><div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>

<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- DATACARD -->
<jsp:include page="datacardSuccess.jsp" />

<!-- PROGRESS INDICATOR -->
<jsp:include page="progress.jsp" />

<!-- DEVELOPER TOOLS -->
<jsp:include page="devtools.jsp" />

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
	<script type="text/javascript">navunhook();</script>
    
    <script type="text/javascript">
        var URL = getCookie('raReferrer');
        var RA = getCookie('ra');
		var TRAIL = document.getElementById('breadcrumbs').innerHTML;
        document.getElementById('breadcrumbs').innerHTML="<a tabIndex='5' href='" + URL + "'>" + RA + "</a> &gt; " + TRAIL;
		document.getElementById('returntext').innerHTML="<b>Return to "+ RA +"</b>";
        document.getElementById('returnlink').setAttribute('href', URL);
		document.getElementById('returnlink').setAttribute('tabIndex', '6');
		focusOn('returnlink');
    </script> 
  </body>
</html>
