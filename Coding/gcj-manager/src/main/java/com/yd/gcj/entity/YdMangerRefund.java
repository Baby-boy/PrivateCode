package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerRefund {

	/**编号（主键）*/
	private Integer refund_id;
	
	private Integer refund_fid;//服务商id
	private Integer refund_gid;//雇主id
	private Integer refund_tid;//任务id
	private String refund_fname;//服务商姓名
	private String refund_gname;//雇主姓名
	private String refund_fphone;//服务商手机号
	private String refund_gphone;//雇主手机号
	private float refund_captial;//退款金额
	private Integer refund_state;//退款状态(0:提交审核中 1:退给雇主 2:退给服务商 3:拒绝申请)
	
	/**原因*/
	private String refund_reason;
	/**内容*/
	private String refund_contents;
	/**上传文件名称*/
	private String refund_filename;
	/**下载文件路径*/
	private String refund_filepath;
	/**信息创建时间*/
	private Date refund_create_time;
	/**信息更新时间*/
	private Date refund_update_time;
	
	/**文件信息id*/
	private Integer refund_fileid;
	/**提交申请用户id*/
	private Integer refund_uid;
	
	public Date getRefund_create_time() {
		return refund_create_time;
	}

	public void setRefund_create_time(Date refund_create_time) {
		this.refund_create_time = refund_create_time;
	}

	public Date getRefund_update_time() {
		return refund_update_time;
	}

	public void setRefund_update_time(Date refund_update_time) {
		this.refund_update_time = refund_update_time;
	}

	public Integer getRefund_fid() {
		return refund_fid;
	}

	public void setRefund_fid(Integer refund_fid) {
		this.refund_fid = refund_fid;
	}

	public Integer getRefund_gid() {
		return refund_gid;
	}

	public void setRefund_gid(Integer refund_gid) {
		this.refund_gid = refund_gid;
	}

	public Integer getRefund_tid() {
		return refund_tid;
	}

	public void setRefund_tid(Integer refund_tid) {
		this.refund_tid = refund_tid;
	}

	public String getRefund_fname() {
		return refund_fname;
	}

	public void setRefund_fname(String refund_fname) {
		this.refund_fname = refund_fname;
	}

	public String getRefund_gname() {
		return refund_gname;
	}

	public void setRefund_gname(String refund_gname) {
		this.refund_gname = refund_gname;
	}

	public String getRefund_fphone() {
		return refund_fphone;
	}

	public void setRefund_fphone(String refund_fphone) {
		this.refund_fphone = refund_fphone;
	}

	public String getRefund_gphone() {
		return refund_gphone;
	}

	public void setRefund_gphone(String refund_gphone) {
		this.refund_gphone = refund_gphone;
	}

	public float getRefund_captial() {
		return refund_captial;
	}

	public void setRefund_captial(float refund_captial) {
		this.refund_captial = refund_captial;
	}

	public Integer getRefund_state() {
		return refund_state;
	}

	public void setRefund_state(Integer refund_state) {
		this.refund_state = refund_state;
	}

	/**
	 * 获取 编号（主键）
	 * @return refund_id
	 */
	public Integer getRefund_id() {
		return refund_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param refund_id
	 */
	public void setRefund_id(Integer refund_id) {
		this.refund_id = refund_id;
	}
	
	/**
	 * 获取 原因
	 * @return refund_reason
	 */
	public String getRefund_reason() {
		return refund_reason;
	}
	
	/**
	 * 设置 原因
	 * @param refund_reason
	 */
	public void setRefund_reason(String refund_reason) {
		this.refund_reason = refund_reason;
	}
	
	/**
	 * 获取 内容
	 * @return refund_contents
	 */
	public String getRefund_contents() {
		return refund_contents;
	}
	
	/**
	 * 设置 内容
	 * @param refund_contents
	 */
	public void setRefund_contents(String refund_contents) {
		this.refund_contents = refund_contents;
	}
	
	/**
	 * 获取 上传文件名称
	 * @return refund_filename
	 */
	public String getRefund_filename() {
		return refund_filename;
	}
	
	/**
	 * 设置 上传文件名称
	 * @param refund_filename
	 */
	public void setRefund_filename(String refund_filename) {
		this.refund_filename = refund_filename;
	}
	
	/**
	 * 获取 下载文件路径
	 * @return refund_filepath
	 */
	public String getRefund_filepath() {
		return refund_filepath;
	}
	
	/**
	 * 设置 下载文件路径
	 * @param refund_filepath
	 */
	public void setRefund_filepath(String refund_filepath) {
		this.refund_filepath = refund_filepath;
	}

	public Integer getRefund_fileid() {
		return refund_fileid;
	}

	public void setRefund_fileid(Integer refund_fileid) {
		this.refund_fileid = refund_fileid;
	}

	public Integer getRefund_uid() {
		return refund_uid;
	}

	public void setRefund_uid(Integer refund_uid) {
		this.refund_uid = refund_uid;
	}
	
	
}
