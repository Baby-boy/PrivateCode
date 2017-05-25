package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdStore;

public interface YdShopMapperStore {
	List<YdStore> get_many_brand(HashMap<String, Object> map);
	
	Integer total(HashMap<String, Object> map);
	
	List<HashMap<String, Object>> select();

	List<HashMap<String, Object>> link();

	List<HashMap<String, Object>> classfiy();

	List<HashMap<String, Object>> top_sell_goods();

	List<HashMap<String, Object>> recommend_goods();

	List<HashMap<String, Object>> goodsAll(HashMap<String, Object> request);

	Integer goodsAllTotal(HashMap<String, Object> request);

	HashMap<String, Object> info(@Param("store_id") Integer store_id);

	Object banner(Integer store_id);

	List<HashMap<String, Object>> nearbyStore(HashMap<String, Object> map);
	
	Integer nearbyTotal(HashMap<String, Object> map);
}
