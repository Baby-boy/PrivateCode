package com.yd.dby.b.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperBanner;
import com.yd.dby.b.shop.service.YdShopServiceBanner;

@Service("_banner")
public class YdShopServiceImplBanner implements YdShopServiceBanner {
	@Autowired
	private YdShopMapperBanner ydShopMapperBanner;
	
	@Override
	public Object lists(String banner_type) {
		return ydShopMapperBanner.lists(banner_type);
	}
	
	@Override
	public Object findByType(String banner_type) {
		System.out.println(1 );
		return ydShopMapperBanner.findByType(banner_type);
	}
}