package com.rabbitmq.test.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

// 通配符模式rabbitMQ
public class SendMsgUtil {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	public void sendMsg(Long itemId, String type) {
		try {
			// 发送消息通知其它系统
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemId", itemId);
			map.put("type", type);
			map.put("date", System.currentTimeMillis());
			this.rabbitTemplate.convertAndSend("item." + type, MAPPER.writeValueAsString(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
