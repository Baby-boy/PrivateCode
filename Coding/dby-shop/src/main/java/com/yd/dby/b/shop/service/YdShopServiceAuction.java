package com.yd.dby.b.shop.service;

import java.util.HashMap;

public interface YdShopServiceAuction {

	// 出价历史
	Object select_history_bid(HashMap<String, Object> request);
	
	// 出价次数
	Object count_history_bid(HashMap<String, Object> request);
	
	// 查询最后出价人
	Object select_latest_bid(HashMap<String, Object> request);

	// 是否出了保证金
	Object check_is_bail(HashMap<String, Object> request);

	// 添加保证金
	Object insert_bail(HashMap<String, Object> request);

	// 添加出价
	Object insert_bid(HashMap<String, Object> request);

	// 更新价格
	Object update_bid(HashMap<String, Object> request);

}