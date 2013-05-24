//DEVELOPER TOOLS SETTINGS
try {
	
	if (document.getElementById('debug')) {
		toggleDebug('debug');
	}
	
	document.getElementById("btncookies").innerHTML = "Delete Cookies";
	
	var ONorOFF = "";
	
	if (getCookie('validation') == "OFF") {
		ONorOFF = "OFF";
		document.getElementById("btnvalidation").innerHTML = "Validation is " + ONorOFF;
		$("#btnvalidation").attr('class', 'btn btn-danger');
	}
	else {
		ONorOFF = "ON";
		document.getElementById("btnvalidation").innerHTML = "Validation is " + ONorOFF;
		$("#btnvalidation").attr('class', 'btn btn-success');
	}
	
	if ((getCookie('debug') == "ON") || (document.getElementById('debug'))) {
		ONorOFF = "ON";
		document.getElementById("btndebug").innerHTML = "Debugging is " + ONorOFF;
		$("#btndebug").attr('class', 'btn btn-success');
	}
	else {
		ONorOFF = "OFF";
		document.getElementById("btndebug").innerHTML = "Debugging is " + ONorOFF;
		$("#btndebug").attr('class', 'btn btn-danger');
	}
}
catch (err) {
	//alert("Error setting dev mode settings!");
}

//Help links and content
var tipwidth='200px' //default tooltip width
var disappeardelay=50  //tooltip disappear speed onMouseout (in miliseconds)
var vertical_offset="0px" //horizontal offset of tooltip from anchor link
var horizontal_offset="-3px" //horizontal offset of tooltip from anchor link

var ie4=document.all
var ns6=document.getElementById&&!document.all

if (ie4||ns6)
document.write('<div id="helpcontent" style="visibility:hidden;width:'+tipwidth+';" ></div>')

function getposOffset(what, offsettype){
var totaloffset=(offsettype=="left")? what.offsetLeft : what.offsetTop;
var parentEl=what.offsetParent;
while (parentEl!=null){
totaloffset=(offsettype=="left")? totaloffset+parentEl.offsetLeft : totaloffset+parentEl.offsetTop;
parentEl=parentEl.offsetParent;
}
return totaloffset;
}

function showhide(obj, e, visible, hidden, tipwidth){
if (ie4||ns6)
dropmenuobj.style.left=dropmenuobj.style.top=-500
if (tipwidth!=""){
dropmenuobj.widthobj=dropmenuobj.style
dropmenuobj.widthobj.width=tipwidth
}
if (e.type=="click" && obj.visibility==hidden || e.type=="mouseover")
obj.visibility=visible
else if (e.type=="click")
obj.visibility=hidden
}

