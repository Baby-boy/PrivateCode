package com.yd.dby.a.sys.entity;

public class YdSysUserSecurity {
	private Integer user_id;
	private String user_username;
	private String user_password;
	private String user_nickname;
	private String user_mobile;
	
	private String token;
	private String role;

	public YdSysUserSecurity() {
	}

	public YdSysUserSecurity(String user_mobile, String user_password, String user_username) {
		this.user_mobile = user_mobile;
		this.user_password = user_password;
		this.user_username = user_username;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}