package com.yd.dby.app.entity.para;

import java.util.ArrayList;
import java.util.List;

public class CouponPara {

	// 购物车里所有商铺ID 例："1,2,3"
	private String storeIdStr;

	// 购物车里每个商铺的付款金额，与上面的商铺ID一一对应，例："100,200,300"
	private String moneyStr;

	private Integer userId;

	private List<Object> ids = new ArrayList<Object>();

	public String getStoreIdStr() {
		return storeIdStr;
	}

	public void setStoreIdStr(String storeIdStr) {
		this.storeIdStr = storeIdStr;
	}

	public String getMoneyStr() {
		return moneyStr;
	}

	public void setMoneyStr(String moneyStr) {
		this.moneyStr = moneyStr;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public List<Object> getIds() {
		return ids;
	}

	public void setIds(List<Object> ids) {
		this.ids = ids;
	}

}
