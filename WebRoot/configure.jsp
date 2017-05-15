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
<script type="text/javascript" src="js/jquery-ui.min.js"></script> 
<script type="text/javascript" src="js/showhide.js"></script> 
<script type="text/JavaScript" src="js/jquery.mousewheel.js"></script> 

<!-- validationEngine -->
<link rel="stylesheet" href="css/validationEngine.jquery.css" type="text/css"/>
<!-- <script src="js/jquery.validationEngine-zh_CN.js" type="text/javascript" charset="utf-8"></script> -->
<script src="js/jquery.validationEngine-en.js" type="text/javascript" charset="utf-8"></script>
<script src="js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>


<script type="text/javascript">
$(document).ready(function(){
	$("#configform").validationEngine();
	
	$("#transcoder_keepaspectratio").val('${transcoder_keepaspectratio}');
	$("#transcoder_watermarkuse").val('${transcoder_watermarkuse}');
});
</script> 
</head>

<body id="subpage">

<div id="svw_header_wrapper">
	<%@ include file="/cheader.jsp"%>
</div>	<!-- END of svw_header_wrapper -->

<div id="svw_main">
	<div id="content">
    	<div class="post">
            	
            
            <div id="contact_form">
            <form id="configform" method="post" name="update" action="ConfigureUpdateUpdate.action" enctype ="multipart/form-data">
            			<h2><s:property value="%{getText('configure.configure')}"/></h2>
            				<label for="vcodec"><s:property value="%{getText('configure.vcodec')}"/>:</label> <input type="text" id="vcodec" name="vcodec" value="${vcodec}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="vbitrate"><s:property value="%{getText('configure.vbitrate')}"/>:</label> <input type="text" id="vbitrate" name="vbitrate" value="${vbitrate}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="vfps"><s:property value="%{getText('configure.vfps')}"/>:</label>  <input type="text" id="vfps" name="vfps" value="${vfps}" class="validate[required,custom[integer]] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="acodec"><s:property value="%{getText('configure.acodec')}"/>:</label> <input type="text" id="acodec" name="acodec" value="${acodec}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="abitrate"><s:property value="%{getText('configure.abitrate')}"/>:</label> <input type="text" id="abitrate" name="abitrate" value="${abitrate}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>         
                            <label for="vreso"><s:property value="%{getText('configure.vreso')}"/>:</label>  <input type="text" id="vreso" name="vreso" value="${vreso}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="watermarkuse"><s:property value="%{getText('configure.watermarkuse')}"/>:</label>  
	                            <select id="watermarkuse" name="watermarkuse" class="required input_field">
								  <option value ="true"><s:property value="%{getText('configure.true')}"/></option>
								  <option value ="false"><s:property value="%{getText('configure.false')}"/></option>
								</select>
							<div class="cleaner h10"></div>
                            <label for="watermark_url"><s:property value="%{getText('configure.watermark_url')}"/>:</label>  <input type="text" id="watermark_url" name="watermark_url" value="${watermark_url}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="watermark_cor"><s:property value="%{getText('configure.watermark_cor')}"/>:</label>  <input type="text" id="watermark_cor" name="watermark_cor" value="${transcoder_watermark_x}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="keepaspectratio"><s:property value="%{getText('configure.keepaspectratio')}"/>:</label>  
	                            <select id="keepaspectratio" name="keepaspectratio" class="required input_field">
								  <option value ="true"><s:property value="%{getText('configure.true')}"/></option>
								  <option value ="false"><s:property value="%{getText('configure.false')}"/></option>
								</select>
                            <div class="cleaner h10"></div>
                            <label for="thumbnail_ss"><s:property value="%{getText('configure.thumbnail_ss')}"/>:</label>  <input type="text" id="thumbnail_ss" name="thumbnail_ss" value="${thumbnail_ss}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="folder_videoori"><s:property value="%{getText('configure.folder_videoori')}"/>:</label>  <input type="text" id="folder_videoori" name="folder_videoori" value="${folder_videoori}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="folder_dashfiles"><s:property value="%{getText('configure.folder_dashfiles')}"/>:</label>  <input type="text" id="folder_dashfiles" name="folder_dashfiles" value="${folder_dashfiles}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="folder_thumbnail"><s:property value="%{getText('configure.folder_thumbnail')}"/>:</label>  <input type="text" id="folder_thumbnail" name="folder_thumbnail" value="${folder_thumbnail}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                           	<label for="folder_logs"><s:property value="%{getText('configure.folder_logs')}"/>:</label>  <input type="text" id="folder_logs" name="folder_logs" value="${folder_logs}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="dash_segmentsize"><s:property value="%{getText('configure.dash_segmentsize')}"/>:</label>  <input type="text" id="dash_segmentsize" name="dash_segmentsize" value="${dash_segmentsize}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="dash_mpdname"><s:property value="%{getText('configure.dash_mpdname')}"/>:</label>  <input type="text" id="dash_mpdname" name="dash_mpdname" value="${dash_mpdname}" class="validate[required] required input_field" />
                            <div class="cleaner h10"></div>
                            <label for="dash_tisi"><s:property value="%{getText('configure.dash_tisi')}"/>:</label>  
	                            <select id="dash_tisi" name="dash_tisi" class="required input_field">
								  <option value ="true"><s:property value="%{getText('configure.true')}"/></option>
								  <option value ="false"><s:property value="%{getText('configure.false')}"/></option>
								</select>
                            <div class="cleaner h10"></div>
                            <input type="submit" value="<s:property value="%{getText('global.submit')}"/>" id="submit" name="submit" class="submit_btn float_l" />
                            <input type="reset" value="<s:property value="%{getText('global.reset')}"/>" id="reset" name="reset" class="submit_btn float_r" />

                    </form>
            </div>
        </div>
        <div class="cleaner"></div>
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