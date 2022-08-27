package com.oasystem.bean;


import java.util.Date;

public class Category {//分类
	
	private Long id;//ID
	private String name;//分类名称
	private String comment;//备注
	
	public Category() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
