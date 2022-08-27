package top.yztz.oa.bean;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;

@Entity
public class User {
	@Column(nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;//ID

	@Column(name = "username")
	private String userName;//用户名

	private String password;//密码

	private String phone;//电话

	@Column(name = "type")
	private String userType;//用户类型

	private String comment;

	public User(){}

	public User(long id, String userName, String password, String phone, String userType,
			String comment) {
		this.id = id;

		this.userName = userName;
		this.password = password;
		this.phone = phone;
		this.userType = userType;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName
				+ ", password=" + password + ", phone=" + phone + ", userType=" + userType + ", comment=" + comment
				+ "]";
	}

	
	
}
