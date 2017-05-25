package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

public interface YdShopServiceNav {
	List<HashMap<String, Object>> getByType(String nav_type);
}