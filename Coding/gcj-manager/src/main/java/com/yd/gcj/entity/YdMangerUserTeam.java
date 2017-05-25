package com.yd.gcj.entity;

import java.util.Date;
/***
 * 服务商用户团队信息
 * @author Administrator
 *
 */
public class YdMangerUserTeam {
	/**唯一标识符*/
	private Integer team_id;
	/**用户id*/
	private Integer team_uid;
	/**团队工作状态*/
	private Integer team_state;
	/**能否接受驻场*/
	private Integer team_isresid;
	/**公司名称*/
	private String team_ename;
	/**信息创建时间*/
	private Date team_create_time;
	/**信息最后一次更新时间*/
	private Date team_update_time;
	
	/**
	 * 获取 唯一标识符
	 * @return team_id
	 */
	public Integer getTeam_id() {
		return team_id;
	}
	
	/**
	 * 设置 唯一标识符
	 * @param team_id
	 */
	public void setTeam_id(Integer team_id) {
		this.team_id = team_id;
	}
	
	/**
	 * 获取 用户id
	 * @return team_uid
	 */
	public Integer getTeam_uid() {
		return team_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param team_uid
	 */
	public void setTeam_uid(Integer team_uid) {
		this.team_uid = team_uid;
	}
	
	/**
	 * 获取 团队工作状态
	 * @return team_state
	 */
	public Integer getTeam_state() {
		return team_state;
	}
	
	/**
	 * 设置 团队工作状态
	 * @param team_state
	 */
	public void setTeam_state(Integer team_state) {
		this.team_state = team_state;
	}
	
	/**
	 * 获取 能否接受驻场
	 * @return team_isresid
	 */
	public Integer getTeam_isresid() {
		return team_isresid;
	}
	
	/**
	 * 设置 能否接受驻场
	 * @param team_isresid
	 */
	public void setTeam_isresid(Integer team_isresid) {
		this.team_isresid = team_isresid;
	}
	
	/**
	 * 获取 公司名称
	 * @return team_ename
	 */
	public String getTeam_ename() {
		return team_ename;
	}
	
	/**
	 * 设置 公司名称
	 * @param team_ename
	 */
	public void setTeam_ename(String team_ename) {
		this.team_ename = team_ename;
	}
	
	/**
	 * 获取 信息创建时间
	 * @return team_create_time
	 */
	public Date getTeam_create_time() {
		return team_create_time;
	}
	
	/**
	 * 设置 信息创建时间
	 * @param team_create_time
	 */
	public void setTeam_create_time(Date team_create_time) {
		this.team_create_time = team_create_time;
	}
	
	/**
	 * 获取 信息最后一次更新时间
	 * @return team_update_time
	 */
	public Date getTeam_update_time() {
		return team_update_time;
	}
	
	/**
	 * 设置 信息最后一次更新时间
	 * @param team_update_time
	 */
	public void setTeam_update_time(Date team_update_time) {
		this.team_update_time = team_update_time;
	}
	
}
