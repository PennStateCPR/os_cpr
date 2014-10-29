<%@ taglib prefix="s" uri="/struts-tags" %>

<section>
	<div class="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="datacard">
						<div class="titlebar">
							<h1><span><s:property value="endPageHeader"/></span></h1>
						</div>
						<div class="content">
							<div class="hero-unit cm-hero-unit">
								<h2><s:property value="#CONDITION_ERROR"/>:</h2>
								<s:if test='%{#uiMessage != ""}'>
									<s:property value="uiMessage"/>
								</s:if>
								<s:if test="hasActionMessages()">
									<s:actionmessage/>
								</s:if>
							</div>
						</div>
						<div class="actions clearfix">
							<a id="continueButton" href="javascript:;" class="btn btn-primary cm-btn cm-btn-primary pull-right">OK</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>