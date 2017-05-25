package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTaskModel;

public interface YdMangerServiceTaskModel {

	/***
	 * 查询任务所有节点模块
	 * @param taskmd_tid
	 * @return
	 */
	List<YdMangerTaskModel> $queryByTid(Integer taskId);
	
	/***
	 * 查询指定任务模块信息
	 * @param taskmd_id
	 * @return
	 */
	Object $queryById(HashMap<String, Object> map);
	
	/***
	 * 修改模块
	 * @param taskmd
	 * @return
	 */
	@Transactional
	Object $update(HashMap<String, Object> map);
	
	/***
	 * 修改模块状态，主要为检查确认是使用
	 * @param taskmd_tid 任务id
	 * @param taskmd_state 需要修改的状态
	 * @param taskmd_update_time 更新时间
	 * @return
	 */
	@Transactional
	Object $updateState(HashMap<String, Object> map);
	
	/***
	 * 添加任务新模块节点计划
	 * @param taskmd
	 * @return
	 */
	@Transactional
	Object $insert(YdMangerTaskModel taskModel);
	
	/***
	 * 删除指定模块
	 * @param taskmd_id
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
}
