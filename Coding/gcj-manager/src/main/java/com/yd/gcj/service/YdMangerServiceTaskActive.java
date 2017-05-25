package com.yd.gcj.service;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerTaskActive;

public interface YdMangerServiceTaskActive {

	/***
	 * 根据任务id查询该任务的评论及回复信息
	 * @param map
	 * @return
	 */
	Object $queryByTid(HashMap<String, Object> map);
	
	/***
	 * 添加任务新评论信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $insert(YdMangerTaskActive taskActive);
	
	/***
	 * 删除评论
	 * @param map
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
}
