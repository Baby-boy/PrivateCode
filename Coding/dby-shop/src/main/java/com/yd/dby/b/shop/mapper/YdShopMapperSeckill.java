package com.yd.dby.b.shop.mapper;

import java.util.List;

import com.yd.dby.d.seller.entity.YdSellerSeckill;

public interface YdShopMapperSeckill {

	List<YdSellerSeckill> lists();

	YdSellerSeckill info(Integer id);

}