package com.yd.dby.app.mapper;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdReceiveCoupon;

public interface YdReceiveCouponMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(YdReceiveCoupon record);

	int insertSelective(YdReceiveCoupon record);

	YdReceiveCoupon selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(YdReceiveCoupon record);

	int updateByPrimaryKey(YdReceiveCoupon record);

	int deleteByUserIdAndCouponId(@Param("userId") Integer userId, @Param("couponId") Integer couponId);
}