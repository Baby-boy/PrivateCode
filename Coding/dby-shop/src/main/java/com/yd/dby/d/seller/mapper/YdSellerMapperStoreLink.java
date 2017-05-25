package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author 作者 E-mail:
 * @version 创建时间：2017年2月14日 上午11:14:45
 * 
 */
public interface YdSellerMapperStoreLink {

	// 根据store_id查询对应的友情店铺
	List<HashMap<String, Object>> stores(HashMap<String, Object> request);

	Integer store(HashMap<String, Object> request);

	// 友情链接修改
	Object edit(HashMap<String, Object> request);

	// 友情链接修改保存
	Integer update(HashMap<String, Object> request);

	// 删除友情链接
	Integer destroy(HashMap<String, Object> request);

}
