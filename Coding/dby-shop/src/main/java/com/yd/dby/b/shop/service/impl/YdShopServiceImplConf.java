package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperDict;
import com.yd.dby.b.shop.service.YdShopServiceConf;
import com.yd.dby.utils.algorithm.YdUtilAlgorithm;

@Service("_conf")
public class YdShopServiceImplConf implements YdShopServiceConf {
	@Autowired
	private YdShopMapperDict ydShopMapperDict;

	public HashMap<String, Object> get(String cly_type) {
		return YdUtilAlgorithm.ListToMap(ydShopMapperDict.many(cly_type));
	}

}