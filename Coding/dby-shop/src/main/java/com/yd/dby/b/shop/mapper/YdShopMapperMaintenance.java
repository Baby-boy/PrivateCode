package com.yd.dby.b.shop.mapper;

import java.util.List;

import com.yd.dby.d.seller.entity.YdSellerGoodsVo;

public interface YdShopMapperMaintenance {

	/**
	 * @return
	 * 方法作用(查询所有养护套餐)
	 */
	List<YdSellerGoodsVo> queryAllMaintenance(Integer to);

	/**
	 * @return
	 * 方法作用(查询养护套餐的总数)
	 */
	Integer queryAllNum();

}
