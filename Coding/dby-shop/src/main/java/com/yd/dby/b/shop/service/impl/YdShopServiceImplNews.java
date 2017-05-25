package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperNews;
import com.yd.dby.b.shop.service.YdShopServiceNews;

@Service("_News")
public class YdShopServiceImplNews implements YdShopServiceNews {
	@Autowired
	private YdShopMapperNews ydShopMapperNews;

	@Override
	public Object hot(HashMap<String, Object> request) {
		return ydShopMapperNews.hot();
	}

	@Override
	public Object count(HashMap<String, Object> request) {
		return ydShopMapperNews.count();
	}

	@Override
	public Object selectAll(HashMap<String, Object> request) {
		return ydShopMapperNews.selectAll(2);
	}
	
	
	
	@Override
	public Object hot2(HashMap<String, Object> request) {
		return ydShopMapperNews.hot2(null);
	}

	@Override
	public Object count2(HashMap<String, Object> request) {
		return ydShopMapperNews.count2(null);
	}

	@Override
	public Object selectAll2(HashMap<String, Object> request) {
		return ydShopMapperNews.selectAll2(null, 2);
	}


}