<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="10%"</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%">DATA ACCURACY STATEMENT</div>
		<a class="help" onclick="showhelpcontent('dataaccuracy', this, event, '200px')" onMouseover="showhelpcontent('dataaccuracy', this, event, '200px')" onMouseout="delayhidetip()"><div class="div-inline" style="width: 2%; padding-top: 2px; margin-right: 2px;"><i class="icon-question-sign icon-white"></i></div></a>
	</div>

<div class="dataform">
<p>I certify that all information I provide is true and accurate to the best of my knowledge.<p>
<s:form action="/data_accuracy" id="dataaccuracy" method="post" cssClass="form-inline" theme="simple">
<div class="desktop-spacer visible-desktop">&nbsp;</div>			
<div id="checkboxcontainer" class="checkboxcontainer" style="padding-bottom: 20px" onclick="javascript:toggle(this);">
<s:checkbox name="statementAgree" id="statementAgree" tabIndex="5" cssClass="checkbox inline" onclick="javascript:toggle(this);" /> <b>I agree</b>
</div>

<s:if test="hasActionMessages()">
   <div class="help-block actionmessage">
      <s:actionmessage/>
   </div>
</s:if>

<s:hidden name="btnsubmit" id="btnsubmit" value="" />
<s:token />
</s:form>
<p>&nbsp;<p>
</div>

<div class="desktop-spacer visible-desktop">&nbsp;</div>

<!-- NAVIGATION (Large Screen) -->    
<jsp:include page="navigationLarge.jsp" />
<script type="text/javascript">document.getElementById('continueLarge').style.visibility="hidden";</script>

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
