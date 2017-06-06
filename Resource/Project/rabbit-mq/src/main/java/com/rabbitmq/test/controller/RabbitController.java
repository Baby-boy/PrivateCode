package com.rabbitmq.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabbitmq.test.send.SendMsgUtil;

@RestController
public class RabbitController {

	@RequestMapping("send")
	public String send(@RequestParam("type") String type) {
		SendMsgUtil.sendMsg(type);
		return "发送成功";
	}

	@RequestMapping("accept")
	public String accept() {
		String msg = SendMsgUtil.acceptMsg();
		return msg;
	}
}
