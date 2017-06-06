package com.rabbit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rabbit.common.DetailRes;
import com.rabbit.service.ConsumerMsgService;
import com.rabbit.util.ConnectionFactoryUtil;

@RestController
@RequestMapping("message")
public class ConsumerController {

	@Autowired
	private ConsumerMsgService consumerMsgService;

	@RequestMapping("consumer")
	public String consumerMsg() {
		String routingKey = "delete#";
		DetailRes detailRes = consumerMsgService.consumerMsg(ConnectionFactoryUtil.exchangeName, ConnectionFactoryUtil.queueName, routingKey).consumer();
		return detailRes.toString();
	}

}
