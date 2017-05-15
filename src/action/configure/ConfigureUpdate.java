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
package action.configure;

import java.util.Date;
import java.util.List;

import service.BaseService;

import bean.Configure;
import com.opensymphony.xwork2.ActionSupport;

public class ConfigureUpdate extends ActionSupport {
	//DASH Transcode Options
	private String vcodec;
	private String vbitrate;
	private String vfps;
	private String vreso;
	private String keepaspectratio;
	private String acodec;
	private String abitrate;
	private String dash_mpdname;
	private String dash_segmentsize;
	private String dash_tisi;
	private String watermarkuse;
	private String watermark_url;
	private String watermark_cor;
	private String thumbnail_ss;
	private String folder_videoori;
	private String folder_dashfiles;
	private String folder_logs;
	private String folder_thumbnail;

	
	
	private List<Configure> configurelist;
	private BaseService baseService;
	
	public List<Configure> getConfigurelist() {
		return configurelist;
	}
	public void setConfigurelist(List<Configure> configurelist) {
		this.configurelist = configurelist;
	}
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public String getVcodec() {
		return vcodec;
	}
	public void setVcodec(String vcodec) {
		this.vcodec = vcodec;
	}
	public String getVbitrate() {
		return vbitrate;
	}
	public void setVbitrate(String vbitrate) {
		this.vbitrate = vbitrate;
	}
	public String getVfps() {
		return vfps;
	}
	public void setVfps(String vfps) {
		this.vfps = vfps;
	}
	public String getVreso() {
		return vreso;
	}
	public void setVreso(String vreso) {
		this.vreso = vreso;
	}
	public String getKeepaspectratio() {
		return keepaspectratio;
	}
	public void setKeepaspectratio(String keepaspectratio) {
		this.keepaspectratio = keepaspectratio;
	}
	public String getAcodec() {
		return acodec;
	}
	public void setAcodec(String acodec) {
		this.acodec = acodec;
	}
	public String getAbitrate() {
		return abitrate;
	}
	public void setAbitrate(String abitrate) {
		this.abitrate = abitrate;
	}
	public String getDash_mpdname() {
		return dash_mpdname;
	}
	public void setDash_mpdname(String dash_mpdname) {
		this.dash_mpdname = dash_mpdname;
	}
	public String getDash_segmentsize() {
		return dash_segmentsize;
	}
	public void setDash_segmentsize(String dash_segmentsize) {
		this.dash_segmentsize = dash_segmentsize;
	}
	public String getDash_tisi() {
		return dash_tisi;
	}
	public void setDash_tisi(String dash_tisi) {
		this.dash_tisi = dash_tisi;
	}
	public String getWatermarkuse() {
		return watermarkuse;
	}
	public void setWatermarkuse(String watermarkuse) {
		this.watermarkuse = watermarkuse;
	}
	public String getWatermark_url() {
		return watermark_url;
	}
	public void setWatermark_url(String watermark_url) {
		this.watermark_url = watermark_url;
	}
	public String getWatermark_cor() {
		return watermark_cor;
	}
	public void setWatermark_cor(String watermark_cor) {
		this.watermark_cor = watermark_cor;
	}
	public String getFolder_logs() {
		return folder_logs;
	}
	public void setFolder_logs(String folder_logs) {
		this.folder_logs = folder_logs;
	}
	public String getThumbnail_ss() {
		return thumbnail_ss;
	}
	public void setThumbnail_ss(String thumbnail_ss) {
		this.thumbnail_ss = thumbnail_ss;
	}
	public String getFolder_videoori() {
		return folder_videoori;
	}
	public void setFolder_videoori(String folder_videoori) {
		this.folder_videoori = folder_videoori;
	}

	public String getFolder_thumbnail() {
		return folder_thumbnail;
	}
	public void setFolder_thumbnail(String folder_thumbnail) {
		this.folder_thumbnail = folder_thumbnail;
	}
	//DASH Support
	public String getFolder_dashfiles() {
		return folder_dashfiles;
	}
	public void setFolder_dashfiles(String folder_dashfiles) {
		this.folder_dashfiles = folder_dashfiles;
	}
	
