package com.yd.dby.app.entity.para;

public class StorePara extends BasePara {

	private Integer storeId;// 店铺ID

	private Double longitude;// 当前用户所在位置经度

	private Double latitude;// 当前用户所在位置纬度

	private String keywords;// 搜索关键字

	private Integer userId;// 当前用户ID

	private Integer scId;// 分类ID

	// 筛选字段
	private Integer goodsPriceKey;// 商品价格
	private Integer goodRateKey;// 好评率
	private Integer saleNumKey;// 销售额

	// 筛选的价格区间字段
	private Double lowPrice;
	private Double highPrice;

	private Integer pid;// 分类父ID

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getScId() {
		return scId;
	}

	public void setScId(Integer scId) {
		this.scId = scId;
	}

	public Integer getGoodsPriceKey() {
		return goodsPriceKey;
	}

	public void setGoodsPriceKey(Integer goodsPriceKey) {
		this.goodsPriceKey = goodsPriceKey;
	}

	public Integer getGoodRateKey() {
		return goodRateKey;
	}

	public void setGoodRateKey(Integer goodRateKey) {
		this.goodRateKey = goodRateKey;
	}

	public Integer getSaleNumKey() {
		return saleNumKey;
	}

	public void setSaleNumKey(Integer saleNumKey) {
		this.saleNumKey = saleNumKey;
	}

	public Double getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(Double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public Double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
