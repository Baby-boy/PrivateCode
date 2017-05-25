package com.yd.gcj.system.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.vo.YdMangerMessageVo;

public interface YdMangerMapperSystemMessage {

	/**
	 * description(查询用户消息的信息)
	 * @param
	 * @return
	 */
	List<YdMangerMessageVo> queryAllMessage(HashMap<String, Object> map);

	/**
	 * description(添加系统消息)
	 * @param
	 * @param ydMangerMessage
	 * @return
	 */
	Integer addMessage(YdMangerMessage ydMangerMessage);

	/**
	 * description(根据指定的msg_id删除消息)
	 * @param
	 * @param ydMangerMessageVo
	 * @return
	 */
	Integer deleteMessageByMsgId(Integer msg_id);

	/**
	 * description(查询指定的消息)
	 * @param
	 * @param msg_id
	 * @return
	 */
	YdMangerMessageVo queryMessageByMsgId(@Param("msg_id")Integer msg_id);

	/**
	 * description(查询系统消息信息)
	 * @param
	 * @return
	 */						
	List<YdMangerMessageVo> queryAllMessageAdmin(HashMap<String,Object> map
			);

	/**
	 * description(根据指定的msg_id修改系统消息)
	 * @param
	 * @param ydMangerMessageVo
	 * @return
	 */
	Integer updateAdminMessageByMsgId(YdMangerMessageVo ydMangerMessageVo);

}
