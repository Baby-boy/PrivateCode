package com.rabbit.service;

public interface SendMsgService {

	/**
	 * 说明: topic是通配符模式交换机(通配模式,"#"匹配一个或多个词,符号"*"匹配一个词)
	 * 
	 * 说明: direct是路由模式交换机(完全匹配模式,需要消费者的routingKey和发送者的routingKey完全匹配)
	 * 
	 * 说明: fanout是订阅模式交换机(交换机不做任何处理,只要和该交换机绑定的队列都能收到消息)
	 * 
	 * @param exchangeName(交换机名称)
	 * @param routingKey(路由key)
	 */
	public MessageSender sendMsgByExchange(String exchangeName, String routingKey);

}
