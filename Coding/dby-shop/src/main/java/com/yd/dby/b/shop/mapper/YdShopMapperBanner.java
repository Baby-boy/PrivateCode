package com.yd.dby.b.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.b.shop.entity.YdBanner;

public interface YdShopMapperBanner {

	List<YdBanner> lists(@Param("banner_type") String banner_type);

	
	YdBanner findByType(@Param("banner_type") String banner_type);
}