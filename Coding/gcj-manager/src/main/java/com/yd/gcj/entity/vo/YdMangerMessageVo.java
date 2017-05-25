package com.yd.gcj.entity.vo;

import com.yd.gcj.entity.YdMangerMessage;

public class YdMangerMessageVo extends YdMangerMessage{

	//发送方用户名称
	private String user_name;

	//接收方用户名称
	private String user_name_recive;
	
	//发送方管理员名称
	private String admin_name;
	
	//发送方昵称
	private String user_nickname;
	
	//接收方昵称
	private String user_nickname_receive;
	
	public String getUser_nickname_receive() {
		return user_nickname_receive;
	}

	public void setUser_nickname_receive(String user_nickname_receive) {
		this.user_nickname_receive = user_nickname_receive;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	//任务编号
	private String task_num;
	
	/**任务名称*/
	private String task_name;
	
	
	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getTask_num() {
		return task_num;
	}

	public void setTask_num(String task_num) {
		this.task_num = task_num;
	}

	public String getUser_name_recive() {
		return user_name_recive;
	}

	public void setUser_name_recive(String user_name_recive) {
		this.user_name_recive = user_name_recive;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	/**
	 * 获取 任务名称
	 * @return task_name
	 */
	public String getTask_name() {
		return task_name;
	}
	

	/**
	 * 设置 任务名称
	 * @param task_name
	 */
	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}
	

}
