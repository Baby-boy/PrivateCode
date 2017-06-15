package com.glanway.hr.sso.entity.user;

import java.util.Date;

import com.glanway.hr.sso.entity.BaseEntity;

public class User extends BaseEntity {

	private static final long serialVersionUID = 1656599731253625578L;

	private String name;// 用户名

	private String pwd;// 密码

	private String realName;// 真实姓名

	private Integer state;// 状态(1:正常, 2:异常)

	private String lastLoginIp;// 最后登录IP

	private Date lastLoginTime;// 最后登录时间

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getLastLoginIp() {
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

}