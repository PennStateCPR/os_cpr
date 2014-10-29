<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>The IAM Testbed Project: Additional Information</title>
		<jsp:include page="commitHead.jsp" />
	</head>
	<body class="page contactinfo-page">
		<!--HEADER-->
		<jsp:include page="commitHeader.jsp" />

		<!--DATACARD-->
		<jsp:include page="commitDataCardContactInfo.jsp" />

		<!--FOOTER-->
		<jsp:include page="commitFooter.jsp" />

		<!--JAVASCRIPT DEPENDENCIES-->
		<jsp:include page="commitLibs.jsp" />
		<s:if test='%{#DEV_MODE == "true"}'>
			<script type="text/javascript">
				var dob = $('#dobControl').dob();
			</script>
			<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/contact-info.js"></script>
		</s:if>
		<s:else>
			<script type="text/javascript">
				var dob = $('#dobControl').dob();
			</script>
			<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/contact-info-min.js"></script>
		</s:else>
	</body>
</html>
