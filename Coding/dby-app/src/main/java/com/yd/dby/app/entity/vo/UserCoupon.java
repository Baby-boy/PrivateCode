package com.yd.dby.app.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.yd.dby.app.entity.YdCoupon;

/**
 * 说明: 用户结算时可用的优惠券
 * 
 * @Author: lgl(lgl@zwzyd.com)
 * @date:2017年2月16日 上午10:45:57
 * @Version:1.0
 */
public class UserCoupon {

	// 商家ID
	private Integer storeId;

	// 对应商家可用的优惠券
	private List<YdCoupon> coupons = new ArrayList<>();

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public List<YdCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<YdCoupon> coupons) {
		this.coupons = coupons;
	}
}
