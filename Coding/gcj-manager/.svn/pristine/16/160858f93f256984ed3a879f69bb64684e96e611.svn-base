package com.yd.gcj.system.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.gcj.entity.YdMangerMessage;
import com.yd.gcj.entity.vo.YdMangerMessageVo;
import com.yd.gcj.system.mapper.YdMangerMapperSystemMessage;
import com.yd.gcj.system.service.YdMangerServiceSystemMessage;

@Service("ydMangerServiceSystemMessage")
public class YdMangerServiceImplSystemMessage implements YdMangerServiceSystemMessage {

	@Autowired
	private YdMangerMapperSystemMessage ydMangerMapperSystemMessage;
	
	//查询用户消息信息
	@Override
	public List<YdMangerMessageVo> queryAllMessage(HashMap<String, Object> map) {
		List<YdMangerMessageVo> messageList = ydMangerMapperSystemMessage.queryAllMessage(map);
		return messageList;
	}

	//添加系统消息
	@Override
	public Integer addMessage(YdMangerMessage ydMangerMessage) {
		Integer addNum = ydMangerMapperSystemMessage.addMessage(ydMangerMessage);
		return addNum;
	}

	//删除指定的消息(修改消息中的一个字段的状态)
	@Override
	public Integer deleteMessageByMsgId(Integer msg_id) {
		
		Integer updateNum = ydMangerMapperSystemMessage.deleteMessageByMsgId(msg_id);
		return updateNum;
	}

	//查询指定的消息
	@Override
	public YdMangerMessageVo queryMesssageByMsgId(Integer msg_id) {
		YdMangerMessageVo ydMangerMessageVo = ydMangerMapperSystemMessage.queryMessageByMsgId(msg_id);
		
		return ydMangerMessageVo;
	}

	//查询系统消息
	@Override
	public List<YdMangerMessageVo> queryAllMessageAdmin(HashMap<String,Object> map) {
		List<YdMangerMessageVo> messageList = ydMangerMapperSystemMessage.queryAllMessageAdmin(map);
		return messageList;
	}

	//修改系统消息
	@Override
	public Integer updateAdminMessageByMsgId(YdMangerMessageVo ydMangerMessageVo) {
		Integer updateNum = ydMangerMapperSystemMessage.updateAdminMessageByMsgId(ydMangerMessageVo);
		return updateNum;
	}

	
}