function iecompattest(){
return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function clearbrowseredge(obj, whichedge){
var edgeoffset=(whichedge=="rightedge")? parseInt(horizontal_offset)*-1 : parseInt(vertical_offset)*-1
if (whichedge=="rightedge"){
var windowedge=ie4 && !window.opera? iecompattest().scrollLeft+iecompattest().clientWidth-15 : window.pageXOffset+window.innerWidth-15
dropmenuobj.contentmeasure=dropmenuobj.offsetWidth
if (windowedge-dropmenuobj.x < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure-obj.offsetWidth
}
else{
var windowedge=ie4 && !window.opera? iecompattest().scrollTop+iecompattest().clientHeight-15 : window.pageYOffset+window.innerHeight-18
dropmenuobj.contentmeasure=dropmenuobj.offsetHeight
if (windowedge-dropmenuobj.y < dropmenuobj.contentmeasure)
edgeoffset=dropmenuobj.contentmeasure+obj.offsetHeight
}
return edgeoffset
}

function showhelpcontent(helptext, obj, e, tipwidth){
if (window.event) event.cancelBubble=true
else if (e.stopPropagation) e.stopPropagation()
clearhidetip()
dropmenuobj=document.getElementById? document.getElementById("helpcontent") : helpcontent
thesitename = document.getElementById('sitename').innerHTML;
thefamiliarname = document.getElementById('familiarname').innerHTML;
theformalname = document.getElementById('formalname').innerHTML;
thenickname = document.getElementById('nickname').innerHTML;

if (ie4||ns6){
showhide(dropmenuobj.style, e, "visible", "hidden", tipwidth)
dropmenuobj.x=getposOffset(obj, "left")
dropmenuobj.y=getposOffset(obj, "top")
dropmenuobj.style.left=dropmenuobj.x-clearbrowseredge(obj, "rightedge")+"px"
dropmenuobj.style.top=dropmenuobj.y-clearbrowseredge(obj, "bottomedge")+obj.offsetHeight+"px"
}

switch(helptext) {

case "welcome":
 dropmenuobj.innerHTML= thesitename + ' is a tool to help you get digitally connected to ' + thefamiliarname;
break;

case "dataaccuracy":
 dropmenuobj.innerHTML='Data accuracy is important. You must agree to the ' +thesitename + ' Data Accuracy Statement before you can proceed.';
break;

case "name":
 dropmenuobj.innerHTML='Please enter your full, legally-given name. Typically, this is the name that is given to you at birth and/or recognized by a government or other legal entity, and appears on a birth certificate or other government issued docments such as a passport. It may also include a spouse\'s or partner\'s last name. Enter your name information and click Continue.';
break;

case "formername":
 dropmenuobj.innerHTML='If your name has changed since you attended high school and/or any postsecondary institution, please enter your former name here.';
break;

case "address":
 dropmenuobj.innerHTML='Please enter your current postal/surface mailing address. You must first specify the country, then include your street address and other relevant information such as an apartment or unit number, the city, state/province, and the postal/zip code. If you use an APO/FPO military mailing address, indicate United States as the country where you are currently living and provide that address.';
break;

case "previousaddress":
 dropmenuobj.innerHTML='If your home address has changed since you attended high school and/or any postsecondary institution, please enter your previous address here.';
break;

case "contact":
 dropmenuobj.innerHTML='Please provide your contact information as requested below.';
break;

case "personalinfo":
 dropmenuobj.innerHTML='Please provide your personal information as requested below. NOTE: Your birth month and birth day are REQUIRED fields.';
break;

case "idinfo":
 dropmenuobj.innerHTML='Please provide your identity information as requested below.';
break;

case "verify":
dropmenuobj.innerHTML='Please verify all of the information you have entered. If you need to make changes or corrections, click the (edit) link in the appropriate section.';
break;

case "policy":
 dropmenuobj.innerHTML='You must agree to the Policy Statement before continuing.';
break;

case "securityquestions":
 dropmenuobj.innerHTML='Here you will establish a set of three security questions for managing your password. You will need to answer these questions should you ever forget or need to reset your password, as they provide a method for confirming your identity. Select a question from each question\'s pull-down menu and establish an answer in the answer field for each question. All three questions and answers must be established.';
break;

case "securityanswers":
 dropmenuobj.innerHTML='You must answer this security question correctly to proceed. If you do not answer the security questions correctly, you will be required to contact customer service to regain access to your account.';
break;

case "password":
 dropmenuobj.innerHTML='Please choose a password for your account.';
break;

// UNUSED
//case "success":
// dropmenuobj.innerHTML='Welcome to Penn State!';
//break;

case "matchfound":
 dropmenuobj.innerHTML='Q and A Help Content';
break;

case "isthisyou":
 dropmenuobj.innerHTML='Is This You? Help Content';
break;

//Field-specific help content
case "emailAddress":
 dropmenuobj.innerHTML='<b>Example:</b> you@psu.edu<br><br>Please enter your primary e-mail address in this field. You will receive communication from ' +thesitename + ' at this e-mail address.';
break;

case "phoneNumber":
 dropmenuobj.innerHTML='<b>Examples: </b><br></br>(555) 234-5678<br></br>5552345678<br></br>+1 555 234 5678<br></br>+61 1 2345 6789<br><br>Be sure to include the country code if the telephone number is based outside of the United States.';
break;

// UNUSED
//case "extension":
// dropmenuobj.innerHTML='<b>Example:</b> 2345678';
//break;

// UNUSED
//case "internationalNumber":
// dropmenuobj.innerHTML='Please check this checkbox if your telephone number is based outside the United States';
//break;

case "DOB":
 dropmenuobj.innerHTML='Your date of birth is used to help uniquely identify you in case there is someone else who shares the same name and birth date. At a minimum, the birth month and birth day are required.';
break;

case "ssn":
 dropmenuobj.innerHTML='Click the \'Display\' checkbox to see the numbers as they are entered into the Social Security Number field. <br><br>Additional information on the use of your SSN is available in University Policy AD19: Use of '+thefamiliarname+' Identification Number and Social Security Number';
break;

// PENN STATE USE
case "psuId":
 dropmenuobj.innerHTML='A Penn State Identification Number or PSU ID is assigned to individuals and is used as the primary identifier in Penn State\'s administrative and academic systems. The PSU ID is a nine digit number, beginning with 9 in the following format: 9-XXXX-XXXX<br></br>Additional information is available in University Policy AD19: Use of Penn State Identification Number and Social Security Number';
break;

case "userId":
 dropmenuobj.innerHTML='<b>Example:</b> xyz1234';
break;

// UNUSED
//case "endpage":
// dropmenuobj.innerHTML='<b>Session Ended Help Content</b>';
//break;

// Pass help content through from each help link verbatim
default: {
 dropmenuobj.innerHTML=helptext;
}
}
}

function hidetip(e){
if (typeof dropmenuobj!="undefined"){
if (ie4||ns6)
dropmenuobj.style.visibility="hidden"
}
}

function delayhidetip(){
if (ie4||ns6)
delayhide=setTimeout("hidetip()",disappeardelay)
}

function clearhidetip(){
if (typeof delayhide!="undefined")
clearTimeout(delayhide)
}

//Handle Cookies
function deleteCookies() {
	var message = "Cookies successfully deleted";
	deleteCookie('debug');
	deleteCookie('referrer');
	deleteCookie('validation');
	$("#cookiemessage").attr('class', 'alert alert-block');
	$("#cookiemessage").html( "<b>"+ message +"</b> - Reloading..." );
	setTimeout(function() {window.location.reload(true);}, 1000);
}

function hideAddressBar() //hides address bar on handheld devices after page load
{
	if(!window.location.hash)
	{
		if(document.height < window.outerHeight)
		{
			//document.body.style.height = (window.outerHeight + 50) + 'px';
		}
		
		setTimeout( function(){ window.scrollTo(0, 1); }, 50 );
	}
}

//trap for IE to ignore unsupported function
try {
window.addEventListener("load", function(){ if(!window.pageYOffset){ hideAddressBar(); } } );
window.addEventListener("orientationchange", hideAddressBar );
}
catch (err) {
}

function notempty(value) {
	if (value != '' && value != null && value != 'undefined' && value.length != 0) {
		return true;
	}
	else {
		return false;
	}
}

function toggle(element) {
	//define the sender
	var sender = element.id;
	//initialize the checkbox
	var checkbox = document.forms[0].elements[0];
	//alert('Sender '+ sender);
	if (sender == 'checkboxcontainer') {
		//toggle the checkbox
		if (checkbox.checked == true) {
			checkbox.checked = false;
			document.getElementById('continueLarge').style.visibility="hidden";
			document.getElementById('continueSmall').style.visibility="hidden";
		}
		else {
			checkbox.checked = true;
			document.getElementById('continueLarge').style.visibility="visible";
			document.getElementById('continueSmall').style.visibility="visible";
		}
	}
	
	if (sender != 'checkboxcontainer') {
		//toggle the checkbox
		if (checkbox.checked == true) {
			checkbox.checked = false;
			document.getElementById('continueLarge').style.visibility="hidden";
			document.getElementById('continueSmall').style.visibility="hidden";
		}
		else {
			checkbox.checked = true;
			document.getElementById('continueLarge').style.visibility="visible";
			document.getElementById('continueSmall').style.visibility="visible";
		}
	}
}

function stateorprovince(country) {
	if (country != "USA") {
		$('#thestate').addClass('hidden');
		document.getElementById('state').selectedIndex=0;
		$('#theprovince').removeClass('hidden');
	}
	else {
		$('#thestate').removeClass('hidden');
		document.getElementById('province').value=null;
		$('#theprovince').addClass('hidden');
	}
}

function PstateorPprovince(country) {
	if (country != "USA") {
		$('#prevstate').addClass('hidden');
		document.getElementById('prevstate').selectedIndex=0;
		$('#prevprovince').removeClass('hidden');
	}
	else {
		$('#prevstate').removeClass('hidden');
		document.getElementById('prevprovince').value=null;
		$('#prevprovince').addClass('hidden');
	}
}

function setValidation() {
	//Dev mode for toggling front end validation
	var message = "";
	var getDebug = getCookie('debug');
	if (getCookie('validation') == 'OFF')
	{
		setCookie('validation', 'ON');
		toggleValidation('off');
		message = 'Validation is now ' + getCookie('validation') + '!';
		$("#validationmessage").attr('class', 'alert alert-success');
		document.getElementById("btnvalidation").innerHTML = "Validation is ON";
		$("#btnvalidation").attr('class', 'btn btn-success');
	}
	else {
		setCookie('validation', 'OFF');
		toggleValidation('on');
		message = 'Validation is now ' + getCookie('validation') + '!';
		$("#validationmessage").attr('class', 'alert alert-error');
		document.getElementById("btnvalidation").innerHTML = "Validation is OFF";
		$("#btnvalidation").attr('class', 'btn btn-danger');
	}
	$("#validationmessage").fadeIn('fast');
	$("#validationmessage").html( "<b>"+ message +"</b>");
	setTimeout(function() {$("#validationmessage").fadeOut('slow');}, 2000);
}


function toggleValidation(ONorOFF) {
	//Dev mode for toggling back end validation
	var thedata = "";
	var getDebug = getCookie('debug');
	var validation = ONorOFF;
	var theurl = $.ajax( "debug?debugSkipValidation=" + validation )
    .done(function( html ) {
			$("#validationmessage").html(html);
		  })
    .fail(function() {
		  //do nothing
		  })
    .always(function() {
			if (getDebug == validation) {
				setDebug('toggle');
			}
	});
}


function setDebug(sender) {
	//Dev mode for toggling front end Debug mode
	var thedata = "";
	var theurl = $.ajax( "debug" )
    .done(function( html ) {
		  thedata = html;
		  if (thedata.match("enabled")){
		  setCookie('debug', 'ON');
		  document.getElementById("btndebug").innerHTML = "Debugging is ON";
		  $("#btndebug").attr('class', 'btn btn-success');
			  if (sender != 'toggle') {
			  $("#debugdata").html( html + "- Reloading...");
			  $("#debugdata").attr('class', 'alert alert-success');
			  }
		  }
		  else {
		  setCookie('debug', 'OFF');
		  document.getElementById("btndebug").innerHTML = "Debugging is OFF";
		  $("#btndebug").attr('class', 'btn btn-danger');
			  if (sender != 'toggle') {
			  $("#debugdata").html( html + "- Reloading...");
			  $("#debugdata").attr('class', 'alert alert-error');
			  }
		  }
		  })
    .fail(function() {
		  //do nothing
		  })
    .always(function() {
			if (sender != 'toggle') {
			setTimeout(function() {window.location.reload(true);}, 2000);
			}
			});
}


function validateforms() {
	
	var theform = document.forms[0].id;
	var validated = false;
	var needinput = false;
	
	if (getCookie('validation') != 'OFF') {
		
		switch(theform) {
			case "dataaccuracy":
				setCookie('referrer', theform);
				//if the user agreed to the data accuracy statement...
				if (document.forms[0].elements[0].checked == true) {
					validated = true;
				}
				break;
				
			case "legalname":
				setCookie('referrer', theform);
				needinput = false;
				//check the 'first name' field
				if (notempty(document.getElementById('firstName').value)) {
					document.getElementById('thefirstnamehelp').innerHTML = '&nbsp;';
					$('#thefirstname').removeClass('error');
					$('#thefirstnamehelp').addClass('hidden');
				}
				else {
					//mark the field as required
					document.getElementById('thefirstnamehelp').innerHTML = 'First/Given Name is required';
					$('#thefirstname').addClass('error');
					$('#thefirstnamehelp').removeClass('hidden');
					needinput = true;
				}
				//check the 'last name' field
				if (notempty(document.getElementById('lastName').value)) {
					document.getElementById('thelastnamehelp').innerHTML = '&nbsp;';
					$('#thelastname').removeClass('error');
					$('#thelastnamehelp').addClass('hidden');
				}
				else {
					//mark the field as required
					document.getElementById('thelastnamehelp').innerHTML = 'Last/Family Name is required';
					$('#thelastname').addClass('error');
					$('#thelastnamehelp').removeClass('hidden');
					needinput = true;
				}
				if (needinput == false) {
					validated = true;
				}
				break;
				
			case "address":
				setCookie('referrer', theform);
				needinput = false;
				//check the 'addressLine1' field
				if (notempty(document.getElementById('addressLine1').value)) {
					document.getElementById('theaddress1help').innerHTML = '&nbsp;';
					$('#theaddress1').removeClass('error');
					$('#theaddress1help').addClass('hidden');
				}
				else {
					//mark the field as required
					document.getElementById('theaddress1help').innerHTML = 'Address is required';
					$('#theaddress1').addClass('error');
					$('#theaddress1help').removeClass('hidden');
					needinput = true;
				}
				//check the 'city' field
				if (notempty(document.getElementById('city').value)) {
					document.getElementById('thecityhelp').innerHTML = '&nbsp;';
					$('#thecity').removeClass('error');
					$('#thecityhelp').addClass('hidden');
				}
				else {
					//mark the field as required
					document.getElementById('thecityhelp').innerHTML = 'City is required';
					$('#thecity').addClass('error');
					$('#thecityhelp').removeClass('hidden');
					needinput = true;
				}
				//check the 'state' or 'province' fields
				country = document.getElementById('country').value;
				//reset validation warnings
				document.getElementById('thestatehelp').innerHTML = '&nbsp;';
				$('#thestate').removeClass('error');
				$('#thestatehelp').addClass('hidden');
				document.getElementById('theprovincehelp').innerHTML = '&nbsp;';
				$('#theprovince').removeClass('error');
				$('#theprovincehelp').addClass('hidden');
				//revalidate
				switch(country) {
					case 'USA':
						//validate the 'state' field
						if (document.getElementById('state').selectedIndex != 0) {
							document.getElementById('thestatehelp').innerHTML = '&nbsp;';
							$('#thestate').removeClass('error');
							$('#thestatehelp').addClass('hidden');
						}
						else {
							//mark the field as required
							document.getElementById('thestatehelp').innerHTML = 'State selection is required';
							$('#thestate').addClass('error');
							$('#thestatehelp').removeClass('hidden');
							needinput = true;
						}
						break;
					default:
						//validate the 'province' field
						if (notempty(document.getElementById('province').value)) {
							document.getElementById('theprovincehelp').innerHTML = '&nbsp;';
							$('#theprovince').removeClass('error');
							$('#theprovincehelp').addClass('hidden');
						}
						else {
							//mark the field as required
							document.getElementById('theprovincehelp').innerHTML = 'Province is required';
							$('#theprovince').addClass('error');
							$('#theprovincehelp').removeClass('hidden');
							needinput = true;
						}
				}
				//check the 'postalCode' field
				if (notempty(document.getElementById('postalCode').value)) {
					document.getElementById('thepostalcodehelp').innerHTML = '&nbsp;';
					$('#thepostalcode').removeClass('error');
					$('#thepostalcodehelp').addClass('hidden');
				}
				else {
					//mark the field as required
					document.getElementById('thepostalcodehelp').innerHTML = 'Postal Code is required';
					$('#thepostalcode').addClass('error');
					$('#thepostalcodehelp').removeClass('hidden');
					needinput = true;
				}
				if (needinput == false) {
					validated = true;
				}
				break;
				
			case "contactinfo":
				setCookie('referrer', theform);
				needinput = false;
				//check the 'e-mail address' field
				if (notempty(document.getElementById('email').value)) {
					if (emailValidate()) {
						document.getElementById('theemailhelp').innerHTML = '&nbsp;';
						$('#contactemail').removeClass('error');
						$('#theemailhelp').addClass('hidden');
					}
					else {
						document.getElementById('theemailhelp').innerHTML = 'Invalid E-mail address';
						$('#contactemail').addClass('error');
						$('#theemailhelp').removeClass('hidden');
						needinput = true;
					}
				}
				else {
					//mark the field as required
					document.getElementById('theemailhelp').innerHTML = 'E-mail address is required';
					$('#contactemail').addClass('error');
					$('#theemailhelp').removeClass('hidden');
					needinput = true;
				}
				
				//check the 'telephone number' field
				if (notempty(document.getElementById('phoneNumber').value)) {
					if (phoneValidate()) {
						document.getElementById('thephonenumberhelp').innerHTML = '&nbsp;';
						$('#contactphone').removeClass('error');
						$('#thephonenumberhelp').addClass('hidden');
					}
					else {
						document.getElementById('thephonenumberhelp').innerHTML = 'Invalid or international telephone number';
						$('#contactphone').addClass('error');
						$('#thephonenumberhelp').removeClass('hidden');
						needinput = true;
					}
				}
				else {
					//mark the field as required
					document.getElementById('thephonenumberhelp').innerHTML = 'Telephone number is required';
					$('#contactphone').addClass('error');
					$('#thephonenumberhelp').removeClass('hidden');
					needinput = true;
				}
				
				if (needinput == false) {
					var mailto = document.getElementById('email').value;
					var msg = document.getElementById('themessage').innerHTML;
					var newmsg = msg.replace('&lt;EMAIL_ADDRESS&gt;', '<br><br>&nbsp;<b>'+mailto+'</b>&nbsp;<br><br>');
					document.getElementById('themessage').innerHTML = newmsg;
					$('#emailConfirm').modal('show');
					document.body.style.zoom = "100%";
					focusOn('yes');
				}
				break;
				
			case "personalinfo":
				setCookie('referrer', theform);
				needinput = false;
				datemismatch = false;
				dob =	document.getElementById('birthDay').value + "/" +
						document.getElementById('birthMonth').value + "/" +
						document.getElementById('birthYear').value;
				document.getElementById('theBirthhelp').innerHTML = '';
				startyear = minYear(document.getElementById('birthYear').value);
				endyear = maxYear(document.getElementById('birthYear').value);
				
				if ((document.getElementById('birthMonth').value <= '9') && (document.getElementById('birthMonth').value.length == 1)) {
				var currentvalue = document.getElementById('birthMonth').value;
				document.getElementById('birthMonth').value = '0' + currentvalue;
				}
				
				if ((document.getElementById('birthDay').value <= '9') && (document.getElementById('birthMonth').value.length == 1)) {
				var currentvalue = document.getElementById('birthMonth').value;
				document.getElementById('birthMonth').value = '0' + currentvalue;
				}
				
				//check the 'birthMonth' field
				if (notempty(document.getElementById('birthMonth').value)) {
						if ((document.getElementById('birthMonth').value <1) || (document.getElementById('birthMonth').value >12)) {
							document.getElementById('theBirthhelp').innerHTML = 'Birth month is invalid<br>';
							document.getElementById('birthMonth').style.color = '#FF0000';
							document.getElementById('birthMonth').style.borderColor = '#FF0000';
							needinput = true;
						}
						else {
							document.getElementById('theBirthhelp').innerHTML = '&nbsp;';
							document.getElementById('birthMonth').style.color = '';
							document.getElementById('birthMonth').style.borderColor = '';
						}
				}
				else {
					//mark the field as required
						document.getElementById('theBirthhelp').innerHTML = 'Birth month is required<br>';
						document.getElementById('birthMonth').style.color = '#FF0000';
						document.getElementById('birthMonth').style.borderColor = '#FF0000';
						needinput = true;
				}
				
				//check the 'birthDay' field
				if (notempty(document.getElementById('birthDay').value)) {					
					if ((document.getElementById('birthMonth').value == '02') && (document.getElementById('birthDay').value >= 30)) {
						datemismatch = true;
					}
					if ((document.getElementById('birthMonth').value == '04') && (document.getElementById('birthDay').value > 30)) {
						datemismatch = true;
					}
					if ((document.getElementById('birthMonth').value == '06') && (document.getElementById('birthDay').value > 30)) {
						datemismatch = true;
					}
					if ((document.getElementById('birthMonth').value == '09') && (document.getElementById('birthDay').value > 30)) {
						datemismatch = true;
					}
					if ((document.getElementById('birthMonth').value == '11') && (document.getElementById('birthDay').value > 30)) {
						datemismatch = true;
					}
					if ((document.getElementById('birthDay').value <1) || (document.getElementById('birthDay').value >31) || (datemismatch == true)) {
						document.getElementById('theBirthhelp').innerHTML = document.getElementById('theBirthhelp').innerHTML + 'Birth day is invalid<br>';
						document.getElementById('birthDay').style.color = '#FF0000';
						document.getElementById('birthDay').style.borderColor = '#FF0000';
						needinput = true;
					}
					else {
						document.getElementById('birthDay').style.color = '';
						document.getElementById('birthDay').style.borderColor = '';
					}
				}
				else {
					//mark the field as required
						document.getElementById('theBirthhelp').innerHTML = document.getElementById('theBirthhelp').innerHTML + 'Birth day is required<br>';
						document.getElementById('birthDay').style.color = '#FF0000';
						document.getElementById('birthDay').style.borderColor = '#FF0000';
						needinput = true;
				}
				
				//check the 'birthYear' field
				if (notempty(document.getElementById('birthYear').value)) {
					if (isValidDate(dob)) {
						if ((document.getElementById('birthYear').value <startyear) || (document.getElementById('birthYear').value >endyear)) {
							document.getElementById('theBirthhelp').innerHTML = document.getElementById('theBirthhelp').innerHTML + 'Birth year is invalid<br>';
							document.getElementById('birthYear').style.color = '#FF0000';
							document.getElementById('birthYear').style.borderColor = '#FF0000';
							needinput = true;
						}
						else {
							document.getElementById('birthYear').style.color = '';
							document.getElementById('birthYear').style.borderColor = '';
						}
					}
					else {
					document.getElementById('theBirthhelp').innerHTML = "Date of birth is invalid";
						needinput = true;
					}
				}
				else {
					//mark the field as required
				}

				if (needinput == true) {
				document.getElementById('birthLabelMM').style.color = '#FF0000';
				document.getElementById('theBirthhelp').style.color = '#FF0000';
				$('#theBirthhelp').removeClass('hidden');
				}
								
				if (needinput == false) {
				document.getElementById('birthLabelMM').style.color = '';
				document.getElementById('theBirthhelp').style.color = '';
				$('#theBirthhelp').addClass('hidden');
				validated = true;
				
				}
				break;
				
			case "securityquestions":
				setCookie('referrer', theform);
				//assume all is valid unless noted otherwise...
				var isvalid = true;
				//validate that all 3 questions were selected
				if (document.getElementById("securityQuestion1").selectedIndex == 0 ||
					document.getElementById("securityQuestion2").selectedIndex == 0 ||
					document.getElementById("securityQuestion3").selectedIndex == 0) {
					isvalid = false;
				}
				//validate that all 3 questions were answered
				if (document.getElementById("securityQuestion1Answer").value == "" ||
					document.getElementById("securityQuestion2Answer").value == "" ||
					document.getElementById("securityQuestion3Answer").value == "") {
					isvalid = false;
				}
				if (isvalid) {
					document.getElementById('btnsubmit').value=".";
					validated = true;
				}
				else {
					//Warn the user and accept an 'opt-out' response
					$('#optOut').modal('show');
					focusOn('no');
				}
				break;
				
			case "matchfound":
				setCookie('referrer', theform);
				var validated = true;
				document.getElementById('btnsubmit').value=".";
				break;
			
			case "question1":
				setCookie('referrer', theform);
				var validated = true;
				document.getElementById('btnsubmit').value=".";
				break;
				
			case "question2":
				setCookie('referrer', theform);
				var validated = true;
				document.getElementById('btnsubmit').value=".";
				break;
			case "question3":
				setCookie('referrer', theform);
				var validated = true;
				document.getElementById('btnsubmit').value=".";
				break;
					
			case "setpassword":
				setCookie('referrer', theform);
				needinput = false;
				if (passwordValidate()) {
					needinput = false;
				}
				else {
					needinput = true;
					focusOn('password');
				}
				
				if (needinput == false) {
				document.getElementById('btnsubmit').value=".";
				validated = true;
				}
				break;
				
			default:
				setCookie('referrer', theform);
				//no validation required
				document.getElementById('btnsubmit').value=".";
				validated = true;
		}
		
		if (validated == true) {
			document.getElementById('btnsubmit').value=".";
			navunhook();
			document.getElementById(theform).submit();
		}
		else {
			//FAILED validation!
		}
		
	}
	else {
		//bypass validation (dev mode)
		setCookie('referrer', theform);
		document.getElementById('btnsubmit').value=".";
		navunhook();
		document.getElementById(theform).submit();
	}
}

function minYear(theyear) {
var currentDate = new Date();
var day = currentDate.getDate();
var month = currentDate.getMonth() + 1;
var year = currentDate.getFullYear() - 150;
theyear = year;
return theyear;
}

function maxYear(theyear) {
var currentDate = new Date();
var day = currentDate.getDate();
var month = currentDate.getMonth() + 1;
var year = currentDate.getFullYear();
theyear = year;
return theyear;
}

function isValidDate(thedate) {
    s = thedate.split('/');
    d = new Date(+s[2], s[1]-1, +s[0]);
    if (Object.prototype.toString.call(d) === "[object Date]") {
        if (!isNaN(d.getTime()) && d.getDate() == s[0] && 
            d.getMonth() == (s[1] - 1)) {
            return true;
        }
    }
    return false;
}

function submitSecurityQuestions() {
	document.getElementById('btnsubmit').value=".";
	navunhook();
	setTimeout(function() {document.getElementById('securityquestions').submit();}, 500);
}

function submitContactInfo() {
	document.getElementById('btnsubmit').value=".";
	navunhook();
	setTimeout(function() {document.getElementById('contactinfo').submit();}, 500);
}

//field-level validation functions
function emailValidate() {
	var email = document.getElementById('email').value;
	var emailRegString = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
	var emailReg = new RegExp(emailRegString);
	return emailReg.test(email);
}

function phoneValidate() {
	var phone = document.getElementById('phoneNumber').value;
	if (document.getElementById('internationalNumber').checked) {
		var intPhoneRegString = "^\\+?(?:[0-9]{1,3}?)([0-9- ]{6,15})[0-9 ]$";
		var intPhoneReg = new RegExp(intPhoneRegString);
		return intPhoneReg.test(phone);
	}
	var usPhoneRegString = "^(\\+[0]{1,2}?[1]{1})?[-. ]?\\(?([0-9]{3})\\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$";
	var usPhoneReg = new RegExp(usPhoneRegString);
	return usPhoneReg.test(phone);
}

//HANDLE COOKIE DATA
//var expDays = 1; //add days to expiration time
var exp = new Date();
exp.setTime(exp.getTime() + 1800000) // 30 minutes or use days instead: (expDays*24*60*60*1000));

function getCookieVal (offset) {
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)
	{
		endstr = document.cookie.length;
	}
	return unescape(document.cookie.substring(offset, endstr));
}

function getCookie(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen) {
		var j = i + alen;
		if (document.cookie.substring(i, j) == arg)
			return getCookieVal (j);
		i = document.cookie.indexOf(" ", i) + 1;
		if (i == 0) break;
	}
	return null;
}

