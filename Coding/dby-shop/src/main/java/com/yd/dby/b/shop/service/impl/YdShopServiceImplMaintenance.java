package com.yd.dby.b.shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperMaintenance;
import com.yd.dby.b.shop.service.YdShopServiceMaintenance;
import com.yd.dby.d.seller.entity.YdSellerGoodsVo;

@Service("_YdShopServiceMaintenance")
public class YdShopServiceImplMaintenance implements YdShopServiceMaintenance {

	@Autowired
	private YdShopMapperMaintenance ydShopMapperMaintenance;
	
	/**
	 * 查询所有的养护套餐
	 */
	@Override
	public List<YdSellerGoodsVo> queryAllMaintenance(Integer to) {
		List<YdSellerGoodsVo> goodsList = ydShopMapperMaintenance.queryAllMaintenance(to);
		return goodsList;
	}

	/**
	 * 查询养护套餐的总数
	 */
	@Override
	public Integer queryAllNum() {
		Integer sum = ydShopMapperMaintenance.queryAllNum();
		return sum;
	}
	
	
}