package com.yd.dby.app.entity.vo;

/**
 * 说明: 流通置换店铺列表
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月8日 下午2:32:12
 * @Version:1.0
 */
public class StoreCirculationVo {

	private Integer storeId;// 店铺ID

	private String storeName;// 店铺名称

	private String storeCover;// 店铺封面图

	private Float storeAvgScore;// 店铺平均分(1-5星)

	private String distance;// 距离店铺实际距离

	private Double storeLongitude;// 店铺所在位置经度

	private Double storeLatitude;// 店铺所在位置纬度

	private String storeBanner;// 店铺banner图

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

	public String getStoreCover() {
		return storeCover;
	}

	public void setStoreCover(String storeCover) {
		this.storeCover = storeCover;
	}

	public Float getStoreAvgScore() {
		return storeAvgScore;
	}

	public void setStoreAvgScore(Float storeAvgScore) {
		this.storeAvgScore = storeAvgScore;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public Double getStoreLongitude() {
		return storeLongitude;
	}

	public void setStoreLongitude(Double storeLongitude) {
		this.storeLongitude = storeLongitude;
	}

	public Double getStoreLatitude() {
		return storeLatitude;
	}

	public void setStoreLatitude(Double storeLatitude) {
		this.storeLatitude = storeLatitude;
	}

	public String getStoreBanner() {
		return storeBanner;
	}

	public void setStoreBanner(String storeBanner) {
		this.storeBanner = storeBanner;
	}

}
