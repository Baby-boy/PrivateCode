package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerBankTR;

public interface YdMangerServiceBankTR {
	
	/***
	 * 查询指定用户交易记录
	 * @param map
	 * @return
	 */
	List<YdMangerBankTR> $queryAll(Integer userId,Integer startPageNum,Integer pageNum);
	
	/***
	 * 根据时间查询交易记录，主要用于后台操作
	 * @param map
	 * @return
	 */
	Object $queryByDate(HashMap<String, Object> map);
	
	/***
	 * 多条件复杂情况使用sql动态查询
	 * @param map
	 * @return
	 */
	Object $queryBySql(HashMap<String, Object> map);
	
	/***
	 * 根据id查询指定交易记录信息
	 * @param map
	 * @return
	 */
	Object $queryById(HashMap<String, Object> map);
	
	/***
	 * 根据用户id查询该用户的交易记录数量，主要用户用户查询分页
	 * @param map
	 * @return
	 */
	Object $queryCountNum(HashMap<String, Object> map);
	
	/***
	 * 查询数据库所有交易记录信息数量，主要用于后台查询分页
	 * @return
	 */
	Integer $queryCountNum(Integer userId);
	
	/***
	 * 生成新的交易记录信息
	 * @param map
	 * @return
	 */
	@Transactional
	Object $insert(HashMap<String, Object> map);
	
}
