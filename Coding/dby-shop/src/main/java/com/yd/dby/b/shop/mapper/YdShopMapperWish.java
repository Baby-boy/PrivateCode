package com.yd.dby.b.shop.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Insert;

public interface YdShopMapperWish {

	@Insert("insert yd_wish (user_id,wish_content,wish_created_time) value (#{user_id}, #{wish_content}, NOW())")
	Integer insert(HashMap<String, Object> model);

}
