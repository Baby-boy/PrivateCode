package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerTaskActive {

	/**编号（主键）*/
	private Integer taska_id;
	/**任务id*/
	private Integer taska_tid;
	/**发送者*/
	private Integer taska_auid;
	/**被回复者id，直接评论任务该值为0*/
	private Integer taska_buid;
	/**内容*/
	private String taska_contents;
	/**任务评论组*/
	private Integer taska_group;
	/**评论时间*/
	private Date taska_create_time;
	/**
	 * 获取 编号（主键）
	 * @return taska_id
	 */
	public Integer getTaska_id() {
		return taska_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param taska_id
	 */
	public void setTaska_id(Integer taska_id) {
		this.taska_id = taska_id;
	}
	
	/**
	 * 获取 任务id
	 * @return taska_tid
	 */
	public Integer getTaska_tid() {
		return taska_tid;
	}
	
	/**
	 * 设置 任务id
	 * @param taska_tid
	 */
	public void setTaska_tid(Integer taska_tid) {
		this.taska_tid = taska_tid;
	}
	
	/**
	 * 获取 发送者
	 * @return taska_auid
	 */
	public Integer getTaska_auid() {
		return taska_auid;
	}
	
	/**
	 * 设置 发送者
	 * @param taska_auid
	 */
	public void setTaska_auid(Integer taska_auid) {
		this.taska_auid = taska_auid;
	}
	
	/**
	 * 获取 被回复者id，直接评论任务该值为0
	 * @return taska_buid
	 */
	public Integer getTaska_buid() {
		return taska_buid;
	}
	
	/**
	 * 设置 被回复者id，直接评论任务该值为0
	 * @param taska_buid
	 */
	public void setTaska_buid(Integer taska_buid) {
		this.taska_buid = taska_buid;
	}
	
	/**
	 * 获取 内容
	 * @return taska_contents
	 */
	public String getTaska_contents() {
		return taska_contents;
	}
	
	/**
	 * 设置 内容
	 * @param taska_contents
	 */
	public void setTaska_contents(String taska_contents) {
		this.taska_contents = taska_contents;
	}
	
	/**
	 * 获取 任务评论组
	 * @return taska_group
	 */
	public Integer getTaska_group() {
		return taska_group;
	}
	

	/**
	 * 设置 任务评论组
	 * @param taska_group
	 */
	public void setTaska_group(Integer taska_group) {
		this.taska_group = taska_group;
	}
	

	/**
	 * 获取 评论时间
	 * @return taska_create_time
	 */
	public Date getTaska_create_time() {
		return taska_create_time;
	}
	

	/**
	 * 设置 评论时间
	 * @param taska_create_time
	 */
	public void setTaska_create_time(Date taska_create_time) {
		this.taska_create_time = taska_create_time;
	}
	
}
