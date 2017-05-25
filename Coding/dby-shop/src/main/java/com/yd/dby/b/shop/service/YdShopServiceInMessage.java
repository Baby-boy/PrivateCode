package com.yd.dby.b.shop.service;

import java.util.Map;

public interface YdShopServiceInMessage {

	/**
	 * @param im_store_id (留言店铺id)
	 * @param im_message (留言内容)
	 * @param im_depot_id(商品id)
	 * @return
	 * 方法作用(添加普通商品留言)
	 */
	Object inMessage(Integer im_store_id, Integer im_goods_id, String im_message);

	/**
	 * @param request
	 * @return
	 * 方法作用(添加ctc商品留言)
	 */
	Object ctcSave(Map<String, Object> request);

	/**
	 * @param request
	 * @return
	 * 方法作用(回复留言)
	 */
	Object ctcRecive(Map<String, Object> request);

}