function setCookie(name, value) {
	var argv = setCookie.arguments;
	var argc = setCookie.arguments.length;
	var expires = (argc > 2) ? argv[2] : null;
	var path = (argc > 3) ? argv[3] : null;
	var domain = (argc > 4) ? argv[4] : null;
	var secure = (argc > 5) ? argv[5] : false;
	document.cookie = name + "=" + escape (value) +
	((expires == null) ? "" : ("; expires=" + expires.toGMTString())) +
	((path == null) ? "" : ("; path=" + path)) +
	((domain == null) ? "" : ("; domain=" + domain)) +
	((secure == true) ? "; secure" : "");
}

function deleteCookie(name) {
	var exp = new Date();
	exp.setTime (exp.getTime() - 10);
	var cval = getCookie(name);
	document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}

//set focus on the proper field
function focusOn(what) {
	if (what != null) {
		var whatField = document.getElementById(what).id;
		document.getElementById(whatField).focus();
	}
	else {
		//do nothing...
	}
}

//if back button in focus, enter key will navigate back instead of forward
var focusedElement = null;

$(document).ready(function() {
	$("*").keypress(function(e) {
		var code = (e.keyCode ? e.keyCode : e.which);
		if (code == 13) {
			if (focusedElement === 'goback') {
			navunhook();
			document.location = document.getElementById('goback').href;
			}
			else {
			validateforms();
			return false;
			}
		}
	});
});

