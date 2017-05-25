package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;


public interface YdSellerMapperStoreNav {
	
	// 商家导航列表
	List<Object> index(HashMap<String, Object> request);
	
	// 添加商家导航
	Integer store(HashMap<String, Object> request);
	
	// 商家导航-修改
	Object edit(HashMap<String, Object> request);
	
	// 设为显示或隐藏
	Integer setShow(HashMap<String, Object> request);
	
	// 修改商家导航
	Integer update(HashMap<String, Object> request);

	// 删除商家导航
	Integer destroy(HashMap<String, Object> request);
}