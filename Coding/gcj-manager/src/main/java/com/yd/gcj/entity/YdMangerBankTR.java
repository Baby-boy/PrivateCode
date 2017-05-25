package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerBankTR {

	/**编号（主键）*/
	private Integer btr_id;
	/**用户id*/
	private Integer btr_uid;
	/**交易日期*/
	private Date btr_rtime;
	/**交易金额*/
	private float btr_price;
	/**交易类型*/
	private Integer btr_type;
	/**交易账号*/
	private String btr_account;
	/**交易类型描述（如：支付宝支付or建设银行）*/
	private String btr_tdesc;
	/**交易用户id*/
	private Integer btr_osid;
	/**对方交易账号*/
	private String btr_aos;
	/**信息创建时间*/
	private Date btr_create_time;
	
	public Date getBtr_create_time() {
		return btr_create_time;
	}

	public void setBtr_create_time(Date btr_create_time) {
		this.btr_create_time = btr_create_time;
	}

	/**
	 * 获取 编号（主键）
	 * @return btr_id
	 */
	public Integer getBtr_id() {
		return btr_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param btr_id
	 */
	public void setBtr_id(Integer btr_id) {
		this.btr_id = btr_id;
	}
	
	/**
	 * 获取 用户id
	 * @return btr_uid
	 */
	public Integer getBtr_uid() {
		return btr_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param btr_uid
	 */
	public void setBtr_uid(Integer btr_uid) {
		this.btr_uid = btr_uid;
	}
	
	/**
	 * 获取 交易日期
	 * @return btr_rtime
	 */
	public Date getBtr_rtime() {
		return btr_rtime;
	}
	
	/**
	 * 设置 交易日期
	 * @param btr_rtime
	 */
	public void setBtr_rtime(Date btr_rtime) {
		this.btr_rtime = btr_rtime;
	}
	
	/**
	 * 获取 交易金额
	 * @return btr_price
	 */
	public float getBtr_price() {
		return btr_price;
	}
	
	/**
	 * 设置 交易金额
	 * @param btr_price
	 */
	public void setBtr_price(float btr_price) {
		this.btr_price = btr_price;
	}
	
	/**
	 * 获取 交易类型
	 * @return btr_type
	 */
	public Integer getBtr_type() {
		return btr_type;
	}
	
	/**
	 * 设置 交易类型
	 * @param btr_type
	 */
	public void setBtr_type(Integer btr_type) {
		this.btr_type = btr_type;
	}
	
	/**
	 * 获取 交易账号
	 * @return btr_account
	 */
	public String getBtr_account() {
		return btr_account;
	}
	
	/**
	 * 设置 交易账号
	 * @param btr_account
	 */
	public void setBtr_account(String btr_account) {
		this.btr_account = btr_account;
	}
	
	/**
	 * 获取 交易类型描述（如：支付宝支付or建设银行）
	 * @return btr_tdesc
	 */
	public String getBtr_tdesc() {
		return btr_tdesc;
	}
	
	/**
	 * 设置 交易类型描述（如：支付宝支付or建设银行）
	 * @param btr_tdesc
	 */
	public void setBtr_tdesc(String btr_tdesc) {
		this.btr_tdesc = btr_tdesc;
	}
	
	/**
	 * 获取 交易用户id
	 * @return btr_osid
	 */
	public Integer getBtr_osid() {
		return btr_osid;
	}
	
	/**
	 * 设置 交易用户id
	 * @param btr_osid
	 */
	public void setBtr_osid(Integer btr_osid) {
		this.btr_osid = btr_osid;
	}
	
	/**
	 * 获取 对方交易账号
	 * @return btr_aos
	 */
	public String getBtr_aos() {
		return btr_aos;
	}
	
	/**
	 * 设置 对方交易账号
	 * @param btr_aos
	 */
	public void setBtr_aos(String btr_aos) {
		this.btr_aos = btr_aos;
	}
	
	
}
