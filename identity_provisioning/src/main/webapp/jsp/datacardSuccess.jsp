<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="100%"</script>

<div class="container">
<div class="datacard">
<div class="title"><s:property value="#application['ui.request.complete.header']"/></div>

<div class="dataform">

<s:if test='%{#session["sec.password.setting"] == "initial"}'>
<p>Congratulations, you have successfully created your User ID! It will be used for accessing <s:property value="#application['ui.name.familiar']"/>  electronic services. Your <s:property value="#application['ui.name.nickname']"/> ID is your unique <s:property value="#application['ui.name.familiar']"/> identity.</p>
	<div class="left">
		<big>
		Name: <b><s:property value="#session['lna.firstName']" /> <s:property value="#session['lna.middleNames']" /> <s:property value="#session['lna.lastName']" /> <s:property value="#session['lna.suffix']" /></b><br>
		User ID: <b><s:property value="userId" /></b><br>
		<s:property value="#application['ui.name.nickname']"/> ID: <b><s:property value="psuId" /></b>
		</big>
	</div>
	<p>You will receive an e-mail at <b><s:property value="#session['con.email']" /></b> containing your User ID and your <s:property value="#application['ui.name.nickname']"/> ID. <s:property value="#application['ui.help.contact.message']"/></p>
</s:if>
<s:else>
<p>Congratulations, your password has been reset successfully! You may now log into <s:property value="#application['ui.name.familiar']"/> electronic services using the User ID below and your new password.</p>
	<div class="left">
		<big>
		Name: <b><s:property value="#session['lna.firstName']" /> <s:property value="#session['lna.middleNames']" /> <s:property value="#session['lna.lastName']" /> <s:property value="#session['lna.suffix']" /></b><br>
		User ID: <b><s:property value="userId" /></b><br>
		<s:property value="#application['ui.name.nickname']"/> ID: <b><s:property value="psuId" /></b>
		</big>
	</div>
	<p>You will receive an e-mail at <b><s:property value="#session['con.email']" /></b> confirming this change. <s:property value="#application['ui.help.contact.message']"/></p>
</s:else>

</div>

<div class="center">
<a id="returnlink" class="btn-navbar" href="#"><div id="returntext" class="navbar-link btn btn-primary btn-large" style="padding: 15px 25px;">
</div></a>
</div>

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
