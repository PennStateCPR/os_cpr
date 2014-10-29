<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="85%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">SECURITY QUESTIONS</div>
		<a class="help" onclick="showhelpcontent('securityquestions', this, event, '260px')" onMouseover="showhelpcontent('securityquestions', this, event, '260px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">

<s:form action="/security_questions" id="securityquestions" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">

<div id="theQuestionOne" class="control-group">
<label class="control-label" for="securityQuestion1">Question One:</label>
<div class="controls">
<s:select name="securityQuestion1"
headerKey="-1" headerValue="Please select a security question..." cssClass="input-xlarge" list="securityQuestions1" id="securityQuestion1" tabindex="5"/>
<span id="theQuestionOneHelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theAnswerOne" class="control-group">
<label class="control-label" for="securityQuestion1Answer">Answer One:</label>
<div class="controls">
<s:textfield name="securityQuestion1Answer" cssClass="input-xlarge" id="securityQuestion1Answer" size="40" maxlength="100" tabindex="6"/>
<span id="theAnswerOneHelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theQuestionTwo" class="control-group">
<label class="control-label" for="securityQuestion2">Question Two:</label>
<div class="controls">
<s:select name="securityQuestion2"
headerKey="-2" headerValue="Please select a security question..." cssClass="input-xlarge" list="securityQuestions2" id="securityQuestion2" tabindex="7"/>
<span id="theQuestionOneHelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theAnswerTwo" class="control-group">
<label class="control-label" for="securityQuestion2Answer">Answer Two:</label>
<div class="controls">
<s:textfield name="securityQuestion2Answer" cssClass="input-xlarge" id="securityQuestion2Answer" size="40" maxlength="100" tabindex="8"/>
<span id="theAnswerTwoHelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theQuestionThree" class="control-group">
<label class="control-label" for="securityQuestion3">Question Three:</label>
<div class="controls">
<s:select name="securityQuestion3"
headerKey="-3" headerValue="Please select a security question..." cssClass="input-xlarge" list="securityQuestions3" id="securityQuestion3" tabindex="9"/>
<span id="theQuestionThreeHelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theAnswerThree" class="control-group">
<label class="control-label" for="securityQuestion3Answer">Answer Three:</label>
<div class="controls">
<s:textfield name="securityQuestion3Answer" cssClass="input-xlarge" id="securityQuestion3Answer" size="40" maxlength="100" tabindex="10"/>
<span id="theAnswerThreeHelp" class="help-block hidden">&nbsp;</span>
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

<!-- NAVIGATION (Large Screen) -->    
<jsp:include page="navigationLarge.jsp" />

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->