<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form namespace="/" autocomplete="off" action="security_questions" id="securityquestions" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar">
									<h1>
										<span>Security Questions</span>
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

									<!--QUESTION 1-->
									<div class="control-group">
										<label class="control-label" for="securityQuestion1">Question One:</label>
										<div class="controls">
											<s:select name="securityQuestion1" headerKey="" headerValue="Please select a question..." cssClass="input-large" list="securityQuestions1" id="securityQuestion1" />
										</div>
									</div>

									<!--ANSWER 1-->
									<div class="control-group">
										<label class="control-label" for="securityQuestion1Answer">Answer One:</label>
										<div class="controls">
											<s:textfield name="securityQuestion1Answer" cssClass="input-large" id="securityQuestion1Answer" size="40" maxlength="100" />
										</div>
									</div>

									<!--QUESTION 2-->
									<div class="control-group">
										<label class="control-label" for="securityQuestion2">Question Two:</label>
										<div class="controls">
											<s:select name="securityQuestion2" headerKey="" headerValue="Please select a question..." cssClass="input-large" list="securityQuestions2" id="securityQuestion2" />
										</div>
									</div>

									<!--ANSWER 2-->
									<div class="control-group">
										<label class="control-label" for="securityQuestion2Answer">Answer Two:</label>
										<div class="controls">
											<s:textfield name="securityQuestion2Answer" cssClass="input-large" id="securityQuestion2Answer" size="40" maxlength="100" />
										</div>
									</div>

									<!--QUESTION 3-->
									<div class="control-group">
										<label class="control-label" for="securityQuestion3">Question Three:</label>
										<div class="controls">
											<s:select name="securityQuestion3" headerKey="" headerValue="Please select a question..." cssClass="input-large" list="securityQuestions3" id="securityQuestion3" />
										</div>
									</div>

									<!--ANSWER 3-->
									<div class="control-group">
										<label class="control-label" for="securityQuestion3Answer">Answer Three:</label>
										<div class="controls">
											<s:textfield name="securityQuestion3Answer" cssClass="input-large" id="securityQuestion3Answer" size="40" maxlength="100" />
										</div>
									</div>
								</div>

								<!--FORM POPOVER-->
								<div id="formPopoverContent" class="hidden">
									<p class="muted">
										<span>
											Here you will establish a set of three security questions 
											for managing your password. You will need to answer these 
											questions should you ever forget or need to reset your password, 
											as they provide a method for confirming your identity. Select a 
											question from each question's pull-down menu and establish an 
											answer in the answer field for each question. All three questions 
											and answers must be established.
										</span>
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
						<div class="datacard-tabs">
							<ul class="unstyled">
								<li><span>Legal Name</span></li>
								<li><span>Address</span></li>
								<li><span>Additional</span></li>
								<li><span>Verify</span></li>
								<li><span>Privacy Policy</span></li>
								<li><span class="active">Security</span></li>
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
									You have not set all three security questions and answers. 
									If you do not set all three, you must contact the Help Desk 
									for assistance if you ever forget your password. Do you want 
									to continue without setting all three security questions and 
									answers?
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