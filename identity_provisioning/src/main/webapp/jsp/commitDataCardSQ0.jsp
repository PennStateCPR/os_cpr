<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard">
						<s:form namespace="/" autocomplete="off" action="sq0" id="matchfound" method="post" cssClass="form-horizontal" theme="simple">
							<div class="titlebar">
								<h1>
									<span>Match Found</span>
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

								<!--HERO UNIT-->
								<div class="hero-unit cm-hero-unit">
									<h2>You Have An Existing Account:</h2>
									<p>
										Our records indicate that you have already established 
										an account. The three security questions you established 
										when you created your account must be answered correctly 
										in order for you to manage your account or reset your 
										password. The security questions provide an additional 
										way for us to verify your identity. When all three 
										questions are answered correctly, you will be prompted 
										to reset your password.
									</p>
									<p>
										Please click <strong>continue</strong> to proceed.
									</p>
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
</section>