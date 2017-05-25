package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperAds;
import com.yd.dby.b.shop.service.YdShopServiceAds;
import com.yd.dby.utils.algorithm.YdUtilAlgorithm;

@Service("_YdShopServiceAds")
public class YdShopServiceImplAds implements YdShopServiceAds {
	@Autowired
	private YdShopMapperAds ydShopMapperAds;

	public List<HashMap<String, Object>> get_many_map(@Param("cly_type") String cly_type) {
		return YdUtilAlgorithm.ListToTreeList(ydShopMapperAds.get_many(cly_type));
	}

	public List<HashMap<String, Object>> get_many_list(@Param("cly_type") String cly_type) {
		return ydShopMapperAds.get_many(cly_type);
	}
	
	public List<HashMap<String, Object>> get_list(@Param("cly_type") String cly_type) {
		return ydShopMapperAds.get_list(cly_type);
	}
}