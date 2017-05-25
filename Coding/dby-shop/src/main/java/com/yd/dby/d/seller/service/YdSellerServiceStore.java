package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

public interface YdSellerServiceStore {

	/*商家信息-获取*/
	Object setting(HashMap<String, Object> request);
	
	/*商家信息-修改*/
	Map<String, Object> update(HashMap<String, Object> request);

	/*商家banner-更新*/
	Map<String, Object> slideUpdate(HashMap<String, Object> request);
}
