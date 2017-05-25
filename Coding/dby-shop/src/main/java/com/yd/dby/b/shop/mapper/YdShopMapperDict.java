package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperDict {
	List<HashMap<String, Object>> get_many(@Param("dict_type") String dict_type);

	List<HashMap<String, Object>> get_many_simple(@Param("dict_type") String dict_type, @Param("dict_pid") String dict_pid);

	List<HashMap<String, Object>> many(@Param("dict_type") String dict_type);
	
}