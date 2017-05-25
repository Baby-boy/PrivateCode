package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerBanner {

	/**编号（主键）*/
	private Integer banner_id;
	/**标题*/
	private String banner_title;
	/**副标题*/
	private String banner_subtitle;
	/**类型*/
	private Integer banner_type;
	/**图片指向地址*/
	private String banner_jumpurl;
	/**图片地址*/
	private String banner_imgsrc;
	/**新窗口打开 1是 0否*/
	private Integer banner_isopen;
	/**排序*/
	private Integer banner_sort;
	/**信息创建时间*/
	private Date banner_create_time;
	/**信息更新时间*/
	private Date banner_update_time;
	
	
	/**
	 * 获取 编号（主键）
	 * @return banner_id
	 */
	public Integer getBanner_id() {
		return banner_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param banner_id
	 */
	public void setBanner_id(Integer banner_id) {
		this.banner_id = banner_id;
	}
	
	/**
	 * 获取 标题
	 * @return banner_title
	 */
	public String getBanner_title() {
		return banner_title;
	}
	
	/**
	 * 设置 标题
	 * @param banner_title
	 */
	public void setBanner_title(String banner_title) {
		this.banner_title = banner_title;
	}
	
	/**
	 * 获取 副标题
	 * @return banner_subtitle
	 */
	public String getBanner_subtitle() {
		return banner_subtitle;
	}
	
	/**
	 * 设置 副标题
	 * @param banner_subtitle
	 */
	public void setBanner_subtitle(String banner_subtitle) {
		this.banner_subtitle = banner_subtitle;
	}
	
	/**
	 * 获取 类型
	 * @return banner_type
	 */
	public Integer getBanner_type() {
		return banner_type;
	}
	
	/**
	 * 设置 类型
	 * @param banner_type
	 */
	public void setBanner_type(Integer banner_type) {
		this.banner_type = banner_type;
	}
	
	/**
	 * 获取 图片指向地址
	 * @return banner_jumpurl
	 */
	public String getBanner_jumpurl() {
		return banner_jumpurl;
	}
	
	/**
	 * 设置 图片指向地址
	 * @param banner_jumpurl
	 */
	public void setBanner_jumpurl(String banner_jumpurl) {
		this.banner_jumpurl = banner_jumpurl;
	}
	
	/**
	 * 获取 图片地址
	 * @return banner_imgsrc
	 */
	public String getBanner_imgsrc() {
		return banner_imgsrc;
	}
	
	/**
	 * 设置 图片地址
	 * @param banner_imgsrc
	 */
	public void setBanner_imgsrc(String banner_imgsrc) {
		this.banner_imgsrc = banner_imgsrc;
	}
	
	/**
	 * 获取 新窗口打开1是0否
	 * @return banner_isopen
	 */
	public Integer getBanner_isopen() {
		return banner_isopen;
	}
	
	/**
	 * 设置 新窗口打开1是0否
	 * @param banner_isopen
	 */
	public void setBanner_isopen(Integer banner_isopen) {
		this.banner_isopen = banner_isopen;
	}
	
	/**
	 * 获取 排序
	 * @return banner_sort
	 */
	public Integer getBanner_sort() {
		return banner_sort;
	}
	
	/**
	 * 设置 排序
	 * @param banner_sort
	 */
	public void setBanner_sort(Integer banner_sort) {
		this.banner_sort = banner_sort;
	}

	/**
	 * 获取 信息创建时间
	 * @return banner_create_time
	 */
	public Date getBanner_create_time() {
		return banner_create_time;
	}
	

	/**
	 * 设置 信息创建时间
	 * @param banner_create_time
	 */
	public void setBanner_create_time(Date banner_create_time) {
		this.banner_create_time = banner_create_time;
	}
	

	/**
	 * 获取 信息更新时间
	 * @return banner_update_time
	 */
	public Date getBanner_update_time() {
		return banner_update_time;
	}
	

	/**
	 * 设置 信息更新时间
	 * @param banner_update_time
	 */
	public void setBanner_update_time(Date banner_update_time) {
		this.banner_update_time = banner_update_time;
	}
	
	
	
}
