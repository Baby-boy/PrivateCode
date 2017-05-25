package com.yd.dby.c.member.entity;

import java.util.Date;

public class User {

	private Integer user_id;
	private Integer user_sex;
	private String user_username;
	private String user_nickname;
	private String user_avatar;
	private String user_mobile;
	private String user_email;
	private Integer user_grade;
	private String user_payment_password;
	private Float user_money;
	private String user_birthday;
	private String user_province_id;
	private String user_city_id;
	private String user_area_id;
	private String user_pca;
	private String user_address;
	private Date user_created_time;
	private Integer ctc_sell_num;
	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getCtc_sell_num() {
		return ctc_sell_num;
	}

	public void setCtc_sell_num(Integer ctc_sell_num) {
		this.ctc_sell_num = ctc_sell_num;
	}

	public Date getUser_created_time() {
		return user_created_time;
	}

	public void setUser_created_time(Date user_created_time) {
		this.user_created_time = user_created_time;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_username() {
		return user_username;
	}

	public void setUser_username(String user_username) {
		this.user_username = user_username;
	}

	public String getUser_nickname() {
		return user_nickname;
	}

	public void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}

	public String getUser_avatar() {
		return user_avatar;
	}

	public void setUser_avatar(String user_avatar) {
		this.user_avatar = user_avatar;
	}

	public String getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(String user_mobile) {
		this.user_mobile = user_mobile;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public Integer getUser_grade() {
		return user_grade;
	}

	public void setUser_grade(Integer user_grade) {
		this.user_grade = user_grade;
	}

	public String getUser_payment_password() {
		return user_payment_password;
	}

	public void setUser_payment_password(String user_payment_password) {
		this.user_payment_password = user_payment_password;
	}

	public Float getUser_money() {
		return user_money;
	}

	public void setUser_money(Float user_money) {
		this.user_money = user_money;
	}

	public String getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(String user_birthday) {
		this.user_birthday = user_birthday;
	}

	public String getUser_province_id() {
		return user_province_id;
	}

	public void setUser_province_id(String user_province_id) {
		this.user_province_id = user_province_id;
	}

	public String getUser_city_id() {
		return user_city_id;
	}

	public void setUser_city_id(String user_city_id) {
		this.user_city_id = user_city_id;
	}

	public String getUser_area_id() {
		return user_area_id;
	}

	public void setUser_area_id(String user_area_id) {
		this.user_area_id = user_area_id;
	}

	public String getUser_pca() {
		return user_pca;
	}

	public void setUser_pca(String user_pca) {
		this.user_pca = user_pca;
	}

}
