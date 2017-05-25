package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperBrand {

	List<HashMap<String, Object>> getTypeBrand(@Param("type_id") Integer type_id);
	
	List<Object> brandIndex();

}