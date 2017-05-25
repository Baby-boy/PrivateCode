package com.yd.gcj.entity;

import java.beans.Transient;
import java.util.Date;



public class YdMangerSystemAdmin {

	private Integer admin_id;//编号(主键)
	private String admin_name;//管理员名称
	private String admin_password;//管理员密码
	private Date admin_login_time;//登录时间
	private Integer admin_login_num;//登录次数
	private Integer admin_is_super;//0:是超级管理员 其他:不是
	private Integer admin_gid;//权限组ID
	private String admin_account;//管理员账号
	private String admin_phone;//管理员联系方式(手机号码)
	private Date admin_create_time;//管理员账号创建时间
	private Date admin_update_time;//管理员账号修改时间
	private Date admin_last_time;//管理员最后一次登录时间
	
	private String code;//验证码 
	
	@Transient//不需要持久到数据库注解
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(Integer admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public Date getAdmin_login_time() {
		return admin_login_time;
	}
	public void setAdmin_login_time(Date admin_login_time) {
		this.admin_login_time = admin_login_time;
	}
	public Integer getAdmin_login_num() {
		return admin_login_num;
	}
	public void setAdmin_login_num(Integer admin_login_num) {
		this.admin_login_num = admin_login_num;
	}
	public Integer getAdmin_is_super() {
		return admin_is_super;
	}
	public void setAdmin_is_super(Integer admin_is_super) {
		this.admin_is_super = admin_is_super;
	}
	public Integer getAdmin_gid() {
		return admin_gid;
	}
	public void setAdmin_gid(Integer admin_gid) {
		this.admin_gid = admin_gid;
	}
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_phone() {
		return admin_phone;
	}
	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}
	public Date getAdmin_create_time() {
		return admin_create_time;
	}
	public void setAdmin_create_time(Date admin_create_time) {
		this.admin_create_time = admin_create_time;
	}
	public Date getAdmin_update_time() {
		return admin_update_time;
	}
	public void setAdmin_update_time(Date admin_update_time) {
		this.admin_update_time = admin_update_time;
	}
	public Date getAdmin_last_time() {
		return admin_last_time;
	}
	public void setAdmin_last_time(Date admin_last_time) {
		this.admin_last_time = admin_last_time;
	}
	
	
	
	
	
}