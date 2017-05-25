package com.yd.dby.b.shop.entity;

public class YdOrder {

	private Integer order_id;
	private String order_sn;
	private String pay_sn;
	private Integer store_id;
	private String store_name;
	private Integer buyer_id;
	private String buyer_name;
	private String payment_code;
	private Integer payment_time;
	private Integer finnshed_time;
	private Integer type;
	private Integer state_service;
	private Integer processing_status;
	private Integer state;
	private Integer coupon_id;
	private Float coupon_price;
	private Float goods_price;
	private Float total_price;
	private Float refund_price;
	private Float transport_price;
	private Integer transport_fid;
	private String transport_address;
	private Integer invoice_type;
	private String invoice_no;
	private String created_at;
	private String order_message;
	private String receipt_name;
	private String receipt_mobile;
	private String receipt_address;
	
	//商铺的图片logo
	private String storeLogo;
	
	private String receiving_time;//收货时间
	
	public String getStoreLogo() {
		return storeLogo;
	}
	public void setStoreLogo(String storeLogo) {
		this.storeLogo = storeLogo;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getOrder_sn() {
		return order_sn;
	}
	public void setOrder_sn(String order_sn) {
		this.order_sn = order_sn;
	}
	public String getPay_sn() {
		return pay_sn;
	}
	public void setPay_sn(String pay_sn) {
		this.pay_sn = pay_sn;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState_service() {
		return state_service;
	}
	public void setState_service(Integer state_service) {
		this.state_service = state_service;
	}
	public Integer getProcessing_status() {
		return processing_status;
	}
	public void setProcessing_status(Integer processing_status) {
		this.processing_status = processing_status;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}
	public Float getCoupon_price() {
		return coupon_price;
	}
	public void setCoupon_price(Float coupon_price) {
		this.coupon_price = coupon_price;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
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
	public Integer getTransport_fid() {
		return transport_fid;
	}
	public void setTransport_fid(Integer transport_fid) {
		this.transport_fid = transport_fid;
	}
	public String getTransport_address() {
		return transport_address;
	}
	public void setTransport_address(String transport_address) {
		this.transport_address = transport_address;
	}
	public Integer getInvoice_type() {
		return invoice_type;
	}
	public void setInvoice_type(Integer invoice_type) {
		this.invoice_type = invoice_type;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getOrder_message() {
		return order_message;
	}
	public void setOrder_message(String order_message) {
		this.order_message = order_message;
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
	public String getReceipt_address() {
		return receipt_address;
	}
	public void setReceipt_address(String receipt_address) {
		this.receipt_address = receipt_address;
	}
	public String getReceiving_time() {
		return receiving_time;
	}
	public void setReceiving_time(String receiving_time) {
		this.receiving_time = receiving_time;
	}
	

}