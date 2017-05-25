package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerTender {

	/**编号*/
	private Integer tender_id;
	/**任务id*/
	private Integer tender_tid;
	/**服务商id*/
	private Integer tender_uid;
	/**公司or个人*/
	private Integer tender_utype;
	/**申请人*/
	private String tender_uname;
	/**手机号*/
	private String tender_uphone;
	/**QQ号码*/
	private String tender_uqq;
	/**邮箱*/
	private String tender_uemail;
	/**所属企业*/
	private String tender_uine;
	/**好评数*/
	private Integer tender_ugnum;
	/**服务商级别*/
	private Integer tender_ulevel;
	/**投标价格*/
	private float tender_price;
	/**团队人数*/
	private Integer tender_pnum;
	/**其他说明（备注）*/
	private String tender_conts;
	/**投标状态 0等待 1选中 2未选中	*/
	private Integer tender_state;
	/**已支付佣金*/
	private float tender_pcomm;
	/**任务中所担任角色*/
	private Integer tender_type;
	/**信息是否被删除*/
	private Integer tender_isdel;
	/**信息创建时间*/
	private Date tender_create_time;
	/**信息更新时间*/
	private Date tender_update_time;
	
	
	
	/**
	 * 获取 编号
	 * @return tender_id
	 */
	public Integer getTender_id() {
		return tender_id;
	}
	
	/**
	 * 设置 编号
	 * @param tender_id
	 */
	public void setTender_id(Integer tender_id) {
		this.tender_id = tender_id;
	}
	
	/**
	 * 获取 任务id
	 * @return tender_tid
	 */
	public Integer getTender_tid() {
		return tender_tid;
	}
	
	/**
	 * 设置 任务id
	 * @param tender_tid
	 */
	public void setTender_tid(Integer tender_tid) {
		this.tender_tid = tender_tid;
	}
	
	/**
	 * 获取 服务商id
	 * @return tender_uid
	 */
	public Integer getTender_uid() {
		return tender_uid;
	}
	
	/**
	 * 设置 服务商id
	 * @param tender_uid
	 */
	public void setTender_uid(Integer tender_uid) {
		this.tender_uid = tender_uid;
	}
	
	/**
	 * 获取 公司or个人
	 * @return tender_utype
	 */
	public Integer getTender_utype() {
		return tender_utype;
	}
	
	/**
	 * 设置 公司or个人
	 * @param tender_utype
	 */
	public void setTender_utype(Integer tender_utype) {
		this.tender_utype = tender_utype;
	}
	

	/**
	 * 获取 申请人
	 * @return tender_uname
	 */
	public String getTender_uname() {
		return tender_uname;
	}
	
	/**
	 * 设置 申请人
	 * @param tender_uname
	 */
	public void setTender_uname(String tender_uname) {
		this.tender_uname = tender_uname;
	}
	
	/**
	 * 获取 手机号
	 * @return tender_uphone
	 */
	public String getTender_uphone() {
		return tender_uphone;
	}
	
	/**
	 * 设置 手机号
	 * @param tender_uphone
	 */
	public void setTender_uphone(String tender_uphone) {
		this.tender_uphone = tender_uphone;
	}
	
	/**
	 * 获取 QQ号码
	 * @return tender_uqq
	 */
	public String getTender_uqq() {
		tender_uqq = tender_uqq==null?"":tender_uqq;
		return tender_uqq;
	}
	

	/**
	 * 设置 QQ号码
	 * @param tender_uqq
	 */
	public void setTender_uqq(String tender_uqq) {
		this.tender_uqq = tender_uqq;
	}
	

	/**
	 * 获取 邮箱
	 * @return tender_uemail
	 */
	public String getTender_uemail() {
		return tender_uemail;
	}
	
	/**
	 * 设置 邮箱
	 * @param tender_uemail
	 */
	public void setTender_uemail(String tender_uemail) {
		this.tender_uemail = tender_uemail;
	}
	
	/**
	 * 获取 所属企业
	 * @return tender_uine
	 */
	public String getTender_uine() {
		return tender_uine;
	}
	
	/**
	 * 设置 所属企业
	 * @param tender_uine
	 */
	public void setTender_uine(String tender_uine) {
		this.tender_uine = tender_uine;
	}
	
	/**
	 * 获取 好评数
	 * @return tender_ugnum
	 */
	public Integer getTender_ugnum() {
		return tender_ugnum;
	}
	
	/**
	 * 设置 好评数
	 * @param tender_ugnum
	 */
	public void setTender_ugnum(Integer tender_ugnum) {
		this.tender_ugnum = tender_ugnum;
	}
	
	/**
	 * 获取 服务商级别
	 * @return tender_ulevel
	 */
	public Integer getTender_ulevel() {
		return tender_ulevel;
	}
	
	/**
	 * 设置 服务商级别
	 * @param tender_ulevel
	 */
	public void setTender_ulevel(Integer tender_ulevel) {
		this.tender_ulevel = tender_ulevel;
	}
	
	/**
	 * 获取 投标价格
	 * @return tender_price
	 */
	public float getTender_price() {
		return tender_price;
	}
	
	/**
	 * 设置 投标价格
	 * @param tender_price
	 */
	public void setTender_price(float tender_price) {
		this.tender_price = tender_price;
	}
	
	/**
	 * 获取 团队人数
	 * @return tender_pnum
	 */
	public Integer getTender_pnum() {
		return tender_pnum;
	}
	
	/**
	 * 设置 团队人数
	 * @param tender_pnum
	 */
	public void setTender_pnum(Integer tender_pnum) {
		this.tender_pnum = tender_pnum;
	}
	
	/**
	 * 获取 其他说明（备注）
	 * @return tender_conts
	 */
	public String getTender_conts() {
		return tender_conts;
	}
	
	/**
	 * 设置 其他说明（备注）
	 * @param tender_conts
	 */
	public void setTender_conts(String tender_conts) {
		this.tender_conts = tender_conts;
	}
	
	/**
	 * 获取 投标状态0等待1选中2未选中
	 * @return tender_state
	 */
	public Integer getTender_state() {
		return tender_state;
	}
	
	/**
	 * 设置 投标状态0等待1选中2未选中
	 * @param tender_state
	 */
	public void setTender_state(Integer tender_state) {
		this.tender_state = tender_state;
	}
	
	/**
	 * 获取 已支付佣金
	 * @return tender_pcomm
	 */
	public float getTender_pcomm() {
		return tender_pcomm;
	}
	
	/**
	 * 设置 已支付佣金
	 * @param tender_pcomm
	 */
	public void setTender_pcomm(float tender_pcomm) {
		this.tender_pcomm = tender_pcomm;
	}
	
	/**
	 * 获取 任务中所担任角色
	 * @return tender_type
	 */
	public Integer getTender_type() {
		return tender_type;
	}
	
	/**
	 * 设置 任务中所担任角色
	 * @param tender_type
	 */
	public void setTender_type(Integer tender_type) {
		this.tender_type = tender_type;
	}
	
	/**
	 * 获取 信息是否被删除
	 * @return tender_isdel
	 */
	public Integer getTender_isdel() {
		return tender_isdel;
	}
	
	/**
	 * 设置 信息是否被删除
	 * @param tender_isdel
	 */
	public void setTender_isdel(Integer tender_isdel) {
		this.tender_isdel = tender_isdel;
	}

	/**
	 * 获取 信息创建时间
	 * @return tender_create_time
	 */
	public Date getTender_create_time() {
		return tender_create_time;
	}
	

	/**
	 * 设置 信息创建时间
	 * @param tender_create_time
	 */
	public void setTender_create_time(Date tender_create_time) {
		this.tender_create_time = tender_create_time;
	}
	

	/**
	 * 获取 信息更新时间
	 * @return tender_update_time
	 */
	public Date getTender_update_time() {
		return tender_update_time;
	}
	

	/**
	 * 设置 信息更新时间
	 * @param tender_update_time
	 */
	public void setTender_update_time(Date tender_update_time) {
		this.tender_update_time = tender_update_time;
	}
	
	
}
