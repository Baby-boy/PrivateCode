package com.yd.gcj.service;

import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

public interface YdMangerServiceCases {

	/***
	 * 查询指定用户案例
	 * @param case_uid
	 * @return
	 */
	Object $queryAll(HashMap<String, Object> map);
	
	/***
	 * 根据多条件查询案例
	 * @param case_uid
	 * @param startPageNum
	 * @param queryPageNum
	 * @return
	 */
	Object $queryByPageNum(HashMap<String, Object> map);
	
	/***
	 * 查询指定案例信息
	 * @param case_id
	 * @return
	 */
	Object $queryById(HashMap<String, Object> map);
	
	/***
	 * 查询指定用户案例信息数量
	 * @param case_uid
	 * @return
	 */
	Object $queryCountNum(HashMap<String, Object> map);
	
	/***
	 * 
	 * 添加用户案例信息
	 * @param cases
	 * @return
	 */
	@Transactional
	Object $insert(HashMap<String, Object> map);
	
	/***
	 * 修改用户案例信息
	 * @param cases
	 * @return
	 */
	@Transactional
	Object $update(HashMap<String, Object> map);
	
	/***
	 * 删除指定案例信息
	 * @param case_id
	 * @return
	 */
	@Transactional
	Object $delete(HashMap<String, Object> map);
}
