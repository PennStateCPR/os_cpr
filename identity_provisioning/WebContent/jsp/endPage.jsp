<% 
/* SVN FILE: $Id: endPage.jsp 5865 2012-12-10 18:20:17Z Charles $ */
	
/**
 * endPage.jsp is the page used to transmit error awareness to the end user.  
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

<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta charset="utf-8">

<c:choose>
<c:when test='${exception == "datalock"}'>
<title><s:property value="#application['ui.sitename']"/>: Session Incomplete!</title>
</c:when>

<c:when test='${exception == "other"}'>
<title><s:property value="#application['ui.sitename']"/>: Session Ended</title>
</c:when>

<c:otherwise>
<title><s:property value="#application['ui.sitename']"/>: Session Complete!</title>
</c:otherwise>
</c:choose>

<!-- DOCUMENT HEAD -->
<jsp:include page="head.jsp" />

</head>

<body>
<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.sitename']"/> &gt;


<c:choose>
<c:when test='${exception == "datalock"}'>
<b> <s:property value="endPageHeader"/> </b>
</c:when>

<c:when test='${exception == "other"}'>
<b> Session Ended </b>
</c:when>

<c:otherwise>
<b> Session Completed </b>
</c:otherwise>
</c:choose>

</div><div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>

<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- DATACARD -->
<jsp:include page="datacardEndPage.jsp" />

<!-- DEVELOPER TOOLS -->
<jsp:include page="devtools.jsp" />



<div id="navigationSmall" class="navbar footer visible-phone">
	<div class="navbar-inner">
		<div class="container">
			<div id="backSmall" class="hidden">
			<a tabindex="28" class="btn-navbar-left" href="<s:property value="#session['previousAction']" />"><div class="navbar-link btn btn-navbar navSmall" onclick="javascript:navunhook();" role="button">
				<small>Back</small>
			</div></a>
			</div>
			<div id="continueSmall" class="navSmall">
			<a id="returnlink" tabindex="27" class="btn-navbar-right"><div class="navbar-link btn btn-navbar">
				<div id="returntext" role="button"><small><b>Continue</b></small></div>
			</div></a>
			</div>
		</div>
	</div>
</div>



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
		<c:choose>
		<c:when test='${exception == "datalock"}'>
		var URL = 'javascript:window.location.reload(true)';
		</c:when>
		<c:when test='${exception == "other"}'>
		var URL = getCookie('raHome');
		</c:when>
		<c:otherwise>
		var URL = getCookie('raReferrer');
		</c:otherwise>
		</c:choose>        
		var RA = getCookie('ra');
		var TRAIL = document.getElementById('breadcrumbs').innerHTML;
		<s:if test='%{exception == "datalock"}'>
		document.getElementById('returntext').innerHTML="<b>Continue</b>";
		</s:if>
		<s:else>
		document.getElementById('breadcrumbs').innerHTML="<a href='" + URL + "'>" + RA + "</a> &gt; " + TRAIL;
		document.getElementById('returntext').innerHTML="<b>OK</b>";
		</s:else>
        document.getElementById('returnlink').setAttribute('href', URL);
		focusOn('returnlink');
    </script>

  </body>
  
  <s:if test="%{#session.do.not.invalidate != 'y'}">
  	<% session.invalidate(); %>
  </s:if>
</html>