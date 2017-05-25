package com.yd.dby.b.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yd.dby.b.shop.mapper.YdShopMapperAddress;
import com.yd.dby.b.shop.mapper.YdShopMapperOrder;

@RestController
@RequestMapping(value = "/test")
public class YdShopControllerTest {

	@Autowired
	YdShopMapperOrder ydShopMapperOrder;
	@Autowired
	YdShopMapperAddress ydShopMapperAddress;

	@RequestMapping(value = "/coupon/select", method = { RequestMethod.GET, RequestMethod.POST })
	public Object coupon_select() {
		return ydShopMapperOrder.SelectCoupon(1, null);
	}

	@RequestMapping(value = "/order/select", method = { RequestMethod.GET, RequestMethod.POST })
	public Object order_select() {
		return ydShopMapperOrder.SelectGoods(1);
	}

	@RequestMapping(value = "/address/select", method = { RequestMethod.GET, RequestMethod.POST })
	public Object address_select() {
		return ydShopMapperAddress.select(1);
	}
}
