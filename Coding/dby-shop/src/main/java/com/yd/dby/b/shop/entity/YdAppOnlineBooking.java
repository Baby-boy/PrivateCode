package com.yd.dby.b.shop.entity;

public class YdAppOnlineBooking {
	private Integer id;
	private Integer type;
	private Integer user_id;
	private String user_name;
	private String user_phone;
	private String user_address;
	private String goods_name;
	private String goods_summary;
	private String goods_cover;
	private Object goods_pics;
	private Integer address_province_id;
	private Integer address_city_id;
	private String address_pca;
	private String province_value;
	private String city_value;
	private String created_time;
	private Integer classify_id1;
	private Integer classify_id2;
	private String details_address;
	
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
	public String getDetailsAddress() {
		return details_address;
	}
	public void setDetailsAddress(String details_address) {
		this.details_address = details_address;
	}
	public Integer getClassify_id1() {
		return classify_id1;
	}
	public Integer getClassify_id2() {
		return classify_id2;
	}
	public void setClassify_id1(Integer classify_id1) {
		this.classify_id1 = classify_id1;
	}
	public void setClassify_id2(Integer classify_id2) {
		this.classify_id2 = classify_id2;
	}
	public Integer getId() {
		return id;
	}
	public Integer getType() {
		return type;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public String getUser_address() {
		return user_address;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public String getGoods_summary() {
		return goods_summary;
	}
	public String getGoods_cover() {
		return goods_cover;
	}
	public Object getGoods_pics() {
		return goods_pics;
	}
	public Integer getAddress_province_id() {
		return address_province_id;
	}
	public Integer getAddress_city_id() {
		return address_city_id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public void setGoods_summary(String goods_summary) {
		this.goods_summary = goods_summary;
	}
	public void setGoods_cover(String goods_cover) {
		this.goods_cover = goods_cover;
	}
	public void setGoods_pics(Object goods_pics) {
		this.goods_pics = goods_pics;
	}
	public void setAddress_province_id(Integer address_province_id) {
		this.address_province_id = address_province_id;
	}
	public void setAddress_city_id(Integer address_city_id) {
		this.address_city_id = address_city_id;
	}
	public String getProvince_value() {
		return province_value;
	}
	public String getCity_value() {
		return city_value;
	}
	public String getAddress_pca() {
		return address_pca;
	}
	public void setAddress_pca(String address_pca) {
		this.address_pca = address_pca;
	}
	public void setProvince_value(String province_value) {
		this.province_value = province_value;
	}
	public void setCity_value(String city_value) {
		this.city_value = city_value;
	}
	
	

}
