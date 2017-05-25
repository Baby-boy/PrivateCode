package com.yd.gcj.system.service;

import java.util.List;

import com.yd.gcj.entity.YdMangerTaskType;

public interface YdMangerServiceSystemTaskType {

	/**
	 * description(查询所有的任务类型)
	 * @param
	 * @return
	 */
	List<YdMangerTaskType> queryAllTaskType();

	/**
	 * description(根据指定的taskt_id删除任务类型)
	 * @param
	 * @param taskt_id
	 * @return
	 */
	Integer deleteTaskTypeByTasktId(Integer taskt_id);

	/**
	 * description(添加任务类型)
	 * @param
	 * @param ydMangerTaskType
	 * @return
	 */
	Integer addTaskType(YdMangerTaskType ydMangerTaskType);

	/**
	 * description(修改任务类型之前先根据taskType_id查询当前任务类型的信息)
	 * @param
	 * @param task_id
	 * @return
	 */
	YdMangerTaskType updateTaskTypeByTaskTypeId(Integer taskt_id);

	/**
	 * description(修改任务类型信息)
	 * @param
	 * @param ydMangerTaskType
	 * @return
	 */
	Integer updateTaskType(YdMangerTaskType ydMangerTaskType);

}
