package com.rabbit.service.impl;

import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbit.common.Constants;
import com.rabbit.common.DetailRes;
import com.rabbit.entity.RabbitMqMessage;
import com.rabbit.service.MessageSender;
import com.rabbit.service.SendMsgService;
import com.rabbit.util.ConnectionFactoryUtil;
import com.rabbit.util.JedisUtil;
import com.rabbit.util.MyConnectionFactory;
import com.rabbitmq.client.Channel;

@Service("sendMsgService")
public class SendMsgServiceImpl implements SendMsgService {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public MessageSender sendMsgByExchange(String exchangeName, String routingKey) {
		return sendMsgByExchange(exchangeName, routingKey, ConnectionFactoryUtil.exchangeType);
	}

	private MessageSender sendMsgByExchange(String exchangeName, String routingKey, String exchangeType) {
		ConnectionFactory connectionFactory = MyConnectionFactory.getConnectionFactory();
		Connection connection = connectionFactory.createConnection();

		buildChannel(connection, exchangeName, exchangeType);

		// 创建模板
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		// rabbitTemplate.setMandatory(true);
		// rabbitTemplate.setExchange(exchangeName);
		// rabbitTemplate.setRoutingKey(routingKey);

		// 设置发送确认回调
		rabbitTemplate.setConfirmCallback(new ConfirmCallback() {

			@Override
			public void confirm(CorrelationData correlationData, boolean ack, String cause) {

				if (ack) {
					// 消息发送成功,删除缓存
					JedisUtil.del(correlationData.getId());
				} else {
					// 消费方未确认消费消息
				}
			}
		});

		// 设置失败后返回回调
		rabbitTemplate.setReturnCallback(new ReturnCallback() {

			@Override
			public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
				try {
					Thread.sleep(Constants.ONE_SECOND);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 重新发送消息
				rabbitTemplate.send(message);
			}
		});

		return new MessageSender() {

			@Override
			public DetailRes send(RabbitMqMessage mqMessage) {
				try {
					String key = mqMessage.getObjId();
					JedisUtil.set(key, MAPPER.writeValueAsString(mqMessage), 60 * 60 * 24 * 3);
					rabbitTemplate.correlationConvertAndSend(MAPPER.writeValueAsString(mqMessage), new CorrelationData(key));
				} catch (Exception e) {
					return new DetailRes(false, "Send messge failed!");
				}
				return new DetailRes(true, "Send messge success!");
			}

		};
	}

	private void buildChannel(Connection connection, String exchangeName, String exchangeType) {
		Channel channel = null;
		try {
			// 创建通道并定义交换机
			channel = connection.createChannel(false);
			channel.exchangeDeclare(exchangeName, exchangeType, true, false, null);

		} catch (IOException e) {
			throw new RuntimeException("Failed to declare exchange!");
		} finally {
			try {
				channel.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
