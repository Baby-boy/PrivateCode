package com.yd.gcj.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTask;
import com.yd.gcj.entity.vo.YdMangerTaskVo;

public interface YdMangerMapperSystemTask {

	/**
	 * description(查询所有的任务信息)
	 * @param
	 * @return
	 */
	List<YdMangerTaskVo> queryAllTask(@Param("task_num")String task_num,@Param("task_state")Integer task_state);

	/**
	 * description(根据指定的task_id删除任务)
	 * @param
	 * @param task_id
	 * @return
	 */
	Integer deleteTaskByTaskId(Integer task_id);

	/**
	 * description(添加系统消息发送到个人时查询任务id)
	 * @param
	 * @param task_num
	 * @return
	 */
	YdMangerTask queryTaskByTaskNum(String task_num);

	/**
	 * description(根据task_id查询任务详细信息)
	 * @param
	 * @param task_id
	 * @return
	 */
	YdMangerTaskVo queryTaskByTaskId(Integer task_id);

	/**
	 * description(查询任务状态的所有类型)
	 * @param
	 * @return
	 */
	List<YdMangerTaskVo> queryTaskAllState();

	/**
	 * description(修改任务状态)
	 * @param
	 * @param ydMangerTaskVo
	 * @return
	 */
	Integer updateTaskStateByTaskId(YdMangerTaskVo ydMangerTaskVo);

}
