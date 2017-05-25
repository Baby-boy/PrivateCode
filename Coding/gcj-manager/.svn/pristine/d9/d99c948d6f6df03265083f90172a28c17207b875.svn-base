package com.yd.gcj.service;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

public interface YdMangerServiceTaskTypeTR {
	

	/**
	 * 查询指定任务id查询该任务的所有类型
	 * @param task_id
	 * @return
	 */
	Object $queryAllByTaskId(HashMap<String, Object> map);
	
	/**
	 * 根据指定类型查询该类型的所有任务id
	 * @param map
	 * @return
	 */
	Object $queryAllTaskIdByTypeId(HashMap<String, Object> map);
	
	/**
	 * 为任务添加类型
	 * @param map
	 * @return
	 */
	@Transactional
	Object $insert(HashMap<String, Object> map);
	
	/**
	 * 更新任务类型信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $update(HashMap<String, Object> map);
	
	/**
	 * 删除指定任务类型
	 * @param map
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
}
