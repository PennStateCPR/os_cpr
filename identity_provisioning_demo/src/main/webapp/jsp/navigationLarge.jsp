<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="navigationLarge" class="desktop-nav hidden-phone">
	<div id="backLarge">
	<a id="goback" href="<s:property value="previousAction"/>" tabindex="26" class="btn-navbar-left" onfocus="setFocusedElement(this.id)" onblur="setFocusedElement(null)"><div class="navbar-link btn btn-primary btn-large"  onclick="javascript:navunhook();" role="button">
		<small>Back</small>
	</div></a>
	</div>
	
	<div id="continueLarge">
	<a tabindex="25" class="btn-navbar-right"><div class="navbar-link btn btn-primary btn-large" onclick="javascript:validateforms();">
		<div id="continuetextLarge" role="button"><b>Continue</b></div>
	</div></a>
	</div>
</div>
