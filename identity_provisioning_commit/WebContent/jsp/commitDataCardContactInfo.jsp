<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form action="/contact_info" id="contactinfo" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar">
									<h1>
										<span>Additional Information</span>
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

									<!--EMAIL-->
									<div id="emailControlGroup" class="control-group">
										<label class="control-label" for="email">Email:</label>
										<div class="controls">
											<s:textfield name="email" cssClass="input-medium" maxlength="256" id="email"/>
											<i id="emailPopover" data-toggle="popover" class="icon icon-question-sign"></i>
										</div>
									</div>

									<!--DATE OF BIRTH-->
									<div class="control-group">
										<label class="control-label" for="dob">Date of Birth:</label>
										<div class="controls">
											<!--DOB-->
											<s:textfield name="dob" cssClass="input-small" id="dob" placeholder="mm/dd/yyyy"/>
											<!--<i id="dobPopover" data-toggle="popover" class="icon icon-question-sign"></i>-->
										</div>
									</div>

									<!--TELEPHONE-->
									<div class="control-group">
										<label class="control-label" for="phoneNumber">Telephone:</label>
										<div class="controls">
											<s:textfield name="phoneNumber" cssClass="input-medium" maxlength="16" id="phoneNumber"/>
											<i id="phonePopover" data-toggle="popover" class="icon icon-question-sign"></i>
										</div>
									</div>

									<!--EXTENSION-->
									<div class="control-group">
										<label class="control-label" for="extension">Extension:</label>
										<div class="controls">
											<s:textfield name="extension" cssClass="input-mini" maxlength="40" id="extension"/>
										</div>
									</div>

									<!--INTERNATIONAL CHECKBOX-->
									<div class="control-group">
										<label class="control-label" for="internationalNumber">International:</label>
										<div class="controls">
											<s:checkbox name="internationalNumber" id="internationalNumber" onclick="commit.contactinfo.onInternationalSelect(this);"/>
										</div>
									</div>

									<!--EMAIL POPOVER-->
									<div id="emailPopoverContent" class="hidden">
										<h6>Expected Format:</h6>
										<p class="muted">
											<span class="help-block"><em>you@email.com</em></span>
											<span>
												Please enter your primary e-mail address in this field.
												It is important to note, that you will receive important
												information and updates through this email.
											</span>
										</p>
									</div>

									<!--DOB POPOVER-->
									<div id="dobPopoverContent" class="hidden">
										<h6>Expected Format:</h6>
										<p class="muted">
											<span class="help-block"><em>mm/dd/yyyy</em></span>
											<span>
												Please enter your date of birth in this field.
											</span>
										</p>
									</div>

									<!--PHONE POPOVER-->
									<div id="phonePopoverContent" class="hidden">
										<h6>Expected Format:</h6>
										<p class="muted">
											<span class="help-block"><em>(555) 234-5678</em></span>
											<span class="help-block"><em>5552345678</em></span>
											<span class="help-block"><em>+1 555 234 5678</em></span>
											<span class="help-block"><em>+61 1 2345 6789</em></span>
											<span>
												Be sure to include the country code if the telephone number
												is based outside of the United States.
											</span>
										</p>
									</div>
								</div>

								<!--FORM POPOVER-->
								<div id="formPopoverContent" class="hidden">
									<p class="muted">
										<span>
											Please provide your contact information as requested below.
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
								<li><span class="active">Additional</span></li>
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
								<p>
									You have indicated that your primary e-mail address is: 
									<span><strong id="primaryEmail"></strong></span>. Do you 
									grant permission to send information related to your 
									registration to this email address?
								</p>
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