package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.vo.TaskVoNums;
import com.yd.gcj.entity.vo.YdMangerTaskVo;
import com.yd.gcj.tool.MapInitFactory;

@Service("serviceTask")
public interface YdMangerServiceTask {
	
	/***
	 * 查询用户任务类型数量
	 * @param userType
	 * @return
	 */
	TaskVoNums $queryByTask(Integer userType,Integer userId);
	
	/***
	 * 根据分页数据查询任务信息
	 * @param map
	 * @return
	 */
	Object $queryByPageNum();
	
	/***
	 * 根据雇主id查询该雇主所发布的任务信息
	 * @param map
	 * @return
	 */
	List<YdMangerTaskVo> $queryByEId(Integer userId,Integer taskState);
	
	/***
	 * 根据服务商id查询服务商所做的任务
	 * @param map
	 * @return
	 */
	List<YdMangerTaskVo> $queryBySId(Integer userId,Integer taskState);
	
	/***
	 * 多条件复杂混合查询时时用sql插入查询
	 * @param map
	 * @return
	 */
	Object $queryBySql(HashMap<String, Object> map);
	
	/***
	 * 无条件查询所有任务
	 * @return
	 */
	Object $queryAll();
	
	/***
	 * 根据任务id查询任务详细信息
	 * @param map
	 * @return
	 */
	YdMangerTaskVo $queryById(Integer taskId,Integer userId);
	
	/***
	 * 查询指定id任务信息
	 * @param taskId
	 * @param userId
	 * @return
	 */
	YdMangerTaskVo $queryByTaskId(Integer taskId);
	/***
	 * 根据雇主id和任务id查询任务状态
	 * @param userId
	 * @param taskId
	 * @return
	 */
	Integer $queryStateByEidAndTaskId(Integer userId,Integer taskId);
	
	/***
	 * 根据任务id查询任务详细信息（提供给修改任务页面使用）
	 * @param map
	 * @return
	 */
	YdMangerTaskVo $queryByIdToUpdate(Integer taskId);
	
	/***
	 * 根据雇主id查询雇主所发布的任务数量
	 * @param map
	 * @return
	 */
	Object $queryCountNumByEId(HashMap<String, Object> map);
	
	/***
	 * 查询所有任务数量
	 * @return
	 */
	Object $queryCountNum();
	
	/***
	 * 获取任务合同状态
	 * @param taskId
	 * @param userId
	 * @return
	 */
	Integer $queryContractStateBytaskId(Integer taskId);
	
	/***
	 * 检查指定用户是否与指定任务有关系
	 * @param taskId
	 * @param userId
	 * @param userType
	 * @return
	 */
	Integer $isTask(Integer taskId,Integer userId,Integer userType);
	
	/***
	 * 添加新的任务信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $insert(HashMap<String, Object> map);
	
	/***
	 * 跟新任务信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $update(HashMap<String, Object> map);
	
	/***
	 * 修改任务合同状态
	 * @param state
	 * @return
	 */
	@Transactional
	MapInitFactory $updateTaskContractState(Integer taskId,Integer state);
	
	/***
	 * 更新任务状态
	 * @param taskId
	 * @param state
	 * @return
	 */
	@Transactional
	Object $updateTaskState(Integer taskId,Integer state);
	
	/***
	 * 删除任务信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
	/**
	 * 任务修改中删除任务文件功能
	 * @param fileId
	 * @param taskId
	 * @return
	 */
	@Transactional
	Object $delTaskFile(Integer fileId,Integer taskId);
	
}
