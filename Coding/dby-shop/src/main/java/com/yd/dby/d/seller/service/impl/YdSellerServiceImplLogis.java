package com.yd.dby.d.seller.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.d.seller.mapper.YdSellerMapperLogis;
import com.yd.dby.d.seller.service.YdSellerServiceLogis;


@Service("_YdSellerServiceLogis")
public class YdSellerServiceImplLogis implements YdSellerServiceLogis {
	
	@Autowired
	private YdSellerMapperLogis ydSellerMapperLogis;
	
	@Override
	public Object lists() {
		return ydSellerMapperLogis.lists();
	}

}