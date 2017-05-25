package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

public interface YdShopServiceCtc {
	
	HashMap<String, Object> Release(HashMap<String, Object> request);
	
	
	List<Object> listByClassId2(Integer ctc_classify_id2);
	
	
	/**
	 * 获取懒鱼
	 * @return
	 */
	List<HashMap<String, Object>> get_ctc();
	/**
	 * 获取懒鱼一级分类
	 * @return
	 */
	List<HashMap<String, Object>> get_ctc_type();
	/**
	 * 获取懒鱼二级分类
	 * @return
	 */
	List<HashMap<String, Object>> get_ctc_type_two();

}