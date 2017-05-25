package com.yd.dby.b.shop.service;

import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

public interface YdShopServiceConf {
	
	HashMap<String, Object> get(@Param("type") String type);
}