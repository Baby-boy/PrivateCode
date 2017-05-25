package com.yd.dby.c.member.entity;

public class YdOnLineBookIng {
	
	private Integer id;//流通置换 主键id
	private Integer type;//类型
	private Integer user_id;//用户id
	private String user_name;//用户姓名
	private String user_phone;//用户手机号
	private String user_address;//用户地址
	private String goods_name;//商品名称
	private String goods_summary;//商品简介
	private String goods_cover;//图片地址(默认首图)
	private String goods_pics;//商品多张图片
	private String address_province_id;//省id
	private String address_city_id;//市id
	private String address_area_id;//区id
	private String address_pca;//地址省-市-区
	private String province_value;//省名称
	private String address_area_value;//市名称
	private String city_value;//区名称
	private String created_time;//创建时间
	private String details_address;//详细地址
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
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
	public String getGoods_pics() {
		return goods_pics;
	}
	public void setGoods_pics(String goods_pics) {
		this.goods_pics = goods_pics;
	}
	public String getAddress_province_id() {
		return address_province_id;
	}
	public void setAddress_province_id(String address_province_id) {
		this.address_province_id = address_province_id;
	}
	public String getAddress_city_id() {
		return address_city_id;
	}
	public void setAddress_city_id(String address_city_id) {
		this.address_city_id = address_city_id;
	}
	public String getAddress_area_id() {
		return address_area_id;
	}
	public void setAddress_area_id(String address_area_id) {
		this.address_area_id = address_area_id;
	}
	public String getAddress_pca() {
		return address_pca;
	}
	public void setAddress_pca(String address_pca) {
		this.address_pca = address_pca;
	}
	public String getProvince_value() {
		return province_value;
	}
	public void setProvince_value(String province_value) {
		this.province_value = province_value;
	}
	public String getAddress_area_value() {
		return address_area_value;
	}
	public void setAddress_area_value(String address_area_value) {
		this.address_area_value = address_area_value;
	}
	public String getCity_value() {
		return city_value;
	}
	public void setCity_value(String city_value) {
		this.city_value = city_value;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public String getDetails_address() {
		return details_address;
	}
	public void setDetails_address(String details_address) {
		this.details_address = details_address;
	}
}