function setFocusedElement(name) {
focusedElement = name;
}

//Handle SSN masking/unmasking
jQuery.fn.showPasswordCheckbox = function() {
	return this.each(function () {
					 if((jQuery(this).attr("type") == "password") && (jQuery(this).attr("name") != "undefined")){
					 // Get the name field from "this"
					 var altPasswordFieldName = "alt"+jQuery(this).attr("name");
					 var showPasswordFieldName = "show"+jQuery(this).attr("name");
					 
					 // Markup for text password field
					 var textPasswordMarkup = "<input type=\"text\" id=\""+altPasswordFieldName+"\" name=\""+altPasswordFieldName+"\" />";
					 // Markup for showPassword checkbox
					 var showPasswordMarkup = "&nbsp;<input type=\"checkbox\" id=\""+showPasswordFieldName+"\" name=\""+showPasswordFieldName+"\" /><label for=\""+showPasswordFieldName+"\"><small>&nbsp;Display</small></label>";
					 
					 // Insert the text password field and showPassword field and label
					 jQuery(this).after(textPasswordMarkup+showPasswordMarkup);
					 
					 // Clone attributes from this to #altPassword. Do not include "id" and "type".
					 // Otherwise, the altPassword field may not behave or look like the password field.
					 var attributes = new Array("align","disabled","maxlength","readonly","size","class","dir","lang","style","value","title","xml:lang","onblur","onchange","onclick","ondblclick","onfocus","onmousedown","onmousemove","onmouseout","onmouseover","onmouseup","onkeydown","onkeypress","onkeyup","onselect");
					 for(attribute in attributes){
					 if(jQuery(this).attr(attributes[attribute]) != "undefined"){
					 jQuery("#"+altPasswordFieldName).attr(
														   attributes[attribute],
														   jQuery(this).attr(attributes[attribute])
														   );
					 }
					 }
					 
					 // Initially obscure the text field, until toggled on.
					 // This must come after the attributes or an existing style attribute may override the hide.
					 jQuery("#"+altPasswordFieldName).hide();
					 
					 // Toggle the password and altPassword fields' visibility and values as needed
					 var shufflePasswordFields = function(jqPassword) {
					 
					 jQuery("#"+showPasswordFieldName).change(function(){
															  if(jqPassword.is(':visible')){
															  // hide password field and show text password field with correct value
															  jqPassword.hide();
															  jQuery("#"+altPasswordFieldName).val(jqPassword.val()).show();
															  setTimeout('autohide()',30000);
															  }else{
															  // hide altPassword field and show password field
															  jQuery("#"+altPasswordFieldName).hide();
															  jqPassword.show();
															  //clearTimeout("setTimeout('autohide()',3000);");
															  }
															  
															  });
					 
					 // Keep password value in sync with altPassword value
					 jQuery("#"+altPasswordFieldName).change(function(){
															 jqPassword.val(jQuery("#"+altPasswordFieldName).val());
															 });
					 
					 return jqPassword;
					 }
					 
					 return shufflePasswordFields(jQuery(this));
					 }else
					 {
					 // Just return without change if the type is not "password"
					 return this;
					 }
					 });
	
};

