/**
 * @author zhangshuang
 * 2017年4月18日 上午10:15:01
 */
package com.glanway.iclock.entity.task;

import java.util.Date;

import com.glanway.iclock.entity.BaseEntity;

/**
 * 说明 : 任务实体
 * 
 * @author zhangshaung
 * @version 1.0.0
 * @dateTime 2017年4月18日 上午10:15:01
 */
public class Task extends BaseEntity{
	 /**
	 * @author zhangshuang
	 * 2017年4月18日 下午12:04:17
	 */
	private static final long serialVersionUID = -769738822427404731L;

	/*
	  * 设备序列号
	  */
	private String sn;
	
	/*
	 * 状态(1:未处理, 2:处理中, 3:已完成)
	 */
	private Integer state;
	
	/*
	 * 命令
	 */
	private String command;
	
	/*
	 * 命令参数
	 */
	private String 	args;
	
	/*
	 * 开始处理时间
	 */
	private Date startHandleTime;
	
	/*
	 * 完成时间
	 */
	private Date completeTime;
	
	/*
	 * Batch执行日
	 */
	private Date batchDate;
	
	/*
	 * 是否删除 0.否;1.是
	 */
	private String deleted;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getArgs() {
		return args;
	}

	public void setArgs(String args) {
		this.args = args;
	}

	public Date getStartHandleTime() {
		return startHandleTime;
	}

	public void setStartHandleTime(Date startHandleTime) {
		this.startHandleTime = startHandleTime;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public Date getBatchDate() {
		return batchDate;
	}

	public void setBatchDate(Date batchDate) {
		this.batchDate = batchDate;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}
	
	

}