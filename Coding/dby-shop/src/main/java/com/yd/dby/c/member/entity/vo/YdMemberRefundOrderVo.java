package com.yd.dby.c.member.entity.vo;

import com.yd.dby.c.member.entity.YdMemberRefundOrder;

public class YdMemberRefundOrderVo extends YdMemberRefundOrder {

	private String seller_nameString;// 卖家真实姓名

	private String seller_city;// 卖家城市
	private String seller_phone;// 卖家联系方式
	private String buyer_phone;// 买家联系方式
	private String receipt_address;// 收货地址
	private String shipping_address;// 发货地址
	private Double transport_price;// 运费
	private Double discount_price;// 优惠金额
	private String user_truename;// 卖家真实姓名
	private String user_pca;// 城市
	private String store_telephone;// 店铺座机
	private String store_mobile;// 店铺手机号
	private String user_email;// 用户邮箱
	private String receipt_mobile;// 收货手机号
	private Double goods_price;// 商品价格
	private String shipping_time;// 发货时间
	private String payment_time;// 支付时间
	private Double goods_pay_price;// 商品总价
	private String format_time;// 格式化之后的时间
	private Integer refund_state;	// 

	public Integer getRefund_state() {
		return refund_state;
	}

	public void setRefund_state(Integer refund_state) {
		this.refund_state = refund_state;
	}

	public String getFormat_time() {
		return format_time;
	}

	public void setFormat_time(String format_time) {
		this.format_time = format_time;
	}

	public Double getGoods_pay_price() {
		return goods_pay_price;
	}

	public void setGoods_pay_price(Double goods_pay_price) {
		this.goods_pay_price = goods_pay_price;
	}

	public String getSeller_nameString() {
		return seller_nameString;
	}

	public void setSeller_nameString(String seller_nameString) {
		this.seller_nameString = seller_nameString;
	}

	public String getSeller_city() {
		return seller_city;
	}

	public void setSeller_city(String seller_city) {
		this.seller_city = seller_city;
	}

	public String getSeller_phone() {
		return seller_phone;
	}

	public void setSeller_phone(String seller_phone) {
		this.seller_phone = seller_phone;
	}

	public String getBuyer_phone() {
		return buyer_phone;
	}

	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}

	public String getReceipt_address() {
		return receipt_address;
	}

	public void setReceipt_address(String receipt_address) {
		this.receipt_address = receipt_address;
	}

	public String getShipping_address() {
		return shipping_address;
	}

	public void setShipping_address(String shipping_address) {
		this.shipping_address = shipping_address;
	}

	public Double getTransport_price() {
		return transport_price;
	}

	public void setTransport_price(Double transport_price) {
		this.transport_price = transport_price;
	}

	public Double getDiscount_price() {
		return discount_price;
	}

	public void setDiscount_price(Double discount_price) {
		this.discount_price = discount_price;
	}

	public String getUser_truename() {
		return user_truename;
	}

	public void setUser_truename(String user_truename) {
		this.user_truename = user_truename;
	}

	public String getUser_pca() {
		return user_pca;
	}

	public void setUser_pca(String user_pca) {
		this.user_pca = user_pca;
	}

	public String getStore_telephone() {
		return store_telephone;
	}

	public void setStore_telephone(String store_telephone) {
		this.store_telephone = store_telephone;
	}

	public String getStore_mobile() {
		return store_mobile;
	}

	public void setStore_mobile(String store_mobile) {
		this.store_mobile = store_mobile;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getReceipt_mobile() {
		return receipt_mobile;
	}

	public void setReceipt_mobile(String receipt_mobile) {
		this.receipt_mobile = receipt_mobile;
	}

	public Double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(Double goods_price) {
		this.goods_price = goods_price;
	}

	public String getShipping_time() {
		return shipping_time;
	}

	public void setShipping_time(String shipping_time) {
		this.shipping_time = shipping_time;
	}

	public String getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(String payment_time) {
		this.payment_time = payment_time;
	}

}
