package com.yd.dby.d.seller.entity;


public class YdSellerGoods {
	
	private Integer goods_id;
	private Integer store_id;
	private String goods_name;
	private Float goods_price;
	private Integer goods_num;
	private String goods_serialnumber;
	private String goods_summary;
	private Integer goods_total_stock;
	private Integer goods_total_sell;
	private Integer goods_total_fav;
	private Integer goods_total_comment;
	private String goods_content;
	private String goods_content_pics;
	private String goods_attrs;
	private String goods_pics;
	private String goods_cover;
	private String goods_province_id;
	private String goods_city_id;
	private Float goods_freight;
	private Integer brand_id;
	private String brand_name;
	private String goods_afterservice;
	private Float  goods_avg_score;
	private String goods_created_time;
	private String goods_attr_select;
	
	private Integer depot_id;//仓库id
	
	public Integer getDepot_id() {
		return depot_id;
	}
	public void setDepot_id(Integer depot_id) {
		this.depot_id = depot_id;
	}
	public String getGoods_attr_select() {
		return goods_attr_select;
	}
	public void setGoods_attr_select(String goods_attr_select) {
		this.goods_attr_select = goods_attr_select;
	}
	public Integer getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(Integer goods_id) {
		this.goods_id = goods_id;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Integer getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(Integer goods_num) {
		this.goods_num = goods_num;
	}
	public String getGoods_serialnumber() {
		return goods_serialnumber;
	}
	public void setGoods_serialnumber(String goods_serialnumber) {
		this.goods_serialnumber = goods_serialnumber;
	}
	public String getGoods_summary() {
		return goods_summary;
	}
	public void setGoods_summary(String goods_summary) {
		this.goods_summary = goods_summary;
	}
	public Integer getGoods_total_stock() {
		return goods_total_stock;
	}
	public void setGoods_total_stock(Integer goods_total_stock) {
		this.goods_total_stock = goods_total_stock;
	}
	public Integer getGoods_total_sell() {
		return goods_total_sell;
	}
	public void setGoods_total_sell(Integer goods_total_sell) {
		this.goods_total_sell = goods_total_sell;
	}
	public Integer getGoods_total_fav() {
		return goods_total_fav;
	}
	public void setGoods_total_fav(Integer goods_total_fav) {
		this.goods_total_fav = goods_total_fav;
	}
	public Integer getGoods_total_comment() {
		return goods_total_comment;
	}
	public void setGoods_total_comment(Integer goods_total_comment) {
		this.goods_total_comment = goods_total_comment;
	}
	public String getGoods_content() {
		return goods_content;
	}
	public void setGoods_content(String goods_content) {
		this.goods_content = goods_content;
	}
	public String getGoods_content_pics() {
		return goods_content_pics;
	}
	public void setGoods_content_pics(String goods_content_pics) {
		this.goods_content_pics = goods_content_pics;
	}
	public String getGoods_attrs() {
		return goods_attrs;
	}
	public void setGoods_attrs(String goods_attrs) {
		this.goods_attrs = goods_attrs;
	}
	public String getGoods_pics() {
		return goods_pics;
	}
	public void setGoods_pics(String goods_pics) {
		this.goods_pics = goods_pics;
	}
	public String getGoods_cover() {
		return goods_cover;
	}
	public void setGoods_cover(String goods_cover) {
		this.goods_cover = goods_cover;
	}
	public String getGoods_province_id() {
		return goods_province_id;
	}
	public void setGoods_province_id(String goods_province_id) {
		this.goods_province_id = goods_province_id;
	}
	public String getGoods_city_id() {
		return goods_city_id;
	}
	public void setGoods_city_id(String goods_city_id) {
		this.goods_city_id = goods_city_id;
	}
	public Float getGoods_freight() {
		return goods_freight;
	}
	public void setGoods_freight(Float goods_freight) {
		this.goods_freight = goods_freight;
	}
	public Integer getBrand_id() {
		return brand_id;
	}
	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public String getGoods_afterservice() {
		return goods_afterservice;
	}
	public void setGoods_afterservice(String goods_afterservice) {
		this.goods_afterservice = goods_afterservice;
	}
	public Float getGoods_avg_score() {
		return goods_avg_score;
	}
	public void setGoods_avg_score(Float goods_avg_score) {
		this.goods_avg_score = goods_avg_score;
	}
	public String getGoods_created_time() {
		return goods_created_time;
	}
	public void setGoods_created_time(String date) {
		this.goods_created_time = date;
	}
	
	public Float getGoods_price() {
		return goods_price;
	}
	
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
}
