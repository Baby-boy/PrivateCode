package com.yd.dby.c.member.entity;

import java.util.Date;

public class YdWish {
	
	private Integer user_id;//用户id
	private Integer wish_id;//许愿id
	private String wish_content;//许愿内容
	private Date wish_created_time;//创建时间
	
	private String user_nickname;//用户昵称
	
	public String getUser_nickname() {
		return user_nickname;
	}
	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getWish_id() {
		return wish_id;
	}
	public void setWish_id(Integer wish_id) {
		this.wish_id = wish_id;
	}
	public String getWish_content() {
		return wish_content;
	}
	public void setWish_content(String wish_content) {
		this.wish_content = wish_content;
	}
	public Date getWish_created_time() {
		return wish_created_time;
	}
	public void setWish_created_time(Date wish_created_time) {
		this.wish_created_time = wish_created_time;
	}//创建时间
}
