<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>The CommIT Project: Personal Information</title>
		<jsp:include page="commitHead.jsp" />
	</head>
	<body class="page personalinfo-page">
		<!--HEADER-->
		<jsp:include page="commitHeader.jsp" />

		<!--DATACARD-->
		<jsp:include page="commitDataCardPersonalInformation.jsp" />

		<!--FOOTER-->
		<jsp:include page="commitFooter.jsp" />

		<!--JAVASCRIPT DEPENDENCIES-->
		<jsp:include page="commitLibs.jsp" />
		<s:if test='%{#DEV_MODE == "true"}'>
			<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/personal-info.js"></script>
		</s:if>
		<s:else>
			<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/personal-info-min.js"></script>
		</s:else>
	</body>
</html>