package com.yd.dby.app.entity.para;

import com.yd.dby.app.entity.YdOrderGoods;

public class OrderAddDBGoodsPara extends YdOrderGoods {

	private Integer depotStock;

	private Integer cartId;

	public Integer getDepotStock() {
		return depotStock;
	}

	public void setDepotStock(Integer depotStock) {
		this.depotStock = depotStock;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

}
