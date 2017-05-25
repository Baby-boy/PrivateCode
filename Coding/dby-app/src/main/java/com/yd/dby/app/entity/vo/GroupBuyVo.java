package com.yd.dby.app.entity.vo;

import java.math.BigDecimal;

public class GroupBuyVo {

	private Integer depotId;

	private String goodsCover;

	private String goodsName;

	private String goodsSummary;

	// 原价
	private BigDecimal depotOriginalPrice;

	// 当前价
	private BigDecimal depotPrice;

	// 开团人数
	private Integer gbgAlreadyNum;

	public Integer getDepotId() {
		return depotId;
	}

	public void setDepotId(Integer depotId) {
		this.depotId = depotId;
	}

	public String getGoodsCover() {
		return goodsCover;
	}

	public void setGoodsCover(String goodsCover) {
		this.goodsCover = goodsCover;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSummary() {
		return goodsSummary;
	}

	public void setGoodsSummary(String goodsSummary) {
		this.goodsSummary = goodsSummary;
	}

	public BigDecimal getDepotOriginalPrice() {
		return depotOriginalPrice;
	}

	public void setDepotOriginalPrice(BigDecimal depotOriginalPrice) {
		this.depotOriginalPrice = depotOriginalPrice;
	}

	public BigDecimal getDepotPrice() {
		return depotPrice;
	}

	public void setDepotPrice(BigDecimal depotPrice) {
		this.depotPrice = depotPrice;
	}

	public Integer getGbgAlreadyNum() {
		return gbgAlreadyNum;
	}

	public void setGbgAlreadyNum(Integer gbgAlreadyNum) {
		this.gbgAlreadyNum = gbgAlreadyNum;
	}

}
