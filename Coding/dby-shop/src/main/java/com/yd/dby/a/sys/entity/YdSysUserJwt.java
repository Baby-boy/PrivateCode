package com.yd.dby.a.sys.entity;

public class YdSysUserJwt {
	private Integer id;
	private String nickname;
	private String role;

	public YdSysUserJwt() {

	}

	public YdSysUserJwt(YdSysUserSecurity user) {
		this.id = user.getUser_id();
		this.nickname = user.getUser_nickname();
		this.role = user.getRole();
	}

	public Integer getId() {
		return id;
	}

	public String getNickname() {
		return nickname;
	}

	public String getRole() {
		return role;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setRole(String role) {
		this.role = role;
	}

}