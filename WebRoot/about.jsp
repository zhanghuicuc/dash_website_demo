<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Video, FFmpeg, JavaEE, DASHEncoder2" />
<meta name="author" content="Lei Xiaohua, Hui Zhang" />
<meta name="description" content="a Website Demo supporting DASH media content upload, generate and watch" />

<title>DASH Website Demo</title>

<link href="css/svw_style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script> 
<!--script type="text/javascript" src="/jqueryui/js/jquery-ui-1.7.2.custom.min.js"></script--> 
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script type="text/javascript" src="js/showhide.js"></script> 
<script type="text/JavaScript" src="js/jquery.mousewheel.js"></script> 

</head>

<body id="subpage">

<div id="svw_header_wrapper">
	<%@ include file="/cheader.jsp"%>
</div>	<!-- END of svw_header_wrapper -->

<div id="svw_main">
	
    <div id="content">
    	<h2><s:property value="%{getText('about.about')}"/></h2>
    	<p>a Website Demo supporting DASH media content upload, generate and watch. it is based on JavaEE, FFmpeg and DASHEncoder2.</p>
        <p>The site use following projects:</p>
        <ul>
          <li>
           jQuery
          </li>
          <li>
           Struts2
          </li>
          <li>
           Spring
          </li>
          <li>
           Hibernate
          </li>
          <li>
            MySQL
          </li>
          <li>
            FFmpeg
          </li>
          <li>
            MediaInfo
          </li>
          <li>
            DASHEncoder2
          </li>
        </ul>
<div class="cleaner h30"></div>
        <div class="about_box">
			
            <div class="cleaner"></div>
        </div>
    </div>
    
    <div id="sidebar">

		<s:action name="SidebarRecent" executeResult="true">
           	<s:param name="num">5</s:param>
        </s:action>
    </div> <!-- end of sidebar -->
    
    <div class="cleaner"></div>
</div> <!-- END of svw_main -->

 <!-- END of svw_bottom_wrapper -->

<div id="svw_footer_wrapper">
    <%@ include file="/cfooter.jsp"%>
</div> <!-- END of svw_footer -->

</body>
</html>