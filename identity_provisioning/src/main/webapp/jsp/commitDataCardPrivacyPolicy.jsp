<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form namespace="/" autocomplete="off" action="privacy_policy" id="privacypolicy" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar">
									<h1>
										<span>Terms of Use & Privacy Policy</span>
										<i id="formPopover" data-toggle="popover" class="icon icon-question-sign"></i>
									</h1>
								</div>
								<div class="content">
									<div id="checkboxcontainer" class="checkboxcontainer">
										<s:checkbox name="agree" id="agree" class="checkbox inline" onclick="javascript:toggle(this);" /> 
											By clicking this box, you acknowledge that you have read and agree to the CommIT Terms of Use and Privacy Policy.
									</div>
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
								<li><span>Verify</span></li>
								<li><span>Additional</span></li>
								<li><span class="active">Privacy Policy</span>
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