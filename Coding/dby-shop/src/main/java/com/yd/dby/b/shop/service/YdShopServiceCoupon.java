package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

public interface YdShopServiceCoupon {
	List<HashMap<String, Object>> get_many(Integer user_id, Integer isexpired, List<Integer> store_ids);
}