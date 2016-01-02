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

import service.BaseService;

import bean.Video;
import com.opensymphony.xwork2.ActionSupport;
/**
 * @author 雷霄骅
 * 根据指定顺序读取视频信息Action
 */
public class VideoReadLimitedByOrder extends ActionSupport {
	private int num;
	private BaseService baseService;
	private List<Video> resultvideo;

	public BaseService getBaseService() {
		return baseService;
	}
	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public List<Video> getResultvideo() {
		return resultvideo;
	}
	public void setResultvideo(List<Video> resultvideo) {
		this.resultvideo = resultvideo;
	}
	public String execute(){
		try{
			resultvideo= baseService.ReadLimitedByOrder("Video", "edittime", num, "asc");
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
}
