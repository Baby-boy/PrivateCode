package com.yd.dby.app.entity.vo;

/**
 * 说明: 店铺详情
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月8日 下午2:32:47
 * @Version:1.0
 */
public class StoreVo {

	private Integer favId;// 收藏ID(不为null 则是收藏)

	private Integer storeId;// 店铺ID

	private String storeCover;// 店铺封面图

	private String storeBanner;// 店铺banner图

	private String storeName;// 店铺名称

	private Float storeAvgScore;// 店铺平均分(1-5星)

	private Integer storeTotalFav;// 店铺收藏总数

	private String storeDescription;// 店铺简介

	private String storeTelephone;// 店铺座机

	private String storeMobile;// 店铺手机

	private String storeQQ;// 店铺QQ

	private String storeAddress;// 店铺地址

	public Integer getFavId() {
		return favId;
	}

	public void setFavId(Integer favId) {
		if (favId == null) {
			this.favId = 0;
		} else {
			this.favId = favId;
		}
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreCover() {
		return storeCover;
	}

	public void setStoreCover(String storeCover) {
		this.storeCover = storeCover;
	}

	public String getStoreBanner() {
		return storeBanner;
	}

	public void setStoreBanner(String storeBanner) {
		this.storeBanner = storeBanner;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Float getStoreAvgScore() {
		return storeAvgScore;
	}

	public void setStoreAvgScore(Float storeAvgScore) {
		this.storeAvgScore = storeAvgScore;
	}

	public Integer getStoreTotalFav() {
		return storeTotalFav;
	}

	public void setStoreTotalFav(Integer storeTotalFav) {
		this.storeTotalFav = storeTotalFav;
	}

	public String getStoreDescription() {
		return storeDescription;
	}

	public void setStoreDescription(String storeDescription) {
		this.storeDescription = storeDescription;
	}

	public String getStoreTelephone() {
		return storeTelephone;
	}

	public void setStoreTelephone(String storeTelephone) {
		this.storeTelephone = storeTelephone;
	}

	public String getStoreMobile() {
		return storeMobile;
	}

	public void setStoreMobile(String storeMobile) {
		this.storeMobile = storeMobile;
	}

	public String getStoreQQ() {
		return storeQQ;
	}

	public void setStoreQQ(String storeQQ) {
		this.storeQQ = storeQQ;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

}
