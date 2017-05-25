package com.yd.dby.b.shop.mapper;

import java.util.HashMap;
import java.util.List;

import com.yd.dby.b.shop.entity.YdAppOnlineBooking;

public interface YdShopMapperOnlinebooking {

	Integer insert(HashMap<String, Object> model);
	//流通置换
	List<HashMap<String, Object>> get_Store();
	
	Integer add(YdAppOnlineBooking request);

}