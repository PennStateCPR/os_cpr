<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- UPDATE PROGRESS -->
<script type="text/javascript">document.getElementById('progressbar').style.width="1%"</script>

<div class="container">
<div class="datacard">

<div class="title">
<div class=" div-inline pull-left" style="width: 2%">&nbsp;</div>
<div id="titletext" class="div-inline" style="width: 90%"><script>document.write("<s:property value="#application['ui.welcome.header']"/>")</script><noscript> Cookies and JavaScript required </noscript></div>
<div id="helplink" class="div-inline invisible" style="width: 2%; padding-top: 2px; margin-right: 2px;"><a class="help" onclick="showhelpcontent('welcome', this, event, '200px')" onMouseover="showhelpcontent('welcome', this, event, '200px')" onMouseout="delayhidetip()"><i class="icon-question-sign icon-white"></i></a>
<script type="text/javascript">
document.getElementById("helplink").className = document.getElementById("helplink").className.replace( /(?:^|\s)invisible(?!\S)/g , '' );
</script>
</div>
</div>

<div id="ncjs" class="center">
<noscript>
<p>Please be sure cookies and JavaScript are enabled to proceed.</p>
</noscript>
</div>

<div id="datacardcontent" class="dataform invisible">

<p><s:property value="#session['rac.raMessage']"/></p>

<s:form action="/welcome" id="welcome" method="post" cssClass="form-inline" theme="simple">
<s:hidden name="control" id="control" value="" />
<s:hidden name="btnsubmit" id="btnsubmit" value="" />
<s:token />
</s:form>
</div>

<s:if test="hasActionMessages()">
   <div class="help-block actionmessage">
      <s:actionmessage/>
   </div>
</s:if>

<div class="desktop-spacer visible-desktop">&nbsp;</div>

<!-- NAVIGATION (Large Screen) -->    
<jsp:include page="navigationLarge.jsp" />

<script type="text/javascript">
document.getElementById('backLarge').style.visibility="hidden";
document.getElementById('continuetextLarge').innerHTML="<b>Begin</b>";
</script>

</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->