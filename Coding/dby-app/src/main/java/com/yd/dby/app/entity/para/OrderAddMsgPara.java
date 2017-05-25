package com.yd.dby.app.entity.para;

import java.util.ArrayList;
import java.util.List;

public class OrderAddMsgPara {

	// 1 普通商品支付 ,2 ctc商品支付
	private Integer metadataPayType;

	private Integer userId;

	private String userUsername;

	// 支付方式
	private String paymentCode;

	// 发票类型 1:没有 2:公司 3:个人
	private Byte invoiceType;

	// 发票抬头
	private String invoiceNo;

	// 收货人姓名
	private String receiptName;

	// 收货人手机号
	private String receiptMobile;

	// 收货地址
	private String receiptAddress;

	// 使用积分
	private Integer integral;

	// 物流公司
	private String shippingExpress;

	// 1:普通订单 2:团购 3:秒杀 4:拍卖 5:养护
	private Byte type=1;

	// 订单里的店铺
	private List<OrderAddMsgStorePara> orderStore = new ArrayList<>();

	public Integer getMetadataPayType() {
		return metadataPayType;
	}

	public void setMetadataPayType(Integer metadataPayType) {
		this.metadataPayType = metadataPayType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserUsername() {
		return userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getPaymentCode() {
		return paymentCode;
	}

	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}

	public Byte getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Byte invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getReceiptName() {
		return receiptName;
	}

	public void setReceiptName(String receiptName) {
		this.receiptName = receiptName;
	}

	public String getReceiptMobile() {
		return receiptMobile;
	}

	public void setReceiptMobile(String receiptMobile) {
		this.receiptMobile = receiptMobile;
	}

	public String getReceiptAddress() {
		return receiptAddress;
	}

	public void setReceiptAddress(String receiptAddress) {
		this.receiptAddress = receiptAddress;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public List<OrderAddMsgStorePara> getOrderStore() {
		return orderStore;
	}

	public void setOrderStore(List<OrderAddMsgStorePara> orderStore) {
		this.orderStore = orderStore;
	}

	public String getShippingExpress() {
		return shippingExpress;
	}

	public void setShippingExpress(String shippingExpress) {
		this.shippingExpress = shippingExpress;
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

}
