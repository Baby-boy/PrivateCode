package com.yd.dby.b.shop.entity;

public class YdOrderGoods {

	private Integer og_id;
	private Integer order_id;
	private Integer goods_id;
	private String goods_name;
	private Integer og_state;
	private Integer goods_num;
	private Float goods_price;
	private String goods_image;
	private Float goods_pay_price;
	private Integer store_id;
	private Integer buyer_id;
	private Integer refund_num;
	private Float discount_price;
	private String goods_summary;
	private String goods_cover;
	private Float goods_original_price;
	private Integer og_num;
	private Integer og_return_state;
	private Float og_price;
	private String created_at;
	
	public Integer getOg_id() {
		return og_id;
	}
	public void setOg_id(Integer og_id) {
		this.og_id = og_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getOg_state() {
		return og_state;
	}
	public void setOg_state(Integer og_state) {
		this.og_state = og_state;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public Float getDiscount_price() {
		return discount_price;
	}
	public void setDiscount_price(Float discount_price) {
		this.discount_price = discount_price;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_image() {
		return goods_image;
	}
	public void setGoods_image(String goods_image) {
		this.goods_image = goods_image;
	}
	public Float getGoods_pay_price() {
		return goods_pay_price;
	}
	public void setGoods_pay_price(Float goods_pay_price) {
		this.goods_pay_price = goods_pay_price;
	}
	public Integer getRefund_num() {
		return refund_num;
	}
	public void setRefund_num(Integer refund_num) {
		this.refund_num = refund_num;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(Integer buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getGoods_summary() {
		return goods_summary;
	}
	public void setGoods_summary(String goods_summary) {
		this.goods_summary = goods_summary;
	}
	public String getGoods_cover() {
		return goods_cover;
	}
	public void setGoods_cover(String goods_cover) {
		this.goods_cover = goods_cover;
	}
	public Float getGoods_original_price() {
		return goods_original_price;
	}
	public void setGoods_original_price(Float goods_original_price) {
		this.goods_original_price = goods_original_price;
	}
	public Integer getOg_num() {
		return og_num;
	}
	public void setOg_num(Integer og_num) {
		this.og_num = og_num;
	}
	public Integer getOg_return_state() {
		return og_return_state;
	}
	public void setOg_return_state(Integer og_return_state) {
		this.og_return_state = og_return_state;
	}
	public Float getOg_price() {
		return og_price;
	}
	public void setOg_price(Float og_price) {
		this.og_price = og_price;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
}