package com.yd.dby.app.entity.vo;

import com.yd.dby.app.entity.YdDepot;

public class DepotAndGoodsVo extends YdDepot {

	private String goodsName;

	private String goodsSummary;

	private String goodsCover;

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

	public String getGoodsCover() {
		return goodsCover;
	}

	public void setGoodsCover(String goodsCover) {
		this.goodsCover = goodsCover;
	}
}
