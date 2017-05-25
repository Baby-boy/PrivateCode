package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerTaskMsg {

	/**编号（主键）*/
	private Integer taskmsg_id;
	/**任务id*/
	private Integer taskmsg_tid;
	/**公告内容*/
	private String taskmsg_contents;
	/**用户id*/
	private Integer taskmsg_uid;
	/**用户类型（雇主or服务商）*/
	private Integer taskmsg_utype;
	/**发布时间*/
	private Date taskmsg_create_time;
	
	
	/**
	 * 获取 编号（主键）
	 * @return taskmsg_id
	 */
	public Integer getTaskmsg_id() {
		return taskmsg_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param taskmsg_id
	 */
	public void setTaskmsg_id(Integer taskmsg_id) {
		this.taskmsg_id = taskmsg_id;
	}
	
	/**
	 * 获取 任务id
	 * @return taskmsg_tid
	 */
	public Integer getTaskmsg_tid() {
		return taskmsg_tid;
	}
	
	/**
	 * 设置 任务id
	 * @param taskmsg_tid
	 */
	public void setTaskmsg_tid(Integer taskmsg_tid) {
		this.taskmsg_tid = taskmsg_tid;
	}
	
	/**
	 * 获取 公告内容
	 * @return taskmsg_contents
	 */
	public String getTaskmsg_contents() {
		return taskmsg_contents;
	}
	
	/**
	 * 设置 公告内容
	 * @param taskmsg_contents
	 */
	public void setTaskmsg_contents(String taskmsg_contents) {
		this.taskmsg_contents = taskmsg_contents;
	}
	
	/**
	 * 获取 用户id
	 * @return taskmsg_uid
	 */
	public Integer getTaskmsg_uid() {
		return taskmsg_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param taskmsg_uid
	 */
	public void setTaskmsg_uid(Integer taskmsg_uid) {
		this.taskmsg_uid = taskmsg_uid;
	}
	
	/**
	 * 获取 用户类型（雇主or服务商）
	 * @return taskmsg_utype
	 */
	public Integer getTaskmsg_utype() {
		return taskmsg_utype;
	}
	
	/**
	 * 设置 用户类型（雇主or服务商）
	 * @param taskmsg_utype
	 */
	public void setTaskmsg_utype(Integer taskmsg_utype) {
		this.taskmsg_utype = taskmsg_utype;
	}
	
	/**
	 * 获取 发布时间
	 * @return taskmsg_create_time
	 */
	public Date getTaskmsg_create_time() {
		return taskmsg_create_time;
	}
	
	/**
	 * 设置 发布时间
	 * @param taskmsg_create_time
	 */
	public void setTaskmsg_create_time(Date taskmsg_create_time) {
		this.taskmsg_create_time = taskmsg_create_time;
	}
	
}
