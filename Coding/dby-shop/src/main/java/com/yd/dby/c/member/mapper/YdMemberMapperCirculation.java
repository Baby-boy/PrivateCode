package com.yd.dby.c.member.mapper;

import java.util.HashMap;
import java.util.List;

import com.yd.dby.c.member.entity.YdOnLineBookIng;

public interface YdMemberMapperCirculation {

	/**
	 * @param request
	 * @return
	 * 方法作用(查询当前用户的流通置换)
	 */
	List<HashMap<String, Object>> queryCirculationByUserId(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(查询当前用户流通置换总数)
	 */
	Integer totalCirculation(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(流通置换 删除)
	 */
	Integer delete(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(查询流通 置换  商品详情)
	 */
	YdOnLineBookIng circulationDetailes(HashMap<String, Object> request);

}
