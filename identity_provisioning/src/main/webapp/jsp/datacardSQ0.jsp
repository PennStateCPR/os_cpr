<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="75%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div class=" div-inline" style="width: 90%">MATCH FOUND!</div>
		<div class=" div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;">&nbsp;</div>
	</div>

<div class="dataform">

<s:form action="/sq0" id="matchfound" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">

<p>Our records indicate that you have already established an account. The three security questions you established when you created your account must be answered correctly in order for you to manage your account or reset your password. The security questions provide an additional way for us to verify your identity. When all three questions are answered correctly, you will be prompted to reset your password.</p>
<br>
<p>Please click <b>continue</b> to proceed.</p>

</fieldset>

<s:if test="hasActionMessages()">
   <div class="help-block actionmessage">
      <s:actionmessage/>
   </div>
</s:if>

<s:hidden name="btnsubmit" id="btnsubmit" value="" />

<s:token />
</s:form>

</div>

<div class="desktop-spacer visible-desktop">&nbsp;</div>

<!-- NAVIGATION (Large Screen) -->    
<jsp:include page="navigationLarge.jsp" />
<script type="text/javascript">
document.getElementById('backLarge').style.visibility="hidden";
</script>


</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
