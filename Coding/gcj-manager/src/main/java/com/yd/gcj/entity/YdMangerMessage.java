package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerMessage {

	/**编号（主键）*/
	private Integer msg_id;
	/**类型（0系统消息 1任务消息 2留言消息 3评价消息）*/
	private Integer msg_type;
	/**发送消息方用户id（系统消息时该值为0*/
	private Integer msg_said;
	/**接收消息方用户id*/
	private Integer msg_sbid;
	/**消息内容*/
	private String msg_contents;
	/**任务id（消息与任务信息无关时该值为0）*/
	private Integer msg_tid;
	/**是否被删除*/
	private Integer msg_isdel;
	/**信息创建时间*/
	private Date msg_create_time;
	
	public YdMangerMessage(){}
	
	public YdMangerMessage(Integer id,Integer type,Integer said,Integer sbid,String contents,Integer tid,Integer isdel,Date createTime){
		msg_id = id!=null?id:0;
		msg_type = type!=null?type:0;
		msg_said = said!=null?said:0;
		msg_sbid = sbid!=null?sbid:0;
		msg_tid = tid!=null?tid:0;
		msg_contents = contents!=null?contents:null;
		msg_isdel = isdel!=null?isdel:0;
		msg_create_time = createTime!=null?createTime:new Date();
	}
	
	/**
	 * 获取 编号（主键）
	 * @return msg_id
	 */
	public Integer getMsg_id() {
		return msg_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param msg_id
	 */
	public void setMsg_id(Integer msg_id) {
		this.msg_id = msg_id;
	}
	
	/**
	 * 获取 类型（0系统消息1任务消息2留言消息3评价消息）
	 * @return msg_type
	 */
	public Integer getMsg_type() {
		return msg_type;
	}
	
	/**
	 * 设置 类型（0系统消息1任务消息2留言消息3评价消息）
	 * @param msg_type
	 */
	public void setMsg_type(Integer msg_type) {
		this.msg_type = msg_type;
	}
	
	/**
	 * 获取 发送消息方用户id（系统消息时该值为0
	 * @return msg_said
	 */
	public Integer getMsg_said() {
		return msg_said;
	}
	
	/**
	 * 设置 发送消息方用户id（系统消息时该值为0
	 * @param msg_said
	 */
	public void setMsg_said(Integer msg_said) {
		this.msg_said = msg_said;
	}
	
	/**
	 * 获取 接收消息方用户id
	 * @return msg_sbid
	 */
	public Integer getMsg_sbid() {
		return msg_sbid;
	}
	
	/**
	 * 设置 接收消息方用户id
	 * @param msg_sbid
	 */
	public void setMsg_sbid(Integer msg_sbid) {
		this.msg_sbid = msg_sbid;
	}
	
	/**
	 * 获取 消息内容
	 * @return msg_contents
	 */
	public String getMsg_contents() {
		return msg_contents;
	}
	
	/**
	 * 设置 消息内容
	 * @param msg_contents
	 */
	public void setMsg_contents(String msg_contents) {
		this.msg_contents = msg_contents;
	}
	
	/**
	 * 获取 任务id（消息与任务信息无关时该值为0）
	 * @return msg_tid
	 */
	public Integer getMsg_tid() {
		return msg_tid;
	}
	
	/**
	 * 设置 任务id（消息与任务信息无关时该值为0）
	 * @param msg_tid
	 */
	public void setMsg_tid(Integer msg_tid) {
		this.msg_tid = msg_tid;
	}
	
	/**
	 * 获取 是否被删除
	 * @return msg_isdel
	 */
	public Integer getMsg_isdel() {
		return msg_isdel;
	}
	

	/**
	 * 设置 是否被删除
	 * @param msg_isdel
	 */
	public void setMsg_isdel(Integer msg_isdel) {
		this.msg_isdel = msg_isdel;
	}

	/**
	 * 获取 信息创建时间
	 * @return msg_create_time
	 */
	public Date getMsg_create_time() {
		return msg_create_time;
	}
	

	/**
	 * 设置 信息创建时间
	 * @param msg_create_time
	 */
	public void setMsg_create_time(Date msg_create_time) {
		this.msg_create_time = msg_create_time;
	}
	
	
}
