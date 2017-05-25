package com.yd.dby.app.entity.para;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderAddMsgStorePara {

	// 店铺ID
	private Integer storeId;

	// 优惠券ID
	private Integer couponId;

	// 优惠券金额
	private BigDecimal couponPrice;

	// 卖家留言
	private String orderMessage;

	// 0:送货上门 1:自提店id
	private Integer transportFid;

	// 自提地址
	private String transportAddress;

	// // 商品价格
	// private BigDecimal goodsPrice;
	//
	// // 总价(支付此店铺的实际价格)
	// private BigDecimal totalPrice;
	//
	// 运费
	// private BigDecimal transportPrice;

	// 订单店铺里的商品
	private List<OrderAddMsgGoodsPara> goods = new ArrayList<>();

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
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

	public List<OrderAddMsgGoodsPara> getGoods() {
		return goods;
	}

	public void setGoods(List<OrderAddMsgGoodsPara> goods) {
		this.goods = goods;
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
