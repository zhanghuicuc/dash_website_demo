/**
 * DASH Website Demo
 * a Website Demo supporting DASH media content upload, generate and watch.一个支持DASH内容上传、生成、观看的网站示例。
 * dont support live DASH currently
 * 
 * Dependencies:
 * DASHEncoder2 (https://github.com/zhanghuicuc/DASHEncoder2) and FFmpeg 
 *
 * 张晖
 * Hui Zhang
 * 中国传媒大学/数字电视技术
 * Communication University of China/Digital Video Technology
 * 
 * zhanghuicuc@gmail.com
 * http://blog.csdn.net/nonmarking
 * 
 * this website is based on Lei Xiaohua's simplest video website
 */

package util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import bean.Configure;
import bean.Video;
import bean.Videostate;


import service.BaseService;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

/**
 * @author 雷霄骅,张晖
 * 转码
 */
public class VideoTranscoderThread extends Thread {
private ServletContext servletContext;
	
	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	//构造函数
	public VideoTranscoderThread(ServletContext servletContext) {
		super();
		this.servletContext = servletContext;
	}
	public void updatePlayList(Configure folder_dashfile_cfg) {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		BaseService baseService = (BaseService)ctx.getBean("BaseService"); 
		String fileName = servletContext.getRealPath("/").replace('\\', '/')+folder_dashfile_cfg.getVal()+"/"+"dashdemo.exolist.json";
		List<Video> vodvideo=baseService.ReadByProperty("Video","islive",0);
		List<Video> livevideo=baseService.ReadByProperty("Video","islive",1);
		JSONArray wholelist = new JSONArray();
		JSONObject vodobject = new JSONObject();
		JSONObject liveobject = new JSONObject();
		if(vodvideo != null) {
			vodobject.put("name", "vod samples");
			JSONArray vodlist = new JSONArray();
			for(Video video:vodvideo) {
				JSONObject vodsample = new JSONObject();
				vodsample.put("name", video.getName());
				vodsample.put("uri", video.getMpdurl());
				vodlist.add(vodsample);
			}
			vodobject.put("samples", vodlist);
		}
		if(livevideo != null) {
			liveobject.put("name", "live samples");
			JSONArray livelist = new JSONArray();
			for(Video video:livevideo) {
				JSONObject livesample = new JSONObject();
				livesample.put("name", video.getName());
				livesample.put("uri", video.getMpdurl());
				livelist.add(livesample);
			}
			liveobject.put("samples", livelist);
		}
		wholelist.add(vodobject);
		wholelist.add(liveobject);
		StringWriter out = new StringWriter(); 
		try {
			wholelist.writeJSONString(out); 
			String jsonText = out.toString();
			BufferedWriter jsonout=new BufferedWriter(new FileWriter(fileName));
			jsonout.write(jsonText);
			jsonout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void copyReports(){
		String srcFiles = "D:/xampp/htdocs/DASH/uploads/*.*";
		srcFiles = srcFiles.replace('/', '\\');
		String destdir = servletContext.getRealPath("/");
		String copycommand="cmd ";
		copycommand+="/c copy ";
		copycommand+= srcFiles;
		copycommand+=" ";
		copycommand+=destdir;
		try{
			Process process=Runtime.getRuntime().exec(copycommand);
			//------------------------
			BufferedInputStream in = new BufferedInputStream(process.getInputStream()); 
			BufferedInputStream err = new BufferedInputStream(process.getErrorStream()); 
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
			BufferedReader errBr = new BufferedReader(new InputStreamReader(err));
			String lineStr;
			while ((lineStr = inBr.readLine()) != null) { 
				System.out.println(lineStr);
			}
			while ((lineStr = errBr.readLine()) != null) { 
				System.out.println(lineStr);
			}
			//Check
			process.destroy();
			inBr.close();  
			in.close();  
			err.close();
			errBr.close();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		try {
		do{
			int order=3;
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		BaseService baseService = (BaseService)ctx.getBean("BaseService"); 
		//Load Configure
		Configure vcodec_cfg=(Configure) baseService.ReadSingle("Configure", "name", "vcodec");
		Configure vbitrate_cfg=(Configure) baseService.ReadSingle("Configure", "name", "vbitrate");
		Configure vfps_cfg=(Configure) baseService.ReadSingle("Configure", "name", "vfps");
		Configure vreso_cfg=(Configure) baseService.ReadSingle("Configure", "name", "vreso");
		Configure keepaspectratio_cfg=(Configure) baseService.ReadSingle("Configure", "name", "keepaspectratio");
		Configure acodec_cfg=(Configure) baseService.ReadSingle("Configure", "name", "acodec");
		Configure abitrate_cfg=(Configure) baseService.ReadSingle("Configure", "name", "abitrate");
		Configure mpdname_cfg=(Configure) baseService.ReadSingle("Configure", "name", "dash_mpdname");
		Configure segmentsize_cfg=(Configure) baseService.ReadSingle("Configure","name","dash_segmentsize");
		Configure tisi_cfg=(Configure) baseService.ReadSingle("Configure","name","dash_tisi");
		Configure watermarkuse_cfg=(Configure) baseService.ReadSingle("Configure", "name", "watermarkuse");
		Configure watermark_url_cfg=(Configure) baseService.ReadSingle("Configure", "name", "watermark_url");
		Configure watermark_cor_cfg=(Configure) baseService.ReadSingle("Configure", "name", "watermark_cor");
		Configure folder_dashfile_cfg=(Configure) baseService.ReadSingle("Configure", "name", "folder_dashfiles");
		Configure folder_logs_cfg=(Configure) baseService.ReadSingle("Configure", "name", "folder_logs");
		
		//Folder of Watermark
		String[] watermarkstrlist=watermark_url_cfg.getVal().split("/");
		String watermarkDir="";
		String watermarkFile=watermarkstrlist[watermarkstrlist.length-1];
		for(int i=0;i<watermarkstrlist.length-1;i++){
			watermarkDir+=watermarkstrlist[i]+"/";
		}
		String realwatermarkDir=servletContext.getRealPath("/").replace('\\', '/')+watermarkDir;
		File realwatermarkDirFile= new File(realwatermarkDir);
		//Check
		if(!realwatermarkDirFile.exists()  && !realwatermarkDirFile.isDirectory()){
			System.out.println("Directory not exist. Create it.");  
			System.out.println(realwatermarkDirFile);  
			realwatermarkDirFile.mkdir();
		}
		
		
		//../webinf/dashfile,真实文件夹地址
		String realdashfileDir=servletContext.getRealPath("/").replace('\\', '/')+folder_dashfile_cfg.getVal();
		//Check
		File realdashfileDirFile =new File(realdashfileDir);
		if(!realdashfileDirFile.exists()  && !realdashfileDirFile.isDirectory()){
			System.out.println("Directory not exist. Create it."); 
			System.out.println(realdashfileDirFile);  
			realdashfileDirFile.mkdir();
		}
				
		//../webinf/logfile,真实文件夹地址
		String reallogfileDir=servletContext.getRealPath("/").replace('\\', '/')+folder_logs_cfg.getVal();
		//Check
		File reallogDirFile =new File(reallogfileDir);
		if(!reallogDirFile.exists()  && !reallogDirFile.isDirectory()){
			System.out.println("Directory not exist. Create it."); 
			System.out.println(reallogDirFile);  
			reallogDirFile.mkdir();
		}
		
					
			List<Video> resultvideo=baseService.ReadByProperty("Video","videostate.order", order);
			Videostate nextvideostate=(Videostate) baseService.ReadSingle("Videostate","order", order+1);
				if(resultvideo!=null){
					for(Video video:resultvideo){
						String realmpdfile=servletContext.getRealPath("/").replace('\\', '/')+folder_dashfile_cfg.getVal()+"/"+video.getName()+"/"+mpdname_cfg.getVal()+".mpd";
						
						//Transcode
						//../webinf/videoir/test.mp4
						String realfileoriginalPath=servletContext.getRealPath("/").replace('\\', '/')+video.getOriurl();
						//在video文件夹根据视频名称建立子文件夹，将dashfile存于其中
						//video/test/
						String real_dashfile_path=realdashfileDir+"/"+video.getName();											
						String real_logfile_path=reallogfileDir+"/"+video.getName()+".txt";
						
						double segmentsizeinSec=Double.parseDouble(segmentsize_cfg.getVal())/1000.0;
						int real_segmentsize=(int)(segmentsizeinSec*Double.parseDouble(vfps_cfg.getVal()));
						//转码命令如下所示
						//ffmpeg -i xxx.mkv -ar 22050 -b 600k -vcodec libx264 
						//-vf scale=w=640:h=360:force_original_aspect_ratio=decrease,pad=w=640:h=360:x=(ow-iw)/2:y=(oh-ih)/2[aa];
						//movie=watermark.png[bb];[aa][bb]overlay=5:5 yyy.flv
						//AVFilter参数作用如下所示
						//scale:视频拉伸滤镜。force_original_aspect_r atio用于强制保持宽高比
						//pad:用于加黑边，四个参数含义分别为：处理后宽，处理后高，输入图像左上角x坐标，输入视频左上角Y坐标。
						//其中ow,oh为输出（填充后）视频的宽高；iw,ih为输入（填充前）视频的宽高。
						//movie：用于指定需要叠加的水印Logo（PNG文件）。
						//overlay:用于叠加水印Logo和视频文件
						//命令行不同的执行方式
						//cmd /c xxx 是执行完xxx命令后关闭命令窗口。
						//cmd /k xxx 是执行完xxx命令后不关闭命令窗口。
						//cmd /c start xxx 会打开一个新窗口后执行xxx指令，原窗口会关闭。
						String videotranscodecommand="cmd ";
						videotranscodecommand+="/c ";
						
						//DASH Support
						videotranscodecommand+="dashencode.bat -v -d -f";
						videotranscodecommand+=" --encfiles-output-dir="+real_dashfile_path;
						videotranscodecommand+=" -b "+vbitrate_cfg.getVal();
						videotranscodecommand+=" -a "+abitrate_cfg.getVal();
						videotranscodecommand+=" -r "+vreso_cfg.getVal();
						videotranscodecommand+=" -o "+real_dashfile_path;
						videotranscodecommand+=" -s "+real_segmentsize;
						videotranscodecommand+=" --mpd-name="+mpdname_cfg.getVal()+".mpd";
						videotranscodecommand+=" --use-segment-list";
						if(watermarkuse_cfg.getVal().equals("true")){
							videotranscodecommand+=" -e movie=";
							videotranscodecommand+=watermarkFile;
							videotranscodecommand+="[bb];";
							videotranscodecommand+="[aa][bb]";
							videotranscodecommand+="overlay="+watermark_cor_cfg.getVal();
						}else{
							videotranscodecommand+=" ";
						}
						videotranscodecommand+=" "+realfileoriginalPath;
						videotranscodecommand+=" >"+real_logfile_path+" 2>&1";
						
						
						//videotranscodecommand+="/c ";
						/*videotranscodecommand+="ffmpeg -y ";
						videotranscodecommand+="-i ";
						videotranscodecommand+="\""+realfileoriginalPath+"\" ";
						videotranscodecommand+="-vcodec "+transcoder_vcodec_cfg.getVal()+" ";
						videotranscodecommand+="-b:v "+transcoder_bv_cfg.getVal()+" ";
						videotranscodecommand+="-r "+transcoder_framerate_cfg.getVal()+" ";
						videotranscodecommand+="-acodec "+transcoder_acodec_cfg.getVal()+" ";
						videotranscodecommand+="-b:a "+transcoder_ba_cfg.getVal()+" ";
						videotranscodecommand+="-ar "+transcoder_ar_cfg.getVal()+" ";
						videotranscodecommand+="-vf ";
						videotranscodecommand+="scale=w="+transcoder_scale_w_cfg.getVal()+":h="+transcoder_scale_h_cfg.getVal();
						if(transcoder_keepaspectratio_cfg.getVal().equals("true")){
							videotranscodecommand+=":"+"force_original_aspect_ratio=decrease,pad=w="+
						transcoder_scale_w_cfg.getVal()+":h="+transcoder_scale_h_cfg.getVal()+":x=(ow-iw)/2:y=(oh-ih)/2";
						}
						videotranscodecommand+="[aa]";
						if(transcoder_watermarkuse_cfg.getVal().equals("true")){
							videotranscodecommand+=";movie=";
							videotranscodecommand+=watermarkFile;
							videotranscodecommand+="[bb];";
							videotranscodecommand+="[aa][bb]";
							videotranscodecommand+="overlay=x="+transcoder_watermark_x_cfg.getVal()+":y="+transcoder_watermark_y_cfg.getVal()+" ";
						}else{
							videotranscodecommand+=" ";
						}
						videotranscodecommand+="\"";
						videotranscodecommand+=realfilePath;
						videotranscodecommand+="\"";*/
						
						
						System.out.println(videotranscodecommand);
						Process process=Runtime.getRuntime().exec(videotranscodecommand);
						//------------------------
						BufferedInputStream in = new BufferedInputStream(process.getInputStream()); 
						BufferedInputStream err = new BufferedInputStream(process.getErrorStream()); 
						BufferedReader inBr = new BufferedReader(new InputStreamReader(in));
						BufferedReader errBr = new BufferedReader(new InputStreamReader(err));
						String lineStr;
						while ((lineStr = inBr.readLine()) != null) { 
							System.out.println(lineStr);
						}
						while ((lineStr = errBr.readLine()) != null) { 
							System.out.println(lineStr);
						}
						//Check
						File realmpdfileFile =new File(realmpdfile);
						if(!realmpdfileFile.exists()){
							System.err.println("Failed!"); 
						}else{
							process.destroy();
						}
						inBr.close();  
						in.close();  
						err.close();
						errBr.close();
						//video/1/1.mpd
						video.setMpdurl(folder_dashfile_cfg.getVal()+"/"+video.getName()+"/"+mpdname_cfg.getVal()+".mpd");
						video.setLogurl(folder_logs_cfg.getVal()+"/"+video.getName()+".txt");
						video.setDashfileurl(folder_dashfile_cfg.getVal()+"/"+video.getName());
						video.setVideostate(nextvideostate);
						baseService.update(video);
						
						//Rest--------------------------
						sleep(10 * 1000);
					}
				}
				updatePlayList(folder_dashfile_cfg);				
				copyReports();
			sleep(10 * 1000);
		}while(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
