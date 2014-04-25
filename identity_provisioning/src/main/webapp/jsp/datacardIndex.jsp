<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="0%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class="div-inline" style="width: 90%"><s:property value="#application['ui.sitename']"/> Demo Site</div>
	</div>

<div class="dataform">
<h1><small>Welcome to the <s:property value="#application['ui.sitename']"/> Demo Site!</small></h1>
<p>Use this page to demonstrate entering the <s:property value="#application['ui.sitename']"/> Identity Provisioning process as a new user.</p>
<p>Click on the RA below to simulate <s:property value="#application['ui.sitename']"/> Identity Provisioning coming from that RA website.</p>
</div>

<div class="desktop-spacer visible-desktop">&nbsp;</div>
<!-- NAVIGATION (Large Screen) -->    

<div class="desktop-nav hidden-phone" style="padding-bottom: 90px;">

<!--left button content-->
<a class="btn-navbar-left" tabIndex="6"><div class="navbar-link btn btn-primary btn-large" onclick="javascript:navunhook(); ouaSubmit()" style="margin-left:35px; padding: 15px 25px;">
<b>Undergraduate Admissions</b>
</div></a>

<!--right button content-->
<a class="btn-navbar-right" tabIndex="7"><div class="navbar-link btn btn-primary btn-large" onclick="javascript:navunhook(); gsoSubmit()" style="margin-right:35px; padding: 15px 25px;">
<b>The Graduate School</b>
</div></a>
</div>

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->