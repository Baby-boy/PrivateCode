package com.glanway.hr.sso.entity.user;

import java.util.Date;

import com.glanway.hr.sso.entity.BaseEntity;

public class User extends BaseEntity {

	private static final long serialVersionUID = 3634503637929836762L;

	private String employeeCode;// 职员代码

	private String realName;// 真实姓名

	private String mobile;// 手机号

	private String email;// 电子邮箱

	private String pwd;// 登录密码

	private String salaryPwd;// 薪资查询密码

	private Integer state;// 状态(1:正常,2:异常)

	private String lastLoginIp;// 最后登录的IP

	private Date lastLoginTime;// 最后登录的时间

	private Integer loginNum;// 登录次数(薪资查询用)

	private String pwdReset; // 是否被重置密码

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode == null ? null : employeeCode.trim();
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName == null ? null : realName.trim();
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd == null ? null : pwd.trim();
	}

	public String getSalaryPwd() {
		return salaryPwd;
	}

	public void setSalaryPwd(String salaryPwd) {
		this.salaryPwd = salaryPwd == null ? null : salaryPwd.trim();
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
		this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Integer getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}

	public String getPwdReset() {
		return pwdReset;
	}

	public void setPwdReset(String pwdReset) {
		this.pwdReset = pwdReset;
	}
}