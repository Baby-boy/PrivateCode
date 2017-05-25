package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperCtc;
import com.yd.dby.b.shop.mapper.YdShopMapperCtcClass;
import com.yd.dby.b.shop.service.YdShopServiceCtcClass;
import com.yd.dby.utils.algorithm.YdUtilAlgorithm;

@Service("_YdShopServiceCtcClass")
public class YdShopServiceImplCtcClass implements YdShopServiceCtcClass {
	@Autowired
	private YdShopMapperCtcClass ydShopMapperCtcClass;

	@Autowired
	private YdShopMapperCtc ydShopMapperCtc;
	
	
	public Object listTreeGoods() {
		List<HashMap<String, Object>> listCtcClass = ydShopMapperCtcClass.listTree();
		
		for (HashMap<String, Object> hashMap : listCtcClass) {
			if ( !hashMap.get("pid").equals(0) ) {
				hashMap.put("goods", ydShopMapperCtc.listByClassId2( Integer.parseInt( hashMap.get("id").toString() ) ));
			}
		}
		return YdUtilAlgorithm.ListToTreeList(listCtcClass);
	}

	
	public Object listTree() {
		return YdUtilAlgorithm.ListToTreeList(ydShopMapperCtcClass.listTree());
	}
}