<%@ taglib prefix="s" uri="/struts-tags"%>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">
	document.getElementById('progressbar').style.width = "20%"
</script>

<div class="container">
	<div class="datacard">
		<div class="title">
			<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
			<div id="titletext" class=" div-inline" style="width: 90%">ADDRESS</div>
			<a class="help" onclick="showhelpcontent('address', this, event, '260px')" onMouseover="showhelpcontent('address', this, event, '260px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
		</div>

		<div class="dataform">

			<s:form action="/current_address" id="address" method="post"
				cssClass="form-inline" theme="simple">

				<fieldset class="fieldset">
					<div id="thecountry" class="control-group">
						<label class="control-label" for="country">*Country:</label>
						<div class="controls">
							<s:select name="country" cssClass="input-xlarge" list="countryMap" id="country" onchange="stateorprovince(this.options[selectedIndex].value);" tabindex="5"></s:select>
							<span id="thecountryhelp" class="help-block hidden">&nbsp;</span>
						</div>
					</div>

					<div id="theaddress1" class="control-group">
						<label class="control-label" for="addressLine1">*Address:</label>
						<div class="controls">
							<s:textfield name="addressLine1" cssClass="input-xlarge"
								id="addressLine1" tabindex="6"/>
							<span id="theaddress1help" class="help-block hidden">&nbsp;</span>
						</div>
					</div>
					<div id="theaddress2" class="control-group">
						<label class="control-label" for="addressLine2"></label>
						<div class="controls">
							<s:textfield name="addressLine2" cssClass="input-xlarge"
								id="addressLine2" tabindex="7"/>
							<span id="theaddress2help" class="help-block hidden">&nbsp;</span>
						</div>
					</div>
					<div id="theaddress3" class="control-group">
						<label class="control-label" for="addressLine3"></label>
						<div class="controls">
							<s:textfield name="addressLine3" cssClass="input-xlarge"
								id="addressLine3" tabindex="8"/>
							<span id="theaddress3help" class="help-block hidden">&nbsp;</span>
						</div>
					</div>

					<div id="thecity" class="control-group">
						<label class="control-label" for="city">*City:</label>
						<div class="controls">
							<s:textfield name="city" cssClass="input-xlarge" id="city" tabindex="9"/>
							<span id="thecityhelp" class="help-block hidden">&nbsp;</span>
						</div>
					</div>

					<div id="thestate" class="control-group">
						<label class="control-label" for="state">*State:</label>
						<div class="controls">
							<s:select name="state" cssClass="input-xlarge" id="state" list="stateMap"
								headerValue="Please select..." headerKey="" tabindex="10">
							</s:select>
							<span id="thestatehelp" class="help-block hidden">&nbsp;</span>
						</div>
					</div>

					<div id="theprovince" class="control-group hidden">
						<label class="control-label" for="province">*Province:</label>
						<div class="controls">
							<s:textfield name="province" cssClass="input-xlarge" id="province" tabindex="10"/>
							<span id="theprovincehelp" class="help-block hidden">&nbsp;</span>
						</div>
					</div>

					<div id="thepostalcode" class="control-group">
						<label class="control-label" for="postalCode">*Postal
							Code:</label>
						<div class="controls">
							<s:textfield name="postalCode" cssClass="input-xlarge"
								id="postalCode" tabindex="11"/>
							<span id="thepostalcodehelp" class="help-block hidden">&nbsp;</span>
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

	</div>
	<!-- END DATACARD -->
</div>
<!-- END CONTAINER -->

