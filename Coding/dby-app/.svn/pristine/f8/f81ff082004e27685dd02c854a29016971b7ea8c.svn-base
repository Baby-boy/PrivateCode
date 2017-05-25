package com.yd.dby.app.entity.vo;

import java.util.ArrayList;
import java.util.List;

public class CartVo {

	private Integer storeId;// 店铺ID

	private String storeName;// 店铺名称

	private String storeBanner;// 店铺banner图

	private String cartStoreName;// 店铺名称(购物车表中存储数据)

	private Integer storeExpire;// 店铺是否过期

	private List<CartStoreGoodsVo> cartStoreGoodsList = new ArrayList<>();

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreBanner() {
		return storeBanner;
	}

	public void setStoreBanner(String storeBanner) {
		this.storeBanner = storeBanner;
	}

	public List<CartStoreGoodsVo> getCartStoreGoodsList() {
		return cartStoreGoodsList;
	}

	public void setCartStoreGoodsList(List<CartStoreGoodsVo> cartStoreGoodsList) {
		this.cartStoreGoodsList = cartStoreGoodsList;
	}

	public String getCartStoreName() {
		return cartStoreName;
	}

	public void setCartStoreName(String cartStoreName) {
		this.cartStoreName = cartStoreName;
	}

	public Integer getStoreExpire() {
		return storeExpire;
	}

	public void setStoreExpire(Integer storeExpire) {
		if (null == storeExpire) {
			this.storeExpire = 0;// 过期
		} else {
			this.storeExpire = 1;// 未过期
		}
	}

}
