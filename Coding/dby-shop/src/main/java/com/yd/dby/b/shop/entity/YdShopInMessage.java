package com.yd.dby.b.shop.entity;

import java.util.Date;

public class YdShopInMessage {

	private Integer im_mid;//留言主键
	private Integer message_id;//留言表id
	private Integer im_store_id;//店铺id
	private Integer im_depot_id;//商品仓库id
	private Integer user_id;//留言用户id
	private String user_name;//留言用户昵称
	private String user_avatar;//留言用户头像
	private String im_message;//留言内容
	private String goods_name;//商品名称
	private Float goods_price;//商品价格
	private String goods_pic;//商品图片
	private Date im_time;//留言创建时间
	
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_pic() {
		return goods_pic;
	}
	public void setGoods_pic(String goods_pic) {
		this.goods_pic = goods_pic;
	}
	public Integer getIm_mid() {
		return im_mid;
	}
	public void setIm_mid(Integer im_mid) {
		this.im_mid = im_mid;
	}
	
	public Integer getMessage_id() {
		return message_id;
	}
	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}
	public Integer getIm_store_id() {
		return im_store_id;
	}
	public void setIm_store_id(Integer im_store_id) {
		this.im_store_id = im_store_id;
	}
	public Integer getIm_depot_id() {
		return im_depot_id;
	}
	public void setIm_depot_id(Integer im_depot_id) {
		this.im_depot_id = im_depot_id;
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
	public String getUser_avatar() {
		return user_avatar;
	}
	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}
	public String getIm_message() {
		return im_message;
	}
	public void setIm_message(String im_message) {
		this.im_message = im_message;
	}
	public Date getIm_time() {
		return im_time;
	}
	public void setIm_time(Date im_time) {
		this.im_time = im_time;
	}
	
}
