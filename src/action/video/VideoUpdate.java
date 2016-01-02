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
package action.video;

import java.sql.Timestamp;
import java.util.Date;

import service.BaseService;

import bean.Video;

import com.opensymphony.xwork2.ActionSupport;
/**
 * @author 雷霄骅
 * 更新视频信息的ACtion
 */
public class VideoUpdate extends ActionSupport {
	private int videoid;
	private String name;
	private String intro;
	private BaseService baseService;
	private Video video;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	public Video getVideo() {
		return video;
	}
	public void setVideo(Video video) {
		this.video = video;
	}
	public int getVideoid() {
		return videoid;
	}
	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	public String Read(){
		try{
			video=(Video) baseService.ReadByID("Video", videoid);
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
	public String Update(){
		try{
			video.setName(name);
			video.setIntro(intro);
			video.setEdittime(new Timestamp(new Date().getTime()));
			baseService.update(video);
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
}
