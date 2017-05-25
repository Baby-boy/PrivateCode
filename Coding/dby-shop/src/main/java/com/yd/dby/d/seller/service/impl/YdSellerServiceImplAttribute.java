package com.yd.dby.d.seller.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperAttribute;
import com.yd.dby.d.seller.service.YdSellerServiceAttribute;

@Service("_YdSellerServiceAttribute")
public class YdSellerServiceImplAttribute implements YdSellerServiceAttribute {


	@Autowired
	private YdSellerMapperAttribute ydSellerMapperAttribute;


	@Override
	public Map<String, Object> lists(HashMap<String, Object> request) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("data", ydSellerMapperAttribute.lists(request));
		return map;
	}


}