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
<script type="text/javascript" src="http://cdn.dashjs.org/latest/dash.all.debug.js"></script>
<style>
        video {
            width: 640px;
            height: 360px;
        }
</style>
<script>
        var player;
        function init()
        {
            player = dashjs.MediaPlayerFactory.create(document.querySelector(".dashjs-player"));
            setInterval(updateStats,500)
        }
        function updateStats()
        {
            document.querySelector("#stats").innerHTML = "Buffer level " + player.getBufferLength() + "s";
        }
</script>
</head>

<body id="subpage" onload="init()">

<div id="svw_header_wrapper">
	<%@ include file="/cheader.jsp"%>
</div>	<!-- END of svw_header_wrapper -->

<div id="svw_main">
	<div id="content">
    	<div class="post">
            <h2>
            <c:choose>
	    	<c:when test="${video.islive==1}">[<s:property value="%{getText('video.live')}"/>]</c:when>
	    	<c:otherwise>[<s:property value="%{getText('video.vod')}"/>]</c:otherwise>
	    	</c:choose>
	    	${video.name}
	    	</h2>
            <div id="player_window">	            
					<video class="dashjs-player" autoplay preload="none" controls="true">
                		<source src="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/${video.mpdurl}" type="application/dash+xml"/>
            		</video>
			</div>
			<div>
            	<span id="stats"/>
        	</div>
            <div class="meta">
                <span class="content"><a href="VideoReadByID.action?videoid=${video.id}"><s:property value="%{getText('video.content')}"/></a></span>
                <span class="edit"><a href="VideoUpdateRead.action?videoid=${video.id}"><s:property value="%{getText('video.edit')}"/></a></span>
                <span class="delete"><a href="javascript:if(confirm('Are you sure to delete?'))location='VideoDelete.action?videoid=${video.id}'"><s:property value="%{getText('video.delete')}"/></a></span>
                
                <div class="cleaner"></div>
            </div> 
        </div>
        
        <div class="cleaner h20"></div>
            <h3><s:property value="%{getText('video.intro')}"/></h3>
        <div id="comment_section">
       		<c:choose>
   			<c:when test="${empty video.intro}">
   				<p><s:property value="%{getText('video.intronull')}"/></p>
   			</c:when>
   			<c:otherwise>
   				<p>${video.intro}</p>
   			</c:otherwise>
	    	</c:choose> 
        </div>
        <div class="cleaner h20"></div>
        <!-- Default: Do not use MediaInfo  -->
        
        <c:if test="0">
            <h3><s:property value="%{getText('video.mediainfo')}"/></h3>
              	<div id="comment_section">
                	<ol class="comments first_level">
                        
                        <li>
                        <h4><s:property value="%{getText('video.mediainfo.upload')}"/></h4>
                            <div class="comment_box commentbox1">
                                <div class="comment_text">
                                    ${original_videoinfo}
                                </div>
                                <div class="cleaner"></div>
                            </div> 
                        </li>
                        <li>
                        <h4><s:property value="%{getText('video.mediainfo.transcode')}"/></h4>
                            <div class="comment_box commentbox1">
                                <div class="comment_text">
                                    ${convert_videoinfo}
                                </div>
                                <div class="cleaner"></div>
                            </div> 
                        </li>
                        
	                </ol>
	                <div class="cleaner h20"></div>    
                    
                </div>
                
                <div class="cleaner h20"></div>
         </c:if>
          
    </div>
    
    <div id="sidebar">
        <ul class="svw_list">
        	<li><a href="VideoReadAll.action?islive=${video.islive}"><s:property value="%{getText('video.return')}"/></a></li>
        	<li><a href="VideoReadByID.action?videoid=${video.id}"><s:property value="%{getText('video.content')}"/></a></li>
        	<li><a href="VideoUpdateRead.action?videoid=${video.id}"><s:property value="%{getText('video.edit')}"/></a></li>
            <li><a href="javascript:if(confirm('Are you sure to Delete?'))location='VideoDelete.action?videoid=${video.id}'"><s:property value="%{getText('video.delete')}"/></a></li>

        </ul>
        
        <div class="cleaner h30"></div>

        <s:action name="SidebarRecent" executeResult="true">
           	<s:param name="num">5</s:param>
        </s:action>
    </div> <!-- end of sidebar -->
    <div class="cleaner"></div>
</div> <!-- END of svw_main -->

<div id="svw_footer_wrapper">
    <%@ include file="/cfooter.jsp"%>
</div> <!-- END of svw_footer -->

</body>
</html>