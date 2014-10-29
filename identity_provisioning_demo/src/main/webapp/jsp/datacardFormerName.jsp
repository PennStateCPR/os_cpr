<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="20%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">FORMER NAME</div>
		<a class="help" onclick="showhelpcontent('formername', this, event, '200px')" onMouseover="showhelpcontent('formername', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">

<s:form action="/former_name" id="formername" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">
<div id="formerfirstname" class="control-group">
<label class="control-label" for="firstName">First/Given Name:</label>
<div class="controls">
<s:textfield name="firstName" cssClass="input-xlarge" id="FfirstName" tabindex="5"/>
<span id="thefirstnamehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="formermiddlenames" class="control-group">
<label class="control-label" for="middleNames">Middle Names:</label>
<div class="controls">
<s:textfield name="middleNames" cssClass="input-xlarge" id="FmiddleNames" tabindex="6"/>
<span id="themiddlenameshelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="formerlastname" class="control-group">
<label class="control-label" for="lastName">Last/Family Name:</label>
<div class="controls">
<s:textfield name="lastName" cssClass="input-xlarge" id="FlastName" tabindex="7"/>
<span id="thelastnamehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="formersuffix" class="control-group">
<label class="control-label" for="Fsuffix">Suffix:</label>
<div class="controls">
<s:textfield name="suffix" cssClass="input-xlarge" id="Fsuffix" tabindex="8"/>
<span id="thesuffixhelp" class="help-block hidden">&nbsp;</span>
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
