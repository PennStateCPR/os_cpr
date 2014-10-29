<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<s:if test='%{#session["sec.password.setting"] == "initial"}'>
	<s:set name="IS_PASSWORD" value="true"/>
</s:if>
<s:else>
	<s:set name="IS_PASSWORD" value="false"/>
</s:else>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>The IAM Testbed Project: Success</title>
		<jsp:include page="commitHead.jsp" />
	</head>
	<body class="page success-page">
		<!--HEADER-->
		<jsp:include page="commitHeader.jsp" />

		<!--DATACARD-->
		<jsp:include page="commitDataCardSuccess.jsp" />

		<!--FOOTER-->
		<jsp:include page="commitFooter.jsp" />

		<!--JAVASCRIPT DEPENDENCIES-->
		<jsp:include page="commitLibs.jsp" />
		<s:if test='%{#DEV_MODE == "true"}'>
			<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/success.js"></script>
		</s:if>
		<s:else>
			<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/success-min.js"></script>
		</s:else>
		<script type="text/javascript">
			// Define.
			var url, idpUrl, idpCookieUrl;

			// Initialize.
			idpUrl = "<s:property value='#IDP_URL'/>";
			idpCookieUrl = commit.utils.getCookie('idp');
			url = (!idpCookieUrl) ? idpUrl : idpCookieUrl;

			// Execute.
			commit.success.initialize(url);
		</script>
	</body>
</html>
