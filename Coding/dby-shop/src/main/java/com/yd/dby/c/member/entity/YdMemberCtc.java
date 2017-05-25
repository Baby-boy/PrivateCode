package com.yd.dby.c.member.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 懒鱼的实体类
 * 
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月7日 上午10:23:12
 */
public class YdMemberCtc {
	// 用户id
	private int user_id;
	// 懒鱼id
	private int ctc_id;
	// 名称
	private String ctc_name;
	// 简介
	private String ctc_summary;
	// 状态 1:出售中 2:下架 3:违规'
	private int ctc_is_available;
	// 首图
	private String ctc_cover;
	// 缩略图
	private String ctc_pics;
	// 分类一
	private int ctc_classify_id1;
	// 分类二
	private int ctc_classify_id2;
	// 评论次数
	private int ctc_total_message;
	// 收藏次数
	private int ctc_total_fav;
	// 价格
	private BigDecimal ctc_price;
	// 运费
	private BigDecimal ctc_logistics_price;
	// 省id
	private int ctc_province_id;
	// 市id
	private int ctc_city_id;
	// 区id
	private int ctc_area_id;

	// 省
	private String ctc_province_value;
	// 市
	private String ctc_city_value;
	// 区
	private String ctc_area_value;
	// 创建时间
	private Date ctc_created_time;
	// 几层新
	private Integer ctc_quality_id;
	// 几层新
	private String ctc_quality_text;
	// 地址
	private String ctc_pca;

	public String getCtc_quality_text() {
		return ctc_quality_text;
	}

	public void setCtc_quality_text(String ctc_quality_text) {
		this.ctc_quality_text = ctc_quality_text;
	}

	public int getCtc_area_id() {
		return ctc_area_id;
	}

	public void setCtc_area_id(int ctc_area_id) {
		this.ctc_area_id = ctc_area_id;
	}

	public String getCtc_area_value() {
		return ctc_area_value;
	}

	public void setCtc_area_value(String ctc_area_value) {
		this.ctc_area_value = ctc_area_value;
	}

	public String getCtc_pca() {
		return ctc_pca;
	}

	public void setCtc_pca(String ctc_pca) {
		this.ctc_pca = ctc_pca;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getCtc_id() {
		return ctc_id;
	}

	public void setCtc_id(int ctc_id) {
		this.ctc_id = ctc_id;
	}

	public String getCtc_name() {
		return ctc_name;
	}

	public void setCtc_name(String ctc_name) {
		this.ctc_name = ctc_name;
	}

	public String getCtc_summary() {
		return ctc_summary;
	}

	public void setCtc_summary(String ctc_summary) {
		this.ctc_summary = ctc_summary;
	}

	public int getCtc_is_available() {
		return ctc_is_available;
	}

	public void setCtc_is_available(int ctc_is_available) {
		this.ctc_is_available = ctc_is_available;
	}

	public String getCtc_cover() {
		return ctc_cover;
	}

	public void setCtc_cover(String ctc_cover) {
		this.ctc_cover = ctc_cover;
	}

	public String getCtc_pics() {
		return ctc_pics;
	}

	public void setCtc_pics(String ctc_pics) {
		this.ctc_pics = ctc_pics;
	}

	public int getCtc_classify_id1() {
		return ctc_classify_id1;
	}

	public void setCtc_classify_id1(int ctc_classify_id1) {
		this.ctc_classify_id1 = ctc_classify_id1;
	}

	public int getCtc_classify_id2() {
		return ctc_classify_id2;
	}

	public void setCtc_classify_id2(int ctc_classify_id2) {
		this.ctc_classify_id2 = ctc_classify_id2;
	}

	public int getCtc_total_message() {
		return ctc_total_message;
	}

	public void setCtc_total_message(int ctc_total_message) {
		this.ctc_total_message = ctc_total_message;
	}

	public int getCtc_total_fav() {
		return ctc_total_fav;
	}

	public void setCtc_total_fav(int ctc_total_fav) {
		this.ctc_total_fav = ctc_total_fav;
	}

	public BigDecimal getCtc_price() {
		return ctc_price;
	}

	public void setCtc_price(BigDecimal ctc_price) {
		this.ctc_price = ctc_price;
	}

	public BigDecimal getCtc_logistics_price() {
		return ctc_logistics_price;
	}

	public void setCtc_logistics_price(BigDecimal ctc_logistics_price) {
		this.ctc_logistics_price = ctc_logistics_price;
	}

	public int getCtc_province_id() {
		return ctc_province_id;
	}

	public void setCtc_province_id(int ctc_province_id) {
		this.ctc_province_id = ctc_province_id;
	}

	public int getCtc_city_id() {
		return ctc_city_id;
	}

	public void setCtc_city_id(int ctc_city_id) {
		this.ctc_city_id = ctc_city_id;
	}

	public String getCtc_province_value() {
		return ctc_province_value;
	}

	public void setCtc_province_value(String ctc_province_value) {
		this.ctc_province_value = ctc_province_value;
	}

	public String getCtc_city_value() {
		return ctc_city_value;
	}

	public void setCtc_city_value(String ctc_city_value) {
		this.ctc_city_value = ctc_city_value;
	}

	public Date getCtc_created_time() {
		return ctc_created_time;
	}

	public void setCtc_created_time(Date ctc_created_time) {
		this.ctc_created_time = ctc_created_time;
	}

	public Integer getCtc_quality_id() {
		return ctc_quality_id;
	}

	public void setCtc_quality_id(Integer ctc_quality_id) {
		this.ctc_quality_id = ctc_quality_id;
	}

}
