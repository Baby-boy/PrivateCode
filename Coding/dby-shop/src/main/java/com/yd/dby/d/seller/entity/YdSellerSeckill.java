package com.yd.dby.d.seller.entity;

import com.yd.dby.utils.date.YdUtilDate;

public class YdSellerSeckill extends YdSellerDepot {
	
	private Integer seckill_id;
	private String seckill_title;
	private String seckill_subtitle;
	private String seckill_logo;
	private String seckill_cover;
	private Integer seckill_sort;
	private Integer seckill_state;
	private String start_time;
	private String end_time;
	private String create_time;
	
	
	public String getStart_time_text() {
		return YdUtilDate.stampToDateDay(start_time);
	}
	public String getEnd_time_text() {
		return YdUtilDate.stampToDateDay(end_time);
	}
	public String getCreate_time_text() {
		return YdUtilDate.stampToDateDay(create_time);
	}
	public Integer getSeckill_id() {
		return seckill_id;
	}
	public void setSeckill_id(Integer seckill_id) {
		this.seckill_id = seckill_id;
	}
	public String getSeckill_title() {
		return seckill_title;
	}
	public void setSeckill_title(String seckill_title) {
		this.seckill_title = seckill_title;
	}
	public String getSeckill_subtitle() {
		return seckill_subtitle;
	}
	public void setSeckill_subtitle(String seckill_subtitle) {
		this.seckill_subtitle = seckill_subtitle;
	}
	public String getSeckill_logo() {
		return seckill_logo;
	}
	public void setSeckill_logo(String seckill_logo) {
		this.seckill_logo = seckill_logo;
	}
	public String getSeckill_cover() {
		return seckill_cover;
	}
	public void setSeckill_cover(String seckill_cover) {
		this.seckill_cover = seckill_cover;
	}
	public Integer getSeckill_sort() {
		return seckill_sort;
	}
	public void setSeckill_sort(Integer seckill_sort) {
		this.seckill_sort = seckill_sort;
	}
	public Integer getSeckill_state() {
		return seckill_state;
	}
	public void setSeckill_state(Integer seckill_state) {
		this.seckill_state = seckill_state;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
}
