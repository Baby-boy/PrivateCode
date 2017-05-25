package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerMessageTR {
	
	/**消息与用户关系信息id*/
	private Integer msgtr_id;
	/**消息id*/
	private Integer msgtr_mid;
	/**用户id*/
	private Integer msgtr_uid;
	/**状态（0位删除、1已删除）*/
	private Integer msgtr_isdel;
	/**信息读取时间*/
	private Date msgtr_create_time;
	
	/**
	 * 获取 消息与用户关系信息id
	 * @return msgtr_id
	 */
	public Integer getMsgtr_id() {
		return msgtr_id;
	}
	
	/**
	 * 设置 消息与用户关系信息id
	 * @param msgtr_id
	 */
	public void setMsgtr_id(Integer msgtr_id) {
		this.msgtr_id = msgtr_id;
	}
	
	/**
	 * 获取 消息id
	 * @return msgtr_mid
	 */
	public Integer getMsgtr_mid() {
		return msgtr_mid;
	}
	
	/**
	 * 设置 消息id
	 * @param msgtr_mid
	 */
	public void setMsgtr_mid(Integer msgtr_mid) {
		this.msgtr_mid = msgtr_mid;
	}
	
	/**
	 * 获取 用户id
	 * @return msgtr_uid
	 */
	public Integer getMsgtr_uid() {
		return msgtr_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param msgtr_uid
	 */
	public void setMsgtr_uid(Integer msgtr_uid) {
		this.msgtr_uid = msgtr_uid;
	}
	
	/**
	 * 获取 状态（0位删除、1已删除）
	 * @return msgtr_isdel
	 */
	public Integer getMsgtr_isdel() {
		return msgtr_isdel;
	}
	
	/**
	 * 设置 状态（0位删除、1已删除）
	 * @param msgtr_isdel
	 */
	public void setMsgtr_isdel(Integer msgtr_isdel) {
		this.msgtr_isdel = msgtr_isdel;
	}

	/**
	 * 获取 信息读取时间
	 * @return msgtr_create_time
	 */
	public Date getMsgtr_create_time() {
		return msgtr_create_time;
	}
	
	/**
	 * 设置 信息读取时间
	 * @param msgtr_create_time
	 */
	public void setMsgtr_create_time(Date msgtr_create_time) {
		this.msgtr_create_time = msgtr_create_time;
	}
	
}