	public String Read(){
		try{
			configurelist=baseService.ReadAll("Configure");
			for(Configure configure:configurelist){ 
				if(configure.getName().equals("vcodec")){
					vcodec=configure.getVal();
				}else if(configure.getName().equals("vbitrate")){
					vbitrate=configure.getVal();
				}else if(configure.getName().equals("vfps")){
					vfps=configure.getVal();
				}else if(configure.getName().equals("vreso")){
					vreso=configure.getVal();
				}else if(configure.getName().equals("keepaspectratio")){
					keepaspectratio=configure.getVal();
				}else if(configure.getName().equals("acodec")){
					acodec=configure.getVal();
				}else if(configure.getName().equals("abitrate")){
					abitrate=configure.getVal();
				}else if(configure.getName().equals("dash_mpdname")){
					dash_mpdname=configure.getVal();
				}else if(configure.getName().equals("dash_segmentsize")){
					dash_segmentsize=configure.getVal();
				}else if(configure.getName().equals("dash_tisi")){
					dash_tisi=configure.getVal();
				}else if(configure.getName().equals("watermarkuse")){
					watermarkuse=configure.getVal();
				}else if(configure.getName().equals("watermark_url")){
					watermark_url=configure.getVal();
				}else if(configure.getName().equals("watermark_cor")){
					watermark_cor=configure.getVal();
				}else if(configure.getName().equals("thumbnail_ss")){
					thumbnail_ss=configure.getVal();
				}else if(configure.getName().equals("folder_videoori")){
					folder_videoori=configure.getVal();
				}else if(configure.getName().equals("folder_dashfiles")){
					folder_dashfiles=configure.getVal();
				}else if(configure.getName().equals("folder_logs")){
					folder_logs=configure.getVal();
				}else if(configure.getName().equals("folder_thumbnail")){
					folder_thumbnail=configure.getVal();
				}
			} 
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	public String Update(){
		try{
			for(Configure configure:configurelist){ 
				if(configure.getName().equals("vcodec")){
					configure.setVal(vcodec);
				}else if(configure.getName().equals("vbitrate")){
					configure.setVal(vbitrate);
				}else if(configure.getName().equals("vfps")){
					configure.setVal(vfps);
				}else if(configure.getName().equals("vreso")){
					configure.setVal(vreso);
				}else if(configure.getName().equals("keepaspectratio")){
					configure.setVal(keepaspectratio);
				}else if(configure.getName().equals("acodec")){
					configure.setVal(acodec);
				}else if(configure.getName().equals("abitrate")){
					configure.setVal(abitrate);
				}else if(configure.getName().equals("dash_mpdname")){
					configure.setVal(dash_mpdname);
				}else if(configure.getName().equals("dash_segmentsize")){
					configure.setVal(dash_segmentsize);
				}else if(configure.getName().equals("dash_tisi")){
					configure.setVal(dash_tisi);
				}else if(configure.getName().equals("watermarkuse")){
					configure.setVal(watermarkuse);
				}else if(configure.getName().equals("watermark_url")){
					configure.setVal(watermark_url);
				}else if(configure.getName().equals("watermark_cor")){
					configure.setVal(watermark_cor);
				}else if(configure.getName().equals("thumbnail_ss")){
					configure.setVal(thumbnail_ss);
				}else if(configure.getName().equals("folder_videoori")){
					//Simple Check
					String str=folder_videoori;
					int strlenth=str.length();
					//End with '/'
					if(str.charAt(strlenth-1)=='/'){
						str=str.substring(0, strlenth-1);
					}
					//Begin with '/'
					if(str.charAt(0)=='/'){
						str=str.substring(1);
					}
					configure.setVal(str);
				}else if(configure.getName().equals("folder_dashfiles")){
					//Simple Check
					String str=folder_dashfiles;
					int strlenth=str.length();
					if(str.charAt(strlenth-1)=='/'){
						str=str.substring(0, strlenth-1);
					}
					if(str.charAt(0)=='/'){
						str=str.substring(1);
					}
					configure.setVal(str);
				}else if(configure.getName().equals("folder_logs")){
					//Simple Check
					String str=folder_logs;
					int strlenth=str.length();
					if(str.charAt(strlenth-1)=='/'){
						str=str.substring(0, strlenth-1);
					}
					if(str.charAt(0)=='/'){
						str=str.substring(1);
					}
					configure.setVal(str);
				}else if(configure.getName().equals("folder_thumbnail")){
					//Simple Check
					String str=folder_thumbnail;
					int strlenth=str.length();
					if(str.charAt(strlenth-1)=='/'){
						str=str.substring(0, strlenth-1);
					}
					if(str.charAt(0)=='/'){
						str=str.substring(1);
					}
					configure.setVal(str);
				}
				baseService.update(configure);
			} 
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	
}
