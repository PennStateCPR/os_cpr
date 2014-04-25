<%  /* SVN FILE: $Id: debugOptions.jsp 5500 2012-10-22 19:50:17Z jal55 $ */  %>
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" errorPage=""%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%      
/**
 * contactInformation.jsp retrieves personal contact ifnormation; such as,
 * email, telephone, extension, and whether or not a number is an international number.
 * 
 * This work is licensed under the Creative Commons Attribution-NonCommercial-NoDerivs 3.0 United States License. To 
 * view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/us/ or send a letter to Creative 
 * Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
 * 
 * @author $Author: jal55 $
 * @version $Rev: 5500 $
 * @lastrevision $Date: 2012-10-22 15:50:17 -0400 (Mon, 22 Oct 2012) $
 */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">

  <head>
    <meta charset="utf-8">
    <title>Welcome to <s:property value="#application['ui.sitename']"/>!</title>
	
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta names="apple-mobile-web-app-status-bar-style" content="black-translucent" />	
    
	<meta name="description" content="Identity Provisioning">
    <meta name="author" content="Charles Crust, Identity and Access Management, The Pennsylvania State University">

    <!-- styles -->
    <link href="./assets/css/bootstrap.css" rel="stylesheet">
    <link href="./assets/css/bootstrap-responsive.css" rel="stylesheet">

    <!-- HTML5 shim (for IE6-8 support of HTML5 elements) -->
    <!--[if lt IE 9]>
      <script src="./assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- fav and touch icons -->
    <link rel="shortcut icon" href="./assets/ico/psuicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="./assets/ico/iamps144.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="./assets/ico/iamps114.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="./assets/ico/iamps72.png">
    <link rel="apple-touch-icon-precomposed" href="./assets/ico/iamps57.png">
  </head>

  <body>

    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">

          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
			<span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
		  
          <a class="brand" href="http://www.psu.edu"><img src="http://webstyleguide.psu.edu/img/white-border10.png" width="78" height="42" alt="Links to www.psu.edu"><s:property value="#application['ui.sitename']"/></a>

          <div class="nav-collapse">
			<ul class="nav">
			  <li>SEARCH:</li>
              <li><a href="http://www.psu.edu/directory/"><i class="icon-user icon-white hidden-tablet"></i>  People</a></li>
              <li><a href="http://www.psu.edu/directory/dept/"><i class="icon-home icon-white hidden-tablet"></i>  Departments</a></li>
              <li><a href="http://search.psu.edu/"><i class="icon-search icon-globe icon-white hidden-tablet"></i>  Penn State</a></li>
			  <li class="visible-phone">Progress:<div class="progress"><div class="bar" style="width: 25%;"></div></div></li>
			</ul>
          </div><!-- end .nav-collapse -->

        </div><!-- end container -->
      </div><!-- end navbar-inner -->
    </div><!-- end navbar -->

<div><!-- <div class="container">-->
	<div><!-- <div class="datacard">-->
	<div class="title">SET DEBUGGING OPTIONS</div>
	
	<div> <!-- </div><div class="dataform"> -->
      <p>&nbsp;</p>

			<!-- begin jsp content -->
			<s:form action="/debug_options" id="debug" method="post" theme="simple">
            <table>
            	<tr><td align=right>Debug Option:          </td>
            		<td>
            			 <input type="radio" name="debug" value="on" >On</input>
            		     <input type="radio" name="debug" value="off">Off</input>
            		</td>
            	</tr>
            	<tr><td align=right>Bypass Server-side Validation:</td>
            		<td> <input type="radio" name="validation" value="on" >On</input>
            			 <input type="radio" name="validation" value="off">Off</input>
            		</td>
            	</tr>
            </table>
			<s:hidden name="btnsubmit" id="btnsubmit" value=""/>
			<s:token />
			</s:form>
	</div>
						
		<div class="desktop-spacer visible-desktop">&nbsp;</div>

		<!-- NAVIGATION - Tablets, Desktops -->
		<div class="desktop-nav hidden-phone">
			<!--left button content-->
			<a class="btn-navbar-left"><div class="navbar-link btn btn-primary btn-large"  onclick="javascript:alert('Back!')">
				<small>Back</small>
			</div></a>
			
			<!--right button content-->
			<a class="btn-navbar-right"><div class="navbar-link btn btn-primary btn-large" onclick="javascript:document.getElementById('debug').submit();">
				<b>Continue</b>
			</div></a>
		</div>
		
	</div> <!-- END DATACARD -->
</div> <!-- END CONTAINER -->

<s:if test="%{#session.debug == 'enabled'}">
	<s:debug/>
</s:if>

<!-- NAVIGATION - Phones, Handhelds -->    
<div class="navbar navbar-fixed-bottom visible-phone">
	<div class="navbar-inner">
		<div class="container">
			<!--left button content-->
			<a class="btn-navbar-left"><div class="navbar-link btn btn-navbar" onclick="javascript:alert('Back!')">
				<small>Back</small>
			</div></a>
			<!--right button content-->
			<a class="btn-navbar-right"><div class="navbar-link btn btn-navbar" onclick="javascript:document.getElementById('formdata').submit();">
				<small><b>Continue</b></small>
			</div></a>
		</div>
	</div>
</div>

<div class="footer visible-desktop">&copy;2012 <a href="http://www.psu.edu/"> The Pennsylvania State University </a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="http://www.psu.edu/ur/legal.html"> Privacy and Legal Statements </a></div>

	<!-- JavaScript placed at the end of the document so the pages load faster! -->
    <script src="./assets/js/jquery.js"></script>
    <script src="./assets/js/bootstrap-transition.js"></script>
    <script src="./assets/js/bootstrap-alert.js"></script>
    <script src="./assets/js/bootstrap-modal.js"></script>
    <script src="./assets/js/bootstrap-dropdown.js"></script>
    <script src="./assets/js/bootstrap-scrollspy.js"></script>
    <script src="./assets/js/bootstrap-tab.js"></script>
    <script src="./assets/js/bootstrap-tooltip.js"></script>
    <script src="./assets/js/bootstrap-popover.js"></script>
    <script src="./assets/js/bootstrap-button.js"></script>
    <script src="./assets/js/bootstrap-collapse.js"></script>
    <script src="./assets/js/bootstrap-carousel.js"></script>
    <script src="./assets/js/bootstrap-typeahead.js"></script>
    <script src="./assets/js/bootstrap-iam.js"></script>

  </body>
</html>