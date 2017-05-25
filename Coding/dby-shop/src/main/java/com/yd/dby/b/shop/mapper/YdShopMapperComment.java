package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperComment {

	/**
	 * 获取商品评价列表
	 * @param request
	 * @return
	 */
	List<HashMap<String, Object>> lists(HashMap<String, Object> request);
	
	
	/**
	 * 获取商品评价总数
	 * @param request
	 * @return
	 */
	Integer total(HashMap<String, Object> request);
	
	
	
	
	Integer get_count(
			@Param("goods_id") Integer goods_id, 
			@Param("type") Integer type);

	List<String> get_list_only_img(
			@Param("goods_id") Integer goods_id);
	
	List<HashMap<String, Object>> get_list(
			@Param("goods_id") Integer goods_id, 
			@Param("type") Integer type,
			@Param("page_start") Integer page_start);

}