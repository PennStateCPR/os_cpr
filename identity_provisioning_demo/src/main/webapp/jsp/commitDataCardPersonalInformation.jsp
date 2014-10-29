<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form namespace="/" autocomplete="off" action="personal_info" id="personalinfo" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar">
									<h1>
										<span>Personal Information</span>
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

									<!--DATE OF BIRTH-->
									<div class="control-group">
										<label class="control-label">Date of Birth:</label>
										<div class="controls">
											<!--month-->
											<label class="hidden" for="birthMonth">Month:</label>
											<s:textfield name="birthMonth" cssClass="input-mini cm-input-mini" id="birthMonth" maxlength="2" placeholder="mm" data-mask="99"/>
											<span> / </span>

											<!--day-->
											<label class="hidden" for="birthDay">Day:</label>
											<s:textfield name="birthDay" cssClass="input-mini cm-input-mini" id="birthDay" maxlength="2" placeholder="dd" data-mask="99"/>
											<span> / </span>

											<!--year-->
											<label class="hidden" for="birthYear">Year:</label>
											<s:textfield name="birthYear" cssClass="input-mini cm-input-mini" id="birthYear" maxlength="4" placeholder="yyyy" data-mask="9999"/>
										</div>
									</div>

									<!--GENDER-->
									<div class="control-group">
										<label for="gender" class="control-label">Gender:</label>
										<div class="controls">
											<s:select id="gender" name="gender" list="genderMap" headerKey="0" headerValue="Please select..." cssClass="input-large"></s:select>
										</div>
									</div>

									<!--COUNTRY-->
									<div class="control-group">
										<label class="control-label" for="country">Country of Origin:</label>
										<div class="controls">
											<s:select cssClass="input-large" name="country" list="countryMap" headerKey="0" headerValue="Please select..." id="country"></s:select>
										</div>
									</div>

									<!--CITIZENSHIP
									<div class="control-group">
										<label class="control-label" for="citizenship">Citizenship:</label>
										<div class="controls">
											<s:select id="citizenship" name="citizenship" list="citizenList" headerKey="0" headerValue="Please select..." cssClass="input-large"/>
										</div>
									</div>
									-->
								</div>

								<!--FORM POPOVER-->
								<div id="formPopoverContent" class="hidden">
									<p class="muted">
										<span>
											Please provide your personal information as requested below.
											NOTE: Your date of birth is required.
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
								<li><span>Address</span></li>
								<li><span>Additional</span></li>
								<li><span class="active">Personal</span></li>
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