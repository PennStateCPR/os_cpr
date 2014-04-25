<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="30%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">CONTACT INFORMATION</div>
		<a class="help" onclick="showhelpcontent('contact', this, event, '200px')" onMouseover="showhelpcontent('contact', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">

<s:form action="/contact_info" id="contactinfo" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">
<div id="contactemail" class="control-group">
<label class="control-label" for="email">*E-mail:</label>

<div class="controls inline">
<s:textfield name="email" cssClass="input-xlarge" maxlength="256" id="email" tabindex="5"/>
	<span> <a class="help" onclick="showhelpcontent('emailAddress', this, event, '200px')" onMouseover="showhelpcontent('emailAddress', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="padding-top: 6px; margin-left: 5px;"><i class="icon-question-sign"></i></div></a> </span>
<span id="theemailhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="contactphone" class="control-group">
<label class="control-label" for="phoneNumber">*Telephone Number:</label>
<div class="controls inline">
<s:textfield name="phoneNumber" cssClass="input-xlarge" maxlength="16" id="phoneNumber" tabindex="6"/>
	<span> <a class="help" onclick="showhelpcontent('phoneNumber', this, event, '200px')" onMouseover="showhelpcontent('phoneNumber', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="padding-top: 6px; margin-left: 5px;"><i class="icon-question-sign"></i></div></a> </span>
<span id="thephonenumberhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="contactextension" class="control-group">
<label class="control-label" for="extension">Extension:</label>
<div class="controls">
<s:textfield name="extension" cssClass="input-xlarge" maxlength="40" id="extension" tabindex="7"/>
<span id="theextensionhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="contactinternational" class="control-group">
<label class="control-label" for="internationalNumber">International Number? </label>
<div class="controls">
<s:checkbox name="internationalNumber" cssClass="input-xlarge" id="internationalNumber" tabindex="8"/>
<span id="theinternationalhelp" class="help-block hidden">&nbsp;</span>
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
						
