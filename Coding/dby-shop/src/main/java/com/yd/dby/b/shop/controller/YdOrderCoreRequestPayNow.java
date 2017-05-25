package com.yd.dby.b.shop.controller;

public class YdOrderCoreRequestPayNow {
	private Integer depot_id;
	private Integer coupon_id;
	private Integer transport_id;
	private Float transport_price;
	private Integer invoice_id;
	private String invoice_no;
	private String pay_type;

	public Integer getDepot_id() {
		return depot_id;
	}

	public Integer getCoupon_id() {
		return coupon_id;
	}

	public Integer getTransport_id() {
		return transport_id;
	}

	public Float getTransport_price() {
		return transport_price;
	}

	public Integer getInvoice_id() {
		return invoice_id;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public String getPay_type() {
		return pay_type;
	}

	public void setDepot_id(Integer depot_id) {
		this.depot_id = depot_id;
	}

	public void setCoupon_id(Integer coupon_id) {
		this.coupon_id = coupon_id;
	}

	public void setTransport_id(Integer transport_id) {
		this.transport_id = transport_id;
	}

	public void setTransport_price(Float transport_price) {
		this.transport_price = transport_price;
	}

	public void setInvoice_id(Integer invoice_id) {
		this.invoice_id = invoice_id;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

}
