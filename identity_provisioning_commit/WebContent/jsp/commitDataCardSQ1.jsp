<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard">
						<s:form action="/sq1" id="sqanswer" method="post" cssClass="form-inline" theme="simple">
							<div class="titlebar">
								<h1>
									<span>Security Question: 1</span>
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

								<!--SECURITY QUESTION-->
								<p class="lead">
									<s:property value="%{#attr.securityQuestion}" />
								</p>

								<!--ANSWER-->
								<div class="control-group">
									<label class="control-label" for="securityAnswer">Answer: </label>
									<div class="controls">
										<s:textfield name="securityAnswer" cssClass="input-large" id="securityAnswer" />
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