<%  /* SVN FILE: $Id: securityQuestions.jsp 5865 2012-12-10 18:20:17Z Charles $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/**
 * securityQuestions.jsp - Present user with several several challenge questions
 * and record their answer for future 'I forgot password' endevors. 
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
<title><s:property value="#application['ui.sitename']"/>: Security Questions</title>

<!-- DOCUMENT HEAD -->
<jsp:include page="head.jsp" />

</head>

<body>

<div class="navbar navbar-fixed-top">
<!-- BREADCRUMB TRAIL -->
<div id="breadcrumbs" class="breadcrumb-left navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.sitename']"/> > <b>Security Questions </b></div><div class="breadcrumb-right navbar-fixed-top-content visible-desktop"><s:property value="#application['ui.application.id']"/></div>


<!-- HEADER -->
<jsp:include page="header.jsp" />

<!-- DATACARD -->
<jsp:include page="datacardSecurityQuestions.jsp" />

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

	<!-- Confirm security question opt-out -->
		<div class="modal show fade" id="optOut" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
			<div class="modal-header">
				<h3 id="modalLabel">Important!</h3>
			</div>
			<div class="modal-body">
				You have not set all three security questions and answers. If you do not set all three, you must contact the Help Desk for assistance if you ever forget your password. Do you want to continue without setting all three security questions and answers?
			</div>
			<div class="modal-footer">
				<button tabIndex="49" id="no" class="btn" data-dismiss="modal" aria-hidden="true">No</button>
				<button tabIndex="48" id="yes" class="btn btn-primary" onclick="$('#optOut').modal('hide'); submitSecurityQuestions()">Yes</button>
			</div>
		</div>
		
<script type="text/javascript">
var URL = getCookie('raReferrer');
var RA = getCookie('ra');
var TRAIL = document.getElementById('breadcrumbs').innerHTML;
document.getElementById('breadcrumbs').innerHTML=RA + TRAIL;
focusOn('securityQuestion1');
</script>

  </body>
</html>
