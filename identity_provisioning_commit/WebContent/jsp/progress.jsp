<%@ taglib prefix="s" uri="/struts-tags" %>

<div id="progresscontainer" class="center hidden-phone" style="max-width: 600px; margin-top: 10px;">

<script type="text/javascript">
var pageTotal = <s:property value="#session['rac.screenTotal']"/>;
var pageCount = <s:property value="#session['rac.screenNumber']"/>;

for (var i = 0; i < pageTotal; i++) {
  if (i < pageCount) {
	document.write('<div class="div-inline dots-on"></div>');
  }
  else {
	document.write('<div class="div-inline dots-off"></div>');
  }
}
</script>

</div>
