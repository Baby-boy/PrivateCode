package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerAttention {

	/**编号（主键）*/
	private Integer att_id;
	/**用户自己id*/
	private Integer att_aid;
	/**被关注方id*/
	private Integer att_bid;
	/**被关注方用户姓名*/
	private String att_uname;
	/**公司名称*/
	private String att_pname;
	/**关注时间*/
	private Date att_create_time;
	/**被关注方头像*/
	private String attr_bavatar = "";
	
	/**
	 * 获取 编号（主键）
	 * @return att_id
	 */
	public Integer getAtt_id() {
		return att_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param att_id
	 */
	public void setAtt_id(Integer att_id) {
		this.att_id = att_id;
	}
	
	/**
	 * 获取 用户自己id
	 * @return att_aid
	 */
	public Integer getAtt_aid() {
		return att_aid;
	}
	
	/**
	 * 设置 用户自己id
	 * @param att_aid
	 */
	public void setAtt_aid(Integer att_aid) {
		this.att_aid = att_aid;
	}
	
	/**
	 * 获取 被关注方id
	 * @return att_bid
	 */
	public Integer getAtt_bid() {
		return att_bid;
	}
	
	/**
	 * 设置 被关注方id
	 * @param att_bid
	 */
	public void setAtt_bid(Integer att_bid) {
		this.att_bid = att_bid;
	}
	
	/**
	 * 获取 被关注方用户姓名
	 * @return att_uname
	 */
	public String getAtt_uname() {
		return att_uname;
	}
	
	/**
	 * 设置 被关注方用户姓名
	 * @param att_uname
	 */
	public void setAtt_uname(String att_uname) {
		this.att_uname = att_uname;
	}
	
	/**
	 * 获取 公司名称
	 * @return att_pname
	 */
	public String getAtt_pname() {
		return att_pname;
	}
	
	/**
	 * 设置 公司名称
	 * @param att_pname
	 */
	public void setAtt_pname(String att_pname) {
		this.att_pname = att_pname;
	}

	/**
	 * 获取 关注时间
	 * @return att_create_time
	 */
	public Date getAtt_create_time() {
		return att_create_time;
	}
	

	/**
	 * 设置 关注时间
	 * @param att_create_time
	 */
	public void setAtt_create_time(Date att_create_time) {
		this.att_create_time = att_create_time;
	}

	/**
	 * 获取 被关注方头像
	 * @return attr_bavatar
	 */
	public String getAttr_bavatar() {
		return attr_bavatar;
	}
	
	/**
	 * 设置 被关注方头像
	 * @param attr_bavatar
	 */
	public void setAttr_bavatar(String attr_bavatar) {
		this.attr_bavatar = attr_bavatar;
	}
	
}
