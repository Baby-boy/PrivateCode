package com.yd.dby.a.sys.entity;

import java.util.Date;

/** 
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月18日 上午11:06:50 
 * 
 */
public class YdSysThirdPartyLoginUser {

	/**
	 * 第三方登陆表的主键
	 */
	private Integer tpl_id;
	
	/**
	 * 用户的id
	 */
	private Integer tpl_user_id;
	
	/**
	 * 第三方登陆的类型
	 */
	private Integer tpl_type;
	
	/**
	 * 第三方返回的用户唯一标识
	 */
	private String tpl_openid;
	
	/**
	 * 第三方和用户关联的时间
	 */
	private Date tpl_created_time;

	public Integer getTpl_id() {
		return tpl_id;
	}

	public void setTpl_id(Integer tpl_id) {
		this.tpl_id = tpl_id;
	}

	public Integer getTpl_user_id() {
		return tpl_user_id;
	}

	public void setTpl_user_id(Integer tpl_user_id) {
		this.tpl_user_id = tpl_user_id;
	}

	public Integer getTpl_type() {
		return tpl_type;
	}

	public void setTpl_type(Integer tpl_type) {
		this.tpl_type = tpl_type;
	}

	public String getTpl_openid() {
		return tpl_openid;
	}

	public void setTpl_openid(String tpl_openid) {
		this.tpl_openid = tpl_openid;
	}

	public Date getTpl_created_time() {
		return tpl_created_time;
	}

	public void setTpl_created_time(Date tpl_created_time) {
		this.tpl_created_time = tpl_created_time;
	}

}
