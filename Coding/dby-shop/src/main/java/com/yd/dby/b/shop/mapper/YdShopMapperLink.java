package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

public interface YdShopMapperLink {

	@Select("select title,url,ext from yd_link where type=#{type} order by sort asc")
	@ResultType(HashMap.class)
	List<HashMap<String, Object>> get_many(@Param("type") String type);

}