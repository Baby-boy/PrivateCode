package com.yd.gcj.entity;

public class YdMangerFeedBack {

	/**编号（主键）*/
	private Integer fb_id;
	/**问题*/
	private String fb_problem;
	/**用户id*/
	private Integer fb_uid;
	/**联系手机号*/
	private String fb_phone;
	/**反馈内容*/
	private String fb_content;
	/**后台管理员是否已查看信息*/
	private Integer fb_isread;
	/**后台管理员备注信息*/
	private String fb_remarks;
	/**是否被删除*/
	private Integer fb_isdel;
	/**信息创建时间*/
	private long fb_create_time;
	/**信息更新时间*/
	private long fb_update_time;
	
	/**
	 * 获取 编号（主键）
	 * @return fb_id
	 */
	public Integer getFb_id() {
		return fb_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param fb_id
	 */
	public void setFb_id(Integer fb_id) {
		this.fb_id = fb_id;
	}
	
	/**
	 * 获取 问题
	 * @return fb_problem
	 */
	public String getFb_problem() {
		return fb_problem;
	}
	
	/**
	 * 设置 问题
	 * @param fb_problem
	 */
	public void setFb_problem(String fb_problem) {
		this.fb_problem = fb_problem;
	}
	
	/**
	 * 获取 用户id
	 * @return fb_uid
	 */
	public Integer getFb_uid() {
		return fb_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param fb_uid
	 */
	public void setFb_uid(Integer fb_uid) {
		this.fb_uid = fb_uid;
	}
	
	/**
	 * 获取 联系手机号
	 * @return fb_phone
	 */
	public String getFb_phone() {
		return fb_phone;
	}
	
	/**
	 * 设置 联系手机号
	 * @param fb_phone
	 */
	public void setFb_phone(String fb_phone) {
		this.fb_phone = fb_phone;
	}
	
	/**
	 * 获取 反馈内容
	 * @return fb_content
	 */
	public String getFb_content() {
		return fb_content;
	}
	
	/**
	 * 设置 反馈内容
	 * @param fb_content
	 */
	public void setFb_content(String fb_content) {
		this.fb_content = fb_content;
	}
	
	/**
	 * 获取 后台管理员是否已查看信息
	 * @return fb_isAdminRead
	 */
	
	/**
	 * 获取 后台管理员备注信息
	 * @return fb_remarks
	 */
	public String getFb_remarks() {
		return fb_remarks;
	}
	
	/**
	 * 获取 后台管理员是否已查看信息
	 * @return fb_isread
	 */
	public Integer getFb_isread() {
		return fb_isread;
	}
	

	/**
	 * 设置 后台管理员是否已查看信息
	 * @param fb_isread
	 */
	public void setFb_isread(Integer fb_isread) {
		this.fb_isread = fb_isread;
	}
	

	/**
	 * 设置 后台管理员备注信息
	 * @param fb_remarks
	 */
	public void setFb_remarks(String fb_remarks) {
		this.fb_remarks = fb_remarks;
	}
	

	/**
	 * 获取 是否被删除
	 * @return fb_isdel
	 */
	public Integer getFb_isdel() {
		return fb_isdel;
	}
	

	/**
	 * 设置 是否被删除
	 * @param fb_isdel
	 */
	public void setFb_isdel(Integer fb_isdel) {
		this.fb_isdel = fb_isdel;
	}
	

	/**
	 * 获取 信息创建时间
	 * @return fb_create_time
	 */
	public long getFb_create_time() {
		return fb_create_time;
	}
	
	/**
	 * 设置 信息创建时间
	 * @param fb_create_time
	 */
	public void setFb_create_time(long fb_create_time) {
		this.fb_create_time = fb_create_time;
	}
	
	/**
	 * 获取 信息更新时间
	 * @return fb_update_time
	 */
	public long getFb_update_time() {
		return fb_update_time;
	}
	
	/**
	 * 设置 信息更新时间
	 * @param fb_update_time
	 */
	public void setFb_update_time(long fb_update_time) {
		this.fb_update_time = fb_update_time;
	}
	
}
