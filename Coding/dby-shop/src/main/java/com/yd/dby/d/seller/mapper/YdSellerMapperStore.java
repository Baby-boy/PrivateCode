package com.yd.dby.d.seller.mapper;

import java.util.HashMap;

import com.yd.dby.d.seller.entity.YdSellerStore;


public interface YdSellerMapperStore {
	
	// 创建店铺
	Integer create(YdSellerStore ydSellerStore);
	
	// 获取店铺信息
	Object info(Integer store_id);
	
	// 获取店铺信息
	Object infoUserId(Integer user_id);
	
	// 修改店铺信息
	Integer update(HashMap<String, Object> request);

	// 更新店铺banner
	Integer slideUpdate(HashMap<String, Object> request);
	
}