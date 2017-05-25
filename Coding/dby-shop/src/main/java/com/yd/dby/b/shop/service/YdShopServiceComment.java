package com.yd.dby.b.shop.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface YdShopServiceComment {
	
	/**
	 * 获取商品评价列表
	 * @param request
	 * @return
	 * @throws ParseException 
	 */
	Map<String, Object> lists(HashMap<String, Object> request) throws ParseException;
	
	/**
	 * 评价总数
	 * @param goods_id
	 * @param type
	 * @return
	 */
	Integer total(String type, Integer goods_id);


	Integer get_count(
			@Param("goods_id") Integer goods_id, 
			@Param("type") Integer type);

	List<String> get_list_only_img(
			@Param("goods_id") Integer goods_id);
	
	List<HashMap<String, Object>> get_list(
			@Param("goods_id") Integer goods_id, 
			@Param("type") Integer type,
			@Param("page_start") Integer page_index);
}

