package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperNav {

	List<HashMap<String, Object>> getByType(@Param("nav_type") String nav_type);

}