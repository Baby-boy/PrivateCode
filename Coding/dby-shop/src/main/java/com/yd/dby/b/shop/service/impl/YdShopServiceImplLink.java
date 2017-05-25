package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperLink;
import com.yd.dby.b.shop.service.YdShopServiceLink;

@Service("_link")
public class YdShopServiceImplLink implements YdShopServiceLink {
	@Autowired
	private YdShopMapperLink ydShopMapperLink;

	@Override
	public List<HashMap<String, Object>> many(String type) {
		return ydShopMapperLink.get_many(type);
	}

}