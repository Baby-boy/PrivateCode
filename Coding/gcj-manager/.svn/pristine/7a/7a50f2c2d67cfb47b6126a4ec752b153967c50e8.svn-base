package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTaskType;

public interface YdMangerServiceTaskType {
	
	/**
	 * 查询任务类型全部信息
	 * @param map
	 * @return
	 */
	List<YdMangerTaskType> $queryAll();
	
	/**
	 * 根据类型id查询单个类型
	 * @param map
	 * @return
	 */
	YdMangerTaskType $queryById(HashMap<String, Object> map);
	
	/**
	 * 添加类型
	 * @param map
	 * @return
	 */
	@Transactional
	Integer $insert(HashMap<String, Object> map);
	
	/**
	 * 更新任务类型信息
	 * @param map
	 * @return
	 */
	@Transactional
	Integer $update(HashMap<String, Object> map);
	
	/**
	 * 删除指定的任务类型
	 * @param map
	 * @return
	 */
	@Transactional
	Integer $delete(HashMap<String, Object> map);
	
}
