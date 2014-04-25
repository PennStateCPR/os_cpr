<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.display='none';</script>

<div class="container">
<div class="datacard">
	<div class="title">
		<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
		<div id="titletext" class=" div-inline" style="width: 90%"><s:property value="endPageHeader"/></div>
	</div>

<div class="dataform">

<c:choose>
<c:when test='${exception == "datalock"}'>
<p>The following problem has been encountered:</p>
</c:when>

<c:when test='${exception == "other"}'>
<p>The following error has been encountered:</p>
</c:when>

<c:otherwise>
<p>The following condition has been encountered:</p>
</c:otherwise>
</c:choose>

<p><s:property value="uiMessage"/></p> 

<s:if test="hasActionMessages()">
   <div class="help-block actionmessage">
      <s:actionmessage/>
   </div>
</s:if>

</div>

<div class="desktop-spacer visible-desktop">&nbsp;</div>

<div id="navigationLarge" class="desktop-nav hidden-phone">
	<div id="backLarge" class="hidden">
	<a tabindex="26" class="btn-navbar-left" href="<s:property value="#session['previousAction']" />"><div class="navbar-link btn btn-primary btn-large"  onclick="javascript:navunhook();" role="button">
		<small>Back</small>
	</div></a>
	</div>
	
	<div id="continueLarge">
	<a id="returnlink" tabindex="25" class="btn-navbar-right"><div class="navbar-link btn btn-primary btn-large">
		<div id="returntext" role="button"><b>Continue</b></div>
	</div></a>
	</div>
</div>



</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->
