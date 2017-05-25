package com.yd.dby.b.shop.service;

import java.util.HashMap;

public interface YdShopServiceOrder {

	/*
	// 立即支付
	Object PayNow(HashMap<String, Object> request) throws Exception;

	// 支付-购物车选中
	Object PayCartChooses(HashMap<String, Object> request) throws Exception;

	// 支付-未付款订单
	Object PayNoPay(HashMap<String, Object> request) throws Exception;
*/
	
	// 支付-未付款订单
	Object SelcetGoods(HashMap<String, Object> request) throws Exception;
	
	// 支付-未付款订单
	Object SelectCoupon(HashMap<String, Object> request) throws Exception;
	
	
}

