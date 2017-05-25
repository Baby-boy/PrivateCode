package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface YdShopMapperAddress {

	HashMap<String, Object> only(@Param("user_id") Integer user_id, @Param("address_id") Integer address_id);

	List<HashMap<String, Object>> select(@Param("user_id") Integer user_id);

	Integer insert(@Param("model") HashMap<String, Object> model);

	Integer update(@Param("model") HashMap<String, Object> model);
	
	Integer default0(@Param("user_id") Integer user_id, @Param("address_id") Integer address_id);

	Integer delete(@Param("user_id") Integer user_id, @Param("address_id") Integer address_id);

}