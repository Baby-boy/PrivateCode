package com.yd.dby.b.shop.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperCoupon;
import com.yd.dby.b.shop.service.YdShopServiceCoupon;

@Service("_Coupon")
public class YdShopServiceImplCoupon implements YdShopServiceCoupon {
	@Autowired
	private YdShopMapperCoupon ydShopMapperCoupon;

	@Override
	public List<HashMap<String, Object>> get_many(Integer user_id, Integer isexpired, List<Integer> store_ids) {
		return ydShopMapperCoupon.get_many(user_id, isexpired, store_ids);
	}

}