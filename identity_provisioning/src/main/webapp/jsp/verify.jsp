<%  /* SVN FILE: $Id: verify.jsp 5865 2012-12-10 18:20:17Z Charles $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/*
 * verify.jsp is the page the end user will view and verify the previously entered data.
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
<title><s:property value="#application['ui.sitename']"/>: Verify Your Information</title>

<!-- DOCUMENT HEAD -->
<jsp:include page="head.jsp" />

</head>

<body>

<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"> &gt; <s:property value="#application['ui.sitename']"/> &gt; <b>Verify Your Information </b></div><div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>

<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- DATACARD -->
<jsp:include page="datacardVerify.jsp" />
<s:property value="#session['ip.ra.screens.Identity Provisioning.portal5']" />
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
	document.getElementById('backSmall').style.visibility="hidden";
	document.getElementById('backLarge').style.visibility="hidden";
	loadVerifData();
	if (document.getElementById('thebirthyear').innerHTML !="/ ") {
		$("#thebirthyear").attr('class', 'div-inline');
	}
	else {
		$("#thebirthyear").attr('class', 'hidden');
	}
	if (('<s:property value="#session['fmr.firstName']"/>' == "") && ('<s:property value="#session['fmr.middleNames']"/>' == "") && ('<s:property value="#session['fmr.lastName']"/>' == "") && ('<s:property value="#session['fmr.suffix']"/>' == "")) {
	$("#noFname").attr('class', 'div-inline');
	}
	else {
	$("#noFname").attr('class', 'hidden');
	}

	var screens="<s:property value="#session['rac.screen.order.list']" />";
	var miniDataCardPreviousAddress = screens.indexOf("PreviousAddress");
	var miniDataCardFormerName = screens.indexOf("FormerName");
	
	if(miniDataCardPreviousAddress != -1) {
	$("#previousaddress").attr('class', 'div-inline');
	}
		
	if(miniDataCardFormerName != -1) {
	$("#formername").attr('class', 'div-inline');
	}

	</script>

  </body>
</html>
