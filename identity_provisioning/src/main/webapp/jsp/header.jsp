<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="navbar-inner">
<div class="container">

<div class="logo"><a href="<s:property value='#application["ui.university.logo.href"]'/>"><img id="logo" class="img" src="<s:property value='#application["ui.university.logo.path"]'/>" alt="Links to <s:property value='#application["ui.university.logo.href"]'/>" tabindex="1"></a></div>

<div id="sitename" class="hidden"><s:property value='#application["gbl.sitename"]'/></div>
<div id="familiarname" class="hidden"><s:property value='#application["gbl.name.familiar"]'/></div>
<div id="formalname" class="hidden"><s:property value='#application["gbl.name.formal"]'/></div>
<div id="nickname" class="hidden"><s:property value='#application["gbl.name.nickname"]'/></div>

<div class="sitename" <s:if test='%{#application["deployed"] != "Y"}'>onclick="navunhook(); document.location.href='index.jsp'" style="cursor: pointer"</s:if> >
<p><s:property value='#application["gbl.sitename"]'/></p>
</div>

<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse" tabindex="-1">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</a>

<div class="nav-collapse">
<ul class="nav">
<li><s:property value='#application["ui.header.links.title"]'/></li>
<li><a href="<s:property value='#application["ui.header.link1.href"]'/>"><i class="icon-user icon-white hidden-tablet" tabindex="2"></i>  <s:property value='#application["ui.header.link1.title"]'/></a></li>
<li><a href="<s:property value='#application["ui.header.link2.href"]'/>"><i class="icon-home icon-white hidden-tablet" tabindex="3"></i>  <s:property value='#application["ui.header.link2.title"]'/></a></li>
<li><a href="<s:property value='#application["ui.header.link3.href"]'/>"><i class="icon-search icon-globe icon-white hidden-tablet" tabindex="4"></i>  <s:property value="#application['ui.header.link3.title']"/></a></li>
<li class="visible-phone" style="background-color: #000000;">Progress:<div class="progress"><div id="progressbar" class="bar" style="width: 0%;"></div></div></li>
</ul>
</div><!-- end .nav-collapse -->

</div><!-- end container -->
</div><!-- end navbar-inner -->
</div><!-- end navbar -->
