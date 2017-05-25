package com.yd.dby.d.seller.entity;

public class YdSellerGoodsVo extends YdSellerGoods {

	private Integer depot_id;//养护套餐的仓库 id
	private Integer depot_price;//掩护套餐中的仓库商品的价格
	public Integer getDepot_id() {
		return depot_id;
	}
	public void setDepot_id(Integer depot_id) {
		this.depot_id = depot_id;
	}
	public Integer getDepot_price() {
		return depot_price;
	}
	public void setDepot_price(Integer depot_price) {
		this.depot_price = depot_price;
	}
	
	
}
