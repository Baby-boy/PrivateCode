package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperNews {

	List<HashMap<String, Object>> hot();

	List<HashMap<String, Object>> count();

	List<HashMap<String, Object>> selectAll(@Param("page_start") Integer page_start);

	List<HashMap<String, Object>> hot2(@Param("type") String type);

	List<HashMap<String, Object>> count2(@Param("type") String type);

	List<HashMap<String, Object>> selectAll2(@Param("type") String type, @Param("page_start") Integer page_start);

}
