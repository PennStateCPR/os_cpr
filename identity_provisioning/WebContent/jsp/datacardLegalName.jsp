<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="15%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">LEGAL NAME</div>
		<a class="help" onclick="showhelpcontent('name', this, event, '260px')" onMouseover="showhelpcontent('name', this, event, '260px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">

<s:form action="/legal_name" id="legalname" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">
<div id="thefirstname" class="control-group">
<label class="control-label" for="firstName">*First/Given Name:</label>
<div class="controls">
<s:textfield name="firstName" cssClass="input-xlarge" id="firstName" tabindex="5" />
<span id="thefirstnamehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="themiddlenames" class="control-group">
<label class="control-label" for="middleNames">Middle Names:</label>
<div class="controls">
<s:textfield name="middleNames" cssClass="input-xlarge" id="middleNames" tabindex="6" />
<span id="themiddlenameshelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="thelastname" class="control-group">
<label class="control-label" for="lastName">*Last/Family Name:</label>
<div class="controls">
<s:textfield name="lastName" cssClass="input-xlarge" id="lastName" tabindex="7" />
<span id="thelastnamehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="thesuffix" class="control-group">
<label class="control-label" for="suffix">Suffix:</label>
<div class="controls">
<s:textfield name="suffix" cssClass="input-xlarge" id="suffix" tabindex="8" />
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
