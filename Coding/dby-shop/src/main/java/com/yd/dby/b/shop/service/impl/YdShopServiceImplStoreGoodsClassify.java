package com.yd.dby.b.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.dby.b.shop.mapper.YdShopMapperStoreGoodsClassify;
import com.yd.dby.b.shop.service.YdShopServiceStoreGoodsClassify;
import com.yd.dby.utils.algorithm.YdUtilAlgorithm;


@Service("_YdShopServiceStoreGoodsClassify")
public class YdShopServiceImplStoreGoodsClassify implements YdShopServiceStoreGoodsClassify {

	@Autowired
	private YdShopMapperStoreGoodsClassify ydShopMapperStoreGoodsClassify;
	
	@Override
	public Object lists(Integer store_id) {
		return YdUtilAlgorithm.ListToTreeList( ydShopMapperStoreGoodsClassify.lists(store_id) );
	}

}