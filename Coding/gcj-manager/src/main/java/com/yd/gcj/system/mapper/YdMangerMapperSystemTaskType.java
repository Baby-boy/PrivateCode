package com.yd.gcj.system.mapper;

import java.util.List;

import com.yd.gcj.entity.YdMangerTaskType;

public interface YdMangerMapperSystemTaskType {

	/**
	 * description(查询所有的任务类型)
	 * @param
	 * @return
	 */
	List<YdMangerTaskType> queryAllTaskType();
	/*List<YdMangerTaskType> queryAllTaskType(@Param("taskt_name")String taskt_name);*/

	/**
	 * description(根据指定的taskt_id删除任务类型信息)
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
	 * description(修改任务类型之前吸纳根据taskType_id查询当球员任务类型的信息)
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
