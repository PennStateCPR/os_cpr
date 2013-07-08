<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!--VARS-->
<s:set name="IDP_URL" value="%{'https://login.commonidtrust.org/idp/Authn/UserPassword'}"/>
<s:set name="ICO_DIR" value="%{'./assets/ico'}"/>
<s:set name="CSS_DIR" value="%{'./assets/css'}"/>
<s:set name="JS_DIR" value="%{'./assets/js'}"/>
<s:set name="JS_LIB_DIR" value="%{'./assets/js/lib'}"/>
<s:set name="JS_SRC_DIR" value="%{'./assets/js/src'}"/>
<s:set name="JS_MIN_DIR" value="%{'./assets/js/min'}"/>
<s:set name="THEME_DIR" value="%{'/default'}"/>
<s:set name="DEV_MODE" value="%{'false'}"/>

<!--BASE-->
<base href="/IdentityProvisioning/">

<!--META TAGS-->
<meta name="language" content="English">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="expires" content="-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="robots" content="noindex, nofollow">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent">
<meta name="description" content="Identity Provisioning">
<meta name="keywords" content="Identity Provisioning">

<!--CSS DEPENDENCIES-->
<link href="<s:property value='#CSS_DIR'/><s:property value='#THEME_DIR'/>/commit.css" rel="stylesheet">
<link href="<s:property value='#JS_LIB_DIR'/>/jquery-ui/base/jquery-ui-min.css" rel="stylesheet">

<!-- HTML5 SHIM (for IE6-8 support of HTML5 elements) -->
<!--[if lt IE 9]>
<script src="<s:property value='#JS_LIB_DIR'/>/html5/html5shiv-min.js"></script>
<![endif]-->

<!--FAV & TOUCH ICONS-->
<link rel="shortcut icon" href="<s:property value='#ICO_DIR'/>/ico.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144" href="<s:property value='#ICO_DIR'/>/icon144.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114" href="<s:property value='#ICO_DIR'/>/icon114.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72" href="<s:property value='#ICO_DIR'/>/icon72.png">
<link rel="apple-touch-icon-precomposed" sizes="57x57" href="<s:property value='#ICO_DIR'/>/icon57.png">