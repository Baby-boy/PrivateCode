package com.yd.dby.app.entity.vo;

import java.math.BigDecimal;

public class ChargeVo {

	//
	String orderNo;

	// 支付金额
	BigDecimal amount;

	// 商品的标题，该参数最长为 32 个 Unicode 字符，银联全渠道（ upacp / upacp_wap ）限制在 32 个字节。
	String subject;

	// 商品的描述信息，该参数最长为 128 个 Unicode 字符，yeepay_wap 对于该参数长度限制为 100 个 Unicode 字符。
	String body;

	// 支付使用的第三方支付渠道(微信：weipay，支付宝：alipay，银联：unionpay)
	String channel;

	// 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1
	String clientIp;

	// 1 普通商品 ,2 ctc商品（放入元数据）
	Integer metadataPayType;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Integer getMetadataPayType() {
		return metadataPayType;
	}

	public void setMetadataPayType(Integer metadataPayType) {
		this.metadataPayType = metadataPayType;
	}

}
