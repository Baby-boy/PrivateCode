package com.yd.gcj.entity;

public class YdMangerTaskType {
	
	/**任务类型id*/
	private Integer taskt_id;
	
	/**任务类型名称*/
	private String taskt_name;
	
	/**是否被选中*/
	private Integer is_select = 0;
	
	/**
	 * 获取 任务类型id
	 * @return taskt_id
	 */
	public Integer getTaskt_id() {
		return taskt_id;
	}
	
	/**
	 * 设置 任务类型id
	 * @param taskt_id
	 */
	public void setTaskt_id(Integer taskt_id) {
		this.taskt_id = taskt_id;
	}
	
	/**
	 * 获取 任务类型名称
	 * @return taskt_name
	 */
	public String getTaskt_name() {
		return taskt_name;
	}
	
	/**
	 * 设置 任务类型名称
	 * @param taskt_name
	 */
	public void setTaskt_name(String taskt_name) {
		this.taskt_name = taskt_name;
	}

	/**
	 * 获取 是否被选中
	 * @return is_select
	 */
	public Integer getIs_select() {
		return is_select;
	}
	
	/**
	 * 设置 是否被选中
	 * @param is_select
	 */
	public void setIs_select(Integer is_select) {
		this.is_select = is_select;
	}
	
}
