 package com.yd.gcj.entity;

public class YdMangerTaskTemp {
	
	/**主键*/
	private Integer tasktemp_id;
	/**任务编号*/
	private String tasktemp_num;
	/**面积*/
	private Integer tasktemp_area;
	/**金额*/
	private float tasktemp_price;
	/**参与人数*/
	private Integer tasktemp_pnum;
	/**工作进度*/
	private Integer tasktemp_schedule;
	
	
	/**
	 * 获取 主键
	 * @return tasktemp_id
	 */
	public Integer getTasktemp_id() {
		return tasktemp_id;
	}
	
	/**
	 * 设置 主键
	 * @param tasktemp_id
	 */
	public void setTasktemp_id(Integer tasktemp_id) {
		this.tasktemp_id = tasktemp_id;
	}
	
	/**
	 * 获取 任务编号
	 * @return tasktemp_num
	 */
	public String getTasktemp_num() {
		return tasktemp_num;
	}
	
	/**
	 * 设置 任务编号
	 * @param tasktemp_num
	 */
	public void setTasktemp_num(String tasktemp_num) {
		this.tasktemp_num = tasktemp_num;
	}
	
	/**
	 * 获取 面积
	 * @return tasktemp_area
	 */
	public Integer getTasktemp_area() {
		return tasktemp_area;
	}
	
	/**
	 * 设置 面积
	 * @param tasktemp_area
	 */
	public void setTasktemp_area(Integer tasktemp_area) {
		this.tasktemp_area = tasktemp_area;
	}
	
	/**
	 * 获取 金额
	 * @return tasktemp_price
	 */
	public float getTasktemp_price() {
		return tasktemp_price;
	}
	
	/**
	 * 设置 金额
	 * @param tasktemp_price
	 */
	public void setTasktemp_price(float tasktemp_price) {
		this.tasktemp_price = tasktemp_price;
	}
	
	/**
	 * 获取 参与人数
	 * @return tasktemp_pnum
	 */
	public Integer getTasktemp_pnum() {
		return tasktemp_pnum;
	}
	
	/**
	 * 设置 参与人数
	 * @param tasktemp_pnum
	 */
	public void setTasktemp_pnum(Integer tasktemp_pnum) {
		this.tasktemp_pnum = tasktemp_pnum;
	}
	
	/**
	 * 获取 工作进度
	 * @return tasktemp_schedule
	 */
	public Integer getTasktemp_schedule() {
		return tasktemp_schedule;
	}
	
	/**
	 * 设置 工作进度
	 * @param tasktemp_schedule
	 */
	public void setTasktemp_schedule(Integer tasktemp_schedule) {
		this.tasktemp_schedule = tasktemp_schedule;
	}
	
}
