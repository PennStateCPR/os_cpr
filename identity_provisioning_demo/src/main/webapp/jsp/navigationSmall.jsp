<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="navigationSmall" class="navbar footer visible-phone">
	<div class="navbar-inner">
		<div class="container">
			<div id="backSmall">
			<a tabindex="28" class="btn-navbar-left" href="<s:property value="previousAction"/>"><div class="navbar-link btn btn-navbar navSmall" onclick="javascript:navunhook();" role="button">
				<small>Back</small>
			</div></a>
			</div>
			<div id="continueSmall" class="navSmall">
			<a tabindex="27" class="btn-navbar-right"><div class="navbar-link btn btn-navbar" onclick="javascript:validateforms();">
				<div id="continuetextSmall" role="button"><small><b>Continue</b></small></div>
			</div></a>
			</div>
		</div>
	</div>
</div>
