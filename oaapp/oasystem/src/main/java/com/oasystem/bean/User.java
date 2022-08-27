package com.oasystem.bean;

import java.util.Date;

public class User {
	
	private long id;//ID
	private String userId;//用户ID
	private String account;//账号
	private String userName;//用户名
	private String password;//密码
	private String phone;//电话
	private String userType;//用户类型
	private String appendix;
	
	public User() {}

	public User(long id, String userId, String account, String userName, String password, String phone, String userType,
			String appendix) {
		this.id = id;
		this.userId = userId;
		this.account = account;
		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.userType = userType;
		this.appendix = appendix;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getAppendix() {
		return appendix;
	}

	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", account=" + account + ", userName=" + userName
				+ ", password=" + password + ", phone=" + phone + ", userType=" + userType + ", appendix=" + appendix
				+ "]";
	}

	
	
}
