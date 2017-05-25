package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

public interface YdSellerServiceGoods {
	
	/*商家商品列表*/
	Map<String, Object> index(HashMap<String, Object> request);

	/*商家商品删除*/
	Map<String, Object> destroy(HashMap<String, Object> request);
	
	/*商家商品创建*/
	Map<String, Object> create(HashMap<String, Object> request);
	
	/*商家商品创建-提交*/
	Object store(HashMap<String, Object> request);

	/*商品商品编辑*/
	Map<String, Object> edit(HashMap<String, Object> request);

	/*商品商品更新*/
	Object update(HashMap<String, Object> request);

	
	/**
	 * 商品上下架
	 * @param id
	 * @param up
	 * @return
	 */
	Object showhide(Integer id, Integer up);

	/**
	 * 搜索
	 * @param map
	 * @return
	 */
	Object search(HashMap<String, Object> map);
	
}
