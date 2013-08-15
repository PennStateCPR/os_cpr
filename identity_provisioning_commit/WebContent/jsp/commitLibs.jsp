<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript" src="<s:property value='#JS_LIB_DIR'/>/jquery/jquery-min.js"></script>
<script type="text/javascript" src="<s:property value='#JS_LIB_DIR'/>/jquery-ui/ui/jquery-ui-min.js"></script>
<script type="text/javascript" src="<s:property value='#JS_LIB_DIR'/>/underscore/underscore-min.js"></script>
<script type="text/javascript" src="<s:property value='#JS_LIB_DIR'/>/bootstrap/bootstrap-min.js"></script>
<script type="text/javascript" src="<s:property value='#JS_LIB_DIR'/>/validation/jquery-validation-min.js"></script>
<script type="text/javascript" src="<s:property value='#JS_LIB_DIR'/>/validation/additional-methods-min.js"></script>
<script type="text/javascript" src="<s:property value='#JS_LIB_DIR'/>/outside/jquery-outside-min.js"></script>
<s:if test='%{#DEV_MODE == "true"}'>
	<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/utils.js"></script>
<!-- <script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/logo-url-parser.js"></script> -->
	<script type="text/javascript" src="<s:property value='#JS_SRC_DIR'/>/validator.js"></script>
</s:if>
<s:else>
	<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/utils-min.js"></script>
<!--  <script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/logo-url-parser-min.js"></script> -->
	<script type="text/javascript" src="<s:property value='#JS_MIN_DIR'/>/validator-min.js"></script>
</s:else>

<script type="text/javascript">
	// Define & Initialize.
	var ra = "<s:property value='#session["rac.ra"]' />",
		raHome = "<s:property value='#session["rac.homeURL"]' />",
		raReferrer = "<s:property value='#session["rac.raReferrer"]' />"
		idp = "<s:property value='#IDP_URL'/>";

	// Set cookies.
	commit.utils.setCookie('ra', ra);
	commit.utils.setCookie('raHome', raHome);
	commit.utils.setCookie('raReferrer', raReferrer);
	commit.utils.setCookie('idp', idp);

	// Data loss notificatin.
	//commit.utils.beforeUnload();
</script>