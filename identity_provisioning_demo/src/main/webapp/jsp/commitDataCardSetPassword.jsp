<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form namespace="/" autocomplete="off" action="password" id="setpassword" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar">
									<h1>
										<span><s:property value='#PASSWORD_TITLE_CONTEXT'/> Password</span>
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

									<!--PASSWORD INSTRUCTIONS-->
									<div class="alert alert-block alert-info cm-alert">
										<dl class="cm-dl">
											<dt>Passwords must contain:</dt>
											<dd>At least 1 number, 1 lowercase letter and 8 characters.</dd>
										</dl>

										<dl class="cm-dl">
											<dt>Passwords must NOT contain:</dt>
											<dd>Your name or your initials.</dd>
											<s:if test="%{#session['sec.password.setting'] != 'initial'}">
												<dd>Your User ID <s:property value="#session['suc.userId']" />.</dd>
											</s:if>
										</dl>
									</div>

									<!--PASSWORD-->
									<div class="control-group">
										<label class="control-label" for="password">Enter Password:</label>
										<div class="controls">
											<s:password name="password" cssClass="input-medium" id="password" onkeyup="commit.password.onPasswordStrength(this.value);" />
											<i id="passwordPopover" data-toggle="popover" class="icon icon-question-sign hidden"></i>
										</div>
									</div>

									<!--MATCH PASSWORD-->
									<div class="control-group">
										<label class="control-label" for="passwordConfirmed">Re-enter Password:</label>
										<div class="controls">
											<s:password name="passwordConfirmed" cssClass="input-medium" id="passwordConfirmed" />
										</div>
									</div>

									<!--PROGRESS-->
									<div class="control-group">
										<label class="control-label">Password Strength:</label>
										<div class="controls">
											<div id="progress" class="progress progress-info cm-width-150">
												<div id="progressBar" class="bar" style="width: 0%"></div>
												<s:hidden id="progressHidden" name="progressHidden" value="0" />
											</div>
										</div>
									</div>

									<!--PASSWORD POPOVER-->
									<div id="passwordPopoverContent" class="hidden muted">
										<h6>Passwords must contain:</h6>
										<ul>
											<li>1 Number</li>
											<li>1 Lowercase Letter</li>
											<li>8 Characters</li>
										</ul>

										<h6>Passwords must NOT contain:</h6>
										<ul>
											<li>Name</li>
											<li>Initials</li>
											<s:if test="%{#session['sec.password.setting'] != 'initial'}">
												<li>User ID <s:property value="#session['suc.userId']" /></li>
											</s:if>
										</ul>
									</div>
								</div>

								<!--FORM POPOVER-->
								<div id="formPopoverContent" class="hidden">
									<p class="muted">
										<span>
											Please choose a password for your account.
										</span>
									</p>
								</div>

								<!--FORM CONTROLS-->
								<div class="actions clearfix">
									<a id="backButton" href="<s:property value="previousAction"/>" class="btn btn-primary cm-btn cm-btn-primary pull-left">Back</a>
									<button id="continueButton" type="submit" class="btn btn-primary cm-btn cm-btn-primary pull-right">Finish</button>
								</div>

								<s:hidden name="btnsubmit" id="btnsubmit" value="" />
								<s:token />
							</s:form>
						</div>
						<div class="datacard-tabs">
							<ul class="unstyled">
								<li><span>Legal Name</span></li>
								<li><span>Additional</span></li>
								<li><span>Verify</span></li>
								<li><span>Security</span></li>
								<li><span class="active">Password</span></li>
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