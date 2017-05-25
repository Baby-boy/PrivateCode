package com.yd.gcj.entity;

public class YdMangerUserLabel {

	/**用户id*/
	private Integer userl_uid;
	/**技能id*/
	private Integer userl_lid;
	/**技能类型*/
	private Integer userl_type;
	/**技能名称*/
	private String userl_name;
	/**技能描述*/
	private String userl_desc;
	/**技能父id*/
	private Integer userl_pid;
	/**
	 * 获取 用户id
	 * @return userl_uid
	 */
	public Integer getUserl_uid() {
		return userl_uid;
	}
	
	/**
	 * 设置 用户id
	 * @param userl_uid
	 */
	public void setUserl_uid(Integer userl_uid) {
		this.userl_uid = userl_uid;
	}
	
	/**
	 * 获取 技能id
	 * @return userl_lid
	 */
	public Integer getUserl_lid() {
		return userl_lid;
	}
	
	/**
	 * 设置 技能id
	 * @param userl_lid
	 */
	public void setUserl_lid(Integer userl_lid) {
		this.userl_lid = userl_lid;
	}
	
	/**
	 * 获取 技能类型
	 * @return userl_type
	 */
	public Integer getUserl_type() {
		return userl_type;
	}
	
	/**
	 * 设置 技能类型
	 * @param userl_type
	 */
	public void setUserl_type(Integer userl_type) {
		this.userl_type = userl_type;
	}
	
	/**
	 * 获取 技能名称
	 * @return userl_name
	 */
	public String getUserl_name() {
		return userl_name;
	}
	
	/**
	 * 设置 技能名称
	 * @param userl_name
	 */
	public void setUserl_name(String userl_name) {
		this.userl_name = userl_name;
	}
	
	/**
	 * 获取 技能描述
	 * @return userl_desc
	 */
	public String getUserl_desc() {
		return userl_desc;
	}
	
	/**
	 * 设置 技能描述
	 * @param userl_desc
	 */
	public void setUserl_desc(String userl_desc) {
		this.userl_desc = userl_desc;
	}
	
	/**
	 * 获取 技能父id
	 * @return userl_pid
	 */
	public Integer getUserl_pid() {
		return userl_pid;
	}
	
	/**
	 * 设置 技能父id
	 * @param userl_pid
	 */
	public void setUserl_pid(Integer userl_pid) {
		this.userl_pid = userl_pid;
	}
}
