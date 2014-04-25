<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard">
						<div class="titlebar">
							<h1><span>Request Complete</span></h1>
						</div>
						<div class="content">
							<div class="hero-unit cm-hero-unit">
								<h2>Congratulations!</h2>
								<p>
									<s:if test='%{#IS_PASSWORD}'>
										You have successfully created your <strong>User ID</strong>. It will be used 
										to access electronic services.
									</s:if>
									<s:else>
										Your password has been reset successfully. You may now log into 
										the system using the User ID below and your new password.
									</s:else>
								</p>
								<p>
									<ul class="unstyled">
										<li>Name: <strong><s:property value="#session['lna.firstName']" /> <s:property value="#session['lna.middleNames']" /> <s:property value="#session['lna.lastName']" /> <s:property value="#session['lna.suffix']" /></strong></li>
										<li>User ID: <strong><s:property value="userId" /></strong></li>
			
									</ul>
								</p>
								<p>
									<s:if test='%{#IS_PASSWORD}'>
										You will receive an e-mail at <strong><s:property value="#session['con.email']" />
										</strong> containing your User ID. <s:property value="#application['ui.help.contact.message']"/>
									</s:if>
									<s:else>
										You will receive an e-mail at <strong><s:property value="#session['con.email']" /></strong> 
										confirming this change. <s:property value="#application['ui.help.contact.message']"/>
									</s:else>
								</p>
							</div>
						</div>

						<!--FORM CONTROLS-->
						<div class="actions clearfix">
							<a id="continueButton" href="javascript:;" class="btn btn-primary cm-btn cm-btn-primary pull-right">Continue</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>