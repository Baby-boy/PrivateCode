package com.yd.dby.d.seller.entity;

import java.util.Date;

public class YdSellerCoupon {

	/**
	 * 优惠券id 
	 */
	private Integer coupon_id;
	
	/**
	 * 优惠券名称
	 */
	private String coupon_name;
	
	/**
	 * 店铺id
	 */
	private Integer store_id;
	
	/**
	 * 优惠券类型 1:全场通用 2:店铺使用
	 */
	private Integer coupon_type;
	
	/**
	 * 店铺优惠券类型 1:满减 2:减免
	 */
	private Integer coupon_store_type;
	
	/**
	 * 优惠券金额
	 */
	private Double coupon_money;
	
	/**
	 * 优惠券满金额
	 */
	private Double coupon_full_money;
	
	/**
	 * 优惠券总库存
	 */
	private Integer coupon_total_stock;
	
	/**
	 * 优惠券当前库存
	 */
	private Integer coupon_stock;
	
	/**
	 * 优惠券开始时间
	 */
	private Date coupon_begin_time;
	
	/**
	 * 优惠券结束时间
	 */
	private Date coupon_end_time;
	
	/**
	 * 优惠券创建时间
	 */
	private Date coupon_created_time;
	
	public Integer getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(Integer coupon_id) {
	}
	public String getCoupon_name() {
		return coupon_name;
	}
	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getCoupon_type() {
		return coupon_type;
	}
	public void setCoupon_type(Integer coupon_type) {
		this.coupon_type = coupon_type;
	}
	public Integer getCoupon_store_type() {
		return coupon_store_type;
	}
	public void setCoupon_store_type(Integer coupon_store_type) {
		this.coupon_store_type = coupon_store_type;
	}
	public Double getCoupon_money() {
		return coupon_money;
	}
	public void setCoupon_money(Double coupon_money) {
		this.coupon_money = coupon_money;
	}
	public Double getCoupon_full_money() {
		return coupon_full_money;
	}
	public void setCoupon_full_money(Double coupon_full_money) {
		this.coupon_full_money = coupon_full_money;
	}
	public Integer getCoupon_total_stock() {
		return coupon_total_stock;
	}
	public void setCoupon_total_stock(Integer coupon_total_stock) {
		this.coupon_total_stock = coupon_total_stock;
	}
	public Integer getCoupon_stock() {
		return coupon_stock;
	}
	public void setCoupon_stock(Integer coupon_stock) {
		this.coupon_stock = coupon_stock;
	}
	public Date getCoupon_begin_time() {
		return coupon_begin_time;
	}
	public void setCoupon_begin_time(Date coupon_begin_time) {
		this.coupon_begin_time = coupon_begin_time;
	}
	public Date getCoupon_end_time() {
		return coupon_end_time;
	}
	public void setCoupon_end_time(Date coupon_end_time) {
		this.coupon_end_time = coupon_end_time;
	}
	public Date getCoupon_created_time() {
		return coupon_created_time;
	}
	public void setCoupon_created_time(Date coupon_created_time) {
		this.coupon_created_time = coupon_created_time;
	}
	
}
