package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerEpcs;

public interface YdMangerServiceEpcs {

	/***
	 * 查询指定任务的评价
	 * @param map
	 * @return
	 */
	Object $queryAllByTid(HashMap<String, Object> map);
	
	/***
	 * 查询用户的点评信息
	 * @param userId
	 * @return
	 */
	List<YdMangerEpcs> $queryAllByUserId(Integer userId);
	
	/***
	 * 使用sql进行多条件情况查询
	 * @param map
	 * @return
	 */
	Object $queryBySql(HashMap<String, Object> map);
	
	/***
	 * 查询指定任务评价数量
	 * @param map
	 * @return
	 */
	Object $queryCountNum(HashMap<String, Object> map);
	
	/***
	 * 使用sql查询多条件下评价数量
	 * @param map
	 * @return
	 */
	Object $queryCountNumBySql(HashMap<String, Object> map);
	
	/***
	 * 添加新评价信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $insert(YdMangerEpcs epcs);
	
	/***
	 * 修改评价信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $update(HashMap<String, Object> map);
	
	/***
	 * 删除指定评价信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
	
}
