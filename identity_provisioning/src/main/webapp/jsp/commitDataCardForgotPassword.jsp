<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard">
						<s:form namespace="/" autocomplete="off" action="forgot_password" id="reset" method="post" cssClass="form-horizontal" theme="simple">
							<div class="titlebar">
								<h1>
									<span>Forgot Password</span>
									<i id="formPopover" data-toggle="popover" class="icon icon-question-sign hidden"></i>
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

								<!--COMMIT ID-->
								<div class="control-group">
									<label class="control-label" for="commitId">IAM Testbed Username:</label>
									<div class="controls">
										<s:textfield name="commitId" cssClass="input-large" id="commitId" maxlength="60" />
									</div>
								</div>

								<!--FIRST NAME-->
								<div class="control-group">
									<label class="control-label" for="firstName">First Name:</label>
									<div class="controls">
										<s:textfield name="firstName" cssClass="input-large" id="firstName" maxlength="60" />
									</div>
								</div>

								<!--LAST NAME-->
								<div class="control-group">
									<label class="control-label" for="lastName">Last Name:</label>
									<div class="controls">
										<s:textfield name="lastName" cssClass="input-large" id="lastName" maxlength="60" />
									</div>
								</div>

								<!--CITY-->
								<div class="control-group">
									<label class="control-label" for="city">City:</label>
									<div class="controls">
										<s:textfield name="city" cssClass="input-large" id="city" maxlength="40" />
									</div>
								</div>

								<!--DATE OF BIRTH-->
								<div class="control-group">
									<label class="control-label" for="dob">Date of Birth:</label>
									<div id="dobControl" class="controls dob clearfix">
										<div class="dob-selectlist">
											<select id="dobMonth"></select>
											<select id="dobDay"></select>
											<select id="dobYear"></select>
											<div id="dobLoader" class="dob-loader">&nbsp;</div>
										</div>

										<s:textfield name="dob" cssClass="input-small hide" id="dob" placeholder="mm/dd/yyyy" />
									</div>
								</div>
							</div>

							<!--FORM POPOVER-->
							<div id="formPopoverContent" class="hidden">
								<p class="muted">
									<span></span>
								</p>
							</div>

							<!--FORM CONTROLS-->
							<div class="actions clearfix">
								<button id="continueButton" type="submit" class="btn btn-primary cm-btn cm-btn-primary pull-right">Continue</button>
							</div>

							<s:hidden name="btnsubmit" id="btnsubmit" value="" />
							<s:token />
						</s:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
