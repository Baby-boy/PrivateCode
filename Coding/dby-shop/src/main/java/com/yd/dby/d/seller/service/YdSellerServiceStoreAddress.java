package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

public interface YdSellerServiceStoreAddress {
	
	/*商家地址-列表*/
	Map<String, Object> index(HashMap<String, Object> request);
	
	/*商家地址-添加*/
	Map<String, Object> store(HashMap<String, Object> request);
	
	/*商家地址-修改*/
	Map<String, Object> update(HashMap<String, Object> request);
	
	/*商家地址-设为默认*/
	Map<String, Object> setDefault(HashMap<String, Object> request);
	
	/*商家地址删除*/
	Map<String, Object> destroy(HashMap<String, Object> request);
}
