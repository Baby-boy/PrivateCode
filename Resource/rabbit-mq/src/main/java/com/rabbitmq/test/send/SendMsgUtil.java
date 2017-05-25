package com.rabbitmq.test.send;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class SendMsgUtil {

	private static Integer rabbitmqPort;
	private static String rabbitmqUsername;
	private static String rabbitmqPassword;
	private static String rabbitmqVhost;

	private static String exchangeName;
	private static String queueName;

	// 连接
	private static ConnectionFactory factory;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		Properties properties = new Properties();
		try {
			// 读取本地配置文件
			InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream("application.properties");
			properties.load(inputStream);
			inputStream.close();
			if (properties.containsKey("rabbitmq.port")) {
				rabbitmqPort = Integer.valueOf(properties.getProperty("rabbitmq.port"));
			}
			if (properties.containsKey("rabbitmq.username")) {
				rabbitmqUsername = properties.getProperty("rabbitmq.username");
			}
			if (properties.containsKey("rabbitmq.password")) {
				rabbitmqPassword = properties.getProperty("rabbitmq.password");
			}
			if (properties.containsKey("rabbitmq.vhost")) {
				rabbitmqVhost = properties.getProperty("rabbitmq.vhost");
			}
			if (properties.containsKey("rabbitmq.exchangeName")) {
				// 获取交换机
				exchangeName = properties.getProperty("rabbitmq.exchangeName");
			}
			if (properties.containsKey("rabbitmq.queueName")) {
				// 获取队列名称
				queueName = properties.getProperty("rabbitmq.queueName");
			}

			// 定义连接工厂
			factory = new ConnectionFactory();
			factory.setPort(rabbitmqPort);
			factory.setUsername(rabbitmqUsername);
			factory.setPassword(rabbitmqPassword);
			factory.setVirtualHost(rabbitmqVhost);
			// factory.setChannelCacheSize(100);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendMsg(String type) {
		try {
			// 创建连接并获取通道
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			// 定义交换机(通配符型)
			channel.exchangeDeclare(exchangeName, "topic");
			// 发送消息通知其它系统
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("time", System.currentTimeMillis());
			channel.basicPublish(exchangeName, type, null, MAPPER.writeValueAsBytes(map));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String acceptMsg() {
		try {
			// 创建连接并获取通道
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			// 声明队列
			channel.queueDeclare(queueName, false, false, false, null);
			// 绑定队列到交换机
			channel.queueBind(queueName, exchangeName, "#");
			// 同一时刻服务器只会发一条消息给消费者
			channel.basicQos(1);

			// 定义队列的消费者
			QueueingConsumer consumer = new QueueingConsumer(channel);
			// 监听队列，手动返回完成
			channel.basicConsume(queueName, false, consumer);

			// 获取消息
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			JsonNode jsonNode = MAPPER.readTree(message);
			String type = jsonNode.get("type").asText();
			Long time = jsonNode.get("time").asLong();

			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
			return "类型: " + type + ",时间: " + time;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "返回错误";
	}
}
