<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="25%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">PREVIOUS ADDRESS</div>
		<a class="help" onclick="showhelpcontent('previousaddress', this, event, '200px')" onMouseover="showhelpcontent('previousaddress', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">
<!-- begin jsp content -->
<s:form action="/previous_address" id="previousaddress" method="post" cssClass="form-inline" theme="simple">

<fieldset class="fieldset">
<div id="prevcountry" class="control-group">
<label class="control-label" for="Pcountry">Country:</label>
<div class="controls">
<s:select name="country" cssClass="input-xlarge" list="countryMap" id="Pcountry" onchange="PstateorPprovince(this.options[selectedIndex].value);" tabindex="5"/>  	            	       
<span id="prevcountryhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="prevaddress1" class="control-group">
<label class="control-label" for="PaddressLine1">Address:</label>
<div class="controls">
<s:textfield name="addressLine1" cssClass="input-xlarge" id="PaddressLine1" tabindex="6" />
<span id="prevaddress1help" class="help-block hidden">&nbsp;</span>
</div>
</div>
<div id="prevaddress2" class="control-group">
<label class="control-label" for="PaddressLine2"></label>
<div class="controls">
<s:textfield name="addressLine2" cssClass="input-xlarge" id="PaddressLine2" tabindex="7" />
<span id="prevaddress2help" class="help-block hidden">&nbsp;</span>
</div>
</div>
<div id="prevaddress3" class="control-group">
<label class="control-label" for="PaddressLine3"></label>
<div class="controls">
<s:textfield name="addressLine3" cssClass="input-xlarge" id="PaddressLine3" tabindex="8" />
<span id="prevaddress3help" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="prevcity" class="control-group">
<label class="control-label" for="Pcity">City:</label>
<div class="controls">
<s:textfield name="city" cssClass="input-xlarge" id="Pcity" tabindex="9" />
<span id="prevcityhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="prevstate" class="control-group">
<label class="control-label" for="Pstate">State:</label>
<div class="controls">
<s:select name="state" cssClass="input-xlarge" id="Pstate" list="stateMap"
	headerValue="Please select..." headerKey="" tabindex="10">
</s:select>
<span id="prevstatehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="prevprovince" class="control-group hidden">
<label class="control-label" for="Pprovince">Province:</label>
<div class="controls">
<s:textfield name="province" cssClass="input-xlarge" id="Pprovince" tabindex="10" />
<span id="prevprovincehelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="prevpostalcode" class="control-group">
<label class="control-label" for="PpostalCode">Postal Code:</label>
<div class="controls">
<s:textfield name="postalCode" cssClass="input-xlarge" id="PpostalCode" tabindex="11"/>
<span id="prevpostalcodehelp" class="help-block hidden">&nbsp;</span>
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
