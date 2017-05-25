package com.yd.dby.app.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yd.dby.app.entity.YdCoupon;
import com.yd.dby.app.entity.para.CouponPara;
import com.yd.dby.app.entity.vo.UserCoupon;

public interface YdCouponMapper {
	int deleteByPrimaryKey(Integer couponId);

	int insert(YdCoupon record);

	int insertSelective(YdCoupon record);

	YdCoupon selectByPrimaryKey(Integer couponId);

	int updateByPrimaryKeySelective(YdCoupon record);

	int updateByPrimaryKey(YdCoupon record);

	List<UserCoupon> selectCouponList(@Param("para") CouponPara para);

	List<UserCoupon> selectCouponCanList(@Param("para") CouponPara para);
}