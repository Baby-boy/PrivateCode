package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperNav;
import com.yd.dby.b.shop.service.YdShopServiceNav;

@Service("_nav")
public class YdShopServiceImplNav implements YdShopServiceNav {
	@Autowired
	private YdShopMapperNav ydShopMapperNav;
	
	@Override
	public List<HashMap<String, Object>> getByType(String nav_type) {
		return ydShopMapperNav.getByType(nav_type);
	}
}