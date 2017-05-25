package com.rabbit.util;

import java.io.IOException;
import java.util.Properties;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.core.io.ClassPathResource;

public class MyConnectionFactory {

	// private static String rabbitmqHostName;// IP地址
	// private static Integer rabbitmqPort;// 端口号(默认为5672)
	// private static String rabbitmqUsername;// 用户名
	// private static String rabbitmqPassword;// 密码
	// private static String rabbitmqVhost;// 存储空间

	public static ConnectionFactory getConnectionFactory() {
		Properties properties = new Properties();

		try {
			ClassPathResource resource = new ClassPathResource("rabbitmq.properties");
			properties.load(resource.getInputStream());
		} catch (IOException e) {
			throw new RuntimeException("Failed to load rabbitmq.properties!");
		}

		String rabbitmqHostName = properties.getProperty("rabbitmq.hostName");
		Integer rabbitmqPort = Integer.valueOf(properties.getProperty("rabbitmq.port"));
		String rabbitmqUsername = properties.getProperty("rabbitmq.username");
		String rabbitmqPassword = properties.getProperty("rabbitmq.password");
		String rabbitmqVhost = properties.getProperty("rabbitmq.vhost");

		CachingConnectionFactory factory = new CachingConnectionFactory(rabbitmqHostName, rabbitmqPort);
		factory.setUsername(rabbitmqUsername);
		factory.setPassword(rabbitmqPassword);
		factory.setVirtualHost(rabbitmqVhost);
		factory.setPublisherConfirms(true);// 使用确认模式
		factory.getRabbitConnectionFactory().setAutomaticRecoveryEnabled(true);// 设置自动恢复启用
		return factory;
	}
}
