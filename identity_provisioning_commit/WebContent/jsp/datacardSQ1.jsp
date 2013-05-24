<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="75%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div class=" div-inline" style="width: 90%">SECURITY QUESTION ONE</div>
		<a class="help" onclick="showhelpcontent('securityanswers', this, event, '260px')" onMouseover="showhelpcontent('securityanswers', this, event, '260px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">

<s:form action="/sq1" id="question1" method="post" cssClass="form-inline" theme="simple">

<div id="theSecurityQuestion" style="padding:20px;">
<s:property value="%{#attr.securityQuestion}" />
</div>

<fieldset class="fieldset">

<div id="theSecurityAnswer" class="control-group">
<label class="control-label" for="securityAnswer">Answer: </label>
<div class="controls">
<s:textfield name="securityAnswer" cssClass="input-xlarge" id="securityAnswer" tabindex="5"/>
<span id="theSecurityAnswerHelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

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

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
