package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;


public interface YdSellerMapperStoreAddress {
	
	List<Object> index(HashMap<String, Object> request);
	
	// 添加商家地址
	Integer store(HashMap<String, Object> request);
	
	// 修改商家地址
	Integer update(HashMap<String, Object> request);
	
	// 设为默认
	Integer setDefault(HashMap<String, Object> request);
	
	// 删除商家地址
	Integer destroy(HashMap<String, Object> request);
}