<%  /* SVN FILE: $Id: welcome.jsp 5982 2013-01-07 18:47:53Z Charles $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/*
 * welcome.jsp is the welcome, and first screren encountered, by the end-user, after  
 * being transferred to IAM via a registration authority.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @package edu.psu.iam.cpr.ip.ui.action 
 * @author $Author: Charles $
 * @version $Rev: 5982 $
 * @lastrevision $Date: 2013-01-07 13:47:53 -0500 (Mon, 07 Jan 2013) $
 */
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">
<title><s:property value="#application['ui.sitename']"/>: <s:property value="#application['ui.welcome.header']"/></title>

<!-- DOCUMENT HEAD -->
<jsp:include page="head.jsp" />

</head>

<body>
<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"> <s:property value="#application['ui.sitename']"/> &gt; <b><s:property value="#application['ui.welcome.header']"/> </b></div>
<div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>

<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- DATACARD -->
<jsp:include page="datacardWelcome.jsp" />

<!-- PROGRESS INDICATOR -->
<jsp:include page="progress.jsp" />

<!-- DEVELOPER TOOLS -->
<jsp:include page="devtools.jsp" />

<!-- NAVIGATION (Small Screen) -->    
<jsp:include page="navigationSmall.jsp" />

<script type="text/javascript">
document.getElementById('backSmall').style.visibility="hidden";
document.getElementById('continuetextSmall').innerHTML="<b>Begin</b>";
</script>

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
        setCookie('ra', "<s:property value='#session["rac.ra"]' />");
		//deleteCookie('ra');
		setCookie('raHome', "<s:property value='#session["rac.homeURL"]' />");
        setCookie('raReferrer', "<s:property value='#session["rac.raReferrer"]' />");
        var URL = getCookie('raReferrer');
        var RA = getCookie('ra');
		var TRAIL = document.getElementById('breadcrumbs').innerHTML;
		if ((RA !='') && (RA != null)) {
		document.getElementById('breadcrumbs').innerHTML="<a tabIndex='5' href='" + URL + "'>" + RA + "</a> &gt;" + TRAIL; document.getElementById("datacardcontent").className=document.getElementById("datacardcontent").className.replace( /(?:^|\s)invisible(?!\S)/g , '' );
		document.getElementById("ncjs").className="hidden";
		}
		else {
		document.getElementById('titletext').innerHTML="Cookies and JavaScript required";
		document.getElementById('ncjs').innerHTML="JavaScript is enabled but cookies are not. Please enable cookies to proceed.";
		document.getElementById("helplink").className="hidden";
		document.getElementById("navigationLarge").className="hidden";
		document.getElementById("navigationSmall").className="hidden";
		}
    </script>

  </body>
</html>