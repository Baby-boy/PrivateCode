package com.yd.dby.c.member.entity;

import com.yd.dby.utils.date.YdUtilDate;


public class YdComplain {
	
	private Integer complain_id;
	private Integer order_id;
	private Integer order_goods_id;
	private Integer accuser_id;
	private String accuser_name;
	private Integer accused_id;
	private String accused_name;
	private String complain_subject_content;
	private Integer complain_subject_id;
	private String complain_content;
	private String complain_pics;
	private String complain_datetime;
	private String complain_datetime_text;
	private String complain_handle_datetime;
	private Integer complain_handle_member_id;
	private String appeal_message;
	private String appeal_datetime;
	private String appeal_pics;
	private String final_handle_message;
	private String final_handle_datetime;
	private Integer final_handle_member_id;
	private Integer complain_state;
	private Integer complain_active;
	private String goods_name;
	private Integer goods_id;
	private String goods_cover;
	
	
	public String getComplain_datetime_text() {
		return YdUtilDate.stampToDateDay(complain_datetime);
	}
	public String getGoods_cover() {
		return goods_cover;
	}
	public void setGoods_cover(String goods_cover) {
		this.goods_cover = goods_cover;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getComplain_id() {
		return complain_id;
	}
	public void setComplain_id(Integer complain_id) {
		this.complain_id = complain_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getOrder_goods_id() {
		return order_goods_id;
	}
	public void setOrder_goods_id(Integer order_goods_id) {
		this.order_goods_id = order_goods_id;
	}
	public Integer getAccuser_id() {
		return accuser_id;
	}
	public void setAccuser_id(Integer accuser_id) {
		this.accuser_id = accuser_id;
	}
	public String getAccuser_name() {
		return accuser_name;
	}
	public void setAccuser_name(String accuser_name) {
		this.accuser_name = accuser_name;
	}
	public Integer getAccused_id() {
		return accused_id;
	}
	public void setAccused_id(Integer accused_id) {
		this.accused_id = accused_id;
	}
	public String getAccused_name() {
		return accused_name;
	}
	public void setAccused_name(String accused_name) {
		this.accused_name = accused_name;
	}
	public String getComplain_subject_content() {
		return complain_subject_content;
	}
	public void setComplain_subject_content(String complain_subject_content) {
		this.complain_subject_content = complain_subject_content;
	}
	public Integer getComplain_subject_id() {
		return complain_subject_id;
	}
	public void setComplain_subject_id(Integer complain_subject_id) {
		this.complain_subject_id = complain_subject_id;
	}
	public String getComplain_content() {
		return complain_content;
	}
	public void setComplain_content(String complain_content) {
		this.complain_content = complain_content;
	}
	public String getComplain_pics() {
		return complain_pics;
	}
	public void setComplain_pics(String complain_pics) {
		this.complain_pics = complain_pics;
	}
	public String getComplain_datetime() {
		return complain_datetime;
	}
	public void setComplain_datetime(String complain_datetime) {
		this.complain_datetime = complain_datetime;
	}
	public String getComplain_handle_datetime() {
		return complain_handle_datetime;
	}
	public void setComplain_handle_datetime(String complain_handle_datetime) {
		this.complain_handle_datetime = complain_handle_datetime;
	}
	public Integer getComplain_handle_member_id() {
		return complain_handle_member_id;
	}
	public void setComplain_handle_member_id(Integer complain_handle_member_id) {
		this.complain_handle_member_id = complain_handle_member_id;
	}
	public String getAppeal_message() {
		return appeal_message;
	}
	public void setAppeal_message(String appeal_message) {
		this.appeal_message = appeal_message;
	}
	public String getAppeal_datetime() {
		return appeal_datetime;
	}
	public void setAppeal_datetime(String appeal_datetime) {
		this.appeal_datetime = appeal_datetime;
	}
	public String getAppeal_pics() {
		return appeal_pics;
	}
	public void setAppeal_pics(String appeal_pics) {
		this.appeal_pics = appeal_pics;
	}
	public String getFinal_handle_message() {
		return final_handle_message;
	}
	public void setFinal_handle_message(String final_handle_message) {
		this.final_handle_message = final_handle_message;
	}
	public String getFinal_handle_datetime() {
		return final_handle_datetime;
	}
	public void setFinal_handle_datetime(String final_handle_datetime) {
		this.final_handle_datetime = final_handle_datetime;
	}
	public Integer getFinal_handle_member_id() {
		return final_handle_member_id;
	}
	public void setFinal_handle_member_id(Integer final_handle_member_id) {
		this.final_handle_member_id = final_handle_member_id;
	}
	public Integer getComplain_state() {
		return complain_state;
	}
	public void setComplain_state(Integer complain_state) {
		this.complain_state = complain_state;
	}
	public Integer getComplain_active() {
		return complain_active;
	}
	public void setComplain_active(Integer complain_active) {
		this.complain_active = complain_active;
	}
	
	
}
