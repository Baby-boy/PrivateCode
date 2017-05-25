package com.yd.gcj.entity;

import java.util.Date;

public class YdMangerCollection {

	/**编号（主键）*/
	private Integer colle_id;
	/**用户id*/
	private Integer colle_uid;
	/**任务id*/
	private Integer colle_taskid;
	/**项目名称*/
	private String colle_tpn;
	/**项目地点*/
	private String colle_tpa;
	/**佣金*/
	private float colle_price;
	/**工期*/
	private String colle_term;
	/**发布时间*/
	private Date colle_start_time;
	/**截止时间*/
	private Date colle_end_time;
	/**剩余时间*/
	private String remainDateTime;
	
	/**
	 * 获取 编号（主键）
	 * @return colle_id
	 */
	public Integer getColle_id() {
		return colle_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param colle_id
	 */
	public void setColle_id(Integer colle_id) {
		this.colle_id = colle_id;
	}
	
	/**
	 * 获取 用户id
	 * @return colle_uid
	 */
	public Integer getColle_uid() {
		return colle_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param colle_uid
	 */
	public void setColle_uid(Integer colle_uid) {
		this.colle_uid = colle_uid;
	}
	
	/**
	 * 获取 任务id
	 * @return colle_taskid
	 */
	public Integer getColle_taskid() {
		return colle_taskid;
	}
	
	/**
	 * 设置 任务id
	 * @param colle_taskid
	 */
	public void setColle_taskid(Integer colle_taskid) {
		this.colle_taskid = colle_taskid;
	}
	
	/**
	 * 获取 项目名称
	 * @return colle_tpn
	 */
	public String getColle_tpn() {
		return colle_tpn;
	}
	
	/**
	 * 设置 项目名称
	 * @param colle_tpn
	 */
	public void setColle_tpn(String colle_tpn) {
		this.colle_tpn = colle_tpn;
	}
	
	/**
	 * 获取 项目地点
	 * @return colle_tpa
	 */
	public String getColle_tpa() {
		return colle_tpa;
	}
	
	/**
	 * 设置 项目地点
	 * @param colle_tpa
	 */
	public void setColle_tpa(String colle_tpa) {
		this.colle_tpa = colle_tpa;
	}
	
	/**
	 * 获取 佣金
	 * @return colle_price
	 */
	public float getColle_price() {
		return colle_price;
	}
	
	/**
	 * 设置 佣金
	 * @param colle_price
	 */
	public void setColle_price(float colle_price) {
		this.colle_price = colle_price;
	}

	/**
	 * 获取 工期
	 * @return colle_term
	 */
	public String getColle_term() {
		colle_term = colle_term==null?"":colle_term;
		return colle_term;
	}
	

	/**
	 * 设置 工期
	 * @param colle_term
	 */
	public void setColle_term(String colle_term) {
		this.colle_term = colle_term;
	}
	

	/**
	 * 获取 发布时间
	 * @return colle_start_time
	 */
	public Date getColle_start_time() {
		return colle_start_time;
	}
	

	/**
	 * 设置 发布时间
	 * @param colle_start_time
	 */
	public void setColle_start_time(Date colle_start_time) {
		this.colle_start_time = colle_start_time;
	}
	

	/**
	 * 获取 截止时间
	 * @return colle_end_time
	 */
	public Date getColle_end_time() {
		return colle_end_time;
	}
	

	/**
	 * 设置 截止时间
	 * @param colle_end_time
	 */
	public void setColle_end_time(Date colle_end_time) {
		this.colle_end_time = colle_end_time;
	}
	
	/**
	 * 获取 剩余时间
	 * @return remainDateTime
	 */
	public String getRemainDateTime() {
		return remainDateTimes();
	}
	

	/**
	 * 设置 剩余时间
	 * @param remainDateTime
	 */
	public void setRemainDateTime(String remainDateTime) {
		this.remainDateTime = remainDateTime;
	}
	

	private String remainDateTimes(){
		long nowt = new Date().getTime();
		long endt = colle_end_time.getTime();
		Integer s = 1000;
		Integer m = 1000*60;
		Integer h = 1000*60*60;
		Integer d = 1000*60*60*24;
		long remainTime = endt-nowt;
		String remainDT = "0天0时0分0秒";
		try {
			Integer remain = 0;
			Integer remainH = 0;
			Integer remainM = 0;
			Integer remainS = 0;
			if(remainTime>0){
				Integer dNum = (int) (remainTime/d);
				if(dNum>=1){
					remain = dNum;
				}else{
					remain = 0;
				}
				Integer hNum = (int) ((remainTime%d)/h);
				if(hNum >= 1){
					remainH = hNum;
				}else{
					remainH = 0;
				}
				Integer mNum = (int) ((remainTime%h)/m);
				if(mNum>=1){
					remainM = mNum;
				}else{
					remainM = 0;
				}
				Integer sNum = (int) ((remainTime%m)/s);
				if(sNum>=1){
					remainS = sNum;
				}else{
					remainS = 0;
				}
				remainDT = remain+" 天"+remainH+" 小时"+remainM+" 分"+remainS+" 秒";
			}else{
				remainDT = "0天0小时0分0秒";
			}
		} catch (Exception e) {
			remainDT = "0天0时0分0秒";
		}
		return remainDT;
	}
}
