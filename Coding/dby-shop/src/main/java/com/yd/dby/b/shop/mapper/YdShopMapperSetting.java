package com.yd.dby.b.shop.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdShopSetting;


public interface YdShopMapperSetting {

	YdShopSetting info(@Param("name") String name);
}