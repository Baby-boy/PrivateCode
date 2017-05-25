package com.yd.dby.c.member.entity;


public class YdRefundReason {
	
	private Integer reason_id;
	private String reason_info;
	private Integer sort;
	private String update_time;
	public Integer getReason_id() {
		return reason_id;
	}
	public void setReason_id(Integer reason_id) {
		this.reason_id = reason_id;
	}
	public String getReason_info() {
		return reason_info;
	}
	public void setReason_info(String reason_info) {
		this.reason_info = reason_info;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	
}
