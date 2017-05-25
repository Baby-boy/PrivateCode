package com.yd.dby.app.entity.para;

/**
 * 说明: 懒鱼首页
 * 
 * @Author: fqh(fqh@zwzyd.com)
 * @date:2017年2月15日 下午3:02:42
 * @Version:1.0
 */
public class CtcHomePara extends BasePara {

	private Integer ccId;// 分类ID

	// 筛选字段
	private Integer ctcPriceKey;// 商品价格
	private Integer ctcTotalMessageKey;// 评论数
	private Integer ctcTotalFavKey;// 收藏数

	// 筛选的价格区间字段
	private Double lowPrice;
	private Double highPrice;

	private Integer ccPid;// 分类父ID

	public Integer getCcId() {
		return ccId;
	}

	public void setCcId(Integer ccId) {
		this.ccId = ccId;
	}

	public Integer getCtcPriceKey() {
		return ctcPriceKey;
	}

	public void setCtcPriceKey(Integer ctcPriceKey) {
		this.ctcPriceKey = ctcPriceKey;
	}

	public Integer getCtcTotalMessageKey() {
		return ctcTotalMessageKey;
	}

	public void setCtcTotalMessageKey(Integer ctcTotalMessageKey) {
		this.ctcTotalMessageKey = ctcTotalMessageKey;
	}

	public Integer getCtcTotalFavKey() {
		return ctcTotalFavKey;
	}

	public void setCtcTotalFavKey(Integer ctcTotalFavKey) {
		this.ctcTotalFavKey = ctcTotalFavKey;
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

	public Integer getCcPid() {
		return ccPid;
	}

	public void setCcPid(Integer ccPid) {
		this.ccPid = ccPid;
	}

}
