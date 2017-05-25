package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface YdShopServiceStore {

	List<HashMap<String, Object>> select(HashMap<String, Object> value);

	List<HashMap<String, Object>> link(Integer store_id);

	List<HashMap<String, Object>> classfiy(Integer store_id);

	List<HashMap<String, Object>> top_sell_goods(Integer store_id);

	List<HashMap<String, Object>> recommend_goods(Integer store_id);

	Object goodsAll(HashMap<String, Object> request);

	Object info(Integer store_id);

	Map<String, Object> banner(Integer store_id);
	
	// 店铺列表
	Object list(HashMap<String, Object> map);
	
	/**
	 * 流通-转换 - 附近店铺
	 */
	Object nearbyStore(HashMap<String, Object> map);
}