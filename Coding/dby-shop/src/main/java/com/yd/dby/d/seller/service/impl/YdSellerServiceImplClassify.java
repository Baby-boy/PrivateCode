package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperClassify;
import com.yd.dby.d.seller.service.YdSellerServiceClassify;


@Service("_YdSellerServiceClassify")
public class YdSellerServiceImplClassify implements YdSellerServiceClassify {
	
	@Autowired
	private YdSellerMapperClassify ydWebMapperClassify;
	
	@Override
	public Map<String, Object> son(HashMap<String, Object> request) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydWebMapperClassify.son(request));
		return map;
	}

}