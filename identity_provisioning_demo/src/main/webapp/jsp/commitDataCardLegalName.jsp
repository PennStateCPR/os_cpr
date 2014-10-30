<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form namespace="/" autocomplete="off" action="legal_name" id="legalname" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar clearfix">
									<h1>
										<span>Legal Name</span>
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

									<!--FIRST NAME-->
									<div class="control-group">
										<label class="control-label" for="firstName">First Name:</label>
										<div class="controls">
											<s:textfield name="firstName" cssClass="input-large" id="firstName" maxlength="60" />
										</div>
									</div>

									<!--MIDDLE NAME-->
									<div class="control-group">
										<label class="control-label" for="middleNames">Middle Name:</label>
										<div class="controls">
											<s:textfield name="middleNames" cssClass="input-large" id="middleNames" maxlength="60" />
										</div>
									</div>

									<!--LAST NAME-->
									<div class="control-group">
										<label class="control-label" for="lastName">Last Name:</label>
										<div class="controls">
											<s:textfield name="lastName" cssClass="input-large" id="lastName" maxlength="60" />
										</div>
									</div>

									<!--NICK NAME-->
									<div class="control-group">
										<label class="control-label" for="nickName">Preferred Name:</label>
										<div class="controls">
											<s:textfield name="nickName" cssClass="input-large" id="nickName" maxlength="40" />
										</div>
									</div>

									<!--SUFFIX LIST BOX-->
									<div class="control-group">
										<label class="control-label" for="suffix">Suffix:</label>
										<div class="controls">
											<s:select cssClass="input-large"
												list="suffixList" 
												name="suffix" 
												value="defaultSuffix" />
										</div>
									</div>									
								</div>

								<!--FORM POPOVER-->
								<div id="formPopoverContent" class="hidden">
									<p class="muted">
										<span>
											Please enter your full, legally-given name. Typically, this is the name 
											that is given to you at birth and/or recognized by a government or other 
											legal entity, and appears on a birth certificate or other government issued 
											docments such as a passport. It may also include a spouse's or partner's last name. Enter your name information and click Continue.
										</span>
									</p>
								</div>

								<!--FORM CONTROLS-->
								<div class="actions clearfix">
									<!--<a id="backButton" href="<s:property value="previousAction"/>" class="btn btn-primary cm-btn cm-btn-primary pull-left">Back</a>-->
									<button id="continueButton" type="submit" class="btn btn-primary cm-btn cm-btn-primary pull-right">Continue</button>
								</div>

								<s:hidden name="btnsubmit" id="btnsubmit" value="" />
								<s:token />
							</s:form>
						</div>
						<div class="datacard-tabs">
							<ul class="unstyled">
								<li><span class="active">Legal Name</span></li>
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