<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>The CommIT Project: Privacy Policy</title>
<jsp:include page="commitHead.jsp" />
</head>
<body class="page legalname-page">
	<!--HEADER-->
	<jsp:include page="commitHeader.jsp" />

	<!--DATACARD-->
	<jsp:include page="commitDataCardPrivacyPolicy.jsp" />

	<!--FOOTER-->
	<jsp:include page="commitFooter.jsp" />

	<!--JAVASCRIPT DEPENDENCIES-->
	<jsp:include page="commitLibs.jsp" />

	<script type="text/javascript">
			document.getElementById('backButton').style.visibility = "hidden";
			document.getElementById('continueButton').style.visibility = "hidden";
		</script>

	<script type="text/javascript">
		function toggle(element) {
			//define the sender
			var sender = element.id;
			//initialize the checkbox
			var checkbox = document.forms[0].elements[0];
			if (sender == 'checkboxcontainer') {
				//toggle the checkbox
				if (checkbox.checked == true) {
					document.getElementById('continueButton').style.visibility = "visible";
					document.getElementById('continueButton').style.visibility = "visible";
					document.getElementById('btnsubmit').value = ".";
				} else {
					document.getElementById('continueButton').style.visibility = "hidden";
					document.getElementById('continueButton').style.visibility = "hidden";
					document.getElementById('btnsubmit').value = "";
				}
			}

			if (sender != 'checkboxcontainer') {
				//toggle the checkbox
				if (checkbox.checked == true) {
					document.getElementById('continueButton').style.visibility = "visible";
					document.getElementById('continueButton').style.visibility = "visible";
					document.getElementById('btnsubmit').value = ".";
				} else {
					document.getElementById('continueButton').style.visibility = "hidden";
					document.getElementById('continueButton').style.visibility = "hidden";
					document.getElementById('btnsubmit').value = "";
				}
			}
		}
	</script>

</body>
</html>