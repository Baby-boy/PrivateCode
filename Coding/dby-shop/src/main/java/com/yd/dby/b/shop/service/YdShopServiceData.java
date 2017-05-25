package com.yd.dby.b.shop.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopServiceData {
	List<HashMap<String, Object>> get(@Param("type") String type);

	HashMap<String, Object> only(@Param("type") String type);
}