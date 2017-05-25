package com.yd.dby.b.shop.controller;

import java.util.List;

public class YdRequestParamsOnlingBooking {
	private String username;
	private String phone;
	private String name;
	private String summary;
	private Integer classify_id1;
	private Integer classify_id2;
	private Integer province_id;
	private Integer city_id;
	private String province_value;
	private String city_value;
	private List<String> pics;
	private String details_address;
	
	public String getDetailsAddress() {
		return details_address;
	}
	public void setDetailsAddress(String details_address) {
		this.details_address = details_address;
	}
	public String getUsername() {
		return username;
	}
	public String getPhone() {
		return phone;
	}
	public String getName() {
		return name;
	}
	public String getSummary() {
		return summary;
	}
	public Integer getClassify_id1() {
		return classify_id1;
	}
	public Integer getClassify_id2() {
		return classify_id2;
	}
	public Integer getProvince_id() {
		return province_id;
	}
	public Integer getCity_id() {
		return city_id;
	}
	public String getProvince_value() {
		return province_value;
	}
	public String getCity_value() {
		return city_value;
	}
	public List<String> getPics() {
		return pics;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public void setClassify_id1(Integer classify_id1) {
		this.classify_id1 = classify_id1;
	}
	public void setClassify_id2(Integer classify_id2) {
		this.classify_id2 = classify_id2;
	}
	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public void setProvince_value(String province_value) {
		this.province_value = province_value;
	}
	public void setCity_value(String city_value) {
		this.city_value = city_value;
	}
	public void setPics(List<String> pics) {
		this.pics = pics;
	}
	

}
