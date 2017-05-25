package com.yd.dby.b.shop.entity;


public class YdFavorite {
	
	private Integer fav_id;
	private Integer user_id;
	private Integer fav_type;
	private Integer fav_fid;
	private String fav_created_time;
	public Integer getFav_id() {
		return fav_id;
	}
	public void setFav_id(Integer fav_id) {
		this.fav_id = fav_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getFav_type() {
		return fav_type;
	}
	public void setFav_type(Integer fav_type) {
		this.fav_type = fav_type;
	}
	public Integer getFav_fid() {
		return fav_fid;
	}
	public void setFav_fid(Integer fav_fid) {
		this.fav_fid = fav_fid;
	}
	public String getFav_created_time() {
		return fav_created_time;
	}
	public void setFav_created_time(String fav_created_time) {
		this.fav_created_time = fav_created_time;
	}
	
	
	
}
