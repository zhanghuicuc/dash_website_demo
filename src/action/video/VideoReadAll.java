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

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import service.BaseService;

import bean.Video;
/**
 * @author 雷霄骅
 * 读取所有视频信息的Action
 */
public class VideoReadAll extends ActionSupport{
	private List<Video> resultvideo;
	private BaseService baseService;
	private int islive;
	
	public List<Video> getResultvideo() {
		return resultvideo;
	}

	public void setResultvideo(List<Video> resultvideo) {
		this.resultvideo = resultvideo;
	}
	
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}


	public int getIslive() {
		return islive;
	}

	public void setIslive(int islive) {
		this.islive = islive;
	}

	@SuppressWarnings("unchecked")
	public String execute(){
		try{
			if(islive==0){
				resultvideo=baseService.ReadByProperty("Video","islive",0);
			}else{
				resultvideo=baseService.ReadByProperty("Video","islive",1);
			}
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
}
