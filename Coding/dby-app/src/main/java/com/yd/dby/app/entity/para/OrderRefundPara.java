package com.yd.dby.app.entity.para;

import java.math.BigDecimal;

public class OrderRefundPara {

	// 订单(yd_order)ID
	private Integer orderId;

	// 订单商品(yd_order_goods)ID
	private Integer ogId;

	// 申请类型(1.退货退款 2.仅退款 3.换货 4.维修)
	private byte refundType;

	// 申请售后的商品数量
	private Integer goodsNum;

	// 金额
	private BigDecimal refundAmount;

	// 凭证图片
	private String picInfo;

	// 原因
	private String buyerMessage;

	private Integer userId;

	private Byte goodsState;

	private Integer reasonId;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOgId() {
		return ogId;
	}

	public void setOgId(Integer ogId) {
		this.ogId = ogId;
	}

	public byte getRefundType() {
		return refundType;
	}

	public void setRefundType(byte refundType) {
		this.refundType = refundType;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(String picInfo) {
		this.picInfo = picInfo;
	}

	public String getBuyerMessage() {
		return buyerMessage;
	}

	public void setBuyerMessage(String buyerMessage) {
		this.buyerMessage = buyerMessage;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Byte getGoodsState() {
		return goodsState;
	}

	public void setGoodsState(Byte goodsState) {
		this.goodsState = goodsState;
	}

	public Integer getReasonId() {
		return reasonId;
	}

	public void setReasonId(Integer reasonId) {
		this.reasonId = reasonId;
	}

}
