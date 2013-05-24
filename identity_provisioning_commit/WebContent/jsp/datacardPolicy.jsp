<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="80%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">POLICY AGREEMENT</div>
		<a class="help" onclick="showhelpcontent('policy', this, event, '200px')" onMouseover="showhelpcontent('policy', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">
<p>By continuing, you acknowledge that you will comply with the policies of <s:property value="#application['ui.name.formal']"/>, and both Federal and Commonwealth of Pennsylvania law in all uses of this account. You understand that unauthorized or illegal use could potentially result in investigation of the activity in question, termination of the account, civil action against you or criminal prosecution. If you do not agree to the above terms of use, please do not continue.</p>
<s:form action="/policy_info" id="policy" method="post" cssClass="form-inline" theme="simple">
			
<div id="checkboxcontainer" class="checkboxcontainer" onclick="javascript:toggle(this);">
<s:checkbox name="agree" id="policyAgree" class="checkbox inline" onclick="javascript:toggle(this);" /> <b>I AGREE TO THE ABOVE TERMS</b>
</div>

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

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
