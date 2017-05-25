package com.yd.gcj.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yd.gcj.entity.YdMangerTask;
import com.yd.gcj.entity.vo.TaskVoNums;
import com.yd.gcj.entity.vo.YdMangerTaskVo;

public interface YdMangerMapperTask {
	
	/***
	 * 查询雇主任务类型数量
	 * @return
	 */
	TaskVoNums $queryByTaskE(@Param("userId") Integer userId);
	
	/***
	 * 查询服务商任务类型数量
	 * @return
	 */
	TaskVoNums $queryByTaskS(@Param("userId") Integer userId);
	
	/***
	 * 根据分页数据查询任务信息
	 * @param startPageNum 开始条数
	 * @param queryPageNum 查询数量
	 * @return
	 */
	List<YdMangerTaskVo> $queryByPageNum(Map<String,Object> map);
	
	/***
	 * 根据分页数据查询任务信息(标识是否收藏)
	 * @param userId 用户id
	 * @param startPageNum 开始条数
	 * @param queryPageNum 查询数量
	 * @return
	 */
	List<YdMangerTaskVo> $queryByPageNumAndUserId(Map<String,Object> map);
	/***
	 * 根据雇主id查询该雇主所发布的任务信息
	 * @param task_uid
	 * @return
	 */
	List<YdMangerTaskVo> $queryByEId(@Param("userId") Integer userId,@Param("taskState") Integer taskState);
	
	/***
	 * 查询指定雇主的任务名称及id
	 * @param userId
	 * @return
	 */
	List<YdMangerTask> $queryNameAndIdByEId(@Param("userId") Integer userId);
	/***
	 * 根据服务商id查询服务商所做的任务
	 * @param task_uid
	 * @return
	 */
	List<YdMangerTaskVo> $queryBySId(@Param("userId") Integer userId,@Param("taskState") Integer taskState);
	
	/***
	 * 多条件复杂混合查询时时用sql插入查询
	 * @param sql
	 * @return
	 */
	List<YdMangerTask> $queryBySql(@Param("sql") String sql);
	
	/***
	 * 无条件查询所有任务
	 * @return
	 */
	List<YdMangerTask> $queryAll();
	
	/***
	 * 根据任务id查询任务详细信息
	 * @param task_id
	 * @return
	 */
	YdMangerTaskVo $queryById(@Param("taskId") Integer taskId);
	
	/**
	 * 根据任务id和用户id查询任务详细信息
	 * @param taskId
	 * @param userId
	 * @return
	 */
	YdMangerTaskVo $queryByTaskIdAndUserId(@Param("taskId") Integer taskId,@Param("userId") Integer userId);
	
	/***
	 * 根据雇主id和任务id查询任务的状态
	 * @param userId
	 * @param taskId
	 * @return
	 */
	Integer $queryStateByEidAndTaskId(@Param("userId") Integer userId,@Param("taskId") Integer taskId);
	
	/***
	 * 根据雇主id查询雇主所发布的任务数量
	 * @param task_uid
	 * @return
	 */
	Integer $queryCountNumByEId(@Param("task_uid") Integer task_uid);
	
	/***
	 * 查询所有任务数量
	 * @return
	 */
	Integer $queryCountNum(Map<String, Object> map);
	
	/***
	 * 添加新的任务信息
	 * @param task
	 * @return
	 */
	Integer $insert(YdMangerTask task);
	
	/***
	 * 跟新任务信息
	 * @param task
	 * @return
	 */
	Integer $update(YdMangerTask task);
	
	/***
	 * 添加合同的同时修改任务的合同状态
	 * @param taskId
	 * @param contractState
	 * @return
	 */
	Integer $updateContractState(@Param("taskId") Integer taskId,@Param("contractState") Integer contractState);
	
	/***
	 * 修改任务状态
	 * @param taskId
	 * @param state
	 * @return
	 */
	Integer $updateTaskState(@Param("taskId") Integer taskId,@Param("state") Integer state);
	
	/****
	 * 修改任务组长选定状态
	 * @param taskId
	 * @param learnState
	 * @return
	 */
	Integer $updateTaskLearState(@Param("taskId") Integer taskId,@Param("learState")Integer learState);
	
	/***
	 * 删除任务信息
	 * @param task_id
	 * @return
	 */
	Integer $delete(@Param("task_id") Integer task_id);
	
	/***
	 * 获取任务合同状态
	 * @param taskId
	 * @param userId
	 * @return
	 */
	Integer $queryContractStateBytaskId(@Param("taskId") Integer taskId);
	
	/***
	 * 查询指定用户与指定任务是否有关系
	 * @param taskId
	 * @param userId
	 * @param userType
	 * @return
	 */
	Integer $isTask(@Param("taskId") Integer taskId,@Param("userId") Integer userId,@Param("userType") Integer userType);
	
	/***
	 * 更新任务托管的资金
	 * @param taskId
	 * @return
	 */
	Integer $updateHostPrice(@Param("taskId") Integer taskId);
	
}
