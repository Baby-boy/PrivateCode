package com.rabbit.service;

import com.rabbit.common.DetailRes;
import com.rabbit.entity.RabbitMqMessage;

/**
 * 说明: 这是一个消息的回调
 * 
 * @Version:1.0
 */
public interface MessageSender {

	public DetailRes send(RabbitMqMessage mqMessage);
}
