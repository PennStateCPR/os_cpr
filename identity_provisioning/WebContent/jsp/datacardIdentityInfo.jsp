<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="60%"</script>

<div class="container">
<div class="datacard" >
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">IDENTITY INFORMATION</div>
		<a class="help" onclick="showhelpcontent('idinfo', this, event, '200px')" onMouseover="showhelpcontent('idinfo', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>
	

<s:if test='%{#session["per.citizenship"] != "NON-US"}'>
<div style="padding:15px;">
<s:property value="#session['rac.ssnMessage']" />
</div>
</s:if>

<div class="dataform">
<s:form action="/identity_info" id="formdata" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">

<s:if test='%{#session["per.citizenship"] != "NON-US"}'>
<div id="thesocialSecurityNumber" class="control-group">
<label class="control-label" for="socialSecurityNumber">Social Security Number:</label>
<div class="controls inline">
<s:password name="socialSecurityNumber" cssClass="input-large" id="ssn" placeholder="999 99 9999" data-mask="999 99 9999" maxlength="11" tabindex="7"/>
<script type="text/javascript">
document.getElementById("ssn").setAttribute("placeholder","123-45-6789");
//document.getElementById("ssn").setAttribute("data-mask","999-99-9999");
</script>
<span> <a class="help" onclick="showhelpcontent('ssn', this, event, '200px')" onMouseover="showhelpcontent('ssn', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="padding-top: 6px; margin-left: 5px;"><i class="icon-question-sign"></i></div></a> </span>

<span><div id="btnGetSSN" class="btn btn-danger btn-mini hidden" onclick="getTestSSN();">Get SSN</div></span>

<span id="thesocialSecurityNumberhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>
</s:if>

<div id="theID" class="control-group <s:if test='%{#application["ui.fields.hidden.universityid"] == "yes"}'>hidden</s:if> ">
<label class="control-label" for="pennStateId"><s:property value="#application['ui.name.familiar']"/> ID <small>(if known)</small>  :</label>
<div class="controls inline">
<s:textfield name="pennStateId" cssClass="input-large" id="pennStateId" placeholder="9 1234 5678" data-mask="9-9999-9999" maxlength="11" tabindex="8"/>
<script type="text/javascript">
document.getElementById("pennStateId").setAttribute("placeholder","9-9999-9999");
document.getElementById("pennStateId").setAttribute("data-mask","9-9999-9999");
</script>
<span><a class="help" onclick="showhelpcontent('psuId', this, event, '200px')" onMouseover="showhelpcontent('psuId', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="padding-top: 6px; margin-left: 5px;"><i class="icon-question-sign"></i></div></a> </span>
<span id="theIDhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="theuserId" class="control-group <s:if test='%{#application["ui.fields.hidden.userid"] == "yes"}'>hidden</s:if> ">
<label class="control-label" for="userId">User ID <small>(if known)</small> :</label>
<div class="controls inline">
<s:textfield name="userId" cssClass="input-large" id="userId" placeholder="xyz1234" tabindex="9"/>
<span> <a class="help" onclick="showhelpcontent('userId', this, event, '200px')" onMouseover="showhelpcontent('userId', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="padding-top: 6px; margin-left: 5px;"><i class="icon-question-sign"></i></div></a> </span>
<span id="theuserIdhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

</fieldset>

<s:if test="hasActionMessages()">
   <div class="help-block actionmessage">
      <s:actionmessage/>
   </div>
</s:if>

<s:if test='%{#session["per.citizenship"] == "NON-US"}'>
<div class="desktop-spacer visible-desktop" style="margin:50px;">&nbsp;</div>
</s:if>

<s:hidden name="btnsubmit" id="btnsubmit" value="" />

<s:token />
</s:form>
</div>

<!-- NAVIGATION (Large Screen) -->    
<jsp:include page="navigationLarge.jsp" />

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
