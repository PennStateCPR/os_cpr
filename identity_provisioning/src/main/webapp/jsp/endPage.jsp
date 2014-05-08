<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
	<c:when test='${exception == "datalock"}'>
		<s:set name="TITLE_ERROR" value="%{'Session Incomplete'}"/>
		<s:set name="CONDITION_ERROR" value="%{'The following problem has been encountered'}"/>
	</c:when>
	<c:when test='${exception == "other"}'>
		<s:set name="TITLE_ERROR" value="%{'Session Ended'}"/>
		<s:set name="CONDITION_ERROR" value="%{'The following error has been encountered'}"/>
	</c:when>
	<c:otherwise>
		<s:set name="TITLE_ERROR" value="%{'Session Complete'}"/>
		<s:set name="CONDITION_ERROR" value="%{'The following condition has been encountered'}"/>
	</c:otherwise>
</c:choose>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>The IAM Testbed Project: <s:property value="#TITLE_ERROR"/></title>
		<jsp:include page="commitHead.jsp" />
	</head>
	<body class="page error-page">
		<!--HEADER-->
		<jsp:include page="commitHeader.jsp" />

		<!--DATACARD-->
		<jsp:include page="commitDataCardEndPage.jsp" />

		<!--FOOTER-->
		<jsp:include page="commitFooter.jsp" />

		<!--JAVASCRIPT DEPENDENCIES-->
		<jsp:include page="commitLibs.jsp" />
		<s:if test='%{#DEV_MODE == "true"}'>
			<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/error.js"></script>
		</s:if>
		<s:else>
			<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/error-min.js"></script>
		</s:else>
		<script type="text/javascript">
			// Define.
			var exception;

			// Initialize.
			exception = '${exception}';

			// Execute.
			commit.error.initialize(exception);
		</script>
	</body>
</html>
