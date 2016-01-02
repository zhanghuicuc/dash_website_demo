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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import service.BaseService;
import bean.Video;

import com.opensymphony.xwork2.ActionSupport;
/**
 * @author 雷霄骅,张晖
 * 删除视频的Action
 */
public class VideoDelete extends ActionSupport {
	private int videoid;
	private BaseService baseService;
	public int getVideoid() {
		return videoid;
	}

	public void setVideoid(int videoid) {
		this.videoid = videoid;
	}
	
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public String execute(){
		try{
			Video video=(Video) baseService.ReadByID("Video", videoid);
			//相对路径
			String thumbnailPath=video.getThumbnailurl();
			String path=video.getUrl();
			String oripath=video.getOriurl();
			//获取根路径（绝对路径）
			String thumbnailrealpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+thumbnailPath;
			String realpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+path;
			String orirealpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+oripath;
			//DASH Support 
			String segmentrealpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+video.getMpdurl().substring(0, video.getMpdurl().lastIndexOf("/"));
			File thumbnailfile=new File(thumbnailrealpath);
			File videofile=new File(realpath);
			File orivideofile=new File(orirealpath);
			//删除与之相关的截图文件和视频文件
			if(thumbnailfile!=null){
				thumbnailfile.delete();
			}
			if(videofile!=null){
				videofile.delete();
			}
			if(orivideofile!=null){
				orivideofile.delete();
			}
			//DASH Support
			String segmentdelcommand="cmd /c rmdir /s/q "+"\""+segmentrealpath+"\" ";
			System.out.println(segmentdelcommand);
			Process process=Runtime.getRuntime().exec(segmentdelcommand);
			//------------------------
			BufferedInputStream in = new BufferedInputStream(process.getInputStream());  
			BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
			String lineStr;  
			while ((lineStr = inBr.readLine()) != null)  
					System.out.println(lineStr);
			if (process.waitFor() != 0) {  
				if (process.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束  
					System.err.println("Failed!");  
			}  
			inBr.close();  
			in.close();
			//最后才删除该记录
			baseService.delete(video);
			return SUCCESS;
		}
		catch(Exception ex){
			ex.printStackTrace();
			return ERROR;
		}
	}
}
