<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="95%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">
		
			<s:if test='%{#session["sec.password.setting"] != "initial"}'>
			RESET PASSWORD
			</s:if>
			<s:else>
			SET PASSWORD
			</s:else>
		
		</div>
		<a class="help" onclick="showhelpcontent('password', this, event, '200px')" onMouseover="showhelpcontent('password', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">



<div class="span">
<b>Must contain at least:</b>
<ul>
	<li>1 number </li>
	<li>1 lowercase letter </li>
	<li>8 characters </li>
</ul>
</div>

<div class="span" style="margin-right:20px;">
<b>Must NOT contain:</b>
<ul>
	<li>Name </li>
	<li>Initials </li>

	<s:if test='%{#session["sec.password.setting"] != "initial"}'>
	<li>User ID (<s:property value="#session['suc.userId']" />)
	</li>
	</s:if>

</ul>
</div>

<div class="bold" style="padding-top: 0px;">Strength Meter</div>

<div id="progress" class="progress progress-danger" style="width: 120px;">
	<div id="progress-bar" class="bar" style="width: 0%;"></div>
</div>

<div id="passwordStrength" style="padding-top: 0px;">&nbsp;</div>

<s:form action="/password" id="setpassword" method="post" cssClass="form-inline" theme="simple">
<br><br>
<fieldset class="fieldset">

<div id="thePassword" class="control-group">
<label class="control-label" for="password">*Enter Password:</label>
<div class="controls">
<s:password name="password" cssClass="input-xlarge" id="password" onkeyup="passwordStrength(this.value)" tabindex="5"/>
<span id="thepasswordhelp" class="help-block hidden">&nbsp;</span>
</div>
</div>

<div id="thePasswordConfirmed" class="control-group">
<label class="control-label" for="passwordConfirmed">*Re-enter Password:</label>
<div class="controls">
<s:password name="passwordConfirmed" cssClass="input-xlarge" id="passwordConfirmed" tabindex="6" />
<span id="thepasswordconfirmedhelp" class="help-block">&nbsp;</span>
</div>
</div>
</fieldset>

<s:if test="hasActionMessages()">
   <div class="help-block actionmessage">
      <s:actionmessage/>
   </div>
</s:if>

<div class="visible-desktop" style="margin:20px">&nbsp;</div>

<s:hidden name="btnsubmit" id="btnsubmit" value="" />

<s:token />
</s:form>

</div>


<!-- NAVIGATION (Large Screen) -->    
<jsp:include page="navigationLarge.jsp" />

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
