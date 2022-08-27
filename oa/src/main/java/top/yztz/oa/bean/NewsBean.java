package top.yztz.oa.bean;

public class NewsBean {
	
	private long id;
	private String newsId;//信息ID
	private String typeId;//分类ID
	private String typeName;//分类名称
	private String number;//编号
	private String picName;//图片名称
	private String   time;//发布时间
	private String appendix;
	
	public NewsBean() {}

	public NewsBean(long id, String newsId, String typeId, String typeName, String number, String picName, String time,
			String appendix) {
		this.id = id;
		this.newsId = newsId;
		this.typeId = typeId;
		this.typeName = typeName;
		this.number = number;
		this.picName = picName;
		this.time = time;
		this.appendix = appendix;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}

	@Override
	public String toString() {
		return "NewsBean [id=" + id + ", newsId=" + newsId + ", typeId=" + typeId + ", typeName=" + typeName
				+ ", number=" + number + ", picName=" + picName + ", time=" + time + ", appendix=" + appendix + "]";
	}

}
