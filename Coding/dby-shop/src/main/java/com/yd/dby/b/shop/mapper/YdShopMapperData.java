package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperData {

	List<HashMap<String, Object>> get_many(@Param("type") String type);

	List<HashMap<String, Object>> get_random(@Param("type") String type);
	
	HashMap<String, Object> get_only(@Param("type") String type);
}
