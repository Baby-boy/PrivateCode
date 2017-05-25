package com.yd.dby.d.seller.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.d.seller.entity.YdSellerSeckillGoods;


public interface YdSellerMapperSeckill {
	
	List<YdSellerSeckillGoods> lists(HashMap<String, Object> map);
	
	YdSellerSeckillGoods info(@Param("sgId") Integer sgId, @Param("storeId") Integer storeId);
	
	Integer delete(@Param("sgId") Integer sgId, @Param("storeId") Integer storeId);

	Integer store(YdSellerSeckillGoods ydSellerSeckillGoods);

	Integer total(@Param("storeId") Integer storeId);
}