package top.yztz.oa.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Entity
public class News {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	@Id

	private Long id;

	private String name;//编号
	private String picName;//图片名称
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date   time;//发布时间
	private String comment;
	@Column(name = "c_id")
	private Long categoryID;

	public News() {}

	public News(long id, String name, String picName, Date time, String comment) {
		this.id = id;
		this.name = name;
		this.picName = picName;
		this.time = time;
		this.comment = comment;
	}

	public Long getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(long categoryID) {
		this.categoryID = categoryID;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "News{" +
				"id=" + id +
				", name='" + name + '\'' +
				", picName='" + picName + '\'' +
				", time=" + time +
				", comment='" + comment + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
