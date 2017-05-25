package com.yd.dby.b.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperRecommend;
import com.yd.dby.b.shop.service.YdShopServiceRecommend;

@Service("_recommend")
public class YdShopServiceImplRecommend implements YdShopServiceRecommend {
	@Autowired
	private YdShopMapperRecommend ydShopMapperRecommend;

	public Object listsByCode(String rec_code) {
		return ydShopMapperRecommend.listsByCode(rec_code);
	}
	
}