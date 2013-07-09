<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard-tabform clearfix">
						<div class="datacard-form">
							<s:form action="/verify_info" id="verifyinfo" method="post" cssClass="form-horizontal" theme="simple">
								<div class="titlebar">
									<h1>
										<span>Verify Your Information</span>
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

									<!--MINI:DATACARD:LEGAL NAME-->
									<div class="datacard-mini">
										<div class="datacard-mini-titlebar">
											<h2><a href="legal_name" class="verification-link">Legal Name<i class="icon-edit"></i></a></h2>
										</div>
										<div class="datacard-mini-content">
											<p class="help-block">
												<s:property value="#session['lna.firstName']" />
												<s:property value="#session['lna.middleNames']" />
												<s:property value="#session['lna.lastName']" />
												<s:property value="#session['lna.suffix']" />
											</p>
											<s:if test="%{#session['lna.nickName'] != ''}">
												<p class="help-block">
													<span><strong>Nick Name: </strong></span>
													<s:property value="#session['lna.nickName']" />
												</p>
											</s:if>
										</div>
									</div>

									<!--MINI:DATACARD:ADDRESS-->
									<div class="datacard-mini">
										<div class="datacard-mini-titlebar">
											<h2><a href="current_address" class="verification-link">Home Address<i class="icon-edit"></i></a></h2>
										</div>
										<div class="datacard-mini-content">
											<p class="help-block">
												<s:property value="#session['cra.addressLine1']" />
												<s:property value="#session['cra.addressLine2']" />
												<s:property value="#session['cra.addressLine3']" />
											</p>
											<p class="help-block">
												<s:property value="#session['cra.city']" />,
												<s:property value="#session['cra.state']" />
												<s:property value="#session['cra.province']" />
												<s:property value="#session['cra.postalCode']" />
											</p>
											<p class="help-block">
												<s:property value="#session['cra.country']" />
											</p>
										</div>
									</div>

									<!--MINI:DATACARD:ALTERNATE_ADDRESS-->
									<s:if test="%{#session['alt.addressLine1'] != null}">
										<div class="datacard-mini">
											<div class="datacard-mini-titlebar">
												<h2><a href="alternate_address" class="verification-link">Alternate Address<i class="icon-edit"></i></a></h2>
											</div>
											<div class="datacard-mini-content">
												<p class="help-block">
													<s:property value="#session['alt.addressLine1']" />
													<s:property value="#session['alt.addressLine2']" />
													<s:property value="#session['alt.addressLine3']" />
												</p>
												<p class="help-block">
													<s:property value="#session['alt.city']" />,
													<s:property value="#session['alt.state']" />
													<s:property value="#session['alt.province']" />
													<s:property value="#session['alt.postalCode']" />
												</p>
												<p class="help-block">
													<s:property value="#session['alt.country']" />
												</p>
											</div>
										</div>
									</s:if>
									
									

									<!--MINI:DATACARD:ADDITIONAL INFORMATION-->
									<div class="datacard-mini">
										<div class="datacard-mini-titlebar">
											<h2><a href="contact_info" class="verification-link">Additional Information<i class="icon-edit"></i></a></h2>
										</div>
										<div class="datacard-mini-content">
											<p class="help-block">
												<span><strong>Phone: </strong></span>
												<s:property value="#session['con.phoneNumber']" />
											</p>
											<s:if test="%{#session['con.extension'] != ''}">
												<p class="help-block">
													<span><strong>Extension: </strong></span>
													<s:property value="#session['con.extension']" />
												</p>
											</s:if>
											<s:if test="%{#session['con.internationalNumber'] == 'true'}">
												<p class="help-block">
													<span><em>( international number )</em></span>
												</p>
											</s:if>
											<p class="help-block">
												<span><strong>Email: </strong></span>
												<s:property value="#session['con.email']" />
											</p>
											<p class="help-block">
												<span><strong>Date of Birth: </strong></span>
												<s:property value="#session['con.dob']" />
											</p>
										</div>
									</div>

								</div>

								<!--FORM POPOVER-->
								<div id="formPopoverContent" class="hidden">
									<p class="muted">
										<span>
											Please verify all of the information you have entered. 
											If you need to make changes or corrections, click the 
											<strong>edit</strong> link in the appropriate section.
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
								<li><span class="active">Verify</span></li>
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