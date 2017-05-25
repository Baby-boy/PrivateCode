package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperDict;
import com.yd.dby.b.shop.service.YdShopServiceDict;

@Service("_dict")
public class YdShopServiceImplDict implements YdShopServiceDict {
	@Autowired
	private YdShopMapperDict ydShopMapperDict;

	@Override
	public List<HashMap<String, Object>> list(String dict_type) {
		return ydShopMapperDict.get_many(dict_type);
	}

	@Override
	public List<HashMap<String, Object>> list(String dict_type, String dict_pid) {
		return ydShopMapperDict.get_many_simple(dict_type, dict_pid);
	}
	
	@Override
	public List<HashMap<String, Object>> many(String dict_type) {
		return ydShopMapperDict.many(dict_type);
	}

}