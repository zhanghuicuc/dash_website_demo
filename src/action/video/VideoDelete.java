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
			String dashfilepath=video.getDashfileurl();
			String oripath=video.getOriurl();
			String logpath=video.getLogurl();
			//获取根路径（绝对路径）
			String thumbnailrealpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+thumbnailPath;
			String dashfilerealpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+dashfilepath;
			String orirealpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+oripath;
			String logrealpath=ServletActionContext.getServletContext().getRealPath("/").replace('\\', '/')
					+logpath;
			File thumbnailfile=new File(thumbnailrealpath);
			File dashfile=new File(dashfilerealpath);
			File orivideofile=new File(orirealpath);
			File logfile=new File(logrealpath);
			//删除与之相关的截图文件和视频文件
			if(thumbnailfile!=null){
				thumbnailfile.delete();
			}
			if(dashfile!=null){
				String dashfiledelcommand="cmd /c rmdir /s/q "+"\""+dashfilerealpath+"\" ";
				System.out.println(dashfiledelcommand);
				Process process=Runtime.getRuntime().exec(dashfiledelcommand);
				BufferedInputStream in = new BufferedInputStream(process.getInputStream());  
				BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
				String lineStr;  
				while ((lineStr = inBr.readLine()) != null)  
						System.out.println(lineStr);
				if (process.waitFor() != 0) {  
					if (process.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束  
						System.err.println("Delete dash files Failed!");  
				} 
				inBr.close();  
				in.close();
			}
			if(orivideofile!=null){
				orivideofile.delete();
			}
			if(logfile!=null){
				String logfiledelcommand="cmd /c rmdir /s/q "+"\""+logrealpath+"\" ";
				System.out.println(logfiledelcommand);
				Process process=Runtime.getRuntime().exec(logfiledelcommand);
				BufferedInputStream in = new BufferedInputStream(process.getInputStream());  
				BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
				String lineStr;  
				while ((lineStr = inBr.readLine()) != null)  
						System.out.println(lineStr);
				if (process.waitFor() != 0) {  
					if (process.exitValue() == 1)//p.exitValue()==0表示正常结束，1：非正常结束  
						System.err.println("Delete log files Failed!");  
				} 
				inBr.close();  
				in.close();
			}
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
