package com.rabbitmq.test.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ConnectUtil {

	public static Connection getConnection() throws Exception {
		// 定义连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		// 设置服务地址
		factory.setHost("127.0.0.1");
		// 端口
		factory.setPort(5672);
		// 设置账号信息，用户名、密码、vhost
		factory.setVirtualHost("baby");
		factory.setUsername("baby");
		factory.setPassword("baby");
		// 通过工程获取连接
		Connection connection = factory.newConnection();
		return connection;
	}
}
