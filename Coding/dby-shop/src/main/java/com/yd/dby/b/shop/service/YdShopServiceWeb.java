package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopServiceWeb {
	List<HashMap<String, Object>> get_many_map(@Param("ads_type") String ads_type);

	List<HashMap<String, Object>> get_many_list(@Param("ads_type") String ads_type);

}