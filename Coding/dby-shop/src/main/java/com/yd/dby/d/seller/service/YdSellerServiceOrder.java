package com.yd.dby.d.seller.service;

import java.util.HashMap;
import java.util.Map;

public interface YdSellerServiceOrder {
	
	/*商家订单列表*/
	Map<String, Object> index(HashMap<String, Object> request);
	
	
	/*商家订单 - 发货页面*/
	Map<String, Object> delivery(HashMap<String, Object> request);
	
	
	/**
	 * 更新收货地址
	 */
	Object updateTake(Integer order_id, String text);


	/**
	 * 发货
	 */
	Object deliver(Integer order_id, String shipping_express,
			String logis_code,
			String shipping_code, String deliver_explain,
			String shipping_address, Integer shipping_id);
	
	
	/**
	 * 订单详情
	 */
	Object info(Integer id);
	
	
	/**
	 * 订单商品
	 */
	Object orderGoods(Integer id);
	
}
