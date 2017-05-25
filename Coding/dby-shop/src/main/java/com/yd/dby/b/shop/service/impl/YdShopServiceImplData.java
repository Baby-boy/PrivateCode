package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperData;
import com.yd.dby.b.shop.service.YdShopServiceData;
import com.yd.dby.utils.YdUtilTree2;

@Service("_cache")
public class YdShopServiceImplData implements YdShopServiceData {
	@Autowired
	private YdShopMapperData ydShopMapperAds;

	public List<HashMap<String, Object>> get(String cly_type) {
		return YdUtilTree2.toList(ydShopMapperAds.get_many(cly_type));
	}
	
	public List<HashMap<String, Object>> random(String cly_type) {
		return YdUtilTree2.toList(ydShopMapperAds.get_random(cly_type));
	}
	
	public HashMap<String, Object> only(String cly_type) {
		return ydShopMapperAds.get_only(cly_type);
	}
//	
//	public List<HashMap<String, Object>> list(String cly_type) {
//		return ydShopMapperAds.get_many(cly_type);
//	}
}