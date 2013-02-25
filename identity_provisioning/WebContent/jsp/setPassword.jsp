<%  /* SVN FILE: $Id: setPassword.jsp 5865 2012-12-10 18:20:17Z Charles $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/**
 * setPassword.jsp - Allow user to enter password, confirm password
 * and validate to ensure it meets university password rules. 
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 *
 * @author $Author: Charles $
 * @version $Rev: 5865 $
 * @lastrevision $Date: 2012-12-10 13:20:17 -0500 (Mon, 10 Dec 2012) $
 */
%> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">

<s:if test='%{#session["sec.password.setting"] != "initial"}'>
<title><s:property value="#application['ui.sitename']"/>: Reset Password</title>
</s:if>
<s:else>
<title><s:property value="#application['ui.sitename']"/>: Set Password</title>
</s:else>

<!-- DOCUMENT HEAD -->
<jsp:include page="head.jsp" />

</head>

<body>

<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"> &gt; <s:property value="#application['ui.sitename']"/> &gt;

<s:if test='%{#session["sec.password.setting"] != "initial"}'>
<b>Reset Password </b>
</s:if>
<s:else>
<b>Set Password </b>
</s:else>

</div><div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>

<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- DATACARD -->
<jsp:include page="datacardSetPassword.jsp" />

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

<s:if test="hasActionMessages()">
	<script type="text/javascript">
	var errormessage = '<s:actionmessage/>';
	 document.getElementById("thepasswordconfirmedhelp").innerHTML = errormessage;
	 $('#thePassword').addClass('error');
	 $('#thePasswordConfirmed').addClass('error');
	</script>
</s:if>

<script type="text/javascript">
var URL = getCookie('raReferrer');
var RA = getCookie('ra');
var TRAIL = document.getElementById('breadcrumbs').innerHTML
document.getElementById('breadcrumbs').innerHTML=RA + TRAIL;
focusOn('password');
document.getElementById('continuetextLarge').innerHTML="<b>Finish</b>"
document.getElementById('continuetextSmall').innerHTML="<b>Finish</b>"
</script>

  </body>
</html>
