package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerTask {

	/**主键（自增）*/
	private Integer task_id;
	/**任务编号*/
	private String task_num;
	/**雇主id*/
	private Integer task_uid;
	/**项目名称*/
	private String task_pname;
	/**项目地点*/
	private String task_paddr;
	/**期限*/
	private String task_term;
	/**描述详情*/
	private String task_discrip;
	/**排序*/
	private Integer task_sort = 0;
	/**状态
	 * 任务状态（0：发布审核中、1：审核通过（投标中）、2：投标结束，开始选标（投标时间截止）、3：编辑合同(并签订合同)、4：工作中、5：工作完成（并支付完尾款，待评价）、6：评价完成（任务完成）、7：退款申请、8：退款完成、9：审核失败）
	 */
	private Integer task_state = 0;
	/**任务报价*/
	private float task_price = 0;
	/**参与人数*/
	private String task_pnum;
	/**实际参与人数*/
	private Integer task_pnum_actual;
	/**是否选定组长*/
	private Integer task_lear_state;
	/**已付佣金*/
	private float task_pay_price;
	/**任务工作进度*/
	private Integer task_schedule = 0;
	/**是否托管（0默认否，1托管）*/
	private Integer task_hosting = 0;
	/**托管金额*/
	private float task_host_price;
	/**合同签订状态*/
	private Integer task_contract_state;
	/**任务发布时间*/
	private Date task_start_time;
	/**任务结束时间*/
	private Date task_end_time;
	/**提交时间*/
	private Date task_create_time;
	/**修改时间*/
	private Date task_update_time = new Date();
	
	/**
	 * 获取 主键（自增）
	 * @return task_id
	 */
	public Integer getTask_id() {
		return task_id;
	}
	
	/**
	 * 设置 主键（自增）
	 * @param task_id
	 */
	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}
	
	/**
	 * 获取 任务编号
	 * @return task_num
	 */
	public String getTask_num() {
		return task_num;
	}
	
	/**
	 * 设置 任务编号
	 * @param task_num
	 */
	public void setTask_num(String task_num) {
		this.task_num = task_num;
	}
	
	/**
	 * 获取 雇主id
	 * @return task_uid
	 */
	public Integer getTask_uid() {
		return task_uid;
	}
	
	/**
	 * 设置 雇主id
	 * @param task_uid
	 */
	public void setTask_uid(Integer task_uid) {
		this.task_uid = task_uid;
	}
	
	/**
	 * 获取 项目名称
	 * @return task_pname
	 */
	public String getTask_pname() {
		return task_pname;
	}
	
	/**
	 * 设置 项目名称
	 * @param task_pname
	 */
	public void setTask_pname(String task_pname) {
		this.task_pname = task_pname;
	}
	
	/**
	 * 获取 项目地点
	 * @return task_paddr
	 */
	public String getTask_paddr() {
		return task_paddr;
	}
	
	/**
	 * 设置 项目地点
	 * @param task_paddr
	 */
	public void setTask_paddr(String task_paddr) {
		this.task_paddr = task_paddr;
	}
	
	/**
	 * 获取 期限
	 * @return task_term
	 */
	public String getTask_term() {
		return task_term;
	}
	
	/**
	 * 设置 期限
	 * @param task_term
	 */
	public void setTask_term(String task_term) {
		this.task_term = task_term;
	}
	
	/**
	 * 获取 描述详情
	 * @return task_discrip
	 */
	public String getTask_discrip() {
		return task_discrip;
	}
	
	/**
	 * 设置 描述详情
	 * @param task_discrip
	 */
	public void setTask_discrip(String task_discrip) {
		this.task_discrip = task_discrip;
	}
	
	/**
	 * 获取 排序
	 * @return task_sort
	 */
	public Integer getTask_sort() {
		return task_sort;
	}
	
	/**
	 * 设置 排序
	 * @param task_sort
	 */
	public void setTask_sort(Integer task_sort) {
		this.task_sort = task_sort;
	}
	
	/**
	 * 获取 状态（任务完成度）
	 * @return task_state
	 */
	public Integer getTask_state() {
		return task_state;
	}
	
	/**
	 * 设置 状态（任务完成度）
	 * @param task_state
	 */
	public void setTask_state(Integer task_state) {
		this.task_state = task_state;
	}
	
	/**
	 * 获取 任务报价
	 * @return task_price
	 */
	public float getTask_price() {
		return task_price;
	}
	
	/**
	 * 设置 任务报价
	 * @param task_price
	 */
	public void setTask_price(float task_price) {
		this.task_price = task_price;
	}
	
	/**
	 * 获取 参与人数
	 * @return task_pnum
	 */
	public String getTask_pnum() {
		return task_pnum;
	}
	
	/**
	 * 设置 参与人数
	 * @param task_pnum
	 */
	public void setTask_pnum(String task_pnum) {
		this.task_pnum = task_pnum;
	}
	
	public Integer getTask_pnum_actual() {
		if(task_pnum_actual == null){
			task_pnum_actual = 0;
		}
		return task_pnum_actual;
	}

	public void setTask_pnum_actual(Integer task_pnum_actual) {
		this.task_pnum_actual = task_pnum_actual;
	}

	/**
	 * 获取 任务工作进度
	 * @return task_schedule
	 */
	public Integer getTask_schedule() {
		return task_schedule;
	}
	
	/**
	 * 设置 任务工作进度
	 * @param task_schedule
	 */
	public void setTask_schedule(Integer task_schedule) {
		this.task_schedule = task_schedule;
	}
	
	public Integer getTask_lear_state() {
		return task_lear_state;
	}

	public void setTask_lear_state(Integer task_lear_state) {
		this.task_lear_state = task_lear_state;
	}
	
	public float getTask_pay_price() {
		return task_pay_price;
	}

	public void setTask_pay_price(float task_pay_price) {
		this.task_pay_price = task_pay_price;
	}

	/**
	 * 获取 是否托管（0默认否，1托管）
	 * @return task_hosting
	 */
	public Integer getTask_hosting() {
		return task_hosting;
	}
	
	/**
	 * 设置 是否托管（0默认否，1托管）
	 * @param task_hosting
	 */
	public void setTask_hosting(Integer task_hosting) {
		this.task_hosting = task_hosting;
	}
	
	/**
	 * 获取 托管金额
	 * @return task_host_price
	 */
	public float getTask_host_price() {
		return task_host_price;
	}
	
	/**
	 * 设置 托管金额
	 * @param task_host_price
	 */
	public void setTask_host_price(float task_host_price) {
		this.task_host_price = task_host_price;
	}
	
	/**
	 * 获取 合同签订状态
	 * @return task_contract_state
	 */
	public Integer getTask_contract_state() {
		return task_contract_state;
	}
	
	/**
	 * 设置 合同签订状态
	 * @param task_contract_state
	 */
	public void setTask_contract_state(Integer task_contract_state) {
		this.task_contract_state = task_contract_state;
	}
	

	/**
	 * 获取 任务发布时间
	 * @return task_start_time
	 */
	public Date getTask_start_time() {
		return task_start_time;
	}
	
	/**
	 * 设置 任务发布时间
	 * @param task_start_time
	 */
	public void setTask_start_time(Date task_start_time) {
		this.task_start_time = task_start_time;
	}
	
	/**
	 * 获取 任务结束时间
	 * @return task_end_time
	 */
	public Date getTask_end_time() {
		return task_end_time;
	}
	
	/**
	 * 设置 任务结束时间
	 * @param task_end_time
	 */
	public void setTask_end_time(Date task_end_time) {
		this.task_end_time = task_end_time;
	}
	
	/**
	 * 获取 提交时间
	 * @return task_create_time
	 */
	public Date getTask_create_time() {
		return task_create_time;
	}
	
	/**
	 * 设置 提交时间
	 * @param task_create_time
	 */
	public void setTask_create_time(Date task_create_time) {
		this.task_create_time = task_create_time;
	}
	
	/**
	 * 获取 修改时间
	 * @return task_update_time
	 */
	public Date getTask_update_time() {
		return task_update_time;
	}
	
	/**
	 * 设置 修改时间
	 * @param task_update_time
	 */
	public void setTask_update_time(Date task_update_time) {
		this.task_update_time = task_update_time;
	}
	
		
}
