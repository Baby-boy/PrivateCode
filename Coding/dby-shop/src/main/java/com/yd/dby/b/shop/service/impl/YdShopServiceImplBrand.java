package com.yd.dby.b.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperBrand;
import com.yd.dby.b.shop.service.YdShopServiceBrand;

@Service("_brand")
public class YdShopServiceImplBrand implements YdShopServiceBrand {
	@Autowired
	private YdShopMapperBrand ydShopMapperBrand;
	
	@Override
	public Object brandIndex() {
		return ydShopMapperBrand.brandIndex();
	}
}