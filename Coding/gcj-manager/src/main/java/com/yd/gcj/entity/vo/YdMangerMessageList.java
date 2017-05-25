package com.yd.gcj.entity.vo;

import java.util.List;

public class YdMangerMessageList {
	
	/**任务消息（包括任务消息、评价消息）*/
	private List<YdMangerMessageVo> taskMessage;
	
	/**系统消息*/
	private List<YdMangerMessageVo> systemMessage;
	
	/**用户留言消息*/
	private List<YdMangerMessageVo> userMessage;

	/**
	 * 获取 任务消息（包括任务消息、评价消息）
	 * @return taskMessage
	 */
	public List<YdMangerMessageVo> getTaskMessage() {
		return taskMessage;
	}
	/**
	 * 设置 任务消息（包括任务消息、评价消息）
	 * @param taskMessage
	 */
	public void setTaskMessage(List<YdMangerMessageVo> taskMessage) {
		this.taskMessage = taskMessage;
	}
	/**
	 * 获取 系统消息
	 * @return systemMessage
	 */
	public List<YdMangerMessageVo> getSystemMessage() {
		return systemMessage;
	}
	/**
	 * 设置 系统消息
	 * @param systemMessage
	 */
	public void setSystemMessage(List<YdMangerMessageVo> systemMessage) {
		this.systemMessage = systemMessage;
	}
	/**
	 * 获取 用户留言消息
	 * @return userMessage
	 */
	public List<YdMangerMessageVo> getUserMessage() {
		return userMessage;
	}
	/**
	 * 设置 用户留言消息
	 * @param userMessage
	 */
	public void setUserMessage(List<YdMangerMessageVo> userMessage) {
		this.userMessage = userMessage;
	}
}