function autohide() {
	var x=document.getElementById('showssn');
	if (x.checked!=true) {
		//		document.getElementById(ad).style.visibility='hidden';
		//		document.getElementById(ma).style.visibility="visible";
	}
	else {
		document.getElementById('ssn').style.display="inline-block";
		document.getElementById('altssn').style.display="none";
		x.checked = false;
	}
}

function togglescale(element) {
	var datacard = element.id;
	//document.getElementById('heading').innerHTML = datacard;
}

function goVerify() {
	if (getCookie('referrer') == 'verify') {
		document.getElementById('backLarge').style.visibility="hidden";
		document.getElementById('backSmall').style.visibility="hidden";
		document.getElementById('continuetextLarge').innerHTML="<b>Verify</b>";
		document.getElementById('continuetextSmall').innerHTML="<b>Verify</b>";
	}
}

function loadVerifData () {
	var gendertext = document.getElementById('thegender').innerHTML;
	var citizenshiptext = document.getElementById('thecitizenship').innerHTML;
	setCookie('referrer','verify');
	if (document.getElementById('Caddress2').innerHTML != '<br>') {
		$('#Caddress2').removeClass('hidden');
	}
	if (document.getElementById('Caddress3').innerHTML != '<br>') {
		$('#Caddress3').removeClass('hidden');
	}
	if (document.getElementById('Faddress2').innerHTML != '<br>') {
		$('#Faddress2').removeClass('hidden');
	}
	if (document.getElementById('Faddress3').innerHTML != '<br>') {
		$('#Faddress3').removeClass('hidden');
	}
	if (document.getElementById('phoneextension').innerHTML != '&nbsp;extension ') {
		$('#phoneextension').removeClass('hidden');
	}
	if (document.getElementById('international').innerHTML == 'true') {
		document.getElementById('international').innerHTML = '(international number)';
		$('#international').removeClass('hidden');
	}
	if (document.getElementById('theSSN').innerHTML != 'SSN: ') {
		$('#theSSN').removeClass('hidden');
		$('#noIDinfo').addClass('hidden');
		document.getElementById('theSSN').innerHTML = 'SSN: *** ** ****'
	}
	if (document.getElementById('thePSUID').innerHTML != 'PSU ID: ') {
		$('#thePSUID').removeClass('hidden');
		$('#noIDinfo').addClass('hidden');
	}
	if (document.getElementById('theUserID').innerHTML != 'User ID: ') {
		$('#theUserID').removeClass('hidden');
		$('#noIDinfo').addClass('hidden');
	}
	if (gendertext != 'Gender:&nbsp;&nbsp;') {
		switch (gendertext) {
			case 'Gender:&nbsp;&nbsp;GENDER_MALE':
				document.getElementById('thegender').innerHTML = 'Gender: Male';
				break;
			case 'Gender:&nbsp;&nbsp;GENDER_FEMALE':
				document.getElementById('thegender').innerHTML = 'Gender: Female';
				break;
			case 'Gender:&nbsp;&nbsp;GENDER_OTHER':
				document.getElementById('thegender').innerHTML = 'Gender: Other';
				break;
			default: {
				document.getElementById('thegender').innerHTML = 'Gender: Not Provided';
			}
		}
	}
	else {
		document.getElementById('thegender').innerHTML = 'Gender: Not Provided';
	}
	citizenshiptext = citizenshiptext.replace(/(\r\n|\n|\r)/gm,"");
	if ($.trim(citizenshiptext) != 'Citizenship:&nbsp;&nbsp;') {
		switch (citizenshiptext) {
			case 'Citizenship:&nbsp;&nbsp;US':
				document.getElementById('thecitizenship').innerHTML = 'Citizenship: U.S. Citizen';
				break;
			case 'Citizenship:&nbsp;&nbsp;US ': //IE bug fix
				document.getElementById('thecitizenship').innerHTML = 'Citizenship: U.S. Citizen';
				break;
			case 'Citizenship:&nbsp;&nbsp;NON-US':
				document.getElementById('thecitizenship').innerHTML = 'Citizenship: Non U.S. Citizen';
				break;
			case 'Citizenship:&nbsp;&nbsp;NON-US ': //IE bug fix
				document.getElementById('thecitizenship').innerHTML = 'Citizenship: Non U.S. Citizen';
				break;
			default: {
				document.getElementById('thecitizenship').innerHTML = 'Citizenship: Not Provided';
			}
		}
	}
	else {
		document.getElementById('thecitizenship').innerHTML = 'Citizenship: Not Provided';
	}
	if (document.getElementById('Paddresslineone').innerHTML != '') {
		$('#Paddress').removeClass('hidden');
		$('#noPaddress').addClass('hidden');
	}
	else {
		$('#Paddress').addClass('hidden');
		$('#noPaddress').removeClass('hidden');
	}
}

