<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="80%"</script>

<div class="container" style="max-width: 980px">

<div class="datacard verifydata" style="">

<s:form action="/verify_info" id="classname" method="post" cssClass="form-inline" theme="simple">


<div id="heading" class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">PLEASE VERIFY YOUR INFORMATION BEFORE CONTINUING</div>
		<a class="help" onclick="showhelpcontent('verify', this, event, '200px')" onMouseover="showhelpcontent('verify', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
</div><br>

<div class="center">
<!-- Mini data card - top row -->
<div class="div-inline">
<div id="miniNames" class="minidatacard tilt-left" onclick="javascript:togglescale(this)">
<a tabindex="6" href="legal_name" onclick="javascript:navunhook();" title="edit legal name"><b>Legal Name</b> (edit)</a><br>
<s:property value="#session['lna.firstName']" />
<s:property value="#session['lna.middleNames']" />
<s:property value="#session['lna.lastName']" />
<s:property value="#session['lna.suffix']" /><br><br>
<!-- May or may not collect -->
<div id="formername" class="hidden">
<a href="former_name" tabindex="7" onclick="javascript:navunhook();" title="edit former name"><b>Former Name</b> (edit)</a><br>
<div id="thefmrFirstName" class="div-inline"><s:property value="#session['fmr.firstName']" /></div>
<s:property value="#session['fmr.middleNames']" />
<div id="thefmrLastName" class="div-inline"><s:property value="#session['fmr.lastName']" /></div>
<s:property value="#session['fmr.suffix']" />
<div id="noFname" class="div-inline">No Former Name Provided</div>
</div>
<!-- END of may or may not collect -->
</div>
</div>

<div class="div-inline">
<div id="miniAddress" class="minidatacard" onclick="javascript:togglescale(this)">
<a href="current_address" tabindex="8" onclick="javascript:navunhook();" title="edit address"><b>Address</b> (edit)</a><br>
<s:property value="#session['cra.addressLine1']" />
<div id="Caddress2" class="hidden"><s:property value="#session['cra.addressLine2']" /></div>
<div id="Caddress3" class="hidden"><s:property value="#session['cra.addressLine3']" /></div>
<s:property value="#session['cra.city']" />,
<s:property value="#session['cra.state']" />
<s:property value="#session['cra.province']" />
<s:property value="#session['cra.postalCode']" /><br>
<s:property value="#session['cra.country']" />
</div>
</div>

<div class="div-inline">
<div id="miniContact" class="minidatacard tilt-right" onclick="javascript:togglescale(this)">
<a href="contact_info" tabindex="9" onclick="javascript:navunhook();" title="edit contact information"><b>Contact Information</b> (edit)</a><br>
<s:property value="#session['con.phoneNumber']" /><br>
<div id="phoneextension" class="hidden">&nbsp;extension <s:property value="#session['con.extension']" /></div>
<div id="international" class="hidden"><s:property value="#session['con.internationalNumber']" /></div>
<s:property value="#session['con.email']" />
</div>
</div>

<div class="div-inline">
<div id="miniNames" class="minidatacard tilt-right" onclick="javascript:togglescale(this)">
<a href="identity_info" tabindex="10" onclick="javascript:navunhook();" title="edit identity information"><b>Identity Information</b> (edit)</a><br>
<div id="theSSN" class="hidden">SSN: <s:property value="#session['idi.socialSecurityNumber']" /></div>
<div id="thePSUID" class="hidden">PSU ID: <s:property value="#session['idi.pennStateId']" /></div>
<div id="theUserID" class="hidden">User ID: <s:property value="#session['idi.userId']" /></div>
<div id="noIDinfo" class="div-inline">No Identity Information Provided</div>
</div>
</div>

<!-- May or may not collect -->
<div id="previousaddress" class="hidden">
<div id="miniPrevAddress" class="minidatacard" onclick="javascript:togglescale(this)">
<a href="previous_address" tabindex="11" onclick="javascript:navunhook();" title="edit previous address"><b>Previous Address</b> (edit)</a><br>
<div id="Paddress" class="hidden">
	<div id="Paddresslineone"><s:property value="#session['pra.addressLine1']" /></div>
<div id="Faddress2" class="hidden"><s:property value="#session['pra.addressLine2']" /></div>
<div id="Faddress3" class="hidden"><s:property value="#session['pra.addressLine3']" /></div>
<s:property value="#session['pra.city']" />,
<s:property value="#session['pra.state']" />
<s:property value="#session['pra.province']" />
<s:property value="#session['pra.postalCode']" /><br>
<s:property value="#session['pra.country']" />
</div>
<div id="noPaddress" class="">No Previous Address Provided</div>
</div>
</div>
<!-- END of may or may not collect -->

<div class="div-inline">
<div id="miniContact" class="minidatacard tilt-left" onclick="javascript:togglescale(this)">
<a href="personal_info" tabindex="12" onclick="javascript:navunhook();" title="edit personal information"><b>Personal Information</b> (edit)</a><br>
Birth Date:&nbsp;&nbsp;<s:property value="#session['per.birthMonth']" /> /
<s:property value="#session['per.birthDay']" />
<div id="thebirthyear" class="div-inline">/ <s:property value="#session['per.birthYear']" /></div><br>
<div id="thegender" class="div-inline">Gender:&nbsp;&nbsp;<s:property value="#session['per.gender']" /></div><br>
Country of Origin:&nbsp;&nbsp;<s:property value="#session['per.country']" /><br>
<div id="thecitizenship" class="div-inline">Citizenship:&nbsp;&nbsp;<s:property value="#session['per.citizenship']" />
</div>
</div>
<p>&nbsp;</p>
</div>

<s:if test="hasActionMessages()">
   <div id="hasactionmessage" class="help-block actionmessage">
      <s:actionmessage/>
   </div>
</s:if>

<s:hidden name="control" value="" />
<s:hidden name="btnsubmit" id="btnsubmit" value="" />

<s:token />
</s:form>

<!-- NAVIGATION (Large Screen) -->    
<jsp:include page="navigationLarge.jsp" />

</div>
</div> <!-- END CONTAINER -->
