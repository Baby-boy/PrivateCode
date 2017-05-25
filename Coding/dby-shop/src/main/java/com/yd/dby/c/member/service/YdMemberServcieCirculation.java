package com.yd.dby.c.member.service;

import java.util.HashMap;
import java.util.Map;

public interface YdMemberServcieCirculation {

	/**
	 * @return 方法作用(查询当前登录用户的流通 置换 商品)
	 */
	Map<String, Object> queryCirculationByUserId(HashMap<String, Object> request);

	/**
	 * @param request
	 * @return 方法作用(流通置换 删除)
	 */
	Object delete(HashMap<String, Object> request);
	
	/**
	 * @param request
	 * @return
	 * 方法作用(查询流通置换的详情)
	 */
	Map<String,Object> circulationDetailes(HashMap<String,Object> request);
}
