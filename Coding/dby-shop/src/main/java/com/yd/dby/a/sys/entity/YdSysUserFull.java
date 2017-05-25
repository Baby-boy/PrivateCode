package com.yd.dby.a.sys.entity;

public class YdSysUserFull {

	private Integer user_id;
	private Integer user_sex;
	private String user_username;
	private String user_nickname;
	private String user_avatar;
	private String user_email;
	private String user_address;
	private Integer user_birthday;
	private Integer user_grade;
	private Integer user_province_id;
	private Integer user_city_id;
	private Integer user_area_id;
	private String user_province_value;
	private String user_city_value;
	private String user_area_value;
	private Float user_account_balance;
	private Integer user_integration;
	private Integer user_total_coupon;
	private Integer user_total_bankcard;

	public YdSysUserFull() {

	}

	public YdSysUserFull(YdSysUserSecurity user) {
		this.user_id = user.getUser_id();
		this.user_sex = 1;
		this.user_username = user.getUser_username();
		this.user_nickname = "";
		this.user_avatar = "";
		this.user_email = "";
		this.user_address = "";
		this.user_birthday = 0;
		this.user_grade = 0;
		this.user_province_id = 0;
		this.user_city_id = 0;
		this.user_area_id = 0;
		this.user_province_value = "";
		this.user_city_value = "";
		this.user_area_value = "";
		this.user_account_balance = 0F;
		this.user_integration = 0;
		this.user_total_coupon = 0;
		this.user_total_bankcard = 0;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public String getUser_nickname() {
		return user_nickname;
	}
	
	public String getUser_username() {
		return user_username;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public String getUser_email() {
		return user_email;
	}

	public String getUser_address() {
		return user_address;
	}

	public Integer getUser_birthday() {
		return user_birthday;
	}

	public Integer getUser_grade() {
		return user_grade;
	}

	public Integer getUser_province_id() {
		return user_province_id;
	}

	public Integer getUser_city_id() {
		return user_city_id;
	}

	public Integer getUser_area_id() {
		return user_area_id;
	}

	public String getUser_province_value() {
		return user_province_value;
	}

	public String getUser_city_value() {
		return user_city_value;
	}

	public String getUser_area_value() {
		return user_area_value;
	}

	public Float getUser_account_balance() {
		return user_account_balance;
	}

	public Integer getUser_integration() {
		return user_integration;
	}

	public Integer getUser_total_coupon() {
		return user_total_coupon;
	}

	public Integer getUser_total_bankcard() {
		return user_total_bankcard;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public void setUser_birthday(Integer user_birthday) {
		this.user_birthday = user_birthday;
	}

	public void setUser_grade(Integer user_grade) {
		this.user_grade = user_grade;
	}

	public void setUser_province_id(Integer user_province_id) {
		this.user_province_id = user_province_id;
	}

	public void setUser_city_id(Integer user_city_id) {
		this.user_city_id = user_city_id;
	}

	public void setUser_area_id(Integer user_area_id) {
		this.user_area_id = user_area_id;
	}

	public void setUser_province_value(String user_province_value) {
		this.user_province_value = user_province_value;
	}

	public void setUser_city_value(String user_city_value) {
		this.user_city_value = user_city_value;
	}

	public void setUser_area_value(String user_area_value) {
		this.user_area_value = user_area_value;
	}

	public void setUser_account_balance(Float user_account_balance) {
		this.user_account_balance = user_account_balance;
	}

	public void setUser_integration(Integer user_integration) {
		this.user_integration = user_integration;
	}

	public void setUser_total_coupon(Integer user_total_coupon) {
		this.user_total_coupon = user_total_coupon;
	}

	public void setUser_total_bankcard(Integer user_total_bankcard) {
		this.user_total_bankcard = user_total_bankcard;
	}

}