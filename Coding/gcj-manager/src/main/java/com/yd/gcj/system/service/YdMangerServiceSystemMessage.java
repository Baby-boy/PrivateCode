package com.yd.gcj.system.service;

import java.util.HashMap;
import java.util.List;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.vo.YdMangerMessageVo;

public interface YdMangerServiceSystemMessage {

	/**
	 * description(查询用户消息信息)
	 * @param
	 * @return
	 */
	List<YdMangerMessageVo> queryAllMessage(HashMap<String, Object> map);

	/**
	 * description(添加系统消息)
	 * @param ydMangerMessage 
	 * @param
	 * @return
	 */
	Integer addMessage(YdMangerMessage ydMangerMessage);
	
	/**
	 * description(查询指定的消息)
	 * @param
	 * @param msg_id
	 * @return
	 */
	YdMangerMessageVo queryMesssageByMsgId(Integer msg_id);

	/**
	 * description(根据指定的msg_id修改消息)
	 * @param
	 * @param ydMangerMessageVo
	 * @return
	 */
	Integer deleteMessageByMsgId(Integer msg_id);

	/**
	 * description(查询所有的系统消息)
	 * @param
	 * @return
	 */
	List<YdMangerMessageVo> queryAllMessageAdmin(HashMap<String, Object> map);

	/**
	 * description(根据指定的msg_id修改系统消息)
	 * @param
	 * @param ydMangerMessageVo
	 * @return
	 */
	Integer updateAdminMessageByMsgId(YdMangerMessageVo ydMangerMessageVo);

}
