package com.yd.dby.app.entity.para;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderAddDBStorePara {

	private Long orderSn;

	private Integer storeId;

	private String storeName;

	private Integer couponId;

	private BigDecimal couponPrice;

	private BigDecimal goodsPrice;

	private BigDecimal totalPrice;

	private BigDecimal refundPrice;

	private BigDecimal transportPrice;

	private String orderMessage;

	private Integer transportFid;

	private String transportAddress;

	private List<OrderAddDBGoodsPara> orderGoodsDB = new ArrayList<OrderAddDBGoodsPara>();

	public Long getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(Long orderSn) {
		this.orderSn = orderSn;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public BigDecimal getCouponPrice() {
		return couponPrice;
	}

	public void setCouponPrice(BigDecimal couponPrice) {
		this.couponPrice = couponPrice;
	}

	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getRefundPrice() {
		return refundPrice;
	}

	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}

	public List<OrderAddDBGoodsPara> getOrderGoodsDB() {
		return orderGoodsDB;
	}

	public void setOrderGoodsDB(List<OrderAddDBGoodsPara> orderGoodsDB) {
		this.orderGoodsDB = orderGoodsDB;
	}

	public BigDecimal getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(BigDecimal transportPrice) {
		this.transportPrice = transportPrice;
	}

	public String getOrderMessage() {
		return orderMessage;
	}

	public void setOrderMessage(String orderMessage) {
		this.orderMessage = orderMessage;
	}

	public Integer getTransportFid() {
		return transportFid;
	}

	public void setTransportFid(Integer transportFid) {
		this.transportFid = transportFid;
	}

	public String getTransportAddress() {
		return transportAddress;
	}

	public void setTransportAddress(String transportAddress) {
		this.transportAddress = transportAddress;
	}

}
