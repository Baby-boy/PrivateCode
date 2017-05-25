package com.yd.dby.b.shop.entity;

public class YdCtcOrder {

	private Integer ctc_order_id;
	private String ctc_order_sn;
	private String pay_sn;
	private Integer user_id;
	private String user_name;
	private Integer buyer_id;
	private String buyer_name;
	private String payment_code;
	private Integer payment_time;
	private Integer finnshed_time;
	private Integer state_service;
	private Integer state;
	private Integer delete_state;
	private Integer ctc_id;
	private String ctc_cover;
	private String goods_name;
	private Float goods_price;
	private Float total_price;
	private Float refund_price;
	private Float transport_price;
	private String created_at;
	private String cancel_reason;
	private String order_message;
	private String deliver_explain;
	private String receipt_name;
	private String receipt_mobile;
	private String receipt_address;
	private String shipping_address;
	private Integer shipping_id;
	private String shipping_express;
	private String shipping_code;
	private Integer shipping_time;
	private Integer evaluation_time;

	public Integer getCtc_id() {
		return ctc_id;
	}

	public void setCtc_id(Integer ctc_id) {
		this.ctc_id = ctc_id;
	}

	public String getReceipt_name() {
		return receipt_name;
	}

	public void setReceipt_name(String receipt_name) {
		this.receipt_name = receipt_name;
	}

	public String getReceipt_mobile() {
		return receipt_mobile;
	}

	public void setReceipt_mobile(String receipt_mobile) {
		this.receipt_mobile = receipt_mobile;
	}

	public Integer getCtc_order_id() {
		return ctc_order_id;
	}

	public void setCtc_order_id(Integer ctc_order_id) {
		this.ctc_order_id = ctc_order_id;
	}

	public String getCtc_order_sn() {
		return ctc_order_sn;
	}

	public void setCtc_order_sn(String ctc_order_sn) {
		this.ctc_order_sn = ctc_order_sn;
	}

	public String getPay_sn() {
		return pay_sn;
	}

	public void setPay_sn(String pay_sn) {
		this.pay_sn = pay_sn;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public String getPayment_code() {
		return payment_code;
	}

	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}

	public Integer getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(Integer payment_time) {
		this.payment_time = payment_time;
	}

	public Integer getFinnshed_time() {
		return finnshed_time;
	}

	public void setFinnshed_time(Integer finnshed_time) {
		this.finnshed_time = finnshed_time;
	}

	public Integer getState_service() {
		return state_service;
	}

	public void setState_service(Integer state_service) {
		this.state_service = state_service;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getDelete_state() {
		return delete_state;
	}

	public void setDelete_state(Integer delete_state) {
		this.delete_state = delete_state;
	}

	public Float getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}

	public String getCtc_cover() {
		return ctc_cover;
	}

	public void setCtc_cover(String ctc_cover) {
		this.ctc_cover = ctc_cover;
	}
	
	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public Float getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Float total_price) {
		this.total_price = total_price;
	}

	public Float getRefund_price() {
		return refund_price;
	}

	public void setRefund_price(Float refund_price) {
		this.refund_price = refund_price;
	}

	public Float getTransport_price() {
		return transport_price;
	}

	public void setTransport_price(Float transport_price) {
		this.transport_price = transport_price;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getCancel_reason() {
		return cancel_reason;
	}

	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

	public String getOrder_message() {
		return order_message;
	}

	public void setOrder_message(String order_message) {
		this.order_message = order_message;
	}

	public String getDeliver_explain() {
		return deliver_explain;
	}

	public void setDeliver_explain(String deliver_explain) {
		this.deliver_explain = deliver_explain;
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

	public Integer getShipping_id() {
		return shipping_id;
	}

	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}

	public String getShipping_express() {
		return shipping_express;
	}

	public void setShipping_express(String shipping_express) {
		this.shipping_express = shipping_express;
	}

	public String getShipping_code() {
		return shipping_code;
	}

	public void setShipping_code(String shipping_code) {
		this.shipping_code = shipping_code;
	}

	public Integer getShipping_time() {
		return shipping_time;
	}

	public void setShipping_time(Integer shipping_time) {
		this.shipping_time = shipping_time;
	}

	public Integer getEvaluation_time() {
		return evaluation_time;
	}

	public void setEvaluation_time(Integer evaluation_time) {
		this.evaluation_time = evaluation_time;
	}

}