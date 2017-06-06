package com.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.common.DetailRes;
import com.rabbit.entity.RabbitMqMessage;
import com.rabbit.service.SendMsgService;
import com.rabbit.util.ConnectionFactoryUtil;
import com.rabbit.util.UUIDUtil;

@RestController
@RequestMapping("message")
public class SendController {

	@Autowired
	private SendMsgService sendMsgService;

	@RequestMapping("send")
	public String sendMsg(@RequestParam("routingKey") String routingKey) {
		RabbitMqMessage mqMessage = new RabbitMqMessage();
		String key = UUIDUtil.generate();
		// String routingKey = "delete";
		mqMessage.setObjId(key);
		mqMessage.setRoutingKey(routingKey);
		mqMessage.setCreatetime(Long.valueOf(System.currentTimeMillis()).toString());
		DetailRes detailRes = sendMsgService.sendMsgByExchange(ConnectionFactoryUtil.exchangeName, routingKey).send(mqMessage);
		return detailRes.toString();
	}

}
