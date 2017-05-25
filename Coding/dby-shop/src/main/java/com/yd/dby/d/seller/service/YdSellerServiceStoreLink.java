package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月14日 上午11:36:23
 * 
 */
public interface YdSellerServiceStoreLink {

	// 根据store_id查询对应的友情店铺
	List<HashMap<String, Object>> stores(HashMap<String, Object> request);

	Map<String, Object> index(HashMap<String, Object> request);

	Map<String, Object> store(HashMap<String, Object> request);

	Map<String, Object> edit(HashMap<String, Object> request);

	Map<String, Object> update(HashMap<String, Object> request);

	Map<String, Object> destroy(HashMap<String, Object> request);

}
