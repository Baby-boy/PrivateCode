package com.yd.dby.b.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperRecommend {

	List<Object> listsByCode(@Param("rec_code") String rec_code);
}
