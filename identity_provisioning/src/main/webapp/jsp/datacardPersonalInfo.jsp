<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="55%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">PERSONAL INFORMATION</div>
		<a class="help" onclick="showhelpcontent('personalinfo', this, event, '200px')" onMouseover="showhelpcontent('personalinfo', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">

<s:form action="/personal_info" id="personalinfo" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">

<div id="theBirth" class="control-group">
<label id="birthLabelMM" class="control-label" for="birthMonth">*Date of Birth:</label>
<div class="controls inline">
<s:textfield name="birthMonth" cssClass="" style="width:25px;" id="birthMonth" maxlength="2" placeholder="mm" tabindex="5" data-mask="99"/>
	<span> / </span>
<label id="birthLabelDD" class="" for="birthDay"></label>
<s:textfield name="birthDay" cssClass="" style="width:25px;" id="birthDay" maxlength="2" placeholder="dd" tabindex="6" data-mask="99"/>
	<span> / </span>
<label id="birthLabelYYYY" class="" for="birthYear"></label>
<s:textfield name="birthYear" cssClass="" style="width:40px;" id="birthYear" maxlength="4" placeholder="yyyy" tabindex="7" data-mask="9999"/>
	<span> <a class="help" onclick="showhelpcontent('DOB', this, event, '200px')" onMouseover="showhelpcontent('DOB', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="padding-top: 6px; margin-left: 5px;"><i class="icon-question-sign"></i></div></a> </span>
<span id="theBirthhelp" class="help-block error hidden">&nbsp;</span>
</div>
</div>

<div id="thegender" class="control-group">
<label id="genderLabel" class="control-label" for="gender">Gender:</label>
<div class="controls">
<s:select name="gender" list="genderMap" headerKey="" headerValue="Please select..." cssClass="input-xlarge" tabindex="8"></s:select> 
<span id="thegenderhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="thecountry" class="control-group">
<label id="labelcountryoforigin" class="control-label" for="country">Country of Origin:</label>
<div class="controls">
<s:select cssClass="input-xlarge" name="country" list="countryMap" id="country"  tabindex="9"></s:select>
<span id="thecountryhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="thecitizenship" class="control-group">
<label id="labelCitizenship" class="control-label" for="citizenship">Citizenship:</label>
<div class="controls">
<s:select name ="citizenship" list="citizenList" headerKey="" headerValue="Please select..." cssClass="input-xlarge" tabindex="10"/>
<span id="thecitizenshiphelp" class="help-block hidden">&nbsp;</span>
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

