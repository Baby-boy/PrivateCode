package com.yd.dby.d.seller.service;

import java.util.HashMap;

import com.yd.dby.d.seller.entity.YdSellerSeckillGoods;

public interface YdSellerServiceSeckill {

	/**
	 * 列表
	 * @param map
	 * @return
	 */
	Object lists(HashMap<String, Object> map);

	
	/**
	 * 取消
	 * @param sgId
	 * @return
	 */
	Object cancel(Integer sgId);


	/**
	 * 添加
	 * @param ydSellerSeckillGoods
	 * @return
	 */
	Object store(Integer depotId, float price, Integer num);
}
