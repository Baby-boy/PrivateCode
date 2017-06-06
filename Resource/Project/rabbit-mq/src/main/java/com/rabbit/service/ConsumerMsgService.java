package com.rabbit.service;

public interface ConsumerMsgService {

	/**
	 * 说明: 消费消息
	 * 
	 * @param exchangeName(交换机名称)
	 * @param queueName(消息队列名称)
	 * @param routingKey(路由key)
	 */
	public MessageConsumer consumerMsg(String exchangeName, String queueName, String routingKey);
}
