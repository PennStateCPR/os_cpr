<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>The CommIT Project: Home Address</title>
		<jsp:include page="commitHead.jsp" />
	</head>
	<body class="page currentaddress-page">
		<!--HEADER-->
		<jsp:include page="commitHeader.jsp" />

		<!--DATACARD-->
		<jsp:include page="commitDataCardCurrentAddress.jsp" />

		<!--FOOTER-->
		<jsp:include page="commitFooter.jsp" />

		<!--JAVASCRIPT DEPENDENCIES-->
		<jsp:include page="commitLibs.jsp" />
		<s:if test='%{#DEV_MODE == "true"}'>
			<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/current-address.js"></script>
		</s:if>
		<s:else>
			<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/current-address-min.js"></script>
		</s:else>
	</body>
</html>