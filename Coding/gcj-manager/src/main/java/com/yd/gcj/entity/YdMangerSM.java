package com.yd.gcj.entity;

public class YdMangerSM {

	/**编号（主键）*/
	private Integer statm_id;
	/**所属用户id*/
	private Integer statm_uid;
	/**会员ID*/
	private Integer statm_memberid;
	/**会员名称*/
	private String statm_membername;
	/**统计时间，当前按照最小时间单位为天*/
	private long statm_time;
	/**记录更新时间*/
	private long statm_updatetime;
	
	
	/**
	 * 获取 编号（主键）
	 * @return statm_id
	 */
	public Integer getStatm_id() {
		return statm_id;
	}
	
	/**
	 * 设置 编号（主键）
	 * @param statm_id
	 */
	public void setStatm_id(Integer statm_id) {
		this.statm_id = statm_id;
	}
	
	/**
	 * 获取 所属用户id
	 * @return statm_uid
	 */
	public Integer getStatm_uid() {
		return statm_uid;
	}
	
	/**
	 * 设置 所属用户id
	 * @param statm_uid
	 */
	public void setStatm_uid(Integer statm_uid) {
		this.statm_uid = statm_uid;
	}
	
	/**
	 * 获取 会员ID
	 * @return statm_memberid
	 */
	public Integer getStatm_memberid() {
		return statm_memberid;
	}
	
	/**
	 * 设置 会员ID
	 * @param statm_memberid
	 */
	public void setStatm_memberid(Integer statm_memberid) {
		this.statm_memberid = statm_memberid;
	}
	
	/**
	 * 获取 会员名称
	 * @return statm_membername
	 */
	public String getStatm_membername() {
		return statm_membername;
	}
	
	/**
	 * 设置 会员名称
	 * @param statm_membername
	 */
	public void setStatm_membername(String statm_membername) {
		this.statm_membername = statm_membername;
	}
	
	/**
	 * 获取 统计时间，当前按照最小时间单位为天
	 * @return statm_time
	 */
	public long getStatm_time() {
		return statm_time;
	}
	
	/**
	 * 设置 统计时间，当前按照最小时间单位为天
	 * @param statm_time
	 */
	public void setStatm_time(long statm_time) {
		this.statm_time = statm_time;
	}
	
	/**
	 * 获取 记录更新时间
	 * @return statm_updatetime
	 */
	public long getStatm_updatetime() {
		return statm_updatetime;
	}
	
	/**
	 * 设置 记录更新时间
	 * @param statm_updatetime
	 */
	public void setStatm_updatetime(long statm_updatetime) {
		this.statm_updatetime = statm_updatetime;
	}
	
}
