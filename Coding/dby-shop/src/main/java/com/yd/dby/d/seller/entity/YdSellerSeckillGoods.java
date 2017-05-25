package com.yd.dby.d.seller.entity;

import com.yd.dby.utils.date.YdUtilDate;




public class YdSellerSeckillGoods extends YdSellerSeckill {
	private Integer sg_id;
	private Integer seckill_id;
	private Integer depot_id;
	private Integer sg_sort;
	private String sg_apply_time;
	private Integer sg_state;
	private String sg_season;
	private String sg_cover;
	private Integer store_id;
	private String store_name;
	
	
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getSg_apply_time_text() {
		return YdUtilDate.stampToDateDay(sg_apply_time);
	}
	public Integer getSg_id() {
		return sg_id;
	}
	public void setSg_id(Integer sg_id) {
		this.sg_id = sg_id;
	}
	public Integer getSeckill_id() {
		return seckill_id;
	}
	public void setSeckill_id(Integer seckill_id) {
		this.seckill_id = seckill_id;
	}
	public Integer getDepot_id() {
		return depot_id;
	}
	public void setDepot_id(Integer depot_id) {
		this.depot_id = depot_id;
	}
	public Integer getSg_sort() {
		return sg_sort;
	}
	public void setSg_sort(Integer sg_sort) {
		this.sg_sort = sg_sort;
	}
	public String getSg_apply_time() {
		return sg_apply_time;
	}
	public void setSg_apply_time(String sg_apply_time) {
		this.sg_apply_time = sg_apply_time;
	}
	public Integer getSg_state() {
		return sg_state;
	}
	public void setSg_state(Integer sg_state) {
		this.sg_state = sg_state;
	}
	public String getSg_season() {
		return sg_season;
	}
	public void setSg_season(String sg_season) {
		this.sg_season = sg_season;
	}
	public String getSg_cover() {
		return sg_cover;
	}
	public void setSg_cover(String sg_cover) {
		this.sg_cover = sg_cover;
	}
	
	
	
}
