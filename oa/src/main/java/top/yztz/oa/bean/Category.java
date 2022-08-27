package top.yztz.oa.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.List;

@Entity
public class Category {//分类
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//ID
	private String name;//分类名称
	private String comment;//备注

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Category() {}

	public Category(long id, String name, String comment) {
		this.id = id;
		this.name = name;
		this.comment = comment;
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

	@Override
	public String toString() {
		return "Classify [id=" + id + ", name=" + name + ", comment=" + comment + "]";
	}

}
