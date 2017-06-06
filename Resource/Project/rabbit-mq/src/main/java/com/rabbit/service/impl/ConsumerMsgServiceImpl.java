package com.rabbit.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.common.Constants;
import com.rabbit.common.DetailRes;
import com.rabbit.service.ConsumerMsgService;
import com.rabbit.service.MessageConsumer;
import com.rabbit.util.ConnectionFactoryUtil;
import com.rabbit.util.MyConnectionFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

@Service("consumerMsgService")
public class ConsumerMsgServiceImpl implements ConsumerMsgService {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public MessageConsumer consumerMsg(String exchangeName, String queueName, String routingKey) {
		return consumerMsg(exchangeName, queueName, routingKey, ConnectionFactoryUtil.exchangeType);
	}

	private MessageConsumer consumerMsg(String exchangeName, final String queueName, String routingKey, String exchangeType) {
		ConnectionFactory connectionFactory = MyConnectionFactory.getConnectionFactory();
		final Connection connection = connectionFactory.createConnection();

		buildChannel(connection, exchangeName, queueName, routingKey, exchangeType);

		return new MessageConsumer() {
			QueueingConsumer consumer;

			{
				consumer = buildQueueConsumer(connection, queueName);
			}

			@Override
			public DetailRes consumer() {
				QueueingConsumer.Delivery delivery = null;
				Channel channel = consumer.getChannel();
				try {
					// 通过delivery获取原始数据
					delivery = consumer.nextDelivery();
					String message = new String(delivery.getBody());

					DetailRes detailRes;
					// 将原始数据转换为特定类型的包
					if (StringUtils.isNotEmpty(message)) {
						System.out.println(message);
						JsonNode jsonNode = MAPPER.readTree(message);
						String routingKey = jsonNode.get("routingKey").asText();
						String objId = jsonNode.get("objId").asText();
						String createtime = jsonNode.get("createtime").asText();
						// TODO 具体获取数据后的处理
						System.out.println("主键: " + objId + ",路由key: " + routingKey + ",创建时间: " + createtime);

						detailRes = new DetailRes(true, "Consumer messge success!");
						// 手动发送ack确认
						channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
					} else {
						detailRes = new DetailRes(true, "Consumer messge failed!");
						// 避免过多失败log
						Thread.sleep(Constants.ONE_SECOND);
						channel.basicNack(delivery.getEnvelope().getDeliveryTag(), false, true);
					}

					return detailRes;
				} catch (Exception e) {
					e.printStackTrace();
					return new DetailRes(false, "exception " + e.toString());
				} finally {
					try {
						channel.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		};
	}

	private void buildChannel(Connection connection, String exchangeName, String queueName, String routingKey, String exchangeType) {
		try {
			// 创建通道并定义交换机
			Channel channel = connection.createChannel(false);
			channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);

			channel.queueDeclare(queueName, true, false, false, null);
			channel.queueBind(queueName, exchangeName, routingKey);

		} catch (Exception e) {
			throw new RuntimeException("Failed to declare exchange!");
		}
	}

	private QueueingConsumer buildQueueConsumer(Connection connection, String queueName) {
		try {
			Channel channel = connection.createChannel(false);
			final QueueingConsumer consumer = new QueueingConsumer(channel);

			// 通过 BasicQos 方法设置prefetchCount = 1。这样RabbitMQ就会使得每个Consumer在同一个时间点最多处理一个Message。
			// 换句话说，在接收到该Consumer的ack前，他它不会将新的Message分发给它
			channel.basicQos(1);
			channel.basicConsume(queueName, false, consumer);

			return consumer;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO 打印日志
			try {
				Thread.sleep(Constants.ONE_SECOND);
			} catch (InterruptedException inE) {
				inE.printStackTrace();
			}
			// 休眠后尝试重新获取
			return buildQueueConsumer(connection, queueName);
		}
	}
}
