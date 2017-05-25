package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperWish;
import com.yd.dby.b.shop.service.YdShopServiceWish;


@Service("_Wish")
public class YdShopServiceImplWish implements YdShopServiceWish {
	@Autowired
	private YdShopMapperWish ydShopMapperWish;

	@Override
	public Object insert(HashMap<String, Object> obj) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", 1);
		map.put("wish_content", obj.get("content"));
		map.put("wish_created_time", System.currentTimeMillis() / 1000);
		
		//
		return map;
	}

}