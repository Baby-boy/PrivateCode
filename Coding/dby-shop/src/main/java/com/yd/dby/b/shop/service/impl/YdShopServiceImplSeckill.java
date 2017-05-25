package com.yd.dby.b.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperSeckill;
import com.yd.dby.b.shop.service.YdShopServiceSeckill;

@Service("_YdShopServiceLink")
public class YdShopServiceImplSeckill implements YdShopServiceSeckill {
	
	@Autowired
	private YdShopMapperSeckill ydShopMapperSeckill;

	@Override
	public Object lists() {
		return ydShopMapperSeckill.lists();
	}

	@Override
	public Object info(Integer id) {
		return ydShopMapperSeckill.info(id);
	}
	
	
}