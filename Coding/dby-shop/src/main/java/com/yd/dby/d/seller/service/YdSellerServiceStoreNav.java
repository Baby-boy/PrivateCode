package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

public interface YdSellerServiceStoreNav {
	
	/*商家导航-列表*/
	Map<String, Object> index(HashMap<String, Object> request);
	
	/*商家导航-添加*/
	Map<String, Object> store(HashMap<String, Object> request);
	
	/*商家导航-修改*/
	Map<String, Object> edit(HashMap<String, Object> request);
	
	/*商家导航-修改提交*/
	Map<String, Object> update(HashMap<String, Object> request);
	
	/*商家导航-设置显示-隐藏*/
	Map<String, Object> setShow(HashMap<String, Object> request);
	
	/*商家导航删除*/
	Map<String, Object> destroy(HashMap<String, Object> request);
}
