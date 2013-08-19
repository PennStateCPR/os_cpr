<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form action="/alternate_address" id="alternate" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar">
									<h1>
										<span>Alternate Address</span>
										<i id="formPopover" data-toggle="popover" class="icon icon-question-sign"></i>
									</h1>
								</div>
								<div class="content">
									<!--SERVER-SIDE ERROR MESSAGES-->
									<s:if test="hasActionMessages()">
										<div class="alert alert-block alert-danger cm-alert cm-alert-danger">
											<button type="button" class="close" data-dismiss="alert">&times;</button>
											<p><s:actionmessage/></p>
										</div>
									</s:if>

									<!--ADDRESS 1-->
									<div class="control-group">
										<label class="control-label" for="addressLine1">Address:</label>
										<div class="controls">
											<s:textfield name="addressLine1" cssClass="input-large" id="addressLine1" maxlength="60" />
										</div>
									</div>

									<!--ADDRESS 2-->
									<div class="control-group">
										<label class="control-label" for="addressLine2">Address 2:</label>
										<div class="controls">
											<s:textfield name="addressLine2" cssClass="input-large" id="addressLine2" maxlength="60" />
										</div>
									</div>

									<!--ADDRESS 3-->
									<div class="control-group">
										<label class="control-label" for="addressLine3">Address 3:</label>
										<div class="controls">
											<s:textfield name="addressLine3" cssClass="input-large" id="addressLine3" maxlength="60" />
										</div>
									</div>

									<!--CITY-->
									<div class="control-group">
										<label class="control-label" for="city">City:</label>
										<div class="controls">
											<s:textfield name="city" cssClass="input-large" id="city" maxlength="40" />
										</div>
									</div>

									<!--STATE-->
									<div class="control-group" style="display: none;">
										<label class="control-label" for="state">State:</label>
										<div class="controls">
											<s:select name="state" cssClass="input-large" id="state" list="stateMap"
												headerValue="Please select..." headerKey="">
											</s:select>
										</div>
									</div>

									<!--PROVINCE-->
									<div id="provinceGroup" class="control-group" style="display: none;">
										<label class="control-label" for="province">Province:</label>
										<div class="controls">
											<s:textfield name="province" cssClass="input-large" id="province" maxlength="40" />
										</div>
									</div>

									<!--ZIP CODE-->
									<div class="control-group">
										<label class="control-label" for="postalCode">Zip Code +4:</label>
										<div class="controls">
											<s:textfield name="postalCode" cssClass="input-large" id="postalCode" maxlength="20" />
										</div>
									</div>

									<!--COUNTRY-->
									<div class="control-group">
										<label class="control-label" for="country">Country:</label>
										<div class="controls">
											<s:select name="country" cssClass="input-large" id="country" list="countryMap" onchange="commit.alternateaddress.onCountryChange(this);"></s:select>
										</div>
									</div>
								</div>

								<!--FORM POPOVER-->
								<div id="formPopoverContent" class="hidden">
									<p class="muted">
										<span>
											Please enter your current postal/surface mailing address. 
											You must first specify the country, then include your street 
											address and other relevant information such as an apartment or 
											unit number, the city, state/province, and the postal/zip code. 
											If you use an APO/FPO military mailing address, indicate United 
											States as the country where you are currently living and provide 
											that address.
										</span>
									</p>
								</div>

								<!--FORM CONTROLS-->
								<div class="actions clearfix">
									<a id="backButton" href="<s:property value="previousAction"/>" class="btn btn-primary cm-btn cm-btn-primary pull-left">Back</a>
									<button id="continueButton" type="submit" class="btn btn-primary cm-btn cm-btn-primary pull-right">Continue</button>
								</div>

								<s:hidden name="btnsubmit" id="btnsubmit" value="" />
								<s:token />
							</s:form>
						</div>
						<div class="datacard-tabs">
							<ul class="unstyled">
								<li><span>Legal Name</span></li>
								<li><span class="active">Address</span></li>
								<li><span>Additional</span></li>
								<li><span>Verify</span></li>
								<li><span>Security</span></li>
								<li><span>Password</span></li>
							</ul>
						</div>

						<!--FORM MODAL-->
						<div id="formModal" class="modal hide fade">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal">&times;</button>
								<h3>Important!</h3>
							</div>
							<div class="modal-body">
								<p></p>
							</div>
							<div class="modal-footer">
								<button id="formModalNo" type="button" class="btn cm-btn" data-dismiss="modal">No</button>
								<button id="formModalYes" type="button" class="btn btn-primary cm-btn cm-btn-primary">Yes</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>