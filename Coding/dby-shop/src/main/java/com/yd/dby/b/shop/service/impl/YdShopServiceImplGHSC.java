package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperAds;
import com.yd.dby.b.shop.service.YdShopServiceGHSC;

@Service("_GHSC")
public class YdShopServiceImplGHSC implements YdShopServiceGHSC {
	@Autowired
	private YdShopMapperAds ydShopMapperAds;

	@Override
	public HashMap<String, Object> post1(HashMap<String, Object> r) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("state", "401");
		map.put("msg", "登录失败");

		return map;
	}

	@Override
	public HashMap<String, Object> post2(HashMap<String, Object> r) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("state", "200");
		map.put("msg", "登录失败");

		return map;
	}

}