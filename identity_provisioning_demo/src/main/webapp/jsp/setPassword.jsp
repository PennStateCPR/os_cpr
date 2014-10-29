<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test="%{#session['sec.password.setting'] != 'initial'}">
	<s:set name="PASSWORD_TITLE_CONTEXT" value="%{'Reset'}"/>
</s:if>
<s:else>
	<s:set name="PASSWORD_TITLE_CONTEXT" value="%{'Set'}"/>
</s:else>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>
			The IAM Testbed Project: <s:property value='#PASSWORD_TITLE_CONTEXT'/> Password
		</title>
		<jsp:include page="commitHead.jsp" />
	</head>
	<body class="page password-page">
		<!--HEADER-->
		<jsp:include page="commitHeader.jsp" />

		<!--DATACARD-->
		<jsp:include page="commitDataCardSetPassword.jsp" />

		<!--FOOTER-->
		<jsp:include page="commitFooter.jsp" />

		<!--JAVASCRIPT DEPENDENCIES-->
		<jsp:include page="commitLibs.jsp" />
		<s:if test='%{#DEV_MODE == "true"}'>
			<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/password.js"></script>
		</s:if>
		<s:else>
			<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/password-min.js"></script>
		</s:else>
	</body>
</html>
