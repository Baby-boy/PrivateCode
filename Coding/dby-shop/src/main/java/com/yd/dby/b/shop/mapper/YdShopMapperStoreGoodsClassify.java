package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;


public interface YdShopMapperStoreGoodsClassify {
	
	// 商家分类列表
	List<HashMap<String, Object>> lists(@Param("store_id")Integer store_id);

}