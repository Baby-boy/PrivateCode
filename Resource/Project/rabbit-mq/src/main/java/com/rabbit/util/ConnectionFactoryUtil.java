package com.rabbit.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class ConnectionFactoryUtil {

	public static String exchangeName;// 交换机名称
	public static String exchangeType;// 交换机类型(topic:通配符类型,direct:完全匹配类型 ,fanout:不处理类型,绑定即发送 )
	public static String queueName;// 消息队列

	static {
		Properties properties = new Properties();

		try {
			ClassPathResource resource = new ClassPathResource("rabbitmq.properties");
			properties.load(resource.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("Failed to load rabbitmq.properties!");
		}

		if (properties.containsKey("rabbitmq.exchangeName")) {
			exchangeName = properties.getProperty("rabbitmq.exchangeName");
		}
		if (properties.containsKey("rabbitmq.exchangeType")) {
			exchangeType = properties.getProperty("rabbitmq.exchangeType");
		}
		if (properties.containsKey("rabbitmq.queueName")) {
			queueName = properties.getProperty("rabbitmq.queueName");
		}

	}
}
