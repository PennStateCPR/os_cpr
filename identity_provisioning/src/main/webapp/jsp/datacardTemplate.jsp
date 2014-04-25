<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="75%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div class=" div-inline" style="width: 90%">DATACARD TITLE</div>
		<div class=" div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><a class="help"><i class="icon-question-sign icon-white"></i></a></div>
	</div>

<div class="dataform">

<s:form action="/class_name" id="classname" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">

<div id="thefieldOne" class="control-group">
<label class="control-label" for="fieldOne">*Field One:</label>
<div class="controls">
<s:textfield name="fieldOne" class="input-large" id="fieldOne"/>
<span id="thefieldonehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theFieldTwo" class="control-group">
<label class="control-label" for="fieldTwo">Field Two:</label>
<div class="controls">
<s:textfield name="fieldTwo" class="input-xlarge" id="fieldTwo" />
<span id="theFieldTwohelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theFieldThree" class="control-group">
<label class="control-label" for="fieldThree">*Field Three:</label>
<div class="controls">
<s:textfield name="fieldThree" class="input-xlarge" id="fieldThree" />
<span id="theFieldThreehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theFieldFour" class="control-group">
<label class="control-label" for="fieldFour">Field Four:</label>
<div class="controls">
<s:textfield name="fieldFour" class="input-xlarge" id="fieldFour" />
<span id="theFieldFourhelp" class="help-block hidden">&nbsp;</span>
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
