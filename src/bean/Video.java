package bean;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Video entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "video", catalog = "dashdemo")
public class Video implements java.io.Serializable {

	// Fields

	private Integer id;
	private Videostate videostate;
	private Category category;
	private String name;
	private String intro;
	private Timestamp edittime;
	private Integer islive;
	private String mpdurl;
	private String dashfileurl;
	private String oriurl;
	private String thumbnailurl;
	private String logurl;
	private String remark;

	/** default constructor */
	public Video() {
	}

	/** full constructor */
	public Video(Videostate videostate, Category category, String name,
			String intro, Timestamp edittime, Integer islive, String mpdurl, String dashfileurl,
			String oriurl, String thumbnailurl, String logurl, String remark) {
		this.videostate = videostate;
		this.category = category;
		this.name = name;
		this.intro = intro;
		this.edittime = edittime;
		this.islive = islive;
		this.mpdurl = mpdurl;
		this.dashfileurl = dashfileurl;
		this.oriurl = oriurl;
		this.thumbnailurl = thumbnailurl;
		this.logurl = logurl;
		this.remark = remark;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "videostateid")
	public Videostate getVideostate() {
		return this.videostate;
	}

	public void setVideostate(Videostate videostate) {
		this.videostate = videostate;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoryid")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "intro", length = 8192)
	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	@Column(name = "edittime", length = 19)
	public Timestamp getEdittime() {
		return this.edittime;
	}

	public void setEdittime(Timestamp edittime) {
		this.edittime = edittime;
	}

	@Column(name = "islive")
	public Integer getIslive() {
		return this.islive;
	}

	public void setIslive(Integer islive) {
		this.islive = islive;
	}

	@Column(name = "mpdurl")
	public String getMpdurl() {
		return this.mpdurl;
	}

	public void setMpdurl(String mpdurl) {
		this.mpdurl = mpdurl;
	}
	
	@Column(name = "dashfileurl")
	public String getDashfileurl() {
		return this.dashfileurl;
	}

	public void setDashfileurl(String dashfileurl) {
		this.dashfileurl = dashfileurl;
	}

	@Column(name = "oriurl")
	public String getOriurl() {
		return this.oriurl;
	}

	public void setOriurl(String oriurl) {
		this.oriurl = oriurl;
	}

	@Column(name = "thumbnailurl")
	public String getThumbnailurl() {
		return this.thumbnailurl;
	}

	public void setThumbnailurl(String thumbnailurl) {
		this.thumbnailurl = thumbnailurl;
	}
	
	@Column(name = "logurl")
	public String getLogurl() {
		return this.logurl;
	}

	public void setLogurl(String logurl) {
		this.logurl = logurl;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}