package com.yd.dby.b.shop.mapper;

import com.yd.dby.b.shop.entity.YdOrderGoods;

public interface YdShopMapperOrderGoods {
	
	/**
	 * 插入订单商品
	 */
	Integer store(YdOrderGoods ydOrderGoods);
}