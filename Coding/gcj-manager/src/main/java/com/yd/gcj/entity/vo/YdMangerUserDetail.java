package com.yd.gcj.entity.vo;

import java.util.List;

import com.yd.gcj.entity.YdMangerCases;
import com.yd.gcj.entity.YdMangerSpcs;
import com.yd.gcj.entity.YdMangerTask;

public class YdMangerUserDetail extends YdMangerUserVo{
	
	/**案例*/
	List<YdMangerCases> cases;
	/**好评*/
	List<YdMangerSpcs> spcs;
	/**任务*/
	List<YdMangerTask> tasks;
	
	/**
	 * 获取 案例
	 * @return cases
	 */
	public List<YdMangerCases> getCases() {
		return cases;
	}
	
	/**
	 * 设置 案例
	 * @param cases
	 */
	public void setCases(List<YdMangerCases> cases) {
		this.cases = cases;
	}
	
	/**
	 * 获取 好评
	 * @return spcs
	 */
	public List<YdMangerSpcs> getSpcs() {
		return spcs;
	}
	
	/**
	 * 设置 好评
	 * @param spcs
	 */
	public void setSpcs(List<YdMangerSpcs> spcs) {
		this.spcs = spcs;
	}

	/**
	 * 获取 任务
	 * @return tasks
	 */
	public List<YdMangerTask> getTasks() {
		return tasks;
	}
	
	/**
	 * 设置 任务
	 * @param tasks
	 */
	public void setTasks(List<YdMangerTask> tasks) {
		this.tasks = tasks;
	}
	
}
