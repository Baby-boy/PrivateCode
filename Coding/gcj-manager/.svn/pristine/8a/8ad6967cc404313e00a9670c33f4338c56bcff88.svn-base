package com.yd.gcj.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.yd.gcj.entity.YdMangerAttention;

public interface YdMangerServiceAttention {

	/***
	 * 根据用户id查询所关注的用户信息
	 */
	List<YdMangerAttention> $queryAll(Integer userId);
	
	/***
	 * 查询指定用户关注数量
	 */
	Object $queryACountNum(HashMap<String, Object> map);
	
	/***
	 * 查询指定用户被关注数量
	 */
	Object $queryBCountNum(HashMap<String, Object> map);
	
	/***
	 * 添加关注
	 */
	@Transactional
	Object $addAtten(YdMangerAttention attention);
	
	/***
	 * 删除关注信息（取消关注）
	 */
	@Transactional
	Object $delAtten(Integer userAId,Integer userBId); 
}