function passwordStrength(password)
{
        var desc = new Array();
        desc[0] = "weak";
        desc[1] = "weak";
        desc[2] = "moderate";
        desc[3] = "moderate";
        desc[4] = "strong";
        desc[5] = "strong";
        var score   = 0;

        //if password bigger than 7 give 1 point
        if (password.length > 7) score++;

        //if password has both lower and uppercase characters give 1 point
        if ( ( password.match(/[a-z]/) ) && ( password.match(/[A-Z]/) ) ) score++;

        //if password has at least one number give 1 point
        if (password.match(/\d+/)) score++;

        //if password has at least one special character give 1 point
        if ( password.match(/.[!,@,#,$,%,^,*,?,_,~,-]/) ) score++;

        //if password bigger than 12 give another 1 point
        if (password.length > 12) score++;
		
		switch(score) {
			case 1:
			  document.getElementById("passwordStrength").innerHTML = "weak";
			  document.getElementById("progress").className = "progress progress-danger";
			  document.getElementById("progress-bar").style.width="10%";
			  break;
			case 2:
			  document.getElementById("passwordStrength").innerHTML = "moderate";
  			  document.getElementById("progress").className = "progress progress-warning";
  			  document.getElementById("progress-bar").style.width="30%";
			  break;
			case 3:
			  document.getElementById("passwordStrength").innerHTML = "moderate";
  			  document.getElementById("progress").className = "progress progress-warning";
			  document.getElementById("progress-bar").style.width="50%";
			  break;
			case 4:
			  document.getElementById("passwordStrength").innerHTML = "strong";
  			  document.getElementById("progress").className = "progress progress-success";
			  document.getElementById("progress-bar").style.width="70%";
			  break;
			case 5:
			  document.getElementById("passwordStrength").innerHTML = "strong";
  			  document.getElementById("progress").className = "progress progress-success";
			  document.getElementById("progress-bar").style.width="100%";
			  break;
			default:
			  document.getElementById("passwordStrength").innerHTML = "&nbsp;";
			  document.getElementById("progress").className = "progress progress-danger";
			  document.getElementById("progress-bar").style.width="0%";
			}
}

function passwordValidate() {
	var password = document.getElementById("password").value;
	var verPass = document.getElementById("passwordConfirmed").value;
	var errorMsg = "";
	var containMsg = "";
	var notContainMsg = "";

	//Do they match?
	if (password != verPass) {
		document.getElementById("thepasswordconfirmedhelp").innerHTML = "Passwords Do Not Match";
		$('#thePassword').addClass('error');
		$('#thePasswordConfirmed').addClass('error');
		return false;
	}
	else {
		document.getElementById("thepasswordconfirmedhelp").innerHTML = "&nbsp;";
		$('#thePassword').removeClass('error');
		$('#thePasswordConfirmed').removeClass('error');
	}
	
	//password must be at least 8 characters
	if (password.length < 8) {
		errorMsg = errorMsg.concat(", 8 characters");
	}
	
	//password must contain lowercase character
	if (!( password.match(/[a-z]/))){
		errorMsg = errorMsg.concat(", a lowercase letter");
	}
	
	//password must contain at least one number
	if (!(password.match(/\d+/))) {
		errorMsg = errorMsg.concat(", a number");
	}
	
	if (errorMsg.length > 0) {
		errorMsg = errorMsg.substr(2);
		startMsg = "Password does not contain ";
		containMsg = startMsg.concat(errorMsg);
		errorMsg = "";
	}
	
	//password must not contain spaces
	if (password.match(/\s/)) {
		errorMsg = errorMsg.concat(", spaces");
	}
	
	//password must not contain quotes
	if (password.match(/['"']/ )) {
	 errorMsg = errorMsg.concat(", quotes");
	 }
						 
	 //password must not contain special characters
	 if (password.match(/[&()|<>]/)) {
	 errorMsg = errorMsg.concat(", special characters \(&, \(, \), |, \<, \>\)");
	 }
	 
	 //password must not contain non-printable characters
	 // ASCII control characters || invisible control characters
	 if (password.match(/[\cA-\cZ]/) || password.match(/\p{C}/)) {
	 errorMsg = errorMsg.concat(", special control characters ");
	 }
	 
	 //password must not contain 3 or more repeating characters
	 if (password.match(/(.)\1{2,}/)) {
	 errorMsg = errorMsg.concat(", 3 repeating characters ");
	 }
	 
	 if (errorMsg.length > 0) {
	 errorMsg = errorMsg.substr(2);
	 startMsg = "</br> Password can not contain ";
	 notContainMsg = startMsg.concat(errorMsg);
	 errorMsg = "";
	 }
	 
	 if (containMsg.length > 0 || notContainMsg.length > 0){
	 errorMsg = containMsg.concat(notContainMsg);
	 document.getElementById("thepasswordconfirmedhelp").innerHTML = errorMsg;
	 $('#thePassword').addClass('error');
	 $('#thePasswordConfirmed').addClass('error');
	 return false;
	 }
return true;
}

//confirm closing the window or navigating away
var navhook = true;

function navunhook() {
navhook=false;
}

window.onbeforeunload = function () {
if (getCookie('validation') != "OFF") {
  if (navhook) {
	//Trap the fly!
	return 'Data you have entered may not be saved!\n';
  }
  else {
	//Let the fly go...
  }
 }
};