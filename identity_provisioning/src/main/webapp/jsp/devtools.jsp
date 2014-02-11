<%@ taglib prefix="s" uri="/struts-tags" %>

<noscript> <!-- </noscript>

<div id="devtools" class="well well-small center <s:if test='%{#application["deployed"] == "Y"}'>hidden</s:if>" style="max-width: 600px; margin-top: 10px;"><p>Developer Tools</p>

<button tabindex="-1" class="btn" id="btnvalidation" style="width: 140px;" onclick="javascript:navunhook();setValidation();">Loading...</button>
<button tabindex="-1" class="btn" id="btndebug" style="width: 140px;" onclick="javascript:navunhook();setDebug();">Loading...</button>
<button tabindex="-1" class="btn" id="btncookies" style="width: 140px;" onclick="javascript:navunhook();deleteCookies();">Loading...</button>
<br><br>
Session ID: <s:property value="#session['short.session.id']"/> <a href="invalidate.jsp">(invalidate)</a>
</div>

<div id="cookiemessage" class="center" style="max-width: 100%;"></div>
<div id="validationmessage" class="center" style="max-width: 100%;"></div>

<div id="debugdata" class="center" style="max-width: 100%;">
<s:if test="%{#session.debug == 'enabled'}">
<s:debug/>
</s:if>
</div>

<noscript> --> </noscript>