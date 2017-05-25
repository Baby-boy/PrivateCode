package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopServiceAds {
	List<HashMap<String, Object>> get_many_map(@Param("type") String type);

	List<HashMap<String, Object>> get_many_list(@Param("type") String type);
}