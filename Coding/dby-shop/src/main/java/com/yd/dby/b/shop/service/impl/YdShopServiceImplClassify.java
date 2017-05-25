package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperClassify;
import com.yd.dby.b.shop.service.YdShopServiceClassify;
import com.yd.dby.utils.algorithm.YdUtilAlgorithm;

@Service("_classify")
public class YdShopServiceImplClassify implements YdShopServiceClassify {
	@Autowired
	private YdShopMapperClassify ydShopMapperClassify;

	public List<HashMap<String, Object>> get(String cly_type) {
		return YdUtilAlgorithm.ListToTreeList(ydShopMapperClassify.get_many(cly_type));
	}
	
	
	/**
	 * 获取子分类
	 */
	@Override
	public Object getSon(Integer classifyId) {
		return ydShopMapperClassify.son(classifyId);
	}
}