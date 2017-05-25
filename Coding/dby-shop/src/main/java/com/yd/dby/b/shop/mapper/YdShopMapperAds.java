package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperAds {

	List<HashMap<String, Object>> get_many(@Param("type") String type);

	List<HashMap<String, Object>> get_list(@Param("type") String type);

}
