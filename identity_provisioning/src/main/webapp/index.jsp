<%  /* SVN FILE: $Id: index.jsp 5865 2012-12-10 18:20:17Z Charles $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/**
 * index.jsp is a development-only start page for entering Identity Provisioning from an authorized
 * registration authority (RA)
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
<title><s:property value="#application['ui.sitename']"/>: Demo Site</title>

<!-- DOCUMENT HEAD -->
<jsp:include page="jsp/head.jsp" />

</head>

<body>

<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.sitename']"/> Demo Site > <b>Welcome! </b></div>
<div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>

<!-- HEADER -->
<jsp:include page="jsp/header.jsp" />

<!-- DATACARD -->
<jsp:include page="jsp/datacardIndex.jsp" />

<div id="debugdata">
<s:if test="%{#session.debug == 'enabled'}">
<s:debug/>
</s:if>
</div>

<div class="navbar footer visible-phone">
	<div class="navbar-inner">
		<div class="container">
			<!--left button content-->
			<a class="btn-navbar-left"><div class="navbar-link btn btn-navbar" style="margin-left: 5px;" onclick="javascript:navunhook(); ouaSubmit()">
				<small><b>Undergrad Admissions</b></small>
			</div></a>
			<!--right button content-->
			<a class="btn-navbar-right"><div class="navbar-link btn btn-navbar" style="margin-right: 5px;" onclick="javascript:navunhook(); gsoSubmit()">
				<small><b>Grad School</b></small>
			</div></a>
		</div>
	</div>
</div>

<!-- FOOTER -->
<jsp:include page="jsp/footer.jsp" />

	<!-- JavaScript placed at the end of the document so the pages load faster! -->
    <script src="./assets/js/jquery.js"></script>
    <script src="./assets/js/bootstrap-transition.js"></script>
    <script src="./assets/js/bootstrap-alert.js"></script>
    <script src="./assets/js/bootstrap-modal.js"></script>
    <script src="./assets/js/bootstrap-dropdown.js"></script>
    <script src="./assets/js/bootstrap-button.js"></script>
    <script src="./assets/js/bootstrap-collapse.js"></script>
    <script src="./assets/js/bootstrap-iam.js"></script>
	<script src="./assets/js/raRedirect.js"></script>

  </body>
</html>